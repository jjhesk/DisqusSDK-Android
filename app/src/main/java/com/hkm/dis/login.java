package com.hkm.dis;

import android.os.Bundle;

import com.hkm.disqus.BuildConfig;
import com.hkm.disqus.api.ApiConfig;
import com.hkm.disqus.api.AuthMgr;
import com.hkm.disqus.api.DisqusClient;
import com.hkm.disqus.api.model.oauth2.AccessToken;
import com.hkm.disqus.application.AuthorizeActivity;
import com.hkm.disqus.application.AuthorizeFragment;

import retrofit.RestAdapter;


/**
 * Created by hesk on 1/6/15.
 */
public class login extends AuthorizeActivity {

    @Override
    protected void statFragmentLogin(Bundle fragment) {
        getFragmentManager().beginTransaction().add(
                com.hkm.disqus.R.id.fragment_id_authorize,
                AuthorizeFragment.newInstance(fragment)

        ).commit();
    }



    @Override
    protected DisqusClient getClient() {
        ApiConfig conf = new ApiConfig(
                BuildConfig.DISQUS_API_KEY,
                BuildConfig.DISQUS_DEFAULT_ACCESS,
                RestAdapter.LogLevel.BASIC);
        conf.setApiSecret(BuildConfig.DISQUS_SECRET);
        conf.setRedirectUri(BuildConfig.DISQUS_REDIRECT_URI);
        return DisqusClient.getInstance(this, conf);
    }


   /* @Override
    protected int authorize_layout() {
        return R.layout.layoutlogin;
    }

    @Override
    protected void statFragmentLogin(Bundle fragment) {
        getFragmentManager().beginTransaction().add(R.id.fragment_id_a, loginInstance.newInstance(fragment)).commit();
    }

    @Override
    public void onFailure() {
        super.onFailure();
    }

    @Override
    protected AuthMgr getManager() {
        return ((applicationbase) getApplication()).getManager();
    }

    @Override
    protected ApiConfig getConfiguration() {
        return ((applicationbase) getApplication()).getConf();
    }


    public static class loginInstance extends AuthorizeFragment {



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
*/
}
