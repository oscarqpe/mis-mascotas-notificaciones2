package com.mismascotas.pe.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by oscarqpe on 06/08/17.
 */

public class NotificationIDTokenService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh () {
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);
    }
    public void enviarTokenRegistro (String token) {
        Log.d("Token: ", token);
    }
}
