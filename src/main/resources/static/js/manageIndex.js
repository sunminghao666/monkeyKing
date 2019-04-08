var li = null;// 手风琴
var stArry = [];// 空的数组 保存标签页路径
var iWidth = 150;
var oNav = null;
var nWidth = 0;
$(function() {
	$("#logo img").attr("src", base.domain + "img/login_logo.png");
	document.title = "xxxx台管理系统";
	var screenhright = $(window).height() - $("#header").height() - 20;
	$("#left").css("height", (screenhright + "px"));
	$("#right").css("height", ((screenhright - 50) + "px"));
	$("#showPage").css("height", ((screenhright - 50) + "px"));
	$("#popup_img img").attr("src", "images/trumpet.png");
	// $(".nvConter").width($(".nvConter").width() - 82);
	$("#popup_inu img").attr("src", "images/btn_white.png");
	$("#sxpopup_img img").attr("src", "images/trumpet.png");
	$("#sxpopup_inu img").attr("src", "images/btn_white.png");
	oNav = document.getElementById("nav");
	nWidth = $(".nvConter").width();
	$("#nav").on("click", "li", function(e) {
		$("#nav li").removeClass("active");// 清除标签选中标识
		var classname = $(this).attr("class");
		$(this).addClass('active');
		$("iframe").hide();// 隐藏所有已存在tab标签
		$("iframe." + classname).show();

	});
	$("#nav").on("click", "span", function(e) {
		if ($(this).parent().hasClass("active")) {
			var preNode = $(this).parent().removeClass("active").prev();// 当前标签为选中，删除时获取前一个标签
			// 显示
			var prename = $(preNode).attr("class");
			$(preNode).addClass('active');
			oscrollTo();// 切换标签
			$("iframe").hide();// 隐藏所有已存在tab标签
			$("iframe." + prename).show();
		}
		var classname = $(this).parent().attr("class");
		var cls = classname.substring(3);

		for (var j = 0; j < stArry.length; j++) {
			if (stArry[j][1] == cls) {
				stArry.splice(j, 1);
				break;
			}
		}
		$("." + classname).remove();
		return false;
	});
	$("#left").scrollBar({
		barWidth : 5,
		position : "y"
	});
	// 滑动标签
	$(".nvpre").on("click", function() {
		var butscroll = parseInt(getStyle(oNav, "left")) + iWidth;
		oscroll(butscroll);
	});
	$(".nvnext").on(
			"click",
			function() {
				var last = $("#nav li").last().width()
						+ $("#nav li").last().position().left;
				var oleft = parseInt(getStyle(oNav, "left"));
				var llst = nWidth - oleft;
				var butscroll = oleft - iWidth;
				if (llst > last) {
					butscroll = oleft;
				}
				oscroll(butscroll);
			});
	showtab("blank.html", "欢迎页");
});
// 跳转
function oscrollTo() {
	var oLeft = $("#nav li.active").position().left;// 激活的左边距离
	var oRight = $("#nav li.active").width() + oLeft;// 激活的右边距离

	var nLeft = parseInt(getStyle(oNav, "left"));// 容器左边位置
	var nRigth = nWidth - nLeft;// 容器右表的位置
	if (oLeft < -nLeft) {// 激活在图像左边，需要左移
		var n = -Math.floor(oLeft / iWidth) * iWidth;
		sMove(oNav, 'left', n);
	} else if (oRight > nRigth) {
		var n = -Math.ceil((oRight - nWidth) / iWidth) * iWidth;
		sMove(oNav, 'left', n);
	}
}

