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
package com.hkm.disqus.api.model.posts;

import com.google.gson.annotations.SerializedName;

/**
 * Post media
 */
public class Media {

    @SerializedName("forum")
    public String forum;

    @SerializedName("thumbnailUrl")
    public String thumbnailUrl;

    @SerializedName("description")
    public String description;

    @SerializedName("thread")
    public String thread;

    @SerializedName("title")
    public String title;

    @SerializedName("url")
    public String url;

    @SerializedName("mediaType")
    public String mediaType;

    @SerializedName("html")
    public String html;

    @SerializedName("location")
    public String location;

    @SerializedName("resolvedUrl")
    public String resolvedUrl;

    @SerializedName("post")
    public String post;

    @SerializedName("thumbnailURL")
    public String thumbnailURL;

    @SerializedName("type")
    public String type;

    @SerializedName("metadata")
    public Metadata metadata;

}
