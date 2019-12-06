package com.xswq.chess.myapplication.fragment;

import android.view.View;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.base.BaseFragment;

public class RadarFragment extends BaseFragment{

    private View mView;
    private WebView mWebView;

    @Override
    protected int setContentView() {
        return R.layout.persoanl_data_fragment_layout;
    }

    /**
     * fragment静态传值
     */
    public static RadarFragment newInstance() {
        return new RadarFragment();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void startLoad() {
        if (mView == null) {
            mView = getContentView();
        }

        mWebView = findViewById(R.id.leveltu_webview);
        mWebView.clearCache(true);
        mWebView.clearHistory();
        mWebView.setBackgroundColor(0);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);

        mWebView.loadUrl("file:///android_asset/echarts/persnal_ability_echar.html?uid=" + userId + "&token=" + token);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return false;
            }

            //网页加载错误是会走这里，做相应的错误处理
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }


            //页面加载完毕
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }

        });

    }


    @Override
    protected void stopLoad() {

    }


    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.clearCache(true); //清空缓存
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
