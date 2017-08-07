package com.mismascotas.pe.restapi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.mismascotas.pe.model.User;
import com.mismascotas.pe.restapi.ConstantesRestApi;
import com.mismascotas.pe.restapi.EndpointsApi;
import com.mismascotas.pe.restapi.deserializador.MascotaDeserializador;
import com.mismascotas.pe.restapi.deserializador.UserDeserializador;
import com.mismascotas.pe.restapi.deserializador.UserResponseDeserializador;
import com.mismascotas.pe.restapi.model.MascotaResponse;
import com.mismascotas.pe.restapi.model.UserResponse;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by oscarqpe on 23/07/17.
 */

public class RestApiAdapter {
    public EndpointsApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }
    public EndpointsApi conexionHeroku() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_HEROKU)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(EndpointsApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());
        return gsonBuilder.create();
    }
    public Gson userDeserializador() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(User.class, new UserDeserializador());
        return gsonBuilder.create();
    }
    public Gson userSearchDeserializador() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UserResponse.class, new UserResponseDeserializador());
        return gsonBuilder.create();
    }
}
