<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>部门指标添加</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
  </head>              
  	<script type="text/javascript">
  	function doSubmit(value)
  	{
  		var theFom=document.forms[0];
  		if(value==2)
  		{
  			theFom.action='index.do?actionType=selectDepEmpsIndexs'
  		}
  		if(value==1)
  		{
  			theFom.action='departIndex.do?actionType=doManagerDepartmentIndex'
  		}
  		theFom.submit();
  	}
  	</script>
  <body>
   

DepartmentIndexShow_selectDepart.jsp
  <form action="departIndex.do?actionType=doManagerDepartmentIndex" method="post">
  <table cellspacing="1" cellpadding="4" width="90%"
			class="tableborder" id="table3">
  <thead>
  <tr><td  colspan="3" class="header">选择部门</td></tr>
  <tr>
  <td colspan="2">
  <select name="items.departID">
  <c:forEach items="${departlist}" var="departInfo">
  <option value="${departInfo.id }">${departInfo.name }</option>
  </c:forEach>
  </select></td></tr>
  <tr class="header">
  <td><input type="button" value="定义指标" onclick="doSubmit(1)"></td>
   <td><input type="button" value="评定指标" onclick="doSubmit(1)"></td>
  <td><input type="button" value="查看指标" onclick="doSubmit(2)"></td>
  </tr>
  </form>
   </thead>
   </table>
   </form>
   </body>
</html>
