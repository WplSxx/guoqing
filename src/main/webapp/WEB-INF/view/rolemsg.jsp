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
   	<a onclick="addRole()"  class="easyui-linkbutton" iconCls="icon-add" plain="true"  >添加角色</a>
</div>
<c:forEach items="${userrole}" var="r">
	<input type="hidden" id="${r.id}" root_name="${r.root_name}" name="role">
</c:forEach>
<table id="rolemsg"></table>
<div id="pow"></div>
<div id="add_dialog"></div>
<script type="text/javascript">
$("#rolemsg").datagrid({
	url:"<%=request.getContextPath()%>/queryRole.jk",
	columns:[[
			 	{field:'id', title:'角色编号', width:100},
			 	{field:'root_name', title:'角色名称', width:100},
				{field:'crea_user', title:'创建人', width:100},
			 	{field:'crea_time', title:'创建时间', width:100},
			 	{field:'', title:'操作', width:100,
			 		formatter:function(value,row,index){
			 			var btn_str = "";
					    btn_str+='<a href="javascript:toPow('+row.id+')">分配权限</a>';
					    btn_str+="&nbsp;&nbsp;&nbsp;"
						btn_str+='<a href="javascript:deleteUser('+row.id+')">删除</a>';
			 			return btn_str;
			 		}},
			 ]],
			 fit:true,
			 fitColumns:true,
});
		
function toPow(id){
	var flag = false;
	 $("input[name='role']").each(function(){
		 if($(this).attr("id") ==1){
			 	flag=true;
			}else if($(this).attr("id") ==2){
				flag=true;
			}
	 });
	if(flag){
		$('#pow').dialog({  
   		    title: '权限列表',    
   		    width: 200,    
   		    height: 250,  
   		    closed: false,
   		    cache: false,    
   		    href:'<%=request.getContextPath()%>/toPowPage.jk?id='+id,
			buttons:[{
				text:'确定',
				iconCls:'icon-save',
				handler:function(){
					var node = $("#powTree").tree('getChecked');
					var ids="";
					for(var i=0; i<node.length;i++){
						ids+=node[i].id+",";
					}
					
					 //保存用户和角色之间关系
					 $.ajax({
						url:"<%=request.getContextPath()%>/role/addUserRole.jk",
						type:"post",
						dataType:"json",
						cache:false,
						asyun:true,
						data:{"rowId":ids,"roleId":id},
						success:function(data){
                           if(data.success){
	                        	alert("保存成功");   
	   							$("#pow").window("close");
                           }else{
                        	    alert(data.msg);
	   							$("#pow").window("close");
                           }                              
						},
						error:function(){
							alert("发送请求失败")
						}
					 });
				}
			}]		 
   		});	
	}else{
		alert("权限不足");
	}	 
 }


//添加角色
function addRole(){
	$("#add_dialog").dialog({
		title:"添加角色的弹框",
		width:300,
   		height:200,
   		modal: true ,
   		href:"<%=request.getContextPath()%>/role/toAddRole.jk",
   		buttons:[{
			text:'保存',
			handler:function(){
				var node = $("#powTreeTwo").tree('getChecked');
				var ids=[];
				for(var i=0; i<node.length;i++){
					ids+=node[i].id+",";
				}
				var role_name = $("input[name='role_name']").val();
				$.ajax({
					type:"post",
					url:"<%=request.getContextPath()%>/role/addRole.jk",
					data:{"root_name":role_name,"rowId":ids},
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
function deleteUser(id){
    $.messager.confirm('提示', '确定要删除吗?', function(b){  
        if (b){  
        	$.ajax({
				url:"<%=request.getContextPath()%>/role/deleteRole.jk",
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