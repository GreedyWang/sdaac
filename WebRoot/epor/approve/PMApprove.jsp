<%@ page language="java"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    <form action="epor.do?actionType=doApprovePR" method="post">
   <input type="hidden" name="approvedPR.prPrForm.id" value="${prForm.id }">
   <input type="hidden" name="approvedPR.prPrForm.source" value="${prForm.source }">
   <input type="hidden" name="approvedPR.prPrForm.version" value="${prForm.version }">
   <input type="hidden" name="approvedPR.prPrForm.state" value="${prForm.state }">
   <input type="hidden" name="approvedPR.tempolyee.uid" value="${logineduser.emp.uid }">
   <input type="hidden" name="approvedPR.prPrForm.type" value="${prForm.type }">
   <input type="hidden" name="approvedPR.prPrForm.prCostCenter.owner" value="${prForm.prCostCenter.owner }">
   <input type="hidden" name="approvedPR.prPrForm.prProject.id" value="${prForm.prProject.id }">
   <input type="hidden" name="approvedPR.prPrForm.tempolyeeByBuyerId" value="${prForm.tempolyeeByBuyerId }">
   <input type="hidden" name="approvedPR.prPrForm.prProject.managerUid" value="${prForm.prProject.managerUid }">
   <input type="hidden" name="approvedPR.prPrForm.tempolyeeByApplicantId.uid" value="${prForm.tempolyeeByApplicantId.uid}"> 
   <input type="hidden" name="approvedPR.prPrForm.prCostCenter.remark" value="${prForm.prCostCenter.remark }">
   <input type="hidden" name="approvedPR.prPrForm.lastState" value="${prForm.lastState }">
   <input type="hidden" name="approvedPR.prPrForm.tempolyeeByApplicantId.tdepartment.id" value="${prForm.tempolyeeByApplicantId.tdepartment.id}"> 
			<input type="hidden" name="approvedPR.prPrForm.source"
				value="${prForm.source }">   
   <table> 
   <tr>
   		<td>审批意见/opinion</td>
   		<td>意见说明/comments</td>
   		
   </tr>
   <tr>
   <td><select name="approvedPR.isApproved">
   <option value="1">批准/approve</option>
   <option value="0">不批准/refuse</option>
   <option value="2">需要解释/need explain</option>
   </select></td>
   <td><textarea rows="" cols="100%" name="approvedPR.context"></textarea> </td>
   </tr>
   <tr><td><input type="submit" value="提交/submit"> </td>
   </tr>
   </table>
   </form>
  </body>
</html>
