package com.xswq.chess.myapplication.http.httpparameterapi;

import com.xswq.chess.myapplication.base.BaseApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.RequestService.RegisterService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class SetupApi extends BaseApi{

    private int matchOpts;
    private int soundOpts;
    private int imOpts;
    public HttpCallBackLisener smHttpCallBackLisener;
    public int getMatchOpts() {
        return matchOpts;
    }

    public void setMatchOpts(int matchOpts) {
        this.matchOpts = matchOpts;
    }

    public int getSoundOpts() {
        return soundOpts;
    }

    public void setSoundOpts(int soundOpts) {
        this.soundOpts = soundOpts;
    }

    public int getImOpts() {
        return imOpts;
    }

    public void setImOpts(int imOpts) {
        this.imOpts = imOpts;
    }

    public SetupApi(HttpCallBackLisener mHttpCallBackLisener){
        smHttpCallBackLisener = mHttpCallBackLisener;
    }
    @Override
    public Observable getObservable(Retrofit retrofit) {
        RegisterService mRegisterService = retrofit.create(RegisterService.class);
        Observable Observable =  mRegisterService.getSetupMyOption(getUserId(),getMatchOpts(),getSoundOpts(),getImOpts(),getToken(),getUid());
        return Observable;
    }
}
