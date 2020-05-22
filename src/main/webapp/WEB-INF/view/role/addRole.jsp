<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="../../../base.jsp"></jsp:include>
</head>
<body>
	<form id="userForm">
		<tr>
			<td>角色名：</td>
			<td>
				<input type="text" name="role_name" class="easyui-textbox" value="${user.user_name}"/>
				<input type="hidden" name="id" value="${user.id}"/>
			</td>
		</tr><br><br>
		
	</form>
	<div id="powTreeTwo"></div>
	<script type="text/javascript">

		$("#powTreeTwo").tree({
			url:'<%=request.getContextPath()%>/getPower.jk',
			checkbox:true,
		});	
		$.ajax({
		url:"<%=request.getContextPath()%>/getPowerByRid.jk",
		dataType:"json",
		cache:false,
		asyun:true,
		success:function(data){
			$(data).each(function(i,obj){
				var n = $("#powTreeTwo").tree('find',obj.id);
				if(n){
					$("#powTreeTwo").tree('check',n.target);
				}
			});
		},
		error:function(){
			alert("发送请求失败")
		}
		});

</script>
</body>

</html>