package com.xswq.chess.myapplication.http.httpparameterapi;

import com.xswq.chess.myapplication.base.BaseApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.RequestService.RegisterService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class SendMessageApi extends BaseApi{

    public HttpCallBackLisener smHttpCallBackLisener;
    //
    //用户账号
    private String userId;

    //账号类型
    private String idFlag;

    private String userTyp;

    public SendMessageApi(HttpCallBackLisener mHttpCallBackLisener){

        smHttpCallBackLisener = mHttpCallBackLisener;

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdFlag() {
        return idFlag;
    }

    public void setIdFlag(String idFlag) {
        this.idFlag = idFlag;
    }

    public String getUserTyp() {
        return userTyp;
    }

    public void setUserTyp(String userTyp) {
        this.userTyp = userTyp;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        RegisterService mRegisterService = retrofit.create(RegisterService.class);
        Observable Observable =  mRegisterService.getSendMessage(getIdFlag(),getUserTyp(),getUserId(),getClient());
        return Observable;
    }

}
