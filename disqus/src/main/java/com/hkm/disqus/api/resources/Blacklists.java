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
import com.hkm.disqus.api.model.blacklists.Entry;

import java.util.List;
import java.util.Map;


import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Blacklists resource
 *
 * @see <a href="https://disqus.com/api/docs/blacklists/">Documentation</a>
 */
public interface Blacklists {

    /**
     * Adds a domain entry/entries to the blacklist
     *
     * @param forum       The forum short name
     * @param domains     An array of domains to add
     * @param retroactive Apply to dates in the past
     * @param notes       Note to add to the entry
     * @return A list of the entries added
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/add/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/add.json")
    Response<List<Entry>> addDomains(@Field("forum") String forum,
                                     @Field("domain") String[] domains,
                                     @Field("retroactive") int retroactive,
                                     @Field("notes") String notes)
            throws ApiException;

    /**
     * Adds a domain entry/entries to the blacklist
     *
     * @param forum       The forum short name
     * @param domains     An array of domains to add
     * @param retroactive Apply to dates in the past
     * @param notes       Note to add to the entry
     * @param callback    Callback for async result
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/add/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/add.json")
    void addDomains(@Field("forum") String forum,
                    @Field("domain") String[] domains,
                    @Field("retroactive") int retroactive,
                    @Field("notes") String notes,
                    Callback<Response<List<Entry>>> callback) throws ApiException;

    /**
     * Adds a word entry/entries to the blacklist
     *
     * @param forum       The forum short name
     * @param words       An array of words to add
     * @param retroactive Apply to dates in the past
     * @param notes       Note to add to the entry
     * @return A list of the entries added
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/add/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/add.json")
    Response<List<Entry>> addWords(@Field("forum") String forum,
                                   @Field("word") String[] words,
                                   @Field("retroactive") int retroactive,
                                   @Field("notes") String notes)
            throws ApiException;

    /**
     * Adds a word entry/entries to the blacklist
     *
     * @param forum       The forum short name
     * @param words       An array of words to add
     * @param retroactive Apply to dates in the past
     * @param notes       Note to add to the entry
     * @param callback    Callback for async result
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/add/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/add.json")
    void addWords(@Field("forum") String forum,
                  @Field("word") String[] words,
                  @Field("retroactive") int retroactive,
                  @Field("notes") String notes,
                  Callback<Response<List<Entry>>> callback) throws ApiException;

    /**
     * Adds an IP entry/entries to the blacklist
     *
     * @param forum       The forum short name
     * @param ips         An array of ips to add
     * @param retroactive Apply to dates in the past
     * @param notes       Note to add to the entry
     * @return A list of the entries added
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/add/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/add.json")
    Response<List<Entry>> addIps(@Field("forum") String forum,
                                 @Field("ip") String[] ips,
                                 @Field("retroactive") int retroactive,
                                 @Field("notes") String notes) throws ApiException;

    /**
     * Adds an IP entry/entries to the blacklist
     *
     * @param forum       The forum short name
     * @param ips         An array of ips to add
     * @param retroactive Apply to dates in the past
     * @param notes       Note to add to the entry
     * @param callback    Callback for async result
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/add/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/add.json")
    void addIps(@Field("forum") String forum,
                @Field("ip") String[] ips,
                @Field("retroactive") int retroactive,
                @Field("notes") String notes,
                Callback<Response<List<Entry>>> callback) throws ApiException;

    /**
     * Adds a user entry/entries to the blacklist
     *
     * @param forum       The forum short name
     * @param users       An array of user ids to add
     * @param retroactive Apply to dates in the past
     * @param notes       Note to add to the entry
     * @return A list of the entries added
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/add/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/add.json")
    Response<List<Entry>> addUsers(@Field("forum") String forum,
                                   @Field("user") Long[] users,
                                   @Field("retroactive") int retroactive,
                                   @Field("notes") String notes)
            throws ApiException;

    /**
     * Adds a user entry/entries to the blacklist
     *
     * @param forum       The forum short name
     * @param users       An array of user ids to add
     * @param retroactive Apply to dates in the past
     * @param notes       Note to add to the entry
     * @param callback    Callback for async result
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/add/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/add.json")
    void addUsers(@Field("forum") String forum,
                  @Field("user") Long[] users,
                  @Field("retroactive") int retroactive,
                  @Field("notes") String notes,
                  Callback<Response<List<Entry>>> callback) throws ApiException;

    /**
     * Adds an email entry/entries to the blacklist
     *
     * @param forum       The forum short name
     * @param emails      An array of emails to add
     * @param retroactive Apply to dates in the past
     * @param notes       Note to add to the entry
     * @return A list of the entries added
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/add/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/add.json")
    Response<List<Entry>> addEmails(@Field("forum") String forum,
                                    @Field("email") String[] emails,
                                    @Field("retroactive") int retroactive,
                                    @Field("notes") String notes)
            throws ApiException;

    /**
     * Adds an email entry/entries to the blacklist
     *
     * @param forum       The forum short name
     * @param emails      An array of emails to add
     * @param retroactive Apply to dates in the past
     * @param notes       Note to add to the entry
     * @param callback    Callback for async result
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/add/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/add.json")
    void addEmails(@Field("forum") String forum,
                   @Field("email") String[] emails,
                   @Field("retroactive") int retroactive,
                   @Field("notes") String notes,
                   Callback<Response<List<Entry>>> callback) throws ApiException;

    /**
     * Returns a list of all blacklist entries
     *
     * @param forum The forum short name
     * @return A list of the entries
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/list/">Documentation</a>
     */
    @GET("/blacklists/list.json")
    Response<List<Entry>> list(@Query("forum") String forum) throws ApiException;

