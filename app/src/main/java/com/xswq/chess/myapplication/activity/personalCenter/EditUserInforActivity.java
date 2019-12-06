package com.xswq.chess.myapplication.activity.personalCenter;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.config.RxCode;
import com.xswq.chess.myapplication.customview.MaterialSpinner;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.PersonalEdituserinforAPI;
import com.xswq.chess.myapplication.http.retrofitHttp.RetrofitManager;
import com.xswq.chess.myapplication.http.subscriber.PersonalEdituserinforSub;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.Util;
import com.xswq.chess.myapplication.widget.CustomDatePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import gorden.rxbus2.RxBus;

public class EditUserInforActivity extends BaseCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, HttpCallBackLisener {
    private EditText personal_user;
    private TextView personal_birthdaystring;
    private RadioButton radio1;
    private RadioButton radio2;
    private MaterialSpinner spinner1;
    private MaterialSpinner spinner2;

    private String[] allProv;//所有的省
    private String[] cities;//所有的市
    //省市区的集合
    private Map<String, String[]> cityMap = new HashMap<>();//key:省p---value:市n  value是一个集合

    private String provinceName;//省的名字
    private String cityName;//城市的名字
    private CustomDatePicker mCustomDatePicker;
    private PersonalEdituserinforAPI mPersonalUserInforApi;
    private PersonalEdituserinforSub mPersonalUserInforSub;
    private int sex;
    private String barthDay;
    private String address;
    private String orgName;
    private String phone;

    @Override
    protected int getLayoutId() {
        return R.layout.personal_edituserinfor_layout;
    }

    @Override
    protected void initView() {
        barthDay = SPUtil.getString("birthday", "");
        sex = SPUtil.getInt("sex", 0);
        address = SPUtil.getString("address", "");
        orgName = SPUtil.getString("orgName", "");
        phone = SPUtil.getString("mobile", "");
        init();
    }

    @Override
    protected void loadData() {

    }

