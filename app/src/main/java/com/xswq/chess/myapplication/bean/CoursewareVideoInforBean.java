package com.xswq.chess.myapplication.bean;

public class CoursewareVideoInforBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"ClassId":638,"ID":4,"CanWatch":"1,2,3,4,5,6","TeachId":1925}
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
         * ClassId : 638
         * ID : 4
         * CanWatch : 1,2,3,4,5,6
         * TeachId : 1925
         */

        private int ClassId;
        private int ID;
        private String CanWatch;
        private int TeachId;

        public int getClassId() {
            return ClassId;
        }

        public void setClassId(int ClassId) {
            this.ClassId = ClassId;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getCanWatch() {
            return CanWatch;
        }

        public void setCanWatch(String CanWatch) {
            this.CanWatch = CanWatch;
        }

        public int getTeachId() {
            return TeachId;
        }

        public void setTeachId(int TeachId) {
            this.TeachId = TeachId;
        }
    }
}
