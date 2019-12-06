package com.xswq.chess.myapplication.bean;

import java.util.List;

public class GameInfoUseridBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : [{"gameId":"eEEnS4xDlm","blackHeadImg":"img/headImg/1.png","ChessSeq":0,"blackLevel":"13K","whitePlayType":"1","GameType":3,"whiteUserId":845,"whiteLevel":"13K","userId":1516,"whiteHeadImg":"img/headImg/1.png","blackUserId":1516,"whiteImAccid":"845","blackPlayType":"1","blackImAccid":"1516","whiteUserNm":"康岩泽","RuleType2":1,"RuleType1":1,"blackUserNm":"卢"}]
     */

    private ErrorBean error;
    private List<DataBean> data;

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class ErrorBean {
        /**
         * returnCode : 0
         * returnMessage : 操作成功
         * returnUserMessage : 操作成功
         * userRanking : null
         * homeTable : null
         */

        private int returnCode;
        private String returnMessage;
        private String returnUserMessage;
        private Object userRanking;
        private Object homeTable;

        public int getReturnCode() {
            return returnCode;
        }

        public void setReturnCode(int returnCode) {
            this.returnCode = returnCode;
        }

        public String getReturnMessage() {
            return returnMessage;
        }

        public void setReturnMessage(String returnMessage) {
            this.returnMessage = returnMessage;
        }

        public String getReturnUserMessage() {
            return returnUserMessage;
        }

        public void setReturnUserMessage(String returnUserMessage) {
            this.returnUserMessage = returnUserMessage;
        }

        public Object getUserRanking() {
            return userRanking;
        }

        public void setUserRanking(Object userRanking) {
            this.userRanking = userRanking;
        }

        public Object getHomeTable() {
            return homeTable;
        }

        public void setHomeTable(Object homeTable) {
            this.homeTable = homeTable;
        }
    }

    public static class DataBean {
        /**
         * gameId : eEEnS4xDlm
         * blackHeadImg : img/headImg/1.png
         * ChessSeq : 0
         * blackLevel : 13K
         * whitePlayType : 1
         * GameType : 3
         * whiteUserId : 845
         * whiteLevel : 13K
         * userId : 1516
         * whiteHeadImg : img/headImg/1.png
         * blackUserId : 1516
         * whiteImAccid : 845
         * blackPlayType : 1
         * blackImAccid : 1516
         * whiteUserNm : 康岩泽
         * RuleType2 : 1
         * RuleType1 : 1
         * blackUserNm : 卢
         */

        private String gameId;
        private String blackHeadImg;
        private int ChessSeq;
        private String blackLevel;
        private String whitePlayType;
        private int GameType;
        private int whiteUserId;
        private String whiteLevel;
        private int userId;
        private String whiteHeadImg;
        private int blackUserId;
        private String whiteImAccid;
        private String blackPlayType;
        private String blackImAccid;
        private String whiteUserNm;
        private int RuleType2;
        private int RuleType1;
        private String blackUserNm;

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }

        public String getBlackHeadImg() {
            return blackHeadImg;
        }

        public void setBlackHeadImg(String blackHeadImg) {
            this.blackHeadImg = blackHeadImg;
        }

        public int getChessSeq() {
            return ChessSeq;
        }

        public void setChessSeq(int ChessSeq) {
            this.ChessSeq = ChessSeq;
        }

        public String getBlackLevel() {
            return blackLevel;
        }

        public void setBlackLevel(String blackLevel) {
            this.blackLevel = blackLevel;
        }

        public String getWhitePlayType() {
            return whitePlayType;
        }

        public void setWhitePlayType(String whitePlayType) {
            this.whitePlayType = whitePlayType;
        }

        public int getGameType() {
            return GameType;
        }

        public void setGameType(int GameType) {
            this.GameType = GameType;
        }

        public int getWhiteUserId() {
            return whiteUserId;
        }

        public void setWhiteUserId(int whiteUserId) {
            this.whiteUserId = whiteUserId;
        }

        public String getWhiteLevel() {
            return whiteLevel;
        }

        public void setWhiteLevel(String whiteLevel) {
            this.whiteLevel = whiteLevel;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getWhiteHeadImg() {
            return whiteHeadImg;
        }

        public void setWhiteHeadImg(String whiteHeadImg) {
            this.whiteHeadImg = whiteHeadImg;
        }

        public int getBlackUserId() {
            return blackUserId;
        }

        public void setBlackUserId(int blackUserId) {
            this.blackUserId = blackUserId;
        }

        public String getWhiteImAccid() {
            return whiteImAccid;
        }

        public void setWhiteImAccid(String whiteImAccid) {
            this.whiteImAccid = whiteImAccid;
        }

        public String getBlackPlayType() {
            return blackPlayType;
        }

        public void setBlackPlayType(String blackPlayType) {
            this.blackPlayType = blackPlayType;
        }

        public String getBlackImAccid() {
            return blackImAccid;
        }

        public void setBlackImAccid(String blackImAccid) {
            this.blackImAccid = blackImAccid;
        }

        public String getWhiteUserNm() {
            return whiteUserNm;
        }

        public void setWhiteUserNm(String whiteUserNm) {
            this.whiteUserNm = whiteUserNm;
        }

        public int getRuleType2() {
            return RuleType2;
        }

        public void setRuleType2(int RuleType2) {
            this.RuleType2 = RuleType2;
        }

        public int getRuleType1() {
            return RuleType1;
        }

        public void setRuleType1(int RuleType1) {
            this.RuleType1 = RuleType1;
        }

        public String getBlackUserNm() {
            return blackUserNm;
        }

        public void setBlackUserNm(String blackUserNm) {
            this.blackUserNm = blackUserNm;
        }
    }
}
