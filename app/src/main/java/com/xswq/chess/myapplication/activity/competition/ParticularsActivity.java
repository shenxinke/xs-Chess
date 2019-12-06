package com.xswq.chess.myapplication.activity.competition;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.StudentParticularsAdatpert;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.base.BaseListAdapter;
import com.xswq.chess.myapplication.base.BaseListViewHolder;
import com.xswq.chess.myapplication.bean.StudentParticylarsBean;
import com.xswq.chess.myapplication.bean.StudentPlayByPlayBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.customview.HorizontalListView;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.DateUtil;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.xswq.chess.myapplication.utils.WindowUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * 对阵详情
 */
public class ParticularsActivity extends BaseCompatActivity implements View.OnClickListener {

    private HorizontalListView listView;
    private ListView blowListView;
    private StudentPlayByPlayBean.DataBean listBean;
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private ImageButton buttonLeft;
    private ImageButton buttonRight;
    private List<StudentPlayByPlayBean.DataBean.GroupListBean> groupList;
    private BaseListAdapter baseListAdapter;
    private List<StudentPlayByPlayBean.DataBean.GroupListBean.RoundsListBean> roundsList;
    int indext = 3;
    private int groupId;
    private int roundsId;
    private TextView noData;
    private ImageView imageNoData;
    private TextView matchTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_student_particulars_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.wei_qi_story_list_bg);
        Intent intent = getIntent();
        listBean = (StudentPlayByPlayBean.DataBean) intent.getSerializableExtra("competitionManagementBean");
        TextView loginTitles = findViewById(R.id.login_titles);
        loginTitles.setText("对阵详情");
        TextView loginBack = findViewById(R.id.login_back);
        loginBack.setVisibility(View.VISIBLE);
        loginBack.setOnClickListener(this);
        listView = findViewById(R.id.list_view);
        blowListView = findViewById(R.id.blow_list_view);
        matchTime = findViewById(R.id.match_time);

        buttonOne = findViewById(R.id.button_one);
        buttonOne.setOnClickListener(this);
        buttonTwo = findViewById(R.id.button_two);
        buttonTwo.setOnClickListener(this);
        buttonThree = findViewById(R.id.button_three);
        buttonThree.setOnClickListener(this);
        buttonLeft = findViewById(R.id.button_left_bg);
        buttonLeft.setOnClickListener(this);
        buttonRight = findViewById(R.id.button_right_bg);
        buttonRight.setOnClickListener(this);
        noData = findViewById(R.id.no_data);
        imageNoData = findViewById(R.id.image_no_data);
    }

    @Override
    protected void loadData() {
        if (listBean != null) {
            groupList = listBean.getGroupList();
            if (groupList != null && groupList.size() > 0) {
                for (int i = 0; i < groupList.size(); i++) {
                    groupList.get(i).setSelect(false);
                }
                groupList.get(0).setSelect(true);
                initAdapter(groupList);
                roundsList = groupList.get(0).getRoundsList();
                upDataView(0);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.button_one:
                oneButtonOnClick();
                if (roundsList != null && roundsList.size() > 0) {
                    roundsId = roundsList.get(indext - 3).getId();
                    long startTime = roundsList.get(indext - 3).getStartTime();
                    String createTimeString = DateUtil.getDateToString(startTime, "yyyy-MM-dd HH:mm");
                    String strMatchTime = "比赛时间：" + createTimeString;
                    matchTime.setText(strMatchTime);
                    getBlowData();
                }
                break;
            case R.id.button_two:
                twoButtonOnClick();
                if (roundsList != null && roundsList.size() > 0) {
                    roundsId = roundsList.get(indext - 2).getId();
                    long startTime = roundsList.get(indext - 2).getStartTime();
                    String createTimeString = DateUtil.getDateToString(startTime, "yyyy-MM-dd HH:mm");
                    String strMatchTime = "比赛时间：" + createTimeString;
                    matchTime.setText(strMatchTime);
                    getBlowData();
                }
                break;
            case R.id.button_three:
                threeButtonOnClick();
                if (roundsList != null && roundsList.size() > 0) {
                    roundsId = roundsList.get(indext - 1).getId();
                    long startTime = roundsList.get(indext - 1).getStartTime();
                    String createTimeString = DateUtil.getDateToString(startTime, "yyyy-MM-dd HH:mm");
                    String strMatchTime = "比赛时间：" + createTimeString;
                    matchTime.setText(strMatchTime);
                    getBlowData();
                }
                break;
            case R.id.button_left_bg:
                if (roundsList != null && roundsList.size() > 0) {
                    int number = indext - 3;
                    oneButtonOnClick();
                    if (number == 3) {
                        buttonLeft.setVisibility(View.GONE);
                    }
                    int oneIntNumber = number - 2;
                    int twoIntNumber = number - 1;
                    String ontTextStr = "第 " + oneIntNumber + " 轮";
                    String twoTextStr = "第 " + twoIntNumber + " 轮";
                    String threeTextStr = "第 " + number + " 轮";
                    buttonOne.setText(ontTextStr);
                    buttonTwo.setText(twoTextStr);
                    buttonThree.setText(threeTextStr);
                    buttonTwo.setVisibility(View.VISIBLE);
                    buttonThree.setVisibility(View.VISIBLE);
                    roundsId = roundsList.get(oneIntNumber - 1).getId();
                    long startTime = roundsList.get(oneIntNumber - 1).getStartTime();
                    String createTimeString = DateUtil.getDateToString(startTime, "yyyy-MM-dd HH:mm");
                    String strMatchTime = "比赛时间：" + createTimeString;
                    matchTime.setText(strMatchTime);
                    getBlowData();
                    indext = number;
                }
                break;
            case R.id.button_right_bg:
                if (roundsList != null && roundsList.size() > 0) {
                    buttonLeft.setVisibility(View.VISIBLE);
                    int number = indext + 3;
                    int size = roundsList.size();
                    oneButtonOnClick();
                    if (number >= size) {
                        int goneNumber = number - size;
                        if (goneNumber == 1) {
                            buttonTwo.setVisibility(View.GONE);
                        } else if (goneNumber == 2) {
                            buttonTwo.setVisibility(View.GONE);
                            buttonThree.setVisibility(View.GONE);
                        }
                        buttonRight.setVisibility(View.GONE);
                    }
                    int oneIntNumber = number - 2;
                    int twoIntNumber = number - 1;
                    String ontTextStr = "第 " + oneIntNumber + " 轮";
                    String twoTextStr = "第 " + twoIntNumber + " 轮";
                    String threeTextStr = "第 " + number + " 轮";
                    buttonOne.setText(ontTextStr);
                    buttonTwo.setText(twoTextStr);
                    buttonThree.setText(threeTextStr);
                    roundsId = roundsList.get(oneIntNumber - 1).getId();
                    long startTime = roundsList.get(oneIntNumber - 1).getStartTime();
                    String createTimeString = DateUtil.getDateToString(startTime, "yyyy-MM-dd HH:mm");
                    String strMatchTime = "比赛时间：" + createTimeString;
                    matchTime.setText(strMatchTime);
                    getBlowData();
                    indext = number;
                }
                break;
            default:
                break;
        }
    }

    private void initAdapter(List<StudentPlayByPlayBean.DataBean.GroupListBean> shareList) {
        baseListAdapter = new BaseListAdapter<StudentPlayByPlayBean.DataBean.GroupListBean>(XSWQApplication.getInstance(), shareList, R.layout.student_particulars_horizontal_item_layout) {
            @Override
            public void convert(BaseListViewHolder holder, int position, StudentPlayByPlayBean.DataBean.GroupListBean item) {
                holder.setText(R.id.button_item, Util.signString(item.getGroupName()));
                if (item.isSelect()) {
                    holder.setTextBgById(R.id.button_item, R.drawable.shape_particulars_btn);
                    holder.setTextPadingLRById(R.id.button_item, WindowUtils.sp2px(ParticularsActivity.this, 10));
                    holder.setTextColor(R.id.button_item, R.color.color_774801);
                } else {
                    holder.setTextBgById(R.id.button_item, R.drawable.shape_particulars_un_btn);
                    holder.setTextPadingLRById(R.id.button_item, WindowUtils.sp2px(ParticularsActivity.this, 10));
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
                int leftRightPading = WindowUtils.sp2px(ParticularsActivity.this, 10);
                itemButton.setPadding(leftRightPading, 0, leftRightPading, 0);
                itemButton.setTextColor(getResources().getColor(R.color.color_774801));
                if (groupList != null && groupList.size() > 0) {
                    for (int i = 0; i < groupList.size(); i++) {
                        groupList.get(i).setSelect(false);
                    }
                    groupList.get(position).setSelect(true);
                    baseListAdapter.notifyDataSetChanged();
                    oneButtonOnClick();
                    roundsList = groupList.get(position).getRoundsList();
                    buttonLeft.setVisibility(View.GONE);
                    long startTime = roundsList.get(0).getStartTime();
                    String createTimeString = DateUtil.getDateToString(startTime, "yyyy-MM-dd HH:mm");
                    String strMatchTime = "比赛时间：" + createTimeString;
                    matchTime.setText(strMatchTime);
                    indext = 3;
                    upDataView(position);
                }
            }
        });
    }

    private void upDataView(int position) {
        if (roundsList != null && roundsList.size() > 0) {
            groupId = groupList.get(position).getId();
            roundsId = roundsList.get(0).getId();
            long startTime = roundsList.get(0).getStartTime();
            String createTimeString = DateUtil.getDateToString(startTime, "yyyy-MM-dd HH:mm");
            String strMatchTime = "比赛时间：" + createTimeString;
            matchTime.setText(strMatchTime);
            getBlowData();
            if (roundsList.size() <= indext) {
                buttonRight.setVisibility(View.GONE);
            } else {
                buttonRight.setVisibility(View.VISIBLE);
            }
            switch (roundsList.size()) {
                case 1:
                    buttonOne.setText("第 1 轮");
                    buttonOne.setVisibility(View.VISIBLE);
                    buttonOne.setTextColor(getResources().getColor(R.color.white));
                    buttonTwo.setVisibility(View.GONE);
                    buttonThree.setVisibility(View.GONE);
                    break;
                case 2:
                    buttonOne.setText("第 1 轮");
                    buttonOne.setVisibility(View.VISIBLE);
                    buttonOne.setTextColor(getResources().getColor(R.color.white));
                    buttonTwo.setText("第 2 轮");
                    buttonTwo.setVisibility(View.VISIBLE);
                    buttonTwo.setTextColor(getResources().getColor(R.color.color_0461d2));
                    buttonThree.setVisibility(View.GONE);
                    break;
                default:
                    buttonOne.setText("第 1 轮");
                    buttonOne.setTextColor(getResources().getColor(R.color.white));
                    buttonOne.setVisibility(View.VISIBLE);
                    buttonTwo.setText("第 2 轮");
                    buttonTwo.setVisibility(View.VISIBLE);
                    buttonTwo.setTextColor(getResources().getColor(R.color.color_0461d2));
                    buttonThree.setText("第 3 轮");
                    buttonThree.setVisibility(View.VISIBLE);
                    buttonThree.setTextColor(getResources().getColor(R.color.color_0461d2));
                    break;
            }
        } else {
            blowListView.setVisibility(View.GONE);
            noData.setVisibility(View.VISIBLE);
            imageNoData.setVisibility(View.VISIBLE);
        }
    }

    private void oneButtonOnClick() {
        if (roundsList != null && roundsList.size() > 0) {
            buttonOne.setBackgroundResource(R.mipmap.student_particulars_blow_list_check);
            buttonTwo.setBackgroundResource(R.mipmap.student_particulars_blow_list_uncheck);
            buttonThree.setBackgroundResource(R.mipmap.student_particulars_blow_list_uncheck);
            switch (roundsList.size()) {
                case 1:
                    buttonOne.setTextColor(getResources().getColor(R.color.white));
                    buttonTwo.setVisibility(View.GONE);
                    buttonThree.setVisibility(View.GONE);
                    break;
                case 2:
                    buttonOne.setTextColor(getResources().getColor(R.color.white));
                    buttonTwo.setTextColor(getResources().getColor(R.color.color_0461d2));
                    buttonThree.setVisibility(View.GONE);
                    break;
                default:
                    buttonOne.setTextColor(getResources().getColor(R.color.white));
                    buttonTwo.setTextColor(getResources().getColor(R.color.color_0461d2));
                    buttonThree.setTextColor(getResources().getColor(R.color.color_0461d2));
                    break;
            }
        }
    }

    private void twoButtonOnClick() {
        if (roundsList != null && roundsList.size() > 0) {
            buttonOne.setBackgroundResource(R.mipmap.student_particulars_blow_list_uncheck);
            buttonTwo.setBackgroundResource(R.mipmap.student_particulars_blow_list_check);
            buttonThree.setBackgroundResource(R.mipmap.student_particulars_blow_list_uncheck);
            switch (roundsList.size()) {
                case 2:
                    buttonOne.setTextColor(getResources().getColor(R.color.color_0461d2));
                    buttonTwo.setTextColor(getResources().getColor(R.color.white));
                    buttonThree.setVisibility(View.GONE);
                    break;
                default:
                    buttonOne.setTextColor(getResources().getColor(R.color.color_0461d2));
                    buttonTwo.setTextColor(getResources().getColor(R.color.white));
                    buttonThree.setTextColor(getResources().getColor(R.color.color_0461d2));
                    break;
            }
        }
    }

    private void threeButtonOnClick() {
        if (roundsList != null && roundsList.size() > 0) {
            buttonOne.setBackgroundResource(R.mipmap.student_particulars_blow_list_uncheck);
            buttonTwo.setBackgroundResource(R.mipmap.student_particulars_blow_list_uncheck);
            buttonThree.setBackgroundResource(R.mipmap.student_particulars_blow_list_check);
            buttonOne.setTextColor(getResources().getColor(R.color.color_0461d2));
            buttonTwo.setTextColor(getResources().getColor(R.color.color_0461d2));
            buttonThree.setTextColor(getResources().getColor(R.color.white));
        }
    }

    private void getBlowData() {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_GETARENAINFO_PATH)
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
                    StudentParticylarsBean studentParticylarsBean = GsonUtil.gsonToBean(response, StudentParticylarsBean.class, ParticularsActivity.this);
                    if (studentParticylarsBean != null) {
                        List<StudentParticylarsBean.DataBean> data = studentParticylarsBean.getData();
                        if (data != null && data.size() > 0) {
                            StudentParticularsAdatpert studentParticularsAdatpert = new StudentParticularsAdatpert(ParticularsActivity.this, data, token, userId);
                            blowListView.setAdapter(studentParticularsAdatpert);
                            blowListView.setVisibility(View.VISIBLE);
                            noData.setVisibility(View.GONE);
                            imageNoData.setVisibility(View.GONE);
                        } else {
                            blowListView.setVisibility(View.GONE);
                            noData.setVisibility(View.VISIBLE);
                            imageNoData.setVisibility(View.VISIBLE);
                        }
                    } else {
                        blowListView.setVisibility(View.GONE);
                        noData.setVisibility(View.VISIBLE);
                        imageNoData.setVisibility(View.VISIBLE);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
