package com.m.wecare.remainder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.m.wecare.R;
import com.m.wecare.remainder.ItemData;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<ItemData> {


    public SpinnerAdapter(Context context, List<ItemData> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    public View customView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_try_layout, parent, false);
        }
        ItemData items = getItem(position);
        ImageView spinnerImage = convertView.findViewById(R.id.img);

        if (items != null) {
            spinnerImage.setImageResource(items.getImageId());

        }
        return convertView;
    }
}
