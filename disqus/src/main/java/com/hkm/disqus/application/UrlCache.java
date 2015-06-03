package com.hkm.disqus.application;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebResourceResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hesk on 3/6/15.
 */
public class UrlCache {
    public static final long ONE_SECOND = 1000L;
    public static final long ONE_MINUTE = 60L * ONE_SECOND;
    public static final long ONE_HOUR = 60L * ONE_MINUTE;
    public static final long ONE_DAY = 24 * ONE_HOUR;

    private static class CacheEntry {
        public String url;
        public String fileName;
        public String mimeType;
        public String encoding;
        public long maxAgeMillis;

        private CacheEntry(String url, String fileName,
                           String mimeType, String encoding, long maxAgeMillis) {

            this.url = url;
            this.fileName = fileName;
            this.mimeType = mimeType;
            this.encoding = encoding;
            this.maxAgeMillis = maxAgeMillis;
        }
    }


    protected Map<String, CacheEntry> cacheEntries = new HashMap<String, CacheEntry>();
    protected AppCompatActivity activity = null;
    protected File rootDir = null;

    public static String LOG_TAG = "cacheEntry";

    public UrlCache(AppCompatActivity activity) {
        this.activity = activity;
        this.rootDir = this.activity.getFilesDir();
    }

    public UrlCache(AppCompatActivity activity, File rootDir) {
        this.activity = activity;
        this.rootDir = rootDir;
    }


    public void register(String url, String cacheFileName,
                         String mimeType, String encoding,
                         long maxAgeMillis) {

        CacheEntry entry = new CacheEntry(url, cacheFileName, mimeType, encoding, maxAgeMillis);

        this.cacheEntries.put(url, entry);
    }


    public WebResourceResponse load(String url) {
        CacheEntry cacheEntry = this.cacheEntries.get(url);

        if (cacheEntry == null) return null;

        File cachedFile = new File(this.rootDir.getPath() + File.separator + cacheEntry.fileName);

        if (cachedFile.exists()) {
            long cacheEntryAge = System.currentTimeMillis() - cachedFile.lastModified();
            if (cacheEntryAge > cacheEntry.maxAgeMillis) {
                cachedFile.delete();

                //cached file deleted, call load() again.
                Log.d(LOG_TAG, "Deleting from cache: " + url);
                return load(url);
            }

            //cached file exists and is not too old. Return file.
            Log.d(LOG_TAG, "Loading from cache: " + url);
            try {
                return new WebResourceResponse(
                        cacheEntry.mimeType, cacheEntry.encoding, new FileInputStream(cachedFile));
            } catch (FileNotFoundException e) {
                Log.d(LOG_TAG, "Error loading cached file: " + cachedFile.getPath() + " : "
                        + e.getMessage(), e);
            }

        } else {
            try {
                downloadAndStore(url, cacheEntry, cachedFile);

                //now the file exists in the cache, so we can just call this method again to read it.
                return load(url);
            } catch (Exception e) {
                Log.d(LOG_TAG, "Error reading file over network: " + cachedFile.getPath(), e);
            }
        }

        return null;
    }


    private void downloadAndStore(String url, CacheEntry cacheEntry, File cachedFile)
            throws IOException {

        URL urlObj = new URL(url);
        URLConnection urlConnection = urlObj.openConnection();
        InputStream urlInput = urlConnection.getInputStream();

        FileOutputStream fileOutputStream =
                this.activity.openFileOutput(cacheEntry.fileName, Context.MODE_PRIVATE);

        int data = urlInput.read();
        while (data != -1) {
            fileOutputStream.write(data);

            data = urlInput.read();
        }

        urlInput.close();
        fileOutputStream.close();
        Log.d(LOG_TAG, "Cache file: " + cacheEntry.fileName + " stored. ");
    }
}
