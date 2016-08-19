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
    myTaskList
  	<form action="task.do?actionType=doSelectMyTask" method="post">
	<jsp:include page="/vave/task/SearchTaskTbar.jsp"></jsp:include>
	</form>	
   <table>
   <thead><tr >
   	<td>状态</td>
  	<td>类型</td>
    <td>工作内容</td>
    <td>任务接受者姓名</td>
    <td>任务接受者部门</td>
    <td>项目负责人姓名</td>
    <td>项目名称</td>
    <td>计划日期</td>
    <td>完成日期</td>
    <td>备注</td>
   </tr></thead>
  
   <tbody> 
   <c:forEach items="${taskList}" var="item" >
   <tr>
    <td>${item.pstate }</td>
   <td>${item.type }</td>
   <td><a href="proposal.do?actionType=doSelectByProposalID_TaskShow&proposalID=${item.vaveTeamWork.tproposal.id }">${item.jobContent }</a></td>
   <td>${item.responsiblePerson.name }</td>
    <td>${item.responsiblePerson.tdepartment.name }</td>
   <td>${item.vaveTeamWork.projectManager.name }</td>  
    <td>${item.vaveTeamWork.teamName }</td> 
   <td>${item.planTiem }</td>
   <td>${item.finishTiem }</td>
   <td>${item.remark }</td>
   </tr>
   </c:forEach>
   </tbody>

  </body>
</html>
