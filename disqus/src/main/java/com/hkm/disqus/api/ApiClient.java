/*
 * Copyright 2014 Phil Bayfield
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hkm.disqus.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hkm.disqus.DisqusConstants;
import com.hkm.disqus.api.exception.ApiException;
import com.hkm.disqus.api.exception.BadRequestException;
import com.hkm.disqus.api.exception.ForbiddenException;
import com.hkm.disqus.api.gson.ApplicationsUsageDeserializer;
import com.hkm.disqus.api.gson.BlacklistsEntryTypeAdapterFactory;
import com.hkm.disqus.api.gson.PostTypeAdapterFactory;
import com.hkm.disqus.api.gson.ThreadTypeAdapterFactory;
import com.hkm.disqus.api.model.applications.Usage;
import com.hkm.disqus.api.model.posts.Media;
import com.hkm.disqus.api.resources.Applications;
import com.hkm.disqus.api.resources.Blacklists;
import com.hkm.disqus.api.resources.Categories;
import com.hkm.disqus.api.resources.Exports;
import com.hkm.disqus.api.resources.Feeds;
import com.hkm.disqus.api.resources.Forums;
import com.hkm.disqus.api.resources.Imports;
import com.hkm.disqus.api.resources.Notes;
import com.hkm.disqus.api.resources.Posts;
import com.hkm.disqus.api.resources.Threads;
import com.hkm.disqus.api.resources.Users;
import com.hkm.disqus.api.resources.notes.Templates;

import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * A wrapper round the Retrofit {@link RestAdapter}
 */
public class ApiClient {

    /**
     * Base URL for all Disqus endpoints
     */
    private static final String BASE_URL = "https://disqus.com/api/3.0";

    /**
     * User agent
     */
    private static final String USER_AGENT = "Disqus Android/0.1";

    /**
     * Rest adapter
     */
    private RestAdapter mAdapter;

    /**
     * Set config and set up the {@link RestAdapter}
     *
     * @param config the collection of configuration
     */
    public ApiClient(final ApiConfig config) {
        // Build Gson with Disqus date format and type adapters
        Gson gson = new GsonBuilder()
                .setDateFormat(DisqusConstants.DATE_FORMAT)
                .registerTypeAdapter(Usage.class, new ApplicationsUsageDeserializer())
                .registerTypeAdapterFactory(new BlacklistsEntryTypeAdapterFactory())
                .registerTypeAdapterFactory(new PostTypeAdapterFactory())
                .registerTypeAdapterFactory(new ThreadTypeAdapterFactory())
                .create();

        // Build RestAdapter
        mAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(config != null ? config.getLogLevel() : RestAdapter.LogLevel.NONE)
                .setErrorHandler(new ErrorHandler() {
                    @Override
                    public Throwable handleError(RetrofitError cause) {
                        Response response = cause.getResponse();
                        if (response != null) {
                            switch (response.getStatus()) {
                                case 400:
                                    return new BadRequestException(cause);
                                case 401:
                                    return new ForbiddenException(cause);
                            }
                        }
                        return new ApiException(cause);
                    }
                })
                .setRequestInterceptor(new RequestInterceptor() {

                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("User-Agent", USER_AGENT);
                        if (config != null) {
                            // Public/secret key query params
                            if (config.getApiSecret() != null) {
                                request.addQueryParam("api_secret", config.getApiSecret());
                            } else if (config.getApiKey() != null) {
                                request.addQueryParam("api_key", config.getApiKey());
                            } else {

                            }

                            // Access token query param
                            if (config.getAccessToken() != null) {
                                request.addQueryParam("access_token", config.getAccessToken());
                            }

                            // Referrer
                            if (config.getReferrer() != null) {
                                request.addHeader("Referer", config.getReferrer());
                            }
                        }
                    }

                })
                .setConverter(new GsonConverter(gson))
                .build();
    }

    /**
     * Create applications resource
     *
     * @return the object
     */
    public Applications createApplications() {
        return mAdapter.create(Applications.class);
    }

    /**
     * Create blacklists resource
     *
     * @return the object
     */
    public Blacklists createBlacklists() {
        return mAdapter.create(Blacklists.class);
    }

    /**
     * Create categories resource
     *
     * @return the object
     */
    public Categories createCategories() {
        return mAdapter.create(Categories.class);
    }

    /**
     * Create exports resource
     *
     * @return the object
     */
    public Exports createExports() {
        return mAdapter.create(Exports.class);
    }

    /**
     * Create feeds resource
     *
     * @return the object
     */
    public Feeds createFeeds() {
        return mAdapter.create(Feeds.class);
    }

    /**
     * Create forums resource
     *
     * @return the object
     */
    public Forums createForums() {
        return mAdapter.create(Forums.class);
    }

    /**
     * Create imports resource
     *
     * @return the object
     */
    public Imports createImports() {
        return mAdapter.create(Imports.class);
    }

    /**
     * Create media resource
     *
     * @return the object
     */
    public Media createMedia() {
        return mAdapter.create(Media.class);
    }

    /**
     * Create notes resource
     *
     * @return the object
     */
    public Notes createNotes() {
        return mAdapter.create(Notes.class);
    }

    /**
     * Create notes/templates resource
     *
     * @return the object
     */
    public Templates createNotesTemplates() {
        return mAdapter.create(Templates.class);
    }

    /**
     * Create posts resource
     *
     * @return the object
     */
    public Posts createPosts() {
        return mAdapter.create(Posts.class);
    }

    /**
     * Create threads resource
     *
     * @return Threads
     */
    public Threads createThreads() {
        return mAdapter.create(Threads.class);
    }

    /**
     * Create users resource
     *
     * @return users
     */
    public Users createUsers() {
        return mAdapter.create(Users.class);
    }

}
