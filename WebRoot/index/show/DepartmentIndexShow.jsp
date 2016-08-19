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

  <body >
  
 
  
  <a onclick="showEmpIndex('${departId }')">查看此部门人员的指标</a>

   <table cellspacing="1" cellpadding="4" width="100%"
			class="tableborder" id="table4">
   <thead>
   <tr>
	<td colspan="12" class="header">${departName }指标查看</td>		
   </tr>  
   <tr>
     <td align="center" class="altbg1">名称</td>  
     <td align="center" class="altbg1"><b>计算公式</b></td>  
     <td align="center" class="altbg1"><b>类型</b></td>  
     <td align="center" class="altbg1"><b>是否必选</b></td>  
 	 <td align="center" class="altbg1"><b>70%</b></td>
     <td align="center" class="altbg1"><b>100%</b></td>
     <td align="center" class="altbg1"><b>150%</b></td>
     <td colspan="1" align="center" class="altbg1"><b>权重(%)</b></td>
 	 <td colspan="1" align="center" class="altbg1"><b>实际值</b></td>
 	 <td colspan="1" align="center" class="altbg1"><b>得分</b></td>
   </tr> 
   </thead>
  <tbody>
  	
	<c:forEach items="${companyIndexs}" var="item_Index" varStatus="state"> 
    <tr>  
	<td align="center" class="altbg1">${item_Index.tindexTarget.name }</td>
	<td align="center" class="altbg1">${item_Index.tindexTarget.formula }</td>
    <td align="center" class="altbg1">${item_Index.tindexTarget.type }</td>
    <td align="center" class="altbg1">${item_Index.tindexTarget.isChoice }</td>
    <td align="center" class="altbg1">${item_Index.tindexTarget.a }</td>
    <td align="center" class="altbg1">${item_Index.tindexTarget.b}</td>  
    <td align="center" class="altbg1">${item_Index.tindexTarget.c } </td> 
   
    <td align="center" class="altbg1">${item_Index.percentage }</td>
    <td align="center" class="altbg1">${item_Index.act_output }"</td>  
    <td align="center" class="altbg1">${item_Index.score }</td> 
    </tr> 
	</c:forEach>

</tbody>
</table>


   <table cellspacing="1" cellpadding="4" width="100%"
			class="tableborder" id="table3">
   <thead> 
   <tr>
     <td align="center" class="altbg1"><b onclick="update_Company_Index(1)">名称</b></td>  
     <td align="center" class="altbg1"><b>计算公式</b></td>  
     <td align="center" class="altbg1"><b>类型</b></td>  
     <td align="center" class="altbg1"><b>是否必选</b></td>  
 	 <td align="center" class="altbg1"><b>70%</b></td>
     <td align="center" class="altbg1"><b>100%</b></td>
     <td align="center" class="altbg1"><b>150%</b></td>
     <td colspan="1" align="center" class="altbg1"><b>权重(%)</b></td>
 	 <td colspan="1" align="center" class="altbg1"><b>实际值</b></td>
 	 <td colspan="1" align="center" class="altbg1"><b>得分</b></td>
  	 <td align="center" class="altbg1" colspan="2"><b>操作</b></td> 
   </tr> </thead>
  <tbody>
  		
	<c:forEach items="${departIndexs}" var="item_Index" varStatus="state"> 
    <tr>  
	<td align="center" class="altbg1">${item_Index.tindexTarget.name }</td>
	<td align="center" class="altbg1">${item_Index.tindexTarget.formula }</td>
    <td align="center" class="altbg1">${item_Index.tindexTarget.type }</td>
    <td align="center" class="altbg1">${item_Index.tindexTarget.isChoice }</td>
    <td align="center" class="altbg1">${item_Index.tindexTarget.a }</td>
    <td align="center" class="altbg1">${item_Index.tindexTarget.b}</td>  
    <td align="center" class="altbg1">${item_Index.tindexTarget.c } </td> 
    <td align="center" class="altbg1">${item_Index.percentage }
    <td align="center" class="altbg1">${item_Index.act_output }  
    <td align="center" class="altbg1">
    ${item_Index.score }
    </td> 
    </tr> 
	</c:forEach>
   </tbody>
   </table>
   <table>
   <tr>
	<td>总分:</td>
	<td>${result }</td>
	</tr>
	</table>
   
  </body>
</html>
