<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:directive.page import="org.springframework.context.ApplicationContext"/>
<jsp:directive.page import="org.springframework.context.support.ClassPathXmlApplicationContext"/>
<jsp:directive.page import="app.biz.DepartmentBiz"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>员工添加</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
  </head>

  <body>

<table cellspacing="1" cellpadding="4" width="90%"
			class="tableborder" id="table3">
   <thead>
   <tr>
				<td colspan="9" class="header"><br><br>员工添加 
				</td>
  </tr>
   <tr>
    <td align="center" class="altbg1"><b>员工编号</b></td>
   	<td align="center" class="altbg1"><b>员工姓名</b></td>
 	<td align="center" class="altbg1"><b>部门名称</b></td>
 	<td align="center" class="altbg1"><b>直系领导工号</b></td>
    <td align="center" class="altbg1"><b>档案工资</b></td>
	<td align="center" class="altbg1"><b>类型</b></td>
	<td align="center" class="altbg1"><b>区域</b></td> 	
  	<td align="center" class="altbg1"><b>NetID</b></td>
  	<td align="center" class="altbg1"><b>PR审批范围</b></td>
  	
   </tr> </thead>
  <tbody>

  <form action="employee.do?actionType=doInsert" method="post" >
  
 <tr>  
	<td align="center" class="altbg1" rowspan="5"><input name="item.uid" type="text" size="8"></td>
   <td align="center" class="altbg1" rowspan="5"><input name="item.name" type="text" size="8"/></td>
   <td align="center" class="altbg1" rowspan="5">
   <select name="item.tdepartment.id" rowspan="5">
   <c:forEach items="${departs}" var="item">
   <option value="${item.id }">${item.name }</option>
   </c:forEach>
   </select></td>
   <td align="center" class="altbg1" rowspan="5"><input type="text" name="item.leaderID" size="8"> </td>
   <td align="center" class="altbg1" rowspan="5"><input name="item.baseSalary" type="text" size="8"/></td>  
	<td align="center" class="altbg1" rowspan="5"><select name="item.type">
	<option value="worker">线上工人</option>
	<option value="salary">办公室人员</option>
	</select> </td>
	<td align="center" class="altbg1" rowspan="5"><select name="item.area" >
	<option value="DT02">上海</option>
	<option value="DT03">沈阳</option>
	<option value="DT04">烟台</option>
	<option value="DT05">上海厂</option>
	</select> </td>
	<td align="center" class="altbg1" rowspan="5"><input type="text" name="item.netid" ></td>
	<td align="center" class="altbg1" >
		<tr><td>上海<input type="radio" name="e1" value="1"></td></tr>
		<tr><td>沈阳<input type="radio" name="e2" value="2"></td></tr>
		<tr><td>烟台<input type="radio" name="e3" value="3"></td></tr>
		<tr><td>上海厂<input type="radio" name="e4" value="4"></td></tr>	
	</td>
	
   </tr>
    
   <tr><td colspan="9" class="header"><input type="submit" value="提交"/></td></tr>
   </from>

   </tbody>
   </table>
  </body>
</html>
