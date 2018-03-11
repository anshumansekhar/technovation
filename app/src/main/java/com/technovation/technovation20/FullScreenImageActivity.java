package com.technovation.technovation20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FullScreenImageActivity extends AppCompatActivity {
    String imageURL;
    ImageView fullImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fullImage=(ImageView)findViewById(R.id.fullImage);

        imageURL=getIntent().getStringExtra("imageURL");

        Glide.with(FullScreenImageActivity.this)
                .load(imageURL)
                .into(fullImage);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return true;
    }
}
