package com.example.dwo.Hero;

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

import com.example.dwo.R;
import com.example.dwo.Villain.VillainAdapter;

import java.util.ArrayList;

public class HeroesFragment extends Fragment {
    private ArrayList<Hero> heroes;
    private int RoomID;
    private RecyclerView recyclerView;
    public VillainAdapter mAdapter;
    private Bundle args;
    private View view;

    public HeroesFragment(){
    }

    public static HeroesFragment newInstance(ArrayList<Hero> heroes){
        HeroesFragment fragment = new HeroesFragment();
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
        Log.d("DebugLogs", "HeroesFragment: heroes: " + heroes.toString());
        RoomID = (int) (Math.random() * 10000);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_room_hero);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new VillainAdapter(getContext(), heroes, RoomID, false);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void refreshHeroes(ArrayList<Hero> heroes){
        this.heroes = heroes;
        mAdapter = new VillainAdapter(getContext(), heroes, RoomID, false);
        recyclerView.setAdapter(mAdapter);
        args.putParcelableArrayList("heroes", (ArrayList<Hero>) heroes);
        mAdapter.notifyDataSetChanged();
    }
}