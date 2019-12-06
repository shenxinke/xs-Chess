package com.xswq.chess.myapplication.http.httpparameterapi;

import com.xswq.chess.myapplication.base.BaseApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.RequestService.RegisterService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class TeachingSystemApi extends BaseApi {


    public HttpCallBackLisener smHttpCallBackLisener;

    private String classType;
    private String teachingType;

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getTeachingType() {
        return teachingType;
    }

    public void setTeachingType(String teachingType) {
        this.teachingType = teachingType;
    }

    public TeachingSystemApi(HttpCallBackLisener mHttpCallBackLisener){

        smHttpCallBackLisener = mHttpCallBackLisener;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        RegisterService mRegisterService = retrofit.create(RegisterService.class);
        Observable Observable =  mRegisterService.getTeacherSystem(getClassType(),getTeachingType(),getPageNum(),getPageSize(),getToken(),getUid());
        return Observable;
    }
}
