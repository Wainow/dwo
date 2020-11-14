package com.example.dwo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.Timer;
import java.util.TimerTask;

public class MyFragmentPagerAdapter extends PagerAdapter {
    private Context context;
    private int resId = 0;
    private ViewPager pager;
    private Timer timer;
    private TimerTask timerTask;
    private MyBroadcastReceiver myBroadcastReceiver;
    private int count = 0;

    private Button btn;
    private TextView text_strength;
    private TextView text_agility;
    private TextView text_intelligence;
    private TextView text_charisma;
    private TextView text_stamina;
    private TextView text_health;



    public MyFragmentPagerAdapter(Context context, ViewPager pager) {
        this.context = context;
        this.pager = pager;
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
        }
        final ViewGroup layout = (ViewGroup) inflater.inflate(resId, collection, false);
        if(resId == R.layout.fragment_first3) {
            GridView g = layout.findViewById(R.id.gridView);
            DataAdapter mAdapter = new DataAdapter(layout.getContext(), pager);
            g.setAdapter(mAdapter);
            Log.d("DebugLogs", "First3Fragment: GridView created");
        } else {
            myBroadcastReceiver = new MyBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter(MyIntentService2.ACTION_MYINTENTSERVICE);
            intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
            layout.getContext().registerReceiver(myBroadcastReceiver, intentFilter);

            count = 0;
            btn = layout.findViewById(R.id.btn_second);
            text_strength = layout.findViewById(R.id.text_strength);
            text_agility = layout.findViewById(R.id.text_agility);
            text_intelligence = layout.findViewById(R.id.text_intelligence);
            text_charisma = layout.findViewById(R.id.text_charisma);
            text_stamina = layout.findViewById(R.id.text_stamina);
            text_health = layout.findViewById(R.id.text_health);
            final Intent intentMyIntentService = new Intent(layout.getContext(), MyIntentService2.class);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(count == 0) {
                        count++;
                        btn.setText("Stop");
                        timer = new Timer();
                        timerTask = new TimerTask() {
                            @Override
                            public void run() {
                                layout.getContext().startService(intentMyIntentService);
                            }
                        };
                        timer.schedule(timerTask, 1000, 10);
                    } else if(count == 1){
                        count++;
                        btn.setText("Next");
                        timer.cancel();
                        timer = null;
                    } else if(count == 2){
                        pager.setCurrentItem(0);
                    }
                }
            });
        }
        collection.addView(layout);
        return layout;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        container.getContext().unregisterReceiver(myBroadcastReceiver);
        count = 0;
        timer.cancel();
        timer = null;
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            text_strength.setText(" Strength:      " + (int)(Math.random() * 10));
            text_agility.setText(" Agility:           " + (int)(Math.random() * 10));
            text_intelligence.setText(" Intelligence: " + (int)(Math.random() * 10));
            text_charisma.setText(" Charisma:     " + (int)(Math.random() * 10));
            text_stamina.setText(" Stamina:      " + (int)(Math.random() * 10));
            text_health.setText(" Health:          " + (int)(Math.random() * 10));
        }
    }
}
