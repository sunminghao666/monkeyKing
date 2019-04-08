/**
 * 验证手机号
 * 
 * @param phoneNo
 *            手机号
 * @returns false-不通过
 */
function chenkMobile(phoneNo) {
	var reg = /^0*(13|14|15|17|18)\d{9}$/;
	return reg.test(phoneNo);
}
//判断输入框为空时
function isNull(str) {
	if (str == null || typeof (str) == "undefined" || str == "null"
			|| str == "") {
		return true;
	}
	return false;
};
/**
 * 验证任意长度字母数字下划线,开头不能是下划线
 * 验证用户密码："^[a-zA-Z]\w{5,17}$"正确格式为：以字母开头，长度在6~18之间，只能包含字符、数字和下划线
 * 
 * @param String
 * @returns false-不通过
 */
function chenkAlphanum(string) {
	var reg = /^[a-zA-Z0-9]\w*$/;
	return reg.test(string);
}

/**
 * 验证任意长度字母数字
 * 
 * @param String
 * @returns false-不通过
 */
function chenkAlphaAndNum(string) {
	var reg = /^[a-zA-Z0-9]*$/;
	return reg.test(string);
}

/**
 * 验证身份证号
 * 
 * @param idcard
 *            身份证号
 * @returns {Number}
 */
function checkIdCard(idcard) {
	var area = {
		11 : "北京",
		12 : "天津",
		13 : "河北",
		14 : "山西",
		15 : "内蒙古",
		21 : "辽宁",
		22 : "吉林",
		23 : "黑龙江",
		31 : "上海",
		32 : "江苏",
		33 : "浙江",
		34 : "安徽",
		35 : "福建",
		36 : "江西",
		37 : "山东",
		41 : "河南",
		42 : "湖北",
		43 : "湖南",
		44 : "广东",
		45 : "广西",
		46 : "海南",
		50 : "重庆",
		51 : "四川",
		52 : "贵州",
		53 : "云南",
		54 : "西藏",
		61 : "陕西",
		62 : "甘肃",
		63 : "青海",
		64 : "宁夏",
		65 : "新疆",
		71 : "台湾",
		81 : "香港",
		82 : "澳门",
		91 : "国外"
	};
	var Y, JYM;
	var S, M;
	var idcard_array = new Array();
	idcard_array = idcard.split("");
	if (area[parseInt(idcard.substr(0, 2))] == null) {
		return 3;
	}
	switch (idcard.length) {
	case 15:
		if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0
				|| ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard
						.substr(6, 2)) + 1900) % 4 == 0)) {
			ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;// 测试出生日期的合法性
		} else {
			ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;// 测试出生日期的合法性
		}
		if (ereg.test(idcard)) {
			return 0;
		} else {
			return 2;
			break;
		}
	case 18:
		if (parseInt(idcard.substr(6, 4)) % 4 == 0
				|| (parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard
						.substr(6, 4)) % 4 == 0)) {
			ereg = /^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;// 闰年出生日期的合法性正则表达式
		} else {
			ereg = /^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;// 平年出生日期的合法性正则表达式
		}
		if (ereg.test(idcard)) {
			S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
					+ (parseInt(idcard_array[1]) + parseInt(idcard_array[11]))
					* 9
					+ (parseInt(idcard_array[2]) + parseInt(idcard_array[12]))
					* 10
					+ (parseInt(idcard_array[3]) + parseInt(idcard_array[13]))
					* 5
					+ (parseInt(idcard_array[4]) + parseInt(idcard_array[14]))
					* 8
					+ (parseInt(idcard_array[5]) + parseInt(idcard_array[15]))
					* 4
					+ (parseInt(idcard_array[6]) + parseInt(idcard_array[16]))
					* 2 + parseInt(idcard_array[7]) * 1
					+ parseInt(idcard_array[8]) * 6 + parseInt(idcard_array[9])
					* 3;
			Y = S % 11;
			M = "F";
			JYM = "10X98765432";
			M = JYM.substr(Y, 1);
			if (M == idcard_array[17]) {
				var iddate = idcard.substr(6, 8);
				var idyear = parseInt(iddate.substr(0, 4));
				var idmonth = parseInt(iddate.substr(4, 2));
				var idday = parseInt(iddate.substr(6, 2));
				var now = new Date();
				var year = now.getFullYear(); // 年
				var month = now.getMonth() + 1; // 月
				var day = now.getDate();
				if (idyear > year) {
					return 2;
				} else if (idyear == year) {
					if (idmonth > month) {
						return 2;
					} else if (idmonth == month) {
						if (idday > day) {
							return 2;
						} else {
							return 0;
						}
					} else {
						return 0;
					}
				} else {
					return 0;
				}
			} else {
				return 3;
			}
		} else {
			return 2;
			break;
		}
	default:
		return 1;
		break;
	}
}

