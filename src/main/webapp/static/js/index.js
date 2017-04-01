$(document).ready(function(e) {
	
	
});

var join = _.throttle(function () {
	var phone = $("#phone").val();
	$("#phone").blur();
	if (phone != '') {
		if (phone.length != 11) {
			openMsg("对不起，请输入正确的手机号码");
			// $("#phone").focus();
		} else {
			var myreg =/^1(3|5|7|8)\d{9}$/;
			if(!myreg.test($("#phone").val())) {
				openMsg("手机号码格式错误");
			} else {
			//	$("#indexForm")[0].reset();
				$("#indexForm").submit();
			}
		}

	} else {
		openMsg("对不起，请输入手机号码");
		// $("#phone").focus();
	}
}, 6000); // 6秒内重复点击无效（简单避免重复点击）

function openMsg(ms) {
	$("#msgInfo").show();
	$("#errorInfo").html(ms);
}

function colseMsg() {
	$("#msgInfo").hide();
	$("#phone").focus();
}

/*$.ajax({
type : "post",
url : project.ctxPath+"/checkPhone.do",
dataType : "json",
data:{"phone":phone},
beforeSend : function() {
	$("#loading").addClass("show");//展示loading框
},
complete : function() {
	//global.loadingEnd();
},
success : function(data) {	
	if ("success" == data.status) {
		openMsg("对不起该手机号已经注册过了")
	} else {
		
	}
},
error : function(msg) {
	console.log(msg);
} 
});*/