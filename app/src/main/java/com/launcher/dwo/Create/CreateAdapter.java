package com.launcher.dwo.Create;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.launcher.dwo.Custom.FileWorker;
import com.launcher.dwo.Generator.ItemGenerator;
import com.launcher.dwo.Generator.StoryGenerator;
import com.launcher.dwo.Hero.Hero;
import com.launcher.dwo.R;
import com.launcher.dwo.Specifications;
import com.launcher.dwo.Villain.VillainSharedPreferences;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.view.View.VISIBLE;

public class CreateAdapter extends PagerAdapter {
    private Context context;
    private int resId = 0;
    private ViewPager pager;
    private int count = 0;
    private CreateClassDataAdapter mAdapter;
    private boolean isEvil;

    private Button btn;
    private TextView text_strength;
    private TextView text_agility;
    private TextView text_intelligence;
    private TextView text_charisma;
    private TextView text_stamina;
    private TextView text_health;
    public CircleImageView imageView;

    private Specifications specifications;
    private Hero hero;
    private String json;
    private int RoomID;
    private int resID;
    private Uri uriResID;
    private boolean isDownloaded = false;
    private SampleTask mSampleTask;
    private boolean isError = false;
    private TreeMap<Integer, Boolean> map;
    private CreateHeroViewModel model;
    private StoryGenerator generator_story;
    private ItemGenerator generator_item;

    private VillainSharedPreferences preferencesHelper;


    public CreateAdapter(CreateHeroViewModel model, Context context, ViewPager pager, int RoomID, boolean isEvil) {
        this.context = context;
        this.pager = pager;
        this.RoomID = RoomID;
        this.isEvil = isEvil;
        this.model = model;
        Log.d("DebugLogs", "CreateAdapter: RoomID: " + RoomID);
    }

    public CreateClassDataAdapter getmAdapter() {
        return mAdapter;
    }

