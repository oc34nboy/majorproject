package com.m.wecare;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Main Activity is launched");
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);



        if(getIntent().getStringExtra("EXTRA")!=null) {
            switch (getIntent().getStringExtra("EXTRA")) {
                case "openFragment":
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new RemainderFragment()).commit();
                    break;
            }
        }else{
            //starting new home fragement as default in starting
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }


//setting bottom navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


    }
    public  void restart(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new RemainderFragment()).commit();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.emergency:
                            selectedFragment = new EmergencyFragment();

                            break;
                        case R.id.remainder:
                            selectedFragment = new RemainderFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };



}












