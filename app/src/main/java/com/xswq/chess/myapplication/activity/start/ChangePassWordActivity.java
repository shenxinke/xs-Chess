package com.xswq.chess.myapplication.activity.start;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseBean;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.MD5;
import com.xswq.chess.myapplication.utils.PhoneUtil;
import com.xswq.chess.myapplication.utils.PreferenceUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class ChangePassWordActivity extends BaseCompatActivity {

    private EditText newPassword;
    private EditText newPasswordAgain;
    private String stringPassWord;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_password_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.navigation_bg);
        newPassword = findViewById(R.id.new_password);
        newPasswordAgain = findViewById(R.id.new_password_again);

        TextView loginBack = findViewById(R.id.login_back);
        loginBack.setVisibility(View.VISIBLE);
        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView loginTitle = findViewById(R.id.login_titles);
        loginTitle.setText(R.string.change_password);

        Button loginLoad = findViewById(R.id.login_load);
        loginLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(newPasswordAgain.getWindowToken(), 0);

                stringPassWord = newPassword.getText().toString();
                String stringNewPasswordAgain = newPasswordAgain.getText().toString();
                if (TextUtils.isEmpty(stringPassWord)) {
                    ToastUtils.show("请输入新密码");
                    return;
                }
                if (!PhoneUtil.isPasswordNO(stringPassWord)) {
                    ToastUtils.show("密码不能有特殊字符，长度在6-16位之间！");
                    return;
                }
                if (TextUtils.isEmpty(stringNewPasswordAgain)) {
                    ToastUtils.show("请再次输入密码");
                    return;
                }

                if (!stringPassWord.equals(stringNewPasswordAgain)) {
                    ToastUtils.show("两次密码输入不一致");
                    return;
                }
                String md5 = MD5.getMD5(stringPassWord);
                if (!TextUtils.isEmpty(md5)) {
                    updataPassWord(md5);
                }
            }
        });
    }

    @Override
    protected void loadData() {

    }

    private void updataPassWord(String password) {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_UPDATE_PWD_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("password", password)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    BaseBean baseBean = GsonUtil.gsonToBean(response, BaseBean.class, ChangePassWordActivity.this);
                    if (baseBean != null) {
                        PreferenceUtil.remove("userpassword");
                        PreferenceUtil.put("userpassword", stringPassWord);
                        ToastUtils.show("修改成功");
                        finish();
                    }
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
