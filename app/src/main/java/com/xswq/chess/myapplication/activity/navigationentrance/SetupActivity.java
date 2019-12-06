package com.xswq.chess.myapplication.activity.navigationentrance;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.SetupApi;
import com.xswq.chess.myapplication.http.retrofitHttp.RetrofitManager;
import com.xswq.chess.myapplication.http.subscriber.SetupSub;
import com.xswq.chess.myapplication.utils.PreferencesUtils;
import com.xswq.chess.myapplication.widget.SwitchButton;

import org.json.JSONException;
import org.json.JSONObject;

public class SetupActivity extends BaseCompatActivity implements View.OnClickListener, SwitchButton.OnCheckedChangeListener, HttpCallBackLisener {

    private SwitchButton setup_cupicons;
    private SwitchButton setup_musictoneicons;
    private SwitchButton setup_speakericonsas;
    private SwitchButton setup_add_frends;
    private SetupApi mSetupApi;
    private SetupSub mSetupSub;
    private int cupicons;
    private int musictoneicons;
    private int speakericonsas;
    private int add_frends;

    @Override
    protected int getLayoutId() {
        return R.layout.system_setup_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.navigation_bg);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("系统设置");

        setup_cupicons = findViewById(R.id.setup_cupicons);
        setup_cupicons.setOnCheckedChangeListener(this);
        setup_musictoneicons = findViewById(R.id.setup_musictoneicons);
        setup_musictoneicons.setOnCheckedChangeListener(this);
        setup_speakericonsas = findViewById(R.id.setup_speakericonsas);
        setup_speakericonsas.setOnCheckedChangeListener(this);
        setup_add_frends = findViewById(R.id.setup_add_frends);
        setup_add_frends.setOnCheckedChangeListener(this);

        Button setup_save_id = findViewById(R.id.setup_save_id);
        setup_save_id.setOnClickListener(this);
        setSwitchValue();

    }

    @Override
    protected void loadData() {
        getRequstParamer();
    }

    private void getRequstParamer() {
        mSetupApi = new SetupApi(this);
        mSetupApi.setUid(userId);
        mSetupApi.setToken(token);
        mSetupApi.setUserId(userId);
        mSetupApi.setMatchOpts(cupicons);
        mSetupApi.setSoundOpts((musictoneicons + speakericonsas));
        mSetupApi.setImOpts((musictoneicons + speakericonsas + add_frends));
        mSetupSub = new SetupSub(mSetupApi);
    }

    private void setSwitchValue() {
        if (PreferencesUtils.getInt(XSWQApplication.mContext, "cupicons") == 0) {
            setup_cupicons.setChecked(false);
            cupicons = 0;
        } else {
            setup_cupicons.setChecked(true);
            cupicons = 1;
        }
        if (PreferencesUtils.getInt(XSWQApplication.mContext, "musictoneicons") == 0) {
            setup_musictoneicons.setChecked(false);
            musictoneicons = 0;
        } else {
            setup_musictoneicons.setChecked(true);
            musictoneicons = 1;
        }
        if (PreferencesUtils.getInt(XSWQApplication.mContext, "speakericonsas") == 0) {
            setup_speakericonsas.setChecked(false);
            speakericonsas = 0;
        } else {
            setup_speakericonsas.setChecked(true);
            speakericonsas = 1;
        }
        if (PreferencesUtils.getInt(XSWQApplication.mContext, "add_frends") == 0) {
            setup_add_frends.setChecked(false);
            add_frends = 0;
        } else {
            setup_add_frends.setChecked(true);
            add_frends = 1;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.setup_save_id:
                RetrofitManager.getRetrofitInstance().handleHttp(mSetupSub, mSetupApi);
                break;
        }
    }

    @Override
    public void onNext(Object t, String method) {
        try {
            JSONObject error = ((JSONObject) t).getJSONObject("error");
            if (error.optInt("returnCode") == 0) {
                Toast.makeText(this, "保存成功", Toast.LENGTH_LONG).show();
            } else if (error.optInt("returnCode") == 10048) {
                quiteApp(SetupActivity.this, error.optString("returnMessage"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        switch (view.getId()) {
            case R.id.setup_cupicons:
                if (isChecked) {
                    cupicons = 1;
                } else {
                    cupicons = 0;
                }
                PreferencesUtils.putInt(XSWQApplication.mContext, "cupicons", cupicons);
                break;
            case R.id.setup_musictoneicons:
                if (isChecked) {
                    musictoneicons = 1;
                } else {
                    musictoneicons = 0;
                }
                PreferencesUtils.putInt(XSWQApplication.mContext, "musictoneicons", musictoneicons);
                break;
            case R.id.setup_speakericonsas:
                if (isChecked) {
                    speakericonsas = 1;
                } else {
                    speakericonsas = 0;
                }
                PreferencesUtils.putInt(XSWQApplication.mContext, "speakericonsas", speakericonsas);
                break;
            case R.id.setup_add_frends:
                if (isChecked) {
                    add_frends = 1;
                } else {
                    add_frends = 0;
                }
                PreferencesUtils.putInt(XSWQApplication.mContext, "add_frends", add_frends);
                break;
        }
    }
}
