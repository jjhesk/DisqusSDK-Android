package com.hkm.disqus.api.exception;

/**
 * Created by hesk on 2/6/15.
 */
public final class Check {

    public static Object checkNotNull(Object param, String message) {
        if (param == null) {
            throw new NullPointerException(message);
        }
        return param;
    }
}