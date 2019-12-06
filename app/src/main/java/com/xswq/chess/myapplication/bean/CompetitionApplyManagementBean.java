package com.xswq.chess.myapplication.bean;

import java.util.List;

public class CompetitionApplyManagementBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : [{"GroupName":"组别9","UserName":"销售1","orgNo":"f073e1f25cfb4f17b0a621b7d061b717","UserId":622,"CreateTime":1564450392000,"Num":1,"ClassName":"123","ClassId":1132,"Level":13,"ID":24,"GroupId":44},{"GroupName":"组别9","UserName":"销售2","orgNo":"f073e1f25cfb4f17b0a621b7d061b717","UserId":623,"CreateTime":1564450395000,"Num":2,"ClassName":"123","ClassId":1132,"Level":11,"ID":25,"GroupId":44},{"GroupName":"组别9","UserName":"销售3","orgNo":"f073e1f25cfb4f17b0a621b7d061b717","UserId":624,"CreateTime":1564450397000,"Num":3,"ClassName":"xian","ClassId":1133,"Level":11,"ID":26,"GroupId":44}]
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
         * GroupName : 组别9
         * UserName : 销售1
         * orgNo : f073e1f25cfb4f17b0a621b7d061b717
         * UserId : 622
         * CreateTime : 1564450392000
         * Num : 1
         * ClassName : 123
         * ClassId : 1132
         * Level : 13
         * ID : 24
         * GroupId : 44
         */

        private String GroupName;
        private String UserName;
        private String orgNo;
        private int UserId;
        private long CreateTime;
        private int Num;
        private String ClassName;
        private int ClassId;
        private int Level;
        private int ID;
        private int GroupId;

        public String getGroupName() {
            return GroupName;
        }

        public void setGroupName(String GroupName) {
            this.GroupName = GroupName;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getOrgNo() {
            return orgNo;
        }

        public void setOrgNo(String orgNo) {
            this.orgNo = orgNo;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public long getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(long CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getNum() {
            return Num;
        }

        public void setNum(int Num) {
            this.Num = Num;
        }

        public String getClassName() {
            return ClassName;
        }

        public void setClassName(String ClassName) {
            this.ClassName = ClassName;
        }

        public int getClassId() {
            return ClassId;
        }

        public void setClassId(int ClassId) {
            this.ClassId = ClassId;
        }

        public int getLevel() {
            return Level;
        }

        public void setLevel(int Level) {
            this.Level = Level;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getGroupId() {
            return GroupId;
        }

        public void setGroupId(int GroupId) {
            this.GroupId = GroupId;
        }
    }
}
