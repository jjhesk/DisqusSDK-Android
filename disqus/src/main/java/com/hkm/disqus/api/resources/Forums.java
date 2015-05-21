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
import com.hkm.disqus.api.model.category.Category;
import com.hkm.disqus.api.model.forums.Forum;
import com.hkm.disqus.api.model.forums.Moderator;
import com.hkm.disqus.api.model.posts.Post;
import com.hkm.disqus.api.model.users.User;

import java.util.List;
import java.util.Map;


import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Forums resource
 *
 * @see <a href="https://disqus.com/api/docs/forums/">Documentation</a>
 */
public interface Forums {

    /**
     * Adds a moderator to a forum
     *
     * @param user  The user id of the moderator
     * @param forum The forum short name
     * @return The moderator id
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/addModerator/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/forums/addModerator.json")
    public Response<Moderator> addModerator(@Field("user") long user, @Query("forum") String forum)
            throws ApiException;

    /**
     * Creates a new forum
     *
     * @param website   The website url
     * @param name      The name of the forum
     * @param shortName The forum short name
     * @return The created forum
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/create/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/forums/create.json")
    public Response<Forum> create(@Field("website") String website,
                                  @Field("name") String name,
                                  @Field("short_name") String shortName) throws ApiException;

    /**
     * Creates a new forum
     *
     * @param website    The website url
     * @param name       The name of the forum
     * @param shortName  The forum short name
     * @param guidelines Forum guidelines
     * @return The created forum
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/create/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/forums/create.json")
    public Response<Forum> create(@Field("website") String website,
                                  @Field("name") String name,
                                  @Field("short_name") String shortName,
                                  @Field("guidelines") String guidelines) throws ApiException;

    /**
     * Returns forum details
     *
     * @param forum The forum short name
     * @return Details of the forum
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/details/">Documentation</a>
     */
    @GET("/forums/details.json")
    public Response<Forum> details(@Query("forum") String forum) throws ApiException;

    /**
     * Returns forum details
     *
     * @param forum   The forum short name
     * @param related Specify relations to include with the response. Allows: author
     * @return Details of the forum
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/details/">Documentation</a>
     */
    @GET("/forums/details.json")
    public Response<Forum> details(@Query("forum") String forum,
                                   @Query("related") String[] related) throws ApiException;

    /**
     * Follow a forum
     *
     * @param target The forum short name
     * @return A response object, data can be ignored
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/follow/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/forums/follow.json")
    public Response<List<Object>> follow(@Field("target") String target) throws ApiException;

    /**
     * Returns a list of categories within a forum
     *
     * @param forum The forum short name
     * @return A list of categories
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/listCategories/">Documentation</a>
     */
    @GET("/forums/listCategories.json")
    public Response<List<Category>> listCategories(@Query("forum") String forum)
            throws ApiException;

