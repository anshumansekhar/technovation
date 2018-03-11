package com.technovation.technovation20;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;

import javax.microedition.khronos.opengles.GL;


public class SplashActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }else{
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
        setContentView(R.layout.activity_splash);

        if (getIntent().getExtras() != null) {

                for (String key : getIntent().getExtras().keySet()) {
                    if(key.contains("body")){
                        startActivity(new Intent(SplashActivity.this,NotificationsActivity.class));
                    }
                }

        }
        imageView=(ImageView)findViewById(R.id.imageView);
        Glide.with(this)
                .load(R.mipmap.splash_image)
                .into(imageView);
        Thread background=new Thread(){
            public void run(){
                try{
                    sleep(3000);
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }catch (Exception e){

                }
            }
        };
        background.start();
    }
}
