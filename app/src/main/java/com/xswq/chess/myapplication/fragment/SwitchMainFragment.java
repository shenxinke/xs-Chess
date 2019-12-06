package com.xswq.chess.myapplication.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.achievement.AchievementSystemActivity;
import com.xswq.chess.myapplication.activity.competition.student.StudentCompetitionManagementActivity;
import com.xswq.chess.myapplication.activity.courseware.CoursewareListActivity;
import com.xswq.chess.myapplication.activity.main.SettingsActivity;
import com.xswq.chess.myapplication.activity.navigationentrance.MessagerListActivity;
import com.xswq.chess.myapplication.activity.navigationentrance.PlayingChessActivity;
import com.xswq.chess.myapplication.activity.navigationentrance.friendlist.FriendsListActivity;
import com.xswq.chess.myapplication.activity.onlinegame.AIOnlineGameActivity;
import com.xswq.chess.myapplication.activity.onlinegame.IncompleteChessGameActivity;
import com.xswq.chess.myapplication.activity.onlinegame.ProgressBarActivity;
import com.xswq.chess.myapplication.activity.personalCenter.EditHeaderActivity;
import com.xswq.chess.myapplication.activity.personalCenter.EditUserInforActivity;
import com.xswq.chess.myapplication.activity.personalCenter.HistoryMatchActivity;
import com.xswq.chess.myapplication.activity.questionstore.QuestionStoreActivity;
import com.xswq.chess.myapplication.activity.start.LoginActivity;
import com.xswq.chess.myapplication.activity.weiqistory.WeiqiStoryListActivity;
import com.xswq.chess.myapplication.adapter.LevelUpOrDownAdapter;
import com.xswq.chess.myapplication.adapter.RadarAdapter;
import com.xswq.chess.myapplication.adapter.SwitchMainListAdapter;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.base.BaseFragment;
import com.xswq.chess.myapplication.bean.CurrentTastBean;
import com.xswq.chess.myapplication.bean.GameInfoUseridBean;
import com.xswq.chess.myapplication.bean.IndividualFulfilmentBean;
import com.xswq.chess.myapplication.bean.PersonalUpDataKeyBean;
import com.xswq.chess.myapplication.bean.RandomBattleBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.config.RxCode;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.PersonalHistoryGamesApi;
import com.xswq.chess.myapplication.http.httpparameterapi.PersonalLevelUpDownApi;
import com.xswq.chess.myapplication.http.retrofitHttp.RetrofitManager;
import com.xswq.chess.myapplication.http.subscriber.PersonalHistoryGamesSub;
import com.xswq.chess.myapplication.http.subscriber.PersonalLevelUpDownSub;
import com.xswq.chess.myapplication.utils.AutoCaseTransformationMethod;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.DateUtil;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ImageLoader;
import com.xswq.chess.myapplication.utils.PreferencesUtils;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.StatisticsUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import gorden.rxbus2.RxBus;
import gorden.rxbus2.ThreadMode;
import okhttp3.Call;

public class SwitchMainFragment extends BaseFragment implements HttpCallBackLisener, View.OnClickListener {

    private View mView;
    private String title;
    private ImageView personalChessChampion;
    private ImageView personalCelebrity;
    private ImageView personalStarrySky;
    private ImageView personalTianyuan;
    private GridView mGridView;
    private TextView textNumber;
    private TextView textNumber2;
    private TextView textNumber3;
    private PersonalLevelUpDownApi mPersonalLevelUpDownApi;
    private PersonalLevelUpDownSub mPersonalLevelUpDownSubcriber;
    private PersonalHistoryGamesApi mPersonalHistoryGamesApi;
    private PersonalHistoryGamesSub mPersonalHistoryGamesSubcriber;
    private PopupWindow popupWindow;
    private SwitchMainListAdapter switchMainListAdapter;
    private int[] mainImage;
    private String userName;
    private int level;
    private TextView userLevel;
    private ImageView headImage;
    private TextView userNames;
    private TextView mechanismName;
    private TextView personalUserName;
    private TextView levelNumber;
    private TextView personalDeadline;
    private ImageView personalHead;
    private PopupWindow vipPopupWindow;

