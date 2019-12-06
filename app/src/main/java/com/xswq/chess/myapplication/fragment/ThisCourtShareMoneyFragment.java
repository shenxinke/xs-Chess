package com.xswq.chess.myapplication.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.base.BaseFragment;
import com.xswq.chess.myapplication.base.BaseListAdapter;
import com.xswq.chess.myapplication.base.BaseListViewHolder;
import com.xswq.chess.myapplication.bean.AlwaysParkListBean;
import com.xswq.chess.myapplication.bean.BrachNameListBean;
import com.xswq.chess.myapplication.bean.OrgProfitListBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.customview.MaterialSpinner;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.DateUtil;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.xswq.chess.myapplication.widget.CustomDatePicker;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import okhttp3.Call;

public class ThisCourtShareMoneyFragment extends BaseFragment implements View.OnClickListener {
    private View mView;
    private MaterialSpinner schoolSpinner;
    private MaterialSpinner spinnerText;
    private TextView prefectStarString;
    private TextView prefectEndString;
    private String now;
    private CustomDatePicker endCustomDatePicker;
    private CustomDatePicker startCustomDatePicker;
    private String userId;
    private String token;
    private String orgNo;
    private String starString;
    private String endString;
    private ListView listView;
    private TextView textMoney;
    private TextView notData;
    private TextView textPhone;
    private LinearLayout linearLayout;
    private int dealerRank;
    private String customerName = "全部";

    @Override
    protected int setContentView() {
        return R.layout.fragment_this_court_share_money_layout;
    }

    @Override
    protected void startLoad() {
        userId = SPUtil.getString("uid", "");
        token = SPUtil.getString("token", "");
        starString = prefectStarString.getText().toString().trim();
        endString = prefectEndString.getText().toString().trim();
        getPrimaryName();
    }

    @Override
    protected void initData() {
        if (mView == null) {
            mView = getContentView();
        }
        notData = mView.findViewById(R.id.not_data);

        textMoney = mView.findViewById(R.id.text_money);
        listView = mView.findViewById(R.id.list_view);
        schoolSpinner = mView.findViewById(R.id.school_spinner);
        prefectStarString = mView.findViewById(R.id.prefect_star_string);
        prefectStarString.setOnClickListener(this);
        prefectEndString = mView.findViewById(R.id.prefect_end_string);
        prefectEndString.setOnClickListener(this);
        Button searchButton = mView.findViewById(R.id.search_button);
        searchButton.setOnClickListener(this);
        textPhone = mView.findViewById(R.id.text_phone);
        linearLayout = mView.findViewById(R.id.aggregate_bg);
        spinnerText = mView.findViewById(R.id.president_text2);

        initDatePicker();
    }

