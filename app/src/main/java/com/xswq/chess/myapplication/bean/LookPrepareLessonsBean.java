package com.xswq.chess.myapplication.bean;

import java.util.List;

public class LookPrepareLessonsBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : [{"id":261,"myHandouts":"fsdf","prepareLessinStage":1,"handoutsImg":"https://test.xswq361.cn/files/47017df826544001a23091be5e049f17.jpg","paraKey":"虎口","tempId":41,"paraValue":"7"},{"id":261,"myHandouts":"","prepareLessinStage":1,"handoutsImg":"https://test.xswq361.cn/files/0a126cd3ace1408e86340d37e3552dbd.jpg","paraKey":"虎口","tempId":41,"paraValue":"7"},{"id":261,"myHandouts":"","prepareLessinStage":1,"handoutsImg":"https://test.xswq361.cn/files/b91687c60b084535aa400d33474c1007.jpg","paraKey":"虎口","tempId":41,"paraValue":"7"},{"id":261,"myHandouts":"","prepareLessinStage":1,"handoutsImg":"https://test.xswq361.cn/files/11407a5259b64be4b751b711a8773ded.jpg","paraKey":"虎口","tempId":41,"paraValue":"7"},{"id":261,"myHandouts":"","prepareLessinStage":1,"handoutsImg":"https://test.xswq361.cn/files/62740b8c71e54779b9a2e16facb77558.jpg","paraKey":"虎口","tempId":41,"paraValue":"7"},{"id":261,"myHandouts":"","prepareLessinStage":1,"handoutsImg":"https://test.xswq361.cn/files/204f0e8598f545ada8d1263f0c87aaf9.jpg","paraKey":"虎口","tempId":41,"paraValue":"7"},{"id":261,"myHandouts":"","prepareLessinStage":1,"handoutsImg":"https://test.xswq361.cn/files/38ffbe0ed8e44d3c969d5156c41e3925.jpg","paraKey":"虎口","tempId":41,"paraValue":"7"}]
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
         * id : 261
         * myHandouts : fsdf
         * prepareLessinStage : 1
         * handoutsImg : https://test.xswq361.cn/files/47017df826544001a23091be5e049f17.jpg
         * paraKey : 虎口
         * tempId : 41
         * paraValue : 7
         */

        private int id;
        private String myHandouts;
        private int prepareLessinStage;
        private String handoutsImg;
        private String paraKey;
        private int tempId;
        private String paraValue;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMyHandouts() {
            return myHandouts;
        }

        public void setMyHandouts(String myHandouts) {
            this.myHandouts = myHandouts;
        }

        public int getPrepareLessinStage() {
            return prepareLessinStage;
        }

        public void setPrepareLessinStage(int prepareLessinStage) {
            this.prepareLessinStage = prepareLessinStage;
        }

        public String getHandoutsImg() {
            return handoutsImg;
        }

        public void setHandoutsImg(String handoutsImg) {
            this.handoutsImg = handoutsImg;
        }

        public String getParaKey() {
            return paraKey;
        }

        public void setParaKey(String paraKey) {
            this.paraKey = paraKey;
        }

        public int getTempId() {
            return tempId;
        }

        public void setTempId(int tempId) {
            this.tempId = tempId;
        }

        public String getParaValue() {
            return paraValue;
        }

        public void setParaValue(String paraValue) {
            this.paraValue = paraValue;
        }
    }
}
