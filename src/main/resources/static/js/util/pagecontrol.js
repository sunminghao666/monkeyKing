/********************************************************
* <p>Description: 国福家庭保险系统_分页的实现</p>
* <p>Project: pagecontrol.js </p>
* <p>Date: 2015-08-17 16:17:52 </p>
* <p>Author: </p>
* <p>Comment : </p>
*
*********************************************************/
// 界面内容数量$.fenye() 分页函数huanye(页码)换页函数
var count;
// 初始化页码数
var pagenum;
// 每页的item数量
var itemnum;

/*************************
* 分页初始化
* @param ：
* @return：
**************************
*/
$(function() {

	// 初始化分页信息
	$("#count").val(0);
	$.fenyeInit();
});

/*************************
* 分页初始化-判断画面是否有数据
* @param ：
* @return：
**************************
*/
$.fenyeInit = function() {

	// 获取页面数据的条数
	count = parseInt($("#count").val());

	// 当页面没有数据的情况下，不显示分页
	if (count == 0) {
		$("div.noproduct").show();
		$(".pagination").hide();
	// 当页面有数据的情况下，显示分页
	} else {
		$("div.noproduct").hide();
		$(".pagination").show();
		// 获取该页面每页数据的条数
		itemnum = parseInt($("#itemnum").val());
		// 获取该页面一共要分多少页
		pagenum = parseInt($("#pagenum").val());
		// 分页处理
		$.fenye(count);
	}
};

/*************************
* 分页处理
* @param ：count：画面总数据条数
* @return：
**************************
*/
$.fenye = function(count) {

	// 数据条数与每页数据数求余
	var result = count % itemnum;
	var i;
	// 当数据条数与每页数据数求余时，没有余数的情况下，页数=count / itemnum
	if (result == 0) {
		i = parseInt(count / itemnum);
	// 当数据条数与每页数据数求余时，有余数的情况下，页数=(count / itemnum) + 1
	} else {
		i = parseInt(count / itemnum) + 1;
	}

	// 渲染分页元素
	var str = "";
	str += '<li><a href="javascript:void(0)" id="pageup" onclick="pageup()">&laquo;</a></li>';
	str += '<li><a href="javascript:void(0)" id="page_1" class="active" onclick="huanye(' + 1 + ',' + count + ')">1</a></li>';
	// 页数在8页以内的情况下，直接分页，分页元素不需要添加"..."
	if (i < 8) {

		for ( var j = 2; j <= i; j++) {
			str += '<li><a href="javascript:void(0)" id="page_' + (j) + '" onclick="huanye(' + j + ',' + count + ')">' + (j) + '</a></li>';
		}
		str += '<li><a href="javascript:void(0)" id="pagedown" onclick="pagedown(' + count
			+ ')">&raquo;</a></li><span id="count_content">(共<span id="count">'
				+ count + '</span>条记录)<input style="opacity: 0; width: 4.5%;"/>跳转到:<input id="gotiao" type="text" style="width:30px;color:#000000;"/> 页<button type="button" style="width:30px;margin-left:1%" onclick="tiaozhuan();">GO</button></span>';
	// 页数在8页以上的情况下，分页时，分页元素需要添加"..."
	} else {
		for ( var j = 2; j < 7; j++) {
			str += '<li><a href="javascript:void(0)" id="page_' + (j) + '" onclick="huanye(' + j
				+ ',' + count + ')">' + (j) + '</a></li>';
		}
		// 添加"..."
		str += '<li><a href="javascript:void(0)">...</a></li>';
		str += '<li><a href="javascript:void(0)" id="page_' + i + '" onclick="huanye(' + i + ',' + count + ')">' + i + '</a></li>';
		str += '<li><a href="javascript:void(0)" id="pagedown" onclick="pagedown(' + count
			+ ')">&raquo;</a></li><span id="count_content">(共<span id="count">'
				+ count + '</span>条记录)<input style="opacity: 0; width: 4.5%;"/>跳转到:<input id="gotiao" type="text" style="width:30px;color:#000000;"/> 页<button type="button" style="width:30px;margin-left:1%" onclick="tiaozhuan();">GO</button></span>：';
	}
	$(".pagination").html(str);
};

