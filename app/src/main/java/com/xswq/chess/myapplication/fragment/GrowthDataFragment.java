package com.xswq.chess.myapplication.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseFragment;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.PersonalGrowthDataApi;
import com.xswq.chess.myapplication.http.retrofitHttp.RetrofitManager;
import com.xswq.chess.myapplication.http.subscriber.PersonalGrowthDataSub;
import com.xswq.chess.myapplication.utils.ContactUrl;
import org.json.JSONObject;

public class GrowthDataFragment extends BaseFragment implements HttpCallBackLisener {

    private View view;
    private TextView personal＿nsist_days;
    private TextView personal_accuracyrate;
    private TextView personal_total_topic;
    private TextView personal_playing_chess;
    private TextView personal_friendes_chess;
    private TextView personal_level_chess;
    private TextView personal_transcend;
    private TextView personal_wrong_question;
    private TextView personal_Turnout;
    private String userId;

    @Override
    protected int setContentView() {
        return R.layout.personal_growth_data_layout;
    }

    public static GrowthDataFragment newInstance(String str, String userId) {

        GrowthDataFragment fragment = new GrowthDataFragment();
        Bundle bundle = new Bundle();
        String KEY = "title";
        bundle.putString(KEY, str);
        bundle.putString("userId", userId);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected void initData() {
        userId = getArguments().getString("userId");
    }


    @Override
    protected void startLoad() {
        if (view == null) {
            view = getContentView();
        }
        personal＿nsist_days = view.findViewById(R.id.personal_nsist_days);
        personal_accuracyrate = view.findViewById(R.id.personal_accuracyrate);
        personal_total_topic = view.findViewById(R.id.personal_total_topic);
        personal_playing_chess = view.findViewById(R.id.personal_playing_chess);
        personal_friendes_chess = view.findViewById(R.id.personal_friendes_chess);
        personal_level_chess = view.findViewById(R.id.personal_level_chess);
        personal_transcend = view.findViewById(R.id.personal_transcend);
        personal_wrong_question = view.findViewById(R.id.personal_wrong_question);
        personal_Turnout = view.findViewById(R.id.personal_turnout);

        PersonalGrowthDataApi mPersonalGrowthDataApi = new PersonalGrowthDataApi(this);
        mPersonalGrowthDataApi.setMethod(ContactUrl.GROWTHDATA);
        mPersonalGrowthDataApi.setUserId(userId);
        mPersonalGrowthDataApi.setToken(token);
        mPersonalGrowthDataApi.setUid(userId);
        PersonalGrowthDataSub mPersonalGrowthDataSub = new PersonalGrowthDataSub(mPersonalGrowthDataApi);
        RetrofitManager.getRetrofitInstance().handleHttp(mPersonalGrowthDataSub, mPersonalGrowthDataApi);
    }


    @Override
    protected void stopLoad() {

    }

    @Override
    public void onNext(Object data, String method) {
        try {
            String data1 = ((JSONObject) data).getString("data");
            if (!TextUtils.isEmpty(data1)) {
                JSONObject o = ((JSONObject) data).getJSONObject("data");
                int signin = (o).optInt("signin");
                String exercisecorrectrate = (o).optString("exercisecorrectrate");
                int exercisenum = (o).optInt("exercisenum");
                int chessnum = (o).optInt("chessnum");
                int friendchessnum = (o).optInt("friendchessnum");
                String level = (o).optString("level");
                String rank = (o).optString("rank");
                int errorcorrectnum = (o).optInt("errorcorrectnum");
                int turnovernum = (o).optInt("turnovernum");
                if ("0".equals(level)) {
                    level = "暂未定级";
                } else if (!"".equals(level)) {
                    if (Integer.parseInt(level) <= 25) {
                        level = 26 - Integer.parseInt(level) + "K";
                    } else {
                        level = Integer.parseInt(level) - 25 + "D";
                    }
                }

                String spannableStringDays = "坚持签到: " + signin + " 天";
                personal＿nsist_days.setText(spannableStringDays);
                String spannableAccuracyrate = "做题准确率: " + exercisecorrectrate + "%";
                personal_accuracyrate.setText(spannableAccuracyrate);
                String spannableStringTopic = "共完成习题: " + exercisenum + " 道";
                personal_total_topic.setText(spannableStringTopic);
                String spannableStringChess = "对弈局数: " + chessnum + " 局";
                personal_playing_chess.setText(spannableStringChess);
                String spannableFriendesChess = "友谊赛对弈局数: " + friendchessnum + " 局";
                personal_friendes_chess.setText(spannableFriendesChess);
                String spannableLevelChess = "棋力水平: " + level;
                personal_level_chess.setText(spannableLevelChess);
                String spannableTranscend = "排名: " + rank ;
                personal_transcend.setText(spannableTranscend);
                String spannableWringQuestion = "共改正错题: " + errorcorrectnum + " 道";
                personal_wrong_question.setText(spannableWringQuestion);
                String spannableTurnout = "翻盘局数: " + turnovernum + " 局";
                personal_Turnout.setText(spannableTurnout);
            }
        } catch (Exception e) {
            e.printStackTrace();
            personal＿nsist_days.setText("坚持签到：0 天");
            personal_accuracyrate.setText("做题准确率： 0%");
            personal_total_topic.setText("共完成习题：0 道");
            personal_playing_chess.setText("对弈局数：0 局");
            personal_friendes_chess.setText("友谊赛对弈局数：0 局");
            personal_level_chess.setText("棋力水平：0");
            personal_transcend.setText("排名: 0");
            personal_wrong_question.setText("共改正错题：0 道");
            personal_Turnout.setText("翻盘局数：0 局");
        }
    }

    @Override
    public void onError(Throwable e) {

    }
}
