package com.xswq.chess.myapplication.http.httpparameterapi;

import com.xswq.chess.myapplication.base.BaseApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.RequestService.RegisterService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class QuestionTypeLevelApi extends BaseApi {

    public HttpCallBackLisener smHttpCallBackLisener;

    private int questionType;

    private String questionlevel;
    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public String getQuestionlevel() {
        return questionlevel;
    }

    public void setQuestionlevel(String questionlevel) {
        this.questionlevel = questionlevel;
    }

    public QuestionTypeLevelApi(HttpCallBackLisener mHttpCallBackLisener){

        smHttpCallBackLisener =  mHttpCallBackLisener;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        RegisterService questionService = retrofit.create(RegisterService.class);
        Observable Observable = questionService.getLevelListDetail(getQuestionlevel(),getQuestionType(),getToken(),getUid());
        return Observable;
    }
}
