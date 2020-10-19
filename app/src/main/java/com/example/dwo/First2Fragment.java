package com.example.dwo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class First2Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private static final String TAG = "DebugLogs";
    private int[] myDataset;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_first2, container, false);
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

        mAdapter = new MyAdapter(getContext(), myDataset);
        recyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout = getActivity().findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        FirstMethod();

        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public static int getRandomInt0_10(){
        double num = (double) Math.random() * 10;
        return (int) num;
    }

    public void setMyDataset() {
        this.myDataset = new int[]{getRandomInt0_10(), getRandomInt0_10(), getRandomInt0_10(), getRandomInt0_10(), getRandomInt0_10(), getRandomInt0_10(), getRandomInt0_10(), getRandomInt0_10(), getRandomInt0_10(), getRandomInt0_10(), getRandomInt0_10(), getRandomInt0_10(), getRandomInt0_10(), getRandomInt0_10()};;
    }
}