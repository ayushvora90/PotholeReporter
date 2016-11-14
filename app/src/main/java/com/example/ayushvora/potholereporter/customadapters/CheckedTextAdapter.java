package com.example.ayushvora.potholereporter.customadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.example.ayushvora.potholereporter.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ayushvora on 11/6/16.
 */

public class CheckedTextAdapter<String> extends ArrayAdapter<String> {
    List<String> options;
    Map<Integer,Integer> questionValPos;
    Context context;
    LayoutInflater inflter;
    Integer questionIndex;

    public static class ViewHolder {
        CheckedTextView checkedTextView;
    };

    public CheckedTextAdapter(Context context, List<String> options, Map<Integer,Integer> questionValPos, Integer questionIndex){
        super(context,R.layout.option_text,options);
        this.context = context;
        this.options = options;
        this.questionValPos = questionValPos;
        this.inflter = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.questionIndex = questionIndex;

    }
    @Override
    public int getCount() {
        return options.size();
    }

    @Override
    public String getItem(int position) {
        return options.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Integer checkedPos = questionValPos.get(questionIndex);
        if(convertView ==  null) {
            convertView = inflter.inflate(R.layout.option_text, parent, false);
            holder = new ViewHolder();
            holder.checkedTextView = (CheckedTextView) convertView.findViewById(R.id.optionsCheckedTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        if(options.size()<=0){
            holder.checkedTextView.setText("No data");
        } else {
            final CheckedTextView simpleCheckedTextView = holder.checkedTextView;
            simpleCheckedTextView.setCheckMarkDrawable(null);
            simpleCheckedTextView.setChecked(false);

            simpleCheckedTextView.setText(options.get(position) + "");
            if (checkedPos != null && checkedPos == position) {
                simpleCheckedTextView.setCheckMarkDrawable(R.drawable.checked);
                simpleCheckedTextView.setChecked(true);
            }

        }
        return convertView;


    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public LayoutInflater getInflter() {
        return inflter;
    }

    public void setInflter(LayoutInflater inflter) {
        this.inflter = inflter;
    }

    public Map<Integer, Integer> getQuestionValPos() {
        return questionValPos;
    }

    public void setQuestionValPos(Map<Integer, Integer> questionValPos) {
        this.questionValPos = questionValPos;
    }

    public Integer getQuestionIndex() {
        return questionIndex;
    }

    public void setQuestionIndex(Integer questionIndex) {
        this.questionIndex = questionIndex;
    }



}
