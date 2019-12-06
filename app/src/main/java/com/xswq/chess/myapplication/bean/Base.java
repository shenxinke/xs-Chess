package com.xswq.chess.myapplication.bean;

public class Base {

    public Base(){

    }
    private	int	id	;//	Userid
    private	String	username	;//	用户平台名称
    private	String	password	;//	用户密码
    private	String	realname	;//	真是姓名
    private	String	mobile	;//	手机号
    private	String	email	;//	邮箱号
    private	int	usertype	;//	用户角色 1 老师 2 普通用户 4 AI
    private	int	usersourse	;//	用户来源 1QQ 2微信 3新浪
    private	String	qq	;//	QQ
    private	String	wechat	;//	wechat
    private	String	sina	;//	sina
    private	String	createtime	;//	创建时间
    private	String	headimg	;//	头像
    private	String	address	;//	用户地址
    private	int	agentid	;//	机构id
    private	int	sex	;//	性别  1 男  2 女
    private	String	birthday	;//	生日
    private	int	state	;//	状态 1 禁用  0 启用
    private	int	orgNo	;//	客户id
    private	int	manyFlg	;//	是否支持多人对战 1是，2否
    private	int	orgName	;//	对应机构id
    private	int	loginState	;//	登陆状态 1已经登陆 2 未登录
    private	String	loginTime	;//	登陆时间
    private	int	level	;//	用户等级
    private	String	cardName	;//	平台卡号
    private	String	cardPsd	;//	平台密码
    private	int	matchOpts	;//	系统设置 0 - 不允许邀请对弈， 1 - 允许对局邀请
    private	int	soundOpts	;//	系统设置 0 - 无声音， 1 - 落子音, 2-提示音，3-落子音 + 提示音
    private	int	imOpts	;//	系统设置  0 - 不允许加好友，无上线提醒，1 - 允许加好友，2 - 好友上线提醒，3 加好友+提醒
    private	String	experienceTime	;//	平台卡号的过期时间
    private	String	token	;//	每次登陆生成的token值
    private	String	className	;//	班级名称
    private	String	teacherName	;//	老师昵称
    private	int	classInfoId	;//	班级id
    private	String	imAccid	;//	网易云id
    private	String	imToken	;//	网易云token

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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(int orgNo) {
        this.orgNo = orgNo;
    }

    public int getManyFlg() {
        return manyFlg;
    }

    public void setManyFlg(int manyFlg) {
        this.manyFlg = manyFlg;
    }

    public int getOrgName() {
        return orgName;
    }

    public void setOrgName(int orgName) {
        this.orgName = orgName;
    }

    public int getLoginState() {
        return loginState;
    }

    public void setLoginState(int loginState) {
        this.loginState = loginState;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardPsd() {
        return cardPsd;
    }

    public void setCardPsd(String cardPsd) {
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

    public String getExperienceTime() {
        return experienceTime;
    }

    public void setExperienceTime(String experienceTime) {
        this.experienceTime = experienceTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getClassInfoId() {
        return classInfoId;
    }

    public void setClassInfoId(int classInfoId) {
        this.classInfoId = classInfoId;
    }

    public String getImAccid() {
        return imAccid;
    }

    public void setImAccid(String imAccid) {
        this.imAccid = imAccid;
    }

    public String getImToken() {
        return imToken;
    }

    public void setImToken(String imToken) {
        this.imToken = imToken;
    }
}
