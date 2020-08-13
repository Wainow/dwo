package com.example.dwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class General extends AppCompatActivity {

    private String type, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        Toolbar toolbar = findViewById(R.id.toolbar_general);
        setSupportActionBar(toolbar);
        setTitle("General Page");

        FirstMethod();

        FloatingActionButton fab = findViewById(R.id.play_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondMethod();
            }
        });
    }

    private void FirstMethod() {
        username = getIntent().getExtras().get("username").toString();
    }

    private void SecondMethod() {
        type = "general";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username);
    }
}