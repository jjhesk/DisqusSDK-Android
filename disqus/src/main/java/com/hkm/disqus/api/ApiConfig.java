/*
 * Copyright 2014 Phil Bayfield
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hkm.disqus.api;

import static retrofit.RestAdapter.LogLevel;

/**
 * Configuration options
 */
public class ApiConfig {

    /**
     * Api key
     */
    private String mApiKey;

    /**
     * Api secret
     */
    private String mApiSecret;

    /**
     * Access token
     */
    private String mAccessToken;

    /**
     * Referrer
     */
    private String mReferrer;

    /**
     * Retrofit log level
     */
    private LogLevel mLogLevel = LogLevel.NONE;

    /**
     * Empty constructor
     */
    public ApiConfig() {

    }

    /**
     * Set api key
     *
     * @param apiKey
     */
    public ApiConfig(String apiKey) {
        mApiKey = apiKey;
    }

    /**
     * Set api key and log level
     *
     * @param apiKey
     */
    public ApiConfig(String apiKey, LogLevel logLevel) {
        this(apiKey);
        mLogLevel = logLevel;
    }

    /**
     * Set api key and access token
     *
     * @param apiKey
     * @param accessToken
     */
    public ApiConfig(String apiKey, String accessToken) {
        this(apiKey);
        mAccessToken = accessToken;
    }

    /**
     * Set api key, access token and log level
     *
     * @param apiKey
     * @param accessToken
     */
    public ApiConfig(String apiKey, String accessToken, LogLevel logLevel) {
        this(apiKey, accessToken);
        mLogLevel = logLevel;
    }

    /**
     * Set api key, access token and referrer
     *
     * @param apiKey
     * @param accessToken
     * @param referrer
     */
    public ApiConfig(String apiKey, String accessToken, String referrer) {
        this(apiKey, accessToken);
        mReferrer = referrer;
    }

    /**
     * Set api key, access token and referrer
     *
     * @param apiKey
     * @param accessToken
     * @param referrer
     */
    public ApiConfig(String apiKey, String accessToken, String referrer, LogLevel logLevel) {
        this(apiKey, accessToken, referrer);
        mLogLevel = logLevel;
    }

    /**
     * Get api key
     *
     * @return
     */
    public String getApiKey() {
        return mApiKey;
    }

    /**
     * Set api key
     *
     * @param apiKey
     * @return
     */
    public ApiConfig setApiKey(String apiKey) {
        this.mApiKey = apiKey;
        return this;
    }

    /**
     * Get api secret
     *
     * @return
     */
    public String getApiSecret() {
        return mApiSecret;
    }

    /**
     * Set api secret. Avoid using api secret as it's a security risk
     *
     * @param apiSecret
     * @return
     */
    public ApiConfig setApiSecret(String apiSecret) {
        this.mApiSecret = apiSecret;
        return this;
    }

    /**
     * Get access token
     *
     * @return
     */
    public String getAccessToken() {
        return mAccessToken;
    }

    /**
     * Set access token
     *
     * @param accessToken
     * @return
     */
    public ApiConfig setAccessToken(String accessToken) {
        this.mAccessToken = accessToken;
        return this;
    }

    /**
     * Get referrer
     *
     * @return
     */
    public String getReferrer() {
        return mReferrer;
    }

    /**
     * Set referrer
     *
     * @param referrer
     * @return
     */
    public ApiConfig setReferrer(String referrer) {
        this.mReferrer = referrer;
        return this;
    }

    /**
     * Get log level
     *
     * @return
     */
    public LogLevel getLogLevel() {
        return mLogLevel;
    }

    /**
     * Set log level
     *
     * @param logLevel
     * @return
     */
    public ApiConfig setLogLevel(LogLevel logLevel) {
        mLogLevel = logLevel;
        return this;
    }

}
