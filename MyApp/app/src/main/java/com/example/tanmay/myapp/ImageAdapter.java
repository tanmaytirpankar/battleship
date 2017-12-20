package com.example.tanmay.myapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by tanma on 9/7/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    public int count=0;
    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(210, 210));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    public Integer[] mThumbIds = {
            R.drawable.blank,R.drawable.blank,R.drawable.blank,R.drawable.blank,R.drawable.blank,R.drawable.blank,R.drawable.blank,R.drawable.blank,R.drawable.blank,R.drawable.blank,
            R.drawable.blank,R.drawable.blank,R.drawable.blank,R.drawable.blank,R.drawable.blank,R.drawable.blank,R.drawable.blank,R.drawable.blank,R.drawable.blank,R.drawable.blank,
            R.drawable.blank,R.drawable.blank,R.drawable.blank,R.drawable.blank,R.drawable.blank
    };
    public void changeToShip(int position)
    {
        mThumbIds[position]=R.drawable.ship;
        notifyDataSetChanged();
        count++;
    }
    public void changeToHit(int position)
    {
        mThumbIds[position]=R.drawable.hit;
        notifyDataSetChanged();
    }
    public void changeToMiss(int position)
    {
        mThumbIds[position]=R.drawable.miss;
        notifyDataSetChanged();
    }
    public void changeToBlank(int position)
    {
        mThumbIds[position]=R.drawable.blank;
        notifyDataSetChanged();
    }
    public void myturn(int[] hisships,int[] hisdestroyedships,int[] hisgrid)
    {
        for (int i = 0; i < 25; i++) {
            if (hisgrid[i] == -1)
                changeToBlank(i);
            else
                changeToMiss(i);
        }
        for (int i = 0; i < 5; i++) {
            if(hisdestroyedships[i]==1)
                changeToHit(hisships[i]);
        }
    }
    public void histurn(int[] myships,int[] mydestroyedships,int[] mygrid)
    {
        for (int i = 0; i < 25; i++) {
            if (mygrid[i]==-1)
                changeToBlank(i);
            else
                changeToMiss(i);
        }
        for (int i = 0; i < 5; i++) {
            if(mydestroyedships[i]==1)
                changeToHit(myships[i]);
            else if(mydestroyedships[i]==-1)
                changeToShip(myships[i]);
        }
    }
}
