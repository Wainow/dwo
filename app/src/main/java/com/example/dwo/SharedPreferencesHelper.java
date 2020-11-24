package com.example.dwo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

import static com.example.dwo.RoomVillainsFragment.observer;

public class SharedPreferencesHelper {
    public static String USERS_KEY;
    public static final String SHARED_PREF_NAME = "SHARED_PREF_NAME";
    private SharedPreferences mSharedPreferences;
    private Moshi moshi = new Moshi.Builder().build();
    public static final Type VILLAINS_TYPE = new TypeToken<List<Hero>>(){}.getType();
    private JsonAdapter<List<Hero>> jsonAdapter = moshi.adapter(VILLAINS_TYPE);
    private List<Hero> villains = new ArrayList<>();

    private Observable<List<Hero>> observable;

    public SharedPreferencesHelper(Context context, String RoomID){
        USERS_KEY = RoomID;
        mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferencesHelper(Context context, int RoomID){
        USERS_KEY = String.valueOf(RoomID);
        mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public List<Hero> getVillains(){
        Log.d("DebugLogs", "SharedPreferences: json: " + mSharedPreferences.getString(USERS_KEY, USERS_KEY));
        try {
            villains = jsonAdapter.fromJson(mSharedPreferences.getString(USERS_KEY, USERS_KEY));
        } catch (JsonDataException | IOException e){
            Log.d("DebugLogs", "SharedPreferences: List is null");
        }
        return villains;
    }

    public boolean addVillain(Hero villain) throws IOException {
        if(getVillains() == null) {
            villains = new ArrayList<>();
        } else{
            villains = getVillains();
            //villains.remove(villains.size() - 1);
            villains.add(villain);
        }
        mSharedPreferences.edit().putString(USERS_KEY, jsonAdapter.toJson(villains)).apply();
        Log.d("DebugLogs", "SharedPreferences: json: " + mSharedPreferences.getString(USERS_KEY, USERS_KEY));
        sendVillains();
        return true;
    }

    public void sendVillains(){
        if(getVillains() == null) {
            villains = new ArrayList<>();
        } else{
            villains = getVillains();
        }
        villains.add(new Hero());
        observable = Observable.fromArray(villains);
        observable.subscribe(observer);
    }

    public void deleteVillains(){
        mSharedPreferences.edit().remove(USERS_KEY).apply();
    }

    public void deleteOneVillain(int position){
        villains = getVillains();
        villains.remove(position);
        mSharedPreferences.edit().putString(USERS_KEY, jsonAdapter.toJson(villains)).apply();
        Log.d("DebugLogs", "SharedPreferences: json: " + mSharedPreferences.getString(USERS_KEY, USERS_KEY));
        sendVillains();
    }
}