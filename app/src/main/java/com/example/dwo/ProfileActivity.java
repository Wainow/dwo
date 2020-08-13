package com.example.dwo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView class_text, strength_text, agility_text, intelligence_text, charisma_text, stamina_text, health_text, inventory_text;
    private String result, delimiter;
    private String[] word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FirstMethod();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void FirstMethod(){
        class_text = findViewById(R.id.class_textview);
        strength_text = findViewById(R.id.strength_textview);
        agility_text = findViewById(R.id.agility_textview);
        intelligence_text = findViewById(R.id.intelligence_textview);
        charisma_text = findViewById(R.id.charisma_textview);
        stamina_text = findViewById(R.id.stamina_textview);
        health_text = findViewById(R.id.health_textview);
        inventory_text = findViewById(R.id.inventory_textview);

        result = getIntent().getExtras().get("result").toString();
        delimiter = " ";
        word = result.split(delimiter);

        setTitle(word[3] + " (" + "Human" + ")");
        class_text.setText("class: " + word[4]);
        strength_text.setText("strength: " + word[5]);
        agility_text.setText("agility: " + word[6]);
        intelligence_text.setText("intelligence: " + word[7]);
        charisma_text.setText("charisma: " + word[8]);
        stamina_text.setText("stamina: " + word[9]);
        health_text.setText("health: " + word[10]);
        inventory_text.setText("inventory: " + word[11] + " " + word[12] + " " + word[13]);

    }
}