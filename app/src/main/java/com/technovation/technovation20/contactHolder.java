package com.technovation.technovation20;

import android.support.constraint.ConstraintLayout;
import android.support.v7.content.res.AppCompatResources;
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
    ConstraintLayout contactCard;
    public contactHolder(View itemView) {
        super(itemView);
        contactName=(TextView)itemView.findViewById(R.id.contactName);
        contactDesignation=(TextView)itemView.findViewById(R.id.contactDesignation);
        contactNumber=(TextView)itemView.findViewById(R.id.phoneNumber);
        contactCard=(ConstraintLayout)itemView.findViewById(R.id.contactCard);
        contactNumber.setCompoundDrawablesWithIntrinsicBounds(AppCompatResources.getDrawable(itemView.getContext(),R.drawable.ic_call_black_24dp), null, null, null);

    }
}
