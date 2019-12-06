package com.xswq.chess.myapplication.bean;

public class StudentDayTaskBean {


    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"course":1,"story":1,"storySum":"1","chessNum":3,"playChessSum":"3","jobSum":5,"game":1,"courseSum":"1","gameSum":"1","userInfo":{"id":168,"username":"唯一宝宝","password":null,"realname":"","mobile":"13041290189","email":"","usertype":3,"usersourse":0,"qq":"","wechat":"","sina":"","createtime":1526988622000,"headimg":"img/headImg/10.png","address":"北京市:朝阳区","agentid":0,"sex":2,"birthday":802972800000,"state":0,"orgNo":"00c418d334114aecbf9cd7d6d198952b","manyFlg":0,"orgName":"幼儿园","loginState":0,"loginTime":null,"level":12,"cardName":null,"cardPsd":null,"matchOpts":1,"soundOpts":3,"imOpts":3,"experienceTime":1596865344000,"cooperationType":"幼儿园","token":null,"className":"1","branchNo":null,"plays":null,"classInfoCount":0,"studentCount":0,"classperiod":0,"totalperiod":0,"teacherUserId":null,"victory":0,"failure":0,"questionCount":0,"totalClass":0,"correct":0,"correctRate":null,"groupingId":null,"groupingName":null,"integral":9999,"teacherName":"汪洋","victorynum":null,"losenum":null,"finishexercises":null,"advancenum":29,"headborderImg":null,"productEffect":null,"classInfoId":"555","imAccid":"168","imToken":null},"composite":"y","jobNum":1}
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

        private int returnCode;
        private String returnMessage;
        private String returnUserMessage;
        private Object userRanking;
        private Object homeTable;

        public int getReturnCode() {
            return returnCode;
        }

        public void setReturnCode(int returnCode) {
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
         * course : 1
         * story : 1
         * storySum : 1
         * chessNum : 3
         * playChessSum : 3
         * jobSum : 5
         * game : 1
         * courseSum : 1
         * gameSum : 1
         * userInfo : {"id":168,"username":"唯一宝宝","password":null,"realname":"","mobile":"13041290189","email":"","usertype":3,"usersourse":0,"qq":"","wechat":"","sina":"","createtime":1526988622000,"headimg":"img/headImg/10.png","address":"北京市:朝阳区","agentid":0,"sex":2,"birthday":802972800000,"state":0,"orgNo":"00c418d334114aecbf9cd7d6d198952b","manyFlg":0,"orgName":"幼儿园","loginState":0,"loginTime":null,"level":12,"cardName":null,"cardPsd":null,"matchOpts":1,"soundOpts":3,"imOpts":3,"experienceTime":1596865344000,"cooperationType":"幼儿园","token":null,"className":"1","branchNo":null,"plays":null,"classInfoCount":0,"studentCount":0,"classperiod":0,"totalperiod":0,"teacherUserId":null,"victory":0,"failure":0,"questionCount":0,"totalClass":0,"correct":0,"correctRate":null,"groupingId":null,"groupingName":null,"integral":9999,"teacherName":"汪洋","victorynum":null,"losenum":null,"finishexercises":null,"advancenum":29,"headborderImg":null,"productEffect":null,"classInfoId":"555","imAccid":"168","imToken":null}
         * composite : y
         * jobNum : 1
         */

        private String course;
        private String story;
        private String storySum;
        private String chessNum;
        private String playChessSum;
        private String jobSum;
        private String game;
        private String courseSum;
        private String gameSum;
        private UserInfoBean userInfo;
        private String composite;
        private String jobNum;

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getStory() {
            return story;
        }

        public void setStory(String story) {
            this.story = story;
        }

        public String getStorySum() {
            return storySum;
        }

        public void setStorySum(String storySum) {
            this.storySum = storySum;
        }

        public String getChessNum() {
            return chessNum;
        }

        public void setChessNum(String chessNum) {
            this.chessNum = chessNum;
        }

        public String getPlayChessSum() {
            return playChessSum;
        }

        public void setPlayChessSum(String playChessSum) {
            this.playChessSum = playChessSum;
        }

        public String getJobSum() {
            return jobSum;
        }

        public void setJobSum(String jobSum) {
            this.jobSum = jobSum;
        }

        public String getGame() {
            return game;
        }

        public void setGame(String game) {
            this.game = game;
        }

        public String getCourseSum() {
            return courseSum;
        }

        public void setCourseSum(String courseSum) {
            this.courseSum = courseSum;
        }

        public String getGameSum() {
            return gameSum;
        }

        public void setGameSum(String gameSum) {
            this.gameSum = gameSum;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public String getComposite() {
            return composite;
        }

        public void setComposite(String composite) {
            this.composite = composite;
        }

        public String getJobNum() {
            return jobNum;
        }

        public void setJobNum(String jobNum) {
            this.jobNum = jobNum;
        }

        public static class UserInfoBean {
            /**
             * id : 168
             * username : 唯一宝宝
             * password : null
             * realname :
             * mobile : 13041290189
             * email :
             * usertype : 3
             * usersourse : 0
             * qq :
             * wechat :
             * sina :
             * createtime : 1526988622000
             * headimg : img/headImg/10.png
             * address : 北京市:朝阳区
             * agentid : 0
             * sex : 2
             * birthday : 802972800000
             * state : 0
             * orgNo : 00c418d334114aecbf9cd7d6d198952b
             * manyFlg : 0
             * orgName : 幼儿园
             * loginState : 0
             * loginTime : null
             * level : 12
             * cardName : null
             * cardPsd : null
             * matchOpts : 1
             * soundOpts : 3
             * imOpts : 3
             * experienceTime : 1596865344000
             * cooperationType : 幼儿园
             * token : null
             * className : 1
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
             * integral : 9999
             * teacherName : 汪洋
             * victorynum : null
             * losenum : null
             * finishexercises : null
             * advancenum : 29
             * headborderImg : null
             * productEffect : null
             * classInfoId : 555
             * imAccid : 168
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
            private String className;
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
            private String teacherName;
            private Object victorynum;
            private Object losenum;
            private Object finishexercises;
            private int advancenum;
            private Object headborderImg;
            private Object productEffect;
            private String classInfoId;
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

            public String getClassName() {
                return className;
            }

            public void setClassName(String className) {
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

            public String getTeacherName() {
                return teacherName;
            }

            public void setTeacherName(String teacherName) {
                this.teacherName = teacherName;
            }

            public Object getVictorynum() {
                return victorynum;
            }

            public void setVictorynum(Object victorynum) {
                this.victorynum = victorynum;
            }

            public Object getLosenum() {
                return losenum;
            }

            public void setLosenum(Object losenum) {
                this.losenum = losenum;
            }

            public Object getFinishexercises() {
                return finishexercises;
            }

            public void setFinishexercises(Object finishexercises) {
                this.finishexercises = finishexercises;
            }

            public int getAdvancenum() {
                return advancenum;
            }

            public void setAdvancenum(int advancenum) {
                this.advancenum = advancenum;
            }

            public Object getHeadborderImg() {
                return headborderImg;
            }

            public void setHeadborderImg(Object headborderImg) {
                this.headborderImg = headborderImg;
            }

            public Object getProductEffect() {
                return productEffect;
            }

            public void setProductEffect(Object productEffect) {
                this.productEffect = productEffect;
            }

            public String getClassInfoId() {
                return classInfoId;
            }

            public void setClassInfoId(String classInfoId) {
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
