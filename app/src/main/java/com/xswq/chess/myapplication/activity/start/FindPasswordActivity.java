package com.xswq.chess.myapplication.activity.start;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.http.httpparameterapi.FindpasswordApi;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.SendMessageApi;
import com.xswq.chess.myapplication.http.retrofitHttp.RetrofitManager;
import com.xswq.chess.myapplication.http.subscriber.FindPasswordSub;
import com.xswq.chess.myapplication.http.subscriber.SendMessageSub;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.MD5;
import com.xswq.chess.myapplication.utils.PhoneUtil;
import org.json.JSONObject;
import io.reactivex.annotations.Nullable;

public class  FindPasswordActivity extends BaseCompatActivity implements View.OnClickListener,HttpCallBackLisener {

    private EditText findpass_usercode;
    private EditText  findpass_newpassword;
    private EditText  findpass_againpassword;
    private EditText  findpass_checkcode;
    private Button findpass_getcheckcode;
    private Button  findpass_submit;
    private TextView login_back;
    private TextView  login_titles;
    public FindPasswordSub mFindPasswordSubcriber;
    public SendMessageSub mSendMessageSubcriber;
    public  SendMessageApi mSendMessageApi;
    public FindpasswordApi mFindpasswordApi;


    @Override
    protected int getLayoutId() {
        return R.layout.findpassword_layout;
    }

    @Override
    protected void initView() {
        ImageView login_titleimage =  findViewById(R.id.login_titleimage);
        login_titleimage.setVisibility(View.VISIBLE);
        findpass_usercode =  findViewById(R.id.findpass_usercode);
        findpass_newpassword =  findViewById(R.id.findpass_newpassword);
        findpass_againpassword = findViewById(R.id.findpass_againpassword);
        findpass_checkcode =  findViewById(R.id.findpass_checkcode);

        findpass_getcheckcode =  findViewById(R.id.findpass_getcheckcode);
        findpass_getcheckcode.setOnClickListener(this);
        findpass_submit =  findViewById(R.id.findpass_submit);
        findpass_submit.setOnClickListener(this);

        login_back =  findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        login_titles = findViewById(R.id.login_titles);
        login_titles.setText("忘记密码");
        getWindow().getDecorView().setBackgroundResource(R.mipmap.login_bg);
    }

    @Override
    protected void loadData() {
        mSendMessageApi = new SendMessageApi(FindPasswordActivity.this);
        mSendMessageApi.setMethod(ContactUrl.PHONESENDMESSAGE);
        mSendMessageSubcriber = new SendMessageSub(mSendMessageApi);
        mFindpasswordApi = new FindpasswordApi(FindPasswordActivity.this);
        mFindpasswordApi.setMethod(ContactUrl.FINDPASSWORD);
        mFindPasswordSubcriber = new FindPasswordSub(mFindpasswordApi);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.findpass_getcheckcode:
                if(isSendMessage()){
                    RetrofitManager.getRetrofitInstance().handleHttp(mSendMessageSubcriber,mSendMessageApi);
                }

                break;
            case R.id.findpass_submit:
                if(checkUserInformatiom()){
                    RetrofitManager.getRetrofitInstance().handleHttp(mFindPasswordSubcriber,mFindpasswordApi);
                }

                break;
            case R.id.login_back:
                finish();
                break;
        }
    }

    //获取验证码校验
    public boolean isSendMessage(){

        String usercode = findpass_usercode.getText().toString().trim();
        if(!findpass_usercode.getText().toString().trim().equals("")){
            if(usercode.indexOf("@")<0){
                if(!PhoneUtil.isPhoneNumberValid(usercode)){
                    Toast.makeText(this,"手机格式有误，请重新输入！",Toast.LENGTH_LONG).show();
                    return false;
                }
                //存储账号方式 1.手机2.邮箱

                mSendMessageApi.setIdFlag("1");
            }else{
                if(!PhoneUtil.isEmailStringValid(usercode)){
                    Toast.makeText(this,"邮箱格式有误，请重新输入！",Toast.LENGTH_LONG).show();
                    return false;
                }

                mSendMessageApi.setIdFlag("2");
            }

            Log.e("ewqeqwe",usercode);

            mSendMessageApi.setUserId(usercode);
            mSendMessageApi.setUserTyp("2");

        }else{
            Toast.makeText(this,"请输入账号，不能为空！",Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    //校验用户名和密码
    public boolean checkUserInformatiom(){


        String find_usercode = findpass_usercode.getText().toString().trim();
        String find_userpassword = findpass_newpassword.getText().toString().trim();
        String find_againpassword = findpass_againpassword.getText().toString().trim();
        String find_sendmessage = findpass_checkcode.getText().toString().trim();

        if(!find_usercode.equals("")){

            if(find_usercode.indexOf("@")<0){
                if(!PhoneUtil.isPhoneNumberValid(find_usercode)){
                    Toast.makeText(this,"手机格式有误，请重新输入！",Toast.LENGTH_LONG).show();
                    return false;
                }
                mFindpasswordApi.setIdFlag("1");

            }else{
                if(!PhoneUtil.isEmailStringValid(find_usercode)){
                    Toast.makeText(this,"邮箱格式有误，请重新输入！",Toast.LENGTH_LONG).show();
                    return false;
                }
                mFindpasswordApi.setIdFlag("2");
            }

            mFindpasswordApi.setUserId(find_usercode);
        }else{
            Toast.makeText(this,"请输入账号，不能为空！",Toast.LENGTH_LONG).show();
            return false;
        }

         if(find_userpassword.equals("")){

            Toast.makeText(this,"请输入密码，不能为空！",Toast.LENGTH_LONG).show();
            return false;
        }else{
             if(!PhoneUtil.isPasswordNO(find_userpassword)){
                 Toast.makeText(this,"密码不能有特殊字符，长度在6-16位之间！",Toast.LENGTH_LONG).show();
                 return false;
             }
         }

        if(find_againpassword.equals("")){
            Toast.makeText(this,"请输入确认密码，不能为空！",Toast.LENGTH_LONG).show();
            return false;

        }else{
            if(!PhoneUtil.isPasswordNO(find_againpassword)){
                Toast.makeText(this,"密码不能有特殊字符，长度在6-16位之间！",Toast.LENGTH_LONG).show();
                return false;
            }
        }

        if(!find_userpassword.equals(find_againpassword)){
            Toast.makeText(this,"俩次密码输入不一致，请重新输入！",Toast.LENGTH_LONG).show();
            return false;
        }else{
            mFindpasswordApi.setMd5pass(MD5.getMD5(find_userpassword));
        }

        if(find_sendmessage.equals("")){
            Toast.makeText(this,"验证码为空，请输入！",Toast.LENGTH_LONG).show();
            return false;
        }else{
            mFindpasswordApi.setCode(find_sendmessage);
        }

        return true;
    }


    @Override
    public void onNext(Object jsonString, String method) {

        if(method.equals(ContactUrl.PHONESENDMESSAGE)){
            Toast.makeText(FindPasswordActivity.this,"验证码已发送！",Toast.LENGTH_LONG).show();
        }else{
            int  returnCode = ((JSONObject)jsonString).optInt("returnCode");
            if(returnCode==0){
                Toast.makeText(FindPasswordActivity.this,"找回密码成功，请重新登录！",Toast.LENGTH_LONG).show();
                finish();
            }else{
                Toast.makeText(FindPasswordActivity.this,"找回密码失败，请重试！",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(this,""+e.toString(),Toast.LENGTH_LONG).show();
    }
}
