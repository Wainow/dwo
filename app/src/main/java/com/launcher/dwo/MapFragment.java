package com.launcher.dwo;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.launcher.dwo.Custom.CustomViewPager;
import com.launcher.dwo.Custom.DrawingView;

public class MapFragment extends Fragment {

    private ImageButton fab_size;
    private ImageButton fab_background;
    private ImageButton fab_color;
    private ImageButton fab_lock;
    private DrawingView drawingView;
    private Integer[] colors = new Integer[9];
    private Integer[] backgrounds = new Integer[9];
    private int i, k;
    private float m;
    private int padding = 80;
    private CustomViewPager pager;

    public MapFragment(){}

    public MapFragment(CustomViewPager pager) {
        this.pager = pager;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirstMethod();
        final boolean[] isPressed = {false};
        fab_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setPagingEnabled(isPressed[0]);
                if (isPressed[0] == false) {
                    fab_lock.setImageResource(R.drawable.lock);
                    isPressed[0] = true;
                } else{
                    fab_lock.setImageResource(R.drawable.unlock);
                    isPressed[0] = false;
                }
            }
        });
        fab_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.setGeneralColor(colors[k]);
                if(k != 8){
                    fab_color.setBackgroundResource(backgrounds[k+1]);
                    k++;
                } else {
                    k = 0;
                    fab_color.setBackgroundResource(backgrounds[k]);
                }
                Log.d("DebugLogs", "MapFragment: color:" + colors[i]);
                Log.d("DebugLogs", "MapFragment: k:" + k);
            }
        });
        fab_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.setBackgroundColor(colors[i]);
                if(i != 8){
                    i++;
                } else
                    i = 0;
                Log.d("DebugLogs", "MapFragment: background:" + colors[i]);
                Log.d("DebugLogs", "MapFragment: k:" + k);
            }
        });
        fab_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m < 200F){
                    m *= 3;
                    Log.d("DebugLogs", "MapFragment: padding:" + padding);
                    padding -= 20;
                    drawingView.setSizePen(m);
                    fab_size.setPadding(padding,padding,padding,padding);

                } else{
                    m = 3F;
                    drawingView.setSizePen(m);
                    padding = 80;
                    fab_size.setPadding(padding,padding,padding,padding);
                    Log.d("DebugLogs", "MapFragment: padding:" + padding);
                }
                Log.d("DebugLogs", "MapFragment: size:" + m);
            }
        });
    }

    public void FirstMethod() {
        fab_size = getActivity().findViewById(R.id.fab_brush_size);
        fab_background = getActivity().findViewById(R.id.fab_background_color);
        fab_color = getActivity().findViewById(R.id.fab_brush_color);
        fab_lock = getActivity().findViewById(R.id.fab_lock);
        drawingView = getActivity().findViewById(R.id.DrawingView);
        setColors();
    }

    private void setColors() {
        i = 0;
        k = 0;
        m = 3F;
        colors[0] = Color.BLUE;
        colors[1] = Color.GREEN;
        colors[2] = Color.YELLOW;
        colors[3] = Color.RED;
        colors[4] = Color.MAGENTA;
        colors[5] = Color.CYAN;
        colors[6] = Color.WHITE;
        colors[7] = Color.GRAY;
        colors[8] = Color.BLACK;

        backgrounds[0] = R.drawable.round_button;
        backgrounds[1] = R.drawable.round_circle1;
        backgrounds[2] = R.drawable.round_circle2;
        backgrounds[3] = R.drawable.round_circle3;
        backgrounds[4] = R.drawable.round_circle4;
        backgrounds[5] = R.drawable.round_circle5;
        backgrounds[6] = R.drawable.round_circle6;
        backgrounds[7] = R.drawable.round_circle7;
        backgrounds[8] = R.drawable.round_circle8;
    }
}