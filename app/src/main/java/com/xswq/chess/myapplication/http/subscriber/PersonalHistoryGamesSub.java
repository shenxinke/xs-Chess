package com.xswq.chess.myapplication.http.subscriber;

import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.PersonalHistoryGamesApi;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PersonalHistoryGamesSub implements Observer<String> {

    public HttpCallBackLisener mLoginHttpLisener;
    public PersonalHistoryGamesApi mbaseApi;

    public PersonalHistoryGamesSub(PersonalHistoryGamesApi mbaseapi){

        this.mbaseApi = mbaseapi;

        mLoginHttpLisener = mbaseApi.smHttpCallBackLisener;



    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String gsonString) {
        if(mLoginHttpLisener!=null){

            try {
                JSONObject mjson = new JSONObject(gsonString);
                JSONObject error = mjson.getJSONObject("error");
                if(error.getInt("returnCode")==0) {
                    JSONObject data = mjson.getJSONObject("data");

                    mLoginHttpLisener.onNext(data,mbaseApi.getMethod());

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
