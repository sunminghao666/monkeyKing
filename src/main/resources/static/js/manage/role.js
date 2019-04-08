var setting = {
	view : {
		selectedMulti : false
	},
	check : {
		enable : true
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		beforeCheck : beforeCheck,
		onCheck : onCheck
	}
};
var code, log, className = "dark";
function beforeCheck(treeId, treeNode) {
	className = (className === "dark" ? "" : "dark");
	showLog("[ " + getTime() + " beforeCheck ]&nbsp;&nbsp;&nbsp;&nbsp;"
			+ treeNode.name);
	return (treeNode.doCheck !== false);
}
function onCheck(e, treeId, treeNode) {
	showLog("[ " + getTime() + " onCheck ]&nbsp;&nbsp;&nbsp;&nbsp;"
			+ treeNode.name);
}
function showLog(str) {
	if (!log)
		log = $("#log");
	log.append("<li class='" + className + "'>" + str + "</li>");
	if (log.children("li").length > 6) {
		log.get(0).removeChild(log.children("li")[0]);
	}
}
function getTime() {
	var now = new Date(), h = now.getHours(), m = now.getMinutes(), s = now
			.getSeconds(), ms = now.getMilliseconds();
	return (h + ":" + m + ":" + s + " " + ms);
}

function checkNode(e) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), type = e.data.type, nodes = zTree
			.getSelectedNodes();
	if (type.indexOf("All") < 0 && nodes.length == 0) {
		alert("请先选择一个节点");
	}

	if (type == "checkAllTrue") {
		zTree.checkAllNodes(true);
	} else if (type == "checkAllFalse") {
		zTree.checkAllNodes(false);
	} else {
		var callbackFlag = $("#callbackTrigger").attr("checked");
		for (var i = 0, l = nodes.length; i < l; i++) {
			if (type == "checkTrue") {
				zTree.checkNode(nodes[i], true, false, callbackFlag);
			} else if (type == "checkFalse") {
				zTree.checkNode(nodes[i], false, false, callbackFlag);
			} else if (type == "toggle") {
				zTree.checkNode(nodes[i], null, false, callbackFlag);
			} else if (type == "checkTruePS") {
				zTree.checkNode(nodes[i], true, true, callbackFlag);
			} else if (type == "checkFalsePS") {
				zTree.checkNode(nodes[i], false, true, callbackFlag);
			} else if (type == "togglePS") {
				zTree.checkNode(nodes[i], null, true, callbackFlag);
			}
		}
	}
}

function setAutoTrigger(e) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.setting.check.autoCheckTrigger = $("#autoCallbackTrigger").attr(
			"checked");
	$("#autoCheckTriggerValue").html(
			zTree.setting.check.autoCheckTrigger ? "true" : "false");
}

