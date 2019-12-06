package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.competition.PlayByPlayActivity;
import com.xswq.chess.myapplication.activity.competition.ParticularsActivity;
import com.xswq.chess.myapplication.activity.competition.ResultOfTheMatchActivity;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.bean.StudentCompetitionManagementBean;
import com.xswq.chess.myapplication.bean.StudentPlayByPlayBean;
import com.xswq.chess.myapplication.bean.UserIsNullBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.DateUtil;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.LogUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class StudentCompetitionManagementAdapter extends BaseAdapter {
    private Context context;
    private List<StudentCompetitionManagementBean.DataBean> dataList;
    private String token;
    private String userId;
    private SparseArray<CountDownTimer> countDownCounters;

    public StudentCompetitionManagementAdapter(Context context, List<StudentCompetitionManagementBean.DataBean> dataList, String token, String uid) {
        this.context = context;
        this.dataList = dataList;
        this.token = token;
        this.userId = uid;
        this.countDownCounters = new SparseArray<>();
    }

    public void upData(List<StudentCompetitionManagementBean.DataBean> data) {
        this.dataList = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ComViewHolder comHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.student_competition_item_layout, parent, false);
            comHolder = new ComViewHolder();
            comHolder.competitionName = convertView.findViewById(R.id.competition_name);
            comHolder.competitionType = convertView.findViewById(R.id.competition_type);
            comHolder.competitionTime = convertView.findViewById(R.id.competition_time);
            comHolder.competitionStatus = convertView.findViewById(R.id.competition_status);
            comHolder.buttonStatus = convertView.findViewById(R.id.button_status);
            comHolder.buttonManagement = convertView.findViewById(R.id.button_management);
            comHolder.startTimeText = convertView.findViewById(R.id.start_time);
            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }
        String competitionName = dataList.get(position).getMatchName();
        String competitionType;
        int matchPattern = dataList.get(position).getMatchPattern();
        switch (matchPattern) {
            case 1:
                competitionType = "比赛模式:  官方赛";
                break;
            case 2:
                competitionType = "比赛模式:  校内赛";
                break;
            case 3:
                competitionType = "比赛模式:  校间赛";
                break;
            case 4:
                competitionType = "比赛模式:  班内赛";
                break;
            default:
                competitionType = "比赛模式:";
                break;
        }
        long startTime = dataList.get(position).getStartTime();
        long endTime = dataList.get(position).getEndTime();
        String strStartTime = DateUtil.getDateToString(startTime, "yyyy-MM-dd HH:mm");
        String strEndTime = DateUtil.getDateToString(endTime, "yyyy-MM-dd HH:mm");
        String competitionTime = "报名时间:  " + strStartTime + " ~ " + strEndTime;
        final int state = dataList.get(position).getState();
        String competitionStatus;
        // state   1 报名中未报名  2 报名中已经报名  3比赛中未报名 4比赛中已经报名   5 比赛结束
        switch (state) {
            case 1:
            case 2:
                competitionStatus = "比赛状态:  报名中";
                comHolder.buttonStatus.setText("比赛详情");
                comHolder.buttonStatus.setVisibility(View.VISIBLE);
                comHolder.buttonManagement.setVisibility(View.GONE);
                List<StudentCompetitionManagementBean.DataBean.GroupListBean> groupList = dataList.get(position).getGroupList();
                if (state == 2 && groupList != null) {
                    for (int i = 0; i < groupList.size(); i++) {
                        int isExist = groupList.get(i).getIsExist();
                        if (isExist == 1) { //isExist 1在组内 2不在
                            comHolder.startTimeText.setVisibility(View.VISIBLE);
                            break;
                        } else {
                            comHolder.startTimeText.setVisibility(View.GONE);
                        }
                    }
                } else {
                    comHolder.startTimeText.setVisibility(View.GONE);
                }
                break;
            case 3:
            case 4:
                competitionStatus = "比赛状态:  比赛中";
                if (state == 3) {
                    comHolder.buttonStatus.setVisibility(View.VISIBLE);
                    comHolder.buttonManagement.setVisibility(View.GONE);
                } else {
                    comHolder.buttonStatus.setVisibility(View.VISIBLE);
                    comHolder.buttonManagement.setVisibility(View.VISIBLE);
                }
                comHolder.buttonStatus.setText("对阵详情");
                comHolder.buttonManagement.setText("进入比赛");
                comHolder.startTimeText.setVisibility(View.GONE);
                break;
            case 5:
                competitionStatus = "比赛状态:  比赛结束";
                comHolder.buttonStatus.setText("比赛结果");
                comHolder.buttonStatus.setVisibility(View.VISIBLE);
                comHolder.buttonManagement.setVisibility(View.GONE);
                comHolder.startTimeText.setVisibility(View.GONE);
                break;
            case 6:
                competitionStatus = "比赛状态:  待比赛中";
                comHolder.buttonStatus.setText("比赛详情");
                comHolder.buttonStatus.setVisibility(View.VISIBLE);
                comHolder.buttonManagement.setVisibility(View.GONE);
                comHolder.startTimeText.setVisibility(View.GONE);
                break;
            default:
                competitionStatus = "比赛状态:";
                comHolder.buttonStatus.setVisibility(View.GONE);
                comHolder.buttonManagement.setVisibility(View.GONE);
                comHolder.startTimeText.setVisibility(View.GONE);
                break;
        }
        comHolder.competitionName.setText(competitionName);
        comHolder.competitionType.setText(competitionType);
        comHolder.competitionTime.setText(competitionTime);
        comHolder.competitionStatus.setText(competitionStatus);
        // state   1 报名中未报名  2 报名中已经报名  3比赛中未报名 4比赛中已经报名   5 比赛结束
        comHolder.buttonStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = dataList.get(position).getId();
                switch (state) {
                    case 1:
                    case 2:
                    case 6:
                        getData(id, 1);
                        break;
                    case 3:
                    case 4:
                        getData(id, 2);
                        break;
                    case 5:
                        getData(id, 3);
                        break;
                    default:
                        break;
                }

            }
        });
        comHolder.buttonManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<StudentCompetitionManagementBean.DataBean.GroupListBean> groupList = dataList.get(position).getGroupList();
                for (int i = 0; i < groupList.size(); i++) {
                    int isExist = groupList.get(i).getIsExist();//1 当前用户存在该分组 2不存在
                    if (isExist == 1) {
                        List<StudentCompetitionManagementBean.DataBean.GroupListBean.RoundsListBean> roundsList = groupList.get(i).getRoundsList();
                        if (roundsList == null || roundsList.isEmpty()) return;
                        for (int j = 0; j < roundsList.size(); j++) {
                            int overType = roundsList.get(j).getOverType();
                            if (overType == 1) {
                                int groupId = roundsList.get(i).getGroupId();
                                getUserIsNull(groupId, v);
                                return;
                            }
                        }
                    }
                }
                showPopWindow(v, "比赛尚未开始，请耐心等待");
            }
        });
        setItemStartTime(startTime, position, comHolder);
        return convertView;
    }

    private void getUserIsNull(int groupId, final View view) {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_USER_IS_NULL_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("userId", userId)
                    .addParams("groupId", String.valueOf(groupId))
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    UserIsNullBean userIsNullBean = GsonUtil.gsonToBean(response, UserIsNullBean.class, context);
                    if (userIsNullBean != null) {
                        UserIsNullBean.DataBean data = userIsNullBean.getData();
                        if (data == null) return;
                        String blackUserId = data.getBlackUserId();
                        String whiteUserId = data.getWhiteUserId();
                        if (Const.STR0.equals(blackUserId) || Const.STR0.equals(whiteUserId)) {
                            showPopWindow(view, "本局轮空");
                        } else {
                            Intent intent = new Intent(context, JsWebViewActivity.class);
                            intent.putExtra("prefix", ContactUrl.MATCHPLAY);
                            context.startActivity(intent);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setItemStartTime(long startTime, int position, final ComViewHolder comHolder) {
        long matchStartTime = 0;
        List<StudentCompetitionManagementBean.DataBean.GroupListBean> groupList = dataList.get(position).getGroupList();
        if (groupList == null) return;
        List<StudentCompetitionManagementBean.DataBean.GroupListBean.RoundsListBean> roundsList = groupList.get(0).getRoundsList();
        if (roundsList != null && !roundsList.isEmpty()) {
            matchStartTime = roundsList.get(0).getStartTime();
        }

        long nowTime = System.currentTimeMillis();
        long distanceStartTime = matchStartTime - nowTime;
        CountDownTimer countDownTimer = countDownCounters.get(comHolder.startTimeText.hashCode());
        //将前一个缓存清除
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        LogUtil.e("开始时间：" + startTime + " 现在时间：" + nowTime + " 剩余时间：" + distanceStartTime);
        if (distanceStartTime > 0) {
            countDownTimer = new CountDownTimer(distanceStartTime, 1000) {
                public void onTick(long millisUntilFinished) {
                    String dateToString = "距开始\n" + DateUtil.getSjc(millisUntilFinished);
                    comHolder.startTimeText.setText(dateToString);
                }

                public void onFinish() {
                    comHolder.startTimeText.setText("距开始\n00:00:00");
                }
            }.start();
            countDownCounters.put(comHolder.startTimeText.hashCode(), countDownTimer);
        } else {
            comHolder.startTimeText.setText("距开始\n00:00:00");
        }
    }

    class ComViewHolder {
        TextView competitionName;
        TextView competitionType;
        TextView competitionTime;
        TextView competitionStatus;
        TextView startTimeText;
        Button buttonStatus;
        Button buttonManagement;
    }

    private void getData(int id, final int type) {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_STU_MATCH_BY_ID_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("matchId", String.valueOf(id))
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    StudentPlayByPlayBean studentPlayByPlayBean = GsonUtil.gsonToBean(response, StudentPlayByPlayBean.class, context);
                    if (studentPlayByPlayBean != null) {
                        StudentPlayByPlayBean.DataBean data = studentPlayByPlayBean.getData();
                        if (data != null) {
                            Intent intent;
                            if (type == 1) { //1 比赛详情  2对阵详情 3比赛结束
                                intent = new Intent(context, PlayByPlayActivity.class);
                                intent.putExtra("flag", false);
                            } else if (type == 2) {
                                intent = new Intent(context, ParticularsActivity.class);
                            } else {
                                intent = new Intent(context, ResultOfTheMatchActivity.class);
                            }
                            intent.putExtra("competitionManagementBean", data);
                            context.startActivity(intent);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清空资源
     */
    public void cancelAllTimers() {
        if (countDownCounters == null) {
            return;
        }
        for (int i = 0, length = countDownCounters.size(); i < length; i++) {
            CountDownTimer cdt = countDownCounters.get(countDownCounters.keyAt(i));
            if (cdt != null) {
                cdt.cancel();
            }
        }
    }


    private void showPopWindow(View view, String strContent) {
        View layout = LayoutInflater.from(context).inflate(R.layout.student_competition_pop_layout, null, false);
        final PopupWindow popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        Button confirm = layout.findViewById(R.id.button_confirm);
        TextView content = layout.findViewById(R.id.text_content);
        content.setText(strContent);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button_confirm:
                        popupWindow.dismiss();
                        break;
                    default:
                        break;
                }
            }
        };
        confirm.setOnClickListener(listener);
    }

}

