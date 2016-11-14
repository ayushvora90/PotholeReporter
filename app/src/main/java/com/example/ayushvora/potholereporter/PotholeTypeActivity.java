package com.example.ayushvora.potholereporter;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ayushvora.potholereporter.types.PotholeType;

import java.util.HashMap;

public class PotholeTypeActivity extends AppCompatActivity {

    ImageView earlyStageImageView;
    ImageView cliffhangerImageView;
    ImageView invertedImageView;
    ImageView tectonicImageView;
    ImageView traditionalImageView;
    String type;

    HashMap<String, String> potholeDataMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pothole_type);
        Intent dimQActivityIntent = getIntent();
        potholeDataMap = (HashMap<String, String>) dimQActivityIntent.getSerializableExtra("potholeDataMap");
        earlyStageImageView = (ImageView) findViewById(R.id.EarlyStageImageView);
        cliffhangerImageView = (ImageView) findViewById(R.id.CliffhangerImageView);
        invertedImageView = (ImageView) findViewById(R.id.InvertedImageView);
        tectonicImageView = (ImageView) findViewById(R.id.TectonicImageView);
        traditionalImageView = (ImageView) findViewById(R.id.TraditionalImageView);
    }

    private void resetAllClicked(){
        earlyStageImageView.setBackgroundResource(0);
        cliffhangerImageView.setBackgroundResource(0);
        invertedImageView.setBackgroundResource(0);
        tectonicImageView.setBackgroundResource(0);
        traditionalImageView.setBackgroundResource(0);
    }

    public void onEarlyStageCLick(View view){
        resetAllClicked();
        potholeDataMap.put("potholeType", PotholeType.EARLYSTAGE.name());
        earlyStageImageView.setBackgroundResource(R.drawable.rounded_corner);
    }

    public void onCliffhangerClick(View view){
        resetAllClicked();
        potholeDataMap.put("potholeType", PotholeType.CLIFFHANGER.name());
        cliffhangerImageView.setBackgroundResource(R.drawable.rounded_corner);
    }

    public void onInvertedClick(View view){
        resetAllClicked();
        potholeDataMap.put("potholeType", PotholeType.INVERTED.name());
        invertedImageView.setBackgroundResource(R.drawable.rounded_corner);
    }

    public void onTectonicClick(View view){
        resetAllClicked();
        potholeDataMap.put("potholeType", PotholeType.TECTONIC.name());
        tectonicImageView.setBackgroundResource(R.drawable.rounded_corner);

    }

    public void onTraditionalClick(View view){
        resetAllClicked();
        potholeDataMap.put("potholeType", PotholeType.TRADITIONAL.name());
        traditionalImageView.setBackgroundResource(R.drawable.rounded_corner);
    }

    public void onNextClick(View view){
        Intent nextIntent = new Intent(this,SurveyActivity.class);
        nextIntent.putExtra("potholeDataMap",potholeDataMap);
        startActivity(nextIntent);

    }
    public void onPrevClick(View view){
        Intent prevIntent = new Intent(this,DimensionQuestionActivity.class);
        prevIntent.putExtra("potholeDataMap",potholeDataMap);
        startActivity(prevIntent);
    }


}
