package com.example.prog01_electrictime;

public class VicItem {
    private String mVicName;
    private int mVicImage;


    public VicItem(String vicName, int vicImage){
        mVicName = vicName;
        mVicImage = vicImage;
    }

    public String getVicName(){ return mVicName;}

    public int getVicImage(){return mVicImage;}
}
