package com.xswq.chess.myapplication.bean;

public class NewMobileVersionBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"id":30,"versionContent":"1. 校长端新增【打卡记录】&&2. 校长端新增【分润记录】&&3. 老师端新增【班级管理-课件设置】&&4. 个人中心新增【购买VIP】&&5. 个人中心新增【激活码兑换】&&6. 完善信息新增【机构选择项】&&7. 修复部分老机型棋盘无法加载的问题","incrementalUpdating":1,"forcedUpdating":1,"mobileType":2,"version":108}
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
         * id : 30
         * versionContent : 1. 校长端新增【打卡记录】&&2. 校长端新增【分润记录】&&3. 老师端新增【班级管理-课件设置】&&4. 个人中心新增【购买VIP】&&5. 个人中心新增【激活码兑换】&&6. 完善信息新增【机构选择项】&&7. 修复部分老机型棋盘无法加载的问题
         * incrementalUpdating : 1
         * forcedUpdating : 1
         * mobileType : 2
         * version : 108
         */

        private int id;
        private String versionContent;
        private int incrementalUpdating;
        private int forcedUpdating;
        private int mobileType;
        private int version;
        private String userId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVersionContent() {
            return versionContent;
        }

        public void setVersionContent(String versionContent) {
            this.versionContent = versionContent;
        }

        public int getIncrementalUpdating() {
            return incrementalUpdating;
        }

        public void setIncrementalUpdating(int incrementalUpdating) {
            this.incrementalUpdating = incrementalUpdating;
        }

        public int getForcedUpdating() {
            return forcedUpdating;
        }

        public void setForcedUpdating(int forcedUpdating) {
            this.forcedUpdating = forcedUpdating;
        }

        public int getMobileType() {
            return mobileType;
        }

        public void setMobileType(int mobileType) {
            this.mobileType = mobileType;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
