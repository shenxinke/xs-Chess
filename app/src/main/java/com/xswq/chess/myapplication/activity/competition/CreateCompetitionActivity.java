package com.xswq.chess.myapplication.activity.competition;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.base.BaseListAdapter;
import com.xswq.chess.myapplication.base.BaseListViewHolder;
import com.xswq.chess.myapplication.bean.ClassInfoListBean;
import com.xswq.chess.myapplication.bean.CompetitionManagementBean;
import com.xswq.chess.myapplication.bean.SelectAllOrgnoBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.customview.MaterialSpinner;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.DateUtil;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.xswq.chess.myapplication.widget.CustomDatePicker;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 创建比赛1
 */
public class CreateCompetitionActivity extends BaseCompatActivity implements View.OnClickListener {

    @BindView(R.id.edit_text_pattern)
    MaterialSpinner textPattern;
    @BindView(R.id.edit_text_name)
    EditText editTextName;
    @BindView(R.id.text_pattern)
    TextView textPatternTitle;
    @BindView(R.id.text_school)
    TextView textSchools;
    @BindView(R.id.edit_text_school)
    EditText textSchool;
    @BindView(R.id.login_titles)
    TextView loginTitles;
    @BindView(R.id.login_back)
    TextView loginBack;

    @BindView(R.id.edit_text_start_time)
    TextView textStartTime;
    @BindView(R.id.edit_text_end_time)
    TextView textEndTime;


