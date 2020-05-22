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
	<table id="tab" style="padding-top:105px;"></table>
<script type="text/javascript">
	
		
			$('#tab').datagrid({
				url:"<%=request.getContextPath()%>/invoice/getInvoice.jk",
				columns:[[
				 	{field:'id', title:'用户编号', width:100},
				 	{field:'user_name', title:'用户姓名', width:100},
					{field:'money', title:'金额', width:100},
				 	{field:'status', title:'状态', width:100,formatter: function(value,row,index){
							if(value==0){
								return "草稿";
							}else if(value == 1){
								return "已提交，待审核";
							}else {
							    return "审核通过";
							}
						}
					},
					{field:'type', title:'发票类型', width:100,formatter: function(value,row,index){
							if(value==1){
								return "办公用品";
							}else if(value == 2){
								return "差旅";
							}else if(value==3){
							    return "餐饮";
							}else{
								return "个人消费";
							}
						}
					},
				 	{field:'examine_name', title:'审核人', width:100},
				 	{field:'examine_time', title:'审核时间', width:100,
				 		formatter:function(value,row,index){  
                            if(value != null){
	                         	var unixTimestamp = new Date(value);  
	                         	return unixTimestamp.toLocaleString();  
                            }else{
                           	 return "";
                            }
	                    } 	
				 	},
				 	{field:'', title:'操作', width:100,formatter:function(value,row,index){
				 		   if(row.status == 0){
						     return '<a href="javascript:toPow('+row.id+')">提交</a>';
				 		   }else if(row.status == 1){
				 			 return '已提交';
				 		   }else if(row.status == 2){
				 			 return '已审核通过';
				 		   }
			 		    },
			 		},
				 ]],
				 fitColumns:true,
				// striped:true,
				// pagination:true,
				 fit:true,
				// toolbar:"#where", 
			});
			
			function toPow(id){
				$.ajax({
					url:"<%=request.getContextPath()%>/invoice/update.jk",
					type:"post",
					data:{"id":id,"status":"1"},
					dataType:"json",
					success:function(data){
						if(data.success == "true"){
							alert("提交成功")
							  $("#tab").datagrid('reload');  
						}else{
							alert("失败")
						}
						
						//$('#table_datagrid').datagrid('reload'); // 重新载入当前页面数据  
					},
					error:function(){
						$("#add_dialog").dialog("close");
						  $.messager.alert("警告","新增报错");    
					}
					
				})
			};
	</script>
</body>
</html>