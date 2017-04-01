$(document).ready(function(e) {
	
});


function confirmA(address) {
	$.ajax({
		type : "post",
		url : project.ctxPath+"/confrimAudit.do",
		dataType : "json",
		async : false,
		data:{"address":address},
		beforeSend : function() {
			//$("#loading").addClass("show");//展示loading框
		},
		complete : function() {
			//global.loadingEnd();
		},
		success : function(data) {
			if ("success" == data.status) {
				openMsg("赔付成功")
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

function tabAuto(obj) {
	if (obj == '1') {
		$("#myHuzhu").show();
		$("#project_info").hide();
	}
	if (obj == '2') {
		$("#myHuzhu").hide();
		$("#project_info").show();
	}
}