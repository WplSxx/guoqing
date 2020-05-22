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

<!-- 查询用户的展示页面 -->
<body>
	<c:forEach items="${userrole}" var="r">
		<input type="hidden" id="${r.id}" root_name="${r.root_name}" name="role">
	</c:forEach>
	
	<table id="tabb"></table>
	
	<div id="add_dialog">
	
    </div>
	<div id="update_dialog">
	
    </div>
	<div id="update_pwd_dialog">
	
    </div>
    
    
    <div id="tb">
    	<a onclick="addUser()"  class="easyui-linkbutton" iconCls="icon-add" plain="true"  >添加用户</a>
		<a href="javascript:daochul()"   class="easyui-linkbutton" iconCls="icon-add" plain="true"  >导出</a> 
		<form id="Manage" method="post" enctype="multipart/form-data" action="" novalidate>
			 <input id="uploadExcel" name="uploadExcel" class="easyui-filebox" style="width: 70%" data-options="prompt:'选择文件...'"> 
			 <a href="#" class="easyui-linkbutton" style="width: 10%" onclick="uploadExcel()">导入学生</a>
		</form>
	</div>
    
<script type="text/javascript">
	//用户信息的添加方法
	function addUser(){
		$("#add_dialog").dialog({
			title:"添加用户的弹框",
			width:300,
    		height:200,
    		modal: true ,
    		href:"<%=request.getContextPath()%>/toaddUser.jk",
    		buttons:[{
				text:'保存',
				handler:function(){
					$.ajax({
						type:"post",
						url:"<%=request.getContextPath()%>/addUser.jk",
						data:$("#userForm").serialize(),
						dataType:"json",
						success:function(data){
							if(data.success){
								$.messager.alert('提示','保存成功');
								$("#add_dialog").window("close");
								$("#tabb").datagrid("reload");
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
					$("#tabb").datagrid("reload");
					
				}
			}]
		});
		$("#add_dialog").window("open");
	}		
			//展示用户的js方法
			$('#tabb').datagrid({
				toolbar: '#tb',
				url:"<%=request.getContextPath()%>/getUserBy.jk",
				columns:[[
				 	{field:'id', title:'用户编号', width:100},
				 	{field:'user_name', title:'用户姓名', width:100},
					{field:'pwd', title:'用户密码', width:100},
				 	{field:'nickname', title:'用户昵称', width:100},
				 	{field:'crtime', title:'创建时间', width:100},
				 	{field:'uptime', title:'修改时间', width:100},
				 	{field:'', title:'操作', width:300,
				 		formatter:function(value,row,index){
				 			var btn_str="";
			            	  btn_str+="<button type='button' class='btn btn-danger glyphicon glyphicon-plane' onclick=fenpei('"+row.id+"')>分配角色</button>";
			            	  btn_str+="<button type='button' class='btn btn-danger glyphicon glyphicon-plane' onclick=deleteUser('"+row.id+"')>删除</button>";
			            	  btn_str+="<button type='button' class='btn btn-danger glyphicon glyphicon-plane' onclick=updateUser('"+row.id+"')>修改</button>";
			            	  btn_str+="<button type='button' class='btn btn-danger glyphicon glyphicon-plane' onclick=updatePwdUser('"+row.id+"')>重置密码</button>";
			            	  return btn_str;
				 		}	
				 	},
				 ]],
				 fit:true,
				 fitColumns:true,
				 checkbox:true,
			});
		
			  //添加角色
				function fenpei(id){
					var fe = false;
				 $("input[name='role']").each(function(){
					 if($(this).attr("id") ==1){
							fe=true;
						}else if($(this).attr("id") ==2){
							fe=true;
						}
				 });
					if(fe){
					$('#add_dialog').dialog({  
		    		    title: '添加角色',    
		    		    width: 200,    
		    		    height: 250,  
		    		    collapsible:false,
		    		    closed: false,
		    		    closable:true,
		    		    cache: false,    
		    		    href:'<%=request.getContextPath()%>/getRoot.jk?id='+id,
						toolbar:[{
							text:'保存',
							iconCls:'icon-save',
							handler:function(){
								var ids = "";
								$("input[name='roleid']:checked").each(function(){
									ids+=","+$(this).val();
								});
								if(ids == ""){
									alert("请至少选择一个")
									return;
								}
								ids = ids.substr(1);
								$.ajax({
									url:"<%=request.getContextPath()%>/giveRole.jk",
									type:"post",
									data:{"id":$("#id").val(),"ids":ids},
									dataType:"json",
									success:function(data){
										if(data.success){
											alert("成功")
											$("#add_dialog").dialog("close");
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
							}
						},{
							text:'取消',
							iconCls:'icon-cancel',
							handler:function(){
								$("#add_dialog").dialog("close");
							}
						}]		 
		    		})	
					}else{
						alert("权限不足");
					}	 
			  }
			  
			  //导出的方法
				function daochul(){
					window.location.href="<%=request.getContextPath()%>/Daochul.jk"
				}
			 
			  
	    //导入excel
		function uploadExcel() {
			//得到上传文件的全路径
			var fileName = $('#uploadExcel').filebox('getValue')
			//进行基本校验
			if (fileName == "") {
				$.messager.alert("提示",
						"请选择上传文件！", "info");
			} else {
				//对文件格式进行校验
				var d1 = /\.[^\.]+$/.exec(fileName);
				if (d1 == ".xls" || d1==".xlsx") {
					$('#Manage')
							.form(
									'submit',
									{
										url : "<%=request.getContextPath()%>/Daoru.jk",
										onSubmit : function() {
											return $(this).form('validate');
										},
										success : function(result) {
											var result = eval('(' + result+ ')');
											if (result == "error") {
												$.messager.alert("警告", "导入失败！","error");
												$('#studentImport').dialog('close'); 
												$('#dg').datagrid('reload'); 
											} else {
												$.messager.alert("提示", "导入成功！","info");
												$('#studentImport').dialog('close'); 
												$('#dg').datagrid('reload'); 
											}
										}
									});
				} else {
					$.messager.alert("提示",
							"请选择xls格式文件！", "info");
					$('#uploadExcel').filebox('setValue', '');
				}
			}
		}
	    function deleteUser(id){
	        $.messager.confirm('提示', '确定要删除吗?', function(b){  
	            if (b){  
	            	$.ajax({
						url:"<%=request.getContextPath()%>/deleteUser.jk",
						data:{"id":id},
						dataType:"json",
						success:function(data){
							if(data.success){
								$.messager.alert('提示','删除成功');
								$("#tabb").datagrid("reload");
							}
						},
						error:function(){
						}
					})
	            } 
	      });  
	    }
	    
		//修改用户
		function updateUser(userId){
			$("#update_dialog").dialog({
				title:"修改用户的弹框",
				width: 200,    
    		    height: 250,  
    		    collapsible:false,
    		    closed: false,
    		    closable:true,
    		    cache: false,    
	    		href:"<%=request.getContextPath()%>/toaddUser.jk?id="+userId,
	    		buttons:[{
					text:'保存',
					handler:function(){
						$.ajax({
							type:"post",
							url:"<%=request.getContextPath()%>/addUser.jk",
							data:$("#userForm").serialize(),
							dataType:"json",
							success:function(data){
								if(data.success){
									$.messager.alert('提示','保存成功');
									$("#update_dialog").window("close");
									$("#tabb").datagrid("reload");
								}
							},
							error:function(){
								$("#update_dialog").window("close");
							}
						})
					}
				},{
					text:'关闭',
					handler:function(){
						$("#update_dialog").window("close");
					}
				}]
			});
			$("#update_dialog").window("open");
		}
		//重置密码
		function updatePwdUser(userId){
			$("#update_pwd_dialog").dialog({
				title:"重置密码的弹框",
				width: 200,    
    		    height: 250,  
    		    collapsible:false,
    		    closed: false,
    		    closable:true,
    		    cache: false,    
	    		href:"<%=request.getContextPath()%>/toUpdateUser.jk?id="+userId,
	    		buttons:[{
					text:'保存',
					handler:function(){
						$.ajax({
							type:"post",
							url:"<%=request.getContextPath()%>/addUser.jk",
							data:$("#userForm").serialize(),
							dataType:"json",
							success:function(data){
								if(data.success){
									$.messager.alert('提示','保存成功');
									$("#update_pwd_dialog").window("close");
									$("#tabb").datagrid("reload");
								}
							},
							error:function(){
								$("#update_pwd_dialog").window("close");
							}
						})
					}
				},{
					text:'关闭',
					handler:function(){
						$("#update_pwd_dialog").window("close");
					}
				}]
			});
			$("#update_pwd_dialog").window("open");
		}
	</script>
</body>
</html>