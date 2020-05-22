<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="base.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登录</title>
</head>
<body background="img/timg.jpg">
        <div id="loginWin" class="easyui-window" title="登录" style="width:350px;height:215px;padding:5px;"
   minimizable="false" maximizable="false" resizable="false" collapsible="false">
    <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
        <form id="loginForm">
            <div style="padding:5px 0;">
                <label>帐号:</label>
                <input type="text" id="user_name" placeholder="输入您的登录账号"   name="user_name" style="width:260px;"></input>
            </div>
            <div style="padding:5px 0;">
                <label>密码:</label>
                <input type="password" id="pwd" name="pwd" placeholder="输入您的登录密码"  style="width:260px;" /><br>
                
            	<tr>
						<td>验证码：</td>
						<td>
							<input id="login_user_imgcode" id="yzm" class="easyui-validatebox" name="yzm" data-options="prompt:'请输入验证码',iconCls:'icon-lock'"><br>
								<span onclick="change_img_code()">
								<img id="login_user_imgcode_img" src="<%=request.getContextPath() %>/imgcode">
								<font color="red">看不清换一张</font>
								</span><br>
						</td>
				</tr>
            	
            </div>
            
            
            <div style="padding:8px 0;">
                <input type="button" value="登录" style="width:60px;" onclick="login()" />
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" value="注册" style="width:60px;" onclick="regis()" />
            </div>
            
            
        </form>
            </div>
    </div>
</div>

	<div id="add_dialog">
         
     </div>
	<script type="text/javascript">
	//注册弹框
    function regis(){
		alert(111111);
    	$("#add_dialog").dialog({
    		title:"用户注册",
    		width:300,
    		height:300,
    		modal: true , 
    		href:"<%=request.getContextPath()%>/toregister.jk",
    			onLoad:function(event,ui){
  					editor = KindEditor.create('textarea[name="user.userShow"]', {
  	     				resizeType : 0,
  	     				uploadJson : '<%=request.getContextPath() %>/js/kindeditor/jsp/upload_json.jsp',
  	     				fileManagerJson : '<%=request.getContextPath() %>/js/kindeditor/jsp/file_manager_json.jsp',
  	     				 allowFileManager: true
  	     				});
  				},	
  				toolbar:[{
  					text:'注册',
  					iconCls:'icon-save',
  					handler:function(){
  						alert($("#reg_form").serialize()),
  						 $.ajax({
  							 url:"<%=request.getContextPath()%>/zhuce.jk",
  							 type:"post",
  							 data:$("#reg_form").serialize(),
  							 dataType:"text",
  							 success:function(){
  								$("#add_dialog").dialog("close");
								$('#table_datagrid').datagrid('reload');    // 重新载入当前页面数据  
								 $.messager.alert("注册成功请到邮箱激活");
  							 },
							error:function(){
								$("#add_dialog").dialog("close");
								  $.messager.alert("警告","大表哥新增报错");    
							}
  						 });
  						
  					}
  				},{
  					text:'取消',
  					iconCls:'icon-cancel',
  					handler:function(){
  						$("#add_dialog").dialog("close");
  						$("#table_datagrid").datagrid("reload");
  					}
  				
  				}]
    	})
    }
		
	
	
	//登录的方法
	function login(){
		if(checkForm()){
			 $.ajax({
				 type:"post",
				 url:"<%=request.getContextPath()%>/login.jk",
				 data:$("#loginForm").serialize(),
				 dataType:"json",
				  success:function(data){
					 if(data.success){
        				 alert(data.msg);
        				 //等成功后要跳转的页面
        				 location.href="getUser.jk"
        			 }else{
        				 alert(data.msg);
        			 } 
				 } 
			 })
		}
			
	     }
	
	
	//验证码的方法
	function change_img_code() {
		$("#login_user_imgcode_img").attr("src", "<%=request.getContextPath() %>/imgcode?time=" + new Date().getTime());
	}

	function checkForm(){
			var yzm = <%=request.getSession().getAttribute("yzm")%>;
			var reg = /^\w+$/;
			if(!reg.test($("#user_name").val())){
				alert("输入正确的用户名")
				return false;
			}else if(!reg.test($("#pwd").val())){
				alert("请输入正确的密码")
				return false;
			}else if($("#yzm").val() !=yzm){
				alert("看好了在输入")
				return false;
			}else{
				return true;
			}
			
		
	}
	</script>        
</body>
</html>

