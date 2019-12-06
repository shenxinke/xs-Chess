package com.xswq.chess.myapplication.activity.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.utils.JumpIntent;

public class TeacherUserHelpActivity extends BaseActivity implements View.OnClickListener {

    private TextView textBack;
    private int pageType;
    //教学体系
    private String strText =
            "1.     教学大纲：在开课前充分了解幼儿园围棋启蒙教学的整体规划、详细内容，可以帮助老师在教学的时候清晰孩子的学习阶段。\n" +
                    "\n" +
                    "2.     教学课标：从知识、棋力、素养三个方面，根据幼儿的年龄特点，结合幼儿园五大领域训练重点，制定幼儿园围棋启蒙教学标准。帮助老师有的放矢地检视幼儿学习成果。\n" +
                    "\n" +
                    "3.     课时规划建议：根据幼儿园教学设置特点和围棋学科的独特性，给出幼儿园大班和幼儿园中班课时规划建议，帮助老师把控教学进度和节奏。\n";

    //标准化智能课件
    private String strText2 = "首创人工智能和幼儿教师双师互动的围棋启蒙教学新模式。利用标准化智能互动课件，将32个围棋基础知识点用动画故事的形式串联起来，即有围棋知识严谨规范的讲解，又有幼儿喜欢的动画情节。让学习更轻松、更有趣！ 每一集动画后面，还设有知识点短视频的剪辑和习题库。保证孩子从知道到做到的学习闭环形成。";
    private String strText3 = "在每次上课前一天，建议老师首先能完整看一遍动画片。然后着重了解一下知识点短视频的剪辑内容，明确本节课的教学重难点。再然后提前了解一下讲课模式题库。最后，去试做一下习题模式题库内容。\n" +
            "\n" + "      这个过程是为课堂上知识点教学做准备的重要备课。\n";
    //指导视频
    private String strText4 =
            "指导视频中包含三个不同角度的视频内容。\n" +
                    "\n" +
                    "1.     说课视频，是老师在备课阶段了解课堂每个环节设计意图、设计内容的途径。\n" +
                    "\n" +
                    "2.     示范课视频，是根据说课内容，在实际授课中的实操。可以看到设计的内容在实操中的发生。\n" +
                    "\n" +
                    "3.     在线集体备课视频是根据园所教学进度，每周五中午12:30-13:30，教研组老师对下周的课程从围棋讲解、教学案例、实战指导三个方面进行详细说明。直播内容会录制下来，放在平台上供老师回放观看，具有指导的时效性。\n";
    //备课列表
    private String strText5 = "备课类型分为主题课和实战课\n" +
            "\n" +
            "1.     每一节课的教学目标、教学重点、教学难点、教学准备，我们教研组已经做了明确的设定。\n" +
            "\n" +
            "2.     每一节课的详案和板书，教研组也已经做好了准备。建议老师在第一次上课的时候，能详细阅读和体会。在标准化详案的基础上，做出个性化详案。\n" +
            "\n" +
            "3.     主题课的7步：导入——分组——自学——导学——试练——摆题——小结。\n" +
            "\n" +
            "4.     实战课的7步：导入——分组——练兵——导学——试练——对弈——小结。\n" +
            "\n" +
            "需要备课的课程，点击列表右侧操作中“我要备课”，进入备课界面\n" +
            "\n" +
            "已备课的课程，点击列表右侧操作中“查看自己备课”，即可查看已备课的内容\n";
    //我要备课
    private String strText6 = "1.     点击“我要备课”，选择课题，即可进入标准化详案和板书的查看页面。右侧有个性化详案的编辑框。如果需要编辑在课堂上老师讲课的详细内容，可在此框填写。（可以自己编写，也可以复制粘贴前面标准化详案里的内容。）\n" +
            "\n" +
            "2.     查看、编写完毕，一定记得点击提交按钮。生成上课要使用的课件。\n" +
            "\n" +
            "3.     点击“查看图片”，可预览板书图片\n";

    //在线对弈
    private String strText7 = "对弈是围棋学习不可或缺的一个环节，先手围棋开发了适合于孩子使用的对弈功能，让孩子们能找到与自己棋力相当的小伙伴对弈，更快的提高自己；更可以挑战自我，与平台不同难度的AI进行对弈。";
    private String strText8 = "晋级赛：与平台内同棋力用户随机匹配对弈，对弈结果将影响个人棋力。\n" +
            "\n" +
            "友谊赛：与平台内好友进行对弈，对弈规则可自定义，对弈结果不影响个人棋力。\n" +
            "\n" +
            "AI对弈：与平台AI进行对弈，共五种难度可供选择，对弈结果不影响个人棋力。\n";

