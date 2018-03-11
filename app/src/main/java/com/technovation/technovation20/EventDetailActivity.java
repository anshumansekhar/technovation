package com.technovation.technovation20;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;


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
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }else{
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
        setContentView(R.layout.activity_event_detail);
        setBackground();


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
        toolbar=(Toolbar)findViewById(R.id.toolbarVisit);
        setSupportActionBar(toolbar);

        image=(ImageView)findViewById(R.id.image);
        if(eventDetail!=null){
            collapsingToolbar.setTitle(eventDetail.eventName);
            Glide.with(EventDetailActivity.this)
                    .load(eventDetail.getEventImage())
                    .into(image);
            eventVenue.setText(eventDetail.getEventVenue());
            eventDescp.setText(Html.fromHtml(eventDetail.getEventDetails()));
            eventTiming.setText(eventDetail.getEventTiming());
            coordinatorName.setText(eventDetail.getEventCoordinatorName());
            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadCallDialog(eventDetail.getEventCoordinatorPhone(),eventDetail.eventCoordinatorName);
                }
            });

        }else{
            //TODO do something

        }
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(EventDetailActivity.this,FullScreenImageActivity.class);
                i.putExtra("imageURL",""+eventDetail.getEventImage());
                startActivity(i);
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    public  void loadCallDialog(String phoneString,String names){
        final String[] phoneNumbers=phoneString.split(",");
        final String[] coordinatorNames=names.split("\n");
        AlertDialog.Builder builder=new AlertDialog.Builder(EventDetailActivity.this);
        builder.setTitle("Choose ");
        builder.setSingleChoiceItems(coordinatorNames, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phoneNumbers[i]));
                startActivity(intent);
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
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
