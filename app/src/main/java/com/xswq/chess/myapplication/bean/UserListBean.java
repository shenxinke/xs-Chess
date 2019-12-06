package com.xswq.chess.myapplication.bean;

import java.util.List;

public class UserListBean {


    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"total":11097,"list":[{"UserName":"范德萨发","ImOpts":3,"Level":15,"ID":37,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"唯一宝宝","ImOpts":3,"Level":10,"ID":168,"HeadImg":"img/headImg/new(5).png","MatchOpts":1},{"UserName":"杨红立","ImOpts":3,"Level":1,"ID":177,"HeadImg":"img/headImg/new(27).png","MatchOpts":1},{"UserName":"wxz","ImOpts":3,"Level":1,"ID":186,"HeadImg":"img/headImg/18.png","MatchOpts":1},{"UserName":"","ImOpts":3,"Level":12,"ID":188,"HeadImg":"img/headImg/2.png","MatchOpts":1},{"UserName":"style","ImOpts":1,"Level":6,"ID":190,"HeadImg":"img/headImg/7.png","MatchOpts":1},{"UserName":"张荣","ImOpts":3,"Level":11,"ID":191,"HeadImg":"img/headImg/new(77).png","MatchOpts":1},{"UserName":"周龙","ImOpts":1,"Level":6,"ID":195,"HeadImg":"img/headImg/new(3).png","MatchOpts":1},{"UserName":"hello","ImOpts":3,"Level":12,"ID":197,"HeadImg":"img/headImg/17.png","MatchOpts":1},{"UserName":"先手科技","ImOpts":3,"Level":30,"ID":198,"HeadImg":"img/headImg/new(47).png","MatchOpts":1},{"UserName":"刘晴","ImOpts":3,"Level":11,"ID":203,"HeadImg":"img/headImg/new(9).png","MatchOpts":1},{"UserName":"陈忠朋","ImOpts":1,"Level":11,"ID":209,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"李伟","ImOpts":3,"Level":4,"ID":245,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"测试111","ImOpts":1,"Level":2,"ID":246,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"小智","ImOpts":3,"Level":10,"ID":250,"HeadImg":"img/headImg/new(41).png","MatchOpts":1},{"UserName":"许卓君","ImOpts":3,"Level":0,"ID":251,"HeadImg":"img/headImg/13.png","MatchOpts":1},{"UserName":"郝明恩","ImOpts":3,"Level":27,"ID":391,"HeadImg":"img/shopStore/viphead6.png","MatchOpts":1},{"UserName":"米叔","ImOpts":3,"Level":11,"ID":392,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"孙一一","ImOpts":1,"Level":1,"ID":393,"HeadImg":"img/headImg/2.png","MatchOpts":1},{"UserName":" --","ImOpts":1,"Level":7,"ID":394,"HeadImg":"img/headImg/new(64).png","MatchOpts":1}],"pageNum":1,"pageSize":20,"size":20,"startRow":1,"endRow":20,"pages":555,"prePage":0,"nextPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6,7,8],"navigateFirstPage":1,"navigateLastPage":8,"firstPage":1,"lastPage":8}
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
         * total : 11097
         * list : [{"UserName":"范德萨发","ImOpts":3,"Level":15,"ID":37,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"唯一宝宝","ImOpts":3,"Level":10,"ID":168,"HeadImg":"img/headImg/new(5).png","MatchOpts":1},{"UserName":"杨红立","ImOpts":3,"Level":1,"ID":177,"HeadImg":"img/headImg/new(27).png","MatchOpts":1},{"UserName":"wxz","ImOpts":3,"Level":1,"ID":186,"HeadImg":"img/headImg/18.png","MatchOpts":1},{"UserName":"","ImOpts":3,"Level":12,"ID":188,"HeadImg":"img/headImg/2.png","MatchOpts":1},{"UserName":"style","ImOpts":1,"Level":6,"ID":190,"HeadImg":"img/headImg/7.png","MatchOpts":1},{"UserName":"张荣","ImOpts":3,"Level":11,"ID":191,"HeadImg":"img/headImg/new(77).png","MatchOpts":1},{"UserName":"周龙","ImOpts":1,"Level":6,"ID":195,"HeadImg":"img/headImg/new(3).png","MatchOpts":1},{"UserName":"hello","ImOpts":3,"Level":12,"ID":197,"HeadImg":"img/headImg/17.png","MatchOpts":1},{"UserName":"先手科技","ImOpts":3,"Level":30,"ID":198,"HeadImg":"img/headImg/new(47).png","MatchOpts":1},{"UserName":"刘晴","ImOpts":3,"Level":11,"ID":203,"HeadImg":"img/headImg/new(9).png","MatchOpts":1},{"UserName":"陈忠朋","ImOpts":1,"Level":11,"ID":209,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"李伟","ImOpts":3,"Level":4,"ID":245,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"测试111","ImOpts":1,"Level":2,"ID":246,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"小智","ImOpts":3,"Level":10,"ID":250,"HeadImg":"img/headImg/new(41).png","MatchOpts":1},{"UserName":"许卓君","ImOpts":3,"Level":0,"ID":251,"HeadImg":"img/headImg/13.png","MatchOpts":1},{"UserName":"郝明恩","ImOpts":3,"Level":27,"ID":391,"HeadImg":"img/shopStore/viphead6.png","MatchOpts":1},{"UserName":"米叔","ImOpts":3,"Level":11,"ID":392,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"孙一一","ImOpts":1,"Level":1,"ID":393,"HeadImg":"img/headImg/2.png","MatchOpts":1},{"UserName":" --","ImOpts":1,"Level":7,"ID":394,"HeadImg":"img/headImg/new(64).png","MatchOpts":1}]
         * pageNum : 1
         * pageSize : 20
         * size : 20
         * startRow : 1
         * endRow : 20
         * pages : 555
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
             * UserName : 范德萨发
             * ImOpts : 3
             * Level : 15
             * ID : 37
             * HeadImg : img/headImg/1.png
             * MatchOpts : 1
             */

            private String UserName;
            private int ImOpts;
            private int Level;
            private String ID;
            private String HeadImg;
            private int MatchOpts;

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public int getImOpts() {
                return ImOpts;
            }

            public void setImOpts(int ImOpts) {
                this.ImOpts = ImOpts;
            }

            public int getLevel() {
                return Level;
            }

            public void setLevel(int Level) {
                this.Level = Level;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getHeadImg() {
                return HeadImg;
            }

            public void setHeadImg(String HeadImg) {
                this.HeadImg = HeadImg;
            }

            public int getMatchOpts() {
                return MatchOpts;
            }

            public void setMatchOpts(int MatchOpts) {
                this.MatchOpts = MatchOpts;
            }
        }
    }
}
