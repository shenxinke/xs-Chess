package com.xswq.chess.myapplication.bean;

import java.util.List;

public class QualificationsDataBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"total":480,"list":[{"startTime":"2018-12-17 17:16:48","userId":5355,"Level":0,"UserName":"先手围棋","endTime":"2018-12-24 17:16:48"},{"startTime":"2018-12-17 17:16:48","userId":5356,"Level":0,"UserName":"先手围棋","endTime":"2018-12-24 17:16:48"},{"startTime":"2018-12-17 17:16:48","userId":5357,"Level":0,"UserName":"先手围棋","endTime":"2018-12-24 17:16:48"},{"startTime":"2018-12-17 17:16:48","userId":5358,"Level":0,"UserName":"先手围棋","endTime":"2018-12-24 17:16:48"},{"startTime":"2018-12-17 17:16:48","userId":5363,"Level":0,"UserName":"宋曦","endTime":"2018-12-24 17:16:48"},{"startTime":"2018-12-17 17:16:48","userId":5773,"Level":0,"UserName":"王建国","endTime":"2018-12-24 17:16:48"},{"startTime":"2018-12-17 17:16:48","userId":5776,"Level":1,"UserName":"涵涵","endTime":"2018-12-24 17:16:48"},{"startTime":"2018-12-17 17:16:48","userId":5781,"Level":0,"UserName":"赵老师","endTime":"2018-12-24 17:16:48"},{"startTime":"2018-12-17 17:16:48","userId":5783,"Level":0,"UserName":"张继强","endTime":"2018-12-24 17:16:48"},{"startTime":"2018-12-17 17:16:48","userId":5784,"Level":0,"UserName":"徐浩","endTime":"2018-12-24 17:16:48"}],"pageNum":1,"pageSize":10,"size":10,"startRow":1,"endRow":10,"pages":48,"prePage":0,"nextPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6,7,8],"navigateFirstPage":1,"navigateLastPage":8,"firstPage":1,"lastPage":8}
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
         * total : 480
         * list : [{"startTime":"2018-12-17 17:16:48","userId":5355,"Level":0,"UserName":"先手围棋","endTime":"2018-12-24 17:16:48"},{"startTime":"2018-12-17 17:16:48","userId":5356,"Level":0,"UserName":"先手围棋","endTime":"2018-12-24 17:16:48"},{"startTime":"2018-12-17 17:16:48","userId":5357,"Level":0,"UserName":"先手围棋","endTime":"2018-12-24 17:16:48"},{"startTime":"2018-12-17 17:16:48","userId":5358,"Level":0,"UserName":"先手围棋","endTime":"2018-12-24 17:16:48"},{"startTime":"2018-12-17 17:16:48","userId":5363,"Level":0,"UserName":"宋曦","endTime":"2018-12-24 17:16:48"},{"startTime":"2018-12-17 17:16:48","userId":5773,"Level":0,"UserName":"王建国","endTime":"2018-12-24 17:16:48"},{"startTime":"2018-12-17 17:16:48","userId":5776,"Level":1,"UserName":"涵涵","endTime":"2018-12-24 17:16:48"},{"startTime":"2018-12-17 17:16:48","userId":5781,"Level":0,"UserName":"赵老师","endTime":"2018-12-24 17:16:48"},{"startTime":"2018-12-17 17:16:48","userId":5783,"Level":0,"UserName":"张继强","endTime":"2018-12-24 17:16:48"},{"startTime":"2018-12-17 17:16:48","userId":5784,"Level":0,"UserName":"徐浩","endTime":"2018-12-24 17:16:48"}]
         * pageNum : 1
         * pageSize : 10
         * size : 10
         * startRow : 1
         * endRow : 10
         * pages : 48
         * prePage : 0
         * nextPage : 2
         * isFirstPage : true
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1,2,3,4,5,6,7,8]
         * navigateFirstPage : 1
         * navigateLastPage : 8
         * firstPage : 1
         * lastPage : 8
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
             * startTime : 2018-12-17 17:16:48
             * userId : 5355
             * Level : 0
             * UserName : 先手围棋
             * endTime : 2018-12-24 17:16:48
             */

            private String startTime;
            private int userId;
            private String Level;
            private String prepareCount;
            private String chessCount;
            private String jobCount;
            private String UserName;
            private String endTime;


            public String getLevel() {
                return Level;
            }

            public void setLevel(String level) {
                Level = level;
            }

            public String getPrepareCount() {
                return prepareCount;
            }

            public void setPrepareCount(String prepareCount) {
                this.prepareCount = prepareCount;
            }

            public String getChessCount() {
                return chessCount;
            }

            public void setChessCount(String chessCount) {
                this.chessCount = chessCount;
            }

            public String getJobCount() {
                return jobCount;
            }

            public void setJobCount(String jobCount) {
                this.jobCount = jobCount;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }
        }
    }
}
