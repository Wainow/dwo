package com.example.dwo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.AEADBadTagException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class QuestFragment extends Fragment {
    private int RoomID;
    private RecyclerView recyclerView;
    private QuestAdapter questAdapter;
    private ArrayList<Quest> data;
    private FragmentTransaction fTrans;

    public static Observer<List<Quest>> observer;
    public SharedPreferencesQuest preferencesQuest;

    public QuestFragment() {
    }

    public static QuestFragment newInstance(int RoomID) {
        QuestFragment fragment = new QuestFragment();
        Bundle args = new Bundle();
        args.putInt("RoomID", RoomID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            RoomID = getArguments().getInt("RoomID");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quest, container, false);
        FirstMethod(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void FirstMethod(final View view) {
        Log.d("DebugLogs", "QuestFragment: FirstMethod");
        recyclerView = view.findViewById(R.id.quest_recycler);
        data = new ArrayList<>();

        fTrans = getChildFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);

        questAdapter = new QuestAdapter(getContext(), data, RoomID);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(questAdapter);
        questAdapter.notifyDataSetChanged();

        observer =  new Observer<List<Quest>>() {
            @Override
            public void onSubscribe(Disposable d) { }

            @Override
            public void onNext(List<Quest> quests) {
                Log.d("DebugLogs", "RoomVillainsFragment: onNext is working");
                data = (ArrayList<Quest>) quests;
                RefreshQuests(view);
                questAdapter.notifyDataSetChanged();
            }
            @Override
            public void onError(Throwable e) { }

            @Override
            public void onComplete() {
            }
        };
        preferencesQuest = new SharedPreferencesQuest(getActivity(), RoomID);
        preferencesQuest.sendQuests();
    }

    private void RefreshQuests(View view) {
        recyclerView = view.findViewById(R.id.quest_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        questAdapter = new QuestAdapter(getContext(), data, RoomID);
        recyclerView.setAdapter(questAdapter);
    }

    public void addQuest(){
        preferencesQuest.addQuest(new QuestGenerator().GenerateQuest());
        questAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }
}