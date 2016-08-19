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
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type='text/javascript'
			src='dwr/interface/prFormListManager.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="ext/ext-all.js"></script>
		<script type="text/javascript"
			src="epor/baseInfo/js/util/loadProjectData.js"></script>
		<script type='text/javascript' src='dwr/interface/EmpManager.js'></script>
		<script type="text/javascript" src="epor/approve/js/wind.js"></script>


	</head>
	<script type="text/javascript">
      var showUid,loadProjectData
      
      	function aaa(){
 		
      		if(window.confirm("确认提交")){
      			//document.forms[0].submit();
      			document.getElementById('aaxs').submit();
      		}else{
      			return false;
      		}
      	}
      	
      </script>
	<body>
		<div id="hello-win2">
			<div id="hello-tabs2"></div>
		</div>

		<form action="epor.do?actionType=doApprovePR" method="post" id="aaxs">
			<input type="hidden" name="approvedPR.prPrForm.id"
				value="${prForm.id }">
			<input type="hidden" name="approvedPR.prPrForm.isInTheSap"
				value="${prForm.isInTheSap }">
			<input type="hidden" name="approvedPR.prPrForm.source"
				value="${prForm.source }">
			<input type="hidden" name="approvedPR.prPrForm.version"
				value="${prForm.version }">
			<input type="hidden" name="approvedPR.prPrForm.state"
				value="${prForm.state }">
			<input type="hidden" name="approvedPR.tempolyee.uid"
				value="${logineduser.emp.uid }">
			<input type="hidden"
				name="approvedPR.prPrForm.tempolyeeByApplicantId.uid"
				value="${prForm.tempolyeeByApplicantId.uid}">
			<input type="hidden"
				name="approvedPR.prPrForm.tempolyeeByApplicantId.tdepartment.id"
				value="${prForm.tempolyeeByApplicantId.tdepartment.id}">
			<input type="hidden" name="approvedPR.prPrForm.total"
				value="${prForm.total}">
			<input type="hidden" id="area" value="${logineduser.emp.area }">
			<input type="hidden" name="approvedPR.prPrForm.isOtherCostCenter" value="${prForm.isOtherCostCenter }">
			<table>
				<tr>
					<td>
						审批意见
					</td>
					<td colspan="5">
						意见说明
					</td>
				</tr>
				<tr>
					<input name="approvedPR.type" type="hidden" value="CWContorlType">
					<td>
						<select name="approvedPR.isApproved">
							<option value="1">
								批准
							</option>
							<option value="0">
								不批准
							</option>
							<option value="2">
								需要解释
							</option>
						</select>
					</td>
					<td colspan="5">
						<textarea cols="100%" name="approvedPR.context"></textarea>
					</td>
				</tr>
				
				<tr>
					
					<td>
						总账科目
						<label id="totalCategroyTip"></label>
						<input type="button" value="编辑" onclick="subjectTip()">
					<td>
										<input type="hidden" id="vv"
						name="approvedPR.prPrForm.totalCategroy">
					</td>
					<td>
						相关部门
						<label id="relateTip"></label>
						<input type="button" value="编辑" onclick="showUid()">
					</td>
					<td>
						<input id="relate" type="hidden" name="approvedPR.relateUids">
					</td>
				</tr>
				<TR>
					<td>
						<input type="button" value="提交" onclick="aaa()">
					</td>
				</TR>
			</table>
		</form>
	</body>
</html>