/**
 * 控制字符长度
 * 
 * @param obj
 * @param limit
 */
function limit(obj, limit) {
	var val = obj.value;
	if (len(val) > limit) {
		val = val.substring(0, limit);
		while (len(val) > limit) {
			val = val.substring(0, val.length - 1);
		}
		;
		obj.value = val;
	}
}

/**
 * 获取字符串的字节长度
 * 
 * @param s
 * @returns
 */
function len(s) {
	s = String(s);
	return s.length + (s.match(/[^\x00-\xff]/g) || "").length;// 加上匹配到的全角字符长度
}

/**
 * 自定义信息提示框（"确定"按钮）
 * 
 * @param message
 *            提示信息学
 */
function createPopup(message, method) {

	var newPopup = document.createElement("div"); // 弹出对话框
	var newMask = document.createElement("div"); // 遮罩层，用来屏蔽灰掉背部界面
	var newBtn = document.createElement("input"); // 弹出对话框中按钮
	var inner;
	// 弹出对话框中要呈现的内容
	inner = '<h1 style="font-size:14px; font-family: 微软雅黑; color:#fff; margin-left:10px; margin-top:-22px">提示</h1>';
	if (message.length > 40) {
		inner += '<div style="font-size:13px; text-align:center; margin-top:22px; padding-left:10px; padding-right:10px; height: 56px; overflow-y: auto; overflow-x: hidden;"><span style="font-family: 宋体;"> '
				+ message + '</span></div>';
	} else {
		inner += '<div style="font-size:13px; text-align:center; margin-top:38px; padding-left:10px; padding-right:10px;"><span style="font-family: 宋体;"> '
				+ message + '</span></div>';
	}

	// 创建遮罩层
	if (!document.getElementById("mask") && 1) {
		newMask.id = "mask";
		newMask.style.position = "absolute";
		newMask.style.zIndex = "9996";
		newMask.style.width = "100%";
		newMask.style.height = Math.max(document.body.scrollHeight,
				document.documentElement.scrollHeight)
				+ "px";
		newMask.style.top = "0px";
		newMask.style.left = "0px";
		newMask.style.background = "gray";
		newMask.style.opacity = "0.5";
		newMask.style.filter = "Alpha(opacity=50)";
		document.body.appendChild(newMask);
	}
	// 创建弹出层
	if (!document.getElementById("promptID")) {
		newPopup.id = "promptID";
		newPopup.style.position = "fixed";
		newPopup.style.width = "300px";
		newPopup.style.height = "150px";
		newPopup.style.zIndex = "9997";
		newPopup.style.top = parseInt(window.screen.height * 0.28) + "px";
		newPopup.style.left = parseInt(window.screen.width * 0.3) + "px";
		newPopup.style.border = "1.5px solid #1b6bb8";
		newPopup.style.backgroundColor = "#FFFFFF";
		newPopup.style.borderRadius = "5px";
		newPopup.style.fontFamily = "宋体";
		newPopup.style.borderTop = "30px solid #1b6bb8";
		newPopup.innerHTML = inner;
		document.body.appendChild(newPopup);
	}
	// 创建弹出层中按钮
	if (!document.getElementById("btnSubID")) {
		newBtn.id = "btnSubID";
		newBtn.type = "button";
		newBtn.style.width = "80px";
		newBtn.style.height = "30px";
		newBtn.style.position = "absolute";
		newBtn.style.top = "65%";
		newBtn.style.left = "110px";
		newBtn.style.background = "#1b6bb8";
		newBtn.style.color = "#fff";
		newBtn.style.border = "0px";
		newBtn.style.borderRadius = "5px";
		newBtn.style.fontFamily = "宋体";
		newBtn.style.fontSize = "12px";
		newBtn.style.cursor = "pointer";
		newBtn.value = "确  定";
		newBtn.onmouseover = function() {
			newBtn.style.background = "#1b6bb8";
		};
		newBtn.onmouseout = function() {
			newBtn.style.background = "#076dce";
		};
		newBtn.onclick = function() {
			removeElement(document.getElementById("btnSubID"));
			removeElement(document.getElementById("promptID"));
			removeElement(document.getElementById("mask"));
			if (method != null && method != "" && method != undefined) {
				method();
			}
		};

		document.getElementById("promptID").appendChild(newBtn);

		// 回车键关闭
		document.getElementById("btnSubID").focus();
		document.getElementById("btnSubID").onkeydown = function(e) {
			if (!e)
				e = window.event;// 火狐中是 window.event
			if ((e.keyCode || e.which) == 13) {
				var obtnSearch = document.getElementById("btnSubID");
				// obtnSearch.focus();// 让另一个控件获得焦点就等于让文本输入框失去焦点
				obtnSearch.click();
			}
		};
	}
}

