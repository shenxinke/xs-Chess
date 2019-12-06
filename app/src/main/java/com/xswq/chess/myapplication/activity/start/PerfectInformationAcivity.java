package com.xswq.chess.myapplication.activity.start;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.activity.main.SwitchMainActivity;
import com.xswq.chess.myapplication.application.XSWQApplication;
import com.xswq.chess.myapplication.base.BaseBean;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.base.BaseListAdapter;
import com.xswq.chess.myapplication.base.BaseListViewHolder;
import com.xswq.chess.myapplication.bean.AllUserOrgnoBean;
import com.xswq.chess.myapplication.bean.SelectAllOrgnoBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.customview.MaterialSpinner;
import com.xswq.chess.myapplication.greendao.entity.Base;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.CreateClassApi;
import com.xswq.chess.myapplication.http.retrofitHttp.RetrofitManager;
import com.xswq.chess.myapplication.http.subscriber.CreateClassSub;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.GsonUtil;
import com.xswq.chess.myapplication.utils.ToastUtils;
import com.xswq.chess.myapplication.utils.Util;
import com.xswq.chess.myapplication.widget.CustomDatePicker;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.Call;

/**
 * 完善信息页面
 */
public class PerfectInformationAcivity extends BaseCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, HttpCallBackLisener {

    private EditText prefect_username;
    private TextView prefect_birthdaystring;
    private MaterialSpinner prefect_spinner1;
    private MaterialSpinner prefect_spinner2;
    private MaterialSpinner prefect_teacher;
    private MaterialSpinner prefect_class;
    private EditText prefect_text_garden;
    private List<String> allProv = new ArrayList<>();//所有的省
    //省市区的集合
    private Map<String, String[]> cityMap = new HashMap<String, String[]>();//key:省p---value:市n  value是一个集合
    private String provinceName;//省的名字
    private String cityName;//城市的名字
    private String teacher_id;
    private String sexString = "0";
    private String classInfold;
    private CustomDatePicker mCustomDatePicker;
    private ListView list_view;
    private List<SelectAllOrgnoBean.DataBean> listdData;
    private List<SelectAllOrgnoBean.DataBean> newListData = new ArrayList<>();
    Handler handler = new Handler();
    private PopupWindow popupWindow;
    private BaseListAdapter baseListAdapter;
    private boolean isSelect = false;
    private List<AllUserOrgnoBean.DataBean> mListJson;
    private String orgNoId;

