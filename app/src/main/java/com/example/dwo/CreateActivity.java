package com.example.dwo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.dwo.First2Fragment.getMyDataset;

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
    private FloatingActionButton fab;
    private Intent intent;
    private String json;
    private Hero[] heroes;
    private MyBroadcastReceiver myBroadcastReceiver;
    private boolean isNull = true;
    private ImageButton imageButton;
    private ArrayList<Integer> images;
    private Integer room_image_src;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirstMethod();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondMethod();
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                room_image_src = images.get((int) (Math.random()*images.size()-1));
                imageButton.setImageResource(room_image_src);
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
        isNull = false;
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
        fab = findViewById(R.id.fab);
        imageButton = findViewById(R.id.image_room);

        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(AddHeroService.ACTION_MYINTENTSERVICE);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        getApplicationContext().registerReceiver(myBroadcastReceiver, intentFilter);

        images = new ArrayList<>();
        images.add(R.drawable.veryknight);
        images.add(R.drawable.veryrow);
        images.add(R.drawable.veryminimag);
        images.add(R.drawable.mini_thief);
        room_image_src = R.drawable.mini_q;
    }

    public void SecondMethod(){
        if(!isNull) {
            this.myDataset.remove(this.myDataset.size()-1);
            this.room_array.add(new Room(
                    editTextPlus.getText().toString(),
                    this.myDataset,
                    room_image_src
            ));
            json = new Gson().toJson(this.room_array);
            Log.d(TAG, "SecondMethod: " + json);
            FileWorker fileWorker = new FileWorker(getApplicationContext());
            fileWorker.writeFile(json);
            finish();
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