    public static SwitchMainFragment getInstances(String title) {
        SwitchMainFragment mSwitchMainFragment = new SwitchMainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        mSwitchMainFragment.setArguments(bundle);
        return mSwitchMainFragment;
    }

    @Override
    protected int setContentView() {
        Bundle bundle = getArguments();
        title = bundle.getString("title");
        return getLayout(Util.signString(title));
    }

    @Override
    protected void startLoad() {

    }

    @Override
    protected void initData() {
        if (mView == null) {
            mView = getContentView();
        }
        RxBus.get().register(this);
        getFragmentAssembly();
    }

    @Override
    protected void stopLoad() {

    }

    private int getLayout(String titles) {
        switch (titles) {
            case Const.STRING_MAIN_PAGE:
                return R.layout.main_page_layout;
            case Const.STRING_MINE_FIND:
                return R.layout.main_find_layout;
            case Const.STRING_MINE_PERSONAL_CENTER:
                return R.layout.main_switch_middle_layout;
            default:
                break;
        }
        return 0;
    }

    private void getFragmentAssembly() {
        final RecyclerView mRecyclerView;
        GridLayoutManager gridLayoutManager;
        switch (title) {
            case Const.STRING_MAIN_PAGE:
            case Const.STRING_MINE_FIND:
                userLevel = findViewById(R.id.user_level);
                headImage = findViewById(R.id.head_image);
                userNames = findViewById(R.id.user_names);
                mechanismName = findViewById(R.id.mechanism_name);
                if (Const.STRING_MAIN_PAGE.equals(title)) {
                    mRecyclerView = findViewById(R.id.recycler_view);
                    mainImage = new int[]{R.mipmap.main_onlin, R.mipmap.main_question_bank,
                            R.mipmap.main_ai_image, R.mipmap.main_analyse, R.mipmap.main_secret_area,
                            R.mipmap.main_story, R.mipmap.main_drill};
                    gridLayoutManager = new GridLayoutManager(getActivity(), 1);
                } else {
                    mRecyclerView = findViewById(R.id.recycler_view);
                    mainImage = new int[]{R.mipmap.main_wrong, R.mipmap.main_job,
                            R.mipmap.main_appraisal, R.mipmap.main_match, R.mipmap.main_friend,
                            R.mipmap.main_play_chess};
                    gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                }
                mRecyclerView.setLayoutManager(gridLayoutManager);
                switchMainListAdapter = new SwitchMainListAdapter(getContext(), mainImage, title);
                mRecyclerView.setAdapter(switchMainListAdapter);
                switchMainListAdapter.setItemClickListener(new SwitchMainListAdapter.OnRecyclerViewClickListener() {
                    @Override
                    public void onItemClickListener(View view) {
                        int childAdapterPosition = mRecyclerView.getChildAdapterPosition(view);
                        if (Util.isClickable()) return;
                        getIntentSwitch(childAdapterPosition, title);
                    }
                });
                break;
            case Const.STRING_MINE_PERSONAL_CENTER:
                findViewById(R.id.main_message).setOnClickListener(this);
                findViewById(R.id.main_set).setOnClickListener(this);
                findViewById(R.id.modified_data).setOnClickListener(this);
                findViewById(R.id.personal_center_pay).setOnClickListener(this);
                findViewById(R.id.personal_see_all).setOnClickListener(this);
                findViewById(R.id.personal_historical_match).setOnClickListener(this);
                findViewById(R.id.personal_update_headview).setOnClickListener(this);
                String[] titles = new String[]{"能力图", "成长日志"};
                personalHead = findViewById(R.id.personal_head);
                personalUserName = findViewById(R.id.personal_username);
                levelNumber = findViewById(R.id.level_number);
                personalDeadline = findViewById(R.id.personal_deadline);
                personalChessChampion = findViewById(R.id.personal_chess_Champion);
                personalCelebrity = findViewById(R.id.personal_celebrity);
                personalStarrySky = findViewById(R.id.personal_starry_sky);
                personalTianyuan = findViewById(R.id.personal_tianyuan);
                mGridView = findViewById(R.id.personal_chess);
                textNumber = findViewById(R.id.text_number);
                textNumber2 = findViewById(R.id.text_number2);
                textNumber3 = findViewById(R.id.text_number3);
                TabLayout mTabLayout = findViewById(R.id.personal_tablayout);
                ViewPager mViewPager = findViewById(R.id.personal_viewpager);
                for (String tab : titles) {
                    mTabLayout.addTab(mTabLayout.newTab().setText(tab));
                }
                RadarAdapter mRadarFragmentAdapter = new RadarAdapter(getActivity().getSupportFragmentManager(), getActivity(), titles, userId);
                mViewPager.setAdapter(mRadarFragmentAdapter);
                mTabLayout.setupWithViewPager(mViewPager);
                getachievementDate();
                initRequestApi();
                getUserInfotmation();
                break;
            default:
                break;
        }
        updateUserInfo();
    }