// 滚动事件
function oscroll(l) {
	var last = $("#nav li").last().width()
			+ $("#nav li").last().position().left;
	if (l > 0 || last < nWidth) {
		l = 0;
	}
	sMove(oNav, 'left', l);
}
// 获取
function getStyle(obj, attr) {
	return obj.currentStyle ? obj.currentStyle[attr] : getComputedStyle(obj,
			false)[attr];
}
function sMove(obj, attr, iT) {
	clearInterval(obj.timer);
	obj.timer = setInterval(function() {
		dMove(obj, attr, iT);
	}, 30);
}
function dMove(obj, attr, iT) {
	var iCur = 0;
	iCur = parseInt(getStyle(obj, attr));
	var iS = (iT - iCur) / 5;
	iS = iS > 0 ? Math.ceil(iS) : Math.floor(iS);
	if (iCur == iT) {
		clearInterval(obj.timer);
	} else {
		obj.style[attr] = iCur + iS + 'px';
	}
}
/** 显示标签页 */
function showtab(path, spname) {

	// 判断打开的标签是否已经存在
	var czflag = true;// 标签默认不存在，新增一个iframe，存在就直接打开
	for (var i = 0; i < stArry.length; i++) {
		if (stArry[i][0] == path) {
			czflag = false;
			break;
		}
	}
	$("iframe").hide();// 隐藏所有已存在tab标签
	$("#nav li").removeClass("active");// 清除标签选中标识
	if (czflag) {
		// 创建iframe 打开页面
		var ifra = document.createElement("iframe");
		ifra.src = path;
		var idtm = new Date().getTime();// 设置iframeid
		ifra.id = "id" + idtm;
		ifra.className = "tab" + idtm;
		$("#right").append(ifra);

		var nli = document.createElement("li");
		nli.className = "active tab" + idtm;
		nli.role = "presentation"
		nli.innerHTML = '<a href="#">' + spname + '</a><span>&times;</span>';
		$("#nav").append(nli);

		stArry.push([ path, idtm ]);// 数据表存标签的路径 与名称
	} else {
		$("#id" + stArry[i][1]).attr("src", path).show();
		$("li.tab" + stArry[i][1]).addClass("active");
	}
	oscrollTo();// 切换标签
}

function nvclick(obj) {
	changeImgStyle(obj);
	changebackcolor(obj);
	var path = $(obj).find('input').val();
	var spname = $(obj).find('span').html();
	showtab(path, spname);
}

// 修改密码
function uppwd() {
	var loginUserId = $("#loginUserId").val();
	loginUserId = UrlEncode(loginUserId);
	showtab("manage/changePassword.jsp?parm=" + loginUserId, "修改密码");
}
// 退出
function logout() {
	window.location.href = base.domain + "login.html";
}
// 登录
function nowLogin() {
	window.location.href = base.domain + "login.html";
}
function popup() {
	$("#cxpopup").show();
}
// 关闭车险消息
function cxNoticeClose() {
	$("#cxpopup").hide();
}
// 关闭寿险消息
function sxNoticeClose() {
	$("#sxpopup").hide();
}

/**
 * 控制子菜单的显示与隐藏
 * 
 * @param obj
 *            当前对象
 */
function changeStyle(obj, n) {
	// console.log(n);
	/* 一级菜单 */
	if (n == 1) {
		if (li != null) {
			li.find('li').hide(300);
		}
		var ul = $(obj).next("ul");
		if (ul.find("li").html() != null) {
			li = $(obj).next("ul");
			if (ul.children('li').css("display") == "none") {
				ul.children('li').show(300);
			} else {
				ul.find('li').hide(300);
			}
		}
		changeImgStyle(obj);
	} else {/* 二级菜单* */
		var ul = $(obj).next("ul");
		if (ul.find("li").html() != null) {
			if (ul.find('li').css("display") == "none") {
				ul.find('li').show(300);
			} else {
				ul.find('li').hide(300);
			}
		}
	}
}

/**
 * 改变图片样式为选中状态或未选中off<-->on
 * 
 * @param obj
 *            当前对象
 */
function changeImgStyle(obj) {
	var temp = "";
	// var $img = $('ul').eq(0).find('img');
	var $img = $('ul').eq(0).find('img');
	$img.each(function(i) {
		temp = i;
		var img = $img.eq(i);
		var src = img.attr('src');
		var index = src.indexOf('on');

		if (index > 0) {
			var newSrc = src.substring(0, index) + 'off.png';
			img.attr('src', newSrc);
			// $(obj).next("ul").find('li').eq(temp).css("background","white");
		}
	});
	var oldImgSrc = $(obj).find('img').attr('src');
	// var oldbackColor = $(obj).next("ul").find("li");
	var newImgSrc = "";
	index = oldImgSrc.indexOf('off');
	newImgSrc = oldImgSrc.substring(0, index) + 'on.png';
	$(obj).find('img').attr('src', newImgSrc);
}
/**
 * 改变背景颜色
 * 
 * @param obj
 *            当前对象
 */
function changebackcolor(obj) {
	$(".nav").css("background", "#fff");
	$(".nav").css("color", "#444444");
	$(obj).css("background", "#1b6bb8");
	$(obj).css("color", "#fff");
}
function switchToBlank() {
	showtab("manage/blank.jsp", "主页");
};
