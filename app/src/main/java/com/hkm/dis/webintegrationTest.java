package com.hkm.dis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hkm.disqus.application.WebIntergrationFragment;

/**
 * Created by hesk on 9/11/15.
 */
public class webintegrationTest extends AppCompatActivity {

    protected void statFragmentLogin(Bundle fragmentextras) {
        getFragmentManager().beginTransaction().add(
                R.id.fragment_id_a,
                WebIntergrationFragment.B(fragmentextras)
        ).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutlogin);
        Bundle extras = new Bundle();
        extras.putString(WebIntergrationFragment.EXTRA_URL, "http://hypetrak.com/2015/11/tbd-jay-electronica-jai-paul-and-the-allure-of-the-unreleased-masterpiece/");
        extras.putString(WebIntergrationFragment.EXTRA_TITLE, "TBD: Jay Electronica, Jai Paul, and the Allure of the Unreleased Masterpiece");
        extras.putString(WebIntergrationFragment.EXTRA_IDENT, "293393");
        statFragmentLogin(extras);
    }
}
