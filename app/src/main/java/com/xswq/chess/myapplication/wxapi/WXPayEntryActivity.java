package com.xswq.chess.myapplication.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.utils.PreferencesUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Const.WX_API = WXAPIFactory.createWXAPI(this, Const.APP_ID, false);
        Const.WX_API.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Const.WX_API.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
//        LogUtil.e("WXPayEntryActivity = " + req.openId);
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) { //支付成功
                PreferencesUtils.putBoolean(XSWQApplication.mContext, "isPaySucceed", true);
            } else if (resp.errCode == -2) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "支付取消", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "支付失败", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
            finish();
        }
    }


}
