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
        super.onFailure();
    }


    public static class loginInstance extends AuthorizeFragment {


        /**
         * Get a new instance of this fragment
         *
         * @param b the data bundle
         * @return the object in return
         */

        public static loginInstance newInstance(Bundle b) {
            loginInstance fragment = new loginInstance();
            fragment.setArguments(b);
            return fragment;
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
