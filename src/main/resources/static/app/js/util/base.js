var base = {
	domain : window.location.protocol + "//" + window.location.host + "/youyue/",
	url : window.location.protocol + "//" + window.location.host + "/youyue/",
	keyStr : "t171420100302rsa",
	ivStr : "t171420100302rsa",
	imagePath : "../../imgs/"
};
if (typeof tit !== 'object') {
	var tit = {};
}
var wx_userName = 'monkey';
var method;
document.write("<script type='text/javascript' src='" + base.url
		+ "app/js/util/rollups/aes.js' ><\/script>");
document.write("<script type='text/javascript' src='" + base.url
		+ "app/js/util/components/pad-iso10126-min.js' ><\/script>");
function URLencodeForBase64(a) {
	return escape(a).replace(/\+/g, "%2B").replace(/\"/g, "%22").replace(/\'/g,
			"%27").replace(/\//g, "%2F")
}
function aesEncrypt(f) {
	var b = CryptoJS.enc.Utf8.parse(f);
	var d = CryptoJS.enc.Utf8.parse(base.keyStr);
	var c = CryptoJS.enc.Utf8.parse(base.ivStr);
	var e = CryptoJS.AES.encrypt(b, d, {
		iv : c,
		mode : CryptoJS.mode.CBC
	});
	var a = CryptoJS.enc.Base64.stringify(e.ciphertext);
	a = URLencodeForBase64(a);
	return a
};
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null) {
		return unescape(r[2]); // 返回参数值
	} else {
		return null;
	}
}
function getUrlQueryString(names, urls) {
	urls = urls || window.location.href;
	urls && urls.indexOf("?") > -1 ? urls = urls
			.substring(urls.indexOf("?") + 1) : "";
	var reg = new RegExp("(^|&)" + names + "=([^&]*)(&|$)", "i");
	var r = urls ? urls.match(reg) : window.location.search.substr(1)
			.match(reg);
	if (r != null && r[2] != "")
		return decodeURI(r[2]);
	return null;
};
function shareGetConfig(fuc) {
	method = fuc;
	var obj = {
		"username" : wx_userName,
		"url" : encodeURIComponent(location.href.split('#')[0])
	};
	var sendData = {
		request : obj
	};
	sendData = "jsonKey=" + aesEncrypt(JSON.stringify(sendData));
	$.ajax({
		async : false,
		type : "post",
		url : base.domain + "/wxBasic/getConfig.do",
		data : sendData,
		dataType : 'json',
		success : function(param) {
			if (param != "") {
				if (param.statusCode == '000000') {
					wx
							.config({
								debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
								appId : param.returns.config.appId, // 必填，公众号的唯一标识
								timestamp : param.returns.config.timestamp, // 必填，生成签名的时间戳
								nonceStr : param.returns.config.nonceStr, // 必填，生成签名的随机串
								signature : param.returns.config.signature,// 必填，签名，见附录1
								"url" : encodeURIComponent(location.href
										.split('#')[0]),
								jsApiList : [ 'chooseImage',
										'onMenuShareTimeline',
										'onMenuShareAppMessage',
										'onMenuShareQQ', 'uploadImage',
										'hideMenuItems',
										'hideAllNonBaseMenuItem', 'scanQRCode',
										'closeWindow' ]
							// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
							});
					wx.error(function(res) {
						// alert("wxError--" + JSON.stringify(res));
						// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
					});
					wx.ready(function() {
						wx.checkJsApi({
							jsApiList : [ 'chooseImage', 'onMenuShareTimeline',
									'onMenuShareAppMessage', 'onMenuShareQQ',
									'uploadImage', 'hideMenuItems',
									'hideAllNonBaseMenuItem', 'scanQRCode',
									'closeWindow' ], // 需要检测的JS接口列表，所有JS接口列表见附录2,
							success : function(res) {
								// alert("wxReady---" + res);
								wx.hideAllNonBaseMenuItem();
								// （此处代码复制到需要分享的页面js中，注意在初始化结束之后调用，可以设置成页面加载完成后或延时执行）
								method();
								/*
								 * wx.hideMenuItems({ menuList :
								 * ['menuItem:share:timeline'] //
								 * 要隐藏的菜单项，只能隐藏“传播类”和“保护类”按钮，所有menu项见附录3 });
								 */
								// 以键值对的形式返回，可用的api值true，不可用为false
								// 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
							}
						});

					});
				} else {
					// alert("公众号初始化失败！");
				}
			}
		},
		error : function() {
			modelAlert("请求服务超时");
		}
	});
};
/**
 * 页面加载时的遮罩 使用方法： $.ajaxPrevent(); author: maweiwei tel：13564515264
 */
