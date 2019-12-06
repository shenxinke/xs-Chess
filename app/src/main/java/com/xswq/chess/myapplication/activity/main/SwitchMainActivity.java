package com.xswq.chess.myapplication.activity.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.netease.neliveplayer.playerkit.sdk.PlayerManager;
import com.netease.neliveplayer.playerkit.sdk.model.SDKInfo;
import com.netease.neliveplayer.playerkit.sdk.model.SDKOptions;
import com.netease.neliveplayer.proxy.config.NEPlayerConfig;
import com.netease.neliveplayer.sdk.NELivePlayer;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.SwitchMainAdapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.UserInfoBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.config.RxCode;
import com.xswq.chess.myapplication.customview.NoScrollViewPager;
import com.xswq.chess.myapplication.fragment.SwitchMainFragment;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.HttpPostUtils;
import com.xswq.chess.myapplication.utils.InitWebSocket;
import com.xswq.chess.myapplication.utils.LogUtil;
import com.xswq.chess.myapplication.utils.NewMobileVersionUtils;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.zhangke.websocket.WebSocketHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import gorden.rxbus2.RxBus;
import gorden.rxbus2.Subscribe;
import gorden.rxbus2.ThreadMode;
import okhttp3.Call;

public class SwitchMainActivity extends BaseCompatActivity implements View.OnClickListener {

    @BindView(R.id.switch_viewpage)
    NoScrollViewPager switchViewPage;
    @BindView(R.id.btoom_image)
    ImageView btoomImage;
    @BindView(R.id.main_page_image_button)
    ImageButton mainPageImageButton;
    @BindView(R.id.main_find_image_button)
    ImageButton mainFindImageButton;
    @BindView(R.id.main_personage_image_button)
    ImageButton mainPersonageImageButton;

    @BindView(R.id.main_find_image_)
    ImageView mainFindImage;
    @BindView(R.id.main_personage_image)
    ImageView mainPersonageImage;
    @BindView(R.id.main_page_image)
    ImageView mainPageImage;

    private boolean mIsExit;
    private boolean bPermission = false;
    private final int WRITE_PERMISSION_REQ_CODE = 100;
    private String[] switchTitles = new String[]{"主页", "发现", "个人中心"};

    @Override
    protected int getLayoutId() {
        return R.layout.main_switch_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.main_bg);
        RxBus.get().register(this);
        new NewMobileVersionUtils(SwitchMainActivity.this, 0);
        new InitWebSocket(this);
        WebSocketHandler.getDefault().addListener(socketListener);
        mainPageImageButton.setOnClickListener(this);
        mainFindImageButton.setOnClickListener(this);
        mainPersonageImageButton.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        bPermission = checkPublishPermission();
        if (bPermission) {
            initVideoPlayer();
        }
        List<Fragment> listFragment = new ArrayList<>();
        for (String title : switchTitles) {
            if (!TextUtils.isEmpty(title)) {
                listFragment.add(SwitchMainFragment.getInstances(title));
            }
        }
        SwitchMainAdapter mSwitchMainAdapter = new SwitchMainAdapter(getSupportFragmentManager(), switchTitles, listFragment);
        switchViewPage.setNoScroll(false);
        switchViewPage.setOffscreenPageLimit(listFragment.size());
        switchViewPage.setAdapter(mSwitchMainAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserInfo();
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_page_image_button:
                switchViewPage.setCurrentItem(0);
                mainPageImage.setVisibility(View.VISIBLE);
                mainFindImage.setVisibility(View.GONE);
                mainPersonageImage.setVisibility(View.GONE);
                break;
            case R.id.main_find_image_button:
                switchViewPage.setCurrentItem(1);
                mainPageImage.setVisibility(View.GONE);
                mainFindImage.setVisibility(View.VISIBLE);
                mainPersonageImage.setVisibility(View.GONE);
                break;
            case R.id.main_personage_image_button:
                switchViewPage.setCurrentItem(2);
                mainPageImage.setVisibility(View.GONE);
                mainFindImage.setVisibility(View.GONE);
                mainPersonageImage.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
        WebSocketHandler.getDefault().disConnect();
        WebSocketHandler.getDefault().removeListener(socketListener);
    }

