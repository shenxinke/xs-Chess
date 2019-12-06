package com.xswq.chess.myapplication.bean;

import java.util.List;

public class ListKnowLedgeBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : ["两眼活棋","倒扑","倒脱靴","做活要点","关山父子","关山父子专辑","关山父子专辑一","内部动手","利用先手","利用缺陷","利用缺陷逃子","制造倒扑","制造关门吃","制造双打吃","制造征吃","制造抱吃","制造枷吃","前田陈尔杰作集1","前田陈尔杰作集2","前田陈尔：百万人的诘棋","加田克司众妙诘棋1","加田克司众妙诘棋2","加田克司众妙诘棋3","加田克司众妙诘棋4","劫杀","劫活","占据要点","双叫吃","双打吃","双方互打","双方急所","双活","吃一线子","吃三线子","吃二线子","吃子","吃子做活","吴清源高级死活集","基础死活","天龙图","对杀基础","封锁","征吃","忍耐的计算力第1卷","忍耐的计算力第2卷","忍耐的计算力第3卷","扑和接不归","打劫","打吃","打吃逃子","扩大眼位","抓住弱点","抱吃","接不归","提子","数气","昭和的诘棋","杀棋基础","枷吃","桥本宇太郎 风和刻上","桥本宇太郎 风和刻下","桥本宇太郎 风和刻中","正确次序","死活常型","活棋基础","濑越宪作死活辞典","盘角曲四","破眼","破眼急所","综合吃子","缩小眼位","老鼠偷油","聚杀","胀死牛","藤泽秀行死活杰作集上级","藤泽秀行死活杰作集中级","藤泽秀行死活杰作集初级","藤泽秀行死活杰作集高级","逃子","金鸡独立","门吃","鬼手魔手","黑先做活","黑先打劫","黑先杀白"]
     */

    private ErrorBean error;
    private List<String> data;

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
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
}
