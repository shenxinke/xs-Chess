package com.xswq.chess.myapplication.http.subscriber;

import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.SetupApi;

import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SetupSub implements Observer<String> {

    public HttpCallBackLisener mLoginHttpLisener;
    public SetupApi mSetupApi;
    public SetupSub(SetupApi mbaseapi){

        this.mSetupApi = mbaseapi;
        mLoginHttpLisener = mSetupApi.smHttpCallBackLisener;
    }
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String gsonString) {
        try {
            if (mLoginHttpLisener != null) {
                JSONObject mjson = new JSONObject(gsonString);
                mLoginHttpLisener.onNext(mjson,mSetupApi.getMethod());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {

    }
}
