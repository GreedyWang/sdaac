<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="/WEB-INF/c.tld"%>
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
		
		<script type='text/javascript'src='/bpp/dwr/interface/rejectTypeManager.js'></script>
		<script type='text/javascript' src='epor/approve/js/rjT.js'></script>


	</head>
	<script type="text/javascript">
      var showUid,loadProjectData
      
      	function aaa(){
      		var suggId = document.getElementById('suggId');
			if (suggId.value == 0) {
				var rjSel = document.getElementById('rejectType');
				if (rjSel.value == -1) {
					alert('Select one rejecet kind');
					return false;
				}
			}
      		var projectId = document.getElementById('projectId').value
      		if(projectId != null && projectId != ''){
      		//document.getElementById('relateTip').innerText = document.getElementById('projectId').text.split('#')[1]
      		var obj =document.getElementById('projectId'); //selectid
			var index = obj.selectedIndex; // 选中索引
			document.getElementById('relateTip').innerText = obj.options[index].text.split('#')[1]
      		document.getElementById('relate').value = obj.options[index].text.split('#')[2]
      		}
      		var isPlane = document.getElementById('isPlane')
      		var isPlane2 = document.getElementById('isPlane2')
      		var context='';
      		if((isPlane.checked==false)&&isPlane2.checked==false){
      			alert('请选择计划内/外！');return false;
      		}else{
      			if(isPlane.checked){
      				context+='计划内,'
      			}else{
      				context+='计划外,'
      			}
      		}
      		var isCap = document.getElementById('isCap')
      		var isCap2 = document.getElementById('isCap2')
      		if(isCap.checked==false&&isCap2.checked==false){
      			alert('请选择资本/费用化！');return false;
      		}else{
      			if(isCap.checked){
      				context+='资本化,'
      				if(document.getElementById('arno').innerText==null || document.getElementById('arno').innerText==''){
      					
      					if(window.confirm('PR单没有填写AR_NO,是否可以批准?')){
      					}else{
      						return ;
      					}
      				}
      			}else{
      				context+='费用化,'
      			}
      		}
      		context+='总账科目='+document.getElementById('totalCategroy').value+","
      		context+='相关部门='+document.getElementById('relateTip').innerText+'/'+document.getElementById('relate').value+"?"
      		if(window.confirm(context)){
      			document.getElementById('cwform').submit();
      		}else{
      			return false;
      		}
      	}
      	
      	function bbb(id){
      	
      		if('${prForm.source}'==1 && '${prForm.state}'==42){      		
      			window.location.href='epor.do?actionType=doTranstoZhu&state=2&source=4&id='+id     			
      		}else if('${prForm.source}'==4 && '${prForm.state}'==2){
      			window.location.href='epor.do?actionType=doTranstoZhu&state=42&source=1&id='+id   
      		}else{
      			return false;
      		}
      	}
      </script>
	<body>
		<div id="hello-win2">
			<div id="hello-tabs2"></div>
		</div>

		<form action="epor.do?actionType=doApprovePR" method="post"
			id="cwform">
			<input type="hidden" name="approvedPR.prPrForm.id"
				value="${prForm.id }">
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
			<input type="hidden" name="approvedPR.prPrForm.isOtherCostCenter"
				value="${prForm.isOtherCostCenter }">
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
							style="width: 180px"
							disabled="disabled"></select>
					</td>

					<td colspan="4">
						<textarea cols="100%" name="approvedPR.context"></textarea>
					</td>
				</tr>

				<tr>
					<td>
						GL
						<label id="totalCategroyTip"></label>
						<input type="button" value="编辑" onclick="subjectTip()">
					<td>						
					
					<input type="hidden" id="vv" name="approvedPR.prPrForm.totalCategroy">

				</tr>
				<TR>
					<td>
						<input type="button" value="submit" onclick="valify()">
					</td>

				</TR>
			</table>
		</form>
	</body>
</html>