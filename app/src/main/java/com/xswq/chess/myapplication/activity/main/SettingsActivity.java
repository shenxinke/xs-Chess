package com.xswq.chess.myapplication.activity.main;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.navigationentrance.AboutusActivity;
import com.xswq.chess.myapplication.activity.navigationentrance.HelpcenterActivity;
import com.xswq.chess.myapplication.activity.navigationentrance.SetupActivity;
import com.xswq.chess.myapplication.activity.start.ChangePassWordActivity;
import com.xswq.chess.myapplication.activity.start.LoginActivity;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.utils.NewMobileVersionUtils;
import com.xswq.chess.myapplication.utils.PreferencesUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingsActivity extends BaseCompatActivity {

    @BindView(R.id.login_back)
    TextView loginBack;
    @BindView(R.id.login_titles)
    TextView loginTitles;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settings_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.main_settings_bg);
        loginBack.setVisibility(View.VISIBLE);
        loginTitles.setVisibility(View.VISIBLE);
        loginTitles.setText("设置");
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.setup, R.id.help_center, R.id.change_password, R.id.about_us_image, R.id.check_for_updates_button
            , R.id.switch_account_button, R.id.login_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setup:
                startActivity(new Intent(SettingsActivity.this, SetupActivity.class));
                break;
            case R.id.help_center:
                startActivity(new Intent(SettingsActivity.this, HelpcenterActivity.class));
                break;
            case R.id.change_password:
                startActivity(new Intent(SettingsActivity.this, ChangePassWordActivity.class));
                break;
            case R.id.about_us_image:
                startActivity(new Intent(SettingsActivity.this, AboutusActivity.class));
                break;
            case R.id.check_for_updates_button:
                new NewMobileVersionUtils(SettingsActivity.this, 1);
                break;
            case R.id.switch_account_button:
                PreferencesUtils.clear(SettingsActivity.this);
                Intent intent = new Intent(SettingsActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
            case R.id.login_back:
                finish();
                break;
            default:
                break;
        }
    }
}
