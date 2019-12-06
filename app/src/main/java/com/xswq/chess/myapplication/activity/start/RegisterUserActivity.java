package com.xswq.chess.myapplication.activity.start;


import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.RegisterMessageBean;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.RegisterApi;
import com.xswq.chess.myapplication.http.httpparameterapi.SendMessageApi;
import com.xswq.chess.myapplication.http.retrofitHttp.RetrofitManager;
import com.xswq.chess.myapplication.http.subscriber.RegisterSub;
import com.xswq.chess.myapplication.http.subscriber.SendMessageSub;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.MD5;
import com.xswq.chess.myapplication.utils.PhoneUtil;
import com.xswq.chess.myapplication.utils.TimerCountdown;
import com.google.gson.Gson;

import org.json.JSONObject;

public class RegisterUserActivity extends BaseCompatActivity implements View.OnClickListener, HttpCallBackLisener {

    private EditText register_usercode;
    private EditText register_password;
    private EditText register_againpassword;
    private EditText register_checkcode;
    private Button register_getcheckcode;
    private Button register_register;
    private TextView login_back;
    private TextView login_titles;
    public TimerCountdown mTimerCountdown;
    public SendMessageApi mSendMessageApi;
    public RegisterSub mRegisterSubcriber;
    public SendMessageSub mSendMessageSubcriber;
    public RegisterApi mRegisterApi;
    private String md5String = null;
    private String data;

    @Override
    protected int getLayoutId() {
        return R.layout.registeruser_layout;
    }

    @Override
    protected void initView() {
        ImageView login_titleimage = findViewById(R.id.login_titleimage);
        login_titleimage.setVisibility(View.VISIBLE);
        register_usercode = findViewById(R.id.register_usercode);
        register_password = findViewById(R.id.register_password);
        register_againpassword = findViewById(R.id.register_againpassword);
        register_checkcode = findViewById(R.id.register_checkcode);

        register_getcheckcode = findViewById(R.id.register_getcheckcode);
        register_getcheckcode.setOnClickListener(this);
        register_register = findViewById(R.id.register_register);
        register_register.setOnClickListener(this);

        mTimerCountdown = new TimerCountdown(60000, 1000, register_getcheckcode);

        login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        login_titles = findViewById(R.id.login_titles);
        login_titles.setText("注册");
        getWindow().getDecorView().setBackgroundResource(R.mipmap.login_bg);
    }

