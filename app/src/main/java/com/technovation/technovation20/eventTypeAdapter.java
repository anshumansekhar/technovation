package com.technovation.technovation20;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    public void onBindViewHolder(final eventTypeHolder holder, int position) {
        holder.eventTypeName.setText(eventTypes.get(position));
        switch (position){
            case 0:
                holder.eventTypeImageIcon.setImageResource(R.mipmap.ic_coding_fore);
                holder.eventTypeImageIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(mainActivity,EventsInCategory.class);
                    i.putExtra("EventType","Technical");
                    mainActivity.startActivity(i);

                }
            });
            break;
            case 1:
                holder.eventTypeImageIcon.setImageResource(R.mipmap.ic_darth_vader_fore);
                holder.eventTypeImageIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(mainActivity,EventsInCategory.class);
                    i.putExtra("EventType","Fun");
                    mainActivity.startActivity(i);

                }
            });
                break;
            case 2:
                holder.eventTypeImageIcon.setImageResource(R.mipmap.ic_dance_fore);
                holder.eventTypeImageIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(mainActivity,EventsInCategory.class);
                    i.putExtra("EventType","Cultural");
                    mainActivity.startActivity(i);

                }
            });
                break;
            case 3:
                holder.eventTypeImageIcon.setImageResource(R.mipmap.ic_spy_fore);
                holder.eventTypeImageIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PackageManager pm = mainActivity.getPackageManager();
                    Intent appStartIntent = pm.getLaunchIntentForPackage("com.technovation.iamsherlock");
                    if (null != appStartIntent)
                    {
                        mainActivity.startActivity(appStartIntent);
                    }else{
                        Toast.makeText(mainActivity.getApplicationContext(),"Get the I am Sherlock app from Google Playstore",Toast.LENGTH_SHORT).show();
                    }


                }
            });
                break;
            case 4:
                holder.eventTypeImageIcon.setImageResource(R.mipmap.ic_calendar_fore);

                holder.eventTypeImageIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(mainActivity,EventSchedule.class);
                    mainActivity.startActivity(i);

                }
            });
                break;

            case 5: holder.eventTypeImageIcon.setImageResource(R.mipmap.ic_visitor_fore);
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
