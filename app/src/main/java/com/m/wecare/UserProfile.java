package com.m.wecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class UserProfile extends AppCompatActivity {

    Button nextBtn;
    TextInputEditText userName,userAge;
    RadioGroup userGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("UserProfile is launched");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        nextBtn = (Button) findViewById(R.id.btnExamine);
        userName=findViewById(R.id.userName);
        userAge=findViewById(R.id.userAge);
        userGender=findViewById(R.id.userGender);


        nextBtn.setOnClickListener(nextBtnHandler);

    }

    private View.OnClickListener nextBtnHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //checking user input
            int selectedId = userGender.getCheckedRadioButtonId();
            System.out.println("selected id"+selectedId);

            //if radio is not selected then selectd id will be -1
            String radioData="";
            if(selectedId!=-1) {
                RadioButton radioButton = findViewById(selectedId);
                radioData=radioButton.getText().toString();
            }


         if(userName.getText().toString()!="" && userAge.getText().toString()!="" && radioData!=""){

             Intent intent = new Intent(UserProfile.this, InputScreen.class);
             startActivity(intent);
         }else{
             View coordinatorLayout=findViewById(R.id.coordinatorLayout);
             Snackbar snackbar = Snackbar.make(coordinatorLayout, "ERROR ! CHECK INPUTS", Snackbar.LENGTH_LONG);
             snackbar.show();
         }



        }
    };

}