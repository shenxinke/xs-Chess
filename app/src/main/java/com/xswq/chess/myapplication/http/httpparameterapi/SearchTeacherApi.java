package com.xswq.chess.myapplication.http.httpparameterapi;

import com.xswq.chess.myapplication.base.BaseApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.RequestService.RegisterService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class SearchTeacherApi extends BaseApi{

    public HttpCallBackLisener smHttpCallBackLisener;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public SearchTeacherApi(HttpCallBackLisener mHttpCallBackLisener) {

        smHttpCallBackLisener = mHttpCallBackLisener;

    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        RegisterService mRegisterService = retrofit.create(RegisterService.class);
        Observable Observable =  mRegisterService.geTeachers(getUserId(),getToken(),getUid());
        return Observable;
    }
}
