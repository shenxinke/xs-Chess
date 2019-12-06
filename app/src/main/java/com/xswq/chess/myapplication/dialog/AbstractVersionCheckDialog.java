package com.xswq.chess.myapplication.dialog;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
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

import pub.devrel.easypermissions.EasyPermissions;

/**
 * 版本更新弹框提示
 */
public abstract class AbstractVersionCheckDialog {
    protected Dialog dialog;
    private TextView title;
    private Button sure;
    private Button cancle;
    private Button down;
    private CheckBox checkbox;
    private TextView text;
    private Activity context;
    private boolean flag;
    private String versionContent;
    private int version;
    private String writePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    public AbstractVersionCheckDialog(Activity activity, boolean flag, String versionContent, int version) {
        this.context = activity;
        this.flag = flag;
        this.version = version;
        this.versionContent = versionContent;
        if (dialog == null) {
            getDialog(activity);
        }
    }

    private Dialog getDialog(Activity activity) {
        View view = activity.getLayoutInflater().inflate(R.layout.dialog_verson_update, null);
        dialog = new Dialog(activity, R.style.prompt_progress_dailog_dimenabled);

        dialog.setContentView(view, new LinearLayout.LayoutParams
                (Util.dip2px(activity, 320), Util.dip2px(activity, 300)));
        title = view.findViewById(R.id.txt_msg);
        sure = view.findViewById(R.id.but_dialog_ok);
        down = view.findViewById(R.id.but_dialog_down);
        cancle = view.findViewById(R.id.but_dialog_cancle);
        checkbox = view.findViewById(R.id.checkbox);
        text = view.findViewById(R.id.text);

        if (flag) {
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            checkbox.setVisibility(View.GONE);
            text.setVisibility(View.GONE);
            sure.setVisibility(View.GONE);
            cancle.setVisibility(View.GONE);
            down.setVisibility(View.VISIBLE);
        } else {
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
            down.setVisibility(View.GONE);
            checkbox.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);
            sure.setVisibility(View.VISIBLE);
            cancle.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(versionContent)) {
            String replace = versionContent.replace("&&", "\n");
            title.setText(Util.signString(replace));
        } else {
            title.setText("暂无介绍");
        }
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SPUtil.put("remember", version);
                } else {
                    SPUtil.put("remember", 0);
                }
            }
        });
        Window dialogWindow = dialog.getWindow();
        android.view.WindowManager.LayoutParams lp = dialogWindow
                .getAttributes();
        // 设置生效
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(lp);

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                onSureClick();
            }
        });

        sure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (EasyPermissions.hasPermissions(context, writePermission)) {
                    dialog.dismiss();
                }
                onSureClick();
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        return dialog;
    }

    /**
     * 确定点击事件
     */
    public abstract void onSureClick();

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
