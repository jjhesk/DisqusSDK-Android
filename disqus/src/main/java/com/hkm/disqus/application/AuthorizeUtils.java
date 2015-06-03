package com.hkm.disqus.application;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.hkm.disqus.DisqusConstants;
import com.hkm.disqus.api.model.oauth2.AccessToken;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

/**
 * Created by hesk on 21/5/15.
 */
public class AuthorizeUtils {

    /**
     * Create authorize intent
     *
     * @param context     as is
     * @param apiKey      as is
     * @param scopes      as is
     * @param redirectUri as is
     * @return as is
     */
    public static Intent createIntent(Context context, String apiKey, String[] scopes,
                                      String redirectUri) {
        Intent intent = new Intent(context, AuthorizeActivity.class);
        Bundle extras = new Bundle();
        extras.putString(AuthorizeActivity.EXTRA_API_KEY, apiKey);
        extras.putStringArray(AuthorizeActivity.EXTRA_SCOPES, scopes);
        extras.putString(AuthorizeActivity.EXTRA_REDIRECT_URI, redirectUri);
        intent.putExtras(extras);
        return intent;
    }

    /**
     * Build a scope string from an array of scopes
     *
     * @param scopes as is
     * @return as is
     */
    public static String buildScope(String[] scopes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < scopes.length; i++) {
            builder.append(scopes[i]);
            if (i < scopes.length - 1) {
                builder.append(',');
            }
        }
        return builder.toString();
    }

    /**
     * Build uri for authorize requests
     *
     * @param clientId    as is
     * @param scope       as is
     * @param redirectUri as is
     * @return as is
     */
    public static Uri buildAuthorizeUri(String clientId, String scope, String redirectUri) {
        Uri.Builder builder = Uri.parse(DisqusConstants.AUTHORIZE_URL).buildUpon();
        builder.appendQueryParameter(DisqusConstants.PARAM_CLIENT_ID, clientId);
        if (scope != null) {
            builder.appendQueryParameter(DisqusConstants.PARAM_SCOPE, scope);
        }
        builder.appendQueryParameter(DisqusConstants.PARAM_RESPONSE_TYPE, DisqusConstants.RESPONSE_TYPE_CODE);
        builder.appendQueryParameter(DisqusConstants.PARAM_REDIRECT_URI, redirectUri);
        return builder.build();
    }


    public static String buildCodeUri(String code, String clientId, String secret, String redirect) {
        Uri.Builder bu = Uri.parse(DisqusConstants.AUTHORIZE_ACCESS_TOKEN).buildUpon();

        bu.appendQueryParameter(DisqusConstants.PARAM_GRANTTYPE, DisqusConstants.auth_code);
        bu.appendQueryParameter(DisqusConstants.PARAM_CLIENT_ID, clientId);
        bu.appendQueryParameter(DisqusConstants.PARAM_CLIENT_SECRET, secret);
        bu.appendQueryParameter(DisqusConstants.PARAM_REDIRECT_URI, redirect);
        bu.appendQueryParameter(DisqusConstants.PARAM_CODE, code);

        return bu.build().toString();
    }

    public static String buildCodeRequestJustBody(String code, String clientId, String secret, String redirect) {
        String t = buildCodeUri(code, clientId, secret, redirect);
        String replacetarget = DisqusConstants.AUTHORIZE_ACCESS_TOKEN + "?";
        String glongthat = t.replace(replacetarget, "");
        return glongthat;
    }

    public static RequestBody buildRequest(String code, String clientId, String secret, String redirect) {
        final RequestBody fmbody = new FormEncodingBuilder()
                .add(DisqusConstants.PARAM_CODE, code)
                .add(DisqusConstants.PARAM_GRANTTYPE, DisqusConstants.auth_code)
                .add(DisqusConstants.PARAM_CLIENT_ID, clientId)
                .add(DisqusConstants.PARAM_CLIENT_SECRET, secret)
                .add(DisqusConstants.PARAM_REDIRECT_URI, redirect)
                .build();

        return fmbody;
    }

    public static AccessToken getDataToken(String url) {
        Uri uri = Uri.parse(url);
        String uriFragment = uri.getFragment();
        // Extract data from fragment and pass to callback
        Uri queryUri = new Uri.Builder().encodedQuery(uriFragment).build();

        AccessToken accessToken = new AccessToken();
        accessToken.username = queryUri.getQueryParameter(DisqusConstants.PARAM_USERNAME);
        accessToken.userId = Long.parseLong(queryUri.getQueryParameter(DisqusConstants.PARAM_USER_ID));
        accessToken.accessToken = queryUri.getQueryParameter(DisqusConstants.PARAM_ACCESS_TOKEN);
        accessToken.expiresIn = Long.parseLong(queryUri.getQueryParameter(DisqusConstants.PARAM_EXPIRES_IN));
        accessToken.tokenType = queryUri.getQueryParameter(DisqusConstants.PARAM_TOKEN_TYPE);
        accessToken.state = queryUri.getQueryParameter(DisqusConstants.PARAM_STATE);
        accessToken.scope = queryUri.getQueryParameter(DisqusConstants.PARAM_SCOPE);

        return accessToken;
    }
}
