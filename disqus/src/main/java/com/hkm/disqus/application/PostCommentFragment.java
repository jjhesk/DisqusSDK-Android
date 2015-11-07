package com.hkm.disqus.application;

import android.app.Fragment;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.hkm.disqus.DisqusClient;
import com.hkm.disqus.api.ApiConfig;
import com.hkm.disqus.api.exception.ApiException;
import com.hkm.disqus.api.model.Response;
import com.hkm.disqus.api.model.oauth2.AccessToken;
import com.hkm.disqus.api.model.posts.Post;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by zJJ on 11/7/2015.
 */
public abstract class PostCommentFragment extends Fragment {
    public static String TAG = "debugDisqusComment";

    protected abstract ApiConfig getConfiguration();

    /**
     * debug log line
     *
     * @param newline the string
     */
    protected void addLine(String newline) {

    }

    private Callback<Response<List<Post>>> response_cb = new Callback<com.hkm.disqus.api.model.Response<List<Post>>>() {
        @Override
        public void success(com.hkm.disqus.api.model.Response<List<Post>> posts, retrofit.client.Response response) {
            com.hkm.disqus.api.model.Response<List<Post>> d = posts;
            Log.d(TAG, "now its working now");
            addLine(response.getBody() + " and the " + d.data.size() + " items were found");
        }

        @Override
        public void failure(RetrofitError error) {
            Log.d(TAG, error.getMessage());
            addLine(error.getBody().toString());
        }
    };

    private Callback<com.hkm.disqus.api.model.Response<Post>> return_cb = new Callback<com.hkm.disqus.api.model.Response<Post>>() {
        @Override
        public void success(com.hkm.disqus.api.model.Response<Post> postResponse, retrofit.client.Response response) {
            addLine(response.getBody().toString());
        }

        @Override
        public void failure(RetrofitError error) {
            addLine(error.getUrl().toString() + "\n" + error.getMessage());
            addLine("===============================================");
        }
    };


    private DisqusClient getClient() {
        return DisqusClient.getInstance(getActivity(), getConfiguration());
    }


    protected void postPost(String postmessage, String thread_id) {
        if (getClient().getAuthManager().isAuthenticated()) {
            getClient().postPost(postmessage, thread_id, return_cb);
        } else {
            getClient().loginNow(getActivity());
        }

    }

    protected void getPost(String threadID) {
        //"1008680 http://hypebeast.com/?p=1008680"
        try {
            getClient().getComments(threadID, response_cb);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

}
