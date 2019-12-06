package com.xswq.chess.myapplication.http.subscriber;

import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.GuideVideoApi;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class GuideVideoSub implements Observer<String> {

    public HttpCallBackLisener mLoginHttpLisener;
    public GuideVideoApi mGuideVideoApi;

    public GuideVideoSub(GuideVideoApi mGuideVideoApi){

        this.mGuideVideoApi = mGuideVideoApi;

        mLoginHttpLisener = mGuideVideoApi.smHttpCallBackLisener;

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String gsonString) {
        try {
            if (mLoginHttpLisener != null) {
                JSONObject mjson = new JSONObject(gsonString);
                mLoginHttpLisener.onNext(mjson, mGuideVideoApi.getMethod());

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
