package com.hkm.disqus.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.hkm.disqus.api.exception.ApiException;
import com.hkm.disqus.api.model.Response;
import com.hkm.disqus.api.model.posts.Post;
import com.hkm.disqus.application.AuthorizeActivity;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;

/**
 * Created by hesk on 5/6/15.
 */
public class DisqusClient extends ApiClient {
    public static final int authorization_intent_id = 9392;
    private Context mcontent;
    private static AuthMgr instance_auth;

    private static DisqusClient main_instance;

    /**
     * @param c    context
     * @param conf apiconfiguration
     * @return the disqus client instance
     */

    public static DisqusClient getInstance(Context c, ApiConfig conf) {
        if (main_instance == null) {
            main_instance = new DisqusClient(conf, c);
        }
        return main_instance;
    }

    /**
     * THe content
     *
     * @param config  the collection of configuration
     * @param context the context uneeded for your dailt vntry
     */
    public DisqusClient(ApiConfig config, Context context) {
        super(config);
        mcontent = context;
        if (instance_auth == null) {
            instance_auth = createAuthenticationManager(context);
        }
    }

    public AuthMgr getAuthManager() {
        return instance_auth;
    }

    public void getComments(String comment_id, Callback<Response<List<Post
            >>> cb) throws ApiException {
        createThreads().listPostByIDAsync(comment_id, "hypebeast", cb);
    }

    public void postPost(String text, String thread_id, Callback<Response<Post>> cbpost) {
        try {
            createPosts().create(text, thread_id, cbpost);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public  <T extends AuthorizeActivity> void loginNow(final Class<T> login_class, AppCompatActivity activity) {
        Intent in = new Intent(activity, login_class);
        in.putExtras(getConfiguration().getLogInBundle());
        activity.startActivityForResult(in, authorization_intent_id);
    }

    /**
     * create and return picasso
     *
     * @return the te ciea
     */
    public Picasso providesPicasso() {
        return new Picasso.Builder(mcontent)
                .downloader(new OkHttpDownloader(mcontent))
                .memoryCache(new LruCache(mcontent))
                .build();
    }
}
