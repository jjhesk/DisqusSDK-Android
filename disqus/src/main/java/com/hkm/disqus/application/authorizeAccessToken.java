package com.hkm.disqus.application;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.hkm.disqus.DisqusConstants;
import com.hkm.disqus.api.ApiConfig;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import org.json.JSONException;

/**
 * Created by hesk on 2/6/15.
 */
public class authorizeAccessToken extends asyclient {
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final String _code;
    private final ApiConfig _config;

    public authorizeAccessToken(Context ccc, callback cb, String code, ApiConfig cfg) {
        super(ccc, cb);
        _code = code;
        _config = cfg;
        try {
            setURL(DisqusConstants.AUTHORIZE_ACCESS_TOKEN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void GSONParser(String data) throws JSONException, JsonSyntaxException, JsonIOException, JsonParseException {
        Log.d(TAG, data);
    }

    @Override
    protected void configOkClient(OkHttpClient client) {


    }

    @Override
    protected void addHeaderParam(Request.Builder request) {

        request.post(AuthorizeUtils.buildRequest(_code, _config.getApiKey(), _config.getApiSecret(), _config.getReferrer()));
    }
}
