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
import android.widget.Toast;

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
    protected static final String TAG = AuthorizeFragment.class.getName();

    /**
     * Arguments
     */
    protected static final String ARG_API_KEY = "api_key";
    protected static final String ARG_SCOPES = "scope";
    protected static final String ARG_REDIRECT_URI = "redirect_uri";

    protected ACTION maction;

    enum ACTION {
        REFRESH_TOKEN, STEP1, STEP2
    }

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
    private AuthorizeListener mListener;

    /**
     * A {@link WebView} to display the Disqus login
     */
    private WebView mWebView;


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

    protected abstract int get_web_view_id();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(disqus_fragment_authorize(), container, false);
    }

    private void setup2() {

        // Setup custom client to catch the redirect
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(mRedirectUri + DisqusConstants.authorizeCode)) {
                    String code = url.substring(mRedirectUri.length(), url.length());
                    Log.d(TAG, "Acquiring Code: " + code);

                    mWebView.loadUrl(AuthorizeUtils.buildCodeUri(code, mApiKey, mSecret, mRedirectUri));


                } else if (url.startsWith(mRedirectUri)) {
                    // Get fragment from url
                    Log.d(TAG, "Acquiring Processing redirect: " + url);
                    mListener.onSuccess(AuthorizeUtils.getDataToken(url));
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });


        Uri uri = AuthorizeUtils.buildAuthorizeUri(mApiKey, getScopes(), mRedirectUri);
        String fftoken = "Loading authorize url: " + uri.toString();
        Log.d(TAG, fftoken);
        mWebView.loadUrl(uri.toString());
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mWebView = (WebView) view.findViewById(get_web_view_id());


        setup2();
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
    // abstract protected void posttextview(String something);

    /**
     * Listener interface, must be implemented by calling activity
     * TODO Should handle failures too but at time of writing the cancel button on the Disqus site
     * TODO is broken so only way to cancel is via back button which can be handled in the activity
     */
    public interface AuthorizeListener {

        void onSuccess(AccessToken accessToken);

        void onFailure();
    }

}