package com.xswq.chess.myapplication.bean;

import java.util.List;

public class PresidentListBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"total":42,"list":[{"UserName":"唯一宝宝","chessCount":12,"prepareCount":0,"dayNum":0,"Level":10,"userId":168,"jobCount":0},{"UserName":"杨红立","chessCount":9,"prepareCount":0,"dayNum":0,"Level":1,"userId":177,"jobCount":0},{"UserName":"hello","chessCount":10,"prepareCount":0,"dayNum":0,"Level":12,"userId":197,"jobCount":0},{"UserName":"袁星月","chessCount":2,"prepareCount":0,"dayNum":0,"Level":0,"userId":400,"jobCount":0},{"UserName":"销售9","chessCount":1,"prepareCount":0,"dayNum":0,"Level":11,"userId":630,"jobCount":0},{"UserName":"销售10","chessCount":30,"prepareCount":0,"dayNum":0,"Level":13,"userId":631,"jobCount":0},{"UserName":"销售11","chessCount":2,"prepareCount":0,"dayNum":0,"Level":11,"userId":632,"jobCount":0},{"UserName":"销售12","chessCount":3,"prepareCount":0,"dayNum":0,"Level":11,"userId":633,"jobCount":0},{"UserName":"销售13","chessCount":1,"prepareCount":0,"dayNum":0,"Level":12,"userId":634,"jobCount":0},{"UserName":"销售14","chessCount":4,"prepareCount":0,"dayNum":0,"Level":12,"userId":635,"jobCount":0}],"pageNum":1,"pageSize":10,"size":10,"startRow":1,"endRow":10,"pages":5,"prePage":0,"nextPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3,4,5],"navigateFirstPage":1,"navigateLastPage":5,"lastPage":5,"firstPage":1}
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
         * total : 42
         * list : [{"UserName":"唯一宝宝","chessCount":12,"prepareCount":0,"dayNum":0,"Level":10,"userId":168,"jobCount":0},{"UserName":"杨红立","chessCount":9,"prepareCount":0,"dayNum":0,"Level":1,"userId":177,"jobCount":0},{"UserName":"hello","chessCount":10,"prepareCount":0,"dayNum":0,"Level":12,"userId":197,"jobCount":0},{"UserName":"袁星月","chessCount":2,"prepareCount":0,"dayNum":0,"Level":0,"userId":400,"jobCount":0},{"UserName":"销售9","chessCount":1,"prepareCount":0,"dayNum":0,"Level":11,"userId":630,"jobCount":0},{"UserName":"销售10","chessCount":30,"prepareCount":0,"dayNum":0,"Level":13,"userId":631,"jobCount":0},{"UserName":"销售11","chessCount":2,"prepareCount":0,"dayNum":0,"Level":11,"userId":632,"jobCount":0},{"UserName":"销售12","chessCount":3,"prepareCount":0,"dayNum":0,"Level":11,"userId":633,"jobCount":0},{"UserName":"销售13","chessCount":1,"prepareCount":0,"dayNum":0,"Level":12,"userId":634,"jobCount":0},{"UserName":"销售14","chessCount":4,"prepareCount":0,"dayNum":0,"Level":12,"userId":635,"jobCount":0}]
         * pageNum : 1
         * pageSize : 10
         * size : 10
         * startRow : 1
         * endRow : 10
         * pages : 5
         * prePage : 0
         * nextPage : 2
         * isFirstPage : true
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1,2,3,4,5]
         * navigateFirstPage : 1
         * navigateLastPage : 5
         * lastPage : 5
         * firstPage : 1
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
        private int lastPage;
        private int firstPage;
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

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
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
             * UserName : 唯一宝宝
             * chessCount : 12
             * prepareCount : 0
             * dayNum : 0
             * Level : 10
             * userId : 168
             * jobCount : 0
             */

            private String UserName;
            private String chessCount;
            private int prepareCount;
            private String dayNum;
            private String questionNum;
            private int Level;
            private int userId;
            private int jobCount;

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getChessCount() {
                return chessCount;
            }

            public void setChessCount(String chessCount) {
                this.chessCount = chessCount;
            }

            public int getPrepareCount() {
                return prepareCount;
            }

            public void setPrepareCount(int prepareCount) {
                this.prepareCount = prepareCount;
            }

            public String getDayNum() {
                return dayNum;
            }

            public void setDayNum(String dayNum) {
                this.dayNum = dayNum;
            }

            public int getLevel() {
                return Level;
            }

            public void setLevel(int Level) {
                this.Level = Level;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getJobCount() {
                return jobCount;
            }

            public void setJobCount(int jobCount) {
                this.jobCount = jobCount;
            }

            public String getQuestionNum() {
                return questionNum;
            }

            public void setQuestionNum(String questionNum) {
                this.questionNum = questionNum;
            }
        }
    }
}
