package com.technovation.technovation20;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Anshuman-HP on 01-03-2018.
 */

public class sponsorHolder extends RecyclerView.ViewHolder {
    ImageView sponsorLogo;
    public sponsorHolder(View itemView) {
        super(itemView);
        sponsorLogo=(ImageView)itemView.findViewById(R.id.sponsorLogo);
    }
}
