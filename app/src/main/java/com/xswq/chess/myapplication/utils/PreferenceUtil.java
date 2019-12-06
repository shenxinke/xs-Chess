package com.xswq.chess.myapplication.utils;

import android.content.Context;

import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.config.Const;

/**
 * 登录账号密码加密sp
 */
public class PreferenceUtil {

    private static SecuritySharedPreference securitySharedPreference;

    public static SecuritySharedPreference getSp() {
        if (securitySharedPreference == null) {
            securitySharedPreference = new SecuritySharedPreference(XSWQApplication.getInstance(), "xswq_chess", Context.MODE_PRIVATE);
        }
        return securitySharedPreference;
    }

    public static SecuritySharedPreference.Editor getEditor() {
        return getSp().edit();
    }

    /**
     * 或去指定key对应的值，若没有该值则返回指定”默认值“
     */
    public static int getInt(String key, int defaultVal) {
        return getSp().getInt(key, defaultVal);
    }

    public static String getString(String key, String defaultVal) {
        return getSp().getString(key, defaultVal);
    }

    public static boolean getBoolean(String key, boolean defaultVal) {
        return getSp().getBoolean(key, defaultVal);
    }

    public static float getFloat(String key, float defaultVal) {
        return getSp().getFloat(key, defaultVal);
    }

    public static long getLong(String key, long defaultVal) {
        return getSp().getLong(key, defaultVal);
    }

    /**
     * 为给定key设置指定的值
     */
    public static void put(String key, Object object) {
        SecuritySharedPreference.Editor editor = getEditor();

        if (null == object) {
            return;
        }

        String type = object.getClass().getSimpleName();

        if (Const.CONS_STR_STRING.equals(type)) {
            editor.putString(key, (String) object);
        } else if (Const.CONS_STR_INTEGER.equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if (Const.CONS_STR_BOOLEAN.equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if (Const.CONS_STR_FLOAT.equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if (Const.CONS_STR_LONG.equals(type)) {
            editor.putLong(key, (Long) object);
        }
        editor.commit();
    }


    public static void remove(String key) {
        SecuritySharedPreference.Editor editor = getEditor();
        editor.remove(key);
        editor.commit();
    }
}
