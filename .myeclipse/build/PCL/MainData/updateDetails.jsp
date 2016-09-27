<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script>
	function init() {

		//反应速度类型
		var hourType = '${rs.feedbackType }' 
		
		if(hourType == 1) {
			document.getElementById('24').checked = true;
		}else if(hourType == 2) {
			
			document.getElementById('48').checked  = true;
			
		}else if(hourType == 3) {
			document.getElementById('49').checked = true;
		}
		//物料号类型
		var isCreate = '${rs.isCreate }' 
		if(isCreate == 1) {
			document.getElementById('new').checked = true;
		}else if(isCreate == 2) {
			document.getElementById('update').checked = true;
		}
		//selfMade
		var selfMade = '${rs.isSelfMade }' 
		if(selfMade == 1) {
			document.getElementById('selfMade').checked = true;
		}
		
		var isStorageManage = '${rs.isStorageManage }' 
		if(isStorageManage == 1) {
			document.getElementById('isStorageManage').checked = true;
		}
		
		var isPackage = '${rs.isPackage }' 
		if(isStorageManage == 1) {
			document.getElementById('isPackage').checked = true;
		}
		
		var type2 = '${rs.type2 }' 
		if(type2 == 1) {
			document.getElementById('type2').checked = true;
		}else if(type2 == 2) {
			document.getElementById('type3').checked = true;
		}
		
		var isExpense = '${rs.isExpense }' 
		if(isStorageManage == 1) {
			document.getElementById('isExpense').checked = true;
		}
		var isDanger = '${rs.isDanger }' 
		if(isDanger == 1) {
			document.getElementById('isDanger').checked = true;
		}
		//设置采购员名称
		var buyerNames = document.getElementById('buyerName').options
		
		for (var i = 0; i < buyerNames.length; i++) {
			if ('${rs.buyerName}' == buyerNames[i].value) {
				buyerNames[i].selected = 'selected'
			}							
		}
		//类型
		var typeName = document.getElementById('typeName').options		
		var nowTypeName = '${rs.typeName}'.split('_')[0];						
			for (var i = 0; i < typeName.length; i++) {		
						if ( nowTypeName == typeName[i].value) {							
							typeName[i].selected = 'selected'
						}							
					}					
					
		//设置图片附件
		if('${rs.file1 }' == null || '${rs.file1 }' == '') {		
			document.getElementById('img1').height = 0			
		}
		
	}
