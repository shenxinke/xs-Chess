package com.xswq.chess.myapplication.http.httpparameterapi;

import com.xswq.chess.myapplication.base.BaseApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.RequestService.RegisterService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class ExplanationPatternApi extends BaseApi {

    private String classid;

    private String titleResource;

    public String pageExplanationSize = "50";

    public HttpCallBackLisener smHttpCallBackLisener;

    public ExplanationPatternApi(HttpCallBackLisener mHttpCallBackLisener){

        smHttpCallBackLisener =  mHttpCallBackLisener;

    }
    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getTitleResource() {
        return titleResource;
    }

    public void setTitleResource(String titleResource) {
        this.titleResource = titleResource;
    }

    public String getPageExplanationSize() {
        return pageExplanationSize;
    }

    public void setPageExplanationSize(String pageExplanationSize) {
        this.pageExplanationSize = pageExplanationSize;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        RegisterService mRegisterService = retrofit.create(RegisterService.class);
        Observable Observable =  mRegisterService.getExplanationPatterns(getClassid(),getPageNum(),getPageExplanationSize(),getTitleResource(),getToken(),getUid());
        return Observable;
    }
}
