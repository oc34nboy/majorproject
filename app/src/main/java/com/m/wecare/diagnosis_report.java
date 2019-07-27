package com.m.wecare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class diagnosis_report extends AppCompatActivity {


 private ArrayList<String> diseaseName=new ArrayList<>();
 private ArrayList<String> diseaseProb=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis_report);

        System.out.println("diagnois activity open");

        getData();



    }

    private void getData() {
        diseaseName.add("first disease Name");
        diseaseProb.add("86%");
        diseaseName.add("second disease Name");
        diseaseProb.add("86%");
        diseaseName.add("second disease Name");
        diseaseProb.add("86%");
        initResult();


    }

    private void initResult() {
        System.out.println("recycle init");

        RecyclerView recyclerView=findViewById(R.id.report_recycleView);
        Report_recycleView_adapter adapter = new Report_recycleView_adapter(this,diseaseName,diseaseProb);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
