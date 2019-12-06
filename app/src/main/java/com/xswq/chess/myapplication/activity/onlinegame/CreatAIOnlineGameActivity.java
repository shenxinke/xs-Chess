package com.xswq.chess.myapplication.activity.onlinegame;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.CreatAIOnlineGameSpinnerAdapter;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.bean.IsPlayingChessBean;
import com.xswq.chess.myapplication.customview.StringScrollPicker;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;

public class CreatAIOnlineGameActivity extends BaseActivity implements View.OnClickListener {
    private String[] rangChild = new String[]{"猜先", "执黑", "执白"};
    private String[] rangZi = new String[]{"让 2 子", "让 3 子", "让 4 子", "让 5 子", "让 6 子", "让 7 子", "让 8 子", "让 9 子"};
    private String[] time = new String[]{"20分"};
    private String[] second = new String[]{"30秒"};
    private String[] numberTimeout = new String[]{"3次"};
    private TextView spinner;
    private TextView spinner2;
    private TextView spinner3;
    private TextView spinner4;
    private TextView spinner5;
    private TextView textSon;
    private int type;
    private String name;
    private int stringSon;
    private PopupWindow popupWindow;
    private ImageView image5;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_ai_game_layout);
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
        initView();
        initDate();
    }

    private void initView() {
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        spinner = findViewById(R.id.president_text);
        spinner.setOnClickListener(this);
        spinner2 = findViewById(R.id.president_text2);
        spinner2.setOnClickListener(this);
        spinner3 = findViewById(R.id.president_text3);
        spinner3.setOnClickListener(this);
        spinner4 = findViewById(R.id.president_text4);
        spinner4.setOnClickListener(this);
        spinner5 = findViewById(R.id.president_text5);
        spinner5.setOnClickListener(this);
        textSon = findViewById(R.id.text_son);
        image5 = findViewById(R.id.image5);
        Button buttonYes = findViewById(R.id.button_yes);
        buttonYes.setOnClickListener(this);
        Button buttonSkip = findViewById(R.id.button_skip);
        buttonSkip.setOnClickListener(this);
    }

    private void initDate() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_yes:
                if (type == 1) {
                    getAIPlayChess(type);
                } else if (type == 2) {
                    getAIPlayChess(type);
                } else if (type == 3) {
                    getAIPlayChess(type);
                } else if (type == 4) {
                    getAIPlayChess(type);
                }
                break;
            case R.id.button_skip:
                finish();
                break;
            case R.id.president_text:
                showPopupWindow(1, rangChild);
                break;
            case R.id.president_text2:
                showPopupWindow(2, time);
                break;
            case R.id.president_text3:
                showPopupWindow(3, second);
                break;
            case R.id.president_text4:
                showPopupWindow(4, numberTimeout);
                break;
            case R.id.president_text5:
                showPopupWindow(5, rangZi);
                break;
            default:
                break;
        }
    }

    //创建棋局
    private void getAIPlayChess(int type) {
        name = spinner.getText().toString();
        try {
            GetBuilder getBuilder = OkHttpUtils.get();
            getBuilder.url(ContactUrl.GET_IS_PLAY_CHESS);
            getBuilder.addParams("token", token);
            getBuilder.addParams("uid", userId);
            if (type == 1) {
                getBuilder.addParams("ai_level", "15k");
            } else if (type == 2) {
                getBuilder.addParams("ai_level", "5k");
            } else if (type == 3) {
                getBuilder.addParams("ai_level", "2D");
            } else if (type == 4) {
                getBuilder.addParams("ai_level", "5D");
            }
            if ("执黑".equals(name)) {
                getBuilder.addParams("color", "black");
                getBuilder.addParams("count", String.valueOf(stringSon));
            } else if ("执白".equals(name)) {
                getBuilder.addParams("color", "white");
            } else {
                getBuilder.addParams("color", "");
            }
            getBuilder.addParams("size", "19");
            getBuilder.build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    Gson gson = new Gson();
                    Intent intent;
                    IsPlayingChessBean studentGuidanceBean = gson.fromJson(response, IsPlayingChessBean.class);
                    String returnCode = studentGuidanceBean.getError().getReturnCode();
                    if (returnCode.equals("0")) {
                        intent = new Intent(CreatAIOnlineGameActivity.this, JsWebViewActivity.class);
                        intent.putExtra("prefix", ContactUrl.PLAY);
                        intent.putExtra("AIplay", "2");
                        startActivity(intent);
                        finish();
                    } else if (returnCode.equals("10051")) {
                        intent = new Intent(CreatAIOnlineGameActivity.this, IncompleteChessGameActivity.class);
                        intent.putExtra("intenType", 2);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showPopupWindow(final int type, final String[] stringList) {
        View layout = LayoutInflater.from(CreatAIOnlineGameActivity.this).inflate(R.layout.activity_management_group_list_layout, null, false);
        popupWindow = new PopupWindow(layout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        View view = getWindow().getDecorView();
        popupWindow.setClippingEnabled(false);
        ColorDrawable dw = new ColorDrawable(0xff);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        final StringScrollPicker stringScrollPicker = layout.findViewById(R.id.string_scroll);
        Button buttonConfirm = layout.findViewById(R.id.button_confirm);
        Button buttonCancel = layout.findViewById(R.id.button_cancel);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button_cancel:
                        popupWindow.dismiss();
                        break;
                    case R.id.button_confirm:
                        int selectedPosition = stringScrollPicker.getSelectedPosition();
                        String itemString = stringList[selectedPosition];
                        switch (type) {
                            case 1:
                                spinner.setText(itemString);
                                if ("执黑".equals(itemString)) {
                                    textSon.setVisibility(View.VISIBLE);
                                    spinner5.setVisibility(View.VISIBLE);
                                    image5.setVisibility(View.VISIBLE);
                                    stringSon = 2;
                                } else {
                                    textSon.setVisibility(View.GONE);
                                    spinner5.setVisibility(View.GONE);
                                    image5.setVisibility(View.GONE);
                                }
                                break;
                            case 2:
                                spinner2.setText(itemString);
                                break;
                            case 3:
                                spinner3.setText(itemString);
                                break;
                            case 4:
                                spinner4.setText(itemString);
                                break;
                            case 5:
                                spinner5.setText(itemString);
                                stringSon = selectedPosition + 2;
                                break;
                            default:
                                break;
                        }
                        popupWindow.dismiss();
                        break;
                    default:
                        break;
                }
            }
        };
        buttonCancel.setOnClickListener(listener);
        buttonConfirm.setOnClickListener(listener);
        List<String> strings = new ArrayList<>(Arrays.asList(stringList));
        if (strings.size() > 0) {
            stringScrollPicker.setData(strings);
        }
    }

}
