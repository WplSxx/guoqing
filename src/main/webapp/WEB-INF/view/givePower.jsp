<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="powTree"></div>
	<script type="text/javascript">
	$(function(){
		 $("#powTree").tree({
			url:'<%=request.getContextPath()%>/getPower.jk',
			checkbox:true,
	     });	
		 $.ajax({
				url:"<%=request.getContextPath()%>/getPowerByRid.jk?id=${id}",
				dataType:"json",
				cache:false,
				asyun:false,
				success:function(data){
					alert();
					$(data).each(function(i,obj){
						var n = $("#powTree").tree('find',obj.id);
						if(n){
							$("#powTree").tree('check',n.target);
						}
					});
				},
				error:function(){
					alert("发送请求失败")
				}
			 });
		
	});
		
	</script>
</body>
</html>