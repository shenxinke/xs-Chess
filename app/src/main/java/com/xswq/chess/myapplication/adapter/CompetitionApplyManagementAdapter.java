package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseBean;
import com.xswq.chess.myapplication.bean.CompetitionApplyManagementBean;
import com.xswq.chess.myapplication.bean.UpdateEnrollBean;
import com.xswq.chess.myapplication.customview.StringScrollPicker;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class CompetitionApplyManagementAdapter extends BaseAdapter {
    private Context context;
    private List<CompetitionApplyManagementBean.DataBean> schoolManagementList;
    private List<CompetitionApplyManagementBean.DataBean> lastschoolManagementList = new ArrayList<>();
    private List<UpdateEnrollBean> stringList;
    private String token;
    private String userId;
    private String groupId;
    private String enrollId;
    private PopupWindow popupWindow;
    private int indext;
    private String selectedName;


    public CompetitionApplyManagementAdapter(Context context, List<CompetitionApplyManagementBean.DataBean> schoolManagementList, List<UpdateEnrollBean> stringList, String token, String userId) {
        this.context = context;
        this.schoolManagementList = schoolManagementList;
        this.stringList = stringList;
        this.token = token;
        this.userId = userId;
        this.lastschoolManagementList.addAll(schoolManagementList);
    }

    @Override
    public int getCount() {
        return schoolManagementList.size();
    }

    @Override
    public Object getItem(int position) {
        return schoolManagementList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ComViewHolder comHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.competition_apply_management_adapter_layout, null);
            comHolder = new ComViewHolder();
            comHolder.playerName = convertView.findViewById(R.id.play_name);
            comHolder.className = convertView.findViewById(R.id.class_name);
            comHolder.groupName = convertView.findViewById(R.id.group_name);
            comHolder.butAlter = convertView.findViewById(R.id.but_alter);
            convertView.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) convertView.getTag();
        }
        comHolder.playerName.setText(Util.signString(schoolManagementList.get(position).getUserName()));
        comHolder.className.setText(Util.signString(schoolManagementList.get(position).getClassName()));
        comHolder.groupName.setText(Util.signString(schoolManagementList.get(position).getGroupName()));

        comHolder.butAlter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Util.isClickable()) {
                    return;
                }
                if (stringList.size() > 0) {
                    indext = position;
                    enrollId = String.valueOf(schoolManagementList.get(position).getID());
                    showPopupWindow(v);
                }
            }
        });
        return convertView;
    }

    class ComViewHolder {
        TextView playerName;
        TextView className;
        TextView groupName;
        Button butAlter;
    }

    public void upListDate(List<CompetitionApplyManagementBean.DataBean> data) {
        if (schoolManagementList != null) {
            this.schoolManagementList = data;
            notifyDataSetChanged();
        }
    }

    private void showPopupWindow(View view) {
        View layout = LayoutInflater.from(context).inflate(R.layout.activity_management_group_list_layout, null, false);
        popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setClippingEnabled(false);
        ColorDrawable dw = new ColorDrawable(0xff);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        final StringScrollPicker stringScrollPicker = layout.findViewById(R.id.string_scroll);
        Button buttonConfirm = layout.findViewById(R.id.button_confirm);
        Button buttonCancel = layout.findViewById(R.id.button_cancel);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button_cancel:
                        popupWindow.dismiss();
                        break;
                    case R.id.button_confirm:
                        int selectedPosition = stringScrollPicker.getSelectedPosition();
                        groupId = stringList.get(selectedPosition).getId();
                        selectedName = stringList.get(selectedPosition).getName();
                        if (!TextUtils.isEmpty(enrollId)) {
                            getDate();
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        buttonCancel.setOnClickListener(listener);
        buttonConfirm.setOnClickListener(listener);
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < stringList.size(); i++) {
            String name = stringList.get(i).getName();
            strings.add(name);
        }
        if (strings.size() > 0) {
            stringScrollPicker.setData(strings);
        }
    }

    //更改组别
    private void getDate() {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_UPDATE_ENROLL_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("groupId", groupId)
                    .addParams("enrollId", enrollId)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                }

                @Override
                public void onResponse(String response, int id) {
                    BaseBean baseBean = GsonUtil.gsonToBean(response, BaseBean.class, context);
                    if (baseBean != null) {
                        lastschoolManagementList.get(indext).setGroupName(selectedName);
                        schoolManagementList.clear();
                        schoolManagementList.addAll(lastschoolManagementList);
                        popupWindow.dismiss();
                        notifyDataSetChanged();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
