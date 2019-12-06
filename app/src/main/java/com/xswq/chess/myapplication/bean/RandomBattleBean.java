package com.xswq.chess.myapplication.bean;

import com.google.gson.annotations.SerializedName;

public class RandomBattleBean {


    /**
     * error : {"returnCode":"2","returnMessage":"随机棋局进行中","returnUserMessage":"随机棋局进行中","userRanking":null,"homeTable":null}
     * data : {"id":109653,"userid":20060,"chessid":"gvtrP13Rq8","chessseq":0,"begintime":1572332138000,"playtype":"1","playstate":"1","playflg":"1","userName":null,"layout":null,"final":null,"overall":null,"turnOver":null,"emptiness":null,"takeNum":null,"connect":null,"abscission":null,"selfSteps":null,"calculate":null}
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
         * returnCode : 2
         * returnMessage : 随机棋局进行中
         * returnUserMessage : 随机棋局进行中
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
         * id : 109653
         * userid : 20060
         * chessid : gvtrP13Rq8
         * chessseq : 0
         * begintime : 1572332138000
         * playtype : 1
         * playstate : 1
         * playflg : 1
         * userName : null
         * layout : null
         * final : null
         * overall : null
         * turnOver : null
         * emptiness : null
         * takeNum : null
         * connect : null
         * abscission : null
         * selfSteps : null
         * calculate : null
         */

        private int id;
        private int userid;
        private String chessid;
        private int chessseq;
        private long begintime;
        private String playtype;
        private String playstate;
        private String playflg;
        private Object userName;
        private Object layout;
        @SerializedName("final")
        private Object finalX;
        private Object overall;
        private Object turnOver;
        private Object emptiness;
        private Object takeNum;
        private Object connect;
        private Object abscission;
        private Object selfSteps;
        private Object calculate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getChessid() {
            return chessid;
        }

        public void setChessid(String chessid) {
            this.chessid = chessid;
        }

        public int getChessseq() {
            return chessseq;
        }

        public void setChessseq(int chessseq) {
            this.chessseq = chessseq;
        }

        public long getBegintime() {
            return begintime;
        }

        public void setBegintime(long begintime) {
            this.begintime = begintime;
        }

        public String getPlaytype() {
            return playtype;
        }

        public void setPlaytype(String playtype) {
            this.playtype = playtype;
        }

        public String getPlaystate() {
            return playstate;
        }

        public void setPlaystate(String playstate) {
            this.playstate = playstate;
        }

        public String getPlayflg() {
            return playflg;
        }

        public void setPlayflg(String playflg) {
            this.playflg = playflg;
        }

        public Object getUserName() {
            return userName;
        }

        public void setUserName(Object userName) {
            this.userName = userName;
        }

        public Object getLayout() {
            return layout;
        }

        public void setLayout(Object layout) {
            this.layout = layout;
        }

        public Object getFinalX() {
            return finalX;
        }

        public void setFinalX(Object finalX) {
            this.finalX = finalX;
        }

        public Object getOverall() {
            return overall;
        }

        public void setOverall(Object overall) {
            this.overall = overall;
        }

        public Object getTurnOver() {
            return turnOver;
        }

        public void setTurnOver(Object turnOver) {
            this.turnOver = turnOver;
        }

        public Object getEmptiness() {
            return emptiness;
        }

        public void setEmptiness(Object emptiness) {
            this.emptiness = emptiness;
        }

        public Object getTakeNum() {
            return takeNum;
        }

        public void setTakeNum(Object takeNum) {
            this.takeNum = takeNum;
        }

        public Object getConnect() {
            return connect;
        }

        public void setConnect(Object connect) {
            this.connect = connect;
        }

        public Object getAbscission() {
            return abscission;
        }

        public void setAbscission(Object abscission) {
            this.abscission = abscission;
        }

        public Object getSelfSteps() {
            return selfSteps;
        }

        public void setSelfSteps(Object selfSteps) {
            this.selfSteps = selfSteps;
        }

        public Object getCalculate() {
            return calculate;
        }

        public void setCalculate(Object calculate) {
            this.calculate = calculate;
        }
    }
}
