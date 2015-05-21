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


import com.hkm.disqus.api.model.Response;
import com.hkm.disqus.api.model.users.User;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Users resource
 *
 * @see <a href="https://disqus.com/api/docs/users/">Documentation</a>
 */
public interface Users {

    @POST("/users/checkUsername.json")
    public Response<String> checkUsername(@Query("username") String username);

    @GET("/users/details.json")
    public Response<User> details(@Query("user") Long user);

}
