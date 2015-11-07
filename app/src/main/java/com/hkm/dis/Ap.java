package com.hkm.dis;

import android.app.Application;

import me.drakeet.library.CrashWoodpecker;

/**
 * Created by zJJ on 11/7/2015.
 *
 */
public class Ap extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashWoodpecker.fly().to(this);
    }
}