    private String now;
    private CustomDatePicker endCustomDatePicker;
    private CustomDatePicker startCustomDatePicker;
    private String starString;
    private String endString;
    private List<SelectAllOrgnoBean.DataBean> listdData;
    private List<SelectAllOrgnoBean.DataBean> newListData = new ArrayList<>();
    private String schoolID;
    private CompetitionManagementBean.DataBean.ListBean listBean;
    private ListView list_view;
    private PopupWindow popupWindowList;
    private Handler handler = new Handler();
    private boolean isSelect = false;
    private BaseListAdapter baseListAdapter;
    private boolean teacherType;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_competition_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.wei_qi_story_list_bg);
        loginTitles.setText("创建比赛");
        loginBack.setVisibility(View.VISIBLE);
        loginBack.setOnClickListener(this);
        findViewById(R.id.button_next).setOnClickListener(this);
        textPattern.setPadding(Util.dip2px(CreateCompetitionActivity.this, 11), 0, Util.dip2px(CreateCompetitionActivity.this, 11), 0);
        textSchool.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                handler.post(runnable);
                isSelect = false;
            }
        });
        textStartTime.setOnClickListener(this);
        textEndTime.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        Intent intent = getIntent();
        listBean = (CompetitionManagementBean.DataBean.ListBean) intent.getSerializableExtra("competitionManagementBean");
        teacherType = intent.getBooleanExtra("teacherType", false);
        if (teacherType) {
            textPatternTitle.setVisibility(View.GONE);
            textPattern.setVisibility(View.GONE);
            textSchools.setVisibility(View.GONE);
            textSchool.setVisibility(View.GONE);
        }
        initDatePicker();
        patternInitData();
        getTheGarden();
        if (listBean != null) {
            String matchName = listBean.getMatchName();
            int matchPattern = listBean.getMatchPattern();
            long startTime = listBean.getStartTime();
            long endTime = listBean.getEndTime();
            String invitedSchName = listBean.getInvitedSchName();
            schoolID = listBean.getInvitedSch();
            editTextName.setText(Util.signString(matchName));
            if (matchPattern == Const.INTEGER_2) {
                textPattern.setText("校内赛");
                textPattern.setVisibility(View.VISIBLE);
                textPatternTitle.setVisibility(View.VISIBLE);
                textSchools.setVisibility(View.GONE);
                textSchool.setVisibility(View.GONE);
            } else if (matchPattern == Const.INTEGER_3) {
                textPattern.setText("校间赛");
                textPattern.setVisibility(View.VISIBLE);
                textPatternTitle.setVisibility(View.VISIBLE);
                textSchools.setVisibility(View.VISIBLE);
                textSchool.setVisibility(View.VISIBLE);
            } else {
                textPattern.setText("班内赛");
                textPattern.setVisibility(View.GONE);
                textPatternTitle.setVisibility(View.GONE);
                textSchools.setVisibility(View.GONE);
                textSchool.setVisibility(View.GONE);
            }
            textStartTime.setText(DateUtil.getDateToString(startTime, "yyyy-MM-dd HH:mm"));
            textEndTime.setText(DateUtil.getDateToString(endTime, "yyyy-MM-dd HH:mm"));
            textSchool.setText(Util.signString(invitedSchName));
        }
    }

    private void patternInitData() {
        textPattern.setItem("校内赛");
        textPattern.setItem("校间赛");

        textPattern.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                if (item.equals("校间赛")) {
                    textSchools.setVisibility(View.VISIBLE);
                    textSchool.setVisibility(View.VISIBLE);
                } else if (item.equals("校内赛")) {
                    textSchools.setVisibility(View.GONE);
                    textSchool.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                showPopupWindow();
                break;
            case R.id.button_next:
                starString = textStartTime.getText().toString().trim();
                endString = textEndTime.getText().toString().trim();
                String name = editTextName.getText().toString().trim();
                String pattern = textPattern.getText().toString().trim();
                String school = textSchool.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    ToastUtils.show("比赛名称为空");
                    return;
                }
                if ("请选择比赛模式".equals(pattern) && !teacherType) {
                    ToastUtils.show("请选择比赛模式");
                    return;
                }
                if (pattern.equals("校间赛") && TextUtils.isEmpty(school)) {
                    ToastUtils.show("请选择比赛学校");
                    return;
                }

                if (newListData != null && !newListData.isEmpty() && !teacherType && pattern.equals("校间赛")) {
                    List<String> strings = new ArrayList<>();
                    for (int i = 0; i < newListData.size(); i++) {
                        strings.add(newListData.get(i).getCustomerName());
                    }
                    if (!strings.contains(school)) {
                        ToastUtils.show("不存在该学校,请重新选择");
                        return;
                    }
                }

                if (starString.equals("请选择报名开始时间")) {
                    ToastUtils.show("请选择报名开始时间");
                    return;
                }
                if (endString.equals("请选择报名结束时间")) {
                    ToastUtils.show("请选择报名结束时间");
                    return;
                }
                if (isEndNoStart()) {
                    ToastUtils.show("报名结束时间不能早于开始时间");
                    return;
                }
                if (isStartNo()) {
                    ToastUtils.show("报名结束时间至少在当前时间20分钟后");
                } else {
                    classInfoList(name, pattern);
                }
                break;
            case R.id.edit_text_start_time:
                startCustomDatePicker.show(now);
                break;
            case R.id.edit_text_end_time:
                endCustomDatePicker.show(now);
                break;
            default:
                break;
        }
    }

    //日历控件调用
    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());

        startCustomDatePicker = new CustomDatePicker(CreateCompetitionActivity.this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                textStartTime.setText(time);
            }
        }, now, "2030-01-01 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        startCustomDatePicker.showSpecificTime(true); // 显示时和分
        startCustomDatePicker.setIsLoop(false); // 不允许循环滚动

        endCustomDatePicker = new CustomDatePicker(CreateCompetitionActivity.this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                textEndTime.setText(time);
            }
        }, now, "2030-01-01 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        endCustomDatePicker.showSpecificTime(true); // 显示时和分
        endCustomDatePicker.setIsLoop(false); // 不允许循环滚动
    }

    //验证日期规则 1200000=20分钟
    private boolean isStartNo() {
        String pattern = "yyyy-MM-dd HH:mm";
        long star = DateUtil.getStringToDate(endString, pattern);
        long stringNow = DateUtil.getStringToDate(now, pattern);
        long end = stringNow + 1200000;
        return star < end;
    }

    //验证日期规则
    private boolean isEndNoStart() {
        String pattern = "yyyy-MM-dd HH:mm";
        long stringStart = DateUtil.getStringToDate(starString, pattern);
        long stringEnd = DateUtil.getStringToDate(endString, pattern);
        return stringStart > stringEnd;
    }

    private void showPopupWindow() {
        View layout = LayoutInflater.from(CreateCompetitionActivity.this).inflate(R.layout.create_competition_pop_window_layout, null, false);
        View view = getWindow().getDecorView();
        final PopupWindow popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setClippingEnabled(false);
        ColorDrawable dw = new ColorDrawable(0xff);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        Button btnSava = layout.findViewById(R.id.button_save);
        Button btnCancel = layout.findViewById(R.id.button_cancel);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button_save:
                        popupWindow.dismiss();
                        finish();
                        break;
                    case R.id.button_cancel:
                        popupWindow.dismiss();
                        break;
                    default:
                        break;
                }
            }
        };
        //设置popwindow布局控件的点击事件
        btnSava.setOnClickListener(listener);
        btnCancel.setOnClickListener(listener);
    }

    //获取学校信息
    private void getTheGarden() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_SELECT_ALL_ORGNO)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    SelectAllOrgnoBean selectAllOrgnoBean = GsonUtil.gsonToBean(response, SelectAllOrgnoBean.class, CreateCompetitionActivity.this);
                    if (selectAllOrgnoBean != null) {
                        listdData = selectAllOrgnoBean.getData();
                        newListData.addAll(listdData);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            String data = textSchool.getText().toString();
            if (data.length() == 0)
                return;
            if (popupWindowList != null && !popupWindowList.isShowing() && !isSelect) {
                popupWindowList.showAsDropDown(textSchool, 0, 0);
            }
            if (popupWindowList == null && listdData != null) {
                showListPopWindow();
                popupWindowList.showAsDropDown(textSchool, 0, 0);
            }
            if (listdData != null) {
                listdData.clear();//先要清空，不然会叠加
            }
            getNewData(newListData.size(), data, newListData);//获取更新数据
            if (baseListAdapter != null) {
                baseListAdapter.notifyDataSetChanged();//更新
            }
        }
    };

    //显示提示框
    private void showListPopWindow() {
        final View layout = LayoutInflater.from(CreateCompetitionActivity.this).inflate(R.layout.perfect_information_pop_layout, null, false);
        popupWindowList = new PopupWindow(layout, textSchool.getWidth(), textSchool.getWidth(), false);
        popupWindowList.setOutsideTouchable(true);
        popupWindowList.setClippingEnabled(false);
        ColorDrawable dw = new ColorDrawable(0xff);
        popupWindowList.setBackgroundDrawable(dw);
        popupWindowList.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        popupWindowList.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        list_view = layout.findViewById(R.id.list_view);
        if (listdData != null && listdData.size() > 0) {
            initShareInfo();
        }
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String customerName = listdData.get(position).getCustomerName();
                textSchool.setText(Util.signString(customerName));
                schoolID = listdData.get(position).getID();
                isSelect = true;
                if (popupWindowList != null) {
                    popupWindowList.dismiss();
                }
            }
        });
    }

    private void initShareInfo() {
        baseListAdapter = new BaseListAdapter<SelectAllOrgnoBean.DataBean>(XSWQApplication.getInstance(), listdData, R.layout.perfect_information_item_layout) {
            @Override
            public void convert(BaseListViewHolder holder, int position, SelectAllOrgnoBean.DataBean item) {
                holder.setText(R.id.text_name, item.getCustomerName());
            }
        };
        list_view.setAdapter(baseListAdapter);
    }

    private void getNewData(int size, String value, List<SelectAllOrgnoBean.DataBean> newListData) {
        if (size > 0) {
            for (int i = 0; i < newListData.size(); i++) {
                String customerName = newListData.get(i).getCustomerName();
                SelectAllOrgnoBean.DataBean dataBean = newListData.get(i);
                if (customerName.contains(value)) {
                    listdData.add(dataBean);
                }
            }
        }
    }

    private void classInfoList(final String name, final String pattern) {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_CLASS_INFO_LIST_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("pageNum", Const.STR1)
                    .addParams("pageSize", Const.STR20)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    ClassInfoListBean classInfoListBean = GsonUtil.gsonToBean(response, ClassInfoListBean.class, CreateCompetitionActivity.this);
                    if (classInfoListBean == null) return;
                    ClassInfoListBean.DataBean data = classInfoListBean.getData();
                    if (data == null) return;
                    int total = data.getTotal();
                    if (total > 0) {
                        Intent intent = new Intent(CreateCompetitionActivity.this, CompetitionContentActivity.class);
                        if (teacherType) {
                            intent.putExtra("matchType", 1);//1班内赛
                        }
                        intent.putExtra("matchName", name);
                        if ("校内赛".equals(pattern)) {
                            intent.putExtra("matchPattern", 2);
                        } else if ("校间赛".equals(pattern)) {
                            intent.putExtra("matchPattern", 3);
                        } else {
                            intent.putExtra("matchPattern", 4);
                        }
                        intent.putExtra("invitedSch", schoolID);
                        intent.putExtra("startTime", starString);
                        intent.putExtra("endTime", endString);
                        intent.putExtra("competitionManagementBean", listBean);
                        startActivity(intent);
                        finish();
                    } else {
                        View layout = LayoutInflater.from(CreateCompetitionActivity.this).inflate(R.layout.create_competition_pop_window_layout, null, false);
                        View view = getWindow().getDecorView();
                        final PopupWindow popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
                        popupWindow.setTouchable(true);
                        popupWindow.setClippingEnabled(false);
                        ColorDrawable dw = new ColorDrawable(0xff);
                        popupWindow.setBackgroundDrawable(dw);
                        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                        Button btnSava = layout.findViewById(R.id.button_save);
                        Button btnCancel = layout.findViewById(R.id.button_cancel);
                        TextView text = layout.findViewById(R.id.text);
                        text.setText("创建比赛前，请先添加班级");
                        btnSava.setText("添加班级");
                        View.OnClickListener listener = new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                switch (view.getId()) {
                                    case R.id.button_save:
                                        Intent mIntent = new Intent(CreateCompetitionActivity.this, JsWebViewActivity.class);
                                        mIntent.putExtra("prefix", ContactUrl.CLASSMANAGE);
                                        startActivity(mIntent);
                                        popupWindow.dismiss();
                                        break;
                                    case R.id.button_cancel:
                                        popupWindow.dismiss();
                                        break;
                                    default:
                                        break;
                                }
                            }
                        };
                        //设置popwindow布局控件的点击事件
                        btnSava.setOnClickListener(listener);
                        btnCancel.setOnClickListener(listener);
                    }
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
