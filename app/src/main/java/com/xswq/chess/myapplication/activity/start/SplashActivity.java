package com.xswq.chess.myapplication.activity.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.main.SwitchMainActivity;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.greendao.GreenDaoManager;
import com.xswq.chess.myapplication.greendao.GreenDaoUtils;
import com.xswq.chess.myapplication.greendao.entity.Base;
import com.xswq.chess.myapplication.http.RequestService.RegisterService;
import com.xswq.chess.myapplication.http.retrofitHttp.RetrofitManager;
import com.xswq.chess.myapplication.utils.PreferencesUtils;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import om.org.greendao.gen.DaoSession;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        init();
    }

    private void init() {
        Intent intent;
        String uid = PreferencesUtils.getString(XSWQApplication.mContext, "uid", "");
        String username = PreferencesUtils.getString(XSWQApplication.mContext, "username", "");
        String baiDuToken = SPUtil.getString("baiDuToken", "");
        if (!TextUtils.isEmpty(uid) && !TextUtils.isEmpty(baiDuToken)) {
            if (!TextUtils.isEmpty(Const.STR_FINISH)) {
                if (!TextUtils.isEmpty(username)) {
                    intent = new Intent(SplashActivity.this, SwitchMainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } else {
                    intent = new Intent(SplashActivity.this, PerfectInformationAcivity.class);
                }
                startActivity(intent);
                finish();
            } else {
                setOneLogin();
            }
        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
        getWindow().getDecorView().setBackgroundResource(R.drawable.splash_bg);
    }

    //之后要优化Token的刷新方式
    private void setOneLogin() {
        String login_id = PreferencesUtils.getString(XSWQApplication.mContext, "login_id", "");
        String login_password = PreferencesUtils.getString(XSWQApplication.mContext, "login_password", "");
        int login_idflag = PreferencesUtils.getInt(XSWQApplication.mContext, "login_idflag", 0);

        RegisterService mRegisterService = RetrofitManager.getRetrofitInstance().getRetrofit().create(RegisterService.class);
        Observable Observable = mRegisterService.userLogin(login_idflag, login_id, login_password,"3");
        Observable.subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String gsonString) {
                        try {
                            JSONObject mjson = new JSONObject(gsonString);
                            JSONObject error = mjson.getJSONObject("error");
                            Intent intent;
                            if (error.getInt("returnCode") == 0) {
                                Const.STR_FINISH = "isLoging";
                                DaoSession mDaoSession = GreenDaoManager.getDaoSession();
                                GreenDaoUtils mGreenDaoUtils = new GreenDaoUtils(mDaoSession);
                                JSONObject data = mjson.getJSONObject("data");
                                String token = data.getString("token");
                                String uid = data.getString("id");
                                int usertype = data.optInt("usertype");
                                String imAccid = data.getString("imAccid");
                                String imToken = data.getString("imToken");
                                int level = data.optInt("level");
                                String username = data.getString("username");
                                Base mBase = new Base();
                                mBase.setId(uid);
                                mBase.setToken(token);
                                mBase.setImAccid(imAccid);
                                mBase.setImToken(imToken);
                                mGreenDaoUtils.updateStudent(mBase);
                                PreferencesUtils.putString(XSWQApplication.mContext, "token", token);
                                PreferencesUtils.putString(XSWQApplication.mContext, "imToken", imToken);
                                PreferencesUtils.putString(XSWQApplication.mContext, "username", username);
                                GreenDaoManager.closeConnection();
                                SPUtil.put("uid", uid);
                                SPUtil.put("token", token);
                                if (TextUtils.isEmpty(username)) {
                                    intent = new Intent(SplashActivity.this, PerfectInformationAcivity.class);
                                } else {
                                    intent = new Intent(SplashActivity.this, SwitchMainActivity.class);
                                    ToastUtils.show("登录成功!");
                                }
                                startActivity(intent);
                            } else if (error.getInt("returnCode") == 10006) {
                                ToastUtils.show("此用户被锁定！");
                                PreferencesUtils.putString(XSWQApplication.getmContext(), "uid", "");
                                intent = new Intent(SplashActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else if (error.getInt("returnCode") == 10048) {
                                ToastUtils.show("用户已过期 请重新登陆！");
                                PreferencesUtils.putString(XSWQApplication.mContext, "uid", "");
                                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                            } else {
                                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                            }
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        ToastUtils.show("登录失败,请重新登录!");
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        finish();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
