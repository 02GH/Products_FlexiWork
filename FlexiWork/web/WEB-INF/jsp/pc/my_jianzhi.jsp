<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ck" uri="http://com.work/common"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8"> 
	<title>我的兼职</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/pc/css/common.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/pc/css/my_jianzhi.css">
	<link rel="icon" type="image/x-icon" href="${ctx}/pc/images/titleLogo.png">
	
</head>
<body>
	<div class="page">
		<jsp:include page="/pc/index/goHeader.action" />
		<div class="div_personal">
			<div class="div_zhong">
				<jsp:include page="/pc/personal/goPersonalCommon.action" />
				<div class="div_zhong_r">
					<div class="div_zhong_rz">
						<ul class="ul_span_z_nei">
						<c:forEach items="${jianzhis}" var="jianzhi">
							<li>
<%--								<div class="div_z_t">--%>
<%--									<span class="span_z_t">--%>
<%--										<c:if test="${work.image == ''}">--%>
<%--											<img src="http://localhost:8080/moonlighting/headerImages/20200105112128eaba6.jpg" width="50" height="50">--%>
<%--									</c:if>--%>
<%--										<c:if test="${work.image != ''}">--%>
<%--											<img src="${work.image}" width="50" height="50">--%>
<%--										</c:if>--%>
<%--									</span>--%>
<%--								</div>--%>
								<div class="div_z_n">
									<a href="${ctx}/pc/index/goFindWork.action?id=${jianzhi.workId}" title="查看兼职信息" target="_blank">
										<span class="span_z_n">
											${jianzhi.title}
										</span>
									</a>
									<span class="span_z_delete">
									联系人：${jianzhi.realName} &nbsp;&nbsp;&nbsp; 联系人电话：${jianzhi.phone}

									</span>


								</div>
							</li>
						</c:forEach>
						</ul>

					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/pc/index/goFooter.action" />
	</div>
	<script type="text/javascript" src="${ctx}/pc/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
	//取消兼职
	/* var span_z_quxiao_nums=$(".ul_span_z_nei li .span_z_quxiao");
	for(var i=0;i<span_z_quxiao_nums.length;i++){
		$(".ul_span_z_nei li .span_z_quxiao:eq("+i+")").click(function(nums){
			return function(){
				var tf=confirm("取消后状态变为失效，确定取消兼职吗？");
				if(tf){
					$.ajax({
						url:"${ctx}/pc/personal/updateMyWork.action",
						data:{"id":$(this).attr("value")},
					   type:"POST",
					   success:function(data){
						   if("t"==data){
							    alert("取消成功！");
								window.location.reload();
						   }
						},
						error:function(){
							alert("网络错误！");
						}					   
					});
				}
				
			};
		}(i));
	} */
	//删除兼职
	<%--var span_z_delete_nums=$(".ul_span_z_nei li .span_z_delete");--%>
	<%--for(var i=0;i<span_z_delete_nums.length;i++){--%>
	<%--	$(".ul_span_z_nei li .span_z_delete:eq("+i+")").click(function(nums){--%>
	<%--		return function(){--%>
	<%--			var tf=confirm("确定删除兼职吗？");--%>
	<%--			if(tf){--%>
	<%--				$.ajax({--%>
	<%--				   url:"${ctx}/pc/personal/deleteMyWork.action",--%>
	<%--				   data:{"id":$(this).attr("value")},--%>
	<%--				   type:"POST",--%>
	<%--				   success:function(res){--%>
	<%--					   if(res.code == 0){--%>
	<%--						   	alert("删除成功！");--%>
	<%--							window.location.reload();--%>
	<%--					   }--%>
	<%--					},--%>
	<%--					error:function(){--%>
	<%--						alert("网络错误！");--%>
	<%--					}--%>
	<%--				});--%>
	<%--			};--%>
	<%--		}--%>
	<%--	}(i));--%>
	<%--}--%>
	</script>
</body>
</html>