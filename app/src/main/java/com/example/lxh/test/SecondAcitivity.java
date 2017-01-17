package com.example.lxh.test;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.TextView;


/**
 * Created by LXH on 17/1/5.
 */

public class SecondAcitivity extends AppCompatActivity {

    TextView tv_tv_gotoSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findView();
        initBoot();
        initLister();
        tv_tv_gotoSecond = (TextView) findViewById(R.id.tv_tv_gotoSecond);
        tv_tv_gotoSecond.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.i("BBBB", "tv_gotoSecond.getY()-->" + tv_tv_gotoSecond.getY());
                performEnterAnimation();
                tv_tv_gotoSecond.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

    }

    public void findView() {

    }

    public void initBoot() {

    }

    public void initLister() {

    }


    private void performEnterAnimation() {
        float originY = getIntent().getIntExtra("y", 0);

        int location[] = new int[2];
        tv_tv_gotoSecond.getLocationOnScreen(location);
        final float translateY = originY - (float) location[1];
        Log.i("BBBB", "originY-->" + originY);
        Log.i("BBBB", "location[1]-->" + location[1]);
        Log.i("BBBB", "translateY-->" + translateY);
        Log.i("BBBB", "tv_gotoSecond.getY()-->" + tv_tv_gotoSecond.getY());
        //放到前一个页面的位置
        tv_tv_gotoSecond.setY(tv_tv_gotoSecond.getY() + translateY);
        final ValueAnimator translateVa = ValueAnimator.ofFloat(tv_tv_gotoSecond.getY(), tv_tv_gotoSecond.getY() - translateY);
        translateVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Log.i("BBBB", "(Float) valueAnimator.getAnimatedValue()-->" + valueAnimator.getAnimatedValue());
                tv_tv_gotoSecond.setY((Float) valueAnimator.getAnimatedValue());

//                mArrowImg.setY(mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mArrowImg.getHeight()) / 2);
//                mHintTxt.setY(mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mHintTxt.getHeight()) / 2);
//                mSearchTxt.setY(mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mSearchTxt.getHeight()) / 2);
            }
        });

        ValueAnimator scaleVa = ValueAnimator.ofFloat(1, 0.8f);
        scaleVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                tv_tv_gotoSecond.setScaleX((Float) valueAnimator.getAnimatedValue());
            }
        });

        ValueAnimator alphaVa = ValueAnimator.ofFloat(0, 1f);
        alphaVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                tv_tv_gotoSecond.setAlpha((Float) valueAnimator.getAnimatedValue());
//                mSearchTxt.setAlpha((Float) valueAnimator.getAnimatedValue());
//                mArrowImg.setAlpha((Float) valueAnimator.getAnimatedValue());
            }
        });

        alphaVa.setDuration(500);
        translateVa.setDuration(500);
        scaleVa.setDuration(500);

        alphaVa.start();
        translateVa.start();
        scaleVa.start();

    }
}
