package com.xswq.chess.myapplication.bean;

import java.util.List;

public class OrgProfitListBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"totalProfit":-714,"pageInfo":{"total":6,"list":[{"money":"1","createTime":1558519626000,"mobile":"18888888888","userName":"mengmeng","profit":-119,"customerName":"888"},{"money":"1","createTime":1558519814000,"mobile":"18888888888","userName":"mengmeng","profit":-119,"customerName":"888"},{"money":"1","createTime":1558574507000,"mobile":"13711101233","userName":"张三","profit":-119,"customerName":"888"},{"money":"1","createTime":1558574687000,"mobile":"13711101233","userName":"张三","profit":-119,"customerName":"888"},{"money":"1","createTime":1558603170000,"mobile":"18888888888","userName":"mengmeng","profit":-119,"customerName":"888"},{"money":"1","createTime":1558680313000,"mobile":"13100000000","userName":"杨","profit":-119,"customerName":"888"}],"pageNum":1,"pageSize":1000,"size":6,"startRow":1,"endRow":6,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"lastPage":1,"firstPage":1}}
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
         * totalProfit : -714
         * pageInfo : {"total":6,"list":[{"money":"1","createTime":1558519626000,"mobile":"18888888888","userName":"mengmeng","profit":-119,"customerName":"888"},{"money":"1","createTime":1558519814000,"mobile":"18888888888","userName":"mengmeng","profit":-119,"customerName":"888"},{"money":"1","createTime":1558574507000,"mobile":"13711101233","userName":"张三","profit":-119,"customerName":"888"},{"money":"1","createTime":1558574687000,"mobile":"13711101233","userName":"张三","profit":-119,"customerName":"888"},{"money":"1","createTime":1558603170000,"mobile":"18888888888","userName":"mengmeng","profit":-119,"customerName":"888"},{"money":"1","createTime":1558680313000,"mobile":"13100000000","userName":"杨","profit":-119,"customerName":"888"}],"pageNum":1,"pageSize":1000,"size":6,"startRow":1,"endRow":6,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"lastPage":1,"firstPage":1}
         */

        private String totalProfit;
        private PageInfoBean pageInfo;

        public String getTotalProfit() {
            return totalProfit;
        }

        public void setTotalProfit(String totalProfit) {
            this.totalProfit = totalProfit;
        }

        public PageInfoBean getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfoBean pageInfo) {
            this.pageInfo = pageInfo;
        }

        public static class PageInfoBean {
            /**
             * total : 6
             * list : [{"money":"1","createTime":1558519626000,"mobile":"18888888888","userName":"mengmeng","profit":-119,"customerName":"888"},{"money":"1","createTime":1558519814000,"mobile":"18888888888","userName":"mengmeng","profit":-119,"customerName":"888"},{"money":"1","createTime":1558574507000,"mobile":"13711101233","userName":"张三","profit":-119,"customerName":"888"},{"money":"1","createTime":1558574687000,"mobile":"13711101233","userName":"张三","profit":-119,"customerName":"888"},{"money":"1","createTime":1558603170000,"mobile":"18888888888","userName":"mengmeng","profit":-119,"customerName":"888"},{"money":"1","createTime":1558680313000,"mobile":"13100000000","userName":"杨","profit":-119,"customerName":"888"}]
             * pageNum : 1
             * pageSize : 1000
             * size : 6
             * startRow : 1
             * endRow : 6
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
             * lastPage : 1
             * firstPage : 1
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
            private int lastPage;
            private int firstPage;
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

            public int getLastPage() {
                return lastPage;
            }

            public void setLastPage(int lastPage) {
                this.lastPage = lastPage;
            }

            public int getFirstPage() {
                return firstPage;
            }

            public void setFirstPage(int firstPage) {
                this.firstPage = firstPage;
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
                 * money : 1
                 * createTime : 1558519626000
                 * mobile : 18888888888
                 * userName : mengmeng
                 * profit : -119
                 * customerName : 888
                 */

                private String money;
                private long createTime;
                private String mobile;
                private String userName;
                private String profit;
                private String customerName;

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public String getProfit() {
                    return profit;
                }

                public void setProfit(String profit) {
                    this.profit = profit;
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
}
