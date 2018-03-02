package com.technovation.technovation20;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeoutException;

/**
 * Created by Anshuman-HP on 19-02-2018.
 */

public class NotificationsRecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView message;
    TextView postedTime;

    public NotificationsRecyclerViewHolder(View itemView) {
        super(itemView);
        message=(TextView)itemView.findViewById(R.id.notifiactionBody);
        postedTime=(TextView)itemView.findViewById(R.id.postedOm);
    }
}
