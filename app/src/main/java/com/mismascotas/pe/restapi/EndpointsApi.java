package com.mismascotas.pe.restapi;

import com.mismascotas.pe.model.User;
import com.mismascotas.pe.restapi.model.DeviceResponse;
import com.mismascotas.pe.restapi.model.MascotaResponse;
import com.mismascotas.pe.restapi.model.UserResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by oscarqpe on 23/07/17.
 */

public interface EndpointsApi {
    //@GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    @GET("users/{userid}/media/recent/?access_token=" + ConstantesRestApi.ACCESS_TOKEN)
    Call<MascotaResponse> getRecentMedia(@Path("userid") String userid);

    @GET(ConstantesRestApi.KEY_GET_USER_INFO + "{userid}/" + ConstantesRestApi.KEY_ACCESS_TOKEN
        + ConstantesRestApi.ACCESS_TOKEN)
    Call<User> getUserInfo(@Path("userid") String userid);

    @GET(ConstantesRestApi.KEY_SEARCH_USER)
    Call<UserResponse> getSearchUser(@Query("q") String query, @Query("access_token") String access_token);

    @FormUrlEncoded
    @POST(ConstantesRestApi.ROOT_HEROKU + ConstantesRestApi.DEVICE_REGISTER)
    Call<DeviceResponse> registerDevice(@Field("id_dispositivo") String id_dispositivo,
                                        @Field("id_usuario_instagram") String id_usuario_instagram);
}
