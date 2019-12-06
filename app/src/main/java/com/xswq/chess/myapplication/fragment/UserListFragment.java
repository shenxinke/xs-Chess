package com.xswq.chess.myapplication.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.UserListAdapter;
import com.xswq.chess.myapplication.base.BaseFragment;
import com.xswq.chess.myapplication.bean.UserListBean;
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

public class UserListFragment extends BaseFragment {
    private View mView;
    private ListView listView;
    private RelativeLayout relativeLayout;
    private int pageNum = 1;
    private List<UserListBean.DataBean.ListBean> userList = new ArrayList<>();
    private UserListAdapter userListAdapter;
    private SmartRefreshLayout smartRefreshLayout;
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
                getUserList();
            }
        });
        getUserList();
        initListener();
    }

    @Override
    protected void stopLoad() {

    }

    @Override
    protected void startLoad() {
    }

    private int getLayout() {
        return R.layout.user_fragment_layout;
    }

    private void initListener() {
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageNum++;
                getUserList();
                smartRefreshLayout.finishLoadmore(1000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNum = 1;
                if (userList != null) {
                    userList.clear();
                }
                getUserList();
                smartRefreshLayout.finishRefresh(500);
            }
        });
    }

    private void getUserList() {
        try {
            PostFormBuilder post = OkHttpUtils.post();
            post.url(ContactUrl.POST_FRIENDS_INFO_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("type", Const.STR3)
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
                    UserListBean userListBean = GsonUtil.gsonToBean(response, UserListBean.class, getActivity());
                    if (userListBean != null) {
                        UserListBean.DataBean data = userListBean.getData();
                        if (data != null) {
                            userList.addAll(data.getList());
                            if (userList != null && userList.size() > 0) {
                                relativeLayout.setVisibility(View.GONE);
                                listView.setVisibility(View.VISIBLE);
                                if (userListAdapter == null) {
                                    userListAdapter = new UserListAdapter(getActivity(), userList, token, userId);
                                    listView.setAdapter(userListAdapter);
                                } else {
                                    userListAdapter.upUserList(userList);
                                }
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
}
