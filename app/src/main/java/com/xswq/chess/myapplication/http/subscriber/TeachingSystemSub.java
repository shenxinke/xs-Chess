package com.xswq.chess.myapplication.http.subscriber;

import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.TeachingSystemApi;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TeachingSystemSub implements Observer<String> {

    public HttpCallBackLisener mLoginHttpLisener;
    public TeachingSystemApi mTeachingSystemApi;

    public TeachingSystemSub(TeachingSystemApi mbaseapi){

        this.mTeachingSystemApi = mbaseapi;
        mLoginHttpLisener = mTeachingSystemApi.smHttpCallBackLisener;
    }
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String gsonString) {

        try{
            if (mLoginHttpLisener != null) {
                JSONObject mjson = new JSONObject(gsonString);
                //JSONObject error = mjson.getJSONObject("error");
               // if (error.getInt("returnCode") == 0) {
                    mLoginHttpLisener.onNext(mjson,mTeachingSystemApi.getMethod());
             //   }
            }
        } catch (JSONException e) {
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
