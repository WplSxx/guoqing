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
	<form id="userFormTwo">
	<table>
		<tr>
			<td>缴费编号：</td>
			<td>
				${id}
				<input type="hidden" name="pid" value="${id}"/>
			</td>
		</tr><br/><br/>
		<tr>
			<td>应交金额：</td>
			<td>
			    ${yinj}
				
			</td>
		</tr><br/><br/>
		<tr>
			<td>缴费金额：</td>
			<td>
			   <input type="text" name="pamount" class="easyui-textbox" value="" />
			   <input type="hidden" name="pamount_must" class="easyui-textbox" value="${yinj}" />
			</td>
		</tr>
		<tr>
			<td>缴费金额：</td>
			<td>
			   微信:<input type="radio" name="pform1" value="微信" checked="checked"/>
			  支付宝: <input type="radio" name="pform1" value="支付宝" />
			 现金:  <input type="radio" name="pform1" value="现金" />
			 <input type="hidden" name="pform" value="微信" />
			</td>
		</tr>
		</table>
	</form>
</body>
</html>