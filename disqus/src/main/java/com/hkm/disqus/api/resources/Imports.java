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
import com.hkm.disqus.api.model.imports.Import;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Imports resource
 *
 * @see <a href="https://disqus.com/api/docs/imports/">Documentation</a>
 */
public interface Imports {

    /**
     * Get import details
     *
     * @see <a href="https://disqus.com/api/docs/imports/">Documentation</a>
     * @param forum forum name
     * @param group the group ID or name the group ID or name
     * @return return the result object schema
     * @throws ApiException any errors during the http transaction
     */
    @GET("/imports/details.json")
    public Response<Import> details(@Query("forum") String forum,
                                    @Query("group") long group) throws ApiException;

    /**
     * Get list of imports
     *
     * @see <a href="https://disqus.com/api/docs/imports/">Documentation</a>
     * @param forum forum name
     * @return return the result object schema
     * @throws ApiException any errors during the http transaction
     */
    @GET("/imports/list.json")
    public Response<List<Import>> list(@Query("forum") String forum) throws ApiException;

}
