package com.launcher.dwo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.launcher.dwo.Create.CreateClassDataAdapter;
import com.launcher.dwo.Custom.CustomViewPager;
import com.launcher.dwo.Custom.EditTextPlus;
import com.launcher.dwo.Custom.FileWorker;
import com.launcher.dwo.Hero.Hero;
import com.launcher.dwo.Hero.HeroesFragment;
import com.launcher.dwo.Quest.QuestFragment;
import com.launcher.dwo.Villain.VillainsFragment;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.launcher.dwo.Create.CreateDialog.photoUri;
import static com.launcher.dwo.Hero.HeroesFragment.newInstance;

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
    private Boolean isQuest = false;

    private MapFragment RoomMap;
    private QuestFragment RoomQuest;
    private HeroesFragment RoomHeroes;
    private VillainsFragment RoomVillains;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE| WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        FirstMethod();

        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(!isQuest) {
                    range_index++;
                    if (range_index == 6)
                        range_index = 0;
                    fab.setImageResource(roll_backgrounds[range_index]);
                    Snackbar.make(findViewById(R.id.coordinator),
                            "Rolling D" + range[range_index], Snackbar.LENGTH_SHORT)
                            .show();
                }
                return true;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isQuest) {
                    Log.d("DebugLogs", "RoomActivity: DialogFragment is created");
                    //dialogFragment = new RollingDialog(RoomActivity.this, new String[]{"yes", "no", "after", "get"});
                    dialogFragment = new RollingDialog(RoomActivity.this, range[range_index]);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    dialogFragment.show(fragmentManager, "dlg");
                } else{
                    ((QuestFragment)getSupportFragmentManager().getFragments().get(1)).addQuest();
                }
            }
        });

        fab_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DebugLogs", "RoomActivity: Fragment is 2 " + getSupportFragmentManager().getFragments().toString());
                myDataset.get(position).setDescription(editTextPlus.getText().toString());
                json = new Gson().toJson(myDataset);
                Log.d("DebugLogs", "RoomActivity: " + json);
                FileWorker fileWorker = new FileWorker(getApplicationContext());
                fileWorker.writeFile(json);
                finish();
            }
        });

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                if(position == 0) {
                    isQuest = true;
                    Glide.with(getApplicationContext()).load(R.drawable.mini_roll_quest1).into(fab);
                } else {
                    isQuest = false;
                    Glide.with(getApplicationContext()).load(roll_backgrounds[range_index]).into(fab);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_quest:
                pager.setCurrentItem(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_room, menu);
        return true;
    }

    private void FirstMethod() {
        context = RoomActivity.this;
        fab = findViewById(R.id.room_fab_roll);
        fab_ok = findViewById(R.id.room_fab_ok);
        imageButton = findViewById(R.id.room_image);
        pager = findViewById(R.id.room_pager);
        pager.setOffscreenPageLimit(4);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        position = getIntent().getIntExtra("position", 1);
        Log.d("DebugLogs", "RoomActivity: " + position);
        setMyDataset();
        setTitle(myDataset.get(position).getRoom_name());
        imageButton.setImageResource(myDataset.get(position).getRoom_image_src());
        editTextPlus = findViewById(R.id.room_description);
        editTextPlus.setText(myDataset.get(position).getDescription());
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
                    VillainsFragment fragment = ((VillainsFragment) getSupportFragmentManager().getFragments().get(3));
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
                    ((HeroesFragment) getSupportFragmentManager().getFragments().get(0)).refreshHeroes(heroes);
                    ((HeroesFragment) getSupportFragmentManager().getFragments().get(0)).mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onComplete() { }
        };
        Log.d("DebugLogs", "RoomActivity: Init new Fragments");
        RoomMap = new MapFragment(pager);
        RoomVillains = VillainsFragment.newInstance(myDataset.get(getPosition()).getRoomID());
        RoomHeroes = newInstance(myDataset.get(getPosition()).getHeroes());
        RoomQuest = QuestFragment.newInstance(myDataset.get(getPosition()).getRoomID());

        pager.setCurrentItem(1);
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
                case 0:
                    return RoomQuest;
                case 2:
                    return RoomMap;
                case 3:
                    return RoomVillains;
                default:
                    return RoomHeroes;
            }
        }

        @Override
        public int getCount() {
            return 4;
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
        if(requestCode == CreateClassDataAdapter.REQUEST_CODE_GET_PHOTOS && data != null && resultCode == Activity.RESULT_OK){
            photoUri = data.getData();
            Log.d("DebugLogs", "CreateDialog: photoUri:" + photoUri.toString());
        }
    }
}