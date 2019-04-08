/** **初始化*** */
var pageFlag = true;
$(function() {
	/* 加载下拉框 */
	loadSelect("jobtype", "jobtype", "全部", null, null);

	$("#count").val(0);
	$("#pagenum").val(1);
	pageFlag = true;
	selectJob();
	$("#searchBtn").click(function() {
		$("#count").val(0);
		$("#pagenum").val(1);
		pageFlag = true;
		selectJob();
	});

	$("#checkAll").click(function(e, index) {
		if ($(this).prop("checked")) {
			$(".checkJob").prop("checked", true);
		} else {
			$(".checkJob").prop("checked", false);
		}
	});

	$("#carorder-order").on("click", ":checkbox", function() {
		$("#checkAll").prop("checked", false);
	});
});

function show_sub(v) {
	$("#jobMethod").empty();
	//目前只有产品同步和产品管理
	if(v=='1' || v == '2'){
		loadSelect("jobMethod", "jobMethod", "全部", null, v);
	}
}

/*******************************************************************************
 * 分页
 * 
 * @param ：pageNum
 *            页数
 * @return： *******************
 */
function reloadData(pageNum) {
	$("#pagenum").val(pageNum);
	selectJob();
}
/** 查询批跑任务* */
function selectJob() {
	var url = base.domain + "quartz/findAllJob";
	var data = {
		pageNo : $("#pagenum").val(),
		pageSize : 10,
		type : $("#jobtype").val(),
		jobMethod : $("#jobMethod").val(),
		jobStatus : $("#jobStatus").val(),
	}
	$.toAjaxsno(url, data, selectJob_cbk);
	sj = 0;
	$("#checkAll").prop("checked", false);
}
/* 查询回调* */
function selectJob_cbk(data) {
	if (data.statusCode == "000000") {
		$("#count").val(data.returns.pager.entityCount);
		$("#itemnum").val(data.returns.pager.pageSize);
		// 调用pagecontrol.js中的初始化分页信息方法
		if (pageFlag) {
			$.fenyeInit();
			pageFlag = false;
		}
		// 渲染画面
		$.insertFileInfo(data.returns.pager.entities);
	} else {
		window.parent.createPopup(data.statusMessage);
	}
}
$.insertFileInfo = function(param) {
	if (param.length > 0) {
		$(".noproduct").css("display", "none");
		var str = "";
		var content = document.createElement("tbody");
		str += "<tr>";
		for (var i = 0; i < param.length; i++) {
			// 序号
			var indexNum = i + 1;
			// 选择框，序号

			str += "<td width='2%'>" + indexNum + "</td>";
			// 任务号
			var jobNo = param[i].jobNo;
			str += "<td width='10%' ><a href ='productAutoAdd.html?jobNo="
					+ jobNo + "'>" + jobNo + "</td>";
			// 表达式 运行状态 任务状态 有效起期 有效止期 阀值 批跑开始时间 任务描述
			str += "<td width='10%'>" + cKong(param[i].cronExpression)
					+ "</td>";
			str += "<td width='4%'>"
					+ (param[i].runingStatus == "1" ? "正在运行" : "停止") + "</td>";
			str += "<td width='4%' class='jsstatus'>"
					+ (param[i].jobStatus == "1" ? "有效" : "无效") + "</td>";
			str += "<td width='8%'>" + cKong(param[i].jobName) + "</td>";
			str += "<td width='8%'>" + cKong(param[i].dateCreated) + "</td>";
			str += "<td width='8%'>" + cKong(param[i].startTime) + "</td>";
			str += "<td width='8%'>" + cKong(param[i].endTime) + "</td>";
			str += "<td width='4%'>" + cKong(param[i].thresholdValue) + "</td>";
			str += "<td width='8%'>" + cKong(param[i].startRuntime) + "</td>";

			str += "<td width='8%'><button class='btn btn-xs stopJ' jobNo='"
					+ jobNo + "' sj='" + i + "' jobid='" + param[i].id
					+ "'  runstat=" + param[i].jobStatus
					+ " onclick='stopJob(this)'>"
					+ (param[i].jobStatus == "1" ? "无效" : "有效") + "</button>";
			str += " <button class='btn btn-xs' jobNo='" + jobNo
					+ "' onclick='openlog(\"" + jobNo + "\")'>日志</button></td>";
			str += "</tr>";
		}
		$(content).html(str);
		$(".order-content-table").html(content);

	} else {
		$(".order-content-table").html("");
		$(".noproduct").css("display", "block");
	}
};
var sj = 0;
/* 停止批跑任务* */
function stopJob(e) {
	var runstat = $(e).attr("runstat");
	var jobid = $(e).attr("jobid");
	var jobNo = $(e).attr("jobNo");
	sj = parseInt($(e).attr("sj"));
	var url = base.domain + "quartz/updateJobStatus";
	var data = {
		"jobNo" : jobNo,
		"jobStatus" : runstat,
		"jobid" : jobid,
	}
	$.toAjaxsno(url, data, stopjob_cbk);

}
function stopjob_cbk(data) {
	if (data.statusCode == "000000") {
		var runstat = data.returns.jobStatus;

		if (runstat == "1") {
			$(".stopJ").eq(sj).html("无效").attr("runstat", "1");
			$(".jsstatus").eq(sj).html("有效");

		} else {
			$(".stopJ").eq(sj).html("有效").attr("runstat", "0");
			$(".jsstatus").eq(sj).html("无效");
		}

	} else {
		window.parent.createPopup(data.statusMessage);
	}
}

