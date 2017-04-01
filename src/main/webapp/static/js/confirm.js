$(document).ready(function(e) {
	
});

function openMsg(ms) {
	$("#msgInfo").show();
	$("#errorInfo").html(ms);
}

function colseMsg() {
	$("#msgInfo").hide();
}

function confirmBuy() {
	var balance = $("#balance").val();
	$.ajax({
		type : "post",
		url : project.ctxPath+"/buy.do",
		dataType : "json",
		async : false,
		data:{"balance":balance},
		beforeSend : function() {
			//$("#loading").addClass("show");//展示loading框
		},
		complete : function() {
			//global.loadingEnd();
		},
		success : function(data) {
			if ("success" == data.status) {
				window.location.href = project.ctxPath+"/apply.do";
			}
			
			if ("fail" == data.status)  {
				openMsg("加载失败");
			}
			
			if ("rpcError" == data.status) {
				openMsg("接口调用失败");
			}
		}
	});
}