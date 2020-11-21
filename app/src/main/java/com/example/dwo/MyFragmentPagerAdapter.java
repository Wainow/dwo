package com.example.dwo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dwo.First3Fragment;
import com.example.dwo.SecondFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.Timer;
import java.util.TimerTask;

public class MyFragmentPagerAdapter extends PagerAdapter {
    private Context context;
    private int resId = 0;
    private ViewPager pager;
    private int count = 0;
    private DataAdapter mAdapter;
    private boolean isEvil;

    private Button btn;
    private TextView text_strength;
    private TextView text_agility;
    private TextView text_intelligence;
    private TextView text_charisma;
    private TextView text_stamina;
    private TextView text_health;

    private Specifications specifications;
    private Hero hero;
    private String json;
    private int RoomID;
    private SampleTask mSampleTask;


    public MyFragmentPagerAdapter(Context context, ViewPager pager, int RoomID, boolean isEvil) {
        this.context = context;
        this.pager = pager;
        this.RoomID = RoomID;
        this.isEvil = isEvil;
    }

    public Object instantiateItem(final ViewGroup collection, int position) {

        LayoutInflater inflater = LayoutInflater.from(this.context);
        switch (position) {
            case 0:
                resId = R.layout.fragment_first3;
                break;
            case 1:
                resId = R.layout.fragment_second;
                break;
            case 2:
                resId = R.layout.fragment_second2;
        }
        final ViewGroup layout = (ViewGroup) inflater.inflate(resId, collection, false);
        if(resId == R.layout.fragment_first3) {
            GridView g = layout.findViewById(R.id.gridView);
            mAdapter = new DataAdapter(layout.getContext(), pager, isEvil);
            g.setAdapter(mAdapter);
            Log.d("DebugLogs", "First3Fragment: GridView created");
        } else if(resId == R.layout.fragment_second){
            mSampleTask = new SampleTask();
            count = 0;
            btn = layout.findViewById(R.id.btn_second);
            text_strength = layout.findViewById(R.id.text_strength);
            text_agility = layout.findViewById(R.id.text_agility);
            text_intelligence = layout.findViewById(R.id.text_intelligence);
            text_charisma = layout.findViewById(R.id.text_charisma);
            text_stamina = layout.findViewById(R.id.text_stamina);
            text_health = layout.findViewById(R.id.text_health);;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(count == 0) {
                        mSampleTask.execute();
                    } else if(count == 1){
                        mSampleTask.cancel(true);
                    } else if(count == 2){
                        pager.setCurrentItem(2);
                    }
                }
            });
        } else{
            final EditText editName = layout.findViewById(R.id.create_name);
            final EditText editStory = layout.findViewById(R.id.create_story);
            final EditText editMoney = layout.findViewById(R.id.create_money);
            FloatingActionButton fab = layout.findViewById(R.id.create_fab);
            final Intent intentAddHeroService = new Intent(layout.getContext(), AddHeroService.class);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hero = new Hero(
                            editName.getText().toString(),
                            mAdapter.getRole(),
                            specifications,
                            "",
                            editStory.getText().toString(),
                            Double.parseDouble(editMoney.getText().toString())
                    );
                    json = new Gson().toJson(hero);
                    Log.d("DebugLogs", "Second2Fragment: " + json);
                    FileWorker fileWorker = new FileWorker(layout.getContext());
                    Log.d("DebugLogs", "Second2Fragment: RoomID: " + String.valueOf(RoomID));
                    fileWorker.writeFile(String.valueOf(RoomID), json);
                    layout.getContext().startService(intentAddHeroService);

                }
            });
        }
        collection.addView(layout);
        return layout;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    public Hero getHero() {
        return hero;
    }

    class SampleTask extends AsyncTask<Long, Specifications, Specifications> {

        @Override
        protected void onPreExecute() {
            btn.setText("Stop");
            count++;
        }

        @Override
        protected Specifications doInBackground(Long... longs) {

            while(!isCancelled()){
                specifications = new Specifications(
                        (int)(Math.random() * 10),
                        (int)(Math.random() * 10),
                        (int)(Math.random() * 10),
                        (int)(Math.random() * 10),
                        (int)(Math.random() * 10),
                        (int)(Math.random() * 10)
                );
                publishProgress(specifications);
                try{
                    TimeUnit.MILLISECONDS.sleep(10);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            Log.d("DebugLogs", "AsyncTask: process is cancelled");
            return specifications;
        }

        @Override
        protected void onProgressUpdate(Specifications... values) {
            text_strength.setText(" Strength:      " + specifications.getStrength());
            text_agility.setText(" Agility:           " + specifications.getAgility());
            text_intelligence.setText(" Intelligence: " + specifications.getIntelligence());
            text_charisma.setText(" Charisma:     " + specifications.getCharisma());
            text_stamina.setText(" Stamina:      " + specifications.getStamina());
            text_health.setText(" Health:          " + specifications.getHealth());
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            btn.setText("Next");
            count++;
            Log.d("DebugLogs", "AsyncTask: OnCancelled: process is cancelled");
        }
    }
}
