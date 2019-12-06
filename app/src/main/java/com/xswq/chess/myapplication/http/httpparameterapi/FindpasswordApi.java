package com.xswq.chess.myapplication.http.httpparameterapi;

import com.xswq.chess.myapplication.base.BaseApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.RequestService.RegisterService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class FindpasswordApi extends BaseApi {


    public HttpCallBackLisener smHttpCallBackLisener;
    //用户账号
    private String userId;
    //用户密码
    private String md5pass;
    //账号类型
    private String idFlag;
    //验证码
    private String code;


    public FindpasswordApi(HttpCallBackLisener mHttpCallBackLisener){

        smHttpCallBackLisener = mHttpCallBackLisener;

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMd5pass() {
        return md5pass;
    }

    public void setMd5pass(String md5pass) {
        this.md5pass = md5pass;
    }

    public String getIdFlag() {
        return idFlag;
    }

    public void setIdFlag(String idFlag) {
        this.idFlag = idFlag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        RegisterService mRegisterService = retrofit.create(RegisterService.class);
        Observable Observable =  mRegisterService.getFindPassword(getCode(),getIdFlag(),getMd5pass(),getUserId());
        return Observable;
    }


}
