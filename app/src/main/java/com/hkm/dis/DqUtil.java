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
        /**
         * https://github.com/101medialab/Hypetrak-iOS-v2/blob/bbc3afc1349f49910c8b8f69ea2944f5440fc898/Hypetrak-iOS-v2/Model/ServerRequestManager.swift
         */

        /**
         *
         * reference from iOS configurations
         * let urlRequestLink = "https://disqus.com/api/3.0/threads/set.json?forum=\(forumShortName)&api_key=\(apiKey)&thread=ident:\(postID)%20\(domain)/?p=\(postID)"
         */

        conf
                .setForumName("hypebeast")
                .setApiSecret(BuildConfig.DISQUS_SECRET)
                .setRedirectUri(BuildConfig.DISQUS_REDIRECT_URI);


        return conf;
    }
}
