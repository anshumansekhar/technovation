package com.technovation.technovation20;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

/**
 * Created by Anshuman-HP on 28-02-2018.
 */

public class visitUSRecyclerAdapter extends RecyclerView.Adapter<visitUSViewHolder> {
    @Override
    public visitUSViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.visit_us_card,parent,false);
        return new visitUSViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final visitUSViewHolder holder, int position) {
        switch (position){
            case 0:
                Glide.with(holder.itemView)
                        .load(R.mipmap.facebooklogo_round)
                        .into(holder.logoImage);
                holder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i=newFacebookIntent(holder.itemView.getContext().getPackageManager(),"https://www.facebook.com/Technovation2.0/");
                        holder.itemView.getContext().startActivity(i);
                    }
                });
                break;
            case 1:
                Glide.with(holder.itemView)
                        .load(R.mipmap.instagram_round)
                        .into(holder.logoImage);
                holder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://www.instagram.com/technovation2.0/");
                        Intent i=new Intent(Intent.ACTION_VIEW,uri);
                        holder.itemView.getContext().startActivity(i);
                    }
                });

                break;
            case 2:
                Glide.with(holder.itemView)
                        .load(R.mipmap.twitter_round)
                        .into(holder.logoImage);
                holder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://twitter.com/0Technovation?s=17");
                        Intent i=new Intent(Intent.ACTION_VIEW,uri);
                        holder.itemView.getContext().startActivity(i);
                    }
                });

                break;
            case 3:
                Glide.with(holder.itemView)
                        .load(R.mipmap.youtube_round)
                        .into(holder.logoImage);
                holder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://www.youtube.com/channel/UCZ-bRfb43qcC246cWOoyLFA");
                        Intent i=new Intent(Intent.ACTION_VIEW,uri);
                        holder.itemView.getContext().startActivity(i);
                    }
                });

                break;
        }


    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static Intent newFacebookIntent(PackageManager pm, String url) {
        Log.e("dsgfh","facebook");
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }
}
