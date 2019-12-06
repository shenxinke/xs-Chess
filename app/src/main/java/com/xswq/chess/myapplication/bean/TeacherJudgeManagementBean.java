package com.xswq.chess.myapplication.bean;

import java.util.List;

public class TeacherJudgeManagementBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : [{"blackUserId":624,"result":2,"roundsNum":1,"whiteUserName":"销售20","groupId":1,"id":1,"whiteUserId":641,"judge":2,"judgeTime":1562746306000,"roundsId":1,"chessId":1111,"blackUserName":"销售3"},{"blackUserId":624,"result":2,"roundsNum":2,"whiteUserName":"罗政","groupId":1,"id":4,"whiteUserId":643,"judge":2,"judgeTime":1563184062000,"roundsId":2,"chessId":121212,"blackUserName":"销售3"},{"blackUserId":642,"result":1,"roundsNum":2,"whiteUserName":"销售20","groupId":1,"id":5,"whiteUserId":641,"judge":2,"judgeTime":1563184082000,"roundsId":2,"chessId":12321,"blackUserName":"曹树飞"}]
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
         * blackUserId : 624
         * result : 2
         * roundsNum : 1
         * whiteUserName : 销售20
         * groupId : 1
         * id : 1
         * whiteUserId : 641
         * judge : 2
         * judgeTime : 1562746306000
         * roundsId : 1
         * chessId : 1111
         * blackUserName : 销售3
         */

        private int blackUserId;
        private int result;
        private int roundsNum;
        private String whiteUserName;
        private int groupId;
        private int id;
        private int whiteUserId;
        private int judge;
        private long judgeTime;
        private int roundsId;
        private int chessId;
        private String blackUserName;
        private String matchId;
        private String matchName;
        private String groupName;


        public String getMatchName() {
            return matchName;
        }

        public void setMatchName(String matchName) {
            this.matchName = matchName;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getMatchId() {
            return matchId;
        }

        public void setMatchId(String matchId) {
            this.matchId = matchId;
        }

        public int getBlackUserId() {
            return blackUserId;
        }

        public void setBlackUserId(int blackUserId) {
            this.blackUserId = blackUserId;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public int getRoundsNum() {
            return roundsNum;
        }

        public void setRoundsNum(int roundsNum) {
            this.roundsNum = roundsNum;
        }

        public String getWhiteUserName() {
            return whiteUserName;
        }

        public void setWhiteUserName(String whiteUserName) {
            this.whiteUserName = whiteUserName;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getWhiteUserId() {
            return whiteUserId;
        }

        public void setWhiteUserId(int whiteUserId) {
            this.whiteUserId = whiteUserId;
        }

        public int getJudge() {
            return judge;
        }

        public void setJudge(int judge) {
            this.judge = judge;
        }

        public long getJudgeTime() {
            return judgeTime;
        }

        public void setJudgeTime(long judgeTime) {
            this.judgeTime = judgeTime;
        }

        public int getRoundsId() {
            return roundsId;
        }

        public void setRoundsId(int roundsId) {
            this.roundsId = roundsId;
        }

        public int getChessId() {
            return chessId;
        }

        public void setChessId(int chessId) {
            this.chessId = chessId;
        }

        public String getBlackUserName() {
            return blackUserName;
        }

        public void setBlackUserName(String blackUserName) {
            this.blackUserName = blackUserName;
        }
    }
}
