package com.xswq.chess.myapplication.bean;

import java.io.Serializable;
import java.util.List;

public class CompetitionManagementBean implements Serializable {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"total":1,"list":[{"id":1,"matchName":"比赛名称","matchIntroduce":"这是一场巅峰对决","matchPattern":3,"invitedSch":"01e55feda14f43bdb531428f9b6a6d3e","startTime":1563935036000,"endTime":1564021441000,"stage":0,"classId":null,"createTime":1562293452000,"createUser":1516,"createOrg":null,"groupList":[{"id":1,"matchId":1,"groupName":"a组","road":9,"matchRule":2,"levelLimits":"25-15","participantNum":"10-25","judgeChess":1516,"baseTime":10,"countdown":30,"countdownNum":3,"lateTime":5,"chessRule":1,"chessNum":5,"rounds":3,"roundsList":[{"id":1,"groupId":1,"roundsNum":1,"startTime":1562293689000,"createTime":1562293695000},{"id":2,"groupId":1,"roundsNum":2,"startTime":1562293714000,"createTime":1562293721000},{"id":3,"groupId":1,"roundsNum":3,"startTime":1562293717000,"createTime":1562293724000}],"judgeName":"卢胜杰1","currentNum":0,"isExist":2},{"id":2,"matchId":1,"groupName":"b组","road":9,"matchRule":2,"levelLimits":"25-15","participantNum":"10-15","judgeChess":1516,"baseTime":10,"countdown":20,"countdownNum":2,"lateTime":5,"chessRule":1,"chessNum":5,"rounds":3,"roundsList":[{"id":4,"groupId":2,"roundsNum":1,"startTime":1562293740000,"createTime":1562293748000},{"id":5,"groupId":2,"roundsNum":2,"startTime":1562293743000,"createTime":1562293751000},{"id":6,"groupId":2,"roundsNum":3,"startTime":1562293745000,"createTime":1562293753000}],"judgeName":"卢胜杰1","currentNum":0,"isExist":2}]}],"pageNum":1,"pageSize":20,"size":1,"startRow":1,"endRow":1,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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

    public static class ErrorBean implements Serializable {
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

    public static class DataBean implements Serializable {
        /**
         * total : 1
         * list : [{"id":1,"matchName":"比赛名称","matchIntroduce":"这是一场巅峰对决","matchPattern":3,"invitedSch":"01e55feda14f43bdb531428f9b6a6d3e","startTime":1563935036000,"endTime":1564021441000,"stage":0,"classId":null,"createTime":1562293452000,"createUser":1516,"createOrg":null,"groupList":[{"id":1,"matchId":1,"groupName":"a组","road":9,"matchRule":2,"levelLimits":"25-15","participantNum":"10-25","judgeChess":1516,"baseTime":10,"countdown":30,"countdownNum":3,"lateTime":5,"chessRule":1,"chessNum":5,"rounds":3,"roundsList":[{"id":1,"groupId":1,"roundsNum":1,"startTime":1562293689000,"createTime":1562293695000},{"id":2,"groupId":1,"roundsNum":2,"startTime":1562293714000,"createTime":1562293721000},{"id":3,"groupId":1,"roundsNum":3,"startTime":1562293717000,"createTime":1562293724000}],"judgeName":"卢胜杰1","currentNum":0,"isExist":2},{"id":2,"matchId":1,"groupName":"b组","road":9,"matchRule":2,"levelLimits":"25-15","participantNum":"10-15","judgeChess":1516,"baseTime":10,"countdown":20,"countdownNum":2,"lateTime":5,"chessRule":1,"chessNum":5,"rounds":3,"roundsList":[{"id":4,"groupId":2,"roundsNum":1,"startTime":1562293740000,"createTime":1562293748000},{"id":5,"groupId":2,"roundsNum":2,"startTime":1562293743000,"createTime":1562293751000},{"id":6,"groupId":2,"roundsNum":3,"startTime":1562293745000,"createTime":1562293753000}],"judgeName":"卢胜杰1","currentNum":0,"isExist":2}]}]
         * pageNum : 1
         * pageSize : 20
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

        public static class ListBean implements Serializable {
            /**
             * id : 1
             * matchName : 比赛名称
             * matchIntroduce : 这是一场巅峰对决
             * matchPattern : 3
             * invitedSch : 01e55feda14f43bdb531428f9b6a6d3e
             * startTime : 1563935036000
             * endTime : 1564021441000
             * stage : 0
             * classId : null
             * createTime : 1562293452000
             * createUser : 1516
             * createOrg : null
             * groupList : [{"id":1,"matchId":1,"groupName":"a组","road":9,"matchRule":2,"levelLimits":"25-15","participantNum":"10-25","judgeChess":1516,"baseTime":10,"countdown":30,"countdownNum":3,"lateTime":5,"chessRule":1,"chessNum":5,"rounds":3,"roundsList":[{"id":1,"groupId":1,"roundsNum":1,"startTime":1562293689000,"createTime":1562293695000},{"id":2,"groupId":1,"roundsNum":2,"startTime":1562293714000,"createTime":1562293721000},{"id":3,"groupId":1,"roundsNum":3,"startTime":1562293717000,"createTime":1562293724000}],"judgeName":"卢胜杰1","currentNum":0,"isExist":2},{"id":2,"matchId":1,"groupName":"b组","road":9,"matchRule":2,"levelLimits":"25-15","participantNum":"10-15","judgeChess":1516,"baseTime":10,"countdown":20,"countdownNum":2,"lateTime":5,"chessRule":1,"chessNum":5,"rounds":3,"roundsList":[{"id":4,"groupId":2,"roundsNum":1,"startTime":1562293740000,"createTime":1562293748000},{"id":5,"groupId":2,"roundsNum":2,"startTime":1562293743000,"createTime":1562293751000},{"id":6,"groupId":2,"roundsNum":3,"startTime":1562293745000,"createTime":1562293753000}],"judgeName":"卢胜杰1","currentNum":0,"isExist":2}]
             */

