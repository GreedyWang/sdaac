<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>doaManager</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/table.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
   <table>
 		<tr>
 			<td rowspan="3">类别</td>
 			<td rowspan="3">金额（RMB）</td>
 			<td colspan="5">计划内</td>
 			<td colspan="5">计划外</td>
 			<td rowspan="2">与项目相关（实际程序控制中可定义为有项目编号的）</td>
 			<td rowspan="2">与It相关（实际程序控制中可定义为类别为It的）</td>
 		</tr>
   		<tr>
   			<td colspan="5">审批人</td>
   			<td colspan="5">审批人</td>
   		</tr>
   		<tr> 		
   			<td>部门经理</td>
   			<td>	采购部经理</td>
   			<td>	财务部经理</td>
   			<td>	主管副总经理</td>
   			<td>	总经理</td>
   			<td>部门经理</td>
   			<td>	采购部经理</td>
   			<td>	财务部经理</td>
   			<td>	主管副总经理</td>
   			<td>	总经理</td>
   			<td>	项目经理</td>
   			<td>	IT主管</td>
   		</tr>
   		<tr>	
   			<td rowspan="3">Capital</td>
   			<td>500-2,000</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   		</tr>
   		<tr>	
   			<td>2,000-800,000</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   		</tr>
   		<tr>	
   			<td>>800,000</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   		</tr>
   		<tr>	
   			<td rowspan="3">Expense</td>
   			<td>>800,000</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   			<td>√</td>
   		</tr>
   </table>
  </body>
</html>
