<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="default.jsp"%>
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
    <a href="${ctxPath }/apply.do" class="weui-tabbar__item">
        <span style="display: inline-block;position: relative;">
            <img src="${staticPath}/img/icon/apply_off.png" alt="" class="weui-tabbar__icon">
            <!-- 有圆点提示-->
            <!--<span class="weui-badge weui-badge_dot" style="position: absolute;top: 0;right: -6px;"></span>-->
        </span>
        <p class="weui-tabbar__label">公示信息</p>
    </a>
</div>