/* 打开日志* */
function openlog(jobNo) {
	var url = base.domain + "quartz/findJobloginfo";
	var data = {
		"jobNo" : jobNo
	}
	$.toAjaxsno(url, data, openlog_cbk);
}
// 请求回调
function openlog_cbk(data) {
	if (data.statusCode == "000000") {
		var list = data.returns.list;
		var stt = "";
		for (var n = 0; n < list.length; n++) {
			stt += "<tr>"
			stt += "<td width='25%'>" + list[n].logNo + "</td>";
			stt += "<td width='15%'>" + list[n].type + "</td>";
			stt += "<td width='25%'>" + list[n].startTime + "</td>";
			stt += "<td width='25%'>" + list[n].endTime + "</td>";
			stt += "<td width='10%'>" + list[n].longTime + "</td>";
			stt += "</tr>"
		}
		$(".mbbdd").html(stt);
		$('.modshw').modal('show');
	} else {
		window.parent.createPopup(data.statusMessage);
	}
}

/* 返回空字符传 * */
function cKong(str) {
	if ($.isNull(str)) {
		str = '';
	}
	return str;
}
/** ***添加按钮跳转****** */
function addSubmit() {
	window.location.href = "productAutoAdd.html";
}
/*******************************************************************************
 * 操作类型加载 loadSelect("jobtype",null,null)
 ******************************************************************************/
function loadSelect(value, id, all, currentCode, jobMethod) {
	var sendData = {
		"dic_type" : value
	};
	$
			.ajax({
				async : true,
				type : "POST",
				url : base.domain + "/init/loadDicBytype",
				data : sendData,
				dataType : 'json',
				success : function(data) {
					var list = data;
					$("#" + id).empty();
					if (all != null) {
						$("#" + id).append(
								"<option value =''>" + all + "</option>");
					}

					if (jobMethod == "1") {
						for (var i = 0; i < list.length; i++) {
							if (list[i].dicCode == "5") {
								list.remove(i);
							}
						}
					} else if (jobMethod == "2") {
						for (var i = 0; i < list.length;) {
							if (list[i].dicCode == "4"
									|| list[i].dicCode == "3"
									|| list[i].dicCode == "2"
									|| list[i].dicCode == "1") {
								list.remove(i);
								i=0;
								continue;
							}
							i++;
						}
					}
					for (var i = 0; i < list.length; i++) {
						if (id == 'jobMethod') {
							var op = "<option value ='" + list[i].dicCode
									+ "'>" + list[i].dicTypeLabel + "</option>";
							if (currentCode != null) {
								if (currentCode == list[i].dicCode) {
									op = "<option value ='" + list[i].dicCode
											+ "' selected=\"selected\">"
											+ list[i].dicTypeLabel
											+ "</option>";
								}
							}
						} else {
							var op = "<option value ='" + list[i].dicCode
									+ "'>" + list[i].dicValue + "</option>";
							if (currentCode != null) {
								if (currentCode == list[i].dicCode) {
									op = "<option value ='" + list[i].dicCode
											+ "' selected=\"selected\">"
											+ list[i].dicValue + "</option>";
								}
							}
						}

						$("#" + id).append(op);
					}
				}
			});
}
/* 封装ajax* */
$.toAjaxsno = function(url, dataList, callback) {
	var requestData = {};
	requestData.request = dataList;
	var jsonStr = aesEncrypt(JSON.stringify(requestData));
	$.ajax({
		async : true,
		type : "POST",
		url : url,
		data : "jsonKey=" + jsonStr,
		dataType : 'json',
		success : function(data) {
			callback(data);
		},
		error : function() {
			window.parent.createPopup("系统异常，请稍后再试！");
		}
	});
}