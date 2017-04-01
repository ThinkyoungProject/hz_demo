$(document).ready(
		function(e) {
			/* tabbar 切换 */
			$('.weui-tabbar__item').on(
					'click',
					function() {
						$(this).addClass('weui-bar__item_on').siblings(
								'.weui-bar__item_on').removeClass(
								'weui-bar__item_on');
					});
			/* navbar 切换 */
			$('.weui-navbar__item').on(
					'click',
					function() {
						$(this).addClass('weui-bar__item_on').siblings(
								'.weui-bar__item_on').removeClass(
								'weui-bar__item_on');
					});
			$(window).scroll(function() {

				if ($(document).scrollTop() <= 0) {
					window.location.reload();
					return false;
				}

			});
		});

function openMsg(ms) {
	$("#msgInfo").show();
	$("#errorInfo").html(ms);
}

function colseMsg() {
	$("#msgInfo").hide();
	window.location.reload();
}