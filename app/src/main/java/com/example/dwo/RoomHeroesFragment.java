package com.example.dwo;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedList;

public class RoomHeroesFragment extends Fragment {
    private ArrayList<Hero> heroes;
    private int RoomID;
    private RecyclerView recyclerView;
    private My2Adapter mAdapter;

    public RoomHeroesFragment(ArrayList<Hero> heroes) {
        Log.d("DebugLogs", "RoomHeroesFragment: " + heroes);
        this.heroes = heroes;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_room_heroes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirstMethod();
    }

    private void FirstMethod() {
        RoomID = (int) (Math.random() * 10000);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_room_hero);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new My2Adapter(getContext(), heroes, RoomID);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}