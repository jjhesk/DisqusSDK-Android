package com.hkm.disqus.api.retrofitworker;


import com.hkm.disqus.api.ApiConfig;

public class RequestInterceptor implements retrofit.RequestInterceptor {
    private static final String PARAM_API_KEY = "api_key";
    private static final String PARAM_ACCESS_TOKEN = "access_token";
    private static ApiConfig confg;

    public RequestInterceptor(final ApiConfig configuration) {
        confg = configuration;
    }

    private String accessToken;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public void intercept(RequestFacade request) {

        /// request.addQueryParam(PARAM_API_KEY, confg.getApiKey());

        // add the authenticated user to the request if available
        if (accessToken != null) {
            //    request.addQueryParam(PARAM_ACCESS_TOKEN, accessToken);
        }
    }
}