    private void getachievementDate() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_FOUR_ACHIEVEMENT)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("achievementCount", "4")
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    if (response != null) {
                        Gson gson = new Gson();
                        IndividualFulfilmentBean individualFulfilmentBean = gson.fromJson(response, IndividualFulfilmentBean.class);
                        IndividualFulfilmentBean.ErrorBean error = individualFulfilmentBean.getError();
                        String returnCode = error.getReturnCode();
                        if ("0".equals(returnCode)) {
                            List<IndividualFulfilmentBean.DataBean> data = individualFulfilmentBean.getData();
                            switch (data.size()) {
                                case 1:
                                    personalChessChampion.setVisibility(View.VISIBLE);
                                    setImageView(personalChessChampion, data.get(0).getImgUrl());
                                    break;
                                case 2:
                                    personalChessChampion.setVisibility(View.VISIBLE);
                                    setImageView(personalChessChampion, data.get(0).getImgUrl());
                                    personalCelebrity.setVisibility(View.VISIBLE);
                                    setImageView(personalCelebrity, data.get(1).getImgUrl());
                                    break;
                                case 3:
                                    personalChessChampion.setVisibility(View.VISIBLE);
                                    setImageView(personalChessChampion, data.get(0).getImgUrl());
                                    personalCelebrity.setVisibility(View.VISIBLE);
                                    setImageView(personalCelebrity, data.get(1).getImgUrl());
                                    personalStarrySky.setVisibility(View.VISIBLE);
                                    setImageView(personalStarrySky, data.get(2).getImgUrl());
                                    break;
                                case 4:
                                    personalChessChampion.setVisibility(View.VISIBLE);
                                    setImageView(personalChessChampion, data.get(0).getImgUrl());
                                    personalCelebrity.setVisibility(View.VISIBLE);
                                    setImageView(personalCelebrity, data.get(1).getImgUrl());
                                    personalStarrySky.setVisibility(View.VISIBLE);
                                    setImageView(personalStarrySky, data.get(2).getImgUrl());
                                    personalTianyuan.setVisibility(View.VISIBLE);
                                    setImageView(personalTianyuan, data.get(3).getImgUrl());
                                    break;
                                default:
                                    break;
                            }
                        } else if ("10048".equals(error.getReturnCode())) {
                            quiteApp(getActivity(), error.getReturnMessage());
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setImageView(ImageView imageView, String url) {
        ImageLoader.loadImage(ContactUrl.BASE_PATH + "/gobangteach/gobangPc/" + url, imageView);
    }

    private void initRequestApi() {
        //获取升降局信息
        mPersonalLevelUpDownApi = new PersonalLevelUpDownApi(this);
        mPersonalLevelUpDownApi.setMethod(ContactUrl.LEVELUPORDOWN);
        mPersonalLevelUpDownApi.setUserId(userId);
        mPersonalLevelUpDownApi.setToken(token);
        mPersonalLevelUpDownApi.setUid(userId);
        mPersonalLevelUpDownSubcriber = new PersonalLevelUpDownSub(mPersonalLevelUpDownApi);

        //获取胜负统计信息
        mPersonalHistoryGamesApi = new PersonalHistoryGamesApi(this);
        mPersonalHistoryGamesApi.setMethod(ContactUrl.HISTORYGAME);
        mPersonalHistoryGamesApi.setUserId(userId);
        mPersonalHistoryGamesApi.setToken(token);
        mPersonalHistoryGamesApi.setUid(userId);
        mPersonalHistoryGamesSubcriber = new PersonalHistoryGamesSub(mPersonalHistoryGamesApi);

    }

    private void getUserInfotmation() {
        RetrofitManager.getRetrofitInstance().handleHttp(mPersonalLevelUpDownSubcriber, mPersonalLevelUpDownApi);
        RetrofitManager.getRetrofitInstance().handleHttp(mPersonalHistoryGamesSubcriber, mPersonalHistoryGamesApi);
    }

    private void getIntentSwitch(int position, String title) {
        Intent mIntent;
        switch (position) {
            case 0:
                if (Const.STRING_MAIN_PAGE.equals(title)) {
                    if (level == 0) {
                        mIntent = new Intent(getActivity(), IncompleteChessGameActivity.class);
                        mIntent.putExtra("intenType", 1);
                        startActivity(mIntent);
                    } else {
                        randomBattle();
                    }
                } else {
                    mIntent = new Intent(getActivity(), JsWebViewActivity.class);
                    mIntent.putExtra("prefix", ContactUrl.WRONGQUESTION);
                    startActivity(mIntent);
                }
                break;
            case 1:
                if (Const.STRING_MAIN_PAGE.equals(title)) {
                    startActivity(new Intent(getActivity(), QuestionStoreActivity.class));
                } else {
                    getCurrentTast();
                }
                break;
            case 2:
                if (Const.STRING_MAIN_PAGE.equals(title)) {
                    startActivity(new Intent(getActivity(), AIOnlineGameActivity.class));
                } else {
                    StatisticsUtil.getStatistics(token, userId, 19, getActivity());
                    mIntent = new Intent(getActivity(), JsWebViewActivity.class);
                    mIntent.putExtra("prefix", ContactUrl.TESTTITLE);
                    startActivity(mIntent);
                }
                break;
            case 3:
                if (Const.STRING_MAIN_PAGE.equals(title)) {
                    mIntent = new Intent(getActivity(), JsWebViewActivity.class);
                    mIntent.putExtra("prefix", ContactUrl.ANALYSE);
                    startActivity(mIntent);
                } else {
                    startActivity(new Intent(getActivity(), StudentCompetitionManagementActivity.class));
                }
                break;
            case 4:
                if (Const.STRING_MAIN_PAGE.equals(title)) {
                    try {
                        mIntent = new Intent(getActivity(), JsWebViewActivity.class);
                        String name = java.net.URLEncoder.encode(userName, "utf-8");
                        String name1 = java.net.URLEncoder.encode(name, "utf-8");
                        mIntent.putExtra("username", name1);
                        mIntent.putExtra("prefix", ContactUrl.GAME);
                        startActivity(mIntent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    startActivity(new Intent(getActivity(), FriendsListActivity.class));
                }
                break;
            case 5:
                if (Const.STRING_MAIN_PAGE.equals(title)) {
                    startActivity(new Intent(getActivity(), WeiqiStoryListActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), PlayingChessActivity.class));
                }
                break;
            case 6:
                try {
                    StatisticsUtil.getStatistics(token, userId, 18, getActivity());
                    mIntent = new Intent(getActivity(), JsWebViewActivity.class);
                    String name = java.net.URLEncoder.encode(userName, "utf-8");
                    String name1 = java.net.URLEncoder.encode(name, "utf-8");
                    mIntent.putExtra("username", name1);
                    mIntent.putExtra("prefix", ContactUrl.SPECIALIZED_TRAINING);
                    startActivity(mIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onNext(Object o, String method) {
        if (method.equals(ContactUrl.HISTORYGAME)) {
            try {
                try {
                    String mVictoryNum = ((JSONObject) o).getString("VictoryNum");
                    String mLoseNum = ((JSONObject) o).getString("LoseNum");
                    String mRate = ((JSONObject) o).getString("rate");
                    double tem = Double.parseDouble(mRate) * 100;
                    @SuppressLint("DefaultLocale") String format = String.format("%.2f", tem) + "%";
                    textNumber.setText(Util.signString(mVictoryNum));
                    textNumber2.setText(Util.signString(mLoseNum));
                    textNumber3.setText(Util.signString(format));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (method.equals(ContactUrl.LEVELUPORDOWN)) {
            try {
                JsonParser parser = new JsonParser();
                JsonArray Jarray = parser.parse(((JSONObject) o).getString("resultList")).getAsJsonArray();

                List<Integer> mListJson = new ArrayList<>();

                for (JsonElement obj : Jarray) {
                    if (obj != null) {
                        if (obj.toString().trim().equals("1")) {
                            mListJson.add(R.mipmap.personal_win);
                        } else if (obj.toString().trim().equals("2")) {
                            mListJson.add(R.mipmap.personal_fail);
                        } else {
                            mListJson.add(R.mipmap.personal_flat);
                        }
                    }
                }
                mGridView.setAdapter(new LevelUpOrDownAdapter(mListJson, getActivity()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.show(Const.CONS_STR_ERROR);
    }

    private void randomBattle() {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_RANDOM_BATTLE_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response)) {
                        Gson gson = new Gson();
                        RandomBattleBean randomBattleBean = gson.fromJson(response, RandomBattleBean.class);
                        if (randomBattleBean == null) return;
                        RandomBattleBean.ErrorBean error = randomBattleBean.getError();
                        if (error == null) return;
                        String returnCode = error.getReturnCode();
                        switch (Util.signString(returnCode)) {
                            case "0": {
                                Intent mIntent = new Intent(getActivity(), ProgressBarActivity.class);
                                startActivity(mIntent);
                                break;
                            }
                            case "2": {
                                Intent mIntent = new Intent(getActivity(), IncompleteChessGameActivity.class);
                                mIntent.putExtra("intenType", 2);
                                startActivity(mIntent);
                                break;
                            }
                            case "10048":
                                quiteApp(getActivity(), randomBattleBean.getError().getReturnMessage());
                                break;
                            default:
                                break;
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_message:
                startActivity(new Intent(getActivity(), MessagerListActivity.class));
                break;
            case R.id.main_set:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            case R.id.modified_data:
                startActivity(new Intent(getActivity(), EditUserInforActivity.class));
                break;
            case R.id.personal_center_pay:
                showPopupWindow(null);
                break;
            case R.id.personal_see_all:
                AchievementSystemActivity.openActivity(getActivity());
                break;
            case R.id.personal_historical_match:
                startActivity(new Intent(getActivity(), HistoryMatchActivity.class));
                break;
            case R.id.personal_update_headview:
                startActivity(new Intent(getActivity(), EditHeaderActivity.class));
                break;
        }
    }

    //显示续费提示框
    private void showPopupWindow(final String returnMessage) {
        final View layout = LayoutInflater.from(getActivity()).inflate(R.layout.personal_center_pop_layout, null, false);
        popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        popupWindow.setClippingEnabled(false);
        ColorDrawable dw = new ColorDrawable(0xff);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        ImageView imgCancel = layout.findViewById(R.id.shutdown);
        Button button = layout.findViewById(R.id.button);
        TextView text = layout.findViewById(R.id.text);
        final EditText editText = layout.findViewById(R.id.edit_text);
        if ("兑换成功!".equals(returnMessage)) {
            text.setText(Util.signString(returnMessage));
            editText.setVisibility(View.GONE);
            button.setText("确定");
        } else {
            editText.setTransformationMethod(new AutoCaseTransformationMethod());
            editText.setVisibility(View.VISIBLE);
            button.setText("立即激活");
        }
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.shutdown:
                        popupWindow.dismiss();
                        break;
                    case R.id.button:
                        String strCdk = editText.getText().toString().trim();
                        if ("兑换成功!".equals(returnMessage)) {
                            popupWindow.dismiss();
                            if (vipPopupWindow != null && vipPopupWindow.isShowing()) {
                                vipPopupWindow.dismiss();
                                vipPopupWindow = null;
                            }
                            RxBus.get().send(RxCode.CODE_MAIN_EXPIRE_MESSAGE_MESSAGE);
                        } else {
                            if (!TextUtils.isEmpty(strCdk)) {
                                updateKEY(strCdk);
                            } else {
                                ToastUtils.show("请输入激活码");
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        //设置popwindow布局控件的点击事件
        imgCancel.setOnClickListener(listener);
        button.setOnClickListener(listener);
    }

    //显示到期提示框
    private void showVIPWindow() {
        final View layout = LayoutInflater.from(getActivity()).inflate(R.layout.switch_main_popwindow_layout, null, false);
        vipPopupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        vipPopupWindow.setClippingEnabled(false);
        ColorDrawable dw = new ColorDrawable(0xff);
        vipPopupWindow.setBackgroundDrawable(dw);
        vipPopupWindow.setOutsideTouchable(false);
        vipPopupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        Button button = layout.findViewById(R.id.button);
        Button button2 = layout.findViewById(R.id.button2);
        TextView text = layout.findViewById(R.id.text);
        text.setText(Util.signString("VIP已到期，请立即续费"));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button:
                        showPopupWindow("VIP");
                        break;
                    case R.id.button2:
                        vipPopupWindow.dismiss();
                        PreferencesUtils.putString(XSWQApplication.mContext, "uid", "");
                        Intent intent = new Intent(getActivity(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        getActivity().finish();
                        break;
                    default:
                        break;
                }
            }
        };
        button.setOnClickListener(listener);
        button2.setOnClickListener(listener);

        layout.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return vipPopupWindow.isShowing();
                }
                return false;
            }
        });
    }

    //CDK兑换
    private void updateKEY(String strCdk) {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_UPDATE_KEY_PATH)
                    .addParams("token", token)
                    .addParams("userId", userId)
                    .addParams("key", strCdk)
                    .addParams("keyType", Const.STR3)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    PersonalUpDataKeyBean personalUpDataKeyBean = GsonUtil.gsonToBean(response, PersonalUpDataKeyBean.class, getActivity());
                    if (personalUpDataKeyBean != null) {
                        popupWindow.dismiss();
                        showPopupWindow("兑换成功!");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (title.equals(Const.STRING_MAIN_PAGE)) {
            getGameInfo();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
        if (vipPopupWindow != null && vipPopupWindow.isShowing()) {
            vipPopupWindow.dismiss();
        }
        RxBus.get().unRegister(this);
    }

    //获取对弈情况
    private void getGameInfo() {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_GAME_INFO_USERID_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                }

                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response)) {
                        Gson gson = new Gson();
                        GameInfoUseridBean gameInfoUseridBean = gson.fromJson(response, GameInfoUseridBean.class);
                        if (gameInfoUseridBean != null) {
                            GameInfoUseridBean.ErrorBean error = gameInfoUseridBean.getError();
                            int returnCode = error.getReturnCode();
                            if (returnCode == 10048) {
                                quiteApp((Activity) getContext(), error.getReturnMessage());
                            } else {
                                List<GameInfoUseridBean.DataBean> data = gameInfoUseridBean.getData();
                                if (Const.STRING_MAIN_PAGE.equals(title)) {
                                    if (data != null && !data.isEmpty()) {
                                        mainImage[0] = R.mipmap.main_onlin_bad_image;
                                    } else {
                                        mainImage[0] = R.mipmap.main_onlin;
                                    }
                                    switchMainListAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @gorden.rxbus2.Subscribe(code = RxCode.CODE_MAIN_MESSAGE_MESSAGE, threadMode = ThreadMode.MAIN)
    public void upData() {
        updateUserInfo();
        if (title.equals(Const.STRING_MAIN_PAGE)) {
            long experienceTime = SPUtil.getLong("experienceTime", 0);
            long timeStamp = System.currentTimeMillis();
            if (experienceTime < timeStamp) {
                if (vipPopupWindow == null) {
                    showVIPWindow();
                }
            }
        }
    }

    private void updateUserInfo() {
        userName = SPUtil.getString("userName", "");
        level = SPUtil.getInt("level", 0);
        String chessLevel = Util.getChessLevel(level);
        String strLevel;
        if (chessLevel.equals("0k")) {
            strLevel = "棋力：暂未定级";
        } else {
            strLevel = "棋力：" + chessLevel;
        }
        String userHeading = SPUtil.getString("userHeading", "");
        String mainHeading = ContactUrl.BASE_PATH + "/" + userHeading;
        switch (title) {
            case Const.STRING_MAIN_PAGE:
            case Const.STRING_MINE_FIND:
                String orgName = SPUtil.getString("orgName", "");
                userNames.setText(Util.signString(userName));
                userLevel.setText(strLevel);
                mechanismName.setText(Util.signString(orgName));
                ImageLoader.loadHeadImage(mainHeading, headImage);
                break;
            case Const.STRING_MINE_PERSONAL_CENTER:
                personalUserName.setText(Util.signString(userName));
                levelNumber.setText(strLevel);
                ImageLoader.loadHeadImage(mainHeading, personalHead);
                long experienceTime = SPUtil.getLong("experienceTime", 0);
                String dateToString = "VIP到期时间:  " + DateUtil.getDateToString(experienceTime, "yyyy-MM-dd");
                personalDeadline.setText(dateToString);
                break;
            default:
                break;
        }

    }

    private void getCurrentTast() {
        try {
            OkHttpUtils.post().url(ContactUrl.CURRENT_TAST_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("pageSize", Const.STR10)
                    .addParams("pageNum", Const.STR1)
                    .addParams("history", Const.STR1)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    CurrentTastBean currentTastBean = GsonUtil.gsonToBean(response, CurrentTastBean.class, getActivity());
                    if (currentTastBean != null) {
                        CurrentTastBean.DataBean data = currentTastBean.getData();
                        if (data == null) {
                            Intent mIntent = new Intent(getActivity(), JsWebViewActivity.class);
                            mIntent.putExtra("prefix", ContactUrl.MYWORD);
                            startActivity(mIntent);
                        } else {
                            List<CurrentTastBean.DataBean.ListBean> list = data.getList();
                            int total = data.getTotal();
                            if (list != null && !list.isEmpty()) {
                                String rightWrong = list.get(0).getRightWrong();
                                if (!Const.STR0.equals(rightWrong)) {
                                    Intent mIntent = new Intent(getActivity(), JsWebViewActivity.class);
                                    mIntent.putExtra("jobId", list.get(0).getJobId());
                                    mIntent.putExtra("prefix", ContactUrl.MYWORDHISTORY);
                                    startActivity(mIntent);
                                } else {
                                    Intent mIntent = new Intent(getActivity(), JsWebViewActivity.class);
                                    mIntent.putExtra("prefix", ContactUrl.MYWORD);
                                    mIntent.putExtra("total", total);
                                    startActivity(mIntent);
                                }
                            } else {
                                Intent mIntent = new Intent(getActivity(), JsWebViewActivity.class);
                                mIntent.putExtra("prefix", ContactUrl.MYWORD);
                                mIntent.putExtra("total", total);
                                startActivity(mIntent);
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
