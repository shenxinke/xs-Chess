package com.xswq.chess.myapplication.bean;

import java.util.List;

public class ListInstitutionsBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"total":5748,"list":[{"UserName":"葛环","ImOpts":3,"Level":1,"ID":662,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"周峰晨","ImOpts":3,"Level":1,"ID":663,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"许俊","ImOpts":3,"Level":1,"ID":664,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"范园","ImOpts":3,"Level":1,"ID":665,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"苗艺姬","ImOpts":3,"Level":1,"ID":669,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"何姬","ImOpts":3,"Level":2,"ID":670,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"李涛","ImOpts":3,"Level":2,"ID":671,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"胡毓","ImOpts":3,"Level":2,"ID":672,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"何艳燕","ImOpts":3,"Level":2,"ID":673,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"孔瑶岚","ImOpts":3,"Level":2,"ID":674,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"唐莲","ImOpts":3,"Level":2,"ID":675,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"喻壮哲","ImOpts":3,"Level":2,"ID":676,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"鲁之奇","ImOpts":3,"Level":2,"ID":677,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"舒萍","ImOpts":3,"Level":2,"ID":678,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"茅民壮","ImOpts":3,"Level":2,"ID":679,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"水家","ImOpts":3,"Level":2,"ID":680,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"陶艳","ImOpts":3,"Level":2,"ID":681,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"赵舒","ImOpts":3,"Level":2,"ID":682,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"孟月","ImOpts":3,"Level":2,"ID":683,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"蔡青荣","ImOpts":3,"Level":2,"ID":684,"HeadImg":"img/headImg/1.png","MatchOpts":1}],"pageNum":1,"pageSize":20,"size":20,"startRow":1,"endRow":20,"pages":288,"prePage":0,"nextPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6,7,8],"navigateFirstPage":1,"navigateLastPage":8,"firstPage":1,"lastPage":8}
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
         * total : 5748
         * list : [{"UserName":"葛环","ImOpts":3,"Level":1,"ID":662,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"周峰晨","ImOpts":3,"Level":1,"ID":663,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"许俊","ImOpts":3,"Level":1,"ID":664,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"范园","ImOpts":3,"Level":1,"ID":665,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"苗艺姬","ImOpts":3,"Level":1,"ID":669,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"何姬","ImOpts":3,"Level":2,"ID":670,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"李涛","ImOpts":3,"Level":2,"ID":671,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"胡毓","ImOpts":3,"Level":2,"ID":672,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"何艳燕","ImOpts":3,"Level":2,"ID":673,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"孔瑶岚","ImOpts":3,"Level":2,"ID":674,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"唐莲","ImOpts":3,"Level":2,"ID":675,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"喻壮哲","ImOpts":3,"Level":2,"ID":676,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"鲁之奇","ImOpts":3,"Level":2,"ID":677,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"舒萍","ImOpts":3,"Level":2,"ID":678,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"茅民壮","ImOpts":3,"Level":2,"ID":679,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"水家","ImOpts":3,"Level":2,"ID":680,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"陶艳","ImOpts":3,"Level":2,"ID":681,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"赵舒","ImOpts":3,"Level":2,"ID":682,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"孟月","ImOpts":3,"Level":2,"ID":683,"HeadImg":"img/headImg/1.png","MatchOpts":1},{"UserName":"蔡青荣","ImOpts":3,"Level":2,"ID":684,"HeadImg":"img/headImg/1.png","MatchOpts":1}]
         * pageNum : 1
         * pageSize : 20
         * size : 20
         * startRow : 1
         * endRow : 20
         * pages : 288
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
             * UserName : 葛环
             * ImOpts : 3
             * Level : 1
             * ID : 662
             * HeadImg : img/headImg/1.png
             * MatchOpts : 1
             */

            private String UserName;
            private int ImOpts;
            private int Level;
            private int ID;
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

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
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
