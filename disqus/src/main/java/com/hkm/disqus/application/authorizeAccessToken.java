package com.hkm.disqus.application;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.hkm.disqus.DisqusConstants;
import com.hkm.disqus.api.model.oauth2.AccessToken;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import org.json.JSONException;

/**
 * Created by hesk on 2/6/15.
 */
public class authorizeAccessToken extends capclient {
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final RequestBody _requestBody;
    private final gsonCallBack mgsonCallBack;

    public interface gsonCallBack {
        void gparser(AccessToken data);
    }

    public authorizeAccessToken(Context ccc, RequestBody postRequestBody, callback cb, gsonCallBack gb) {
        super(ccc, cb);
        mgsonCallBack = gb;
        _requestBody = postRequestBody;
        try {
            setURL(DisqusConstants.AUTHORIZE_ACCESS_TOKEN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void GSONParser(String data) throws JSONException, JsonSyntaxException, JsonIOException, JsonParseException {
        Log.d(TAG, data);
        final AccessToken tk = getGson().fromJson(data, AccessToken.class);
        mgsonCallBack.gparser(tk);
    }

    @Override
    protected void configOkClient(OkHttpClient client) {

    }

    @Override
    protected void addHeaderParam(Request.Builder request) {
        request.post(_requestBody);
    }
}
