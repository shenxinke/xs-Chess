package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.base.BaseBean;
import com.xswq.chess.myapplication.base.BaseListAdapter;
import com.xswq.chess.myapplication.base.BaseListViewHolder;
import com.xswq.chess.myapplication.bean.StudentPlayByPlayBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.DateUtil;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class PlayByPlayAdapter extends BaseAdapter {

    private Context context;
    private List<StudentPlayByPlayBean.DataBean.GroupListBean> groupList;
    private List<StudentPlayByPlayBean.DataBean.GroupListBean> groupListAll = new ArrayList<>();
    private String[] strings = new String[]{"第一轮:  ", "第二轮:  ", "第三轮:  ", "第四轮:  ", "第五轮:  ", "第六轮:  ", "第七轮:  ", "第八轮:  ",
            "第九轮:  ", "第十轮:  ", "第十一轮:  ", "第十二轮:  ", "第十三轮:  "};
    private List<String> listString = new ArrayList<>();
    private String token;
    private String userId;
    private int stage;
    private int userType;

    public PlayByPlayAdapter(Context context, List<StudentPlayByPlayBean.DataBean.GroupListBean> groupList, String token, String userId, int userType, int stage) {
        this.context = context;
        this.groupList = groupList;
        this.token = token;
        this.userId = userId;
        this.stage = stage;
        this.userType = userType;
        this.groupListAll.addAll(groupList);
    }


    @Override
    public int getCount() {
        return groupList.size();
    }

    @Override
    public Object getItem(int position) {
        return groupList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.student_play_by_play_item_layout, null);
            holder.groupName = convertView.findViewById(R.id.group_name);
            holder.matchType = convertView.findViewById(R.id.match_type);
            holder.matchTournament = convertView.findViewById(R.id.match_tournament);
            holder.matchWay = convertView.findViewById(R.id.match_way);
            holder.matchJudgeName = convertView.findViewById(R.id.match_judgeName);
            holder.matchLevel = convertView.findViewById(R.id.match_level);
            holder.matchPeople = convertView.findViewById(R.id.match_people);
            holder.matchRound = convertView.findViewById(R.id.match_round);
            holder.buttonLook = convertView.findViewById(R.id.button_look);
            holder.matchRule = convertView.findViewById(R.id.match_rule);
            holder.layTime = convertView.findViewById(R.id.lay_time);
            holder.matchTime = convertView.findViewById(R.id.match_time);
            holder.matchSecondsTime = convertView.findViewById(R.id.match_seconds_time);
            holder.buttonApply = convertView.findViewById(R.id.button_apply);
            holder.textNameFull = convertView.findViewById(R.id.text_name_full);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        String groupName = groupList.get(position).getGroupName();
        int road = groupList.get(position).getRoad();
        int judgeChess = groupList.get(position).getJudgeChess();
        String participantNumber = groupList.get(position).getParticipantNum();
        String judgeName = "裁         判:  " + groupList.get(position).getJudgeName();
        String levelLimits = "参赛棋力:  " + groupList.get(position).getLevelLimits();
        String participantNum = "参赛人数:  " + groupList.get(position).getParticipantNum() + "人";
        String rounds = "比赛轮次:  " + groupList.get(position).getRounds() + "轮";
        String lateTime = "迟到时间:  " + groupList.get(position).getLateTime() + "分钟";
        String baseTime = "对局时间:  " + groupList.get(position).getBaseTime() + "分钟";
        int countdownNum = groupList.get(position).getCountdownNum();
        String participant = participantNumber.substring(participantNumber.indexOf("-") + 1);
        int intParticipant = Integer.parseInt(participant);
        int currentNum = groupList.get(position).getCurrentNum();
        int isExist = groupList.get(position).getIsExist();
        holder.groupName.setText(Util.signString(groupName));
        if (road == 9) {
            holder.matchType.setText("比赛类型:  9路吃子赛");
            holder.matchRule.setText("对局规则:  吃三子胜");
        } else {
            holder.matchType.setText("比赛类型:  19路对局赛");
            holder.matchRule.setText("对局规则:  分先  黑贴3又3/4子");
        }
        holder.matchTournament.setText("比赛赛制:  积分循环赛");
        if (judgeChess == 0) {
            holder.matchWay.setText("判棋方式:  AI判棋");
            holder.matchJudgeName.setVisibility(View.GONE);
        } else {
            holder.matchWay.setText("判棋方式:  裁判判棋");
            holder.matchJudgeName.setText(Util.signString(judgeName));
            holder.matchJudgeName.setVisibility(View.VISIBLE);
        }
        holder.matchLevel.setText(levelLimits);
        holder.matchPeople.setText(participantNum);
        holder.matchRound.setText(rounds);
        holder.layTime.setText(lateTime);
        holder.matchTime.setText(baseTime);
        switch (countdownNum) {
            case 1:
                holder.matchSecondsTime.setText("读秒时间:  30秒/1次");
                break;
            case 2:
                holder.matchSecondsTime.setText("读秒时间:  30秒/2次");
                break;
            case 3:
                holder.matchSecondsTime.setText("读秒时间:  30秒/3次");
                break;
            default:
                break;
        }
        if (userType == 3) {
            if (currentNum >= intParticipant) {
                if (stage == 6) {
                    holder.buttonApply.setVisibility(View.GONE);
                    holder.textNameFull.setVisibility(View.GONE);
                } else {
                    if (isExist == 1) {
                        holder.buttonApply.setBackgroundResource(R.mipmap.play_by_play_apply);
                        holder.buttonApply.setText("取消报名");
                        holder.buttonApply.setVisibility(View.VISIBLE);
                        holder.textNameFull.setVisibility(View.GONE);
                    } else {
                        holder.buttonApply.setVisibility(View.GONE);
                        holder.textNameFull.setVisibility(View.VISIBLE);
                    }
                }
            } else {
                if (stage == 6) {
                    holder.buttonApply.setVisibility(View.GONE);
                    holder.textNameFull.setVisibility(View.GONE);
                } else {
                    holder.textNameFull.setVisibility(View.INVISIBLE);
                    holder.buttonApply.setVisibility(View.VISIBLE);
                }
                switch (isExist) {
                    case 1:
                        if (stage == 6) {
                            holder.buttonApply.setVisibility(View.GONE);
                        } else {
                            holder.buttonApply.setBackgroundResource(R.mipmap.play_by_play_apply);
                            holder.buttonApply.setText("取消报名");
                        }
                        break;
                    case 2:
                        holder.buttonApply.setBackgroundResource(R.mipmap.play_by_play_apply);
                        holder.buttonApply.setText("报名");
                        break;
                    case 3:
                        holder.buttonApply.setBackgroundResource(R.mipmap.play_by_play_unapply);
                        holder.buttonApply.setText("报名");
                        break;
                    default:
                        break;
                }
            }
        } else {
            holder.textNameFull.setVisibility(View.GONE);
            holder.buttonApply.setVisibility(View.GONE);
        }
        holder.buttonLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<StudentPlayByPlayBean.DataBean.GroupListBean.RoundsListBean> roundsList = groupList.get(position).getRoundsList();
                if (listString.size() > 0) listString.clear();
                for (int i = 0; i < roundsList.size(); i++) {
                    long startTime = roundsList.get(i).getStartTime();
                    String dateToString = strings[i] + DateUtil.getDateToString(startTime, "yyyy-MM-dd HH:mm");
                    listString.add(dateToString);
                }
                showPopupWindow(view);
            }
        });
        holder.buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isExist = groupList.get(position).getIsExist();
                int id = groupList.get(position).getId();
                String matchId = groupList.get(position).getMatchId();
                if (isExist == 1) {
                    getData(ContactUrl.POST_DELETE_ENROLL_PATH, id, position, null);
                } else if (isExist == 2) {
                    getData(ContactUrl.POST_CREATE_ENROLL_PATH, id, position, matchId);
                }

            }
        });
        return convertView;
    }

    static class Holder {
        TextView groupName;
        TextView matchType;
        TextView matchTournament;
        TextView matchWay;
        TextView matchJudgeName;
        TextView matchLevel;
        TextView matchPeople;
        TextView matchRound;
        Button buttonLook;
        TextView matchRule;
        TextView layTime;
        TextView matchTime;
        TextView matchSecondsTime;
        Button buttonApply;
        TextView textNameFull;
    }

    private void showPopupWindow(View view) {
        View layout = LayoutInflater.from(context).inflate(R.layout.play_by_play_popwindow_layout, null, false);
        final PopupWindow popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setClippingEnabled(false);
        ColorDrawable dw = new ColorDrawable(0xff);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        ListView listView = layout.findViewById(R.id.list_view);
        Button btnCancel = layout.findViewById(R.id.guanbi);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.guanbi:
                        popupWindow.dismiss();
                        break;
                    default:
                        break;
                }
            }
        };
        btnCancel.setOnClickListener(listener);
        if (listString.size() > 0) {
            initShareInfo(listView);
        }
    }

    private void initShareInfo(ListView listView) {
        BaseListAdapter baseListAdapter = new BaseListAdapter<String>(XSWQApplication.getInstance(), listString, R.layout.playbypaly_item_layout) {
            @Override
            public void convert(BaseListViewHolder holder, int position, String item) {
                holder.setText(R.id.text, item);
            }
        };
        listView.setAdapter(baseListAdapter);
    }

    private void getData(final String url, int groupId, final int position, String matchId) {
        try {
            PostFormBuilder post = OkHttpUtils.post();
            post.url(url);
            post.addParams("token", token);
            post.addParams("uid", userId);
            post.addParams("groupId", String.valueOf(groupId));
            if (!TextUtils.isEmpty(matchId)) {
                post.addParams("matchId", matchId);
            }
            post.build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    BaseBean baseBean = GsonUtil.gsonToBean(response, BaseBean.class, context);
                    if (baseBean != null) {
                        if (url.equals(ContactUrl.POST_CREATE_ENROLL_PATH)) {
                            ToastUtils.show("报名成功");
                            for (int i = 0; i < groupListAll.size(); i++) {
                                groupListAll.get(i).setIsExist(3);
                            }
                            groupListAll.get(position).setIsExist(1);
                        } else {
                            ToastUtils.show("取消报名成功");
                            for (int i = 0; i < groupListAll.size(); i++) {
                                groupListAll.get(i).setIsExist(2);
                            }
                        }
                        groupList.clear();
                        groupList.addAll(groupListAll);
                        notifyDataSetChanged();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
