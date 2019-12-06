package com.xswq.chess.myapplication.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseFragment;

public class MessagerFragment extends BaseFragment {
    private View mView;
    private ImageView imageView;
    private TextView textView;

    @Override
    protected int setContentView() {
        return getLayout();
    }

    @Override
    protected void startLoad() {

    }

    @Override
    protected void initData() {
        if (mView == null) {
            mView = getContentView();
        }
        imageView = mView.findViewById(R.id.image_message);
        textView = mView.findViewById(R.id.text_message);
    }

    @Override
    protected void stopLoad() {

    }

    private int getLayout() {
        return R.layout.system_messager_layout;
    }

}
