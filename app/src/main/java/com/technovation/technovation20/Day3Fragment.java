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
public class Day3Fragment extends Fragment {


    RecyclerView eventRecylerSchedule;
    ArrayList<EventScheduleClass> day3Events;
    Day3RecyclerAdapter adapter;


    public Day3Fragment() {
        day3Events=new ArrayList<>();
        adapter=new Day3RecyclerAdapter();
        loadDay3Events();
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
    public void loadDay3Events(){
        FirebaseDatabase.getInstance()
                .getReference()
                .child("eventSchedule")
                .child("day3")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        day3Events.add(dataSnapshot.getValue(EventScheduleClass.class));
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        day3Events.add(dataSnapshot.getValue(EventScheduleClass.class));
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
    public class Day3RecyclerAdapter extends RecyclerView.Adapter<EventRecyclerHolder>{

        @Override
        public EventRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.event_schedule_card,parent,false);
            return new EventRecyclerHolder(v);
        }

        @Override
        public void onBindViewHolder(EventRecyclerHolder holder,final int position) {
            holder.eventLocation.setText(day3Events.get(position).getEventVenue());
            holder.eventTiming.setText(day3Events.get(position).getEventTiming());
            holder.eventName.setText(day3Events.get(position).getEventName());
            Glide.with(holder.view.getContext())
                    .load(day3Events.get(position).getEventImage())
                    .into(holder.eventImage);
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(getActivity(),EventDetailActivity.class);
                    i.putExtra("EventType",day3Events.get(position).eventType);
                    i.putExtra("EventName",day3Events.get(position).eventName);
                    startActivity(i);
                }
            });


        }

        @Override
        public int getItemCount() {
            return day3Events.size();
        }
    }

}
