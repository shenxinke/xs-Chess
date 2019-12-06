package com.xswq.chess.myapplication.http.httpparameterapi;

import com.xswq.chess.myapplication.base.BaseApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.RequestService.RegisterService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class PrepareLessonsByIdApi extends BaseApi {
    private String lessonsId;

    public String getLessonsId() {
        return lessonsId;
    }

    public void setLessonsId(String lessonsId) {
        this.lessonsId = lessonsId;
    }

    public HttpCallBackLisener smHttpCallBackLisener;

    public PrepareLessonsByIdApi(HttpCallBackLisener mHttpCallBackLisener) {
        smHttpCallBackLisener = mHttpCallBackLisener;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        RegisterService mRegisterService = retrofit.create(RegisterService.class);
        Observable Observable = mRegisterService.getPreparelessonsById(getLessonsId(),getToken(),getUid());
        return Observable;
    }
}
