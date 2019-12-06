package om.org.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.xswq.chess.myapplication.greendao.entity.Base;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BASE".
*/
public class BaseDao extends AbstractDao<Base, Long> {

    public static final String TABLENAME = "BASE";

    /**
     * Properties of entity Base.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property IdIndex = new Property(0, Long.class, "IdIndex", true, "_id");
        public final static Property Id = new Property(1, String.class, "id", false, "USERID");
        public final static Property Username = new Property(2, String.class, "username", false, "USERNAME");
        public final static Property Password = new Property(3, String.class, "password", false, "PASSWORD");
        public final static Property Realname = new Property(4, String.class, "realname", false, "REALNAME");
        public final static Property Mobile = new Property(5, String.class, "mobile", false, "MOBILE");
        public final static Property Email = new Property(6, String.class, "email", false, "EMAIL");
        public final static Property Usertype = new Property(7, int.class, "usertype", false, "USERTYPE");
        public final static Property Usersourse = new Property(8, int.class, "usersourse", false, "USERSOURSE");
        public final static Property Qq = new Property(9, String.class, "qq", false, "QQ");
        public final static Property Wechat = new Property(10, String.class, "wechat", false, "WECHAT");
        public final static Property Sina = new Property(11, String.class, "sina", false, "SINA");
        public final static Property Createtime = new Property(12, String.class, "createtime", false, "CREATETIME");
        public final static Property Headimg = new Property(13, String.class, "headimg", false, "HEADIMG");
        public final static Property Address = new Property(14, String.class, "address", false, "ADDRESS");
        public final static Property Agentid = new Property(15, int.class, "agentid", false, "AGETID");
        public final static Property Sex = new Property(16, int.class, "sex", false, "SEX");
        public final static Property Birthday = new Property(17, String.class, "birthday", false, "BIRTHDAY");
        public final static Property State = new Property(18, int.class, "state", false, "STATE");
        public final static Property OrgNo = new Property(19, String.class, "orgNo", false, "ORGNO");
        public final static Property ManyFlg = new Property(20, int.class, "manyFlg", false, "MANYFLAG");
        public final static Property OrgName = new Property(21, String.class, "orgName", false, "ORGNAME");
        public final static Property LoginState = new Property(22, int.class, "loginState", false, "LOGINSTATE");
        public final static Property LoginTime = new Property(23, String.class, "loginTime", false, "LOGINTIME");
        public final static Property Level = new Property(24, int.class, "level", false, "LEVEL");
        public final static Property CardName = new Property(25, String.class, "cardName", false, "CARDNAME");
        public final static Property CardPsd = new Property(26, String.class, "cardPsd", false, "CARDPSD");
        public final static Property MatchOpts = new Property(27, int.class, "matchOpts", false, "MATCHOPTS");
        public final static Property SoundOpts = new Property(28, int.class, "soundOpts", false, "SOUNDOPTS");
        public final static Property ImOpts = new Property(29, int.class, "imOpts", false, "IMOPTS");
        public final static Property ExperienceTime = new Property(30, String.class, "experienceTime", false, "EXPERIENCETIME");
        public final static Property CooperationType = new Property(31, String.class, "cooperationType", false, "COOPERATIONTYPE");
        public final static Property Token = new Property(32, String.class, "token", false, "TOKEN");
        public final static Property ClassName = new Property(33, String.class, "className", false, "CLASSNAME");
        public final static Property BranchNo = new Property(34, String.class, "branchNo", false, "BRANCHNO");
        public final static Property Plays = new Property(35, String.class, "plays", false, "PLAYS");
        public final static Property TeacherName = new Property(36, String.class, "teacherName", false, "TEACHERNAME");
        public final static Property Victorynum = new Property(37, String.class, "victorynum", false, "VICTORYNUM");
        public final static Property Losenum = new Property(38, String.class, "losenum", false, "LOSENUM");
        public final static Property Finishexercises = new Property(39, String.class, "finishexercises", false, "FINISHEXERCISES");
        public final static Property Advancenum = new Property(40, int.class, "advancenum", false, "ADVANCENUM");
        public final static Property ClassInfoId = new Property(41, String.class, "classInfoId", false, "CLASSINFOID");
        public final static Property ImAccid = new Property(42, String.class, "imAccid", false, "IMACCID");
        public final static Property ImToken = new Property(43, String.class, "imToken", false, "IMTOKEN");
    }


    public BaseDao(DaoConfig config) {
        super(config);
    }
    
    public BaseDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BASE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: IdIndex
                "\"USERID\" TEXT," + // 1: id
                "\"USERNAME\" TEXT," + // 2: username
                "\"PASSWORD\" TEXT," + // 3: password
                "\"REALNAME\" TEXT," + // 4: realname
                "\"MOBILE\" TEXT," + // 5: mobile
                "\"EMAIL\" TEXT," + // 6: email
                "\"USERTYPE\" INTEGER NOT NULL ," + // 7: usertype
                "\"USERSOURSE\" INTEGER NOT NULL ," + // 8: usersourse
                "\"QQ\" TEXT," + // 9: qq
                "\"WECHAT\" TEXT," + // 10: wechat
                "\"SINA\" TEXT," + // 11: sina
                "\"CREATETIME\" TEXT," + // 12: createtime
                "\"HEADIMG\" TEXT," + // 13: headimg
                "\"ADDRESS\" TEXT," + // 14: address
                "\"AGETID\" INTEGER NOT NULL ," + // 15: agentid
                "\"SEX\" INTEGER NOT NULL ," + // 16: sex
                "\"BIRTHDAY\" TEXT," + // 17: birthday
                "\"STATE\" INTEGER NOT NULL ," + // 18: state
                "\"ORGNO\" TEXT," + // 19: orgNo
                "\"MANYFLAG\" INTEGER NOT NULL ," + // 20: manyFlg
                "\"ORGNAME\" TEXT," + // 21: orgName
                "\"LOGINSTATE\" INTEGER NOT NULL ," + // 22: loginState
                "\"LOGINTIME\" TEXT," + // 23: loginTime
                "\"LEVEL\" INTEGER NOT NULL ," + // 24: level
                "\"CARDNAME\" TEXT," + // 25: cardName
                "\"CARDPSD\" TEXT," + // 26: cardPsd
                "\"MATCHOPTS\" INTEGER NOT NULL ," + // 27: matchOpts
                "\"SOUNDOPTS\" INTEGER NOT NULL ," + // 28: soundOpts
                "\"IMOPTS\" INTEGER NOT NULL ," + // 29: imOpts
                "\"EXPERIENCETIME\" TEXT," + // 30: experienceTime
                "\"COOPERATIONTYPE\" TEXT," + // 31: cooperationType
                "\"TOKEN\" TEXT," + // 32: token
                "\"CLASSNAME\" TEXT," + // 33: className
                "\"BRANCHNO\" TEXT," + // 34: branchNo
                "\"PLAYS\" TEXT," + // 35: plays
                "\"TEACHERNAME\" TEXT," + // 36: teacherName
                "\"VICTORYNUM\" TEXT," + // 37: victorynum
                "\"LOSENUM\" TEXT," + // 38: losenum
                "\"FINISHEXERCISES\" TEXT," + // 39: finishexercises
                "\"ADVANCENUM\" INTEGER NOT NULL ," + // 40: advancenum
                "\"CLASSINFOID\" TEXT," + // 41: classInfoId
                "\"IMACCID\" TEXT," + // 42: imAccid
                "\"IMTOKEN\" TEXT);"); // 43: imToken
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BASE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Base entity) {
        stmt.clearBindings();
 
        Long IdIndex = entity.getIdIndex();
        if (IdIndex != null) {
            stmt.bindLong(1, IdIndex);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(3, username);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(4, password);
        }
 
        String realname = entity.getRealname();
        if (realname != null) {
            stmt.bindString(5, realname);
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(6, mobile);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(7, email);
        }
        stmt.bindLong(8, entity.getUsertype());
        stmt.bindLong(9, entity.getUsersourse());
 
        String qq = entity.getQq();
        if (qq != null) {
            stmt.bindString(10, qq);
        }
 
        String wechat = entity.getWechat();
        if (wechat != null) {
            stmt.bindString(11, wechat);
        }
 
        String sina = entity.getSina();
        if (sina != null) {
            stmt.bindString(12, sina);
        }
 
        String createtime = entity.getCreatetime();
        if (createtime != null) {
            stmt.bindString(13, createtime);
        }
 
        String headimg = entity.getHeadimg();
        if (headimg != null) {
            stmt.bindString(14, headimg);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(15, address);
        }
        stmt.bindLong(16, entity.getAgentid());
        stmt.bindLong(17, entity.getSex());
 
        String birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindString(18, birthday);
        }
        stmt.bindLong(19, entity.getState());
 
        String orgNo = entity.getOrgNo();
        if (orgNo != null) {
            stmt.bindString(20, orgNo);
        }
        stmt.bindLong(21, entity.getManyFlg());
 
        String orgName = entity.getOrgName();
        if (orgName != null) {
            stmt.bindString(22, orgName);
        }
        stmt.bindLong(23, entity.getLoginState());
 
        String loginTime = entity.getLoginTime();
        if (loginTime != null) {
            stmt.bindString(24, loginTime);
        }
        stmt.bindLong(25, entity.getLevel());
 
        String cardName = entity.getCardName();
        if (cardName != null) {
            stmt.bindString(26, cardName);
        }
 
        String cardPsd = entity.getCardPsd();
        if (cardPsd != null) {
            stmt.bindString(27, cardPsd);
        }
        stmt.bindLong(28, entity.getMatchOpts());
        stmt.bindLong(29, entity.getSoundOpts());
        stmt.bindLong(30, entity.getImOpts());
 
        String experienceTime = entity.getExperienceTime();
        if (experienceTime != null) {
            stmt.bindString(31, experienceTime);
        }
 
        String cooperationType = entity.getCooperationType();
        if (cooperationType != null) {
            stmt.bindString(32, cooperationType);
        }
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(33, token);
        }
 
        String className = entity.getClassName();
        if (className != null) {
            stmt.bindString(34, className);
        }
 
        String branchNo = entity.getBranchNo();
        if (branchNo != null) {
            stmt.bindString(35, branchNo);
        }
 
        String plays = entity.getPlays();
        if (plays != null) {
            stmt.bindString(36, plays);
        }
 
        String teacherName = entity.getTeacherName();
        if (teacherName != null) {
            stmt.bindString(37, teacherName);
        }
 
        String victorynum = entity.getVictorynum();
        if (victorynum != null) {
            stmt.bindString(38, victorynum);
        }
 
        String losenum = entity.getLosenum();
        if (losenum != null) {
            stmt.bindString(39, losenum);
        }
 
        String finishexercises = entity.getFinishexercises();
        if (finishexercises != null) {
            stmt.bindString(40, finishexercises);
        }
        stmt.bindLong(41, entity.getAdvancenum());
 
        String classInfoId = entity.getClassInfoId();
        if (classInfoId != null) {
            stmt.bindString(42, classInfoId);
        }
 
        String imAccid = entity.getImAccid();
        if (imAccid != null) {
            stmt.bindString(43, imAccid);
        }
 
        String imToken = entity.getImToken();
        if (imToken != null) {
            stmt.bindString(44, imToken);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Base entity) {
        stmt.clearBindings();
 
        Long IdIndex = entity.getIdIndex();
        if (IdIndex != null) {
            stmt.bindLong(1, IdIndex);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(3, username);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(4, password);
        }
 
        String realname = entity.getRealname();
        if (realname != null) {
            stmt.bindString(5, realname);
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(6, mobile);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(7, email);
        }
        stmt.bindLong(8, entity.getUsertype());
        stmt.bindLong(9, entity.getUsersourse());
 
        String qq = entity.getQq();
        if (qq != null) {
            stmt.bindString(10, qq);
        }
 
        String wechat = entity.getWechat();
        if (wechat != null) {
            stmt.bindString(11, wechat);
        }
 
        String sina = entity.getSina();
        if (sina != null) {
            stmt.bindString(12, sina);
        }
 
        String createtime = entity.getCreatetime();
        if (createtime != null) {
            stmt.bindString(13, createtime);
        }
 
        String headimg = entity.getHeadimg();
        if (headimg != null) {
            stmt.bindString(14, headimg);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(15, address);
        }
        stmt.bindLong(16, entity.getAgentid());
        stmt.bindLong(17, entity.getSex());
 
        String birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindString(18, birthday);
        }
        stmt.bindLong(19, entity.getState());
 
        String orgNo = entity.getOrgNo();
        if (orgNo != null) {
            stmt.bindString(20, orgNo);
        }
        stmt.bindLong(21, entity.getManyFlg());
 
        String orgName = entity.getOrgName();
        if (orgName != null) {
            stmt.bindString(22, orgName);
        }
        stmt.bindLong(23, entity.getLoginState());
 
        String loginTime = entity.getLoginTime();
        if (loginTime != null) {
            stmt.bindString(24, loginTime);
        }
        stmt.bindLong(25, entity.getLevel());
 
        String cardName = entity.getCardName();
        if (cardName != null) {
            stmt.bindString(26, cardName);
        }
 
        String cardPsd = entity.getCardPsd();
        if (cardPsd != null) {
            stmt.bindString(27, cardPsd);
        }
        stmt.bindLong(28, entity.getMatchOpts());
        stmt.bindLong(29, entity.getSoundOpts());
        stmt.bindLong(30, entity.getImOpts());
 
        String experienceTime = entity.getExperienceTime();
        if (experienceTime != null) {
            stmt.bindString(31, experienceTime);
        }
 
        String cooperationType = entity.getCooperationType();
        if (cooperationType != null) {
            stmt.bindString(32, cooperationType);
        }
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(33, token);
        }
 
        String className = entity.getClassName();
        if (className != null) {
            stmt.bindString(34, className);
        }
 
        String branchNo = entity.getBranchNo();
        if (branchNo != null) {
            stmt.bindString(35, branchNo);
        }
 
        String plays = entity.getPlays();
        if (plays != null) {
            stmt.bindString(36, plays);
        }
 
        String teacherName = entity.getTeacherName();
        if (teacherName != null) {
            stmt.bindString(37, teacherName);
        }
 
        String victorynum = entity.getVictorynum();
        if (victorynum != null) {
            stmt.bindString(38, victorynum);
        }
 
        String losenum = entity.getLosenum();
        if (losenum != null) {
            stmt.bindString(39, losenum);
        }
 
        String finishexercises = entity.getFinishexercises();
        if (finishexercises != null) {
            stmt.bindString(40, finishexercises);
        }
        stmt.bindLong(41, entity.getAdvancenum());
 
        String classInfoId = entity.getClassInfoId();
        if (classInfoId != null) {
            stmt.bindString(42, classInfoId);
        }
 
        String imAccid = entity.getImAccid();
        if (imAccid != null) {
            stmt.bindString(43, imAccid);
        }
 
        String imToken = entity.getImToken();
        if (imToken != null) {
            stmt.bindString(44, imToken);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Base readEntity(Cursor cursor, int offset) {
        Base entity = new Base( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // IdIndex
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // username
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // password
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // realname
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // mobile
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // email
            cursor.getInt(offset + 7), // usertype
            cursor.getInt(offset + 8), // usersourse
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // qq
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // wechat
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // sina
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // createtime
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // headimg
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // address
            cursor.getInt(offset + 15), // agentid
            cursor.getInt(offset + 16), // sex
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // birthday
            cursor.getInt(offset + 18), // state
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // orgNo
            cursor.getInt(offset + 20), // manyFlg
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // orgName
            cursor.getInt(offset + 22), // loginState
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // loginTime
            cursor.getInt(offset + 24), // level
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // cardName
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // cardPsd
            cursor.getInt(offset + 27), // matchOpts
            cursor.getInt(offset + 28), // soundOpts
            cursor.getInt(offset + 29), // imOpts
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // experienceTime
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // cooperationType
            cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32), // token
            cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33), // className
            cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34), // branchNo
            cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35), // plays
            cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36), // teacherName
            cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37), // victorynum
            cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38), // losenum
            cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39), // finishexercises
            cursor.getInt(offset + 40), // advancenum
            cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41), // classInfoId
            cursor.isNull(offset + 42) ? null : cursor.getString(offset + 42), // imAccid
            cursor.isNull(offset + 43) ? null : cursor.getString(offset + 43) // imToken
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Base entity, int offset) {
        entity.setIdIndex(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUsername(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPassword(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setRealname(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setMobile(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setEmail(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setUsertype(cursor.getInt(offset + 7));
        entity.setUsersourse(cursor.getInt(offset + 8));
        entity.setQq(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setWechat(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setSina(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setCreatetime(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setHeadimg(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setAddress(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setAgentid(cursor.getInt(offset + 15));
        entity.setSex(cursor.getInt(offset + 16));
        entity.setBirthday(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setState(cursor.getInt(offset + 18));
        entity.setOrgNo(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setManyFlg(cursor.getInt(offset + 20));
        entity.setOrgName(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setLoginState(cursor.getInt(offset + 22));
        entity.setLoginTime(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setLevel(cursor.getInt(offset + 24));
        entity.setCardName(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setCardPsd(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setMatchOpts(cursor.getInt(offset + 27));
        entity.setSoundOpts(cursor.getInt(offset + 28));
        entity.setImOpts(cursor.getInt(offset + 29));
        entity.setExperienceTime(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setCooperationType(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setToken(cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32));
        entity.setClassName(cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33));
        entity.setBranchNo(cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34));
        entity.setPlays(cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35));
        entity.setTeacherName(cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36));
        entity.setVictorynum(cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37));
        entity.setLosenum(cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38));
        entity.setFinishexercises(cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39));
        entity.setAdvancenum(cursor.getInt(offset + 40));
        entity.setClassInfoId(cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41));
        entity.setImAccid(cursor.isNull(offset + 42) ? null : cursor.getString(offset + 42));
        entity.setImToken(cursor.isNull(offset + 43) ? null : cursor.getString(offset + 43));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Base entity, long rowId) {
        entity.setIdIndex(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Base entity) {
        if(entity != null) {
            return entity.getIdIndex();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Base entity) {
        return entity.getIdIndex() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
