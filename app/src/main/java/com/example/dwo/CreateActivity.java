package com.example.dwo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

import static android.provider.Telephony.Mms.Part.FILENAME;
import static com.example.dwo.First2Fragment.getMyDataset;
import static com.example.dwo.First2Fragment.getRandomInt0_10;

public class CreateActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private My2Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Hero> myDataset;
    private String TAG = "DebugLogs";
    private int RoomID;

    final String LOG_TAG = "myLogs";
    final String FILENAME = "file";
    final String DIR_SD = "MyFiles";
    final String FILENAME_SD = "fileSD";

    private ArrayList<Room> room_array;
    private EditTextPlus editTextPlus;
    private Intent intent;
    private String json;
    private Hero[] heroes;
    private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirstMethod();

        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton fab_check = findViewById(R.id.fab_check);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondMethod();
                finish();
            }
        });
        fab_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RefreshHeroes();
            }
        });
    }

    private void RefreshHeroes() {
        this.myDataset.remove(this.myDataset.size()-1);
        FileWorker fileWorker = new FileWorker(getBaseContext());
        Log.d(TAG, "ReshreshHeroes: RoomID: " + String.valueOf(RoomID));
        json = fileWorker.readFile(String.valueOf(RoomID));
        Log.d(TAG, "ReshreshHeroes: " + json);
        this.myDataset.add(new Gson().fromJson(json, Hero.class));
        Log.d(TAG, "MyDataset: " + this.myDataset.toString());
        this.myDataset.add(new Hero());
        Log.d(TAG, "First hero: " + myDataset.get(0).toString());
        mAdapter.notifyDataSetChanged();
        mAdapter.getDialogFragment().dismiss();
    }

    public void FirstMethod(){
        RoomID = (int) (Math.random() * 10000);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_hero);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        setMyDataset();

        mAdapter = new My2Adapter(this, myDataset, RoomID);
        recyclerView.setAdapter(mAdapter);

        editTextPlus = findViewById(R.id.title_text);
        intent = new Intent(this, General.class);
        room_array = getMyDataset();

        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(AddHeroService.ACTION_MYINTENTSERVICE);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        getApplicationContext().registerReceiver(myBroadcastReceiver, intentFilter);
    }

    public void SecondMethod(){
        if(heroes != null) {
            this.room_array.add(new Room(
                    editTextPlus.getText().toString(),
                    heroes
            ));
            json = new Gson().toJson(this.room_array);
            Log.d(TAG, "SecondMethod: " + json);
            FileWorker fileWorker = new FileWorker(getApplicationContext());
            fileWorker.writeFile(json);
        } else {
            Toast.makeText(this, "Your room doesn't have any heroes!", Toast.LENGTH_LONG).show();
        }
    }

    public void setMyDataset() {
        this.myDataset = new ArrayList<>();
        this.myDataset.add(new Hero());
        Log.d(TAG, "First hero: " + myDataset.get(0).toString());
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            RefreshHeroes();
        }
    }

    @Override
    protected void onStop() {
        getApplicationContext().unregisterReceiver(myBroadcastReceiver);
        super.onStop();
    }
}