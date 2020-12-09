package com.example.dwo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;

public class FirstFragment extends Fragment {

    private static final String TAG = "DebugLogs";
    private int step = 0;
    public TextView textView;
    private BroadcastReceiver myBroadcastReceiver;
    private int resourceId = R.drawable.bigmag;
    private String imageAddress;
    private ImageButton imageBtn;
    private RelativeLayout relative;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.textview_first);
        imageAddress = "android.resource://"  + view.getContext().getPackageName() + "/" + resourceId;
        imageBtn = view.findViewById(R.id.mag_img);
        Glide.with(view.getContext()).load(imageAddress).into(imageBtn);
        relative = view.findViewById(R.id.fragment_first);

        final Intent intentMyIntentService = new Intent(getActivity(), MyIntentService.class);

        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DebugLogs", "RoomActivity: quest:" + new QuestGenerator().GenerateQuest());
                getActivity().startService(intentMyIntentService.putExtra("step", step));
                step++;
                Log.d("DebugLogs", "step is" + String.valueOf(step));
            }
        });
        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(MyIntentService.ACTION_MYINTENTSERVICE);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        getActivity().registerReceiver(myBroadcastReceiver, intentFilter);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("result");
            textView.setText(result);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(myBroadcastReceiver);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}