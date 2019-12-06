package com.xswq.chess.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseBean;
import com.xswq.chess.myapplication.bean.HistoryMatchBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class HistoryRecordAdapter extends BaseAdapter {
    private Context context;
    private List<HistoryMatchBean.DataBean.ListBean> list;
    private String token;
    private String userId;

    public void upDataList(List<HistoryMatchBean.DataBean.ListBean> datas) {
        this.list = datas;
        notifyDataSetChanged();
    }

    public HistoryRecordAdapter(Context context, List<HistoryMatchBean.DataBean.ListBean> list, String token, String userId) {
        this.context = context;
        this.list = list;
        this.token = token;
        this.userId = userId;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ComViewHolder comHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.personal_history_item_layout, null);
            comHolder = new ComViewHolder();
            comHolder.history_gametype = view.findViewById(R.id.history_gametype);
            comHolder.history_gametime = view.findViewById(R.id.history_gametime);
            comHolder.history_user1 = view.findViewById(R.id.history_user1);
            comHolder.history_head1 = view.findViewById(R.id.history_head1);
            comHolder.history_user2 = view.findViewById(R.id.history_user2);
            comHolder.history_head2 = view.findViewById(R.id.history_head2);
            comHolder.black_level = view.findViewById(R.id.history_recoder_black_level);
            comHolder.white_level = view.findViewById(R.id.history_recoder_white_level);
            comHolder.history_geme_result = view.findViewById(R.id.history_geme_result);
            comHolder.history_collect = view.findViewById(R.id.history_collect);
            view.setTag(comHolder);
        } else {
            comHolder = (ComViewHolder) view.getTag();
        }
        try {
            if (list.get(i).getGameType() == 1 || list.get(i).getGameType() == 3) {
                comHolder.history_gametype.setText("升降级对局");
            } else if (list.get(i).getGameType() == 2) {
                comHolder.history_gametype.setText("友谊对局");
            } else {
                comHolder.history_gametype.setText("AI对局");
            }

            comHolder.history_gametime.setText(Util.signString(list.get(i).getEndTime()));
            comHolder.history_user1.setText(Util.signString(list.get(i).getBlackUserName()));
            StringBuffer imageUri1 = new StringBuffer();
            imageUri1.append(ContactUrl.BASE_PATH).append("/gobangteach/gobangPc/").append(list.get(i).getBlackHead());
            Glide.with(context)
                    .load(imageUri1.toString())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(comHolder.history_head1);

            comHolder.history_user2.setText(list.get(i).getWhiteUserName());

            StringBuffer imageUri2 = new StringBuffer();
            imageUri2.append(ContactUrl.BASE_PATH).append("/gobangteach/gobangPc/").append(list.get(i).getWhiteHead());
            Glide.with(context)
                    .load(imageUri2.toString())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(comHolder.history_head2);

            if (list.get(i).getPlayResult() == 1) {
                comHolder.history_geme_result.setImageResource(R.mipmap.personal_win);
            } else if (list.get(i).getPlayResult() == 2) {
                comHolder.history_geme_result.setImageResource(R.mipmap.personal_fail);
            } else if (list.get(i).getPlayResult() == 3) {
                comHolder.history_geme_result.setImageResource(R.mipmap.personal_flat);
            } else {
                comHolder.history_geme_result.setImageResource(R.mipmap.personal_nil);
            }
            comHolder.black_level.setText(getStage(list.get(i).getBlackUserLevel()));
            comHolder.white_level.setText(getStage(list.get(i).getWhiteUserLevel()));
            int collect = list.get(i).getCollect();
            if (collect == 1) {
                comHolder.history_collect.setBackgroundResource(R.mipmap.personal_cancel_collection);
            } else {
                comHolder.history_collect.setBackgroundResource(R.mipmap.personal_collection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        comHolder.history_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int collect = list.get(i).getCollect();
                if (collect == 1) {
                    getData(list.get(i).getChessId(), 2);
                    list.get(i).setCollect(2);
                    comHolder.history_collect.setBackgroundResource(R.mipmap.personal_collection);
                } else {
                    getData(list.get(i).getChessId(), 1);
                    list.get(i).setCollect(1);
                    comHolder.history_collect.setBackgroundResource(R.mipmap.personal_cancel_collection);
                }
            }
        });
        return view;
    }

    class ComViewHolder {
        TextView history_gametype;
        TextView history_gametime;
        TextView history_user1;
        TextView history_user2;
        TextView black_level;
        TextView white_level;
        ImageView history_geme_result;
        Button history_collect;
        ImageView history_head1;
        ImageView history_head2;
    }

    private String getStage(int level) {
        String levels;
        if (level <= 25) {
            levels = 26 - level + "K";
        } else {
            levels = level - 25 + "D";
        }
        return levels;
    }

    private void getData(int chessId, final int collect) {
        try {
            PostFormBuilder post = OkHttpUtils.post();
            post.url(ContactUrl.HISTORYCOLLECTCHESS_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("type", "2")
                    .addParams("collect", String.valueOf(collect))
                    .addParams("chessId", String.valueOf(chessId));
            post.build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    BaseBean baseBean = GsonUtil.gsonToBean(response, BaseBean.class, context);
                    if (baseBean != null) {
                        if (collect == 1) {
                            ToastUtils.show("收藏成功");
                        } else {
                            ToastUtils.show("取消收藏成功");
                        }
                    }
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