    @Override
    protected void loadData() {
        mSendMessageApi = new SendMessageApi(RegisterUserActivity.this);
        mSendMessageApi.setMethod(ContactUrl.PHONESENDMESSAGE);
        mSendMessageSubcriber = new SendMessageSub(mSendMessageApi);
        mRegisterApi = new RegisterApi(RegisterUserActivity.this);
        mRegisterApi.setMethod(ContactUrl.REGISTER);
        mRegisterSubcriber = new RegisterSub(mRegisterApi);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.register_getcheckcode:
                if (isSendMessage()) {
                    mTimerCountdown.start();
                    RetrofitManager.getRetrofitInstance().handleHttp(mSendMessageSubcriber, mSendMessageApi);
                }

                break;
            case R.id.register_register:
                if (checkUserInformatiom()) {
                    RetrofitManager.getRetrofitInstance().handleHttp(mRegisterSubcriber, mRegisterApi);
                }
                break;
            case R.id.login_back:
                finish();
                break;
        }
    }


    //校验用户名和密码
    public boolean checkUserInformatiom() {
        String usercode = register_usercode.getText().toString().trim();
        String userpassword = register_password.getText().toString().trim();
        String againpassword = register_againpassword.getText().toString().trim();
        String sendmessage = register_checkcode.getText().toString().trim();
        if (!usercode.equals("")) {
            if (!PhoneUtil.isPhoneNumberValid(usercode)) {
                Toast.makeText(this, "手机格式有误，请重新输入！", Toast.LENGTH_LONG).show();
                return false;
            }
            mRegisterApi.setUserId(usercode);
        } else {
            Toast.makeText(this, "请输入账号，不能为空！", Toast.LENGTH_LONG).show();
            return false;
        }

        if (userpassword.equals("")) {

            Toast.makeText(this, "请输入密码，不能为空！", Toast.LENGTH_LONG).show();
            return false;
        } else {
            if (!PhoneUtil.isPasswordNO(userpassword)) {
                Toast.makeText(this, "密码不能有特殊字符，长度在6-16位之间！", Toast.LENGTH_LONG).show();
                return false;
            }
        }

        if (againpassword.equals("")) {
            Toast.makeText(this, "请输入确认密码，不能为空！", Toast.LENGTH_LONG).show();
            return false;

        } else {
            if (!PhoneUtil.isPasswordNO(againpassword)) {
                Toast.makeText(this, "密码不能有特殊字符，长度在6-16位之间！", Toast.LENGTH_LONG).show();
                return false;
            }
        }

        if (!userpassword.equals(againpassword)) {
            Toast.makeText(this, "俩次密码输入不一致，请重新输入！", Toast.LENGTH_LONG).show();
            return false;
        } else {
            mRegisterApi.setMd5pass(MD5.getMD5(userpassword));
        }

        if (sendmessage.equals("")) {
            Toast.makeText(this, "验证码为空，请重新输入！", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!TextUtils.isEmpty(md5String)) {
            data = MD5.getMD5(usercode + sendmessage).toUpperCase();
            if (!md5String.equals(data)) {
                Toast.makeText(this, "验证码错误，请重新输入！", Toast.LENGTH_LONG).show();
                return false;
            }
        } else {
            Toast.makeText(this, "请先获取验证码！", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public boolean isSendMessage() {

        String usercode = register_usercode.getText().toString().trim();
        if (!usercode.equals("")) {

            if (usercode.indexOf("@") < 0) {
                if (!PhoneUtil.isPhoneNumberValid(usercode)) {
                    Toast.makeText(this, "手机格式有误，请重新输入！", Toast.LENGTH_LONG).show();
                    return false;
                }
                //存储账号方式 1,.手机2.邮箱

                mSendMessageApi.setIdFlag("1");
            } else {
                if (!PhoneUtil.isEmailStringValid(usercode)) {
                    Toast.makeText(this, "邮箱格式有误，请重新输入！", Toast.LENGTH_LONG).show();
                    return false;
                }

                mSendMessageApi.setIdFlag("2");
            }

            mSendMessageApi.setUserId(usercode);
            mSendMessageApi.setUserTyp("1");

        } else {
            Toast.makeText(this, "请输入账号，不能为空！", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }


    @Override
    public void onNext(Object jsonString, String method) {
        if (method.equals(ContactUrl.PHONESENDMESSAGE)) {
            Gson gson = new Gson();
            String str = String.valueOf(jsonString);
            RegisterMessageBean registerMessageBean = gson.fromJson(str, RegisterMessageBean.class);
            md5String = registerMessageBean.getData();
            Toast.makeText(this, "验证码已发送！", Toast.LENGTH_LONG).show();
        } else {
            int returnCode = ((JSONObject) jsonString).optInt("returnCode");
            if (returnCode == 0) {
                Toast.makeText(RegisterUserActivity.this, "注册成功，请登录！", Toast.LENGTH_LONG).show();
                finish();
            } else if (returnCode == 10049) {
                Toast.makeText(RegisterUserActivity.this, "此用户已存在！", Toast.LENGTH_LONG).show();
            } else if (returnCode == 10059) {
                Toast.makeText(RegisterUserActivity.this, "此用户已存在！", Toast.LENGTH_LONG).show();
            } else if (returnCode == 10061) {
                Toast.makeText(RegisterUserActivity.this, "此用户已存在！", Toast.LENGTH_LONG).show();
            } else if (returnCode == 10062) {
                Toast.makeText(RegisterUserActivity.this, "此用户已存在！", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(RegisterUserActivity.this, "注册失败，请重试！", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }
}
