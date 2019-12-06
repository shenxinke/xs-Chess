package com.xswq.chess.myapplication.customview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.main.SetCourseHintActivity;
import com.xswq.chess.myapplication.adapter.SetCourseSyllabusGridAdapter;
import com.xswq.chess.myapplication.base.BaseBean;
import com.xswq.chess.myapplication.bean.SetCourseIsSlectBean;
import com.xswq.chess.myapplication.bean.SetCourseSyllabusBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class SetCourseSyllabusPopWindow implements View.OnClickListener {

    private Activity activity;
    private PopupWindow popupWindow;
    private String userId;
    private String token;
    private String id;
    private RecyclerView recyclerView;
    private List<SetCourseIsSlectBean> allCourseList = new ArrayList<>();
    private SetCourseSyllabusGridAdapter setCourseSyllabusGridAdapter;
    private boolean isAllSlect = true;
    private ImageView radioAllText;
    private String indextStr = "";
    private int tagAll = 0;

    //设置侧边窗口动画
    public void initPopupWindow(String name, String id, Activity activity) {
        try {
            this.activity = activity;
            this.id = id;
            userId = SPUtil.getString("uid", "");
            token = SPUtil.getString("token", "");

            View popupWindowView = activity.getLayoutInflater().inflate(R.layout.popup_course_syllabus_layout, null);
            popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
            //显示位置
            popupWindow.showAtLocation(activity.getLayoutInflater().inflate(R.layout.activity_course_syllabus_layout, null), Gravity.CENTER, 0, 0);
            Button buttonOk = popupWindowView.findViewById(R.id.button_ok);
            buttonOk.setOnClickListener(this);
            Button buttonCleack = popupWindowView.findViewById(R.id.button_cleack);
            buttonCleack.setOnClickListener(this);
            recyclerView = popupWindowView.findViewById(R.id.recycler_view);
            TextView className = popupWindowView.findViewById(R.id.class_name);
            className.setText(Util.signString(name));

            radioAllText = popupWindowView.findViewById(R.id.radio_button_unchecked);
            radioAllText.setOnClickListener(this);
            TextView radioAll = popupWindowView.findViewById(R.id.text_all);
            radioAll.setOnClickListener(this);


            //布局管理器对象 参数1.上下文 2.规定一行显示几列的参数常量
            GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 3);
            //设置RecycleView显示的方向是水平还是垂直 GridLayout.HORIZONTAL水平  GridLayout.VERTICAL默认垂直
            gridLayoutManager.setOrientation(GridLayout.VERTICAL); //设置布局管理器， 参数gridLayoutManager对象
            recyclerView.setLayoutManager(gridLayoutManager);

            if (!TextUtils.isEmpty(id)) {
                getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_ok:
                popupWindow.dismiss();
                if (allCourseList != null && allCourseList.size() > 0) {
                    for (int i = 0; i < allCourseList.size(); i++) {
                        boolean slect = allCourseList.get(i).isSlect();
                        if (slect) {
                            indextStr += allCourseList.get(i).getName() + ",";
                        }
                    }
                    if (!TextUtils.isEmpty(id)) {
                        if (!TextUtils.isEmpty(indextStr)) {
                            String substring = indextStr.substring(0, indextStr.length() - 1);
                            putData(substring);
                            Log.e("aaa", substring);
                        } else {
                            putData("");
                        }
                    }
                }
                break;
            case R.id.button_cleack:
                popupWindow.dismiss();
                break;
            case R.id.radio_button_unchecked:
            case R.id.text_all:
                if (setCourseSyllabusGridAdapter != null) {
                    if (isAllSlect) {
                        for (int i = 0; i < allCourseList.size(); i++) {
                            allCourseList.get(i).setSlect(false);
                        }
                        isAllSlect = false;
                        radioAllText.setBackgroundResource(R.mipmap.radio_button_unchecked);
                    } else {
                        for (int i = 0; i < allCourseList.size(); i++) {
                            allCourseList.get(i).setSlect(true);
                        }
                        isAllSlect = true;
                        radioAllText.setBackgroundResource(R.mipmap.radio_button_checked);
                    }
                    setCourseSyllabusGridAdapter.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
    }

    //根据主园查询对应的 视频权限
    private void getData() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_COURSE_URISDICTION_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("orgNo", id)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    SetCourseSyllabusBean setCourseSyllabusBean = GsonUtil.gsonToBean(response, SetCourseSyllabusBean.class, activity);
                    if (setCourseSyllabusBean != null) {
                        SetCourseSyllabusBean.DataBean data = setCourseSyllabusBean.getData();
                        if (data != null) {
                            String canWatch = data.getCanWatch();
                            String allCourse = data.getAllCourse();
                            String[] splitCanWatch;
                            String[] splitAllCourse;

                            if (!TextUtils.isEmpty(allCourse) && allCourse.length() > 0) {
                                splitAllCourse = allCourse.split(",");
                                for (int i = 0; i < splitAllCourse.length; i++) {
                                    SetCourseIsSlectBean setCourseIsSlectBean = new SetCourseIsSlectBean();
                                    setCourseIsSlectBean.setName(splitAllCourse[i]);
                                    setCourseIsSlectBean.setSlect(false);
                                    allCourseList.add(setCourseIsSlectBean);
                                }
                            }

                            if (!TextUtils.isEmpty(canWatch) && canWatch.length() > 0) {
                                splitCanWatch = canWatch.split(",");

                                for (int i = 0; i < allCourseList.size(); i++) {
                                    int number = Integer.parseInt(allCourseList.get(i).getName());
                                    for (int j = 0; j < splitCanWatch.length; j++) {
                                        int indext = Integer.parseInt(splitCanWatch[j]);
                                        if (number == indext) {
                                            allCourseList.get(i).setSlect(true);
                                        }
                                    }

                                }
                            }
                            for (int i = 0; i < allCourseList.size(); i++) {
                                if (!allCourseList.get(i).isSlect()) {
                                    isAllSlect = false;
                                    radioAllText.setBackgroundResource(R.mipmap.radio_button_unchecked);
                                }
                            }

                            setCourseSyllabusGridAdapter = new SetCourseSyllabusGridAdapter(activity, allCourseList);
                            recyclerView.setAdapter(setCourseSyllabusGridAdapter);
                            setCourseSyllabusGridAdapter.setRecyclerItemClickListener(new SetCourseSyllabusGridAdapter.OnRecyclerItemClickListener() {
                                @Override
                                public void onItemClick(int position, List<SetCourseIsSlectBean> allCourseList) {
                                    try {
                                        boolean slect = allCourseList.get(position).isSlect();
                                        if (slect) {
                                            allCourseList.get(position).setSlect(false);
                                        } else {
                                            allCourseList.get(position).setSlect(true);
                                        }
                                        setCourseSyllabusGridAdapter.notifyDataSetChanged();
                                        for (int i = 0; i < allCourseList.size(); i++) {
                                            if (allCourseList.get(i).isSlect()) {
                                                tagAll++;
                                            }
                                        }
                                        if (tagAll == allCourseList.size()) {
                                            isAllSlect = true;
                                            radioAllText.setBackgroundResource(R.mipmap.radio_button_checked);
                                        } else {
                                            isAllSlect = false;
                                            radioAllText.setBackgroundResource(R.mipmap.radio_button_unchecked);
                                        }
                                        tagAll = 0;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void putData(String substring) {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_SUBMIT_COURSE_ORGNO_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("indexs", substring)
                    .addParams("orgNo", id)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    BaseBean baseBean = GsonUtil.gsonToBean(response, BaseBean.class, activity);
                    if (baseBean != null) {
                       activity.startActivity(new Intent(activity, SetCourseHintActivity.class));
                    } else {
                        ToastUtils.show("操作失败!");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
