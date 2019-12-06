package com.xswq.chess.myapplication.bean;

import java.util.List;

public class StudentParticylarsBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : [{"result":1,"whiteScore":0,"blackHeading":"img/headImg/1.png","blackNum":1,"blackId":622,"whiteName":"销售2","blackName":"销售1","whiteHeading":"img/headImg/1.png","blackScore":0,"whiteId":623,"whiteNum":2},{"result":1,"whiteScore":0,"blackHeading":"img/headImg/1.png","blackNum":1,"blackId":624,"blackName":"销售3","blackScore":0},{"result":1,"whiteScore":0,"blackHeading":"img/headImg/1.png","blackNum":3,"blackId":624,"blackName":"销售3","blackScore":0}]
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

        private String returnCode;
        private String returnMessage;
        private String returnUserMessage;
        private Object userRanking;
        private Object homeTable;

        public String getReturnCode() {
            return returnCode;
        }

        public void setReturnCode(String returnCode) {
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
         * result : 1
         * whiteScore : 0
         * blackHeading : img/headImg/1.png
         * blackNum : 1
         * blackId : 622
         * whiteName : 销售2
         * blackName : 销售1
         * whiteHeading : img/headImg/1.png
         * blackScore : 0
         * whiteId : 623
         * whiteNum : 2
         */

        private int result;
        private int whiteScore;
        private String blackHeading;
        private int blackNum;
        private String blackId;
        private String whiteName;
        private String blackName;
        private String whiteHeading;
        private int blackScore;
        private String whiteId;
        private int whiteNum;
        private String id;
        private int judge;

        public int getJudge() {
            return judge;
        }

        public void setJudge(int judge) {
            this.judge = judge;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public int getWhiteScore() {
            return whiteScore;
        }

        public void setWhiteScore(int whiteScore) {
            this.whiteScore = whiteScore;
        }

        public String getBlackHeading() {
            return blackHeading;
        }

        public void setBlackHeading(String blackHeading) {
            this.blackHeading = blackHeading;
        }

        public int getBlackNum() {
            return blackNum;
        }

        public void setBlackNum(int blackNum) {
            this.blackNum = blackNum;
        }

        public String getBlackId() {
            return blackId;
        }

        public void setBlackId(String blackId) {
            this.blackId = blackId;
        }

        public String getWhiteName() {
            return whiteName;
        }

        public void setWhiteName(String whiteName) {
            this.whiteName = whiteName;
        }

        public String getBlackName() {
            return blackName;
        }

        public void setBlackName(String blackName) {
            this.blackName = blackName;
        }

        public String getWhiteHeading() {
            return whiteHeading;
        }

        public void setWhiteHeading(String whiteHeading) {
            this.whiteHeading = whiteHeading;
        }

        public int getBlackScore() {
            return blackScore;
        }

        public void setBlackScore(int blackScore) {
            this.blackScore = blackScore;
        }

        public String getWhiteId() {
            return whiteId;
        }

        public void setWhiteId(String whiteId) {
            this.whiteId = whiteId;
        }

        public int getWhiteNum() {
            return whiteNum;
        }

        public void setWhiteNum(int whiteNum) {
            this.whiteNum = whiteNum;
        }
    }
}
