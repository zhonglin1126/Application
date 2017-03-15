package com.lin.application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import com.lin.commonadapter.listview.CommonAdapter;
import com.lin.commonadapter.listview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SpinnerActivity extends AppCompatActivity {
    private Spinner spinner;
    private CommonAdapter<String> stringCommonAdapter;
    private List<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        init();
    }

    private void init() {
        spinner = (Spinner) findViewById(R.id.spinner);
        stringList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            stringList.add("2017-11-" + i);
        }
        spinner.setPromptId(R.string.spinner_hint);
        stringCommonAdapter = new CommonAdapter<String>(this, stringList, R.layout.list_item) {
            @Override
            protected void convert(ViewHolder viewHolder, Object item) {
                viewHolder.setText(R.id.tv_text, item.toString());
            }
        };
        spinner.setAdapter(stringCommonAdapter);
    }
}
