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
	<script type="text/javascript" src="front/hrpost/th/checkFrom.js"></script>
  </head>

  <body>
  <br><br><br><!-- 奖金编码 -->
  <form id="1" action="figureAndPostId.do?actionType=addFigure" method="post" onsubmit="return checkFrom()" >
  <table cellspacing="1" cellpadding="4" width="90%"
			class="tableborder" id="table1">
   <thead>
   <tr>
   <td colspan="6" class="header">图号添加 </td>
   </tr>
   <tr>
   <td align="center" class="altbg1"><b>图号<b></td>
   <td align="center" class="altbg1"><b>物料号(sap号)<b></td>
   <td align="center" class="altbg1"><b>名称</b></td>
    <td align="center" class="altbg1"><b>标准工时(s)</b></td>
     <td align="center" class="altbg1"><b>类型</b></td>
   <td align="center" class="altbg1"><b>车间</b></td>
   </tr> 
   </thead>
   <tbody>
   	<tr>
   	<td align="center"><input name="bf.figureId" ></td>
   	<td align="center"><input name="bf.remark" ></td>
   	<td align="center"><input name="bf.carType" ></td>
   		<td align="center"><input name="bf.standWorkTime" ></td>
   	<td align="center"><select name="bf.type" >
   				<option value="总成">总成</option>
   				<option value="芯体">芯体</option>
   				<option value="其他">其他</option>
   				</select></td>
   	<td align="center">
   	<select id="productArea" name="bf.productArea" >
   	<option value="-1">请选择车间</option>
   	<option value="104A">104A</option>
   	<option value="100">100</option>
   	<option value="101">101</option>
   	<option value="103">103</option>
   	<option value="chukou">chukou</option>
   	</select>
   	</td>
   	</tr>
   	<tr>
   	<td align="center" class="header" colspan="6"><input type="submit" value="提交"></td>
   	</tr>
   </tbody> 
   </form>
 
 	
   </body>
   </html>
