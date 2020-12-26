package com.launcher.dwo.Create;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.launcher.dwo.Hero.Hero;
import com.launcher.dwo.R;

import java.util.TreeMap;

public class CreateDialog extends DialogFragment {
    private GridView g;
    private ProgressBar progressBar;
    private CreateClassDataAdapter mAdapter;
    private Context context;
    private TextView textView;
    public int RoomID;
    private boolean isEvil;
    private CreateAdapter pagerAdapter;
    public static Uri photoUri;
    private Bundle args;
    private ViewPager pager;
    private CreateHeroViewModel model;
    private TreeMap<Integer, Hero> treeMap;
    private LiveData<TreeMap<Integer, Hero>> data;

    private Hero hero;

    public CreateDialog(){}

    public static CreateDialog newInstance(Context context, int RoomID, boolean isEvil){
        CreateDialog fragment = new CreateDialog();
        Bundle args = new Bundle();
        args.putInt("RoomID", RoomID);
        args.putBoolean("isEvil", isEvil);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        args = getArguments();
        RoomID = args.getInt("RoomID");
        isEvil = args.getBoolean("isEvil");
        context = getContext();

        model = ViewModelProviders.of(this).get(CreateHeroViewModel.class);
        data = model.getData();
        data.observe(this, new Observer<TreeMap<Integer, Hero>>() {
            @Override
            public void onChanged(TreeMap<Integer, Hero> hero1) {
                Log.d("DebugLogs", "HeroShowDialog: onChanged");
                treeMap = hero1;
            }
        });
        try {
            treeMap = data.getValue();
            hero = treeMap.get(treeMap.firstKey());
        } catch (java.lang.NullPointerException | java.util.NoSuchElementException e){
            hero = new Hero();
            model.updateHero(RoomID, hero);
        }

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.create_dialog, null);
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context, R.style.Dialog_PurpleAppTheme_NoActionBar);
        mDialogBuilder.setView(promptsView);

        textView = promptsView.findViewById(R.id.text_select);
        progressBar = promptsView.findViewById(R.id.progress_creating);
        progressBar.setProgress(25);
        pager = promptsView.findViewById(R.id.pager_dialog);
        pagerAdapter = new CreateAdapter(model, context, pager, RoomID, isEvil);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(0);
        if(!isEvil) {
            pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 0) {
                        progressBar.setProgress(25);
                        textView.setText("Select class");
                    } else if (position == 1) {
                        progressBar.setProgress(50);
                        textView.setText("Generate specifications");
                    } else {
                        progressBar.setProgress(75);
                        textView.setText("Tell about new Hero");
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } else{
            pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 0) {
                        progressBar.setProgress(25);
                        textView.setText("Select class");
                    } else if (position == 1) {
                        progressBar.setProgress(50);
                        textView.setText("Generate specifications");
                    } else {
                        progressBar.setProgress(75);
                        textView.setText("Tell about new Villain");
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
        AlertDialog alertDialog = mDialogBuilder.create();
        alertDialog.show();
        alertDialog.getWindow().clearFlags( WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        alertDialog.getWindow().setLayout(getDP(getContext(), 350), getDP(getContext(),350)); // 1000, 1100

        return alertDialog;
    }

    public void setProgress(int x){
        this.progressBar.setProgress(x);
    }

    @Override
    public void startActivityForResult(Intent data, int requestCode) {
        if(requestCode == CreateClassDataAdapter.REQUEST_CODE_GET_PHOTOS && data != null){
            photoUri = data.getData();
            Log.d("DebugLogs", "CreateDialog: photoUri:" + photoUri.getPath());
            pagerAdapter.setRoleImage();
        }
        super.startActivityForResult(data, requestCode);
    }

    public static int getDP(Context context, float x){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float dp = x;
        float fpixels = metrics.density * dp;
        int pixels = (int) (fpixels + 0.5f);
        return pixels;
    }
}
