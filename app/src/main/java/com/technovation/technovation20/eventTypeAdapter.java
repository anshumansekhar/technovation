package com.technovation.technovation20;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Anshuman-HP on 19-02-2018.
 */

public class eventTypeAdapter extends RecyclerView.Adapter<eventTypeHolder> {
    ArrayList<String> eventTypes;
    Activity mainActivity;

    public eventTypeAdapter(Activity activity) {
        mainActivity=activity;
        eventTypes=new ArrayList<>();
        eventTypes.add(0,"Technical");
        eventTypes.add(1,"Fun");
        eventTypes.add(2,"Cultural");
        eventTypes.add(3,"I am Sherlock");
        eventTypes.add(4,"Event Schedule");
        eventTypes.add(5,"Visit Us");
    }

    @Override
    public eventTypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_event_card_layout,parent,false);
        return new eventTypeHolder(v);
    }

    @Override
    public void onBindViewHolder(eventTypeHolder holder, int position) {
        holder.eventTypeName.setText(eventTypes.get(position));
        switch (position){
            case 0:
                holder.eventTypeImageIcon.setImageResource(R.drawable.ic_005_coding);
                holder.eventTypeImageIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(mainActivity,EventsInCategory.class);
                    i.putExtra("EventType","Technical");
                    mainActivity.startActivity(i);

                }
            });
            break;
            case 1:                    holder.eventTypeImageIcon.setImageResource(R.drawable.ic_001_darth_vader);

                holder.eventTypeImageIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(mainActivity,EventsInCategory.class);
                    i.putExtra("EventType","Fun");
                    mainActivity.startActivity(i);

                }
            });
                break;
            case 2:                    holder.eventTypeImageIcon.setImageResource(R.drawable.ic_006_dance);

                holder.eventTypeImageIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(mainActivity,EventsInCategory.class);
                    i.putExtra("EventType","Cultural");
                    mainActivity.startActivity(i);

                }
            });
                break;
            case 3:                    holder.eventTypeImageIcon.setImageResource(R.drawable.ic_007_spy);

                holder.eventTypeImageIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(mainActivity,QRScannerActivity.class);
                    mainActivity.startActivity(i);

                }
            });
                break;
            case 4:
                holder.eventTypeImageIcon.setImageResource(R.drawable.ic_003_calendar);

                holder.eventTypeImageIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(mainActivity,EventSchedule.class);
                    mainActivity.startActivity(i);

                }
            });
                break;

            case 5: holder.eventTypeImageIcon.setImageResource(R.drawable.ic_002_visitor);
                holder.eventTypeImageIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(mainActivity,VisitUSActivity.class);
                    mainActivity.startActivity(i);

                }
            });
                break;

        }


    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
