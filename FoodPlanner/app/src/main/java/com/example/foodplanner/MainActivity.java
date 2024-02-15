package com.example.foodplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button Signupbtn;
Button Loginbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Signupbtn=findViewById(R.id.signupbtn);
       Loginbtn=findViewById(R.id.loginbtn);
       Signupbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
             Intent myintent=new Intent(MainActivity.this, SignUpScreen.class);
             startActivity(myintent);
           }
       });
        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent(MainActivity.this, LoginScreen.class);
                startActivity(myintent);
            }
        });


    }
}