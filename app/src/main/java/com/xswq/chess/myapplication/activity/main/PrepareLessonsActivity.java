package com.xswq.chess.myapplication.activity.main;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.fragment.BKVideoFragment;
import com.xswq.chess.myapplication.fragment.GuideVideoFragment;
import com.xswq.chess.myapplication.fragment.PracticalLessonsFragment;
import com.xswq.chess.myapplication.fragment.SKVideoFragment;
import com.xswq.chess.myapplication.fragment.ThemeCassFragment;

/**
 * 备课列表
 */
public class PrepareLessonsActivity extends BaseCompatActivity implements View.OnClickListener {

    private String[] switch_titles = new String[]{"主题课", "实战课", "示范课视频", "说课视频", "备课视频"};
    private int pageType = 4;
    private ImageButton leftButton;
    private ImageButton rightButton;
    private Button themeClassButton;
    private Button practicalLessonsButton;
    private Button modelLessonButton;
    private FragmentManager fragmentManager;
    private int flgInt;
    private ThemeCassFragment themeCassFragment;
    private PracticalLessonsFragment practicalLessonsFragment;
    private GuideVideoFragment guideVideoFragment;
    private SKVideoFragment skVideoFragment;
    private BKVideoFragment bkVideoFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.prepare_lessons_activity;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.prepare_lessons_backgroud);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(PrepareLessonsActivity.this);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("备课列表");
        ImageView loginUserHelp = findViewById(R.id.user_help);
        leftButton = findViewById(R.id.prepare_lessons_button_left_bg);
        leftButton.setOnClickListener(this);
        rightButton = findViewById(R.id.prepare_lessons_button_right_bg);
        rightButton.setOnClickListener(this);

        themeClassButton = findViewById(R.id.theme_class_button);
        themeClassButton.setOnClickListener(this);
        practicalLessonsButton = findViewById(R.id.practical_lessons_button);
        practicalLessonsButton.setOnClickListener(this);
        modelLessonButton = findViewById(R.id.model_lesson_button);
        modelLessonButton.setOnClickListener(this);

        if (userType != 3) {
            loginUserHelp.setVisibility(View.VISIBLE);
            loginUserHelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TeacherUserHelpActivity.openActivity(PrepareLessonsActivity.this, pageType);
                }
            });
        } else {
            loginUserHelp.setVisibility(View.GONE);
        }
    }

    @Override
    protected void loadData() {
        Intent mIntent = getIntent();
        flgInt = mIntent.getIntExtra("index", 0);
        fragmentManager = getSupportFragmentManager();
        if (flgInt == 0) {
            themeCassFragment = ThemeCassFragment.getInstances(userId, token, userType);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.prepare_viewpage, themeCassFragment).commit();
        } else {
            tabOneAndThree();
            getForTab(1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.prepare_lessons_button_left_bg:
                leftButton.setVisibility(View.GONE);
                rightButton.setVisibility(View.VISIBLE);
                themeClassButton.setText(switch_titles[0]);
                practicalLessonsButton.setText(switch_titles[1]);
                modelLessonButton.setText(switch_titles[2]);
                tabAll();
                if (flgInt == 0) {
                    tabZeroAndTwo();
                } else if (flgInt == 1) {
                    tabOneAndThree();
                } else if (flgInt == 2) {
                    tabTwoAndFour();
                }
                break;
            case R.id.prepare_lessons_button_right_bg:
                leftButton.setVisibility(View.VISIBLE);
                rightButton.setVisibility(View.GONE);
                themeClassButton.setText(switch_titles[2]);
                practicalLessonsButton.setText(switch_titles[3]);
                modelLessonButton.setText(switch_titles[4]);
                tabAll();
                if (flgInt == 2) {
                    tabZeroAndTwo();
                } else if (flgInt == 3) {
                    tabOneAndThree();
                } else if (flgInt == 4) {
                    tabTwoAndFour();
                }
                break;
            case R.id.theme_class_button:
                String strThemeClass = themeClassButton.getText().toString();
                if (strThemeClass.equals(switch_titles[0])) {
                    getForTab(0);
                } else {
                    getForTab(2);
                }
                tabZeroAndTwo();
                break;
            case R.id.practical_lessons_button:
                String strPracticalLessons = practicalLessonsButton.getText().toString();
                if (strPracticalLessons.equals(switch_titles[1])) {
                    getForTab(1);
                } else {
                    getForTab(3);
                }
                tabOneAndThree();
                break;
            case R.id.model_lesson_button:
                String strModelLessons = modelLessonButton.getText().toString();
                if (strModelLessons.equals(switch_titles[2])) {
                    getForTab(2);
                } else {
                    getForTab(4);
                }
                tabTwoAndFour();
                break;
            default:
                break;
        }
    }

    private void getForTab(int index) {
        flgInt = index;
        hidefragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (index) {
            case 0:
                if (themeCassFragment == null) {
                    themeCassFragment = ThemeCassFragment.getInstances(userId, token, userType);
                    fragmentTransaction.add(R.id.prepare_viewpage, themeCassFragment).commit();
                } else {
                    fragmentTransaction.show(themeCassFragment).commit();
                }
                break;
            case 1:
                if (practicalLessonsFragment == null) {
                    practicalLessonsFragment = PracticalLessonsFragment.getInstances(userId, token, userType);
                    fragmentTransaction.add(R.id.prepare_viewpage, practicalLessonsFragment).commit();
                } else {
                    fragmentTransaction.show(practicalLessonsFragment).commit();
                }
                break;
            case 2:
                if (guideVideoFragment == null) {
                    guideVideoFragment = GuideVideoFragment.getInstances(userId, token);
                    fragmentTransaction.add(R.id.prepare_viewpage, guideVideoFragment).commit();
                } else {
                    fragmentTransaction.show(guideVideoFragment).commit();
                }
                break;
            case 3:
                if (skVideoFragment == null) {
                    skVideoFragment = SKVideoFragment.getInstances(userId, token);
                    fragmentTransaction.add(R.id.prepare_viewpage, skVideoFragment).commit();
                } else {
                    fragmentTransaction.show(skVideoFragment).commit();
                }
                break;
            case 4:
                if (bkVideoFragment == null) {
                    bkVideoFragment = BKVideoFragment.getInstances(userId, token);
                    fragmentTransaction.add(R.id.prepare_viewpage, bkVideoFragment).commit();
                } else {
                    fragmentTransaction.show(bkVideoFragment).commit();
                }
                break;
            default:
                break;
        }
    }

    private void tabZeroAndTwo() {
        themeClassButton.setBackgroundResource(R.mipmap.prepare_lessons_button_check_bg);
        themeClassButton.setTextColor(getResources().getColor(R.color.color_774801));
        practicalLessonsButton.setBackgroundResource(R.mipmap.prepare_lessons_button_uncheck_bg);
        practicalLessonsButton.setTextColor(getResources().getColor(R.color.color_0058b0));
        modelLessonButton.setBackgroundResource(R.mipmap.prepare_lessons_button_uncheck_bg);
        modelLessonButton.setTextColor(getResources().getColor(R.color.color_0058b0));
    }

    private void tabOneAndThree() {
        themeClassButton.setBackgroundResource(R.mipmap.prepare_lessons_button_uncheck_bg);
        themeClassButton.setTextColor(getResources().getColor(R.color.color_0058b0));
        practicalLessonsButton.setBackgroundResource(R.mipmap.prepare_lessons_button_check_bg);
        practicalLessonsButton.setTextColor(getResources().getColor(R.color.color_774801));
        modelLessonButton.setBackgroundResource(R.mipmap.prepare_lessons_button_uncheck_bg);
        modelLessonButton.setTextColor(getResources().getColor(R.color.color_0058b0));
    }

    private void tabTwoAndFour() {
        themeClassButton.setBackgroundResource(R.mipmap.prepare_lessons_button_uncheck_bg);
        themeClassButton.setTextColor(getResources().getColor(R.color.color_0058b0));
        practicalLessonsButton.setBackgroundResource(R.mipmap.prepare_lessons_button_uncheck_bg);
        practicalLessonsButton.setTextColor(getResources().getColor(R.color.color_0058b0));
        modelLessonButton.setBackgroundResource(R.mipmap.prepare_lessons_button_check_bg);
        modelLessonButton.setTextColor(getResources().getColor(R.color.color_774801));
    }

    private void tabAll() {
        themeClassButton.setBackgroundResource(R.mipmap.prepare_lessons_button_uncheck_bg);
        themeClassButton.setTextColor(getResources().getColor(R.color.color_0058b0));
        practicalLessonsButton.setBackgroundResource(R.mipmap.prepare_lessons_button_uncheck_bg);
        practicalLessonsButton.setTextColor(getResources().getColor(R.color.color_0058b0));
        modelLessonButton.setBackgroundResource(R.mipmap.prepare_lessons_button_uncheck_bg);
        modelLessonButton.setTextColor(getResources().getColor(R.color.color_0058b0));
    }

    private void hidefragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //如果Fragment不为空并且已经添加,就隐藏
        if (themeCassFragment != null && themeCassFragment.isAdded()) {
            fragmentTransaction.hide(themeCassFragment);
        }
        if (practicalLessonsFragment != null && practicalLessonsFragment.isAdded()) {
            fragmentTransaction.hide(practicalLessonsFragment);
        }
        if (guideVideoFragment != null && guideVideoFragment.isAdded()) {
            fragmentTransaction.hide(guideVideoFragment);
        }
        if (skVideoFragment != null && skVideoFragment.isAdded()) {
            fragmentTransaction.hide(skVideoFragment);
        }
        if (bkVideoFragment != null && bkVideoFragment.isAdded()) {
            fragmentTransaction.hide(bkVideoFragment);
        }
        fragmentTransaction.commit();
    }

}
