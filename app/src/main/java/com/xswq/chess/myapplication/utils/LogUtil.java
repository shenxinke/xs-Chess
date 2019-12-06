package com.xswq.chess.myapplication.utils;

import android.util.Log;
import com.xswq.chess.myapplication.config.Const;

public class LogUtil {
    public static void e(Object object) {
        if (!Const.BOOLEAN_IS_LOG) {
            return ;
        }
        Log.e("aaa", object + "");
    }
}
