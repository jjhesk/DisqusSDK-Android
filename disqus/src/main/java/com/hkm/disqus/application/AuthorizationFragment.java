package com.hkm.disqus.application;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.hkm.disqus.DisqusConstants;
import com.hkm.disqus.api.DisqusClient;
import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.app.BasicWebViewNormal;
import com.hkm.ezwebview.webviewclients.PaymentClient;

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
        n.putString(ARG_API_KEY, apikey);
        n.putString(ARG_SCOPES, scope);
        n.putString(ARG_REDIRECT_URI, redirect_uri);
        n.putString(ARG_SECRET, secret);
        return n;
    }

    /**
     * on press
     */
    private static final String authURI = "https://disqus.com/api/oauth/2.0/grant/";
    /**
     * Logging tag
     */
    protected static final String TAG = AuthorizeFragment.class.getName();

    /**
     * Arguments
     */
    protected static final String ARG_API_KEY = "api_key";
    protected static final String ARG_SCOPES = "scope";
    protected static final String ARG_REDIRECT_URI = "redirect_uri";
    protected static final String ARG_SECRET = "secret";

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
     * A {@link WebView} to display the Disqus login
     */
    private WebView mWebView;
    private boolean isDialogShown = false;

    enum ACTION {
        REFRESH_TOKEN, STEP1, STEP2
    }

    protected void LoadConfig() {
        if (getArguments().containsKey(ARG_API_KEY)) {
            mApiKey = getArguments().getString(ARG_API_KEY);
        }
        if (getArguments().containsKey(ARG_SCOPES)) {
            mScopes = getArguments().getStringArray(ARG_SCOPES);
        }
        if (getArguments().containsKey(ARG_REDIRECT_URI)) {
            mRedirectUri = getArguments().getString(ARG_REDIRECT_URI);
        }
        if (getArguments().containsKey(ARG_SECRET)) {
            mSecret = getArguments().getString(ARG_SECRET);
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
        setup_token_view(new AuthorizationClient(getActivity(), block, mRedirectUri));
    }


    protected <T extends PaymentClient> void setup_token_view(T clientLogic) {

        Uri request_access_token_path_final = AuthorizeUtils.buildAuthorizeUri(mApiKey, getScopes(), mRedirectUri);
        String msg = "Loading authorize url: " + request_access_token_path_final.toString();
        Log.d(TAG, msg);
        Fx9C.setup_payment_gateway(
                clientLogic,
                framer,
                block,
                betterCircleBar,
                request_access_token_path_final.toString(),
                "Disqus/1.0",
                1600);
    }

    private static class AuthorizationClient extends PaymentClient {

        private String redirect;

        public static AuthorizationClient with(Activity context, WebView mview, String redirect) {
            final AuthorizationClient pay = new AuthorizationClient(context, mview, redirect);
            return pay;
        }

        @Override
        protected void triggerNative(Uri trigger_url) {
            super.triggerNative(trigger_url);
        }

        @Override
        protected boolean interceptUrl(WebView view, String url) {
            final String coderequeststart = redirect + DisqusConstants.authorizeCode;
            Log.d(TAG, "base change: " + url);

            if (url.startsWith(coderequeststart)) {
                Log.d("", "Got Url " + url);
                Uri uri = Uri.parse(url);
                final String code = uri.getQueryParameter("code");
                Log.d(TAG, "Acquiring Code: " + code);

            //    DisqusClient
                authentication_manager.authorizeAsync(code);


                // mWebView.postUrl(DisqusConstants.AUTHORIZE_ACCESS_TOKEN, EncodingUtils.getBytes(AuthorizeUtils.buildCodeRequestJustBody(code, mApiKey, mSecret, mRedirectUri), "BASE64"));


                return true;
            } else if (url.startsWith(allowBUttonPressUrl)) {
                dislogProcessNotice();
                //continue
            } else if (url.startsWith(mRedirectUri)) {
                // Get fragment from url
                Log.d(TAG, "Acquiring Processing redirect: " + url);
                //  mListener.onSuccess(AuthorizeUtils.getDataToken(url));
                return true;
            }
            return false;
        }

        public AuthorizationClient(Activity context, WebView fmWebView, String redirect) {
            super(context, fmWebView);
            this.redirect = redirect;
            allow = new String[]{
                    authURI,

            };
            // nativefunctions = new String[]{
            //start native app
            //        domain_start,
            //         domain_start_insecure

            // };

        }


    }
}
