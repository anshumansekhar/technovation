package com.technovation.technovation20;

import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Anshuman-HP on 17-02-2018.
 */

public class InstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("token", "Refreshed token: " + refreshedToken);

        sendRegistrationToServer(refreshedToken);
    }

    public void sendRegistrationToServer(String token){
        FirebaseDatabase.getInstance()
                .getReference()
                .child("notificationTokens")
                .child(token).setValue(true);

    }
}
