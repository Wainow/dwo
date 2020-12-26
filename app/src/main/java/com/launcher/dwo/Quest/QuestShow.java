package com.launcher.dwo.Quest;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.launcher.dwo.Custom.EditTextPlus;
import com.launcher.dwo.R;
import com.launcher.dwo.Custom.TextViewPlus;

public class QuestShow extends Fragment {
    private ImageButton close_btn;
    private TextViewPlus title_view;
    private EditTextPlus history_view;
    private EditTextPlus description_view;

    private int RoomID;
    private int position;
    private String title;
    private String history;
    private String description;

    public QuestShow(){
    }

    public static Fragment newInstance(String name, String history, String description, int RoomID, int position) {
        Log.d("DebugLogs", "QuestShow: args: " + name + " " + history + " " + description);
        QuestShow fragment = new QuestShow();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("history", history);
        args.putString("description", description);
        args.putInt("RoomID", RoomID);
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quest_show, container, false);
        setHasOptionsMenu(true);
        Toolbar toolbar = view.findViewById(R.id.fragment_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        FirstMethod(view);
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_quest).setVisible(false);
    }

    private void FirstMethod(View view) {
        close_btn = view.findViewById(R.id.fragment_close);
        title_view = view.findViewById(R.id.quest_title);
        history_view = view.findViewById(R.id.quest_story);
        description_view = view.findViewById(R.id.quest_description);

        if(getArguments() != null) {
            title = getArguments().getString("name");
            history = getArguments().getString("history");
            description = getArguments().getString("description");

            title_view.setText("«" + title + "»");
            history_view.setText(history);
            description_view.setText(description);

            RoomID = getArguments().getInt("RoomID");
            position = getArguments().getInt("position");
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DebugLogs", "QuestShow: Click");
                getParentFragment()
                        .getChildFragmentManager()
                        .popBackStack();
                new QuestSharedPreferences(getContext(), RoomID).setQuest(position, new Quest(
                        title,
                        description,
                        history_view.getText().toString()
                ));
            }
        });
    }
}