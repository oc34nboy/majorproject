package com.m.wecare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;


public class HomeScreen extends AppCompatActivity {

    Button btnProfile, btnExamine, btnEmergency, btnReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dash_board);
        System.out.println("main called");

        btnEmergency = (Button) findViewById(R.id.buttonEmergency);
        btnProfile = (Button) findViewById(R.id.buttonProfile);
        btnReminder = (Button) findViewById(R.id.buttonReminder);
        btnExamine = (Button) findViewById(R.id.buttonExamine);

        btnEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Button click");
                openMain4Activity();
            }
        });


        btnExamine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Button click");
                openMain2Activity();
            }
        });

        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Button click");
                MedicineApp();
            }
        });


    }

    public void MedicineApp() {
        System.out.println("Pill Reminder clicked");
        Intent intent = new Intent(this, Table.class);
        startActivity(intent);
    }

    public void openMain4Activity() {
        System.out.println("Emergency clicked");
        Intent intent = new Intent(this, Table.class);
        startActivity(intent);
    }


    public void openMain2Activity() {

        //calling input screeen

        System.out.println("Examine clicked");
        Intent intent = new Intent(this, InputScreen.class);
        startActivity(intent);
    }





}


