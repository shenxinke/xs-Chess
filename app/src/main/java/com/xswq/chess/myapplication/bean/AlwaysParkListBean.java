package com.xswq.chess.myapplication.bean;

import java.util.List;

public class AlwaysParkListBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"now":{"ID":"0f2b1afa11b14cce9ce7046b47d4942e","dealerRank":2,"customerName":"先手科技"},"primary":[{"ID":"0f2b1afa11b14cce9ce7046b47d4942e","dealerRank":2,"customerName":"先手科技"}]}
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
         * now : {"ID":"0f2b1afa11b14cce9ce7046b47d4942e","dealerRank":2,"customerName":"先手科技"}
         * primary : [{"ID":"0f2b1afa11b14cce9ce7046b47d4942e","dealerRank":2,"customerName":"先手科技"}]
         */

        private NowBean now;
        private List<PrimaryBean> primary;

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public List<PrimaryBean> getPrimary() {
            return primary;
        }

        public void setPrimary(List<PrimaryBean> primary) {
            this.primary = primary;
        }

        public static class NowBean {
            /**
             * ID : 0f2b1afa11b14cce9ce7046b47d4942e
             * dealerRank : 2
             * customerName : 先手科技
             */

            private String ID;
            private int dealerRank;
            private String customerName;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public int getDealerRank() {
                return dealerRank;
            }

            public void setDealerRank(int dealerRank) {
                this.dealerRank = dealerRank;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }
        }

        public static class PrimaryBean {
            /**
             * ID : 0f2b1afa11b14cce9ce7046b47d4942e
             * dealerRank : 2
             * customerName : 先手科技
             */

            private String ID;
            private int dealerRank;
            private String customerName;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public int getDealerRank() {
                return dealerRank;
            }

            public void setDealerRank(int dealerRank) {
                this.dealerRank = dealerRank;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }
        }
    }
}
