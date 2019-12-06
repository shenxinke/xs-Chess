package com.xswq.chess.myapplication.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.AddFriendsAdapter;
import com.xswq.chess.myapplication.base.BaseFragment;
import com.xswq.chess.myapplication.bean.AddFriendsBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class AddFriendMessagerFragment extends BaseFragment {
    private View mView;
    private RelativeLayout relativeLayout;
    private ListView listView;
    private AddFriendsAdapter addFriendsAdapter;

    @Override
    protected int setContentView() {
        return getLayout();
    }

    @Override
    protected void startLoad() {

    }

    @Override
    protected void initData() {
        if (mView == null) {
            mView = getContentView();
        }
        listView = mView.findViewById(R.id.add_friend_list);
        relativeLayout = mView.findViewById(R.id.add_fragment_rl);
        friendsInviteList();
    }

    @Override
    protected void stopLoad() {

    }

    private int getLayout() {
        return R.layout.add_friend_messager_layout;
    }

    private void friendsInviteList() {
        try {
            PostFormBuilder post = OkHttpUtils.post();
            post.url(ContactUrl.POST_FRIENDS_INVITE_LIST_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("state", Const.STR2)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    AddFriendsBean addFriendsBean = GsonUtil.gsonToBean(response, AddFriendsBean.class, getActivity());
                    if (addFriendsBean != null) {
                        List<AddFriendsBean.DataBean> data = addFriendsBean.getData();
                        if (data != null && !data.isEmpty()) {
                            if (addFriendsAdapter == null) {
                                addFriendsAdapter = new AddFriendsAdapter(getActivity(), data,token,userId);
                                listView.setAdapter(addFriendsAdapter);
                            } else {
                                addFriendsAdapter.upDataList(data);
                            }
                        } else {
                            relativeLayout.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.GONE);
                        }
                    } else {
                        relativeLayout.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
