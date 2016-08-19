<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

		<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
		<link rel="stylesheet" type="text/css" href="css/icons.css">
		<link rel="stylesheet" type="text/css" href="css/UploadDialog.css">
		<link rel="stylesheet" type="text/css" href="css/file-upload.css" />
		<link rel="stylesheet" type="text/css" href="css/vave3.css">
		
		<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="ext/ext-all.js"></script>
		<script type="text/javascript" src="epor/js/ardetail.js"></script>


		<script type="text/javascript" src="epor/util/UploadDialog.js"></script>
		<script type="text/javascript" src="epor/util/FileUploadField.js"></script>
		<script type="text/javascript" src="epor/util/swfupload.js"></script>
		<script type="text/javascript"
			src="dwr/interface/subjectDetailService.js"></script>
		<script type='text/javascript'
			src='dwr/interface/prFormListManager.js'></script>
		<script type='text/javascript' src='dwr/interface/formService.js'></script>
		<script type='text/javascript' src='dwr/interface/PuyerListBizManager'></script>

		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type="text/javascript" src="epor/js/newRejectForm.js"></script>
		<script type="text/javascript"
			src="epor/baseInfo/js/util/loadProjectData.js"></script>

	</head>

	<body>

		<input type="hidden" id="fpid" value="${prForm.id}">
		<input type="hidden" id="processInstanceId" value="${prForm.processInstanceId }">
		<input type="hidden" id="fissueFor" value="${prForm.issueFor}">
		<input type="hidden" id="ftelephone" value="${prForm.telephone}">
		<input type="hidden" id="fisAssignation" value="${prForm.isAssignation}">
		<input type="hidden" id="fisUrgency" value="${prForm.isUrgency}">
		<input type="hidden" id="fisCapital" value="${prForm.isCapital}">
		<input type="hidden" id="fisPlan" value="${prForm.isPlan}">
		<input type="hidden" id="fcapitalcategroy" value="${prForm.capitalcategroy}">
		<input type="hidden" id="fpid" value="${prForm.id}">
		<input type="hidden" id="fbuyer" value="${prForm.tempolyeeByBuyerId}">
		<input type="hidden" id="fremark" value="${prForm.remark}">
		
		<input type="hidden" id="area" value="${logineduser.emp.area }">
		
		<input type="hidden" id="fccid" value="${prForm.prCostCenter.id}">
		<input type="hidden" id="fprProjectid" value="${prForm.prProject.id}">
		<input type="hidden" id="farno" value="${prForm.arno}">
		
		<input type="hidden" id="freceivingPlacePerpoleTel" value="${prForm.receivingPlacePerpoleTel}">
		<input type="hidden" id="frecommendedSupplier" value="${prForm.recommendedSupplier}">
		<input type="hidden" id="fworkshopToUse" value="${prForm.workshopToUse}">
		
		<input type="hidden" id="fprCostCenterid" value="${prForm.prCostCenter.id}">
		
		<input type="hidden" id="fprCostCenterOwner" value="${prForm.prCostCenter.owner}">
		<input type="hidden" id="fmanagerUid" value="${prForm.prProject.managerUid}">
		<input type="hidden" id="fbuyerNetid" value="${prForm.buyerNetid}">
		
		<div id="test">
			<div id="fi-button-msg" style="display: none;"></div>

			<div id="fi-button"></div>
		</div>
		
		<div id="div1"></div>
		
		<table width=1060>
			<tr>
				<td colspan="15" >
					<label style="color: blue; size: 16px">
						Attachments:
					</label>
					<hr>
				</td>
			</tr>
			<tr>
				<c:if test="${prForm.fileName != ''}">

					<td>
						<input type="button" value="Other Attachment"
							onClick="dl('${prForm.id}','${prForm.fileName}')">
						<form action="epor.do?actionType=doGetAttach" method="post"
							id="f1">
							<input type="hidden" name="path" id="ffile">
						</form>
					</td>

				</c:if>
				<c:if test="${prForm.urgentFile != ''}">

					<td>
						<input type="button"
							value="Urgent Purchase Form"
							onClick="dl('${prForm.id}','${prForm.urgentFile}')">
						<form action="epor.do?actionType=doGetAttach" method="post"
							id="f1">
							<input type="hidden" name="path" id="ffile">
						</form>
					</td>

				</c:if>
				<c:if test="${prForm.singlesourceFile != ''}">

					<td>
						<input type="button"
							value="Single Source Form"
							onClick="dl('${prForm.id}','${prForm.singlesourceFile}')">
						<form action="epor.do?actionType=doGetAttach" method="post"
							id="f1">
							<input type="hidden" name="path" id="ffile">
						</form>
					</td>

				</c:if>
				<c:if test="${prForm.bigcountFile != ''}">

					<td>
						<input type="button"
							value="100K Approval Form"
							onClick="dl('${prForm.id}','${prForm.bigcountFile}')">
						<form action="epor.do?actionType=doGetAttach" method="post"
							id="f1">
							<input type="hidden" name="path" id="ffile">
						</form>
					</td>

				</c:if>



				<c:if test="${prForm.north_AmericaFileName != null}">

					<td>
						<input type="button" value="北美附件"
							onClick="dl('${prForm.id}','${prForm.north_AmericaFileName}')">
						<form action="epor.do?actionType=doGetAttach" method="post"
							id="f1">
							<input type="hidden" name="path" id="ffile">
						</form>

					</td>

				</c:if>
			</tr>


		</table>
		<table width="1060">
			<tr>
				<td colspan="5">
					<label style="color: blue; size: 16px">
						Approval Route List
					</label>
					<hr>
				</td>
			</tr>

			<tr>
				<td width=100>
					Position
				</td>
				<td width=150>
					Approver
				</td>
				<td width=150>
					Opinion
				</td>
				<td>
					Comments
				</td>

				<td width=100>
					Date
				</td>
			</tr>

			<c:forEach items="${prForm.copyPrApprovedForm}" var="item">

				<tr>
					<td width=100>
						${item.type }
					</td>
					<td width=150>
						${item.tempolyee.name }${item.tempolyee.ename }
					</td>
					<td width=150>
						${item.isApprovedName }
					</td>
					<td>
						${item.newContext }
					</td>
					<td width=100>
						<fmt:formatDate value="${item.datetime }" pattern="yyyy-MM-dd" />


					</td>
				</tr>

			</c:forEach>
		</table>
			<jsp:include page="approve/refillin.jsp"></jsp:include>
	</body>
		<script type="text/javascript">
		function dl(id, fileName) {
			var url = '\\' + id + '\\' + fileName;
			document.getElementById('ffile').value = url
			document.getElementById('f1').submit();
 		}
	</script>
</html>
