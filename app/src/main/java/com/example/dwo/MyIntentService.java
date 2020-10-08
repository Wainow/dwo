package com.example.dwo;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import javax.xml.transform.Result;

public class MyIntentService extends IntentService {
    private String result;
    public static final String ACTION_MYINTENTSERVICE = "dwo.intentservice.RESPONSE";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService() {
        super("my_name");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        result = "true";

        Intent responseIntent = new Intent();
        responseIntent.setAction(ACTION_MYINTENTSERVICE);
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT);
        responseIntent.putExtra("result", result);
        sendBroadcast(responseIntent);
    }
}
