package com.hkm.dis;

import android.os.Bundle;

import com.hkm.disqus.api.ApiConfig;
import com.hkm.disqus.api.model.oauth2.AccessToken;
import com.hkm.disqus.application.AuthorizeActivity;
import com.hkm.disqus.application.AuthorizeFragment;


/**
 * Created by hesk on 1/6/15.
 */
public class login extends AuthorizeActivity {

    @Override
    protected int authorize_layout() {
        return R.layout.layoutlogin;
    }

    @Override
    protected void statFragmentLogin(Bundle fragment) {
        getFragmentManager().beginTransaction().add(R.id.fragment_id_a, loginInstance.newInstance(fragment)).commit();
    }

    @Override
    protected void saveToken(AccessToken accessToken) {
        applicationbase base = ((applicationbase) getApplication());
        base.afterLogin(accessToken);
    }

    @Override
    public void onFailure() {

    }


    public static class loginInstance extends AuthorizeFragment {

        /**
         * * Get a new instance of this fragment
         *
         * @param apiKey      as is
         * @param scopes      as is
         * @param redirectUri as is
         * @return AuthorizeFragment
         */
        public static loginInstance newInstance(String apiKey, String[] scopes, String redirectUri) {
            loginInstance fragment = new loginInstance();
            Bundle args = new Bundle();
            args.putString(ARG_API_KEY, apiKey);
            args.putStringArray(ARG_SCOPES, scopes);
            args.putString(ARG_REDIRECT_URI, redirectUri);
            fragment.setArguments(args);
            return fragment;
        }

        public static loginInstance newInstance(Bundle b) {
            loginInstance fragment = new loginInstance();
            fragment.setArguments(b);
            return fragment;
        }

        @Override
        protected int disqus_fragment_authorize() {
            return R.layout.fragmentdisquewv;
        }

        @Override
        protected int get_web_view_id() {
            return R.id.disqus_authorize_webview;
        }

        @Override
        protected String getNativeCallBack() {
            return applicationbase.redirecturidisqushbcallback;
        }

        @Override
        protected ApiConfig getConf() {
            return ((applicationbase) getActivity().getApplication()).getConf();
        }
    }

}
