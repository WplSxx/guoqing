<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="userForm">
		<tr>
			<td>权限名称：</td>
			<td>
				<input type="text" name="org_name" class="easyui-textbox" value="${org.org_name}"/>
				<input type="hidden" name="id" value="${org.id}"/>
			</td>
		</tr><br><br>
		<tr>
			<td>url：</td>
			<td>
				<input type="text" name="url" class="easyui-textbox" value="${org.url}" />
			</td>
		</tr><br><br>
		<tr>
			<td>是否父节点：</td>
			<td>
				是：<input type="radio" name="isfId" value="1" checked="checked" onclick="checkedIsFid(this);"/>
				否：<input type="radio" name="isfId" value="2" onclick="checkedIsFid(this);"/>
			</td>
		</tr><br><br>
		<tr >
		<div id="fidTr" style="display:none;">
			<td>父节点：</td>
			<td>
			<c:forEach items="${fidList}" var="r">
				${r.org_name }:<input type="radio" name="fid" value="${r.id}"/>
			</c:forEach>
			</td>
			</div>
		</tr><br><br>
		<tr>
			<td>是否展开：</td>
			<td>
				否：<input type="radio" name="state" value="closed" checked="checked"/>
				是：<input type="radio" name="state" value="open" />
			</td>
		</tr><br><br>
	</form>
	<script type="text/javascript">
	  function checkedIsFid(a){
		  var idValue = $(a).val();
		  if(idValue=="2"){
			  $("#fidTr").show();
		  }else{
			  $("#fidTr").hide();
		  }
	  }
	</script>
</body>
</html>