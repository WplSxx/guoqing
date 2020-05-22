<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="hidden" id="id" value="${id}">
	
	<c:forEach items="${list}" var="r">
		<input type="checkbox" 
			<c:forEach items="${urole }" var="u">
				<c:if test="${u.id ==r.id }">
					checked
				</c:if>
			</c:forEach> 
		value="${r.id}" name="roleid" />${r.root_name }<br>
		
	</c:forEach>
</body>
</html>