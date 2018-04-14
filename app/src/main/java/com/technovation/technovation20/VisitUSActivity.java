package com.technovation.technovation20;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

public class VisitUSActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView website;
    RecyclerView contactRecycler;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_visit_us);
        setBackground();

        toolbar = (Toolbar) findViewById(R.id.toolbarVisit);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        website=(TextView)findViewById(R.id.websiteText);

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.technovation2k18.com/");
                Intent i=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        });

        contactRecycler=(RecyclerView)findViewById(R.id.contactRecyler);
        contactRecycler.setLayoutManager(new LinearLayoutManager(VisitUSActivity.this));
        contactRecycler.setAdapter(new contactAdapter());

        recyclerView=(RecyclerView)findViewById(R.id.visitUSRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(new visitUSRecyclerAdapter());


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
