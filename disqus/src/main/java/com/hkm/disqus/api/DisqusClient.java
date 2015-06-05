package com.hkm.disqus.api;

import android.content.Context;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by hesk on 5/6/15.
 */
public class DisqusClient extends ApiClient {

    private Context mcontent;

    /**
     * Set config and set up the {@link RestAdapter}
     *
     * @param config the collection of configuration
     */
    public DisqusClient(ApiConfig config, Context context) {
        super(config);
        mcontent = context;
    }

    public Picasso providesPicasso() {
        return new Picasso.Builder(mcontent)
                .downloader(new OkHttpDownloader(mcontent))
                .memoryCache(new LruCache(mcontent))
                .build();
    }
}
