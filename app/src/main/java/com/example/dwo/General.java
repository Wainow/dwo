package com.example.dwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomappbar.BottomAppBar;
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

        //FirstMethod();

        FloatingActionButton fab = findViewById(R.id.play_fab);
        fab.setBackgroundResource(R.drawable.play);
        /*
        //final BottomAppBar bottomAppBar = findViewById(R.id.bottom);
        //FragmentManager fragmentManager = getSupportFragmentManager();
        //final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.add(R.id.fragment_container, new FirstFragment()).commit();

        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                //fragmentTransaction.replace(R.id.fragment_container, new BlankFragment()).commit();
            }
        });

        
         */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SecondMethod();
                ChangeFragment(view);
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

    private void ChangeFragment(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BottomAppBar bottomAppBar = findViewById(R.id.bottom);
        FloatingActionButton fab = findViewById(R.id.play_fab);
        bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
        fragmentTransaction.replace(R.id.fragment_container, new First2Fragment());
        fragmentTransaction.commit();
        fab.setImageResource(R.drawable.plus);
    }
}