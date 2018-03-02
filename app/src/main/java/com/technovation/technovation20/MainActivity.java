package com.technovation.technovation20;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ConstraintLayout mainLayout;
    RecyclerView mainNavigationList;
    eventTypeAdapter eventTypeAdapter;
    ViewPager eventsImagePager;

    int currentPage,NUM_PAGES;

    Handler handler=new Handler();

    public static HashMap<String,EventDetails> funEvents=new HashMap<>();
    public static HashMap<String,EventDetails> culturalEvents=new HashMap<>();
    public static HashMap<String,EventDetails> technicalEvents=new HashMap<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadEventData();

        handler.removeCallbacks(r);
        handler.postDelayed(r,3000);

        Log.e("token",FirebaseInstanceId.getInstance().getToken());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        eventsImagePager=(ViewPager)findViewById(R.id.mainPager);
        eventsImagePager.setAdapter(new mainActivityPagerAdapter(this, LayoutInflater.from(this)));

        currentPage = eventsImagePager.getCurrentItem();

        NUM_PAGES = eventsImagePager.getAdapter().getCount();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.indicatorTab);
        tabLayout.setupWithViewPager(eventsImagePager, true);





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mainLayout=(ConstraintLayout)findViewById(R.id.mainLayout);
        mainNavigationList=(RecyclerView)findViewById(R.id.mainNavigationList);
        setBackground();
        setMainNavigationRecycler();


    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.notification_menu:
                startActivity(new Intent(MainActivity.this,NotificationsActivity.class));
                break;
            default:
                break;
        }
        return true;
    }

    public void setMainNavigationRecycler(){
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        mainNavigationList.setLayoutManager(gridLayoutManager);
        eventTypeAdapter=new eventTypeAdapter(MainActivity.this);
        mainNavigationList.setAdapter(eventTypeAdapter);

    }
    public void setBackground(){
        Log.e("dgs","SetBackground");
        final ImageView view=new ImageView(this);
        Glide.with(this)
                .load(R.drawable.ic_notifications_black_24dp)
                .into(new Target<Drawable>() {
                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                    }

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        mainLayout.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void getSize(@NonNull SizeReadyCallback cb) {

                    }

                    @Override
                    public void removeCallback(@NonNull SizeReadyCallback cb) {

                    }

                    @Override
                    public void setRequest(@Nullable Request request) {

                    }

                    @Nullable
                    @Override
                    public Request getRequest() {
                        return null;
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onStop() {

                    }

                    @Override
                    public void onDestroy() {

                    }
                });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.About:
                startActivity(new Intent(MainActivity.this,AboutActivity.class));
                break;
            case R.id.sponsors:
                startActivity(new Intent(MainActivity.this,SponsorsActivity.class));
                break;
            case R.id.nav_share:
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Technovation 2.0");
                    String sAux = "\nGet The Official app of Technovation 2.0 and stay Updated with live Event Notifications" +
                            " and all your Event details at The Same Place\n\n";
                    sAux = sAux + "https://play.google.com/store/apps/details?id=com.technovation.technovation20 \n\n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
                break;


        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    Runnable r=new Runnable() {
        @Override
        public void run() {
            if(currentPage==NUM_PAGES-1){
                currentPage=0;
            }else{
                currentPage=currentPage+1;
            }

            eventsImagePager.setCurrentItem(currentPage,true);
            handler.postDelayed(r,3000);
        }
    };

    public void loadEventData(){
        FirebaseDatabase.getInstance()
                .getReference()
                .child("eventDetails")
                .child("cultural")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Log.e("g",dataSnapshot.getKey());
                        culturalEvents.put(dataSnapshot.getKey(),dataSnapshot.getValue(EventDetails.class));

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        Log.e("g",dataSnapshot.getKey());
                        culturalEvents.put(dataSnapshot.getKey(),dataSnapshot.getValue(EventDetails.class));


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
        FirebaseDatabase.getInstance()
                .getReference()
                .child("eventDetails")
                .child("fun")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Log.e("g",dataSnapshot.getKey());
                        funEvents.put(dataSnapshot.getKey(),dataSnapshot.getValue(EventDetails.class));

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        Log.e("g",dataSnapshot.getKey());

                        funEvents.put(dataSnapshot.getKey(),dataSnapshot.getValue(EventDetails.class));


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
        FirebaseDatabase.getInstance()
                .getReference()
                .child("eventDetails")
                .child("technical")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Log.e("g",dataSnapshot.getKey());
                        technicalEvents.put(dataSnapshot.getKey(),dataSnapshot.getValue(EventDetails.class));

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        Log.e("g",dataSnapshot.getKey());
                        technicalEvents.put(dataSnapshot.getKey(),dataSnapshot.getValue(EventDetails.class));


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


}
