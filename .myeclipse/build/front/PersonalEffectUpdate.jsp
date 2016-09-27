<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:directive.page import="org.springframework.context.ApplicationContext"/>
<jsp:directive.page import="org.springframework.context.support.ClassPathXmlApplicationContext"/>
<jsp:directive.page import="app.biz.PersonalPBiz"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>岗位管理</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="calc/calendar.js"></script>
		<script type="text/javascript" src="calc/calendar-en.js"></script>
	  <script type='text/javascript' src="js/cal.js"></script>
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
	<link type="text/css" rel="stylesheet" href="calc/calendar-system.css"/>
  </head>
  
  <body>
	<table>

	
   </table>
<table cellspacing="1" cellpadding="4" width="90%"
			class="tableborder" id="table3">
   <thead>
   <tr>
				<td colspan="7" class="header">员工绩效更新 
				</td>
  </tr>
   <tr>
   <td align="center" class="altbg1"><b>员工编号</b></td>
   <td align="center" class="altbg1"><b>岗位代码</b></td>
   <td align="center" class="altbg1"><b>工作时间(小时)</b></td>
   <td align="center" class="altbg1"><b>产出</b></td>
     <td align="center" class="altbg1"><b>日期</b></td>
       <td align="center" class="altbg1"><b>组长</b></td>
       
   </tr> </thead>
  <tbody>
    <form action="personal.do?actionType=updatePersonalDailyForm" method="post">
    <tr><td align="center" class="altbg1">${param.uid}</td>
   <input type="hidden" name="item.tempolyee.uid" value="${param.uid}">
    <input type="hidden" name="item.id" value="${param.id}">
  <td align="center" class="altbg1"><input name="item.tpost.id" value="${param.postid}"> </td>
   <td align="center" class="altbg1"><input name="item.worktime" value="${param.worktime}"> </td>
    <td align="center" class="altbg1"><input name="item.output" value="${param.output}"> </td>
     <td align="center" class="altbg1"><input name="datatime" value="${param.datatime}"> </td>
     <td align="center" class="altbg1"><input name="item.teamLeaderId" value="${param.teamLeaderId}"> </td>
     <td align="center" class="altbg1"><input type="submit" value="提交"> </td>
     </tr>
    </form>
</tr>
   </tbody>
   </table>
  </body>
</html>
