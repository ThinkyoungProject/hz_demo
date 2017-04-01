<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport"
		        content="width=device-width, initial-scale=1.0, user-scalable=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<meta name="format-detection" content="telephone=no"/>
		<meta http-equiv="expires" content="-1"/>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<title>互助计划</title>
		<%@include file="common/default.jsp"%>
		<link rel="stylesheet" href="${staticPath}/static/weui/dist/style/weui.css" />
		<link rel="stylesheet" href="${staticPath}/static/weui/dist/example/example.css" />
		<link rel="stylesheet" href="${staticPath}/css/xhyhz.css" />
		<link rel="stylesheet" href="${staticPath}/static/swiper/swiper-3.3.1.min.css" />
		<script type="text/javascript" src="${staticPath}/static/jq/jquery-2.1.4.js" ></script>
		<script type="text/javascript" src="${staticPath}/static/swiper/swiper.min.js"></script>
	</head>
	<body style="background-color: #ECECEC;">
		<div class="swiper-container" id="swiper-container">
            <div class="swiper-wrapper">
            	<!--
                	切图时可适当拉高图片，css代码里面设置了最高为200px
                -->
				<div class="swiper-slide"><img src="${staticPath}/img/banner/banner.jpg" alt="" width="100%"></div>
				<%-- <div class="swiper-slide"><img src="${staticPath}/img/banner/banner1.jpg" alt="" width="100%"></div> --%>
            </div>
            <!-- 如果需要分页器 -->
            <div class="swiper-pagination"></div>
        </div> 
        <!--
        	以下展示产品列表
        -->
        <div class="product_list">
        	<div class="product_img">
        		<img src="${staticPath}/img/product/product.png"/>
        	</div>
        	<div class="product_desc">
    			<p class="firTitle fz_1_em">爱心互助计划${mutua.issue}</p>
    			<p class="secTitle fz_point8_em">最高可获<span class="fc_05e8d7">100HZB</span>保障</p>
    			<p class="thirdTitle fz_point7_em">保障人群： 1-60周岁</p>
        		<div class="product_btn fz_point7_em">
        			<div class="left_pat">
        				<p class="">已有${mutua.num }人加入</p>
	        			<p class="">互助金总余额<fmt:formatNumber type="number" maxFractionDigits="2" value="${mutua.balance}"/>HZB</p>
        			</div>
        			<div id="joinTo" class="right_pat">
        				
        			</div>
        		</div>
        	</div>
        </div>
        <div class="product_list">
        	<div class="product_img">
        		<img src="${staticPath}/img/product/pic2.png"/>
        	</div>
        	<div class="product_desc">
        		<p class="firTitle fz_1_em">亲情保障计划</p>
        		<p class="secTitle fz_point8_em">最高可获<span class="fc_05e8d7">100HZB</span>保障</p>
        		<p class="thirdTitle fz_point7_em">保障人群： 1-60周岁</p>
        		<div class="product_btn fz_point7_em">
        			<div class="left_pat">
        				<p class="">已有0人加入</p>
	        			<p class="">互助金总余额0HZB</p>
        			</div>
        			<div class="right_pat">
        				<a href="javascript:void(0);" class="weui-btn weui-btn_mini weui_btn_disabled  bc_cccccc">即将开始</a>
        			</div>
        		</div>
        	</div>
        </div>
       
        <!--与tabbar之间的距离-->
        <div style="height: 60px;">&nbsp;</div>
        <div class="weui-tabbar">
		    <a href="${ctxPath }/main.do" class="weui-tabbar__item weui-bar__item_on">
		        <span style="display: inline-block;position: relative;">
		            <img src="${staticPath}/img/icon/home_on.png" alt="" class="weui-tabbar__icon">
		            <!-- 有数字提示提示-->
		            <!--<span class="weui-badge" style="position: absolute;top: -2px;right: -13px;">8</span>-->
		        </span>
		        <p class="weui-tabbar__label">首页</p>
		    </a>
		    <a href="${ctxPath }/publicity.do" class="weui-tabbar__item">
		        <img src="${staticPath}/img/icon/publicity_off.png" alt="" class="weui-tabbar__icon">
		        <p class="weui-tabbar__label">我的互助</p>
		    </a>
		    <a href="${ctxPath }/publicInfo.do" class="weui-tabbar__item">
		        <span style="display: inline-block;position: relative;">
		            <img src="${staticPath}/img/icon/apply_off.png" alt="" class="weui-tabbar__icon">
		            <!-- 有圆点提示-->
		            <!--<span class="weui-badge weui-badge_dot" style="position: absolute;top: 0;right: -6px;"></span>-->
		        </span>
		        <p class="weui-tabbar__label">公示信息</p>
		    </a>
		</div>
		
        <script type="text/javascript" src="${staticPath}/js/main.js"></script>
        <script type="text/javascript" src="${staticPath}/js/common.js" ></script>
	</body>
</html>

