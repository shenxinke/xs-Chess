package com.xswq.chess.myapplication.http.subscriber;

import android.text.TextUtils;

import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.RegisterApi;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RegisterSub implements Observer<String> {


    public HttpCallBackLisener mLoginHttpLisener;
    public RegisterApi mbaseApi;

    public RegisterSub(RegisterApi mbaseapi){

        this.mbaseApi = mbaseapi;

        mLoginHttpLisener = mbaseApi.smHttpCallBackLisener;



    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String gsonString) {

        try{
            if (mLoginHttpLisener != null) {
                if (!TextUtils.isEmpty(gsonString)) {
                    JSONObject mjson = new JSONObject(gsonString);
                    JSONObject error = mjson.getJSONObject("error");
                    mLoginHttpLisener.onNext(error,mbaseApi.getMethod());
                }
            }
        } catch (JSONException e) {
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
