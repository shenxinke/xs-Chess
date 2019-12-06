package com.xswq.chess.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.main.SwitchMainActivity;
import com.xswq.chess.myapplication.activity.personalCenter.IntegralShoppingMallGuideActivity;
import com.xswq.chess.myapplication.adapter.IntegralShoppingMallGridViewAdapter;
import com.xswq.chess.myapplication.base.BaseFragment;
import com.xswq.chess.myapplication.bean.IntegralShoppingMallListBean;
import com.xswq.chess.myapplication.bean.IntegralShoppingUseAndShoppingBean;
import com.xswq.chess.myapplication.customview.RecyclerViewItemClickListener;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Request;

public class IntegralShoppingMallFragment extends BaseFragment {
    private String title;
    private String userId;
    private String token;
    private String productClassify;
    private String classifyName;
    private View mView;
    private ImageView headImage;
    private TextView textName;
    private TextView textDescribe;
    private TextView textNoData;
    private ImageView imageBackground;
    private RecyclerView horizontalListView;
    private Button integralShoppingUse;
    private Button integralShopping;
    private String productId;
    private int productBelong;
    private ImageView alreadyOwned;
    private String productImg;
    private List<IntegralShoppingMallListBean.DataBean.ListBean> list = new ArrayList<>();
    private IntegralShoppingMallGridViewAdapter adapter;
    private int flag;


    public static IntegralShoppingMallFragment getInstances(String productClassify, String userId, String token, String title, String classifyName) {

        IntegralShoppingMallFragment integralShoppingMallFragment = new IntegralShoppingMallFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("userId", userId);
        bundle.putString("token", token);
        bundle.putString("productClassify", productClassify);
        bundle.putString("classifyName", classifyName);
        integralShoppingMallFragment.setArguments(bundle);
        return integralShoppingMallFragment;
    }

    @Override
    protected int setContentView() {
        Bundle bundle = getArguments();
        title = bundle.getString("title");
        userId = bundle.getString("userId");
        token = bundle.getString("token");
        productClassify = bundle.getString("productClassify");
        classifyName = bundle.getString("classifyName");
        return R.layout.fragment_integral_shopping_mall;
    }

    @Override
    protected void startLoad() {
        if (title.equals("已拥有")) {
            String belong = "1";
            getData(belong);
        } else if (title.equals("未拥有")) {
            String belong = "0";
            getData(belong);
        } else if (title.equals("全部")) {
            getAllData();
        }
    }

