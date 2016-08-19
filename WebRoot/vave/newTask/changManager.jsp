<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'changManager.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	   <script type='text/javascript' src='/bpp/dwr/engine.js'></script>
	<script type='text/javascript' src='/bpp/dwr/interface/EmpManager.js'></script>
  </head>
  <script type="text/javascript">
	 function checkUid(elem)
	  {	 
	  	var uid=elem.value;
	  	EmpManager.doSelectById(uid,function(data)
	  	{
	  		document.getElementById('uname').innerText=data;
	  	});
	  }
	  function checkName()
	  {
	 
	  if(	document.getElementById('uname').innerText!='[]') return true;
	  else return false;
	  }
  </script>
  
  <body>
  <form action="task.do?actionType=ChangeProjectManager" method="post" onsubmit="return checkName()">
  <table>
  <tr>
   <td>员工编号</td>
   <td><input type="text" name="teamWork.projectManager.uid" onblur="checkUid(this)"></td>
   <input  type="hidden" value="${param.projectId }" name="teamWork.tproposal.id">
   <td>员工姓名</td>
   <td><label id="uname"></label> </td>
   </tr>
   <tr><td><input type="submit" value="确定"> </td></tr>
   </table>
  </form>
  </body>
</html>
