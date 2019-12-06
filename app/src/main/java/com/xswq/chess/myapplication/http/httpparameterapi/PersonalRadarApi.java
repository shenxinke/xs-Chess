package com.xswq.chess.myapplication.http.httpparameterapi;

import com.xswq.chess.myapplication.base.BaseApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.RequestService.RegisterService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class PersonalRadarApi extends BaseApi {


    public HttpCallBackLisener smHttpCallBackLisener;

    private String leveltype;

    private String userId;
    private int stage;


    public  PersonalRadarApi (HttpCallBackLisener mHttpCallBackLisener){

        smHttpCallBackLisener =  mHttpCallBackLisener;

    }
    public String getLeveltype() {
        return leveltype;
    }

    public void setLeveltype(String leveltype) {
        this.leveltype = leveltype;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }
    @Override
    public Observable getObservable(Retrofit retrofit) {

        RegisterService mRegisterService = retrofit.create(RegisterService.class);
        Observable Observable = mRegisterService.getRadarAll(getUserId(),getStage(),getToken(),getUid());
        return Observable;
    }
}
