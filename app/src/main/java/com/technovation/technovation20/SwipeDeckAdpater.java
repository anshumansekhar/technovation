package com.technovation.technovation20;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Anshuman-HP on 23-02-2018.
 */

public class SwipeDeckAdpater extends BaseAdapter {
    Activity activity;
    ArrayList<EventDetails> events;

    public SwipeDeckAdpater(Activity activity,String type) {
        this.activity = activity;

        if(type.contains("Technical")){
            events=new ArrayList<>(MainActivity.technicalEvents.values());
        }else if(type.contains("Fun")){
            events=new ArrayList<>(MainActivity.funEvents.values());
        }else if(type.contains("Cultural")){
            events=new ArrayList<>(MainActivity.culturalEvents.values());
        }
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int i) {
        return "Test";
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_swipe_card, viewGroup, false);
        }
        ImageView eventImage;
        TextView eventName;
        eventImage=(ImageView)v.findViewById(R.id.eventImage);
        eventName=(TextView)v.findViewById(R.id.eventName);

        eventName.setText(events.get(i).eventName);
        Glide.with(viewGroup.getContext())
                .load(events.get(i).eventImage)
                .into(eventImage);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Layer type: ", Integer.toString(v.getLayerType()));
                Log.i("Hardware Accel type:", Integer.toString(View.LAYER_TYPE_HARDWARE));
                Intent intent=new Intent(activity,EventDetailActivity.class);
                intent.putExtra("EventType",events.get(i).eventType);
                intent.putExtra("EventName",events.get(i).eventName);
                Log.e("gsfn",events.get(i).eventType);
                activity.startActivity(intent);
            }
        });
        return v;
    }
}
