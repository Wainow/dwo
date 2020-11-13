package com.example.dwo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.example.dwo.First3Fragment;
import com.example.dwo.SecondFragment;

public class MyFragmentPagerAdapter extends PagerAdapter {
    private Context context;
    private int resId = 0;

    public MyFragmentPagerAdapter(Context context) {
        this.context = context;
    }

    public Object instantiateItem(ViewGroup collection, int position) {

        LayoutInflater inflater = LayoutInflater.from(this.context);
        switch (position) {
            case 0:
                resId = R.layout.fragment_first3;
                break;
            case 1:
                resId = R.layout.fragment_second;
                break;
        }
        ViewGroup layout = (ViewGroup) inflater.inflate(resId, collection, false);
        if(resId == R.layout.fragment_first3) {
            GridView g = layout.findViewById(R.id.gridView);
            DataAdapter mAdapter = new DataAdapter(layout.getContext());
            g.setAdapter(mAdapter);
            Log.d("DebugLogs", "First3Fragment: GridView created");
            g.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
        }
        collection.addView(layout);
        return layout;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

}
