package com.example.dwo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.dwo.CreateDialog.photoUri;

public class RoomActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private FloatingActionButton fab_ok;
    private EditTextPlus editTextPlus;
    private ImageButton imageButton;
    private CustomViewPager pager;
    private PagerAdapter pagerAdapter;
    private int position;
    private ArrayList<Room> myDataset;
    private DialogFragment dialogFragment;
    private int[] range;
    private int range_index;
    private Integer[] roll_backgrounds;

    private String json;
    public static Observer<TreeMap<Integer, Hero>> observer_room;

    private MapFragment RoomMap;
    private RoomHeroesFragment RoomHeroes;
    private RoomVillainsFragment RoomVillains;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirstMethod();

        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                range_index++;
                if(range_index == 6)
                    range_index = 0;
                fab.setImageResource(roll_backgrounds[range_index]);
                Snackbar.make(findViewById(R.id.coordinator),
                        "Rolling D" + range[range_index], Snackbar.LENGTH_SHORT)
                        .show();
                return  true;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DebugLogs", "My2Adapter: DialogFragment is created");
                //dialogFragment = new RollingDialog(RoomActivity.this, new String[]{"yes", "no", "after", "get"});
                dialogFragment = new RollingDialog(RoomActivity.this, range[range_index]);
                FragmentManager fragmentManager = getSupportFragmentManager();
                dialogFragment.show(fragmentManager, "dlg");
            }
        });

        fab_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                json = new Gson().toJson(myDataset);
                Log.d("DebugLogs", "RoomActivity: " + json);
                FileWorker fileWorker = new FileWorker(getApplicationContext());
                fileWorker.writeFile(json);
                finish();
            }
        });
    }

    private void FirstMethod() {
        fab = findViewById(R.id.room_fab_roll);
        fab_ok = findViewById(R.id.room_fab_ok);
        imageButton = findViewById(R.id.room_image);
        pager = findViewById(R.id.room_pager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        position = getIntent().getIntExtra("position", 0);
        Log.d("DebugLogs", "RoomActivity: " + position);
        setMyDataset();
        setTitle(myDataset.get(position).getRoom_name());
        imageButton.setImageResource(myDataset.get(position).getRoom_image_src());
        editTextPlus = findViewById(R.id.room_description);
        roll_backgrounds = new Integer[]{
                R.drawable.mini_roll3,
                R.drawable.mini_roll6,
                R.drawable.mini_roll10,
                R.drawable.mini_roll20,
                R.drawable.mini_roll30,
                R.drawable.mini_roll100
        };
        range = new int[]{3,6,10,20,30,100};
        range_index = 4;
        observer_room = new Observer<TreeMap<Integer, Hero>>() {
            @Override
            public void onSubscribe(Disposable d) { }

            @Override
            public void onNext(TreeMap<Integer, Hero> integerHeroTreeMap) {
                Log.d("DebugLogs", "RoomActivity: onNext");
                ArrayList<Hero> heroes = myDataset.get(position).getHeroes();
                heroes.set(integerHeroTreeMap.firstKey(), integerHeroTreeMap.get(integerHeroTreeMap.firstKey()));
                Log.d("DebugLogs", "RoomActivity: heroes: " + heroes.toString());
                myDataset.get(position).setHeroes(heroes);
                RoomHeroes.FirstMethod();
                RoomHeroes.mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onComplete() { }
        };

        RoomMap = new MapFragment(pager);
        RoomVillains = new RoomVillainsFragment(myDataset.get(getPosition()).getRoomID());
        RoomHeroes = new RoomHeroesFragment(myDataset.get(getPosition()).getHeroes());
    }

    public int getPosition() {
        return position;
    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 1:
                    return RoomMap;
                case 2:
                    return RoomVillains;
                default:
                    return RoomHeroes;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

    }

    public void setMyDataset() {
        FileWorker fileWorker = new FileWorker(this);
        String json = fileWorker.readFile();
        Log.d("DebugLogs", "json: " + json);
        myDataset = new ArrayList<>(Arrays.asList(new Gson().fromJson(json, Room[].class)));
        Log.d("DebugLogs", "MyDataset: " + this.myDataset.toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == DataAdapter.REQUEST_CODE_GET_PHOTOS && data != null && resultCode == Activity.RESULT_OK){
            photoUri = data.getData();
            Log.d("DebugLogs", "CreateDialog: photoUri:" + photoUri.toString());
        }
    }
}