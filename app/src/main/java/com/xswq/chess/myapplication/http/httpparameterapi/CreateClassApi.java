package com.xswq.chess.myapplication.http.httpparameterapi;

import com.xswq.chess.myapplication.base.BaseApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.RequestService.RegisterService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class CreateClassApi extends BaseApi{

    public HttpCallBackLisener smHttpCallBackLisener;


    private String teacherId;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
    public CreateClassApi(HttpCallBackLisener mHttpCallBackLisener) {

        smHttpCallBackLisener = mHttpCallBackLisener;

    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        RegisterService mRegisterService = retrofit.create(RegisterService.class);
        Observable Observable =  mRegisterService.getClass(getTeacherId(),getToken(),getUid());
        return Observable;
    }
}
