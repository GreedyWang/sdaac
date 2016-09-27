<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ShowEmpUid.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <script type='text/javascript' src='/bpp/dwr/interface/DepartmemntManager.js'></script>
 	 <script type='text/javascript' src='/bpp/dwr/engine.js'></script>
 	<script type='text/javascript' src='/bpp/dwr/interface/EmpManager.js'></script>

	<script type="text/javascript">
		function aaa()
		{
			DepartmemntManager.getAll(function(data){
				var select1=document.getElementById('departName')
				select1.length=0
			 	for(var i=0;i<data.length;i++)
			 	{
			 		var opt=document.createElement('Option');
			 		opt.innerText = data[i].name
					opt.value = data[i].id    
					select1.appendChild(opt);
		 		} 
			})
		}
		
		function getEmp(departID)
		{
			EmpManager.doSelectByDepartID(departID,function(data){
				var select2=document.getElementById('empName')
				select2.length=0
			 	for(var i=0;i<data.length;i++)
			 	{
			 		var opt=document.createElement('Option');
			 		opt.innerText = data[i].name
					opt.value = data[i].uid    
					select2.appendChild(opt);
		 		} 
			
			})
		}
		function showEmpUid(elem)
		{
			document.getElementById('empUid').innerText=elem.value;
		}
	</script>
  </head>
  
  <body onload="aaa()" style="width: 200px">
    <select id="departName" onchange="getEmp(this.value)">
    </select>
    <br><select id="empName" onclick="showEmpUid(this)"></select>
    <label id="empUid"></label>
    
    
     <table>
   <tr>
 <form id="1" action="employee.do?actionType=selectEmp2"  name="selectCustomerOrder"  method="post">
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
   <td align="center" class="altbg1"><b>员工编号</b></td>
   <td align="center" class="altbg1"><b>员工姓名</b></td>
   <td align="center" class="altbg1"><b>部门</b></td>
   </tr> 
   </thead>
  <tbody>
	<c:forEach items="${rs}" var="empList">
	 <tr>  
		<td align="center" class="altbg1">${empList.uid } </td>
   		<td align="center" class="altbg1">${empList.name }</td>
   		<td align="center" class="altbg1">${departs}</td>
	<tr>
	</c:forEach>

   </tbody>
   </table>
    
  </body>
</html>
