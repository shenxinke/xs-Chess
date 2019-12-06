package com.xswq.chess.myapplication.bean;

import java.util.List;

public class AddFriendsBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : [{"inviteName":"建行徐","beInvitedUser":1516,"beInviteHeadImg":"img/headImg/1.png","createTime":1565767911000,"state":2,"message":"12345","inviteUser":1748,"beInvitedName":"卢","inviteHeadImg":"img/headImg/1.png"}]
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
         * inviteName : 建行徐
         * beInvitedUser : 1516
         * beInviteHeadImg : img/headImg/1.png
         * createTime : 1565767911000
         * state : 2
         * message : 12345
         * inviteUser : 1748
         * beInvitedName : 卢
         * inviteHeadImg : img/headImg/1.png
         */

        private String inviteName;
        private String beInvitedUser;
        private String beInviteHeadImg;
        private long createTime;
        private int state;
        private String message;
        private String inviteUser;
        private String beInvitedName;
        private String inviteHeadImg;

        public String getInviteName() {
            return inviteName;
        }

        public void setInviteName(String inviteName) {
            this.inviteName = inviteName;
        }

        public String getBeInvitedUser() {
            return beInvitedUser;
        }

        public void setBeInvitedUser(String beInvitedUser) {
            this.beInvitedUser = beInvitedUser;
        }

        public String getBeInviteHeadImg() {
            return beInviteHeadImg;
        }

        public void setBeInviteHeadImg(String beInviteHeadImg) {
            this.beInviteHeadImg = beInviteHeadImg;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getInviteUser() {
            return inviteUser;
        }

        public void setInviteUser(String inviteUser) {
            this.inviteUser = inviteUser;
        }

        public String getBeInvitedName() {
            return beInvitedName;
        }

        public void setBeInvitedName(String beInvitedName) {
            this.beInvitedName = beInvitedName;
        }

        public String getInviteHeadImg() {
            return inviteHeadImg;
        }

        public void setInviteHeadImg(String inviteHeadImg) {
            this.inviteHeadImg = inviteHeadImg;
        }
    }
}
