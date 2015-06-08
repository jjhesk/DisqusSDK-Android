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
     * Set config and set up the @link restafapter
     *
     * @param config the collection of configuration
     */


    /**
     * THe content
     *
     * @param config  the collection of configuration
     * @param context the context uneeded for your dailt vntry
     */
    public DisqusClient(ApiConfig config, Context context) {
        super(config);
        mcontent = context;
    }

    /**
     * create and return picasso
     *
     * @return the te ciea
     */
    public Picasso providesPicasso() {
        return new Picasso.Builder(mcontent)
                .downloader(new OkHttpDownloader(mcontent))
                .memoryCache(new LruCache(mcontent))
                .build();
    }
}
