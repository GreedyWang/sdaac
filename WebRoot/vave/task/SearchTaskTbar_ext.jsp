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
   <td>��������<input type="text" name=""> </td>
   <td>��Ŀ����<input type="text" name="teamWork.teamName"></td>
   <td>��������<input type="text" name="teamWorkProject.jobContent"></td>
   <td>����������<input type="text" name="teamWorkProject.responsiblePerson.name"></td>
   <td>����<input type="text" id="1" name="teamWorkProject.responsiblePerson.tdepartment.id"></td>
   </tr><tr>
   <td>��Ŀ�������� <input type="text" name="teamWork.projectManager.name"> </td>
   <td>�ƻ����ʱ��<input type="text" name="teamWorkProject.myTime"></td>
   <td>ʵ�����ʱ��<input type="text" name="teamWorkProject.myFinishTime"></td>
 

   <td>��ע<input type="text" name=""></td>
   <td><input type="submit" value="����"> </td>
   </tr>
   </table>
	</form>
  </body>
</html>
