package com.xswq.chess.myapplication.http.subscriber;

import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.PersonalRadarApi;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PersonalRadarallSub implements Observer<String> {

    public HttpCallBackLisener mLoginHttpLisener;
    public PersonalRadarApi mbaseApi;

    public PersonalRadarallSub(PersonalRadarApi mbaseapi){

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
                JSONObject error = mjson.getJSONObject("error");
                if (error.getInt("returnCode") == 0) {
                    if( mjson.getJSONObject("data")!=null){
                        JSONObject data = mjson.getJSONObject("data");
                        data.put("leveltype",mbaseApi.getLeveltype());
                        mLoginHttpLisener.onNext(data, mbaseApi.getMethod());
                    }
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
