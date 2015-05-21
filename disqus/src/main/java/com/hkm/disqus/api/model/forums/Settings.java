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
package com.hkm.disqus.api.model.forums;

import com.google.gson.annotations.SerializedName;

/**
 * Settings
 */
public class Settings {

    @SerializedName("canEnableSponsoredComments")
    public boolean canEnableSponsoredComments;

    @SerializedName("hasCustomAvatar")
    public boolean hasCustomAvatar;

    @SerializedName("allowAnonPost")
    public boolean allowAnonPost;

    @SerializedName("allowMedia")
    public boolean allowMedia;

    @SerializedName("disable3rdPartyTrackers")
    public boolean disable3rdPartyTrackers;

    @SerializedName("audienceSyncEnabled")
    public boolean audienceSyncEnabled;

    @SerializedName("discoveryLocked")
    public boolean discoveryLocked;

    @SerializedName("ssoRequired")
    public boolean ssoRequired;

    @SerializedName("sponsoredCommentsEnabled")
    public boolean sponsoredCommentsEnabled;

}
