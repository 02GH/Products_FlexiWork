<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%> <!-- 设置页面语言为Java，内容类型为HTML，字符编码为UTF-8 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- 引入JSTL核心标签库 -->
<%@ taglib prefix="ck" uri="http://com.work/common"%> <!-- 引入自定义标签库（假设用于其他功能） -->
<c:set var="ctx" value="${pageContext.request.contextPath}" /> <!-- 设置上下文路径变量ctx -->
<%--这个 JSP 文件是项目中公共头部的实现，主要负责显示网站的欢迎信息、用户的登录状态、导航菜单和一些动态交互功能。
它通过 JSTL 和 jQuery 提供了一种用户友好的界面，使得用户体验更加流畅和直观。
同时，它的结构也为后续的页面扩展和维护提供了便利。--%>




<!DOCTYPE html>
<html>
<head>
	<title>公共头部</title> <!-- 页面标题 -->
	<link rel="stylesheet" type="text/css" href="${ctx}/pc/css/common.css"> <!-- 引入公共样式 -->
	<link rel="stylesheet" type="text/css" href="${ctx}/pc/css/header.css"> <!-- 引入头部样式 -->
</head>
<body>
<div class="top"> <!-- 顶部区域 -->
	<div class="top_zhong"> <!-- 顶部内容容器 -->
		<div class="top_zhong_le"> <!-- 左侧内容 -->
			<p>${systemUpset.welcomText}</p> <!-- 显示欢迎文本 -->
		</div>
		<div class="top_zhong_ri"> <!-- 右侧内容 -->
			<c:if test="${pcUser == null}"> <!-- 如果用户未登录 -->
				<ul class="top_zhong_ul01"> <!-- 未登录用户的导航菜单 -->
					<li><a href="${ctx}/pc/index/goMailbox.action" class="a_ping" target="_self"><span class="span_ping">平台信箱</span></a></li>
					<li><a href="${ctx}/pc/login/goLogin.action" class="a_deng"><span class="span_deng" align="right">登录</span></a></li>
					<li><span class="span_xian"></span><a href="${ctx}/pc/login/goRegister.action" class="a_zhu"><span class="span_zhu">注册</span></a></li>
				</ul>
			</c:if>
			<c:if test="${pcUser != null}"> <!-- 如果用户已登录 -->
				<ul class="top_zhong_ul02"> <!-- 已登录用户的导航菜单 -->
					<li><a href="${ctx}/pc/index/goMailbox.action" class="a_ping" target="_self"><span class="span_ping">平台信箱</span></a></li>
					<li>
						<a href="#" class="a_deng">
							<span class="span_deng" align="right">${pcUser.nickName}</span> <!-- 显示用户昵称 -->
						</a>
						<ul class="top_zhong_ul03"> <!-- 用户下拉菜单 -->
							<li><a href="${ctx}/pc/personal/goPersonal.action"><span class="span_ul031" align="center">个人中心</span></a></li>
							<li><a href="${ctx}/pc/index/goPublishWork.action" target="_self"><span class="span_ul032" align="center">发布兼职</span></a></li>
							<li><a href=""><span class="span_ul034" align="center">退出登录</span></a></li> <!-- 退出登录按钮 -->
						</ul>
					</li>
					<li>
						<span class="span_xian"></span>
						<span class="span_tou"> <!-- 用户头像 -->
								<img src="${pcUser.headerImage}" width="26" height="26"> <!-- 显示用户头像 -->
							</span>
					</li>
				</ul>
			</c:if>
		</div>
	</div>
</div>
<div class="daohang" id="daohang"> <!-- 导航区域 -->
	<div class="daohang_zhong">
		<div class="daohang_logo"> <!-- 网站Logo -->
			<div class="daohang_logo01">
				<a href="${ctx}/pc/index/goIndex.action" title="平台首页" target="_parent"><img src="${systemUpset.companyLogo}" width="200" height="60"></a>
			</div>
		</div>

		<div class="daohang_daohang"> <!-- 导航菜单 -->
			<ul>
				<li><a href="${ctx}/pc/index/goForum.action" class="a_lun" target="_parent"><span class="span_lun">论坛交流</span></a></li>
				<li><a href="${ctx}/pc/personal/goPersonal.action" class="a_ge" target="_parent"><span class="span_ge">个人中心</span></a></li>
				<li><a href="${ctx}/pc/index/goNotice.action" class="a_tai" target="_parent"><span class="span_tai">平台公告</span></a></li>
			</ul>
		</div>
	</div>
</div>

<script type="text/javascript" src="${ctx}/pc/js/jquery-1.11.1.min.js"></script> <!-- 引入jQuery -->
<script type="text/javascript">
	// 导航置顶判断
	$(window).scroll(function(){
		if($(document).scrollTop() >= 40){ // 判断滚动条位置
			$("#daohang").addClass("daohang_1").removeClass("daohang");
			$(".div_forum").css("margin-top", 100);
		} else {
			$("#daohang").addClass("daohang").removeClass("daohang_1");
			$(".div_forum").css("margin-top", 0);
		}
	})

	// 退出登录的功能实现
	$(".span_ul034").click(function(){
		var tf = confirm("确定退出登录吗？"); // 弹出确认对话框
		if(tf == true){
			$.ajax({
				type: "POST",
				url: "${ctx}/pc/login/outLogin.action", // 退出登录的请求处理
				data: {},
				success: function(res) {
					if (res.code == 0) {
						window.location.href = "${ctx}/pc/index/goIndex.action"; // 登录成功后重定向到首页
					} else {
						alert(res.msg); // 显示错误消息
					}
				},
				error: function(res) {
					alert("网络错误"); // 显示网络错误
				}
			});
		}
	});
</script>
</body>
</html>
