<%@ page language="java"  pageEncoding="utf-8"%>
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
	<script type='text/javascript' src='/bpp/dwr/interface/teamWorkManager.js'></script>
  <script type='text/javascript' src='/bpp/dwr/engine.js'></script>
	
  </head>
  
  <script type="text/javascript">
  	function update(){
  		var vaveTeamWork={
  			planfinish:document.getElementById("time").value,
  			estimatedCostSavings:document.getElementById("money").value,
  			workTeamId:document.getElementById("teamId").value
  		}
  		teamWorkManager.changeTeamWorkActSaving(vaveTeamWork,function(data){
  			alert("success");
  		})
  	}
  </script>
  
  <body>
   <table>
 
   <input id="teamId" type="hidden" value="${param.projectId }">
  		<tr><td>预计节约金额</td><td><input type="text" id="money"> </td></tr>
   		<tr><td>预计节约完成日期</td><td><input type="text" id="time">  </td></tr>
   		<tr><td><input type="button" value="确定" onclick="update()"> </td></tr>

   </table>
  </body>
</html>
