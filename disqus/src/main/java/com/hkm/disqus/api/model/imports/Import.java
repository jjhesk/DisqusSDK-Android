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
package com.hkm.disqus.api.model.imports;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Import details
 */
public class Import {

    @SerializedName("forum")
    public String forum;

    @SerializedName("chunksTotal")
    public int chunksTotal;

    @SerializedName("platform")
    public String platform;

    @SerializedName("finishedAt")
    public Date finishedAt;

    @SerializedName("startedAt")
    public Date startedAt;

    @SerializedName("statusName")
    public String statusName;

    @SerializedName("chunksDone")
    public int chunksDone;

    @SerializedName("id")
    public long id;

    @SerializedName("statusCode")
    public int statusCode;

}