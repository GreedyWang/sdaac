<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
	
  
  </head>
  
  <body>
 <table cellspacing="1" cellpadding="4" width="90%"
			class="tableborder" id="table3">
   <thead>
   <tr>
				<td colspan="7" class="header">岗位管理 
				</td>
  </tr>
   <tr>
   <td align="center" class="altbg1"><b>岗位编号</b></td>
   <td align="center" class="altbg1"><b>岗位名称</b></td>
   <td align="center" class="altbg1"><b>岗位类型</b></td>
   <td align="center" class="altbg1"><b>指标</b></td>
   <td align="center" class="altbg1"><b>目标数量</b></td>
  
   </tr> </thead>
  <tbody>

  <form action="post.do?actionType=doUpdate" method="post" >
  <input name="item.id" type="hidden" value="${param.postid }">
 <tr>  
	<td align="center" class="altbg1">${param.postid }</td>
   <td align="center" class="altbg1"><input name="item.productName" type="text" value="${param.postName }"/></td>
   <td align="center" class="altbg1"><input name="item.tpostType.type" type="text" /></td>  
    <td align="center" class="altbg1"><input name="item.target" type="text" /></td>  
     <td align="center" class="altbg1"><input name="item.targetQuality" type="text" /></td>  
    
   </tr> 
   <tr><input type="submit" value="提交"/> </tr>
   </from>

<tr >
</tr>
   </tbody>
   </table>


  </body>
</html>
