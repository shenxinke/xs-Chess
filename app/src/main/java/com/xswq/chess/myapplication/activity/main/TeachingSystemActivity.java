package com.xswq.chess.myapplication.activity.main;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.TeachingSystemAdapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.base.Config;
import com.xswq.chess.myapplication.bean.TeacherSystemImage;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.TeachingSystemApi;
import com.xswq.chess.myapplication.http.retrofitHttp.RetrofitManager;
import com.xswq.chess.myapplication.http.subscriber.TeachingSystemSub;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.StatisticsUtil;
import com.xswq.chess.myapplication.widget.PopupwindowSpinner;
import com.google.gson.Gson;

import android.widget.PopupWindow.OnDismissListener;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.List;

public class TeachingSystemActivity extends BaseCompatActivity implements HttpCallBackLisener {

    private TextView teacher_system_title;
    private TextView teacher_system_kinds_id;
    private ListView main_teacher_system_easyview;
    private PopupwindowSpinner<String> mPopupwindowSpinnerMechanism;
    private PopupwindowSpinner<String> mPopupwindowSpinnerContext;
    private List<String> listMechanismData;
    private List<String> listContext;
    private TeachingSystemApi mTeachingSystemApi;
    private TeachingSystemSub mTeachingSystemSub;
    private int pageType = 1;
    private ImageView imageBackgroud;
    private TextView textBackgroud;
    private String sClassType;
    private String sTeachingType;


    @Override
    protected int getLayoutId() {
        return R.layout.main_teaching_system_layout;
    }

    @Override
    protected void initView() {
        getDeviceDensity();
        initMechanismData();
        initContextData();
        init();
    }

    @Override
    protected void loadData() {
        StatisticsUtil.getStatistics(token, userId, 7, TeachingSystemActivity.this);
    }

