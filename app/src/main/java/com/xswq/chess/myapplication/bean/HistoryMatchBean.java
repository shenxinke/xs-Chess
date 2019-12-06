package com.xswq.chess.myapplication.bean;

import java.util.List;

public class HistoryMatchBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"total":10,"list":[{"WhiteUserId":663,"BlackUserName":"申新科","WhiteUserName":"周峰晨","EndTime":"2019-08-28 11:41:15","WhiteHead":"img/headImg/1.png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":3,"whiteUserLevel":1,"PlayResult":1,"BlackUserId":4960,"ID":82305,"evaluateState":0,"chessId":9691},{"WhiteUserId":1516,"BlackUserName":"申新科","WhiteUserName":"卢","EndTime":"2019-08-27 14:55:26","WhiteHead":"img/headImg/1.png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":2,"whiteUserLevel":13,"PlayResult":4,"BlackUserId":4960,"ID":82280,"evaluateState":0,"chessId":9666},{"WhiteUserId":167,"BlackUserName":"申新科","WhiteUserName":"就","EndTime":"2019-08-27 10:24:41","WhiteHead":"img/headImg/new(19).png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":2,"whiteUserLevel":1,"PlayResult":4,"BlackUserId":4960,"ID":82251,"evaluateState":0,"chessId":9644},{"WhiteUserId":665,"BlackUserName":"申新科","WhiteUserName":"范园","EndTime":"2019-08-23 14:18:37","WhiteHead":"img/headImg/1.png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":3,"whiteUserLevel":1,"PlayResult":4,"BlackUserId":4960,"ID":82220,"evaluateState":0,"chessId":9631},{"WhiteUserId":669,"BlackUserName":"申新科","WhiteUserName":"苗艺姬","EndTime":"2019-08-23 14:16:38","WhiteHead":"img/headImg/1.png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":3,"whiteUserLevel":1,"PlayResult":2,"BlackUserId":4960,"ID":82219,"evaluateState":0,"chessId":9630},{"WhiteUserId":662,"BlackUserName":"申新科","WhiteUserName":"葛环","EndTime":"2019-08-23 11:52:20","WhiteHead":"img/headImg/1.png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":3,"whiteUserLevel":1,"PlayResult":4,"BlackUserId":4960,"ID":82215,"evaluateState":0,"chessId":9629},{"WhiteUserId":663,"BlackUserName":"申新科","WhiteUserName":"周峰晨","EndTime":"2019-08-23 11:23:27","WhiteHead":"img/headImg/1.png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":3,"whiteUserLevel":1,"PlayResult":4,"BlackUserId":4960,"ID":82211,"evaluateState":0,"chessId":9628},{"WhiteUserId":664,"BlackUserName":"申新科","WhiteUserName":"许俊","EndTime":"2019-08-23 11:20:41","WhiteHead":"img/headImg/1.png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":3,"whiteUserLevel":1,"PlayResult":4,"BlackUserId":4960,"ID":82210,"evaluateState":0,"chessId":9627},{"WhiteUserId":663,"BlackUserName":"申新科","WhiteUserName":"周峰晨","EndTime":"2019-08-23 11:13:24","WhiteHead":"img/headImg/1.png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":3,"whiteUserLevel":1,"PlayResult":4,"BlackUserId":4960,"ID":82209,"evaluateState":0,"chessId":9626},{"WhiteUserId":663,"BlackUserName":"申新科","WhiteUserName":"周峰晨","EndTime":"2019-08-23 11:10:44","WhiteHead":"img/headImg/1.png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":3,"whiteUserLevel":1,"PlayResult":4,"BlackUserId":4960,"ID":82206,"evaluateState":0,"chessId":9625}],"pageNum":1,"pageSize":50,"size":10,"startRow":1,"endRow":10,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * total : 10
         * list : [{"WhiteUserId":663,"BlackUserName":"申新科","WhiteUserName":"周峰晨","EndTime":"2019-08-28 11:41:15","WhiteHead":"img/headImg/1.png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":3,"whiteUserLevel":1,"PlayResult":1,"BlackUserId":4960,"ID":82305,"evaluateState":0,"chessId":9691},{"WhiteUserId":1516,"BlackUserName":"申新科","WhiteUserName":"卢","EndTime":"2019-08-27 14:55:26","WhiteHead":"img/headImg/1.png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":2,"whiteUserLevel":13,"PlayResult":4,"BlackUserId":4960,"ID":82280,"evaluateState":0,"chessId":9666},{"WhiteUserId":167,"BlackUserName":"申新科","WhiteUserName":"就","EndTime":"2019-08-27 10:24:41","WhiteHead":"img/headImg/new(19).png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":2,"whiteUserLevel":1,"PlayResult":4,"BlackUserId":4960,"ID":82251,"evaluateState":0,"chessId":9644},{"WhiteUserId":665,"BlackUserName":"申新科","WhiteUserName":"范园","EndTime":"2019-08-23 14:18:37","WhiteHead":"img/headImg/1.png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":3,"whiteUserLevel":1,"PlayResult":4,"BlackUserId":4960,"ID":82220,"evaluateState":0,"chessId":9631},{"WhiteUserId":669,"BlackUserName":"申新科","WhiteUserName":"苗艺姬","EndTime":"2019-08-23 14:16:38","WhiteHead":"img/headImg/1.png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":3,"whiteUserLevel":1,"PlayResult":2,"BlackUserId":4960,"ID":82219,"evaluateState":0,"chessId":9630},{"WhiteUserId":662,"BlackUserName":"申新科","WhiteUserName":"葛环","EndTime":"2019-08-23 11:52:20","WhiteHead":"img/headImg/1.png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":3,"whiteUserLevel":1,"PlayResult":4,"BlackUserId":4960,"ID":82215,"evaluateState":0,"chessId":9629},{"WhiteUserId":663,"BlackUserName":"申新科","WhiteUserName":"周峰晨","EndTime":"2019-08-23 11:23:27","WhiteHead":"img/headImg/1.png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":3,"whiteUserLevel":1,"PlayResult":4,"BlackUserId":4960,"ID":82211,"evaluateState":0,"chessId":9628},{"WhiteUserId":664,"BlackUserName":"申新科","WhiteUserName":"许俊","EndTime":"2019-08-23 11:20:41","WhiteHead":"img/headImg/1.png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":3,"whiteUserLevel":1,"PlayResult":4,"BlackUserId":4960,"ID":82210,"evaluateState":0,"chessId":9627},{"WhiteUserId":663,"BlackUserName":"申新科","WhiteUserName":"周峰晨","EndTime":"2019-08-23 11:13:24","WhiteHead":"img/headImg/1.png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":3,"whiteUserLevel":1,"PlayResult":4,"BlackUserId":4960,"ID":82209,"evaluateState":0,"chessId":9626},{"WhiteUserId":663,"BlackUserName":"申新科","WhiteUserName":"周峰晨","EndTime":"2019-08-23 11:10:44","WhiteHead":"img/headImg/1.png","blackUserLevel":1,"BlackHead":"img/shopStore/viphead3.png","GameType":3,"whiteUserLevel":1,"PlayResult":4,"BlackUserId":4960,"ID":82206,"evaluateState":0,"chessId":9625}]
         * pageNum : 1
         * pageSize : 50
         * size : 10
         * startRow : 1
         * endRow : 10
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
             * WhiteUserId : 663
             * BlackUserName : 申新科
             * WhiteUserName : 周峰晨
             * EndTime : 2019-08-28 11:41:15
             * WhiteHead : img/headImg/1.png
             * blackUserLevel : 1
             * BlackHead : img/shopStore/viphead3.png
             * GameType : 3
             * whiteUserLevel : 1
             * PlayResult : 1
             * BlackUserId : 4960
             * ID : 82305
             * evaluateState : 0
             * chessId : 9691
             */

            private int WhiteUserId;
            private String BlackUserName;
            private String WhiteUserName;
            private String EndTime;
            private String WhiteHead;
            private int blackUserLevel;
            private String BlackHead;
            private int GameType;
            private int whiteUserLevel;
            private int PlayResult;
            private int BlackUserId;
            private int ID;
            private int evaluateState;
            private int chessId;
            private int collect;

            public int getCollect() {
                return collect;
            }

            public void setCollect(int collect) {
                this.collect = collect;
            }

            public int getWhiteUserId() {
                return WhiteUserId;
            }

            public void setWhiteUserId(int WhiteUserId) {
                this.WhiteUserId = WhiteUserId;
            }

            public String getBlackUserName() {
                return BlackUserName;
            }

            public void setBlackUserName(String BlackUserName) {
                this.BlackUserName = BlackUserName;
            }

            public String getWhiteUserName() {
                return WhiteUserName;
            }

            public void setWhiteUserName(String WhiteUserName) {
                this.WhiteUserName = WhiteUserName;
            }

            public String getEndTime() {
                return EndTime;
            }

            public void setEndTime(String EndTime) {
                this.EndTime = EndTime;
            }

            public String getWhiteHead() {
                return WhiteHead;
            }

            public void setWhiteHead(String WhiteHead) {
                this.WhiteHead = WhiteHead;
            }

            public int getBlackUserLevel() {
                return blackUserLevel;
            }

            public void setBlackUserLevel(int blackUserLevel) {
                this.blackUserLevel = blackUserLevel;
            }

            public String getBlackHead() {
                return BlackHead;
            }

            public void setBlackHead(String BlackHead) {
                this.BlackHead = BlackHead;
            }

            public int getGameType() {
                return GameType;
            }

            public void setGameType(int GameType) {
                this.GameType = GameType;
            }

            public int getWhiteUserLevel() {
                return whiteUserLevel;
            }

            public void setWhiteUserLevel(int whiteUserLevel) {
                this.whiteUserLevel = whiteUserLevel;
            }

            public int getPlayResult() {
                return PlayResult;
            }

            public void setPlayResult(int PlayResult) {
                this.PlayResult = PlayResult;
            }

            public int getBlackUserId() {
                return BlackUserId;
            }

            public void setBlackUserId(int BlackUserId) {
                this.BlackUserId = BlackUserId;
            }

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public int getEvaluateState() {
                return evaluateState;
            }

            public void setEvaluateState(int evaluateState) {
                this.evaluateState = evaluateState;
            }

            public int getChessId() {
                return chessId;
            }

            public void setChessId(int chessId) {
                this.chessId = chessId;
            }
        }
    }
}
