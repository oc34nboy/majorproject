package com.m.wecare.alarm;

import android.support.annotation.NonNull;


import com.m.wecare.source.History;
import com.m.wecare.source.MedicineAlarm;
import com.m.wecare.source.MedicineDataSource;
import com.m.wecare.source.MedicineRepository;


public class ReminderPresenter implements ReminderContract.Presenter {

    private final MedicineRepository mMedicineRepository;

    private final ReminderContract.View mReminderView;

    ReminderPresenter(@NonNull MedicineRepository medicineRepository, @NonNull ReminderContract.View reminderView) {
        this.mMedicineRepository = medicineRepository;
        this.mReminderView = reminderView;

        mReminderView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void finishActivity() {
        mReminderView.onFinish();
    }

    @Override
    public void onStart(long id) {
        loadMedicineById(id);
    }

    @Override
    public void loadMedicineById(long id) {
        loadMedicine(id);
    }


    private void loadMedicine(long id) {
        mMedicineRepository.getMedicineAlarmById(id, new MedicineDataSource.GetTaskCallback() {
            @Override
            public void onTaskLoaded(MedicineAlarm medicineAlarm) {
                if (!mReminderView.isActive()) {
                    return;
                }
                if (medicineAlarm == null) {
                    return;
                }
                mReminderView.showMedicine(medicineAlarm);
            }

            @Override
            public void onDataNotAvailable() {
                mReminderView.showNoData();
            }
        });
    }

    @Override
    public void addPillsToHistory(History history) {
        mMedicineRepository.saveToHistory(history);
    }
}
