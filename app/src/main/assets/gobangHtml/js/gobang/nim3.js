var nim_data = {};
var nim_instance;
function initNim(opts) {
	var appKey = "f2ee483b27648349f2da06d6d01456c2";
	var uid = Tool.queryString("uid")||readCookie("userid");;
	var sdkToken = 'qisheng123';

//    if (!sdkToken) {
//        return false;
//    }

	window.nim = nim_instance = SDK.NIM.getInstance({
		// 初始化SDK
		// debug: true
		db : true,
		appKey : appKey,
		account : uid,
		token : sdkToken,
		onconnect : onConnect,
		onerror : onError,
		onwillreconnect : onWillReconnect,
		ondisconnect : onDisconnect,
		// 多端登录
		onloginportschange : onLoginPortsChange,
		// 用户关系
		onblacklist : onBlacklist,
		onsyncmarkinblacklist : onMarkInBlacklist,
		onmutelist : onMutelist,
		onsyncmarkinmutelist : onMarkInMutelist,
		// 好友关系
		onfriends : onFriends,
		onsyncfriendaction : onSyncFriendAction,
		// 用户名片
		onmyinfo : onMyInfo,
		onupdatemyinfo : onUpdateMyInfo,
		onusers : onUsers,
		onupdateuser : onUpdateUser,
		// 机器人列表的回调
		onrobots : onRobots,
		// 群组
		onteams : onTeams,
		onsynccreateteam : onCreateTeam,
		onUpdateTeam : onUpdateTeam,
		onteammembers : onTeamMembers,
		// onsyncteammembersdone: onSyncTeamMembersDone,
		onupdateteammember : onUpdateTeamMember,
		// 群消息业务已读通知
		onTeamMsgReceipt : onTeamMsgReceipt,
		// 会话
		onsessions : onSessions,
		onupdatesession : onUpdateSession,
		// 消息
		onroamingmsgs : onRoamingMsgs,
		onofflinemsgs : onOfflineMsgs,
		onmsg : onMsg,
		// 系统通知
		onofflinesysmsgs : onOfflineSysMsgs,
		onsysmsg : onSysMsg,
		onupdatesysmsg : onUpdateSysMsg,
		onsysmsgunread : onSysMsgUnread,
		onupdatesysmsgunread : onUpdateSysMsgUnread,
		onofflinecustomsysmsgs : onOfflineCustomSysMsgs,
		oncustomsysmsg : onCustomSysMsg,
		// 收到广播消息
		onbroadcastmsg : onBroadcastMsg,
		onbroadcastmsgs : onBroadcastMsgs,
		// 同步完成
		onsyncdone : (opts && opts.onSyncDone) || onSyncDone
	});

	return window.nim;
}

function onConnect() {
	console.log('连接成功');
	eve.f("nim_onConnect")();
}
function onWillReconnect(obj) {
	// 此时说明 `SDK` 已经断开连接, 请开发者在界面上提示用户连接已断开, 而且正在重新建立连接
	console.log('即将重连');
	console.log(obj.retryCount);
	console.log(obj.duration);
}
function onDisconnect(error) {
	// 此时说明 `SDK` 处于断开状态, 开发者此时应该根据错误码提示相应的错误信息, 并且跳转到登录页面
	console.log('丢失连接');
	console.log(error);
	if (error) {
		switch (error.code) {
		// 账号或者密码错误, 请跳转到登录页面并提示错误
		case 302:
			break;
		// 被踢, 请提示错误后跳转到登录页面
		case 'kicked':
			break;
		default:
			break;
		}
	}
}
function onError(error) {
	console.log(error);
}

function onLoginPortsChange(loginPorts) {
	console.log('当前登录帐号在其它端的状态发生改变了', loginPorts);
}

