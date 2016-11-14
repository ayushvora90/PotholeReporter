package com.example.ayushvora.potholereporter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    HashMap<String,String> potholeDataMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        potholeDataMap = new HashMap<String, String>();
        potholeDataMap.put("currentDate",new Date().toString());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSurvey(View view){
        Intent intent = new Intent(this,PotholePictureLocationActivity.class);
        intent.putExtra("potholeDataMap",potholeDataMap);
        startActivity(intent);

    }

}
