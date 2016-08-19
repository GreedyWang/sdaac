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
  <form action="proposal.do?actionType=showAllTopic" method="post">
  		<table>
  			<tr><td>主题关键字</td><td><input type="text" name="item.title"> </td>
  				
  				<td>提案人</td><td><input type="text" name="item.proposalPerson.name"></td>
  			</tr>
  			<tr>
  				<td><input type="submit" value="查询"> </td>
  			</tr>
  		</table>
  </form>
  
    <table>
    	<tr>
    		<td>主题</td>
    		<td>部门</td>
    		<td>提案人</td>
    	</tr>
    	<c:forEach items="${rs}" var="item">
    		<tr>
	    		<td>${item.title }</td>
	    		<td>${item.proposalPerson.tdepartment.name }</td>
	    		<td>${item.proposalPerson.name }</td>
    		</tr>
    	</c:forEach>
    </table>
  </body>
</html>
