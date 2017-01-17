package com.example.lxh.test;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        testAppLanuch();


    }

    Button tbn_gotoSecond;

    public void initView() {
        tbn_gotoSecond = (Button) findViewById(R.id.tbn_gotoSecond);
        tbn_gotoSecond.setOnClickListener(this);
        findViewById(R.id.tv_gotoSecond).setOnClickListener(this);
        findViewById(R.id.goto_flipper).setOnClickListener(this);
        findViewById(R.id.goto_listView).setOnClickListener(this);
        findViewById(R.id.goto_interval).setOnClickListener(this);
        tbn_gotoSecond.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.i("BBBB", "tv_gotoSecond.getY()-->" + tbn_gotoSecond.getY());
//                performEnterAnimation();
                tbn_gotoSecond.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goto_flipper:
                startActivity(new Intent(getApplicationContext(), FlipperActivity.class));
                break;

            case R.id.goto_listView:
                startActivity(new Intent(getApplicationContext(), CustomChoiceActivity.class));
                break;
            case R.id.goto_interval:
                startActivity(new Intent(getApplicationContext(), InterValActivity.class));
                break;

            case R.id.tv_gotoSecond:
                Intent intent = new Intent(MainActivity.this, SecondAcitivity.class);
                int location[] = new int[2];
                tbn_gotoSecond.getLocationOnScreen(location);
                intent.putExtra("x", location[0]);
                intent.putExtra("y", location[1]);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;

        }
    }

    /***
     * 动态修改图片
     * 参考链接:
     * http://yifeng.studio/2016/12/30/android-change-app-launcher-icon-dynamically/
     */
    public void testAppLanuch() {

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pm = getPackageManager();
                pm.setComponentEnabledSetting(getComponentName(),
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                pm.setComponentEnabledSetting(new ComponentName(MainActivity.this, "com.example.lxh.test.MainAliasActivity"),
                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
            }

        });
    }

    public void testToucheEvent() {

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "外层_按钮点击---->被点击了了", Toast.LENGTH_SHORT).show();
            }
        });

        RelativeLayout rl_container = (RelativeLayout) findViewById(R.id.rl_container);

        rl_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "container_---->被点击了", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "container_按钮---->被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void test() {
        //        String str = "l4Tf2slIesFQeLaMjscYXAi\\/W9cnXMn3g4TZsEOp2t7cbovYZK4XJ9mA669p7nDH";
//
//
//        TextView textView = (TextView) findViewById(R.id.tv);
//        textView.setText(getAppInfo());
//
//        TextView textView2 = (TextView) findViewById(R.id.tv2);
//
//
//        StringBuilder builder = new StringBuilder();
//        String strStr1 = "la啦啦啦啊" + "\n";
//        builder.append(strStr1);
//        builder.append(strStr1);
//
//        String strStr2 = "<br><font color=\"#FF0000\">" + "失败原因:" + "</font>";
//
//
//        textView2.setText(Html.fromHtml(strStr2));
//
//
//        String message = "";
//        if (!TextUtils.isEmpty(mWithdrawInfo.RealNameCheckExplain)) {
//            message = mWithdrawInfo.RealNameExplain;
//        } else {
//        message = "fsdfdsfsa" + "<br><font color=\"#FF0000\" >" + "失败原因:" + "</font>";
//        }
//        final TextView myView = new TextView(getApplicationContext());
//        myView.setText(Html.fromHtml(message));
//        myView.setTextSize(12);
//
//        Dialog dialog = new AlertDialog.Builder(MainActivity.this, R.style.MyAlertDialogStyle)
//                .setIconAttribute(android.R.attr.alertDialogIcon)
//                .setMessage(Html.fromHtml(message))
//                .setPositiveButton("去认证", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
////                        TurnToActivityUtils.turnToActivity(getContext(), RealNameAuthenticActivity.class, null);
//                    }
//                }).create();
////        TextView tv = (TextView) dialog.findViewById(android.R.id.message);
////        tv.setTextSize(30);
////        dialog.show();
//        dialog.show();

    }

    private String getAppInfo() {
        try {
            String pkName = this.getPackageName();
            String versionName = this.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            int versionCode = this.getPackageManager()
                    .getPackageInfo(pkName, 0).versionCode;
            return pkName + "----" + versionName + "---" + versionCode;
        } catch (Exception e) {
        }
        return null;
    }


}
