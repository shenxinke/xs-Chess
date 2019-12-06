package com.xswq.chess.myapplication.utils;

import android.content.Context;
import android.text.TextUtils;

import com.xswq.chess.myapplication.base.BaseBean;
import com.xswq.chess.myapplication.config.Const;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 模块使用统计接口
 */
public class StatisticsUtil {

    public static void getStatistics(String touken, String userId, final int module, final Context context) {
        try {
            OkHttpUtils.get().url(ContactUrl.GET_STATISTICS)
                    .addParams("token", touken)
                    .addParams("uid", userId)
                    .addParams("userId", userId)
                    .addParams("module", String.valueOf(module))
                    .addParams("terminalType", "3")
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                   ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response)) {
                        BaseBean baseBean = GsonUtil.gsonToBean(response, BaseBean.class, context);
                        if (baseBean != null) {
                            LogUtil.e(module + "StatisticsResponse" + response);
                        }
                    }
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
