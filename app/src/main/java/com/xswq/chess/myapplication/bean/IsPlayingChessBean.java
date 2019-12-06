package com.xswq.chess.myapplication.bean;

import com.google.gson.annotations.SerializedName;

public class IsPlayingChessBean {

    /**
     * error : {"returnCode":"10051","returnMessage":"当前有未完成的对局","returnUserMessage":"当前有未完成的对局","userRanking":null,"homeTable":null}
     * data : {"id":103496,"userid":623,"chessid":"8eAOTQO3Uk","chessseq":0,"begintime":1545633427000,"playtype":"1","playstate":"1","playflg":"1","layout":null,"selfSteps":null,"takeNum":null,"connect":null,"abscission":null,"calculate":null,"final":null,"overall":null,"emptiness":null,"turnOver":null}
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
         * returnCode : 10051
         * returnMessage : 当前有未完成的对局
         * returnUserMessage : 当前有未完成的对局
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
         * id : 103496
         * userid : 623
         * chessid : 8eAOTQO3Uk
         * chessseq : 0
         * begintime : 1545633427000
         * playtype : 1
         * playstate : 1
         * playflg : 1
         * layout : null
         * selfSteps : null
         * takeNum : null
         * connect : null
         * abscission : null
         * calculate : null
         * final : null
         * overall : null
         * emptiness : null
         * turnOver : null
         */

        private int id;
        private int userid;
        private String chessid;
        private int chessseq;
        private long begintime;
        private String playtype;
        private String playstate;
        private String playflg;
        private Object layout;
        private Object selfSteps;
        private Object takeNum;
        private Object connect;
        private Object abscission;
        private Object calculate;
        @SerializedName("final")
        private Object finalX;
        private Object overall;
        private Object emptiness;
        private Object turnOver;

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

        public Object getLayout() {
            return layout;
        }

        public void setLayout(Object layout) {
            this.layout = layout;
        }

        public Object getSelfSteps() {
            return selfSteps;
        }

        public void setSelfSteps(Object selfSteps) {
            this.selfSteps = selfSteps;
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

        public Object getCalculate() {
            return calculate;
        }

        public void setCalculate(Object calculate) {
            this.calculate = calculate;
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

        public Object getEmptiness() {
            return emptiness;
        }

        public void setEmptiness(Object emptiness) {
            this.emptiness = emptiness;
        }

        public Object getTurnOver() {
            return turnOver;
        }

        public void setTurnOver(Object turnOver) {
            this.turnOver = turnOver;
        }
    }
}