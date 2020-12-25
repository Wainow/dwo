package com.example.dwo.Create;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dwo.Hero.Hero;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.TreeMap;

public class CreateHeroViewModel extends AndroidViewModel {
    private HeroData heroData;
    private MutableLiveData<TreeMap<Integer, Hero>> hero = new MutableLiveData<>();
    private int position;

    public CreateHeroViewModel(@NonNull Application application) {
        super(application);
        Log.d("DebugLogs", "CreateHeroViewModel: constructor");
        heroData = new HeroData(getContext());
    }

    public Context getContext() {
        return getApplication().getApplicationContext();
    }

    public Context getBaseContext(){
        return getApplication();
    }

    public MutableLiveData<TreeMap<Integer, Hero>> getData(){
        Log.d("DebugLogs", "CreateHeroViewModel: getData");
        heroData = new HeroData(getContext());
        hero.setValue(heroData.getHero());
        return hero;
    }

    public void updateHero(Integer position, Hero hero){
        Log.d("DebugLogs", "CreateHeroViewModel: updateHero");
        TreeMap<Integer, Hero> map = new TreeMap<>();
        map.put(position, hero);
        heroData.addHero(map);
        this.hero.setValue(map);
    }

    public static class HeroData {
        public static final String HERO_KEY = "CREATE_HERO_KEY";
        private SharedPreferences mSharedPreferences;
        private Context context;
        public static final String SHARED_PREF_NAME = "CREATE_HERO_DATA";
        private TreeMap<Integer, Hero> hero;

        public Context getContext() {
            return context;
        }

        private Gson gson = new Gson();
        public static final Type HERO_TYPE = new TypeToken<TreeMap<Integer, Hero>>(){}.getType();

        public HeroData(Context context){
            this.context = context;
            mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        }

        public TreeMap<Integer, Hero> getHero(){
            Log.d("DebugLogs", "CreateHeroViewModel: getHero: json: " + mSharedPreferences.getString(HERO_KEY, HERO_KEY));
            try {
                hero = gson.fromJson(mSharedPreferences.getString(HERO_KEY, HERO_KEY), HERO_TYPE);
                return hero;
            } catch (com.google.gson.JsonSyntaxException e){
                return null;
            }
        }

        public boolean addHero(TreeMap<Integer, Hero> hero){
            Log.d("DebugLogs", "HeroViewModel: AddHero: json: " + gson.toJson(hero, HERO_TYPE));
            mSharedPreferences.edit().putString(HERO_KEY, gson.toJson(hero, HERO_TYPE)).apply();
            return true;
        }
    }
}