    //题库
    private String strText9 = "围棋学习，离不开做题，先手围棋的题库，总量超过两万道题。为了给教学提供更好的支持，先手围棋按棋力等级、知识类型做了细致的分类，主要包括死活、手筋、官子、行棋四大类，根据级别，每个分类下涵盖了学习全部所需的知识点，从25K到6D，覆盖全面。";
    private String strText10 = "1.     在左侧棋盘上点击落子，解答正确的习题将提示正确，解答错误的习题将提示错误，并将错题自动加入到“错题本”，可进入错题本重新做题，在错题本中解答正确后，该题将会移出错题本\n" +
            "\n" +
            "2.     右侧上方可选择习题的类型、级别、知识点\n" +
            "\n" +
            "3.     收藏按钮可收藏习题，收藏后的习题可在“我的收藏”中查看\n" +
            "\n" +
            "4.     点击“换一批”按钮后，即可更新当前习题列表\n";

    //布置作业
    private String strText11 = "• 左侧为学生列表，在列表中勾选需要布置作业的班级或学生\n" +
            "\n" +
            "• 课程：通过标准化智能课件的课后练习选择习题\n" +
            "\n" +
            "• 题库：通过平台题库选择习题\n" +
            "\n" +
            "• 我的收藏：通过收藏夹选择习题\n" +
            "\n" +
            "• 选择习题后，习题缩略图即显示在右侧区域，点击缩略图可删除习题\n" +
            "\n" +
            "• 点击“布置作业”即可将作业布置到学生端\n";
    //棋力测评
    private String strText12 = "先手围棋基于人工智能技术，设立了合理的评估体系，孩子们通过测评，能较为准确的了解自己当前的棋力水平。";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        Intent intent = getIntent();
        pageType = intent.getIntExtra("pageType", 1);
        initView();
        textBack = findViewById(R.id.login_back);
        textBack.setOnClickListener(this);
    }

    private void initView() {
        if (pageType == 1) {
            setContentView(R.layout.activity_user_help_layout);
            TextView textHelp1 = findViewById(R.id.user_help_text);
            textHelp1.setText(strText);
        } else if (pageType == 2) {
            setContentView(R.layout.activity_user_help3_layout);
            TextView textFunction = findViewById(R.id.user_help_text);
            textFunction.setText(strText2);
            TextView textFunction2 = findViewById(R.id.user_function_text);
            textFunction2.setText(strText3);
        } else if (pageType == 3) {
            setContentView(R.layout.activity_user_help_layout);
            TextView textHelp1 = findViewById(R.id.user_help_text);
            textHelp1.setText(strText4);
        } else if (pageType == 4) {
            setContentView(R.layout.activity_user_help_layout);
            TextView textHelp1 = findViewById(R.id.user_help_text);
            textHelp1.setText(strText5);
        } else if (pageType == 5) {
            setContentView(R.layout.activity_user_help_layout);
            TextView textHelp1 = findViewById(R.id.user_help_text);
            textHelp1.setText(strText6);
        } else if (pageType == 6) {
            setContentView(R.layout.activity_user_help3_layout);
            TextView textFunction = findViewById(R.id.user_help_text);
            textFunction.setText(strText8);
            TextView textFunction2 = findViewById(R.id.user_function_text);
            textFunction2.setText(strText7);
        } else if (pageType == 7) {
            setContentView(R.layout.activity_user_help3_layout);
            TextView textFunction = findViewById(R.id.user_help_text);
            textFunction.setText(strText10);
            TextView textFunction2 = findViewById(R.id.user_function_text);
            textFunction2.setText(strText9);
        } else if (pageType == 8) {
            setContentView(R.layout.activity_user_help_layout);
            TextView textHelp1 = findViewById(R.id.user_help_text);
            textHelp1.setText(strText11);
        } else if (pageType == 9) {
            setContentView(R.layout.activity_user_help_layout);
            TextView textHelp1 = findViewById(R.id.user_help_text);
            textHelp1.setText(strText12);
        } else if (pageType == 10) {
            setContentView(R.layout.activity_user_help2_layout);
        }
        finishOnClick();
    }

    public static void openActivity(Context context, int pageType) {
        Bundle bundle = new Bundle();
        bundle.putInt("pageType", pageType);
        JumpIntent.jump((Activity) context, TeacherUserHelpActivity.class, bundle);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            default:
                break;
        }
    }

    private void finishOnClick() {
        if (pageType == 1 || pageType == 3 || pageType == 4 || pageType == 5 || pageType == 8 || pageType == 9) {
            ConstraintLayout userMain = findViewById(R.id.user_help_main);
            userMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else if (pageType == 2 || pageType == 6 || pageType == 7) {
            ConstraintLayout userMain3 = findViewById(R.id.user_help_main3);
            userMain3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else if (pageType == 10) {
            ConstraintLayout userMain2 = findViewById(R.id.user_help_main2);
            userMain2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}
