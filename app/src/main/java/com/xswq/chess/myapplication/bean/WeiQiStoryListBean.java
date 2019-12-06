package com.xswq.chess.myapplication.bean;

import java.io.Serializable;
import java.util.List;

public class WeiQiStoryListBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"total":2,"list":[{"createTime":1544579599000,"storyImg":"https://test.xswq361.cn/files/gs/image/gs1_04.jpg;https://test.xswq361.cn/files/gs/image/gs1_01.jpg;https://test.xswq361.cn/files/gs/image/gs1_02.jpg;https://test.xswq361.cn/files/gs/image/gs1_03.jpg","storyName":"尧造围棋教子丹朱","changeTime":"0;51;70;146","ID":1,"soundTime":"03:14","storySound":"https://test.xswq361.cn/files/gs/audio/gs1.mp3"},{"createTime":1544754390000,"storyImg":"https://test.xswq361.cn/files/gs/image/gs1_04.jpg","storyName":"尧造围棋教子丹朱2","changeTime":"0","ID":3,"soundTime":"03:14","storySound":"https://test.xswq361.cn/files/gs/audio/gs1.mp3"}],"pageNum":1,"pageSize":50,"size":2,"startRow":1,"endRow":2,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * total : 2
         * list : [{"createTime":1544579599000,"storyImg":"https://test.xswq361.cn/files/gs/image/gs1_04.jpg;https://test.xswq361.cn/files/gs/image/gs1_01.jpg;https://test.xswq361.cn/files/gs/image/gs1_02.jpg;https://test.xswq361.cn/files/gs/image/gs1_03.jpg","storyName":"尧造围棋教子丹朱","changeTime":"0;51;70;146","ID":1,"soundTime":"03:14","storySound":"https://test.xswq361.cn/files/gs/audio/gs1.mp3"},{"createTime":1544754390000,"storyImg":"https://test.xswq361.cn/files/gs/image/gs1_04.jpg","storyName":"尧造围棋教子丹朱2","changeTime":"0","ID":3,"soundTime":"03:14","storySound":"https://test.xswq361.cn/files/gs/audio/gs1.mp3"}]
         * pageNum : 1
         * pageSize : 50
         * size : 2
         * startRow : 1
         * endRow : 2
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

        public static class ListBean implements Serializable {
            /**
             * createTime : 1544579599000
             * storyImg : https://test.xswq361.cn/files/gs/image/gs1_04.jpg;https://test.xswq361.cn/files/gs/image/gs1_01.jpg;https://test.xswq361.cn/files/gs/image/gs1_02.jpg;https://test.xswq361.cn/files/gs/image/gs1_03.jpg
             * storyName : 尧造围棋教子丹朱
             * changeTime : 0;51;70;146
             * ID : 1
             * soundTime : 03:14
             * storySound : https://test.xswq361.cn/files/gs/audio/gs1.mp3
             */

            private long createTime;
            private String storyImg;
            private String storyName;
            private String changeTime;
            private String ID;
            private String soundTime;
            private String storySound;

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getStoryImg() {
                return storyImg;
            }

            public void setStoryImg(String storyImg) {
                this.storyImg = storyImg;
            }

            public String getStoryName() {
                return storyName;
            }

            public void setStoryName(String storyName) {
                this.storyName = storyName;
            }

            public String getChangeTime() {
                return changeTime;
            }

            public void setChangeTime(String changeTime) {
                this.changeTime = changeTime;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getSoundTime() {
                return soundTime;
            }

            public void setSoundTime(String soundTime) {
                this.soundTime = soundTime;
            }

            public String getStorySound() {
                return storySound;
            }

            public void setStorySound(String storySound) {
                this.storySound = storySound;
            }
        }
    }
}
