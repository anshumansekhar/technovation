package com.technovation.technovation20;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class VisitUSActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView website;
    RecyclerView contactRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_us);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        website=(TextView)findViewById(R.id.websiteText);

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.technovation2k18.com/");
                Intent i=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        });

        contactRecycler=(RecyclerView)findViewById(R.id.contactRecyler);
        contactRecycler.setLayoutManager(new LinearLayoutManager(VisitUSActivity.this));
        contactRecycler.setAdapter(new contactAdapter());

        recyclerView=(RecyclerView)findViewById(R.id.visitUSRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(new visitUSRecyclerAdapter());


    }
}
