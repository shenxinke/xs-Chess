package com.xswq.chess.myapplication.http.httpparameterapi;

import com.xswq.chess.myapplication.base.BaseApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.RequestService.RegisterService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class UserListApi extends BaseApi {
    public HttpCallBackLisener smHttpCallBackLisener;

    public UserListApi(HttpCallBackLisener mHttpCallBackLisener) {
        smHttpCallBackLisener = mHttpCallBackLisener;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        RegisterService mRegisterService = retrofit.create(RegisterService.class);
        Observable Observable = mRegisterService.getUserList(getToken(),getUid());
        return Observable;
    }
}
