/**
 * 默认查询所有数据
 */
var comparys = null;
var orgs = null;
$(function() {
	// loadAllComparys();// 查询所有保险公司
	// loadAllOrgs();//查询所有机构
	queryUserByPage();// 初始化页面
	// 机构勾选框控制，"全选"按钮操作
	$("#org1All").click(function() {
		if ($(this).is(":checked")) {
			$("input[name='org1']").prop('checked', true);
		} else {
			$("input[name='org1']").prop('checked', false);
		}
	});

});

/** *********************************** 查询所有保险公司 **************************** */
function loadAllComparys() {
	$
			.ajax({
				async : false,
				type : "post",
				url : base.domain + "user/getCompanyCode",
				data : null,
				dataType : 'json',
				success : function(data) {
					if (data != "") {
						var paramList = eval("(" + data + ")");
						if (paramList.status.statusCode == "000000") {
							comparys = paramList.result;
							if (comparys != null) {
								for (var i = 0; i < comparys.length; i++) {
									var content = document
											.createElement("label");
									var str = "<input name='compary1' class='compary1' type='checkbox' value='"
											+ comparys[i].dicCode
											+ "' />"
											+ comparys[i].dicValue;
									$(content).html(str);
									$("#compary1").append(content);
									$("#compary1").append("<br>");
								}
							}
						} else {
							window.parent.createPopup("获取保险公司信息失败！");
						}
					} else {
						window.parent.createPopup("获取保险公司信息失败！");
					}
				},
				error : function() {
					alert("请求服务超时");
				}
			});
}

/** *********************************** 查询所有机构信息 **************************** */
function loadAllOrgs() {
	var sendData = {
		"dic_type" : "register_area"
	};
	$
			.ajax({
				async : false,
				type : "POST",
				url : base.domain + "/init/loadDicBytype",
				data : sendData,
				dataType : 'json',
				success : function(data) {
					if (data != "") {
						orgs = data;
						if (orgs != null) {
							var content = document.createElement("label");
							var str = "<input id='org1All' name='org1All' class='org1 org1All' type='checkbox' value='9999' />"
									+ "全选";
							$(content).html(str);
							$("#org1").append(content);
							$("#org1").append("<br>");
							for (var i = 0; i < orgs.length; i++) {
								var content = document.createElement("label");
								var str = "";
								str = "<input name='org1' class='org1 org1Sub' type='checkbox' value='"
										+ orgs[i].dicCode
										+ "' />"
										+ orgs[i].dicValue;
								$(content).html(str);
								$("#org1").append(content);
								$("#org1").append("<br>");
							}
						}
					} else {
						// window.parent.createPopup("获取机构信息失败！");
					}
				},
				error : function() {
					alert("请求服务超时");
				}
			});
}

/** *********************************** 查询功能 begin **************************** */
// 翻页时重新加载数据
function reloadData(pageNum) {
	$("#pagenum").val(pageNum);
	queryUserByPag();
}

