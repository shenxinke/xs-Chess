package com.xswq.chess.myapplication.utils;

import android.content.Context;

import com.xswq.chess.myapplication.config.Const;
import com.zhangke.websocket.WebSocketHandler;
import com.zhangke.websocket.WebSocketManager;
import com.zhangke.websocket.WebSocketSetting;

public class InitWebSocket {

    public InitWebSocket(Context context) {
        String token = SPUtil.getString("token", "");
        String userId = SPUtil.getString("uid", "");
        String urlString = ContactUrl.BASE_WEB_SCOKET_PATH3 + "/gobangteach/WebSocket?uid=" + userId + "&token=" + token;
        LogUtil.e(urlString);
        if (Const.STR_FINISH_IN_MAIN) {
            WebSocketSetting setting = new WebSocketSetting();
            setting.setConnectUrl(urlString);//必填
            //设置连接超时时间
            setting.setConnectTimeout(100);
            //设置心跳间隔时间
            setting.setConnectionLostTimeout(60);
            //设置断开后的重连次数，可以设置的很大，不会有什么性能上的影响
            setting.setReconnectFrequency(1000);
            //网络状态发生变化后是否重连，
            //需要调用 WebSocketHandler.registerNetworkChangedReceiver(context) 方法注册网络监听广播
            setting.setReconnectWithNetworkChanged(true);
            //通过 init 方法初始化默认的 WebSocketManager 对象
            WebSocketManager manager = WebSocketHandler.init(setting);
            //启动连接
            manager.start();
            //注意，需要在 AndroidManifest 中配置网络状态获取权限
            //注册网路连接状态变化广播
            WebSocketHandler.registerNetworkChangedReceiver(context);
            Const.STR_FINISH_IN_MAIN = false;
        } else {
            WebSocketSetting settingAgain = WebSocketHandler.getDefault().getSetting();
            settingAgain.setConnectUrl(urlString);
            //设置连接超时时间
            settingAgain.setConnectTimeout(100);
            //设置心跳间隔时间
            settingAgain.setConnectionLostTimeout(60);
            //设置断开后的重连次数，可以设置的很大，不会有什么性能上的影响
            settingAgain.setReconnectFrequency(1000);
            //网络状态发生变化后是否重连，
            settingAgain.setReconnectWithNetworkChanged(true);
            WebSocketHandler.getDefault().reconnect(settingAgain);
            WebSocketHandler.registerNetworkChangedReceiver(context);
        }
    }
}
