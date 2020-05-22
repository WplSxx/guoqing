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
   	<a onclick="addRow('0')"  class="easyui-linkbutton" iconCls="icon-add" plain="true"  >欠费名单</a>
</div>
<div id="tb">
   	<a onclick="daochul()"  class="easyui-linkbutton" iconCls="icon-add" plain="true"  >生成欠费名单</a>
</div>
<c:forEach items="${userrole}" var="r">
	<input type="hidden" id="${r.id}" root_name="${r.root_name}" name="role">
</c:forEach>
<table id="rolemsg"></table>
<div id="pow"></div>
<div id="add_dialogrr"></div>
<script type="text/javascript">
$("#rolemsg").datagrid({
	url:"<%=request.getContextPath()%>/student/dataList.jk",
	columns:[[
			 	{field:'sid', title:'编号', width:100},
			 	{field:'sname', title:'学生姓名', width:100},
				{field:'sage', title:'学生年龄', width:100},
			 	{field:'sphone', title:'学生联系电话', width:100},
			 	{field:'sedu', title:'学生的学历', width:100},
			 	{field:'sjiguan', title:'学生的籍贯', width:100},
			 	{field:'sparent', title:'家长的姓名', width:100},
			 	{field:'spaphone', title:'家长的联系电话', width:100},
			 	{field:'slive', title:'是否住校', width:100},
			 	{field:'sdorm', title:'宿舍号', width:100},
			 	{field:'', title:'操作', width:100,
			 		formatter:function(value,row,index){
			 			var btn_str = "";
					    btn_str+='<a href="javascript:addRow('+row.sid+');">缴费记录</a>';
			 			return btn_str;
			 		}},
			 ]],
			 fit:true,
			 fitColumns:true,
});
		

//添加角色
function addRow(id){
	$("#add_dialogrr").dialog({
		title:"欠费名单",
		width:600,
   		height:400,
   		modal: true ,
   		href:"<%=request.getContextPath()%>/student/toPay.jk?id="+id,
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
							$("#add_dialogrr").window("close");
							$("#rolemsg").datagrid("reload");
						}
					},
					error:function(){
						$("#add_dialogrr").window("close");
					}
				})
			}
		},{
			text:'关闭',
			handler:function(){
				$("#add_dialogrr").window("close");
			}
		}]
	});
	$("#add_dialogrr").window("open");
}


//导出的方法
function daochul(){
	window.location.href="<%=request.getContextPath()%>/student/Daochul.jk"
}
</script>
</body>
</html>