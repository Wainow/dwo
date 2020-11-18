package com.example.dwo;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;

public class DataAdapter extends BaseAdapter {

    private Context mContext;
    private ViewPager pager;
    private int role;

    public DataAdapter(Context c, ViewPager viewPager) {
        this.mContext = c;
        this.pager = viewPager;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageButton imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageButton(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            int color = ContextCompat.getColor(mContext, R.color.colorPrimaryPurpleDark);
            imageView.setBackgroundColor(color);
        } else {
            imageView = (ImageButton) convertView;
        }
        String imageAddress = "android.resource://"  + mContext.getPackageName() + "/" + mThumbIds[position];
        Glide.with(mContext).load(imageAddress).into(imageView);
        //imageView.setImageResource(mThumbIds[position]);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        Log.d("DebugLogs", "DataAdapter: Item knight selected!");
                        role = 1;
                        pager.setCurrentItem(1);
                        break;
                    case 1:
                        Log.d("DebugLogs", "DataAdapter: Item mag selected!");
                        role = 2;
                        pager.setCurrentItem(1);
                        break;
                    case 2:
                        Log.d("DebugLogs", "DataAdapter: Item rower selected!");
                        role = 3;
                        pager.setCurrentItem(1);
                        break;
                    case 3:
                        Log.d("DebugLogs", "DataAdapter: Item thief selected!");
                        role = 4;
                        pager.setCurrentItem(1);
                        break;
                }
            }
        });
        return imageView;
    }

    // references to our images
    public	Integer[] mThumbIds = { R.drawable.mini_knight, R.drawable.mini_mag, R.drawable.mini_row, R.drawable.mini_thief};

    public int getRole() {
        return role;
    }
}
