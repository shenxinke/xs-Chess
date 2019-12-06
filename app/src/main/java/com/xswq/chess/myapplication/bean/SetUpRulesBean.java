package com.xswq.chess.myapplication.bean;

public class SetUpRulesBean {
    private String inviteName;
    private String inviteUser;
    private String inviteLevel;
    private String inviteHeadImg;
    private String beInvitedName;
    private String beInvitedLevel;
    private String beInvitedUser;
    private String beInviteHeadImg;
    private String ruletype;
    private String size;
    private String messageType;

    public String getInviteName() {
        return inviteName;
    }

    public void setInviteName(String inviteName) {
        this.inviteName = inviteName;
    }

    public String getInviteUser() {
        return inviteUser;
    }

    public void setInviteUser(String inviteUser) {
        this.inviteUser = inviteUser;
    }

    public String getInviteLevel() {
        return inviteLevel;
    }

    public void setInviteLevel(String inviteLevel) {
        this.inviteLevel = inviteLevel;
    }

    public String getInviteHeadImg() {
        return inviteHeadImg;
    }

    public void setInviteHeadImg(String inviteHeadImg) {
        this.inviteHeadImg = inviteHeadImg;
    }

    public String getBeInvitedName() {
        return beInvitedName;
    }

    public void setBeInvitedName(String beInvitedName) {
        this.beInvitedName = beInvitedName;
    }

    public String getBeInvitedLevel() {
        return beInvitedLevel;
    }

    public void setBeInvitedLevel(String beInvitedLevel) {
        this.beInvitedLevel = beInvitedLevel;
    }

    public String getBeInvitedUser() {
        return beInvitedUser;
    }

    public void setBeInvitedUser(String beInvitedUser) {
        this.beInvitedUser = beInvitedUser;
    }

    public String getBeInviteHeadImg() {
        return beInviteHeadImg;
    }

    public void setBeInviteHeadImg(String beInviteHeadImg) {
        this.beInviteHeadImg = beInviteHeadImg;
    }

    public String getRuletype() {
        return ruletype;
    }

    public void setRuletype(String ruletype) {
        this.ruletype = ruletype;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}


//message:{
//        “inviteName”: “建行徐”, 邀请人姓名
//        “inviteUser”: “1748”,    邀请人 id
//        “inviteLevel”: “17”,    邀请人棋力
//        “inviteHeadImg”: “img/headImg/1.png” ，  邀请人头像
//        “beInvitedName”: “卢”,  被邀请人姓名
//        “beInvitedLevel”: “15”,  被邀请人棋力
//        “beInvitedUser”: “1516”,  被邀请人id
//        “beInviteHeadImg”: “img/headImg/1.png”,  被邀请人头像
//        Ruletype: “1”   1分先 2 让4子 3 让9子（分先，随机白黑 ，让子 发起方为执白）
//        size: “19”        19 9
//        messageType:”1”    1 发送邀请 2 同意对弈 3 修改规则 4 拒绝
//        }