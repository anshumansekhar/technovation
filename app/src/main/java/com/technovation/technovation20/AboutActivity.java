package com.technovation.technovation20;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;


public class AboutActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView aboutText;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setBackground();
        image=(ImageView)findViewById(R.id.app_bar_image);
        //TODO load cover photo
        Glide.with(AboutActivity.this)
                .load(R.mipmap.igit)
                .into(image);
        aboutText=(TextView)findViewById(R.id.aboutText);
        aboutText.setText(Html.fromHtml(getString(R.string.about_details)));

        toolbar=(Toolbar)findViewById(R.id.toolbarVisit);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void setBackground(){
        Log.e("dgs","SetBackground");
        RequestOptions options=new RequestOptions();
        options.centerCrop();
        options.override(getWindow().getDecorView().getWidth(),getWindow().getDecorView().getHeight());
        Glide.with(this)
                .load(R.mipmap.splash_image_faded)
                .apply(options)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        getWindow().getDecorView().setBackground(resource);
                    }
                });
    }
}
