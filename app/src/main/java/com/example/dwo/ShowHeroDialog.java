package com.example.dwo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShowHeroDialog extends DialogFragment {
    private Context context;
    private Hero hero;

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


    public ShowHeroDialog(Context context, Hero hero){
        this.context = context;
        this.hero = hero;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.show_hero_dialog, null);
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context, R.style.Dialog_PurpleAppTheme_NoActionBar);
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
        if(hero.getInventory().equals(""))
        showInventory.setText(hero.getInventory());
        if(hero.getStory().equals(""))
            showStory.setVisibility(View.GONE);
        showStory.setText(hero.getStory());
        heroImage.setImageResource(hero.getResID());
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
}
