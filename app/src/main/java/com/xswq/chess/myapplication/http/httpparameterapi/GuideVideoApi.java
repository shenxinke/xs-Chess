package com.xswq.chess.myapplication.http.httpparameterapi;

import com.xswq.chess.myapplication.base.BaseApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.RequestService.RegisterService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class GuideVideoApi extends BaseApi {

    private String juniorVideoname;
    public HttpCallBackLisener smHttpCallBackLisener;

    public GuideVideoApi(HttpCallBackLisener mHttpCallBackLisener){

        smHttpCallBackLisener = mHttpCallBackLisener;

    }

    public String getJuniorVideoname() {
        return juniorVideoname;
    }

    public void setJuniorVideoname(String juniorVideoname) {
        this.juniorVideoname = juniorVideoname;
    }
    @Override
    public Observable getObservable(Retrofit retrofit) {
        RegisterService mRegisterService = retrofit.create(RegisterService.class);
        Observable Observable =  mRegisterService.getGuideVideo(getJuniorVideoname(),getPageNum(),getPageSize(),getToken(),getUid());
        return Observable;
    }
}
