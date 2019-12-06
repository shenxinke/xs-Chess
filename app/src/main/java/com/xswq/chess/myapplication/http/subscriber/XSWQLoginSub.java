package com.xswq.chess.myapplication.http.subscriber;

import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.greendao.GreenDaoManager;
import com.xswq.chess.myapplication.greendao.GreenDaoUtils;
import com.xswq.chess.myapplication.greendao.entity.Base;
import com.xswq.chess.myapplication.greendao.entity.Detail;
import com.xswq.chess.myapplication.http.httpparameterapi.LoginApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.utils.PreferencesUtils;
import com.xswq.chess.myapplication.utils.SPUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import om.org.greendao.gen.DaoSession;

public class XSWQLoginSub implements Observer<String> {

    public HttpCallBackLisener mLoginHttpLisener;

    public LoginApi mLoginApi;

    public XSWQLoginSub(LoginApi mLoginApi) {
        this.mLoginApi = mLoginApi;
        mLoginHttpLisener = mLoginApi.smHttpCallBackLisener;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String gsonString) {

        if (mLoginHttpLisener != null) {
            try {
                JSONObject mjson = new JSONObject(gsonString);
                JSONObject error = mjson.getJSONObject("error");
                if (error.getInt("returnCode") == 0) {
                    JSONObject data = mjson.getJSONObject("data");
                    boolean flags1 = false;
                    boolean flags2 = false;
                    DaoSession mDaoSession = GreenDaoManager.getDaoSession();
                    GreenDaoUtils mGreenDaoUtils = new GreenDaoUtils(mDaoSession);

                    Detail mDetail = new Detail();
                    mDetail.setUserid(data.getString("id"));
                    mDetail.setUsername(data.getString("username"));

                    Base mBase = new Base();
                    mBase.setId(data.getString("id"));
                    mBase.setToken(data.getString("token"));
                    mBase.setImAccid(data.getString("imAccid"));
                    mBase.setImToken(data.getString("imToken"));
                    int level = data.optInt("level");
                    int usertype = data.optInt("usertype");

                    if (mGreenDaoUtils.queryDetailOneData(data.getString("id"))) {

                        flags1 = mGreenDaoUtils.insertEntity(mDetail);
                    } else {
                        mDetail.setId(mGreenDaoUtils.getDetailUser(data.getString("id")).getId());
                        flags1 = mGreenDaoUtils.updateStudent(mDetail);

                    }

                    if (mGreenDaoUtils.getBaseUser(data.getString("id")) == null) {

                        flags2 = mGreenDaoUtils.insertEntity(mBase);
                    } else {
                        mBase.setIdIndex(mGreenDaoUtils.getBaseUser(data.getString("id")).getIdIndex());
                        flags2 = mGreenDaoUtils.updateStudent(mBase);
                    }

                    PreferencesUtils.putString(XSWQApplication.mContext, "uid", data.getString("id"));
                    PreferencesUtils.putString(XSWQApplication.mContext, "login_id", mLoginApi.getUserId());
                    PreferencesUtils.putString(XSWQApplication.mContext, "login_password", mLoginApi.getMd5pass());
                    PreferencesUtils.putInt(XSWQApplication.mContext, "login_idflag", mLoginApi.getIdFlag());
                    PreferencesUtils.putString(XSWQApplication.mContext, "token", data.getString("token"));
                    PreferencesUtils.putString(XSWQApplication.mContext, "imToken", data.getString("imToken"));
                    SPUtil.put("uid", data.getString("id"));
                    SPUtil.put("token", data.getString("token"));
                    SPUtil.put("userType", usertype);
                    //设置标签（根据userType）
                    Set<String> tagSet = new HashSet<>();
                    tagSet.add(String.valueOf(data.optInt("usertype")));
                    PreferencesUtils.putSet(XSWQApplication.mContext, "set", tagSet);
                    GreenDaoManager.closeConnection();
                }
                mLoginHttpLisener.onNext(mjson, mLoginApi.getMethod());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onError(Throwable e) {
        if (mLoginHttpLisener != null) {
            mLoginHttpLisener.onError(e);
        }
    }

    @Override
    public void onComplete() {

    }
    protected static final String TAG = "LoginActivity";
}
