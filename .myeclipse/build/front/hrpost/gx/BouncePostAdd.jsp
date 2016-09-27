<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
	<script type="text/javascript" src="front/hrpost/th/FigureAccount.js"></script>
  </head>
  <body>
  <!-- 奖金编码 -->
  
 
 	<form id="2" action="figureAndPostId.do?actionType=addPostId" method="post" onsubmit="return checkFrom()">
 	
 	<table cellspacing="1" cellpadding="4" width="90%"
			class="tableborder" id="table2">
 	<thead>
 	<tr>
   	<td colspan="4" class="header">工序添加</td>
  	</tr>
 	<tr>
 	<td align="center" class="altbg1">工序号</td>
 	<td align="center" class="altbg1">工序名</td>
 	<td align="center" class="altbg1">设备数</td>
    <td align="center" class="altbg1"><b>车间</b></td>
 	</tr>
 	</thead>
 	<tbody>
 	<tr>
 	<td align="center"><input name="bp.postId" ></td>
   	<td align="center"><input name="bp.postName" ></td>
   	<td align="center"><input name="bp.deviceNum" ></td>
   		<td >
   	<select id="productArea" name="bp.remark" >
   	<option value="-1" selected="selected">请选择车间</option>
   	<option value="104A">104A</option>
   	<option value="100">100</option>
   	 <option value="101">101</option>
   	<option value="103">103</option>
   	<option value="chukou">chukou</option>
   	</select>
   	</td>
   	</tr>
   	<tr>
   	<td align="center"  class="header" colspan="4"><input type="submit" value="提交"></td>
 	</tr>
 	</tbody>
 	</table>
 	
 	</form>  
   </body>
   </html>
