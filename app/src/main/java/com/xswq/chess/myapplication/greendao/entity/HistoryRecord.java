package com.xswq.chess.myapplication.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class HistoryRecord {
    @Id(autoincrement = true)
    private Long historyid;
    @Property(nameInDb = "WHITEUSERID")
    private	String	whiteUserId;
    @Property(nameInDb = "BLACKUSERNAME")
    private	String	blackUserName;
    @Property(nameInDb = "BLACKHEAD")
    private String blackHead;
    @Property(nameInDb = "BHITEUSERNAME")
    private	String	whiteUserName;
    @Property(nameInDb = "WHITEHEAD")
    private String whiteHead;
    @Property(nameInDb = "ENDTIME")
    private	long endTime;
    @Property(nameInDb = "BLACKUSERLEVEL")
    private	String	blackUserLevel;
    @Property(nameInDb = "WHITEUSERLEVEL")
    private	String	whiteUserLevel;
    @Property(nameInDb = "PLAYRESULT")
    private	String	playResult;
    @Property(nameInDb = "BLACKUSERID")
    private	String	blackUserId;
    @Property(nameInDb = "ID")
    private	String	id;
    @Property(nameInDb = "EVALUATESTATE")
    private	String evaluateState;
    @Property(nameInDb = "GAMETYPE")
    private	String	gameType;
    @Property(nameInDb = "CHESSID")
    private	String	chessId;
    @Property(nameInDb = "COLLECT")
    private	String	collect;
    @Generated(hash = 729559977)
    public HistoryRecord(Long historyid, String whiteUserId, String blackUserName,
            String blackHead, String whiteUserName, String whiteHead, long endTime,
            String blackUserLevel, String whiteUserLevel, String playResult,
            String blackUserId, String id, String evaluateState, String gameType,
            String chessId, String collect) {
        this.historyid = historyid;
        this.whiteUserId = whiteUserId;
        this.blackUserName = blackUserName;
        this.blackHead = blackHead;
        this.whiteUserName = whiteUserName;
        this.whiteHead = whiteHead;
        this.endTime = endTime;
        this.blackUserLevel = blackUserLevel;
        this.whiteUserLevel = whiteUserLevel;
        this.playResult = playResult;
        this.blackUserId = blackUserId;
        this.id = id;
        this.evaluateState = evaluateState;
        this.gameType = gameType;
        this.chessId = chessId;
        this.collect = collect;
    }
    @Generated(hash = 725453896)
    public HistoryRecord() {
    }
    public Long getHistoryid() {
        return this.historyid;
    }
    public void setHistoryid(Long historyid) {
        this.historyid = historyid;
    }
    public String getWhiteUserId() {
        return this.whiteUserId;
    }
    public void setWhiteUserId(String whiteUserId) {
        this.whiteUserId = whiteUserId;
    }
    public String getBlackUserName() {
        return this.blackUserName;
    }
    public void setBlackUserName(String blackUserName) {
        this.blackUserName = blackUserName;
    }
    public String getBlackHead() {
        return this.blackHead;
    }
    public void setBlackHead(String blackHead) {
        this.blackHead = blackHead;
    }
    public String getWhiteUserName() {
        return this.whiteUserName;
    }
    public void setWhiteUserName(String whiteUserName) {
        this.whiteUserName = whiteUserName;
    }
    public String getWhiteHead() {
        return this.whiteHead;
    }
    public void setWhiteHead(String whiteHead) {
        this.whiteHead = whiteHead;
    }
    public long getEndTime() {
        return this.endTime;
    }
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
    public String getBlackUserLevel() {
        return this.blackUserLevel;
    }
    public void setBlackUserLevel(String blackUserLevel) {
        this.blackUserLevel = blackUserLevel;
    }
    public String getWhiteUserLevel() {
        return this.whiteUserLevel;
    }
    public void setWhiteUserLevel(String whiteUserLevel) {
        this.whiteUserLevel = whiteUserLevel;
    }
    public String getPlayResult() {
        return this.playResult;
    }
    public void setPlayResult(String playResult) {
        this.playResult = playResult;
    }
    public String getBlackUserId() {
        return this.blackUserId;
    }
    public void setBlackUserId(String blackUserId) {
        this.blackUserId = blackUserId;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEvaluateState() {
        return this.evaluateState;
    }
    public void setEvaluateState(String evaluateState) {
        this.evaluateState = evaluateState;
    }
    public String getGameType() {
        return this.gameType;
    }
    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
    public String getChessId() {
        return this.chessId;
    }
    public void setChessId(String chessId) {
        this.chessId = chessId;
    }
    public String getCollect() {
        return this.collect;
    }
    public void setCollect(String collect) {
        this.collect = collect;
    }
   
}
