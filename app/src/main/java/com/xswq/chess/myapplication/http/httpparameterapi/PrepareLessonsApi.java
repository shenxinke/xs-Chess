package com.xswq.chess.myapplication.http.httpparameterapi;

import com.xswq.chess.myapplication.base.BaseApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.RequestService.RegisterService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class PrepareLessonsApi extends BaseApi {
    private String classType;

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public HttpCallBackLisener smHttpCallBackLisener;

    public PrepareLessonsApi(HttpCallBackLisener mHttpCallBackLisener) {
        smHttpCallBackLisener = mHttpCallBackLisener;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        RegisterService mRegisterService = retrofit.create(RegisterService.class);
        // 1主题课 2实战课
        Observable Observable = mRegisterService.getPreparelessons(getClassType(),getPageNum(),getPageSize(),getToken(),getUid(),getUserId());
        return Observable;
    }
}
