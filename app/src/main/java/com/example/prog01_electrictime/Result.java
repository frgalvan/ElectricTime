package com.example.prog01_electrictime;

public class Result {
    private String name;
    private String t3; //time to travel
    private int resultImage;

    public Result(String name, String t3, int resultImage){
        this.name = name;
        this.t3 = t3;
        this.resultImage = resultImage;
    }

    public String getName(){ return name;}

    public void setName(String name){ this.name = name;}

    public String getT3(){ return t3;}

    public void setT3(String t3){this.t3 = t3;}

    public int getResultImage(){return resultImage;}

    public void setResultImage(int resultImage){this.resultImage = resultImage;}

}
