package com.hkm.disqus.api.retrofitworker;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * build an encoded url string with params
 */
public class UrlParam {

    private String encodedUrl;
    private Map<String, String> paramMap;

    private UrlParam(Builder init) {
        this.paramMap = init.params;
        try {
            encodedUrl = init.baseUrl + buildParams();
        } catch (UnsupportedEncodingException e) {
            Log.e("", "Error encoding url", e);
        }
    }

    public String getEncodedUrl() {
        return encodedUrl;
    }

    public String getValue(String param) {
        return paramMap.get(param);
    }

    private String buildParams() throws UnsupportedEncodingException {
        boolean isFirst = true;
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : paramMap.keySet()) {
            if (isFirst) {
                stringBuilder.append("?");
                isFirst = false;
            } else {
                stringBuilder.append("&");
            }
            stringBuilder.append(URLEncoder.encode(key, "UTF-8"));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(paramMap.get(key), "UTF-8"));
        }
        return stringBuilder.toString();
    }

    public static class Builder {
        Map<String, String> params = new HashMap<>();
        String baseUrl;

        public Builder(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public Builder addParam(String param, String value) {
            params.put(param, value);
            return this;
        }

        public UrlParam build() {
            return new UrlParam(this);
        }
    }

}
