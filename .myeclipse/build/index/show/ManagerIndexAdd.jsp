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
	
  
  <body>
 ManagerIndexAdd.jsp
  

  
   <table cellspacing="1" cellpadding="4" width="100%"
			class="tableborder" id="table1">
   <thead>
   <tr>
	<td colspan="12" class="header">公司指标</td>		
   <tr>
     <td align="center" class="altbg1"><b>名称</b></td>  
     <td align="center" class="altbg1"><b>计算公式</b></td>  
     <td align="center" class="altbg1"><b>类型</b></td>  
     <td align="center" class="altbg1"><b>是否必选</b></td>  
 	 <td align="center" class="altbg1"><b>70%</b></td>
     <td align="center" class="altbg1"><b>100%</b></td>
     <td align="center" class="altbg1"><b>150%</b></td>
     <td  align="center" class="altbg1"><b>权重(100%)</b></td>
 	 <td  align="center" class="altbg1"><b>实际值</b></td>
 	 <td  align="center" class="altbg1"><b>得分</b></td>
 	 </tr> 
 	 </thead>
  <tbody>
	
	<!-- 显示公司的指标 -->
	<c:forEach items="${companyIndexs}" var="item" varStatus="state">
	<tr class="altbg1">
	<td>${item.tindexTarget.name }</td>
	<td>${item.tindexTarget.formula }</td>
	<td>${item.tindexTarget.type }</td>
	<td>${item.tindexTarget.isChoice }</td>
	<td>${item.tindexTarget.a }</td>
	<td>${item.tindexTarget.b }</td>
	<td>${item.tindexTarget.c }</td>
	<td>${item.percentage }</td>
	<td>${item.act_output }</td>
	<td>${item.score }</td>
	</td>
	
</c:forEach>

   </tbody>
   </table>

<form action="empIndex.do?actionType=doInsertEmpIndicators" method="post" onsubmit="return checkSubmit()">
   <table cellspacing="1" cellpadding="4" width="100%"
			class="tableborder" id="table3">
   <thead>
   <tr>
	<td colspan="12" class="header">${empName }指标定义 
	
	<input type="hidden" name="uid" value="${empUid }">
	</td>		
  </tr>
  
   <tr>
     <td align="center" class="altbg1"><b>名称</b></td>  
     <td align="center" class="altbg1"><b>计算公式</b></td>  
     <td align="center" class="altbg1"><b>类型</b></td>  
     <td align="center" class="altbg1"><b>是否必选</b></td>  
 	 <td align="center" class="altbg1"><b>70%</b></td>
     <td align="center" class="altbg1"><b>100%</b></td>
     <td align="center" class="altbg1"><b>150%</b></td>
     <td colspan="1" align="center" class="altbg1"><b>权重(100%)</b></td>
 	 <td colspan="1" align="center" class="altbg1"><b>实际值</b></td>
 	 <td colspan="1" align="center" class="altbg1"><b>得分</b></td>
  	 
   </tr> </thead>
  <tbody>
  
	<!-- 显示已经定义的指标 -->
	<c:forEach items="${empIndexs}" var="item" varStatus="state">
	<tr class="altbg1"><td>${item.index.name }</td>
	<td>${item.index.formula }</td>
	<td>${item.index.type }</td>
	<td>${item.index.isChoice }</td>
	<td>${item.index.a }</td>
	<td>${item.index.b }</td>
	<td>${item.index.c }</td>	
	<td>${item.percentage }</td>
	<td>${item.act_output }</td>
	<td>${item.score }</td>
	</c:forEach>

   </tbody>
   </table>
   <table>
    <tr><td>公司得分</td><td>个人得分</td><td>总得分</td></tr>
   	<tr><td colspan="3">${result }</td></tr>
   </table>
   	</form>
  </body>
</html>
