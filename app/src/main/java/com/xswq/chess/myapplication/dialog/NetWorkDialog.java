package com.xswq.chess.myapplication.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.utils.SPUtil;
import com.xswq.chess.myapplication.utils.Util;

/**
 * 网络状态弹框提示
 */
public abstract class NetWorkDialog {
    protected Dialog dialog;
    private TextView title;
    private Button sure;
    private Button cancle;
    private CheckBox checkbox;
    private TextView text;
    private Activity context;
    private String versionContent;

    public NetWorkDialog(Activity activity, String versionContent) {
        this.context = activity;
        this.versionContent = versionContent;
        if (dialog == null) {
            getDialog(activity);
        }
    }

    private Dialog getDialog(Activity activity) {
        View view = activity.getLayoutInflater().inflate(R.layout.dialog_net_work, null);
        dialog = new Dialog(activity, R.style.prompt_progress_dailog_dimenabled);

        dialog.setContentView(view, new LinearLayout.LayoutParams
                (Util.dip2px(activity, 320), Util.dip2px(activity, 300)));
        title = view.findViewById(R.id.txt_msg);
        sure = view.findViewById(R.id.but_dialog_ok);
        cancle = view.findViewById(R.id.but_dialog_cancle);
        checkbox = view.findViewById(R.id.checkbox);
        text = view.findViewById(R.id.text);
        title.setText(versionContent);

        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        checkbox.setVisibility(View.VISIBLE);
        text.setVisibility(View.VISIBLE);
        sure.setVisibility(View.VISIBLE);
        cancle.setVisibility(View.VISIBLE);

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SPUtil.put("netWork", false);
                } else {
                    SPUtil.put("netWork", true);
                }
            }
        });
        Window dialogWindow = dialog.getWindow();
        android.view.WindowManager.LayoutParams lp = dialogWindow
                .getAttributes();
        // 设置生效
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(lp);


        sure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                onSureClick();
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                onFalseClick();
            }
        });
        return dialog;
    }

    /**
     * 确定点击事件
     */
    public abstract void onSureClick();

    /**
     * 取消点击事件
     */
    public abstract void onFalseClick();

    public void showDialog() {
        dialog.show();
    }

    public void dismissDialog() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}