    @Override
    protected void stopLoad() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prefect_star_string:
                startCustomDatePicker.show(now.split(" ")[0]);
                break;
            case R.id.prefect_end_string:
                endCustomDatePicker.show(now.split(" ")[0]);
                break;
            case R.id.search_button:
                starString = prefectStarString.getText().toString().trim();
                endString = prefectEndString.getText().toString().trim();
                if (isEndNoStart()) {
                    ToastUtils.show("结束时间不能早于开始时间，请重新选择!");
                } else {
                    getData();
                }
                break;
            default:
                break;
        }
    }

    //日历控件调用
    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());

        startCustomDatePicker = new CustomDatePicker(getActivity(), new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                prefectStarString.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        startCustomDatePicker.showSpecificTime(false); // 不显示时和分
        startCustomDatePicker.setIsLoop(false); // 不允许循环滚动

        endCustomDatePicker = new CustomDatePicker(getActivity(), new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                prefectEndString.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        endCustomDatePicker.showSpecificTime(false); // 不显示时和分
        endCustomDatePicker.setIsLoop(false); // 不允许循环滚动
    }

    //查询所有的主院
    private void getPrimaryName() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_PRIMARY_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    AlwaysParkListBean alwaysParkListBean = GsonUtil.gsonToBean(response, AlwaysParkListBean.class, getActivity());
                    if (alwaysParkListBean != null) {
                        AlwaysParkListBean.DataBean data = alwaysParkListBean.getData();
                        if (data != null) {
                            AlwaysParkListBean.DataBean.NowBean now = data.getNow();
                            if (now != null) {
                                dealerRank = now.getDealerRank();
                            }
                            List<AlwaysParkListBean.DataBean.PrimaryBean> primary = data.getPrimary();
                            if (primary != null && primary.size() > 0) {
                                schoolSpinner.notifyData();
                                if (dealerRank == 1) {
                                    textPhone.setText("分润提成(元)");
                                    linearLayout.setVisibility(View.VISIBLE);
                                    getBrachName(primary.get(0).getID());
                                    primaryInfor(primary);
                                } else {
                                    if (dealerRank == 3) {
                                        schoolSpinner.setItem("全部");
                                        spinnerText.notifyData();
                                        spinnerText.setItem("全部");
                                        primaryInfor(primary);
                                        getData();
                                    } else {
                                        getBrachName(primary.get(0).getID());
                                        primaryInfor(primary);
                                    }
                                    textPhone.setText("续费时间");
                                    linearLayout.setVisibility(View.GONE);
                                }

                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void primaryInfor(final List<AlwaysParkListBean.DataBean.PrimaryBean> schoolManagementList) {
        for (int i = 0; i < schoolManagementList.size(); i++) {
            schoolSpinner.setItem(schoolManagementList.get(i).getCustomerName());
        }

        schoolSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                if (dealerRank == 3) {
                    if (position == 0) {
                        spinnerText.notifyData();
                        spinnerText.setItem("全部");
                        orgNo = null;
                        customerName = "全部";
                        getData();
                    } else {
                        String primaryID = schoolManagementList.get(position - 1).getID();
                        if (!TextUtils.isEmpty(primaryID)) {
                            getBrachName(primaryID);
                        }
                    }
                } else {
                    String primaryID = schoolManagementList.get(position).getID();
                    if (!TextUtils.isEmpty(primaryID)) {
                        getBrachName(primaryID);

                    }
                }
            }
        });
    }

    //查询所有的分院
    private void getBrachName(String orgNos) {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_BRANCH_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("orgNo", orgNos)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    BrachNameListBean brachNameListBean = GsonUtil.gsonToBean(response, BrachNameListBean.class, getActivity());
                    if (brachNameListBean != null) {
                        List<BrachNameListBean.DataBean> data = brachNameListBean.getData();
                        if (data != null && data.size() > 0) {
                            spinnerText.notifyData();
                            if (dealerRank == 3) {
                                spinnerText.setItem("全部");
                                brachNameInfor(data);
                            } else {
                                orgNo = data.get(0).getID();
                                customerName = data.get(0).getCustomerName();
                                brachNameInfor(data);
                            }
                            getData();
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void brachNameInfor(final List<BrachNameListBean.DataBean> schoolManagementList) {
        for (int i = 0; i < schoolManagementList.size(); i++) {
            spinnerText.setItem(schoolManagementList.get(i).getCustomerName());
        }
        spinnerText.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                if (dealerRank == 3) {
                    if (position == 0) {
                        orgNo = null;
                        customerName = "全部";
                        getData();
                    } else {
                        orgNo = schoolManagementList.get(position - 1).getID();
                        customerName = schoolManagementList.get(position - 1).getCustomerName();
                        if (!TextUtils.isEmpty(orgNo)) {
                            getData();
                        }
                    }
                } else {
                    orgNo = schoolManagementList.get(position).getID();
                    customerName = schoolManagementList.get(position).getCustomerName();
                    if (!TextUtils.isEmpty(orgNo)) {
                        getData();
                    }
                }
            }
        });
    }

    //获取分润数据
    private void getData() {
        try {
            PostFormBuilder post = OkHttpUtils.post();
            post.url("https://android.xswq361.cn/gobangteach/presidentController/getOrgProfitList");
            post.addParams("token", token);
            post.addParams("uid", userId);
            post.addParams("pageNum", Const.STR1);
            post.addParams("pageSize", Const.STR1000);
            if (!"全部".equals(customerName) && !TextUtils.isEmpty(orgNo)) {
                post.addParams("orgNo", orgNo);
            }
            if (!Const.PLEASE_CHOOCE.equals(starString)) {
                post.addParams("beginTime", starString);
            }
            if (!Const.PLEASE_CHOOCE.equals(endString)) {
                post.addParams("endTime", endString);
            }
            post.build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                }

                @Override
                public void onResponse(String response, int id) {
                    if (response != null) {
                        Gson gson = new Gson();
                        OrgProfitListBean orgProfitListBean = gson.fromJson(response, OrgProfitListBean.class);
                        OrgProfitListBean.ErrorBean error = orgProfitListBean.getError();
                        String returnCode = error.getReturnCode();
                        if (returnCode.equals("0")) {
                            OrgProfitListBean.DataBean data = orgProfitListBean.getData();
                            if (data != null) {
                                List<OrgProfitListBean.DataBean.PageInfoBean.ListBean> list = data.getPageInfo().getList();
                                textMoney.setText(Util.signString(data.getTotalProfit()));
                                if (list != null && list.size() > 0) {
                                    notData.setVisibility(View.GONE);
                                    listView.setVisibility(View.VISIBLE);
                                    initAdapter(list);
                                } else {
                                    notData.setVisibility(View.VISIBLE);
                                    listView.setVisibility(View.GONE);
                                }
                            }

                        } else if (returnCode.equals("10048")) {
                            quiteApp(getActivity(), error.getReturnMessage());
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initAdapter(List<OrgProfitListBean.DataBean.PageInfoBean.ListBean> shareList) {
        BaseListAdapter baseListAdapter = new BaseListAdapter<OrgProfitListBean.DataBean.PageInfoBean.ListBean>(XSWQApplication.getInstance(), shareList, R.layout.punching_card_teacher_item_layout) {
            @Override
            public void convert(BaseListViewHolder holder, int position, OrgProfitListBean.DataBean.PageInfoBean.ListBean item) {
                holder.setText(R.id.name, Util.signString(item.getUserName()));
                if (dealerRank == 1) {
                    holder.setText(R.id.phone, Util.signString(item.getProfit()));
                } else {
                    holder.setText(R.id.phone, DateUtil.getDateToString(item.getCreateTime(), "yyyy-MM-dd HH:mm"));
                }
                holder.setText(R.id.schedule, Util.signString(item.getCustomerName()));
            }
        };
        listView.setAdapter(baseListAdapter);
    }

    //验证日期规则
    private boolean isEndNoStart() {
        String pattern = "yyyy-MM-dd";
        return DateUtil.getStringToDate(starString, pattern) > DateUtil.getStringToDate(endString, pattern);
    }
}
