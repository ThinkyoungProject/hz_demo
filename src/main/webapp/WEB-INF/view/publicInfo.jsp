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
		<title>公示信息</title>
		<%@include file="common/default.jsp"%>
		<link rel="stylesheet" href="${staticPath}/static/weui/dist/style/weui.css" />
		<link rel="stylesheet" href="${staticPath}/static/weui/dist/example/example.css" />
		<link rel="stylesheet" href="${staticPath}/css/xhyhz.css" />
		<script type="text/javascript" src="${staticPath}/static/jq/jquery-2.1.4.js" ></script>
	</head>
	<body style="background-color: #ECECEC;">
		<!--navbar-->
		<div class="weui-tab">
            <div class="weui-navbar">
                <div class="weui-navbar__item weui-bar__item_on fz_16_px" onclick="tabHelp('1')">
                    爱心名单
                </div>
                <div class="weui-navbar__item fz_16_px" onclick="tabHelp('2')">
                    互助公示
                </div>
            </div>
            <div class="weui-tab__panel">

            </div>
        </div>
        
        <!--爱心名单-->
        <div id="myHuzhu">
        	<div class="myhuzhu_list clear">
	            <div class="left left_detail_info">
	            	<p>互助余额：<fmt:formatNumber type="number" maxFractionDigits="2" value="${mutua.balance}" />HZB</p>
	            </div>
	            <div class="left right_detail_info">
	            	<p>参与人数：<fmt:formatNumber type="number" maxFractionDigits="2" value="${mutua.num }"/></p>
	            </div>
		     </div>
        	 <div class="myhuzhu_list clear">
	            <div class="left left_detail_info" style="width:100%">
	            	<p>合约地址：${fn:substring(mutua.address , 0, 25)}</p>
	            </div>
		     </div>
		      <div class="myhuzhu_list clear">
	            <div class="left right_detail_info">
	            	<p>项目描述：爱心互助计划</p>
	            </div>
		     </div>
        	<div class="project_title">
        		<div class="left">账号</div>
        		<div class="left">互助金额</div>
        		<div class="left">互助状态</div>
        	</div>
        	<ul class="clear project_bd">
        		<c:forEach items="${trxstates }" var="summary">
        			<li class="clear">
	        			<div class="left">${fn:substring(summary.phone, 0, 3)}****${fn:substring(summary.phone, 7, 11)}</div>
	        			<div class="left"><fmt:formatNumber type="number" maxFractionDigits="2" value="${summary.amount}" />HZB</div>
	        			<c:if test="${summary.state eq 0}">
	        				<div class="left">加入中</div>	
	        			</c:if>
	        			<c:if test="${summary.state eq 1}">
	        				<div class="left">已加入</div>	
	        			</c:if>
	        			<c:if test="${summary.state eq 2}">
	        				<div class="left">审核中</div>	
	        			</c:if>
	        			<c:if test="${summary.state eq 3}">
	        				<div class="left">已获赔</div>	
	        			</c:if>
	        			<c:if test="${summary.state eq -1}">
	        				<div class="left">交易失败</div>	
	        			</c:if>
	        		</li>
        		</c:forEach>
        	</ul>
        </div>
        
        <!-- 获助公示 -->
        <div id="project_info" style="display: none;">
        	<div class="myhuzhu_list clear">
	            <div class="left left_detail_info">
	            	<p>赔付总额：<fmt:formatNumber type="number" maxFractionDigits="2" value="${tranNum.amount}" />HZB</p>
	            </div>
	            <div class="left right_detail_info">
	            	<p>赔付人数：${tranNum.num}</p>
	            </div>
		     </div>
		     <div class="myhuzhu_list clear">
	            <div class="left left_detail_info" style="width:100%">
	            	<p>合约地址：${fn:substring(mutua.address , 0, 25)}</p>
	            </div>
		     </div>
		      <div class="myhuzhu_list clear">
	            <div class="left right_detail_info">
	            	<p>项目描述：爱心互助计划</p>
	            </div>
		     </div>
        	<div class="project_title">
        		<div class="left">账号</div>
        		<div class="left">赔付金额</div>
        		<div class="left">互助状态</div>
        	</div>
        	<ul class="clear project_bd">
        		<c:forEach items="${tranAll }" var="summary">
        			<li class="clear">
	        			<div class="left">${fn:substring(summary.phone, 0, 3)}****${fn:substring(summary.phone, 7, 11)}</div>
	        			<div class="left"><fmt:formatNumber type="number" maxFractionDigits="2" value="${summary.amount}" />HZB</div>
	        			<div class="left">已理赔</div>
	        		</li>
        		</c:forEach>
        	</ul>
        </div>
        
        <!--tabbar上面的距离
        <div style="height: 70px;">&nbsp;</div>
        -->
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
            
            <a href="${ctxPath }/publicity.do" class="weui-tabbar__item weui-bar__item_on">
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
		
        <script type="text/javascript" src="${staticPath}/js/publicInfo.js"></script>
        <script type="text/javascript" src="${staticPath}/js/common.js" ></script>
	</body>
</html>