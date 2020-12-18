package com.example.prog01_electrictime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class VicAdapter extends ArrayAdapter<VicItem> {

    public VicAdapter(Context context, ArrayList<VicItem> vicList){
        super(context, 0, vicList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.picture_row, parent, false
            );
        }

        ImageView imageViewVic = convertView.findViewById(R.id.image_vic);
        TextView textViewName = convertView.findViewById(R.id.text_view_name);


        VicItem currentItem = getItem(position);

        if (currentItem != null) {
            imageViewVic.setImageResource(currentItem.getVicImage());
            textViewName.setText(currentItem.getVicName());
        }

        return convertView;
    }
}
