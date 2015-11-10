package com.hkm.disqus.application;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.app.BasicWebViewNormal;
import com.hkm.ezwebview.webviewclients.PaymentClient;

/**
 * Created by hesk on 10/11/15.
 */
public class WiDisquscomment extends BasicWebViewNormal {
    private static final String
            HEAD_URL = "http://hypetrak.com/disquscommenting/",
            URL = "url",
            IDENTIFER = "identifier",
            SHORTNAME = "shortname";

    public static WiDisquscomment newInstance(
            Bundle b) {
        final WiDisquscomment wi = new WiDisquscomment();
        wi.setArguments(b);
        return wi;
    }

    public static Bundle B(
            final String id,
            final String url,
            final String shortname) {
        Bundle b = new Bundle();
        b.putString(URL, url);
        b.putString(IDENTIFER, id);
        b.putString(SHORTNAME, shortname);
        return b;
    }

    public static WiDisquscomment newInstance(
            final String id,
            final String url,
            final String shortname) {
        final WiDisquscomment wi = new WiDisquscomment();
        wi.setArguments(B(id, url, shortname));
        return wi;
    }

    protected String tokenBuilder() {
        Uri.Builder bu = Uri.parse(HEAD_URL).buildUpon();
        bu.appendQueryParameter(URL, getArguments().getString(URL));
        bu.appendQueryParameter(IDENTIFER, getArguments().getString(IDENTIFER));
        bu.appendQueryParameter(SHORTNAME, getArguments().getString(SHORTNAME));
        return bu.build().toString();
    }

    private class GM extends PaymentClient {
        public GM(Activity context, WebView fmWebView) {
            super(context, fmWebView);
        }
        @Override
        protected boolean interceptUrl(WebView view, String url) {
            return false;
        }
    }

    @Override
    public void onViewCreated(View v, Bundle b) {
        super.onViewCreated(v, b);
        try {
            Fx9C.setup_payment_gateway(
                    new GM(getActivity(), block),
                    framer,
                    block,
                    betterCircleBar,
                    tokenBuilder(),
                    "",
                    3000
            );
        } catch (Exception e) {

        }
    }
}
