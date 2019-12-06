package com.xswq.chess.myapplication.bean;

import java.util.List;

public class ToAchieveBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : [{"detailedness":[{"AchieveType":"游戏","ImgUrl":"img/achievementImg/AYSL1.png","AchieveName":"遨游森林","CreateTime":1533635816000,"ID":114,"UserId":1570,"ActualName":"第二关卡全三星"},{"AchieveType":"游戏","ImgUrl":"img/achievementImg/CRMJ1.png","AchieveName":"初入秘境","CreateTime":1530932333000,"ID":43,"UserId":1570,"ActualName":"第一关卡全三星"}],"typeAndSumCount":{"acquireNum":2,"achieveType":"游戏","typeNum":9}}]
     */

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
         * detailedness : [{"AchieveType":"游戏","ImgUrl":"img/achievementImg/AYSL1.png","AchieveName":"遨游森林","CreateTime":1533635816000,"ID":114,"UserId":1570,"ActualName":"第二关卡全三星"},{"AchieveType":"游戏","ImgUrl":"img/achievementImg/CRMJ1.png","AchieveName":"初入秘境","CreateTime":1530932333000,"ID":43,"UserId":1570,"ActualName":"第一关卡全三星"}]
         * typeAndSumCount : {"acquireNum":2,"achieveType":"游戏","typeNum":9}
         */

        private TypeAndSumCountBean typeAndSumCount;
        private List<DetailednessBean> detailedness;

        public TypeAndSumCountBean getTypeAndSumCount() {
            return typeAndSumCount;
        }

        public void setTypeAndSumCount(TypeAndSumCountBean typeAndSumCount) {
            this.typeAndSumCount = typeAndSumCount;
        }

        public List<DetailednessBean> getDetailedness() {
            return detailedness;
        }

        public void setDetailedness(List<DetailednessBean> detailedness) {
            this.detailedness = detailedness;
        }

        public static class TypeAndSumCountBean {
            /**
             * acquireNum : 2
             * achieveType : 游戏
             * typeNum : 9
             */

            private int acquireNum;
            private String achieveType;
            private int typeNum;
            private int noAcquireNum;
            private int Num;

            public int getNum() {
                return Num;
            }

            public void setNum(int num) {
                Num = num;
            }

            public int getNoAcquireNum() {
                return noAcquireNum;
            }

            public void setNoAcquireNum(int noAcquireNum) {
                this.noAcquireNum = noAcquireNum;
            }

            public int getAcquireNum() {
                return acquireNum;
            }

            public void setAcquireNum(int acquireNum) {
                this.acquireNum = acquireNum;
            }

            public String getAchieveType() {
                return achieveType;
            }

            public void setAchieveType(String achieveType) {
                this.achieveType = achieveType;
            }

            public int getTypeNum() {
                return typeNum;
            }

            public void setTypeNum(int typeNum) {
                this.typeNum = typeNum;
            }
        }

        public static class DetailednessBean {
            /**
             * AchieveType : 游戏
             * ImgUrl : img/achievementImg/AYSL1.png
             * AchieveName : 遨游森林
             * CreateTime : 1533635816000
             * ID : 114
             * UserId : 1570
             * ActualName : 第二关卡全三星
             */

            private String AchieveType;
            private String ImgUrl;
            private String AchieveName;
            private long CreateTime;
            private int ID;
            private int UserId;
            private String ActualName;
            private int MaxValue;
            private int DetailednessType;

            public int getDetailednessType() {
                return DetailednessType;
            }

            public void setDetailednessType(int detailednessType) {
                DetailednessType = detailednessType;
            }

            public int getMaxValue() {
                return MaxValue;
            }

            public void setMaxValue(int maxValue) {
                MaxValue = maxValue;
            }

            public String getAchieveType() {
                return AchieveType;
            }

            public void setAchieveType(String AchieveType) {
                this.AchieveType = AchieveType;
            }

            public String getImgUrl() {
                return ImgUrl;
            }

            public void setImgUrl(String ImgUrl) {
                this.ImgUrl = ImgUrl;
            }

            public String getAchieveName() {
                return AchieveName;
            }

            public void setAchieveName(String AchieveName) {
                this.AchieveName = AchieveName;
            }

            public long getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(long CreateTime) {
                this.CreateTime = CreateTime;
            }

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public int getUserId() {
                return UserId;
            }

            public void setUserId(int UserId) {
                this.UserId = UserId;
            }

            public String getActualName() {
                return ActualName;
            }

            public void setActualName(String ActualName) {
                this.ActualName = ActualName;
            }
        }
    }
}
