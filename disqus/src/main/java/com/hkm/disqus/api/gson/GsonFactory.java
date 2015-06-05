package com.hkm.disqus.api.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.util.Date;

public class GsonFactory {

    public static Gson newGsonInstance() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT);
        gsonBuilder.registerTypeAdapter(Date.class, new DateAdapter());
        return gsonBuilder.create();
    }
}