    @Override
    protected void initData() {
        if (mView == null) {
            mView = getContentView();
        }
        headImage = mView.findViewById(R.id.head_image);
        textName = mView.findViewById(R.id.text_name);
        textDescribe = mView.findViewById(R.id.text_describe);
        textNoData = mView.findViewById(R.id.no_data);
        imageBackground = mView.findViewById(R.id.image_background);
        horizontalListView = mView.findViewById(R.id.horizontalListView);
        alreadyOwned = mView.findViewById(R.id.alreadyOwned);
        integralShoppingUse = getActivity().findViewById(R.id.integral_shopping_use);
        integralShopping = getActivity().findViewById(R.id.integral_shopping);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalListView.setLayoutManager(layoutManager);
        adapter = new IntegralShoppingMallGridViewAdapter(getActivity(), list);
        horizontalListView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                flag = position;
                for (int i = 0; i < list.size(); i++) {
                    if (i != position) {
                        list.get(i).setCheck(false);
                    } else {
                        list.get(i).setCheck(true);
                    }
                }
                if (classifyName.equals("会员")) {
                    textName.setText("会员：" + list.get(position).getProductName());
                } else if (classifyName.equals("头像")) {
                    textName.setText("头像：" + list.get(position).getProductName());
                } else if (classifyName.equals("头像装饰")) {
                    textName.setText("头像装饰：" + list.get(position).getProductName());
                }
                textDescribe.setText(list.get(position).getProductDescription());
                productImg = list.get(position).getProductImg();
                productBelong = list.get(position).getBelong();
                productId = String.valueOf(list.get(position).getID());
                String userHeading = ContactUrl.BASE_PATH + "/" + productImg;
                Glide.with(getActivity()).load(userHeading).dontAnimate().into(headImage);
                adapter.notifyDataSetChanged();
                if (productBelong == 0) {
                    integralShopping.setVisibility(View.VISIBLE);
                    integralShoppingUse.setVisibility(View.GONE);
                    alreadyOwned.setVisibility(View.GONE);
                } else {
                    alreadyOwned.setVisibility(View.VISIBLE);
                    integralShoppingUse.setVisibility(View.VISIBLE);
                    integralShopping.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    protected void stopLoad() {

    }

    private void getAllData() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_SHOPPING_CLASSIFY)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("pageNum", "1")
                    .addParams("pageSize", "20")
                    .addParams("productClassify", productClassify)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response)) {
                        Gson gson = new Gson();
                        IntegralShoppingMallListBean integralShoppingMallListBean = gson.fromJson(response, IntegralShoppingMallListBean.class);
                        IntegralShoppingMallListBean.ErrorBean error = integralShoppingMallListBean.getError();
                        String returnCode = error.getReturnCode();
                        if (returnCode.equals("0")) {
                            IntegralShoppingMallListBean.DataBean data = integralShoppingMallListBean.getData();
                            if (data != null) {
                                int total = data.getTotal();
                                List<IntegralShoppingMallListBean.DataBean.ListBean> list = data.getList();
                                upWindowView(total, list);
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取数据
    private void getData(String belong) {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_SHOPPING_CLASSIFY)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("pageNum", "1")
                    .addParams("pageSize", "20")
                    .addParams("belong", belong)
                    .addParams("productClassify", productClassify)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response)) {
                        Gson gson = new Gson();
                        IntegralShoppingMallListBean integralShoppingMallListBean = gson.fromJson(response, IntegralShoppingMallListBean.class);
                        IntegralShoppingMallListBean.ErrorBean error = integralShoppingMallListBean.getError();
                        String returnCode = error.getReturnCode();
                        if (returnCode.equals("0")) {
                            IntegralShoppingMallListBean.DataBean data = integralShoppingMallListBean.getData();
                            if (data != null) {
                                int total = data.getTotal();
                                List<IntegralShoppingMallListBean.DataBean.ListBean> list = data.getList();
                                upWindowView(total, list);
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void upWindowView(int total, final List<IntegralShoppingMallListBean.DataBean.ListBean> list) {
        if (total > 0) {
            this.list.clear();
            this.list.addAll(list);
            this.list.get(0).setCheck(true);
            adapter.updateList(this.list);

            textNoData.setVisibility(View.GONE);
            imageBackground.setVisibility(View.VISIBLE);
            textName.setVisibility(View.VISIBLE);
            textDescribe.setVisibility(View.VISIBLE);
            horizontalListView.setVisibility(View.VISIBLE);
            textName.setText(this.list.get(0).getProductName());
            textDescribe.setText(this.list.get(0).getProductDescription());
            productBelong = this.list.get(0).getBelong();
            productId = String.valueOf(this.list.get(0).getID());
            productImg = this.list.get(0).getProductImg();
            String userHeading = ContactUrl.BASE_PATH + "/" + productImg;
            Glide.with(getActivity()).load(userHeading).dontAnimate().into(headImage);
            if (productBelong == 0) {
                integralShopping.setVisibility(View.VISIBLE);
                integralShoppingUse.setVisibility(View.GONE);
                alreadyOwned.setVisibility(View.GONE);
            } else {
                alreadyOwned.setVisibility(View.VISIBLE);
                integralShoppingUse.setVisibility(View.VISIBLE);
                integralShopping.setVisibility(View.GONE);
            }

        } else if (total == 0) {
            textNoData.setVisibility(View.VISIBLE);
            imageBackground.setVisibility(View.GONE);
            textName.setVisibility(View.GONE);
            textDescribe.setVisibility(View.GONE);
            horizontalListView.setVisibility(View.GONE);
            integralShoppingUse.setVisibility(View.GONE);
            integralShopping.setVisibility(View.GONE);
        }
        if (productBelong == 0) {
            alreadyOwned.setVisibility(View.GONE);
            integralShopping.setVisibility(View.VISIBLE);
            integralShoppingUse.setVisibility(View.GONE);
        } else {
            alreadyOwned.setVisibility(View.VISIBLE);
            integralShoppingUse.setVisibility(View.VISIBLE);
            integralShopping.setVisibility(View.GONE);
        }
        integralShoppingUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getShoppingUse();
            }
        });
        integralShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getShoppingBuy();
            }
        });
    }

    //购买商品
    private void getShoppingBuy() {
        try {
            OkHttpUtils.get().url( ContactUrl.GET_SHOPPING_BUY)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("productId", productId)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response)) {
                        getUseAndShoppingData(response, 1);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //使用商品
    private void getShoppingUse() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_SHOPPING_USE)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("productId", productId)
                    .build().execute(new StringCallback() {

                @Override
                public void onBefore(Request request, int id) {
                    super.onBefore(request, id);
                    showProgressDialog();
                }

                @Override
                public void onAfter(int id) {
                    super.onAfter(id);
                    dismissProgressDialog();
                }

                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response)) {
                        getUseAndShoppingData(response, 2);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //使用和购买的公用解析类
    private void getUseAndShoppingData(String response, int type) {
        try {
            Gson gson = new Gson();
            IntegralShoppingUseAndShoppingBean integralShoppingForRecordBean = gson.fromJson(response, IntegralShoppingUseAndShoppingBean.class);
            String returnCode = integralShoppingForRecordBean.getError().getReturnCode();
            if (returnCode.equals("0")) {
                if (type == 1) {
                    if (classifyName.equals("会员")) {
                        integralShopping.setVisibility(View.VISIBLE);
                        integralShoppingUse.setVisibility(View.GONE);
                        getVIPShoppingUse();
                    } else {
                        integralShopping.setVisibility(View.GONE);
                        integralShoppingUse.setVisibility(View.VISIBLE);
                        list.get(flag).setBelong(1);
                        adapter.notifyDataSetChanged();
                        alreadyOwned.setVisibility(View.VISIBLE);
                    }
                }
                Intent intent = new Intent(getActivity(), IntegralShoppingMallGuideActivity.class);
                intent.putExtra("type", type);
                intent.putExtra("headUrl", productImg);
                startActivity(intent);
            } else if (returnCode.equals("10067")) {
                ToastUtils.show(integralShoppingForRecordBean.getError().getReturnMessage());
            } else if (returnCode.equals("10048")) {
                ToastUtils.show(integralShoppingForRecordBean.getError().getReturnMessage());
                Intent quiteIntent = new Intent(getActivity(), SwitchMainActivity.class);
                quiteIntent.putExtra("exist", true);
                startActivity(quiteIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //使用会员
    private void getVIPShoppingUse() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_SHOPPING_USE)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("productId", productId)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response)) {
                        Gson gson = new Gson();
                        IntegralShoppingUseAndShoppingBean integralShoppingForRecordBean = gson.fromJson(response, IntegralShoppingUseAndShoppingBean.class);
                        String returnCode = integralShoppingForRecordBean.getError().getReturnCode();
                        if (returnCode.equals("10048")) {
                            ToastUtils.show(integralShoppingForRecordBean.getError().getReturnMessage());
                            Intent quiteIntent = new Intent(getActivity(), SwitchMainActivity.class);
                            quiteIntent.putExtra("exist", true);
                            startActivity(quiteIntent);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
