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
		<title>公示</title>
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
                <div class="weui-navbar__item weui-bar__item_on fz_16_px" onclick="tabAuto('1')">
                    互助审核
                </div>
                <div class="weui-navbar__item fz_16_px" onclick="tabAuto('2')">
                    赔付信息
                </div>
            </div>
            <div class="weui-tab__panel">

            </div>
        </div>
        
        <!--我的审核-->
        <div id="myHuzhu">
        	<c:forEach items="${result }" var="summary">
        		<div class="myhuzhu_list clear">
		            <div class="left left_detail_info">
		            	<p>手机号码：${summary.phone} </p>
		            	<p>互助对象地址：${fn:substring(summary.address, 0, 10)}</p>
		            	<p>互助金额： ${summary.amount }HZB</p>
		            	<p>描述： 加入意外互助计划</p>
		            </div>
		            <div class="right right_date_info">
		            	<p><fmt:formatDate value="${summary.trxTime}" type="both" pattern="yyyy-MM-dd HH:mm"/>  </p>
		            	<div align="center">
		            		<%-- <img src="${staticPath}/img/icon/true.png"/> --%>
		            	</div>
		            			            		
		            	<p class="fc_05e8d7" align="center"><a href="javascript:void(0);" onclick="confirmA('${summary.trxFromAddress}')" class="weui-btn weui-btn_mini weui-btn_primary bc_05e8d7">赔付</a></p>
		           	</div>
		        </div>
        	</c:forEach>
        </div>
        
        <!--项目信息-->
        <div id="project_info" style="display: none;">
        	<div class="project_title">
        		<div class="left">账号</div>
        		<div class="left">赔付金额</div>
        		<div class="left">互助状态</div>
        	</div>
        	<ul class="clear project_bd">
        		<c:forEach items="${tranAll }" var="summary">
        			<li class="clear">
	        			<div class="left">${summary.phone}</div>
	        			<div class="left"><fmt:formatNumber type="number" maxFractionDigits="2" value="${summary.amount}" />HZB</div>
	        			<div class="left">已获赔</div>
	        		</li>
        		</c:forEach>
        	</ul>
        </div>
        
        <!--tabbar上面的距离
        <div style="height: 70px;">&nbsp;</div>
        -->
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
        
        <script type="text/javascript" src="${staticPath}/js/auditing.js"></script>
        <script type="text/javascript" src="${staticPath}/js/common.js" ></script>
	</body>
</html>