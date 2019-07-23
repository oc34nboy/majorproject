package com.m.wecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Profile extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Profile is launched");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        button = (Button) findViewById(R.id.btnExamine);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Button clicked");
                openMain2Activity();


            }
        });
    }

    public void openMain2Activity(){
        System.out.println("Function clicked");
        Intent intent = new Intent(this, InputScreen.class);
        startActivity(intent);
    }
}