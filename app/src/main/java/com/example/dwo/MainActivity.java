package com.example.dwo;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.IBinder;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private EditText UsernameEt, PasswordEt;
    private String username, password, type;
    private Animation edit_text_anim;
    private ProgressBar bar;
    private String imageAddress;
    private int resourceId = R.drawable.knight;
    private ImageView imageView;


    private CountService service;
    boolean mBound = false;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            CountService.CountBinder binder = (CountService.CountBinder) iBinder;
            service = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(this, CountService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mBound){
            unbindService(mConnection);
            mBound = false;
        }
    }

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
                if(mBound) {
                    bar.setVisibility(View.VISIBLE);
                    Snackbar.make(view, "Checking your connection..." + "{" + service.getCounter() + "}", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    //SecondMethod();
                    service.plusCounter();
                    if (service.getCounter() >= 100) {
                        TestMethod();
                    }
                }
            }
        });
    }

    public void FirstMethod() {
        edit_text_anim = AnimationUtils.loadAnimation(this, R.anim.edit_text_anim);
        UsernameEt = findViewById(R.id.login_edittext);
        PasswordEt = findViewById(R.id.password_edittext);
        bar = findViewById(R.id.fabProgress);
        imageAddress = "android.resource://"  + this.getPackageName() + "/" + resourceId;
        imageView = findViewById(R.id.image_knight);
        Glide.with(this).load(imageAddress).into(imageView);
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

    private void TestMethod(){
        Intent intent = new Intent(this, General.class);
        startActivity(intent);
    }
}