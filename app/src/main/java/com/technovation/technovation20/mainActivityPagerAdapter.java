package com.technovation.technovation20;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by Anshuman-HP on 25-02-2018.
 */

public class mainActivityPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;

    int[] mResources = {
            R.mipmap.lazer_maze,
           R.mipmap.merchandise,
            R.mipmap.special,
            R.mipmap.special4,
            R.mipmap.star,
            R.mipmap.comedy,
            R.mipmap.dj,
            R.mipmap.alok
    };

    public mainActivityPagerAdapter(Context mContext, LayoutInflater mLayoutInflater) {
        this.mContext = mContext;
        this.mLayoutInflater = mLayoutInflater;
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ConstraintLayout) object);
    }



    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.image_card, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        Glide.with(container)
                .load(mResources[position])
                .into(imageView);
        container.addView(itemView);

        return itemView;
}

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View)object);
    }
}
