package com.xswq.chess.myapplication.activity.competition;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseBean;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.base.BaseListAdapter;
import com.xswq.chess.myapplication.base.BaseListViewHolder;
import com.xswq.chess.myapplication.bean.CompetitionSetThrCategoryBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.config.RxCode;
import com.xswq.chess.myapplication.customview.MyListView;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import gorden.rxbus2.RxBus;
import okhttp3.Call;

/**
 * 设置组别
 */
public class CompetitionSetTheCategoryActivity extends BaseCompatActivity implements View.OnClickListener {

    private String matchId;
    private List<CompetitionSetThrCategoryBean.DataBean.GroupListBean> groupList;
    private String invitedSch;
    private String createOrg;
    private String endTime;
    private BaseListAdapter groupListBeanBaseListAdapter;
    private int matchType;

    @BindView(R.id.login_titles)
    TextView loginTitles;
    @BindView(R.id.login_back)
    TextView loginBack;
    @BindView(R.id.list_view)
    MyListView listView;

    @Override
    protected void onStart() {
        super.onStart();
        getMatchInfoById();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_competition_set_category_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.wei_qi_story_list_bg);
        loginTitles.setText("创建比赛");
        loginBack.setVisibility(View.VISIBLE);
        loginBack.setOnClickListener(this);
        findViewById(R.id.button_new_group).setOnClickListener(this);
        findViewById(R.id.button_next).setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        Intent intent = getIntent();
        matchId = intent.getStringExtra("matchId");
        matchType = intent.getIntExtra("matchType", 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                showPopupWindow();
                break;
            case R.id.button_new_group:
                Intent intent = new Intent(CompetitionSetTheCategoryActivity.this, CreateNewGroupActivity.class);
                intent.putExtra("invitedSch", invitedSch);
                intent.putExtra("createOrg", createOrg);
                intent.putExtra("matchId", matchId);
                intent.putExtra("endTime", endTime);
                if (matchType == 1) {
                    intent.putExtra("matchType", 1);
                }
                startActivity(intent);
                break;
            case R.id.button_next:
                if (groupList != null && groupList.size() > 0) {
                    RxBus.get().send(RxCode.CODE_COMPETITION_MESSAGE);
                    finish();
                } else {
                    ToastUtils.show("请先创建组别");
                }
                break;
            default:
                break;
        }
    }

    private void getMatchInfoById() {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_MATCH_INFO_BYID_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("matchId", matchId)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    CompetitionSetThrCategoryBean competitionSetThrCategoryBean = GsonUtil.gsonToBean(response, CompetitionSetThrCategoryBean.class, CompetitionSetTheCategoryActivity.this);
                    if (competitionSetThrCategoryBean != null) {
                        CompetitionSetThrCategoryBean.DataBean data = competitionSetThrCategoryBean.getData();
                        if (data != null) {
                            invitedSch = data.getInvitedSch();
                            createOrg = data.getCreateOrg();
                            groupList = data.getGroupList();
                            endTime = data.getEndTime();
                            int matchPattern = data.getMatchPattern();//比赛模式 1 官方赛 2 校内赛 3 校间赛 4 班内赛
                            if (groupList != null && groupList.size() > 0) {
                                initAdapter(matchPattern);
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initAdapter(final int matchPattern) {
        groupListBeanBaseListAdapter = new BaseListAdapter<CompetitionSetThrCategoryBean.DataBean.GroupListBean>(CompetitionSetTheCategoryActivity.this, groupList, R.layout.competition_set_category_item_layout) {
            @Override
            public void convert(BaseListViewHolder holder, final int position, final CompetitionSetThrCategoryBean.DataBean.GroupListBean item) {
                String name = "组别名称：" + item.getGroupName();
                int road = item.getRoad();
                String type;
                if (road == 9) {
                    type = "比赛类型：9路吃字赛";
                } else {
                    type = "比赛类型：19路对局赛";
                }
                String leve = "参赛棋力：" + item.getLevelLimits();
                holder.setText(R.id.text_name, name);
                holder.setText(R.id.text_type, type);
                holder.setText(R.id.text_number, leve);
                holder.setButtonListener(R.id.button_new_group, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CompetitionSetTheCategoryActivity.this, CreateNewGroupActivity.class);
                        intent.putExtra("competitionSetBean", item);
                        intent.putExtra("invitedSch", invitedSch);
                        intent.putExtra("createOrg", createOrg);
                        intent.putExtra("matchId", matchId);
                        intent.putExtra("endTime", endTime);
                        if (matchType == 1 || matchPattern == 4) {
                            intent.putExtra("matchType", 1);
                        }
                        startActivity(intent);
                    }
                });
                holder.setButtonListener(R.id.button_delete, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = item.getId();
                        int matchId = item.getMatchId();
                        deleteGroup(groupList, position, id, String.valueOf(matchId));
                    }
                });

            }
        };
        listView.setAdapter(groupListBeanBaseListAdapter);
    }

    private void showPopupWindow() {
        View layout = LayoutInflater.from(CompetitionSetTheCategoryActivity.this).inflate(R.layout.create_competition_pop_window_layout, null, false);
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

    private void deleteGroup(final List<CompetitionSetThrCategoryBean.DataBean.GroupListBean> groupList, final int position, String id, String matchId) {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_DELETE_GROUP_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("id", id)
                    .addParams("matchId", matchId)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    BaseBean baseBean = GsonUtil.gsonToBean(response, BaseBean.class, CompetitionSetTheCategoryActivity.this);
                    if (baseBean != null) {
                        if (groupList != null && groupList.size() > 0) {
                            groupList.remove(position);
                            if (groupListBeanBaseListAdapter != null) {
                                groupListBeanBaseListAdapter.upData(groupList);
                            }
                        }
                    }
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
