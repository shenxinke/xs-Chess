package com.xswq.chess.myapplication.bean;

public class ConpetitionContentBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"id":8,"matchName":"滴滴","matchIntroduce":"描述","matchPattern":3,"invitedSch":"00ddceb7888b4b34b3679875d6c0334f","startTime":1563811200000,"endTime":1563897600000,"stage":0,"classId":null,"createTime":1563956396000,"createUser":1516,"createOrg":"f073e1f25cfb4f17b0a621b7d061b717","groupList":null}
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
         * id : 8
         * matchName : 滴滴
         * matchIntroduce : 描述
         * matchPattern : 3
         * invitedSch : 00ddceb7888b4b34b3679875d6c0334f
         * startTime : 1563811200000
         * endTime : 1563897600000
         * stage : 0
         * classId : null
         * createTime : 1563956396000
         * createUser : 1516
         * createOrg : f073e1f25cfb4f17b0a621b7d061b717
         * groupList : null
         */

        private String id;
        private String matchName;
        private String matchIntroduce;
        private int matchPattern;
        private String invitedSch;
        private long startTime;
        private long endTime;
        private int stage;
        private Object classId;
        private long createTime;
        private int createUser;
        private String createOrg;
        private Object groupList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMatchName() {
            return matchName;
        }

        public void setMatchName(String matchName) {
            this.matchName = matchName;
        }

        public String getMatchIntroduce() {
            return matchIntroduce;
        }

        public void setMatchIntroduce(String matchIntroduce) {
            this.matchIntroduce = matchIntroduce;
        }

        public int getMatchPattern() {
            return matchPattern;
        }

        public void setMatchPattern(int matchPattern) {
            this.matchPattern = matchPattern;
        }

        public String getInvitedSch() {
            return invitedSch;
        }

        public void setInvitedSch(String invitedSch) {
            this.invitedSch = invitedSch;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getStage() {
            return stage;
        }

        public void setStage(int stage) {
            this.stage = stage;
        }

        public Object getClassId() {
            return classId;
        }

        public void setClassId(Object classId) {
            this.classId = classId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getCreateUser() {
            return createUser;
        }

        public void setCreateUser(int createUser) {
            this.createUser = createUser;
        }

        public String getCreateOrg() {
            return createOrg;
        }

        public void setCreateOrg(String createOrg) {
            this.createOrg = createOrg;
        }

        public Object getGroupList() {
            return groupList;
        }

        public void setGroupList(Object groupList) {
            this.groupList = groupList;
        }
    }
}
