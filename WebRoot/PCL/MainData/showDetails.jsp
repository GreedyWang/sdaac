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
showDetails.jsp
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
		//设置图片附件
		if('${rs.file1 }' == null || '${rs.file1 }' == '') {		
			document.getElementById('img1').height = 0			
		}
		
	}
	
	  function del(){
	  	if(window.confirm('确认删除')){
      	   window.location.href='mainData.do?actionType=doDelete&id='+'${rs.uuid}'
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
	
		<table BORDER="1" width="800">
				<tr>
			<td>
				<a target="blank" href="mainData.do?actionType=doShowDetails&id=${id}">详细信息/Details</a>
			</td>
			<td>
					<input type="button" value="删除" onclick="del('${rs.uuid }')">
			</td>
		</tr>

			<tr>
				<td colspan="4" align="middle">
					<label>间接物料主数据申请单</label>
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
				<td colspan="2" align="middle" >
					姓名：${rs.tempolyeeByApplicantId.name }
				</td>
				<td colspan="1" align="middle" >
					部门：${rs.tempolyeeByApplicantId.tdepartment.name }
				</td>
				<td colspan="1" align="middle" >
					流程状态：${rs.stateName }
				</td>				
			</tr>
			<tr>
				<td>电话/Phone</td>
				<td><input type="text" name=".phone" value="${rs.phone}"> </td>
				<td>成本中心/Cost Center</td>
				<td><input type="text" name=".costCenter" value="${rs.costCenter }"></td>
			</tr>
			<tr>
				<td>24小时内响应/feedback in 24H<input type="radio" name=".feedbackType" group="aa" value="1" id="24"></td>
				<td>48小时内响应/feedback in 48H<input type="radio" name=".feedbackType" group="aa" value="2" id="48"></td>
				<td colspan="2" >响应时间超出48小时/feedback over 48H<input type="radio" name=".feedbackType" group="aa" value="3" id="49"></td>
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
				<td>新建/New<input type="radio" name=".isCreate" group="bb" value="1" id="new"></td>
				<td>修改/Change<input type="radio" name=".isCreate" group="bb" value="2" id="update"></td>
				<td>旧物料号/Old Material SN</td>
				<td><input type="text" name=".oldMaterialSn" value="${rs.oldMaterialSn }"></td>
			</tr>
			<tr>
				<td>类型：${rs.typeName}</td>
			</tr>
			<tr>
				<td>物料描述(中文,小于20个字长)/Description in Chinese</td>
				<td colspan=3 >
				<textArea rows=3 cols=130 name=".description">${rs.description }</textArea>
					
				</td>
			</tr>
			<tr>
				<td>物料描述(英文,40个字母长)/Description in English</td>
				<td colspan=3 >
				<textArea rows=3 cols=130 name=".description2">${rs.description2 }</textArea>
					
				</td>
			</tr>
			<tr>
				<td>备注/Remark</td>
				<td colspan=3 >
				<textArea rows=3 cols=130 name=".remark">${rs.remark }</textArea>				
				</td>
			</tr>
			<tr>
				<td>制造商物料号/Manufacturer SN</td>
				<td><input type="text" name=".manufacturerSn" value="${rs.manufacturerSn }"></td>
				<td>制造商名称/manufacturer Name</td>
				<td><input type="text" name=".manufacturerName" value="${rs.manufacturerName }"></td>
			</tr>
			<tr>
				<td>基本单位/Base Unit</td>
				<td><input type="text" name=".unit" value="${rs.unit }"></td>
				<td>资产标签号（仅用于ERSA（备件））/ERSA</td>
				<td><input type="text" name=".ersa" value="${rs.ersa }"></td>
			</tr>
			<tr>
				<td>危险物料/Is Danger<input type="checkbox" name=".isDanger" id="isDanger" ></td>
				<td></td>
				<td>If yes, FID #:</td>
				<td><input type="text" name=".fid" value="${rs.fid }"></td>
			</tr>
			<tr>
				<td>最低库存数量<input type="text" name=".amin" value="${rs.amin }"></td>
				<td>最高库存数量<input type="text" name=".amax" value="${rs.amax }"></td>
				<td>年度用量<input type="text" name=".annual" value="${rs.annual }"></td>
			</tr>
			<tr>
				<td>长度/Long<input type="text" name=".longa" value="${rs.longa }"></td>
				<td>宽度/Width<input type="text" name=".width" value="${rs.width }"></td>
				<td>高度/Height<input type="text" name=".height" value="${rs.height }"></td>
			</tr>
			<tr>
				<td>自制/<input type="checkbox" name=".isSelfMade" id="selfMade"></td>
				<td><br></td>
				<td>单价/Unit Price</td>
				<td><input type="text" name=".unitPrice" value="${rs.unitPrice }"></td>
			</tr>
			
			<tr>
			<td >是否为包装</td>
			<td><input type="checkbox" name=".isPackage" value=1"> </td>
			<td >采购员
				<select name=".buyerName" id="buyerName">
							<option value="张亚波483">张亚波483</option>
							
							<option value="郑驰晖487">
								郑驰晖487
							</option>
							<option value="徐桂根485">
								徐桂根485
							</option>
							<option value="任士龙481">
								任士龙481
							</option>
							<option value="刘  鸣">
								刘  鸣
							</option>
								
							<option value="王其华">
								王其华
							</option>											
				</select> 
			</td>
			</tr>
			
	
		</table>
		<table>
			<tr>
				<td>
					<img id="img1" height="300" width="480" src="P:\Public\Indirect Material\IMM/${rs.tempolyeeByApplicantId.name }\<fmt:formatDate value="${rs.applicateDate }" pattern="yyyy-MM-dd-HH-mm"/>/${rs.file1 }">
			
				</td>
				<td>
				附件地址：P:\Public\Indirect Material\IMM/${rs.tempolyeeByApplicantId.name }\<fmt:formatDate value="${rs.applicateDate }" pattern="yyyy-MM-dd-HH-mm"/>/${rs.file1 }
				<a id="img1" href="\\filesrv\department\Public\Indirect Material\IMM/${rs.tempolyeeByApplicantId.name }\<fmt:formatDate value="${rs.applicateDate }" pattern="yyyy-MM-dd-HH-mm"/>/${rs.file1 }">打开</a>
				</td>
			</tr>
			
			<tr>
				<td colspan="4" align="middle" class="header" >其他信息</td>
			</tr>
			
			<tr>
				<td>1. 是否做库存管理<input type="checkbox" name=".isStorageManage"></td>
				<td><br></td>
				<td>2. 收货就费用化</td>
				<td><input type="text" name=".isExpense"></td>
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
						<td>
							物料组:N100
						</td>
						<td>
							库存检查： <input type="radio" name=".checkStorage" value="">YES<input type="radio" name="" value="">NO  
						</td>
					</tr>
					<tr>
						<td>
							MRP类型：<input type="checkbox" name=".MRPType" value="V1(手工订单)">V1(手工订单)
								   <input type="checkbox" name=".MRPType" value="V2(自动订单)">V2(自动订单)
						</td>
						<td>
							再订购点 ：<input type="input" name=".reorderPlace" >
						</td>
						<td>
							ND(无计划) ：<input type="checkbox" name=".ND" >
						</td>
					</tr>
					<tr>
						<td colspan=3 >
							批量：<input type="checkbox" name=".bluk" value="V1(手工订单)">EX(补充到再订购点)
								   <input type="checkbox" name=".bluk" value="V2(自动订单)">HB(最高库存水平)
								   <input type="checkbox" name=".bluk" value="V2(自动订单)">FX(修正批量)
						</td>
					</tr>
					<tr>
						<td colspan=3 >
							修正批量(如果选择FX)：<input type="input" name=".ifFX" >
							最高库存水平(如果选择HB):<input type="input" name=".ifHB" >					   
						</td>
					</tr>
					<tr>
						<td>
							标准包装(可选项)：<input type="input" name=".standardPackage" >
						</td>
						<td>
							库存地点：<input type="input" name=".storagePlace" >
						</td>
						<td>
							MRP控制者：韩建荣
						</td>
					</tr>
					<tr>
						<td colspan=3>最高库存期限(天数):<input type="input" name=".maxPeriod" ></td>
					</tr>
					<tr>
						<td>管理名字:<input type="input" name=".manger" ></td>
						<td>电话号码:<input type="input" name=".managerPhone" ></td>
						<td>日期:<input type="input" name=".managerDate" ></td>
					</tr>	
		
		</table>	
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
		<table>
			<jsp:include page="approve/${url}"></jsp:include>	
			
				<div id="aaaaaaaaa"></div>	
		</table>
	</body>
</html>
