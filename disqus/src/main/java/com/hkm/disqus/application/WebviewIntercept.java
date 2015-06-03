package com.hkm.disqus.application;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hesk on 3/6/15.
 * WebviewIntercept
 * <p/>
 * App.Compat
 */
public class WebviewIntercept extends WebViewClient {
    private final AppCompatActivity activity;
    public static final String LOG_TAG = "webviewnow";

    private UrlCache urlCache = null;

    public WebviewIntercept(AppCompatActivity a) {
        this.activity = a;
        this.urlCache = new UrlCache(a);
        this.urlCache.register("http://tutorials.jenkov.com/", "tutorials-jenkov-com.html",
                "text/html", "UTF-8", 5 * UrlCache.ONE_MINUTE);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.indexOf("jenkov.com") > -1) return false;

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
        return true;
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest rs) {
        if (rs.getUrl().toString().startsWith("http://tutorials.jenkov.com/images/logo.png")) {
            return loadFromAssets(rs.getUrl().toString(), "images/logo.png", "image/png", "");
        }
        return this.urlCache.load(rs.getUrl().toString());
    }

    private WebResourceResponse loadFromAssets(
            String url, String assetPath,
            String mimeType, String encoding
    ) {

        AssetManager assetManager = this.activity.getAssets();
        InputStream input = null;
        try {
            Log.d(LOG_TAG, "Loading from assets: " + assetPath);


            input = assetManager.open("/images/logo.png");
            WebResourceResponse response =
                    new WebResourceResponse(mimeType, encoding, input);

            return response;
        } catch (IOException e) {
            Log.e("WEB-APP", "Error loading " + assetPath + " from assets: " +
                    e.getMessage(), e);
        }
        return null;
    }
}
