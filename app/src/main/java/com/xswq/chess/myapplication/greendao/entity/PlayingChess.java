package com.xswq.chess.myapplication.greendao.entity;

import java.util.List;

public class PlayingChess {


    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"total":4,"list":[{"BlackUsername":"高朝娜","WhiteUsername":"薛裕","BlackUserId":17958,"WhiteUserlevel":11,"BlackUserImAccid":"17958","BlackUserlevel":11,"WhiteUserImAccid":"2675","WhiteUserhead":"img/headImg/1.png","BeginTime":1568875770000,"BlackUserhead":"img/headImg/1.png","ID":196709,"WhiteUserId":2675,"ChessId":"fr4QW9Iqnm"},{"BlackUsername":"宫琳琳","WhiteUsername":"庞柔菁","BlackUserId":2877,"WhiteUserlevel":6,"BlackUserImAccid":"2877","BlackUserlevel":6,"WhiteUserImAccid":"754","WhiteUserhead":"img/headImg/1.png","BeginTime":1568873280000,"BlackUserhead":"img/headImg/1.png","ID":196703,"WhiteUserId":754,"ChessId":"fr1T5vNhvO"},{"BlackUsername":"吴蝶","WhiteUsername":"康娥","BlackUserId":18840,"WhiteUserlevel":12,"BlackUserImAccid":"18840","BlackUserlevel":12,"WhiteUserImAccid":"1650","WhiteUserhead":"img/headImg/1.png","BeginTime":1568872638000,"BlackUserhead":"img/headImg/1.png","ID":196696,"WhiteUserId":1650,"ChessId":"fr17FVAEfK"},{"BlackUsername":"崔娜娜","WhiteUsername":"元旭言","BlackUserId":15838,"WhiteUserlevel":3,"BlackUserImAccid":"15838","BlackUserlevel":3,"WhiteUserImAccid":"704","WhiteUserhead":"img/headImg/1.png","BeginTime":1568869608000,"BlackUserhead":"img/headImg/1.png","ID":196687,"WhiteUserId":704,"ChessId":"fqXvUaks1i"}],"pageNum":1,"pageSize":10,"size":4,"startRow":1,"endRow":4,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * total : 4
         * list : [{"BlackUsername":"高朝娜","WhiteUsername":"薛裕","BlackUserId":17958,"WhiteUserlevel":11,"BlackUserImAccid":"17958","BlackUserlevel":11,"WhiteUserImAccid":"2675","WhiteUserhead":"img/headImg/1.png","BeginTime":1568875770000,"BlackUserhead":"img/headImg/1.png","ID":196709,"WhiteUserId":2675,"ChessId":"fr4QW9Iqnm"},{"BlackUsername":"宫琳琳","WhiteUsername":"庞柔菁","BlackUserId":2877,"WhiteUserlevel":6,"BlackUserImAccid":"2877","BlackUserlevel":6,"WhiteUserImAccid":"754","WhiteUserhead":"img/headImg/1.png","BeginTime":1568873280000,"BlackUserhead":"img/headImg/1.png","ID":196703,"WhiteUserId":754,"ChessId":"fr1T5vNhvO"},{"BlackUsername":"吴蝶","WhiteUsername":"康娥","BlackUserId":18840,"WhiteUserlevel":12,"BlackUserImAccid":"18840","BlackUserlevel":12,"WhiteUserImAccid":"1650","WhiteUserhead":"img/headImg/1.png","BeginTime":1568872638000,"BlackUserhead":"img/headImg/1.png","ID":196696,"WhiteUserId":1650,"ChessId":"fr17FVAEfK"},{"BlackUsername":"崔娜娜","WhiteUsername":"元旭言","BlackUserId":15838,"WhiteUserlevel":3,"BlackUserImAccid":"15838","BlackUserlevel":3,"WhiteUserImAccid":"704","WhiteUserhead":"img/headImg/1.png","BeginTime":1568869608000,"BlackUserhead":"img/headImg/1.png","ID":196687,"WhiteUserId":704,"ChessId":"fqXvUaks1i"}]
         * pageNum : 1
         * pageSize : 10
         * size : 4
         * startRow : 1
         * endRow : 4
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
             * BlackUsername : 高朝娜
             * WhiteUsername : 薛裕
             * BlackUserId : 17958
             * WhiteUserlevel : 11
             * BlackUserImAccid : 17958
             * BlackUserlevel : 11
             * WhiteUserImAccid : 2675
             * WhiteUserhead : img/headImg/1.png
             * BeginTime : 1568875770000
             * BlackUserhead : img/headImg/1.png
             * ID : 196709
             * WhiteUserId : 2675
             * ChessId : fr4QW9Iqnm
             */

            private String BlackUsername;
            private String WhiteUsername;
            private String BlackUserId;
            private int WhiteUserlevel;
            private String BlackUserImAccid;
            private int BlackUserlevel;
            private String WhiteUserImAccid;
            private String WhiteUserhead;
            private long BeginTime;
            private String BlackUserhead;
            private int ID;
            private int WhiteUserId;
            private String ChessId;

            public String getBlackUsername() {
                return BlackUsername;
            }

            public void setBlackUsername(String BlackUsername) {
                this.BlackUsername = BlackUsername;
            }

            public String getWhiteUsername() {
                return WhiteUsername;
            }

            public void setWhiteUsername(String WhiteUsername) {
                this.WhiteUsername = WhiteUsername;
            }

            public String getBlackUserId() {
                return BlackUserId;
            }

            public void setBlackUserId(String BlackUserId) {
                this.BlackUserId = BlackUserId;
            }

            public int getWhiteUserlevel() {
                return WhiteUserlevel;
            }

            public void setWhiteUserlevel(int WhiteUserlevel) {
                this.WhiteUserlevel = WhiteUserlevel;
            }

            public String getBlackUserImAccid() {
                return BlackUserImAccid;
            }

            public void setBlackUserImAccid(String BlackUserImAccid) {
                this.BlackUserImAccid = BlackUserImAccid;
            }

            public int getBlackUserlevel() {
                return BlackUserlevel;
            }

            public void setBlackUserlevel(int BlackUserlevel) {
                this.BlackUserlevel = BlackUserlevel;
            }

            public String getWhiteUserImAccid() {
                return WhiteUserImAccid;
            }

            public void setWhiteUserImAccid(String WhiteUserImAccid) {
                this.WhiteUserImAccid = WhiteUserImAccid;
            }

            public String getWhiteUserhead() {
                return WhiteUserhead;
            }

            public void setWhiteUserhead(String WhiteUserhead) {
                this.WhiteUserhead = WhiteUserhead;
            }

            public long getBeginTime() {
                return BeginTime;
            }

            public void setBeginTime(long BeginTime) {
                this.BeginTime = BeginTime;
            }

            public String getBlackUserhead() {
                return BlackUserhead;
            }

            public void setBlackUserhead(String BlackUserhead) {
                this.BlackUserhead = BlackUserhead;
            }

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public int getWhiteUserId() {
                return WhiteUserId;
            }

            public void setWhiteUserId(int WhiteUserId) {
                this.WhiteUserId = WhiteUserId;
            }

            public String getChessId() {
                return ChessId;
            }

            public void setChessId(String ChessId) {
                this.ChessId = ChessId;
            }
        }
    }
}
