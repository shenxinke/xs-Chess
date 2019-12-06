package com.xswq.chess.myapplication.http.subscriber;

import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.FindpasswordApi;
import org.json.JSONException;
import org.json.JSONObject;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FindPasswordSub implements Observer<String> {

    public HttpCallBackLisener mLoginHttpLisener;
    public FindpasswordApi mbaseApi;

    public FindPasswordSub(FindpasswordApi mbaseapi){

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
                    if (error.getInt("returnCode") == 0) {
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
