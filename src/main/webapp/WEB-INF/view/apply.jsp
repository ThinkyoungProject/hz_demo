<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport"
		        content="width=device-width, initial-scale=1.0, user-scalable=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<meta name="format-detection" content="telephone=no"/>
		<meta http-equiv="expires" content="-1"/>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<title>申请互助</title>
		<%@include file="common/default.jsp"%>
		<link rel="stylesheet" href="${staticPath}/static/weui/dist/style/weui.css" />
		<link rel="stylesheet" href="${staticPath}/static/weui/dist/example/example.css" />
		<link rel="stylesheet" href="${staticPath}/css/xhyhz.css" />
		<script type="text/javascript" src="${staticPath}/static/jq/jquery-2.1.4.js" ></script>
	</head>
	<body style="background-color: #ECECEC;">
		<div class="weui-msg">
	        <div class="weui-msg__icon-area">
	        	<!--<i class="weui-icon-success weui-icon_msg"></i>-->
	        	<img src="${staticPath}/img/icon/shengqin.png" class="apply_icon"/>
	        </div>
	        <div class="weui-msg__text-area">
	            <h2 class="weui-msg__title">申请已提交</h2>
	            <p class="weui-msg__desc">您的申请已经提交，等待审核。</p>
	        </div>
	        <div class="weui-msg__opr-area">
	            <p class="weui-btn-area">
	                <a href="${ctxPath}/publicity.do" class="weui-btn weui-btn_primary bc_05e8d7">确认</a>
	            </p>
	        </div>
	    </div>
        
        <!--tabbar-->
        <div class="weui-tabbar">
            <a href="${ctxPath }/main.do" class="weui-tabbar__item weui-bar__item_on">
                <span style="display: inline-block;position: relative;">
                    <img src="${staticPath}/img/icon/home_off.png" alt="" class="weui-tabbar__icon">
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
                    <img src="${staticPath}/img/icon/apply_on.png" alt="" class="weui-tabbar__icon">
                    <!-- 有圆点提示-->
                    <!--<span class="weui-badge weui-badge_dot" style="position: absolute;top: 0;right: -6px;"></span>-->
                </span>
                <p class="weui-tabbar__label">公示信息</p>
            </a>
        </div>
		
        <script type="text/javascript" src="${staticPath}/js/common.js" ></script>
	</body>
</html>