/**
 * 自定义信息提示框，有回调函数（“确定”，“取消”按钮）
 * 
 * @param message
 *            提示信息学
 * @param method
 *            回调函数
 */
function createPopupMethod(message, method1, method2) {

	var newPopup = document.createElement("div"); // 弹出对话框
	var newMask = document.createElement("div"); // 遮罩层，用来屏蔽灰掉背部界面
	var newBtn = document.createElement("input"); // 弹出对话框中按钮
	var newBtn2 = document.createElement("input"); // 弹出对话框中"取消"按钮
	var inner;
	// 弹出对话框中要呈现的内容
	inner = '<h1 style="font-size:14px; font-family: 微软雅黑; color:#fff; margin-left:10px; margin-top:-22px">提示</h1>';
	if (message.length > 40) {
		inner += '<div style="font-size:13px; text-align:center; margin-top:22px; padding-left:10px; padding-right:10px; height: 56px; overflow-y: auto; overflow-x: hidden;"><span style="font-family: 宋体;"> '
				+ message + '</span></div>';
	} else {
		inner += '<div style="font-size:13px; text-align:center; margin-top:36px; padding-left:10px; padding-right:10px;"><span style="font-family: 宋体;"> '
				+ message + '</span></div>';
	}
	// 创建遮罩层
	if (!document.getElementById("mask") && 1) {
		newMask.id = "mask";
		newMask.style.position = "absolute";
		newMask.style.zIndex = "9996";
		newMask.style.width = "100%";
		newMask.style.height = Math.max(document.body.scrollHeight,
				document.documentElement.scrollHeight)
				+ "px";
		newMask.style.top = "0px";
		newMask.style.left = "0px";
		newMask.style.background = "gray";
		newMask.style.opacity = "0.5";
		newMask.style.filter = "Alpha(opacity=50)";
		document.body.appendChild(newMask);
	}
	// 创建弹出层
	if (!document.getElementById("promptID")) {
		newPopup.id = "promptID";
		newPopup.style.position = "fixed";
		newPopup.style.width = "300px";
		newPopup.style.height = "150px";
		newPopup.style.zIndex = "9997";
		newPopup.style.top = parseInt(window.screen.height * 0.28) + "px";
		newPopup.style.left = parseInt(window.screen.width * 0.3) + "px";
		newPopup.style.border = "1.5px solid #1b6bb8";
		newPopup.style.backgroundColor = "#FFFFFF";
		newPopup.style.borderRadius = "5px";
		newPopup.style.fontFamily = "宋体";
		newPopup.style.borderTop = "30px solid #1b6bb8";
		newPopup.innerHTML = inner;
		document.body.appendChild(newPopup);
	}
	// 创建弹出层中"确定"按钮
	if (!document.getElementById("btnSubID")) {
		newBtn.id = "btnSubID";
		newBtn.type = "button";
		newBtn.style.width = "80px";
		newBtn.style.height = "30px";
		newBtn.style.position = "absolute";
		newBtn.style.top = "66%";
		newBtn.style.left = "50px";
		newBtn.style.background = "#1b6bb8";
		newBtn.style.color = "#fff";
		newBtn.style.border = "0px";
		newBtn.style.borderRadius = "5px";
		newBtn.style.fontFamily = "宋体";
		newBtn.style.fontSize = "12px";
		newBtn.style.cursor = "pointer";
		newBtn.value = "确  定";
		newBtn.onmouseover = function() {
			newBtn.style.background = "#1B3F88";
		};
		newBtn.onmouseout = function() {
			newBtn.style.background = "#1b6bb8";
		};
		newBtn.onclick = function() {
			removeElement(document.getElementById("btnSubID"));
			removeElement(document.getElementById("btnSubID2"));
			removeElement(document.getElementById("promptID"));
			removeElement(document.getElementById("mask"));
			method1();
		};
		document.getElementById("promptID").appendChild(newBtn);

		// 回车键关闭
		document.getElementById("btnSubID").focus();
		document.getElementById("btnSubID").onkeydown = function(e) {
			if (!e)
				e = window.event;// 火狐中是 window.event
			if ((e.keyCode || e.which) == 13) {
				var obtnSearch = document.getElementById("btnSubID");
				// obtnSearch.focus();// 让另一个控件获得焦点就等于让文本输入框失去焦点
				obtnSearch.click();
			}
		};
	}
	// 创建弹出层中"取消"按钮
	if (!document.getElementById("btnSubID2")) {
		newBtn2.id = "btnSubID2";
		newBtn2.type = "button";
		newBtn2.style.width = "80px";
		newBtn2.style.height = "30px";
		newBtn2.style.position = "absolute";
		newBtn2.style.top = "66%";
		newBtn2.style.left = "170px";
		newBtn2.style.background = "#1b6bb8";
		newBtn2.style.color = "#fff";
		newBtn2.style.border = "0px";
		newBtn2.style.borderRadius = "5px";
		newBtn2.style.fontFamily = "宋体";
		newBtn.style.fontSize = "12px";
		newBtn2.style.cursor = "pointer";
		newBtn2.value = "取  消";
		newBtn2.onmouseover = function() {
			newBtn2.style.background = "#1b6bb8";
		};
		newBtn2.onmouseout = function() {
			newBtn2.style.background = "#1B3F88";
		};
		newBtn2.onclick = function() {
			if (method2 != null && method2 != "" && method2 != undefined) {
				removeElement(document.getElementById("btnSubID"));
				removeElement(document.getElementById("btnSubID2"));
				removeElement(document.getElementById("promptID"));
				removeElement(document.getElementById("mask"));
				method2();
			} else {
				removeElement(document.getElementById("btnSubID"));
				removeElement(document.getElementById("btnSubID2"));
				removeElement(document.getElementById("promptID"));
				removeElement(document.getElementById("mask"));
			}
		};
		document.getElementById("promptID").appendChild(newBtn2);
	}
}

