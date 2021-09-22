package com.example.shopunlimited;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
ImageView pic;
private int SCREEN_TIME=7000;
private Animation slideAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set ids
        pic=findViewById(R.id.pic1);
        slideAnim= AnimationUtils.loadAnimation(this,R.anim.slide_animation);
        pic.setAnimation(slideAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,SigninActivity.class);
                startActivity(intent);
                finish();//restrict to come back
            }
        },SCREEN_TIME);
    }
}