package com.xswq.chess.myapplication.bean;

import java.util.List;

public class ResultOfTheMatchBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : [{"dsList":[{"roundsNum":1,"stage":1,"dsUserName":"销售2","dsUserid":623},{"roundsNum":2,"stage":0,"dsUserName":"销售3","dsUserid":624},{"roundsNum":3,"stage":1,"dsUserName":null,"dsUserid":0}],"num":1,"blackNum":2,"userScore":4,"userName":null,"userId":622,"whiteNum":1},{"dsList":[{"roundsNum":1,"stage":0,"dsUserName":"销售1","dsUserid":622},{"roundsNum":2,"stage":1,"dsUserName":null,"dsUserid":0},{"roundsNum":3,"stage":1,"dsUserName":"销售3","dsUserid":624}],"num":2,"blackNum":2,"userScore":4,"userName":null,"userId":623,"whiteNum":1},{"dsList":[{"roundsNum":1,"stage":1,"dsUserName":null,"dsUserid":0},{"roundsNum":2,"stage":1,"dsUserName":"销售1","dsUserid":622},{"roundsNum":3,"stage":0,"dsUserName":"销售2","dsUserid":623}],"num":3,"blackNum":2,"userScore":4,"userName":null,"userId":624,"whiteNum":1}]
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
         * dsList : [{"roundsNum":1,"stage":1,"dsUserName":"销售2","dsUserid":623},{"roundsNum":2,"stage":0,"dsUserName":"销售3","dsUserid":624},{"roundsNum":3,"stage":1,"dsUserName":null,"dsUserid":0}]
         * num : 1
         * blackNum : 2
         * userScore : 4
         * userName : null
         * userId : 622
         * whiteNum : 1
         */

        private int num;
        private int blackNum;
        private int userScore;
        private String userName;
        private int userId;
        private int whiteNum;
        private int dsScore;
        private String sumCount;

        public String getSumCount() {
            return sumCount;
        }

        public void setSumCount(String sumCount) {
            this.sumCount = sumCount;
        }

        public int getDsScore() {
            return dsScore;
        }

        public void setDsScore(int dsScore) {
            this.dsScore = dsScore;
        }

        private List<DsListBean> dsList;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getBlackNum() {
            return blackNum;
        }

        public void setBlackNum(int blackNum) {
            this.blackNum = blackNum;
        }

        public int getUserScore() {
            return userScore;
        }

        public void setUserScore(int userScore) {
            this.userScore = userScore;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhiteNum() {
            return whiteNum;
        }

        public void setWhiteNum(int whiteNum) {
            this.whiteNum = whiteNum;
        }

        public List<DsListBean> getDsList() {
            return dsList;
        }

        public void setDsList(List<DsListBean> dsList) {
            this.dsList = dsList;
        }

        public static class DsListBean {
            /**
             * roundsNum : 1
             * stage : 1
             * dsUserName : 销售2
             * dsUserid : 623
             */

            private int roundsNum;
            private int stage;
            private String dsUserName;
            private int dsUserid;

            public int getRoundsNum() {
                return roundsNum;
            }

            public void setRoundsNum(int roundsNum) {
                this.roundsNum = roundsNum;
            }

            public int getStage() {
                return stage;
            }

            public void setStage(int stage) {
                this.stage = stage;
            }

            public String getDsUserName() {
                return dsUserName;
            }

            public void setDsUserName(String dsUserName) {
                this.dsUserName = dsUserName;
            }

            public int getDsUserid() {
                return dsUserid;
            }

            public void setDsUserid(int dsUserid) {
                this.dsUserid = dsUserid;
            }
        }
    }
}
