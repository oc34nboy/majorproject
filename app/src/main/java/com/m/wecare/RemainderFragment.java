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

import com.m.wecare.remainder.Database.DBHandler;
import com.m.wecare.remainder.Database.MedicineModel;
import com.m.wecare.remainder.RecycleAdapter;
import com.m.wecare.remainder.pillAdd;

import java.util.ArrayList;
import java.util.List;

public class RemainderFragment extends Fragment {

    Button btnStartAlarm, btnCancelAlarm;

    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    private ArrayList<Integer> imageId=new ArrayList<>();
    private ArrayList<String> medicineName=new ArrayList<>();
    private ArrayList<String> medicineDosage=new ArrayList<>();
    private ArrayList<String> medicineType=new ArrayList<>();
    private ArrayList<String> medicineTime=new ArrayList<>();
    FloatingActionButton addBtn;
    private MediaPlayer mMediaPlayer;
    TextView noDataMessage;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        System.out.println("remainder fragement");
        getActivity().setTitle("Medicine Reminder");
        v=inflater.inflate(R.layout.pill_home,container,false);
        setRetainInstance(false);
        //add btn
        noDataMessage=v.findViewById(R.id.noDataMessage);
        addBtn =v.findViewById(R.id.pill_addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("btn clicked");

                //opening input screen activity
                Intent intent=new Intent(getActivity(), pillAdd.class);
                startActivity(intent);
            }
        });
        //recycle view
        initData();


        return  v;
    }

    private void initData() {

        List<MedicineModel> mn=getData();

        if(mn.isEmpty()){
            noDataMessage.setVisibility(View.VISIBLE);

        }else {
            noDataMessage.setVisibility(View.INVISIBLE);
            for (MedicineModel model : mn) {
                medicineName.add(model.getMedicineName());
                medicineDosage.add(model.getMedicineDosage());
                medicineTime.add(model.getMedicineTime());
                medicineType.add(model.getMedicineType());

                if (model.getMedicineType().equals("Capsule")) {
                    imageId.add(R.drawable.capsule);
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
        String query= "SELECT * FROM "+ MedicineModel.TABLE_NAME;
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
                mn.setMedicineTime(cursor.getString(cursor.getColumnIndex(MedicineModel.COLUMN_TIME)));
                medicineList.add(mn);
            } while (cursor.moveToNext());
        }
        db.close();
        return medicineList;
    }

    private void initRecyclerView() {

        RecyclerView recyclerView = v.findViewById(R.id.recycle_view);
        RecycleAdapter adapter = new RecycleAdapter(getContext(),imageId,medicineName,medicineDosage,medicineType,medicineTime);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
