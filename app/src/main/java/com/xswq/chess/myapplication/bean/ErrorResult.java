package com.xswq.chess.myapplication.bean;

public class ErrorResult {

        public  String  returnUserMessage ;
        public String returnMessage;
        public String userRanking;
        public int returnCode;
        public String homeTable;

        public String getReturnUserMessage() {
            return returnUserMessage;
        }

        public void setReturnUserMessage(String returnUserMessage) {
            this.returnUserMessage = returnUserMessage;
        }

        public String getReturnMessage() {
            return returnMessage;
        }

        public void setReturnMessage(String returnMessage) {
            this.returnMessage = returnMessage;
        }

        public String getUserRanking() {
            return userRanking;
        }

        public void setUserRanking(String userRanking) {
            this.userRanking = userRanking;
        }

        public String getHomeTable() {
            return homeTable;
        }

        public void setHomeTable(String homeTable) {
            this.homeTable = homeTable;
        }


        public int getReturnCode() {
            return returnCode;
        }

        public void setReturnCode(int returnCode) {
            this.returnCode = returnCode;
        }
}

