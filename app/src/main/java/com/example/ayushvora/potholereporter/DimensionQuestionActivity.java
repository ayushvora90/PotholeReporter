package com.example.ayushvora.potholereporter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import java.util.HashMap;

public class DimensionQuestionActivity extends AppCompatActivity {

    HashMap<String, String> potholeDataMap;
    NumberPicker lengthPicker;
    NumberPicker widthPicker;
    NumberPicker depthPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimension_question);
        Intent potPicLocActivityIntent = getIntent();
        potholeDataMap = (HashMap<String, String>) potPicLocActivityIntent.getSerializableExtra("potholeDataMap");
        lengthPicker = (NumberPicker) findViewById(R.id.lengthPicker);
        widthPicker = (NumberPicker) findViewById(R.id.widthPicker);
        depthPicker = (NumberPicker) findViewById(R.id.depthPicker);
        lengthPicker.setMinValue(0);
        lengthPicker.setMaxValue(12);
        lengthPicker.setMinValue(0);
        lengthPicker.setValue(2);
        widthPicker.setMaxValue(12);
        widthPicker.setMinValue(0);
        widthPicker.setValue(2);
        depthPicker.setMaxValue(20);
        depthPicker.setMinValue(0);
        depthPicker.setValue(5);
    }

    public void onNextClick(View view) {

        potholeDataMap.put("length",lengthPicker.getValue()+"");
        potholeDataMap.put("width",widthPicker.getValue()+"");
        potholeDataMap.put("depth",depthPicker.getValue()+"");
        Intent nextIntent = new Intent(this,PotholeTypeActivity.class);
        nextIntent.putExtra("potholeDataMap",potholeDataMap);
        startActivity(nextIntent);

    }

    public void onPrevClick(View view){
        NumberPicker lengthPicker = (NumberPicker) findViewById(R.id.lengthPicker);
        NumberPicker widthPicker = (NumberPicker) findViewById(R.id.widthPicker);
        NumberPicker depthPicker = (NumberPicker) findViewById(R.id.depthPicker);
        potholeDataMap.put("length",lengthPicker.getValue()+"");
        potholeDataMap.put("width",widthPicker.getValue()+"");
        potholeDataMap.put("depth",depthPicker.getValue()+"");
        Intent prevIntent = new Intent(this,PotholePictureLocationActivity.class);
        prevIntent.putExtra("potholeDataMap",potholeDataMap);
        startActivity(prevIntent);


    }
}
