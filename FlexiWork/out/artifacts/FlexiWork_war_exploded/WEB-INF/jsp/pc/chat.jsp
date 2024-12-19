<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ck" uri="http://com.work/common"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" /> <!-- 设置上下文路径，以便引用静态资源 -->
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>用户聊天</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/pc/css/common.css"> <!-- 引入公共 CSS 样式 -->
	<link rel="stylesheet" type="text/css" href="${ctx}/pc/css/chat.css"> <!-- 引入聊天相关 CSS 样式 -->
	<link rel="icon" type="image/x-icon" href="${ctx}/pc/images/titleLogo.png"> <!-- 设置页面图标 -->
	<style>
		/* 上传和下载按钮的容器 */
		.upload-container {
			margin: 20px 0;
			text-align: center; /* 居中对齐 */
		}

		/* 按钮的通用样式 */
		.custom-button {
			display: inline-block;
			padding: 12px 24px; /* 增加按钮的内边距 */
			font-size: 16px;
			font-weight: bold;
			color: #fff; /* 文字颜色 */
			background: linear-gradient(90deg, #007bff, #00c6ff); /* 渐变背景 */
			border: none;
			border-radius: 8px; /* 圆角 */
			cursor: pointer;
			box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* 阴影 */
			transition: all 0.3s ease; /* 过渡效果 */
			margin: 0 10px; /* 按钮之间的间距 */
		}

		/* 按钮悬停效果 */
		.custom-button:hover {
			background: linear-gradient(90deg, #0056b3, #00aaff); /* 悬停时的渐变背景 */
			box-shadow: 0 6px 10px rgba(0, 0, 0, 0.2); /* 悬停时的阴影 */
			transform: translateY(-2px); /* 悬停时稍微上移 */
		}

		/* 按钮点击效果 */
		.custom-button:active {
			transform: translateY(0); /* 点击时恢复原位 */
			box-shadow: 0 3px 5px rgba(0, 0, 0, 0.1); /* 点击时的阴影 */
		}

		/* 文件输入框的样式 */
		.file-input {
			display: none; /* 隐藏原生的文件输入框 */
		}

		/* 自定义文件选择按钮 */
		.custom-file-label {
			display: inline-block;
			padding: 10px 20px;
			font-size: 16px;
			font-weight: bold;
			color: #fff;
			background: linear-gradient(90deg, #28a745, #21c87a); /* 渐变背景 */
			border: none;
			border-radius: 8px;
			cursor: pointer;
			box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
			transition: all 0.3s ease;
		}

		/* 文件选择按钮悬停效果 */
		.custom-file-label:hover {
			background: linear-gradient(90deg, #1e7e34, #1abc9c); /* 悬停时的渐变背景 */
			box-shadow: 0 6px 10px rgba(0, 0, 0, 0.2);
			transform: translateY(-2px);
		}

		/* 文件选择按钮点击效果 */
		.custom-file-label:active {
			transform: translateY(0);
			box-shadow: 0 3px 5px rgba(0, 0, 0, 0.1);
		}
	</style>
</head>
<body>
<div class="top">
	<div class="div_logo">
		<a href="${ctx}/pc/index/goIndex.action" title="返回首页">
			<img src="${systemUpset.companyLogo}"> <!-- 显示公司 Logo -->
		</a>
	</div>
</div>
<div class="chat">
	<div class="chat_zhong">
		<div class="chat_z">
			<div class="div_chat">
				<div class="div_s"> <!-- 聊天内容区域 -->
					<!-- 聊天内容代码 -->
				</div>
				<div class="div_x"> <!-- 输入区域 -->
					<textarea name="chat" class="input_chat" maxlength="70"></textarea> <!-- 聊天输入框 -->
					<input type="button" value="发送" class="input_fa"> <!-- 发送按钮 -->
				</div>
			</div>
		</div>

		<!-- 添加文件上传区域 -->
		<div class="upload-container">
			<!-- 上传简历表单 -->
			<form id="uploadForm" action="${ctx}/pc/action/uploadResume.action" method="post" enctype="multipart/form-data">
				<!-- 隐藏的文件输入框 -->
				<input type="file" name="file" id="fileInput" class="file-input" required>
				<!-- 自定义文件选择按钮 -->
				<label for="fileInput" class="custom-file-label">选择文件</label>
				<input type="hidden" name="userIdJie" value="${user.userId}">
				<!-- 上传简历按钮 -->
				<button type="submit" class="custom-button">上传简历</button>
			</form>
			<!-- 下载简历按钮 -->
			<button onclick="downloadResume()" class="custom-button">接收简历</button>
			<!-- 隐藏的用户ID -->
			<input type="hidden" id="userIdJie" value="${user.userId}">
		</div>
	</div>
</div>
<div class="bottom">
	<!-- 这里可以添加其他页面底部内容 -->
</div>
<script type="text/javascript" src="${ctx}/pc/js/jquery-1.11.1.min.js"></script> <!-- 引入 jQuery 库 -->
<script type="text/javascript">
	// 设置聊天区域自动滚动到最底部
	function setScroll(){
		var all_height = $(".div_s").get(0).scrollHeight; // 获取聊天内容的总高度
		$(".div_s").scrollTop(all_height); // 设置滚动条位置为最底部
	}
	setScroll(); // 页面加载时调用一次

	// 获取聊天内容并更新聊天区域
	function userChat(){
		$(".div_s").empty(); // 清空聊天内容区域
		$.ajax({
			url: "${ctx}/pc/action/findChatList.action", // 请求获取聊天记录的接口
			data: {userId: "${user.userId}"}, // 发送者 ID
			type: "POST",
			success: function(res){
				// 遍历获取的聊天记录
				for(var i = 0; i < res.data.length; i++){
					// 判断信息的发送者是自己还是对方，根据不同情况添加不同的样式
					if(res.data[i].userIdFa == "${user.userId}"){
						$(".div_s").append("<div class=\"div_l\"><div class=\"div_l_l\"><div class=\"div_l_t\"><img src=\"" + res.data[i].headerImageFa + "\" width=\"60\" height=\"60\"></div></div><div class=\"div_l_r\"><span>" + res.data[i].content + "</span></div></div>");
					} else {
						$(".div_s").append("<div class=\"div_r\"><div class=\"div_r_r\"><div class=\"div_r_t\"><img src=\"" + res.data[i].headerImageFa + "\" width=\"60\" height=\"60\"></div></div><div class=\"div_r_l\"><span>" + res.data[i].content + "</span></div></div>");
					}
					setScroll(); // 每次添加聊天记录后，重新设置滚动条
				}
			},
			error: function(res){
				alert("网络错误！"); // 请求失败时提示错误
			}
		});
	}
	userChat(); // 页面加载时立即获取聊天内容

	// 检查是否有新消息
	var ChatCount = 0; // 保存当前聊天记录数目
	function xinNotice(){
		$.ajax({
			url: "${ctx}/pc/action/findChatListCount.action", // 请求获取聊天记录数量的接口
			data: {userId: "${user.userId}"}, // 当前用户的 ID
			type: "POST",
			success: function(res){
				console.log(res.code); // 输出当前聊天记录的数量
				// 如果新消息数量大于已知数量，更新聊天记录
				if(res.code > ChatCount){
					userChat(); // 重新获取聊天记录
					ChatCount = res.code; // 更新已知数量
				}
			},
			error: function(res){
				alert("网络错误！"); // 请求失败时提示错误
			}
		});
	}
	// 设置定时器每隔 3 秒检查是否有新消息
	var timer = setInterval(xinNotice, 3000);

	// 发送消息功能
	$(".input_fa").click(function(){
		// 插入数据库
		$.ajax({
			url: "${ctx}/pc/action/addChat.action", // 请求添加聊天记录的接口
			data: {
				"userIdJie": "${user.userId}", // 接收者 ID
				"content": $(".input_chat").val() // 发送的内容
			},
			type: "POST",
			success: function(res){
				if(res.code == 0){
					// 处理成功
				} else {
					alert("网络错误！"); // 请求失败时提示错误
				}
			},
			error: function(res){
				alert("网络错误！"); // 请求失败时提示错误
			}
		});
		// 直接添加聊天内容到聊天区域
		$(".div_s").append("<div class=\"div_r\"><div class=\"div_r_r\"><div class=\"div_r_t\"><img src=\"${pcUser.headerImage}\" width=\"60\" height=\"60\"></div></div><div class=\"div_r_l\"><span>" + $(".input_chat").val() + "</span></div></div>");
		// 清空输入框
		$(".input_chat").val("");
		// 调用滚动条设置
		setScroll();
	});

	function downloadResume() {
		; // 清空存储
		const userIdJie = localStorage.getItem('userId');// 接收者 ID
		const userIdFa = "${user.userId}"; // 发送者 ID
		if (userIdJie) {
			fetch(`${ctx}/pc/action/downloadResume.action?userIdFa=`+userIdFa+`&userIdJie=`+userIdJie)
					.then(response => {
						if (!response.ok) {
							throw new Error('网络响应错误');
						}
						return response.blob();
					})
					.then(blob => {
						const url = window.URL.createObjectURL(blob);
						const a = document.createElement('a');
						a.href = url;
						a.download = 'resume.pdf'; // 可以动态设置文件名
						document.body.appendChild(a);
						a.click();
						a.remove();
						window.URL.revokeObjectURL(url);
					})
					.catch(error => {
						alert('文件下载失败: ' + error.message);
					});
		}
	}
</script>
</body>
</html>

