<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>vave提案单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style type="text/css" media="screen">@import url(css/vave.css);</style>	
  </head>

  <body >
team
  	<form action="task.do?actionType=doSelectMyTeamTask" method="post">
	<tr><jsp:include flush="true" page="/vave/task/SearchTaskTbar.jsp"></jsp:include></tr>
	</form>
  
   <table id="title" width="600px">
   
   <thead><tr>
   <td>计划完成日期</td>
   <td>项目名称</td>
   <td>项目经理</td>
   <td>项目部门</td>
   </tr></thead>
   <tbody>
   <c:forEach items="${teamWorks}" var="item" >
   <tr>
   <td>${item.planfinishtime }</td>
   <td>
   <a href="proposal.do?actionType=doSelecetMyTaskTeam&proposalID=${item.tproposal.id }">
   ${item.teamName }</a>
   </td>
	<td>${item.projectManager.name }</td>
	<td>${item.projectManager.tdepartment.name }</td>
   </tr>
   </c:forEach>
   </tbody>
  </table>
  </body>
</html>
