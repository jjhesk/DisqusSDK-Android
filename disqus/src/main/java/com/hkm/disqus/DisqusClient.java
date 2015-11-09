package com.hkm.disqus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hkm.disqus.api.ApiClient;
import com.hkm.disqus.api.ApiConfig;
import com.hkm.disqus.api.AuthTokenServiceManager;
import com.hkm.disqus.api.exception.ApiException;
import com.hkm.disqus.api.model.Response;
import com.hkm.disqus.api.model.posts.Post;
import com.hkm.disqus.api.resources.AccessTokenService;
import com.hkm.disqus.application.AuthorizeActivity;

import java.util.List;

import retrofit.Callback;

/**
 * Created by hesk on 5/6/15.
 */
public class DisqusClient extends ApiClient {
    public static final int authorization_intent_id = 9392;
    private Context mcontent;
    private static AuthTokenServiceManager instance_am;
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

    public static DisqusClient getInstance() throws NullPointerException {
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
        if (instance_am == null) {
            instance_am = createAuthenticationManager(context);
        }
    }

    public AuthTokenServiceManager getAuthManager() {
        return instance_am;
    }

    public void getComments(String comment_id, Callback<Response<List<Post
            >>> cb) throws ApiException {
        createThreads().listPostByIDAsync(comment_id, getConfiguration().getForumName(), cb);
    }

    public void postPost(String text, String thread_id, Callback<Response<Post>> cbpost) {
        try {
            createPosts().create(text, thread_id, cbpost);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public void loginNow(Activity activity) {
        Intent in = new Intent(activity, AuthorizeActivity.class);
        Bundle h = getConfiguration().getLogInBundle();
        h.putBoolean(AuthorizeActivity.EXTRA_STATUS_BAR_TOP, false);
        in.putExtras(h);
        activity.startActivityForResult(in, authorization_intent_id);
    }
}
