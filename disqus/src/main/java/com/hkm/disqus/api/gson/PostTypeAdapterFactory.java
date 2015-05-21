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
package com.hkm.disqus.api.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.hkm.disqus.api.model.forums.Forum;
import com.hkm.disqus.api.model.posts.Post;

import java.io.IOException;


/**
 * A {@link TypeAdapterFactory} to handle different {@link Post} responses
 */
public class PostTypeAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> type) {
        // Return null if not a post object
        if (!type.getType().equals(Post.class)) {
            return null;
        }

        // Get delegate
        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);

        // Return adapter
        return new TypeAdapter<T>() {

            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                JsonElement jsonTree = gson.fromJson(in, JsonElement.class);
                JsonElement forum = jsonTree.getAsJsonObject().get("forum");
                JsonElement thread = jsonTree.getAsJsonObject().get("thread");

                // Process the post with the delegate
                T post = delegate.fromJsonTree(jsonTree);

                // Process forum and thread if needed
                if (forum.isJsonObject()) {
                    ((Post) post).forum = gson.fromJson(forum, Forum.class);
                }
                if (thread.isJsonObject()) {
                    ((Post) post).thread = gson.fromJson(thread, Thread.class);
                }

                // Return post
                return post;
            }

        };

    }

}
