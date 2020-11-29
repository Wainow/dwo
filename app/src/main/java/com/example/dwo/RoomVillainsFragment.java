package com.example.dwo;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RoomVillainsFragment extends Fragment {
    private RecyclerView recyclerView;
    public My2Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Hero> myDataset = new ArrayList<>();
    private Bundle args;

    private String TAG = "DebugLogs";
    private int RoomID;
    public static Observer<List<Hero>> observer;
    private SharedPreferencesHelper preferencesHelper;

    public RoomVillainsFragment(){}

    public static RoomVillainsFragment newInstance(int RoomID){
        RoomVillainsFragment fragment = new RoomVillainsFragment();
        Bundle args = new Bundle();
        args.putInt("RoomID", RoomID);
        fragment.setArguments(args);
        return fragment;
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

    public void FirstMethod(){
        args = getArguments();
        RoomID = args.getInt("RoomID");

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_room_villain);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new My2Adapter(getContext(), myDataset, RoomID, true);
        recyclerView.setAdapter(mAdapter);
        observer =  new Observer<List<Hero>>() {
            @Override
            public void onSubscribe(Disposable d) { }

            @Override
            public void onNext(List<Hero> heroes) {
                Log.d("DebugLogs", "RoomVillainsFragment: onNext is working");
                myDataset = (ArrayList<Hero>) heroes;
                if (mAdapter.getDialogFragment() != null) {
                    mAdapter.getDialogFragment().dismiss();
                } else
                    Log.d("DebugLogs", "RoomVillainsFragment: DialogFragment is null");
                RefreshVillains();
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onError(Throwable e) { }

            @Override
            public void onComplete() {
            }
        };
        preferencesHelper = new SharedPreferencesHelper(getActivity(), RoomID);
        preferencesHelper.sendVillains();
    }

    public void RefreshVillains(){
        Log.d("DebugLogs", "RoomVillainsFragment: RoomID: " + RoomID);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_room_villain);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new My2Adapter(getContext(), myDataset, RoomID, true);
        recyclerView.setAdapter(mAdapter);
    }
}