    @SuppressLint("SetTextI18n")
    public Object instantiateItem(final ViewGroup collection, int position) {

        LayoutInflater inflater = LayoutInflater.from(this.context);
        switch (position) {
            case 0:
                resId = R.layout.fragment_class_create;
                break;
            case 1:
                resId = R.layout.fragment_specification_create;
                break;
            case 2:
                resId = R.layout.fragment_info_additional_create;
        }

        final ViewGroup layout = (ViewGroup) inflater.inflate(resId, collection, false);
        /** ------------------------------------------ Creating Hero Class -------------------------------------------------- */
        if(resId == R.layout.fragment_class_create) {
            GridView g = layout.findViewById(R.id.gridView);
            mAdapter = new CreateClassDataAdapter(model, layout.getContext(), pager, isEvil);
            g.setAdapter(mAdapter);
            Log.d("DebugLogs", "CreateClassFragment: GridView created");
        }
        /** ------------------------------------------ Creating Hero Specifications ------------------------------------------ */
        else if(resId == R.layout.fragment_specification_create){
            mSampleTask = new SampleTask();
            count = 0;
            btn = layout.findViewById(R.id.btn_second);
            text_strength = layout.findViewById(R.id.text_strength);
            text_agility = layout.findViewById(R.id.text_agility);
            text_intelligence = layout.findViewById(R.id.text_intelligence);
            text_charisma = layout.findViewById(R.id.text_charisma);
            text_stamina = layout.findViewById(R.id.text_stamina);
            text_health = layout.findViewById(R.id.text_health);

            specifications = model.getData().getValue().get(model.getData().getValue().firstKey()).getSpecifications();

            if(!specifications.isNull()) {
                text_strength.setText(" Strength:      " + specifications.getStrength());
                text_agility.setText(" Agility:           " + specifications.getAgility());
                text_intelligence.setText(" Intelligence: " + specifications.getIntelligence());
                text_charisma.setText(" Charisma:     " + specifications.getCharisma());
                text_stamina.setText(" Stamina:      " + specifications.getStamina());
                text_health.setText(" Health:          " + specifications.getHealth());
                btn.setText("Next");
                count = 2;
            } else
                count = 0;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(count == 0) {
                        mSampleTask.execute();
                    } else if(count == 1){
                        mSampleTask.cancel(true);
                        hero = model.getData().getValue().get(model.getData().getValue().firstKey());
                        hero.setSpecifications(specifications);
                        model.updateHero(RoomID, hero);
                        Log.d("DebugLogs", "CreateSpecificationFragment: CreateViewModel: hero: " + model.getData().getValue().get(model.getData().getValue().firstKey()));
                    } else if(count == 2){
                        pager.setCurrentItem(2);
                        setRoleImage();
                    }
                }
            });
        }
        /** ------------------------------------------ Creating additional information about hero --------------------------------- */
        else{
            imageView = layout.findViewById(R.id.create_circle_image);
            setRoleImage();
            hero = model.getData().getValue().get(model.getData().getValue().firstKey());
            final EditText editName = layout.findViewById(R.id.create_name);
            final EditText editStory = layout.findViewById(R.id.create_story);
            final EditText editMoney = layout.findViewById(R.id.create_money);
            FloatingActionButton fab = layout.findViewById(R.id.create_fab);
            final FloatingActionButton generate_fab = layout.findViewById(R.id.generate_fab);
            generate_fab.setVisibility(View.GONE);

            editName.setText(hero.getName());
            if(!hero.getStory().equals("..."))
                editStory.setText(hero.getStory());
            editMoney.setText(String.valueOf(hero.getMoney()));

            if(editMoney.getText().toString().equals("0") && editStory.getText().toString().equals("")){
                generate_fab.setVisibility(VISIBLE);
            }
            generate_fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isEvil){
                        generator_story = new StoryGenerator();
                    } else{
                        generator_story = new StoryGenerator(hero.getRoleInt());
                    }
                    editStory.setText(generator_story.GenerateStory());
                    editMoney.setText(String.valueOf((int) (Math.random() * 5000)));

                    hero.setStory(editStory.getText().toString());
                    hero.setMoney(Integer.parseInt(editMoney.getText().toString()));
                    model.updateHero(RoomID, hero);
                    generate_fab.setVisibility(View.GONE);
                }
            });

            editName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d("DebugLogs", "CreateAdditionalInfoFragment: editStory: onTextChanged");
                    hero = model.getData().getValue().get(model.getData().getValue().firstKey());
                    hero.setName(editName.getText().toString());
                    model.updateHero(RoomID, hero);
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
            editStory.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d("DebugLogs", "CreateAdditionalInfoFragment: editStory: onTextChanged");
                    hero = model.getData().getValue().get(model.getData().getValue().firstKey());
                    hero.setStory(editStory.getText().toString());
                    model.updateHero(RoomID, hero);
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
            editMoney.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d("DebugLogs", "CreateAdditionalInfoFragment: editStory: beforeTextChanged");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d("DebugLogs", "CreateAdditionalInfoFragment: editStory: onTextChanged");
                    hero = model.getData().getValue().get(model.getData().getValue().firstKey());
                    if(!editMoney.getText().toString().equals("")) {
                        hero.setMoney(Integer.parseInt(editMoney.getText().toString()));
                    }
                    model.updateHero(RoomID, hero);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d("DebugLogs", "CreateAdditionalInfoFragment: editStory: afterTextChanged");
                }
            });

            final Intent intentAddHeroService = new Intent(layout.getContext(), CreateHeroService.class);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("DebugLogs", "CreateAdditionalInfoFragment: Click!");
                    hero = model.getData().getValue().get(model.getData().getValue().firstKey());
                    if(hero.isEvil())
                        generator_item = new ItemGenerator();
                    else
                        generator_item = new ItemGenerator(hero.getRoleInt());
                    try {
                        if(!isDownloaded) {
                            hero = new Hero(
                                    model.getData().getValue().get(model.getData().getValue().firstKey()).getName(), //CreateDialog.getName()
                                    model.getData().getValue().get(model.getData().getValue().firstKey()).getRole(), //old: mAdapter.getRole();
                                    model.getData().getValue().get(model.getData().getValue().firstKey()).getSpecifications(), //old: specifications;
                                    generator_item.Generate(),//generator_item.Generate(),
                                    model.getData().getValue().get(model.getData().getValue().firstKey()).getStory(), //CreateDialog.getStory();
                                    model.getData().getValue().get(model.getData().getValue().firstKey()).getMoney() //CreateDialog.getRole();
                            );
                        } else{
                            hero = new Hero(
                                    model.getData().getValue().get(model.getData().getValue().firstKey()).getName(),
                                    model.getData().getValue().get(model.getData().getValue().firstKey()).getSpecifications(), //old: specifications;
                                    generator_item.Generate(),//generator_item.Generate(),
                                    model.getData().getValue().get(model.getData().getValue().firstKey()).getStory(),
                                    model.getData().getValue().get(model.getData().getValue().firstKey()).getMoney(),
                                    uriResID
                            );
                        }
                        model.updateHero(RoomID, hero);
                        if (!isEvil) {
                            json = new Gson().toJson(hero);
                            Log.d("DebugLogs", "CreateAdditionalInfoFragment: " + json);
                            FileWorker fileWorker = new FileWorker(layout.getContext());
                            Log.d("DebugLogs", "CreateAdditionalInfoFragment: RoomID: " + String.valueOf(RoomID));
                            fileWorker.writeFile(String.valueOf(RoomID), json);
                            layout.getContext().startService(intentAddHeroService);
                        } else {
                            Log.d("DebugLogs", "CreateAdditionalInfoFragment: RoomID: " + String.valueOf(RoomID));
                            preferencesHelper = new VillainSharedPreferences(context, RoomID);
                            try {
                                preferencesHelper.addVillain(model.getData().getValue().get(model.getData().getValue().firstKey()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (java.lang.NumberFormatException e){
                        Toast.makeText(layout.getContext(), "Not enough money", Toast.LENGTH_LONG).show();
                    } catch (java.lang.NullPointerException e){
                        Toast.makeText(context, "You can't create heroes after resize, reload dialog page please...", Toast.LENGTH_LONG).show();
                        layout.getContext().startService(intentAddHeroService);
                    }
                    mSampleTask.cancel(true);
                    model.updateHero(RoomID, null);
                }
            });
        }
        collection.addView(layout);
        return layout;
    }

    public void setRoleImage() {
        try { // old: mAdapter.getRole()
            switch (model.getData().getValue().get(model.getData().getValue().firstKey()).getRoleInt()) {
                case 1:
                    resID = R.drawable.mini_knight;
                    break;
                case 2:
                    resID = R.drawable.mini_mag;
                    break;
                case 3:
                    resID = R.drawable.mini_row;
                    break;
                case 4:
                    resID = R.drawable.mini_thief;
                    break;
                case 5:
                    resID = R.drawable.mini_evil1;
                    break;
                case 6:
                    resID = R.drawable.mini_evil2;
                    break;
                case 7:
                    resID = R.drawable.mini_evil3;
                    break;
                case 8:
                    resID = R.drawable.mini_evil4;
                    break;
                case 9:
                    resID = R.drawable.mini_evil6;
                    break;
                case 10:
                    if (CreateDialog.photoUri != null) {
                        Log.d("DebugLogs", "CreateAdapter: photoUri is not null");
                        //imageView.setImageURI(CreateDialog.photoUri);
                        uriResID = Uri.parse(CreateDialog.photoUri.toString());
                        Log.d("DebugLogs", "CreateAdapter: Uri Path: " + CreateDialog.photoUri.toString());
                        isDownloaded = true;
                    } else {
                        Log.d("DebugLogs", "CreateAdapter: photoUri is null");
                        resID = R.drawable.mini_download;
                    }
                    break;
                default:
                    resID = R.drawable.mini_q;
                    break;
            }
            if (!isDownloaded)
                imageView.setImageResource(resID);
            else
                imageView.setImageURI(uriResID);
        } catch (java.lang.NullPointerException e){
            Toast.makeText(context, "Your hero/villain has been deleted, please recreate hero/villain", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public Hero getHero() {
        return hero;
    }

    /** ------------------------------------------ Inner class for counting specifications ------------------------------------------ */

    class SampleTask extends AsyncTask<Long, Specifications, Specifications> {

        @Override
        protected void onPreExecute() {
            btn.setText("Stop");
            count++;
        }

        @Override
        protected Specifications doInBackground(Long... longs) {

            while(!isCancelled()){
                specifications = new Specifications(
                        (int) (Math.random() * 10),
                        (int) (Math.random() * 10),
                        (int) (Math.random() * 10),
                        (int) (Math.random() * 10),
                        (int) (Math.random() * 10),
                        (int) (Math.random() * 10)
                );
                //Log.d("DebugLogs", "CreateAdapter: if(!isEvil)");
                int sum = specifications.getAgility()
                        + specifications.getCharisma()
                        + specifications.getHealth()
                        + specifications.getStrength()
                        + specifications.getIntelligence()
                        + specifications.getStamina();
                while(sum != 30) {
                    //Log.d("DebugLogs", "CreateAdapter: while(sum != 30)");
                    int addTo = (int) (Math.random() * 5);
                    if (sum < 30) {
                        //Log.d("DebugLogs", "CreateAdapter: if(sum < 30)");
                        switch (addTo) {
                            case 0:
                                specifications.setAgility(specifications.getAgility() + 1);
                                break;
                            case 1:
                                specifications.setCharisma(specifications.getCharisma() + 1);
                                break;
                            case 2:
                                specifications.setHealth(specifications.getHealth() + 1);
                                break;
                            case 3:
                                specifications.setIntelligence(specifications.getIntelligence() + 1);
                                break;
                            case 4:
                                specifications.setStrength(specifications.getStrength() + 1);
                                break;
                            case 5:
                                specifications.setStamina(specifications.getStamina() + 1);
                                break;
                        }
                    } else{
                        switch (addTo) {
                            case 0:
                                specifications.setAgility(specifications.getAgility() - 1);
                                break;
                            case 1:
                                specifications.setCharisma(specifications.getCharisma() - 1);
                                break;
                            case 2:
                                specifications.setHealth(specifications.getHealth() - 1);
                                break;
                            case 3:
                                specifications.setIntelligence(specifications.getIntelligence() - 1);
                                break;
                            case 4:
                                specifications.setStrength(specifications.getStrength() - 1);
                                break;
                            case 5:
                                specifications.setStamina(specifications.getStamina() - 1);
                                break;
                        }
                    }
                    sum = specifications.getAgility()
                            + specifications.getCharisma()
                            + specifications.getHealth()
                            + specifications.getStrength()
                            + specifications.getIntelligence()
                            + specifications.getStamina();
                }
                if(!isEvil){
                    switch (mAdapter.getRole()){
                        case 1:
                            specifications.setStrength(
                                    specifications.getStrength() + 1
                            );
                            specifications.setHealth(
                                    specifications.getHealth() + 1
                            );
                            specifications.setIntelligence(
                                    specifications.getIntelligence() - 1
                            );
                            specifications.setAgility(
                                    specifications.getAgility() - 1
                            );
                            break;
                        case 4:
                            specifications.setStrength(
                                    specifications.getStrength() - 1
                            );
                            specifications.setStamina(
                                    specifications.getStamina() + 1
                            );
                            specifications.setHealth(
                                    specifications.getHealth() - 1
                            );
                            specifications.setIntelligence(
                                    specifications.getIntelligence() - 1
                            );
                            specifications.setAgility(
                                    specifications.getAgility() + 1
                            );
                            specifications.setCharisma(
                                    specifications.getCharisma() + 1
                            );
                            break;
                        case 3:
                            specifications.setStrength(
                                    specifications.getStrength() - 1
                            );
                            specifications.setStamina(
                                    specifications.getStamina() + 1
                            );
                            specifications.setIntelligence(
                                    specifications.getIntelligence() + 1
                            );
                            specifications.setCharisma(
                                    specifications.getCharisma() - 1
                            );
                            break;
                        case 2:
                            specifications.setStrength(
                                    specifications.getStrength() - 1
                            );
                            specifications.setStamina(
                                    specifications.getStamina() + 1
                            );
                            specifications.setIntelligence(
                                    specifications.getIntelligence() + 1
                            );
                            specifications.setAgility(
                                    specifications.getAgility() - 1
                            );
                            break;
                        default:
                            break;
                    }
                }
                publishProgress(specifications);
                try{
                    TimeUnit.MILLISECONDS.sleep(10);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            Log.d("DebugLogs", "AsyncTask: process is cancelled");
            return specifications;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onProgressUpdate(Specifications... values) {
            text_strength.setText(" Strength:      " + specifications.getStrength());
            text_agility.setText(" Agility:           " + specifications.getAgility());
            text_intelligence.setText(" Intelligence: " + specifications.getIntelligence());
            text_charisma.setText(" Charisma:     " + specifications.getCharisma());
            text_stamina.setText(" Stamina:      " + specifications.getStamina());
            text_health.setText(" Health:          " + specifications.getHealth());
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            btn.setText("Next");
            count++;
            Log.d("DebugLogs", "AsyncTask: OnCancelled: process is cancelled");
        }
    }
}
