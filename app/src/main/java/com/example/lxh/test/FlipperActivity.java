package com.example.lxh.test;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;

public class FlipperActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {


    ViewFlipper flipper;
    GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipper);

        initView();
    }

    /****
     * http://www.dengzhr.com/others/mobile/703
     * http://blog.csdn.net/nnmmbb/article/details/40433903
     */
    private void initView() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        flipper = (ViewFlipper) findViewById(R.id.flipper);

        flipper.setInAnimation(getApplicationContext(), R.anim.right_in);
        flipper.setOutAnimation(getApplicationContext(), R.anim.left_out);

//        flipper.startFlipping();
        flipper.setAutoStart(true);

        mGestureDetector = new GestureDetector(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        
        return this.mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e2.getX() - e1.getX() > 120) {
            flipper.setInAnimation(getApplicationContext(), R.anim.left_in);
            flipper.setOutAnimation(getApplicationContext(), R.anim.right_out);
            flipper.showPrevious();
            return true;
        } else if (e1.getX() - e2.getX() > 120) {
            flipper.setInAnimation(getApplicationContext(), R.anim.right_in);
            flipper.setOutAnimation(getApplicationContext(), R.anim.left_out);
            flipper.showNext();
            return true;
        }
        return false;
    }


    public int start;

    class flipperOnTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // 手势判断
            switch (event.getAction()) {

                //手势按下
                case MotionEvent.ACTION_DOWN:
                    //获取手指按下的点
                    start = (int) event.getX();
                    flipper.stopFlipping();
                    break;
                //手势移动
                case MotionEvent.ACTION_MOVE:
                    //移动判断

                    break;
                //手离开
                case MotionEvent.ACTION_UP:

                    //按下的点 和结束的点 的插 大于100 为 向右
                    if (start - event.getX() > 1000) {
                        //可以添加过度效果
                        //下一张
                        flipper.showPrevious();
                    }


                    //按下的点 和结束的点 的插 小于100 为 向左
                    if (start - event.getX() < 1000) {
                        //上一张
                        flipper.showNext();
                    }
//                    flipper.setAutoStart(true);
                    break;
            }
            return true;
        }

    }

}
