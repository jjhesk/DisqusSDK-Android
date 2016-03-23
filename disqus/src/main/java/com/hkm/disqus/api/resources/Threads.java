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
import com.hkm.disqus.api.model.threads.Thread;

import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Threads resource
 *
 * @see <a href="https://disqus.com/api/docs/threads/">Documentation</a>
 */
public interface Threads {

    /**
     * Closes a thread
     *
     * @param thread as is
     * @return as is
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/threads/close/">Documentation</a>
     */
    @POST("/threads/close.json")
    Response<List<Thread>> close(@Query("thread") long thread) throws ApiException;

    /**
     * Closes a thread
     *
     * @param thread as is
     * @return as is
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/threads/close/">Documentation</a>
     */
    @POST("/threads/close.json")
    Response<List<Thread>> close(@Query("thread") long[] thread) throws ApiException;

    /**
     * Creates a new thread
     *
     * @param forum the name of the forum
     * @param title the title of the topic
     * @return as is
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/threads/create/">Documentation</a>
     */
    @POST("/threads/create.json")
    Response<Thread> create(@Query("forum") String forum,
                            @Query("title") String title) throws ApiException;

    /**
     * Creates a new thread
     *
     * @param forum          string
     * @param title          string
     * @param optionalParams extra things
     * @return as is
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/threads/create/">Documentation</a>
     */
    @POST("/threads/create.json")
    Response<Thread> create(@Query("forum") String forum,
                            @Query("title") String title,
                            @QueryMap Map<String, String> optionalParams) throws ApiException;

    /**
     * Returns thread details
     *
     * @param thread as is
     * @return as is
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/threads/details/">Documentation</a>
     */
    @GET("/threads/details.json")
    Response<Thread> details(@Query("thread") long thread) throws ApiException;

    /**
     * Returns thread details
     *
     * @param thread as is
     * @param forum  as is
     * @return as is
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/threads/details/">Documentation</a>
     */
    @GET("/threads/details.json")
    Response<Thread> details(
            @Query("thread") long thread,
            @Query("forum") String forum) throws ApiException;


    /**
     * Returns thread details
     *
     * @param thread as 1194797 http://hypebeast.com/?p=1194797 in format
     * @param forum  as is
     * @param cb     the callback
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/threads/details/">Documentation</a>
     */
    @GET("/threads/details.json")
    void detailsId(
            @Query("thread:ident") String thread,
            @Query("forum") String forum,
            Callback<Response<Thread>> cb
    ) throws ApiException;


    /**
     * Returns thread details
     *
     * @param thread as 1194797 http://hypebeast.com/?p=1194797 in format
     * @param forum  as is
     * @return as is
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/threads/details/">Documentation</a>
     */
    @GET("/threads/details.json")
    Response<Thread> details(
            @Query("thread:ident") String thread,
            @Query("forum") String forum) throws ApiException;


    /**
     * Returns thread details
     *
     * @param thread  as is
     * @param forum   as is
     * @param related as is
     * @return as is
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/threads/details/">Documentation</a>
     */
    @GET("/threads/details.json")
    Response<Thread> details(@Query("thread") long thread,
                             @Query("forum") String forum,
                             @Query("related") String[] related) throws ApiException;


    /**
     * * Return the list of the post from a specific thread ID
     * ex.
     * thread:ident  1008680 http://hypebeast.com/?p=1008680
     * forum     hypebeast
     *
     * @param identifer  Looks up a thread by ID
     * @param forum_name Defaults to null  looks up a forum by ID (aka short name)
     * @return the result
     * @throws ApiException any errors
     */
    @GET("/threads/listPosts.json")
    Response<List<Post>> listPostByID(@Query("thread:ident") String identifer, @Query("forum") String forum_name) throws ApiException;

    /**
     * @param identifer  the id in string
     * @param forum_name name
     * @param cb         callback object
     * @throws ApiException any errors
     */
    @GET("/threads/listPosts.json")
    void listPostByIDAsync(
            @Query("thread:ident") String identifer,
            @Query("forum") String forum_name,
            Callback<Response<List<Post>>> cb
    ) throws ApiException;
}
