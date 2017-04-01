$(document).ready(function(e) {
    /*navbar 切换*/
    $('.weui-navbar__item').on('click', function () {
        $(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
    });
})

// tab 切换
function tabHelp(obj) {
	if (obj == '1') {
		$("#myHuzhu").show();
		$("#project_info").hide();
	}
	if (obj == '2') {
		$("#myHuzhu").hide();
		$("#project_info").show();
	}
}