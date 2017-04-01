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
		<title>我的互助</title>
		<%@include file="common/default.jsp"%>
		<link rel="stylesheet" href="${staticPath}/static/weui/dist/style/weui.css" />
		<link rel="stylesheet" href="${staticPath}/static/weui/dist/example/example.css" />
		<link rel="stylesheet" href="${staticPath}/css/xhyhz.css" />
		<!--<link rel="stylesheet" href="../css/iscroll.min.css" />-->
		<style>
			#myHuzhu {position:absolute;top:60px; bottom:48px;left:0;width:100%}  
			#project_info {position:absolute;top:60px; bottom:48px;left:0;width:100%}
			/*#scroller li {padding:0 10px;height:60px;line-height:60px;background:#ecf6ff;margin-top:10px}  */
			/*#pullDown, #pullUp {padding:0 10px;height:30px;line-height:30px;color:#888;text-align:center}  */
		</style>
		<script type="text/javascript" src="${staticPath}/static/jq/jquery-2.1.4.js" ></script>
		<script type="text/javascript" src="${staticPath}/js/iscroll.js" ></script>
	</head>
	<body style="background-color: #ECECEC;">
		
		<!--navbar-->
		<div class="weui-tab">
            <div class="weui-navbar">
                <div class="weui-navbar__item weui-bar__item_on fz_16_px">
                    参与信息
                </div>
                <div class="weui-navbar__item fz_16_px">
                    申请帮助
                </div>
            </div>
            <div class="weui-tab__panel">
            </div>
        </div>
      
        <!--参与信息-->
        <div id="myHuzhu">
        	<div id="">
        		<div id="myHuzhupullDown">  
		            <span class="pullDownLabel" style="display:none">下拉刷新</span>  
		        </div>  
	        	<div class="myhuzhu_list clear">
		            <div class="left left_detail_info">
		            	<p>我的余额：<fmt:formatNumber type="number" maxFractionDigits="2" value="${account.balance}" />HZB</p>
		            </div>
		            <div class="left right_detail_info">
		            	<p>区块链费用：<fmt:formatNumber type="number" maxFractionDigits="2" value="${account.block }"/>HZB(已代缴)</p>
		            </div>
			     </div>
			     <ul id ="myHuzhuList">
			     	<li class="myhuzhu_list clear">
				     	
				     <c:choose>
				     	<c:when test="${empty result && empty transaction}">
				     		<div class="weui-msg">
						        <div class="weui-msg__icon-area">
						        	<!--<i class="weui-icon-success weui-icon_msg"></i>-->
						        	<img src="${staticPath}/img/icon/shengqin.png" class="apply_icon"/>
						        </div>
						        <div class="weui-msg__text-area">
						            <h2 class="weui-msg__title">没有找到互助项目</h2>
						            <p class="weui-msg__desc">您还没有加入任何互助项目，请先选择并加入一个互助项目</p>
						        </div>
						        <div class="weui-msg__opr-area">
						            <p class="weui-btn-area">
						                <a href="${ctxPath}/main.do" class="weui-btn weui-btn_primary bc_05e8d7">加入互助计划</a>
						            </p>
						        </div>
						    </div>
				     	</c:when>
				     	<c:otherwise>
				     		<c:forEach items="${result }" var="summary">
				        		<div class="myhuzhu_list clear">
						            <div class="left left_detail_info">
						            	<p>操作单号：${fn:substring(summary.trxId, 0, 10)}... </p>
						            	<p>互助对象地址：${fn:substring(summary.trxFromAddress, 0, 8)}...</p>
						            	<p>互助金额：<fmt:formatNumber type="number" maxFractionDigits="2" value="${summary.transferAmount }"/> HZB</p>
						            	<p>描述： 爱心互助计划</p>
						            </div>
						            <div class="right right_date_info">
						            	<p><fmt:formatDate value="${summary.trxTime}" type="both" pattern="yyyy-MM-dd HH:mm"/>  </p>
						            	<div align="center">
						            		<img src="${staticPath}/img/icon/true.png"/>
						            	</div>
						            	<c:if test="${summary.trxType eq '0' && summary.trxState eq '0'}">
						            		<p class="fc_05e8d7" align="center">加入中</p>
						            	</c:if>
						            	<c:if test="${summary.trxState eq '1'}">
						            		<p class="fc_05e8d7" align="center">已加入</p>
						            	</c:if>
						            	<c:if test="${summary.trxState eq '2'}">
						            		<p class="fc_05e8d7" align="center">审核中</p>
						            	</c:if>
						            	<c:if test="${summary.trxState eq '3'}">
						            		<p class="fc_05e8d7" align="center">已获赔</p>
						            	</c:if>
						            	<c:if test="${summary.trxState eq '-1'}">
						            		<p class="fc_05e8d7" align="center">交易失败</p>
						            	</c:if>
						           	</div>
						        </div>
				        	</c:forEach>
				        	
				        	<c:forEach items="${transaction }" var="summary">
				        		<div class="myhuzhu_list clear">
						            <div class="left left_detail_info">
						            	<p>操作单号：${fn:substring(summary.trxId, 0, 10)}... </p>
						            	<p>互助对象地址：${fn:substring(summary.address, 0, 8)}...</p>
						            	<p>赔付金额： <fmt:formatNumber type="number" maxFractionDigits="2" value="${summary.amount }"/>HZB</p>
						            	<p>描述： 爱心互助计划</p>
						            </div>
						            <div class="right right_date_info">
						            	<p><fmt:formatDate value="${summary.trxTime}" type="both" pattern="yyyy-MM-dd HH:mm"/>  </p>
						            	<div align="center">
						            		<img src="${staticPath}/img/icon/true.png"/>
						            	</div>
						            	<p class="fc_05e8d7" align="center">已理赔</p>
						           	</div>
						        </div>
				        	</c:forEach>
				     	</c:otherwise>
				     </c:choose>
				      </li>
				     </ul>
			     </div>
        </div>
        
        <!--申请帮助-->
        <div id="project_info" style="display: none;">
        	<c:choose>
        		<c:when test="${empty publicList && empty result}">
        			<div class="weui-msg">
				        <div class="weui-msg__icon-area">
				        	<!--<i class="weui-icon-success weui-icon_msg"></i>-->
				        	<img src="${staticPath}/img/icon/shengqin.png" class="apply_icon"/>
				        </div>
				        <div class="weui-msg__text-area">
				            <h2 class="weui-msg__title">没有找到互助项目</h2>
				            <p class="weui-msg__desc">您还没有加入任何互助项目，请先选择并加入一个互助项目</p>
				        </div>
				        <div class="weui-msg__opr-area">
				            <p class="weui-btn-area">
				                <a href="${ctxPath}/main.do" class="weui-btn weui-btn_primary bc_05e8d7">加入互助计划</a>
				            </p>
				        </div>
				    </div>
        		</c:when>
        		<c:otherwise>
        			
        			<c:if test="${!empty result}">
        				<c:forEach items="${result }" var="summary">
        					<c:if test="${summary.trxState eq '2'}">
        						<div class="weui-msg">
							        <div class="weui-msg__icon-area">
							        	<!--<i class="weui-icon-success weui-icon_msg"></i>-->
							        	<img src="${staticPath}/img/icon/shengqin.png" class="apply_icon"/>
							        </div>
							        <c:if test="${tranCount eq 0  }">
	        							<div class="weui-msg__text-area">
								            <h2 class="weui-msg__title">申请已提交</h2>
								            <p class="weui-msg__desc">您的申请已经提交经过第三方专业公估机构核实后，会在全网公示，公示通过无异议，会由智能合约自动执行划款。</p>
								        </div>
        							</c:if>
        		 					<c:if test="${tranCount ne 0  }">
	        							<div class="weui-msg__text-area">
								            <h2 class="weui-msg__title">申请款已发放</h2>
								            <p class="weui-msg__desc">您的申请已经由第三方专业公估机构核实，并在全网公示通过。申请款项已自动发放，请注意查收。</p>
								        </div>
        							</c:if>
							    </div>
        					</c:if>
        				</c:forEach>
        			</c:if>
        			
        			<div class="project_title">
		        		<div class="left">账号</div>
		        		<div class="left">互助金额</div>
		        		<div class="left">互助状态</div>
		        	</div>
		        	<ul class="clear project_bd" id="projectInfoList">
		        		<c:forEach items="${publicList}" var="summary">
		        			<li class="clear">
			        			<div class="left">${fn:substring(summary.phone, 0, 3)}****${fn:substring(summary.phone, 7, 11)}</div> 
			        			<div class="left"><fmt:formatNumber type="number" maxFractionDigits="2" value="${summary.amount}" />HZB</div>
			        			<c:if test="${tranCount eq 0  }">
			        				<c:if test="${summary.state eq 0}">
				        				<div class="left">加入中</div>	
				        			</c:if>
				        			<c:if test="${summary.state eq 1}">
				        				<div class="left"><a href="#" onclick="helpInfo('${summary.id}')">申请帮助</a></div>	
				        			</c:if>
				        			<c:if test="${summary.state eq 2}">
				        				<div class="left">已申赔</div>	
				        			</c:if>
									<c:if test="${summary.state eq 3}">
				        				<div class="left">已获赔</div>	
				        			</c:if>
				        			<c:if test="${summary.state eq -1}">
				        				<div class="left">交易失败</div>	
				        			</c:if>
			        			</c:if>
			        			
			        			<c:if test="${tranCount ne 0  }">
			        				<div class="left">已获赔</div>
			        			</c:if>
			        		</li>
		        		</c:forEach>
		        	</ul>
        		</c:otherwise>
        	</c:choose>
        </div>
        
        <div id="myHuzhupullUp">
             <span class="pullUpIcon"></span>
              <span class="pullUpLabel" style="display:none">上拉刷新 </span>
        </div>
        
        <!--显示dialog-->
        <div class="js_dialog" id="msgInfo" style="display: none;">
            <div class="weui-mask"></div>
            <div class="weui-dialog">
                <div class="weui-dialog__bd" id="errorInfo"></div>
                <div class="weui-dialog__ft">
                    <a href="javascript:void(0)" onclick="colseMcg()" class="weui-dialog__btn weui-dialog__btn_primary">确定</a>
                </div>
            </div>
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
            
            <a href="${ctxPath }/publicity.do" class="weui-tabbar__item">
                <img src="${staticPath}/img/icon/publicity_on.png" alt="" class="weui-tabbar__icon">
                <p class="weui-tabbar__label">我的互助</p>
            </a>
            <a href="${ctxPath }/publicInfo.do" class="weui-tabbar__item weui-bar__item_on">
                <span style="display: inline-block;position: relative;">
                    <img src="${staticPath}/img/icon/apply_off.png" alt="" class="weui-tabbar__icon">
                    <!-- 有圆点提示-->
                    <!--<span class="weui-badge weui-badge_dot" style="position: absolute;top: 0;right: -6px;"></span>-->
                </span>
                <p class="weui-tabbar__label">公示信息</p>
            </a>
        </div>
		
        <script type="text/javascript" src="${staticPath}/js/publicity.js"></script>
	</body>
</html>
