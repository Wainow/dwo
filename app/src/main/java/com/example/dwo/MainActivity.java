package com.example.dwo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText UsernameEt, PasswordEt;
    private String username, password, type;
    private Animation edit_text_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Dark World Online");

        FirstMethod();
        StartAnim();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Checking your connection...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                SecondMethod();
            }
        });
    }

    public void FirstMethod() {
        edit_text_anim = AnimationUtils.loadAnimation(this, R.anim.edit_text_anim);
        UsernameEt = findViewById(R.id.login_edittext);
        PasswordEt = findViewById(R.id.password_edittext);
    }

    private void StartAnim() {
        UsernameEt.startAnimation(edit_text_anim);
        PasswordEt.startAnimation(edit_text_anim);
    }

    public void SecondMethod() {
        username = UsernameEt.getText().toString();
        password = PasswordEt.getText().toString();
        type = "login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}