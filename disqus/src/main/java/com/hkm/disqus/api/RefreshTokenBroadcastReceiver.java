package com.hkm.disqus.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;



public class RefreshTokenBroadcastReceiver extends BroadcastReceiver {


  private AuthTokenServiceManager authManager;

    public RefreshTokenBroadcastReceiver() {
        //DisqusSdkProvider.getInstance().getObjectGraph().inject(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {


        authManager.postRefreshTokenAsync();
    }
}
