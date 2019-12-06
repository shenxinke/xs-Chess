package com.xswq.chess.myapplication.activity.courseware;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.ExplanationAdapter;
import com.xswq.chess.myapplication.androidtojs.JsWebViewActivity;
import com.xswq.chess.myapplication.base.BaseCompatActivity;
import com.xswq.chess.myapplication.bean.ExplanationSnapshot;
import com.xswq.chess.myapplication.http.HttpCallBackLisener;
import com.xswq.chess.myapplication.http.httpparameterapi.ExplanationPatternApi;
import com.xswq.chess.myapplication.http.retrofitHttp.RetrofitManager;
import com.xswq.chess.myapplication.http.subscriber.ExplanationPatternSub;
import com.xswq.chess.myapplication.utils.ContactUrl;
import com.xswq.chess.myapplication.utils.StatisticsUtil;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ExplanationActivity extends BaseCompatActivity implements View.OnClickListener,HttpCallBackLisener{

    private int class_id;
    private String titleResource;
    private RecyclerView mEasyRecyclerView;
    private List<ExplanationSnapshot> listExplanationSnapshot;
    private ExplanationAdapter mExplanationAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.explanation_layout;
    }

    @Override
    protected void initView() {
        Intent mIntent = getIntent();
        class_id = mIntent.getIntExtra("classId",0);
        titleResource = mIntent.getStringExtra("title");
        StatisticsUtil.getStatistics(token, userId, 10,ExplanationActivity.this);
        getWindow().getDecorView().setBackgroundResource(R.mipmap.courseware_bg);
        TextView login_back =  findViewById(R.id.login_back);
        login_back.setVisibility(View.VISIBLE);
        login_back.setOnClickListener(this);
        TextView login_titles =  findViewById(R.id.login_titles);
        login_titles.setText(titleResource);
        mEasyRecyclerView =  findViewById(R.id.explanation_view);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(ExplanationActivity.this, 3);
        mEasyRecyclerView.setLayoutManager(mGridLayoutManager);
        mExplanationAdapter = new ExplanationAdapter(ExplanationActivity.this);
        getRequestParamer();
        mExplanationAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent jsWebIntent = new Intent(ExplanationActivity.this, JsWebViewActivity.class);
                jsWebIntent.putExtra("class_id",class_id);
                jsWebIntent.putExtra("title",titleResource);
                jsWebIntent.putExtra("chessid",listExplanationSnapshot.get(position).getChessid());
                jsWebIntent.putExtra("index",Integer.parseInt(listExplanationSnapshot.get(position).getIx()));
                jsWebIntent.putExtra("prefix",ContactUrl.COURSEWARETEACH);
                startActivity(jsWebIntent);
            }
        });
    }

    @Override
    protected void loadData() {

    }

    private void getRequestParamer(){
        ExplanationPatternApi mExplanationPatternApi = new ExplanationPatternApi(ExplanationActivity.this);
        mExplanationPatternApi.setMethod(ContactUrl.QUESTIONBANKBYCLASSID);
        mExplanationPatternApi.setClassid(String.valueOf(class_id));
        mExplanationPatternApi.setTitleResource("1");
        mExplanationPatternApi.setToken(token);
        mExplanationPatternApi.setUid(userId);
        ExplanationPatternSub mExplanationPatternSub = new ExplanationPatternSub(mExplanationPatternApi);
        RetrofitManager.getRetrofitInstance().handleHttp(mExplanationPatternSub, mExplanationPatternApi);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_back:
                finish();
            break;
        }
    }

    @Override
    public void onNext(Object t, String method) {
        try {
            JSONObject error = ((JSONObject) t).getJSONObject("error");
            if(error.optInt("returncode")==0){
                JSONObject data = ((JSONObject) t).getJSONObject("data");
                int total = data.optInt("total");
                if(total!=0){
                    JSONArray listSnapshot = data.getJSONArray("list");
                    listExplanationSnapshot =  new ArrayList<>();
                    for(int i=0;i<listSnapshot.length();i++){
                        ExplanationSnapshot mSnapshot = new ExplanationSnapshot();
                        mSnapshot.setIx(new JSONObject(listSnapshot.get(i).toString()).optString("ix"));
                        mSnapshot.setSnapshotImage(new JSONObject(listSnapshot.get(i).toString()).optString("snapshot"));
                        mSnapshot.setChessid(new JSONObject(listSnapshot.get(i).toString()).optString("id"));
                        listExplanationSnapshot.add(mSnapshot);
                    }
                    mEasyRecyclerView.setAdapter(mExplanationAdapter);
                    mExplanationAdapter.removeAll();
                    mExplanationAdapter.addAll(listExplanationSnapshot);
                }
            }else if(error.optInt("returncode")==10048){//用户过期
                quiteApp(this,error.optString("returnMessage"));
                finish();
            }
        }catch (Exception e){
          e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
      e.printStackTrace();
    }
}