$.ajaxPrevent = function() {
	// 创建遮罩
	var ajaxPrevent = "";
	ajaxPrevent += "<div class='ajax_prevent' style='position: fixed;width: 100%;height: 100%;top: 0;"
			+ "left: 0;z-index: 1000;background:rgba(0,0,0,0.5)'></div>";
	$("body").append(ajaxPrevent);
};
/**
 * ajax请求方法
 */
$.toAjaxs = function(url, dataList, callBack, asyn) {
	if (typeof (asyn) == "undefined") {
		asyn = true;
	} else if (!asyn) {
		asyn = false;
	} else {
		asyn = true;
	}

	var requestData = {};
	requestData.request = dataList;
	var requestJson = aesEncrypt(JSON.stringify(requestData));
	$.ajax({
		url : url,
		type : 'POST',
		data : "jsonKey=" + requestJson,
		dataType : "json",
		timeout : 60000,
		success : function(data) {
			if ($.isNull(callBack)) {
				$(".ajax_prevent").remove();
			} else {
				$(".ajax_prevent").remove();
				callBack(data);
			}
		},
		error : function(data) {
			$(".ajax_prevent").remove();
			modelAlert("网络异常，加载失败。请稍后再试....");
		},
		complete : function(data) {
		},
		beforeSend : function(xhr) {
			$.ajaxPrevent();
		},
		async : asyn,
	});
};
/* 确定弹出框 */
function modelAlert(message, btn, method, title) {
	var str = '';
	str += '<div class="yl-pop-confirm"><div class="yl-pop-alert">';
	str += '<div class="yl-pop-title">';
	if ($.isNull(title)) {
		str += '温馨提示';
	} else {
		str += title;
	}
	str += '</div><div class="yl-pop-close"><img src="' + base.imagePath
			+ 'X.png"></div>';
	str += '<div class="clear"></div>';
	str += '<div class="yl-pop-message">' + message;
	str += '</div>';
	str += '<div class="yl-pop-btncont">';
	str += '<div id="yl-alert-btn" class="yl-pop-btn yl-blue-btn">';
	if ($.isNull(btn)) {
		str += '确定';
	} else {
		str += btn;
	}
	str += '</div>';
	str += '</div>';
	str += '</div>';
	str += '</div>';

	$("body").append(str);
	/* 关闭弹出框 */
	$(".yl-pop-close").unbind("tap").bind("tap", function() {
		$(this).parent().parent().remove();
	});
	/* 点击确定按钮 */
	$("#yl-alert-btn").unbind("tap").bind("tap", function() {
		if (!$.isNull(method)) {
			method();
			$(this).parent().parent().parent().remove();
		} else {
			$(this).parent().parent().parent().remove();
		}
	});
};
/* 确定选择框 */
function modelConfirm(message, btn1, btn2, method1, method2, title) {
	var str = '';
	str += '<div class="yl-pop-confirm"><div class="yl-pop-alert">';
	str += '<div class="yl-pop-title">';
	if ($.isNull(title)) {
		str += '温馨提示';
	} else {
		str += title;
	}
	str += '</div><div class="yl-pop-close"><img src="' + base.imagePath
			+ 'X.png"></div>';
	str += '<div class="clear"></div>';
	str += '<div class="yl-pop-message">' + message;
	str += '</div>';
	str += '<div class="yl-pop-btncont">';
	str += '<div id="yl-confirm-btn" class="yl-pop-btn yl-blue-btn fl">';
	if ($.isNull(btn1)) {
		str += '确定';
	} else {
		str += btn1;
	}
	str += '</div>';
	str += '<div id="yl-cancle-btn" class="yl-pop-btn yl-grey-btn fr">';
	if ($.isNull(btn2)) {
		str += '取消';
	} else {
		str += btn2;
	}
	str += '</div>';
	str += '</div>';
	str += '</div>';
	str += '</div>';

	$("body").append(str);
	/* 关闭弹出框 */
	$(".yl-pop-close").unbind("tap").bind("tap", function() {
		$(this).parent().parent().remove();
	});
	/* 点击确定按钮 */
	$("#yl-confirm-btn").unbind("tap").bind("tap", function() {
		if (!$.isNull(method1)) {
			method1();
			$(this).parent().parent().parent().remove();
		} else {
			$(this).parent().parent().parent().remove();
		}
	});
	/* 点击取消按钮 */
	$("#yl-cancle-btn").unbind("tap").bind("tap", function() {
		if (!$.isNull(method2)) {
			method2();
			$(this).parent().parent().parent().remove();
		} else {
			$(this).parent().parent().parent().remove();
		}
	});
};
/*******************************************************************************
 * isChinese : 判断是否为中文 alert(tit.regExp.isChinese("我是中文")) isName ： 判断是否为姓名
 * isEmail : 判断是否为Email地址 alert(tit.regExp.isEmail("lancer07@139.com")) isMobile :
 * 判断是否为手机号 alert(tit.regExp.isMobile("13661234567")) isTel : 判断是否为固定电话号
 * alert(tit.regExp.isTel("021-56565656")) isCardNum : 判断是否只有字母和数字
 * alert(tit.regExp.isCardNum("223200")) isMoney : 判断是否只有点和数字
 * alert(tit.regExp.isMoney("2.1")) isNumber : 判断是否只有数字
 * alert(tit.regExp.isNumber("2.1")) isNumAndpoint ： 判断是否只有点和数字
 * alert(tit.regExp.isMoney("2.1"))
 ******************************************************************************/
