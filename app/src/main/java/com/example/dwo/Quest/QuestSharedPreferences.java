package com.example.dwo.Quest;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.dwo.Quest.Quest;
import com.google.gson.reflect.TypeToken;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static com.example.dwo.Quest.QuestFragment.observer;

public class QuestSharedPreferences {
    public static String USERS_KEY;
    public static final String SHARED_PREF_NAME = "SHARED_PREF_QUEST";
    private SharedPreferences mSharedPreferences;
    private Moshi moshi = new Moshi.Builder().build();
    public static final Type QUEST_TYPE = new TypeToken<List<Quest>>(){}.getType();
    private JsonAdapter<List<Quest>> jsonAdapter = moshi.adapter(QUEST_TYPE);
    private List<Quest> quests = new ArrayList<>();

    private Observable<List<Quest>> observable;

    public QuestSharedPreferences(Context context, int RoomID){
        USERS_KEY = String.valueOf(RoomID);
        mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public List<Quest> getQuests(){
        Log.d("DebugLogs", "SharedPreferences: getQuests: json: " + mSharedPreferences.getString(USERS_KEY, USERS_KEY));
        try {
            quests = jsonAdapter.fromJson(mSharedPreferences.getString(USERS_KEY, USERS_KEY));
        } catch (JsonDataException | IOException e){
            Log.d("DebugLogs", "SharedPreferences: List is null");
        }
        return quests;
    }

    public boolean addQuest(Quest quest){
        if(getQuests() == null) {
            quests = new ArrayList<>();
        } else{
            quests = getQuests();
            quests.add(quest);
        }
        mSharedPreferences.edit().putString(USERS_KEY, jsonAdapter.toJson(quests)).apply();
        Log.d("DebugLogs", "SharedPreferences: addQuest: json: " + mSharedPreferences.getString(USERS_KEY, USERS_KEY));
        sendQuests();
        return true;
    }

    public void setQuest(int index, Quest quest){
        quests = getQuests();
        quests.set(index, quest);
        mSharedPreferences.edit().putString(USERS_KEY, jsonAdapter.toJson(quests)).apply();
        Log.d("DebugLogs", "SharedPreferences: setQuest: json: " + mSharedPreferences.getString(USERS_KEY, USERS_KEY));
        sendQuests();
    }

    public void deleteOneQuest(int position){
        quests = getQuests();
        try {
            quests.remove(position);
            mSharedPreferences.edit().putString(USERS_KEY, jsonAdapter.toJson(quests)).apply();
            Log.d("DebugLogs", "SharedPreferences: deleteVillains: json: " + mSharedPreferences.getString(USERS_KEY, USERS_KEY));
            sendQuests();
        } catch (java.lang.IndexOutOfBoundsException e){
            Log.d("DebugLogs", "SharedPreferences: deleteVillains: IndexOutOfBoundsException");
        }
    }

    public void sendQuests(){
        if(getQuests() == null) {
            quests = new ArrayList<>();
        } else{
            quests = getQuests();
        }
        observable = Observable.fromArray(quests);
        observable.subscribe(observer);
    }
}
