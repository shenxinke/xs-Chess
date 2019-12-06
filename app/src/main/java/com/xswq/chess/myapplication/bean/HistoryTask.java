package com.xswq.chess.myapplication.bean;

import java.util.List;

public class HistoryTask {


    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"total":1,"list":[{"id":null,"jobname":null,"description":null,"userid":null,"questionid":null,"createtime":1544843371000,"jobid":"2002195e82bd4d429255132bc552326a","teachName":null,"jobtype":null,"questionType":null,"classInfoId":null,"className":null,"createUser":null,"jobStes":null,"startTime":null,"endTime":null,"complete":0,"history":null,"correct":0,"questCount":null,"totalcount":4}],"pageNum":1,"pageSize":10,"size":1,"startRow":1,"endRow":1,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * list : [{"id":null,"jobname":null,"description":null,"userid":null,"questionid":null,"createtime":1544843371000,"jobid":"2002195e82bd4d429255132bc552326a","teachName":null,"jobtype":null,"questionType":null,"classInfoId":null,"className":null,"createUser":null,"jobStes":null,"startTime":null,"endTime":null,"complete":0,"history":null,"correct":0,"questCount":null,"totalcount":4}]
         * pageNum : 1
         * pageSize : 10
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
             * id : null
             * jobname : null
             * description : null
             * userid : null
             * questionid : null
             * createtime : 1544843371000
             * jobid : 2002195e82bd4d429255132bc552326a
             * teachName : null
             * jobtype : null
             * questionType : null
             * classInfoId : null
             * className : null
             * createUser : null
             * jobStes : null
             * startTime : null
             * endTime : null
             * complete : 0
             * history : null
             * correct : 0
             * questCount : null
             * totalcount : 4
             */

            private Object id;
            private Object jobname;
            private Object description;
            private Object userid;
            private Object questionid;
            private long createtime;
            private String jobid;
            private Object teachName;
            private Object jobtype;
            private Object questionType;
            private Object classInfoId;
            private Object className;
            private Object createUser;
            private Object jobStes;
            private Object startTime;
            private Object endTime;
            private int complete;
            private Object history;
            private int correct;
            private Object questCount;
            private int totalcount;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Object getJobname() {
                return jobname;
            }

            public void setJobname(Object jobname) {
                this.jobname = jobname;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public Object getUserid() {
                return userid;
            }

            public void setUserid(Object userid) {
                this.userid = userid;
            }

            public Object getQuestionid() {
                return questionid;
            }

            public void setQuestionid(Object questionid) {
                this.questionid = questionid;
            }

            public long getCreatetime() {
                return createtime;
            }

            public void setCreatetime(long createtime) {
                this.createtime = createtime;
            }

            public String getJobid() {
                return jobid;
            }

            public void setJobid(String jobid) {
                this.jobid = jobid;
            }

            public Object getTeachName() {
                return teachName;
            }

            public void setTeachName(Object teachName) {
                this.teachName = teachName;
            }

            public Object getJobtype() {
                return jobtype;
            }

            public void setJobtype(Object jobtype) {
                this.jobtype = jobtype;
            }

            public Object getQuestionType() {
                return questionType;
            }

            public void setQuestionType(Object questionType) {
                this.questionType = questionType;
            }

            public Object getClassInfoId() {
                return classInfoId;
            }

            public void setClassInfoId(Object classInfoId) {
                this.classInfoId = classInfoId;
            }

            public Object getClassName() {
                return className;
            }

            public void setClassName(Object className) {
                this.className = className;
            }

            public Object getCreateUser() {
                return createUser;
            }

            public void setCreateUser(Object createUser) {
                this.createUser = createUser;
            }

            public Object getJobStes() {
                return jobStes;
            }

            public void setJobStes(Object jobStes) {
                this.jobStes = jobStes;
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

            public int getComplete() {
                return complete;
            }

            public void setComplete(int complete) {
                this.complete = complete;
            }

            public Object getHistory() {
                return history;
            }

            public void setHistory(Object history) {
                this.history = history;
            }

            public int getCorrect() {
                return correct;
            }

            public void setCorrect(int correct) {
                this.correct = correct;
            }

            public Object getQuestCount() {
                return questCount;
            }

            public void setQuestCount(Object questCount) {
                this.questCount = questCount;
            }

            public int getTotalcount() {
                return totalcount;
            }

            public void setTotalcount(int totalcount) {
                this.totalcount = totalcount;
            }
        }
    }
}
