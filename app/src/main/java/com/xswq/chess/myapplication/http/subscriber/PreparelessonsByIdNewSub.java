package com.xswq.chess.myapplication.http.subscriber;

import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.PreparelessonsByIdNewApi;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PreparelessonsByIdNewSub implements Observer<String> {
    public HttpCallBackLisener mLoginHttpLisener;
    public PreparelessonsByIdNewApi preparelessonsByIdNewApi;

    public PreparelessonsByIdNewSub(PreparelessonsByIdNewApi preparelessonsByIdNewApi) {
        this.preparelessonsByIdNewApi = preparelessonsByIdNewApi;
        mLoginHttpLisener = preparelessonsByIdNewApi.smHttpCallBackLisener;
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
                    mLoginHttpLisener.onNext(gsonString, preparelessonsByIdNewApi.getMethod());
                }
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
