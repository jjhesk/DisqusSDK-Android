package com.hkm.disqus.application;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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
    public static final String EXTRA_SCOPES = "scope";
    public static final String EXTRA_REDIRECT_URI = "redirect_uri";

    /**
     * Extras that are passed in the result
     */
    public static final String EXTRA_ACCESS_TOKEN = "access_token";
    public static final String EXTRA_SECRET = "secret";

    protected abstract int authorize_layout();

    protected abstract void statFragmentLogin(Bundle fragmentextras);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(authorize_layout());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Get extras
            statFragmentLogin(extras);
        } else {
            // Can't do anything without the right extras so finish
            // TODO Add some sort of error handling?
            finish();
        }
    }

    protected abstract void saveToken(AccessToken accessToken);

    @Override
    public void onSuccess(AccessToken accessToken) {
        // Create a result intent
        // Toast.makeText(this, "login success", Toast.LENGTH_LONG);
        Intent data = new Intent();
        data.putExtra(EXTRA_ACCESS_TOKEN, accessToken);
        setResult(RESULT_OK, data);
        saveToken(accessToken);
        finish();
    }

    @Override
    public void onFailure() {
        Toast.makeText(this, getResources().getString(R.string.failurelogin), Toast.LENGTH_LONG);
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }


}