function queryUserByPag() {

	var userName = $("#userName").val();// 用户名称
	var userCode = $("#userCode").val();// 用户编码
	var pageIndex = $("#pagenum").val();// 当前页
	var itemnum = $("#itemnum").val();
	var obj = {
		"userName" : userName,
		"userCode" : userCode,
		"pageIndex" : pageIndex,
		"pageSize" : itemnum
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	$.ajax({
		async : false,
		type : "post",
		url : base.domain + "user/queryUserByPage",
		data : sendData,
		dataType : 'json',
		success : function(data) {
			if (data != "") {
				var paramList = eval("(" + data + ")");
				if (paramList.status.statusCode == "000000") {
					userInfoList = paramList.page.entities;// 用户列表信息
					if (userInfoList.length < 1) {
						$("#noCus").show("fast");
						$(".useManage_article_button").css("display", "none");
						// $("#page_control").hide();
						$("#content").html("");
						return;
					}
					$("#count").val(paramList.page.entityCount);
					$("#itemnum").val(paramList.page.pageSize);

					$.userInfo();

				} else {
					window.parent.createPopup("获取用户信息失败！");
				}
			} else {
				window.parent.createPopup("获取用户信息异常！");
			}
		},
		error : function() {
			alert("请求服务超时");
		}
	});
}

// 翻页时重新请求数据
function queryUserByPage() {

	var userName = $("#userName").val();// 用户名称
	var userCode = $("#userCode").val();// 用户编码
	var pageIndex = $("#pagenum").val();// 当前页
	var itemnum = $("#itemnum").val();
	var obj = {
		"userName" : userName,
		"userCode" : userCode,
		"pageIndex" : pageIndex,
		"pageSize" : itemnum
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	$.ajax({
		async : false,
		type : "post",
		url : base.domain + "user/queryUserByPage",
		data : sendData,
		dataType : 'json',
		success : function(data) {
			if (data != "") {
				var paramList = eval("(" + data + ")");
				if (paramList.status.statusCode == "000000") {
					userInfoList = paramList.page.entities;// 用户列表信息
					if (userInfoList.length < 1) {
						$("#noCus").show("fast");
						$(".useManage_article_button").css("display", "none");
						$("#page_control").hide();
						$("#content").html("");
						return;
					}
					$("#count").val(paramList.page.entityCount);
					$("#itemnum").val(paramList.page.pageSize);
					$.fenyeInit();// 分页信息
					$.userInfo();

				} else {
					window.parent.createPopup("获取用户信息失败！");
				}
			} else {
				window.parent.createPopup("获取用户信息异常！");
			}
		},
		error : function() {
			alert("请求服务超时");
		}
	});
}

// 往页面渲染数据
$.userInfo = function() {
	var param = userInfoList;
	if (param != null && param.length > 0) {
		$("#noCus").hide();
		$(".useManage_article_button").css("display", "block");
		$("#page_control").show("fast");
		var str = "";
		for (var i = 0; i < param.length; i++) {
			var comp = "";
			if (comparys != null) {
				for (var j = 0; j < comparys.length; j++) {
					if (param[i].comcode.indexOf(comparys[j].dicCode) >= 0) {
						if (comp == "") {
							comp = comparys[j].dicValue;
						} else {
							comp = comp + "," + comparys[j].dicValue;
						}
					}
				}
			}
			str += "<tr>";
			str += "<td><input id='checkRes' type='checkbox' name='checkResName'/><input type='hidden' value='"
					+ param[i].id + "'/></td>";
			str += "<td><span>" + param[i].id + "</span></td>";
			str += "<td><div class='authorization' onclick='showAuthorizationDiglog(this)'><input type='hidden' value='"
					+ param[i].id + "'/>授权</div></td>";
			str += "<td>" + param[i].username + "</td>";
			str += "<td>" + param[i].usercode + "</td>";
			var createdate = "";
			if (param[i].createdate != "" && param[i].createdate != null) {
				createdate = timeFormatDate(param[i].createdate.time,
						'yyyy-MM-dd HH:mm:ss');
			}
			str += "<td>" + createdate + "</td>";
			str += "<td>" + param[i].userphone + "</td>";
			str += "<td>" + param[i].validstatus + "</td>";
			str += "<td>" + param[i].department + "</td>";
			str += "<td>" + param[i].position + "</td>";
			str += "</tr>";
		}

		$("#content").html(str);
	} else {
		$("#content").html("");
		$("#noCus").show("fast");
		$(".useManage_article_button").css("display", "none");
		$("#page_control").hide();
	}

};

// 查询按钮事件
$("#queryUser").click(function() {
	// $("#pagenum").val();
	queryUserByPage();
});
/**
 * ********************************************** 查询功能 end
 * **************************************************
 */

/**
 * ************************************************* 添加功能 begin
 * *********************************************
 */

/* 获取遮窗高度 */

/* 添加按钮弹出框 */
$("#add_userInfo").click(function() {
	$("#fullbg").css("height", $(document).height());
	$("input[name='compary1']").attr("checked", false);
	$("input[name='org1All']").attr("checked", false);
	$("input[name='org1']").attr("checked", false);
	$("#fullbg").show();
	$("#dialog").show();
	$("#add_submit").show();
});

/* 按钮变色 */
$(".useManage_article_button").mouseover(function() {
	$(this).css('background-color', '#1B6BC8');
});
$(".useManage_article_button").mouseout(function() {
	$(this).css('background-color', '#076dce');
});
$(".delete_dialog_div").mouseover(function() {
	$(this).css('background-color', '#1B6BC8');
});
$(".delete_dialog_div").mouseout(function() {
	$(this).css('background-color', '#076dce');
});
$(".authorization").mouseover(function() {
	$(this).css('background-color', '#B2DFEE');
});
$(".authorization").mouseout(function() {
	$(this).css('background-color', '#d8eaf4');
});
/* 添加页面，保存事件 */
$("#add_submit").click(function() {
	// 页面数据校验
	var flag = checkAddUser();
	if (flag) {
		$("#dialog").hide();
		$("#fullbg").hide();
		// 添加用户
		addUser();
		cleanHistory();
	}
});

/**
 * 新增页面数据校验
 */
function checkAddUser() {
	// var comp = "";
	// $("input[name='compary1']:checked").each(function() {
	// // alert(this.value);
	// if (comp == "") {
	// comp = this.value;
	// } else {
	// comp = comp + "," + this.value;
	// }
	// });
	// var orgp = "";
	// $("input[name='org1']:checked").each(function() {
	// // alert(this.value);
	// if (orgp == "") {
	// orgp = this.value;
	// } else {
	// orgp = orgp + "," + this.value;
	// }
	// });
	var userCode = $("#userCodeDig").val();// 用户账号
	var userName = $("#userNameDig").val();// 用户名称
	var password = $("#passwordDig").val();// 密码
	// var comcode = comp;// 用户管理公司实际值
	// var orgcode = orgp;// 用户管理机构实际值
	var userPhone = $("#userPhoneDig").val();// 手机号码
	var userEmail = $("#userEmailDig").val();// 邮件

	if (userCode != null && userCode != "") {
		if (tit.regExp.isCardNum(userCode) == false) {
			window.parent.createPopup("用户账号只能填写数字和字母");
			return false;
		}
	}

	if (userCode == null || userCode == "") {
		window.parent.createPopup("账号不能为空!");
		return false;
	}
	if ($.trim(userName) == null || $.trim(userName) == "") {
		window.parent.createPopup("用户名不能为空!");
		return false;
	}
	if ($.trim(password) == null || $.trim(password) == "") {
		window.parent.createPopup("密码不能为空!");
		return false;
	}
	// if (comcode == null || comcode == "") {
	// window.parent.createPopup("管理保险公司不能为空!");
	// return false;
	// }
	// if (orgcode == null || orgcode == "") {
	// window.parent.createPopup("管理机构不能为空!");
	// return false;
	// }
	var phoneReg = /^1[3|4|5|8]\d{9}/;
	if (userPhone != null && userPhone != '') {
		if (!phoneReg.test(userPhone)) {
			window.parent.createPopup("手机号码格式不对!");
			return false;
		}
	}
	var search_str = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	if (userEmail != null && userEmail != '') {
		if (!search_str.test(userEmail)) {
			window.parent.createPopup("邮箱格式不正确!");
			return false;
		}
	}

	return true;

}

// 添加用户
function addUser() {
	var userCode = $("#userCodeDig").val();// 用户账号
	var userName = $("#userNameDig").val();// 用户名称
	var password = $("#passwordDig").val();// 密码
	// var comp = "";
	// $("input[name='compary1']:checked").each(function() {
	// if (comp == "") {
	// comp = this.value;
	// } else {
	// comp = comp + "," + this.value;
	// }
	// });
	// var orgp = "";
	// $("input[name='org1']:checked").each(function() {
	// if (orgp == "") {
	// orgp = this.value;
	// } else {
	// orgp = orgp + "," + this.value;
	// }
	// });
	var userPhone = $("#userPhoneDig").val();// 手机号码
	var userEmail = $("#userEmailDig").val();// 邮件
	var validStatus = $("#validStatusDig").val();// 用户状态实际值
	var department = $("#userDepartment").val();// 部门
	var position = $("#userPosition").val();// 职位
	var validStatusName = $("#validStatusDig option:checked").text();// 用户状态显示值
	var obj = {
		"department" : department,
		"position" : position,
		"userCode" : userCode,
		"userName" : userName,
		"password" : password,
		"comcode" : "default",
		"manageCompany" : "default",
		"userPhone" : userPhone,
		"userEmail" : userEmail,
		"validStatus" : validStatus
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	$
			.ajax({
				type : 'post',
				data : sendData,
				url : base.domain + "user/addUser",
				dataType : 'json',
				success : function(data) {
					var param = eval("(" + data + ")");
					if (param.status.statusCode == "000000"
							&& param.user != null) {

						// createPopup("添加用户成功");

						window.location.reload(); // 刷新页面
						var orginCount = $("#count").val();
						var comp = "";
						if (comparys != null) {
							for (var j = 0; j < comparys.length; j++) {
								if (param.user.comcode
										.indexOf(comparys[j].dicCode) >= 0) {
									if (comp == "") {
										comp = comparys[j].dicValue;
									} else {
										comp = comp + ","
												+ comparys[j].dicValue;
									}
								}
							}
						}
						var str = "";
						str += "<tr>";
						str += "<td class='um_th_1'><input id='checkRes' type='checkbox' name='checkResName'/><input type='hidden' value='"
								+ param.user.id + "'/></td>";
						str += "<td class='um_th_2' id='userId'><span>"
								+ param.user.id + "</span></td>";
						str += "<td class='um_th_2'><div class='authorization' onclick='showAuthorizationDiglog(this)'><input type='hidden' value='"
								+ param.user.id + "'/>授权</div></td>";
						str += "<td class='um_th_3'>" + param.user.username
								+ "</td>";
						str += "<td class='um_th_4'>" + param.user.usercode
								+ "</td>";
						str += "<td class='um_th_6'>"
								+ timeFormatDate(param.user.createdate,
										'yyyy-MM-dd HH:mm:ss') + "</td>";
						str += "<td class='um_th_7'>" + param.user.userphone
								+ "</td>";
						str += "<td class='um_th_8'>" + validStatusName
								+ "</td>";

						str += "</tr>";

						$("#content").prepend(str);
						if ($("#content").find("tr").length > 10) {
							$("#content").find("tr").last().remove();
						}
						$("#count").val(Number(orginCount) + 1);
						$.fenyeInit();// 分页信息
					} else {
						window.parent.createPopup("用户名称或编码已经存在！新增用户失败");
					}
				},
				error : function() {
					alert("请求服务超时");
				}
			});
}

function cleanHistory() {
	$("#userCodeDig").val("");// 用户账号
	$("#userNameDig").val("");// 用户名称
	$("#passwordDig").val("");// 密码
	$("#comcodeDig").val("");// 用户管理公司实际值
	$("#userPhoneDig").val("");// 手机号码
	$("#userEmailDig").val("");// 邮件
	// $("#validStatusDig").val("");// 用户状态实际值
	$("#validStatusDig option[value='1']").attr("selected", true);// 用户状态显示值
}

/* 隐藏添加页面弹框 */
$("#cancle_submit").click(function() {
	$("#dialog").hide();
	$("#fullbg").hide();
	cleanHistory();
});

/**
 * ************************************************* 添加功能 end
 * *********************************************
 */

/**
 * ************************************************* 修改功能 begin
 * *********************************************
 */
/* 修改用户页面弹出框 */
$("#alter_userInfo").click(function() {
	var checkedList = $("input[name='checkResName']:checked").closest("tr");
	// check选择的行数
	if (checkedList.length != 1) {
		window.parent.createPopup("请选择一条用户信息进行修改");
		return false;
	}
	// 用户id
	var userId = $(checkedList[0]).children("td").eq(1).text();
	// 修改页面数据渲染
	findUser(userId);
	$("#fullbg").css("height", $(document).height());
	$("#fullbg").show();
	$("#dialog2").show();

});

/* 修改页面，保存事件 */
$("#add_submit2").click(function() {
	// 数据校验
	var flag = checkUpdateUser();
	if (flag) {
		$("#dialog2").hide();
		$("#fullbg").hide();
		// 更新用户
		updateUser();
	}
});

/**
 * 修改页面数据校验
 */
function checkUpdateUser() {
	// var comp = "";
	// $("input[name='compary2']:checked").each(function() {
	// if (comp == "") {
	// comp = this.value;
	// } else {
	// comp = comp + "," + this.value;
	// }
	// });
	// var orgp = "";
	// $("input[name='org2']:checked").each(function() {
	// if (orgp == "") {
	// orgp = this.value;
	// } else {
	// orgp = orgp + "," + this.value;
	// }
	// });
	var userCode = $("#userCodeDig2").val();// 用户账号
	var userName = $("#userNameDig2").val();// 用户名称
	var password = $("#passwordDig2").val();// 密码
	// var comcode = comp;// 用户管理公司实际值
	// var orgcode = orgp;// 用户管理机构实际值
	var userPhone = $("#userPhoneDig2").val();// 手机号码
	var userEmail = $("#userEmailDig2").val();// 邮件

	if (userCode != null && userCode != "") {
		if (tit.regExp.isCardNum(userCode) == false) {
			window.parent.createPopup("用户账号只能填写数字和字母");
			return false;
		}
	}

	if (userCode == null || userCode == "") {
		window.parent.createPopup("账号不能为空!");
		return false;
	}
	if (userName == null || userName == "") {
		window.parent.createPopup("用户名不能为空!");
		return false;
	}
	if (password == null || password == "") {
		window.parent.createPopup("密码不能为空!");
		return false;
	}
	// if (comcode == null || comcode == "") {
	// window.parent.createPopup("管理保险公司不能为空!");
	// return false;
	// }
	// if (orgcode == null || orgcode == "") {
	// window.parent.createPopup("管理机构不能为空!");
	// return false;
	// }
	var phoneReg = /^1[3|4|5|8]\d{9}/;
	if (userPhone != null && userPhone != '') {
		if (!phoneReg.test(userPhone)) {
			window.parent.createPopup("手机号码格式不对!");
			return false;
		}
	}
	var search_str = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	if (userEmail != null && userEmail != '') {
		if (!search_str.test(userEmail)) {
			window.parent.createPopup("邮箱格式不正确!");
			return false;
		}
	}

	return true;

}

/* 隐藏修改页面弹框 */
$("#cancle_submit2").click(function() {
	$("#dialog2").hide();
	$("#fullbg").hide();
});

/**
 * 查询某个用户
 */
function findUser(userId) {

	var obj = {
		"userId" : userId
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	// 提交数据到后台 ajax方式提交到后台
	$
			.ajax({
				type : 'post',
				data : sendData,
				url : base.domain + "user/findUserById",
				dataType : 'json',
				success : function(data) {
					// 页面渲染处理
					if (data != "") {
						var param = eval("(" + data + ")");
						if (param.status.statusCode == "000000"
								&& param.user != null) {
							userInfo = param.user;// 用户信息
							$("#compary2").empty();
							$("#org2").empty();
							var disableOrg2Flag = false;// 是否禁用勾选子机构
							if (comparys != null) {

								for (var i = 0; i < comparys.length; i++) {

									var content = document
											.createElement("label");
									if (userInfo.comcode
											.indexOf(comparys[i].dicCode) >= 0) {
										var str = "<input name='compary2' class='compary2' type='checkbox' value='"
												+ comparys[i].dicCode
												+ "' checked='true'/>"
												+ comparys[i].dicValue;
										$(content).html(str);
										$("#compary2").append(content);
										$("#compary2").append("<br>");
									} else {
										var str = "<input name='compary2' class='compary2' type='checkbox' value='"
												+ comparys[i].dicCode
												+ "' />"
												+ comparys[i].dicValue;
										$(content).html(str);
										$("#compary2").append(content);
										$("#compary2").append("<br>");
									}

								}
							}
							if (orgs != null) {
								var content = document.createElement("label");
								var str = "<input id='org2All' name='org2All' class='org2 org2All' type='checkbox' value='9999'/>"
										+ "全选";
								$(content).html(str);
								$("#org2").append(content);
								$("#org2").append("<br>");
								for (var i = 0; i < orgs.length; i++) {

									var content = document
											.createElement("label");
									if (userInfo.manageCompany
											.indexOf(orgs[i].dicCode) >= 0) {
										var str = "";
										str = "<input name='org2' class='org2 org2Sub' type='checkbox' value='"
												+ orgs[i].dicCode
												+ "' checked='true'/>"
												+ orgs[i].dicValue;
										$(content).html(str);
										$("#org2").append(content);
										$("#org2").append("<br>");
									} else {
										var str = "";
										str = "<input name='org2' class='org2 org2Sub' type='checkbox' value='"
												+ orgs[i].dicCode
												+ "' />"
												+ orgs[i].dicValue;
										$(content).html(str);
										$("#org2").append(content);
										$("#org2").append("<br>");
									}

								}
							}

							$("#userId").val(userInfo.id);
							$("#userCodeDig2").val(userInfo.usercode);
							$("#userNameDig2").val(userInfo.username);
							$("#passwordDig2").val(userInfo.password);
							// $("#comcodeDig2").val(userInfo.comcode);
							$("#userDepartment2").val(userInfo.department);
							$("#userPosition2").val(userInfo.position);
							$("#userPhoneDig2").val(userInfo.userphone);
							$("#userEmailDig2").val(userInfo.useremail);
							$("#validStatusDig2").val(userInfo.validstatus);
							if (disableOrg2Flag) {
								$(".org2Sub").removeAttr("checked");
								$(".org2Sub").attr("disabled", "disabled");
							}
							bindOrg2Click();// 修改页面机构选择事件绑定
						} else {
							window.parent.createPopup("获取用户信息失败！");
						}
					} else {
						window.parent.createPopup("获取用户信息异常！");
					}

				},
				error : function() {
					alert("请求服务超时");
				}
			});

	return true;
}

// 修改页面机构选择事件绑定
function bindOrg2Click() {
	$("#org2All").click(function() {
		if ($(this).is(":checked")) {
			$("input[name='org2']").prop('checked', true);
		} else {
			$("input[name='org2']").prop('checked', false);
		}

	});
}

/**
 * 修改用户
 */
function updateUser() {
	// var comp = "";
	// $("input[name='compary2']:checked").each(function() {
	// if (comp == "") {
	// comp = this.value;
	// } else {
	// comp = comp + "," + this.value;
	// }
	// });
	// var orgp = "";
	// $("input[name='org2']:checked").each(function() {
	// if (orgp == "") {
	// orgp = this.value;
	// } else {
	// orgp = orgp + "," + this.value;
	// }
	// });
	var userId = $("#userId").val();// 用户id
	var userCode = $("#userCodeDig2").val();// 用户账号
	var userName = $("#userNameDig2").val();// 用户名称
	var password = $("#passwordDig2").val();// 密码
	// var comcode = comp;// 管理公司
	// var orgcode = orgp;// 管理机构
	var userPhone = $("#userPhoneDig2").val();// 电话
	var userEmail = $("#userEmailDig2").val();// 邮件
	var department = $("#userDepartment2").val();// 部门
	var position = $("#userPosition2").val();// 职位
	var validStatus = $("#validStatusDig2").val();// 有效实际值
	var validStatusName = $("#validStatusDig2 option:checked").text();// 有效显示值
	var obj = {
		"department" : department,
		"position" : position,
		"userId" : userId,
		"userCode" : userCode,
		"userName" : userName,
		"password" : password,
		"comcode" : "default",
		"manageCompany" : "default",
		"userPhone" : userPhone,
		"userEmail" : userEmail,
		"validStatus" : validStatus
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	$.ajax({
		type : 'post',
		data : sendData,
		url : base.domain + "user/updateUser",
		dataType : 'json',
		success : function(data) {
			var param = eval("(" + data + ")");
			if (param.status.statusCode == "000000" && param.result == 1) {
				window.parent.createPopup("修改用户成功");
				// 页面端更新
				var checkedList = $(":checkbox:checked").closest("tr");
				var comp = "";
				if (comparys != null) {
					for (var j = 0; j < comparys.length; j++) {
						// if (comcode.indexOf(comparys[j].dicCode) >= 0) {
						// if (comp == "") {
						// comp = comparys[j].dicValue;
						// } else {
						// comp = comp + "," + comparys[j].dicValue;
						// }
						// }
					}
				}
				var $td = checkedList.find('td');
				$td.eq(3).html(userName);
				$td.eq(4).html(userCode);
				// $td.eq(5).html(comp);
				$td.eq(6).html(userPhone);
				$td.eq(7).html(validStatusName);

			} else {
				window.parent.createPopup("用户名称或编码已经存在;修改失败");
			}

		},
		error : function() {
			alert("请求服务超时");
		}
	});
}
/**
 * ************************************************* 修改功能 end
 * *********************************************
 */

/**
 * ************************************************* 删除功能 begin
 * *********************************************
 */

/* 点击删除显示弹窗 */
$("#delete_userInfo").click(function() {
	var $tbody = $("#content");
	var $tr = $tbody.children("tr");
	var i = 0;
	$tr.each(function() {
		var $td = $(this).children('td');
		var $input = $td.children("input");
		if ($input[0].checked == true) {
			i++;
		}
	});
	// check选择的行数
	if (i < 1) {
		window.parent.createPopup("请选择一条用户信息进行删除");
		return false;
	}
	$("#fullbg").css("height", $(document).height());
	$("#fullbg").show();
	$(".dialog_delete").show();
});

/* 删除框 按钮ok点击事件 */
$("#ok").click(function() {

	deleteUser();
	$(".dialog_delete").hide();
	$("#fullbg").hide();

});

/* 删除框 按钮no点击事件 */
$("#no").click(function() {
	$(".dialog_delete").hide();
	$("#fullbg").hide();
});

/*
 * 删除用户
 */
function deleteUser() {

	var checkedList = $(":checkbox:checked").closest("tr");
	var userIds = "";

	var $tbody = $("#content");
	var $tr = $tbody.children("tr");
	$tr.each(function() {
		var $td = $(this).children('td');
		var $input = $td.children("input");
		if ($input[0].checked == true) {
			userIds += $input[1].value + ",";
		}
	});

	var obj = {
		"userIds" : userIds
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	// 提交数据到后台 ajax方式提交到后台
	$.ajax({
		type : 'post',
		data : sendData,
		url : base.domain + "user/deleteUser",
		dataType : 'json',
		success : function(data) {
			var param = eval("(" + data + ")");
			if (param.status.statusCode == "000000") {
				window.parent.createPopup("删除用户信息成功");
				// 页面更新有效状态
				$("#pagenum").val("");
				queryUserByPage();
				// var i = 0;
				// for(;i < checkedList.length;i++){
				// $(checkedList[i]).remove();//移除选中的行
				// }
				// var orginCount = $("#count").val();
				// $("#count").val(Number(orginCount)-i);
				// $.fenyeInit();//分页信息
				// var length = $('#content').find('tr').length;
				// if(length<1){
				// $("#content").html("");
				// $(".useManage_article_button").css("display","none");
				// $("#page_control").hide();
				// }
			}

		},
		error : function() {
			alert("请求服务超时");
		}
	});
}

/**
 * ************************************************* 删除功能 end
 * *********************************************
 */

/**
 * ************************************************* 授权功能 begin
 * *********************************************
 */

/* 弹出 授权页面 */
function showAuthorizationDiglog(obj) {
	$("#fullbg").css("height", $(document).height());
	$("#fullbg").show();
	$(".dialog_authorization").show();
	var userId = obj.firstChild.value;// 当前用户id
	// 用户授权页面数据渲染
	queryRoleByPage(userId);
	// 默认选中当前用户下的角色
	findCurrentUserRole(userId);
}

/*
 * 角色查询并授权角色数据
 */
function queryRoleByPage(userId) {
	var obj = {
		"roleName" : "",
		"roleCode" : "",
		"pageIndex" : 1,
		"pageSize" : 100
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	$.ajax({
		async : false,
		type : "post",
		url : base.domain + "/role/queryRoleByPage",
		data : sendData,
		dataType : 'json',
		success : function(data) {
			if (data != "") {
				var paramList = eval("(" + data + ")");
				if (paramList.status.statusCode == "000000") {
					roleInfoList = paramList.page.entities;// 角色列表信息
					// 用户授权页面数据渲染
					roleInfo(userId);
				} else {
					window.parent.createPopup("获取角色信息失败！");
				}
			} else {
				window.parent.createPopup("获取角色信息异常！");
			}
		},
		error : function() {
			alert("请求服务超时");
		}
	});
}

/**
 * 查询当前用户下的角色 并 选中已经存在的角色
 * 
 * @param userId
 */
function findCurrentUserRole(userId) {
	var obj = {
		"userId" : userId
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	$.ajax({
		async : false,
		type : "post",
		url : base.domain + "userRole/findUserRolesByUserCode",
		data : sendData,
		dataType : 'json',
		success : function(data) {
			if (data != "") {
				var paramList = eval("(" + data + ")");
				if (paramList.status.statusCode == "000000") {
					// 选中已有的角色
					var userRoleList = paramList.userRoleList;
					// 对当前用户下以后的角色做遍历判断处理
					for (var i = 0; i < userRoleList.length; i++) {

						var $tbody = $("#roleContent");
						var $tr = $tbody.children("tr");
						// 遍历table中的数据
						$tr.each(function() {
							var $td = $(this).children('td');
							var $input = $td.children("input");
							if (userRoleList[i].roleCode == $input[1].value) {
								// 选中checkBox
								$input[0].checked = true;
							}
						});
					}

				} else {
					window.parent.createPopup("查询用户角色关系失败！");
				}
			} else {
				window.parent.createPopup("查询用户角色关系失败！");
			}
		},
		error : function() {
			alert("请求服务超时");
		}
	});
}

// 往用户授权页面渲染数据
function roleInfo(userId) {
	var param = roleInfoList;
	if (param != null && param.length > 0) {
		var str = "";
		for (var i = 0; i < param.length; i++) {
			str += "<tr>";
			str += "<td class='um_th_1'><input type='checkbox' id='checkRole' name='checkRoleName' /><input type='hidden' id='roleId' value='"
					+ param[i].id + "'/></td>";
			str += "<td class='um_th_3'>" + param[i].roleName + "</td>";
			str += "</tr>";
		}
		$("#roleContent").html(str);
		$("#userRoleId").val(userId);
	} else {
		$("#roleContent").html("");
	}

};

/* 授权页面,保存按钮 */
$("#author_submit").click(function() {
	// 保存数据到用户角色关系表中
	var userId = $("#userRoleId").val();// 当前选中的用户id
	var checkedId = "";// 所有选中的角色id
	var unCheckedId = "";// 所有未选中的角色id

	var $tbody = $("#roleContent");
	var $tr = $tbody.children("tr");
	$tr.each(function() {
		var $td = $(this).children('td');
		var $input = $td.children("input");
		if ($input[0].checked == true) {
			checkedId += $input[1].value + ",";
		} else {
			unCheckedId += $input[1].value + ",";
		}
	});

	addUserRole(userId, checkedId, unCheckedId);
	$("#fullbg").hide();
	$(".dialog_authorization").hide();
	$("#roleContent").html("");

});

/* 授权关闭 */
$(".author_close").click(function() {
	$("#fullbg").hide();
	$(".dialog_authorization").hide();
	$("#roleContent").html("");
});
/**
 * 用户角色关系维护
 * 
 * @param userId
 * @param checked
 * @param unChecked
 */
function addUserRole(userId, checked, unChecked) {
	var obj = {
		"userId" : userId,
		"checked" : checked,
		"unChecked" : unChecked
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	$.ajax({
		async : false,
		type : "post",
		url : base.domain + "userRole/updateUserRoles",
		data : sendData,
		dataType : 'json',
		success : function(data) {
			if (data != "") {
				var paramList = eval("(" + data + ")");
				if (paramList.status.statusCode == "000000") {
					window.parent.createPopup("用户角色关系维护成功");
				} else {
					window.parent.createPopup("用户角色关系维护失败！");
				}
			} else {
				window.parent.createPopup("用户角色关系维护失败！");
			}
		},
		error : function() {
			alert("请求服务超时");
		}
	});
}

/**
 * ************************************************* 授权功能 end
 * *********************************************
 */

/*
 * 日期本地化函数
 * 
 * function dateConvert(date) { var result = new Date(date.time); return
 * result.toLocaleString(); }
 */

/*
 * 弹出框头上的关闭X号，关闭弹出框
 */
$(".cancel").click(function() {
	$(".dialog,#fullbg,#add_submit").hide();
});

/*
 * 全选/反选
 */
$("#checkResIndex").click(function() {
	if ($(this).is(":checked")) {
		$("input[name='checkResName']").prop('checked', true);
	} else {
		$("input[name='checkResName']").prop('checked', false);
	}

});