</script>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/style_admin.css">
		<link rel="stylesheet" type="text/css" href="css/vave.css">		
	</head>
	<body onload="init()">
	<form action="mainData.do?actionType=doUpdate" method = "post">
	<input type="hidden" name="form.uuid" value="${rs.uuid }">  
		<table BORDER="1" width="800">
			<tr>
				<td colspan="4" align="middle">
					<label>间接物料主数据申请单_updateDetails.jsp</label>
				</td>
			</tr>
			<tr>
				<td colspan="2">物料号/Material SN:${rs.materialSn }</td>
				<td colspan="2">编号/ID：${rs.uuid }</td>
			</tr>
			<tr>
				<td colspan="4" align="middle" >申请人填写</td>
			</tr>
			<tr>
				<td>电话/Phone</td>
				<td><input type="text" name="form.phone" value="${rs.phone}"> </td>
				<td>成本中心/Cost Center</td>
				<td><input type="text" name="form.costCenter" value="${rs.costCenter }"></td>
			</tr>
			<tr>
				<td>24小时内响应/feedback in 24H<input type="radio" name="form.feedbackType" group="aa" value="1" id="24"></td>
				<td>48小时内响应/feedback in 48H<input type="radio" name="form.feedbackType" group="aa" value="2" id="48"></td>
				<td colspan="2" >响应时间超出48小时/feedback over 48H<input type="radio" name="form.feedbackType" group="aa" value="3" id="49"></td>
			</tr>
			<tr>
				<td colspan="4" align="middle" >注意：普通物料每次设置时间为从管理中心发出到物料主数据小组收到要1到2个工作日</td>
			</tr>
			<tr>
				<td>工厂/Factory</td>
				<td>${rs.factory }</td>
				<td>库存地点/Storage Address</td>
				<td>${rs.storageAddress }</td>
			</tr>
			<tr>
				<td>新建/New<input type="radio" name="form.isCreate" group="bb" value="1" id="new"></td>
				<td>修改/Change<input type="radio" name="form.isCreate" group="bb" value="2" id="update"></td>
				<td>旧物料号/Old Material SN</td>
				<td><input type="text" name="form.oldMaterialSn" value="${rs.oldMaterialSn }"></td>
			</tr>
			<tr>
				<td>物料类型</td>							
				<td>
				<select name="form.typeName" id="typeName">
				<option value="">选择类型</option>
	 		 			<c:forEach items="${types}" var="s">	
	   						<option value="${s}">${s}</option>
	  		 			</c:forEach>
					</select> 
				</td>
			</tr>
			<tr>
				<td>物料描述(中文,小于20个字长)/Description in Chinese</td>
				<td colspan=3 >
				<textArea rows=3 cols=130 name="form.description">${rs.description }</textArea>
					
				</td>
			</tr>
			<tr>
				<td>物料描述(英文,40个字母长)/Description in English</td>
				<td colspan=3 >
				<textArea rows=3 cols=130 name="form.description2">${rs.description2 }</textArea>
					
				</td>
			</tr>
			<tr>
				<td>备注/Remark</td>
				<td colspan=3 >
				<textArea rows=3 cols=130 name="form.remark">${rs.remark }</textArea>				
				</td>
			</tr>
			<tr>
				<td>制造商物料号/Manufacturer SN</td>
				<td><input type="text" name="form.manufacturerSn" value="${rs.manufacturerSn }"></td>
				<td>制造商名称/manufacturer Name</td>
				<td><input type="text" name="form.manufacturerName" value="${rs.manufacturerName }"></td>
			</tr>
			<tr>
				<td>基本单位/Base Unit</td>
				<td><input type="text" name="form.unit" value="${rs.unit }"></td>
				<td>资产标签号（仅用于ERSA（备件））/ERSA</td>
				<td><input type="text" name="form.ersa" value="${rs.ersa }"></td>
			</tr>
			<tr>
				<td>危险物料/Is Danger<input type="checkbox" name="form.isDanger" id="isDanger" ></td>
				<td></td>
				<td>If yes, FID #:</td>
				<td><input type="text" name="form.fid" value="${rs.fid }"></td>
			</tr>
			<tr>
				<td>最低库存数量<input type="text" name="form.amin" value="${rs.amin }"></td>
				<td>最高库存数量<input type="text" name="form.amax" value="${rs.amax }"></td>
				<td>年度用量<input type="text" name="form.annual" value="${rs.annual }"></td>
			</tr>
			<tr>
				<td>长度/Long<input type="text" name="form.longa" value="${rs.longa }"></td>
				<td>宽度/Width<input type="text" name="form.width" value="${rs.width }"></td>
				<td>高度/Height<input type="text" name="form.height" value="${rs.height }"></td>
			</tr>
			<tr>
				<td>自制/<input type="checkbox" name="form.isSelfMade" id="selfMade"></td>
				<td><br></td>
				<td>单价/Unit Price</td>
				<td><input type="text" name="form.unitPrice" value="${rs.unitPrice }"></td>
			</tr>
			
			<tr>
			<td >是否为包装</td>
			<td><input type="checkbox" name="form.isPackage" value=1"> </td>
			<td >采购员
				<select name="form.buyerName" id="buyerName">
						
						<option value="郑驰晖487">郑驰晖487</option>
						<option value="徐桂根485">徐桂根485</option>
						<option value="任士龙481">任士龙481</option>
						<option value="刘  鸣">刘 鸣</option>								
						<option value="王其华">王其华</option>		
						<option value="张亚波483">张亚波483</option>				
				</select> 
			</td>
			</tr>
			
	
		</table>
		<table>
			<tr>
				<td>
					<img id="img1" height="300" width="480" src="P:\Public\Indirect Material\IMM/${rs.tempolyeeByApplicantId.name }\<fmt:formatDate value="${rs.applicateDate }" pattern="yyyy-MM-dd-HH-mm"/>/${rs.file1 }">
			
				</td>
			</tr>
			<tr>
				<td colspan="4" align="middle" class="header" >其他信息</td>
			</tr>
			
			<tr>
				<td>1. 是否做库存管理<input type="checkbox" name="form.isStorageManage"></td>
				<td><br></td>
				<td>2. 收货就费用化</td>
				<td><input type="text" name="form.isExpense"></td>
			</tr>
			<tr>
				<td colspan="4" align="middle">If Yes, Yes = UNBW             If No, Yes = NLAG                     If Yes, No Complete Item 3</td>
			</tr>
			<tr>
				<td>3. 设备维修项 (ERSA) <input type="radio" id="type2" group="cc"></td>
				<td>运营支持(HIBE)<input type="radio" id="type3" group="cc"></td>
			</tr>	
		
		</table>
		
		
		<table>
			<tr>
				<td colspan="4" align="middle" class="header" >采购信息</td>
			</tr>
			<tr>
						<td>
							供应商代码<input type="input" value="${rs.sapSupplyCode}" >
						</td>
						<td>
							供应商名称 <input type="input" value="${rs.supplysn}" > 
						</td>
						<td>
							联系人<input type="input" value="${rs.supplier}" >
						</td>
						<td>
							联系方式 <input type="input" value="${rs.supplyContact}" > 
						</td>
					</tr>
					<tr>
						<td>
							Sap合同号<input type="input" value="${rs.sapContract}">
						</td>
						<td>
							采购周期 <input type="input" value="${rs.leadTime}" > 
						</td>
						<td>
							标准包装<input type="input" value="${rs.standardPackage}" >
						</td>
						<td>
							最小起订量 <input type="input" value="${rs.minOrder}"> 
						</td>
					</tr>
		</table>
		
		<table>
			<tr>
				<td colspan="5" align="middle" class="header" >审批信息</td>
			</tr>
			<tr id="intro_right">
				<td>
					审批人职位/position
				</td>
				<td>
					审批人姓名/name
				</td>
				<td>
					建议/suggestion
				</td>
				<td>
					审批意见/opinion
				</td>
				<td>
					审批日期/date
				</td>
			</tr>
			<c:forEach items="${rs.approveForm}" var="item">			
				<c:if test="${item.tempolyee.name != null}" >			
					<tr>
						<td>
							${item.type }
						</td>
						<td>
							${item.tempolyee.name }
						</td>
						<td>
							${item.approvalName }
						</td>
						<td>
							${item.context }
						</td>
						<td>
							${item.datetime }
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	 		
			<input type="hidden" name="apForm.item.uuid" value="${rs.uuid }">  
			
				<tr>
					<td ><textarea cols="180" rows="3" name="apForm.context"></textarea></td>
					<td> <input type="submit" value="提交/submit"></td>
				</tr>
		</table>	
		</form>	
	
	
	</body>
</html>