/**
 * 自定义数据加载遮罩保护层
 * 
 * @param method
 *            延迟执行的方法名
 * 
 */
function createLoadingMask(method) {
	// 判断是否存在加载数据中遮罩层
	// if (document.getElementById("loadingMask")) {
	// removeElement(document.getElementById("loadingPromptID"));
	// removeElement(document.getElementById("loadingMask"));
	// }
	// 判断是否存在加载数据中遮罩层
	if (document.getElementById("loadingMask") == null) {
		// alert("创建遮罩");
		var newPopup = document.createElement("div"); // 弹出对话框
		var newMask = document.createElement("div"); // 遮罩层，用来屏蔽灰掉背部界面
		// var newBtn = document.createElement("input"); // 弹出对话框中按钮
		var inner;
		// 弹出对话框中要呈现的内容
		inner = '<h1 style="font-size:14px; font-family: 微软雅黑; color:#fff; margin-left:10px; margin-top:-22px">提示</h1><div style="margin-top: -30px;width: 48%;float: right;"><img id="loadingBtnSubIDPre" src="'
				+ base.domain
				+ 'PC/images/btn_white.png" style="margin-left:124px; cursor:pointer;"/></div>';
		// inner += '<img src="../../images/loadingimg.gif"
		// style="text-align:center;
		// margin-top:10px; margin-left:120px"/>';
		inner += '<div style="font-size:13px; text-align:center; margin-top:40px"><img src="'
				+ base.domain
				+ 'PC/images/loadingimg.gif" style="text-align:center; margin-top:10px; "/><span> '
				+ "数据正在努力加载中" + '</span></div>';
		// 创建遮罩层
		if (!document.getElementById("loadingMask") && 1) {
			newMask.id = "loadingMask";
			newMask.style.position = "absolute";
			newMask.style.zIndex = "9998";
			newMask.style.width = "100%";
			newMask.style.height = Math.max(document.body.scrollHeight,
					document.documentElement.scrollHeight)
					+ "px";
			newMask.style.top = "0px";
			newMask.style.left = "0px";
			newMask.style.background = "gray";
			newMask.style.opacity = "0.5"; /* 支持Firefox, Safari, Chrome */
			newMask.style.filter = "Alpha(opacity=50)";/* 支持IE */
			document.body.appendChild(newMask);
		}
		// 创建弹出层
		if (!document.getElementById("loadingPromptID")) {
			newPopup.id = "loadingPromptID";
			newPopup.style.position = "fixed";
			newPopup.style.width = "300px";
			newPopup.style.height = "150px";
			newPopup.style.zIndex = "9999";
			newPopup.style.top = parseInt(window.screen.height * 0.28) + "px";
			newPopup.style.left = parseInt(window.screen.width * 0.4) + "px";
			newPopup.style.border = "1.5px solid #ca0101";
			newPopup.style.backgroundColor = "#FFFFFF";
			newPopup.style.borderRadius = "5px";
			newPopup.style.color = "#333";
			newPopup.style.fontFamily = "宋体";
			newPopup.style.borderTop = "30px solid #ca0101";
			newPopup.innerHTML = inner;
			document.body.appendChild(newPopup);
		}
		// // 创建弹出层中按钮
		// if (!document.getElementById("loadingBtnSubIDPre")) {
		// newBtn.id = "loadingBtnSubIDPre";
		// newBtn.type = "button";
		// newBtn.style.width = "50px";
		// newBtn.style.height = "30px";
		// newBtn.style.position = "absolute";
		// newBtn.style.top = "-30px";
		// newBtn.style.left = "248px";
		// newBtn.style.background = "#ca0101";
		// newBtn.style.color = "#fff";
		// newBtn.style.border = "0px";
		// newBtn.style.borderRadius = "5px";
		// newBtn.style.fontFamily = "宋体";
		// newBtn.style.fontSize = "12px";
		// newBtn.style.cursor = "pointer";
		// newBtn.value = "关闭";
		// newBtn.onmouseover = function() {
		// newBtn.style.background = "#AD0201";
		// };
		// newBtn.onmouseout = function() {
		// newBtn.style.background = "#ca0101";
		// };
		// newBtn.onclick = function() {
		// removeElement(document.getElementById("loadingBtnSubIDPre"));
		// removeElement(document.getElementById("loadingPromptID"));
		// removeElement(document.getElementById("loadingMask"));
		// };
		// // 回车键关闭
		// // document.onkeydown = function(e) {
		// // if (!e)
		// // e = window.event;// 火狐中是 window.event
		// // if ((e.keyCode || e.which) == 13) {
		// // var obtnSearch = document.getElementById("btnSubID");
		// // obtnSearch.focus();// 让另一个控件获得焦点就等于让文本输入框失去焦点
		// // obtnSearch.click();
		// // }
		// // };
		// document.getElementById("loadingPromptID").appendChild(newBtn);
		// }
		document.getElementById("loadingBtnSubIDPre").onclick = function() {
			// removeElement(document.getElementById("loadingBtnSubIDPre"));
			removeElement(document.getElementById("loadingPromptID"));
			removeElement(document.getElementById("loadingMask"));
		};
		setTimeout(method, 200);
	}

}

