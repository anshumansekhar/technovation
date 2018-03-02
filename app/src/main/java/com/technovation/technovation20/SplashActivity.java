package com.technovation.technovation20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import javax.microedition.khronos.opengles.GL;


public class SplashActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView=(ImageView)findViewById(R.id.imageView);
        Glide.with(this)
                .load(R.mipmap.ic_launcher)
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
