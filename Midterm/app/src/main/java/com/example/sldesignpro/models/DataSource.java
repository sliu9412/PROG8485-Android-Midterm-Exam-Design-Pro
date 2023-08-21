package com.example.sldesignpro.models;

import android.content.Context;
import android.widget.ArrayAdapter;

public class DataSource {
    private final Context context;

    public DataSource(Context context) {
        this.context = context;
    }

    public ArrayAdapter GenerateAdapter(String[] source) {
        return new ArrayAdapter(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, source);
    }
}
