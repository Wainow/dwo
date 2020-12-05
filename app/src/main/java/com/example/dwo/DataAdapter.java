package com.example.dwo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;

public class DataAdapter extends BaseAdapter {

    private Context mContext;
    private ViewPager pager;
    private int role;
    private boolean isEvil;
    public	Integer[] mThumbIds;
    public static final int REQUEST_CODE_GET_PHOTOS = 101;
    private CreateHeroViewModel model;

    public DataAdapter(CreateHeroViewModel model, Context c, ViewPager viewPager, boolean isEvil) {
        this.model = model;
        this.mContext = c;
        this.pager = viewPager;
        this.isEvil = isEvil;
        if(!isEvil){
            mThumbIds = new Integer[]{R.drawable.mini_knight, R.drawable.mini_mag, R.drawable.mini_row, R.drawable.mini_thief};
        } else{
            mThumbIds = new Integer[]{R.drawable.mini_evil1, R.drawable.mini_download, R.drawable.mini_evil2, R.drawable.mini_evil3, R.drawable.mini_evil4, R.drawable.mini_evil6};
        }
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
            imageView.setLayoutParams(new GridView.LayoutParams(CreateDialog.getDP(mContext, 100), CreateDialog.getDP(mContext, 100)));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            int color = ContextCompat.getColor(mContext, R.color.colorPrimaryPurpleDark);
            imageView.setBackgroundColor(color);
        } else {
            imageView = (ImageButton) convertView;
        }
        String imageAddress;
        Log.d("DebugLogs", "DataAdapter: " + position);
        imageAddress = "android.resource://" + mContext.getPackageName() + "/" + mThumbIds[position];
        Glide.with(mContext).load(imageAddress).into(imageView);

        //imageView.setImageResource(mThumbIds[position]);
        if(!isEvil) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position) {
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
        } else{
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position) {
                        case 0:
                            role = 5;
                            pager.setCurrentItem(1);
                            break;
                        case 1:
                            openGallery();
                            role = 10;
                            pager.setCurrentItem(1);
                            break;
                        case 2:
                            role = 6;
                            pager.setCurrentItem(1);
                            break;
                        case 3:
                            role = 7;
                            pager.setCurrentItem(1);
                            break;
                        case 4:
                            role = 8;
                            pager.setCurrentItem(1);
                            break;
                        case 5:
                            role = 9  ;
                            pager.setCurrentItem(1);
                            break;
                    }
                }
            });
        }
        Hero hero = model.getData().getValue().get(model.getData().getValue().firstKey());
        hero.setRole(role);
        model.updateHero(model.getData().getValue().firstKey(), hero);
        return imageView;
    }

    public int getRole() {
        return role;
    }

    private void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        ((Activity) mContext).startActivityForResult(intent, REQUEST_CODE_GET_PHOTOS);
        Log.d("DebugLogs", "DataAdapter : ((Activity) mContext).getLocalClassName(): " + ((Activity) mContext).getLocalClassName());
    }


}
