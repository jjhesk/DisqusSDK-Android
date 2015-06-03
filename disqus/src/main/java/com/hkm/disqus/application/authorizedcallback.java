package com.hkm.disqus.application;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * currently we dont use it in the code
 * This class will be removed in the next version
 * Created by hesk on 2/6/15.
 */
@Deprecated
public class authorizedcallback extends AppCompatActivity {
    public static final String CALLBACK_URL = "disqus-hb-cb:///";
    public static final String TAG = "nowdisqus";

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        dealWithTwitterResponse(intent);
    }

    private void dealWithTwitterResponse(Intent intent) {
        Uri uri = intent.getData();
        System.out.println("URI=" + uri);
        if (uri != null && uri.toString().startsWith(CALLBACK_URL)) {
            String oauthVerifier = uri.getQueryParameter("oauth_verifier");
            //authoriseNewUser(oauthVerifier);

            Log.d(TAG, uri.toString());
        }
    }
}
