<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath",basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
 	 <title>管理人员-管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
	
  
  </head>
  
  <body>
 <table cellspacing="1" cellpadding="4" width="90%"
			class="tableborder" id="table3">
   <thead>
   <tr>
				<td colspan="7" class="header">添加新任务 
				</td>
  </tr>
   <tr>
   <td align="center" class="altbg1"><b>任务级别</b></td>
   <td align="center" class="altbg1"><b>类型</b></td>
   <td align="center" class="altbg1"><b>工作内容</b></td>
   <td align="center" class="altbg1"><b>负责人</b></td>
   <td align="center" class="altbg1"><b>计划完成日期</b></td>
  <td align="center" class="altbg1"><b>实际完成日期</b></td>
  <td align="center" class="altbg1"><b>备注</b></td>
   </tr> </thead>
  <tbody>

  <form action="task.do?actionType=doInsertMyTeamTask" method="post" >
 <tr>  
 <input name="teamWorkProject.vaveTeamWork.workTeamId" type="hidden" value="${param.teamWorkID }"/>
<input name="proposalID" type="hidden" value="${param.proposalID }" >	
	<td align="center" class="altbg1"><input name="teamWorkProject.grade" type="text" /></td>
   <td align="center" class="altbg1"><input name="teamWorkProject.type" type="text" /></td>
   <td align="center" class="altbg1"><input name="teamWorkProject.jobContent" type="text" /></td>  
    <td align="center" class="altbg1"><input name="teamWorkProject.responsiblePerson" type="text" /></td>  
     <td align="center" class="altbg1"><input name="teamWorkProject.myTime" type="text" /></td>  
    <td align="center" class="altbg1"><input name="teamWorkProject.myFinishTime" type="text" /></td>  
     <td align="center" class="altbg1"><input name="teamWorkProject.remarks" type="text" /></td>  
   </tr> 
   <tr><input type="submit" value="提交"/> </tr>
   </from>

<tr >
</tr>
   </tbody>
   </table>


  </body>
</html>
