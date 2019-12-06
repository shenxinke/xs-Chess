package com.xswq.chess.myapplication.phoneiscall;

import java.io.Serializable;

public interface Observer<T> extends Serializable {

    /**
     * 通知产生后的回调函数
     *
     * @param t 事件参数
     */
    void onEvent(T t);
}