package com.example.lxh.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomChoiceActivity extends AppCompatActivity {

    ListView lv_singleChoice;
    ListView lv_multipleChoice;
    private ArrayList<String> mTestData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_choice);

        findView();
        initView();
    }

    public void findView() {
        lv_singleChoice = (ListView) findViewById(R.id.lv_singleChoice);
        lv_multipleChoice = (ListView) findViewById(R.id.lv_multipleChoice);
    }

    public void initView() {
        lv_singleChoice.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.item_lv_single_choice, initData()));
        lv_multipleChoice.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.item_lv_single_choice, initData()));

    }

    public ArrayList<String> initData() {

        mTestData = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            mTestData.add("测试数据" + i);
        }
        return mTestData;
    }

    public class myMultipleAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mTestData.size();
        }

        @Override
        public Object getItem(int position) {
            return mTestData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_lv_multiple_choice, parent, false);
                TextView tv_content = (TextView) convertView.findViewById(R.id.tv_content);
                tv_content.setText(mTestData.get(position));
            }
            return convertView;
        }
    }

}
