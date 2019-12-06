package com.xswq.chess.myapplication.bean;

import java.io.Serializable;
import java.util.List;

public class CompetitionSetThrCategoryBean implements Serializable {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"id":1,"matchName":"比赛名称","matchIntroduce":"这是一场巅峰对决","matchPattern":3,"invitedSch":"01e55feda14f43bdb531428f9b6a6d3e","startTime":1563935036000,"endTime":1564021441000,"stage":4,"classId":null,"createTime":1562293452000,"createUser":1516,"createOrg":null,"groupList":[{"id":1,"matchId":1,"groupName":"a组","road":9,"matchRule":2,"levelLimits":"25-15","participantNum":"10-25","judgeChess":1516,"baseTime":10,"countdown":30,"countdownNum":3,"lateTime":5,"chessRule":1,"chessNum":5,"rounds":3,"roundsList":[{"id":1,"groupId":1,"roundsNum":1,"startTime":1562293689000,"createTime":1562293695000},{"id":2,"groupId":1,"roundsNum":2,"startTime":1562293714000,"createTime":1562293721000},{"id":3,"groupId":1,"roundsNum":3,"startTime":1562293717000,"createTime":1562293724000}],"judgeName":"卢胜杰1","currentNum":4,"isExist":2},{"id":2,"matchId":1,"groupName":"b组","road":9,"matchRule":2,"levelLimits":"25-15","participantNum":"10-15","judgeChess":1516,"baseTime":10,"countdown":20,"countdownNum":2,"lateTime":5,"chessRule":1,"chessNum":5,"rounds":3,"roundsList":[{"id":4,"groupId":2,"roundsNum":1,"startTime":1562293740000,"createTime":1562293748000},{"id":5,"groupId":2,"roundsNum":2,"startTime":1562293743000,"createTime":1562293751000},{"id":6,"groupId":2,"roundsNum":3,"startTime":1562293745000,"createTime":1562293753000}],"judgeName":"卢胜杰1","currentNum":1,"isExist":2}]}
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

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * matchName : 比赛名称
         * matchIntroduce : 这是一场巅峰对决
         * matchPattern : 3
         * invitedSch : 01e55feda14f43bdb531428f9b6a6d3e
         * startTime : 1563935036000
         * endTime : 1564021441000
         * stage : 4
         * classId : null
         * createTime : 1562293452000
         * createUser : 1516
         * createOrg : null
         * groupList : [{"id":1,"matchId":1,"groupName":"a组","road":9,"matchRule":2,"levelLimits":"25-15","participantNum":"10-25","judgeChess":1516,"baseTime":10,"countdown":30,"countdownNum":3,"lateTime":5,"chessRule":1,"chessNum":5,"rounds":3,"roundsList":[{"id":1,"groupId":1,"roundsNum":1,"startTime":1562293689000,"createTime":1562293695000},{"id":2,"groupId":1,"roundsNum":2,"startTime":1562293714000,"createTime":1562293721000},{"id":3,"groupId":1,"roundsNum":3,"startTime":1562293717000,"createTime":1562293724000}],"judgeName":"卢胜杰1","currentNum":4,"isExist":2},{"id":2,"matchId":1,"groupName":"b组","road":9,"matchRule":2,"levelLimits":"25-15","participantNum":"10-15","judgeChess":1516,"baseTime":10,"countdown":20,"countdownNum":2,"lateTime":5,"chessRule":1,"chessNum":5,"rounds":3,"roundsList":[{"id":4,"groupId":2,"roundsNum":1,"startTime":1562293740000,"createTime":1562293748000},{"id":5,"groupId":2,"roundsNum":2,"startTime":1562293743000,"createTime":1562293751000},{"id":6,"groupId":2,"roundsNum":3,"startTime":1562293745000,"createTime":1562293753000}],"judgeName":"卢胜杰1","currentNum":1,"isExist":2}]
         */

        private int id;
        private String matchName;
        private String matchIntroduce;
        private int matchPattern;
        private String invitedSch;
        private long startTime;
        private String endTime;
        private int stage;
        private Object classId;
        private long createTime;
        private int createUser;
        private String createOrg;
        private List<GroupListBean> groupList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMatchName() {
            return matchName;
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

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getStage() {
            return stage;
        }

        public void setStage(int stage) {
            this.stage = stage;
        }

        public Object getClassId() {
            return classId;
        }

        public void setClassId(Object classId) {
            this.classId = classId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getCreateUser() {
            return createUser;
        }

        public void setCreateUser(int createUser) {
            this.createUser = createUser;
        }

        public String getCreateOrg() {
            return createOrg;
        }

        public void setCreateOrg(String createOrg) {
            this.createOrg = createOrg;
        }

        public List<GroupListBean> getGroupList() {
            return groupList;
        }

        public void setGroupList(List<GroupListBean> groupList) {
            this.groupList = groupList;
        }

        public static class GroupListBean implements Serializable{
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
             * currentNum : 4
             * isExist : 2
             */

            private String id;
            private int matchId;
            private String groupName;
            private int road;
            private int matchRule;
            private String levelLimits;
            private String participantNum;
            private int judgeChess;
            private int baseTime;
            private int countdown;
            private String countdownNum;
            private int lateTime;
            private int chessRule;
            private int chessNum;
            private String rounds;
            private String judgeName;
            private int currentNum;
            private int isExist;
            private List<RoundsListBean> roundsList;

            public String getId() {
                return id;
            }

            public void setId(String id) {
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

            public String getCountdownNum() {
                return countdownNum;
            }

            public void setCountdownNum(String countdownNum) {
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

            public String getRounds() {
                return rounds;
            }

            public void setRounds(String rounds) {
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

            public static class RoundsListBean implements Serializable{
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
