package com.technovation.technovation20;

import android.app.Application;
import android.content.res.Configuration;
import android.support.multidex.MultiDexApplication;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Anshuman-HP on 10-03-2018.
 */

public class TechnovationApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
