package com.launcher.dwo.General;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.launcher.dwo.Create.CreateActivity;
import com.launcher.dwo.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GeneralActivity extends AppCompatActivity {

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
        setTitle("Hi, Master!");
        FirstMethod();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count == 0){
                    ChangeFragment();
                    count++;
                }
            }
        });
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                if(position == 0) {
                        Log.d("DebugLogs", "position000: " + position);
                        bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                        fab.setImageResource(R.drawable.play);
                        setTitle("Hi, Master!");
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
                        //pager.setPadding(0, 170,0,0);
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(intent);
                            }
                        });
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    private void FirstMethod() {
        intent = new Intent(this, CreateActivity.class);
        fab = findViewById(R.id.play_fab);
        fab.setBackgroundResource(R.drawable.play);
        bottomAppBar = findViewById(R.id.bottom);

        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
    }

    private void ChangeFragment(){
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
                    return new GeneralRoomListFragment();
                default:
                    return new GeneralStoryFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

    }
}