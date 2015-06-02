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
package com.hkm.disqus.api.model.oauth2;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


/**
 * Model representing an access token response
 *
 * This model implements the {@link Parcelable} interface to simplify passing it between activities,
 * for example when using Authorize in an application.
 */
public class AccessToken implements Parcelable {

    /**
     * Parcelable creator implementation
     */
    public static final Creator<AccessToken> CREATOR = new Creator<AccessToken>() {

        @Override
        public AccessToken createFromParcel(Parcel source) {
            return new AccessToken(source);
        }

        @Override
        public AccessToken[] newArray(int size) {
            return new AccessToken[size];
        }

    };

    @SerializedName("username")
    public String username;

    @SerializedName("user_id")
    public long userId;

    @SerializedName("access_token")
    public String accessToken;

    @SerializedName("expires_in")
    public long expiresIn;

    @SerializedName("token_type")
    public String tokenType;

    @SerializedName("state")
    public String state;

    @SerializedName("scope")
    public String scope;

    @SerializedName("refresh_token")
    public String refreshToken;

    /**
     * No args constructor
     */
    public AccessToken() {
    }

    /**
     * Constructor with parcel
     *
     * @param source the original code for the properties
     */
    public AccessToken(Parcel source) {
        username = source.readString();
        userId = source.readLong();
        accessToken = source.readString();
        expiresIn = source.readLong();
        tokenType = source.readString();
        state = source.readString();
        scope = source.readString();
        refreshToken = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeLong(userId);
        dest.writeString(accessToken);
        dest.writeLong(expiresIn);
        dest.writeString(tokenType);
        dest.writeString(state);
        dest.writeString(scope);
        dest.writeString(refreshToken);
    }

}
