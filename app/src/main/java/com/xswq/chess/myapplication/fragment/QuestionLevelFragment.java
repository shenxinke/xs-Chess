package com.xswq.chess.myapplication.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.QuestionLevelAdapter;
import com.xswq.chess.myapplication.adapter.QuestionLevelPopAdapter;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.base.BaseFragment;
import com.xswq.chess.myapplication.bean.QuestionLevelBean;
import com.xswq.chess.myapplication.bean.QuestionLevelStringBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;

public class QuestionLevelFragment extends BaseFragment {
    private View mView;
    private List<QuestionLevelStringBean> strList = new ArrayList<>();
    private int[] images = new int[]{R.mipmap.bu_ju,R.mipmap.si_huo , R.mipmap.guan_zi, R.mipmap.shou_jing};
    private PopupWindow popupWindow;
    private GridView gridView;
    private List<String> data;

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
        gridView = mView.findViewById(R.id.grid_view);
        getQuestionLevel();
    }

    @Override
    protected void stopLoad() {

    }

    private int getLayout() {
        return R.layout.fragment_question_level_layout;
    }

    private void showPopWindow(final String sLevel) {
        View layout = LayoutInflater.from(getActivity()).inflate(R.layout.question_level_pop_layout, null, false);
        popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setClippingEnabled(false);
        ColorDrawable dw = new ColorDrawable(0xff);
        popupWindow.setBackgroundDrawable(dw);
        View view = getActivity().getWindow().getDecorView();
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        GridView gridView = layout.findViewById(R.id.question_level_id);
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

        List<Integer> imageInteger =new ArrayList<>();
        for (int image : images) {
            imageInteger.add(image);
        }
        QuestionLevelPopAdapter questionLevelPopAdapter = new QuestionLevelPopAdapter(getActivity(), imageInteger);
        gridView.setAdapter(questionLevelPopAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), JsWebViewActivity.class);
                intent.putExtra("prefix", ContactUrl.QUESTIONSTORE);
                intent.putExtra("intentType", Const.INTEGER_2);
                intent.putExtra("level", sLevel);
                switch (position) {
                    case 0:
                        intent.putExtra("questionType", Const.STR2);
                        break;
                    case 1:
                        intent.putExtra("questionType", Const.STR1);
                        break;
                    case 2:
                        intent.putExtra("questionType", Const.STR3);
                        break;
                    case 3:
                        intent.putExtra("questionType", Const.STR5);
                        break;
                    default:
                        break;
                }
                startActivity(intent);
                popupWindow.dismiss();
            }
        });
    }

    private void getQuestionLevel() {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_QUESTION_LEVEL_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    QuestionLevelBean questionLevelBean = GsonUtil.gsonToBean(response, QuestionLevelBean.class, getActivity());
                    if (questionLevelBean != null) {
                        data = questionLevelBean.getData();
                        if (data == null || data.isEmpty()) return;
                        for (int i = 0; i < data.size(); i++) {
                            QuestionLevelStringBean questionLevelStringBean = new QuestionLevelStringBean();
                            questionLevelStringBean.setmLevel(data.get(i));
                            if (i < 6) {
                                questionLevelStringBean.setmImage(R.mipmap.question_level_one);
                            } else if (i < 10) {
                                questionLevelStringBean.setmImage(R.mipmap.question_level_two);
                            } else if (i < 15) {
                                questionLevelStringBean.setmImage(R.mipmap.question_level_three);
                            } else if (i < 20) {
                                questionLevelStringBean.setmImage(R.mipmap.question_level_four);
                            } else if (i < 25) {
                                questionLevelStringBean.setmImage(R.mipmap.question_level_five);
                            } else {
                                questionLevelStringBean.setmImage(R.mipmap.question_level_six);
                            }
                            strList.add(questionLevelStringBean);
                        }

                        QuestionLevelAdapter baseListAdapter = new QuestionLevelAdapter(getActivity(), strList);
                        gridView.setAdapter(baseListAdapter);

                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String strings = data.get(position);
                                showPopWindow(strings);
                            }
                        });
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
