package com.technovation.technovation20;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class NotificationsActivity extends AppCompatActivity {

    RecyclerView notificationsRecycler;
    LinearLayoutManager linearLayoutManager;
    NotificationsActivity.NotificationsRecyclerAdapter notificationsRecyclerAdapter;
    LinearLayout adminLayout;
    EditText messageEditText;
    FloatingActionButton  sendMessage;
    ArrayList<NotificationMessage> notificationMessages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        adminLayout=(LinearLayout)findViewById(R.id.adminLayout);
        adminLayout.setVisibility(View.INVISIBLE);

        messageEditText=(EditText)findViewById(R.id.message);
        sendMessage=(FloatingActionButton)findViewById(R.id.sendMEssage);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!messageEditText.getText().toString().isEmpty()){
                    sendMessage(messageEditText.getText().toString().trim());
                }else{
                    Toast.makeText(NotificationsActivity.this,"Empty Message",Toast.LENGTH_SHORT).show();
                }

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        linearLayoutManager=new LinearLayoutManager(this);
        notificationsRecyclerAdapter=new NotificationsRecyclerAdapter();

        notificationsRecycler=(RecyclerView)findViewById(R.id.notifications_recycler);
        notificationsRecycler.setLayoutManager(linearLayoutManager);
        notificationsRecycler.setAdapter(notificationsRecyclerAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.notifications_activity_menu,menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.adminLogin:
                View v= LayoutInflater.from(NotificationsActivity.this).inflate(R.layout.logindialog,null,false);
                final EditText passwordField=(EditText)v.findViewById(R.id.password);
                AlertDialog.Builder builder=new AlertDialog.Builder(NotificationsActivity.this)
                        .setTitle("Login")
                        .setView(v)
                        .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final String password=passwordField.getText().toString().trim();
                                Log.e("zvcb",password);
                                if(password.contains("AdminLogin")){
                                    adminLayout.setVisibility(View.VISIBLE);
                                }else{
                                    Toast.makeText(NotificationsActivity.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                AlertDialog dialog=builder.create();
                dialog.show();
                break;
            case android.R.id.home:
                startActivity(new Intent(NotificationsActivity.this,MainActivity.class));
                break;

        }
        return true;
    }

    public void sendMessage(String message){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("dd:MMM:yyyy-HH:mm a");
        date.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));

        String localTime = date.format(currentLocalTime);
        NotificationMessage notificationMessage=new NotificationMessage(message,"Technovation",localTime);
        FirebaseDatabase.getInstance()
                .getReference()
                .child("notification_messages")
                .child(localTime)
                .setValue(notificationMessage)
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                notificationsRecyclerAdapter.notifyDataSetChanged();
            }
        });
        FirebaseDatabase.getInstance()
                .getReference()
                .child("notificationToSend")
                .child("body")
                .setValue(message);
    }
    public class NotificationsRecyclerAdapter extends RecyclerView.Adapter<NotificationsRecyclerViewHolder> {


        public NotificationsRecyclerAdapter() {
            notificationMessages=new ArrayList<>();
            FirebaseDatabase.getInstance()
                    .getReference()
                    .child("notification_messages")
                    .addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            NotificationMessage message=dataSnapshot.getValue(NotificationMessage.class);
                            notificationMessages.add(message);
                            notificationsRecyclerAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {


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
        public NotificationsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_card,parent,false);
            return new NotificationsRecyclerViewHolder(v);
        }

        @Override
        public void onBindViewHolder(NotificationsRecyclerViewHolder holder, int position) {
            holder.message.setText(notificationMessages.get(position).getMessage());
            holder.postedTime.setText(notificationMessages.get(position).getTimeOfPost());

        }



        @Override
        public int getItemCount() {
            return notificationMessages.size();
        }
    }



}