function onBlacklist(blacklist) {
	console.log('收到黑名单', blacklist);
	nim_data.blacklist = nim_instance.mergeRelations(nim_data.blacklist,
			blacklist);
	nim_data.blacklist = nim_instance.cutRelations(nim_data.blacklist,
			blacklist.invalid);
	refreshBlacklistUI();
}
function onMarkInBlacklist(obj) {
	console.log(obj);
	console.log(obj.account + '被你在其它端' + (obj.isAdd ? '加入' : '移除') + '黑名单');
	if (obj.isAdd) {
		addToBlacklist(obj);
	} else {
		removeFromBlacklist(obj);
	}
}
function addToBlacklist(obj) {
	nim_data.blacklist = nim_instance.mergeRelations(nim_data.blacklist,
			obj.record);
	refreshBlacklistUI();
}
function removeFromBlacklist(obj) {
	nim_data.blacklist = nim_instance.cutRelations(nim_data.blacklist,
			obj.record);
	refreshBlacklistUI();
}
function refreshBlacklistUI() {
	// 刷新界面
	eve.f("nim_refreshBlacklist")();
}
function onMutelist(mutelist) {
	console.log('收到静音列表', mutelist);
	nim_data.mutelist = nim_instance
			.mergeRelations(nim_data.mutelist, mutelist);
	nim_data.mutelist = nim_instance.cutRelations(nim_data.mutelist,
			mutelist.invalid);
	refreshMutelistUI();
}
function onMarkInMutelist(obj) {
	console.log(obj);
	console.log(obj.account + '被你' + (obj.isAdd ? '加入' : '移除') + '静音列表');
	if (obj.isAdd) {
		addToMutelist(obj);
	} else {
		removeFromMutelist(obj);
	}
}
function addToMutelist(obj) {
	nim_data.mutelist = nim_instance.mergeRelations(nim_data.mutelist,
			obj.record);
	refreshMutelistUI();
}
function removeFromMutelist(obj) {
	nim_data.mutelist = nim_instance
			.cutRelations(nim_data.mutelist, obj.record);
	refreshMutelistUI();
}
function refreshMutelistUI() {
	// 刷新界面
}

function onFriends(friends) {
	console.log('收到好友列表', friends);
	nim_data.friends = nim_instance.mergeFriends(nim_data.friends, friends);
	nim_data.friends = nim_instance.cutFriends(nim_data.friends,
			friends.invalid);
	console.log('收到好友列表2', friends);
	refreshFriendsUI();
}
function onSyncFriendAction(obj) {
	console.log(obj);
	switch (obj.type) {
	case 'addFriend':
		console.log('你在其它端直接加了一个好友' + obj.account + ', 附言' + obj.ps);
		onAddFriend(obj);
		break;
	case 'applyFriend':
		console.log('你在其它端申请加了一个好友' + obj.account + ', 附言' + obj.ps);
		break;
	case 'passFriendApply':
		console.log('你在其它端通过了一个好友申请' + obj.account + ', 附言' + obj.ps);
		onAddFriend(obj);
		break;
	case 'rejectFriendApply':
		console.log('你在其它端拒绝了一个好友申请' + obj.account + ', 附言' + obj.ps);
		break;
	case 'deleteFriend':
		console.log('你在其它端删了一个好友' + obj.account);
		onDeleteFriend(obj);
		break;
	case 'updateFriend':
		console.log('你在其它端更新了一个好友', obj.friend);
		onUpdateFriend(obj);
		break;
	}
}
function onAddFriend(friend) {
	nim_data.friends = nim_instance.mergeFriends(nim_data.friends, friend);

	refreshFriendsUI();
}
function onDeleteFriend(account) {
	nim_data.friends = nim_instance.cutFriendsByAccounts(nim_data.friends,
			account.account);
	refreshFriendsUI();
}
function onUpdateFriend(friend) {
	nim_data.friends = nim_instance.mergeFriends(nim_data.friends, friend);
	refreshFriendsUI();
}
function refreshFriendsUI() {
	// 刷新界面
	eve.f("nim_refreshFriends")();
}

