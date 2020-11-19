package com.example.dwo;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MapFragment extends Fragment {

    private FloatingActionButton fab_size;
    private FloatingActionButton fab_background;
    private FloatingActionButton fab_color;
    private DrawingView drawingView;
    private Integer[] colors = new Integer[9];
    private int i, k;
    private float m;

    public MapFragment() {
    }
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        return fragment;
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
        fab_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.setGeneralColor(colors[k]);
                if(k != 8){
                    k++;
                } else
                    k = 0;
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
                    drawingView.setSizePen(m);
                    fab_size.getBehavior();

                } else
                    m = 3F;
                Log.d("DebugLogs", "MapFragment: size:" + m);
            }
        });
    }

    public void FirstMethod() {
        fab_size = getActivity().findViewById(R.id.fab_brush_size);
        fab_background = getActivity().findViewById(R.id.fab_background_color);
        fab_color = getActivity().findViewById(R.id.fab_brush_color);
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
    }
}