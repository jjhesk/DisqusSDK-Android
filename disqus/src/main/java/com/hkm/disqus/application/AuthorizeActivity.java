package com.hkm.disqus.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hkm.disqus.R;
import com.hkm.disqus.api.model.oauth2.AccessToken;

/**
 * Created by hesk on 21/5/15.
 */
public abstract class AuthorizeActivity extends AppCompatActivity implements AuthorizeFragment.AuthorizeListener {

    /**
     * Extras that should be passed in the {@link Intent}
     */
    public static final String EXTRA_API_KEY = "api_key";
    public static final String EXTRA_SCOPES = "scopes";
    public static final String EXTRA_REDIRECT_URI = "redirect_uri";

    /**
     * Extras that are passed in the result
     */
    public static final String EXTRA_ACCESS_TOKEN = "access_token";

    protected abstract int authorize_layout();


    protected abstract void statFragmentLogin(int framelayoutID, AuthorizeFragment fragment);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(authorize_layout());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Get extras
            String apiKey = extras.getString(EXTRA_API_KEY);
            String[] scopes = extras.getStringArray(EXTRA_SCOPES);
            String redirectUri = extras.getString(EXTRA_REDIRECT_URI);
            statFragmentLogin(R.id.fragment_id_authorize, AuthorizeFragment.newInstance(apiKey, scopes, redirectUri));

        } else {
            // Can't do anything without the right extras so finish
            // TODO Add some sort of error handling?
            finish();
        }
    }

    @Override
    public void onSuccess(AccessToken accessToken) {
        // Create a result intent
        Intent data = new Intent();
        data.putExtra(EXTRA_ACCESS_TOKEN, accessToken);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

}