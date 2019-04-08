/**
 * 默认查询所有数据
 */
var setting = {
	view : {
		selectedMulti : false
	},
	check : {
		enable : true,
		chkStyle : "radio",
		radioType : "all"
	},
	data : {
		simpleData : {
			enable : true
		}
	}
};
$(document).ready()
$(function() {
	getTree();
	firstResource("parentIdDig", "", null, true);
	firstResource("parentIdDig2", "", null, true);
	// resourceType("resource_type", "resourceTypeDig", "", null);
	resourceType("resource_type", "resourceTypeDig2", null);
	// resourceType("enabled", "enabledDig", "", null);
	resourceType("enabled", "enabledDig2", null);
	queryResourceByPage();

});
/** *****************************加载资源树************************************* */
function getTree() {
	var sendData = {
		request : null
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	$.ajax({
		async : false,
		type : "post",
		url : base.domain + "resource/findAllResource",
		data : sendData,
		dataType : 'json',
		success : function(data) {
			if (data != "") {
				var paramList = eval("(" + data + ")");
				if (paramList.status.statusCode == "000000") {
					$.fn.zTree.init($("#treeDemo"), setting,
							paramList.ztreeDTOs);
					$.fn.zTree.init($("#treeDemo1"), setting,
							paramList.ztreeDTOs);
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
/** *********************************** 查询功能 begin **************************** */
// 翻页时重新加载数据
function reloadData(pageNum) {
	$("#pagenum").val(pageNum);
	queryResourceByPag();
}

function queryResourceByPag() {

	var resourceName = $("#resourceName").val();// 资源名称
	var resourceCode = $("#resourceCode").val();// 资源编码
	var pageIndex = $("#pagenum").val();// 当前页
	var itemnum = $("#itemnum").val();
	var obj = {
		"resourceName" : resourceName,
		"resourceCode" : resourceCode,
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
		url : base.domain + "resource/queryResourceByPage",
		data : sendData,
		dataType : 'json',
		success : function(data) {
			if (data != "") {
				var paramList = eval("(" + data + ")");
				if (paramList.status.statusCode == "000000") {
					resourceInfoList = paramList.page.entities;// 资源列表信息
					if (resourceInfoList.length < 1) {
						$("#noCus").show("fast");
						$(".useManage_article_button").css("display", "none");
						$("#page_control").hide();
						$("#content").html("");
						return;
					}
					$("#count").val(paramList.page.entityCount);
					$("#itemnum").val(paramList.page.pageSize);

					$.resourceInfo();

				} else {
					window.parent.createPopup("获取资源信息失败！");
				}
			} else {
				window.parent.createPopup("获取资源信息异常！");
			}
		},
		error : function() {
			window.parent.createPopup("请求服务超时");
		}
	});
}

// 翻页时重新请求数据
function queryResourceByPage() {

	var resourceName = $("#resourceName").val();// 资源名称
	var resourceCode = $("#resourceCode").val();// 资源编码
	var pageIndex = $("#pagenum").val();// 当前页
	var itemnum = $("#itemnum").val();
	var obj = {
		"resourceName" : resourceName,
		"resourceCode" : resourceCode,
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
		url : base.domain + "resource/queryResourceByPage",
		data : sendData,
		dataType : 'json',
		success : function(data) {
			if (data != "") {
				var paramList = eval("(" + data + ")");
				if (paramList.status.statusCode == "000000") {
					resourceInfoList = paramList.page.entities;// 资源列表信息
					if (resourceInfoList.length < 1) {
						$("#noCus").show("fast");
						$(".useManage_article_button").css("display", "none");
						$("#page_control").hide();
						$("#content").html("");
						return;
					}
					$("#count").val(paramList.page.entityCount);
					$("#itemnum").val(paramList.page.pageSize);
					$.fenyeInit();// 分页信息
					$.resourceInfo();

				} else {
					window.parent.createPopup("获取资源信息失败！");
				}
			} else {
				window.parent.createPopup("获取资源信息异常！");
			}
		},
		error : function() {
			window.parent.createPopup("请求服务超时");
		}
	});
}

// 往页面渲染数据
$.resourceInfo = function() {
	var param = resourceInfoList;
	if (param != null && param.length > 0) {
		$("#noCus").hide();
		$(".useManage_article_button").css("display", "block");
		$("#page_control").show("fast");
		var str = "";
		for (var i = 0; i < param.length; i++) {
			str += "<tr>";
			str += "<td class='um_th_1'><input id='checkRes' type='checkbox' name='checkResName'/><input type='hidden' value='"
					+ param[i].id + "'/></td>";
			str += "<td class='um_th_2'>" + param[i].id + "</td>";
			str += "<td class='um_th_2'>" + param[i].resourceCode + "</td>";
			str += "<td class='um_th_3'>" + param[i].resourceName + "</td>";
			if (param[i].resourceType == 1) {
				str += "<td class='um_th_4'>菜单</td>";
			}
			if (param[i].resourceType == 2) {
				str += "<td class='um_th_4'>按钮</td>";
			}
			if (param[i].resourceType == 3) {
				str += "<td class='um_th_4'>链接</td>";
			}
			str += "<td class='um_th_5'>" + param[i].url + "</td>";
			str += "<td class='um_th_6'>" + param[i].sortOrder + "</td>";
			str += "<td class='um_th_7'>" + param[i].parentName + "</td>";
			str += "<td class='um_th_8'>" + param[i].enabled + "</td>";
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
// 资源id转义成资源名称
function transferParentIdToName(id) {
	var name = "";
	if (id == 1) {
		name = "系统管理";
	}
	if (id == 2) {
		name = "资源管理";
	}
	if (id == 3) {
		name = "用户管理";
	}
	if (id == 4) {
		name = "角色管理";
	}
	if (id == 5) {
		name = "代理人管理";
	}
	if (id == 6) {
		name = "客户管理";
	}
	if (id == 7) {
		name = "产品设置管理";
	}
	if (id == 8) {
		name = "统计报表管理";
	}
	return name;
}

// 查询按钮事件
$("#queryResource").click(function() {
	$("#pagenum").val();
	queryResourceByPage();
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
/*
 * var bh = $(document).height(); alert(bh); $("#fullbg").css({ height : bh, });
 */

/* 添加按钮弹出框 */
$("#add_resourceInfo").click(function() {
	getTree();
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

/* 添加页面，保存事件 */
$("#add_submit").click(function() {
	// 页面数据校验
	var flag = checkAddResource();
	if (flag) {
		$("#dialog").hide();
		$("#fullbg").hide();
		// 添加资源
		addResource();
		cleanHistory();
	}
});

/**
 * 新增页面数据校验
 */
function checkAddResource() {

	var resourceCode = $("#resourceCodeDig").val();// 资源编码
	var sortId = $("#sortIdDig").val();// 排序号resourceTypeDig
	var resourceTypeDig = $("#resourceTypeDig").val;// 资源类型
	var enabledDig = $("#enabledDig").val;// 启用标示
	var resourceName = $("#resourceNameDig").val();// 资源名称enabledDig
	var url = $("#urlDig").val();// 资源路径

	if (resourceCode != null && resourceCode != "") {
		var reg = /^\w+$/;
		if (!reg.test(resourceCode)) {
			window.parent.createPopup("资源编码只能填写数字和字母");
			return false;
		}
	}

	if (enabledDig == null || enabledDig == "") {
		window.parent.createPopup("启用标示不能为空!");
		return false;
	}
	if (resourceTypeDig == null || resourceTypeDig == "") {
		window.parent.createPopup("资源类型不能为空!");
		return false;
	}
	if (resourceCode == null || resourceCode == "") {
		window.parent.createPopup("资源编码不能为空!");
		return false;
	}
	if ($.trim(sortId) == null || $.trim(sortId) == "") {
		window.parent.createPopup("排序号不能为空!");
		return false;
	}
	// 只能是数字的正则表达式
	var sortIdReg = /^\d+$/;
	if (!sortIdReg.test(sortId)) {
		window.parent.createPopup("排序号只能是数字!");
		return false;
	}
	if ($.trim(resourceName) == null || $.trim(resourceName) == "") {
		window.parent.createPopup("资源名不能为空!");
		return false;
	}
	// if (url == null || url == "") {
	// window.parent.createPopup("资源路径不能为空!");
	// return false;
	// }
	return true;

}

// 添加资源
function addResource() {

	var resourceCode = $("#resourceCodeDig").val();// 资源编码
	var sortId = $("#sortIdDig").val();// 资源排序号
	var resourceName = $("#resourceNameDig").val();// 资源名称
	var resourceType = $("#resourceTypeDig").val();// 资源类型
	var enabled = $("#enabledDig").val();// 启用标识实际值
	var enabledName = $("#enabledDig option:checked").text();// 启用标识显示值
	var zTreeNodes = $.fn.zTree.getZTreeObj("treeDemo1");
	var nodes = zTreeNodes.getCheckedNodes(true);
	var parentId;
	if (nodes.length > 0) {
		parentId = nodes[0].id;// 父资源id
	} else {
		parentId = "";// 父资源id
	}
	var parentName = $("#parentIdDig option:checked").text();// 父资源名称
	var url = $("#urlDig").val();// 资源路径
	var description = $("#descriptionDig").val();// 资源描述
	var obj = {
		"resourceCode" : resourceCode,
		"sortId" : sortId,
		"resourceName" : resourceName,
		"resourceType" : resourceType,
		"enabled" : enabled,
		"parentId" : parentId,
		"url" : url,
		"description" : description
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	$.ajax({
		type : 'post',
		data : sendData,
		url : base.domain + "resource/addRes",
		dataType : 'json',
		success : function(data) {
			var param = eval("(" + data + ")");
			if (param.status.statusCode == "000000" && param.resource != null) {
				window.parent.createPopup("添加资源成功");
				/*
				 * var orginCount = $("#count").val(); var str = ""; str += "<tr>";
				 * str += "<td class='um_th_1'><input id='checkRes'
				 * type='checkbox' name='checkResName'/><input type='hidden'
				 * value='" + param.resource.id + "'/></td>"; str += "<td class='um_th_2'>" +
				 * param.resource.id + "</td>"; str += "<td class='um_th_2'>" +
				 * param.resource.resourceCode + "</td>"; str += "<td class='um_th_3'>" +
				 * param.resource.resourceName + "</td>"; if
				 * (param.resource.resourceType == 1) { str += "<td class='um_th_4'>菜单</td>"; }
				 * if (param.resource.resourceType == 2) { str += "<td class='um_th_4'>按钮</td>"; }
				 * if (param.resource.resourceType == 3) { str += "<td class='um_th_4'>链接</td>"; }
				 * str += "<td class='um_th_5'>" + param.resource.url + "</td>";
				 * str += "<td class='um_th_6'>" + param.resource.sortOrder + "</td>";
				 * str += "<td class='um_th_7'>" + parentName + "</td>"; str += "<td class='um_th_8'>" +
				 * enabledName + "</td>";
				 * 
				 * str += "</tr>";
				 * 
				 * $("#content").prepend(str); if
				 * ($("#content").find("tr").length > 10) {
				 * $("#content").find("tr").last().remove(); }
				 * $("#count").val(Number(orginCount) + 1);
				 */
				// $.fenyeInit();// 分页信息
				queryResourceByPage();
			}
			if (param.status.statusCode == "000008") {
				window.parent.createPopup(param.status.statusMessage);
			} else {
				window.parent.createPopup("新增资源失败");
			}

		},
		error : function() {
			alert("请求服务超时");
		}
	});
}
/**
 * 清除页面数据
 */
function cleanHistory() {
	$("#resourceCodeDig").val("");// 资源编码
	$("#sortIdDig").val("");// 资源排序号
	$("#resourceNameDig").val("");// 资源名称
	$("#resourceTypeDig").val("1");// 资源类型
	$("#enabledDig").val("1");// 启用标识实际值
	// $("#enabledDig option:checked").text("1");// 启用标识显示值
	$("#parentIdDig").val("");// 父资源id
	$("#parentIdDig option:checked").text("");// 父资源名称
	$("#urlDig").val("");// 资源路径
	$("#descriptionDig").val("");// 资源描述
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
$("#alter_resourceInfo").click(function() {
	getTree();
	var checkedList = $(":checkbox:checked").closest("tr");
	// check选择的行数
	if (checkedList.length != 1) {
		window.parent.createPopup("请选择一条资源信息进行修改");
		return false;
	}
	// 资源id
	var resourceId = $(checkedList[0]).children("td").eq(1).text();
	// 修改页面数据渲染
	findResource(resourceId);
	$("#fullbg").css("height", $(document).height());
	$("#fullbg").show();
	$("#dialog2").show();

});

/* 修改页面，保存事件 */
$("#add_submit2").click(function() {
	// 数据校验
	var flag = checkUpdateResource();
	if (flag) {
		$("#dialog2").hide();
		$("#fullbg").hide();
		// 更新资源
		updateResource();
	}
});

/**
 * 修改页面数据校验
 */
function checkUpdateResource() {

	var resourceCode = $("#resourceCodeDig2").val();// 资源编码
	var sortId = $("#sortIdDig2").val();// 排序号
	var resourceTypeDig = $("#resourceTypeDig").val;// 资源类型
	var enabledDig = $("#enabledDig").val;// 启用标示
	var resourceName = $("#resourceNameDig2").val();// 资源名称
	var url = $("#urlDig2").val();// 资源路径

	if (enabledDig == null || enabledDig == "") {
		window.parent.createPopup("启用标示不能为空!");
		return false;
	}
	if (resourceTypeDig == null || resourceTypeDig == "") {
		window.parent.createPopup("资源类型不能为空!");
		return false;
	}

	if (resourceCode == null || resourceCode == "") {
		var reg = /^\w+$/;
		if (!reg.test(resourceCode)) {
			window.parent.createPopup("资源编码不能为空!");
			return false;
		}
	}
	if (sortId == null || sortId == "") {
		window.parent.createPopup("资源排序号不能为空!");
		return false;
	}
	// 只能是数字的正则表达式
	var sortIdReg = /^\d+$/;
	if (!sortIdReg.test(sortId)) {
		window.parent.createPopup("资源排序号只能是数字!");
		return false;
	}
	if (resourceName == null || resourceName == "") {
		window.parent.createPopup("资源名不能为空!");
		return false;
	}
	// if (url == null || url == "") {
	// window.parent.createPopup("资源路径不能为空!");
	// return false;
	// }
	return true;

}

/* 隐藏修改页面弹框 */
$("#cancle_submit2").click(function() {
	$("#dialog2").hide();
	$("#fullbg").hide();
});

/**
 * 查询某个资源
 */
function findResource(resourceId) {
	var obj = {
		"resourceId" : resourceId
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	// 提交数据到后台 ajax方式提交到后台
	$.ajax({
		type : 'post',
		data : sendData,
		url : base.domain + "resource/findResById",
		dataType : 'json',
		success : function(data) {
			// 页面渲染处理
			if (data != "") {
				var param = eval("(" + data + ")");
				if (param.status.statusCode == "000000"
						&& param.resource != null) {
					resourceInfo = param.resource;// 用户信息
					$("#resourceId").val(resourceInfo.id);
					$("#resourceCodeDig2").val(resourceInfo.resourceCode);
					$("#sortIdDig2").val(resourceInfo.sortOrder);
					$("#resourceNameDig2").val(resourceInfo.resourceName);
					$("#resourceTypeDig2").val(resourceInfo.resourceType);
					$("#enabledDig2").val(resourceInfo.enabled);
					$("#parentIdDig2").val(resourceInfo.parentId);
					$("#urlDig2").val(resourceInfo.url);
					$("#descriptionDig2").val(resourceInfo.description);
					if (!$.isNull(resourceInfo.parentId)) {
						var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
						var node = treeObj.getNodesByParam("id",
								resourceInfo.parentId);
						if (node.length > 0) {
							treeObj.checkNode(node[0], true, true);
						}
					}

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

	return true;
}

/**
 * 修改用户
 */
function updateResource() {

	var resourceId = $("#resourceId").val();// 资源id
	var resourceCode = $("#resourceCodeDig2").val();// 资源编码
	var sortId = $("#sortIdDig2").val();// 资源排序号
	var resourceName = $("#resourceNameDig2").val();// 资源名称
	var resourceType = $("#resourceTypeDig2").val();// 资源类型实际值
	var resourceTypeName = $("#resourceTypeDig2 option:checked").text();// 资源类型显示值
	var enabled = $("#enabledDig2").val();// 启用标识实际值
	var enabledName = $("#enabledDig2 option:checked").text();// 启用标识显示值
	var zTreeNodes = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = zTreeNodes.getCheckedNodes(true);
	var parentId;
	if (nodes.length > 0) {
		parentId = nodes[0].id;// 父资源id
	} else {
		parentId = "";// 父资源id
	}
	var parentName = $("#parentIdDig2 option:checked").text();// 父资源名称
	var url = $("#urlDig2").val();// 资源路径
	var description = $("#descriptionDig2").val();// 资源描述

	var obj = {
		"resourceId" : resourceId,
		"resourceCode" : resourceCode,
		"sortId" : sortId,
		"resourceName" : resourceName,
		"resourceType" : resourceType,
		"enabled" : enabled,
		"parentId" : parentId,
		"url" : url,
		"description" : description
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	$.ajax({
		type : 'post',
		data : sendData,
		url : base.domain + "resource/updateRes",
		dataType : 'json',
		success : function(data) {
			var param = eval("(" + data + ")");
			if (param.status.statusCode == "000000" && param.result == 1) {
				window.parent.createPopup("修改资源成功");
				// 页面端更新
				var checkedList = $(":checkbox:checked").closest("tr");

				var $td = checkedList.find('td');
				$td.eq(2).html(resourceCode);
				$td.eq(3).html(resourceName);
				$td.eq(4).html(resourceTypeName);
				$td.eq(5).html(url);
				$td.eq(6).html(sortId);
				$td.eq(7).html(parentName);
				$td.eq(8).html(enabledName);

			} else {
				window.parent.createPopup("资源名称或编码已经存在,不能修改！");
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
$("#delete_resourceInfo").click(function() {
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
		window.parent.createPopup("请选择一条资源信息进行删除");
		return false;
	}
	$("#fullbg").css("height", $(document).height());
	$("#fullbg").show();
	$(".dialog_delete").show();
});

/* 删除框 按钮ok点击事件 */
$("#ok").click(function() {

	deleteResource();
	$(".dialog_delete").hide();
	$("#fullbg").hide();

});

/* 删除框 按钮no点击事件 */
$("#no").click(function() {
	$(".dialog_delete").hide();
	$("#fullbg").hide();
});

/*
 * 删除资源
 */
function deleteResource() {

	var checkedList = $(":checkbox:checked").closest("tr");
	var resIds = "";
	var $tbody = $("#content");
	var $tr = $tbody.children("tr");
	$tr.each(function() {
		var $td = $(this).children('td');
		var $input = $td.children("input");
		if ($input[0].checked == true) {
			resIds += $input[1].value + ",";
		}
	});
	var obj = {
		"resIds" : resIds
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	// 提交数据到后台 ajax方式提交到后台
	$.ajax({
		type : 'post',
		data : sendData,
		url : base.domain + "resource/deleteRes",
		dataType : 'json',
		success : function(data) {
			var param = eval("(" + data + ")");
			if (param.status.statusCode == "000000") {
				window.parent.createPopup("删除资源信息成功");
				// 页面更新启用状态
				$("#pagenum").val("1");
				queryResourceByPage();
				// var i = 0;
				// for (; i < checkedList.length; i++) {
				// $(checkedList[i]).remove();// 移除选中的行
				// }
				// var orginCount = $("#count").val();
				// $("#count").val(Number(orginCount) - i);
				// $.fenyeInit();// 分页信息
				// var length = $('#content').find('tr').length;
				// if (length < 1) {
				// $("#content").html("");
				// $(".useManage_article_button").css("display", "none");
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
