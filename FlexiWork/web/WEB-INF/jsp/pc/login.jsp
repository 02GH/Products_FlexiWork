<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--该 JSP 文件实现了用户登录的基本功能，包含用户界面、数据验证、与服务器的交互等。
其设计关注用户体验，如通过 localStorage 保存用户信息、动态显示错误提示等。
同时，使用 AJAX 技术使得页面在登录过程中的操作更为流畅，无需重新加载整个页面。
整体上，这个文件是用户登录流程的重要组成部分。--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" /> <!-- 设置上下文路径 -->




<html>
<head>
    <meta charset="utf-8">
    <title>欢迎登录</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/pc/css/common.css"> <!-- 引入公共样式 -->
    <link href="${ctx}/pc/css/login.css" rel="stylesheet" type="text/css"> <!-- 引入登录样式 -->
    <link rel="icon" type="image/x-icon" href="${ctx}/pc/images/titleLogo.png"> <!-- 设置页面图标 -->
</head>

<body>
<div class="div_deng"> <!-- 登录框的主容器 -->
    <div class="div_top"> <!-- 顶部区域 -->
        <div class="div_logo"> <!-- Logo区域 -->
            <a href="${ctx}/pc/index/goIndex.action" title="返回首页">
                <img src="${systemUpset.companyLogo}"> <!-- 显示公司Logo -->
            </a>
        </div>
    </div>
    <div class="div_zhong"> <!-- 中间区域 -->
        <div class="div_zhong_l"></div>
        <div class="div_zhong_r"> <!-- 右侧登录表单 -->
            <form class="form_deng" action="#" method="post" name="用户登录">
                <h2 align="center">兼职平台登录</h2><br>
                <span class="span">账号：</span>
                <input type="text" size="20" class="input_text" id="input_text" placeholder="请输入账号"/><br>
                <span class="msg zhang_msg"></span><br>
                <span class="span">密码：</span>
                <input type="password" size="20" class="input_text" id="input_password" placeholder="请输入密码"/><br>
                <span class="msg password_msg"></span><br>
                <div class="remeber flex-box">
                    <input id="remebers" type="checkbox" name="" value="1" />
                    <span>记住密码</span>
                    <span class="forget-password">忘记密码？</span> <!-- 忘记密码链接 -->
                </div>
                <input class="input_deng" type="button" value="登录"/> <!-- 登录按钮 -->
                <a href="${ctx}/pc/login/goRegister.action" title="用户注册">
                    <input class="input_zhu" type="button" value="注册"/> <!-- 注册按钮 -->
                </a>
                <br>
                <span class="msg deng_msg">${msg}</span> <!-- 显示登录结果的消息 -->
            </form>
        </div>
    </div>
    <div class="div_xia"></div>
</div>

<!-- 引入 jQuery -->
<script type="text/javascript" src="${ctx}/pc/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
    // 记住用户名和密码
    function remember_name(){
        var isRem = $("#remebers").prop("checked"); // 获取复选框状态
        if (isRem) {
            localStorage.setItem('userId', $("#input_text").val()); // 存储用户名
            localStorage.setItem('password', $("#input_password").val()); // 存储密码
        } else {
            localStorage.setItem('userId', ""); // 清空存储
            localStorage.setItem('password', "");
        }
    }

    // 从 localStorage 获取用户名和密码
    $("#input_text").val(localStorage.getItem('userId'));
    $("#input_password").val(localStorage.getItem('password'));
    if (localStorage.getItem('userId') == "") {
        $("#remebers").attr("checked", false); // 复选框未选中
    } else {
        $("#remebers").attr("checked", true); // 复选框选中
    }

    // 校验用户名和密码
    $("#input_text").focusout(function(){
        if ($("#input_text").val() == "") {
            $(".zhang_msg").text("账号不能为空！");
        } else {
            $(".zhang_msg").text("");
        }
    });

    $("#input_password").focusout(function(){
        if ($("#input_password").val() == "") {
            $(".password_msg").text("密码不能为空！");
        } else {
            $(".password_msg").text("");
        }
    });

    // 登录按钮点击事件
    $(".input_deng").click(function(){
        if ($("#input_text").val() == "") {
            $(".zhang_msg").text("账号不能为空！");
        } else if ($("#input_password").val() == "") {
            $(".password_msg").text("密码不能为空！");
        } else {
            $.ajax({
                type: "POST",
                url: "${ctx}/pc/login/doLogin.action",
                data: {
                    userId: $("#input_text").val(), // 用户名
                    password: $("#input_password").val() // 密码
                },
                success: function(res) {
                    if (res.code == 0) {
                        // 记住用户名和密码
                        remember_name(); // 调用记住用户名和密码的函数
                        window.location.reload(); // 刷新页面
                    } else {
                        $(".password_msg").text(res.msg); // 显示错误消息
                    }
                },
                error: function(res) {
                    $(".password_msg").text("网络错误"); // 显示网络错误
                }
            });
        }
    });

    // 忘记密码的处理
    $(".forget-password").click(function(){
        if ($("#input_text").val() == "") {
            $(".zhang_msg").text("请输入账号！");
        } else {
            $.ajax({
                type: "POST",
                url: "${ctx}/pc/login/isQuestion.action",
                data: {
                    userId: $("#input_text").val() // 发送用户名查询安全问题
                },
                success: function(res) {
                    if (res.code == 0) {
                        url = "${ctx}/pc/login/goForgetPassword.action?userId=" + $("#input_text").val(); // 重定向到找回密码页面
                        window.location.href = url;
                    } else {
                        $(".zhang_msg").text(res.msg); // 显示错误消息
                    }
                },
                error: function(res) {
                    $(".password_msg").text("网络错误"); // 显示网络错误
                }
            });
        }
    });

</script>
</body>
</html>
