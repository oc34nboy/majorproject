package com.m.wecare;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Report_recycleView_adapter extends RecyclerView.Adapter<Report_recycleView_adapter.ViewHolder> {

    private ArrayList<String> diseaseName=new ArrayList<>();
    private ArrayList<String> diseaseProb=new ArrayList<>();
    Context context;

    public Report_recycleView_adapter(Context context,ArrayList<String> diseaseName, ArrayList<String> diseaseProb) {
        this.diseaseName = diseaseName;
        this.diseaseProb = diseaseProb;
        this.context = context;
    }

    @NonNull
    @Override
    public Report_recycleView_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.report_list,viewGroup,false);
        ViewHolder holder =new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull Report_recycleView_adapter.ViewHolder viewHolder, int i) {

            viewHolder.name.setText(diseaseName.get(i));
            viewHolder.percent.setText(diseaseProb.get(i));
            final int  position=i;

            viewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, diseaseName.get(position), Toast.LENGTH_SHORT).show();
                }
            });

    }

    @Override
    public int getItemCount() {
        return diseaseName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,percent;
        LinearLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name =itemView.findViewById(R.id.diseaseName);
            percent=itemView.findViewById(R.id.percentage);
            parent_layout=itemView.findViewById(R.id.reportLayout);
        }
    }

}
