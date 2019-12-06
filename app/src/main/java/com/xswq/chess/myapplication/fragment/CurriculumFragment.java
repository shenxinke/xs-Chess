package com.xswq.chess.myapplication.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.QuestionLevelAdapter;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.base.BaseFragment;
import com.xswq.chess.myapplication.base.BaseListAdapter;
import com.xswq.chess.myapplication.base.BaseListViewHolder;
import com.xswq.chess.myapplication.bean.MaxSgfStateBean;
import com.xswq.chess.myapplication.bean.QuestionLevelBean;
import com.xswq.chess.myapplication.bean.QuestionLevelStringBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

public class CurriculumFragment extends BaseFragment {

    private View mView;
    private int total = 0;
    private ImageView jiChuShang;
    private ImageView jiChuXia;
    private ImageView tiShengShang;
    private ImageView tiShengXia;

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
        jiChuShang = mView.findViewById(R.id.jichu_shang_no);
        jiChuXia = mView.findViewById(R.id.jichu_xia_no);
        tiShengShang = mView.findViewById(R.id.tisheng_shang_no);
        tiShengXia = mView.findViewById(R.id.tisheng_xia_no);
        getQuestion();
    }

    @Override
    protected void stopLoad() {

    }

    private int getLayout() {
        return R.layout.curriculum_fragment_layout;
    }

    @OnClick({R.id.qimeng_shang, R.id.qimeng_xia, R.id.jichu_shang_no, R.id.jichu_xia_no, R.id.tisheng_shang_no, R.id.tisheng_xia_no})
    public void viewOnClickListener(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.qimeng_shang:
                intent = new Intent(getActivity(), JsWebViewActivity.class);
                intent.putExtra("prefix", ContactUrl.QUESTIONSTORE);
                intent.putExtra("intentType", Const.INTEGER_1);
                intent.putExtra("courseNum", "3-24");
                startActivity(intent);
                break;
            case R.id.qimeng_xia:
                intent = new Intent(getActivity(), JsWebViewActivity.class);
                intent.putExtra("prefix", ContactUrl.QUESTIONSTORE);
                intent.putExtra("intentType", Const.INTEGER_1);
                intent.putExtra("courseNum", "25-48");
                startActivity(intent);
                break;
            case R.id.jichu_shang_no:
                if (total < 49) {
                    ToastUtils.show("暂未解锁");
                } else {
                    intent = new Intent(getActivity(), JsWebViewActivity.class);
                    intent.putExtra("prefix", ContactUrl.QUESTIONSTORE);
                    intent.putExtra("intentType", Const.INTEGER_1);
                    intent.putExtra("courseNum", "49-72");
                    startActivity(intent);
                }
                break;
            case R.id.jichu_xia_no:
                if (total < 73) {
                    ToastUtils.show("暂未解锁");
                } else {
                    intent = new Intent(getActivity(), JsWebViewActivity.class);
                    intent.putExtra("prefix", ContactUrl.QUESTIONSTORE);
                    intent.putExtra("intentType", Const.INTEGER_1);
                    intent.putExtra("courseNum", "73-96");
                    startActivity(intent);
                }
                break;
            case R.id.tisheng_shang_no:
                if (total < 97) {
                    ToastUtils.show("暂未解锁");
                } else {
                    intent = new Intent(getActivity(), JsWebViewActivity.class);
                    intent.putExtra("prefix", ContactUrl.QUESTIONSTORE);
                    intent.putExtra("intentType", Const.INTEGER_1);
                    intent.putExtra("courseNum", "97-120");
                    startActivity(intent);
                }
                break;
            case R.id.tisheng_xia_no:
                if (total < 121) {
                    ToastUtils.show("暂未解锁");
                } else {
                    intent = new Intent(getActivity(), JsWebViewActivity.class);
                    intent.putExtra("prefix", ContactUrl.QUESTIONSTORE);
                    intent.putExtra("intentType", Const.INTEGER_1);
                    intent.putExtra("courseNum", "121-144");
                    startActivity(intent);
                }
                break;
        }
    }

    private void getQuestion() {
        try {
            OkHttpUtils.post().url(ContactUrl.MAX_SGF_STATE_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    MaxSgfStateBean maxSgfStateBean = GsonUtil.gsonToBean(response, MaxSgfStateBean.class, getActivity());
                    if (maxSgfStateBean != null) {
                        total = maxSgfStateBean.getData();
                        if (total < 49) {
                            jiChuShang.setBackgroundResource(R.mipmap.jichu_shang_no);
                            jiChuXia.setBackgroundResource(R.mipmap.jichu_xia_no);
                            tiShengShang.setBackgroundResource(R.mipmap.tisheng_shang_no);
                            tiShengXia.setBackgroundResource(R.mipmap.tisheng_xia_no);
                        } else if (total < 73) {
                            jiChuShang.setBackgroundResource(R.mipmap.jichu_shang);
                            jiChuXia.setBackgroundResource(R.mipmap.jichu_xia_no);
                            tiShengShang.setBackgroundResource(R.mipmap.tisheng_shang_no);
                            tiShengXia.setBackgroundResource(R.mipmap.tisheng_xia_no);
                        } else if (total < 97) {
                            jiChuShang.setBackgroundResource(R.mipmap.jichu_shang);
                            jiChuXia.setBackgroundResource(R.mipmap.jichu_xia);
                            tiShengShang.setBackgroundResource(R.mipmap.tisheng_shang_no);
                            tiShengXia.setBackgroundResource(R.mipmap.tisheng_xia_no);
                        } else if (total < 121) {
                            jiChuShang.setBackgroundResource(R.mipmap.jichu_shang);
                            jiChuXia.setBackgroundResource(R.mipmap.jichu_xia);
                            tiShengShang.setBackgroundResource(R.mipmap.tisheng_shang);
                            tiShengXia.setBackgroundResource(R.mipmap.tisheng_xia_no);
                        } else {
                            jiChuShang.setBackgroundResource(R.mipmap.jichu_shang);
                            jiChuXia.setBackgroundResource(R.mipmap.jichu_xia);
                            tiShengShang.setBackgroundResource(R.mipmap.tisheng_shang);
                            tiShengXia.setBackgroundResource(R.mipmap.tisheng_xia);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
