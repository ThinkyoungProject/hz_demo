<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<script type="text/javascript" src="${staticPath }/static/jq/jquery-2.1.4.js" ></script>
<script type="text/javascript" src="${staticPath}/static/underscore-min.js"></script>
</head>
<body class="register">
		<div class="logo">
			<img alt="" style="width:40%" src="${staticPath }/img/bg/logo.png">
		</div>
		<div class="register_form">
			<form id="indexForm" method="post" action="${ctxPath }/main.do">
			<div class="weui-cells zll_weui_cells_form">
	            <div class="weui-cell zll_weui_cell">
	                <div class="weui-cell__bd">
	                    <input class="weui-input" type="tel" id="phone" name="phone" 
	                    	placeholder="请输入手机号码" maxlength="11"/>
	                </div>
	            </div>
	        </div>
	        <div class="weui-cells zll_weui_cells_form">
                <div class="weui-cell__bd">
                	<!--
                    	weui-btn_disabled有此class表示无法点击disable状态，使得能点击remove此class即可
                    -->
                    <a href="javascript:void(0);" onclick="join()" class="weui-btn zll_addPlan">加入互助计划</a>
                </div>
	        </div>
	        </form>
		</div>
        
        <!--toast展示框-->
        <div id="" style="display: none;">
	        <div class="weui-mask_transparent"></div>
	        <div class="weui-toast">
	            <i class="weui-icon-success-no-circle weui-icon_toast"></i>
	            <p class="weui-toast__content">对不起请输入手机号码</p>
	        </div>
	    </div>
		<!--loading展示框-->
		<div id="loading" style="display:none;">
	        <div class="weui-mask_transparent"></div>
	        <div class="weui-toast">
	            <i class="weui-loading weui-icon_toast"></i>
	            <p class="weui-toast__content">数据加载中</p>
	        </div>
	    </div>
	    
		<!--显示dialog-->
        <div class="js_dialog" id="msgInfo" style="display: none;">
            <div class="weui-mask"></div>
            <div class="weui-dialog">
                <div class="weui-dialog__bd" id="errorInfo"></div>
                <div class="weui-dialog__ft">
                    <a href="javascript:void(0)" onclick="colseMsg()" class="weui-dialog__btn weui-dialog__btn_primary">确定</a>
                </div>
            </div>
        </div>
        
        
        <script type="text/javascript" src="${staticPath }/js/index.js"></script>
	</body>
</html>