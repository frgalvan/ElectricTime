package com.example.prog01_electrictime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    ArrayList<VicItem> mVicList; //array for spinner list
    VicAdapter mAdapter;

    int index;

    float hours;
    float minutes;

    float dist;
    Button myButton;
    EditText distance;

    String[] name = {"Walking", "Boosted Mini S Board", "Evolve Bamboo GTR 2in1", "OneWheel XR", "MotoTec Skateboard", "Segway Ninebot S",
                     "Segway Ninebot S-PLUS", "Razor Scooter", "GeoBlade 500","Hovertrax Hoverboard"};

    int[] mipmaps = {R.mipmap.walking, R.mipmap.boosted, R.mipmap.bamboo, R.mipmap.onewheel, R.mipmap.mototec,
            R.mipmap.segway, R.mipmap.segwayplus, R.mipmap.razor, R.mipmap.geoblade, R.mipmap.hoverboard};

    Float[] speed = {3.1f, 18f, 24f, 19f, 22f, 10f, 12f, 18f, 15f, 9f};
    Float[] range = {30f, 7f, 31f, 18f, 10f, 13f, 22f, 15f, 8f, 6f};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //prevents resources from being shifted when keyboard comes up
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //populates Array for Spinner
        initList();
            //Dropdown (spinner) selection list
            Spinner spinnerVics = findViewById(R.id.spinner1);
            mAdapter = new VicAdapter(this, mVicList);
            spinnerVics.setAdapter(mAdapter);

            spinnerVics.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    index = i;
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    index = 0; // does nothing ... ?
                }
            });//spinner OnSelectedItemListener

            distance = findViewById(R.id.distanceInput);
            myButton = findViewById(R.id.button);

            myButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Prevent crashing upon no user Input
                    if (distance.getText().toString().length() == 0) {
                        distance.setText("0");
                    }
                    dist = Float.valueOf(distance.getText().toString());

                    //DEBUGGING::::::
                    //System.out.print("SOMETHIING LOUDLY");  //DOESN'T DO ANYTHING
                    Context context = getApplicationContext();
                    CharSequence text = "SOMETHING LOUDER!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    //END DEBUGGING BLOCK......

                    //hopefully the listview doesn't appear until click...DOPE
                    ListView mListView = (ListView) findViewById(R.id.ListView);

                    ArrayList<Result> resultList = new ArrayList<>();

                    String message, submessage;

                    //Prominent answer here
                    TextView prominentName = findViewById(R.id.nameProminent);
                    TextView prominentMinutes = findViewById(R.id.minutesProminent);
                    TextView minutesSub = findViewById(R.id.minutesText);
                    prominentName.setText(name[index]);
                    message = String.valueOf((dist / speed[index]) * 60f);
                    submessage = "minutes";

                    //Prominent message and submessage go green
                    prominentMinutes.setText(message);
                    minutesSub.setText(submessage);
                    prominentMinutes.setTextColor(Color.GREEN);
                    minutesSub.setTextColor(Color.GREEN);

                    //if Prominent Displayed (selected vehicle) is out or range
                    if (dist > range[index]) {
                        message = "Too far";
                        submessage = "Out of Range";
                        //Red of out of range
                        prominentMinutes.setText(message);
                        minutesSub.setText(submessage);
                        prominentMinutes.setTextColor(Color.RED);
                        minutesSub.setTextColor(Color.RED);
                    }
                    index++; // get passed prominents, to display ListView separately

                    //Other options textview prompt (for ListView)
                    TextView otherOptions = findViewById(R.id.otherOptions);
                    otherOptions.setText("Here's how long it might take for the others...");

                    //And All other vehicles
                    for (int n = 0; n < 10; n++) {
                        if (index > 9) { index = 0; } //recycle my Arrays

                        hours = dist / speed[index];
                        minutes = hours * 60f;
                        message = minutes + " minutes";

                        if (dist > range[index]) {
                            message = "Too far for this vehicle!";
                        }// if out of range

                        resultList.add((new Result(name[index], message, mipmaps[index])));

                        index++;
                    }//for "all others"
                     if (index > 9) { index = 0; } //recycle my Arrays

                    ResultListAdapter rAdapter = new ResultListAdapter(MainActivity.this,
                            R.layout.adapter_view_layout, resultList);
                    mListView.setAdapter(rAdapter);

                    hideKeyboard(v);
                }//on Click
            });
    }//on Create

    //Spinner (dropdown) list
    private void initList(){
        mVicList = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            mVicList.add(new VicItem(name[i], mipmaps[i]));
        }//for
    }//initList

    private void hideKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }//hideKeyboard

}//MainActivity

