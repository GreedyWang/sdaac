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
		<script type='text/javascript' src='/bpp/dwr/engine.js'></script>
		<script type='text/javascript'
			src='/bpp/dwr/interface/rejectTypeManager.js'></script>
		<script type='text/javascript'
			src='dwr/interface/prFormListManager.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="ext/ext-all.js"></script>
		<script type='text/javascript' src='dwr/interface/PuyerListBizManager'></script>
		<script type='text/javascript' src='dwr/interface/EmpManager.js'></script>
		<script type="text/javascript"
			src="epor/baseInfo/js/util/loadProjectData.js"></script>



		<script type='text/javascript' src='epor/approve/js/rjT.js'></script>
		<script type="text/javascript" src="epor/approve/js/writeIO.js"></script>
	</head>
	<script type="text/javascript">     
      var PNTip
      function bb(index){
      	PNTip(index);
      }
  	
  	  function changeCategory(){
  	  	changeCategoryJ();
  	  }	
  		
  		function cc(){

  			var plane = document.getElementById("isPlanea")
  			var cap = document.getElementById("isCapa")
  			var arno = document.getElementById("arnoa")
  			var costcenter = document.getElementById("costCentera")
  			
  			
  			var context = "请确认你对PR进行了修改信息：";
  						
  			if(plane.value == 0) {
  				context += '计划内/外:计划内; '
  				document.getElementById("isPlane3").value = plane.value
  			}
  			if(plane.value == 1) {
  				context += '计划内/外:计划外; '
  				document.getElementById("isPlane3").value = plane.value
  			}
  			if(cap.value == 0) {
  				context += '资本化/费用化:资本化; '
  				document.getElementById("isCap3").value = cap.value
  			}
  			if(cap.value == 1) {
  				context += '资本化/费用化:费用化; '
  				document.getElementById("isCap3").value = cap.value
  			}
  			if(arno.value != '') {
  				context += "AR_NO:"+arno.value+'; '
  				document.getElementById("arno3").value = arno.value
  			}
  			if(costcenter.value != '0') {
  				context += "成本中心："+costcenter.options[costcenter.selectedIndex].text;
  				document.getElementById("costcentre3").value = costcenter.value
  			}
  			
  			if(window.confirm(context)) {
  				
  			}else {
  				return false;
  			}
  			
  			if(window.confirm('确认填写IO号及上传北美附件?')){
  			// document.forms[0].submit();
  			document.getElementById('aaxs').submit();
  			}
  		}
  		
  		function dd() {		
  			var e=document.getElementById("showDiv")
  			e.style.display='block';
			window.scrollTop=window.scrollHeight;
  			return false;		
  		}
  		
  		/**
 * 载入成本中心数据
 */
//function loadCostcentreData() {

//}
  </script>
	<body >
		<form id="aasx" action="epor.do?actionType=doApprovePR"
			enctype="multipart/form-data" method="post">
			<input type="hidden" name="approvedPR.prPrForm.ssid"
				value="${prForm.ssid }">
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
					<td>Categroy</td>
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
					<td>
						<input id="totalCategroy3" type="hidden" name="approvedPR.prPrForm.totalCategroy">
					</td>
					<td colspan="3">
						<textarea cols="100%" name="approvedPR.context"></textarea>
					</td>
				</tr>

				<TR>
					<td>
						<input type="button" value="submit" onclick="valify2()">
					</td>

				</TR>

			</table>
			
		</form>
	</body>
	

</html>
