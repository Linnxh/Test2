package com.example.lxh.test;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by LXH on 17/1/3.
 */

public class myTouchRelative extends RelativeLayout {
    public myTouchRelative(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public myTouchRelative(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public myTouchRelative(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public myTouchRelative(Context context) {
        super(context);
    }

//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return true;
//    }
////    @Override
////    public boolean onTouchEvent(MotionEvent event) {
////        return true;
////    }


}
