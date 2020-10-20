package com.example.dwo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.provider.Telephony.Mms.Part.FILENAME;
import static com.example.dwo.First2Fragment.getRandomInt0_10;

public class CreateActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private int[] myDataset;

    final String LOG_TAG = "myLogs";
    final String FILENAME = "file";
    final String DIR_SD = "MyFiles";
    final String FILENAME_SD = "fileSD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirstMethod();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Writing your room...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                FileWorker fileWorker = new FileWorker(getApplicationContext());
                fileWorker.writeFile();
                fileWorker.readFile();
            }
        });
    }

    public void FirstMethod(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_hero);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        setMyDataset();

        mAdapter = new My2Adapter(this, myDataset);
        recyclerView.setAdapter(mAdapter);
    }

    public void setMyDataset() {
        this.myDataset = new int[]{1,2,3,4,5};
    }
}