/**
 * 关闭自定义数据加载遮罩保护层
 * 
 * @param
 * 
 */
function closeLoadingMask() {
	// alert("关闭遮罩");
	// 判断是否存在加载数据中遮罩层
	if (document.getElementById("loadingMask") != null) {
		removeElement(document.getElementById("loadingPromptID"));
		removeElement(document.getElementById("loadingMask"));
	}
}

/**
 * 精确报价页面--自定义信息提示框 “保费计算中”
 * 
 * @param message
 *            提示信息学
 * @param method
 *            延迟执行的方法名
 */
function createPopupPreNotice(message, method) {
	// alert("111111");
	// alert(document.getElementById("maskPre"));
	if (document.getElementById("maskPre") == null) {
		// alert("2222");
		var newPopup = document.createElement("div"); // 弹出对话框
		var newMask = document.createElement("div"); // 遮罩层，用来屏蔽灰掉背部界面
		// var newBtn = document.createElement("input"); // 弹出对话框中按钮
		var inner;
		// 弹出对话框中要呈现的内容
		inner = '<h1 style="width:50%; font-size:14px; font-family: 微软雅黑; color:#fff; margin-left:10px; margin-top:-22px">提示</h1><div style="margin-top: -20px;width: 48%;float: right;"><img id="btnSubIDPre" src="images/btn_white.png" style="margin-left:124px; cursor:pointer;"/></div>';
		inner += '<div style="font-size:12px; padding-left:10px; padding-right:10px; text-align:center; margin-top:38px"><img src="images/loadingimg.gif" style="text-align:center; margin-top:1px;"/><span style="font-family: 宋体;"> '
				+ message + '</span><p id="ppid"></div>';
		// 创建遮罩层
		if (!document.getElementById("maskPre") && 1) {
			newMask.id = "maskPre";
			newMask.style.position = "absolute";
			newMask.style.zIndex = "9996";
			newMask.style.width = "100%";
			newMask.style.height = Math.max(document.body.scrollHeight,
					document.documentElement.scrollHeight)
					+ "px";
			newMask.style.top = "0px";
			newMask.style.left = "0px";
			newMask.style.background = "gray";
			newMask.style.opacity = "0.5";
			newMask.style.filter = "Alpha(opacity=50)";
			document.body.appendChild(newMask);
		}
		// 创建弹出层
		if (!document.getElementById("promptIDPre")) {
			newPopup.id = "promptIDPre";
			newPopup.style.position = "fixed";
			newPopup.style.width = "300px";
			newPopup.style.height = "150px";
			newPopup.style.zIndex = "9997";
			newPopup.style.top = parseInt(window.screen.height * 0.28) + "px";
			newPopup.style.left = parseInt(window.screen.width * 0.4) + "px";
			newPopup.style.border = "1.5px solid #1b6bb8";
			newPopup.style.backgroundColor = "#FFFFFF";
			newPopup.style.borderRadius = "5px";
			newPopup.style.fontFamily = "宋体";
			newPopup.style.borderTop = "30px solid #1b6bb8";
			newPopup.innerHTML = inner;
			document.body.appendChild(newPopup);
		}
		// // 创建弹出层中按钮
		// if (!document.getElementById("btnSubIDPre")) {
		// newBtn.id = "btnSubIDPre";
		// newBtn.type = "button";
		// newBtn.style.width = "50px";
		// newBtn.style.height = "30px";
		// newBtn.style.position = "absolute";
		// newBtn.style.top = "-30px";
		// newBtn.style.left = "248px";
		// newBtn.style.background = "#ca0101";
		// newBtn.style.color = "#fff";
		// newBtn.style.border = "0px";
		// newBtn.style.borderRadius = "5px";
		// newBtn.style.fontFamily = "宋体";
		// newBtn.style.fontSize = "12px";
		// newBtn.style.cursor = "pointer";
		// newBtn.value = "关闭";
		// newBtn.onmouseover = function() {
		// newBtn.style.background = "#AD0201";
		// };
		// newBtn.onmouseout = function() {
		// newBtn.style.background = "#ca0101";
		// };
		// newBtn.onclick = function() {
		// removeElement(document.getElementById("btnSubIDPre"));
		// removeElement(document.getElementById("promptIDPre"));
		// removeElement(document.getElementById("maskPre"));
		// };
		// // 回车键关闭
		// // document.onkeydown = function(e) {
		// // if (!e)
		// // e = window.event;// 火狐中是 window.event
		// // if ((e.keyCode || e.which) == 13) {
		// // var obtnSearch = document.getElementById("btnSubID");
		// // obtnSearch.focus();// 让另一个控件获得焦点就等于让文本输入框失去焦点
		// // obtnSearch.click();
		// // }
		// // };
		// document.getElementById("promptIDPre").appendChild(newBtn);
		$("#maskPre").show("fast");
		$("#promptIDPre").show("fast");
		document.getElementById("btnSubIDPre").onclick = function() {
			// removeElement(document.getElementById("btnSubIDPre"));
			removeElement(document.getElementById("promptIDPre"));
			removeElement(document.getElementById("maskPre"));
		};

		setTimeout(method, 200);
		// alert("33333");
		// }

	}
}

