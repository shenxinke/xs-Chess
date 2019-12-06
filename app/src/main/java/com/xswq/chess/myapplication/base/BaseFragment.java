package com.xswq.chess.myapplication.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xswq.chess.myapplication.activity.main.SwitchMainActivity;
import com.xswq.chess.myapplication.activity.start.LoginActivity;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.dialog.CommonProgressDialog;
import com.xswq.chess.myapplication.utils.PreferencesUtils;
import com.xswq.chess.myapplication.utils.SPUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected boolean isInit = false;//视图是否已经初初始化
    protected boolean isLoad = false;//是否加载
    protected final String TAG = "BaseFragment";
    private View view;//视图
    private CommonProgressDialog onLineDialog;//进度条显示
    public String userId;
    public String token;
    public int userType;
    public String orgNo;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(setContentView(), container, false);
            unbinder = ButterKnife.bind(this, view);
            isInit = true;
            userId = SPUtil.getString("uid", "");
            token = SPUtil.getString("token", "");
            userType = SPUtil.getInt("userType", 0);
            orgNo = SPUtil.getString("orgName", "");
            initData();
            /**初始化的时候去加载数据**/
            isCanLoadData();
        }
        return view;
    }

    /**
     * 视图是否可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    /**
     * 是否可以加载数据
     */
    private void isCanLoadData() {
        if (!isInit) {
            return;
        }
        if (getUserVisibleHint()) {
            startLoad();
            isLoad = true;
        } else {
            if (isLoad) {
                stopLoad();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
        isLoad = false;
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    protected View getContentView() {
        return view;
    }

    /**
     * findViewById
     */
    protected <T extends View> T findViewById(int id) {
        return (T) getContentView().findViewById(id);
    }

    protected abstract int setContentView();//显示的布局

    protected abstract void startLoad();//加载数据

    protected abstract void initData();//初始化数据

    /**
     * 当视图不可见并且加载过数据,调用此方法
     */
    protected abstract void stopLoad();

    /**
     * 显示界面等待条
     */
    public void showProgressDialog() {
        if (null == onLineDialog) {
            onLineDialog = CommonProgressDialog.showDialog(getActivity(), true);
        }
        if (!getActivity().isFinishing()) {
            try {
                onLineDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
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

    //返回登录页面
    public void quiteApp(Activity activityContext, String content) {
        Toast.makeText(activityContext, content, Toast.LENGTH_LONG).show();
        PreferencesUtils.putString(XSWQApplication.mContext, "uid", "");
        Intent intent = new Intent(activityContext, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        activityContext.finish();
    }

}
