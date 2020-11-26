package com.example.dwo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import static android.view.View.GONE;

public class RollingDialog extends DialogFragment {
    private Context context;
    private String[] words;
    private int range;

    private static int general_number;
    private TextView numbers, shadow_rolling, text_roll, numbers_fake;
    private Animation numbers_anim, numbers_anim_end, circle_white, circle_alpha, cloud_text;
    private ImageView c_white, c_alpha;
    private AnimationDrawable really_circle;

    public RollingDialog(Context context, String[] words){
        this.context = context;
        this.words = words;
        this.range = words.length;
    }

    public RollingDialog(Context context, int range){
        this.context = context;
        this.range = range;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.rolling_dialog, null);
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context, R.style.Dialog_PurpleAppTheme_NoActionBar);
        mDialogBuilder.setView(promptsView);

        FirstMethod(promptsView);
        StartAnim();

        AlertDialog alertDialog = mDialogBuilder.create();
        alertDialog.show();
        alertDialog.getWindow().clearFlags( WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        View v = alertDialog.getWindow().getDecorView();
        v.setBackgroundResource(android.R.color.transparent);
        return alertDialog;
    }

    private void FirstMethod(View promptsView) {
        numbers = promptsView.findViewById(R.id.start_number1);
        numbers_fake = promptsView.findViewById(R.id.start_number_fake1);
        numbers_fake.setBackgroundResource(R.drawable.really_circle_animation);
        numbers_anim = AnimationUtils.loadAnimation(context, R.anim.number_down);
        numbers_anim_end = AnimationUtils.loadAnimation(context, R.anim.number_down_down);
        really_circle = (AnimationDrawable) numbers_fake.getBackground();
        circle_alpha = AnimationUtils.loadAnimation(context, R.anim.circle_alpha);
        circle_white = AnimationUtils.loadAnimation(context, R.anim.circle_white);
        cloud_text = AnimationUtils.loadAnimation(context, R.anim.cloud_text);
        shadow_rolling = promptsView.findViewById(R.id.shadow_rolling1);
        text_roll = promptsView.findViewById(R.id.roll_text1);
        c_alpha = promptsView.findViewById(R.id.circe_alpha1);
        c_white = promptsView.findViewById(R.id.circle_white1);
        if(words == null)
            text_roll.setVisibility(GONE);
    }

    public void StartAnim(){
        final int[] i = {50};
        final String[] colors = {"#0033CC", "#006699", "#009933", "#CC9900", "#CC0000", "#CC0099", "#6600FF", "#990099", "#339966", "#990000"};
        numbers.startAnimation(numbers_anim);
        numbers_anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(i[0] != 0) {
                    numbers.startAnimation(numbers_anim);
                    double num = (double) Math.random() * range;
                    general_number = (int) num;
                    String s = String.valueOf(general_number);
                    numbers.setText(s);
                    shadow_rolling.setTextColor(Color.parseColor(colors[general_number]));
                    if(words != null){
                        text_roll.setText(words[general_number]);
                    } else{
                        text_roll.setVisibility(GONE);
                    }
                    i[0]--;
                } else{
                    numbers.startAnimation(numbers_anim_end);
                    numbers_anim_end.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            c_white.startAnimation(circle_white);
                            c_alpha.startAnimation(circle_alpha);
                            really_circle.start();
                            numbers_fake.setVisibility(View.VISIBLE);
                            circle_white.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    c_white.setVisibility(View.INVISIBLE);
                                    really_circle.stop();
                                    numbers_fake.setVisibility(GONE);

                                    if(words!= null) {
                                        text_roll.setVisibility(View.VISIBLE);
                                        text_roll.startAnimation(cloud_text);
                                        cloud_text.setAnimationListener(new Animation.AnimationListener() {
                                            @Override
                                            public void onAnimationStart(Animation animation) {

                                            }

                                            @Override
                                            public void onAnimationEnd(Animation animation) {
                                                dismiss();
                                            }

                                            @Override
                                            public void onAnimationRepeat(Animation animation) {

                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
