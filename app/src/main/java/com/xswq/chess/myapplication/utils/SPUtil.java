package com.xswq.chess.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.config.Const;

public class SPUtil {

    private static SecuritySharedPreference securitySharedPreference;

    public static SecuritySharedPreference getSp() {
        if (securitySharedPreference == null) {
            securitySharedPreference = new SecuritySharedPreference(XSWQApplication.getInstance(), "security_prefs", Context.MODE_PRIVATE);
        }
        return securitySharedPreference;
    }

    private static SecuritySharedPreference.Editor getEditor() {
        return getSp().edit();
    }


    /**
     * 清空SP里所以数据
     */
    public void clear() {
        SharedPreferences.Editor editor = getEditor();
        editor.clear();
        editor.commit();
    }

    /**
     * 删除SP里指定key对应的数据项
     *
     * @param key
     */
    public static void remove(String key) {
        SharedPreferences.Editor editor = getEditor();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 获取SP数据里指定key对应的value。如果key不存在，则返回默认值defValue。
     *
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(String key, String defValue) {
        return getSp().getString(key, defValue);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return getSp().getBoolean(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return getSp().getFloat(key, defValue);
    }

    public static int getInt(String key, int defValue) {
        return getSp().getInt(key, defValue);
    }

    public static long getLong(String key, long defValue) {
        return getSp().getLong(key, defValue);
    }

    /**
     * 判断SP是否包含特定key的数据
     *
     * @param key
     * @return
     */
    public boolean contains(String key) {
        return getSp().contains(key);
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

}
