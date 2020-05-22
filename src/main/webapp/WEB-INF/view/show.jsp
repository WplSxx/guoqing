<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/base.jsp"></jsp:include>
</head>
<body class="easyui-layout">   
    <div data-options="region:'north',title:'头部页面',split:true" style="height:200px;"></div>   
    <div data-options="region:'west',title:'菜单栏',split:true" style="width:200px;">
    	<div id="aa" class="easyui-accordion" style="width:300px;height:200px;" data-options="fit:true">   
		    	<div class="easyui-tree" id="menu_tree">
		    		
		    	</div>
		</div>  
    </div> 
      
    <div data-options="region:'center',title:'主页面'">
    	<div id="tab" class="easyui-tabs" style="width:500px;height:250px;" data-options="fit:true">   
		     
		</div>  
    </div> 
    
    
    <script type="text/javascript">
	    $("#menu_tree").tree({
			url:"<%=request.getContextPath()%>/getTree.jk",
			onClick:function(node){
				if(node.id>1){
					if($("#tab").tabs("exists",node.text)){
						$("#tab").tabs("select",node.text)
					}else{
						$("#tab").tabs("add",{
							title:node.text,
							closable:true,
							href:node.url,
						})
				}
				
				}
				
			},
			method:"post",
			checkbox:true,
			cascadeCheck:false,
	    })	
    </script> 
	     
</body>
</html>