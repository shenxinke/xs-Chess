package com.xswq.chess.myapplication.activity.start;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.main.SwitchMainActivity;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.BaiDuTokenBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.http.httpparameterapi.LoginApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.retrofitHttp.RetrofitManager;
import com.xswq.chess.myapplication.http.subscriber.XSWQLoginSub;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.LogUtil;
import com.xswq.chess.myapplication.utils.MD5;
import com.xswq.chess.myapplication.utils.PhoneUtil;
import com.xswq.chess.myapplication.utils.PreferenceUtil;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class LoginActivity extends BaseCompatActivity implements HttpCallBackLisener {

    @BindView(R.id.login_usercode)
    EditText login_usercode;
    @BindView(R.id.login_userpassword)
    EditText login_userpassword;
    @BindView(R.id.login_remindcheckbox)
    CheckBox login_remindcheckbox;
    @BindView(R.id.login_back)
    TextView login_back;
    @BindView(R.id.login_titles)
    TextView login_titles;
    @BindView(R.id.login_titleimage)
    ImageView login_titleimage;
    public LoginApi mLoginApi;
    public XSWQLoginSub mXSWQSubscriber;

    @Override
    protected int getLayoutId() {
        return R.layout.login_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.login_bg);
        login_titleimage.setVisibility(View.VISIBLE);
        login_back.setVisibility(View.GONE);
        login_titles.setText("登录");
        boolean isRememberPass = PreferenceUtil.getBoolean("login_remindcheckbox", false);
        if (isRememberPass) {
            String account = PreferenceUtil.getString("usercode", null);
            String password = PreferenceUtil.getString("userpassword", null);
            login_usercode.setText(account);
            login_userpassword.setText(password);
            login_remindcheckbox.setChecked(true);
        }
        checkPublishPermission();
    }

    @Override
    protected void loadData() {
        mLoginApi = new LoginApi(LoginActivity.this);
        mLoginApi.setMethod(ContactUrl.LOGIN);
        mXSWQSubscriber = new XSWQLoginSub(mLoginApi);
    }

    @OnClick({R.id.login_load, R.id.login_regster, R.id.code_delete, R.id.password_delete, R.id.login_forgetpassword})
    public void viewOnClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.login_load:
                if (Util.isClickableSuper()) return;
                if (checkUserInformatiom()) {
                    showProgressDialog(true);
                    RetrofitManager.getRetrofitInstance().handleHttp(mXSWQSubscriber, mLoginApi);
                }
                break;
            case R.id.login_regster:
                intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
                startActivity(intent);
                break;
            case R.id.login_forgetpassword:
                intent = new Intent(LoginActivity.this, FindPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.code_delete:
                login_usercode.setText("");
                break;
            case R.id.password_delete:
                login_userpassword.setText("");
                break;
            default:
                break;
        }
    }

    //校验用户名和密码
    public boolean checkUserInformatiom() {
        String userCode = login_usercode.getText().toString().trim();
        String userPassWord = login_userpassword.getText().toString().trim();
        if (!TextUtils.isEmpty(userCode)) {
            if (!PhoneUtil.isPhoneNumberValid(userCode)) {
                Toast.makeText(this, "手机格式有误，请重新输入！", Toast.LENGTH_LONG).show();
                return false;
            }
            //1.手机2.邮箱
            mLoginApi.setIdFlag(1);
            mLoginApi.setUserId(userCode);
        } else {
            Toast.makeText(this, "请输入账号，不能为空！", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!TextUtils.isEmpty(userPassWord)) {
            if (PhoneUtil.isPasswordNO(login_userpassword.getText().toString().trim())) {
                mLoginApi.setMd5pass(MD5.getMD5(userPassWord));
            } else {
                Toast.makeText(this, "密码不能有特殊字符，长度在6-16位之间！", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "请输入密码，不能为空！", Toast.LENGTH_LONG).show();
            return false;
        }

        if (login_remindcheckbox.isChecked()) {
            PreferenceUtil.put("login_remindcheckbox", true);
            PreferenceUtil.put("usercode", userCode);
            PreferenceUtil.put("userpassword", userPassWord);
        } else {
            PreferenceUtil.remove("login_remindcheckbox");
            PreferenceUtil.remove("usercode");
            PreferenceUtil.remove("userpassword");
        }
        return true;
    }

    @Override
    public void onNext(Object json, String mehtod) {
        try {
            if (mehtod.equals(ContactUrl.LOGIN)) {
                JSONObject error = ((JSONObject) json).getJSONObject("error");
                if (error.getInt("returnCode") == 0) {
                    JSONObject data = ((JSONObject) json).getJSONObject("data");
                    String username = data.getString("username");
                    getBaiDuToken(username);
                } else if ((error).getInt("returnCode") == 10005) {
                    Toast.makeText(this, (error).getString("returnMessage"), Toast.LENGTH_LONG).show();
                    dismissProgressDialog();
                } else if ((error).getInt("returnCode") == 10006) {
                    Toast.makeText(this, (error).getString("returnMessage"), Toast.LENGTH_LONG).show();
                    dismissProgressDialog();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        ToastUtils.show("登录失败，请检查网络！");
        e.printStackTrace();
    }

    private void checkPublishPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(LoginActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(LoginActivity.this,
                    Manifest.permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(LoginActivity.this,
                    Manifest.permission.READ_PHONE_STATE)) {
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(LoginActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(LoginActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (permissions.size() != 0) {
                int WRITE_PERMISSION_REQ_CODE = 100;
                ActivityCompat.requestPermissions(LoginActivity.this, permissions.toArray(new String[0]),
                        WRITE_PERMISSION_REQ_CODE);
            }
        }
    }

    private void getBaiDuToken(final String username) {
        try {
            OkHttpUtils.post().url(ContactUrl.BAIDU_GET_TOKEN_PATH)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                    dismissProgressDialog();
                }

                @Override
                public void onResponse(String response, int id) {
                    dismissProgressDialog();
                    BaiDuTokenBean baiDuTokenBean = GsonUtil.gsonToBean(response, BaiDuTokenBean.class, LoginActivity.this);
                    if (baiDuTokenBean == null) return;
                    if (TextUtils.isEmpty(username)) {
                        startActivity(new Intent(LoginActivity.this, PerfectInformationAcivity.class));
                    } else {
                        String baiDuToken = baiDuTokenBean.getData();
                        LogUtil.e(baiDuToken);
                        SPUtil.put("baiDuToken", baiDuToken);
                        startActivity(new Intent(LoginActivity.this, SwitchMainActivity.class));
                        ToastUtils.show("登录成功!");
                    }
                    dismissProgressDialog();
                    finish();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
