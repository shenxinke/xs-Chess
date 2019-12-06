package com.xswq.chess.myapplication.http.httpparameterapi;

import com.xswq.chess.myapplication.base.BaseApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.RequestService.RegisterService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class PreparelessonsByIdNewApi extends BaseApi {
    private String headLine;
    private String prepareLessinStage;
    public HttpCallBackLisener smHttpCallBackLisener;


    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getPrepareLessinStage() {
        return prepareLessinStage;
    }

    public void setPrepareLessinStage(String prepareLessinStage) {
        this.prepareLessinStage = prepareLessinStage;
    }

    public PreparelessonsByIdNewApi(HttpCallBackLisener mHttpCallBackLisener) {
        smHttpCallBackLisener = mHttpCallBackLisener;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        RegisterService mRegisterService = retrofit.create(RegisterService.class);
        Observable Observable = mRegisterService.getPreparelessonsByIdNew(getHeadLine(),getPrepareLessinStage(),getToken(),getUid());
        return Observable;
    }
}
