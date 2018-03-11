package com.technovation.technovation20;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
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
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mainLayout;
    RecyclerView mainNavigationList;
    eventTypeAdapter eventTypeAdapter;
    ViewPager eventsImagePager;

    boolean messageAvailable=false;

    Menu notificationMenu;

    int currentPage,NUM_PAGES;

    Handler handler=new Handler();

    public static HashMap<String,EventDetails> funEvents=new HashMap<>();
    public static HashMap<String,EventDetails> culturalEvents=new HashMap<>();
    public static HashMap<String,EventDetails> technicalEvents=new HashMap<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BroadcastReceiver receiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e("asgzd","onreciebe");
                    changeDrawable();
            }
        };

        registerReceiver(receiver,new IntentFilter("ChangingDrawable"));


        loadEventData();

        handler.removeCallbacks(r);
        handler.postDelayed(r,3000);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarVisit);
        toolbar.setTitleTextAppearance(this,R.style.starWarsFont);
        toolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
        toolbar.getBackground().setAlpha(0);
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

        mainLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
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
        notificationMenu=menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.notification_menu:
                messageAvailable=false;
                onPrepareOptionsMenu(notificationMenu);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.About:
                startActivity(new Intent(MainActivity.this,AboutActivity.class));
                break;
            case R.id.nav_share:
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Technovation 2.0");
                    String sAux = "Get The Official app of Technovation 2.0 and stay Updated with live Event Notifications" +
                            " and all your Event details at The Same Place\n";
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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.notification_menu);
        if(messageAvailable) {
            menuItem.setIcon(R.drawable.ic_notifications_active_black_24dp);
        }else{
            menuItem.setIcon(R.drawable.ic_notifications_black_24dp);
        }
        return super.onPrepareOptionsMenu(menu);
    }
    public  void changeDrawable(){
            messageAvailable=true;
            onPrepareOptionsMenu(notificationMenu);

    }

}
