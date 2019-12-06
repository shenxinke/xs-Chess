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
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.bean.TeacherJudgeManagementBean;
import com.xswq.chess.myapplication.utils.ContactUrl;

import java.util.List;

public class TeacherJudgeManagementAdapter extends BaseAdapter {
    private Context context;
    private List<TeacherJudgeManagementBean.DataBean> data;

    public TeacherJudgeManagementAdapter(Context context, List<TeacherJudgeManagementBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ComViewHolder comHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.teacher_judge_management_item, null);
            comHolder = new ComViewHolder();
            comHolder.matchName = convertView.findViewById(R.id.match_name);
            comHolder.groupName = convertView.findViewById(R.id.group_name);
            comHolder.competitionRound = convertView.findViewById(R.id.competition_round);
            comHolder.competitionBlack = convertView.findViewById(R.id.competition_black);
            comHolder.competitionWhite = convertView.findViewById(R.id.competition_white);
            comHolder.resultMatchText = convertView.findViewById(R.id.result_match_text);
            comHolder.buttonJudge = convertView.findViewById(R.id.button_judge);

            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }
        String strRound = "轮次：第" + data.get(position).getRoundsNum() + "轮";
        String strBlackName = "黑方：" + data.get(position).getBlackUserName();
        String strWhiteName = "白方：" + data.get(position).getWhiteUserName();
        String strMatchName = "比赛：" + data.get(position).getMatchName();
        String strGroupName = "组别：" + data.get(position).getGroupName();
        final int judge = data.get(position).getJudge();
        int result = data.get(position).getResult();
        comHolder.competitionRound.setText(strRound);
        comHolder.competitionBlack.setText(strBlackName);
        comHolder.competitionWhite.setText(strWhiteName);
        comHolder.matchName.setText(strMatchName);
        comHolder.groupName.setText(strGroupName);
        if (judge == 1) {
            comHolder.resultMatchText.setText("未判棋");
            comHolder.buttonJudge.setTextColor(context.getResources().getColor(R.color.color_a53f02));
            comHolder.buttonJudge.setBackgroundResource(R.mipmap.integral_shopping_mall_yes);
        } else if (judge == 2) {
            comHolder.buttonJudge.setTextColor(context.getResources().getColor(R.color.white));
            comHolder.buttonJudge.setBackgroundResource(R.mipmap.play_by_play_unapply);
            switch (result) {
                case 1:
                    comHolder.resultMatchText.setText("黑方胜");
                    break;
                case 2:
                    comHolder.resultMatchText.setText("白方胜");
                    break;
                case 3:
                    comHolder.resultMatchText.setText("平局");
                    break;
                case 4:
                    comHolder.resultMatchText.setText("无效对局");
                    break;
                default:
                    break;
            }
        }

        comHolder.buttonJudge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (judge == 1) {
                    String chessId = String.valueOf(data.get(position).getChessId());
                    String arenaId = String.valueOf(data.get(position).getId());
                    String matchId = data.get(position).getMatchId();
                    Intent intent = new Intent(context, JsWebViewActivity.class);
                    intent.putExtra("prefix", ContactUrl.TEACHERJUDGE);
                    intent.putExtra("matchId", matchId);
                    intent.putExtra("arenaId", arenaId);
                    intent.putExtra("chessid", chessId);
                    context.startActivity(intent);
                }
            }
        });

        return convertView;
    }

    class ComViewHolder {
        TextView competitionRound;
        TextView competitionBlack;
        TextView competitionWhite;
        TextView resultMatchText;
        TextView matchName;
        TextView groupName;
        Button buttonJudge;
    }
}
