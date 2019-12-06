package com.xswq.chess.myapplication.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.xswq.chess.myapplication.activity.main.SwitchMainActivity;
import com.xswq.chess.myapplication.activity.navigationentrance.friendlist.AddFriendsScoketActivity;
import com.xswq.chess.myapplication.activity.onlinegame.inform.EntryNoticeActivity;
import com.xswq.chess.myapplication.activity.onlinegame.inform.GetRulesActivity;
import com.xswq.chess.myapplication.activity.onlinegame.inform.MatchByeActivity;
import com.xswq.chess.myapplication.activity.onlinegame.inform.RefuseActivity;
import com.xswq.chess.myapplication.activity.onlinegame.inform.ResultTheMatchActivity;
import com.xswq.chess.myapplication.activity.onlinegame.inform.StudentsSubmitDecisionActivity;
import com.xswq.chess.myapplication.activity.onlinegame.inform.TenMinutesActivity;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.bean.AddFriendMessageBean;
import com.xswq.chess.myapplication.bean.ScoketMessageBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.config.RxCode;
import com.xswq.chess.myapplication.greendao.GreenDaoManager;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.CookiesManager;
import com.xswq.chess.myapplication.utils.InitWebSocket;
import com.xswq.chess.myapplication.utils.LogUtil;
import com.xswq.chess.myapplication.utils.OkHttpLog;
import com.facebook.stetho.Stetho;
import com.tencent.smtt.sdk.QbSdk;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.zhangke.websocket.SimpleListener;
import com.zhangke.websocket.SocketListener;
import com.zhangke.websocket.WebSocketHandler;
import com.zhangke.websocket.response.ErrorResponse;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

import gorden.rxbus2.RxBus;
import okhttp3.OkHttpClient;

public class XSWQApplication extends Application {

    private static XSWQApplication instance;

    public static Context mContext;

    private int count;


    public static Context getmContext() {
        return mContext;
    }

    private ActivityLifecycleCallbacks activityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityStopped(@NonNull Activity activity) {
            count--;
            if (count == 0) {
                RxBus.get().send(RxCode.CODE_CLOSE_SCOKET_MESSAGE);
            }
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {
            if (count == 0) {
                RxBus.get().send(RxCode.CODE_CONNECT_SCOKET_MESSAGE);
            }
            count++;
        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        GreenDaoManager.getInstance(mContext);
        Stetho.initializeWithDefaults(this);
        instance = this;
        initX5WebView();
        initOkHttp();
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    private void initX5WebView() {
        //非wifi情况下，主动下载x5内核
        QbSdk.setDownloadWithoutWifi(true);
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.e("XSWQ", String.valueOf(arg0));
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static XSWQApplication getInstance() {
        return instance;
    }

    private void initOkHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new OkHttpLog("OkHttpUtils"))
                .connectTimeout(15000L, TimeUnit.MILLISECONDS)
                .readTimeout(15000L, TimeUnit.MILLISECONDS)
                .writeTimeout(15000L, TimeUnit.MILLISECONDS)
                //cookie管理
                .cookieJar(new CookiesManager())
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }


}
