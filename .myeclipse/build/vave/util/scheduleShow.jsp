<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-1_0-rt.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>schedule</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/vave/cacl.js"></script>
	<script type='text/javascript' src='/bpp/dwr/interface/statisticsSchedule.js'></script>
    <script type='text/javascript' src='/bpp/dwr/interface/projectSchedule.js'></script>
  	<script type='text/javascript' src='/bpp/dwr/engine.js'></script>
	 <script type="text/javascript" src="calc/calendar.js"></script>
	<script type="text/javascript" src="calc/calendar-en.js"></script>
	<script type='text/javascript' src="js/cal.js"></script>
	<link type="text/css" rel="stylesheet" href="calc/calendar-system.css"/>
  </head>
  
  <body>

<c:if test="${VaveSchedule!=null}">
<jsp:include page="/vave/util/staticis.jsp"></jsp:include>
<jsp:include page="/vave/util/projectShow.jsp"></jsp:include>
</c:if>


 </body>
</html>
