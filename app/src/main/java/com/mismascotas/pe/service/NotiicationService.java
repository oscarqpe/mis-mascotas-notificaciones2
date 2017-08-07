package com.mismascotas.pe.service;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by oscarqpe on 06/08/17.
 */

public class NotiicationService extends FirebaseMessagingService {
    public static final String TAG = "FIREBASE";
    @Override
    public void onMessageReceived (RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
    }
}
