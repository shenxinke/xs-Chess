package com.xswq.chess.myapplication.bean;

public class ScoketMessageBean {


    private String content;
    private String message;
    private String matchName;
    private String roundsNum;
    private String blackUserHead;
    private String blackUserName;
    private String whiteUserHead;
    private String whiteUserName;
    private String blackUserId;
    private String whiteUserId;
    private long roundsStartTime;
    private int type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getRoundsNum() {
        return roundsNum;
    }

    public void setRoundsNum(String roundsNum) {
        this.roundsNum = roundsNum;
    }

    public String getBlackUserHead() {
        return blackUserHead;
    }

    public void setBlackUserHead(String blackUserHead) {
        this.blackUserHead = blackUserHead;
    }

    public String getBlackUserName() {
        return blackUserName;
    }

    public void setBlackUserName(String blackUserName) {
        this.blackUserName = blackUserName;
    }

    public String getWhiteUserHead() {
        return whiteUserHead;
    }

    public void setWhiteUserHead(String whiteUserHead) {
        this.whiteUserHead = whiteUserHead;
    }

    public String getWhiteUserName() {
        return whiteUserName;
    }

    public void setWhiteUserName(String whiteUserName) {
        this.whiteUserName = whiteUserName;
    }

    public long getRoundsStartTime() {
        return roundsStartTime;
    }

    public void setRoundsStartTime(long roundsStartTime) {
        this.roundsStartTime = roundsStartTime;
    }

    public String getBlackUserId() {
        return blackUserId;
    }

    public void setBlackUserId(String blackUserId) {
        this.blackUserId = blackUserId;
    }

    public String getWhiteUserId() {
        return whiteUserId;
    }

    public void setWhiteUserId(String whiteUserId) {
        this.whiteUserId = whiteUserId;
    }
}
