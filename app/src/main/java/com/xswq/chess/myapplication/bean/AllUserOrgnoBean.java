package com.xswq.chess.myapplication.bean;

import java.util.List;

public class AllUserOrgnoBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : [{"WeChat":"","ImAccid":"1516","UserName":"卢胜杰","RealName":"","AgentId":0,"MatchOpts":1,"UserSourse":0,"Birthday":"2019-05-27","ImOpts":3,"PassWord":"f5bb0c8de146c67b44babbf4e6584cc0","LoginTime":1551081994000,"QQ":"","Mobile":"18301518508","ID":1516,"OrgNo":"01d04cf965654d0f9307e1afd2a01bee","Client":1,"HeadImg":"img/headImg/1.png","Sex":1,"LoginState":0,"UserType":1,"ImToken":"qisheng123","Integral":10047,"ExperienceTime":1610640000000,"ManyFlg":"1","Sina":"","Email":"","State":0,"CreateTime":1530261143000,"Address":"北京市:东城区","SoundOpts":3}]
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
         * WeChat :
         * ImAccid : 1516
         * UserName : 卢胜杰
         * RealName :
         * AgentId : 0
         * MatchOpts : 1
         * UserSourse : 0
         * Birthday : 2019-05-27
         * ImOpts : 3
         * PassWord : f5bb0c8de146c67b44babbf4e6584cc0
         * LoginTime : 1551081994000
         * QQ :
         * Mobile : 18301518508
         * ID : 1516
         * OrgNo : 01d04cf965654d0f9307e1afd2a01bee
         * Client : 1
         * HeadImg : img/headImg/1.png
         * Sex : 1
         * LoginState : 0
         * UserType : 1
         * ImToken : qisheng123
         * Integral : 10047
         * ExperienceTime : 1610640000000
         * ManyFlg : 1
         * Sina :
         * Email :
         * State : 0
         * CreateTime : 1530261143000
         * Address : 北京市:东城区
         * SoundOpts : 3
         */
        private String UserName;
        private String ID;

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

    }
}
