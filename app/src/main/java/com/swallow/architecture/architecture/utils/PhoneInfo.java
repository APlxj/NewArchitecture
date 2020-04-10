package com.swallow.architecture.architecture.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;


import java.io.File;
import java.lang.reflect.Method;


public class PhoneInfo {
    private TelephonyManager telephonemanager;
    private String IMSI;
    private Context ctx;

    /**
     * 获取手机国际识别码IMEI
     */
    public PhoneInfo(Context context) {
        ctx = context;
        telephonemanager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
//        String deviceId = telephonemanager.getDeviceId();
    }

    //获取手机IMEI号
    public String getIMEI() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            return Settings.System.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        TelephonyManager telephonyManager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String imei = telephonyManager.getDeviceId();
        return imei;
    }

    //获取手机IMSI号
    public String getIMSI() {
        TelephonyManager mTelephonyMgr = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String imsi = mTelephonyMgr.getSubscriberId();
        return imsi;
    }

    /**
     * @param slotId slotId为卡槽Id，它的值为 0、1；
     * @return
     */
    public static String getIMEI(Context context, int slotId) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Method method = manager.getClass().getMethod("getImei", int.class);
            String imei = (String) method.invoke(manager, slotId);
            return imei;
        } catch (Exception e) {
            return "";
        }
    }

    /*
     * 创建文件夹
     *
     * */
    public static void mkParentDir(String path) {
        File file = new File(path);
        if (!file.exists())
            file.mkdir();
    }


}