function onMyInfo(user) {
	console.log('收到我的名片', user);
	nim_data.myInfo = user;
	updateMyInfoUI();
}
function onUpdateMyInfo(user) {
	console.log('我的名片更新了', user);
	//nim_data.myInfo = nim_instance.merge(nim_data.myInfo, user);
	nim_data.myInfo = Object.assign(nim_data.myInfo, user);
	updateMyInfoUI();
}
function updateMyInfoUI() {
	// 刷新界面
}
function onUsers(users) {
	console.log('收到用户名片列表', users);
	nim_data.users = nim_instance.mergeUsers(nim_data.users, users);
}
function onUpdateUser(user) {
	console.log('用户名片更新了', user);
	nim_data.users = nim_instance.mergeUsers(nim_data.users, user);
}
function onRobots(robots) {
	// 客户私有化方案不支持
	console.log('收到机器人列表', robots);
	nim_data.robots = robots;
}
function onTeams(teams) {
	console.log('群列表', teams);
	nim_data.teams = nim_instance.mergeTeams(nim_data.teams, teams);
	onInvalidTeams(teams.invalid);
	eve.f("nim_onTeams", teams)();
}
function onInvalidTeams(teams) {
	nim_data.teams = nim_instance.cutTeams(nim_data.teams, teams);
	nim_data.invalidTeams = nim_instance.mergeTeams(nim_data.invalidTeams,
			teams);
	refreshTeamsUI();
}
function onCreateTeam(team) {
	console.log('你创建了一个群', team);
	nim_data.teams = nim_instance.mergeTeams(nim_data.teams, team);
	refreshTeamsUI();
	onTeamMembers({
		teamId : team.teamId,
		members : owner
	});
}
function refreshTeamsUI() {
	// 刷新界面
}
function onTeamMembers(teamId, members) {
	console.log('群id', teamId, '群成员', members);
	var teamId = obj.teamId;
	var members = obj.members;
	nim_data.teamMembers = nim_data.teamMembers || {};
	nim_data.teamMembers[teamId] = nim_instance.mergeTeamMembers(
			nim_data.teamMembers[teamId], members);
	nim_data.teamMembers[teamId] = nim_instance.cutTeamMembers(
			nim_data.teamMembers[teamId], members.invalid);
	refreshTeamMembersUI();
}
// function onSyncTeamMembersDone() {
// console.log('同步群列表完成');
// }
function onUpdateTeam(team) {
	console.log('群状态更新', team)
}
function onUpdateTeamMember(teamMember) {
	console.log('群成员信息更新了', teamMember);
	onTeamMembers({
		teamId : teamMember.teamId,
		members : teamMember
	});
}
function refreshTeamMembersUI() {
	// 刷新界面
}
function onTeamMsgReceipt(teamMsgReceipts) {
	console.log('群消息已读通知', teamMsgReceipts)
}

function onSessions(sessions) {
	console.log('收到会话列表', sessions);
	nim_data.sessions = nim_instance.mergeSessions(nim_data.sessions, sessions);
	updateSessionsUI();
}
function onUpdateSession(session) {
	console.log('会话更新了', session);
	nim_data.sessions = nim_instance.mergeSessions(nim_data.sessions, session);
	updateSessionsUI();
}
function updateSessionsUI() {
	// 刷新界面
}

function onRoamingMsgs(obj) {
	console.log('漫游消息', obj);
	pushMsg(obj.msgs);
}
function onOfflineMsgs(obj) {
	console.log('离线消息', obj);
	pushMsg(obj.msgs);
}
function onMsg(msg) {
	console.log('收到消息', msg.scene, msg.type, msg);
	pushMsg(msg);
}
function onBroadcastMsg(msg) {
	console.log('收到广播消息', msg);
	eve.f("nim_onBroadcastMsg", msg)();
}
function onBroadcastMsgs(msgs) {
	console.log('收到广播消息列表', msgs);
}
function pushMsg(msgs) {
	if (!Array.isArray(msgs)) {
		msgs = [ msgs ];
	}
	var sessionId = msgs[0].sessionId;
	nim_data.msgs = nim_data.msgs || {};
	nim_data.msgs[sessionId] = nim_instance.mergeMsgs(nim_data.msgs[sessionId],
			msgs);
	eve.f("nim_onMsg", msgs)();
}

