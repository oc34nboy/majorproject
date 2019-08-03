package com.m.wecare.remainder.Database;

import com.m.wecare.R;

import java.util.ArrayList;
import java.util.List;

public class MedicineModel {

    public static final String TABLE_NAME = "Medicinedb";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "medicineName";
    public static final String COLUMN_TYPE = "medicineType";
    public static final String COLUMN_DOSAGE="medicineDosage";
    public static final String COLUMN_TIME="medicineTime";
    public static final String COLUMN_ICON="iconType";
    public static final String COLUMN_NOTE="medicineNote";

    public static final int[] icon_list={R.drawable.capsule_img01,R.drawable.capsule_img02,R.drawable.capsule_img03,R.drawable.pill_img01,R.drawable.pill_img02,R.drawable.bottle_img01,R.drawable.bottle_img02};

    //public static final String API_URL="http://10.0.2.2:5000"; //local
    public static final String API_URL="https://lapzap98.pythonanywhere.com"; //web

    private int id;
    private String medicineName;
    private String medicineType;
    private String medicineDosage;
    private String medicineTime;
    private int iconType;
    private String medicineNote;
    private List<String> time=new ArrayList<>();


    public static final String CREATE_TABLE=
            "CREATE TABLE " + TABLE_NAME +"("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME+ " VARCHAR(100),"
                    + COLUMN_DOSAGE +" VARCHAR(50),"
                    + COLUMN_ICON + " INTEGER ,"
                    + COLUMN_NOTE + " VARCHAR(50)"
                    + ")";



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public String getMedicineDosage() {
        return medicineDosage;
    }

    public void setMedicineDosage(String medicineDosage) {
        this.medicineDosage = medicineDosage;
    }

    public String getMedicineTime() {
        return medicineTime;
    }

    public void setMedicineTime(String medicineTime) {
        this.medicineTime = medicineTime;
    }
    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public int getIconType() {
        return iconType;
    }

    public void setIconType(int iconType) {
        this.iconType = iconType;
    }

    public String getMedicineNote() {
        return medicineNote;
    }

    public void setMedicineNote(String medicineNote) {
        this.medicineNote = medicineNote;
    }
}
