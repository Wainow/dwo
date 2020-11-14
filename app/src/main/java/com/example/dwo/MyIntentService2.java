package com.example.dwo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyIntentService2  extends IntentService {
    public static final String ACTION_MYINTENTSERVICE = "dwo.intentservice.GENERATE";
    private int general_number;
    private int step;

    public MyIntentService2() {
        super("my_name");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Intent responseIntent = new Intent();
        responseIntent.setAction(ACTION_MYINTENTSERVICE);
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT);
        sendBroadcast(responseIntent);
    }
}