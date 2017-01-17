package com.example.lxh.test.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lxh.test.R;
import com.example.lxh.test.utils.SystemUtils;

import java.util.List;

/**
 * Created by LXH on 17/1/17.
 */

public class MyIntervalViewTopCircle extends LinearLayout {

    private Context context;
    CharSequence top_text;
    int ImaRes;
    //线的参数
    private LayoutParams lineLp;

    public MyIntervalViewTopCircle(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public MyIntervalViewTopCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.InterValView);
        top_text = a.getText(R.styleable.InterValView_text);
        ImaRes = a.getResourceId(R.styleable.InterValView_icon, 0);
        a.recycle();
        initView();
    }

    public MyIntervalViewTopCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyIntervalViewTopCircle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    LinearLayout activity_inter_val;

    public void initView() {
//        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(R.layout.activity_inter_val, this);
//        activity_inter_val = (LinearLayout) findViewById(R.id.activity_inter_val);

        lineLp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lineLp.leftMargin = 32;

    }

    /****
     *
     * @param intervals
     * @param position
     */
    public void setDatas(List<Integer> intervals, int position) {
        removeAllViews();
        int screenWidth = SystemUtils.getScreenWidth(getContext()) - SystemUtils.dp2px(getContext(),40);
        int screenHeight = SystemUtils.getScreenHeight(getContext());

        int circleImgWidth = SystemUtils.dp2px(getContext(), 20);
        LayoutParams params = new LayoutParams(circleImgWidth, circleImgWidth);

        int lineWidth = (screenWidth - circleImgWidth * intervals.size()) / (intervals.size() - 1);
        int lineHeight = SystemUtils.dp2px(getContext(), 10);
        LayoutParams LineParams = new LayoutParams(lineWidth, lineHeight);

        for (int i = 0; i < intervals.size(); i++) {
            Integer integer = intervals.get(i);
//
            TextView tv = new TextView(getContext());
            tv.setBackgroundResource(R.drawable.red_circle);
            tv.setText(integer + "");
            tv.setTextSize(10);
            tv.setLayoutParams(params);
            tv.setTextColor(Color.WHITE);
            tv.setGravity(Gravity.CENTER);

            addView(tv);

            if ((i < intervals.size() - 1)) {

                if (position > integer) {
                    if (position < intervals.get(i + 1)) {

                        int a = (intervals.get(i + 1) - integer);
                        Log.i("BBBB", "a-->" + a);
                        int positionWidth = (lineWidth * (position - integer)) / a;
                        Log.i("BBBB", "positionWidth-->" + positionWidth);
                        TextView LineTv = new TextView(getContext());
                        LineTv.setBackground(getResources().getDrawable(R.color.colorAccent));
                        LayoutParams positionParams = new LayoutParams(positionWidth, lineHeight);
                        LineTv.setLayoutParams(positionParams);
                        addView(LineTv);

                        TextView LineTv2 = new TextView(getContext());
                        LineTv2.setBackgroundResource(R.drawable.interval_line);
                        LayoutParams positionParams2 = new LayoutParams(lineWidth - positionWidth, lineHeight);
                        LineTv2.setLayoutParams(positionParams2);
                        addView(LineTv2);
                    } else {
                        TextView LineTv = new TextView(getContext());
                        LineTv.setBackgroundResource(R.drawable.interval_line);
                        LineTv.setLayoutParams(LineParams);
                        LineTv.setBackground(getResources().getDrawable(R.color.colorAccent));
                        addView(LineTv);
                    }
                } else {
                    TextView LineTv = new TextView(getContext());
                    LineTv.setBackgroundResource(R.drawable.interval_line);
                    LineTv.setLayoutParams(LineParams);
                    addView(LineTv);
                }
            }

        }

    }



}