function onOfflineSysMsgs(sysMsgs) {
	console.log('收到离线系统通知', sysMsgs);
	pushSysMsgs(sysMsgs);
}
function onSysMsg(sysMsg) {
	console.log('收到系统通知', sysMsg)
	if(sysMsg.type === 'passFriendApply') {
		alert(sysMsg.ps);
		onAddFriend(sysMsg.friend);
		//nim_instance.markSysMsgRead({sysMsgs: sysMsg, done: function() {}});
	} else if(sysMsg.type === 'rejectFriendApply') {
		alert(sysMsg.ps);
		//nim_instance.markSysMsgRead({sysMsgs: sysMsg, done: function() {}});
	}
	pushSysMsgs(sysMsg);
}
function onUpdateSysMsg(sysMsg) {
	pushSysMsgs(sysMsg);
}
function pushSysMsgs(sysMsgs) {
	console.log("系统通知更新", sysMsgs);
	nim_data.sysMsgs = nim_instance.mergeSysMsgs(nim_data.sysMsgs, sysMsgs);
	refreshSysMsgsUI();
}
function onSysMsgUnread(obj) {
	console.log('收到系统通知未读数', obj);
	nim_data.sysMsgUnread = obj;
	refreshSysMsgsUI();
}
function onUpdateSysMsgUnread(obj) {
	console.log('系统通知未读数更新了', obj);
	nim_data.sysMsgUnread = obj;
	refreshSysMsgsUI();
}
function refreshSysMsgsUI() {
	// 刷新界面
	eve.f("nim_onSysMsgs")();
}
function onOfflineCustomSysMsgs(sysMsgs) {
	console.log('收到离线自定义系统通知', sysMsgs);
	pushSysMsgs(sysMsgs);
}
function onCustomSysMsg(sysMsg) {
	console.log('收到自定义系统通知', sysMsg);
	pushSysMsgs(sysMsg);
}

function onSyncDone() {
	console.log('同步完成');
	eve.f("nim_onSyncDone")();
}

// nim 方法
function nim_inAccounts(account, accounts) {
	for (var i = 0; i < accounts.length; i++) {
		if (account.account == accounts[i].account) {
			return true;
		}
	}

	return false;
}
function nim_toAccounts(accountObjs, notinObjs) {
	if (accountObjs && accountObjs.length) {
		notinObjs = notinObjs || [];
		var accounts = [];
		for (var i = 0; i < accountObjs.length; i++) {
			if (!nim_inAccounts(accountObjs[i], notinObjs)) {
				accounts.push(accountObjs[i].account);
			}
		}
		return accounts;
	}
	return [];
}

function nim_getUsers(opts) {
	console.log("nim_getUsers: ", opts);
	nim_instance.getUsers(opts);
}

