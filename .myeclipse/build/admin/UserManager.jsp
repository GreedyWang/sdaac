<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:directive.page import="common.util.Page;"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath",basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
 	 <title>管理人员-管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
	
	<script type="text/javascript">
	function update(empid)
  {
  	var url='${basePath}'+'Manager/updateEmp.jsp?empid='+empid;
		
		//alert(url);
	    window.open(url, "xxx", "toolbars=0, location=0, statusbars=0, menubars=0,width=700,height=300,scrollbars=1");
  	
  }
  </script>
 
  </head>
  
  <body>
   <table>
   <tr>
 <form action="employee.do?actionType=selectEmp"  name="selectCustomerOrder"  method="post">
  <!-- 
   <td>订单日期</td>
   <td><input type="text" onfocus="calShow('diary');" id="diary" name="date" /></td>
    -->
   <td>员工编号</td>
   <td><input type="text" name="item.uid"></td>
   <td>员工姓名</td>
   <td><input type="text" name="item.name"></td>
   <td><input type="submit"  value="查询"></td>
   <td><input type="reset" value="重置"></td>
   </form></tr>

   </table>
<table cellspacing="1" cellpadding="4" width="90%"
			class="tableborder" id="table3">
   <thead>
   <tr>
				<td colspan="7" class="header">员工管理 
				</td>
  </tr>
   <tr>
   <td align="center" class="altbg1"><b>员工编号</b></td>
   <td align="center" class="altbg1"><b>员工姓名</b></td>
   <td align="center" class="altbg1"><b>部门</b></td>
   <td align="center" class="altbg1"><b>档案工资</b></td>
   <td align="center" class="altbg1"><b>档案工资等级</b></td>
   <td align="center" class="altbg1"><b>操作</b></td>
   </tr> </thead>
  <tbody>
<c:if test="${empList!=null }">
  <form id="form${empList.uid }" method="post" ">
  <input name="orderid" type="hidden" value="${empList.uid }">
 <tr>  
	<td align="center" class="altbg1">${empList.uid }</td>
   <td align="center" class="altbg1">${empList.name }</td>
   <td align="center" class="altbg1">${empList.tdepartment.name }</td>  
    <td align="center" class="altbg1">${empList.baseSalary }</td>  
     <td align="center" class="altbg1">${empList.salaryType}</td>  
        <td align="center" class="altbg1"><img src="images/update.gif" onclick="update('${empList.uid  }')"/></td>
   </tr> 
   </from>
 </c:if>
 <c:if test="${empList==null }">
 无此员工号<br style="color: red" >
 </c:if>
<tr >
</tr>
   </tbody>
   </table>
  
  </body>
</html>