    //按俩次back按钮退出app launchMode为singletask
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();
            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {//是否统一管理handler
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void getUserInfo() {
        try {
            OkHttpUtils.get().url(ContactUrl.GET_USER_INFOR_BY_ID_PATH)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("userid", userId)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    UserInfoBean userInfoBean = GsonUtil.gsonToBean(response, UserInfoBean.class, SwitchMainActivity.this);
                    if (userInfoBean == null) return;
                    UserInfoBean.DataBean data = userInfoBean.getData();
                    if (data == null) return;
                    UserInfoBean.DataBean.BaseBean base = data.getBase();
                    if (base == null) return;
                    String username = base.getUsername();
                    int level = base.getLevel();
                    long birthday = base.getBirthday();
                    String address = base.getAddress();
                    String orgName = base.getOrgName();
                    String userHeading = base.getHeadimg();
                    String mobile = base.getMobile();
                    int sex = base.getSex();
                    long experienceTime = base.getExperienceTime();

                    SPUtil.put("level", level);
                    SPUtil.put("userName", username);
                    SPUtil.put("birthday", birthday);
                    SPUtil.put("address", address);

                    SPUtil.put("userHeading", userHeading);
                    SPUtil.put("sex", sex);
                    SPUtil.put("orgName", orgName);
                    SPUtil.put("mobile", mobile);
                    SPUtil.put("experienceTime", experienceTime);
                    RxBus.get().send(RxCode.CODE_MAIN_MESSAGE_MESSAGE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //---------------------------------------- 初始化视频播放-------------------------------
    private boolean checkPublishPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(SwitchMainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(SwitchMainActivity.this,
                    Manifest.permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(SwitchMainActivity.this,
                    Manifest.permission.READ_PHONE_STATE)) {
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(SwitchMainActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(SwitchMainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions(SwitchMainActivity.this, permissions.toArray(new String[0]),
                        WRITE_PERMISSION_REQ_CODE);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_PERMISSION_REQ_CODE) {
            initVideoPlayer();
            for (int ret : grantResults) {
                if (ret != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }
            bPermission = true;
        }
    }

    private void initVideoPlayer() {
        SDKOptions config = new SDKOptions();
        config.dataUploadListener = mOnDataUploadListener;
        //是否支持H265解码回调
        config.supportDecodeListener = mOnSupportDecodeListener;
        //这里可以绑定客户的账号系统或device_id，方便出问题时双方联调
        //        config.thirdUserId = "your_id";
        config.privateConfig = new NEPlayerConfig();
        PlayerManager.init(this, config);
        SDKInfo sdkInfo = PlayerManager.getSDKInfo(this);
        Log.e("aaa", "NESDKInfo:version" + sdkInfo.version + ",deviceId:" + sdkInfo.deviceId);
    }

    private NELivePlayer.OnDataUploadListener mOnDataUploadListener = new NELivePlayer.OnDataUploadListener() {

        @Override
        public boolean onDataUpload(String url, String data) {
            Log.e("aaa", "onDataUpload url:" + url + ", data:" + data);
            sendData(url, data);
            return true;
        }

        @Override
        public boolean onDocumentUpload(String url, Map<String, String> params, Map<String, String> filepaths) {
            Log.e("aaa", "onDataUpload url:" + url + ", params:" + params + ",filepaths:" + filepaths);
            return (new HttpPostUtils(url, params, filepaths).connPost());
        }
    };

    private NELivePlayer.OnSupportDecodeListener mOnSupportDecodeListener = new NELivePlayer.OnSupportDecodeListener() {

        @Override
        public void onSupportDecode(boolean isSupport) {
        }
    };

    private void sendData(final String urlStr, final String content) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(content.getBytes());
            int response = conn.getResponseCode();
        } catch (IOException e) {
            Log.e("aaa", "sendData, recv code is error: " + e.getMessage());
        } catch (Exception e) {
            Log.e("aaa", "sendData, recv code is error2: " + e.getMessage());

        }
    }

    @Subscribe(code = RxCode.CODE_CONNECT_SCOKET_MESSAGE, threadMode = ThreadMode.MAIN)
    public void connectScoket() {
        WebSocketHandler.getDefault().reconnect();
        LogUtil.e("连接Scoket");
    }

    @Subscribe(code = RxCode.CODE_CLOSE_SCOKET_MESSAGE, threadMode = ThreadMode.MAIN)
    public void closeScoket() {
        WebSocketHandler.getDefault().disConnect();
        LogUtil.e("关闭Scoket");
    }

    @Subscribe(code = RxCode.CODE_MAIN_EXPIRE_MESSAGE_MESSAGE, threadMode = ThreadMode.MAIN)
    public void mainExpire() {
         getUserInfo();
    }
}
