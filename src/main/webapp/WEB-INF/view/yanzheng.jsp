<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<jsp:include page="/base.jsp"></jsp:include>
<body>
	<a href="javascript:void(0);" onclick="yanzheng();"> 激活账户</a>
</body>
<script>
   function yanzheng(){
	    var userName = GetQueryString("user_name");
	    $.ajax({
				 url:"<%=request.getContextPath()%>/updateUser.jk",
				 type:"post",
				 data:{"user_name":userName},
				 dataType:"text",
				 success:function(){
// 					window.location.href="/"; 
					alert("激活成功")
			     },
				error:function(){
					$("#add_dialog").dialog("close");
					  $.messager.alert("警告","大表哥新增报错");    
				}
			 });
   }
   
   
   function GetQueryString(name)
   {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null)return  unescape(r[2]); return null;
   }
</script>
</html>