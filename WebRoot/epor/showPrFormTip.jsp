<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>">
		<link rel="stylesheet" type="text/css" href="css/vave.css">
		<script type="text/javascript" src='epor/js/download.js'></script>
	</head>
	<script type="text/javascript">
	
	     
     function inita(){
	     var isUrgency = '${prForm.isUrgency}';
	     var isAssignation = '${prForm.isAssignation}';
	     if(isUrgency == 1){
	     	document.getElementById('isUrgency').checked = true;
	     }
   	     if(isAssignation == 1){
	     	document.getElementById('isAssignation').checked = true;
	     }	
     }
     </script>

	<body onload="inita()">
		<div id="hello-win">
			<div id="hello-tabs"></div>
		</div>
		<table>
			<tr>
				<td>
					<a icon="print"
						href='epor.do?actionType=doSelectPrForm&prFormID=${prForm.id }'
						target="_blank">详细信息/the details</a>
				</td>
			</tr>
		</table>


		<table width="1240">
			<tr>
				<td colspan="2">
					<label style="color: blue; size: 16px">
						User Info:
					</label>
					<hr>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label>
						Issue By:
					</label>
					${prForm.tempolyeeByApplicantId.name}/${prForm.tempolyeeByApplicantId.mail}
				</td>
			<tr>
			<tr>
				<td colspan="2">
					<label>
						Issue For:
					</label>
					<c:if test="${prForm.issueFor == 1}">
						Company
					</c:if>
					<c:if test="${prForm.issueFor == 2}">
						SY Plant
					</c:if>
					<c:if test="${prForm.issueFor == 3}">
						YT Plant
					</c:if>
					<c:if test="${prForm.issueFor == 4}">
						SH Plant
					</c:if>
				</td>
			</tr>

			<tr>
				<td colspan="2">
					<label>
						Company:
					</label>
					<c:if test="${prForm.source == 1}">
						Company-${prForm.tempolyeeByApplicantId.tdepartment.name }
					</c:if>
					<c:if test="${prForm.source == 2}">
						SY Plant-${prForm.tempolyeeByApplicantId.tdepartment.name }
					</c:if>
					<c:if test="${prForm.source == 3}">
						YT Plant-${prForm.tempolyeeByApplicantId.tdepartment.name }
					</c:if>
					<c:if test="${prForm.source == 4}">
						SH Plant-${prForm.tempolyeeByApplicantId.tdepartment.name }
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label>
						Telephone:
					</label>
					${prForm.telephone}
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label style="color: blue; size: 16px">
						PR Header:
					</label>
					<hr>
				</td>
			</tr>
			<tr>
				<td>
					<label>
						PR No.:
					</label>
					${prForm.sapNO}
				</td>
				<td>
					<label>
						Status:
					</label>
					${prForm.newStateName}
				</td>
			</tr>
			<tr>
				<td>
					<label>
						Buyer:
					</label>
					${prForm.relateuid}
				</td>
				<td>
					<label>
						PR Date:
					</label>
					<fmt:formatDate pattern="yyyy-MM-dd"
						value="${prForm.applicantDate }" />
				</td>
			</tr>
			<tr>
				<td>
					<label>
						In Budget or Not:
					</label>
					<c:if test="${prForm.isPlan==0 }">
						In Budget
					</c:if>

					<c:if test="${prForm.isPlan==1 }">
						Not
					</c:if>
				</td>

				<td>
					<label>
						Capital or Expense:
					</label>
					${prForm.isCapitalName }
				</td>
			</tr>
			<c:if test="${prForm.isCapital==0 }">

				<tr>
					<td>
						<label>
							Capital Category:
						</label>
						${prForm.capitalcategroyName}
					</td>
					<td>
						AR No.: ${prForm.arno}
					</td>

				</tr>
			</c:if>
			<tr>
				<td>
					Urgent:
					<input type="checkbox" id="isUrgency" disabled="disabled"
						value="${prForm.isUrgency}">


				</td>
				<td>
					<c:if test="${prForm.prCostCenter.remark != null}">
					Cost Center:		
					${prForm.prCostCenter.costCenterName }/${prForm.prCostCenter.remark}
					</c:if>
				</td>
			</tr>
			<tr>
				<td>
					Single Source:
					<input type="checkbox" id="isAssignation" disabled="disabled"
						value="${prForm.isAssignation}">
				</td>
				<td>
					Project No.: ${prForm.prProject.sapNo }

				</td>
			</tr>

			<tr>
				<td>
					<label>
						Supplier:
					</label>
					${prForm.recommendedSupplier }
				</td>
				<td>
					<c:if test="${prForm.prsn != null}">
						<label>
							SAP_SN:
						</label>
						${prForm.prsn }
					</c:if>
				</td>
			</tr>

			<tr>
				<td>
					<label>
						Deliever to:
					</label>
					${prForm.receivingPlacePerpoleTel }
				</td>

				<td>
					<label>
						GL:
						<input type="button" value="编辑" onclick="check3()">
					</label>
					<label id="totalCategroy">
						${prForm.totalCategroy }
					</label>
				</td>
			</tr>

			<tr>
				<td>
					<label>
						Purpose or Comments:
						<br>
					</label>
					<textarea rows="4" cols="35">${prForm.remark }</textarea>
				</td>
				<td>
					<label>
						For Which Department/Workshop/Equipment?
						<br>
					</label>
					<textarea rows="4" cols="35">${prForm.workshopToUse }</textarea>
				</td>
			</tr>
		</table>

		<table width="1240">
			<tr>
				<td colspan="15">
					<label style="color: blue; size: 16px">
						PR Detail:
					</label>
					<hr>
				</td>
			</tr>
			<tr>
				<td>
					Description名称(length<21)
				</td>
				<td>
					Brand品牌
				</td>
				<td>
					Type型号
				</td>
				<td>
					Qty.数量
				</td>
				<td>
					UOM单位
				</td>
				<td>
					Req. Deliever date
				</td>
				<td>
					Unit Price(no taxd/RMB)
				</td>
				<td>
					Total Price
				</td>
				<td>
					Remark备注
				</td>
				<td>
					IO
				</td>
				<td>
					Order No.
				</td>
				<td width="60">
					Receiving Date
				</td>

			</tr>
			<c:forEach items="${prForm.prBuyContexts}" var="item">
				<tr>
					<td>
						${item.description }
					</td>
					<td>
						${item.band }
					</td>
					<td>
						${item.type }
					</td>
					<td>
						${item.quantity}
					</td>
					<td>
						${item.unit }
					</td>
					<td>
						<fmt:formatDate pattern="yyyy-MM-dd"
							value="${item.expectDeliveryDate }" />
					</td>
					<td>

						<fmt:formatNumber pattern="#,#00.00#">${item.unitPriceE }</fmt:formatNumber>

					</td>
					<td>

						<fmt:formatNumber pattern="#,#00.00#">${item.quantity * item.unitPriceE }</fmt:formatNumber>

					</td>
					<td>
						${item.remark }

					</td>
					<td>
						${item.io }
						<label id="a${item.id }"></label>

						<input type="button" value="<bean:message key='edit' />"
							onclick="check('${item.id }')">

					</td>
					<td>
						${item.orderNo }
						<label id="b${item.id }"></label>
						<input type="button" value="<bean:message key='edit' />"
							onclick="check2('${item.id }')">
					</td>
					<td>
						${item.PODate }

					</td>

				</tr>
			</c:forEach>
			<tr>
				<td colspan="6">
					Total:
				</td>
				<td>
					<fmt:formatNumber pattern="#,#00.00#">${prForm.totalE }</fmt:formatNumber>
				</td>

				<td>
					<mytag:rights needRoleID="PR"
						hasRoles="${logineduser.emp.roleids_b}">
						<fmt:formatNumber pattern="#,#00.00#">${prForm.total }</fmt:formatNumber>(RMB)
					</mytag:rights>
				</td>
				<td colspan="5"></td>
			</tr>
		</table>




		</table>


	</body>
</html>
