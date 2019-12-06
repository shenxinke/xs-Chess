package com.xswq.chess.myapplication.activity.competition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.base.BaseListAdapter;
import com.xswq.chess.myapplication.base.BaseListViewHolder;
import com.xswq.chess.myapplication.bean.AgainstTheTableBean;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 全屏对阵表
 */
public class AgainstTheTableActivity extends BaseActivity {
    private ListView bottomList;
    private TextView noData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_against_table_layout);
        initView();
        initData();
    }

    private void initData() {
        String gradeData = SPUtil.getString("againstData", "");
        Gson gson = new Gson();
        Type listType = new TypeToken<List<AgainstTheTableBean.DataBean>>() {
        }.getType();
        List<AgainstTheTableBean.DataBean> list = gson.fromJson(gradeData, listType);
        if (list != null && list.size() > 0) {
            noData.setVisibility(View.GONE);
            bottomList.setVisibility(View.VISIBLE);
            initBottomAdapter(list);
        } else {
            noData.setVisibility(View.VISIBLE);
            bottomList.setVisibility(View.GONE);
        }
    }

    private void initView() {
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        bottomList = findViewById(R.id.bottom_list);
        noData = findViewById(R.id.no_data);
        findViewById(R.id.image_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initBottomAdapter(final List<AgainstTheTableBean.DataBean> data) {
        BaseListAdapter bottomListAdapter = new BaseListAdapter<AgainstTheTableBean.DataBean>(XSWQApplication.getInstance(), data, R.layout.item_against_table_layout) {
            @Override
            public void convert(BaseListViewHolder holder, int position, AgainstTheTableBean.DataBean item) {
                int taiHao = position + 1;
                String blackNum = item.getBlackNum();
                String blackName = item.getBlackName();
                String blackScore = item.getBlackScore();
                int result = item.getResult();
                String whiteScore = item.getWhiteScore();
                String whiteName = item.getWhiteName();
                String whiteNum = item.getWhiteNum();

                holder.setText(R.id.tai_hao, String.valueOf(taiHao));
                holder.setText(R.id.black_serial_number, Util.zeroString(blackNum));
                holder.setText(R.id.black_name, Util.signString(blackName));
                holder.setText(R.id.black_last_round, Util.signString(blackScore));
                switch (result) {
                    case 1:
                        holder.setText(R.id.result, "黑棋赢");
                        break;
                    case 2:
                        holder.setText(R.id.result, "白棋赢");
                        break;
                    case 3:
                        holder.setText(R.id.result, "平局");
                        break;
                    case 4:
                        holder.setText(R.id.result, "无效对局");

                        break;
                    default:
                        break;
                }
                holder.setText(R.id.white_last_round, Util.signString(whiteScore));
                holder.setText(R.id.white_name, Util.signString(whiteName));
                holder.setText(R.id.white_serial_number, Util.zeroString(whiteNum));
            }
        };
        bottomList.setAdapter(bottomListAdapter);
    }
}