    @Override
    protected int getLayoutId() {
        return R.layout.perfect_information_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.login_bg);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("完善信息");
        //内容控件
        prefect_username = findViewById(R.id.prefect_username);
        // 控制输入框最多输入10个字符长度（五个汉字）
        prefect_username.setFilters(new InputFilter[]{new InputFilter() {
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

        prefect_birthdaystring = findViewById(R.id.prefect_birthdaystring);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(prefect_birthdaystring.getWindowToken(), 0);
        prefect_birthdaystring.setOnClickListener(this);

        initDatePicker();
        RadioGroup prefect_radioGroup1 = findViewById(R.id.prefect_radioGroup1);
        prefect_radioGroup1.setOnCheckedChangeListener(this);
        RadioButton prefect_radio1 = findViewById(R.id.prefect_radio1);
        RadioButton prefect_radio2 = findViewById(R.id.prefect_radio2);
        prefect_spinner1 = findViewById(R.id.prefect_spinner1);
        prefect_spinner2 = findViewById(R.id.prefect_spinner2);

        prefect_teacher = findViewById(R.id.prefect_teacher);
        prefect_class = findViewById(R.id.prefect_class);
        Button prefect_button = findViewById(R.id.prefect_button);
        prefect_button.setOnClickListener(this);

        prefect_text_garden = findViewById(R.id.prefect_text_garden);

        prefect_text_garden.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }//文本改变之前执行

            @Override
            //文本改变的时候执行
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                handler.post(runnable);
                isSelect = false;
            }//文本改变之后执行
        });
    }

    @Override
    protected void loadData() {
        initJsonCityData();
        getTheGarden();
    }

    //从后台获取相应老师的班级信息
    private void getClassInfor() {
        if (teacher_id == null || teacher_id.equals("")) {
            Toast.makeText(this, "请先选择老师！", Toast.LENGTH_LONG).show();
        } else {
            CreateClassApi mCreateClassApi = new CreateClassApi(this);
            mCreateClassApi.setMethod(ContactUrl.TEACHERCLASS);
            //处理事件的代码
            mCreateClassApi.setTeacherId(teacher_id);
            mCreateClassApi.setUid(userId);
            mCreateClassApi.setToken(token);
            CreateClassSub mCreateClassSub = new CreateClassSub(mCreateClassApi);
            RetrofitManager.getRetrofitInstance().handleHttp(mCreateClassSub, mCreateClassApi);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.prefect_birthdaystring:
                mCustomDatePicker.show(prefect_birthdaystring.getText().toString());
                break;
            case R.id.prefect_button:
                String userName = prefect_username.getText().toString().trim();
                String birthdays = prefect_birthdaystring.getText().toString().trim();
                if (TextUtils.isEmpty(userName)) {
                    ToastUtils.show("用户名不能为空!");
                } else {
                    showProgressDialog(true);
                    getUpdataPerfect(userName, birthdays);
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
            case R.id.prefect_radio1:
                sexString = "0";
                break;
            case R.id.prefect_radio2:
                sexString = "1";
                break;
            default:
                break;
        }
    }

    /**
     * 查询到的老师信息
     */
    private void assignmentTeacherInfor(final List<AllUserOrgnoBean.DataBean> mListJson) {
        for (int i = 0; i < mListJson.size(); i++) {
            prefect_teacher.setItem(mListJson.get(i).getUserName());
        }
        if (mListJson.size() > 0) {
            teacher_id = mListJson.get(0).getID();
            getClassInfor();
        }
        prefect_teacher.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                teacher_id = mListJson.get(position).getID();
                getClassInfor();
            }
        });
    }

    private void assignmentClassIdInfor(final List<Base> mListJsonClass) {
        if (mListJsonClass.size() <= 0) {
            prefect_class.notifyData();
        } else {
            classInfold = mListJsonClass.get(0).getId();
            /**
             * 显示班级列表
             */
            for (int i = 0; i < mListJsonClass.size(); i++) {
                prefect_class.setItem(mListJsonClass.get(i).getClassName());
            }
            prefect_class.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
                @Override
                public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    classInfold = mListJsonClass.get(position).getClassInfoId();
                }
            });
        }
    }

    @Override
    public void onNext(Object jsonString, String method) {
        try {
            if (method.equals(ContactUrl.TEACHERCLASS)) {
                Gson mGson = new Gson();
                List<Base> mListJsonClass = new ArrayList<Base>();
                JSONArray mJarray = (JSONArray) jsonString;
                for (int i = 0; i < mJarray.length(); i++) {
                    JSONObject obj = (JSONObject) mJarray.get(i);
                    if (obj != null) {
                        Base mBase = mGson.fromJson(String.valueOf(obj), Base.class);
                        mListJsonClass.add(mBase);
                    }
                }
                assignmentClassIdInfor(mListJsonClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {

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
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonP = jsonArray.getJSONObject(i);//jsonArray转jsonObject
                String provStr = jsonP.getString("p");//获取所有的省
                allProv.add(provStr);
                JSONArray jsonCity;
                try {
                    jsonCity = jsonP.getJSONArray("c");//在所有的省中取出所有的市，转jsonArray
                } catch (Exception e) {
                    continue;
                }

                //所有的市
                String[] allCity = new String[jsonCity.length()];//所有市的长度
                for (int c = 0; c < jsonCity.length(); c++) {

                    JSONObject jsonCy = jsonCity.getJSONObject(c);//转jsonObject
                    String cityStr = jsonCy.getString("n");//取出所有的市
                    allCity[c] = cityStr;//封装市集合
                }
                cityMap.put(provStr, allCity);//某个省取出所有的市,
            }
            setSpinnerData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jsonObject = null;//清空所有的数据

        }
    }

    /**
     * spinner设置值（默认设置值）
     */
    private void setSpinnerData() {
        if (allProv.size() > 0) {
            provinceName = allProv.get(0);
            prefect_spinner1.setItems(allProv);//设置省
            //省
            prefect_spinner1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
                @Override
                public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    provinceName = item;
                    updateCityAndArea(item);
                }
            });
            //市
            prefect_spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
                @Override
                public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    cityName = item;
                }
            });
            updateCityAndArea(provinceName);
        }
    }

    /**
     * 根据当前的省，更新市和区的信息
     */
    private void updateCityAndArea(String object) {
        String[] cities = cityMap.get(object);
        List<String> cityList = new ArrayList<>();
        if (cities.length > 0) {
            cityName = cities[0];
            for (int i = 0; i < cities.length; i++) {
                cityList.add(cities[i]);
            }
            prefect_spinner2.setItems(cityList);//添加列表“市”
        }
    }

    //日历控件调用
    private void initDatePicker() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        prefect_birthdaystring.setText(now.split(" ")[0]);
        mCustomDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                prefect_birthdaystring.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        mCustomDatePicker.showSpecificTime(false); // 不显示时和分
        mCustomDatePicker.setIsLoop(false); // 不允许循环滚动

    }

    //显示提示框
    private void showListPopWindow() {
        final View layout = LayoutInflater.from(PerfectInformationAcivity.this).inflate(R.layout.perfect_information_pop_layout, null, false);
        popupWindow = new PopupWindow(layout, prefect_text_garden.getWidth(), prefect_text_garden.getWidth(), false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setClippingEnabled(false);
        ColorDrawable dw = new ColorDrawable(0xff);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        list_view = layout.findViewById(R.id.list_view);
        if (listdData.size() > 0) {
            initShareInfo();
        }
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prefect_text_garden.setText(listdData.get(position).getCustomerName());
                orgNoId = listdData.get(position).getID();
                isSelect = true;
                popupWindow.dismiss();
                if (!TextUtils.isEmpty(orgNoId)) {
                    getTeacherInfor();
                }
            }
        });
    }

    private void initShareInfo() {
        baseListAdapter = new BaseListAdapter<SelectAllOrgnoBean.DataBean>(XSWQApplication.getInstance(), listdData, R.layout.perfect_information_item_layout) {
            @Override
            public void convert(BaseListViewHolder holder, int position, SelectAllOrgnoBean.DataBean item) {
                holder.setText(R.id.text_name, item.getCustomerName());
            }
        };
        list_view.setAdapter(baseListAdapter);
    }

    private void getTheGarden() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_SELECT_ALL_ORGNO)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    SelectAllOrgnoBean selectAllOrgnoBean = GsonUtil.gsonToBean(response, SelectAllOrgnoBean.class, PerfectInformationAcivity.this);
                    if (selectAllOrgnoBean != null) {
                        listdData = selectAllOrgnoBean.getData();
                        newListData.addAll(listdData);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            String data = prefect_text_garden.getText().toString();
            if (prefect_text_garden.getText().length() == 0)
                return;
            if (popupWindow != null && !popupWindow.isShowing() && !isSelect) {
                popupWindow.showAsDropDown(prefect_text_garden, 0, -3);
            }
            if (popupWindow == null && listdData != null) {
                showListPopWindow();
                popupWindow.showAsDropDown(prefect_text_garden, 0, -3);
            }
            listdData.clear();//先要清空，不然会叠加
            getNewData(newListData.size(), data, newListData);//获取更新数据
            if (baseListAdapter != null) {
                baseListAdapter.notifyDataSetChanged();//更新
            }
        }
    };

    private void getNewData(int size, String value, List<SelectAllOrgnoBean.DataBean> newListData) {
        if (size > 0) {
            for (int i = 0; i < newListData.size(); i++) {
                String customerName = newListData.get(i).getCustomerName();
                SelectAllOrgnoBean.DataBean dataBean = newListData.get(i);
                if (customerName.contains(value)) {
                    listdData.add(dataBean);
                }
            }
        }
    }

    //获取从后台老师的信息
    private void getTeacherInfor() {
        try {
            OkHttpUtils.post().url(ContactUrl.GET_ALL_USER_ORGNO)
                    .addParams("token", token)
                    .addParams("uid", userId)
                    .addParams("orgNo", orgNoId)
                    .build().execute(new StringCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                }

                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response)) {
                        AllUserOrgnoBean allUserOrgnoBean = GsonUtil.gsonToBean(response, AllUserOrgnoBean.class, PerfectInformationAcivity.this);
                        if (allUserOrgnoBean != null) {
                            mListJson = allUserOrgnoBean.getData();
                            if (mListJson != null && mListJson.size() > 0) {
                                assignmentTeacherInfor(mListJson);
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //更新用户信息
    private void getUpdataPerfect(String username, String birthdays) {
        try {
            PostFormBuilder post = OkHttpUtils.post();
            post.url(ContactUrl.UPDATAMYUSERINFO_PATH);
            post.addParams("token", token);
            post.addParams("uid", userId);
            post.addParams("id", userId);
            post.addParams("username", username);
            post.addParams("sex", sexString);
            post.addParams("birthday", birthdays);
            post.addParams("address", provinceName + ":" + cityName);
            if (!TextUtils.isEmpty(orgNoId)) {
                post.addParams("orgNo", orgNoId);
            } else {
                post.addParams("orgNo", "");
            }
            if (!TextUtils.isEmpty(classInfold)) {
                post.addParams("classInfoId", classInfold);
            } else {
                post.addParams("classInfoId", "");
            }
            post.build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    ToastUtils.show(Const.CONS_STR_ERROR);
                    dismissProgressDialog();
                }

                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response)) {
                        BaseBean baseBean = GsonUtil.gsonToBean(response, BaseBean.class, PerfectInformationAcivity.this);
                        if (baseBean != null) {
                            startActivity(new Intent(PerfectInformationAcivity.this, SwitchMainActivity.class));
                            finish();
                        }
                    }
                    dismissProgressDialog();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
