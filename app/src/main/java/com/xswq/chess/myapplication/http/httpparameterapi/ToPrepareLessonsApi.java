package com.xswq.chess.myapplication.http.httpparameterapi;

import com.xswq.chess.myapplication.base.BaseApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.RequestService.RegisterService;
import io.reactivex.Observable;
import retrofit2.Retrofit;

public class ToPrepareLessonsApi  extends BaseApi{
    private String userId;
    private String tempId;
    private String myHandouts1;
    private String myHandouts2;
    private String myHandouts3;
    private String myHandouts4;
    private String myHandouts5;
    private String myHandouts6;
    private String myHandouts7;

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    public String getMyHandouts1() {
        return myHandouts1;
    }

    public void setMyHandouts1(String myHandouts1) {
        this.myHandouts1 = myHandouts1;
    }

    public String getMyHandouts2() {
        return myHandouts2;
    }

    public void setMyHandouts2(String myHandouts2) {
        this.myHandouts2 = myHandouts2;
    }

    public String getMyHandouts3() {
        return myHandouts3;
    }

    public void setMyHandouts3(String myHandouts3) {
        this.myHandouts3 = myHandouts3;
    }

    public String getMyHandouts4() {
        return myHandouts4;
    }

    public void setMyHandouts4(String myHandouts4) {
        this.myHandouts4 = myHandouts4;
    }

    public String getMyHandouts5() {
        return myHandouts5;
    }

    public void setMyHandouts5(String myHandouts5) {
        this.myHandouts5 = myHandouts5;
    }

    public String getMyHandouts6() {
        return myHandouts6;
    }

    public void setMyHandouts6(String myHandouts6) {
        this.myHandouts6 = myHandouts6;
    }

    public String getMyHandouts7() {
        return myHandouts7;
    }

    public void setMyHandouts7(String myHandouts7) {
        this.myHandouts7 = myHandouts7;
    }

    public HttpCallBackLisener smHttpCallBackLisener;

    public ToPrepareLessonsApi(HttpCallBackLisener mHttpCallBackLisener) {
        smHttpCallBackLisener = mHttpCallBackLisener;
    }
    @Override
    public Observable getObservable(Retrofit retrofit) {
        RegisterService mRegisterService = retrofit.create(RegisterService.class);
        Observable Observable = mRegisterService.getCreatePreareLessonsNew(getUserId(),getTempId(),getMyHandouts1(),getMyHandouts2(),getMyHandouts3(),getMyHandouts4(),getMyHandouts5(),getMyHandouts6(),getMyHandouts7(),getToken(),getUid());
        return Observable;
    }


}
