package com.technovation.technovation20;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class EventDetailActivity extends AppCompatActivity {
    ImageView image;
    Toolbar toolbar;
    FloatingActionButton callButton;
    TextView coordinatorName;
    TextView eventVenue;
    TextView eventDescp;
    TextView eventTiming;
    EventDetails eventDetail;
    String eventType="";
    String eventName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        callButton=(FloatingActionButton)findViewById(R.id.callButton);
        coordinatorName=(TextView)findViewById(R.id.eventCoordinatornamedetail);
        eventTiming=(TextView)findViewById(R.id.eventTimingDetail);
        eventDescp=(TextView)findViewById(R.id.eventDescriptionDetail);
        eventVenue=(TextView)findViewById(R.id.eventVenueDetail);
        Intent intent=getIntent();
        eventType=intent.getStringExtra("EventType");
        eventName=intent.getStringExtra("EventName");

        if(eventType.contains("technical")){
            eventDetail=MainActivity.technicalEvents.get(eventName);
        }else if(eventType.contains("fun")){
            eventDetail=MainActivity.funEvents.get(eventName);
        }else if(eventType.contains("cultural")){
            eventDetail=MainActivity.culturalEvents.get(eventName);
        }


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(eventDetail.eventName);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        image=(ImageView)findViewById(R.id.image);
        if(eventDetail!=null){
            Glide.with(EventDetailActivity.this)
                    .load(eventDetail.eventImage)
                    .into(image);
            eventVenue.setText(eventDetail.getEventVenue());
            eventDescp.setText(eventDetail.getEventDetails());
            eventTiming.setText(eventDetail.getEventTiming());
            coordinatorName.setText(eventDetail.getEventCoordinatorName());
            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+eventDetail.eventCoordinatorPhone));
                    startActivity(intent);
                }
            });

        }else{
            //TODO do something

        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
