package com.example.dwo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
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
    public My2Adapter mAdapter;
    private Bundle args;
    private View view;

    public RoomHeroesFragment(){
    }

    public static RoomHeroesFragment newInstance(ArrayList<Hero> heroes){
        RoomHeroesFragment fragment = new RoomHeroesFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("heroes", (ArrayList<Hero>) heroes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_room_heroes, container, false);
        FirstMethod(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void FirstMethod(View view) {
        args = getArguments();
        heroes = args.getParcelableArrayList("heroes");
        Log.d("DebugLogs", "RoomHeroesFragment: heroes: " + heroes.toString());
        RoomID = (int) (Math.random() * 10000);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_room_hero);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new My2Adapter(getContext(), heroes, RoomID, false);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void refreshHeroes(ArrayList<Hero> heroes){
        this.heroes = heroes;
        mAdapter = new My2Adapter(getContext(), heroes, RoomID, false);
        recyclerView.setAdapter(mAdapter);
        args.putParcelableArrayList("heroes", (ArrayList<Hero>) heroes);
        mAdapter.notifyDataSetChanged();
    }
}