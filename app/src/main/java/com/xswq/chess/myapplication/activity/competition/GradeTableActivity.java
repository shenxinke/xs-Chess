package com.xswq.chess.myapplication.activity.competition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.base.BaseListAdapter;
import com.xswq.chess.myapplication.base.BaseListViewHolder;
import com.xswq.chess.myapplication.bean.ResultOfTheMatchBean;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.Util;
import com.xswq.chess.myapplication.utils.WindowUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
/**
 * 全屏成绩表
 */

public class GradeTableActivity extends BaseActivity {
    private int[] opponent = new int[]{R.id.one_opponent, R.id.two_opponent, R.id.three_opponent, R.id.four_opponent, R.id.five_opponent,
            R.id.six_opponent, R.id.seven_opponent, R.id.eight_opponent, R.id.nine_opponent, R.id.ten_opponent, R.id.eleven_opponent
            , R.id.twelve_opponent, R.id.thirteen_opponent};

    private int[] score = new int[]{R.id.one_score, R.id.two_score, R.id.three_score, R.id.four_score, R.id.five_score,
            R.id.six_score, R.id.seven_score, R.id.eight_score, R.id.nine_score, R.id.ten_score, R.id.eleven_score
            , R.id.twelve_score, R.id.thirteen_score};

    private int[] matchNum = new int[]{R.id.one_match, R.id.two_match, R.id.three_match, R.id.four_match, R.id.five_match,
            R.id.six_match, R.id.seven_match, R.id.eight_match, R.id.nine_match, R.id.ten_match, R.id.eleven_match
            , R.id.twelve_match, R.id.thirteen_match};
    private ListView bottomList;
    private TextView noData;
    private List<ResultOfTheMatchBean.DataBean.DsListBean> dsList;
    private int maxScor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_table_layout);
        initView();
        initData();
    }

    private void initData() {
        String gradeData = SPUtil.getString("gradeData", "");
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ResultOfTheMatchBean.DataBean>>() {
        }.getType();
        List<ResultOfTheMatchBean.DataBean> list = gson.fromJson(gradeData, listType);
        if (list != null && list.size() > 0) {
            noData.setVisibility(View.GONE);
            bottomList.setVisibility(View.VISIBLE);
            maxScor = list.get(0).getUserScore();
            initBottomAdapter(list);
        } else {
            noData.setVisibility(View.VISIBLE);
            bottomList.setVisibility(View.GONE);
        }
    }

    private void initView() {
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        HorizontalScrollView horizontalScrollView = findViewById(R.id.scroll_view);
        bottomList = findViewById(R.id.bottom_list);
        noData = findViewById(R.id.no_data);

        ViewGroup.LayoutParams layoutParams = horizontalScrollView.getLayoutParams();
        layoutParams.width = WindowUtils.getScreenHeight(GradeTableActivity.this) - Util.dip2px(GradeTableActivity.this, 90);
        layoutParams.height = WindowUtils.getScreenWidth(GradeTableActivity.this) - Util.dip2px(GradeTableActivity.this, 30);
        horizontalScrollView.setLayoutParams(layoutParams);
        findViewById(R.id.image_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initBottomAdapter(final List<ResultOfTheMatchBean.DataBean> data) {
        BaseListAdapter bottomListAdapter = new BaseListAdapter<ResultOfTheMatchBean.DataBean>(XSWQApplication.getInstance(), data, R.layout.item_result_match_layout) {
            @Override
            public void convert(BaseListViewHolder holder, int position, ResultOfTheMatchBean.DataBean item) {
                int ranking = position + 1;
                int userScore = item.getUserScore(); //个人积分
                int dsScore = item.getDsScore();
                String sumCount = item.getSumCount();
                holder.setText(R.id.serial_number, String.valueOf(item.getNum()));
                holder.setText(R.id.user_name, Util.signString(item.getUserName()));
                holder.setText(R.id.integral, Util.signString(String.valueOf(userScore)));
                holder.setText(R.id.opponent_score, Util.signString(String.valueOf(dsScore)));
                dsList = item.getDsList();
                holder.setText(R.id.ranking, String.valueOf(ranking));
                if (dsList != null) {
                    holder.setText(R.id.total_points, Util.zeroString(sumCount));
                    for (int i = dsList.size(); i < opponent.length; i++) {
                        holder.setTextGone(opponent[i], 1);
                        holder.setTextGone(score[i], 1);
                    }
                    for (int i = 0; i < dsList.size(); i++) {
                        holder.setText(opponent[i], Util.signString(dsList.get(i).getDsUserName()));
                        int stage = dsList.get(i).getStage();
                        holder.setText(score[i], Util.signString(String.valueOf(stage)));
                        findViewById(matchNum[i]).setVisibility(View.VISIBLE);
                        findViewById(opponent[i]).setVisibility(View.VISIBLE);
                        findViewById(score[i]).setVisibility(View.VISIBLE);
                    }
                }
            }
        };
        bottomList.setAdapter(bottomListAdapter);
    }
}
