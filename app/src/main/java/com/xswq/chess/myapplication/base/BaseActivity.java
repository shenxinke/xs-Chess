package com.xswq.chess.myapplication.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.gyf.immersionbar.ImmersionBar;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.main.SettingsActivity;
import com.xswq.chess.myapplication.activity.main.SwitchMainActivity;
import com.xswq.chess.myapplication.activity.start.LoginActivity;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.dialog.CommonProgressDialog;
import com.xswq.chess.myapplication.utils.PreferencesUtils;
import com.xswq.chess.myapplication.utils.SPUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseActivity extends Activity {
    public String userId;
    public String orgNo;
    public String token;
    public int userType;

    private CommonProgressDialog onLineDialog;//loading动画

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = SPUtil.getString("uid", "");
        token = SPUtil.getString("token", "");
        userType = SPUtil.getInt("userType", 0);
        orgNo = SPUtil.getString("orgName", "");
        ImmersionBar.with(this).fitsSystemWindows(true).addTag("BaseMainActivity").init();
    }

    /**
     * 还原字体大小
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1) {
            getResources();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        //非默认值
        if (res.getConfiguration().fontScale != 1) {
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

    /**
     * 返回登录页面
     */
    public void quiteApp(Context activityContext, String content) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
        PreferencesUtils.putString(XSWQApplication.mContext, "uid", "");
        Intent intent = new Intent(activityContext,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    /**
     * 打开进度条
     */
    public void showProgressDialog(boolean isCancelable) {
        try {
            if (null == onLineDialog) {
                onLineDialog = CommonProgressDialog.showDialog(this,isCancelable);
            }
            if (!this.isFinishing()) {
                try {
                    onLineDialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭进度条
     */
    public void dismissProgressDialog() {
        if (null != onLineDialog) {
            onLineDialog.dismiss();
        }
    }

}
