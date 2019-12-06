package com.xswq.chess.myapplication.http.subscriber;

import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.PersonalEdituserinforAPI;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PersonalEdituserinforSub implements Observer<String> {


    public HttpCallBackLisener mLoginHttpLisener;
    public PersonalEdituserinforAPI mbaseApi;

    public PersonalEdituserinforSub(PersonalEdituserinforAPI mbaseapi){

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
                JSONObject mjson = new JSONObject(gsonString);
                JSONObject error = mjson.getJSONObject("error");
                mLoginHttpLisener.onNext(error, mbaseApi.getMethod());

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
