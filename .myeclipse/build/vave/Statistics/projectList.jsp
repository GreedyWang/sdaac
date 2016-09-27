<%@ page language="java"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="/WEB-INF/c.tld" %>
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
  </head>
  
  <body>
  <!-- <td>部门</td><td><input type="text" name="item.proposalPerson.tdepartment.name"></td> -->
  <form action="task.do?actionType=doSelectProjectList" method="post">
  		<table>
  			<tr><td>项目关键字</td><td><input type="text" name="teamWork.teamName"> </td>  				
  				<td>负责人姓名</td><td><input type="text" name="teamWork.projectManager.name"></td>			
  				<td><input type="submit" value="查询"> </td>
  			</tr>
  		</table>
  </form>
  
    <table>
    	<tr>
    		<td>项目名称</td>
    		<td>项目负责人</td>
    		<td>团队成员</td>
    		<td>计划完成日期</td>
    	</tr>
    	<c:forEach items="${rs}" var="item">
    		<tr>
	    		<td>${item.teamName }</td>
	    		<td>${item.projectManager.name }</td>
	    		<td>${item.meneberName }</td>
	    		<td>${item.planfinishtime }</td>
    		</tr>
    	</c:forEach>
    </table>
  </body>
</html>
