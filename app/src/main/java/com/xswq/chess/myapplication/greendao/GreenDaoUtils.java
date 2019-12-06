package com.xswq.chess.myapplication.greendao;

import com.xswq.chess.myapplication.greendao.entity.Base;
import com.xswq.chess.myapplication.greendao.entity.Detail;

import java.util.List;

import om.org.greendao.gen.BaseDao;
import om.org.greendao.gen.DaoSession;
import om.org.greendao.gen.DetailDao;

//用于完成对某一张数据表的具体操作——ORM操作
public class GreenDaoUtils <Entity>{

    private static final String TAG = GreenDaoUtils.class.getSimpleName();
    private GreenDaoManager mManager;
    private final DaoSession daoSession;

    public GreenDaoUtils(DaoSession daoSession){

        this.daoSession = GreenDaoManager.getDaoSession();
    }

    //插入一条数据
    public boolean insertEntity(Entity mEntity){
        boolean flag = false;

            flag = daoSession.insertOrReplace(mEntity) != -1;

        return flag;
    }

    /**
     * 插入多条记录，需要开辟新的线程
     *
     * @param entitys
     * @return

    */
    public boolean insertMultEntity(final List<Entity> entitys) {

        boolean flag = false;
          try {
              daoSession.runInTx(new Runnable() {
                  @Override
                  public void run() {
                      for (Entity entity : entitys) {
                          daoSession.insertOrReplace(entity);
                      }
                  }
              });
              flag = true;
          }catch (Exception e){

              e.printStackTrace();
          }
       return flag;
    }
    /**
     * 完成对entity的某一条记录的修改
     *
     * @param entity
     * @return
    */
    public boolean updateStudent(Entity entity) {

        boolean flag = false;
        try {
            daoSession.update(entity);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    /**
     * 完成对entity的某一条记录的查询Detail表
     *
     * @param
     * @return
     */
    public boolean queryDetailOneData(String userId){
        boolean flag = false;
        try {

            flag = daoSession.getDetailDao().queryBuilder().where(DetailDao.Properties.Userid.eq(userId)).unique() == null;

        }catch (Exception e){
           e.printStackTrace();
        }
        return flag;
    }
    /**
     * 完成对entity的某一条记录的查询base表
     *
     * @param
     * @return
     */
    public boolean queryBaseOneData(String userId){
        boolean flag = false;
        try {

            flag = daoSession.getDetailDao().queryBuilder().where(BaseDao.Properties.Id.eq(userId)) != null;

        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    /**
     * 完成对entity的某一条记录的查询base表
     *
     * @param
     * @return
     */
    public Base getBaseUser(String userId){
        Base mBase = null;
        try{
             mBase =  daoSession.getBaseDao().queryBuilder().where(BaseDao.Properties.Id.eq(userId)).unique();

        }catch (Exception e){
            e.printStackTrace();
        }
        return mBase;
    }
    /**
     * 完成对entity的某一条记录的查询detail表
     *
     * @param
     * @return
     */
    public Detail getDetailUser(String userId){

        Detail mDetail =  daoSession.getDetailDao().queryBuilder().where(DetailDao.Properties.Userid.eq(userId)).unique();

        return mDetail;
    }
    /**
     * 完成对entity的某一条记录的查询detail表
     *
     * @param
     * @return
     */
    public boolean updateBaseUserType(Base mBase){
        boolean flag = false;
        try {
            daoSession.getBaseDao().update(mBase);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对entity的某一条记录的查询base表
     *
     * @param
     * @return
     */
    public Base getBaseUserByMobile(String mobile){
        Base mBase = null;
        try{
            mBase =  daoSession.getBaseDao().queryBuilder().where(BaseDao.Properties.Mobile.eq(mobile)).unique();

        }catch (Exception e){
            e.printStackTrace();
        }
        return mBase;
    }

}
