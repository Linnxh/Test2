
package com.example.lxh.test.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.util.HashMap;
import java.util.Map;

/**
 * <br/>
 * 系统工具类. <br/>
 * 主要用于获取系统信息,如设备ID、操作系统版本等
 *
 * @author jinpeng
 */
public class SystemUtils {
    public static final String TAG = "SystemUtils";

    /**
     * 当前是否横屏
     *
     * @param context
     * @return
     */
    public static boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * 基于Android3.0的平板
     *
     * @param context
     * @return
     */
    public static boolean isHoneycombTablet(Context context) {
        return false;
    }

    /**
     * 系统为Android3.0
     *
     * @return
     */
    public static boolean isHoneycomb() {
        // Can use static final constants like HONEYCOMB, declared in later versions
        // of the OS since they are inlined at compile time. This is guaranteed behavior.
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    /**
     * 判断是否为平板设备
     *
     * @param context
     * @return
     */
    public static boolean isTablet(Context context) {
        //暂时屏蔽平板
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * 获取设备ID.
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String id = tm.getDeviceId();
        return id;
    }

    /**
     * 获取设备名称.
     *
     * @return
     */
    public static String getBuildModel() {
        return Build.MODEL;
    }

    /**
     * 获取设备SDK版本号.
     *
     * @return
     */
    public static int getBuildVersionSDK() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取设备系统版本号.
     *
     * @return
     */
    public static String getBuildVersionRelease() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 判断SD卡是否插入 即是否有SD卡
     */
    public static boolean isSDCardMounted() {
        return android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment
                .getExternalStorageState());
    }

    /**
     * 是否：已经挂载,但只拥有可读权限
     */
    public static boolean isSDCardMountedReadOnly() {
        return android.os.Environment.MEDIA_MOUNTED_READ_ONLY.equals(android.os.Environment
                .getExternalStorageState());
    }

    /**
     * 获取android当前可用内存大小
     */
    public static String getAvailMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi);
        // mi.availMem; 当前系统的可用内存

        return Formatter.formatFileSize(context, mi.availMem);// 将获取的内存大小规格化
    }

    /**
     * 获取屏幕的亮度
     */
    public static int getScreenBrightness(Activity activity) {
        int nowBrightnessValue = 0;
        ContentResolver resolver = activity.getContentResolver();
        try {
            nowBrightnessValue = Settings.System.getInt(resolver,
                    Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nowBrightnessValue;
    }

    /**
     * 获取手机屏幕的宽和高
     *
     * @param c
     * @return map("w", width) map("h",height);
     */
    public static HashMap<String, Integer> getWidth_Height(Context c) {
        DisplayMetrics metrics = c.getApplicationContext().getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        HashMap<String, Integer> m = new HashMap<String, Integer>();
        m.put("w", width);
        m.put("h", height);
        return m;
    }

    /**
     * 获取平板在横屏时webview的宽度
     *
     * @param c
     * @return
     */
    public static int getTabletWebViewWidth(Context c) {
        return Math.min(SystemUtils.getScreenWidth(c), SystemUtils.getScreenHeight(c)) + 64;
    }

    /**
     * 获取手机屏幕的宽和高size wxh
     *
     * @param c
     * @return width X height
     */
    public static String getWidthXHeight(Context c) {
        Map<String, Integer> m = getWidth_Height(c);
        String size = m.get("w") + "x" + m.get("h");
        return size;
    }

    /**
     * 获取手机分辨率宽度大小
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取手机分辨率长度大小
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 获得设备html宽度
     *
     * @param context
     * @return
     */
    public static int getDeviceHtmlWidth(Context context) {

        if (isHoneycombTablet(context) && isLandscape(context)) {
            return getTabletWebViewWidth(context);
        }

        int width = (int) (getScreenWidth(context) / context.getResources().getDisplayMetrics().density);

        return width;
    }

    /**
     * 得到dimen定义的大小
     *
     * @param context
     * @param dimenId
     * @return
     */
    public static int getDimension(Context context, int dimenId) {
        return (int) context.getResources().getDimension(dimenId);
    }


    /**
     * 补丁地址
     *
     * @param context
     * @return
     */
    public static String getPatchCacheDir(Context context) {
        final String cacheDir = "/Android/data/" + context.getPackageName() + "/patch/";
        return Environment.getExternalStorageDirectory().getPath() + cacheDir;
    }

    /**
     * dp to px
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    /**
     * px to dp
     *
     * @param context
     * @param px
     * @return
     */
    public static int px2dp(Context context, float px) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return (int) dp;
    }


}
