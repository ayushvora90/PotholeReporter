package com.example.ayushvora.potholereporter.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayushvora on 10/24/16.
 */

public class SurveyQuestion<E extends Enum<E>> {

    String fieldId;
    String question;
    Class<E> enumType;
    List<String> options;

    public SurveyQuestion(String fieldId, String question, Class<E> enumType) {
        this.fieldId = fieldId;
        this.question = question;
        this.enumType = enumType;
        options = new ArrayList<String>();
        for(E enumConstant: enumType.getEnumConstants()){
            options.add(enumConstant.toString());
        }
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Class<E> getEnumType() {
        return enumType;
    }

    public void setEnumType(Class<E> enumType) {
        this.enumType = enumType;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
