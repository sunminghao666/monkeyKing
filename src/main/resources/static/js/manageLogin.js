/**
 * 初始化
 */
$(function() {
	$(".span2 img").attr("src", "img/loginLogo.png");
	document.title = "xxxx后台管理系统";
	$(".span3").html("xxxx管理有限公司");
	$("#manageName").focus();// 用户名输入框聚焦
	/* 按钮变色 */
	$(".btn_login").mouseover(function() {
		$(this).css("background-color", "#e35416");// ca0101
	});
	$(".btn_login").mouseout(function() {
		$(this).css("background-color", "#f5692c");
	});
});

/**
 * 校验是否可以提交
 */
function beforeSubmit() {
	var manageName = $("#manageName").val();
	var managePassword = $("#managePassword").val();
	if (manageName == null || manageName == "") {
		createPopup("您还没输入登录名呢！");
		return false;
	} else if (managePassword == null || managePassword == "") {
		createPopup("您还没输入密码呢！");
		return false;
	} else if (chenkAlphanum(managePassword) == false) {
		createPopup("密码必须是数字字母下划线，且不能以下划线开头！");
		return false;
	}
}
// /**
// * 提交登录信息
// */
// function submitLogin() {
// var manageName = document.getElementById("manageName").value;
// var managePassword = document.getElementById("managePassword").value;
// var logInfo = {// flag-登录入口标志 1-pc端管理员后台管理系统登录 2-pc端代理人前台系统登录
// // 3-app端代理人和普通用户登录
// "userName" : manageName,
// "agentPass" : managePassword,
// "flag" : 1
// };
// var sendData = {
// request : logInfo
// };
// sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
// var path = base.domain + "/PC/manageIndex.jsp";
// $.ajax({
// async : false,
// type : "POST",
// url : base.domain + "/login.do",
// data : sendData,
// dataType : 'json',
// success : function(data) {
// var resp = JSON.parse(data);
// if (resp.status.statusCode == "000000") {
// window.open(path, "_self");
// } else {
// createPopup("用户名或密码错误");
// }
// }
// });
// }
