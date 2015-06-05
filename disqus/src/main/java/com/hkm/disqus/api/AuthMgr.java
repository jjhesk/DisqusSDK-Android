package com.hkm.disqus.api;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.hkm.disqus.DisqusConstants;
import com.hkm.disqus.api.model.oauth2.AccessToken;
import com.hkm.disqus.api.resources.AccessTokenService;
import com.hkm.disqus.application.AuthorizeActivity;
import com.hkm.disqus.application.AuthorizeUtils;
import com.hkm.disqus.application.authorizeAccessToken;
import com.hkm.disqus.application.capclient;
import com.squareup.okhttp.RequestBody;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by hesk on 5/6/15.
 */
public class AuthMgr {
    private static final String PREF_AUTH = "AuthManager.Preferences";
    private static final String PREF_TOKEN = ".access_token";
    private static final String PREF_EXPIRE = ".expires_in";
    private static final String PREF_REFRESH_TOKEN = ".refresh_token";
    private static final String PREF_SCOPE = ".scope";
    private Context appContext;
    private AccessTokenService accessTokenService;
    private ApiConfig config;

    public final static String TAG = "AUTH";

    private List<AuthenticationListener> listeners = new ArrayList<>();

    public void addListener(AuthenticationListener listener) {
        listeners.add(listener);
    }

    public void removeListener(AuthenticationListener listener) {
        listeners.remove(listener);
    }


    public AuthMgr(Context context, AccessTokenService accesstokenservice, ApiConfig configurations) {
        appContext = context;
        config = configurations;
        accessTokenService = accesstokenservice;
    }

    public void authorizeAsync(final String code) {
        if (code != null) {
            if (DisqusConstants.apiVersion == 1) {

                RequestBody rb = AuthorizeUtils.buildRequest(code, config.getApiKey(), config.getApiSecret(), config.getRedirectURI());

                new authorizeAccessToken(appContext, rb, new capclient.callback() {
                    @Override
                    public void onSuccess(String data) {
                        Log.d(TAG, "Acquiring Code Success final: " + data);
                    }

                    @Override
                    public void onFailure(String message, int code, boolean systematic) {
                        final String failure = "Acquire token failure:" + message;

                        for (AuthenticationListener listener : listeners) {
                            listener.onLoginFailed(failure);
                        }


                    }

                    @Override
                    public void beforeStart(capclient task) {
                        Log.d(TAG, "Code request Start: " + code);

                    }
                }, new authorizeAccessToken.gsonCallBack() {
                    @Override
                    public void gparser(AccessToken data) {
                        Log.d(TAG, "authorizeAccessToken.gsonCallBack Start token: " + data.accessToken);

                        for (AuthenticationListener listener : listeners) {
                            listener.onLogin(data);
                        }


                    }
                }).execute();


            } else if (DisqusConstants.apiVersion == 2) {
                accessTokenService.PostToken("authorization_code", config.getApiKey(), config.getApiSecret(), config.getRedirectURI(), code, new AccessTokenRequestCallBack(true));
            }
        } else {
            throw new NullPointerException("Code must be a non-null string!");
        }
    }


    public boolean isAuthenticated() {
        return getSharedPreferences().getString(PREF_TOKEN, null) != null;
    }

    private void writeToSharedPrefs(AccessToken accessToken) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(PREF_TOKEN, accessToken.accessToken);
        editor.putLong(PREF_EXPIRE, (accessToken.expiresIn * 1000l) + System.currentTimeMillis() - (60 * 1000));
        editor.putString(PREF_REFRESH_TOKEN, accessToken.refreshToken);
        editor.putString(PREF_SCOPE, accessToken.scope);
        editor.apply();
    }

    public void logout() {
        clearPrefs();

        for (AuthenticationListener listener : listeners) {
            listener.onLogout();
        }
    }

    public interface AuthenticationListener {
        void onLogin(AccessToken token);

        void onLoginFailed(String error);

        void onLogout();
    }

    public void saveTokenFromSuccessLogin(AccessToken token) {
        writeToSharedPrefs(token);
        config.setAccessToken(token.accessToken);
        setupAlarm();
        for (AuthenticationListener listener : listeners) {
            listener.onLogin(token);
        }
    }

    private class AccessTokenRequestCallBack implements Callback<AccessToken> {
        private boolean isLogin;

        private AccessTokenRequestCallBack(boolean isLogin) {
            this.isLogin = isLogin;
        }

        @Override
        public void success(AccessToken accessToken, Response response) {
            if (response.getStatus() == 200) {
                writeToSharedPrefs(accessToken); // save access token data to shared prefs.
                setupAlarm(); // setup refresh token alarm.
                //setRequestInterceptor(); // set value of access token on request intercept

                if (isLogin) {
                    for (AuthenticationListener listener : listeners) {
                        listener.onLogin(accessToken); // notify listeners about login.
                        //currentUserManager.getCurrentUser(null, true);
                        // force a network call to get current user and add to memory.
                    }
                }
            } else {
                if (isLogin) {
                    for (AuthenticationListener listener : listeners) {
                        listener.onLoginFailed("Http response was " + response.getStatus());
                    }
                } else { // token refresh failed. logout.
                    logout();
                    for (AuthenticationListener listener : listeners) {
                        listener.onLogout();
                    }
                }
            }
        }

        @Override
        public void failure(RetrofitError error) {
            if (isLogin) {
                for (AuthenticationListener listener : listeners) {
                    listener.onLoginFailed("code: " + error.getResponse().getStatus() + "\n" + error.getBody().toString() + "\n" + error.getUrl().toString());
                }
            } else { // token refresh failed. logout.
                logout();
                for (AuthenticationListener listener : listeners) {
                    listener.onLogout();
                }
            }
        }
    }

    /**
     * Setup an alarm to refresh tokens when they expire. This will only fire off when the process
     * is running. We must check tokens on startup
     */
    private void setupAlarm() {
        AlarmManager alarmManager = (AlarmManager) appContext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(DisqusConstants.ACTION_REFRESH_TOKEN);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(appContext, -1, intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, getExpireTime(), pendingIntent);
    }

    public void postRefreshTokenAsync() {
        if (getRefreshToken() != null) {
            accessTokenService.PostRefreshToken("refresh_token", config.getApiKey(), config.getApiSecret(), getRefreshToken(), new AccessTokenRequestCallBack(false));
        }
    }

    private String getRefreshToken() {
        return getSharedPreferences().getString(PREF_REFRESH_TOKEN, null);
    }

    private long getExpireTime() {
        return getSharedPreferences().getLong(PREF_EXPIRE, 0);
    }


    private void checkRefresh() {
        if (System.currentTimeMillis() > getExpireTime()) {
            // need to get refresh tokens as they have expired.
            postRefreshTokenAsync();
        } else {
            setupAlarm();
        }
    }

    private SharedPreferences getSharedPreferences() {
        return appContext.getSharedPreferences(PREF_AUTH, Context.MODE_PRIVATE);
    }

    private void clearPrefs() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear();
        editor.apply();
    }
}