/**
 * 关闭精确报价页面--自定义信息提示框 “保费计算中”
 * 
 * @param
 * 
 */
function closePopupPreNotice() {
	// 判断是否存在加载数据中遮罩层
	if (document.getElementById("maskPre") != null) {
		// removeElement(document.getElementById("btnSubIDPre"));
		removeElement(document.getElementById("promptIDPre"));
		removeElement(document.getElementById("maskPre"));
	}
}

/**
 * 删除当前节点（解决ie浏览器不支持element.remove()来删除一个节点的方法）
 * 
 * @param _element
 */
function removeElement(_element) {
	var _parentElement = _element.parentNode;// 获取父节点
	if (_parentElement) {
		_parentElement.removeChild(_element);// 删除子节点
	}
}

/**
 * 替换所有的方法
 * 
 * @param str
 *            替换对象
 * @param sptr
 *            要被替换的字符串
 * @param sptr1
 *            要被替换成的字符串
 * @returns
 */
function ReplaceAll(str, sptr, sptr1) {
	while (str.indexOf(sptr) >= 0) {
		str = str.replace(sptr, sptr1);
	}
	return str;
}
/**
 * 设置下拉框的选中值
 * 
 * @param id
 *            select的id
 * @param value
 *            select的opetion的text
 */
/*
 * function jsselectvalue(id, value) { var options = $("#" + id + " option");
 * for ( var i = 0; i < options.length; i++) { if (options[i].text == value) {
 * options[i].selected = true; break; } } }
 */

