package com.m.wecare.remainder;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.m.wecare.MainActivity;
import com.m.wecare.R;
import com.m.wecare.remainder.Database.DBHandler;
import com.m.wecare.remainder.Database.MedicineModel;
import com.m.wecare.remainder.Database.TimeModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class pillAddV2 extends AppCompatActivity {

    private static int hrs,min,icon;


    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    String[] MedicineType={"Medicine Time","Before BreakFast","After BreakFast"};
    String[] dosage={"Dosage","capsule","ml"};
    Button addMedicineBtn,cancelBtn;
    EditText medicinename,medicineDosage,addTimeBtn;
    Spinner medicinetype,pill_type,pill_icon_spinner,pill_eat_spinner;



    private List<String> medicineTimeList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pill_add_v2);
        setTitle("New MedicineModel");
        System.out.println("starting input activity");

        int time=getIntent().getIntExtra("time",0);
        System.out.println("the time going to be added  -----------"+time);

        //getting component object
        medicinename=findViewById(R.id.pill_name);
        medicineDosage=findViewById(R.id.pill_dosage);
        medicinetype=findViewById(R.id.pill_type);
        addTimeBtn=findViewById(R.id.addTimeBtn);

        addMedicineBtn=findViewById(R.id.addMedicineBtn);
        cancelBtn=findViewById(R.id.cancelBtn);


        //setting event handling
        addMedicineBtn.setOnClickListener(addMedicineBtnHandler);
        cancelBtn.setOnClickListener(cancelHandler);
        addTimeBtn.setOnClickListener(addTimeHandler);







        //Getting the instance of Spinner and applying OnItemSelectedListener on it
         pill_eat_spinner = (Spinner) findViewById(R.id.pill_eat);

//Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,MedicineType);
        adapter1.setDropDownViewResource(R.layout.spinner_item_input);
//Setting the ArrayAdapter data on the Spinner
        pill_eat_spinner.setAdapter(adapter1);
        ArrayAdapter adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dosage);
        adapter2.setDropDownViewResource(R.layout.spinner_item_input);




        pill_eat_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    ((TextView) view).setTextColor(getResources().getColor(R.color.lightGrey));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //spinner try

        ArrayList<ItemData> list=new ArrayList<>();
        list.add(new ItemData(MedicineModel.icon_list[0]));
        list.add(new ItemData(MedicineModel.icon_list[1]));
        list.add(new ItemData(MedicineModel.icon_list[2]));
        list.add(new ItemData(MedicineModel.icon_list[3]));
        list.add(new ItemData(MedicineModel.icon_list[4]));
        list.add(new ItemData(MedicineModel.icon_list[5]));
        list.add(new ItemData(MedicineModel.icon_list[6]));

         pill_icon_spinner=findViewById(R.id.pill_icon);
        SpinnerAdapter adapter=new SpinnerAdapter(this,list);
        pill_icon_spinner.setAdapter(adapter);



    }




    //event handler

    //add time handler
    private View.OnClickListener addTimeHandler=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(pillAddV2.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    //eReminderTime.setText( selectedHour + ":" + selectedMinute);
                    String am_pm = (selectedHour < 12) ? " AM" : " PM";
                    String hh=(selectedHour<10)? "0"+selectedHour:selectedHour+"";
                    String mm=(selectedMinute<10)? "0"+selectedMinute:selectedMinute+"";
                    addTimeBtn.setText(hh+":"+mm+am_pm);
                    hrs=selectedHour;
                    min=selectedMinute;
                }
            }, hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();

        }
    };
    //add medicine Handler
    private View.OnClickListener addMedicineBtnHandler=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("btn clicked");

            //getting data from ui
            String name,dosage,eatTime,pillTime;
            long icon;

            name=medicinename.getText().toString();
            dosage=medicineDosage.getText().toString();
            pillTime=addTimeBtn.getText().toString();
            eatTime=pill_eat_spinner.getSelectedItem().toString();
            icon=pill_icon_spinner.getSelectedItemId();



           //get medicine time
            System.out.println("data list");


            insertData(name,dosage,eatTime,icon,pillTime);

            //open the alarm list menu back
           // Intent intent=new Intent(pillAdd.this, RemainderFragment.class);
           // startActivity(intent);
            //starting fragement
            //first calling main activity and calling its method to start fragment

            Intent intent =new Intent(pillAddV2.this,MainActivity.class);
            intent.putExtra("EXTRA", "openFragment");
            startActivity(intent);





















        }
    };


    private void insertData(String name, String dosage, String eatTime, long icon, String time) {
        //insertint to db

        DBHandler dbhandler=new DBHandler(pillAddV2.this);

        //first getting writable databse
        SQLiteDatabase db=dbhandler.getWritableDatabase();
        //add data
        ContentValues value = new ContentValues();
        value.put(MedicineModel.COLUMN_NAME,name);
        value.put(MedicineModel.COLUMN_DOSAGE,dosage);
        value.put(MedicineModel.COLUMN_NOTE,eatTime);
        value.put(MedicineModel.COLUMN_ICON,icon);

        //inserting in db
        long id = db.insert(MedicineModel.TABLE_NAME, null, value);
        System.out.println("row inserted is "+id);

        //inserting time in

            ContentValues value2=new ContentValues();
            value2.put(TimeModel.COLUMN_MEDICINE_ID,id);
            value2.put(TimeModel.COLUMN_TIME,time);
            long id2 = db.insert(TimeModel.TABLE_NAME, null, value2);
            System.out.println("row inserted is "+id2);




        // close db connection
        db.close();
        //registering event
        System.out.println("database insertion completion");

        registerAlarm(((int) id),time);



    }

    private void registerAlarm(int id,String time) {
        System.out.println("registering alarm called");
        //setting pending intent

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(this, MyReceiver.class);
        alarmIntent.putExtra("id",id+"");
        pendingIntent = PendingIntent.getBroadcast(this, id, alarmIntent, 0);

        //start alarm

        startAlarm(hrs,min);




    }

    private void startAlarm(int hrs,int min) {
        System.out.println("start alarm called");
        //starting alarm

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hrs);
        calendar.set(Calendar.MINUTE, min);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);


    }


    //cancel btn handler

    private View.OnClickListener cancelHandler= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //go back to previous activity
        }
    };
}

