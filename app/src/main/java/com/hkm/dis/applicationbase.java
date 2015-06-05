package com.hkm.dis;

import android.app.Application;
import android.widget.Toast;

import com.hkm.disqus.api.ApiClient;
import com.hkm.disqus.api.ApiConfig;
import com.hkm.disqus.api.AuthMgr;
import com.hkm.disqus.api.exception.ApiException;
import com.hkm.disqus.api.model.Response;
import com.hkm.disqus.api.model.posts.Post;
import com.hkm.disqus.api.resources.Posts;

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
    public final static String secret = "uQqUIkGw9ugORF58WdqHYP24eVHSmU5QvzEv2bxXDiKQtPs7kfrBRzcSqBoHBSNu";
    //"uQqUIkGw9ugORF58WdqHYP24eVHSmU5QvzEv2bxXDiKQtPs7kfrBRzcSqBoHBSNu";
    //"6BxCbw7u0SUZOT308nCTO9q6Lc9nPNuGsNTOV7GtRrpdk4UdiBeDcMI4wblK62Dq";
    public final static String redirecturi = "http://hypebeast.com";
    public final static String redirecturidisqushbcallback = "disqus-hb-cb://hypebeast.com";
    public static ApiConfig conf;
    public static AuthMgr authmanager;

    @Override
    public void onCreate() {
        conf = new ApiConfig(applicationKey, default_access, RestAdapter.LogLevel.BASIC);
        conf.setApiSecret(secret);
        conf.setRedirectUri(redirecturi);
        setup = new ApiClient(conf);
        authmanager = setup.createAuthenticationManager(this);
    }

    public AuthMgr getManager() {
        return authmanager;
    }

    public ApiConfig getConf() {
        return conf;
    }

    public void getComments(String comment_id, Callback<Response<List<Post
            >>> cb) throws ApiException {
        setup.createThreads().listPostByIDAsync(comment_id, "hypebeast", cb);
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
