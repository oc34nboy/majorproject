package com.m.wecare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;


public class HomeScreen extends AppCompatActivity {

    Button btnExamine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        System.out.println("main called");



        btnExamine = (Button) findViewById(R.id.buttonExamine);


        btnExamine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Button click");
                openMain2Activity();
            }
        });


    }


    public void openMain2Activity() {

        //calling input screeen

        System.out.println("disease details clicked");
        Intent intent = new Intent(this, InputScreen.class);
        startActivity(intent);


    }


}


