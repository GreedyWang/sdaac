<%@ page language="java" pageEncoding="utf-8"%>
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

		<script type='text/javascript'
			src='/bpp/dwr/interface/rejectTypeManager.js'></script>



		<script type='text/javascript' src='epor/approve/js/rjT.js'></script>
				
	</head>

      
	<body >
		<form id="aasx" action="epor.do?actionType=doApprovePR"
			enctype="multipart/form-data" method="post">
			<input type="hidden" name="approvedPR.prPrForm.id"
				value="${prForm.id }">
			<input type="hidden" name="approvedPR.prPrForm.processInstanceId"
				value="${prForm.processInstanceId }">
			<input type="hidden" name="approvedPR.prPrForm.version"
				value="${prForm.version }">
			<input type="hidden" name="approvedPR.prPrForm.state"
				value="${prForm.state }">
			<input type="hidden" name="approvedPR.tempolyee.uid"
				value="${logineduser.emp.uid }">
			<input type="hidden" name="approvedPR.prPrForm.total"
				value="${prForm.total}">
			<input type="hidden" name="approvedPR.prPrForm.isPlan"
				value="${prForm.isPlan}">
			<input type="hidden" name="approvedPR.prPrForm.isCapital"
				value="${prForm.isCapital}">
			<input type="hidden"
				name="approvedPR.prPrForm.tempolyeeByApplicantId.uid"
				value="${prForm.tempolyeeByApplicantId.uid}">
			<input type="hidden" name="approvedPR.prPrForm.tempolyeeByBuyerId"
				value="${prForm.tempolyeeByBuyerId }">
			<input type="hidden"
				name="approvedPR.prPrForm.tempolyeeByApplicantId.tdepartment.id"
				value="${prForm.tempolyeeByApplicantId.tdepartment.id}">
			<input type="hidden" name="approvedPR.prPrForm.type"
				value="${prForm.type }">
			<input type="hidden" name="approvedPR.prPrForm.isInTheSap"
				value="${prForm.isInTheSap }">
			<input type="hidden" name="approvedPR.prPrForm.prCostCenter.id"
				value="${prForm.prCostCenter.id }">
			<input type="hidden" name="approvedPR.prPrForm.prCostCenter.remark"
				value="${prForm.prCostCenter.remark }">
			<input type="hidden" name="approvedPR.prPrForm.isOtherCostCenter"
				value="${prForm.isOtherCostCenter }">
			<input type="hidden" name="approvedPR.prPrForm.lastState"
				value="${prForm.lastState }">
			<input type="hidden" name="approvedPR.prPrForm.source"
				value="${prForm.source }">

			<table width=1240>
				<tr>
					<td colspan="3">
						<hr>
					</td>
				</tr>
				<tr>
					<td>
						Opinion
					</td>
					<td>
						Reject Reason
					</td>
					<td>
						Comments
					</td>

				</tr>
				<tr>
					<input name="approvedPR.type" type="hidden" value="CWContorlType">
					<td>
						<select name="approvedPR.isApproved" id="suggId"
							name="approvedPR.isApproved" onchange="rejectDlg(this)">
							<option value="1">
								Approve
							</option>
							<option value="0">
								Reject
							</option>
							<option value="2">
								Need Explanation
							</option>
						</select>
					</td>

					<td>
						<select id="rejectType" name="approvedPR.rejectType"
							style="width: 180;" disabled="disabled"></select>
					</td>

					<td colspan="4">
						<textarea cols="100%" name="approvedPR.context"></textarea>
					</td>
				</tr>
				<tr>
   					<td>SAP_SN:<input type="text" id="prsn" name="approvedPR.prPrForm.prsn" > </td>
   
  				</tr>
				<TR>
					<td>
						<input type="button" value="submit" onclick="valify4()">
					</td>

				</TR>

			</table>

		</form>
	</body>
</html>
