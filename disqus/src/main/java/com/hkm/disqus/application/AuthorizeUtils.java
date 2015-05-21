package com.hkm.disqus.application;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.hkm.disqus.DisqusConstants;

/**
 * Created by hesk on 21/5/15.
 */
public class AuthorizeUtils {

    /**
     * Create authorize intent
     *
     * @param context
     * @param apiKey
     * @param scopes
     * @param redirectUri
     * @return
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
     * @param scopes
     * @return
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
     * @param clientId
     * @param scope
     * @param redirectUri
     * @return
     */
    public static Uri buildAuthorizeUri(String clientId, String scope, String redirectUri) {
        Uri.Builder builder = Uri.parse(DisqusConstants.AUTHORIZE_URL).buildUpon();
        builder.appendQueryParameter(DisqusConstants.PARAM_CLIENT_ID, clientId);
        if (scope != null) {
            builder.appendQueryParameter(DisqusConstants.PARAM_SCOPE, scope);
        }
        builder.appendQueryParameter(DisqusConstants.PARAM_RESPONSE_TYPE,
                DisqusConstants.RESPONSE_TYPE_TOKEN);
        builder.appendQueryParameter(DisqusConstants.PARAM_REDIRECT_URI, redirectUri);
        return builder.build();
    }

}
