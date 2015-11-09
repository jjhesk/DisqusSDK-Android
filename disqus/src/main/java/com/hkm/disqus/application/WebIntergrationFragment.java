package com.hkm.disqus.application;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hkm.disqus.R;
import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.Util.In32;
import com.hkm.ezwebview.app.BasicWebViewNormal;

/**
 * Created by hesk on 9/11/15.
 */
public class WebIntergrationFragment extends BasicWebViewNormal {


    /**
     * Authorize redirect URI
     */
    private String mUri, mIdent, mTitle;


    public final static String
            EXTRA_URL = "d.url",
            EXTRA_IDENT = "d.ident",
            EXTRA_TITLE = "d.title";

    public static WebIntergrationFragment B(final Bundle b) {
        final WebIntergrationFragment t = new WebIntergrationFragment();
        t.setArguments(b);
        return t;
    }

    protected void LoadConfig() {
        if (getArguments().containsKey(WebIntergrationFragment.EXTRA_URL)) {
            mUri = getArguments().getString(WebIntergrationFragment.EXTRA_URL);
        }
        if (getArguments().containsKey(WebIntergrationFragment.EXTRA_IDENT)) {
            mIdent = getArguments().getString(WebIntergrationFragment.EXTRA_IDENT);
        }
        if (getArguments().containsKey(WebIntergrationFragment.EXTRA_TITLE)) {
            mTitle = getArguments().getString(WebIntergrationFragment.EXTRA_TITLE);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoadConfig();
        String template = In32.cssRawName(getActivity(), R.raw.static_disqus_template);
        String t1 = template.replace("____REPLACE_URL", mUri);
        String t2 = t1.replace("____DISQUS_TITLE", mTitle);
        String t3 = t2.replace("____DISQUS_IDENTIFIER", mIdent);

        //    final AuthorizationClient mAth = new AuthorizationClient(getActivity(), block);
        Fx9C.setup_content_block_wb(
                framer,
                block,
                betterCircleBar,
                t3,
                1600);
    }
}
