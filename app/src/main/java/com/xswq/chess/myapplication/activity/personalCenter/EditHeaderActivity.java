package com.xswq.chess.myapplication.activity.personalCenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.EditHeadadapter;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.HeadImager;
import com.xswq.chess.myapplication.config.RxCode;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.PersonalEdituserinforAPI;
import com.xswq.chess.myapplication.http.retrofitHttp.RetrofitManager;
import com.xswq.chess.myapplication.http.subscriber.PersonalEdituserinforSub;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import gorden.rxbus2.RxBus;


public class EditHeaderActivity extends BaseCompatActivity implements HttpCallBackLisener,View.OnClickListener{

    private RecyclerView recyclerView;
    private PersonalEdituserinforAPI mPersonalEdituserinforApi;
    private PersonalEdituserinforSub mPersonalEdituserinforSub;
    private List<HeadImager> headingList;
    private int flag = -1;
    private View oldView;
    private GridLayoutManager mGridLayoutManager;
    boolean isfirst ;

    @Override
    protected int getLayoutId() {
        return R.layout.personal_editheader_layout;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setBackgroundResource(R.mipmap.personal_bg);
        TextView login_back = findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        TextView login_queding = findViewById(R.id.login_queding);
        login_queding.setText("确定");
        login_queding.setVisibility(View.VISIBLE);
        login_queding.setOnClickListener(this);
        TextView login_titles = findViewById(R.id.login_titles);
        login_titles.setText("修改头像");
        recyclerView =  findViewById(R.id.header_view);
        mGridLayoutManager = new GridLayoutManager(EditHeaderActivity.this,4);
        recyclerView.setLayoutManager(mGridLayoutManager);
        EditHeadadapter mEditHeadadapter = new EditHeadadapter(EditHeaderActivity.this);
        dealWithAdapter(mEditHeadadapter);
        headingList =  getHeadingPic(EditHeaderActivity.this);
        mEditHeadadapter.addAll(headingList);
    }

    @Override
    protected void loadData() {
        mPersonalEdituserinforApi = new PersonalEdituserinforAPI(EditHeaderActivity.this);
        mPersonalEdituserinforApi.setUserId(userId);
        mPersonalEdituserinforApi.setToken(token);
        mPersonalEdituserinforApi.setUid(userId);
        mPersonalEdituserinforSub = new PersonalEdituserinforSub(mPersonalEdituserinforApi);
    }

    private void dealWithAdapter(final EditHeadadapter adapter) {
       try {

           recyclerView.setAdapter(adapter);
           adapter.setOnItemClickListener(new EditHeadadapter.OnItemClickListener() {
               @SuppressLint("ResourceAsColor")
               @Override
               public void onItemClick(int position) {
                   
                   if (flag != position) {

                       View view = mGridLayoutManager.findViewByPosition(position);
                       ImageView mHeading =  view.findViewById(R.id.headings);
                       mHeading.setBackgroundResource(R.drawable.head_shange_bround_selector);
                       headingList.get(position).setSelected(true);
                       if(flag!=-1){
                           if(oldView!=null){
                               ImageView mHeading1 = oldView.findViewById(R.id.headings);
                               mHeading1.setBackgroundResource(R.color.transparent);
                               headingList.get(flag).setSelected(false);
                           }
                       }

                       mPersonalEdituserinforApi.setHeadImg("img/headImg/" + headingList.get(position).getImageHeading());
                       flag = position;
                       oldView = view;
                   } else {

                       View view = mGridLayoutManager.findViewByPosition(position);
                       ImageView mHeading =  view.findViewById(R.id.headings);
                       if(isfirst){
                           mHeading.setBackgroundResource(R.drawable.head_shange_bround_selector);
                           mPersonalEdituserinforApi.setHeadImg("img/headImg/" + headingList.get(position).getImageHeading());
                           headingList.get(position).setSelected(true);
                           isfirst = false;
                       }else{
                           mHeading.setBackgroundResource(R.color.transparent);
                           mPersonalEdituserinforApi.setHeadImg("");
                           headingList.get(position).setSelected(false);
                           isfirst = true;
                       }
                   }
               }
           });
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    //获取assets中的图片
    public List<HeadImager> getHeadingPic(Context context){
        List<HeadImager> pciPaths = null;
        AssetManager am = context.getAssets();
        String[] paths = null;
        try {
            paths = am.list("img/heading");  // ""获取所有,填入目录获取该目录下所有资源
            pciPaths = new ArrayList<>();
            for(String pathString:paths){
                HeadImager mHeadImager = new HeadImager();
                mHeadImager.setImageHeading(pathString);
                pciPaths.add(mHeadImager);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pciPaths;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login_back:
                 finish();
                break;
            case R.id.login_queding:
                try {
                    //调用接口
                    if(mPersonalEdituserinforApi.getHeadImg()!=null){
                        RetrofitManager.getRetrofitInstance().handleHttp(mPersonalEdituserinforSub,mPersonalEdituserinforApi);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                break;
        }
    }

    @Override
    public void onNext(Object error, String method) {
        try {
            if (((JSONObject)error).getInt("returnCode") == 0) {
                Toast.makeText(this, "修改头像成功！", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(this, "修改头像失败！", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {

    }
}
