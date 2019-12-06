package com.xswq.chess.myapplication.bean;

public class WxPayBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"appId":"wx92ec0e323addfe2f","timeStamp":"1558690794","signType":"MD5","nonceStr":"xGSqTdRIZkSnUYkG","orderKey":"xs581536851261652992","mchId":"1523754391","prepayId":"wx24173820677426d7984951761424437400","paySign":"210B9997F743F40BE4419D0DB406CBEE"}
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
         * appId : wx92ec0e323addfe2f
         * timeStamp : 1558690794
         * signType : MD5
         * nonceStr : xGSqTdRIZkSnUYkG
         * orderKey : xs581536851261652992
         * mchId : 1523754391
         * prepayId : wx24173820677426d7984951761424437400
         * paySign : 210B9997F743F40BE4419D0DB406CBEE
         */

        private String appId;
        private String timeStamp;
        private String signType;
        private String nonceStr;
        private String orderKey;
        private String mchId;
        private String prepayId;
        private String paySign;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getSignType() {
            return signType;
        }

        public void setSignType(String signType) {
            this.signType = signType;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }

        public String getOrderKey() {
            return orderKey;
        }

        public void setOrderKey(String orderKey) {
            this.orderKey = orderKey;
        }

        public String getMchId() {
            return mchId;
        }

        public void setMchId(String mchId) {
            this.mchId = mchId;
        }

        public String getPrepayId() {
            return prepayId;
        }

        public void setPrepayId(String prepayId) {
            this.prepayId = prepayId;
        }

        public String getPaySign() {
            return paySign;
        }

        public void setPaySign(String paySign) {
            this.paySign = paySign;
        }
    }
}
