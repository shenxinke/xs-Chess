package om.org.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.xswq.chess.myapplication.greendao.entity.Detail;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DETAIL".
*/
public class DetailDao extends AbstractDao<Detail, Long> {

    public static final String TABLENAME = "DETAIL";

    /**
     * Properties of entity Detail.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "Id", true, "_id");
        public final static Property Userid = new Property(1, String.class, "userid", false, "USERID");
        public final static Property Level = new Property(2, int.class, "level", false, "LEVEL");
        public final static Property Victorynum = new Property(3, int.class, "victorynum", false, "VICTORYNUM");
        public final static Property Losenum = new Property(4, int.class, "losenum", false, "LOSENUM");
        public final static Property Finishexercises = new Property(5, int.class, "finishexercises", false, "FINISHEXERCISES");
        public final static Property Advancenum = new Property(6, int.class, "advancenum", false, "ADVANCENUM");
        public final static Property Username = new Property(7, String.class, "username", false, "USERNAME");
        public final static Property Rownum = new Property(8, int.class, "rownum", false, "ROWNUM");
        public final static Property Updateleveltime = new Property(9, String.class, "updateleveltime", false, "UODATELEVELTIME");
        public final static Property Starnum = new Property(10, int.class, "starnum", false, "STARNUM");
    }


    public DetailDao(DaoConfig config) {
        super(config);
    }
    
    public DetailDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DETAIL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: Id
                "\"USERID\" TEXT," + // 1: userid
                "\"LEVEL\" INTEGER NOT NULL ," + // 2: level
                "\"VICTORYNUM\" INTEGER NOT NULL ," + // 3: victorynum
                "\"LOSENUM\" INTEGER NOT NULL ," + // 4: losenum
                "\"FINISHEXERCISES\" INTEGER NOT NULL ," + // 5: finishexercises
                "\"ADVANCENUM\" INTEGER NOT NULL ," + // 6: advancenum
                "\"USERNAME\" TEXT," + // 7: username
                "\"ROWNUM\" INTEGER NOT NULL ," + // 8: rownum
                "\"UODATELEVELTIME\" TEXT," + // 9: updateleveltime
                "\"STARNUM\" INTEGER NOT NULL );"); // 10: starnum
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DETAIL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Detail entity) {
        stmt.clearBindings();
 
        Long Id = entity.getId();
        if (Id != null) {
            stmt.bindLong(1, Id);
        }
 
        String userid = entity.getUserid();
        if (userid != null) {
            stmt.bindString(2, userid);
        }
        stmt.bindLong(3, entity.getLevel());
        stmt.bindLong(4, entity.getVictorynum());
        stmt.bindLong(5, entity.getLosenum());
        stmt.bindLong(6, entity.getFinishexercises());
        stmt.bindLong(7, entity.getAdvancenum());
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(8, username);
        }
        stmt.bindLong(9, entity.getRownum());
 
        String updateleveltime = entity.getUpdateleveltime();
        if (updateleveltime != null) {
            stmt.bindString(10, updateleveltime);
        }
        stmt.bindLong(11, entity.getStarnum());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Detail entity) {
        stmt.clearBindings();
 
        Long Id = entity.getId();
        if (Id != null) {
            stmt.bindLong(1, Id);
        }
 
        String userid = entity.getUserid();
        if (userid != null) {
            stmt.bindString(2, userid);
        }
        stmt.bindLong(3, entity.getLevel());
        stmt.bindLong(4, entity.getVictorynum());
        stmt.bindLong(5, entity.getLosenum());
        stmt.bindLong(6, entity.getFinishexercises());
        stmt.bindLong(7, entity.getAdvancenum());
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(8, username);
        }
        stmt.bindLong(9, entity.getRownum());
 
        String updateleveltime = entity.getUpdateleveltime();
        if (updateleveltime != null) {
            stmt.bindString(10, updateleveltime);
        }
        stmt.bindLong(11, entity.getStarnum());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Detail readEntity(Cursor cursor, int offset) {
        Detail entity = new Detail( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // Id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userid
            cursor.getInt(offset + 2), // level
            cursor.getInt(offset + 3), // victorynum
            cursor.getInt(offset + 4), // losenum
            cursor.getInt(offset + 5), // finishexercises
            cursor.getInt(offset + 6), // advancenum
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // username
            cursor.getInt(offset + 8), // rownum
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // updateleveltime
            cursor.getInt(offset + 10) // starnum
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Detail entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setLevel(cursor.getInt(offset + 2));
        entity.setVictorynum(cursor.getInt(offset + 3));
        entity.setLosenum(cursor.getInt(offset + 4));
        entity.setFinishexercises(cursor.getInt(offset + 5));
        entity.setAdvancenum(cursor.getInt(offset + 6));
        entity.setUsername(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setRownum(cursor.getInt(offset + 8));
        entity.setUpdateleveltime(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setStarnum(cursor.getInt(offset + 10));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Detail entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Detail entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Detail entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
