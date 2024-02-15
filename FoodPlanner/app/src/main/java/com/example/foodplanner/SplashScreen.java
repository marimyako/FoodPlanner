package com.example.foodplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {
LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        lottieAnimationView=findViewById(R.id.lottie);

        lottieAnimationView.animate().translationY(2000).setDuration(2000).setStartDelay(2900);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        },5000);
    }
}