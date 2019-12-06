package com.xswq.chess.myapplication.http;

public  interface HttpCallBackLisener<T>{

    /**

     * 成功后回调方法

     * @param t

     */
    void  onNext(Object t, String method);

    /**

     * 失败或者错误方法

     * 主动调用，更加灵活

     * @param e

     */

    void onError(Throwable e);


}
