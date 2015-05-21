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
package com.hkm.disqus.api.resources;

import com.hkm.disqus.api.exception.ApiException;
import com.hkm.disqus.api.model.Response;
import com.hkm.disqus.api.model.applications.Usage;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Applications resource
 *
 * @see <a href="https://disqus.com/api/docs/applications/">Documentation</a>
 */
public interface Applications {

    /**
     * Returns the API usage per day for this application
     *
     * @return Usage data for the application
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/applications/listUsage/">Documentation</a>
     */
    @GET("/applications/listUsage.json")
    public Response<Usage> listUsage() throws ApiException;

    /**
     * Returns the API usage per day for this application
     *
     * @param callback Callback for async result
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/applications/listUsage/">Documentation</a>
     */
    @GET("/applications/listUsage.json")
    public void listUsage(Callback<Response<Usage>> callback) throws ApiException;

    /**
     * Returns the API usage per day for this application
     *
     * @param days Number of days
     * @return Usage data for the application
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/applications/listUsage/">Documentation</a>
     */
    @GET("/applications/listUsage.json")
    public Response<Usage> listUsage(@Query("days") int days) throws ApiException;

    /**
     * Returns the API usage per day for this application
     *
     * @param days     Number of days
     * @param callback Callback for async result
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/applications/listUsage/">Documentation</a>
     */
    @GET("/applications/listUsage.json")
    public void listUsage(@Query("days") int days,
                          Callback<Response<Usage>> callback) throws ApiException;

    /**
     * Returns the API usage per day for this application
     *
     * @param application Application id
     * @param days        Number of days
     * @return Usage data for the application
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/applications/listUsage/">Documentation</a>
     */
    @GET("/applications/listUsage.json")
    public Response<Usage> listUsage(@Query("application") int application,
                                     @Query("days") Integer days) throws ApiException;

    /**
     * Returns the API usage per day for this application
     *
     * @param application Application id
     * @param days        Number of days
     * @param callback    Callback for async result
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/applications/listUsage/">Documentation</a>
     */
    @GET("/applications/listUsage.json")
    public void listUsage(@Query("application") int application,
                          @Query("days") Integer days,
                          Callback<Response<Usage>> callback) throws ApiException;

}
