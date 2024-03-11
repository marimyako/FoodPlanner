package com.example.foodplanner.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodplanner.R;

public class MainActivity extends AppCompatActivity {

    TextView  guesttbn;

Button SignUPbtn;
Button Loginbtn;



public static Boolean isguest=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       SignUPbtn=findViewById(R.id.signuppbtn);
       Loginbtn=findViewById(R.id.loginnow);
       guesttbn=findViewById(R.id.guest);
       SignUPbtn.setOnClickListener(new View.OnClickListener() {
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
     guesttbn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
           isguest=false;
             Intent myintent=new Intent(MainActivity.this, MainScreen.class);
             startActivity(myintent);
         }
     });


    }
}