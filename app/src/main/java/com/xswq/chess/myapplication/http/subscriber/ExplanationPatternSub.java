package com.xswq.chess.myapplication.http.subscriber;

import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.ExplanationPatternApi;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ExplanationPatternSub implements Observer<String>{

    public HttpCallBackLisener mLoginHttpLisener;

    public ExplanationPatternApi mCoursewareListApi;

    public ExplanationPatternSub(ExplanationPatternApi mCoursewareListApi){

        this.mCoursewareListApi = mCoursewareListApi;
        mLoginHttpLisener = mCoursewareListApi.smHttpCallBackLisener;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String gsonString) {
        try {
            if (mLoginHttpLisener != null) {
                JSONObject mjson = new JSONObject(gsonString);
                mLoginHttpLisener.onNext(mjson, mCoursewareListApi.getMethod());
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
