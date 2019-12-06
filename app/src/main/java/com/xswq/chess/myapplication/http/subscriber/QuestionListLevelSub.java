package com.xswq.chess.myapplication.http.subscriber;

import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.QuestionListLevelApi;
import org.json.JSONException;
import org.json.JSONObject;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class QuestionListLevelSub implements Observer<String> {
    public HttpCallBackLisener questionHttpLisener;
    public QuestionListLevelApi questionListLevelApi;

    public QuestionListLevelSub(QuestionListLevelApi questionListLevelApi){

        this.questionListLevelApi = questionListLevelApi;


        questionHttpLisener = questionListLevelApi.smHttpCallBackLisener;

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
                    questionHttpLisener.onNext( mjson,questionListLevelApi.getMethod());
                }else {
                    questionHttpLisener.onNext( mjson,questionListLevelApi.getMethod());
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(Throwable e) {
        if(questionHttpLisener !=null) {
            questionHttpLisener.onError(e);
        }
    }

    @Override
    public void onComplete() {

    }
}
