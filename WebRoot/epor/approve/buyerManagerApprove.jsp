<%@ page language="java"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <base href="<%=basePath%>">   
      <title>PR审批</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
		<script type='text/javascript' src='dwr/interface/prFormListManager.js'></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/ext-all.js"></script>
  </head>
  
  <body>
    <form action="epor.do?actionType=doApprovePR" method="post">
   <input type="hidden" name="approvedPR.prPrForm.id" value="${prForm.id }">
    <input type="hidden" name="approvedPR.prPrForm.version" value="${prForm.version }">
   <input type="hidden" name="approvedPR.prPrForm.state" value="${prForm.state }">
   <input type="hidden" name="approvedPR.tempolyee.uid" value="${logineduser.emp.uid }">
   <input type="hidden" name="approvedPR.prPrForm.total" value="${prForm.total}">
   <input type="hidden" name="approvedPR.prPrForm.isPlan" value="${prForm.isPlan}">
   <input type="hidden" name="approvedPR.prPrForm.isCapital" value="${prForm.isCapital}">
   <input type="hidden" name="approvedPR.prPrForm.tempolyeeByApplicantId.uid" value="${prForm.tempolyeeByApplicantId.uid}">
  <input type="hidden" name="approvedPR.prPrForm.tempolyeeByBuyerId" value="${prForm.tempolyeeByBuyerId }">
  		<input type="hidden" name="approvedPR.prPrForm.source"
				value="${prForm.source }">
   <table> 
   <tr>
   		<td>审批意见</td>
   		<td>选择采购员</td>
   		<td>意见说明</td>
   		
   </tr>
   <tr>
   <td><select name="approvedPR.isApproved">
   <option value="1">批准</option>
   <option value="0">不批准</option>
    <option value="2">需要解释</option>
     <option value="5">退回到采购员</option>
   </select></td>
 
   <td><textarea rows="" cols="100%" name="approvedPR.context"></textarea> </td>
   </tr><tr>
   <td><input type="submit" value="提交"> </td>
   </tr>
   </table>
   </form>
  </body>
</html>
