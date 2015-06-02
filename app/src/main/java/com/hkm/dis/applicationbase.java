package com.hkm.dis;

import android.app.Application;
import android.widget.EditText;
import android.widget.Toast;

import com.hkm.disqus.api.ApiClient;
import com.hkm.disqus.api.ApiConfig;
import com.hkm.disqus.api.exception.ApiException;
import com.hkm.disqus.api.model.Response;
import com.hkm.disqus.api.model.oauth2.AccessToken;
import com.hkm.disqus.api.model.posts.Post;
import com.hkm.disqus.api.resources.Posts;
import com.hkm.disqus.api.resources.Threads;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by hesk on 21/5/15.
 */
public class applicationbase extends Application {

    private ApiClient setup;
    //public api key

    public final static String applicationKey = "vxtI1dx0za2CziQI4cTKHiwXEf4lxpck218Zh6u62qYW5TRizhQsyPtRItpo48m3";
    // "vxtI1dx0za2CziQI4cTKHiwXEf4lxpck218Zh6u62qYW5TRizhQsyPtRItpo48m3";
    //  "9bRtN8ApvO1tSu5l9XOIsmoRNbJtNw7c7rsV7D3O0t4N6PygFLz6keC4sKPXiKEu";
    public final static String login_api_key = "9bRtN8ApvO1tSu5l9XOIsmoRNbJtNw7c7rsV7D3O0t4N6PygFLz6keC4sKPXiKEu";
    public final static String default_access = "a2ac478e0508499db6c794a1a8cb2c10";
    public final static String secret = "6BxCbw7u0SUZOT308nCTO9q6Lc9nPNuGsNTOV7GtRrpdk4UdiBeDcMI4wblK62Dq";
    //"uQqUIkGw9ugORF58WdqHYP24eVHSmU5QvzEv2bxXDiKQtPs7kfrBRzcSqBoHBSNu";
    //"6BxCbw7u0SUZOT308nCTO9q6Lc9nPNuGsNTOV7GtRrpdk4UdiBeDcMI4wblK62Dq";
    public final static String redirecturi = "http://hypebeast.com";

    @Override
    public void onCreate() {
        ApiConfig apiConfig = new ApiConfig(applicationKey, default_access, RestAdapter.LogLevel.BASIC);
        apiConfig.setApiSecret(secret);
        setup = new ApiClient(apiConfig);
    }

    public void afterLogin(AccessToken token) {
        ApiConfig apiConfig = new ApiConfig(
                applicationKey,
                token.accessToken,
                RestAdapter.LogLevel.BASIC);
        apiConfig.setApiSecret(secret);
        setup = new ApiClient(apiConfig);
    }

    public void getComments(String comment_id, Callback<Response<List<Post
            >>> cb) throws ApiException {
        setup.createThreads().listPostByIDAsync(comment_id, "hypebeast", cb);
    }

    public ApiClient getsetup() {
        return setup;
    }

    public Posts beginPostTransaction() {
        return setup.createPosts();
    }

    public void sso() {
        try {
            setup.createApplications().listUsage();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }
    }
}