if (typeof tit.regExp !== 'object') {
	tit.regExp = {
		isChinese : function(str) {
			var reg = /^[\u4E00-\u9FFF]+$/;
			return reg.test(str);
		},
		isName : function(str) {
			var reg = /^[\u4e00-\u9fa5\·]{2,20}$/;
			return reg.test(str);
		},
		isEmail : function(str) {
			var reg = /^[A-Za-z0-9]+([._\\-]*[a-z0-9])*[A-Za-z0-9_]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}[a-z0-9]+$/;// 开始必须是一个或者多个单词字符或者是-，加上@，然后又是一个或者多个单词字符或者是-。然后是点“.”和单词字符和-的组合，可以有一个或者多个组合,邮箱不能以
			// - _
			// .以及其它特殊字符开头和结束,邮箱域名结尾为2~5个字母，比如cn、com、name
			return reg.test(str);
		},
		isMobile : function(str) {
			var reg = /^0*(13|14|15|16|17|18|19)\d{9}$/;
			return reg.test(str);
		},
		isTel : function(str) {
			var reg = /^\d{3,4}-\d{7,8}(-\d{3,4})?$/;
			return reg.test(str);
		},
		isCardNum : function(str) {
			var reg = /^[A-Za-z0-9]+$/;
			return reg.test(str);
		},
		isMoney : function(str) {
			var reg = /^(\-)?(([1-9]\d*)|[0-9])(\.\d+)?$/;
			return reg.test(str);
		},
		isNumber : function(str) {
			var reg = /^[0-9]+$/;
			return reg.test(str);
		},
		isNumAndpoint : function(str) {
			var reg = /^[0-9]+\.*[0-9]*$/
			return reg.test(str);
		},
		isfloattwo : function(str) {// 最多保留两位小数的字符串
			var reg = /^[0-9]+\.?[0-9]{0,2}$/
			return reg.test(str);
		}
	};
}
// 解决placeholder的line-height问题
$.replacePlaceholder = function(domObj, placeholderStr) {
	if (placeholderStr == "请输入密码") {
		domObj.attr("type", "text");
	}
	$(domObj).unbind().bind({
		focus : function() {
			if ($(domObj).val() == placeholderStr) {
				$(domObj).val("").css("color", "#585858");
				if (placeholderStr == "请输入密码") {
					domObj.attr("type", "password");
				}
			}
		},
		blur : function() {
			if ($(domObj).val() == "") {
				$(domObj).val(placeholderStr).css("color", "#ccc");
				if (placeholderStr == "请输入密码") {
					domObj.attr("type", "text");
				}
			}
		}
	});
};
// $.checkIdCard : 验证身份证
$.checkIdCard = function(idcard) {
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
		65 : "新疆"
	};
	var idcard, Y, JYM;
	var S, M;
	var idcard_array = new Array();
	idcard_array = idcard.split("");
	if (area[parseInt(idcard.substr(0, 2))] == null) {
		return 3
	}
	;
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
};
// 获取特殊字符的个数
function getCount(str1, str2) {
	var regstr = str1.split(str2)
	return regstr.length - 1;
};
/* 判断是否为空 */
$.isNull = function(str) {
	if (str == null || typeof (str) == "undefined" || str == "null"
			|| str == "") {
		return true;
	}
	return false;
};
/**
 * 得到地址栏参数 使用方法：getUrlQueryString(names, urls);
 * 
 * @param names
 *            参数名称
 * @param urls
 *            从指定的urls获取参数
 * @returns string author: maweiwei
 */