function nim_addFriend(uid) {
	console.log("nim_addFriends: ", uid, uid);
	if (uid == uid) {
		alert("请勿添加自己为好友");
		return;
	}

	nim_instance.addFriend({
		account : uid,
		ps : '',
		done : function(err, obj) {
			onAddFriend(obj);
			alert("添加好友成功");
		}
	});
}
function nim_applyFriend(uid, ps) {
	console.log("nim_applyFriend", uid, ps);
	nim_instance.applyFriend({
		account : uid,
		ps : ps || '加个好友',
		done : function(err, obj) {
			//alert("好友申请已发送");
		}
	});
}
function nim_passFriendApply(sysMsg) {
	nim_instance.passFriendApply({
		idServer : sysMsg.idServer,
		account : sysMsg.from,
		ps : readCookie('username') + '通过了您的好友申请',
		done : function(error, obj) {
			if (!error) {
			    onAddFriend(obj.friend);
			}
			//nim_instance.markSysMsgRead({sysMsgs: sysMsg, done: function() {}});
		}
	});
}
function nim_rejectFriendApply(sysMsg) {
	nim_instance.passFriendApply({
		idServer : sysMsg.idServer,
		account : sysMsg.from,
		ps : readCookie('username') + '拒绝了您的好友申请',
		done : function(error, obj) {
			//nim_instance.markSysMsgRead({sysMsgs: sysMsg, done: function() {}});
		}
	});
}
function nim_deleteFriend(uid) {
	console.log("移除好友", uid);
	nim_instance.deleteFriend({
		account : uid,
		done : function(err, obj) {
			onDeleteFriend(obj);
			alert("删除好友成功");
		}
	});
}
function nim_addToBlacklist(uid) {
	nim_instance.addToBlacklist({
		account : uid,
		done : function(err, obj) {
			addToBlacklist(obj);
		}
	});
}
function nim_removeFromBlacklist(uid) {
	nim_instance.removeFromBlacklist({
		account : uid,
		done : function(err, obj) {
			removeFromBlacklist(obj);
		}
	});
}
function nim_sendMsg(uid, text) {
	var msg = nim_instance.sendText({
		scene : 'p2p',
		to : uid,
		text : text,
		done : sendMsgDone
	});
	console.log('正在发送p2p text消息, id=' + msg.idClient);
	pushMsg(msg);

	function sendMsgDone(error, msg) {
		console.log('发送' + msg.scene + ' ' + msg.type + '消息'
				+ (!error ? '成功' : '失败') + ', id=' + msg.idClient, error, msg);
	}
}
function nim_sendTeamMsg(teamId, text) {
	var msg = nim_instance.sendText({
		scene : 'team',
		to : teamId,
		text : text,
		done : sendMsgDone
	});
	msg.time = new Date().getTime();
	console.log('正在发送team text消息, id=' + msg.idClient);
	pushMsg(msg);

	function sendMsgDone(error, msg) {
		console.log('发送' + msg.scene + ' ' + msg.type + '消息'
				+ (!error ? '成功' : '失败') + ', id=' + msg.idClient, error, msg);
	}
}
function nim_sendTeamTipMsg(to, text) {
	var msg = nim_instance.sendTipMsg({
		scene : 'team',
		to : to,
		tip : text,
		done : sendMsgDone
	});
	console.log('正在发送p2p提醒消息, id=' + msg.idClient);
	pushMsg(msg);

	function sendMsgDone(error, msg) {
		console.log('发送' + msg.scene + ' ' + msg.type + '消息'
				+ (!error ? '成功' : '失败') + ', id=' + msg.idClient, error, msg);
	}
}
function nim_createTeam(opts, callback) {
	nim.createTeam({
		type : 'normal',
		name : opts.name,
		avatar : 'avatar',
		accounts : opts.accounts,
		ps : '我建了一个普通群',
		joinMode : 'noVerify',
		beInviteMode : 'noVerify',
		inviteMode : 'all',
		done : function(error, obj) {
			console.log(error);
			console.log("创建NIM普通群", obj);
			if (callback) {
				callback(obj);
			}
		}
	});
}
function nim_getTeams(callback) {
	nim.getTeams({
		done : getTeamsDone
	});
	function getTeamsDone(error, teams) {
		console.log('获取群列表' + (!error ? '成功' : '失败'));
		if (!error) {
			onTeams(teams);
			if (callback) {
				callback(teams);
			}
		}
	}
}
function nim_addTeamMembers(opts) {
	nim.addTeamMembers({
		teamId : opts.teamId,
		accounts : opts.accounts,
		ps : '加入我们的群吧',
		done : addTeamMembersDone
	});
	function addTeamMembersDone(error, obj) {
		console.log(error);
		console.log(obj);
		console.log('入群邀请发送' + (!error ? '成功' : '失败'));
	}
}
function nim_getTeamMembers(teamId, callback) {
	nim_instance.getTeamMembers({
		teamId : teamId,
		done : getTeamMembersDone
	});
	function getTeamMembersDone(error, obj) {
		if (callback) {
			callback(obj);
		}
	}
}
function nim_dismissTeam(teamId, callback) {
	nim.dismissTeam({
		teamId : teamId,
		done : dismissTeamDone
	});
	function dismissTeamDone(error, obj) {
		console.log(error);
		console.log(obj);
		console.log('解散群' + (!error ? '成功' : '失败'));
		if (callback) {
			callback(obj);
		}
	}
}
function nim_getLocalSysMsgs(idServer, type, callback) {
	nim_instance.getLocalSysMsgs({
	    lastIdServer: idServer,
	    limit: 100,
	    type: type,
	    done: callback
	});
}
function nim_mergeSysMsgs(msgs1, msgs2) {
	return nim_instance.mergeSysMsgs(msgs1, msgs2);
}
function nim_cutFriendsByAccounts(friends, friendsToCut) {
	return nim_instance.cutFriendsByAccounts(friends, friendsToCut);
}
