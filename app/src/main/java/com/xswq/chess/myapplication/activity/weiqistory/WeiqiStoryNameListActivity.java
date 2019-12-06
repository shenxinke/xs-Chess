package com.xswq.chess.myapplication.activity.weiqistory;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.adapter.WeiQiListAdapter;
import com.xswq.chess.myapplication.base.BaseActivity;
import com.xswq.chess.myapplication.bean.WeiQiStoryListBean;

import java.io.Serializable;
import java.util.List;

public class WeiqiStoryNameListActivity extends BaseActivity {
    private List<WeiQiStoryListBean.DataBean.ListBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_qi_story_name_layout);
        getWindow().getDecorView().setBackgroundResource(R.color.half);
        ListView listView = findViewById(R.id.list_view);
        Button button = findViewById(R.id.button);
        list = (List<WeiQiStoryListBean.DataBean.ListBean>) getIntent().getSerializableExtra("List");
        WeiQiListAdapter weiQiListAdapter = new WeiQiListAdapter(WeiqiStoryNameListActivity.this, list);
        listView.setAdapter(weiQiListAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                finish();
                WeiqiStoryActivity.instance.finish();
                Intent intent = new Intent(WeiqiStoryNameListActivity.this, WeiqiStoryActivity.class);
                intent.putExtra("Position", position);
                intent.putExtra("List", (Serializable) list);
                startActivity(intent);
            }
        });
    }
}
