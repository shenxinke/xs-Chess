package com.xswq.chess.myapplication.bean;

import java.util.List;

public class ClassInfoListBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"total":1,"list":[{"flag":0,"orgName":"马晓春围棋望京棋校","count":2,"schoolName":null,"userName":null,"progressCount":0,"minLevel":null,"maxLevel":null,"startTime":null,"endTime":null,"bit":2,"totalCount":24,"peopleNum":0,"id":1132,"className":"123","createUser":1516,"createTime":1560923804000,"branchNo":null,"organizationNo":"01521613c13d40e3a6ed872feb3e9800"}],"pageNum":1,"pageSize":100,"size":1,"startRow":1,"endRow":1,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * total : 1
         * list : [{"flag":0,"orgName":"马晓春围棋望京棋校","count":2,"schoolName":null,"userName":null,"progressCount":0,"minLevel":null,"maxLevel":null,"startTime":null,"endTime":null,"bit":2,"totalCount":24,"peopleNum":0,"id":1132,"className":"123","createUser":1516,"createTime":1560923804000,"branchNo":null,"organizationNo":"01521613c13d40e3a6ed872feb3e9800"}]
         * pageNum : 1
         * pageSize : 100
         * size : 1
         * startRow : 1
         * endRow : 1
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
             * flag : 0
             * orgName : 马晓春围棋望京棋校
             * count : 2
             * schoolName : null
             * userName : null
             * progressCount : 0
             * minLevel : null
             * maxLevel : null
             * startTime : null
             * endTime : null
             * bit : 2
             * totalCount : 24
             * peopleNum : 0
             * id : 1132
             * className : 123
             * createUser : 1516
             * createTime : 1560923804000
             * branchNo : null
             * organizationNo : 01521613c13d40e3a6ed872feb3e9800
             */

            private int flag;
            private String orgName;
            private int count;
            private Object schoolName;
            private Object userName;
            private int progressCount;
            private Object minLevel;
            private Object maxLevel;
            private Object startTime;
            private Object endTime;
            private int bit;
            private int totalCount;
            private int peopleNum;
            private String id;
            private String className;
            private int createUser;
            private long createTime;
            private Object branchNo;
            private String organizationNo;

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public String getOrgName() {
                return orgName;
            }

            public void setOrgName(String orgName) {
                this.orgName = orgName;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public Object getSchoolName() {
                return schoolName;
            }

            public void setSchoolName(Object schoolName) {
                this.schoolName = schoolName;
            }

            public Object getUserName() {
                return userName;
            }

            public void setUserName(Object userName) {
                this.userName = userName;
            }

            public int getProgressCount() {
                return progressCount;
            }

            public void setProgressCount(int progressCount) {
                this.progressCount = progressCount;
            }

            public Object getMinLevel() {
                return minLevel;
            }

            public void setMinLevel(Object minLevel) {
                this.minLevel = minLevel;
            }

            public Object getMaxLevel() {
                return maxLevel;
            }

            public void setMaxLevel(Object maxLevel) {
                this.maxLevel = maxLevel;
            }

            public Object getStartTime() {
                return startTime;
            }

            public void setStartTime(Object startTime) {
                this.startTime = startTime;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }

            public int getBit() {
                return bit;
            }

            public void setBit(int bit) {
                this.bit = bit;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }

            public int getPeopleNum() {
                return peopleNum;
            }

            public void setPeopleNum(int peopleNum) {
                this.peopleNum = peopleNum;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getClassName() {
                return className;
            }

            public void setClassName(String className) {
                this.className = className;
            }

            public int getCreateUser() {
                return createUser;
            }

            public void setCreateUser(int createUser) {
                this.createUser = createUser;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public Object getBranchNo() {
                return branchNo;
            }

            public void setBranchNo(Object branchNo) {
                this.branchNo = branchNo;
            }

            public String getOrganizationNo() {
                return organizationNo;
            }

            public void setOrganizationNo(String organizationNo) {
                this.organizationNo = organizationNo;
            }
        }
    }
}
