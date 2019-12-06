package com.xswq.chess.myapplication.bean;

public class PreparelessonsByIdNewBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"prepareLessinStage":1,"paraKey":"虎口","sarget":"1","handouts1":"1","difficulty":"3","handouts2":"2","prepares":"4","handouts3":"3","prepareLessin_tempId":41,"paraValue":"7","headLine":7,"handoutsImg7":"https://test.xswq361.cn/files/38ffbe0ed8e44d3c969d5156c41e3925.jpg","handouts7":"7","handouts6":"6","handouts5":"5","handouts4":"4","handoutsImg1":"https://test.xswq361.cn/files/47017df826544001a23091be5e049f17.jpg","emphasis":"2","handoutsImg2":"https://test.xswq361.cn/files/0a126cd3ace1408e86340d37e3552dbd.jpg","handoutsImg5":"https://test.xswq361.cn/files/62740b8c71e54779b9a2e16facb77558.jpg","handoutsImg6":"https://test.xswq361.cn/files/204f0e8598f545ada8d1263f0c87aaf9.jpg","handoutsImg3":"https://test.xswq361.cn/files/b91687c60b084535aa400d33474c1007.jpg","handoutsImg4":"https://test.xswq361.cn/files/11407a5259b64be4b751b711a8773ded.jpg"}
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
         * prepareLessinStage : 1
         * paraKey : 虎口
         * sarget : 1
         * handouts1 : 1
         * difficulty : 3
         * handouts2 : 2
         * prepares : 4
         * handouts3 : 3
         * prepareLessin_tempId : 41
         * paraValue : 7
         * headLine : 7
         * handoutsImg7 : https://test.xswq361.cn/files/38ffbe0ed8e44d3c969d5156c41e3925.jpg
         * handouts7 : 7
         * handouts6 : 6
         * handouts5 : 5
         * handouts4 : 4
         * handoutsImg1 : https://test.xswq361.cn/files/47017df826544001a23091be5e049f17.jpg
         * emphasis : 2
         * handoutsImg2 : https://test.xswq361.cn/files/0a126cd3ace1408e86340d37e3552dbd.jpg
         * handoutsImg5 : https://test.xswq361.cn/files/62740b8c71e54779b9a2e16facb77558.jpg
         * handoutsImg6 : https://test.xswq361.cn/files/204f0e8598f545ada8d1263f0c87aaf9.jpg
         * handoutsImg3 : https://test.xswq361.cn/files/b91687c60b084535aa400d33474c1007.jpg
         * handoutsImg4 : https://test.xswq361.cn/files/11407a5259b64be4b751b711a8773ded.jpg
         */

        private int prepareLessinStage;
        private String paraKey;
        private String sarget;
        private String handouts1;
        private String difficulty;
        private String handouts2;
        private String prepares;
        private String handouts3;
        private int prepareLessin_tempId;
        private String paraValue;
        private int headLine;
        private String handoutsImg7;
        private String handouts7;
        private String handouts6;
        private String handouts5;
        private String handouts4;
        private String handoutsImg1;
        private String emphasis;
        private String handoutsImg2;
        private String handoutsImg5;
        private String handoutsImg6;
        private String handoutsImg3;
        private String handoutsImg4;

        public int getPrepareLessinStage() {
            return prepareLessinStage;
        }

        public void setPrepareLessinStage(int prepareLessinStage) {
            this.prepareLessinStage = prepareLessinStage;
        }

        public String getParaKey() {
            return paraKey;
        }

        public void setParaKey(String paraKey) {
            this.paraKey = paraKey;
        }

        public String getSarget() {
            return sarget;
        }

        public void setSarget(String sarget) {
            this.sarget = sarget;
        }

        public String getHandouts1() {
            return handouts1;
        }

        public void setHandouts1(String handouts1) {
            this.handouts1 = handouts1;
        }

        public String getDifficulty() {
            return difficulty;
        }

        public void setDifficulty(String difficulty) {
            this.difficulty = difficulty;
        }

        public String getHandouts2() {
            return handouts2;
        }

        public void setHandouts2(String handouts2) {
            this.handouts2 = handouts2;
        }

        public String getPrepares() {
            return prepares;
        }

        public void setPrepares(String prepares) {
            this.prepares = prepares;
        }

        public String getHandouts3() {
            return handouts3;
        }

        public void setHandouts3(String handouts3) {
            this.handouts3 = handouts3;
        }

        public int getPrepareLessin_tempId() {
            return prepareLessin_tempId;
        }

        public void setPrepareLessin_tempId(int prepareLessin_tempId) {
            this.prepareLessin_tempId = prepareLessin_tempId;
        }

        public String getParaValue() {
            return paraValue;
        }

        public void setParaValue(String paraValue) {
            this.paraValue = paraValue;
        }

        public int getHeadLine() {
            return headLine;
        }

        public void setHeadLine(int headLine) {
            this.headLine = headLine;
        }

        public String getHandoutsImg7() {
            return handoutsImg7;
        }

        public void setHandoutsImg7(String handoutsImg7) {
            this.handoutsImg7 = handoutsImg7;
        }

        public String getHandouts7() {
            return handouts7;
        }

        public void setHandouts7(String handouts7) {
            this.handouts7 = handouts7;
        }

        public String getHandouts6() {
            return handouts6;
        }

        public void setHandouts6(String handouts6) {
            this.handouts6 = handouts6;
        }

        public String getHandouts5() {
            return handouts5;
        }

        public void setHandouts5(String handouts5) {
            this.handouts5 = handouts5;
        }

        public String getHandouts4() {
            return handouts4;
        }

        public void setHandouts4(String handouts4) {
            this.handouts4 = handouts4;
        }

        public String getHandoutsImg1() {
            return handoutsImg1;
        }

        public void setHandoutsImg1(String handoutsImg1) {
            this.handoutsImg1 = handoutsImg1;
        }

        public String getEmphasis() {
            return emphasis;
        }

        public void setEmphasis(String emphasis) {
            this.emphasis = emphasis;
        }

        public String getHandoutsImg2() {
            return handoutsImg2;
        }

        public void setHandoutsImg2(String handoutsImg2) {
            this.handoutsImg2 = handoutsImg2;
        }

        public String getHandoutsImg5() {
            return handoutsImg5;
        }

        public void setHandoutsImg5(String handoutsImg5) {
            this.handoutsImg5 = handoutsImg5;
        }

        public String getHandoutsImg6() {
            return handoutsImg6;
        }

        public void setHandoutsImg6(String handoutsImg6) {
            this.handoutsImg6 = handoutsImg6;
        }

        public String getHandoutsImg3() {
            return handoutsImg3;
        }

        public void setHandoutsImg3(String handoutsImg3) {
            this.handoutsImg3 = handoutsImg3;
        }

        public String getHandoutsImg4() {
            return handoutsImg4;
        }

        public void setHandoutsImg4(String handoutsImg4) {
            this.handoutsImg4 = handoutsImg4;
        }
    }
}