/**
 * 设置下拉框的选中值
 * 
 * @param select
 *            select 对象
 * @param value
 *            select的opetion的text
 */
function jsselectvalue(select, value) {
	var options = select.find("option");
	for ( var i = 0; i < options.length; i++) {
		if (options[i].text == value) {
			options[i].selected = true;
			break;
		}
	}
}
/**
 * 设置下拉框的选中值
 * 
 * @param select
 *            select 对象
 * @param value
 *            select的opetion的key
 */
function jsselectkey(select, value) {
	var options = select.find("option");
	for ( var i = 0; i < options.length; i++) {
		if (options[i].key == value) {
			options[i].selected = true;
			break;
		}
	}
}
function UrlEncode(str) {
	var b = new Base64();
	str = b.encode(str);
	return str;
}
function UrlDecode(str) {
	var b = new Base64();
	str = b.decode(str);
	return str;
}

function Base64() {
	_keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
	this.encode = function(input) {
		var output = "";
		var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
		var i = 0;
		input = _utf8_encode(input);
		while (i < input.length) {
			chr1 = input.charCodeAt(i++);
			chr2 = input.charCodeAt(i++);
			chr3 = input.charCodeAt(i++);
			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;
			if (isNaN(chr2)) {
				enc3 = enc4 = 64;
			} else if (isNaN(chr3)) {
				enc4 = 64;
			}
			output = output + _keyStr.charAt(enc1) + _keyStr.charAt(enc2)
					+ _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
		}
		return output;
	}

	// public method for decoding
	this.decode = function(input) {
		var output = "";
		var chr1, chr2, chr3;
		var enc1, enc2, enc3, enc4;
		var i = 0;
		input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
		while (i < input.length) {
			enc1 = _keyStr.indexOf(input.charAt(i++));
			enc2 = _keyStr.indexOf(input.charAt(i++));
			enc3 = _keyStr.indexOf(input.charAt(i++));
			enc4 = _keyStr.indexOf(input.charAt(i++));
			chr1 = (enc1 << 2) | (enc2 >> 4);
			chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
			chr3 = ((enc3 & 3) << 6) | enc4;
			output = output + String.fromCharCode(chr1);
			if (enc3 != 64) {
				output = output + String.fromCharCode(chr2);
			}
			if (enc4 != 64) {
				output = output + String.fromCharCode(chr3);
			}
		}
		output = _utf8_decode(output);
		return output;
	}

	// private method for UTF-8 encoding
	_utf8_encode = function(string) {
		string = string.replace(/\r\n/g, "\n");
		var utftext = "";
		for ( var n = 0; n < string.length; n++) {
			var c = string.charCodeAt(n);
			if (c < 128) {
				utftext += String.fromCharCode(c);
			} else if ((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			} else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}

		}
		return utftext;
	}

	// private method for UTF-8 decoding
	_utf8_decode = function(utftext) {
		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;
		while (i < utftext.length) {
			c = utftext.charCodeAt(i);
			if (c < 128) {
				string += String.fromCharCode(c);
				i++;
			} else if ((c > 191) && (c < 224)) {
				c2 = utftext.charCodeAt(i + 1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			} else {
				c2 = utftext.charCodeAt(i + 1);
				c3 = utftext.charCodeAt(i + 2);
				string += String.fromCharCode(((c & 15) << 12)
						| ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}
		}
		return string;
	}
}

function str2asc(strstr) {
	return ("0" + strstr.charCodeAt(0).toString(16)).slice(-2);
}
function asc2str(ascasc) {
	return String.fromCharCode(ascasc);
}

/**
 * 跳转到首页
 */
function goIndex() {
	window.location.href = base.domain + "init/index.do";
}
/**
 * 回到历史
 * 
 * @param str
 */
function goBack(str) {
	window.history.back(str);
}
function back() {
	window.location.href = document.referrer;
}

/**
 * 取n年前的今天
 * 
 * @param n
 * @returns {Date}
 */
function gap(n) {
	var now = new Date;
	now.setFullYear(now.getFullYear() + n);
	return now;
}

function getNextDay(d) {
	d = +d + 1000 * 60 * 60 * 24;
	d = new Date(d);
	// return d;
	// 格式化
	return d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();

}

/**
 * 保存信息到cookie
 * 
 * @param name
 *            名称
 * @param value
 *            值
 * @param expires
 *            过期时间（单位：秒）
 */
function setCookie(name, value, expires) {
	// ,path,domain,secure
	var expSecs = expires * 1000;
	var expDate = new Date();
	expDate.setTime(expDate.getTime() + expSecs);
	var expString = ((expires == "-1") ? "" : (";expires=" + expDate
			.toGMTString()));
	// var pathString = ((path==null) ? "" : (";path="+path));
	// var domainString = ((domain==null) ? "" : (";domain="+domain));
	// var secureString = ((secure==true) ? ";secure" : "" );
	document.cookie = name + "=" + encodeURI(value) + expString;
	// + pathString + domainString + secureString;//encodeURI escape
}

/**
 * 使cookie信息过期
 * 
 * @param name
 *            名称
 * @param value
 *            值
 */
function delCookie(name, value) {
	// ,path,domain,secure
	var expSecs = -1 * 24 * 60 * 60 * 1000;
	var expDate = new Date();
	expDate.setTime(expDate.getTime() + expSecs);
	var expString = ";expires=" + expDate.toGMTString();
	document.cookie = name + "=" + encodeURI(value) + expString;
	// + pathString + domainString + secureString;//encodeURI escape
}

/**
 * 获取到cookie中信息
 * 
 * @param name
 *            名称
 */
function getCookie(name) {
	var result = null;
	var myCookie = document.cookie + ";";
	var searchName = name + "=";
	var startOfCookie = myCookie.indexOf(searchName);
	var endOfCookie;
	if (startOfCookie != -1) {
		startOfCookie += searchName.length;
		endOfCookie = myCookie.indexOf(";", startOfCookie);// 分隔符;
		if (endOfCookie == -1) {
			endOfCookie = mycookie.indexOf("&", startOfCookie);// 分隔符&
		}
		result = decodeURI(myCookie.substring(startOfCookie, endOfCookie));// unescape
		// decodeURI
	}
	if (result == null)
		result = "";
	return result;
}