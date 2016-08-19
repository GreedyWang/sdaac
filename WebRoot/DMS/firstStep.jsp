<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
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
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">

  </head>
  
  <body>
  <form action="dMS.do?actionType=doScoreReady" method="post">
  <table width="90%"cellspacing="1" cellpadding="4"  class="tableborder">
  <tr>
  	<td align="center" class="header">
  		版本
  	</td>
  	<td align="center" class="header">
  		区域
  	</td>
  	<td align="center" class="header">
  		组
  	</td>
  </tr>
  
  <tr>
  	<td align="center" class="altbg1">
  		<select name="version" >
 		  	<c:forEach items="${versions}" var="v">
   				<option value="${v}">${v}</option>
  		 	</c:forEach> 		  	  			  	
	    </select>
  	</td>
  	<td align="center" class="altbg1">

  		<select name="district"> 
  		
  		 <c:forEach items="${aaa}" var="area">
  		
   			<option value="${area}">${area}</option>
  		 </c:forEach>
  			  	
	    </select>
  	</td>
  	<td align="center" class="altbg1">
  		<select name="group">   	
		 	<c:forEach items="${groups}" var="group">
  		
   			<option value="${group}">
   				<c:if test="${group== 4}">区域到大组</c:if>
   				<c:if test="${group== 3}">大组到小组</c:if>  				
   				<c:if test="${group== 2}">小组到员工</c:if>
   			</option>

  		 </c:forEach>
	    </select>
  	</td>
  </tr>   
    <tr  align="center" class="header">
    <td ><input type="submit" value="next"></td>
    <td></td>
    <td></td>
    </tr>
    </table>
    </form>
  
  </body>
</html>
