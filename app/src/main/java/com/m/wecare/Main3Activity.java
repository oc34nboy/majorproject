package com.m.wecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class Main3Activity extends AppCompatActivity {


    private Button button;
    private TextView resultText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        //getting msg passing through intetn
        Intent intent = getIntent();
        String predicatedDiseaseName = intent.getStringExtra("predicatedDiseaseName");
        resultText = (TextView) findViewById(R.id.disease);
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





