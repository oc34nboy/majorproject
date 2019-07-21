package com.m.wecare.medicine;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.m.wecare.R;

import com.m.wecare.source.MedicineAlarm;
import com.m.wecare.views.RobotoBoldTextView;
import com.m.wecare.views.RobotoRegularTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {

    private List<MedicineAlarm> medicineAlarmList;

    public MedicineAdapter(List<MedicineAlarm> medicineAlarmList) {
        this.medicineAlarmList = medicineAlarmList;
    }

    public void replaceData(List<MedicineAlarm> medicineAlarmList) {
        this.medicineAlarmList = medicineAlarmList;
        notifyDataSetChanged();
    }

    @Override
    public MedicineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_medicine, parent, false);
        return new MedicineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MedicineViewHolder holder, int position) {
        final MedicineAlarm medicineAlarm = medicineAlarmList.get(position);
        if (medicineAlarm == null) {
            return;
        }
        holder.tvMedTime.setText(medicineAlarm.getStringTime());
        holder.tvMedicineName.setText(medicineAlarm.getPillName());
        holder.tvDoseDetails.setText(medicineAlarm.getFormattedDose());
    }

    @Override
    public int getItemCount() {
        return (medicineAlarmList != null && !medicineAlarmList.isEmpty()) ? medicineAlarmList.size() : 0;
    }

    public static class MedicineViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_med_time)
        public
        RobotoBoldTextView tvMedTime;

        @BindView(R.id.tv_medicine_name)
        public
        RobotoBoldTextView tvMedicineName;

        @BindView(R.id.tv_dose_details)
        public
        RobotoRegularTextView tvDoseDetails;

        @BindView(R.id.iv_medicine_action)
        public
        ImageView ivMedicineAction;

        MedicineViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
