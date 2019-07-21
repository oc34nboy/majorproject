package com.m.wecare.alarm;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.m.wecare.R;

import com.m.wecare.medicine.MedicineActivity;
import com.m.wecare.source.History;
import com.m.wecare.source.MedicineAlarm;
import com.m.wecare.views.RobotoBoldTextView;
import com.m.wecare.views.RobotoRegularTextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.VIBRATOR_SERVICE;



public class ReminderFragment extends Fragment implements ReminderContract.View {

    public static final String EXTRA_ID = "extra_id";

    @BindView(R.id.tv_med_time)
    public
    RobotoBoldTextView tvMedTime;

    @BindView(R.id.tv_medicine_name)
    public
    RobotoBoldTextView tvMedicineName;

    @BindView(R.id.tv_dose_details)
    public
    RobotoRegularTextView tvDoseDetails;

    @BindView(R.id.iv_ignore_med)
    public
    ImageView ivIgnoreMed;

    @BindView(R.id.iv_take_med)
    public
    ImageView ivTakeMed;

    @BindView(R.id.linearLayout)
    public
    LinearLayout linearLayout;

    Unbinder unbinder;

    private MedicineAlarm medicineAlarm;

    private long id;

    private MediaPlayer mMediaPlayer;

    private Vibrator mVibrator;

    private ReminderContract.Presenter presenter;

    public static ReminderFragment newInstance(long id) {
        Bundle args = new Bundle();
        args.putLong(EXTRA_ID, id);
        ReminderFragment fragment = new ReminderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getLong(EXTRA_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reminder, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(ReminderContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMedicine(MedicineAlarm medicineAlarm) {
        this.medicineAlarm = medicineAlarm;
        mVibrator = (Vibrator) getContext().getSystemService(VIBRATOR_SERVICE);
        long[] pattern = {0, 1000, 10000};
        mVibrator.vibrate(pattern, 0);

        mMediaPlayer = MediaPlayer.create(getContext(), R.raw.cuco_sound);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

        tvMedTime.setText(medicineAlarm.getStringTime());
        tvMedicineName.setText(medicineAlarm.getPillName());
        tvDoseDetails.setText(medicineAlarm.getFormattedDose());
    }

    @Override
    public void showNoData() {
        //
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onStart(id);
    }

    @OnClick(R.id.iv_take_med)
    public void onMedTakeClick() {
        onMedicineTaken();
        stopMedialPlayer();
        stopVibrator();
    }

    @OnClick(R.id.iv_ignore_med)
    public void onMedIgnoreClick() {
        onMedicineIgnored();
        stopMedialPlayer();
        stopVibrator();
    }

    private void stopMedialPlayer() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
    }

    private void stopVibrator() {
        if (mVibrator != null) {
            mVibrator.cancel();
        }
    }

    private void onMedicineTaken() {
        History history = new History();

        Calendar takeTime = Calendar.getInstance();
        Date date = takeTime.getTime();
        String dateString = new SimpleDateFormat("MMM d, yyyy", Locale.getDefault()).format(date);

        int hour = takeTime.get(Calendar.HOUR_OF_DAY);
        int minute = takeTime.get(Calendar.MINUTE);
        String am_pm = (hour < 12) ? "am" : "pm";

        history.setHourTaken(hour);
        history.setMinuteTaken(minute);
        history.setDateString(dateString);
        history.setPillName(medicineAlarm.getPillName());
        history.setAction(1);
        history.setDoseQuantity(medicineAlarm.getDoseQuantity());
        history.setDoseUnit(medicineAlarm.getDoseUnit());

        presenter.addPillsToHistory(history);

        String stringMinute;
        if (minute < 10)
            stringMinute = "0" + minute;
        else
            stringMinute = "" + minute;

        int nonMilitaryHour = hour % 12;
        if (nonMilitaryHour == 0)
            nonMilitaryHour = 12;

        Toast.makeText(getContext(), medicineAlarm.getPillName() + " was taken at " + nonMilitaryHour + ":" + stringMinute + " " + am_pm + ".", Toast.LENGTH_SHORT).show();

        Intent returnHistory = new Intent(getContext(), MedicineActivity.class);
        startActivity(returnHistory);
        getActivity().finish();
    }


    private void onMedicineIgnored() {
        History history = new History();

        Calendar takeTime = Calendar.getInstance();
        Date date = takeTime.getTime();
        String dateString = new SimpleDateFormat("MMM d, yyyy", Locale.getDefault()).format(date);

        int hour = takeTime.get(Calendar.HOUR_OF_DAY);
        int minute = takeTime.get(Calendar.MINUTE);
        String am_pm = (hour < 12) ? "am" : "pm";

        history.setHourTaken(hour);
        history.setMinuteTaken(minute);
        history.setDateString(dateString);
        history.setPillName(medicineAlarm.getPillName());
        history.setAction(2);
        history.setDoseQuantity(medicineAlarm.getDoseQuantity());
        history.setDoseUnit(medicineAlarm.getDoseUnit());

        presenter.addPillsToHistory(history);

        String stringMinute;
        if (minute < 10)
            stringMinute = "0" + minute;
        else
            stringMinute = "" + minute;

        int nonMilitaryHour = hour % 12;
        if (nonMilitaryHour == 0)
            nonMilitaryHour = 12;

        Toast.makeText(getContext(), medicineAlarm.getPillName() + " was ignored at " + nonMilitaryHour + ":" + stringMinute + " " + am_pm + ".", Toast.LENGTH_SHORT).show();

        Intent returnHistory = new Intent(getContext(), MedicineActivity.class);
        startActivity(returnHistory);
        getActivity().finish();
    }


    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onFinish() {
        stopMedialPlayer();
        stopVibrator();
        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
