package com.xswq.chess.myapplication.bean;

public class UserIsNullBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"id":289,"groupId":261,"roundsId":1303,"chessId":"f3wA9K7p0k","blackUserId":1560,"whiteUserId":1561,"result":3,"judge":0,"judgeTime":null}
     */

    private ErrorBean error;
    private DataBean data;

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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
         * id : 289
         * groupId : 261
         * roundsId : 1303
         * chessId : f3wA9K7p0k
         * blackUserId : 1560
         * whiteUserId : 1561
         * result : 3
         * judge : 0
         * judgeTime : null
         */

        private int id;
        private int groupId;
        private int roundsId;
        private String chessId;
        private String blackUserId;
        private String whiteUserId;
        private int result;
        private int judge;
        private Object judgeTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public int getRoundsId() {
            return roundsId;
        }

        public void setRoundsId(int roundsId) {
            this.roundsId = roundsId;
        }

        public String getChessId() {
            return chessId;
        }

        public void setChessId(String chessId) {
            this.chessId = chessId;
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

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public int getJudge() {
            return judge;
        }

        public void setJudge(int judge) {
            this.judge = judge;
        }

        public Object getJudgeTime() {
            return judgeTime;
        }

        public void setJudgeTime(Object judgeTime) {
            this.judgeTime = judgeTime;
        }
    }
}
