package com.xswq.chess.myapplication.http.httpparameterapi;

import com.xswq.chess.myapplication.base.BaseApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.RequestService.RegisterService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class PersonalEdituserinforAPI extends BaseApi {


    public HttpCallBackLisener smHttpCallBackLisener;

    //用户账号
    private String userId;
    private	String	address	;
    private	String	birthday	;
    private	int	sex	;
    private	String	username	;
    private	String	classInfoId	;
    private	String	headImg	;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClassInfoId() {
        return classInfoId;
    }

    public void setClassInfoId(String classInfoId) {
        this.classInfoId = classInfoId;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }



    public PersonalEdituserinforAPI(HttpCallBackLisener mHttpCallBackLisener){

        smHttpCallBackLisener =  mHttpCallBackLisener;

    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        RegisterService mRegisterService = retrofit.create(RegisterService.class);
        Observable Observable = mRegisterService.getupdateuserhead(getUserId(),getAddress(),getBirthday(),getSex(),getUsername(),getHeadImg(),getToken(),getUid());
        return Observable;
    }
}
