package com.mismascotas.pe.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.mismascotas.pe.model.ConstructorUser;
import com.mismascotas.pe.model.User;
import com.mismascotas.pe.restapi.ConstantesRestApi;
import com.mismascotas.pe.restapi.EndpointsApi;
import com.mismascotas.pe.restapi.adapter.RestApiAdapter;
import com.mismascotas.pe.restapi.model.DeviceResponse;
import com.mismascotas.pe.restapi.model.LikeResponse;
import com.mismascotas.pe.restapi.model.MediaLikesResponse;
import com.mismascotas.pe.restapi.model.MediaResponse;
import com.mismascotas.pe.restapi.model.StatusResponse;
import com.mismascotas.pe.restapi.model.UserResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import com.mismascotas.pe.model.ConstructorMascota;
import com.mismascotas.pe.model.Mascota;
import com.mismascotas.pe.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oscarqpe on 22/07/17.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MascotaViewHolder> {
    ArrayList<Mascota> mascotas = new ArrayList<>();
    Activity activity;
    public RVAdapter(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }
    @Override
    public int getItemCount() {
        if (mascotas == null)
            return 0;
        return this.mascotas.size();
    }
    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private ImageView imagen;
        private TextView rating;
        private ImageButton btn_rating;

        MascotaViewHolder (View v) {
            super(v);
            cv = (CardView) v.findViewById(R.id.cv_mascotas);
            imagen = (ImageView) v.findViewById(R.id.img_foto);
            rating = (TextView) v.findViewById(R.id.tv_raiting);
            btn_rating = (ImageButton) v.findViewById(R.id.btn_raiting_total);
        }
    }
    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        System.out.print("Create view holder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        MascotaViewHolder pvh = new MascotaViewHolder(v);
        return pvh;
    }
    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {
        // Asociar cada elemento con cada view
        final Mascota mascota = mascotas.get(position);
        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.mascotas10)
                .into(mascotaViewHolder.imagen);
        mascotaViewHolder.rating.setText(Integer.toString(mascota.getLikes()));
        mascotaViewHolder.btn_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                RestApiAdapter restApiAdapter = new RestApiAdapter();
                Gson gsonMediaRecent = restApiAdapter.likeMediaDeserializador();
                EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
                Call<LikeResponse> mediaCall = endpointsApi.likeMedia(
                        mascota.getMediaId(), ConstantesRestApi.ACCESS_TOKEN);
                Log.d("MEDIA ID: ", mascota.getMediaId());
                mediaCall.enqueue(new Callback<LikeResponse>() {
                    @Override
                    public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                        LikeResponse likeResponse = response.body();
                        if (likeResponse != null) {
                            Log.d("LIKE: ", likeResponse.getStatus());
                            RestApiAdapter restApiAdapter = new RestApiAdapter();
                            Gson gsonMediaRecent = restApiAdapter.mediaDeserializador();
                            EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
                            Call<MediaResponse> mediaLikes = endpointsApi.getMedia(
                                    mascota.getMediaId());
                            mediaLikes.enqueue(new Callback<MediaResponse>() {
                                @Override
                                public void onResponse(Call<MediaResponse> call, Response<MediaResponse> response) {
                                    MediaResponse mediaResponse = response.body();
                                    String currentLikes = mascotaViewHolder.rating.getText().toString();
                                    Log.d("LIKES MEDIA: ", String.valueOf(mediaResponse.getLikes()));
                                    //if (Integer.valueOf(currentLikes) != mediaResponse.getLikes()) {
                                        mascotaViewHolder.rating.setText((String.valueOf(mediaResponse.getLikes())));
                                        String instagram = "";
                                        ConstructorUser constructorUser = new ConstructorUser(view.getContext());
                                        User user = constructorUser.getCurrentUsuario();
                                        if (user == null) {
                                            System.out.println("No Current User");
                                            instagram = constructorUser.insertUsuario("perritotobi");
                                        } else {
                                            System.out.println("Current User");
                                            instagram = user.getUsuario();
                                        }
                                        String token = FirebaseInstanceId.getInstance().getToken();
                                        Log.d("TOKEN: ", token);
                                        RestApiAdapter restApiAdapter = new RestApiAdapter();
                                        EndpointsApi endpointsApi = restApiAdapter.conexionHeroku();

                                        final Call<StatusResponse> statusCall = endpointsApi.setLikes(
                                                "-KqxpCmr--iKkGucKDqW",
                                                mascota.getMediaId(), instagram, token);

                                        statusCall.enqueue(new Callback<StatusResponse>() {
                                            @Override
                                            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                                                StatusResponse statusResponse = response.body();
                                                Log.d("Status: ", statusResponse.getStatus());
                                            }

                                            @Override
                                            public void onFailure(Call<StatusResponse> call, Throwable t) {

                                            }
                                        });
                                    //}
                                }

                                @Override
                                public void onFailure(Call<MediaResponse> call, Throwable t) {
                                    Log.d("LOG: ", "Something is wrong.");
                                    Log.d("LOG", t.getMessage());
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<LikeResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
}
