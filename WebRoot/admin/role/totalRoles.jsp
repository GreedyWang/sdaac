<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<script type='text/javascript' src='dwr/interface/EmpManager.js'></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/ext-all.js"></script>
	<script type="text/javascript" src="admin/role/js/backerManager.js"></script>
	<script type='text/javascript' src='dwr/interface/EmpManager.js'></script>

  </head>
  
  <body>
      <div id="hello-win">
    <div id="hello-tabs"></div>
  
   <form action="rights.do?actionType=rightsManagerByType" method = "post">
   			<select name="type">
   				<option value="newpr">New_PR</option>
   				<option value="pr">PR</option>
   			</select>
   			
   			<select name="plant">
   				<option value="">ALL</option>
   				<option value="1">SH Company</option>
   				<option value="2">SY</option>
   				<option value="3">YT</option>
   				<option value="4">SH Plant</option>
   			</select>
   			<input type="submit" value="Query">
   </form>
   <table border="1">
   
   
   	<c:forEach items="${allRoles}" var="map">
   				<tr>
   				<td width="100"><label >${map.key }</label></td>
   						
   			<c:forEach items="${map.value}" var="item">
   					
						<td>
							${item.name }/DEL
						</td>

						
				
			</c:forEach>

				</tr>
	</c:forEach>
			</table>
  </body>
</html>
