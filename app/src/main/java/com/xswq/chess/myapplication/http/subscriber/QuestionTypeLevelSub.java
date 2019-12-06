package com.xswq.chess.myapplication.http.subscriber;

import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.QuestionTypeLevelApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class QuestionTypeLevelSub implements Observer<String> {


    public HttpCallBackLisener questionHttpLisener;
    public QuestionTypeLevelApi mQuestionTypeLevelApi;

    public QuestionTypeLevelSub(QuestionTypeLevelApi mQuestionTypeLevelApi){

        this.mQuestionTypeLevelApi = mQuestionTypeLevelApi;


        questionHttpLisener = mQuestionTypeLevelApi.smHttpCallBackLisener;

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String gsonString) {
        try {
            if (questionHttpLisener != null) {
                JSONObject mjson = new JSONObject(gsonString);
                JSONObject error = mjson.getJSONObject("error");
                if(error.getInt("returnCode")==0){
                    JSONArray data = mjson.getJSONArray("data");
                    questionHttpLisener.onNext( data,mQuestionTypeLevelApi.getMethod());
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
