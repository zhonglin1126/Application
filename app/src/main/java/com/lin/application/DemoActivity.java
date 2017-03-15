package com.lin.application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;

import com.lin.commonadapter.listview.CommonAdapter;
import com.lin.commonadapter.listview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends AppCompatActivity {
    private List<String> stringList;
    private ListView lv_list;
    private CommonAdapter<String> commonAdapter;
    private AutoCompleteTextView autoCompleteTextView;
    private MultiAutoCompleteTextView multiAutoCompleteTextView;
    private static final String[] data = new String[]{"abcn=d", "abbbdbdbdbd", "cccccccc", "dddddddd", "ddddeeeeeeaa"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        init();
        autoView();
        mutiView();
    }

    private void mutiView() {
        multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.matv_content);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line
                , data);
        multiAutoCompleteTextView.setAdapter(adapter1);
    }

    private void autoView() {
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.atc_content);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line
                , data);
        autoCompleteTextView.setAdapter(adapter1);
    }

    private void init() {
        stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stringList.add("2017-11-" + i);
        }
        lv_list = (ListView) findViewById(R.id.lv_list);
        lv_list.setDivider(null);
        commonAdapter = new CommonAdapter<String>(this, stringList, R.layout.list_item) {
            @Override
            protected void convert(ViewHolder viewHolder, Object item) {
                viewHolder.setText(R.id.tv_text, item.toString());
            }
        };
        lv_list.setAdapter(commonAdapter);
    }
}
