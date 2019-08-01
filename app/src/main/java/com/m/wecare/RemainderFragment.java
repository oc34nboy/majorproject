package com.m.wecare;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.m.wecare.remainder.Database.DBHandler;
import com.m.wecare.remainder.Database.MedicineModel;
import com.m.wecare.remainder.Database.TimeModel;
import com.m.wecare.remainder.RecycleAdapter;
import com.m.wecare.remainder.pillAdd;
import com.m.wecare.remainder.pillAddV2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RemainderFragment extends Fragment {


    private TextView addMorning,addAfternoon,addEvening;


    private ArrayList<Integer> imageId=new ArrayList<>();
    //morning
    private ArrayList<String> MmedicineName=new ArrayList<>();
    private ArrayList<String> MmedicineDosage=new ArrayList<>();
    private ArrayList<String> MmedicineType=new ArrayList<>();
    private ArrayList<String> MmedicineTime=new ArrayList<>();
//afternoon
    private ArrayList<String> AmedicineName=new ArrayList<>();
    private ArrayList<String> AmedicineDosage=new ArrayList<>();
    private ArrayList<String> AmedicineType=new ArrayList<>();
    private ArrayList<String> AmedicineTime=new ArrayList<>();
    //evening
    private ArrayList<String> EmedicineName=new ArrayList<>();
    private ArrayList<String> EmedicineDosage=new ArrayList<>();
    private ArrayList<String> EmedicineType=new ArrayList<>();
    private ArrayList<String> EmedicineTime=new ArrayList<>();
    FloatingActionButton addBtn;
    private MediaPlayer mMediaPlayer;
    TextView noDataMessage;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        System.out.println("remainder fragement");
        v=inflater.inflate(R.layout.pill_home,container,false);
        setRetainInstance(false);
        //add btn
        noDataMessage=v.findViewById(R.id.noDataMessage);

        addMorning=v.findViewById(R.id.addMorning);
        addAfternoon=v.findViewById(R.id.addAfternoon);
        addEvening=v.findViewById(R.id.addEvening);

        addMorning.setOnClickListener(addMorningHandler);
        addAfternoon.setOnClickListener(addAfternoonHandler);
        addEvening.setOnClickListener(addEveningHandler);

        //recycle view
        initData();

        return  v;
    }


    private View.OnClickListener addMorningHandler=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                    addReminder(0);
        }
    };

    private View.OnClickListener addAfternoonHandler=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addReminder(1);
        }
    };


    private View.OnClickListener addEveningHandler=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addReminder(2);
        }
    };

    private void addReminder(int time){
        //opening addpill activity
        //opening input screen activity
        Intent intent=new Intent(getActivity(), pillAddV2.class);
        intent.putExtra("time",time);
        startActivity(intent);


    }


    private void initData() {

        List<MedicineModel> mn=getData();

        if(mn.isEmpty()){
            noDataMessage.setVisibility(View.VISIBLE);

        }else {
            noDataMessage.setVisibility(View.INVISIBLE);
            for (MedicineModel model : mn) {
                //checking time
                //getting hrs from time string

                Calendar cl=Calendar.getInstance();
                SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
                try {
                    cl.setTime(sdf.parse(model.getMedicineTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                int timeOfDay = cl.get(Calendar.HOUR_OF_DAY);
                int minOfDay=cl.get(Calendar.MINUTE);
                String time=timeOfDay%12 + ":" + minOfDay + " " + ((timeOfDay>=12) ? "PM" : "AM");


                if(timeOfDay >= 0 && timeOfDay < 12){
                    //morning time
                    MmedicineName.add(model.getMedicineName());
                    MmedicineDosage.add(model.getMedicineDosage());
                    MmedicineTime.add(time);
                    MmedicineType.add(model.getMedicineType());

                }else if(timeOfDay >= 12 && timeOfDay < 16){
                        //afternoon
                    AmedicineName.add(model.getMedicineName());
                    AmedicineDosage.add(model.getMedicineDosage());
                    AmedicineTime.add(time);
                    AmedicineType.add(model.getMedicineType());
                }else if(timeOfDay >= 16 && timeOfDay < 24){
                    //evening
                    EmedicineName.add(model.getMedicineName());
                    EmedicineDosage.add(model.getMedicineDosage());
                    EmedicineTime.add(time);
                    EmedicineType.add(model.getMedicineType());

                }

                if (model.getMedicineType().equals("Capsule")) {
                    imageId.add(R.drawable.capsule_1);
                } else {
                    imageId.add(R.drawable.bottle);
                }
            }
            initRecyclerView();
        }

    }

    private List<MedicineModel> getData() {

        System.out.println("reading database");

        List<MedicineModel> medicineList=new ArrayList<>();
        //getting data from dataset
        String query= "SELECT Medicinedb.id,medicineName,medicineType,medicineDosage,Timedb.medicineTime FROM Medicinedb INNER JOIN Timedb ON medicinedb.id=Timedb.medicineId";
        DBHandler dbhandler=new DBHandler(getActivity());
        SQLiteDatabase db = dbhandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MedicineModel mn=new MedicineModel();
                mn.setMedicineName(cursor.getString(cursor.getColumnIndex(MedicineModel.COLUMN_NAME)));
                mn.setMedicineDosage(cursor.getString(cursor.getColumnIndex(MedicineModel.COLUMN_DOSAGE)));
                mn.setMedicineType(cursor.getString(cursor.getColumnIndex(MedicineModel.COLUMN_TYPE)));
                mn.setMedicineTime(cursor.getString(cursor.getColumnIndex(TimeModel.COLUMN_TIME)));
                medicineList.add(mn);
            } while (cursor.moveToNext());
        }
        db.close();
        return medicineList;
    }

    private void initRecyclerView() {

        RecyclerView recyclerView = v.findViewById(R.id.recycle_view);
        RecycleAdapter adapter = new RecycleAdapter(getContext(),imageId,MmedicineName,MmedicineDosage,MmedicineType,MmedicineTime);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        RecyclerView recyclerView1 = v.findViewById(R.id.recycle_view2);
        RecycleAdapter adapter1 = new RecycleAdapter(getContext(),imageId,AmedicineName,AmedicineDosage,AmedicineType,AmedicineTime);
        recyclerView1.setAdapter(adapter1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));

        RecyclerView recyclerView3 = v.findViewById(R.id.recycle_view3);
        RecycleAdapter adapter3 = new RecycleAdapter(getContext(),imageId,EmedicineName,EmedicineDosage,EmedicineType,EmedicineTime);
        recyclerView3.setAdapter(adapter3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
