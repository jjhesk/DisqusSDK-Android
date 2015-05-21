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

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.hkm.disqus.api.model.applications.Usage;

import java.lang.reflect.Type;
import java.util.Date;



/**
 * Custom deserializer to convert Disqus usage nested arrays into a map
 */
public class ApplicationsUsageDeserializer implements JsonDeserializer<Usage> {

    @Override
    public Usage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        // JSON element should be an array
        if (json.isJsonArray()) {
            // Create usage
            Usage usage = new Usage();

            // Iterate the array
            for (JsonElement element : json.getAsJsonArray()) {
                // Each element should be an array containing a date and int
                if (element.isJsonArray() && element.getAsJsonArray().size() == 2) {
                    JsonArray jsonArray = element.getAsJsonArray();
                    Date date = context.deserialize(jsonArray.get(0), Date.class);
                    int count = jsonArray.get(1).getAsInt();
                    usage.put(date, count);
                }
            }
            return usage;
        }
        return null;
    }

}
