package com.hkm.dis;

import com.hkm.disqus.api.ApiConfig;

import retrofit.RestAdapter;

/**
 * Created by zJJ on 11/6/2015.
 */
public class DqUtil {
    public static ApiConfig genConfig() {
        ApiConfig conf = new ApiConfig(
                BuildConfig.DISQUS_API_KEY,
                BuildConfig.DISQUS_DEFAULT_ACCESS,
                RestAdapter.LogLevel.BASIC);
        conf.setApiSecret(BuildConfig.DISQUS_SECRET);
        conf.setRedirectUri(BuildConfig.DISQUS_REDIRECT_URI);
        return conf;
    }
}
