package com.xswq.chess.myapplication.bean;

public class SetCourseSyllabusBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"CanWatch":"1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33","CreateTime":1561370765000,"allCourse":"1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35","ID":10,"OrgNo":"f073e1f25cfb4f17b0a621b7d061b717"}
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
         * CanWatch : 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33
         * CreateTime : 1561370765000
         * allCourse : 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35
         * ID : 10
         * OrgNo : f073e1f25cfb4f17b0a621b7d061b717
         */

        private String CanWatch;
        private long CreateTime;
        private String allCourse;
        private int ID;
        private String OrgNo;

        public String getCanWatch() {
            return CanWatch;
        }

        public void setCanWatch(String CanWatch) {
            this.CanWatch = CanWatch;
        }

        public long getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(long CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getAllCourse() {
            return allCourse;
        }

        public void setAllCourse(String allCourse) {
            this.allCourse = allCourse;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getOrgNo() {
            return OrgNo;
        }

        public void setOrgNo(String OrgNo) {
            this.OrgNo = OrgNo;
        }
    }
}
