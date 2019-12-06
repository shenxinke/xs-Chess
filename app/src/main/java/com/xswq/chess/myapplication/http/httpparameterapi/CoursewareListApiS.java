package com.xswq.chess.myapplication.http.httpparameterapi;

import com.xswq.chess.myapplication.base.BaseApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.RequestService.RegisterService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

class CoursewareListApiS extends BaseApi{

    public String videoName;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public HttpCallBackLisener smHttpCallBackLisener;

    public CoursewareListApiS(HttpCallBackLisener mHttpCallBackLisener){

        smHttpCallBackLisener =  mHttpCallBackLisener;

    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        RegisterService mRegisterService = retrofit.create(RegisterService.class);
        Observable Observable =  mRegisterService.getVideoByName(getVideoName(),getToken(),getUid());
        return Observable;
    }
}
