package com.xswq.chess.myapplication.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Util {
    private static long lastClickTime;

    /**
     * 按钮防爆点击
     *
     * @return b
     */
    public static boolean isClickable() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }


    public static boolean isClickableSuper() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 4000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    //限制edittext字符长度方法：获取字符长度
    public static int getTextLength(String text) {
        int length = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) > 255) {
                length += 2;
            } else {
                length++;
            }
        }
        return length;
    }

    /**
     * 判断字符串是否有效
     *
     * @return 无效返回空字符串
     */
    public static String signString(String strr) {
        if (null == strr) {
            strr = "";
        } else {
            if ("null".equals(strr)) {
                strr = "";
            }
            if ("".equals(strr)) {
                strr = "";
            }
        }
        return strr;
    }

    public static String zeroString(String strr) {
        if (null == strr) {
            strr = "0";
        } else {
            if ("null".equals(strr)) {
                strr = "0";
            }
            if ("".equals(strr)) {
                strr = "0";
            }
        }
        return strr;
    }

    public static Bitmap stringToBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray = Base64.decode(string.split(",")[1], Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 计算棋力
     */
    public static String getChessLevel(int level) {
        String levels = null;
        if (level == 0) {
            levels = "0k";
        } else if (0 < level && level < 26) {
            levels = (26 - level) + "k";
        } else if (level >= 26) {
            levels = (level - 25) + "D";
        }
        return levels;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * String转utf-8
     */
    public static String toUtf8(String str) {
        String strUTF8 = null;
        try {
            strUTF8 = java.net.URLEncoder.encode(str, "utf-8");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return strUTF8;
    }
}
