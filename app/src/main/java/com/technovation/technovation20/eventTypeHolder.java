package com.technovation.technovation20;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Anshuman-HP on 19-02-2018.
 */

public class eventTypeHolder extends RecyclerView.ViewHolder {
    ImageView eventTypeImageIcon;
    TextView eventTypeName;
    public eventTypeHolder(View itemView) {
        super(itemView);
        eventTypeImageIcon=(ImageView)itemView.findViewById(R.id.eventIcon);
        eventTypeName=(TextView)itemView.findViewById(R.id.eventNameType);
    }
}
