package com.xswq.chess.myapplication.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.base.BaseFragment;
import com.xswq.chess.myapplication.base.BaseListAdapter;
import com.xswq.chess.myapplication.base.BaseListViewHolder;
import com.xswq.chess.myapplication.bean.ResultOfTheMatchBean;
import com.xswq.chess.myapplication.bean.StudentPlayByPlayBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.customview.HorizontalListView;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.xswq.chess.myapplication.utils.WindowUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class GradeTableFragment extends BaseFragment {
    private View mView;
    private StudentPlayByPlayBean.DataBean listBean;
    private int[] opponent = new int[]{R.id.one_opponent, R.id.two_opponent, R.id.three_opponent, R.id.four_opponent, R.id.five_opponent,
            R.id.six_opponent, R.id.seven_opponent, R.id.eight_opponent, R.id.nine_opponent, R.id.ten_opponent, R.id.eleven_opponent
            , R.id.twelve_opponent, R.id.thirteen_opponent};

    private int[] score = new int[]{R.id.one_score, R.id.two_score, R.id.three_score, R.id.four_score, R.id.five_score,
            R.id.six_score, R.id.seven_score, R.id.eight_score, R.id.nine_score, R.id.ten_score, R.id.eleven_score
            , R.id.twelve_score, R.id.thirteen_score};

    private int[] matchNum = new int[]{R.id.one_match, R.id.two_match, R.id.three_match, R.id.four_match, R.id.five_match,
            R.id.six_match, R.id.seven_match, R.id.eight_match, R.id.nine_match, R.id.ten_match, R.id.eleven_match
            , R.id.twelve_match, R.id.thirteen_match};
    private TextView noData;
    private ListView bottomList;
    private HorizontalListView listView;
    private List<StudentPlayByPlayBean.DataBean.GroupListBean> groupList;
    private List<ResultOfTheMatchBean.DataBean.DsListBean> dsList;
    private int groupId;
    private BaseListAdapter baseListAdapter;
    private BaseListAdapter bottomListAdapter;


    public static GradeTableFragment getInstances(StudentPlayByPlayBean.DataBean dataBean) {
        GradeTableFragment gradeTableFragment = new GradeTableFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("studentCompetitionManagementBean", dataBean);
        gradeTableFragment.setArguments(bundle);
        return gradeTableFragment;
    }

    @Override
    protected int setContentView() {
        Bundle bundle = getArguments();
        listBean = (StudentPlayByPlayBean.DataBean) bundle.getSerializable("studentCompetitionManagementBean");
        return R.layout.fragment_grade_table_layout;
    }

    @Override
    protected void startLoad() {
        initDatas();
    }

    @Override
    protected void initData() {
        if (mView == null) {
            mView = getContentView();
        }
        listView = mView.findViewById(R.id.list_view);
        noData = mView.findViewById(R.id.no_data);
        bottomList = mView.findViewById(R.id.bottom_list);
    }

    @Override
    protected void stopLoad() {

    }

    private void initDatas() {
        if (listBean != null) {
            groupList = listBean.getGroupList();
            if (groupList != null && groupList.size() > 0) {
                for (int i = 0; i < groupList.size(); i++) {
                    groupList.get(i).setSelect(false);
                }
                groupList.get(0).setSelect(true);
                groupId = groupList.get(0).getId();
                initAdapter(groupList);
                getData();
            }
        }
    }

    private void initAdapter(List<StudentPlayByPlayBean.DataBean.GroupListBean> shareList) {
        baseListAdapter = new BaseListAdapter<StudentPlayByPlayBean.DataBean.GroupListBean>(XSWQApplication.getInstance(), shareList, R.layout.student_particulars_horizontal_item_layout) {
            @Override
            public void convert(BaseListViewHolder holder, int position, StudentPlayByPlayBean.DataBean.GroupListBean item) {
                holder.setText(R.id.button_item, item.getGroupName());
                if (item.isSelect()) {
                    holder.setTextBgById(R.id.button_item, R.drawable.shape_particulars_btn);
                    holder.setTextPadingLRById(R.id.button_item, WindowUtils.sp2px(getActivity(), 10));
                    holder.setTextColor(R.id.button_item, R.color.color_774801);
                } else {
                    holder.setTextBgById(R.id.button_item, R.drawable.shape_particulars_un_btn);
                    holder.setTextPadingLRById(R.id.button_item, WindowUtils.sp2px(getActivity(), 10));
                    holder.setTextColor(R.id.button_item, R.color.color_0058b0);
                }
            }
        };
        listView.setAdapter(baseListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView itemButton = view.findViewById(R.id.button_item);
                itemButton.setBackgroundResource(R.drawable.shape_particulars_btn);
                int leftRightPading = WindowUtils.sp2px(getActivity(), 10);
                itemButton.setPadding(leftRightPading, 0, leftRightPading, 0);
                itemButton.setTextColor(getResources().getColor(R.color.color_774801));
                if (groupList != null && groupList.size() > 0) {
                    for (int i = 0; i < groupList.size(); i++) {
                        groupList.get(i).setSelect(false);
                    }
                    groupList.get(position).setSelect(true);
                    baseListAdapter.notifyDataSetChanged();
                    groupId = groupList.get(position).getId();
                    getData();
                }
            }
        });
    }

    private void getData() {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_ACHIECEMENTS_INFO_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("groupId", String.valueOf(groupId))
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    ResultOfTheMatchBean resultOfTheMatchBean = GsonUtil.gsonToBean(response, ResultOfTheMatchBean.class, getActivity());
                    if (resultOfTheMatchBean != null) {
                        List<ResultOfTheMatchBean.DataBean> data = resultOfTheMatchBean.getData();
                        if (data != null && data.size() > 0) {
                            noData.setVisibility(View.GONE);
                            bottomList.setVisibility(View.VISIBLE);
                            initBottomAdapter(data);
                            List<ResultOfTheMatchBean.DataBean.DsListBean> dsList = data.get(0).getDsList();
                            if (dsList != null) {
                                for (int i = 0; i < dsList.size(); i++) {
                                    int number = i + 1;
                                    String numText = "第 " + number + " 轮";
                                    TextView numTextView = mView.findViewById(matchNum[i]);
                                    numTextView.setVisibility(View.VISIBLE);
                                    numTextView.setText(numText);
                                    mView.findViewById(opponent[i]).setVisibility(View.VISIBLE);
                                    mView.findViewById(score[i]).setVisibility(View.VISIBLE);
                                }
                            }
                            Gson gson = new Gson();
                            String s = gson.toJson(data);
                            SPUtil.put("gradeData",s);
                        } else {
                            noData.setVisibility(View.VISIBLE);
                            bottomList.setVisibility(View.GONE);
                            String gradeData = SPUtil.getString("gradeData", "");
                            if (!TextUtils.isEmpty(gradeData)) {
                                SPUtil.remove("gradeData");
                            }
                        }
                    } else {
                        noData.setVisibility(View.VISIBLE);
                        bottomList.setVisibility(View.GONE);
                        String gradeData = SPUtil.getString("gradeData", "");
                        if (!TextUtils.isEmpty(gradeData)) {
                            SPUtil.remove("gradeData");
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initBottomAdapter(final List<ResultOfTheMatchBean.DataBean> data) {
        if (bottomListAdapter == null) {
            bottomListAdapter = new BaseListAdapter<ResultOfTheMatchBean.DataBean>(XSWQApplication.getInstance(), data, R.layout.item_result_match_layout) {
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
                    holder.setText(R.id.ranking, String.valueOf(ranking));
                    holder.setText(R.id.total_points, Util.zeroString(sumCount));
                    dsList = item.getDsList();
                    if (dsList != null) {
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
        } else {
            bottomListAdapter.upData(data);
        }
    }
}
