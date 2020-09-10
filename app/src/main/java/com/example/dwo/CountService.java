package com.example.dwo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class CountService extends Service {
    private final IBinder mBinder = new CountBinder();
    private int counter = 0;
    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            counter++;
        }
    };

    public class CountBinder extends Binder {
        CountService getService() {
            return CountService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        timer.schedule(timerTask, 1000, 10000);
        return mBinder;
    }

    public int getCounter() {
        return counter;
    }

    public void plusCounter(){
        counter = counter + 10;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        timer.cancel();
        timer = null;
        return super.onUnbind(intent);
    }
}
