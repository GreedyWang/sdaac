<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta name="viewport" content="width=device-width,initial-scale=1" />		
		<link rel="stylesheet" href="mobile/jquery.mobile-1.3.0.css">
				<script src="mobile/jquery.js"></script>
		<script src="mobile/jquery.mobile-1.3.0.js"></script>
		<link rel="stylesheet" type="text/css" href="css/vave.css">
	</head>
	<body>
<div data-role="header">
    <a href="#" data-icon="delete">Need Explain</a>
    <h1>Detail</h1>
    <a href="#" data-icon="check" data-theme="b">Approve</a>
</div>
		<table>		
			<tr>
				<td>
					<label>
						申请人:
					</label>
					${prForm.tempolyeeByApplicantId.name }
				</td>
				<td>
					<label>
						部门:
					</label>
					${prForm.tempolyeeByApplicantId.tdepartment.name }
				</td>
				<td>
					<label>
						电话分机:
					</label>
					${prForm.telephone}
				</td>
				
			</tr>
			<tr>
				<td>
					<label>
						申请日期:
					</label>
					<fmt:formatDate value="${prForm.applicantDate}"></fmt:formatDate>
					
				</td>
				<td>
					<label>
						成本中心:
					</label>
					${prForm.prCostCenter.costCenterName }/${prForm.prCostCenter.remark }
				</td>
				<td>
					<label>
					PR类型:
						${prForm.isCapitalName }/
					</label>
					<c:if test="${prForm.isPlan==0 }">
						<label>
							计划内
						</label>
					</c:if>

					<c:if test="${prForm.isPlan==1 }">
						<label>
							计划外
						</label>
					</c:if>


				</td>

			</tr>
			<tr>
				<td>
					<label>
						实际供应商：
					</label>
					${prForm.acutalSupplier }
				</td>
				<td >
					<label>
						交货地点/收货人/电话：
					</label>
					${prForm.receivingPlacePerpoleTel }
				</td>
				<td>
					<c:if test="${prForm.isInTheSap==1 }">
						<label>
							供应商在SAP系统内
						</label>
					</c:if>
				</td>
			</tr>
			<tr>
				
				<td>
					<label>
						PR状态:
					</label>
					${prForm.stateName }
				</td>
				<td>
					<label>
						总账科目:
					</label>
					${prForm.totalCategroy }
				</td>
				<td>
					<label>
						PR_SN:
					</label>
					${prForm.prsn }
				</td>
			</tr>
			<c:if test="${prForm.prProject.sapNo !='' }">
			<tr>
				<td>
					<label>
						项目sap号:
					</label>
					${prForm.prProject.sapNo }
				</td>
				<td>
					<label>
						项目经理:
					</label>
					${prForm.prProject.number }
				</td>
				<td>
					<label>
						项目内容:
					</label>
					${prForm.prProject.context }
				</td>
			</tr>
			</c:if>
		</table>
		<table>
			<tr>
				<td>
					名称，标准/样式参考资料
				</td>
				<td>
					备注
				</td>
				<td>
					数量
				</td>
				<td>
					单位
				</td>
				<td>
					要求交货日期
				</td>
				<td>
					单价(请购人估价)
				</td>
				
				<td>
					采购员
				</td>
			
				<td>
					币种
				</td>
				<td>
					IO
				</td>
				<td>
					定单NO.
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
						<fmt:formatDate value="${item.expectDeliveryDate }"></fmt:formatDate>						
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

					</td>
					<td>
						${item.orderNo }
						
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
		</table>
		<table>
			<tr >
				<td>
					审批人职位
				</td>
				<td>
					审批人姓名
				</td>
				<td>
					建议
				</td>
				<td>
					审批意见
				</td>
				<td>
					审批日期
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
		</table>
		<div data-role="footer" class="ui-bar" data-position="fixed">
			<a href="#">Login Out</a>
			<a href="#">keyA</a>
			<a href="#">keyA</a>
		</div>
	</body>
</html>
