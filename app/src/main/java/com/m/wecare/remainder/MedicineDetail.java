package com.m.wecare.remainder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.m.wecare.MainActivity;
import com.m.wecare.R;
import com.m.wecare.remainder.Database.DBHandler;
import com.m.wecare.remainder.Database.MedicineModel;

public class MedicineDetail extends AppCompatActivity {

    private TextView nameText,dosageText,timeText;
    private Button deleteBtn;
    private ImageView typeImageView;
    int dataId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pill_medicine_detail);

        nameText=findViewById(R.id.title);
        dosageText=findViewById(R.id.dosage);
        timeText=findViewById(R.id.time);
        typeImageView=findViewById(R.id.type);
        deleteBtn=findViewById(R.id.deleteBtn);

        //getting data from intent
        dataId =Integer.valueOf(getIntent().getStringExtra("id"));
        String name=getIntent().getStringExtra("name");
        String dosage=getIntent().getStringExtra("dosage");
        String type=getIntent().getStringExtra("type");
        String time=getIntent().getStringExtra("time");

        //setting data to ui
        nameText.setText(name);
        dosageText.setText(dosage);
        timeText.setText(time);
        typeImageView.setImageResource(R.drawable.capsule_large);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("removeing this element"+dataId);

                //removing from intent
                Intent intent = new Intent(MedicineDetail.this, MyReceiver.class);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                PendingIntent pendingIntent=PendingIntent.getBroadcast(MedicineDetail.this,dataId,intent,0);

               alarmManager.cancel(pendingIntent);

               //removing from database also
                DBHandler dbhandler=new DBHandler(MedicineDetail.this);

                //first getting writable databse
                SQLiteDatabase db=dbhandler.getWritableDatabase();

                int rowDeleted = db.delete(MedicineModel.TABLE_NAME,MedicineModel.COLUMN_ID + " =?",
                        new String[] {String.valueOf(dataId)});
                db.close();//This is very important once database operation is done.
                if(rowDeleted != 0){
                    //delete success.
                    System.out.println("deletd");

                    Intent intent1 =new Intent(MedicineDetail.this, MainActivity.class);
                    intent1.putExtra("EXTRA", "openFragment");
                    startActivity(intent1);
                } else {
                    System.out.println("error");
                }






                //removing from database

            }
        });






    }
}
