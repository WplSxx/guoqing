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
	 <form id="reg_form">  
            <table align="center">  
                <tr>  
                    <td>金额：</td>  
                    <td>  
                        <input type="text" name="money"/>  
                    </td>  
                </tr>  
                <tr>  
                    <td>发票类型：</td>  
                    <td>  
                                                       办公用品 :<input type="radio" name="type" value="1"/>  
                                                       差旅 :<input type="radio" name="type" value="2"/>  
                                                      餐饮 :<input type="radio" name="type" value="3"/>  
                                                      个人消费 :<input type="radio" name="type" value="4"/>  
                    </td>  
                </tr>  
            </table>  
        </form>  
</body>
</html>