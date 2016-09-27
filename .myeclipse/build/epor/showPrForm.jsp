<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="mytag"%>
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
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
		<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="ext/ext-all.js"></script>
		<link rel="stylesheet" type="text/css"
			href="ext/resources/css/ext-all.css">
		<link rel="stylesheet" type="text/css" href="style/button.css">
		<link rel="stylesheet" type="text/css" href="style/icon.css">
		<link rel="stylesheet" type="text/css" href="css/vave.css">
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script>		
		<script type='text/javascript' src='dwr/interface/prFormListManager.js'></script>
		<script type="text/javascript" src="epor/js/delForm.js"></script>		
		
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
	
	function dl(id, fileName) {
		var url = '\\' + id + '\\' + fileName;
		document.getElementById('ffile').value = url
		document.getElementById('f1').submit();

 	}
  	 /**
  	  * 打印
  	  */
  	 function seeDetail(ID) {       
            window.location.href.target='blank'
           window.location.href='epor.do?actionType=doSelectPrFormPrint&prFormID='+ID 
     }
    
      
     /**
      * 删除
      */
      function del(){
      	   window.location.href='epor.do?actionType=doDeletePR'
      }
      
     function check(index){
      	if(typeof(aa)!='undefined'){
      		aa(index);
      	}else{
      		alert('当前状态不能修改!');
      	}

      }
      
      function check2(index){
      	if(typeof(bb)!='undefined'){
      		bb(index);
      	}else{
      		alert('当前状态不能修改!')
      	}

      }
      
      function checkFT(index){
      	if(typeof(ft)!='undefined'){
      		ft(index);
      	}else{
      		alert('当前状态不能修改!')
      	}
      }
      
     function check3(){
      	if(typeof(changeCategory)!='undefined'){
      		changeCategory();
      	}else{
      		alert('当前状态不能修改!')
      	}

      }
     
     //变更审批意见
     function changeSuggestion(pid,type) {
     	var state = 0;
     	if(window.confirm('确定退回到我审批的状态?')) {
     		if(type == "部门" ) return 1;
				else if(type =="采购员") state = 4;
				else if(type =="采购经理") state = 3;
				else if(type =="财务") state = 2;
				else if(type =="财务经理审批") state = 5;
				else if(type =="副总经理审批") state = 6;
				else if(type =="总经理审批") state = 7;
				else if(type =="回填信息") state = 8;
				else if(type =="等待采购员填写订单号") state = 10;
				else if(type =="PCLSAP录入") state = 9;
     		 var url = 'epor.do?actionType=doCancelSuggetsion&id='+pid+'&state='+state;
     		// alert(type)
     		 window.location.href=url
     	}
     }

     
     function queryPic(pid){
     	window.location.href.target='blank';
     	window.location.href='epor.do?actionType=queryPic&pid='+pid;
     }
  </script>
	<body onload="inita()">

		<div id="hello-win">
			<div id="hello-tabs"></div>
		</div>
		<div id="hello-win3">
			<div id="hello-tabs3"></div>
		</div>

		<table width="1240">
			<tr>

				<c:if test="${prForm.tempolyeeByApplicantId.uid==emp.uid && prForm.newStateName!='finish'}">
					<td>
						<input type="button" icon="icon-del" value="删除" onclick="doDel('${prForm.id }','${prForm.state }')">
					</td>
				</c:if>
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
					${prForm.tempolyeeByApplicantId.name}/${prForm.tempolyeeByApplicantId.ename}
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
						Department:
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
						IO: ${prForm.io }
					</label>
					
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
					Project: ${prForm.prProject.context }

				</td>
			</tr>

			<tr>
				<td>
					<label>
						Purchase Category:
					</label>
					${prForm.buyer1 }
				</td>
				<td>
				
						<label>
							Category Detail:
						</label>
						${prForm.buyer2 }
				
				</td>
			</tr>

			<tr>
				<td>
					<label>
						Recommended Supplier:
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
						Purpose:
						<br>
					</label>
					<textarea rows="4" cols="35" readOnly = "readOnly">${prForm.remark }</textarea>
				</td>
				<td>
					<label>
						For Which Department/Workshop/Equipment?
						<br>
					</label>
					<textarea rows="4" cols="35" readOnly = "readOnly">${prForm.workshopToUse }</textarea>
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
		<table>
			<tr>
				<td colspan="15">
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
						<input type="button" value="North American attachment"
							onClick="dl('${prForm.id}','${prForm.north_AmericaFileName}')">
						<form action="epor.do?actionType=doGetAttach" method="post"
							id="f1">
							<input type="hidden" name="path" id="ffile">
						</form>

					</td>

				</c:if>
				
				<c:if test="${prForm.buyerFile != null}">

					<td>
						<input type="button" value="Buyer attachment"
							onClick="dl('${prForm.id}','${prForm.buyerFile}')">
						<form action="epor.do?actionType=doGetAttach" method="post"
							id="f1">
							<input type="hidden" name="path" id="ffile">
						</form>

					</td>

				</c:if>
			</tr>


		</table>

		<table width="1240">
			<tr>
				<td colspan="4">
					<label style="color: blue; size: 16px">
						Workflow Log
					</label>
					<hr>
				</td>
			</tr>

			<tr>
			
				<td width=200>
					Date
				</td>
				<td width=150>
					Author
				</td>
				<td width=150>
					Actions
				</td>
				<td>
					Remark
				</td>

			</tr>

			<c:forEach items="${prForm.copyPrApprovedForm}" var="item">

				<tr>
					<td width=200>
						<fmt:formatDate value="${item.datetime }" pattern="yyyy-MM-dd HH:mm:ss" />


					</td>
					<td width=150>
						${item.tempolyee.name }/ ${item.tempolyee.ename }
					</td>
					<td width=150>
						${item.isApprovedName }
					</td>
					<td>
						${item.newContext }
					</td>

				</tr>

			</c:forEach>
		</table>

		<jsp:include page="approve/${url}"></jsp:include>
	</body>
</html>