    private void init() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.prepare_lessons_backgroud);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("教学大纲");
        //学生端不可见
        ImageView loginUserHelp = findViewById(R.id.user_help);
        if (userType != 3) {
            loginUserHelp.setVisibility(View.VISIBLE);
            loginUserHelp.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    TeacherUserHelpActivity.openActivity(TeachingSystemActivity.this, pageType);
                }
            });
        } else {
            loginUserHelp.setVisibility(View.GONE);
        }
        imageBackgroud =  findViewById(R.id.image_background);
        textBackgroud = findViewById(R.id.text_background);
        main_teacher_system_easyview = findViewById(R.id.main_teacher_system_easyview);
        initSpinner();
        getRequest();
    }

    private void initSpinner() {
        //机构
        teacher_system_title = findViewById(R.id.teacher_system_title);
        teacher_system_title.setText("大班");
        teacher_system_title.setOnClickListener(clickListenerMechanism);
        //内容
        teacher_system_kinds_id =  findViewById(R.id.teacher_system_kinds_id);
        teacher_system_kinds_id.setText("教学大纲");
        teacher_system_kinds_id.setOnClickListener(clickListenerContext);
        //下拉内容控件 机构
        mPopupwindowSpinnerMechanism = new PopupwindowSpinner<>(this, listMechanismData, itemClickListenerMechanism);
        mPopupwindowSpinnerMechanism.setOnDismissListener(dismissListenerMechanism);

        //下拉内容控件 机构
        mPopupwindowSpinnerContext = new PopupwindowSpinner<>(this, listContext, itemClickListenerContext);
        mPopupwindowSpinnerContext.setOnDismissListener(dismissListenerContext);
    }

    private void getRequest() {
        mTeachingSystemApi = new TeachingSystemApi(this);
        mTeachingSystemApi.setMethod(ContactUrl.TEACHSTRUCTURE_PATH);
        mTeachingSystemApi.setClassType(String.valueOf(1));
        mTeachingSystemApi.setTeachingType(String.valueOf(1));
        mTeachingSystemApi.setUid(userId);
        mTeachingSystemApi.setToken(token);
        mTeachingSystemSub = new TeachingSystemSub(mTeachingSystemApi);
        RetrofitManager.getRetrofitInstance().handleHttp(mTeachingSystemSub, mTeachingSystemApi);
    }

    /**
     * 初始化数据
     */
    private void initMechanismData() {
        listMechanismData = new ArrayList<>();
        listMechanismData.add("大班");
        listMechanismData.add("中班");
    }

    /**
     * 初始化数据
     */
    private void initContextData() {
        listContext = new ArrayList<String>();
        listContext.add("教学大纲");
        listContext.add("教学建议");
    }

    /**
     * 显示PopupWindow
     */
    private OnClickListener clickListenerMechanism = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.teacher_system_title:
                    mPopupwindowSpinnerMechanism.setWidth(teacher_system_title.getWidth());
                    mPopupwindowSpinnerMechanism.showAsDropDown(teacher_system_title);
                    setMechanismTextImage(R.mipmap.icon_up);
                    break;
            }
        }
    };

    /**
     * 显示PopupWindow
     */
    private OnClickListener clickListenerContext = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.teacher_system_kinds_id:
                    mPopupwindowSpinnerContext.setWidth(teacher_system_kinds_id.getWidth());
                    mPopupwindowSpinnerContext.showAsDropDown(teacher_system_kinds_id);
                    setContextTextImage(R.mipmap.icon_up);
                    break;
            }
        }
    };
    /**
     * 监听popupwindow取消
     */
    private OnDismissListener dismissListenerMechanism = new OnDismissListener() {
        @Override
        public void onDismiss() {
            setMechanismTextImage(R.mipmap.icon_down);
        }
    };
    /**
     * 监听popupwindow取消
     */
    private OnDismissListener dismissListenerContext = new OnDismissListener() {
        @Override
        public void onDismiss() {
            setContextTextImage(R.mipmap.icon_down);
        }
    };
    /**
     * popupwindow显示的ListView的item点击事件
     */
    private AdapterView.OnItemClickListener itemClickListenerMechanism = new AdapterView.OnItemClickListener() {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mPopupwindowSpinnerMechanism.dismiss();
            teacher_system_title.setText(listMechanismData.get(position));
            sClassType = String.valueOf(position + 1);
            mTeachingSystemApi.setClassType(sClassType);
            mTeachingSystemApi.setTeachingType(sTeachingType);
            RetrofitManager.getRetrofitInstance().handleHttp(mTeachingSystemSub, mTeachingSystemApi);
        }
    };
    /**
     * popupwindow显示的ListView的item点击事件
     */
    private AdapterView.OnItemClickListener itemClickListenerContext = new AdapterView.OnItemClickListener() {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mPopupwindowSpinnerContext.dismiss();
            teacher_system_kinds_id.setText(listContext.get(position));
            sTeachingType = String.valueOf(position + 1);
            mTeachingSystemApi.setTeachingType(sClassType);
            mTeachingSystemApi.setTeachingType(sTeachingType);
            RetrofitManager.getRetrofitInstance().handleHttp(mTeachingSystemSub, mTeachingSystemApi);
        }
    };

    /**
     * 给TextView右边设置图片 机构
     *
     * @param resId
     */
    private void setMechanismTextImage(int resId) {
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 必须设置图片大小，否则不显示
        teacher_system_title.setCompoundDrawables(null, null, drawable, null);
    }

    /**
     * 给TextView右边设置图片 机构
     * // * @param resId
     */
    private void setContextTextImage(int resId) {
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 必须设置图片大小，否则不显示
        teacher_system_kinds_id.setCompoundDrawables(null, null, drawable, null);
    }

    @Override
    public void onNext(Object t, String method) {
        try {
            Gson gson = new Gson();
            String string = String.valueOf(t);
            if (!TextUtils.isEmpty(string)) {
                TeacherSystemImage teacherSystemImage = gson.fromJson(string, TeacherSystemImage.class);
                TeacherSystemImage.ErrorBean error = teacherSystemImage.getError();
                String returnCode = error.getReturnCode();
                if ("0".equals(returnCode)) {
                    TeacherSystemImage.DataBean data = teacherSystemImage.getData();
                    if (data != null) {
                        imageBackgroud.setVisibility(View.GONE);
                        textBackgroud.setVisibility(View.GONE);
                        List<TeacherSystemImage.DataBean.ListBean> list = data.getList();
                        if (list.size() > 0) {
                            TeachingSystemAdapter mTeachingSystemAdapter = new TeachingSystemAdapter(TeachingSystemActivity.this, list);
                            main_teacher_system_easyview.setAdapter(mTeachingSystemAdapter);
                        } else {
                            imageBackgroud.setVisibility(View.VISIBLE);
                            textBackgroud.setVisibility(View.VISIBLE);
                        }
                    } else {
                        imageBackgroud.setVisibility(View.VISIBLE);
                        textBackgroud.setVisibility(View.VISIBLE);
                    }
                } else if ("10048".equals(returnCode)) {
                    quiteApp(this, error.getReturnMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    protected void getDeviceDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Config.EXACT_SCREEN_HEIGHT = metrics.heightPixels;
        Config.EXACT_SCREEN_WIDTH = metrics.widthPixels;
    }
}
