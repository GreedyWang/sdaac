<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'SearchTaskTbar.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <form action="task.do?actionType=doSelectMyTeamTask" method="post">
   <table>
   <tr>
   <td>任务类型<input type="text" name=""> </td>
   <td>项目名称<input type="text" name="teamWork.teamName"></td>
   <td>工作内容<input type="text" name="teamWorkProject.jobContent"></td>
   <td>负责人姓名<input type="text" name="teamWorkProject.responsiblePerson.name"></td>
   <td>部门<input type="text" id="1" name="teamWorkProject.responsiblePerson.tdepartment.id"></td>
   </tr><tr>
   <td>项目经理姓名 <input type="text" name="teamWork.projectManager.name"> </td>
   <td>计划完成时间<input type="text" name="teamWorkProject.myTime"></td>
   <td>实际完成时间<input type="text" name="teamWorkProject.myFinishTime"></td>
 

   <td>备注<input type="text" name=""></td>
   <td><input type="submit" value="搜索"> </td>
   </tr>
   </table>
	</form>
  </body>
</html>
