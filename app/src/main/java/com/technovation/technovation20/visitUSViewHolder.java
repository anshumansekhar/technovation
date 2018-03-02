package com.technovation.technovation20;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Anshuman-HP on 28-02-2018.
 */

public class visitUSViewHolder extends RecyclerView.ViewHolder {
    ConstraintLayout layout;
    CircleImageView logoImage;
    public visitUSViewHolder(View itemView) {
        super(itemView);
        layout=(ConstraintLayout)itemView.findViewById(R.id.layout);
        logoImage=(CircleImageView)itemView.findViewById(R.id.logo);

    }
}
