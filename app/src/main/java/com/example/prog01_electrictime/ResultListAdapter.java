package com.example.prog01_electrictime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ResultListAdapter extends ArrayAdapter<Result> {

    private static final String TAG = "WhatIsATAG4???";

    private Context mContext;
    private int mResource;

    private static class ViewHolder{
        TextView name;
        TextView t3;
        ImageView resultImage;

    }
    //Makes the list un-selectable (doesn't change tone when scroll-touched)
    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    public ResultListAdapter(Context context, int resource, ArrayList<Result> objects){
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //get the result's information
        String name = getItem(position).getName();
        String t3 = getItem(position).getT3();
        int img = getItem(position).getResultImage();

        //create Result object with ^ info
        Result result = new Result(name, t3, img);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvT3 = (TextView) convertView.findViewById(R.id.textView2);
        ImageView tvImage = (ImageView) convertView.findViewById(R.id.image);

        Result currentResult = getItem(position);

        if (currentResult != null){
            tvName.setText(currentResult.getName());
            tvT3.setText(currentResult.getT3());
            tvImage.setImageResource(currentResult.getResultImage());
        }

        return convertView;

    }

}
