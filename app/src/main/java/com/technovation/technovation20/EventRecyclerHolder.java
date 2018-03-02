package com.technovation.technovation20;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Anshuman-HP on 23-02-2018.
 */

public class EventRecyclerHolder extends RecyclerView.ViewHolder {
    CardView view;
    ImageView eventImage;
    TextView eventName;
    TextView eventTiming;
    TextView eventLocation;

    public EventRecyclerHolder(View itemView) {
        super(itemView);
        view=(CardView)itemView.findViewById(R.id.eventScheduleCard);
        eventImage=(ImageView)itemView.findViewById(R.id.eventImage);
        eventName=(TextView)itemView.findViewById(R.id.EventName);
        eventTiming=(TextView)itemView.findViewById(R.id.EventTime);
        eventLocation=(TextView)itemView.findViewById(R.id.EventLocation);

    }
}