/*************************
* 换页处理
* 在每个页面的js里要定义一个名为reloadData(num)的方法
* @param ：num：页码数
* @param ：count：数据条数数
* @return：
**************************
*/
function huanye(num, count) {

	// 获取页面请求的也码数
	$("#pagenum").val(num);
	// 翻页时请求页面数据，num(页码数)，在自己的js里要定义一个名为reloadData(num)的方法
	reloadData(num);

	// 数据条数与每页数据数求余
	var result = count % itemnum;
	var temp;
	// 当数据条数与每页数据数求余时，没有余数的情况下，页数=count / itemnum
	if (result == 0) {
		temp = parseInt(count / itemnum);
	// 当数据条数与每页数据数求余时，有余数的情况下，页数=(count / itemnum) + 1
	} else {
		temp = parseInt(count / itemnum) + 1;
	}

	// 画面的最大页码数
	var maxpage = temp;

	var str = "";
	// 当画面最大页码数小于8页的情况下，点击页码时不需要变更分页控件样式
	if (maxpage <= 7) {
		$(".pagination .active").removeClass("active");
		$("#page_" + num).addClass("active");

	// 当画面最大页码数打于8页的情况下
	} else {

		// 当用户点击第一页到第四页的情况下，分页控件不需要更改式样
		if (num - 3 <= 1) {

			// 渲染“前一页”图标
			str += '<li><a href="javascript:void(0)" id="pageup" onclick="pageup()">&laquo;</a></li>';
			// 渲染第1页码
			str += '<li><a href="javascript:void(0)" id="page_1" onclick="huanye(' + 1 + ',' + count + ')">1</a></li>';
			// 渲染2-6页的页码
			for ( var j = 2; j < 7; j++) {
				str += '<li><a href="javascript:void(0)" id="page_' + (j) + '" onclick="huanye('
						+ j + ',' + count + ')">' + (j) + '</a></li>';
			}
			// 渲染“...”省略图标
			str += '<li><a href="javascript:void(0)">...</a></li>';
			// 渲染最后一页的页码
			str += '<li><a href="javascript:void(0)" id="page_'
					+ temp + '" onclick="huanye('
					+ temp + ',' + count + ')">'
					+ temp + '</a></li>';
			// 渲染“后一页”图标，以及“共多少条记录”
			str += '<li><a href="javascript:void(0)" id="pagedown" onclick="pagedown(' + count
					+ ')">&raquo;</a></li><span id="count_content">(共<span id="count">'
					+ count + '</span>条记录)<input style="opacity: 0; width: 4.5%;"/>跳转到:<input id="gotiao" type="text" style="width:30px;color:#000000;"/> 页<button type="button" style="width:30px;margin-left:1%" onclick="tiaozhuan();">GO</button></span>';
			$(".pagination").html(str);
			// 用户所点击的页码，样式变更
			$("#page_" + num).addClass("active");

		// 当用户点击后三页，页码的情况下，分页控件需要更改式样
		} else if (num >= maxpage - 3) {
			// 渲染“前一页”图标
			str += '<li><a href="javascript:void(0)" id="pageup" onclick="pageup()">&laquo;</a></li>';
			// 渲染第1页码
			str += '<li><a href="javascript:void(0)" id="page_1" onclick="huanye(' + 1 + ',' + count + ')">1</a></li>';
			// 渲染“...”省略图标
			str += '<li><a href="javascript:void(0)">...</a></li>';
			// 渲染后5页的页码
			for ( var j = maxpage - 5; j <= maxpage; j++) {
				str += '<li><a href="javascript:void(0)" id="page_' + (j) + '" onclick="huanye(' + j + ',' + count + ')">' + (j) + '</a></li>';
			}
			// 渲染“后一页”图标，以及“共多少条记录”
			str += '<li><a href="javascript:void(0)" id="pagedown" onclick="pagedown(' + count
					+ ')">&raquo;</a></li><span id="count_content">(共<span id="count">'
					+ count + '</span>条记录)<input style="opacity: 0; width: 4.5%;"/>跳转到:<input id="gotiao" type="text" style="width:30px;color:#000000;"/> 页<button type="button" style="width:30px;margin-left:1%" onclick="tiaozhuan();">GO</button></span>';
			$(".pagination").html(str);
			// 用户所点击的页码，样式变更
			$("#page_" + num).addClass("active");

		// 当用户点击“...”省略页码，分页控件需要更改式样
		} else {
			// 渲染“前一页”图标
			str += '<li><a href="javascript:void(0)" id="pageup" onclick="pageup()">&laquo;</a></li>';
			// 渲染第1页码
			str += '<li><a href="javascript:void(0)" id="page_1" onclick="huanye(' + 1 + ',' + count + ')">1</a></li>';
			// 渲染“...”省略图标
			str += '<li><a href="javascript:void(0)">...</a></li>';
			// 渲染“...”省略图标之后的页码
			for ( var j = num - 2; j <= num + 1; j++) {
				str += '<li><a href="javascript:void(0)" id="page_' + (j) + '" onclick="huanye(' + j + ',' + count + ')">' + (j) + '</a></li>';

			}
			// 渲染“...”省略图标
			str += '<li><a href="javascript:void(0)">...</a></li>';
			// 渲染最后一页的页码
			str += '<li><a href="javascript:void(0)" id="page_'
					+ temp + '" onclick="huanye('
					+ temp + ',' + count + ')">'
					+ temp + '</a></li>';
			// 渲染“后一页”图标，以及“共多少条记录”
			str += '<li><a href="javascript:void(0)" id="pagedown" onclick="pagedown(' + count
					+ ')">&raquo;</a></li><span id="count_content">(共<span id="count">'
					+ count + '</span>条记录)<input style="opacity: 0; width: 5.5%;"/>跳转到:<input id="gotiao" type="text" style="width:30px;color:#000000;"/> 页<button type="button" style="width:5%;margin-left:1%;" onclick="tiaozhuan();">GO</button></span>';
			$(".pagination").html(str);
			// 用户所点击的页码，样式变更
			$("#page_" + num).addClass("active");
		}
	}
}

