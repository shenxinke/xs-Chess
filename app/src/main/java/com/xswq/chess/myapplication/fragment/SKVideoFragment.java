package com.xswq.chess.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.courseware.VideoPlaybackActivity;
import com.xswq.chess.myapplication.adapter.GuideVideoAdapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.base.BaseFragment;
import com.xswq.chess.myapplication.bean.GuideVideo;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.GuideVideoApi;
import com.xswq.chess.myapplication.http.retrofitHttp.RetrofitManager;
import com.xswq.chess.myapplication.http.subscriber.GuideVideoSub;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

public class SKVideoFragment extends BaseFragment implements HttpCallBackLisener {
    private View view;//视图
    private String userId;
    private ListView mListView;
    private GuideVideoApi mGuideVideoApi;
    private GuideVideoSub mGuideVideoSub;
    private String token;
    private RelativeLayout relativeLayout;

    public static SKVideoFragment getInstances(String userId, String token) {

        SKVideoFragment mGuideVideoFragment = new SKVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId", userId);
        bundle.putString("token", token);
        mGuideVideoFragment.setArguments(bundle);
        return mGuideVideoFragment;
    }

    @Override
    protected int setContentView() {
        Bundle bundle = getArguments();
        userId = bundle.getString("userId");
        token = bundle.getString("token");
        return getLayout();
    }

    @Override
    protected void initData() {
        if (view == null) {
            view = getContentView();
        }
        mListView = findViewById(R.id.guide_video_easyview);
        relativeLayout = findViewById(R.id.fragment_rl);

    }

    @Override
    protected void startLoad() {
        getRequstParamer();
    }

    @Override
    protected void stopLoad() {

    }

    private int getLayout() {

        return R.layout.guide_video_fragment_layout;
    }

    private void getRequstParamer() {
        mGuideVideoApi = new GuideVideoApi(this);
        mGuideVideoApi.setMethod(ContactUrl.JINIORVIDEO);
        mGuideVideoApi.setUid(userId);
        mGuideVideoApi.setToken(token);
        mGuideVideoApi.setJuniorVideoname("SK");
        mGuideVideoSub = new GuideVideoSub(mGuideVideoApi);
        RetrofitManager.getRetrofitInstance().handleHttp(mGuideVideoSub, mGuideVideoApi);
    }

    @Override
    public void onNext(Object t, String method) {
        try {
            JSONObject error = ((JSONObject) t).getJSONObject("error");
            int returnCode = error.optInt("returnCode");
            if (returnCode == 0) {
                String fromGson = String.valueOf(t);
                Gson gson = new Gson();
                GuideVideo guideVideo = gson.fromJson(fromGson, GuideVideo.class);
                int total = guideVideo.getData().getTotal();
                if (total > 0) {
                    final List<GuideVideo.DataBean.ListBean> list = guideVideo.getData().getList();
                    getFragmentAssembly(list);
                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent popIntert = new Intent(getActivity(), VideoPlaybackActivity.class);
                            popIntert.putExtra("videoUrl", list.get(position).getVideoUrl());
                            popIntert.putExtra("ID", list.get(position).getID());
                            popIntert.putExtra("touken", token);
                            popIntert.putExtra("userId", userId);
                            startActivity(popIntert);
                        }
                    });
                } else {
                    relativeLayout.setVisibility(View.VISIBLE);
                }
            } else if (error.getInt("returnCode") == 10048) {
                ((BaseCompatActivity) getActivity()).quiteApp(getContext(), error.optString("returnMessage"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    private void getFragmentAssembly(List<GuideVideo.DataBean.ListBean> dataBeans) {
        GuideVideoAdapter mGuideVideoAdapter = new GuideVideoAdapter(getActivity(), dataBeans);
        mListView.setAdapter(mGuideVideoAdapter);
    }
}
