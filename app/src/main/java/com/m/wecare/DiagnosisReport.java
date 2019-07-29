package com.m.wecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.Button;
import android.widget.TextView;

public class DiagnosisReport extends AppCompatActivity {


    private Button button;
    private TextView resultText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.output);
        //getting msg passing through intetn
        Intent intent = getIntent();
        String predicatedDiseaseName = intent.getStringExtra("predicatedDiseaseName");
        resultText = (TextView) findViewById(R.id.result);
        resultText.setText(predicatedDiseaseName);



/*
        button = (Button) findViewById(R.id.btnCheckup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Button clicked");
                openMain2Activity();


            }
        });
    }
    public void  openMain2Activity(){
        System.out.println("Function clicked");
        Intent intent = new Intent(this, InputScreen.class);
        startActivity(intent);
    }*/


    }
}





