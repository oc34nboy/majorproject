package com.m.wecare.remainder.Database;

public class TimeModel {

    public static final String TABLE_NAME = "Timedb";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MEDICINE_ID = "medicineId";
    public static final String COLUMN_TIME = "medicineTime";


    private int id;
    private int medicineId;
    private String medicineTime;


    public static final String CREATE_TABLE=
            "CREATE TABLE " + TABLE_NAME +"("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_MEDICINE_ID+ " int,"
                    + COLUMN_TIME+" VARCHAR(100),"
                    + "FOREIGN KEY ("+COLUMN_MEDICINE_ID +") REFERENCES "+MedicineModel.TABLE_NAME+"("+MedicineModel.COLUMN_ID+")"
                    + ")";


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineTime() {
        return medicineTime;
    }

    public void setMedicineTime(String medicineTime) {
        this.medicineTime = medicineTime;
    }
}
