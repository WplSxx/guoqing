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

<c:forEach items="${userrole}" var="r">
	<input type="hidden" id="${r.id}" root_name="${r.root_name}" name="role">
</c:forEach>
<div id="add_dialogPay"></div>
<table id="pay"></table>

<script type="text/javascript">
$("#pay").datagrid({
	url:"<%=request.getContextPath()%>/student/dataPayList.jk?sid=${sid}",
	columns:[[
			 	{field:'pid', title:'缴费编号', width:100},
			 	{field:'className', title:'班级', width:100},
			 	{field:'sname', title:'学生姓名', width:100},
			 	{field:'pform', title:'缴费方式', width:100},
			 	{field:'ptime', title:'缴费时间', width:100},
			 	{field:'ptype', title:'费用类型', width:100,
			 		formatter:function(value,row,index){
			 			if(value==1){
			 				return "学费";
			 			}else if(value==2){
			 				return "住宿费";
			 			}else if(value==3){
			 				return "保证金";
			 			}else if(value==4){
			 				return "补交费";
			 			}
			 		}		
			 	},
			 	{field:'slive', title:'是否住校', width:100,
			 		formatter:function(value,row,index){
			 			if(value==2){
			 				return "否";
			 			}else{
			 				return "是";
			 			}
			 		}	
			 	},
			 	{field:'pamount', title:'欠费金额', width:100,
			 		formatter:function(value,row,index){
			 			return row.pamount_must - value;
		 			}
			 	},
			 	{field:'ispay', title:'是否欠费', width:100,
			 		formatter:function(value,row,index){
			 			if(value==0){
			 				return "否";
			 			}else{
			 				return "是";
			 			}
			 		}
			 	},
			 	{field:'', title:'操作', width:100,
			 		formatter:function(value,row,index){
			 			var btn_str = "";
			 			var yinj =row.pamount_must - row.pamount;
					    btn_str+='<a href="javascript:addRow('+row.pid+','+yinj+')">缴费</a>';
			 			return btn_str;
			 		}},
			 ]],
			 fit:true,
			 fitColumns:true,
});
		

//添加角色
function addRow(id,yinj){
	$("#add_dialogPay").dialog({
		title:"添加权限的弹框",
		width:300,
   		height:400,
   		modal: true ,
   		href:"<%=request.getContextPath()%>/student/toUpdatePay.jk?id="+id+"&yinj="+yinj,
   		buttons:[{
			text:'保存',
			handler:function(){
				$("input[name='pform']:checked").val($("input[name='pform1']:checked").val());
				$.ajax({
					type:"post",
					url:"<%=request.getContextPath()%>/student/updatePay.jk",
					data:$("#userFormTwo").serialize(),
					dataType:"json",
					success:function(data){
						if(data.success){
							$.messager.alert('提示','保存成功');
							$("#add_dialogPay").window("close");
							$("#pay").datagrid("reload");
						}
					},
					error:function(){
						$("#add_dialogPay").window("close");
					}
				})
			}
		},{
			text:'关闭',
			handler:function(){
				$("#add_dialogPay").window("close");
			}
		}]
	});
	$("#add_dialogPay").window("open");
}
   
</script>
</body>
</html>