package com.launcher.dwo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.launcher.dwo.General.GeneralActivity;

public class StartActivity extends AppCompatActivity {
    private static int general_number;
    private TextView numbers, shadow_rolling, text_roll, numbers_fake;
    private Animation numbers_anim, numbers_anim_end, circle_white, circle_alpha, cloud_text;
    private ImageView c_white, c_alpha;
    private AnimationDrawable really_circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        FirstMethod();
        StartAnim();
    }

    public void FirstMethod(){
        numbers = findViewById(R.id.start_number);
        numbers_fake = findViewById(R.id.start_number_fake);
        numbers_fake.setBackgroundResource(R.drawable.really_circle_animation);
        numbers_anim = AnimationUtils.loadAnimation(this, R.anim.number_down);
        numbers_anim_end = AnimationUtils.loadAnimation(this, R.anim.number_down_down);
        really_circle = (AnimationDrawable) numbers_fake.getBackground();
        circle_alpha = AnimationUtils.loadAnimation(this, R.anim.circle_alpha);
        circle_white = AnimationUtils.loadAnimation(this, R.anim.circle_white);
        cloud_text = AnimationUtils.loadAnimation(this, R.anim.cloud_text);
        shadow_rolling = findViewById(R.id.shadow_rolling);
        text_roll = findViewById(R.id.roll_text);
        c_alpha = findViewById(R.id.circe_alpha);
        c_white = findViewById(R.id.circle_white);
    }

    public void StartAnim(){
        final int[] i = {50};
        final String[] colors = {"#0033CC", "#006699", "#009933", "#CC9900", "#CC0000", "#CC0099", "#6600FF", "#990099", "#339966", "#990000"};
        numbers.startAnimation(numbers_anim);
        numbers_anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onAnimationEnd(Animation animation) {
                if(i[0] != 0) {
                    numbers.startAnimation(numbers_anim);
                    double num = Math.random() * 10;
                    general_number = (int) num;
                    String s = String.valueOf(general_number);
                    numbers.setText(s);
                    shadow_rolling.setTextColor(Color.parseColor(colors[general_number]));
                    switch(general_number){
                        case 0: text_roll.setText("The program is developing right now");
                            break;
                        case 1: text_roll.setText("Ooops, the big Ork can defeat you!");
                            break;
                        case 2: text_roll.setText("You are barely alive!");
                            break;
                        case 3: text_roll.setText("You feel something wrong");
                            break;
                        case 4: text_roll.setText("Stop! Where is your magic?");
                            break;
                        case 5: text_roll.setText("This is just a golden middle");
                            break;
                        case 6: text_roll.setText("The sword of justice is wining again");
                            break;
                        case 7: text_roll.setText("Even vampires is scared by you");
                            break;
                        case 8: text_roll.setText("Very, very big luck!!!");
                            break;
                        case 9: text_roll.setText("Oh my God what are you doing!");
                            break;
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
                                    numbers_fake.setVisibility(View.GONE);

                                    text_roll.setVisibility(View.VISIBLE);
                                    text_roll.startAnimation(cloud_text);
                                    cloud_text.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            Intent intent = new Intent(StartActivity.this, GeneralActivity.class);
                                            startActivity(intent);
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