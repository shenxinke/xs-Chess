package com.xswq.chess.myapplication.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xswq.chess.myapplication.R;
import com.xswq.chess.myapplication.bean.NewMobileVersionBean;
import com.xswq.chess.myapplication.config.Const;
import com.xswq.chess.myapplication.dialog.AbstractVersionCheckDialog;
import com.xswq.chess.myapplication.dialog.TipsDialog;
import com.itheima.updatelib.PatchUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import okhttp3.Call;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 版本更新
 */
public class NewMobileVersionUtils {
    private Context context;
    private int checkForUpdates;
    private TipsDialog tipsDialog;
    private TextView progressText;
    private ProgressBar progressBar;
    private String writePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static int loadingProgress;//下载进度数
    private Handler mHandler = new Handler();

    //checkForUpdates 0自动检查更新 1手动检查更新
    public NewMobileVersionUtils(final Activity context, int checkForUpdate) {
        this.context = context;
        this.checkForUpdates = checkForUpdate;
        final String userId = SPUtil.getString("uid", "");
        String token = SPUtil.getString("token", "");
        try {
            OkHttpUtils.post()
                    .url(ContactUrl.UPDATEVERSION_PATH)
                    .addParams("mobileType", "5")
                    .addParams("uid", userId)
                    .addParams("token", token)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            TextUtils.isEmpty(Const.CONS_STR_ERROR);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            NewMobileVersionBean newMobileVersionBean = GsonUtil.gsonToBean(response, NewMobileVersionBean.class, context);
                            if (newMobileVersionBean == null) return;
                            NewMobileVersionBean.DataBean data = newMobileVersionBean.getData();
                            if (data == null) return;
                            String updatUserId = data.getUserId();
                            int forcedUpdating = data.getForcedUpdating();//1强更 2弱更
                            String versionContent = data.getVersionContent();//更新介绍
                            int version = data.getVersion();//更新的版本
                            int incrementalUpdating = data.getIncrementalUpdating();//1增量更新 0全更
                            String versionName = VersionUtils.getVersionName(context);
                            versionName = versionName.replaceAll("\\.", "");
                            int appVersion = Integer.parseInt(versionName);//当前版本
                            if (appVersion < version) {//版本号更低
                                if (userId.equals(updatUserId) || Const.STR0.equals(updatUserId) || TextUtils.isEmpty(updatUserId)) {
                                    if (forcedUpdating == 1 && checkForUpdates == 0) {
                                        if (version - appVersion <= 5 && incrementalUpdating == 1) {//强更
                                            forcedUpdating(versionContent, versionName + "_" + version + ".patch");
                                        } else {
                                            forcedUpdating(versionContent, null);
                                        }
                                    } else {
                                        if (version - appVersion <= 5 && incrementalUpdating == 1) {//弱更
                                            weakMore(versionContent, versionName + "_" + version + ".patch", version);
                                        } else {
                                            weakMore(versionContent, null, version);
                                        }
                                    }
                                }
                            } else {
                                if (checkForUpdates == 1) {
                                    ToastUtils.show("当前为最新版本！");
                                }
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 强更
     */
    private void forcedUpdating(String versionContent, String path) {
        remindVersionUpdateDialog(true, versionContent, path, 0);
    }

    /**
     * 弱更
     */
    private void weakMore(String versionContent, String path, int version) {
        if (checkForUpdates == 1) {
            remindVersionUpdateDialog(false, versionContent, path, version);
        } else {
            int remember = SPUtil.getInt("remember", 0);
            if (remember < version) {
                remindVersionUpdateDialog(false, versionContent, path, version);
            }
        }
    }

    /**
     * 版本更新弹出框
     */
    private void remindVersionUpdateDialog(boolean flag, String versionContent, final String path, int isCompel) {
        if (tipsDialog == null) {
            AbstractVersionCheckDialog dialog = new AbstractVersionCheckDialog((Activity) context, flag, versionContent, isCompel) {
                @Override
                public void onSureClick() {
                    if (EasyPermissions.hasPermissions(context, writePermission)) {
                        setDialog((WindowUtils.getScreenWidth((Activity) context) / 6) * 5, path);
                    } else {
                        EasyPermissions.requestPermissions((Activity) context, "请打开存储权限", 200, writePermission);
                    }
                }
            };
            dialog.showDialog();
        }
    }

    /**
     * 下载进度条
     */
    private void setDialog(int width, String path) {
        tipsDialog = TipsDialog.creatTipsDialog(context, width, R.layout.dialog_tips_mid, Gravity.CENTER, 0);
        progressBar = tipsDialog.findViewById(R.id.down_pb);
        progressText = tipsDialog.findViewById(R.id.tvId);
        tipsDialog.setCancelable(false);
        tipsDialog.show();
        tipsDialog.setCanceledOnTouchOutside(false);
        //下载APK
        if (!TextUtils.isEmpty(path)) {
            uploadApkFile(ContactUrl.APK_INCREMENT_PATH + path, path, true);
        } else {
            uploadApkFile(ContactUrl.APK_PATH, ContactUrl.APP_NAME, false);
        }
    }

    /**
     * 下载APK文件
     */
    private void uploadApkFile(final String path, final String name, final boolean type) {
        OkHttpUtils.get().url(path)
                .tag(context)
                .build()
                .execute(new FileCallBack(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath(), name) {
                    @Override
                    public void inProgress(float v, long l, int id) {
                        loadingProgress = (int) (v * 100);
                        progressBar.setProgress(loadingProgress);
                        progressText.setText("下载中:" + loadingProgress + "%");
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "e =" + e.getMessage());
                        ToastUtils.show("下载失败");
                        tipsDialog.dismiss();
                    }

                    @Override
                    public void onResponse(File file, int id) {
                        tipsDialog.dismiss();
                        if (type) {
                            upApkFile(file);
                        } else {
                            installNewApk(file);
                        }
                    }
                });
    }

    /**
     * 合并增量安装包
     */
    private void upApkFile(final File patchFile) {
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo appInfo = pm.getApplicationInfo(context.getPackageName(), 0);
            final String oldPath = appInfo.sourceDir;
            final File newApkFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), ContactUrl.APP_NAME);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int result = PatchUtil.patch(oldPath, newApkFile.getAbsolutePath(), patchFile.getAbsolutePath());
                    if (result == 0) {
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                installNewApk(newApkFile);
                            }
                        });
                    }

                }
            }).start();

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转安装
     */
    private void installNewApk(File apkPath) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            /*由于没有在Activity环境下启动Activity,设置下面的标签*/
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            /*判读版本是否在7.0以上*/
            if (Build.VERSION.SDK_INT >= Const.INTEGER_24) {
                /*参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件*/
                Uri apkUri = FileProvider.getUriForFile(context, "com.xswq.chess.myapplication.packgeName.fileProvider", apkPath);
                /*添加这一句表示对目标应用临时授权该Uri所代表的文件*/
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(Uri.fromFile(apkPath), "application/vnd.android.package-archive");
            }
            context.startActivity(intent);
            mHandler.removeCallbacksAndMessages(null);
            ((Activity) context).finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
