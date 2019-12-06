package com.xswq.chess.myapplication.http.subscriber;

import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.SendMessageApi;

import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SendMessageSub implements Observer<String> {


    public HttpCallBackLisener mLoginHttpLisener;
    public SendMessageApi mbaseApi;

    public SendMessageSub(SendMessageApi mbaseapi){

        this.mbaseApi = mbaseapi;

        mLoginHttpLisener = mbaseApi.smHttpCallBackLisener;


    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String gsonString) {
        try {
            if (mLoginHttpLisener != null) {

                JSONObject mjson = new JSONObject(gsonString);
                mLoginHttpLisener.onNext(mjson, mbaseApi.getMethod());

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        if(mLoginHttpLisener!=null) {
            mLoginHttpLisener.onError(e);
        }
    }

    @Override
    public void onComplete() {

    }
}
