package com.launcher.dwo.General;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class GeneralStoryService extends IntentService {
    public static final String ACTION_MYINTENTSERVICE = "dwo.intentservice.RESPONSE";

    public GeneralStoryService() {
        super("my_name");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        double num = Math.random() * 10;
        int general_number = (int) num;
        assert intent != null;
        int step = intent.getIntExtra("step", 0);
        Log.d("DeveloperLogs", "general number is " + general_number);

        Intent responseIntent = new Intent();
        responseIntent.setAction(ACTION_MYINTENTSERVICE);
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT);
        responseIntent.putExtra("result", getStory(step, general_number));
        sendBroadcast(responseIntent);
    }

    public String getStory(int step, int general_number){
        String result = "THE END";
        if(step == 0){
            result = "In the dark forest you see a terrible creature with fiery red eyes, fear envelops you, dare to approach?";
        } else if(step == 1){
            switch(general_number){
                case 1: result = "It was your death, bye";
                    break;
                case 2: result = "It was some kind of rabid monster that tore you apart in a couple of seconds";
                    break;
                case 3: result = "There is nothing there and it seems that you have imagined everything";
                    break;
                case 4: result = "This one was just a cute little dog";
                    break;
                case 5: result = "You rushed to escape and miraculously survived";
                    break;
                case 6: result = "You cried and begged for mercy";
                    break;
                case 7: result = "You shat a truckload of bricks in your pants";
                    break;
                case 8: result = "You're awake. Another nightmare";
                    break;
                case 9: result = "Program is developing right now";
                    break;
            }
        }
        return result;
    }
}
