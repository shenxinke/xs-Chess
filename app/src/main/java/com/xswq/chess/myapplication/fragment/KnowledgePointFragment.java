package com.xswq.chess.myapplication.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.KnowledgeLevelAdapter;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.base.BaseFragment;
import com.xswq.chess.myapplication.base.BaseListAdapter;
import com.xswq.chess.myapplication.base.BaseListViewHolder;
import com.xswq.chess.myapplication.bean.KnowLedgeBean;
import com.xswq.chess.myapplication.bean.LevelTypeKnowledgeBean;
import com.xswq.chess.myapplication.bean.ListKnowLedgeBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.customview.HorizontalListView;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.xswq.chess.myapplication.utils.WindowUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class KnowledgePointFragment extends BaseFragment implements View.OnClickListener {
    private View mView;
    private PopupWindow popupWindow;
    private List<KnowLedgeBean.GroupListBean> knowledgeData = new ArrayList<>();
    private BaseListAdapter<KnowLedgeBean.GroupListBean> horizontalbaseListAdapter;
    private TextView noData;
    private GridView gridView;

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
        mView.findViewById(R.id.image_bu_ju).setOnClickListener(this);
        mView.findViewById(R.id.image_shou_jing).setOnClickListener(this);
        mView.findViewById(R.id.image_si_huo).setOnClickListener(this);
        mView.findViewById(R.id.image_guan_zi).setOnClickListener(this);
    }

    @Override
    protected void stopLoad() {

    }

    private int getLayout() {
        return R.layout.fragment_knowledge_point_layout;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_bu_ju:
                getListKonwLedege(Const.STR2);
                break;
            case R.id.image_shou_jing:
                getListKonwLedege(Const.STR5);
                break;
            case R.id.image_si_huo:
                getListKonwLedege(Const.STR1);
                break;
            case R.id.image_guan_zi:
                getListKonwLedege(Const.STR3);
                break;
            default:
                break;
        }
    }

    private void getListKonwLedege(final String questionType) {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_LIST_KNOWLEDGE_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("questionType", questionType)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    ListKnowLedgeBean knowLedgeBean = GsonUtil.gsonToBean(response, ListKnowLedgeBean.class, getActivity());
                    if (knowLedgeBean != null) {
                        List<String> data = knowLedgeBean.getData();
                        knowledgeData.clear();
                        KnowLedgeBean.GroupListBean groupListOnew = new KnowLedgeBean.GroupListBean();
                        groupListOnew.setName("全部");
                        groupListOnew.setSelect(false);
                        knowledgeData.add(groupListOnew);
                        for (int i = 0; i < data.size(); i++) {
                            KnowLedgeBean.GroupListBean groupListBean = new KnowLedgeBean.GroupListBean();
                            groupListBean.setName(data.get(i));
                            groupListBean.setSelect(false);
                            knowledgeData.add(groupListBean);
                        }
                        if (knowledgeData != null && !knowledgeData.isEmpty()) {
                            knowledgeData.get(0).setSelect(true);
                            showPopWindow(questionType);
                        }
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showPopWindow(final String questionType) {
        View layout = LayoutInflater.from(getActivity()).inflate(R.layout.knowledge_level_pop_layout, null, false);
        popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setClippingEnabled(false);
        ColorDrawable dw = new ColorDrawable(0xff);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        gridView = layout.findViewById(R.id.question_level_id);
        noData = layout.findViewById(R.id.no_data);
        HorizontalListView horizontalListView = layout.findViewById(R.id.horizontalListView);
        ImageView shutDown = layout.findViewById(R.id.shutdown_id);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.shutdown_id:
                        popupWindow.dismiss();
                        break;
                    default:
                        break;
                }
            }
        };
        shutDown.setOnClickListener(listener);
        horizontalbaseListAdapter = new BaseListAdapter<KnowLedgeBean.GroupListBean>(getActivity(), knowledgeData, R.layout.student_particulars_horizontal_item_layout) {
            @Override
            public void convert(BaseListViewHolder holder, int position, KnowLedgeBean.GroupListBean item) {
                holder.setText(R.id.button_item, Util.signString(item.getName()));
                if (item.isSelect()) {
                    holder.setTextBgById(R.id.button_item, R.drawable.shape_particulars_btn);
                    holder.setTextPadingLRById(R.id.button_item, WindowUtils.sp2px(getActivity(), 10));
                    holder.setTextColor(R.id.button_item, R.color.color_774801);
                } else {
                    holder.setTextBgById(R.id.button_item, R.drawable.shape_particulars_un_btn);
                    holder.setTextPadingLRById(R.id.button_item, WindowUtils.sp2px(getActivity(), 10));
                    holder.setTextColor(R.id.button_item, R.color.color_0058b0);
                }
            }
        };
        horizontalListView.setAdapter(horizontalbaseListAdapter);
        horizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView itemButton = view.findViewById(R.id.button_item);
                itemButton.setBackgroundResource(R.drawable.shape_particulars_btn);
                int leftRightPading = WindowUtils.sp2px(getActivity(), 10);
                itemButton.setPadding(leftRightPading, 0, leftRightPading, 0);
                itemButton.setTextColor(getResources().getColor(R.color.color_774801));
                if (!knowledgeData.isEmpty()) {
                    for (int i = 0; i < knowledgeData.size(); i++) {
                        knowledgeData.get(i).setSelect(false);
                    }
                    knowledgeData.get(position).setSelect(true);
                    horizontalbaseListAdapter.notifyDataSetChanged();
                    String questionName = knowledgeData.get(position).getName();
                    if (!TextUtils.isEmpty(questionName)) {
                        getLeveltypeKonwLedege(questionType, questionName, gridView);
                    }
                }
            }
        });

        String questionName = knowledgeData.get(0).getName();
        if (!TextUtils.isEmpty(questionName)) {
            getLeveltypeKonwLedege(questionType, questionName, gridView);
        }
    }

    private void getLeveltypeKonwLedege(final String questionType, final String questionName, final GridView gridView) {
        try {
            PostFormBuilder post = OkHttpUtils.post();
            post.url(ContactUrl.POST_LEVEL_TYPE_KNOWLEDGE_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("questionType", questionType);
            if (!"全部".equals(questionName)) {
                post.addParams("questionName", questionName);
            }
            post.build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    LevelTypeKnowledgeBean levelTypeKnowledgeBean = GsonUtil.gsonToBean(response, LevelTypeKnowledgeBean.class, getActivity());
                    if (levelTypeKnowledgeBean != null) {
                        final List<String> data = levelTypeKnowledgeBean.getData();
                        if (data != null && !data.isEmpty()) {
                            noData.setVisibility(View.GONE);
                            gridView.setVisibility(View.VISIBLE);
                            KnowledgeLevelAdapter baseListAdapter = new KnowledgeLevelAdapter(getActivity(), data);
                            gridView.setAdapter(baseListAdapter);
                            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String sLevel = data.get(position);
                                    Intent intent = new Intent(getActivity(), JsWebViewActivity.class);
                                    intent.putExtra("prefix", ContactUrl.QUESTIONSTORE);
                                    intent.putExtra("intentType", Const.INTEGER_3);
                                    intent.putExtra("level", sLevel);
                                    intent.putExtra("questionName", questionName);
                                    intent.putExtra("questionType", questionType);
                                    startActivity(intent);
                                    if (popupWindow != null) {
                                        popupWindow.dismiss();
                                    }
                                }
                            });
                        } else {
                            noData.setVisibility(View.VISIBLE);
                            gridView.setVisibility(View.GONE);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
