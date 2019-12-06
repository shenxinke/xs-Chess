package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.competition.CompetitionApplyManagementActivity;
import com.xswq.chess.myapplication.activity.competition.CreateCompetitionActivity;
import com.xswq.chess.myapplication.activity.competition.PlayByPlayActivity;
import com.xswq.chess.myapplication.activity.competition.ParticularsActivity;
import com.xswq.chess.myapplication.activity.competition.ResultOfTheMatchActivity;
import com.xswq.chess.myapplication.bean.CompetitionManagementBean;
import com.xswq.chess.myapplication.bean.StudentPlayByPlayBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.DateUtil;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class CompetitionManagementAdapter extends BaseAdapter {
    private Context context;
    private List<CompetitionManagementBean.DataBean.ListBean> dataList;
    private CompetitionManagementBean.DataBean.ListBean listBean;
    private String token;
    private String userId;
    private String userType;
    private String orgNo;

    public CompetitionManagementAdapter(Context context, List<CompetitionManagementBean.DataBean.ListBean> dataList,
                                        String token, String userId, String userType, String orgNo) {
        this.context = context;
        this.dataList = dataList;
        this.token = token;
        this.userId = userId;
        this.userType = userType;
        this.orgNo = orgNo;
    }

    public void upData(List<CompetitionManagementBean.DataBean.ListBean> data) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.competition_item_layout, null);
            comHolder = new ComViewHolder();
            comHolder.competitionName = convertView.findViewById(R.id.competition_name);
            comHolder.competitionType = convertView.findViewById(R.id.competition_type);
            comHolder.competitionTime = convertView.findViewById(R.id.competition_time);
            comHolder.competitionStatus = convertView.findViewById(R.id.competition_status);
            comHolder.buttonStatus = convertView.findViewById(R.id.button_status);
            comHolder.buttonManagement = convertView.findViewById(R.id.button_management);
            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }
        String competitionName = "比赛名称:  " + dataList.get(position).getMatchName();
        String competitionType;
        final int matchPattern = dataList.get(position).getMatchPattern();
        switch (matchPattern) {
            case 1:
                competitionType = "比赛类型:  官方赛";
                break;
            case 2:
                competitionType = "比赛类型:  校内赛";
                break;
            case 3:
                competitionType = "比赛类型:  校间赛";
                break;
            case 4:
                competitionType = "比赛类型:  班内赛";
                break;
            default:
                competitionType = "比赛类型:";
                break;
        }
        long startTime = dataList.get(position).getStartTime();
        long endTime = dataList.get(position).getEndTime();
        String strStartTime = DateUtil.getDateToString(startTime, "yyyy-MM-dd HH:mm");
        String strEndTime = DateUtil.getDateToString(endTime, "yyyy-MM-dd HH:mm");
        String competitionTime = "报名时间:  " + strStartTime + " ~ " + strEndTime;
        final int stage = dataList.get(position).getStage();
        final String createUser = dataList.get(position).getCreateUser();
        final String invitedSch = dataList.get(position).getInvitedSch();
        String competitionStatus;
        //0 对局无效 1 邀请中  2 同意  3 拒绝  4 待报名中 5 报名中 6 待比赛中 7 比赛中 8 比赛结束  9 比赛结束报名人数未达到要求
        switch (stage) {
            case 0:
                competitionStatus = "比赛状态:  对局无效";
                comHolder.buttonStatus.setText("比赛详情");
                comHolder.buttonStatus.setVisibility(View.VISIBLE);
                comHolder.buttonManagement.setVisibility(View.GONE);
                break;
            case 1:
                competitionStatus = "比赛状态:  邀请中";
                comHolder.buttonStatus.setText("比赛详情");
                comHolder.buttonStatus.setVisibility(View.VISIBLE);
                comHolder.buttonManagement.setVisibility(View.GONE);
                break;
            case 2:
                competitionStatus = "比赛状态:  同意";
                comHolder.buttonStatus.setText("比赛详情");
                comHolder.buttonStatus.setVisibility(View.VISIBLE);
                comHolder.buttonManagement.setVisibility(View.GONE);
                break;
            case 3:
                competitionStatus = "比赛状态:  拒绝";
                comHolder.buttonStatus.setText("比赛详情");
                comHolder.buttonStatus.setVisibility(View.VISIBLE);
                comHolder.buttonManagement.setVisibility(View.GONE);
                break;
            case 4:
                competitionStatus = "比赛状态:  待报名中";
                comHolder.buttonStatus.setVisibility(View.VISIBLE);
                comHolder.buttonManagement.setVisibility(View.GONE);
                if (matchPattern == 1) { //1 官方赛 2 校内赛 3 校间赛 4 班内赛
                    comHolder.buttonStatus.setText("比赛详情");
                } else {
                    if (userId.equals(createUser)) {
                        comHolder.buttonStatus.setText("修改规则");
                    } else {
                        comHolder.buttonStatus.setText("比赛详情");
                    }
                }
                break;
            case 5:
                competitionStatus = "比赛状态:  报名中";
                comHolder.buttonStatus.setText("修改规则");
                comHolder.buttonManagement.setText("报名管理");
                comHolder.buttonManagement.setVisibility(View.VISIBLE);
                comHolder.buttonStatus.setVisibility(View.VISIBLE);
                if (matchPattern == 1) { //1 官方赛 2 校内赛 3 校间赛 4 班内赛
                    comHolder.buttonStatus.setText("比赛详情");
                } else {
                    if (userId.equals(createUser)) {
                        comHolder.buttonStatus.setText("修改规则");
                    } else {
                        comHolder.buttonStatus.setText("比赛详情");
                    }
                }
                break;
            case 6:
                competitionStatus = "比赛状态:  待比赛中";
                comHolder.buttonStatus.setText("比赛详情");
                comHolder.buttonStatus.setVisibility(View.VISIBLE);
                comHolder.buttonManagement.setVisibility(View.GONE);
                break;
            case 7:
                competitionStatus = "比赛状态:  比赛中";
                comHolder.buttonStatus.setText("对阵详情");
                comHolder.buttonStatus.setVisibility(View.VISIBLE);
                comHolder.buttonManagement.setVisibility(View.GONE);
                break;
            case 8:
                competitionStatus = "比赛状态:  比赛结束";
                comHolder.buttonStatus.setText("比赛结果");
                comHolder.buttonStatus.setVisibility(View.VISIBLE);
                comHolder.buttonManagement.setVisibility(View.GONE);
                break;
            case 9:
                competitionStatus = "比赛状态:  比赛结束报名人数未达到要求";
                comHolder.buttonStatus.setText("比赛详情");
                comHolder.buttonStatus.setVisibility(View.VISIBLE);
                comHolder.buttonManagement.setVisibility(View.GONE);
                break;
            default:
                competitionStatus = "比赛状态:";
                break;
        }

        comHolder.competitionName.setText(competitionName);
        comHolder.competitionType.setText(competitionType);
        comHolder.competitionTime.setText(competitionTime);
        comHolder.competitionStatus.setText(competitionStatus);
