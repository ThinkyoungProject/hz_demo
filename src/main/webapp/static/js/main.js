
$(document).ready(function(e) {
	var mySwiper = new Swiper ('.swiper-container', {
	    direction: 'horizontal',//vertical  horizontal
	    loop: false,
	   // autoplay : 2000,
		autoplayDisableOnInteraction : false,
		//speed:300,
	    // 如果需要分页器
	    //pagination: '.swiper-pagination',
	}) ;
	
	$.ajax({
		type : "post",
		url : project.ctxPath+"/checkByJoin.do",
		dataType : "json",
		async : false,
		beforeSend : function() {
			//$("#loading").addClass("show");//展示loading框
		},
		complete : function() {
			//global.loadingEnd();
		},
		success : function(data) {
			if ("success" == data.status) {
				$("#joinTo").html('<a href="javascript:void(0);" class="weui-btn weui-btn_mini weui-btn_primary bc_cccccc">已经加入</a>');
			}else if ("nobalance" == data.status)
			{
				$("#joinTo").html('<a href="javascript:void(0);" class="weui-btn weui-btn_mini weui-btn_primary bc_cccccc">余额不足</a>');
			}				
			else {
				$("#joinTo").html('<a href="javascript:void(0);" onclick="buy()" class="weui-btn weui-btn_mini weui-btn_primary bc_05e8d7">立即加入</a>');
			}
		}
	});
	
});


function buy() {
	window.location.href = project.ctxPath + "/join.do";
}