<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
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
  	 /**
  	  * 打印
  	  */
  	 function seeDetail(ID) {       
            window.location.href.target='blank'
           window.location.href='epor.do?actionType=doSelectPrFormPrint&prFormID='+ID 
     }
     
     function SeeDetail(ID) {
		return '<a href="epor.do?actionType=doSelectPrForm&prFormID=' + ID
				+ '" target="_blank">' + ID + '</span>';
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
      		alert('当前状态不能修改!')
      	}

      }
      
      function check2(index){
      	if(typeof(bb)!='undefined'){
      		bb(index);
      	}else{
      		alert('当前状态不能修改!')
      	}

      }
  </script>
	<body>
		<div id="hello-win">
			<div id="hello-tabs"></div>
		</div>
		<table>
			<tr>
				<td>
				<a icon="print" href='epor.do?actionType=doSelectPrForm&admin=1&prFormID=${prForm.id }' target="_blank">详细信息/the details</a>
				</td>
			</tr>
		</table>

		<table>
		
			<tr>
				<td>
					<label>
						<bean:message key="applicant" />:
					</label>
					${prForm.tempolyeeByApplicantId.name }
				</td>
				<td>
					<label>
						<bean:message key="department" />:
					</label>
					${prForm.tempolyeeByApplicantId.tdepartment.name }
				</td>
				<td>
					<label>
						<bean:message key="phone" />:
					</label>
					${prForm.telephone}
				</td>
				<td>
					<label>
						<bean:message key="applicationDate" />:
					</label>
					${prForm.applicantDate}
				</td>
			</tr>
			<tr>
				<td>
					<label>
						<bean:message key="buyer" />:
					</label>
					${prForm.tempolyeeByBuyerId }
				</td>
				<td>
					<label>
						<bean:message key="costCenter" />:
					</label>
					${prForm.prCostCenter.costCenterName }/${prForm.prCostCenter.remark }
				</td>
				<td>
					<label>
						<bean:message key="kind" />:
					</label>
					${prForm.typeName }
				</td>



				<td>
					<label>
					<bean:message key="prType" />:
						${prForm.isCapitalName }/
					</label>
					<c:if test="${prForm.isPlan==0 }">
						<label>
							<bean:message key="inPlan" />
						</label>
					</c:if>

					<c:if test="${prForm.isPlan==1 }">
						<label>
							<bean:message key="outOfPlan" />
						</label>
					</c:if>


				</td>

			</tr>
			<tr>
				<td colspan="2">
					<label>
						<bean:message key="recommendedSupplier" />：
					</label>
					${prForm.recommendedSupplier }
				</td>
				<td>
					<label>
						<bean:message key="actualSupplier" />：
					</label>
					${prForm.acutalSupplier }
				</td>
				<td>
					<c:if test="${prForm.isInTheSap==1 }">
						<label>
							<bean:message key="supplierInSAP" />
						</label>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label>
						<bean:message key="pptel" />：
					</label>
					${prForm.receivingPlacePerpoleTel }
				</td>
				<td>
					<label>
						<bean:message key="prState" />:
					</label>
					${prForm.stateName }
				</td>
				<td>
					<label>
						<bean:message key="categroy" />:
					</label>
					${prForm.totalCategroy }
				</td>
			</tr>
			<tr>
			
				<td>
					<label>
						PR_SN:
					</label>
					${prForm.prsn }
				</td>
				
				<td></td>
			</tr>
			<c:if test="${prForm.prProject.sapNo !='' }">
			<tr>
				<td>
					<label>
						<bean:message key="projectSAPnumber" />:
					</label>
					${prForm.prProject.sapNo }
				</td>
				<td>
					<label>
						<bean:message key="projectNumber" />:
					</label>
					${prForm.prProject.projectId }
				</td>
				<td>
					<label>
						<bean:message key="projectMgr" />:
					</label>
					${prForm.prProject.number }
				</td>
				<td>
					<label>
						<bean:message key="projectContext" />:
					</label>
					${prForm.prProject.context }
				</td>
			</tr>
			</c:if>
		</table>
		<table>
			<tr id="intro_right">
				<td>
					<bean:message key="name" />
				</td>
				<td>
					<bean:message key="remark" />
				</td>
				<td>
					<bean:message key="quantity" />
				</td>
				<td>
					<bean:message key="unit" />
				</td>
				<td>
					<bean:message key="requiredDeliveryDate" />
				</td>
				<td>
					<bean:message key="price_a" />
				</td>
				
				<td>
					<bean:message key="buyer" />
				</td>
			
				<td>
					币种
				</td>
				<td>
					IO
				</td>
				<td>
					<bean:message key="billID" />
				</td>
			</tr>
			<c:forEach items="${prForm.prBuyContexts}" var="item">
				<tr>
					<td>
						${item.description }
					</td>
					<td>
						${item.remark }
					</td>
					<td>
						${item.quantity}
					</td>
					<td>
						${item.unit }
					</td>
					<td>
						${item.expectDeliveryDate }
					</td>
					<td>
						<fmt:formatNumber pattern="#,#00.0#">${item.unitPriceE }</fmt:formatNumber> 
						
					</td>
				
					<td>
						${item.buyerId }
					</td>
					
					<td>
						${item.currency }
					</td>
					<td>
						${item.glAccount }
						<label id="a${item.id }"></label>
						<input type="button" value="<bean:message key='edit' />" onclick="check('${item.id }')">
					</td>
					<td>
						${item.orderNo }
						<label id="b${item.id }"></label>
						<input type="button" value="<bean:message key='edit' />" onclick="check2('${item.id }')">
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
					<td></td>
				<td>
					
					<fmt:formatNumber pattern="#,#00.0#">${prForm.totalE }</fmt:formatNumber> 
				</td>
				<td></td>
				<td></td>

			</tr>
			
				<tr>
					<c:if test="${prForm.fileName != ''}">
						<td>
							 <a href="#" onClick="download(this,'${prForm.id}','${prForm.fileName}')">附件下载:${prForm.fileName }</a>
						</td>
					</c:if>

					<c:if test="${prForm.north_AmericaFileName != null}">
						<td>
							<a href="#" onClick="download(this,'${prForm.id}','${prForm.north_AmericaFileName}')">北美附件:${prForm.north_AmericaFileName }</a>		
						</td>
					</c:if>
				</tr>
				
		
		</table>
		<table>
			<tr id="intro_right">
				<td>
					<bean:message key="approvalPosition" />
				</td>
				<td>
					<bean:message key="approvalName" />
				</td>
				<td>
					<bean:message key="suggestion" />
				</td>
				<td>
					<bean:message key="approvalOpinion" />
				</td>
				<td>
					<bean:message key="approvalDate" />
				</td>
			</tr>
			<c:forEach items="${prForm.copyPrApprovedForm}" var="item">
				<tr>
					<td>
						${item.type }
					</td>
					<td>
						${item.tempolyee.name }
					</td>
					<td>
						${item.isApprovedName }
					</td>
					<td>
						${item.context }
					</td>
					<td>
						${item.datetime }
					</td>
				</tr>
			</c:forEach>
		<tr id="intro_right">
				<td>
					/
				</td>
				
			</tr>
		</table>

	</body>
</html>
