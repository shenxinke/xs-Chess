package com.xswq.chess.myapplication.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xswq.chess.myapplication.activity.start.LoginActivity;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.config.Const;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

public class GsonUtil {

    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    private GsonUtil() {
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T gsonToBean(String gsonString, Class<T> cls, Context context) {
        T t = null;
        try {
            if (isSucceed(gsonString, context)) {
                if (gson != null && !TextUtils.isEmpty(gsonString)) {
                    t = gson.fromJson(gsonString, cls);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    private static boolean isSucceed(String gsonString, Context context) {
        try {
            if (!TextUtils.isEmpty(gsonString)) {
                JSONObject object = new JSONObject(gsonString);
                JSONObject error = object.getJSONObject("error");
                int returnCode = error.getInt("returnCode");
                String returnMessage = error.getString("returnMessage");
                if (returnCode == 0) {
                    return true;
                } else if (returnCode == 10048) {
                    ToastUtils.show("用户过期，请重新登录!");
                    PreferencesUtils.putString(XSWQApplication.mContext, "uid", "");
                    Intent intent = new Intent(context,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    return false;
                } else if (returnCode == 1){
                    if(TextUtils.isEmpty(SPUtil.getString("token", ""))){
                        ToastUtils.show("用户过期，请重新登录!");
                        Intent intent = new Intent(context,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        return false;
                    }else {
                        ToastUtils.show(returnMessage);
                        return false;
                    }
                }else {
                    ToastUtils.show(returnMessage);
                    return false;
                }
            } else {
                ToastUtils.show(Const.CONS_STR_ERROR);
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
