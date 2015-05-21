package com.hkm.dis;

import android.app.Application;

import com.hkm.disqus.api.ApiClient;
import com.hkm.disqus.api.ApiConfig;
import com.hkm.disqus.api.exception.ApiException;
import com.hkm.disqus.api.model.Response;
import com.hkm.disqus.api.model.posts.Post;
import com.hkm.disqus.api.resources.Threads;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by hesk on 21/5/15.
 */
public class applicationbase extends Application {

    private ApiClient setup;

    @Override
    public void onCreate() {
        ApiConfig apiConfig = new ApiConfig("vxtI1dx0za2CziQI4cTKHiwXEf4lxpck218Zh6u62qYW5TRizhQsyPtRItpo48m3", RestAdapter.LogLevel.BASIC);
        apiConfig.setApiSecret("6BxCbw7u0SUZOT308nCTO9q6Lc9nPNuGsNTOV7GtRrpdk4UdiBeDcMI4wblK62Dq");
        setup = new ApiClient(apiConfig);
    }

    public void getComments(String comment_id, Callback<Response<List<Post
            >>> cb) throws ApiException {
        setup.createThreads().listPostByIDAsync(comment_id, "hypebeast", cb);
    }
}
