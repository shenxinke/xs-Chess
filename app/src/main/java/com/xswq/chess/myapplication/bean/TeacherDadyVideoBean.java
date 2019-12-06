package com.xswq.chess.myapplication.bean;

public class TeacherDadyVideoBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"JuniorVideoname":"\"老师测试\"","Type":1,"ID":214,"VideoUrl":"b5ce3152-eb39-4abd-ad96-e57bc09bf242.mp4","VideoName":"TV"}
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
         * JuniorVideoname : "老师测试"
         * Type : 1
         * ID : 214
         * VideoUrl : b5ce3152-eb39-4abd-ad96-e57bc09bf242.mp4
         * VideoName : TV
         */

        private String JuniorVideoname;
        private int Type;
        private String ID;
        private String VideoUrl;
        private String VideoName;

        public String getJuniorVideoname() {
            return JuniorVideoname;
        }

        public void setJuniorVideoname(String JuniorVideoname) {
            this.JuniorVideoname = JuniorVideoname;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getVideoUrl() {
            return VideoUrl;
        }

        public void setVideoUrl(String VideoUrl) {
            this.VideoUrl = VideoUrl;
        }

        public String getVideoName() {
            return VideoName;
        }

        public void setVideoName(String VideoName) {
            this.VideoName = VideoName;
        }
    }
}
