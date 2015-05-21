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
import com.hkm.disqus.api.model.category.Category;
import com.hkm.disqus.api.model.forums.Forum;
import com.hkm.disqus.api.model.users.User;
import com.hkm.disqus.api.model.threads.Thread;
import java.io.IOException;


/**
 * A {@link TypeAdapterFactory} to handle different {@link Thread} responses
 */
public class ThreadTypeAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> type) {
        // Return null if not the thread type
        if (!type.getType().equals(Thread.class)) {
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
                JsonElement author = jsonTree.getAsJsonObject().get("author");
                JsonElement category = jsonTree.getAsJsonObject().get("category");

                // Process the thread with the delegate
                T thread = delegate.fromJsonTree(jsonTree);

                // Process forum and author if needed
                if (forum.isJsonObject()) {
                    ((Thread) thread).forum = gson.fromJson(forum, Forum.class);
                }
                if (author.isJsonObject()) {
                    ((Thread) thread).author = gson.fromJson(author, User.class);
                }
                if (category.isJsonObject()) {
                    ((Thread) thread).category = gson.fromJson(category, Category.class);
                }

                // Return thread
                return thread;
            }

        };

    }

}
