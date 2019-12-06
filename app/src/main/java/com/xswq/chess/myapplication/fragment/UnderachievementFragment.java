package com.xswq.chess.myapplication.fragment;

import android.view.View;
import android.widget.ListView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.achievement.AchievementSystemActivity;
import com.xswq.chess.myapplication.adapter.UnderachievementAdapter;
import com.xswq.chess.myapplication.base.BaseFragment;
import com.xswq.chess.myapplication.bean.ToAchieveBean;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.List;
import okhttp3.Call;

/**
 * 未获取成就
 */
public class UnderachievementFragment extends BaseFragment {
    private ListView listView;
    private View mView;

    @Override
    protected int setContentView() {
        return getLayout();
    }

    @Override
    protected void startLoad() {
        getDate();
    }

    @Override
    protected void initData() {
        if (mView == null) {
            mView = getContentView();
        }
        listView = findViewById(R.id.underachievement_listview);
    }

    @Override
    protected void stopLoad() {

    }

    private int getLayout() {
        return R.layout.fragment_underachievement_layout;
    }

    //获取数据
    private void getDate() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_DID_NOT_ACHIEVEMENT)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                }

                @Override
                public void onResponse(String response, int id) {
                    if (response != null) {
                        Gson gson = new Gson();
                        ToAchieveBean toAchieveBean = gson.fromJson(response, ToAchieveBean.class);
                        ToAchieveBean.ErrorBean error = toAchieveBean.getError();
                        String returnCode = error.getReturnCode();
                        if (returnCode.equals("0")) {
                            List<ToAchieveBean.DataBean> data = toAchieveBean.getData();
                            if (data.size() > 0) {
                                UnderachievementAdapter underachievementAdapter = new UnderachievementAdapter(getActivity(), data, token, userId);
                                listView.setAdapter(underachievementAdapter);
                            }
                        } else if (returnCode.equals("10048")) {
                            ((AchievementSystemActivity) getActivity()).quiteApp(getContext(), error.getReturnUserMessage());
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
