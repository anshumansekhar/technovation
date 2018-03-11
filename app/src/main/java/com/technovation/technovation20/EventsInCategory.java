package com.technovation.technovation20;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.daprlabs.aaron.swipedeck.CardContainer;
import com.daprlabs.aaron.swipedeck.SwipeDeck;

public class EventsInCategory extends AppCompatActivity {

    SwipeDeck cardStack;
    SwipeDeckAdpater adpater;


    Toolbar toolbar;

    String eventType="";


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
        setContentView(R.layout.activity_events_in_category);
        setBackground();

        Intent i=getIntent();
        eventType=i.getStringExtra("EventType");
        cardStack=(SwipeDeck)findViewById(R.id.swipe_deck);

        toolbar=(Toolbar)findViewById(R.id.toolbarInEventsCategory);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(eventType);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adpater=new SwipeDeckAdpater(EventsInCategory.this,eventType);
        if(cardStack != null){
            cardStack.setAdapter(adpater);

        }



        cardStack.setCallback(new SwipeDeck.SwipeDeckCallback() {
            @Override
            public void cardSwipedLeft(long stableId) {
                Log.i("MainActivity", "card was swiped left, position in adapter: ");
                adpater.events.add(adpater.events.get(Integer.parseInt(""+stableId)));
                adpater.notifyDataSetChanged();


            }

            @Override
            public void cardSwipedRight(long stableId) {
                Log.i("MainActivity", "card was swiped right, position in adapter: ");
                adpater.events.add(adpater.events.get(Integer.parseInt(""+stableId)));
                adpater.notifyDataSetChanged();

            }
        });



    }
    public void setBackground(){
        Log.e("dgs","SetBackground");
        RequestOptions options=new RequestOptions();
        options.centerCrop();
        Glide.with(this)
                .load(R.mipmap.splash_image)
                .apply(options)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        getWindow().getDecorView().setBackground(resource);
                        getWindow().getDecorView().getBackground().mutate().setAlpha(60);
                    }
                });

    }
}
