<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/base.jsp"></jsp:include>
</head>
<body>
<div id="tb">
   	<a onclick="addRow('0')"  class="easyui-linkbutton" iconCls="icon-add" plain="true"  >添加权限</a>
</div>
<c:forEach items="${userrole}" var="r">
	<input type="hidden" id="${r.id}" root_name="${r.root_name}" name="role">
</c:forEach>
<table id="rolemsg"></table>
<div id="pow"></div>
<div id="add_dialog"></div>
<script type="text/javascript">
$("#rolemsg").datagrid({
	url:"<%=request.getContextPath()%>/org/queryOrg.jk",
	columns:[[
			 	{field:'id', title:'权限编号', width:100},
			 	{field:'org_name', title:'权限名称', width:100},
				{field:'url', title:'url', width:100},
			 	{field:'fid', title:'父节点', width:100},
			 	{field:'state', title:'状态', width:100},
			 	{field:'', title:'操作', width:100,
			 		formatter:function(value,row,index){
			 			var btn_str = "";
					    btn_str+='<a href="javascript:addRow('+row.id+')">修改</a>';
					    btn_str+="&nbsp;&nbsp;&nbsp;"
						btn_str+='<a href="javascript:deleteOrg('+row.id+')">删除</a>';
			 			return btn_str;
			 		}},
			 ]],
			 fit:true,
			 fitColumns:true,
});
		

//添加角色
function addRow(id){
	$("#add_dialog").dialog({
		title:"添加权限的弹框",
		width:300,
   		height:400,
   		modal: true ,
   		href:"<%=request.getContextPath()%>/org/toAddOrg.jk?id="+id,
   		buttons:[{
			text:'保存',
			handler:function(){
				
				$.ajax({
					type:"post",
					url:"<%=request.getContextPath()%>/org/addOrg.jk",
					data:$("#userForm").serialize(),
					dataType:"json",
					success:function(data){
						if(data.success){
							$.messager.alert('提示','保存成功');
							$("#add_dialog").window("close");
							$("#rolemsg").datagrid("reload");
						}
					},
					error:function(){
						$("#add_dialog").window("close");
					}
				})
			}
		},{
			text:'关闭',
			handler:function(){
				$("#add_dialog").window("close");
			}
		}]
	});
	$("#add_dialog").window("open");
}
function deleteOrg(id){
    $.messager.confirm('提示', '确定要删除吗?', function(b){  
        if (b){  
        	$.ajax({
				url:"<%=request.getContextPath()%>/org/deleteOrg.jk",
				data:{"id":id},
				dataType:"json",
				success:function(data){
					if(data.success){
						$.messager.alert('提示','删除成功');
						$("#rolemsg").datagrid("reload");
					}
				},
				error:function(){
				}
			})
        } 
  });  
}

   
</script>
</body>
</html>