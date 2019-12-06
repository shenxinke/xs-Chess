package com.xswq.chess.myapplication.http.httpparameterapi;

import com.xswq.chess.myapplication.base.BaseApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.RequestService.RegisterService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class PersonalGrowthDataApi extends BaseApi {

    public HttpCallBackLisener smHttpCallBackLisener;
    //用户账号
    private String userId;

    public  PersonalGrowthDataApi (HttpCallBackLisener mHttpCallBackLisener){

        smHttpCallBackLisener =  mHttpCallBackLisener;

    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



    @Override
    public Observable getObservable(Retrofit retrofit) {

        RegisterService mRegisterService = retrofit.create(RegisterService.class);
        Observable Observable = mRegisterService.getGrowthData(getUserId(),getToken(),getUid());
        return Observable;
    }
}
