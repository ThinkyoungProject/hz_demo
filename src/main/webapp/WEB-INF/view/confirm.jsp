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
		<title>加入爱心互助计划</title>
		<%@include file="common/default.jsp"%>
		<link rel="stylesheet" href="${staticPath}/static/weui/dist/style/weui.css" />
		<link rel="stylesheet" href="${staticPath}/static/weui/dist/example/example.css" />
		<link rel="stylesheet" href="${staticPath}/css/xhyhz.css" />
	</head>
	<body style="background-color: #ECECEC;">
		<div class="padding_20">
			
			<div class="weui-cell_zll">
				<p class="fz_20_px">被保障人信息</p>
			</div>
		
			<div class="address_info">
				<p class="fz_12_px">地址：${fn:substring(userInfo.address, 0, 20)}...</p>
				<p class="fz_16_px">余额：<fmt:formatNumber type="number" maxFractionDigits="2" value="${userInfo.balance }" />HZB</p>
				<div class="weui-cell bc_ffffff fz_16_px">
	                <div class="weui-cell__hd"><label class="weui-label">投保金额（HZB）</label></div>
	                <div class="weui-cell__bd">
	                    <input class="weui-input" type="text" id="balance" name="balance" pattern="[0-9]*" value="10" readonly="readonly" placeholder="请输入投保金额"/>
	                </div>
	            </div>
			</div>
		</div>
		
		<div class="padding_20">
			<div class="weui-cell_zll">
				<p class="fz_20_px">保障合约信息</p>
			</div>
			
			<div class="address_info">
				<p class="fz_12_px">互助金，由智能合约自动管理，通过审核的互助请求，由智能合约自动的打款给被保障用户。</p>
				<p class="fz_12_px">合约地址：${fn:substring(userInfo.privateKey, 0, 45)}</p>
				<p class="fz_12_px">合约描述：爱心互助计划智能合约</p>
				<div class="weui-cells">
	                <div class="weui-cell__bd">
	                    <a href="javascript:void(0);" onclick="confirmBuy()" class="weui-btn zll_addPlan">确认参加</a>
	                </div>
		        </div>
			</div>
			
			<p class="fz_12_px">我同意<a href="#" class="fc_05e8d7">《XXXX要求》</a>
			、<a href="#" class="fc_05e8d7">《XXXX公约》</a>和<a href="#" class="fc_05e8d7">《XXXX条款》</a></p>
		</div>
		
		
		<script type="text/javascript" src="${staticPath}/js/confirm.js"></script>
	</body>
</html>