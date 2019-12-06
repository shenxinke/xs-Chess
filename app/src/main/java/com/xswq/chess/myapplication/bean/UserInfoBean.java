package com.xswq.chess.myapplication.bean;

public class UserInfoBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"detail":{"userid":4960,"level":1,"victorynum":0,"losenum":0,"finishexercises":0,"advancenum":7,"username":null,"rownum":null,"updateleveltime":null,"starnum":21,"headborderImg":"img/shopStore/vipborder2.png","productEffect":"1"},"base":{"id":4960,"username":"申新科","password":null,"realname":"","mobile":"13693263002","email":"","usertype":2,"usersourse":0,"qq":"","wechat":"","sina":"","createtime":1541670096000,"headimg":"img/shopStore/viphead4.png","address":"北京:西城区","agentid":0,"sex":0,"birthday":1570723200000,"state":0,"orgNo":"2dbb559aaec811e8967f00163e0e9fad","manyFlg":0,"orgName":"先手围棋","loginState":0,"loginTime":null,"level":1,"cardName":null,"cardPsd":null,"matchOpts":1,"soundOpts":3,"imOpts":3,"experienceTime":1636364496000,"cooperationType":"幼儿园","token":null,"className":null,"branchNo":null,"plays":null,"classInfoCount":0,"studentCount":0,"classperiod":0,"totalperiod":0,"teacherUserId":null,"victory":0,"failure":0,"questionCount":0,"totalClass":0,"correct":0,"correctRate":null,"groupingId":null,"groupingName":null,"integral":948936,"teacherName":null,"victorynum":0,"losenum":0,"finishexercises":0,"advancenum":7,"headborderImg":"img/shopStore/vipborder2.png","productEffect":null,"classInfoId":null,"imAccid":"4960","imToken":null}}
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
         * detail : {"userid":4960,"level":1,"victorynum":0,"losenum":0,"finishexercises":0,"advancenum":7,"username":null,"rownum":null,"updateleveltime":null,"starnum":21,"headborderImg":"img/shopStore/vipborder2.png","productEffect":"1"}
         * base : {"id":4960,"username":"申新科","password":null,"realname":"","mobile":"13693263002","email":"","usertype":2,"usersourse":0,"qq":"","wechat":"","sina":"","createtime":1541670096000,"headimg":"img/shopStore/viphead4.png","address":"北京:西城区","agentid":0,"sex":0,"birthday":1570723200000,"state":0,"orgNo":"2dbb559aaec811e8967f00163e0e9fad","manyFlg":0,"orgName":"先手围棋","loginState":0,"loginTime":null,"level":1,"cardName":null,"cardPsd":null,"matchOpts":1,"soundOpts":3,"imOpts":3,"experienceTime":1636364496000,"cooperationType":"幼儿园","token":null,"className":null,"branchNo":null,"plays":null,"classInfoCount":0,"studentCount":0,"classperiod":0,"totalperiod":0,"teacherUserId":null,"victory":0,"failure":0,"questionCount":0,"totalClass":0,"correct":0,"correctRate":null,"groupingId":null,"groupingName":null,"integral":948936,"teacherName":null,"victorynum":0,"losenum":0,"finishexercises":0,"advancenum":7,"headborderImg":"img/shopStore/vipborder2.png","productEffect":null,"classInfoId":null,"imAccid":"4960","imToken":null}
         */

        private DetailBean detail;
        private BaseBean base;

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public BaseBean getBase() {
            return base;
        }

        public void setBase(BaseBean base) {
            this.base = base;
        }

        public static class DetailBean {
            /**
             * userid : 4960
             * level : 1
             * victorynum : 0
             * losenum : 0
             * finishexercises : 0
             * advancenum : 7
             * username : null
             * rownum : null
             * updateleveltime : null
             * starnum : 21
             * headborderImg : img/shopStore/vipborder2.png
             * productEffect : 1
             */

            private int userid;
            private int level;
            private int victorynum;
            private int losenum;
            private int finishexercises;
            private int advancenum;
            private Object username;
            private Object rownum;
            private Object updateleveltime;
            private int starnum;
            private String headborderImg;
            private String productEffect;

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getVictorynum() {
                return victorynum;
            }

            public void setVictorynum(int victorynum) {
                this.victorynum = victorynum;
            }

            public int getLosenum() {
                return losenum;
            }

            public void setLosenum(int losenum) {
                this.losenum = losenum;
            }

            public int getFinishexercises() {
                return finishexercises;
            }

            public void setFinishexercises(int finishexercises) {
                this.finishexercises = finishexercises;
            }

            public int getAdvancenum() {
                return advancenum;
            }

            public void setAdvancenum(int advancenum) {
                this.advancenum = advancenum;
            }

            public Object getUsername() {
                return username;
            }

            public void setUsername(Object username) {
                this.username = username;
            }

            public Object getRownum() {
                return rownum;
            }

            public void setRownum(Object rownum) {
                this.rownum = rownum;
            }

            public Object getUpdateleveltime() {
                return updateleveltime;
            }

            public void setUpdateleveltime(Object updateleveltime) {
                this.updateleveltime = updateleveltime;
            }

            public int getStarnum() {
                return starnum;
            }

            public void setStarnum(int starnum) {
                this.starnum = starnum;
            }

            public String getHeadborderImg() {
                return headborderImg;
            }

            public void setHeadborderImg(String headborderImg) {
                this.headborderImg = headborderImg;
            }

            public String getProductEffect() {
                return productEffect;
            }

            public void setProductEffect(String productEffect) {
                this.productEffect = productEffect;
            }
        }

        public static class BaseBean {
            /**
             * id : 4960
             * username : 申新科
             * password : null
             * realname :
             * mobile : 13693263002
             * email :
             * usertype : 2
             * usersourse : 0
             * qq :
             * wechat :
             * sina :
             * createtime : 1541670096000
             * headimg : img/shopStore/viphead4.png
             * address : 北京:西城区
             * agentid : 0
             * sex : 0
             * birthday : 1570723200000
             * state : 0
             * orgNo : 2dbb559aaec811e8967f00163e0e9fad
             * manyFlg : 0
             * orgName : 先手围棋
             * loginState : 0
             * loginTime : null
             * level : 1
             * cardName : null
             * cardPsd : null
             * matchOpts : 1
             * soundOpts : 3
             * imOpts : 3
             * experienceTime : 1636364496000
             * cooperationType : 幼儿园
             * token : null
             * className : null
             * branchNo : null
             * plays : null
             * classInfoCount : 0
             * studentCount : 0
             * classperiod : 0
             * totalperiod : 0
             * teacherUserId : null
             * victory : 0
             * failure : 0
             * questionCount : 0
             * totalClass : 0
             * correct : 0
             * correctRate : null
             * groupingId : null
             * groupingName : null
             * integral : 948936
             * teacherName : null
             * victorynum : 0
             * losenum : 0
             * finishexercises : 0
             * advancenum : 7
             * headborderImg : img/shopStore/vipborder2.png
             * productEffect : null
             * classInfoId : null
             * imAccid : 4960
             * imToken : null
             */

            private int id;
            private String username;
            private Object password;
            private String realname;
            private String mobile;
            private String email;
            private int usertype;
            private int usersourse;
            private String qq;
            private String wechat;
            private String sina;
            private long createtime;
            private String headimg;
            private String address;
            private int agentid;
            private int sex;
            private long birthday;
            private int state;
            private String orgNo;
            private int manyFlg;
            private String orgName;
            private int loginState;
            private Object loginTime;
            private int level;
            private Object cardName;
            private Object cardPsd;
            private int matchOpts;
            private int soundOpts;
            private int imOpts;
            private long experienceTime;
            private String cooperationType;
            private Object token;
            private Object className;
            private Object branchNo;
            private Object plays;
            private int classInfoCount;
            private int studentCount;
            private int classperiod;
            private int totalperiod;
            private Object teacherUserId;
            private int victory;
            private int failure;
            private int questionCount;
            private int totalClass;
            private int correct;
            private Object correctRate;
            private Object groupingId;
            private Object groupingName;
            private int integral;
            private Object teacherName;
            private int victorynum;
            private int losenum;
            private int finishexercises;
            private int advancenum;
            private String headborderImg;
            private Object productEffect;
            private Object classInfoId;
            private String imAccid;
            private Object imToken;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public Object getPassword() {
                return password;
            }

            public void setPassword(Object password) {
                this.password = password;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getUsertype() {
                return usertype;
            }

            public void setUsertype(int usertype) {
                this.usertype = usertype;
            }

            public int getUsersourse() {
                return usersourse;
            }

            public void setUsersourse(int usersourse) {
                this.usersourse = usersourse;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public String getWechat() {
                return wechat;
            }

            public void setWechat(String wechat) {
                this.wechat = wechat;
            }

            public String getSina() {
                return sina;
            }

            public void setSina(String sina) {
                this.sina = sina;
            }

            public long getCreatetime() {
                return createtime;
            }

            public void setCreatetime(long createtime) {
                this.createtime = createtime;
            }

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getAgentid() {
                return agentid;
            }

            public void setAgentid(int agentid) {
                this.agentid = agentid;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public long getBirthday() {
                return birthday;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getOrgNo() {
                return orgNo;
            }

            public void setOrgNo(String orgNo) {
                this.orgNo = orgNo;
            }

            public int getManyFlg() {
                return manyFlg;
            }

            public void setManyFlg(int manyFlg) {
                this.manyFlg = manyFlg;
            }

            public String getOrgName() {
                return orgName;
            }

            public void setOrgName(String orgName) {
                this.orgName = orgName;
            }

            public int getLoginState() {
                return loginState;
            }

            public void setLoginState(int loginState) {
                this.loginState = loginState;
            }

            public Object getLoginTime() {
                return loginTime;
            }

            public void setLoginTime(Object loginTime) {
                this.loginTime = loginTime;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public Object getCardName() {
                return cardName;
            }

            public void setCardName(Object cardName) {
                this.cardName = cardName;
            }

            public Object getCardPsd() {
                return cardPsd;
            }

            public void setCardPsd(Object cardPsd) {
                this.cardPsd = cardPsd;
            }

            public int getMatchOpts() {
                return matchOpts;
            }

            public void setMatchOpts(int matchOpts) {
                this.matchOpts = matchOpts;
            }

            public int getSoundOpts() {
                return soundOpts;
            }

            public void setSoundOpts(int soundOpts) {
                this.soundOpts = soundOpts;
            }

            public int getImOpts() {
                return imOpts;
            }

            public void setImOpts(int imOpts) {
                this.imOpts = imOpts;
            }

            public long getExperienceTime() {
                return experienceTime;
            }

            public void setExperienceTime(long experienceTime) {
                this.experienceTime = experienceTime;
            }

            public String getCooperationType() {
                return cooperationType;
            }

            public void setCooperationType(String cooperationType) {
                this.cooperationType = cooperationType;
            }

            public Object getToken() {
                return token;
            }

            public void setToken(Object token) {
                this.token = token;
            }

            public Object getClassName() {
                return className;
            }

            public void setClassName(Object className) {
                this.className = className;
            }

            public Object getBranchNo() {
                return branchNo;
            }

            public void setBranchNo(Object branchNo) {
                this.branchNo = branchNo;
            }

            public Object getPlays() {
                return plays;
            }

            public void setPlays(Object plays) {
                this.plays = plays;
            }

            public int getClassInfoCount() {
                return classInfoCount;
            }

            public void setClassInfoCount(int classInfoCount) {
                this.classInfoCount = classInfoCount;
            }

            public int getStudentCount() {
                return studentCount;
            }

            public void setStudentCount(int studentCount) {
                this.studentCount = studentCount;
            }

            public int getClassperiod() {
                return classperiod;
            }

            public void setClassperiod(int classperiod) {
                this.classperiod = classperiod;
            }

            public int getTotalperiod() {
                return totalperiod;
            }

            public void setTotalperiod(int totalperiod) {
                this.totalperiod = totalperiod;
            }

            public Object getTeacherUserId() {
                return teacherUserId;
            }

            public void setTeacherUserId(Object teacherUserId) {
                this.teacherUserId = teacherUserId;
            }

            public int getVictory() {
                return victory;
            }

            public void setVictory(int victory) {
                this.victory = victory;
            }

            public int getFailure() {
                return failure;
            }

            public void setFailure(int failure) {
                this.failure = failure;
            }

            public int getQuestionCount() {
                return questionCount;
            }

            public void setQuestionCount(int questionCount) {
                this.questionCount = questionCount;
            }

            public int getTotalClass() {
                return totalClass;
            }

            public void setTotalClass(int totalClass) {
                this.totalClass = totalClass;
            }

            public int getCorrect() {
                return correct;
            }

            public void setCorrect(int correct) {
                this.correct = correct;
            }

            public Object getCorrectRate() {
                return correctRate;
            }

            public void setCorrectRate(Object correctRate) {
                this.correctRate = correctRate;
            }

            public Object getGroupingId() {
                return groupingId;
            }

            public void setGroupingId(Object groupingId) {
                this.groupingId = groupingId;
            }

            public Object getGroupingName() {
                return groupingName;
            }

            public void setGroupingName(Object groupingName) {
                this.groupingName = groupingName;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public Object getTeacherName() {
                return teacherName;
            }

            public void setTeacherName(Object teacherName) {
                this.teacherName = teacherName;
            }

            public int getVictorynum() {
                return victorynum;
            }

            public void setVictorynum(int victorynum) {
                this.victorynum = victorynum;
            }

            public int getLosenum() {
                return losenum;
            }

            public void setLosenum(int losenum) {
                this.losenum = losenum;
            }

            public int getFinishexercises() {
                return finishexercises;
            }

            public void setFinishexercises(int finishexercises) {
                this.finishexercises = finishexercises;
            }

            public int getAdvancenum() {
                return advancenum;
            }

            public void setAdvancenum(int advancenum) {
                this.advancenum = advancenum;
            }

            public String getHeadborderImg() {
                return headborderImg;
            }

            public void setHeadborderImg(String headborderImg) {
                this.headborderImg = headborderImg;
            }

            public Object getProductEffect() {
                return productEffect;
            }

            public void setProductEffect(Object productEffect) {
                this.productEffect = productEffect;
            }

            public Object getClassInfoId() {
                return classInfoId;
            }

            public void setClassInfoId(Object classInfoId) {
                this.classInfoId = classInfoId;
            }

            public String getImAccid() {
                return imAccid;
            }

            public void setImAccid(String imAccid) {
                this.imAccid = imAccid;
            }

            public Object getImToken() {
                return imToken;
            }

            public void setImToken(Object imToken) {
                this.imToken = imToken;
            }
        }
    }
}
