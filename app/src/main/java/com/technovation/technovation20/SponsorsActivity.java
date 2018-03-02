package com.technovation.technovation20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class SponsorsActivity extends AppCompatActivity {
    RecyclerView sponsorsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);
        sponsorsRecycler=(RecyclerView)findViewById(R.id.sponsorsRecylerView);
        sponsorsRecycler.setLayoutManager(new LinearLayoutManager(SponsorsActivity.this));
        sponsorsRecycler.setAdapter(new SponsorAdapter());

    }
}
