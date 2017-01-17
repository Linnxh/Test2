package com.example.lxh.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lxh.test.customview.MyIntervalView;
import com.example.lxh.test.customview.MyIntervalViewTopCircle;

import java.util.ArrayList;
import java.util.List;

public class InterValActivity extends AppCompatActivity {

    MyIntervalView myinterView;
    MyIntervalViewTopCircle myinterViewTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_val);

        myinterView = (MyIntervalView) findViewById(R.id.myinterView);
        myinterViewTop = (MyIntervalViewTopCircle) findViewById(R.id.myinterViewTop);
        initData();
    }

    public void initData(){
        List<Integer> integers=new ArrayList<>();
        integers.add(0);
        integers.add(20);
        integers.add(40);
        integers.add(50);
        myinterView.setDatas(integers,40);//采用的是设置两遍的方法
        myinterViewTop.setDatas(integers,40);


    }
}
