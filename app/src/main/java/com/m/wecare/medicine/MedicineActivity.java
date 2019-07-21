package com.m.wecare.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.m.wecare.Injection;
import com.m.wecare.R;
import com.m.wecare.report.MonthlyReportActivity;
import com.m.wecare.utils.ActivityUtils;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MedicineActivity extends AppCompatActivity {


    @BindView(R.id.compactcalendar_view)
    public
    CompactCalendarView mCompactCalendarView;

    @BindView(R.id.date_picker_text_view)
    public
    TextView datePickerTextView;

    @BindView(R.id.date_picker_button)
    public
    RelativeLayout datePickerButton;

    @BindView(R.id.toolbar)
    public
    Toolbar toolbar;

    @BindView(R.id.collapsingToolbarLayout)
    public
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.app_bar_layout)
    public
    AppBarLayout appBarLayout;

    @BindView(R.id.contentFrame)
    public
    FrameLayout contentFrame;

    @BindView(R.id.fab_add_task)
    public
    FloatingActionButton fabAddTask;

    @BindView(R.id.coordinatorLayout)
    public
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.date_picker_arrow)
    public
    ImageView arrow;

    private MedicinePresenter presenter;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd", /*Locale.getDefault()*/Locale.ENGLISH);

    private boolean isExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        mCompactCalendarView.setLocale(TimeZone.getDefault(), /*Locale.getDefault()*/Locale.ENGLISH);

        mCompactCalendarView.setShouldDrawDaysHeader(true);

        mCompactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                setSubtitle(dateFormat.format(dateClicked));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateClicked);

                int day = calendar.get(Calendar.DAY_OF_WEEK);

                if (isExpanded) {
                    ViewCompat.animate(arrow).rotation(0).start();
                } else {
                    ViewCompat.animate(arrow).rotation(180).start();
                }
                isExpanded = !isExpanded;
                appBarLayout.setExpanded(isExpanded, true);
                presenter.reload(day);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                setSubtitle(dateFormat.format(firstDayOfNewMonth));
            }
        });
        setCurrentDate(new Date());
        MedicineFragment medicineFragment = (MedicineFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (medicineFragment == null) {
            medicineFragment = MedicineFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), medicineFragment, R.id.contentFrame);
        }

        //Create MedicinePresenter
        presenter = new MedicinePresenter(Injection.provideMedicineRepository(MedicineActivity.this), medicineFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.medicine_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_stats) {
            Intent intent = new Intent(this, MonthlyReportActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setCurrentDate(Date date) {
        setSubtitle(dateFormat.format(date));
        mCompactCalendarView.setCurrentDate(date);
    }

    public void setSubtitle(String subtitle) {
        datePickerTextView.setText(subtitle);
    }

    @OnClick(R.id.date_picker_button)
    public void onDatePickerButtonClicked() {
        if (isExpanded) {
            ViewCompat.animate(arrow).rotation(0).start();
        } else {
            ViewCompat.animate(arrow).rotation(180).start();
        }

        isExpanded = !isExpanded;
        appBarLayout.setExpanded(isExpanded, true);
    }
}
