package com.hkm.disqus;

/**
 * Created by hesk on 21/5/15.
 */
public class DisqusConstants {

    /**
     * Authorize URL
     */
    public static final String AUTHORIZE_URL = "https://disqus.com/api/oauth/2.0/authorize/";

    /**
     * Currently available scopes
     */
    public static final String SCOPE_READ = "read";
    public static final String SCOPE_WRITE = "write";
    public static final String SCOPE_EMAIL = "email";
    public static final String SCOPE_ADMIN = "admin";

    /**
     * Params used in Disqus urls
     */
    public static final String PARAM_CLIENT_ID = "client_id";
    public static final String PARAM_SCOPE = "scope";
    public static final String PARAM_RESPONSE_TYPE = "response_type";
    public static final String PARAM_REDIRECT_URI = "redirect_uri";
    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_USER_ID = "user_id";
    public static final String PARAM_ACCESS_TOKEN = "access_token";
    public static final String PARAM_EXPIRES_IN = "expires_in";
    public static final String PARAM_TOKEN_TYPE = "token_type";
    public static final String PARAM_STATE = "state";

    /**
     * Authorization response types
     */
    public static final String RESPONSE_TYPE_TOKEN = "token";

    /**
     * Date format
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
}
