<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="userForm">
		<tr>
			<td>用户姓名：</td>
			<td>
				<input type="text" name="user_name" class="easyui-textbox" value="${user.user_name}"/>
				<input type="hidden" name="id" value="${user.id}"/>
			</td>
		</tr><br><br>
		<tr>
			<td>用户密码：</td>
			<td>
				<input type="text" name="pwd" class="easyui-textbox" value="${user.pwd}" />
			</td>
		</tr><br><br>
		<tr>
			<td>用户昵称：</td>
			<td>
				<input type="text" name="nickname" class="easyui-textbox" value="${user.nickname}"/>
			</td>
		</tr><br><br>
	</form>
</body>
</html>