    /**
     * Returns a list of all blacklist entries
     *
     * @param forum    The forum short name
     * @param callback Callback for async result
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/list/">Documentation</a>
     */
    @GET("/blacklists/list.json")
    void list(@Query("forum") String forum,
              Callback<Response<List<Entry>>> callback) throws ApiException;

    /**
     * Returns a list of all blacklist entries
     *
     * @param forum          The forum short name
     * @param optionalParams A map of optional parameters
     * @return A list of the entries
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/list/">Documentation</a>
     */
    @GET("/blacklists/list.json")
    Response<List<Entry>> list(@Query("forum") String forum,
                               @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of all blacklist entries
     *
     * @param forum          The forum short name
     * @param optionalParams A map of optional parameters
     * @param callback       Callback for async result
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/list/">Documentation</a>
     */
    @GET("/blacklists/list.json")
    void list(@Query("forum") String forum,
              @QueryMap Map<String, String> optionalParams,
              Callback<Response<List<Entry>>> callback) throws ApiException;

    /**
     * Returns a list of all blacklist entries
     *
     * @param forum          The forum short name
     * @param related        Get relations in response
     * @param optionalParams A map of optional parameters
     * @return A list of the entries
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/list/">Documentation</a>
     */
    @GET("/blacklists/list.json")
    Response<List<Entry>> list(@Query("forum") String forum,
                               @Query("related") String[] related,
                               @QueryMap Map<String, String> optionalParams)
            throws ApiException;

    /**
     * Returns a list of all blacklist entries
     *
     * @param forum          The forum short name
     * @param related        Get relations in response
     * @param optionalParams A map of optional parameters
     * @param callback       Callback for async result
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/list/">Documentation</a>
     */
    @GET("/blacklists/list.json")
    void list(@Query("forum") String forum,
              @Query("related") String[] related,
              @QueryMap Map<String, String> optionalParams,
              Callback<Response<List<Entry>>> callback) throws ApiException;

    /**
     * Removes a domain entry/entries to the blacklist
     *
     * @param forum   The forum short name
     * @param domains An array of domains to remove
     * @return A list of the entries removed
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/remove/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/remove.json")
    Response<List<Entry>> removeDomains(@Field("forum") String forum,
                                        @Field("domain") String[] domains)
            throws ApiException;

    /**
     * Removes a domain entry/entries to the blacklist
     *
     * @param forum    The forum short name
     * @param domains  An array of domains to remove
     * @param callback Callback for async result
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/remove/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/remove.json")
    void removeDomains(@Field("forum") String forum,
                       @Field("domain") String[] domains,
                       Callback<Response<List<Entry>>> callback) throws ApiException;

    /**
     * Removes a word entry/entries to the blacklist
     *
     * @param forum The forum short name
     * @param words An array of words to remove
     * @return A list of the entries removed
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/remove/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/remove.json")
    Response<List<Entry>> removeWords(@Field("forum") String forum,
                                      @Field("word") String[] words)
            throws ApiException;

    /**
     * Removes a word entry/entries to the blacklist
     *
     * @param forum    The forum short name
     * @param words    An array of words to remove
     * @param callback Callback for async result
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/remove/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/remove.json")
    void removeWords(@Field("forum") String forum,
                     @Field("word") String[] words,
                     Callback<Response<List<Entry>>> callback) throws ApiException;

    /**
     * Removes an IP entry/entries to the blacklist
     *
     * @param forum The forum short name
     * @param ips   An array of ips to remove
     * @return A list of the entries removed
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/remove/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/remove.json")
    Response<List<Entry>> removeIps(@Field("forum") String forum,
                                    @Field("ip") String[] ips) throws ApiException;

    /**
     * Removes an IP entry/entries to the blacklist
     *
     * @param forum    The forum short name
     * @param ips      An array of ips to remove
     * @param callback Callback for async result
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/remove/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/remove.json")
    void removeIps(@Field("forum") String forum,
                   @Field("ip") String[] ips,
                   Callback<Response<List<Entry>>> callback) throws ApiException;

    /**
     * Removes a user entry/entries to the blacklist
     *
     * @param forum The forum short name
     * @param users An array of user ids to remove
     * @return A list of the entries removed
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/remove/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/remove.json")
    Response<List<Entry>> removeUsers(@Field("forum") String forum,
                                      @Field("user") Long[] users)
            throws ApiException;

    /**
     * Removes a user entry/entries to the blacklist
     *
     * @param forum    The forum short name
     * @param users    An array of user ids to remove
     * @param callback Callback for async result
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/remove/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/remove.json")
    void removeUsers(@Field("forum") String forum,
                     @Field("user") Long[] users,
                     Callback<Response<List<Entry>>> callback) throws ApiException;

    /**
     * Removes an email entry/entries to the blacklist
     *
     * @param forum  The forum short name
     * @param emails An array of emails to remove
     * @return A list of the entries removed
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/remove/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/remove.json")
    Response<List<Entry>> removeEmails(@Field("forum") String forum,
                                       @Field("email") String[] emails)
            throws ApiException;

    /**
     * Removes an email entry/entries to the blacklist
     *
     * @param forum    The forum short name
     * @param emails   An array of emails to remove
     * @param callback Callback for async result
     * @throws ApiException any errors
     * @see <a href="https://disqus.com/api/docs/blacklists/remove/">Documentation</a>
     */
    @FormUrlEncoded
    @POST("/blacklists/remove.json")
    void removeEmails(@Field("forum") String forum,
                      @Field("email") String[] emails,
                      Callback<Response<List<Entry>>> callback) throws ApiException;

}
