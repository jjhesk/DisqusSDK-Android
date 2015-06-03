package com.hkm.dis;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.TintImageView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import com.hkm.disqus.DisqusConstants;
import com.hkm.disqus.application.AuthorizeUtils;
import com.hkm.disqus.application.asyclient;
import com.hkm.disqus.application.authorizeAccessToken;

import java.util.Scanner;

/**
 * Created by hesk on 2/6/15.
 */
public class webviewdisqus extends AppCompatActivity {
    public static String TAG = "webviewDis";

    private static String getwebviewcontent(Context ctx, String disqustID) {
        int ResId = R.raw.embed;
        StringBuilder sb = new StringBuilder();
        Scanner s = new Scanner(ctx.getResources().openRawResource(ResId));
        //  sb.append("<style type=\"text/css\">");
        String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
        sb.append(header);
        while (s.hasNextLine()) {
            sb.append(s.nextLine() + "\n");
        }
        //  sb.append("</style>");
        String n = sb.toString();
        String h = n.replace("<disqus_identifier>", disqustID);
        return h;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentdisquewv);

        final WebView disqus = (WebView) findViewById(R.id.disqus_authorize_webview);
        disqus.setWebViewClient(new WebViewClient());
        WebSettings webSettings = disqus.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //"hypebeast"

        String fftoken = getwebviewcontent(this, "1008680 http://hypebeast.com/?p=1008680");
        //   disqus.loadData(fftoken, "text/html; charset=UTF-8", null);
        disqus.loadDataWithBaseURL("http://hypebeast.com/?p=1008680", fftoken, "text/html; charset=UTF-8", null, null);
        Log.d(TAG, fftoken);
    }


}