            private String id;
            private String matchName;
            private String matchIntroduce;
            private int matchPattern;
            private String invitedSch;
            private String invitedSchName;
            private long startTime;
            private long endTime;
            private int stage;
            private long createTime;
            private String createUser;
            private Object createOrg;
            private List<GroupListBean> groupList;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMatchName() {
                return matchName;
            }

            public String getInvitedSchName() {
                return invitedSchName;
            }

            public void setInvitedSchName(String invitedSchName) {
                this.invitedSchName = invitedSchName;
            }

            public void setMatchName(String matchName) {
                this.matchName = matchName;
            }

            public String getMatchIntroduce() {
                return matchIntroduce;
            }

            public void setMatchIntroduce(String matchIntroduce) {
                this.matchIntroduce = matchIntroduce;
            }

            public int getMatchPattern() {
                return matchPattern;
            }

            public void setMatchPattern(int matchPattern) {
                this.matchPattern = matchPattern;
            }

            public String getInvitedSch() {
                return invitedSch;
            }

            public void setInvitedSch(String invitedSch) {
                this.invitedSch = invitedSch;
            }

            public long getStartTime() {
                return startTime;
            }

            public void setStartTime(long startTime) {
                this.startTime = startTime;
            }

            public long getEndTime() {
                return endTime;
            }

            public void setEndTime(long endTime) {
                this.endTime = endTime;
            }

            public int getStage() {
                return stage;
            }

