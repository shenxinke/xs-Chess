package com.xswq.chess.myapplication.activity.main;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.base.BaseListAdapter;
import com.xswq.chess.myapplication.base.BaseListViewHolder;
import com.xswq.chess.myapplication.bean.AlwaysParkListBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.customview.SetCourseSyllabusPopWindow;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class CourseSyllabusActivity extends BaseCompatActivity implements View.OnClickListener {

    private ListView listView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_course_syllabus_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.prepare_lessons_backgroud);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("课程进度");
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        listView = findViewById(R.id.list_view);
    }


    @Override
    protected void loadData() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_PRIMARY_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    AlwaysParkListBean alwaysParkListBean = GsonUtil.gsonToBean(response, AlwaysParkListBean.class, CourseSyllabusActivity.this);
                    if (alwaysParkListBean != null) {
                        AlwaysParkListBean.DataBean data = alwaysParkListBean.getData();
                        if (data != null) {
                            List<AlwaysParkListBean.DataBean.PrimaryBean> primary = data.getPrimary();
                            if (primary != null && primary.size() > 0) {
                                initAdapter(primary);
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            default:
                break;
        }
    }


    private void initAdapter(List<AlwaysParkListBean.DataBean.PrimaryBean> shareList) {
        BaseListAdapter baseListAdapter = new BaseListAdapter<AlwaysParkListBean.DataBean.PrimaryBean>(XSWQApplication.getInstance(), shareList, R.layout.item_course_syllabus_layout) {
            @Override
            public void convert(BaseListViewHolder holder, final int position, final AlwaysParkListBean.DataBean.PrimaryBean item) {
                final String customerName = item.getCustomerName();
                final String id = item.getID();
                holder.setText(R.id.text_name, Util.signString(customerName));
                holder.setButtonListener(R.id.but_alter, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new SetCourseSyllabusPopWindow().initPopupWindow(customerName, id, CourseSyllabusActivity.this);
                    }
                });
            }
        };
        listView.setAdapter(baseListAdapter);
    }
}