function getUrlQueryString(names, urls) {
	urls = urls || window.location.href;
	urls && urls.indexOf("?") > -1 ? urls = urls
			.substring(urls.indexOf("?") + 1) : "";
	var reg = new RegExp("(^|&)" + names + "=([^&]*)(&|$)", "i");
	var r = urls ? urls.match(reg) : window.location.search.substr(1)
			.match(reg);
	if (r != null && r[2] != "")
		return unescape(r[2]);
	return null;
};
/** ************************************模块分割*************************************** */
// 加密
function UrlEncode(str) {
	var b = new Base64();
	str = b.encode(str);
	return str;
}
// 解密
function UrlDecode(str) {
	var b = new Base64();
	str = b.decode(str);
	return str;
}
function trim(str) { // 删除左右两端的空格
	return str.replace(/(^\s*)|(\s*$)/g, "");
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
		for (var n = 0; n < string.length; n++) {
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
};
// 把时间戳转化为字符串时间格式
function formatDate(da) {
	da = new Date(da);
	var year = da.getFullYear();
	var month = da.getMonth() + 1;
	var date = da.getDate();
	var hours = da.getHours();
	var minutes = da.getMinutes();
	var seconds = da.getSeconds();
	return (year + '-' + month + '-' + date + ' ' + hours + ':' + minutes + ':' + seconds);
}
/**
 * 判读是否是微信客户端
 * 
 */
function is_weixin() {
	// 对浏览器的UserAgent进行正则匹配，不含有微信独有标识的则为其他浏览器
	var ua = navigator.userAgent.toLowerCase();
	var isWeixin = ua.indexOf('micromessenger') != -1;
	if (ua.match(/MicroMessenger/i) != "micromessenger") {
		return true;
	} else {
		document.head.innerHTML = '<title>抱歉，出错了</title><meta charset="utf-8"><meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0"><link rel="stylesheet" type="text/css" href="../../css/userInfo/error.css">';
		document.body.innerHTML = '<div class="weui_msg"><div class="weui_icon_area"><i class="weui_icon_info weui_icon_msg"></i></div><div class="weui_text_area"><h4 class="weui_msg_title">请在微信客户端打开链接</h4></div></div>';
		return false;
	}
};
/* 时间控件，日期 */
/**
 * 打开日期控件:默认当前日期 使用方法：openDataNowDate(id);
 */

function openDate(id, type) {
	var pickers = {};
	var optionsJson = '{"type":"date","beginYear":2017}';
	var options = JSON.parse(optionsJson);
	var id = id;
	var result = document.getElementById(id);
	pickers[id] = pickers[id] || new mui.DtPicker(options);
	pickers[id].show(function(rs) {
		if (type == "1") {
			result.value = rs.text;
		} else {
			result.innerText = rs.text;
		}
		pickers[id].dispose();// 释放组件资源
	});
}
// 初始化选择框
function initSelect(data, id, method, type) {
	(function($, doc) {
		var PopPicker = new $.PopPicker();
		PopPicker.setData(data);
		var PopResult = doc.getElementById(id);
		PopPicker.show(function(items) {
			if (type == "1") {
				PopResult.value = items[0].text;
			} else {
				PopResult.innerText = items[0].text;
			}
			PopResult.title = items[0].value;
			if (method != "" && method != undefined && method != null) {
				method(items[0].value);
			}
		});
	})(mui, document);
};
function getInervalHour(startDate, endDate) {
	var ms = endDate.getTime() - startDate.getTime();
	if (ms < 0)
		return 0;
	return Math.floor(ms / 1000 / 60 / 60);
}
function convertDateFromString(dateString) {
	var str = dateString.toString();
	str = str.replace("/-/g", "/");
	var oDate1 = new Date(str);
	return oDate1;
}
function getDic(paramList, key) {
	var value = "";
	for (var i = 0; i < paramList.length; i++) {
		if (paramList[i].value == key) {
			value = paramList[i].text;
		}
	}
	return value;
}
var checkStrLengths = function(str, maxLength) {
	var maxLength = maxLength;
	var result = 0;
	if (str && str.length > maxLength) {
		result = maxLength;
	} else {
		result = str.length;
	}
	return result;
}