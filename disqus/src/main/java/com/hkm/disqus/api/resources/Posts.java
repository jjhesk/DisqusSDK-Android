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
import com.hkm.disqus.api.model.posts.Post;
import com.hkm.disqus.api.model.posts.Vote;

import java.util.List;
import java.util.Map;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Posts resource
 *
 * @see <a href="https://disqus.com/api/docs/posts/">Documentation</a>
 */
public interface Posts {

    /**
     * Approves the requested post
     *
     * @param post as is
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/approve/">Documentation</a>
     */
    @POST("/posts/approve.json")
    Response<List<Post>> approve(@Query("post") long post) throws ApiException;

    /**
     * Approves the requested posts
     *
     * @param posts as is
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/approve/">Documentation</a>
     */
    @POST("/posts/approve.json")
    Response<List<Post>> approve(@Query("post") long[] posts) throws ApiException;

    /**
     * Creates a new post
     *
     * @param message as is
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/create/">Documentation</a>
     */
    @POST("/posts/create.json")
    Response<Post> create(@Query("message") String message) throws ApiException;

    /**
     * Creates a new post
     *
     * @param message        as is
     * @param optionalParams as is
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/create/">Documentation</a>
     */
    @POST("/posts/create.json")
    Response<Post> create(
            @Query("message") String message,
            @QueryMap Map<String, String> optionalParams)
            throws ApiException;


    /**
     * create a post with call back
     *
     * @param messagek the message to send
     * @param state    Choices: unapproved, approved, spam, killed
     * @param thread   the thread ID
     * @param cbpost   the call back data type
     * @throws ApiException the error from transactions
     */
    @POST("/posts/create.json")
    void create(
            @Query("message") String messagek,
            @Query("state") String state,
            @Query("thread") String thread,
            Callback<Response<Post>> cbpost)
            throws ApiException;

    /**
     * create a post with call back
     *
     * @param messagek the message to send
     * @param thread   the thread ID
     * @param cbpost   the call back data type
     * @throws ApiException the error from transactions
     */
    @POST("/posts/create.json")
    void create(
            @Query("message") String messagek,
            @Query("thread") String thread,
            Callback<Response<Post>> cbpost)
            throws ApiException;

    /**
     * create a post with call back
     *
     * @param messagek the message to send
     * @param cbpost   the call back data type
     * @throws ApiException the error from transactions
     */
    @POST("/posts/create.json")
    void create(
            @Query("message") String messagek,
            Callback<Response<Post>> cbpost)
            throws ApiException;

    /**
     * Returns information about a post
     *
     * @param post as is
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/details/">Documentation</a>
     */
    @GET("/posts/details.json")
    public Response<Post> details(@Query("post") long post) throws ApiException;

    /**
     * Returns information about a post
     *
     * @param post    as is
     * @param related as is
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/details/">Documentation</a>
     */
    @GET("/posts/details.json")
    public Response<Post> details(
            @Query("post") long post,
            @Query("related") String[] related)
            throws ApiException;

    /**
     * Returns the hierarchal tree of a post (all parents)
     *
     * @param post as is
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/getContext/">Documentation</a>
     */
    @GET("/posts/getContext.json")
    public Response<List<Post>> getContext(@Query("post") long post) throws ApiException;


    /**
     * @param post    as is
     * @param depth   as is
     * @param related as is
     * @return Response object schema
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/getContext/">Documentation</a>
     */
    @GET("/posts/getContext.json")
    public Response<List<Post>> getContext(
            @Query("post") long post,
            @Query("depth") Integer depth,
            @Query("related") String[] related)
            throws ApiException;

    /**
     * Returns a list of posts ordered by the date created
     *
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/list/">Documentation</a>
     */
    @GET("/posts/list.json")
    public Response<List<Post>> list() throws ApiException;

    /**
     * Returns a list of posts ordered by the date created
     *
     * @param optionalParams as is
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/list/">Documentation</a>
     */
    @GET("/posts/list.json")
    public Response<List<Post>> list(@QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of posts ordered by the number of likes recently
     *
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/listPopular/">Documentation</a>
     */
    @GET("/posts/listPopular.json")
    public Response<List<Post>> listPopular() throws ApiException;

    /**
     * Returns a list of posts ordered by the number of likes recently
     *
     * @param optionalParams as is
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/listPopular/">Documentation</a>
     */
    @GET("/posts/listPopular.json")
    public Response<List<Post>> listPopular(@QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Deletes the requested post
     *
     * @param post as is
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/remove/">Documentation</a>
     */
    @POST("/posts/remove.json")
    public Response<List<Post>> remove(@Query("post") long post) throws ApiException;

    /**
     * Deletes the requested posts
     *
     * @param posts as is
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/remove/">Documentation</a>
     */
    @POST("/posts/remove.json")
    public Response<List<Post>> remove(@Query("post") long[] posts) throws ApiException;

    /**
     * Reports a post
     *
     * @param post as is
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/report/">Documentation</a>
     */
    @POST("/posts/report.json")
    public Response<Post> report(@Query("post") long post) throws ApiException;

    /**
     * Undeletes the requested post
     *
     * @param post as is
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/restore/">Documentation</a>
     */
    @POST("/posts/restore.json")
    public Response<List<Post>> restore(@Query("post") long post) throws ApiException;

    /**
     * Undeletes the requested posts
     *
     * @param posts as is
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/restore/">Documentation</a>
     */
    @POST("/posts/restore.json")
    public Response<List<Post>> restore(@Query("post") long[] posts) throws ApiException;

    /**
     * Marks the requested post as spam
     *
     * @param post as is
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/spam/">Documentation</a>
     */
    @POST("/posts/spam.json")
    public Response<List<Post>> spam(@Query("post") long post) throws ApiException;

    /**
     * Marks the requested posts as spam
     *
     * @param posts as is
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/spam/">Documentation</a>
     */
    @POST("/posts/spam.json")
    public Response<List<Post>> spam(@Query("post") long[] posts) throws ApiException;

    /**
     * Updates information on a post
     *
     * @param post    as is
     * @param message as is
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/update/">Documentation</a>
     */
    @POST("/posts/update.json")
    public Response<Post> update(@Query("post") long post,
                                 @Query("message") String message) throws ApiException;

    /**
     * Register a vote on a post
     *
     * @param post as is
     * @param vote as is
     * @return as is
     * @throws ApiException any error incurred during the api transaction in http
     * @see <a href="https://disqus.com/api/docs/posts/vote/">Documentation</a>
     */
    @POST("/posts/vote.json")
    public Response<Vote> vote(@Query("post") long post,
                               @Query("vote") int vote) throws ApiException;

}
