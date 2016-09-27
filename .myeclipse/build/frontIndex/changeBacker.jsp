<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript">
		function init(){
			var back = '${logineduser.emp.backer}'
			alert(back)
			var roles = document.getElementById('backer')
			if(back!=null && back!=''){
				var backs = back.split(',');
				for (var i = 0; i < backs.length; i++) {
						var opt = document.createElement('Option');
						opt.text = backs[i]
						opt.value = backs[i]
						roles.options.add(opt);
					}
			}
		}
	</script>

  </head>
  
  <body onload="init()">
  <form action="login.do?actionType=doLoginByUid" method="post">
  
     <tr><td>选择一个工号：
     <select name="uname" id="backer">
     
     </select></td></tr>
     <tr><td><input type="submit" value="确定"> </td></tr>
     </form>
  </body>
</html>
