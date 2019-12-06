package com.xswq.chess.myapplication.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.base.BaseFragment;
import com.xswq.chess.myapplication.base.BaseListAdapter;
import com.xswq.chess.myapplication.base.BaseListViewHolder;
import com.xswq.chess.myapplication.bean.IntroduceShareMoneyBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.DateUtil;
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

public class IntroduceShareMoneyFragment extends BaseFragment implements View.OnClickListener {
    private View mView;
    private TextView prefectStarString;
    private TextView prefectEndString;
    private String now;
    private CustomDatePicker endCustomDatePicker;
    private CustomDatePicker startCustomDatePicker;
    private String starString;
    private String endString;
    private TextView textMoney;
    private TextView notData;
    private ListView listView;
    private final String pattern = "yyyy-MM-dd";

    @Override
    protected int setContentView() {
        return R.layout.fragment_introduce_share_money_layout;
    }

    @Override
    protected void startLoad() {
        starString = prefectStarString.getText().toString().trim();
        endString = prefectEndString.getText().toString().trim();
        getData();
    }

    @Override
    protected void initData() {
        if (mView == null) {
            mView = getContentView();
        }
        textMoney = mView.findViewById(R.id.text_money);
        listView = mView.findViewById(R.id.list_view);
        notData = mView.findViewById(R.id.not_data);
        prefectStarString = mView.findViewById(R.id.prefect_star_string);
        prefectStarString.setOnClickListener(this);
        prefectEndString = mView.findViewById(R.id.prefect_end_string);
        prefectEndString.setOnClickListener(this);
        mView.findViewById(R.id.search_button).setOnClickListener(this);
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

    //获取分润数据
    private void getData() {
        try {
            PostFormBuilder post = OkHttpUtils.post();
            post.url("https://android.xswq361.cn/gobangteach/presidentController/getIntroducerProfitList");
            post.addParams("token", token);
            post.addParams("uid", userId);
            post.addParams("pageNum", Const.STR1);
            post.addParams("pageSize", Const.STR1000);

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
                        IntroduceShareMoneyBean introduceShareMoneyBean = gson.fromJson(response, IntroduceShareMoneyBean.class);
                        IntroduceShareMoneyBean.ErrorBean error = introduceShareMoneyBean.getError();
                        String returnCode = error.getReturnCode();
                        if (returnCode.equals("0")) {
                            IntroduceShareMoneyBean.DataBean data = introduceShareMoneyBean.getData();
                            if (data != null) {
                                List<IntroduceShareMoneyBean.DataBean.PageInfoBean.ListBean> list = data.getPageInfo().getList();
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

    private void initAdapter(List<IntroduceShareMoneyBean.DataBean.PageInfoBean.ListBean> shareList) {
        BaseListAdapter baseListAdapter = new BaseListAdapter<IntroduceShareMoneyBean.DataBean.PageInfoBean.ListBean>(XSWQApplication.getInstance(), shareList, R.layout.punching_card_teacher_item_layout) {
            @Override
            public void convert(BaseListViewHolder holder, int position, IntroduceShareMoneyBean.DataBean.PageInfoBean.ListBean item) {
                holder.setText(R.id.name, Util.signString(item.getCustomerName()));
                holder.setText(R.id.phone, Util.signString(item.getCount()));
                holder.setText(R.id.schedule, Util.signString(item.getProfit()));
            }
        };
        listView.setAdapter(baseListAdapter);
    }

    //验证日期规则
    private boolean isEndNoStart() {
        return DateUtil.getStringToDate(starString, pattern) > DateUtil.getStringToDate(endString, pattern);
    }
}
