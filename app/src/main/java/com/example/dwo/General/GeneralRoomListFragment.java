package com.example.dwo.General;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dwo.Custom.FileWorker;
import com.example.dwo.General.GeneralRoomListAdapter;
import com.example.dwo.R;
import com.example.dwo.Room;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class GeneralRoomListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private static final String TAG = "DebugLogs";
    private Intent intent;
    private String json;
    public static ArrayList<Room> myDataset;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_room_list_general, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirstMethod();
    }

    private void FirstMethod() {
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_rooms);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        setMyDataset();

        mAdapter = new GeneralRoomListAdapter(getContext(), myDataset);
        recyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout = getActivity().findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        intent = getActivity().getIntent();
    }

    @Override
    public void onRefresh() {
        FirstMethod();

        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public static int getRandomInt0_10(){
        double num = Math.random() * 10;
        return (int) num;
    }

    public void setMyDataset() {
        FileWorker fileWorker = new FileWorker(getContext());
        json = fileWorker.readFile();
        Log.d(TAG, "json: " + json);
        if(!json.equals("")) {
            myDataset = new ArrayList<>(Arrays.asList(new Gson().fromJson(json, Room[].class)));
            Log.d(TAG, "MyDataset: " + this.myDataset.toString());
        } else
            myDataset = new ArrayList<>();
    }

    public static ArrayList<Room> getMyDataset(){
        return myDataset;
    }
}