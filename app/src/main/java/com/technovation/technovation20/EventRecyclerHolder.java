package com.technovation.technovation20;

import android.support.constraint.ConstraintLayout;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Anshuman-HP on 23-02-2018.
 */

public class EventRecyclerHolder extends RecyclerView.ViewHolder {
    ConstraintLayout view;
    ImageView eventImage;
    TextView eventName;
    TextView eventTiming;
    TextView eventLocation;

    public EventRecyclerHolder(View itemView) {
        super(itemView);
        view=(ConstraintLayout) itemView.findViewById(R.id.eventScheduleCard);
        eventImage=(ImageView)itemView.findViewById(R.id.eventImage);
        eventName=(TextView)itemView.findViewById(R.id.EventName);
        eventTiming=(TextView)itemView.findViewById(R.id.EventTime);
        eventLocation=(TextView)itemView.findViewById(R.id.EventLocation);

        eventLocation.setCompoundDrawablesWithIntrinsicBounds(AppCompatResources.getDrawable(itemView.getContext(),R.drawable.ic_location_on_black_24dp), null, null, null);
        eventTiming.setCompoundDrawablesWithIntrinsicBounds(AppCompatResources.getDrawable(itemView.getContext(),R.drawable.ic_access_time_black_24dp), null, null, null);


    }
}
