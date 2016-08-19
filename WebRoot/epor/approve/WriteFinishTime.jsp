<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
		<script type='text/javascript'
			src='dwr/interface/prFormListManager.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="ext/ext-all.js"></script>
		<script type="text/javascript"
			src="epor/baseInfo/js/util/loadProjectData.js"></script>
		<script type="text/javascript" src="epor/approve/js/writeFT.js"></script>

	</head>
	<script type="text/javascript">
      var PNTip
      function ft(index){
      	PNTip(index);
      }
      
      </script>

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
	   <input type="hidden" name="approvedPR.prPrForm.tempolyeeByApplicantId.tdepartment.id" value="${prForm.tempolyeeByApplicantId.tdepartment.id}">
	   <input type="hidden" name="approvedPR.prPrForm.type" value="${prForm.type }">
   		<input type="hidden" name="approvedPR.prPrForm.source"
				value="${prForm.source }">
   <table> 

  
   <tr>
    <input type="hidden" name="approvedPR.isApproved" value="1" >
   	<td><input type="submit" value="完成"> </td>
   </tr>
   </table>
   </form>
	
	
	</body>
</html>