/*************************
* 点击“前一页”图标
* @param ：
* @return：
**************************
*/
function pageup() {

	// 获取该页码id
	var current = $(".pagination .active").attr("id");
	var id = parseInt(current.split("_")[1]);

	// 当前页码为第一页的情况下
	if (current == "page_1") {
		if (!$.isNull(window.parent)) {
			window.parent.createPopup("已是第一页");
		} else {
			createPopup("已是第一页！");
		}
	// 当前页码不是第一页的情况下
	} else {
		$(".pagination .active").removeClass("active");
		// 换页处理
		huanye(id - 1, count);
		// id自减
		$("#pagenum").val(id - 1);
	}
}

/*************************
* 点击“后一页”图标
* @param ：count：数据条数
* @return：
**************************
*/
function pagedown(count) {

	// 获取该页码id
	var current = $(".pagination .active").attr("id");
	var id = parseInt(current.split("_")[1]);

	// 数据条数与每页数据数求余
	var result = count % itemnum;
	var temp;
	// 当数据条数与每页数据数求余时，没有余数的情况下，页数=count / itemnum
	if (result == 0) {
		temp = parseInt(count / itemnum);
	// 当数据条数与每页数据数求余时，有余数的情况下，页数=(count / itemnum) + 1
	} else {
		temp = parseInt(count / itemnum) + 1;
	}

	// 当前页码为最后一页的情况下
	if (current == "page_" + temp) {
		if (!$.isNull(window.parent)) {
			window.parent.createPopup("已是最后一页");
		} else {
			createPopup("已是最后一页！");
		}
	// 当前页码不是最后一页的情况下
	} else {
		$("#" + current).removeClass("active");
		// 换页处理
		huanye(id + 1, count);
		// id自加
		$("#pagenum").val(id + 1);
	}

}

//go跳转页面
function tiaozhuan(){
	var countsum=$("#count").val();
	var mnum=$("#itemnum").val();
	var result=countsum % mnum;
	var temp;
	if (result == 0) {
		temp = parseInt(countsum / mnum);
	// 当数据条数与每页数据数求余时，有余数的情况下，页数=(count / itemnum) + 1
	} else {
		temp = parseInt(countsum / mnum) + 1;
	}
	var parm=$("#gotiao").val();
	parm=parseInt(parm);
	if(parm<temp+1&&parm>0){
		$("#pagenum").val(parm);
		$(".pagination .active").removeClass("active");
		$("#page_" + parm).addClass("active");
		reloadData(parm);
		
	}
	
}