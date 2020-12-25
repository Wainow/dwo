package com.example.dwo.Create;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentSender;

import androidx.annotation.Nullable;

public class CreateHeroService extends IntentService {
    public static final String ACTION_MYINTENTSERVICE = "dwo.intentservice.ADD_HERO";

    public CreateHeroService() {
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
