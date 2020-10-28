package com.example.dwo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentController;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class General extends AppCompatActivity {

    private String type, username;
    private int count = 0;
    private Intent intent;
    private BottomAppBar bottomAppBar;
    private FloatingActionButton fab;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        Toolbar toolbar = findViewById(R.id.toolbar_general);
        setSupportActionBar(toolbar);
        if(count == 0){
            setTitle("General");
        } else
            setTitle("List of Rooms");

        //FirstMethod();
        intent = new Intent(this, CreateActivity.class);
        fab = findViewById(R.id.play_fab);
        fab.setBackgroundResource(R.drawable.play);
        bottomAppBar = findViewById(R.id.bottom);
        //final NavController controller = Navigation.findNavController(this, R.id.fragment_container);

        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        //pager.setCurrentItem(0);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0) {
                        Log.d("DebugLogs", "position000: " + position);
                        bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                        fab.setImageResource(R.drawable.play);
                        setTitle("General");
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ChangeFragment();
                            }
                        });
                }
                else{
                        Log.d("DebugLogs", "position111: " + position);
                        bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                        fab.setImageResource(R.drawable.plus);
                        setTitle("List of Rooms");
                        pager.setPadding(0, 170,0,0);
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(intent);
                            }
                        });
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
    }

    private void FirstMethod() {
        username = getIntent().getExtras().get("username").toString();
    }

    private void SecondMethod() {
        type = "general";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username);
    }

    private void ChangeFragment(){
        //controller.navigate(R.id.action_FirstFragment_to_First2Fragment);
        pager.setCurrentItem(1);
        bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
        fab.setImageResource(R.drawable.plus);
    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 1:
                    return new First2Fragment();
                default:
                    return new FirstFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

    }
}