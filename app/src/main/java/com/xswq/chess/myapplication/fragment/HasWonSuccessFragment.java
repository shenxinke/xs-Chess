package com.xswq.chess.myapplication.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.achievement.AchievementSystemActivity;
import com.xswq.chess.myapplication.adapter.HasWonSuccrssSystemAdapter;
import com.xswq.chess.myapplication.base.BaseFragment;
import com.xswq.chess.myapplication.bean.ToAchieveBean;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.List;
import okhttp3.Call;

/**
 * 已获取成就
 */
public class HasWonSuccessFragment extends BaseFragment {
    private ListView listView;
    private View mView;
    private TextView textView;

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
        listView = findViewById(R.id.haswonsuccess_listview);
        textView = findViewById(R.id.not_data);
    }

    @Override
    protected void stopLoad() {

    }

    private int getLayout() {
        return R.layout.fragment_has_won_success_layout;
    }

    //获取数据
    private void getDate() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_ALREADY_ACHIEVEMENT)
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
                            if(data.size()>0){
                                textView.setVisibility(View.GONE);
                                HasWonSuccrssSystemAdapter hasWonSuccrssAdapter = new HasWonSuccrssSystemAdapter(getActivity(),data,token,userId);
                                listView.setAdapter(hasWonSuccrssAdapter);
                            }else {
                                textView.setVisibility(View.VISIBLE);
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
