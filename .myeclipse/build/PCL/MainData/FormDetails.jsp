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
	FormDetails2.jsp
	<script type="text/javascript">
			
	function init() {
		//设置图片附件
		if('${rs.file1 }' == null || '${rs.file1 }' == '') {		
			document.getElementById('img1').height = 0			
		}
		
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
		alert(type2)
		if(type2 == 1) {
			document.getElementById('type2').checked = true;
		}else if(type2 == 2) {
			document.getElementById('type3').checked = true;
		}
		
		var isExpense = '${rs.isExpense }' 
		if(isStorageManage == 1) {
			document.getElementById('isExpense').checked = true;
		}
		//设置采购员名称
		var buyerNames = document.getElementById('buyerName').options
			for (var i = 0; i < buyerNames.length; i++) {
						if ('${buyerName}' == buyerNames[i].value) {
							buyerNames[i].selected = 'selected'
						}							
					}
		
	}
		//---------------------------------	
	function save() {
		if(window.confirm("确认保存吗?")) {
			document.getElementById('isSave').value=1
			//window.forms[0].submit();
			document.getElementById('form1').submit();
		}
	}
	
	function del(id) {
		if(window.confirm("确认删除吗?")) {
			window.location.href = 'mainData.do?actionType=doDelete&id='+id
		}
	}
	</script>
	<body onload="init()">
	<input type="button" value="删除" onclick="del('${rs.uuid }')">
		<form action="mainData.do?actionType=doApply" enctype="multipart/form-data" method="post" id="form1">
			<input type="hidden" name="form.uuid" value="${rs.uuid }">
			<table BORDER="1" width="800">
				<tr class="header">
					<td colspan="4" align="middle">
						<label>
							间接物料主数据申请单
						</label>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						物料号:${rs.materialSn }
					</td>
					<td colspan="2">
						编号
					</td>
				</tr>
				<tr>
					<td colspan="4" align="middle" class="header">
						申请人信息
					</td>
				</tr>

				<tr>
					<td>
						电话
					</td>
					<td>
						<input type="text" name="form.phone" value="${rs.phone}">
					</td>
					<td>
						成本中心
					</td>
					<td>
						<input type="text" name="form.costCenter"
							value="${rs.costCenter }">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="middle" class="header">
						申请内容
					</td>
				</tr>
				<tr>
					<td>
						24小时内响应
						<input id="24" type="radio" name="form.feedbackType" group="aa"
							value="1">
					</td>
					<td>
						48小时内响应
						<input id="48" type="radio" name="form.feedbackType" group="aa"
							value="2">
					</td>
					<td colspan="2">
						响应时间超出48小时
						<input id="49" type="radio" name="form.feedbackType" group="aa"
							value="3">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="middle">
						注意：普通物料每次设置时间为从管理中心发出到物料主数据小组收到要1到2个工作日
					</td>
				</tr>
				<tr>
					<td>
						工厂
					</td>
					<td></td>
					<td>
						库存地点
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						新建
						<input type="radio" id="new" name="form.isCreate" group="bb"
							value="1">
					</td>
					<td>
						修改
						<input type="radio" id="update" name="form.isCreate" group="bb"
							value="2">
					</td>
					<td>
						旧物料号
					</td>
					<td>
						<input type="text" name="form.oldMaterialSn"
							value="${rs.oldMaterialSn }">
					</td>
				</tr>
				<tr>
					<td>
						物料描述(中文,小于20个字长)
					</td>
					<td colspan="3">
						<textarea rows="3" cols="80" name="form.description">${rs.description }</textarea>
				</tr>
				<tr>
					<td>
						物料描述(英文,40个字母长)
					</td>
					<td colspan=3>
						<textArea rows=3 cols=80 name="form.description2">${rs.description2 }</textArea>

					</td>
				</tr>
				
				<tr>
					<td>
						备注,购买理由说明
					</td>
					<td colspan="3">
						<textarea rows="3" cols="80" name="form.remark">${rs.remark }</textarea>
				</tr>
				
				<tr>
					<td>
						制造商物料号
					</td>
					<td>
						<input type="text" name="form.manufacturerSn"
							value="${rs.manufacturerSn }">
					</td>
					<td>
						制造商名称
					</td>
					<td>
						<input type="text" name="form.manufacturerName"
							value="${rs.manufacturerName }">
					</td>
				</tr>
				<tr>
					<td>
						基本单位
					</td>
					<td>
						<input type="text" name="form.unit" value="${rs.unit }">
					</td>
					<td>
						资产标签号（仅用于ERSA（备件））
					</td>
					<td>
						<input type="text" name="form.ersa" value="${rs.ersa }">
					</td>
				</tr>
				<tr>
					<td>
						危险物料
						<input type="checkbox" name="form.isDanger">
					</td>
					<td></td>
					<td>
						If yes, FID #:
					</td>
					<td>
						<input type="text" name="form.fid" value="${rs.fid }">
					</td>
				</tr>
				<tr>
					<td>
						最低库存数量
						<input type="text" name="form.amin" value="${rs.amin }">
					</td>
					<td>
						最高库存数量
						<input type="text" name="form.amax" value="${rs.amax }">
					</td>
					<td>
						年度用量						
					</td>
					<td>						
						<input type="text" name="form.annual" value="${rs.annual }">
					</td>					
				</tr>
				<tr>
					<td>
						长度
						<input type="text" name="form.longa" value="${rs.longa }">
					</td>
					<td>
						宽度
						<input type="text" name="form.width" value="${rs.width }">
					</td>
					<td>
						高度
					</td>
					<td>					
						<input type="text" name="form.height" value="${rs.height }">
					</td>
				</tr>
				<tr>
					<td>
						自制
						<input id="selfMade" type="checkbox" name="form.isSelfMade">
					</td>
					<td></td>
					<td>
						单价
					</td>
					<td>
						<input type="text" name="form.unitPrice" value="${rs.unitPrice }">
					</td>
				</tr>
				<tr>
					<td>
						1. 是否做库存管理
						<input type="checkbox" id="isStorageManage">
					</td>
					<td>
						<br>
					</td>
					<td>
						2. 收货就费用化
					</td>
					<td>
						<input type="checkbox" id="isExpense">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="middle">
						If Yes, Yes = UNBW If No, Yes = NLAG If Yes, No Complete Item 3
					</td>
				</tr>
				<tr>
					<td>
						3. 设备维修项 (ERSA)
						<input type="radio" id="type2" name="ersa" group="cc" value = "1">
					</td>
					<td>
						运营支持(HIBE)
						<input type="radio" id="type3" name="ersa" group="cc" value = "2">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="middle" class="header">
						其他信息
					</td>
				</tr>

				<tr>
				<td rowspan="3">
					<img id="img1" height="300" width="480" src="\\filesrv\Department\Public\IT\Training/${logineduser.emp.name }/<fmt:formatDate value="${rs.applicateDate }" pattern="yyyy-MM-dd-HH-mm"/>/${rs.file1 }">
			
				</td>
				
					<td >
						是否为包装
					</td>
					<td>
						<input type="checkbox" id="isPackage" name="form.isPackage" value=1">
					</td>
				</tr>
				<tr>
					<td>采购员</td>
					<td>
						<select name="form.buyerName" id="buyerName">
							<option value="张亚波483">张亚波483</option>
							<option value="朱国平483">
								朱国平483
							</option>
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
							<option value="史伟明">
								史伟明
							</option>
							<option value="王其华">
								王其华
							</option>
						</select>
					</td>
				</tr>


				<tr>
					<td>
						附件上传
						<input type="file" name="file3">
					</td>
					<td>
						图片上传
						<input type="file" name="formFile">
					</td>

				</tr>
				

				
				<input type="hidden" name="isSave" id="isSave">
				<tr>
					<td>
						<input id="btn1" type="submit" value="提交">
					</td>
					<td colspan="2">
						<input id="btn2" type="button" value="保存" onClick="save()">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="middle" class="header">
						审批意见
					</td>
				</tr>
				<c:forEach items="${rs.approveForm}" var="item">			
				<c:if test="${item.tempolyee.name != null}" >			
					<tr>
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
		</form>
	</body>
</html>
