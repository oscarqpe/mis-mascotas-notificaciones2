package com.mismascotas.pe.restapi.deserializador;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mismascotas.pe.restapi.model.LikeResponse;
import com.mismascotas.pe.restapi.model.MediaLikesResponse;

import java.lang.reflect.Type;

/**
 * Created by oscarqpe on 06/08/17.
 */

public class MediaLikesDeserializador implements JsonDeserializer<MediaLikesResponse> {
    @Override
    public MediaLikesResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MediaLikesResponse like = gson.fromJson(json, MediaLikesResponse.class);
        JsonArray dataResponseData = json.getAsJsonObject().getAsJsonArray("data");
        Log.d("LIKES: ", String.valueOf(dataResponseData.size()));
        int code               = dataResponseData.size();
        like.setTotal(code);
        return like;
    }
}
