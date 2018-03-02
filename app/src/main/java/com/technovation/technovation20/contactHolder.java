package com.technovation.technovation20;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Anshuman-HP on 01-03-2018.
 */

public class contactHolder extends RecyclerView.ViewHolder {
    TextView contactName;
    TextView contactDesignation;
    TextView contactNumber;
    CardView contactCard;
    public contactHolder(View itemView) {
        super(itemView);
        contactName=(TextView)itemView.findViewById(R.id.contactName);
        contactDesignation=(TextView)itemView.findViewById(R.id.contactDesignation);
        contactNumber=(TextView)itemView.findViewById(R.id.phoneNumber);
        contactCard=(CardView)itemView.findViewById(R.id.contactCard);
    }
}
