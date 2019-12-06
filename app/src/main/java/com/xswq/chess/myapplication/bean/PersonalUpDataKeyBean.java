package com.xswq.chess.myapplication.bean;

public class PersonalUpDataKeyBean {

    /**
     * error : {"returnCode":"0","returnMessage":"操作成功","returnUserMessage":"操作成功","userRanking":null,"homeTable":null}
     * data : {"id":167,"username":"22","password":"f5bb0c8de146c67b44babbf4e6584cc0","realname":"","mobile":"18888888888","email":"","usertype":5,"usersourse":0,"qq":"","wechat":"","sina":"","createtime":1526872418000,"headimg":"img/headImg/new(7).png","address":"江苏省:盐城市","agentid":0,"sex":2,"birthday":1525795200000,"state":0,"orgNo":"afdc5b025d9448a5a9012b4f48e294b6","manyFlg":0,"orgName":null,"loginState":0,"loginTime":null,"level":null,"cardName":null,"cardPsd":null,"matchOpts":1,"soundOpts":3,"imOpts":3,"experienceTime":1640072563000,"cooperationType":null,"token":null,"className":null,"branchNo":"50","plays":null,"classInfoCount":0,"studentCount":0,"classperiod":0,"totalperiod":0,"teacherUserId":null,"victory":0,"failure":0,"questionCount":0,"totalClass":0,"correct":0,"correctRate":null,"groupingId":null,"groupingName":null,"integral":100014,"teacherName":null,"classInfoId":null,"imToken":"qisheng123","imAccid":"167"}
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
         * id : 167
         * username : 22
         * password : f5bb0c8de146c67b44babbf4e6584cc0
         * realname :
         * mobile : 18888888888
         * email :
         * usertype : 5
         * usersourse : 0
         * qq :
         * wechat :
         * sina :
         * createtime : 1526872418000
         * headimg : img/headImg/new(7).png
         * address : 江苏省:盐城市
         * agentid : 0
         * sex : 2
         * birthday : 1525795200000
         * state : 0
         * orgNo : afdc5b025d9448a5a9012b4f48e294b6
         * manyFlg : 0
         * orgName : null
         * loginState : 0
         * loginTime : null
         * level : null
         * cardName : null
         * cardPsd : null
         * matchOpts : 1
         * soundOpts : 3
         * imOpts : 3
         * experienceTime : 1640072563000
         * cooperationType : null
         * token : null
         * className : null
         * branchNo : 50
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
         * integral : 100014
         * teacherName : null
         * classInfoId : null
         * imToken : qisheng123
         * imAccid : 167
         */

        private int id;
        private String username;
        private String password;
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
        private Object orgName;
        private int loginState;
        private Object loginTime;
        private Object level;
        private Object cardName;
        private Object cardPsd;
        private int matchOpts;
        private int soundOpts;
        private int imOpts;
        private long experienceTime;
        private Object cooperationType;
        private Object token;
        private Object className;
        private String branchNo;
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
        private Object classInfoId;
        private String imToken;
        private String imAccid;

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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
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

        public Object getOrgName() {
            return orgName;
        }

        public void setOrgName(Object orgName) {
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

        public Object getLevel() {
            return level;
        }

        public void setLevel(Object level) {
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

        public Object getCooperationType() {
            return cooperationType;
        }

        public void setCooperationType(Object cooperationType) {
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

        public String getBranchNo() {
            return branchNo;
        }

        public void setBranchNo(String branchNo) {
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

        public Object getClassInfoId() {
            return classInfoId;
        }

        public void setClassInfoId(Object classInfoId) {
            this.classInfoId = classInfoId;
        }

        public String getImToken() {
            return imToken;
        }

        public void setImToken(String imToken) {
            this.imToken = imToken;
        }

        public String getImAccid() {
            return imAccid;
        }

        public void setImAccid(String imAccid) {
            this.imAccid = imAccid;
        }
    }
}
