package com.technovation.technovation20;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;


/**
 * Created by Anshuman-HP on 01-03-2018.
 */

public class SponsorAdapter extends RecyclerView.Adapter<sponsorHolder> {
    @Override
    public sponsorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.sponsors_card,parent,false);
        return new sponsorHolder(v);
    }

    @Override
    public void onBindViewHolder(sponsorHolder holder, int position) {
        switch (position){
            case 0:
                Glide.with(holder.itemView.getContext())
                        .load(R.mipmap.jeet)
                        .into(holder.sponsorLogo);
                break;
            case 1:
                Glide.with(holder.itemView.getContext())
                        .load(R.mipmap.jt)
                        .into(holder.sponsorLogo);
                break;
            case 2:
                Glide.with(holder.itemView.getContext())
                        .load(R.mipmap.lit)
                        .into(holder.sponsorLogo);
                break;
            case 3:
                Glide.with(holder.itemView.getContext())
                        .load(R.mipmap.enlve)
                        .into(holder.sponsorLogo);
                break;
            case 4:
                Glide.with(holder.itemView.getContext())
                        .load(R.mipmap.ss)
                        .into(holder.sponsorLogo);
                break;
            case 5:
                Glide.with(holder.itemView.getContext())
                        .load(R.mipmap.nice)
                        .into(holder.sponsorLogo);
                break;

        }


    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
