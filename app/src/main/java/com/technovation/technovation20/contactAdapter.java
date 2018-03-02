package com.technovation.technovation20;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Anshuman-HP on 01-03-2018.
 */

public class contactAdapter extends RecyclerView.Adapter<contactHolder> {
    @Override
    public contactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_card,parent,false);

        return new contactHolder(v);
    }

    @Override
    public void onBindViewHolder(final contactHolder holder, int position) {
        switch (position){
            case 0:holder.contactName.setText("Rahul Upadhyay");
                    holder.contactDesignation.setText("(Chief Co-ordinator)");
                    holder.contactNumber.setText("7077105652");
                    holder.contactCard.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:7077105652"));
                            holder.contactCard.getContext().startActivity(intent);
                        }
                    });
                    break;
            case 1:holder.contactName.setText("Simadri Swain");
                holder.contactDesignation.setText("(Assitant Chief Co-ordinator)");
                holder.contactNumber.setText("9439117199");
                holder.contactCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:9439117199"));
                        holder.contactCard.getContext().startActivity(intent);
                    }
                });
                break;
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
