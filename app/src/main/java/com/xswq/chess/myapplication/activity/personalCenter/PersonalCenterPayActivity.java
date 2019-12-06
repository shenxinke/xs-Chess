package com.xswq.chess.myapplication.activity.personalCenter;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.PersonalUpDataKeyBean;
import com.xswq.chess.myapplication.bean.WXPayResultBean;
import com.xswq.chess.myapplication.bean.WxPayBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.PreferencesUtils;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import okhttp3.Call;

public class PersonalCenterPayActivity extends BaseCompatActivity {
    private PopupWindow popupWindow;

    @Override
    protected int getLayoutId() {
        return R.layout.personal_center_pay_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.personal_bg);
        Button wxPay = findViewById(R.id.wx_pay);
        TextView login_queding = findViewById(R.id.login_queding);
        login_queding.setVisibility(View.VISIBLE);
        login_queding.setText("使用激活码");
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("VIP续费");
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        login_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Util.isClickable()) {
                    return;
                }
                showPopupWindow(null);
            }
        });
        wxPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Util.isClickable()) {
                    return;
                }
                getWxPay();
            }
        });
    }

    @Override
    protected void loadData() {

    }

    //微信支付
    private void getWxPay() {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_WX_PAY_PATH)
                    .addParams("money", "720")
                    .addParams("body", "半年卡")
                    .addParams("dayNum", "180")
                    .addParams("token", token)
                    .addParams("userId", userId)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response)) {
                        WxPayBean wxPayBean = GsonUtil.gsonToBean(response, WxPayBean.class, PersonalCenterPayActivity.this);
                        if (wxPayBean != null) {
                            WxPayBean.DataBean data = wxPayBean.getData();
                            Const.WX_API = WXAPIFactory.createWXAPI(PersonalCenterPayActivity.this, data.getAppId());  //应用ID 即微信开放平台审核通过的应用APPID
                            Const.WX_API.registerApp(data.getAppId());    //应用ID
                            PayReq payReq = new PayReq();
                            payReq.appId = data.getAppId();        //应用ID
                            payReq.partnerId = data.getMchId();      //商户号 即微信支付分配的商户号
                            payReq.prepayId = data.getPrepayId();        //预支付交易会话ID
                            payReq.packageValue = "Sign=WXPay";    //扩展字段
                            payReq.nonceStr = data.getNonceStr();        //随机字符串不长于32位。
                            payReq.timeStamp = data.getTimeStamp(); //时间戳
                            payReq.sign = data.getPaySign();             //签名
                            Const.WX_API.sendReq(payReq);
                            String orderKey = data.getOrderKey();
                            PreferencesUtils.putString(XSWQApplication.mContext, "orderKey", orderKey);
                        } else {
                            ToastUtils.show(Const.CONS_STR_ERROR);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //显示提示框
    private void showPopupWindow(String returnMessage) {
        final View layout = LayoutInflater.from(PersonalCenterPayActivity.this).inflate(R.layout.personal_center_pop_layout, null, false);
        View view = getWindow().getDecorView();
        popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        // 设置好参数之后再show
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        ImageView imgCancel = layout.findViewById(R.id.shutdown);
        Button button = layout.findViewById(R.id.button);
        TextView text = layout.findViewById(R.id.text);
        final EditText editText = layout.findViewById(R.id.edit_text);
        if (TextUtils.isEmpty(returnMessage)) {
            editText.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
        } else {
            text.setText(Util.signString(returnMessage));
        }
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.shutdown:
                        popupWindow.dismiss();
                        break;
                    case R.id.button:
                        String strCdk = editText.getText().toString().trim();
                        if (!TextUtils.isEmpty(strCdk)) {
                            updateKEY(strCdk);
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        //设置popwindow布局控件的点击事件
        imgCancel.setOnClickListener(listener);
        button.setOnClickListener(listener);
    }

    //CDK兑换
    private void updateKEY(String strCdk) {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_UPDATE_KEY_PATH)
                    .addParams("token", token)
                    .addParams("userId", userId)
                    .addParams("key", strCdk)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    PersonalUpDataKeyBean personalUpDataKeyBean = GsonUtil.gsonToBean(response, PersonalUpDataKeyBean.class, PersonalCenterPayActivity.this);
                    if (personalUpDataKeyBean != null) {
                        popupWindow.dismiss();
//                        long experienceTime = personalUpDataKeyBean.getData().getExperienceTime();
//                        String dateToString = DateUtil.getDateToString(experienceTime, "yyyy-MM-dd");
                        showPopupWindow("兑换成功!");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        String orderKey = PreferencesUtils.getString(PersonalCenterPayActivity.this, "orderKey");
        boolean isPaySucceed = PreferencesUtils.getBoolean(PersonalCenterPayActivity.this, "isPaySucceed");
        if (isPaySucceed && orderKey != null) {
            getBuyResult(orderKey);
            PreferencesUtils.putBoolean(XSWQApplication.mContext, "isPaySucceed", false);
        }
    }

    //微信支付校验
    private void getBuyResult(String orderKey) {
        try {
            OkHttpUtils.post().url(ContactUrl.POST_SELECT_ORDER_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("orderId", orderKey)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show("支付失败");
                }

                @Override
                public void onResponse(String response, int id) {
                    WXPayResultBean wxPayResultBean = GsonUtil.gsonToBean(response, WXPayResultBean.class, PersonalCenterPayActivity.this);
                    if (wxPayResultBean != null) {
                        showPopupWindow("续费成功!");
                    } else {
                        showPopupWindow("支付失败!");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
