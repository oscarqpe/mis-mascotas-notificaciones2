package com.mismascotas.pe.restapi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mismascotas.pe.model.User;
import com.mismascotas.pe.restapi.JsonKeys;
import com.mismascotas.pe.restapi.model.MediaResponse;

import java.lang.reflect.Type;

/**
 * Created by oscarqpe on 07/08/17.
 */

public class MediaDeserializador implements JsonDeserializer<MediaResponse> {
    @Override
    public MediaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MediaResponse media = gson.fromJson(json, MediaResponse.class);
        JsonObject dataResponseData = json.getAsJsonObject().getAsJsonObject(JsonKeys.MEDIA_RESPONSE_ARRAY);

        String id           = dataResponseData.get("id").getAsString();
        JsonObject likes    = dataResponseData.get("likes").getAsJsonObject();
        int total_likes     = Integer.valueOf(likes.get("count").getAsString());
        media.setId(id);
        media.setLikes(total_likes);
        return media;
    }
}
