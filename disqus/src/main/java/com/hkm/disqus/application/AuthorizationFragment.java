package com.hkm.disqus.application;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.hkm.disqus.DisqusClient;
import com.hkm.disqus.DisqusConstants;
import com.hkm.disqus.api.ApiConfig;
import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.app.BasicWebViewNormal;
import com.hkm.ezwebview.webviewclients.PaymentClient;

import retrofit.RestAdapter;

/**
 * Created by hesk on 6/11/15.
 */
public class AuthorizationFragment extends BasicWebViewNormal {


    public static AuthorizationFragment B(final Bundle b) {
        final AuthorizationFragment t = new AuthorizationFragment();
        t.setArguments(b);
        return t;
    }

    public static Bundle ScopeBundle(@Nullable String apikey,
                                     @Nullable String scope,
                                     @Nullable String redirect_uri,
                                     @Nullable String secret) {
        final Bundle n = new Bundle();
        n.putString(AuthorizeActivity.EXTRA_API_KEY, apikey);
        n.putString(AuthorizeActivity.EXTRA_SCOPES, scope);
        n.putString(AuthorizeActivity.EXTRA_REDIRECT_URI, redirect_uri);
        n.putString(AuthorizeActivity.EXTRA_SECRET, secret);
        return n;
    }

    public static Bundle bundleConfig(@Nullable String apikey,
                                      @Nullable String access_default,
                                      @Nullable String redirect_uri,
                                      @Nullable String secret) {
        final Bundle n = new Bundle();
        n.putString(AuthorizeActivity.EXTRA_API_KEY, apikey);
        n.putString(AuthorizeActivity.EXTRA_DEFAULT_ACCESS, access_default);
        n.putString(AuthorizeActivity.EXTRA_REDIRECT_URI, redirect_uri);
        n.putString(AuthorizeActivity.EXTRA_SECRET, secret);
        return n;
    }

    public static ApiConfig genConfig(Bundle bundle) {
        ApiConfig conf = new ApiConfig(
                bundle.getString(AuthorizeActivity.EXTRA_API_KEY),
                bundle.getString(AuthorizeActivity.EXTRA_DEFAULT_ACCESS),
                RestAdapter.LogLevel.BASIC);
        conf.setApiSecret(bundle.getString(AuthorizeActivity.EXTRA_SECRET));
        conf.setRedirectUri(bundle.getString(AuthorizeActivity.EXTRA_REDIRECT_URI));
        return conf;
    }

    /**
     * on press
     */
    private static final String authURI = "https://disqus.com/api/oauth/2.0/grant/";
    /**
     * Logging tag
     */
    protected static final String TAG = "AuthorizLog";


    protected ACTION maction;

    /**
     * The Disqus API key
     */
    private String mApiKey;
    /**
     * The Disqus secret id
     */
    private String mSecret;

    /**
     * Scopes
     */
    private String[] mScopes;

    /**
     * Authorize redirect URI
     */
    private String mRedirectUri;

    /**
     * Authorization listener
     */
    // private AuthorizeListener mListener;

    /**
     * A {@link WebView} to display the Disqus disqusloginactivityframelayout
     */
    private WebView mWebView;
    private boolean isDialogShown = false;

    enum ACTION {
        REFRESH_TOKEN, STEP1, STEP2
    }

    protected void LoadConfig() {
        if (getArguments().containsKey(AuthorizeActivity.EXTRA_API_KEY)) {
            mApiKey = getArguments().getString(AuthorizeActivity.EXTRA_API_KEY);
        }
        if (getArguments().containsKey(AuthorizeActivity.EXTRA_SCOPES)) {
            mScopes = getArguments().getStringArray(AuthorizeActivity.EXTRA_SCOPES);
        }
        if (getArguments().containsKey(AuthorizeActivity.EXTRA_REDIRECT_URI)) {
            mRedirectUri = getArguments().getString(AuthorizeActivity.EXTRA_REDIRECT_URI);
        }
        if (getArguments().containsKey(AuthorizeActivity.EXTRA_SECRET)) {
            mSecret = getArguments().getString(AuthorizeActivity.EXTRA_SECRET);
        }
    }

    /**
     * Load authorize url
     *
     * @return the string
     */
    private String getScopes() {
        String scope;
        try {
            scope = AuthorizeUtils.buildScope(mScopes);
        } catch (Exception e) {
            scope = "read,write";
        }
        return scope;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoadConfig();
        Uri request_access_token_path_final = AuthorizeUtils.buildAuthorizeUri(mApiKey, getScopes(), mRedirectUri);
        String msg = "Loading authorize url: " + request_access_token_path_final.toString();
        Log.d(TAG, msg);
        final AuthorizationClient mAth = new AuthorizationClient(getActivity(), block);
        Fx9C.setup_payment_gateway(
                mAth,
                framer,
                block,
                betterCircleBar,
                request_access_token_path_final.toString(),
                "Disqus/1.0",
                1600);
    }

    protected void load() {
        if (betterCircleBar == null) return;
        betterCircleBar.setVisibility(View.INVISIBLE);
        ViewCompat.animate(betterCircleBar).alpha(1.0F);
    }

    private class AuthorizationClient extends PaymentClient {

        @Override
        protected void triggerNative(Uri trigger_url) {
            try {
                DisqusClient.getInstance().getAuthManager().authorizeAsync(trigger_url.getQueryParameter("code"));
            } catch (NullPointerException e) {

            }
        }

        @Override
        protected boolean interceptUrl(WebView view, String url) {
            Log.d(TAG, "base change: " + url);
            Uri uri = Uri.parse(url);
            try {
                if (!uri.getQueryParameter("code").equalsIgnoreCase("")) {
                    triggerNative(Uri.parse(url));
                    return true;
                }

                if (url.startsWith(authURI)) {
                    load();
                    return false;
                }
            } catch (NullPointerException e) {
                Log.d(TAG, "no key is found change: " + url);
                return false;
            } catch (UnsupportedOperationException e) {
                Log.d(TAG, "UnsupportedOperationException change: " + url);
                return false;
            } catch (Exception e) {
                Log.d(TAG, "Other things change: " + url);
                return false;
            }
            return false;
        }

        public AuthorizationClient(Activity context, WebView fmWebView) {
            super(context, fmWebView);
        }


    }
}