//0 对局无效 1 邀请中  2 同意  3 拒绝  4 待报名中 5 报名中 6 待比赛中 7 比赛中 8 比赛结束  9 比赛结束报名人数未达到要求
        comHolder.buttonStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                String id = dataList.get(position).getId();
                switch (stage) {
                    case 1:
                    case 2:
                    case 3:
                    case 6:
                    case 9:
                        //比赛详情
                        getData(id, 1, stage, matchPattern, createUser, invitedSch);
                        break;
                    case 4:
                    case 5:
                        if (userId.equals(createUser)) {
                            //修改规则
                            listBean = dataList.get(position);
                            intent = new Intent(context, CreateCompetitionActivity.class);
                            intent.putExtra("competitionManagementBean", listBean);
                            if ("1".equals(userType) || matchPattern == 4) {
                                intent.putExtra("teacherType", true);
                            }
                            context.startActivity(intent);
                        } else {
                            getData(id, 1, stage, matchPattern, createUser, invitedSch);
                        }
                        break;
                    case 7:
                        //对阵详情
                        getData(id, 2, stage, matchPattern, createUser, invitedSch);
                        break;
                    case 8:
                        //比赛结果
                        getData(id, 3, stage, matchPattern, createUser, invitedSch);
                        break;
                    default:
                        break;
                }

            }
        });
        comHolder.buttonManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = dataList.get(position).getId();
                listBean = dataList.get(position);
                Intent intent = new Intent(context, CompetitionApplyManagementActivity.class);
                intent.putExtra("matchId", id);
                intent.putExtra("competitionManagementBean", listBean);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ComViewHolder {
        TextView competitionName;
        TextView competitionType;
        TextView competitionTime;
        TextView competitionStatus;
        Button buttonStatus;
        Button buttonManagement;
    }

    private void getData(String id, final int type, final int stage, final int matchPattern, final String createUser, final String invitedSch) {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_STU_MATCH_BY_ID_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("matchId", id)
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
                                if (stage == 1 && matchPattern == 3 && !userId.equals(createUser) && orgNo.equals(invitedSch)) {
                                    intent.putExtra("flag", true);
                                }
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
}
