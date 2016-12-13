package com.example.ayushvora.potholereporter;

import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ayushvora.potholereporter.ruleengines.SecurityEvaluatorRuleEngine;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    HashMap<String,String> potholeDataMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        potholeDataMap = new HashMap<String, String>();
        potholeDataMap.put("currentDate",new Date().toString());
        potholeDataMap.put("image","");
        potholeDataMap.put("latitude","");
        potholeDataMap.put("longitude","");
        potholeDataMap.put("length","");
        potholeDataMap.put("width","");
        potholeDataMap.put("depth","");
        potholeDataMap.put("potholeType","");
        potholeDataMap.put("countyName","");
        potholeDataMap.put("trafficVolume","");
        potholeDataMap.put("avgSpeed","");
        potholeDataMap.put("streetType","");
        potholeDataMap.put("junctionType","");
        potholeDataMap.put("pprrw","");
        potholeDataMap.put("safetyZoneType","");
        potholeDataMap.put("roadMaterial","");
        potholeDataMap.put("weatherType","");
        potholeDataMap.put("repair","");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startSurvey(View view){
        Intent intent = new Intent(this,PotholePictureLocationActivity.class);
        intent.putExtra("potholeDataMap",potholeDataMap);
        startActivity(intent);

    }

}
