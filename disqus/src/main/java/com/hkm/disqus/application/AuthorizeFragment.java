package com.hkm.disqus.application;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hkm.disqus.DisqusConstants;
import com.hkm.disqus.R;
import com.hkm.disqus.api.model.oauth2.AccessToken;

/**
 * Created by hesk on 21/5/15.
 */
public abstract class AuthorizeFragment extends Fragment {

    /**
     * Logging tag
     */
    private static final String TAG = AuthorizeFragment.class.getName();

    /**
     * Arguments
     */
    private static final String ARG_API_KEY = "api_key";
    private static final String ARG_SCOPES = "scope";
    private static final String ARG_REDIRECT_URI = "redirect_uri";

    /**
     * The Disqus API key
     */
    private String mApiKey;

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
    private AuthorizeListener mListener;

    /**
     * A {@link WebView} to display the Disqus login
     */
    private WebView mWebView;


    /**
     * * Get a new instance of this fragment
     *
     * @param apiKey      as is
     * @param scopes      as is
     * @param redirectUri as is
     * @return AuthorizeFragment
     */
    public static AuthorizeFragment newInstance(String apiKey, String[] scopes, String redirectUri) {
      /*  AuthorizeFragment fragment = new AuthorizeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_API_KEY, apiKey);
        args.putStringArray(ARG_SCOPES, scopes);
        args.putString(ARG_REDIRECT_URI, redirectUri);
        fragment.setArguments(args);*/
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_API_KEY)) {
            mApiKey = getArguments().getString(ARG_API_KEY);
        }
        if (getArguments().containsKey(ARG_SCOPES)) {
            mScopes = getArguments().getStringArray(ARG_SCOPES);
        }
        if (getArguments().containsKey(ARG_REDIRECT_URI)) {
            mRedirectUri = getArguments().getString(ARG_REDIRECT_URI);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (AuthorizeListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement AuthorizeCallback");
        }
    }

    protected abstract int disqus_fragment_authorize();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(disqus_fragment_authorize(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mWebView = (WebView) view.findViewById(R.id.disqus_authorize_webview);

        // Setup custom client to catch the redirect
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(mRedirectUri)) {
                    // Get fragment from url
                    Log.d(TAG, "Processing redirect: " + url);
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
                    mListener.onSuccess(accessToken);
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        // Load authorize url
        String scope = AuthorizeUtils.buildScope(mScopes);
        Uri uri = AuthorizeUtils.buildAuthorizeUri(mApiKey, scope, mRedirectUri);
        Log.d(TAG, "Loading authorize url: " + uri.toString());
        mWebView.loadUrl(uri.toString());
    }

    /**
     * Listener interface, must be implemented by calling activity
     * TODO Should handle failures too but at time of writing the cancel button on the Disqus site
     * TODO is broken so only way to cancel is via back button which can be handled in the activity
     */
    public interface AuthorizeListener {

        public void onSuccess(AccessToken accessToken);

    }

}