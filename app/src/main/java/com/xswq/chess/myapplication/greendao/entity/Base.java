package com.xswq.chess.myapplication.greendao.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Base {

    @Id(autoincrement = true)
    private Long IdIndex;
    @Property(nameInDb = "USERID")
    private String id;//	Userid
    @Property(nameInDb = "USERNAME")
    private String username;//	用户平台名称
    @Property(nameInDb = "PASSWORD")
    private String password;//	用户密码
    @Property(nameInDb = "REALNAME")
    private String realname;//	真是姓名
    @Property(nameInDb = "MOBILE")
    private String mobile;//	手机号
    @Property(nameInDb = "EMAIL")
    private String email;//	邮箱号
    @Property(nameInDb = "USERTYPE")
    private int usertype;//	用户角色 1 老师 2 普通用户 4 AI
    @Property(nameInDb = "USERSOURSE")
    private int usersourse;//	用户来源 1QQ 2微信 3新浪
    @Property(nameInDb = "QQ")
    private String qq;//	QQ
    @Property(nameInDb = "WECHAT")
    private String wechat;//	wechat
    @Property(nameInDb = "SINA")
    private String sina;//	sina
    @Property(nameInDb = "CREATETIME")
    private String createtime;//	创建时间
    @Property(nameInDb = "HEADIMG")
    private String headimg;//	头像
    @Property(nameInDb = "ADDRESS")
    private String address;//	用户地址
    @Property(nameInDb = "AGETID")
    private int agentid;//	机构id
    @Property(nameInDb = "SEX")
    private int sex;//	性别  1 男  2 女
    @Property(nameInDb = "BIRTHDAY")
    private String birthday;//	生日
    @Property(nameInDb = "STATE")
    private int state;//	状态 1 禁用  0 启用
    @Property(nameInDb = "ORGNO")
    private String orgNo;//	客户id
    @Property(nameInDb = "MANYFLAG")
    private int manyFlg;//	是否支持多人对战 1是，2否
    @Property(nameInDb = "ORGNAME")
    private String orgName;//	对应机构id
    @Property(nameInDb = "LOGINSTATE")
    private int loginState;//	登陆状态 1已经登陆 2 未登录
    @Property(nameInDb = "LOGINTIME")
    private String loginTime;//	登陆时间
    @Property(nameInDb = "LEVEL")
    private int level;//	用户等级
    @Property(nameInDb = "CARDNAME")
    private String cardName;//	平台卡号
    @Property(nameInDb = "CARDPSD")
    private String cardPsd;//	平台密码
    @Property(nameInDb = "MATCHOPTS")
    private int matchOpts;//	系统设置 0 - 不允许邀请对弈， 1 - 允许对局邀请
    @Property(nameInDb = "SOUNDOPTS")
    private int soundOpts;//	系统设置 0 - 无声音， 1 - 落子音, 2-提示音，3-落子音 + 提示音
    @Property(nameInDb = "IMOPTS")
    private int imOpts;//	系统设置  0 - 不允许加好友，无上线提醒，1 - 允许加好友，2 - 好友上线提醒，3 加好友+提醒
    @Property(nameInDb = "EXPERIENCETIME")
    private String experienceTime;//	平台卡号的过期时间
    @Property(nameInDb = "COOPERATIONTYPE")
    private String cooperationType;
    @Property(nameInDb = "TOKEN")
    private String token;//	每次登陆生成的token值
    @Property(nameInDb = "CLASSNAME")
    private String className;//	班级名称
    @Property(nameInDb = "BRANCHNO")
    private String branchNo;//分校编号
    @Property(nameInDb = "PLAYS")
    private String plays;//是否允许直播（1允许）
    @Property(nameInDb = "TEACHERNAME")
    private String teacherName;//	老师昵称
    @Property(nameInDb = "VICTORYNUM")
    private String victorynum;
    @Property(nameInDb = "LOSENUM")
    private String losenum;
    @Property(nameInDb = "FINISHEXERCISES")
    private String finishexercises;
    @Property(nameInDb = "ADVANCENUM")
    private int advancenum;
    @Property(nameInDb = "CLASSINFOID")
    private String classInfoId;//	班级id
    @Property(nameInDb = "IMACCID")
    private String imAccid;//	网易云id
    @Property(nameInDb = "IMTOKEN")
    private String imToken;//	网易云token
    @Generated(hash = 914493763)
    public Base(Long IdIndex, String id, String username, String password,
            String realname, String mobile, String email, int usertype,
            int usersourse, String qq, String wechat, String sina,
            String createtime, String headimg, String address, int agentid, int sex,
            String birthday, int state, String orgNo, int manyFlg, String orgName,
            int loginState, String loginTime, int level, String cardName,
            String cardPsd, int matchOpts, int soundOpts, int imOpts,
            String experienceTime, String cooperationType, String token,
            String className, String branchNo, String plays, String teacherName,
            String victorynum, String losenum, String finishexercises,
            int advancenum, String classInfoId, String imAccid, String imToken) {
        this.IdIndex = IdIndex;
        this.id = id;
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.mobile = mobile;
        this.email = email;
        this.usertype = usertype;
        this.usersourse = usersourse;
        this.qq = qq;
        this.wechat = wechat;
        this.sina = sina;
        this.createtime = createtime;
        this.headimg = headimg;
        this.address = address;
        this.agentid = agentid;
        this.sex = sex;
        this.birthday = birthday;
        this.state = state;
        this.orgNo = orgNo;
        this.manyFlg = manyFlg;
        this.orgName = orgName;
        this.loginState = loginState;
        this.loginTime = loginTime;
        this.level = level;
        this.cardName = cardName;
        this.cardPsd = cardPsd;
        this.matchOpts = matchOpts;
        this.soundOpts = soundOpts;
        this.imOpts = imOpts;
        this.experienceTime = experienceTime;
        this.cooperationType = cooperationType;
        this.token = token;
        this.className = className;
        this.branchNo = branchNo;
        this.plays = plays;
        this.teacherName = teacherName;
        this.victorynum = victorynum;
        this.losenum = losenum;
        this.finishexercises = finishexercises;
        this.advancenum = advancenum;
        this.classInfoId = classInfoId;
        this.imAccid = imAccid;
        this.imToken = imToken;
    }
    @Generated(hash = 1335444615)
    public Base() {
    }
    public Long getIdIndex() {
        return this.IdIndex;
    }
    public void setIdIndex(Long IdIndex) {
        this.IdIndex = IdIndex;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRealname() {
        return this.realname;
    }
    public void setRealname(String realname) {
        this.realname = realname;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getUsertype() {
        return this.usertype;
    }
    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
    public int getUsersourse() {
        return this.usersourse;
    }
    public void setUsersourse(int usersourse) {
        this.usersourse = usersourse;
    }
    public String getQq() {
        return this.qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
    public String getWechat() {
        return this.wechat;
    }
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    public String getSina() {
        return this.sina;
    }
    public void setSina(String sina) {
        this.sina = sina;
    }
    public String getCreatetime() {
        return this.createtime;
    }
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    public String getHeadimg() {
        return this.headimg;
    }
    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getAgentid() {
        return this.agentid;
    }
    public void setAgentid(int agentid) {
        this.agentid = agentid;
    }
    public int getSex() {
        return this.sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public String getBirthday() {
        return this.birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public int getState() {
        return this.state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public String getOrgNo() {
        return this.orgNo;
    }
    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }
    public int getManyFlg() {
        return this.manyFlg;
    }
    public void setManyFlg(int manyFlg) {
        this.manyFlg = manyFlg;
    }
    public String getOrgName() {
        return this.orgName;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public int getLoginState() {
        return this.loginState;
    }
    public void setLoginState(int loginState) {
        this.loginState = loginState;
    }
    public String getLoginTime() {
        return this.loginTime;
    }
    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
    public int getLevel() {
        return this.level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public String getCardName() {
        return this.cardName;
    }
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    public String getCardPsd() {
        return this.cardPsd;
    }
    public void setCardPsd(String cardPsd) {
        this.cardPsd = cardPsd;
    }
    public int getMatchOpts() {
        return this.matchOpts;
    }
    public void setMatchOpts(int matchOpts) {
        this.matchOpts = matchOpts;
    }
    public int getSoundOpts() {
        return this.soundOpts;
    }
    public void setSoundOpts(int soundOpts) {
        this.soundOpts = soundOpts;
    }
    public int getImOpts() {
        return this.imOpts;
    }
    public void setImOpts(int imOpts) {
        this.imOpts = imOpts;
    }
    public String getExperienceTime() {
        return this.experienceTime;
    }
    public void setExperienceTime(String experienceTime) {
        this.experienceTime = experienceTime;
    }
    public String getCooperationType() {
        return this.cooperationType;
    }
    public void setCooperationType(String cooperationType) {
        this.cooperationType = cooperationType;
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getClassName() {
        return this.className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getBranchNo() {
        return this.branchNo;
    }
    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }
    public String getPlays() {
        return this.plays;
    }
    public void setPlays(String plays) {
        this.plays = plays;
    }
    public String getTeacherName() {
        return this.teacherName;
    }
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public String getVictorynum() {
        return this.victorynum;
    }
    public void setVictorynum(String victorynum) {
        this.victorynum = victorynum;
    }
    public String getLosenum() {
        return this.losenum;
    }
    public void setLosenum(String losenum) {
        this.losenum = losenum;
    }
    public String getFinishexercises() {
        return this.finishexercises;
    }
    public void setFinishexercises(String finishexercises) {
        this.finishexercises = finishexercises;
    }
    public int getAdvancenum() {
        return this.advancenum;
    }
    public void setAdvancenum(int advancenum) {
        this.advancenum = advancenum;
    }
    public String getClassInfoId() {
        return this.classInfoId;
    }
    public void setClassInfoId(String classInfoId) {
        this.classInfoId = classInfoId;
    }
    public String getImAccid() {
        return this.imAccid;
    }
    public void setImAccid(String imAccid) {
        this.imAccid = imAccid;
    }
    public String getImToken() {
        return this.imToken;
    }
    public void setImToken(String imToken) {
        this.imToken = imToken;
    }
 
}
