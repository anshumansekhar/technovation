package com.technovation.technovation20;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

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
        setContentView(R.layout.activity_events_in_category);

        Intent i=getIntent();
        eventType=i.getStringExtra("EventType");
        cardStack=(SwipeDeck)findViewById(R.id.swipe_deck);

        toolbar=(Toolbar)findViewById(R.id.toolbarInEventsCategory);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(eventType);
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
}
