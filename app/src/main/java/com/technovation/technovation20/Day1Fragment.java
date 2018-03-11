package com.technovation.technovation20;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Day1Fragment extends Fragment {

    RecyclerView eventRecylerSchedule;
    ArrayList<EventScheduleClass> day1Events;
    Day1RecyclerAdapter adapter;


    public Day1Fragment() {

        day1Events=new ArrayList<>();
        adapter=new Day1RecyclerAdapter();

        loadDay1Events();
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_day1, container, false);
        eventRecylerSchedule=(RecyclerView)v.findViewById(R.id.eventScheduleRecyler);
        eventRecylerSchedule.setLayoutManager(new LinearLayoutManager(container.getContext()));
        eventRecylerSchedule.setAdapter(adapter);
        return v;
    }
    public void loadDay1Events(){
        FirebaseDatabase.getInstance()
                .getReference()
                .child("eventSchedule")
                .child("day1")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        day1Events.add(dataSnapshot.getValue(EventScheduleClass.class));
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        day1Events.add(dataSnapshot.getValue(EventScheduleClass.class));
                        adapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
    public class Day1RecyclerAdapter extends RecyclerView.Adapter<EventRecyclerHolder>{

        @Override
        public EventRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.event_schedule_card,parent,false);
            return new EventRecyclerHolder(v);
        }

        @Override
        public void onBindViewHolder(EventRecyclerHolder holder,final int position) {
            holder.eventLocation.setText(day1Events.get(position).getEventVenue());
            holder.eventTiming.setText(day1Events.get(position).getEventTiming());
            holder.eventName.setText(day1Events.get(position).getEventName());
            Glide.with(holder.view.getContext())
                    .load(day1Events.get(position).getEventImage())
                    .into(holder.eventImage);
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(getActivity(),EventDetailActivity.class);
                    if(day1Events.get(position).eventType.contains("gathering")){

                    }else {
                        i.putExtra("EventType", day1Events.get(position).eventType);
                        i.putExtra("EventName", day1Events.get(position).eventName);
                        startActivity(i);
                    }
                }
            });


        }

        @Override
        public int getItemCount() {
            return day1Events.size();
        }
    }

}
