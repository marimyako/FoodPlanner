package com.example.foodplanner.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodplanner.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainScreen extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigater_layout);
        bottomNavigationView=findViewById(R.id.bottomnavigation);

        NavController navController = Navigation.findNavController(this, R.id.homelayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (MainActivity.isguest == true) {
                    if (item.getItemId() == R.id.homeFragment) {
                        navController.navigate(R.id.homeFragment);
                    }
                    if (item.getItemId() == R.id.searchfragment) {
                        navController.navigate(R.id.searchfragment);
                    }
                    if (item.getItemId() == R.id.profileFragment) {
                        navController.navigate(R.id.profileFragment);
                    }
                    if (item.getItemId() == R.id.favfragment) {
                        navController.navigate(R.id.favfragment);
                    }
                    if (item.getItemId() == R.id.plan) {
                        navController.navigate(R.id.plan);
                    }
                }

                if(MainActivity.isguest==false) {
                    if (item.getItemId() == R.id.homeFragment) {
                        navController.navigate(R.id.homeFragment);
                    }
                    if (item.getItemId() == R.id.searchfragment) {
                        navController.navigate(R.id.searchfragment);
                    }
                    if(item.getItemId()==R.id.favfragment||item.getItemId()==R.id.plan||item.getItemId()==R.id.profileFragment)
                    {
                    showDialog();
                    }
                }
                return false;
            }
        });


        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.menu);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android. R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }else{
                drawerLayout. openDrawer (GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);
    }
    private void showDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Signup")
                .setMessage("Do you want to signup ?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.isguest=true;
                        Intent myintent=new Intent(MainScreen.this, SignUpScreen.class);
                        startActivity(myintent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}