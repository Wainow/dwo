package com.example.dwo;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import javax.xml.transform.Result;

public class MyIntentService extends IntentService {
    public static final String ACTION_MYINTENTSERVICE = "dwo.intentservice.RESPONSE";
    private int general_number;
    private int step;

    public MyIntentService() {
        super("my_name");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        double num = (double) Math.random() * 10;
        general_number = (int) num;
        step = intent.getIntExtra("step", 0);
        Log.d("DeveloperLogs", "general number is " + String.valueOf(general_number));

        Intent responseIntent = new Intent();
        responseIntent.setAction(ACTION_MYINTENTSERVICE);
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT);
        responseIntent.putExtra("result", getStory(step, general_number));
        sendBroadcast(responseIntent);
    }

    public String getStory(int step, int general_number){
        String result = "No story";
        if(step == 0){
            result = "In the dark forest you see a terrible creature with fiery red eyes, fear envelops you, dare to approach?";
        } else if(step == 1){
            switch(general_number){
                case 0: result = "It was your death, bye";
                    break;
                case 1: result = "It was some kind of rabid monster that tore you apart in a couple of seconds";
                    break;
                case 2: result = "It was some kind of rabid monster that tore you apart in a couple of seconds";
                    break;
                case 3: result = "It was some kind of rabid monster that tore you apart in a couple of seconds";
                    break;
                case 4: result = "It was some kind of rabid monster that tore you apart in a couple of seconds";
                    break;
                case 5: result = "It was some kind of rabid monster that tore you apart in a couple of seconds";
                    break;
                case 6: result = "It was some kind of rabid monster that tore you apart in a couple of seconds";
                    break;
                case 7: result = "It was some kind of rabid monster that tore you apart in a couple of seconds";
                    break;
                case 8: result = "It was some kind of rabid monster that tore you apart in a couple of seconds";
                    break;
                case 9: result = "It was some kind of rabid monster that tore you apart in a couple of seconds";
                    break;
            }
        }
        return result;
    }
}
