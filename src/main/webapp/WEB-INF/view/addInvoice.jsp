<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发票提交模块</title>
<jsp:include page="/base.jsp"></jsp:include>
</head>
<!-- 查询用户的展示页面 -->
<body>
    <div>
		<button type='button'  onclick="addInvoice();" >添加发票</button>
	</div>
	 <div id="add_dialog">
    </div>
   
<script type="text/javascript">
			//添加角色
			function addInvoice(){
					$('#add_dialog').dialog({  
		    		    title: '报销发票',    
		    		    width: 400,    
		    		    height: 250,  
		    		    closed: false,
		    		    closable:true,
		    		    cache: false,    
		    		    href:'<%=request.getContextPath()%>/invoice/jumpInvoiceSumbmit.jk',
						toolbar:[{
							text:'保存',
							iconCls:'icon-save',
							handler:function(){
								$.ajax({
									url:"<%=request.getContextPath()%>/invoice/save.jk",
									type:"post",
									data:$("#reg_form").serialize(),
									dataType:"json",
									success:function(data){
										if(data.success == "true"){
											alert("成功")
											$("#add_dialog").dialog("close");
										}else{
											alert("错误")
										}
										
										$('#add_dialog').datagrid('reload'); // 重新载入当前页面数据  
									},
									error:function(){
										$("#add_dialog").dialog("close");
										  $.messager.alert("警告","新增报错");    
									}
									
								})
							}
						},{
							text:'取消',
							iconCls:'icon-cancel',
							handler:function(){
								$("#add_dialog").dialog("close");
								
							}
						}]		 
			  });
			  }
			 
			 
	</script>
</body>
</html>