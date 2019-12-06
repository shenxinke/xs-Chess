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
import com.xswq.chess.myapplication.bean.AgainstTheTableBean;
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

public class AgainstTheTableFragment extends BaseFragment implements View.OnClickListener {
    private StudentPlayByPlayBean.DataBean listBean;
    private View mView;
    private TextView matchText;
    private TextView noData;
    private ListView bottomList;
    private HorizontalListView listView;
    private int groupId;
    private int indexNumber = 0;
    private int stringNumber = 1;
    private List<StudentPlayByPlayBean.DataBean.GroupListBean> groupList;
    private List<StudentPlayByPlayBean.DataBean.GroupListBean.RoundsListBean> roundsList;
    private BaseListAdapter baseListAdapter;
    private int roundsId;
    private String strMatchText;
    private BaseListAdapter bottomListAdapter;


    public static AgainstTheTableFragment getInstances(StudentPlayByPlayBean.DataBean dataBean) {
        AgainstTheTableFragment againstTheTableFragment = new AgainstTheTableFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("studentCompetitionManagementBean", dataBean);
        againstTheTableFragment.setArguments(bundle);
        return againstTheTableFragment;
    }

    @Override
    protected int setContentView() {
        Bundle bundle = getArguments();
        listBean = (StudentPlayByPlayBean.DataBean) bundle.getSerializable("studentCompetitionManagementBean");
        return R.layout.fragment_against_table_layout;
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
        noData = mView.findViewById(R.id.no_data);
        listView = mView.findViewById(R.id.list_view);
        mView.findViewById(R.id.last_match).setOnClickListener(this);
        matchText = mView.findViewById(R.id.match_text);
        mView.findViewById(R.id.next_match).setOnClickListener(this);
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
                initAdapter(groupList);
                roundsList = groupList.get(0).getRoundsList();
                if (roundsList != null && roundsList.size() > 0) {
                    strMatchText = stringNumber + "/" + roundsList.size();
                    matchText.setText(strMatchText);
                    roundsId = roundsList.get(0).getId();
                    groupId = roundsList.get(0).getGroupId();
                    getData();
                }
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
                    stringNumber = 1;
                    indexNumber = 0;
                    roundsList = groupList.get(position).getRoundsList();
                    strMatchText = stringNumber + "/" + roundsList.size();
                    matchText.setText(strMatchText);
                    if (roundsList != null && roundsList.size() > 0) {
                        roundsId = roundsList.get(indexNumber).getId();
                        groupId = roundsList.get(indexNumber).getGroupId();
                        getData();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.last_match:
                if (stringNumber <= 1) {
                    return;
                }
                stringNumber--;
                indexNumber--;
                strMatchText = stringNumber + "/" + roundsList.size();
                matchText.setText(strMatchText);
                break;
            case R.id.next_match:
                if (roundsList != null && stringNumber >= roundsList.size()) {
                    return;
                }
                stringNumber++;
                indexNumber++;
                strMatchText = stringNumber + "/" + roundsList.size();
                matchText.setText(strMatchText);
                break;
            default:
                break;
        }
        if (roundsList != null && roundsList.size() > 0) {
            roundsId = roundsList.get(indexNumber).getId();
            groupId = roundsList.get(indexNumber).getGroupId();
            getData();
        }
    }

    private void getData() {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_ARENA_INFO_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("groupId", String.valueOf(groupId))
                    .addParams("roundsId", String.valueOf(roundsId))
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    AgainstTheTableBean againstTheTableBean = GsonUtil.gsonToBean(response, AgainstTheTableBean.class, getActivity());
                    if (againstTheTableBean != null) {
                        List<AgainstTheTableBean.DataBean> data = againstTheTableBean.getData();
                        if (data != null && data.size() > 0) {
                            noData.setVisibility(View.GONE);
                            bottomList.setVisibility(View.VISIBLE);
                            initBottomAdapter(data);
                            Gson gson = new Gson();
                            String s = gson.toJson(data);
                            SPUtil.put("againstData", s);
                        } else {
                            noData.setVisibility(View.VISIBLE);
                            bottomList.setVisibility(View.GONE);
                            String gradeData = SPUtil.getString("againstData", "");
                            if (!TextUtils.isEmpty(gradeData)) {
                                SPUtil.remove("againstData");
                            }
                        }
                    } else {
                        String gradeData = SPUtil.getString("againstData", "");
                        if (!TextUtils.isEmpty(gradeData)) {
                            SPUtil.remove("againstData");
                        }
                        noData.setVisibility(View.VISIBLE);
                        bottomList.setVisibility(View.GONE);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initBottomAdapter(final List<AgainstTheTableBean.DataBean> data) {
        if (bottomListAdapter == null) {
            bottomListAdapter = new BaseListAdapter<AgainstTheTableBean.DataBean>(XSWQApplication.getInstance(), data, R.layout.item_against_table_layout) {
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
        } else {
            bottomListAdapter.upData(data);
        }

    }
}
