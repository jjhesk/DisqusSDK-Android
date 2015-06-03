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
import com.hkm.disqus.api.ApiConfig;
import com.hkm.disqus.api.model.oauth2.AccessToken;

import org.apache.http.util.EncodingUtils;

import retrofit.http.POST;
import retrofit.http.Path;

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
    protected static final String ARG_SECRET = "secret";

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
        if (getArguments().containsKey(ARG_SECRET)) {
            mSecret = getArguments().getString(ARG_SECRET);
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

    protected abstract String getNativeCallBack();

    protected abstract ApiConfig getConf();

    private void routeAuth() {

        // Setup custom client to catch the redirect
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                final String coderequeststart = mRedirectUri + DisqusConstants.authorizeCode;
                if (url.startsWith(coderequeststart)) {
                    String code = url.substring(coderequeststart.length(), url.length());
                    Log.d(TAG, "Acquiring Code: " + code);
                    mWebView.postUrl(DisqusConstants.AUTHORIZE_ACCESS_TOKEN, EncodingUtils.getBytes(AuthorizeUtils.buildCodeRequestJustBody(code, mApiKey, mSecret, mRedirectUri), "BASE64"));


                    return true;
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


        routeAuth();
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

   /* public interface DisqusGetAccessTokenFromCode {
        @POST("https://disqus.com/api/oauth/2.0/access_token/")
        List<Repo> applyCode(@Path("user") String user);
    }*/

}