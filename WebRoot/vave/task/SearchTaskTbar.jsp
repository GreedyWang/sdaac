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
   
   <table>
   <tr>
   <td>�������� </td>
   <td>��Ŀ����</td>
   <td>��������</td>
   <td>����������</td>
   <td>����</td>
   </tr>
   <tr>
   	<td><input type="text" name=""></td>
   	<td><input type="text" name="teamWork.teamName"></td>
   	<td><input type="text" name="teamWorkProject.jobContent"></td>
   	<td><input type="text" name="teamWorkProject.responsiblePerson.name"></td>
   	<td><input type="text" name="teamWorkProject.responsiblePerson.tdepartment.id"></td>
   </tr>
   
   <tr>
   <td>��Ŀ��������  </td>
   <td>�ƻ����ʱ��</td>
   <td>ʵ�����ʱ��</td>
   <td>��ע</td>
   <td> ����</td>
   </tr>
  <tr>
  <td><input type="text" name="teamWork.projectManager.name"></td>
  <td><input type="text" name="teamWorkProject.myTime"></td>
  <td><input type="text" name="teamWorkProject.myFinishTime"></td>
  <td><input type="text" name=""></td>
  <td><input type="submit" value="����"></td>
  </tr>
   </table>

  </body>
</html>
