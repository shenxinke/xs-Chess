package com.xswq.chess.myapplication.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.FriendsListAdapter;
import com.xswq.chess.myapplication.base.BaseFragment;
import com.xswq.chess.myapplication.bean.ListInstitutionsBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class FriendFragment extends BaseFragment {

    private View mView;
    private ListView listView;
    private RelativeLayout relativeLayout;
    private List<ListInstitutionsBean.DataBean.ListBean> userList = new ArrayList<>();
    private int pageNum = 1;
    private SmartRefreshLayout smartRefreshLayout;
    private FriendsListAdapter friendsListAdapter;
    private EditText playingchessUser;
    private String stringName;

    @Override
    protected int setContentView() {
        return getLayout();
    }

    @Override
    protected void initData() {
        if (mView == null) {
            mView = getContentView();
        }
        listView = mView.findViewById(R.id.user_list);
        relativeLayout = mView.findViewById(R.id.url_fragment_rl);
        smartRefreshLayout = mView.findViewById(R.id.smartRefreshLayout);
        playingchessUser = mView.findViewById(R.id.playingchess_user);
        mView.findViewById(R.id.playingchess_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringName = playingchessUser.getText().toString();
                userList.clear();
                getFriendsInfo();
            }
        });
        getFriendsInfo();
        initListener();
    }

    @Override
    protected void stopLoad() {
    }

    @Override
    protected void startLoad() {
    }

    private int getLayout() {
        return R.layout.friend_fragment_layout;
    }

    private void getFriendsInfo() {
        try {
            PostFormBuilder post = OkHttpUtils.post();
            post.url(ContactUrl.POST_FRIENDS_INFO_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("type", Const.STR1)
                    .addParams("pageNum", String.valueOf(pageNum))
                    .addParams("pageSize", Const.STR50);
            if (!TextUtils.isEmpty(stringName)) {
                post.addParams("searchName", stringName);
            }
            post.build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    ListInstitutionsBean listInstitutionsBean = GsonUtil.gsonToBean(response, ListInstitutionsBean.class, getActivity());
                    if (listInstitutionsBean != null) {
                        ListInstitutionsBean.DataBean data = listInstitutionsBean.getData();
                        if (data != null) {
                            userList.addAll(data.getList());
                            if (userList != null && !userList.isEmpty()) {
                                setDataAdapter();
                            } else {
                                relativeLayout.setVisibility(View.VISIBLE);
                                listView.setVisibility(View.GONE);
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

    private void setDataAdapter() {
        relativeLayout.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        if (friendsListAdapter == null) {
            friendsListAdapter = new FriendsListAdapter(getActivity(), userList);
            listView.setAdapter(friendsListAdapter);
        } else {
            friendsListAdapter.upFriendsList(userList);
        }
    }

    private void initListener() {
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageNum++;
                getFriendsInfo();
                smartRefreshLayout.finishLoadmore(1000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNum = 1;
                if (userList != null) {
                    userList.clear();
                }
                getFriendsInfo();
                smartRefreshLayout.finishRefresh(500);
            }
        });
    }
}
