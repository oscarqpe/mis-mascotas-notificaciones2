package com.mismascotas.pe.restapi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mismascotas.pe.model.User;
import com.mismascotas.pe.restapi.JsonKeys;
import com.mismascotas.pe.restapi.model.LikeResponse;
import com.mismascotas.pe.restapi.model.MascotaResponse;

import java.lang.reflect.Type;

/**
 * Created by oscarqpe on 06/08/17.
 */

public class MediaLikeDeserializador implements JsonDeserializer<LikeResponse> {
    @Override
    public LikeResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        LikeResponse like = gson.fromJson(json, LikeResponse.class);
        JsonObject dataResponseData = json.getAsJsonObject().getAsJsonObject("meta");

        String code               = dataResponseData.get("code").getAsString();
        like.setStatus(code);
        return like;
    }
}
