package com.example.ayushvora.potholereporter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ayushvora.potholereporter.types.PriorityType;

public class FinishActivity extends AppCompatActivity {
    String priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent surveyActivityIntent = getIntent();
        priority = surveyActivityIntent.getStringExtra(SurveyActivity.PRIORITY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.PriorityRatingBar);
        ratingBar.setIsIndicator(true);
        ratingBar.setRating(getRating(priority));
        TextView ratingTextView = (TextView) findViewById(R.id.PriorityRatingTextView);
        ratingTextView.setText(priority);
    }

    private float getRating(String priority) {
        if(priority.equals(PriorityType.EXTREMELY_HIGH.toString())){
            return 5f;
        } else if(priority.equals(PriorityType.HIGH.toString())){
            return 4f;
        } else if(priority.equals(PriorityType.MEDIUM.toString())){
            return 3f;
        } else if(priority.equals(PriorityType.LOW.toString())){
            return 2f;
        } else if(priority.equals(PriorityType.EXTREMELY_LOW.toString())){
            return 1f;
        } else{
            return 0f;
        }
    }
}
