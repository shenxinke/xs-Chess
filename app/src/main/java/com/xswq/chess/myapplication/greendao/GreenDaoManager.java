package com.xswq.chess.myapplication.greendao;

import android.content.Context;

import org.greenrobot.greendao.query.QueryBuilder;

import om.org.greendao.gen.DaoMaster;
import om.org.greendao.gen.DaoSession;

public class GreenDaoManager {


    private static final String TAG = GreenDaoManager.class.getSimpleName();
    private static final String DB_NAME = "xswq.db";

    private static Context context;

    //多线程中要被共享的使用volatile关键字修饰
    private volatile static GreenDaoManager manager;

    private static DaoMaster sDaoMaster;
    private static DaoMaster.DevOpenHelper sHelper;
    private static DaoSession sDaoSession;

    public GreenDaoManager(Context context) {
        GreenDaoManager.context = context;
    }

    /**
     * 单例模式获得操作数据库对象
     * @return
     */
    public static GreenDaoManager getInstance(Context context) {
        if (manager == null) {
            synchronized (GreenDaoManager.class) {
                if (manager == null) {
                    manager = new GreenDaoManager(context);
                }
            }
        }
        return manager;
    }

    /**
     * 判断是否有存在数据库，如果没有则创建
     * @return
     */
    public static DaoMaster getsDaoMaster() {

        if(sDaoMaster==null){
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
            sDaoMaster = new DaoMaster(helper.getWritableDatabase());

        }
        return sDaoMaster;
    }

    /**
     * 完成对数据库的添加、删除、修改、查询操作，仅仅是一个接口
     * @return
     */
     public static DaoSession getDaoSession(){

         if(sDaoSession==null){
             if(sDaoMaster==null){
                 sDaoMaster = getsDaoMaster();
             }
          sDaoSession = sDaoMaster.newSession();
         }
         return sDaoSession;
     }
    /**
     * 打开输出日志，默认关闭
     */
    public void setDebug(){
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    /**
     * 关闭所有的操作，数据库开启后，使用完毕要关闭
     */
    public static void closeConnection(){
        closeHelper();
        closeDaoSession();
    }

    public static void closeHelper(){
        if(sHelper != null){
            sHelper.close();
            sHelper = null;
        }
    }

    public static void closeDaoSession(){
        if(sDaoSession != null){
            sDaoSession.clear();
            sDaoSession = null;
        }
    }
}
