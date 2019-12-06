package com.xswq.chess.myapplication.bean;

import java.io.Serializable;
import java.util.List;

public class StudentCompetitionManagementBean implements Serializable {

    private ErrorBean error;
    private List<DataBean> data;

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
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
         * id : 66
         * matchName : 校间赛-总园2
         * matchIntroduce : 你
         * matchPattern : 3
         * invitedSch : f073e1f25cfb4f17b0a621b7d061b717
         * invitedSchName : null
         * startTime : 1564124637000
         * endTime : 1564128238000
         * stage : 7
         * classId : null
         * createTime : 1564124646000
         * createUser : 167
         * createOrg : e60da0555ad54a41bc2374daadf946af
         * groupList : [{"id":44,"matchId":66,"groupName":"组别9","road":9,"matchRule":1,"levelLimits":"25K-25K","participantNum":"4-8","judgeChess":0,"baseTime":5,"countdown":30,"countdownNum":1,"lateTime":5,"chessRule":2,"chessNum":3,"rounds":3,"roundsList":[{"id":289,"groupId":44,"roundsNum":1,"startTime":1564221600000,"createTime":1564124707000},{"id":290,"groupId":44,"roundsNum":2,"startTime":1564308000000,"createTime":1564124707000},{"id":291,"groupId":44,"roundsNum":3,"startTime":1564394400000,"createTime":1564124707000}],"judgeName":null,"currentNum":0,"isExist":2}]
         * state : 3
         */

        private int id;
        private String matchName;
        private String matchIntroduce;
        private int matchPattern;
        private String invitedSch;
        private String invitedSchName;
        private long startTime;
        private long endTime;
        private int stage;
        private Object classId;
        private long createTime;
        private int createUser;
        private String createOrg;
        private int state;
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

        public String getInvitedSchName() {
            return invitedSchName;
        }

        public void setInvitedSchName(String invitedSchName) {
            this.invitedSchName = invitedSchName;
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

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public List<GroupListBean> getGroupList() {
            return groupList;
        }

        public void setGroupList(List<GroupListBean> groupList) {
            this.groupList = groupList;
        }

        public static class GroupListBean implements Serializable {
            /**
             * id : 44
             * matchId : 66
             * groupName : 组别9
             * road : 9
             * matchRule : 1
             * levelLimits : 25K-25K
             * participantNum : 4-8
             * judgeChess : 0
             * baseTime : 5
             * countdown : 30
             * countdownNum : 1
             * lateTime : 5
             * chessRule : 2
             * chessNum : 3.0
             * rounds : 3
             * roundsList : [{"id":289,"groupId":44,"roundsNum":1,"startTime":1564221600000,"createTime":1564124707000},{"id":290,"groupId":44,"roundsNum":2,"startTime":1564308000000,"createTime":1564124707000},{"id":291,"groupId":44,"roundsNum":3,"startTime":1564394400000,"createTime":1564124707000}]
             * judgeName : null
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
            private double chessNum;
            private int rounds;
            private Object judgeName;
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

            public double getChessNum() {
                return chessNum;
            }

            public void setChessNum(double chessNum) {
                this.chessNum = chessNum;
            }

            public int getRounds() {
                return rounds;
            }

            public void setRounds(int rounds) {
                this.rounds = rounds;
            }

            public Object getJudgeName() {
                return judgeName;
            }

            public void setJudgeName(Object judgeName) {
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
                 * id : 289
                 * groupId : 44
                 * roundsNum : 1
                 * startTime : 1564221600000
                 * createTime : 1564124707000
                 */

                private int id;
                private int groupId;
                private int roundsNum;
                private long startTime;
                private long createTime;
                private int overType;

                public int getOverType() {
                    return overType;
                }

                public void setOverType(int overType) {
                    this.overType = overType;
                }

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
