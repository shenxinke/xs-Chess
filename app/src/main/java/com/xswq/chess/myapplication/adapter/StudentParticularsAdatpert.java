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
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.base.BaseBean;
import com.xswq.chess.myapplication.bean.StudentParticylarsBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class StudentParticularsAdatpert extends BaseAdapter {
    private Context context;
    private List<StudentParticylarsBean.DataBean> roundsList;
    private String userId;
    private String token;

    public StudentParticularsAdatpert(Context context, List<StudentParticylarsBean.DataBean> roundsList, String token, String userId) {
        this.context = context;
        this.roundsList = roundsList;
        this.userId = userId;
        this.token = token;
    }

    public void upData(List<StudentParticylarsBean.DataBean> newData) {
        this.roundsList = newData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return roundsList.size();
    }

    @Override
    public Object getItem(int position) {
        return roundsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.student_particulars_item_layout, null);
            holder.titleNumber = convertView.findViewById(R.id.text_title_number);
            holder.headImageLeft = convertView.findViewById(R.id.head_image_left);
            holder.headImageRight = convertView.findViewById(R.id.head_image_right);
            holder.textNameLeft = convertView.findViewById(R.id.text_name_left);
            holder.textNameRight = convertView.findViewById(R.id.text_name_right);
            holder.textLeftSheng = convertView.findViewById(R.id.text_left_sheng);
            holder.textRightSheng = convertView.findViewById(R.id.text_right_sheng);
            holder.buttonAppeal = convertView.findViewById(R.id.button_appeal);
            holder.userLeft = convertView.findViewById(R.id.user_left);
            holder.userRight = convertView.findViewById(R.id.user_right);
            holder.textLeftQi = convertView.findViewById(R.id.text_left_qi);
            holder.textRightQi = convertView.findViewById(R.id.text_right_qi);
            holder.imageVs = convertView.findViewById(R.id.image_vs);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        final String whiteId = roundsList.get(position).getWhiteId();
        final String blackId = roundsList.get(position).getBlackId();
        int indext = position + 1;
        String titleNumber = "第 " + indext + " 台";
        holder.titleNumber.setText(titleNumber);
        if (TextUtils.isEmpty(whiteId) || TextUtils.isEmpty(blackId)) {
            if (TextUtils.isEmpty(whiteId)) {
                holder.headImageLeft.setVisibility(View.GONE);
                holder.textNameLeft.setVisibility(View.GONE);
                holder.textLeftSheng.setVisibility(View.GONE);
                holder.userLeft.setVisibility(View.GONE);
                holder.textLeftQi.setVisibility(View.GONE);
                holder.imageVs.setVisibility(View.GONE);
                holder.buttonAppeal.setVisibility(View.GONE);
                String blackHeading = ContactUrl.BASE_PATH + "/" + roundsList.get(position).getBlackHeading();
                Glide.with(XSWQApplication.getmContext()).load(blackHeading).dontAnimate().into(holder.headImageRight);
                String blackName = roundsList.get(position).getBlackName();
                int blackNum = roundsList.get(position).getBlackNum();
                String blackUser = "选手" + blackNum;
                holder.textNameRight.setText(Util.signString(blackName));
                holder.textRightSheng.setVisibility(View.VISIBLE);
                holder.textRightSheng.setBackgroundResource(R.mipmap.lun_kong);
                holder.userRight.setText(blackUser);
            } else {
                holder.headImageRight.setVisibility(View.GONE);
                holder.textNameRight.setVisibility(View.GONE);
                holder.textRightSheng.setVisibility(View.GONE);
                holder.userRight.setVisibility(View.GONE);
                holder.textRightQi.setVisibility(View.GONE);
                holder.imageVs.setVisibility(View.GONE);
                holder.buttonAppeal.setVisibility(View.GONE);
                String whiteHeading = ContactUrl.BASE_PATH + "/" + roundsList.get(position).getWhiteHeading();
                Glide.with(XSWQApplication.getmContext()).load(whiteHeading).dontAnimate().into(holder.headImageLeft);
                int whiteNum = roundsList.get(position).getWhiteNum();
                String whiteUser = "选手" + whiteNum;
                String whiteName = roundsList.get(position).getWhiteName();
                holder.textNameLeft.setText(Util.signString(whiteName));
                holder.textLeftSheng.setVisibility(View.VISIBLE);
                holder.textLeftSheng.setBackgroundResource(R.mipmap.lun_kong);
                holder.userLeft.setText(whiteUser);
            }
        } else {
            holder.headImageLeft.setVisibility(View.VISIBLE);
            holder.textNameLeft.setVisibility(View.VISIBLE);
            holder.textLeftSheng.setVisibility(View.VISIBLE);
            holder.userLeft.setVisibility(View.VISIBLE);
            holder.textLeftQi.setVisibility(View.VISIBLE);
            holder.imageVs.setVisibility(View.VISIBLE);
            holder.buttonAppeal.setVisibility(View.VISIBLE);

            holder.headImageRight.setVisibility(View.VISIBLE);
            holder.textNameRight.setVisibility(View.VISIBLE);
            holder.textRightSheng.setVisibility(View.VISIBLE);
            holder.userRight.setVisibility(View.VISIBLE);
            holder.textRightQi.setVisibility(View.VISIBLE);

            String whiteHeading = ContactUrl.BASE_PATH + "/" + roundsList.get(position).getWhiteHeading();
            String blackHeading = ContactUrl.BASE_PATH + "/" + roundsList.get(position).getBlackHeading();
            Glide.with(XSWQApplication.getmContext()).load(whiteHeading).dontAnimate().into(holder.headImageLeft);
            Glide.with(XSWQApplication.getmContext()).load(blackHeading).dontAnimate().into(holder.headImageRight);
            String whiteName = roundsList.get(position).getWhiteName();
            holder.textNameLeft.setText(Util.signString(whiteName));
            String blackName = roundsList.get(position).getBlackName();
            holder.textNameRight.setText(Util.signString(blackName));
            int whiteNum = roundsList.get(position).getWhiteNum();
            String whiteUser = "选手" + whiteNum;
            holder.userLeft.setText(whiteUser);
            int blackNum = roundsList.get(position).getBlackNum();
            String blackUser = "选手" + blackNum;
            holder.userRight.setText(blackUser);
            int result = roundsList.get(position).getResult();
            switch (result) {
                case 1:
                    holder.textRightSheng.setBackgroundResource(R.mipmap.student_particulars_sheng);
                    holder.textRightSheng.setVisibility(View.VISIBLE);
                    holder.textLeftSheng.setVisibility(View.GONE);
                    break;
                case 2:
                    holder.textLeftSheng.setBackgroundResource(R.mipmap.student_particulars_sheng);
                    holder.textLeftSheng.setVisibility(View.VISIBLE);
                    holder.textRightSheng.setVisibility(View.GONE);
                    break;
                case 3:
                    holder.textRightSheng.setBackgroundResource(R.mipmap.student_particulars_ping);
                    holder.textLeftSheng.setBackgroundResource(R.mipmap.student_particulars_ping);
                    holder.textLeftSheng.setVisibility(View.VISIBLE);
                    holder.textRightSheng.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
            if (roundsList.get(position).getResult() > 0) {
                if (userId.equals(whiteId) || userId.equals(blackId)) {
                    if (roundsList.get(position).getJudge() == 0) {
                        holder.buttonAppeal.setBackgroundResource(R.mipmap.integral_shopping_mall_yes);
                    } else {
                        holder.buttonAppeal.setBackgroundResource(R.mipmap.play_by_play_unapply);
                    }
                } else {
                    holder.buttonAppeal.setBackgroundResource(R.mipmap.play_by_play_unapply);
                }
            } else {
                holder.buttonAppeal.setBackgroundResource(R.mipmap.play_by_play_unapply);
            }
        }
        holder.buttonAppeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (roundsList.get(position).getResult() > 0) {
                    if (userId.equals(whiteId) || userId.equals(blackId)) {
                        if (roundsList.get(position).getJudge() == 0) {
                            String id = roundsList.get(position).getId();
                            updataPassWord(id, v, position);
                        }
                    }
                }
            }
        });
        return convertView;
    }

    static class Holder {
        TextView titleNumber;
        ImageView headImageLeft;
        ImageView headImageRight;
        ImageView textLeftSheng;
        ImageView textRightSheng;
        ImageView imageVs;
        TextView textNameLeft;
        TextView userRight;
        TextView userLeft;
        TextView textNameRight;
        ImageView textLeftQi;
        ImageView textRightQi;
        Button buttonAppeal;
    }

    private void updataPassWord(String arenaId, final View view, final int position) {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_APPPLAY_JUDGE_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("arenaId", arenaId)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    BaseBean baseBean = GsonUtil.gsonToBean(response, BaseBean.class, context);
                    if (baseBean != null) {
                        roundsList.get(position).setJudge(1);
                        Button button = view.findViewById(R.id.button_appeal);
                        button.setBackgroundResource(R.mipmap.play_by_play_unapply);
                        showPopWindow(view);
                    }
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showPopWindow(View view) {
        View layout = LayoutInflater.from(context).inflate(R.layout.student_competition_pop_layout, null, false);
        final PopupWindow popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setClippingEnabled(false);
        ColorDrawable dw = new ColorDrawable(0xff);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        Button confirm = layout.findViewById(R.id.button_confirm);
        TextView content = layout.findViewById(R.id.text_content);
        content.setText("申诉已提交");
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button_confirm:
                        popupWindow.dismiss();
                        break;
                    default:
                        break;
                }
            }
        };
        confirm.setOnClickListener(listener);
    }
}
