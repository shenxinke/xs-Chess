package com.xswq.chess.myapplication.bean;

import java.io.Serializable;
import java.util.List;

public class StudentPlayByPlayBean implements Serializable{

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"id":71,"matchName":"哈哈哈","matchIntroduce":"介绍","matchPattern":2,"invitedSch":null,"invitedSchName":null,"startTime":1564131600000,"endTime":1564135200000,"stage":5,"classId":null,"createTime":1564127332000,"createUser":1516,"createOrg":"f073e1f25cfb4f17b0a621b7d061b717","groupList":[{"id":48,"matchId":71,"groupName":"啦啦啦啦啦啦啦","road":19,"matchRule":2,"levelLimits":"25k-25k","participantNum":"128-256","judgeChess":400,"baseTime":90,"countdown":30,"countdownNum":1,"lateTime":15,"chessRule":3,"chessNum":1,"rounds":13,"roundsList":[{"id":331,"groupId":48,"roundsNum":1,"startTime":null,"createTime":1564127439000},{"id":332,"groupId":48,"roundsNum":2,"startTime":null,"createTime":1564127439000},{"id":333,"groupId":48,"roundsNum":3,"startTime":null,"createTime":1564127439000},{"id":334,"groupId":48,"roundsNum":4,"startTime":null,"createTime":1564127439000},{"id":335,"groupId":48,"roundsNum":5,"startTime":null,"createTime":1564127439000},{"id":336,"groupId":48,"roundsNum":6,"startTime":null,"createTime":1564127439000},{"id":337,"groupId":48,"roundsNum":7,"startTime":null,"createTime":1564127439000},{"id":338,"groupId":48,"roundsNum":8,"startTime":null,"createTime":1564127439000},{"id":339,"groupId":48,"roundsNum":9,"startTime":null,"createTime":1564127439000},{"id":340,"groupId":48,"roundsNum":10,"startTime":null,"createTime":1564127439000},{"id":341,"groupId":48,"roundsNum":11,"startTime":null,"createTime":1564127439000},{"id":342,"groupId":48,"roundsNum":12,"startTime":null,"createTime":1564127439000},{"id":343,"groupId":48,"roundsNum":13,"startTime":null,"createTime":1564127439000}],"judgeName":"袁星月","currentNum":0,"isExist":2}]}
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

    public static class ErrorBean implements Serializable{
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
         * id : 71
         * matchName : 哈哈哈
         * matchIntroduce : 介绍
         * matchPattern : 2
         * invitedSch : null
         * invitedSchName : null
         * startTime : 1564131600000
         * endTime : 1564135200000
         * stage : 5
         * classId : null
         * createTime : 1564127332000
         * createUser : 1516
         * createOrg : f073e1f25cfb4f17b0a621b7d061b717
         * groupList : [{"id":48,"matchId":71,"groupName":"啦啦啦啦啦啦啦","road":19,"matchRule":2,"levelLimits":"25k-25k","participantNum":"128-256","judgeChess":400,"baseTime":90,"countdown":30,"countdownNum":1,"lateTime":15,"chessRule":3,"chessNum":1,"rounds":13,"roundsList":[{"id":331,"groupId":48,"roundsNum":1,"startTime":null,"createTime":1564127439000},{"id":332,"groupId":48,"roundsNum":2,"startTime":null,"createTime":1564127439000},{"id":333,"groupId":48,"roundsNum":3,"startTime":null,"createTime":1564127439000},{"id":334,"groupId":48,"roundsNum":4,"startTime":null,"createTime":1564127439000},{"id":335,"groupId":48,"roundsNum":5,"startTime":null,"createTime":1564127439000},{"id":336,"groupId":48,"roundsNum":6,"startTime":null,"createTime":1564127439000},{"id":337,"groupId":48,"roundsNum":7,"startTime":null,"createTime":1564127439000},{"id":338,"groupId":48,"roundsNum":8,"startTime":null,"createTime":1564127439000},{"id":339,"groupId":48,"roundsNum":9,"startTime":null,"createTime":1564127439000},{"id":340,"groupId":48,"roundsNum":10,"startTime":null,"createTime":1564127439000},{"id":341,"groupId":48,"roundsNum":11,"startTime":null,"createTime":1564127439000},{"id":342,"groupId":48,"roundsNum":12,"startTime":null,"createTime":1564127439000},{"id":343,"groupId":48,"roundsNum":13,"startTime":null,"createTime":1564127439000}],"judgeName":"袁星月","currentNum":0,"isExist":2}]
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
        private Object classId;
        private long createTime;
        private int createUser;
        private String createOrg;
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

        public List<GroupListBean> getGroupList() {
            return groupList;
        }

        public void setGroupList(List<GroupListBean> groupList) {
            this.groupList = groupList;
        }

        public static class GroupListBean implements Serializable{
            /**
             * id : 48
             * matchId : 71
             * groupName : 啦啦啦啦啦啦啦
             * road : 19
             * matchRule : 2
             * levelLimits : 25k-25k
             * participantNum : 128-256
             * judgeChess : 400
             * baseTime : 90
             * countdown : 30
             * countdownNum : 1
             * lateTime : 15
             * chessRule : 3
             * chessNum : 1.0
             * rounds : 13
             * roundsList : [{"id":331,"groupId":48,"roundsNum":1,"startTime":null,"createTime":1564127439000},{"id":332,"groupId":48,"roundsNum":2,"startTime":null,"createTime":1564127439000},{"id":333,"groupId":48,"roundsNum":3,"startTime":null,"createTime":1564127439000},{"id":334,"groupId":48,"roundsNum":4,"startTime":null,"createTime":1564127439000},{"id":335,"groupId":48,"roundsNum":5,"startTime":null,"createTime":1564127439000},{"id":336,"groupId":48,"roundsNum":6,"startTime":null,"createTime":1564127439000},{"id":337,"groupId":48,"roundsNum":7,"startTime":null,"createTime":1564127439000},{"id":338,"groupId":48,"roundsNum":8,"startTime":null,"createTime":1564127439000},{"id":339,"groupId":48,"roundsNum":9,"startTime":null,"createTime":1564127439000},{"id":340,"groupId":48,"roundsNum":10,"startTime":null,"createTime":1564127439000},{"id":341,"groupId":48,"roundsNum":11,"startTime":null,"createTime":1564127439000},{"id":342,"groupId":48,"roundsNum":12,"startTime":null,"createTime":1564127439000},{"id":343,"groupId":48,"roundsNum":13,"startTime":null,"createTime":1564127439000}]
             * judgeName : 袁星月
             * currentNum : 0
             * isExist : 2
             */

            private int id;
            private String matchId;
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
            private String judgeName;
            private int currentNum;
            private int isExist;
            private List<RoundsListBean> roundsList;
            private boolean select;

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMatchId() {
                return matchId;
            }

            public void setMatchId(String matchId) {
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
                 * id : 331
                 * groupId : 48
                 * roundsNum : 1
                 * startTime : null
                 * createTime : 1564127439000
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
