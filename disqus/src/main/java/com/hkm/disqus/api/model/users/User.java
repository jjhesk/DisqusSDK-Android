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
package com.hkm.disqus.api.model.users;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * User details
 */
public class User {

    @SerializedName("isFollowing")
    public boolean isFollowing;

    @SerializedName("disable3rdPartyTrackers")
    public boolean disable3rdPartyTrackers;

    @SerializedName("isFollowedBy")
    public boolean isFollowedBy;

    @SerializedName("connections")
    public Connections connections;

    @SerializedName("isPrimary")
    public boolean isPrimary;

    @SerializedName("id")
    public long id;

    @SerializedName("numFollowers")
    public int numFollowers;

    @SerializedName("rep")
    public double rep;

    @SerializedName("numFollowing")
    public int numFollowing;

    @SerializedName("numPosts")
    public int numPosts;

    @SerializedName("location")
    public String location;

    @SerializedName("isPrivate")
    public boolean isPrivate;

    @SerializedName("joinedAt")
    public Date joinedAt;

    @SerializedName("username")
    public String username;

    @SerializedName("numLikesReceived")
    public int numLikesReceived;

    @SerializedName("about")
    public String about;

    @SerializedName("name")
    public String name;

    @SerializedName("url")
    public String url;

    @SerializedName("numForumsFollowing")
    public int numForumsFollowing;

    @SerializedName("profileUrl")
    public String profileUrl;

    @SerializedName("reputation")
    public double reputation;

    @SerializedName("avatar")
    public UserAvatar avatar;

    @SerializedName("isAnonymous")
    public boolean isAnonymous;

}
