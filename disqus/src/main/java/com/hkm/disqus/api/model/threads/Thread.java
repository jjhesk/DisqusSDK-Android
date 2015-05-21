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
package com.hkm.disqus.api.model.threads;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Thread
 */
public class Thread {

    @SerializedName("feed")
    public String feed;

    @SerializedName("category")
    public Object category;

    @SerializedName("identifiers")
    public List<String> identifiers;

    @SerializedName("forum")
    public Object forum;

    @SerializedName("clean_title")
    public String cleanTitle;

    @SerializedName("dislikes")
    public int dislikes;

    @SerializedName("isDeleted")
    public boolean isDeleted;

    @SerializedName("author")
    public Object author;

    @SerializedName("userScore")
    public int userScore;

    @SerializedName("raw_message")
    public String rawMessage;

    @SerializedName("id")
    public long id;

    @SerializedName("isClosed")
    public boolean isClosed;

    @SerializedName("posts")
    public int posts;

    @SerializedName("userSubscription")
    public boolean userSubscription;

    @SerializedName("link")
    public String link;

    @SerializedName("createdAt")
    public Date createdAt;

    @SerializedName("title")
    public String title;

    @SerializedName("message")
    public String message;

    @SerializedName("slug")
    public String slug;

    @SerializedName("highlightedPost")
    public String highlightedPost;

    @SerializedName("likes")
    public int likes;

}