    /**
     * Returns a list of categories within a forum
     *
     * @param forum          The forum short name
     * @param optionalParams A map of optional parameters
     * @return A list of categories
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/listCategories/">Documentation</a>
     */
    @GET("/forums/listCategories.json")
    public Response<List<Category>> listCategories(@Query("forum") String forum,
                                                   @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of users following a forum
     *
     * @param forum The forum short name
     * @return A list of users
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/listFollowers/">Documentation</a>
     */
    @GET("/forums/listFollowers.json")
    public Response<List<User>> listFollowers(@Query("forum") String forum)
            throws ApiException;

    /**
     * Returns a list of users following a forum
     *
     * @param forum          The forum short name
     * @param optionalParams A map of optional parameters
     * @return A list of users
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/listFollowers/">Documentation</a>
     */
    @GET("/forums/listFollowers.json")
    public Response<List<User>> listFollowers(@Query("forum") String forum,
                                              @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of all moderators on a forum
     *
     * @param forum The forum short name
     * @return A list of moderators
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/listModerators/">Documentation</a>
     */
    @GET("/forums/listModerators.json")
    public Response<List<Moderator>> listModerators(@Query("forum") String forum)
            throws ApiException;

    /**
     * Returns a list of users active within a forum ordered by most comments made
     *
     * @param forum The forum short name
     * @return A list of users
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/listMostActiveUsers/">Documentation</a>
     */
    @GET("/forums/listMostActiveUsers.json")
    public Response<List<User>> listMostActiveUsers(@Query("forum") String forum)
            throws ApiException;

    /**
     * Returns a list of users active within a forum ordered by most comments made
     *
     * @param forum          The forum short name
     * @param optionalParams A map of optional parameters
     * @return A list of users
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/listMostActiveUsers/">Documentation</a>
     */
    @GET("/forums/listMostActiveUsers.json")
    public Response<List<User>> listMostActiveUsers(@Query("forum") String forum,
                                                    @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of users active within a forum ordered by most likes received
     *
     * @param forum The forum short name
     * @return A list of users
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/listMostLikedUsers/">Documentation</a>
     */
    @GET("/forums/listMostLikedUsers.json")
    public Response<List<User>> listMostLikedUsers(@Query("forum") String forum)
            throws ApiException;

    /**
     * Returns a list of users active within a forum ordered by most likes received
     *
     * @param forum          The forum short name
     * @param optionalParams A map of optional parameters
     * @return A list of users
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/listMostLikedUsers/">Documentation</a>
     */
    @GET("/forums/listMostLikedUsers.json")
    public Response<List<User>> listMostLikedUsers(@Query("forum") String forum,
                                                   @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of posts within a forum
     *
     * @param forum The forum short name
     * @return A list if posts
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/listPosts/">Documentation</a>
     */
    @GET("/forums/listPosts.json")
    public Response<List<Post>> listPosts(@Query("forum") String forum) throws ApiException;

    /**
     * Returns a list of posts within a forum
     *
     * @param forum          The forum short name
     * @param optionalParams A map of optional parameters
     * @return A list of posts
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/listPosts/">Documentation</a>
     */
    @GET("/forums/listPosts.json")
    public Response<List<Post>> listPosts(@Query("forum") String forum,
                                          @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of posts within a forum
     *
     * @param forum          The forum short name
     * @param related        Specify relations to include with the response. Allows: thread
     * @param include        Filter posts by status. Allows: unapproved, approved, spam, deleted,
     *                       flagged, highlighted
     * @param optionalParams A map of optional parameters
     * @return A list of posts
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/listPosts/">Documentation</a>
     */
    @GET("/forums/listPosts.json")
    public Response<List<Post>> listPosts(@Query("forum") String forum,
                                          @Query("related") String[] related,
                                          @Query("include") String[] include,
                                          @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of threads within a forum sorted by the date created
     *
     * @param forum The forum short name
     * @return A list of threads
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/listThreads/">Documentation</a>
     */
    @GET("/forums/listThreads.json")
    public Response<List<Thread>> listThreads(@Query("forum") String forum) throws ApiException;

    /**
     * Returns a list of threads within a forum sorted by the date created
     *
     * @param forum          The forum short name
     * @param optionalParams A map of optional parameters
     * @return A list of threads
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/listThreads/">Documentation</a>
     */
    @GET("/forums/listThreads.json")
    public Response<List<Thread>> listThreads(@Query("forum") String forum,
                                              @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of threads within a forum sorted by the date created
     *
     * @param forum          The forum short name
     * @param related        Specify relations to include with the response. Allows: forum, author
     * @param include        Filter threads by status. Allows: open, closed, killed
     * @param optionalParams A map of optional parameters
     * @return A list of threads
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/listThreads/">Documentation</a>
     */
    @GET("/forums/listThreads.json")
    public Response<List<Thread>> listThreads(@Query("forum") String forum,
                                              @Query("related") String[] related,
                                              @Query("include") String[] include,
                                              @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of users active within a forum
     *
     * @param forum The forum short name
     * @return A list of users
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/listUsers/">Documentation</a>
     */
    @GET("/forums/listUsers.json")
    public Response<List<User>> listUsers(@Query("forum") String forum) throws ApiException;

    /**
     * Returns a list of users active within a forum
     *
     * @param forum          The forum short name
     * @param optionalParams A map of optional parameters
     * @return A List of users
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/listUsers/">Documentation</a>
     */
    @GET("/forums/listUsers.json")
    public Response<List<User>> listUsers(@Query("forum") String forum,
                                          @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Removes a moderator from a forum
     *
     * @param moderator The moderator id
     * @return The removed moderator
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/removeModerator/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/forums/removeModerator.json")
    public Response<Moderator> removeModerator(@Field("moderator") long moderator)
            throws ApiException;

    /**
     * Unfollow a forum
     *
     * @param target The forum short name
     * @return A response object, data can be ignored
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/unfollow/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/forums/unfollow.json")
    public Response<List<Object>> unfollow(@Field("target") String target) throws ApiException;

    /**
     * Updates forum details
     *
     * @param forum      The forum short name
     * @param website    The website url
     * @param name       The name of the forum
     * @param guidelines Forum guidelines
     * @return The updated forum
     * @throws ApiException
     * @see <a href="https://disqus.com/api/docs/forums/update/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/forums/update.json")
    public Response<Forum> update(@Field("forum") String forum,
                                  @Field("website") String website,
                                  @Field("name") String name,
                                  @Field("guidelines") String guidelines) throws ApiException;

}
