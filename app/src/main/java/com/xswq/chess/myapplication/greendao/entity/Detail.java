package com.xswq.chess.myapplication.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Detail {
    @Id(autoincrement = true)
    private Long Id;
    @Property(nameInDb = "USERID")
    private	String 	userid	;
    @Property(nameInDb = "LEVEL")
    private	int	level;
    @Property(nameInDb = "VICTORYNUM")
    private	int	victorynum	;
    @Property(nameInDb = "LOSENUM")
    private	int	losenum	;
    @Property(nameInDb = "FINISHEXERCISES")
    private	int	finishexercises	;
    @Property(nameInDb = "ADVANCENUM")
    private	int	advancenum	;
    @Property(nameInDb = "USERNAME")
    private	String 	username;
    @Property(nameInDb = "ROWNUM")
    private	int	rownum	;
    @Property(nameInDb = "UODATELEVELTIME")
    private	String 	updateleveltime	;
    @Property(nameInDb = "STARNUM")
    private	int	starnum	;
    @Generated(hash = 1647381085)
    public Detail(Long Id, String userid, int level, int victorynum, int losenum,
            int finishexercises, int advancenum, String username, int rownum,
            String updateleveltime, int starnum) {
        this.Id = Id;
        this.userid = userid;
        this.level = level;
        this.victorynum = victorynum;
        this.losenum = losenum;
        this.finishexercises = finishexercises;
        this.advancenum = advancenum;
        this.username = username;
        this.rownum = rownum;
        this.updateleveltime = updateleveltime;
        this.starnum = starnum;
    }
    @Generated(hash = 1665969126)
    public Detail() {
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public String getUserid() {
        return this.userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public int getLevel() {
        return this.level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getVictorynum() {
        return this.victorynum;
    }
    public void setVictorynum(int victorynum) {
        this.victorynum = victorynum;
    }
    public int getLosenum() {
        return this.losenum;
    }
    public void setLosenum(int losenum) {
        this.losenum = losenum;
    }
    public int getFinishexercises() {
        return this.finishexercises;
    }
    public void setFinishexercises(int finishexercises) {
        this.finishexercises = finishexercises;
    }
    public int getAdvancenum() {
        return this.advancenum;
    }
    public void setAdvancenum(int advancenum) {
        this.advancenum = advancenum;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getRownum() {
        return this.rownum;
    }
    public void setRownum(int rownum) {
        this.rownum = rownum;
    }
    public String getUpdateleveltime() {
        return this.updateleveltime;
    }
    public void setUpdateleveltime(String updateleveltime) {
        this.updateleveltime = updateleveltime;
    }
    public int getStarnum() {
        return this.starnum;
    }
    public void setStarnum(int starnum) {
        this.starnum = starnum;
    }

}
