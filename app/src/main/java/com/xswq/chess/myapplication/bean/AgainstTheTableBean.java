package com.xswq.chess.myapplication.bean;

import java.util.List;

public class AgainstTheTableBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : [{"result":1,"whiteScore":0,"blackHeading":"img/headImg/1.png","blackNum":2,"blackId":623,"whiteName":"销售3","blackName":"销售2","whiteHeading":"img/headImg/1.png","blackScore":0,"whiteId":624,"whiteNum":3},{"result":1,"whiteScore":0,"blackHeading":"img/headImg/1.png","blackNum":2,"blackId":623,"whiteName":"销售3","blackName":"销售2","whiteHeading":"img/headImg/1.png","blackScore":0,"whiteId":624,"whiteNum":3},{"result":1,"whiteScore":0,"blackHeading":"img/headImg/1.png","blackNum":2,"blackId":623,"whiteName":"销售3","blackName":"销售2","whiteHeading":"img/headImg/1.png","blackScore":0,"whiteId":624,"whiteNum":3},{"result":1,"whiteScore":0,"blackHeading":"img/headImg/1.png","blackNum":2,"blackId":623,"whiteName":"销售3","blackName":"销售2","whiteHeading":"img/headImg/1.png","blackScore":0,"whiteId":624,"whiteNum":3},{"result":1,"whiteScore":0,"blackHeading":"img/headImg/1.png","blackNum":2,"blackId":623,"whiteName":"销售3","blackName":"销售2","whiteHeading":"img/headImg/1.png","blackScore":0,"whiteId":624,"whiteNum":3},{"result":1,"whiteScore":0,"blackHeading":"img/headImg/1.png","blackNum":2,"blackId":623,"whiteName":"销售3","blackName":"销售2","whiteHeading":"img/headImg/1.png","blackScore":0,"whiteId":624,"whiteNum":3},{"result":1,"whiteScore":0,"blackHeading":"img/headImg/1.png","blackNum":2,"blackId":623,"whiteName":"销售3","blackName":"销售2","whiteHeading":"img/headImg/1.png","blackScore":0,"whiteId":624,"whiteNum":1},{"result":1,"whiteScore":0,"blackHeading":"img/headImg/1.png","blackNum":2,"blackId":623,"whiteName":"销售3","blackName":"销售2","whiteHeading":"img/headImg/1.png","blackScore":0,"whiteId":624,"whiteNum":1},{"result":1,"whiteScore":0,"blackHeading":"img/headImg/1.png","blackNum":2,"blackId":623,"whiteName":"销售3","blackName":"销售2","whiteHeading":"img/headImg/1.png","blackScore":0,"whiteId":624,"whiteNum":1},{"result":1,"whiteScore":0,"blackHeading":"img/headImg/1.png","blackNum":2,"blackId":623,"whiteName":"销售3","blackName":"销售2","whiteHeading":"img/headImg/1.png","blackScore":0,"whiteId":624,"whiteNum":3},{"result":1,"whiteScore":0,"blackHeading":"img/headImg/1.png","blackNum":2,"blackId":623,"whiteName":"销售3","blackName":"销售2","whiteHeading":"img/headImg/1.png","blackScore":0,"whiteId":624,"whiteNum":3},{"result":1,"whiteScore":0,"blackHeading":"img/headImg/1.png","blackNum":2,"blackId":623,"whiteName":"销售3","blackName":"销售2","whiteHeading":"img/headImg/1.png","blackScore":0,"whiteId":624,"whiteNum":3},{"result":1,"whiteScore":0,"blackHeading":"img/headImg/1.png","blackNum":2,"blackId":623,"blackName":"销售2","blackScore":0},{"result":1,"whiteScore":0,"blackHeading":"img/headImg/1.png","blackNum":2,"blackId":623,"blackName":"销售2","blackScore":0},{"result":1,"whiteScore":0,"blackHeading":"img/headImg/1.png","blackNum":2,"blackId":623,"blackName":"销售2","blackScore":0}]
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
         * blackNum : 2
         * blackId : 623
         * whiteName : 销售3
         * blackName : 销售2
         * whiteHeading : img/headImg/1.png
         * blackScore : 0
         * whiteId : 624
         * whiteNum : 3
         */

        private int result;
        private String whiteScore;
        private String blackHeading;
        private String blackNum;
        private String blackId;
        private String whiteName;
        private String blackName;
        private String whiteHeading;
        private String blackScore;
        private String whiteId;
        private String whiteNum;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public String getWhiteScore() {
            return whiteScore;
        }

        public void setWhiteScore(String whiteScore) {
            this.whiteScore = whiteScore;
        }

        public String getBlackHeading() {
            return blackHeading;
        }

        public void setBlackHeading(String blackHeading) {
            this.blackHeading = blackHeading;
        }

        public String getBlackNum() {
            return blackNum;
        }

        public void setBlackNum(String blackNum) {
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

        public String getBlackScore() {
            return blackScore;
        }

        public void setBlackScore(String blackScore) {
            this.blackScore = blackScore;
        }

        public String getWhiteId() {
            return whiteId;
        }

        public void setWhiteId(String whiteId) {
            this.whiteId = whiteId;
        }

        public String getWhiteNum() {
            return whiteNum;
        }

        public void setWhiteNum(String whiteNum) {
            this.whiteNum = whiteNum;
        }
    }
}
