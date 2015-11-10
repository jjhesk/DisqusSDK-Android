package com.hkm.dis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hkm.disqus.application.WiDisquscomment;


/**
 * Created by hesk on 9/11/15.
 */
public class webintegrationTest extends AppCompatActivity {

    protected void statFragmentLogin() {
        getFragmentManager().beginTransaction().add(
                R.id.fragment_id_a,
                WiDisquscomment.newInstance(WiDisquscomment.B(
                        "293393",
                        "http://hypetrak.com/2015/11/tbd-jay-electronica-jai-paul-and-the-allure-of-the-unreleased-masterpiece/",
                        "hypetrak"
                ))
        ).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutlogin);
        Bundle extras = new Bundle();
        statFragmentLogin();
    }
}