$(function() {

	// $(document).ready(function() {});
	queryRoleByPage();
});
/** *********************************** 查询功能 begin **************************** */
// 翻页时重新加载数据
function reloadData(pageNum) {
	$("#pagenum").val(pageNum);
	queryRoleByPag();
}
function queryRoleByPag() {

	var roleName = $("#roleName").val();// 角色名称
	var roleCode = $("#roleCode").val();// 角色编码
	var pageIndex = $("#pagenum").val();// 当前页
	var itemnum = $("#itemnum").val();
	var obj = {
		"roleName" : roleName,
		"roleCode" : roleCode,
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
		url : base.domain + "role/queryRoleByPage",
		data : sendData,
		dataType : 'json',
		success : function(data) {
			if (data != "") {
				var paramList = eval("(" + data + ")");
				if (paramList.status.statusCode == "000000") {
					roleInfoList = paramList.page.entities;// 用户列表信息
					if (roleInfoList.length < 1) {
						$("#noCus").show("fast");
						$(".useManage_article_button").css("display", "none");
						$("#page_control").hide();
						$("#content").html("");
						return;
					}
					$("#count").val(paramList.page.entityCount);
					$("#itemnum").val(paramList.page.pageSize);

					$.roleInfo();

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

// 翻页时重新请求数据itemnum
function queryRoleByPage() {

	var roleName = $("#roleName").val();// 角色名称
	var roleCode = $("#roleCode").val();// 角色编码
	var pageIndex = $("#pagenum").val();// 当前页
	var itemnum = $("#itemnum").val();
	var obj = {
		"roleName" : roleName,
		"roleCode" : roleCode,
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
		url : base.domain + "role/queryRoleByPage",
		data : sendData,
		dataType : 'json',
		success : function(data) {
			if (data != "") {
				var paramList = eval("(" + data + ")");
				if (paramList.status.statusCode == "000000") {
					roleInfoList = paramList.page.entities;// 用户列表信息
					if (roleInfoList.length < 1) {
						$("#noCus").show("fast");
						$(".useManage_article_button").css("display", "none");
						$("#page_control").hide();
						$("#content").html("");
						return;
					}
					$("#count").val(paramList.page.entityCount);
					$("#itemnum").val(paramList.page.pageSize);
					$.fenyeInit();// 分页信息
					$.roleInfo();

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

// 往页面渲染数据
$.roleInfo = function() {
	var param = roleInfoList;
	if (param != null && param.length > 0) {
		$("#noCus").hide();
		$(".useManage_article_button").css("display", "block");
		$("#page_control").show("fast");
		var str = "";
		for (var i = 0; i < param.length; i++) {
			str += "<tr>";
			str += "<td class='um_th_1'><input id='checkRes' type='checkbox' name='checkResName'/><input type='hidden' value='"
					+ param[i].id + "'/></td>";
			str += "<td class='um_th_2'><span>" + param[i].id + "</span></td>";
			str += "<td class='um_th_2'><div class='authorization' onclick='showAuthorizationDiglog(this)'><input type='hidden' value='"
					+ param[i].id + "'/>授权</div></td>";
			str += "<td class='um_th_3'>" + param[i].roleName + "</td>";
			str += "<td class='um_th_4'>" + param[i].roleCode + "</td>";
			str += "<td class='um_th_5'>" + param[i].description + "</td>";
			var insertTime = "";
			if (param[i].insertTime != "" && param[i].insertTime != null) {
				insertTime = timeFormatDate(param[i].insertTime.time,
						'yyyy-MM-dd HH:mm:ss');
			}
			str += "<td class='um_th_6'>" + insertTime + "</td>";
			str += "<td class='um_th_7'>" + param[i].insertUser + "</td>";
			str += "<td class='um_th_8'>" + param[i].enabled + "</td>";
			str += "</tr>";
		}

		$("#content").html(str);
	} else {
		$("#content").html("");
		$("#noCus").show("fast");
		$(".useManage_article_button").css("display", "none");
		$("page_control").hidden();
	}

};

// 查询按钮事件
$("#queryRole").click(function() {

	queryRoleByPage();
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
var bh = document.documentElement.clientHeight;
$("#fullbg").css({
	height : bh,
});

/* 添加按钮弹出框 */
$("#add_roleInfo").click(function() {
	$("#roleNameDig").val("");
	$("#roleCodeDig").val("");
	$("#roleNameDig").val("");
	$("#fullbg").css("height", $(document).height());
	$("#fullbg").show();
	$("#dialog").show();
	$("#add_submit").show();
});
/* 按钮变色 */
$(".useManage_article_button").mouseover(function() {
	$(this).css('background-color', '#1b6bb8');
});
$(".useManage_article_button").mouseout(function() {
	$(this).css('background-color', '#076dce');
});
$(".delete_dialog_div").mouseover(function() {
	$(this).css('background-color', '#1b6bb8');
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
	var flag = checkAddRole();
	if (flag) {
		$("#dialog").hide();
		$("#fullbg").hide();
		// 添加角色
		addRole();
		cleanHistory();
	}
});

/**
 * 新增页面数据校验
 */
function checkAddRole() {

	var roleName = $("#roleNameDig").val();// 角色名称
											// Trim($("#textName").val())!=""
	var roleCode = $("#roleCodeDig").val();// 角色代码(/\s+/g, "")

	if ($.trim(roleName) == null || $.trim(roleName) == "") {

		window.parent.createPopup("角色名不能为空!");
		return false;
	}

	if (roleCode != null && roleCode != "") {
		if (tit.regExp.isCardNum(roleCode) == false) {
			window.parent.createPopup("角色代码只能填写数字和字母");
			return false;
		}
	}

	if (roleCode == null || roleCode == "") {
		window.parent.createPopup("角色代码不能为空!");
		return false;
	}
	return true;

}

// 添加角色
function addRole() {

	var roleName = $("#roleNameDig").val();// 角色名称
	var roleCode = $("#roleCodeDig").val();// 角色编码
	var enabled = $("#enabledDig").val();// 启用标识实际值
	var enabledName = $("#enabledDig option:checked").text();// 启用标识显示值
	var description = $("#descriptionDig").val();// 描述
	var obj = {
		"roleName" : roleName,
		"roleCode" : roleCode,
		"enabled" : enabled,
		"description" : description
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	$
			.ajax({
				type : 'post',
				data : sendData,
				url : base.domain + "role/addRole",
				dataType : 'json',
				success : function(data) {
					var param = eval("(" + data + ")");
					if (param.status.statusCode == "000000"
							&& param.role != null) {
						// createPopup("添加角色成功");
						window.location.reload(); // 刷新页面
						var orginCount = $("#count").val();
						var str = "";
						str += "<tr>";
						str += "<td class='um_th_1'><input id='checkRes' type='checkbox' name='checkResName'/><input type='hidden' value='"
								+ param.role.id + "'/></td>";
						str += "<td class='um_th_2'><span>" + param.role.id
								+ "</span></td>";
						str += "<td class='um_th_2'><div class='authorization' onclick='showAuthorizationDiglog(this)'><input type='hidden' value='"
								+ param.role.id + "'/>授权</div></td>";
						str += "<td class='um_th_3'>" + param.role.roleName
								+ "</td>";
						str += "<td class='um_th_4'>" + param.role.roleCode
								+ "</td>";
						str += "<td class='um_th_5'>" + param.role.description
								+ "</td>";
						str += "<td class='um_th_6'>"
								+ timeFormatDate(param.role.insertTime,
										'yyyy-MM-dd HH:mm:ss') + "</td>";
						str += "<td class='um_th_7'>" + param.role.insertUser
								+ "</td>";
						str += "<td class='um_th_8'>" + enabledName + "</td>";
						str += "</tr>";

						$("#content").prepend(str);
						if ($("#content").find("tr").length > 10) {
							$("#content").find("tr").last().remove();
						}
						$("#count").val(Number(orginCount) + 1);
						$.fenyeInit();// 分页信息
					} else {

						window.parent.createPopup("角色名称或代码已经存在,不能修改");

					}

				},
				error : function() {
					alert("请求服务超时");
				}
			});
}

function cleanHistory() {
	$("#roleNameDig").val("");// 角色名称
	$("#roleCodeDig").val("");// 角色编码
	$("#descriptionDig").val("");// 描述
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
$("#alter_roleInfo").click(function() {

	var checkedList = $(":checkbox:checked").closest("tr");
	// check选择的行数
	if (checkedList.length != 1) {
		window.parent.createPopup("请选择一条角色信息进行修改");
		return false;
	}
	// 用户id
	var roleId = $(checkedList[0]).children("td").eq(1).text();
	// 修改页面数据渲染
	findRole(roleId);
	$("#fullbg").css("height", $(document).height());
	$("#fullbg").show();
	$("#dialog2").show();

});

/* 修改页面，保存事件 */
$("#add_submit2").click(function() {
	// 数据校验
	var flag = checkUpdateRole();
	if (flag) {
		$("#dialog2").hide();
		$("#fullbg").hide();
		// 更新用户
		updateRole();
	}
});

/**
 * 修改页面数据校验
 */
function checkUpdateRole() {

	var roleName = $("#roleNameDig2").val();// 角色名称
	var roleCode = $("#roleCodeDig2").val();// 角色编码
	if (roleName == null || roleName == "") {
		window.parent.createPopup("角色名不能为空!");
		return false;
	}
	if (roleCode == null || roleCode == "") {
		window.parent.createPopup("角色编码不能为空!");
		return false;
	}
	return true;

}

/* 隐藏修改页面弹框 */
$("#cancle_submit2").click(function() {
	$("#dialog2").hide();
	$("#fullbg").hide();
});

/**
 * 查询某个角色
 */
function findRole(roleId) {

	var obj = {
		"roleId" : roleId
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	// 提交数据到后台 ajax方式提交到后台
	$.ajax({
		type : 'post',
		data : sendData,
		url : base.domain + "role/findRoleById",
		dataType : 'json',
		success : function(data) {
			// 页面渲染处理
			if (data != "") {
				var param = eval("(" + data + ")");
				if (param.status.statusCode == "000000" && param.role != null) {
					roleInfo = param.role;// 用户信息
					$("#roleId").val(roleInfo.id);
					$("#roleNameDig2").val(roleInfo.roleName);
					$("#roleCodeDig2").val(roleInfo.roleCode);
					$("#enabledDig2").val(roleInfo.enabled);
					$("#descriptionDig2").val(roleInfo.description);

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

	return true;
}

/**
 * 修改角色
 */
function updateRole() {

	var roleId = $("#roleId").val();// 角色id
	var roleName = $("#roleNameDig2").val();// 角色名称
	var roleCode = $("#roleCodeDig2").val();// 角色编码
	var enabled = $("#enabledDig2").val();// 启用标识实际值
	var enabledName = $("#enabledDig2 option:checked").text();// 启用标识显示值
	var description = $("#descriptionDig2").val();// 描述

	var obj = {
		"roleId" : roleId,
		"roleName" : roleName,
		"roleCode" : roleCode,
		"enabled" : enabled,
		"description" : description
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	$.ajax({
		type : 'post',
		data : sendData,
		url : base.domain + "role/updateRole",
		dataType : 'json',
		success : function(data) {
			var param = eval("(" + data + ")");
			if (param.status.statusCode == "000000" && param.result == 1) {
				window.parent.createPopup("修改角色成功");
				// 页面端更新
				var checkedList = $(":checkbox:checked").closest("tr");

				var $td = checkedList.find('td');
				$td.eq(3).html(roleName);
				$td.eq(4).html(roleCode);
				$td.eq(5).html(description);
				$td.eq(8).html(enabledName);

			} else {

				window.parent.createPopup("角色名称或代码已经存在,不能修改");

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
$("#delete_roleInfo").click(function() {
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
		window.parent.createPopup("请选择一条角色信息进行删除");
		return false;
	}
	$("#fullbg").css("height", $(document).height());
	$("#fullbg").show();
	$(".dialog_delete").show();
});

/* 删除框 按钮ok点击事件 */
$("#ok").click(function() {

	deleteRole();
	$(".dialog_delete").hide();
	$("#fullbg").hide();

});

/* 删除框 按钮no点击事件 */
$("#no").click(function() {
	$(".dialog_delete").hide();
	$("#fullbg").hide();
});

/*
 * 删除角色
 */
function deleteRole() {

	var checkedList = $(":checkbox:checked").closest("tr");
	var roleIds = "";

	var $tbody = $("#content");
	var $tr = $tbody.children("tr");
	$tr.each(function() {
		var $td = $(this).children('td');
		var $input = $td.children("input");
		if ($input[0].checked == true) {
			roleIds += $input[1].value + ",";
		}
	});

	var obj = {
		"roleIds" : roleIds
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	// 提交数据到后台 ajax方式提交到后台
	$.ajax({
		type : 'post',
		data : sendData,
		url : base.domain + "role/deleteRole",
		dataType : 'json',
		success : function(data) {
			var param = eval("(" + data + ")");
			if (param.status.statusCode == "000000") {
				window.parent.createPopup("删除角色成功");
				// 页面更新有效状态
				$("#pagenum").val(1);
				queryRoleByPage();
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
	$("#autoCallbackTrigger").bind("change", {}, setAutoTrigger);
	var roleId = obj.firstChild.value;// 当前角色id
	$("#roleResId").val(roleId);
	var obj = {
		"roleId" : roleId
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	$.ajax({
		async : false,
		type : "post",
		url : base.domain + "roleResource/findResourcesByRoleCode",
		data : sendData,
		dataType : 'json',
		success : function(data) {
			if (data != "") {
				var paramList = eval("(" + data + ")");
				if (paramList.status.statusCode == "000000") {
					showTree(paramList.ztreeDTOs)
				} else {
					window.parent.createPopup("获取资源信息失败！");
				}
			} else {
				window.parent.createPopup("获取资源信息异常！");
			}
		},
		error : function() {
			alert("请求服务超时");
		}
	});

	// 资源授权页面数据渲染
	// queryResByPage(roleId);
	// 默认选中当前角色下的资源
	// findCurrentRoleRes(roleId);
}
function showTree(data) {
	$.fn.zTree.init($("#treeDemo"), setting, data);
	$("#checkTrue").bind("click", {
		type : "checkTrue"
	}, checkNode);
	$("#checkFalse").bind("click", {
		type : "checkFalse"
	}, checkNode);
	$("#toggle").bind("click", {
		type : "toggle"
	}, checkNode);
	$("#checkTruePS").bind("click", {
		type : "checkTruePS"
	}, checkNode);
	$("#checkFalsePS").bind("click", {
		type : "checkFalsePS"
	}, checkNode);
	$("#togglePS").bind("click", {
		type : "togglePS"
	}, checkNode);
	$("#checkAllTrue").bind("click", {
		type : "checkAllTrue"
	}, checkNode);
	$("#checkAllFalse").bind("click", {
		type : "checkAllFalse"
	}, checkNode);
}

/*
 * 资源查询并授权资源数据
 */
function queryResByPage(roleId) {
	var obj = {
		"resourceName" : "",
		"resourceCode" : "",
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
		url : base.domain + "role/getResTree",
		data : sendData,
		dataType : 'json',
		success : function(data) {
			if (data != "") {
				var paramList = eval("(" + data + ")");
				if (paramList.status.statusCode == "000000") {
					// resourceInfoList = paramList.page.entities;//资源列表信息
					var str = paramList.treeMenu;
					$("#resContent").html(str);
					$("#roleResId").val(roleId);
					// 用户授权页面数据渲染
					// resourceInfo(roleId);
				} else {
					window.parent.createPopup("获取资源信息失败！");
				}
			} else {
				window.parent.createPopup("获取资源信息异常！");
			}
		},
		error : function() {
			alert("请求服务超时");
		}
	});
}

/**
 * 查询当前角色下的资源 并 选中已经存在的资源
 * 
 * @param roleId
 */
function findCurrentRoleRes(roleId) {
	var obj = {
		"roleId" : roleId
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	$
			.ajax({
				async : false,
				type : "post",
				url : base.domain + "roleResource/findRoleResourcesByRoleCode",
				data : sendData,
				dataType : 'json',
				success : function(data) {
					if (data != "") {
						var paramList = eval("(" + data + ")");
						if (paramList.status.statusCode == "000000") {
							// 选中已有的资源
							var roleResList = paramList.roleResourceList;
							// 对当前角色下以后的角色做遍历判断处理
							for (var i = 0; i < roleResList.length; i++) {

								var $tbody = $("#resContent");
								var $input = $tbody.find('input[type=hidden]');
								// 遍历table中的数据
								$input
										.each(function(j) {
											if (roleResList[i].resourceCode == $input[j].value) {
												// 选中checkBox
												$($input[j]).prev().prop(
														"checked", true);
											}
										});
							}

						} else {
							window.parent.createPopup("查询角色资源关系失败！");
						}
					} else {
						window.parent.createPopup("查询角色资源关系异常！");
					}
				},
				error : function() {
					alert("请求服务超时");
				}
			});

}

// 往角色授权页面渲染数据
/*
 * function resourceInfo(roleId){ var param = resourceInfoList; if(param != null &&
 * param.length>0){ var str = ""; for(var i=0; i<param.length;i++){ str+="<tr>";
 * str+="<td class='um_th_1'><input type='checkbox' id='checkRole'
 * name='checkRoleName' /><input type='hidden' id='resId'
 * value='"+param[i].id+"'/></td>"; str+="<td class='um_th_3'>"+param[i].resourceName+"</td>";
 * str+="</tr>"; } $("#resContent").html(str); $("#roleResId").val(roleId);
 * }else{ $("#resContent").html(""); } };
 */

/* 授权页面,保存按钮 */
$("#author_submit").click(function() {
	var zTreeNodes = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = zTreeNodes.getCheckedNodes();

	var strResourceCode = '';// 选中节点ids

	// 遍历选中的节点，为s赋值
	for (var i = 0; i < nodes.length; i++) {

		if (strResourceCode != '')
			strResourceCode += ',';

		strResourceCode += nodes[i].id;

	}
	// 保存数据到用户角色关系表中
	var roleId = $("#roleResId").val();// 当前选中的角色id
	var checkedId = "";// 所有选中的资源id
	var unCheckedId = "";

	addRoleResource(roleId, strResourceCode, unCheckedId);

});

/* 授权关闭 */
$(".author_close").click(function() {
	$("#fullbg").hide();
	$(".dialog_authorization").hide();
	$("#roleContent").html("");
});
/* 授权关闭 */
function authorClose() {
	$("#fullbg").hide();
	$(".dialog_authorization").hide();
	$("#roleContent").html("");
};

function changeStyle(obj) {
	var ul = $(obj).next("ul");
	if (ul.find("li").html() != null) {
		if (ul.find('li').css("display") == "none") {
			ul.find('li').show(300);
		} else {
			ul.find('li').hide(300);
		}
	}
}
/**
 * 用户角色关系维护
 * 
 * @param userId
 * @param checked
 * @param unChecked
 */
function addRoleResource(roleId, checked, unChecked) {
	var obj = {
		"roleId" : roleId,
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
		url : base.domain + "roleResource/updateRoleResource",
		data : sendData,
		dataType : 'json',
		success : function(data) {
			if (data != "") {
				var paramList = eval("(" + data + ")");
				if (paramList.status.statusCode == "000000") {
					window.parent.createPopup("角色资源关系维护成功", authorClose);
				} else {
					window.parent.createPopup("角色资源关系维护失败！");
				}
			} else {
				window.parent.createPopup("角色资源关系维护异常！");
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
