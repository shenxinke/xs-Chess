package com.xswq.chess.myapplication.http.subscriber;

import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.UserListApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserListSub implements Observer<String> {
    public HttpCallBackLisener mLoginHttpLisener;
    public UserListApi mUserListApi;

    public UserListSub(UserListApi mUserListApi) {
        this.mUserListApi = mUserListApi;
        mLoginHttpLisener = mUserListApi.smHttpCallBackLisener;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String gsonString) {
        try {
            if (mLoginHttpLisener != null) {
                JSONObject mjson = new JSONObject(gsonString);
                JSONObject error = mjson.getJSONObject("error");
                if (error.getInt("returnCode") == 0) {
                    JSONArray data = mjson.getJSONArray("data");
                    if (data != null) {
                        mLoginHttpLisener.onNext(gsonString, mUserListApi.getMethod());
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
