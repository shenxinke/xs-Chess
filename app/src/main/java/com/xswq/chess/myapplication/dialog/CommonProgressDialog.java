package com.xswq.chess.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.airbnb.lottie.LottieAnimationView;
import com.xswq.chess.myapplication.R;

/**
 * @author Shenxinke
 * @version 4.0.0
 * @data 2018/4/19
 */

public class CommonProgressDialog extends Dialog implements DialogInterface.OnDismissListener {
    private Context context;
    private static CommonProgressDialog dialog;
    private LottieAnimationView lottieAnimationView;


    public CommonProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        setOnDismissListener(this);
    }

    /**
     * 显示dialog的方法
     */
    public static CommonProgressDialog showDialog(Context context,boolean isCancelable) {
        //dialog样式
        dialog = new CommonProgressDialog(context, R.style.MyDialog);
        //dialog布局文件
        dialog.setContentView(R.layout.common_progress_dialog_layout);
        //点击外部不允许关闭dialog
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(isCancelable);
        return dialog;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && null != dialog) {
            lottieAnimationView = dialog.findViewById(R.id.lottie_loading);
            lottieAnimationView.setAnimation("my_loading.json");
            lottieAnimationView.loop(true);
            lottieAnimationView.setSpeed(2);
            lottieAnimationView.playAnimation();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        if (lottieAnimationView != null) {
            lottieAnimationView.cancelAnimation();
        }
    }
}
