package com.example.dwo;

import android.app.Activity;
import android.content.Context;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
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
import static com.example.dwo.RoomHeroesFragment.newInstance;

public class RoomActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private FloatingActionButton fab_ok;
    private EditTextPlus editTextPlus;
    private ImageButton imageButton;
    private CustomViewPager pager;
    private PagerAdapter pagerAdapter;
    private int position = 0;
    private ArrayList<Room> myDataset;
    private DialogFragment dialogFragment;
    private int[] range;
    private int range_index;
    private Integer[] roll_backgrounds;

    private String json;
    public static Observer<TreeMap<Integer, Hero>> observer_room;
    public static Context context;

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
                Log.d("DebugLogs", "RoomActivity: Fragment is 2 " + getSupportFragmentManager().getFragments().toString());

                json = new Gson().toJson(myDataset);
                Log.d("DebugLogs", "RoomActivity: " + json);
                FileWorker fileWorker = new FileWorker(getApplicationContext());
                fileWorker.writeFile(json);
                finish();
            }
        });
    }

    private void FirstMethod() {
        context = RoomActivity.this;
        fab = findViewById(R.id.room_fab_roll);
        fab_ok = findViewById(R.id.room_fab_ok);
        imageButton = findViewById(R.id.room_image);
        pager = findViewById(R.id.room_pager);
        pager.setOffscreenPageLimit(3);
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
                Log.d("DebugLogs", "RoomActivity: hero isEvil? : " + integerHeroTreeMap.get(integerHeroTreeMap.firstKey()).isEvil());
                if(integerHeroTreeMap.get(integerHeroTreeMap.firstKey()).isEvil()){
                    RoomVillainsFragment fragment = ((RoomVillainsFragment) getSupportFragmentManager().getFragments().get(2));
                    Log.d("DebugLogs", "RoomActivity: villain: " +  integerHeroTreeMap.get(integerHeroTreeMap.firstKey()));
                    fragment.preferencesHelper.setVillain(integerHeroTreeMap.firstKey(), integerHeroTreeMap.get(integerHeroTreeMap.firstKey()));
                    fragment.mAdapter.notifyDataSetChanged();
                }
                else{
                    ArrayList<Hero> heroes = myDataset.get(position).getHeroes();
                    heroes.set(integerHeroTreeMap.firstKey(), integerHeroTreeMap.get(integerHeroTreeMap.firstKey()));
                    myDataset.get(position).setHeroes(heroes);
                    Log.d("DebugLogs", "RoomActivity: heroes: " + heroes.toString());
                    Log.d("DebugLogs", "RoomActivity: myDataset: " + myDataset.get(position).getHeroes().toString());
                    ((RoomHeroesFragment) getSupportFragmentManager().getFragments().get(0)).refreshHeroes(heroes);
                    ((RoomHeroesFragment) getSupportFragmentManager().getFragments().get(0)).mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onComplete() { }
        };
        Log.d("DebugLogs", "RoomActivity: Init new Fragments");
        RoomMap = new MapFragment(pager);
        RoomVillains = RoomVillainsFragment.newInstance(myDataset.get(getPosition()).getRoomID());
        RoomHeroes = newInstance(myDataset.get(getPosition()).getHeroes());
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

    public static Context getRoomActivityContext(){
        return RoomActivity.context;
    }
}