    private void init() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.personal_bg);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setOnClickListener(this);
        login_back.setVisibility(View.VISIBLE);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("信息修改");
        TextView login_queding = findViewById(R.id.login_queding);
        login_queding.setText("确定");
        login_queding.setVisibility(View.VISIBLE);
        login_queding.setOnClickListener(this);

        personal_user = findViewById(R.id.personal_user);

        // 控制输入框最多输入10个字符长度（五个汉字）
        personal_user.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                // 输入内容是否超过设定值，最多输入五个汉字10个字符
                if (Util.getTextLength(dest.toString()) + Util.getTextLength(source.toString()) > 10) {
                    // 输入框内已经有10个字符则返回空字符
                    if (Util.getTextLength(dest.toString()) >= 10) {
                        return "";
                        // 如果输入框内没有字符，且输入的超过了10个字符，则截取前五个汉字
                    } else if (Util.getTextLength(dest.toString()) == 0) {
                        return source.toString().substring(0, 5);
                    } else {
                        // 输入框已有的字符数为双数还是单数
                        if (Util.getTextLength(dest.toString()) % 2 == 0) {
                            return source.toString().substring(0, 5 - (Util.getTextLength(dest.toString()) / 2));
                        } else {
                            return source.toString().substring(0, 5 - (Util.getTextLength(dest.toString()) / 2 + 1));
                        }
                    }
                }
                return null;
            }
        }});

        personal_user.setText(Util.signString(username));
        personal_birthdaystring = findViewById(R.id.personal_birthdaystring);

        hidden(personal_birthdaystring);
        personal_birthdaystring.setOnClickListener(this);
        initDatePicker();
        if (barthDay != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            Date date = new Date(Long.valueOf(barthDay));
            String format = sdf.format(date);
            personal_birthdaystring.setText(format);
        }
        RadioGroup mRadioGroup1 = findViewById(R.id.radioGroup1);
        mRadioGroup1.setOnCheckedChangeListener(this);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        if (sex == 0) {
            radio1.setChecked(true);
        } else {
            radio2.setChecked(true);
        }
        spinner1 = findViewById(R.id.spinner1);
        spinner1.setPadding(Util.dip2px(EditUserInforActivity.this, 12), 0,
                Util.dip2px(EditUserInforActivity.this, 10), 0);
        spinner2 = findViewById(R.id.spinner2);
        spinner2.setPadding(Util.dip2px(EditUserInforActivity.this, 12), 0,
                Util.dip2px(EditUserInforActivity.this, 10), 0);
        TextView personal_mechanismtext = findViewById(R.id.personal_mechanismtext);
        personal_mechanismtext.setText(Util.signString(orgName));
        TextView personal_phonestring = findViewById(R.id.personal_phonestring);
        personal_phonestring.setText(Util.signString(phone));
        initJsonCityData();
        setSpinnerData();
        getRequstInfor();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.personal_birthdaystring:
                mCustomDatePicker.show(personal_birthdaystring.getText().toString());
                break;

            case R.id.login_queding:
                String personalUser = personal_user.getText().toString().trim();
                if (!TextUtils.isEmpty(personalUser)) {
                    mPersonalUserInforApi.setUserId(userId);
                    mPersonalUserInforApi.setUsername(personalUser);
                    if (radio1.isChecked()) {
                        mPersonalUserInforApi.setSex(0);
                    } else {
                        mPersonalUserInforApi.setSex(1);
                    }
                    String birthDay = personal_birthdaystring.getText().toString().trim();
                    String provinceName = spinner1.getText().toString().trim();
                    String cityName = spinner2.getText().toString().trim();
                    mPersonalUserInforApi.setBirthday(birthDay);
                    mPersonalUserInforApi.setAddress(provinceName + ":" + cityName);
                    mPersonalUserInforApi.setUid(userId);
                    mPersonalUserInforApi.setToken(token);
                    RetrofitManager.getRetrofitInstance().handleHttp(mPersonalUserInforSub, mPersonalUserInforApi);
                } else {
                    Toast.makeText(EditUserInforActivity.this, "用户名不允许为空", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int id = group.getCheckedRadioButtonId();
        switch (id) {
            case R.id.radio1:
                radio1.getText();
                break;
            case R.id.radio2:
                radio2.getText();
                break;
            default:
                break;
        }
    }

    private void getRequstInfor() {
        mPersonalUserInforApi = new PersonalEdituserinforAPI(EditUserInforActivity.this);
        mPersonalUserInforApi.setMethod(ContactUrl.UPDATEMYUSERINFO);
        mPersonalUserInforSub = new PersonalEdituserinforSub(mPersonalUserInforApi);

    }

    /**
     * 从assert文件夹中获取json数据用于显示
     */
    private void initJsonCityData() {
        //全局的jsonObject
        //把全国的省市区的信息以json的格式保存，解析完成后赋值为null
        JSONObject jsonObject;
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = getAssets().open("province_data.json");//打开json数据
            byte[] by = new byte[is.available()];//转字节
            int len = -1;
            while ((len = is.read(by)) != -1) {
                sb.append(new String(by, 0, len, StandardCharsets.UTF_8));//根据字节长度设置编码
            }
            is.close();// 关闭流
            jsonObject = new JSONObject(sb.toString());//为json赋值

            JSONArray jsonArray = jsonObject.getJSONArray("citylist");//获取整个json数据
            allProv = new String[jsonArray.length()];//封装数据
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonP = jsonArray.getJSONObject(i);//jsonArray转jsonObject
                String provStr = jsonP.getString("p");//获取所有的省
                allProv[i] = provStr;//封装所有的省
                JSONArray jsonCity;
                try {
                    jsonCity = jsonP.getJSONArray("c");//在所有的省中取出所有的市，转jsonArray
                } catch (Exception e) {
                    continue;
                }

                //所有的市
                //所有市的长度
                String[] allCity = new String[jsonCity.length()];
                for (int c = 0; c < jsonCity.length(); c++) {
                    JSONObject jsonCy = jsonCity.getJSONObject(c);//转jsonObject
                    String cityStr = jsonCy.getString("n");//取出所有的市
                    allCity[c] = cityStr;//封装市集合
                }
                cityMap.put(provStr, allCity);//某个省取出所有的市,
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * spinner设置值（默认设置值）
     */
    private void setSpinnerData() {
        for (String s : allProv) {
            spinner1.setItem(s);
        }
        if (!TextUtils.isEmpty(address)) {
            updateCityAndArea(address.split(":")[0]);
            try {
                for (String s : allProv) {
                    if (address.split(":")[0].equals(s)) {
                        provinceName = s;
                        spinner1.setText(provinceName);// 默认选中项
                        break;
                    }
                }
                for (String city : cities) {
                    if (address.split(":")[1].equals(city)) {
                        cityName = city;
                        spinner2.setText(cityName);// 默认选中项
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setListener();
    }

    //设置spinner的点击监听
    private void setListener() {
        // 设置省市区的适配器，进行动态设置
        spinner1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                provinceName = allProv[position];
                if (!TextUtils.isEmpty(provinceName)) {
                    updateCityAndArea(provinceName);
                }
            }
        });
        spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                cityName = cities[position];
            }
        });
    }

    /**
     * 根据当前的省，更新市和区的信息
     */
    private void updateCityAndArea(String object) {
        cities = cityMap.get(object);
        spinner2.notifyData();
        if (cities != null) {
            for (String city : cities) {
                spinner2.setItem(city);
            }
        }
    }


    public void hidden(View mView) {
        if (mView != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mView.getWindowToken(), 0);
        }
    }

    //日历控件调用
    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        personal_birthdaystring.setText(now.split(" ")[0]);

        mCustomDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                personal_birthdaystring.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        mCustomDatePicker.showSpecificTime(false); // 不显示时和分
        mCustomDatePicker.setIsLoop(false); // 不允许循环滚动
    }

    @Override
    public void onNext(Object error, String method) {
        try {
            if (((JSONObject) error).getInt("returnCode") == 0) {
                Toast.makeText(this, "修改信息成功！", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "修改信息失败！", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {

    }
}
