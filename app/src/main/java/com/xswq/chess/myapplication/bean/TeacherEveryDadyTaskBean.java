package com.xswq.chess.myapplication.bean;

import java.util.List;

public class TeacherEveryDadyTaskBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"total":60,"list":[{"id":2,"dayNum":1,"questionId":"41624,41625,41626","playChess":"3","course":"1","createTime":1545102327000,"createUser":401,"stage":1,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":3,"dayNum":2,"questionId":"","playChess":"2","course":null,"createTime":1544781166000,"createUser":1516,"stage":1,"level":"15K","content":"内容","title":"标题","achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":4,"dayNum":3,"questionId":"","playChess":null,"course":"3","createTime":1544582521000,"createUser":391,"stage":1,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":5,"dayNum":4,"questionId":"","playChess":null,"course":"","createTime":1544514221000,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":6,"dayNum":5,"questionId":"","playChess":null,"course":null,"createTime":1544514544000,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":7,"dayNum":6,"questionId":null,"playChess":"","course":"","createTime":1544514554000,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":8,"dayNum":7,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":9,"dayNum":8,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":10,"dayNum":9,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":11,"dayNum":10,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":12,"dayNum":11,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":13,"dayNum":12,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":14,"dayNum":13,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":15,"dayNum":14,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":16,"dayNum":15,"questionId":"","playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":17,"dayNum":16,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":18,"dayNum":17,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":19,"dayNum":18,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":20,"dayNum":19,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":21,"dayNum":20,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":22,"dayNum":21,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":23,"dayNum":22,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":24,"dayNum":23,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":25,"dayNum":24,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":26,"dayNum":25,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":27,"dayNum":26,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":28,"dayNum":27,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":29,"dayNum":28,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":30,"dayNum":29,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":31,"dayNum":30,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":32,"dayNum":31,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":33,"dayNum":32,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":34,"dayNum":33,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":35,"dayNum":34,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":36,"dayNum":35,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":37,"dayNum":36,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":38,"dayNum":37,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":39,"dayNum":38,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":40,"dayNum":39,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":41,"dayNum":40,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":42,"dayNum":41,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":43,"dayNum":42,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":44,"dayNum":43,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":45,"dayNum":44,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":46,"dayNum":45,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":47,"dayNum":46,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":48,"dayNum":47,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":49,"dayNum":48,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":50,"dayNum":49,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":51,"dayNum":50,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":52,"dayNum":51,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":53,"dayNum":52,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":54,"dayNum":53,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":55,"dayNum":54,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":56,"dayNum":55,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":57,"dayNum":56,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":58,"dayNum":57,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":59,"dayNum":58,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":60,"dayNum":59,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":61,"dayNum":60,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null}],"pageNum":1,"pageSize":100,"size":60,"startRow":1,"endRow":60,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * total : 60
         * list : [{"id":2,"dayNum":1,"questionId":"41624,41625,41626","playChess":"3","course":"1","createTime":1545102327000,"createUser":401,"stage":1,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":3,"dayNum":2,"questionId":"","playChess":"2","course":null,"createTime":1544781166000,"createUser":1516,"stage":1,"level":"15K","content":"内容","title":"标题","achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":4,"dayNum":3,"questionId":"","playChess":null,"course":"3","createTime":1544582521000,"createUser":391,"stage":1,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":5,"dayNum":4,"questionId":"","playChess":null,"course":"","createTime":1544514221000,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":6,"dayNum":5,"questionId":"","playChess":null,"course":null,"createTime":1544514544000,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":7,"dayNum":6,"questionId":null,"playChess":"","course":"","createTime":1544514554000,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":8,"dayNum":7,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":9,"dayNum":8,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":10,"dayNum":9,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":11,"dayNum":10,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":12,"dayNum":11,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":13,"dayNum":12,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":14,"dayNum":13,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":15,"dayNum":14,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":16,"dayNum":15,"questionId":"","playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":17,"dayNum":16,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":18,"dayNum":17,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":19,"dayNum":18,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":20,"dayNum":19,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":21,"dayNum":20,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":22,"dayNum":21,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":23,"dayNum":22,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":24,"dayNum":23,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":25,"dayNum":24,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":26,"dayNum":25,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":27,"dayNum":26,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":28,"dayNum":27,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":29,"dayNum":28,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":30,"dayNum":29,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":31,"dayNum":30,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":32,"dayNum":31,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":33,"dayNum":32,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":34,"dayNum":33,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":35,"dayNum":34,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":36,"dayNum":35,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":37,"dayNum":36,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":38,"dayNum":37,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":39,"dayNum":38,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":40,"dayNum":39,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":41,"dayNum":40,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":42,"dayNum":41,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":43,"dayNum":42,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":44,"dayNum":43,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":45,"dayNum":44,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":46,"dayNum":45,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":47,"dayNum":46,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":48,"dayNum":47,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":49,"dayNum":48,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":50,"dayNum":49,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":51,"dayNum":50,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":52,"dayNum":51,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":53,"dayNum":52,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":54,"dayNum":53,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":55,"dayNum":54,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":56,"dayNum":55,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":57,"dayNum":56,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":58,"dayNum":57,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":59,"dayNum":58,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":60,"dayNum":59,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null},{"id":61,"dayNum":60,"questionId":null,"playChess":null,"course":null,"createTime":null,"createUser":0,"stage":0,"level":null,"content":null,"title":null,"achieveState":0,"achievePlayChessNum":null,"achieveQuestion":null,"achieveCourse":null}]
         * pageNum : 1
         * pageSize : 100
         * size : 60
         * startRow : 1
         * endRow : 60
         * pages : 1
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * firstPage : 1
         * lastPage : 1
         */

        private int total;
        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int firstPage;
        private int lastPage;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * id : 2
             * dayNum : 1
             * questionId : 41624,41625,41626
             * playChess : 3
             * course : 1
             * createTime : 1545102327000
             * createUser : 401
             * stage : 1
             * level : null
             * content : null
             * title : null
             * achieveState : 0
             * achievePlayChessNum : null
             * achieveQuestion : null
             * achieveCourse : null
             */

            private int id;
            private int dayNum;
            private String questionId;
            private String playChess;
            private String course;
            private long createTime;
            private int createUser;
            private int stage;
            private String level;
            private String content;
            private String title;
            private int achieveState;
            private Object achievePlayChessNum;
            private Object achieveQuestion;
            private Object achieveCourse;
            private boolean isSelect;
            private String questionsTest;
            private String score;
            private String questionType;

            public String getQuestionType() {
                return questionType;
            }

            public void setQuestionType(String questionType) {
                this.questionType = questionType;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getQuestionsTest() {
                return questionsTest;
            }

            public void setQuestionsTest(String questionsTest) {
                this.questionsTest = questionsTest;
            }

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getDayNum() {
                return dayNum;
            }

            public void setDayNum(int dayNum) {
                this.dayNum = dayNum;
            }

            public String getQuestionId() {
                return questionId;
            }

            public void setQuestionId(String questionId) {
                this.questionId = questionId;
            }

            public String getPlayChess() {
                return playChess;
            }

            public void setPlayChess(String playChess) {
                this.playChess = playChess;
            }

            public String getCourse() {
                return course;
            }

            public void setCourse(String course) {
                this.course = course;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getCreateUser() {
                return createUser;
            }

            public void setCreateUser(int createUser) {
                this.createUser = createUser;
            }

            public int getStage() {
                return stage;
            }

            public void setStage(int stage) {
                this.stage = stage;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getAchieveState() {
                return achieveState;
            }

            public void setAchieveState(int achieveState) {
                this.achieveState = achieveState;
            }

            public Object getAchievePlayChessNum() {
                return achievePlayChessNum;
            }

            public void setAchievePlayChessNum(Object achievePlayChessNum) {
                this.achievePlayChessNum = achievePlayChessNum;
            }

            public Object getAchieveQuestion() {
                return achieveQuestion;
            }

            public void setAchieveQuestion(Object achieveQuestion) {
                this.achieveQuestion = achieveQuestion;
            }

            public Object getAchieveCourse() {
                return achieveCourse;
            }

            public void setAchieveCourse(Object achieveCourse) {
                this.achieveCourse = achieveCourse;
            }
        }
    }
}
