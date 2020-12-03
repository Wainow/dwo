package com.example.dwo;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.TransitionRes;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;

import static com.example.dwo.RoomActivity.observer_room;
import static com.example.dwo.RoomVillainsFragment.observer;

public class ShowHeroDialog extends DialogFragment {
    private Hero hero;
    private int position;
    private Observable<TreeMap<Integer, Hero>> observable;
    private TreeMap<Integer, Hero> treeMap = new TreeMap<>();
    private HeroViewModel model;

    private TextViewPlus showName;
    private CircleImageView heroImage;
    private EditTextPlus showMoney;
    private EditTextPlus showInventory;
    private EditTextPlus showStory;

    private TextView textStrength;
    private TextView textAgility;
    private TextView textIntelligence;
    private TextView textCharisma;
    private TextView textStamina;
    private TextView textHealth;

    public ShowHeroDialog(){
    }

    public static ShowHeroDialog newInstance(Application application, Hero hero, int position){
        HeroViewModel model = new HeroViewModel(application);
        model.updateHero(position, hero);
        return new ShowHeroDialog();
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //New code
        model = ViewModelProviders.of(this).get(HeroViewModel.class);
        LiveData<TreeMap<Integer, Hero>> data = model.getData();
        data.observe(this, new Observer<TreeMap<Integer, Hero>>() {
            @Override
            public void onChanged(TreeMap<Integer, Hero> hero1) {
                Log.d("DebugLogs", "ShowHeroDialog: onChanged");
                treeMap = hero1;
                //position = treeMap.firstKey();
                //hero = treeMap.get(position);
            }
        });

        treeMap = data.getValue();
        position = treeMap.firstKey();
        hero = treeMap.get(position);

        Log.d("DebugLogs", "ShowHeroDialog: hero" + hero.toString());

        LayoutInflater li = LayoutInflater.from(RoomActivity.getRoomActivityContext());
        View promptsView = li.inflate(R.layout.show_hero_dialog, null);
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(RoomActivity.getRoomActivityContext(), R.style.Dialog_PurpleAppTheme_NoActionBar);
        mDialogBuilder.setView(promptsView);

        showName = promptsView.findViewById(R.id.show_name);
        showMoney = promptsView.findViewById(R.id.show_money);
        showInventory = promptsView.findViewById(R.id.show_inventory);
        showStory = promptsView.findViewById(R.id.show_story);
        heroImage = promptsView.findViewById(R.id.hero_image);
        textAgility = promptsView.findViewById(R.id.show_text_agility);
        textCharisma = promptsView.findViewById(R.id.show_text_charisma);
        textHealth = promptsView.findViewById(R.id.show_text_health);
        textIntelligence = promptsView.findViewById(R.id.show_text_intelligence);
        textStrength = promptsView.findViewById(R.id.show_text_strength);
        textStamina = promptsView.findViewById(R.id.show_text_stamina);

        showName.setText(hero.getName() + " [" + hero.getRole() + "]");
        showMoney.setText(String.valueOf(hero.getMoney()));
        showInventory.setText(hero.getInventory());
        if(hero.getStory().equals(""))
            showStory.setVisibility(View.GONE);
        showStory.setText(hero.getStory());
        if(!hero.isDownloaded())
            Glide.with(model.getContext()).load(hero.getResID()).into(heroImage);
        else
            Glide.with(model.getContext()).load(hero.getUriResID()).into(heroImage);
        textAgility.setText("Agility: " + hero.getSpecifications().getAgility());
        textStamina.setText("Stamina: " + hero.getSpecifications().getStamina());
        textStrength.setText("Strength: " + hero.getSpecifications().getStrength());
        textIntelligence.setText("Intelligence: " + hero.getSpecifications().getIntelligence());
        textHealth.setText("Health: " + hero.getSpecifications().getHealth());
        textCharisma.setText("Charisma: " + hero.getSpecifications().getCharisma());

        AlertDialog alertDialog = mDialogBuilder.create();
        alertDialog.show();
        alertDialog.getWindow().clearFlags( WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return alertDialog;
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d("DebugLogs", "ShowHeroDialog: Dialog is cancel");
        Log.d("DebugLogs", "ShowHeroDialog: Inventory: " + showInventory.getText().toString());
        this.hero.setMoney(Integer.parseInt(showMoney.getText().toString()));
        this.hero.setInventory(showInventory.getText().toString());
        this.hero.setStory(showStory.getText().toString());
        treeMap.put(position, this.hero);
        observable = Observable.fromArray(treeMap);
        observable.subscribe(observer_room);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d("DebugLogs", "ShowHeroDialog: Dialog is dismiss");
    }
}
