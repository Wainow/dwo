package com.example.dwo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RoomVillainsFragment extends Fragment {
    private RecyclerView recyclerView;
    private My2Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Hero> myDataset;

    private String TAG = "DebugLogs";
    private int RoomID;

    public RoomVillainsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room_villains, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirstMethod();
    }

    public void FirstMethod() {
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_room_villain);
        this.myDataset = new ArrayList<>();
        this.myDataset.add(new Hero());

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new My2Adapter(getContext(), myDataset, RoomID, true);
        recyclerView.setAdapter(mAdapter);
    }
}