            public void setStage(int stage) {
                this.stage = stage;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public Object getCreateOrg() {
                return createOrg;
            }

            public void setCreateOrg(Object createOrg) {
                this.createOrg = createOrg;
            }

            public List<GroupListBean> getGroupList() {
                return groupList;
            }

            public void setGroupList(List<GroupListBean> groupList) {
                this.groupList = groupList;
            }

            public static class GroupListBean implements Serializable {
                /**
                 * id : 1
                 * matchId : 1
                 * groupName : a组
                 * road : 9
                 * matchRule : 2
                 * levelLimits : 25-15
                 * participantNum : 10-25
                 * judgeChess : 1516
                 * baseTime : 10
                 * countdown : 30
                 * countdownNum : 3
                 * lateTime : 5
                 * chessRule : 1
                 * chessNum : 5
                 * rounds : 3
                 * roundsList : [{"id":1,"groupId":1,"roundsNum":1,"startTime":1562293689000,"createTime":1562293695000},{"id":2,"groupId":1,"roundsNum":2,"startTime":1562293714000,"createTime":1562293721000},{"id":3,"groupId":1,"roundsNum":3,"startTime":1562293717000,"createTime":1562293724000}]
                 * judgeName : 卢胜杰1
                 * currentNum : 0
                 * isExist : 2
                 */

                private int id;
                private int matchId;
                private String groupName;
                private int road;
                private int matchRule;
                private String levelLimits;
                private String participantNum;
                private int judgeChess;
                private int baseTime;
                private int countdown;
                private int countdownNum;
                private int lateTime;
                private int chessRule;
                private int chessNum;
                private int rounds;
                private String judgeName;
                private int currentNum;
                private int isExist;
                private List<RoundsListBean> roundsList;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getMatchId() {
                    return matchId;
                }

                public void setMatchId(int matchId) {
                    this.matchId = matchId;
                }

                public String getGroupName() {
                    return groupName;
                }

                public void setGroupName(String groupName) {
                    this.groupName = groupName;
                }

                public int getRoad() {
                    return road;
                }

                public void setRoad(int road) {
                    this.road = road;
                }

                public int getMatchRule() {
                    return matchRule;
                }

                public void setMatchRule(int matchRule) {
                    this.matchRule = matchRule;
                }

                public String getLevelLimits() {
                    return levelLimits;
                }

                public void setLevelLimits(String levelLimits) {
                    this.levelLimits = levelLimits;
                }

                public String getParticipantNum() {
                    return participantNum;
                }

                public void setParticipantNum(String participantNum) {
                    this.participantNum = participantNum;
                }

                public int getJudgeChess() {
                    return judgeChess;
                }

                public void setJudgeChess(int judgeChess) {
                    this.judgeChess = judgeChess;
                }

                public int getBaseTime() {
                    return baseTime;
                }

                public void setBaseTime(int baseTime) {
                    this.baseTime = baseTime;
                }

                public int getCountdown() {
                    return countdown;
                }

                public void setCountdown(int countdown) {
                    this.countdown = countdown;
                }

                public int getCountdownNum() {
                    return countdownNum;
                }

                public void setCountdownNum(int countdownNum) {
                    this.countdownNum = countdownNum;
                }

                public int getLateTime() {
                    return lateTime;
                }

                public void setLateTime(int lateTime) {
                    this.lateTime = lateTime;
                }

                public int getChessRule() {
                    return chessRule;
                }

                public void setChessRule(int chessRule) {
                    this.chessRule = chessRule;
                }

                public int getChessNum() {
                    return chessNum;
                }

                public void setChessNum(int chessNum) {
                    this.chessNum = chessNum;
                }

                public int getRounds() {
                    return rounds;
                }

                public void setRounds(int rounds) {
                    this.rounds = rounds;
                }

                public String getJudgeName() {
                    return judgeName;
                }

                public void setJudgeName(String judgeName) {
                    this.judgeName = judgeName;
                }

                public int getCurrentNum() {
                    return currentNum;
                }

                public void setCurrentNum(int currentNum) {
                    this.currentNum = currentNum;
                }

                public int getIsExist() {
                    return isExist;
                }

                public void setIsExist(int isExist) {
                    this.isExist = isExist;
                }

                public List<RoundsListBean> getRoundsList() {
                    return roundsList;
                }

                public void setRoundsList(List<RoundsListBean> roundsList) {
                    this.roundsList = roundsList;
                }

                public static class RoundsListBean implements Serializable {
                    /**
                     * id : 1
                     * groupId : 1
                     * roundsNum : 1
                     * startTime : 1562293689000
                     * createTime : 1562293695000
                     */

                    private int id;
                    private int groupId;
                    private int roundsNum;
                    private long startTime;
                    private long createTime;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getGroupId() {
                        return groupId;
                    }

                    public void setGroupId(int groupId) {
                        this.groupId = groupId;
                    }

                    public int getRoundsNum() {
                        return roundsNum;
                    }

                    public void setRoundsNum(int roundsNum) {
                        this.roundsNum = roundsNum;
                    }

                    public long getStartTime() {
                        return startTime;
                    }

                    public void setStartTime(long startTime) {
                        this.startTime = startTime;
                    }

                    public long getCreateTime() {
                        return createTime;
                    }

                    public void setCreateTime(long createTime) {
                        this.createTime = createTime;
                    }
                }
            }
        }
    }
}
