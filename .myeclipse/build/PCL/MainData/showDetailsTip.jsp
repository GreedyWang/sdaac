<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
showDetailsTip.jsp
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
	<body>
	
	
		<table BORDER="1" width="800">
		<tr>
			<td>
				<a target="blank" href="mainData.do?actionType=doShowDetails&id=${id}">详细信息/Details</a>
			</td>
		</tr>
			<tr class="header">
				<td colspan="4" align="middle" >
					<label >间接物料主数据申请单</label>
				</td>
			</tr>
			<tr>
				<td colspan="2">物料号:${rs.materialSn }</td>
				<td colspan="2">编号</td>
			</tr>
			<tr>
				<td colspan="4" align="middle" class="header" >申请人信息</td>
			</tr>
			<tr>
				<td>电话</td>
				<td><input type="text" name="form.phone" value="${rs.phone}"> </td>
				<td>成本中心</td>
				<td><input type="text" name="form.costCenter" value="${rs.costCenter }"></td>
			</tr>
			<tr>
				<td colspan="4" align="middle" class="header" >申请内容</td>
			</tr>
			<tr>
				<td>24小时内响应<input type="radio" name="form.feedbackType" group="aa" value="1"></td>
				<td>48小时内响应<input type="radio" name="form.feedbackType" group="aa" value="2"></td>
				<td colspan="2" >响应时间超出48小时<input type="radio" name="form.feedbackType" group="aa" value="3"></td>
			</tr>
			<tr>
				<td colspan="4" align="middle" >注意：普通物料每次设置时间为从管理中心发出到物料主数据小组收到要1到2个工作日</td>
			</tr>
			<tr>
				<td>工厂</td>
				<td></td>
				<td>库存地点</td>
				<td></td>
			</tr>
			<tr>
				<td>新建<input type="radio" name="form.isCreate" group="bb" value="1"></td>
				<td>修改<input type="radio" name="form.isCreate" group="bb" value="2"></td>
				<td>旧物料号</td>
				<td><input type="text" name="form.oldMaterialSn" value="${rs.oldMaterialSn }"></td>
			</tr>
			<tr>
				<td>物料描述</td>
				<td colspan="2"><textarea rows="3" cols="80" name="form.description" value="${rs.description }"></textarea>
			</tr>
			<tr>
				<td>制造商物料号</td>
				<td><input type="text" name="form.manufacturerSn" value="${rs.manufacturerSn }"></td>
				<td>制造商名称</td>
				<td><input type="text" name="form.manufacturerName" value="${rs.manufacturerName }"></td>
			</tr>
			<tr>
				<td>基本单位</td>
				<td><input type="text" name="form.unit" value="${rs.unit }"></td>
				<td>资产标签号（仅用于ERSA（备件））</td>
				<td><input type="text" name="form.ersa" value="${rs.ersa }"></td>
			</tr>
			<tr>
				<td>危险物料<input type="checkbox" name="form.isDanger"></td>
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
				<td>长度<input type="text" name="form.longa" value="${rs.longa }"></td>
				<td>宽度<input type="text" name="form.width" value="${rs.width }"></td>
				<td>高度<input type="text" name="form.height" value="${rs.height }"></td>
			</tr>
			<tr>
				<td>自制<input type="checkbox" name="form.isSelfMade"></td>
				<td></td>
				<td>单价</td>
				<td><input type="text" name="form.unitPrice" value="${rs.unitPrice }"></td>
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
				<td>3. 设备维修项 (ERSA) <input type="radio" name="form.type2" group="cc"></td>
				<td>运营支持(HIBE)<input type="radio" name="form.type2" group="cc"></td>
			</tr>
			<tr>
				<td colspan="4" align="middle" class="header" >其他信息</td>
			</tr>
			
			<tr>
			<td >是否为包装</td>
			<td><input type="checkbox" name="form.isPackage" value=1"> </td>
			<td >采购员:${rs.buyerName }
				
			</td>
			</tr>	
			

			<tr>

			</tr>
			<tr>
				<td>备注,购买理由说明</td>
				<td colspan="2"><textarea rows="3" cols="80" name="form.description" value="${rs.description }"></textarea>
			</tr>
		
		</table>
		
	<table>
					<tr>
						<td>
							物料组:N100
						</td>
						<td>
							库存检查： <input type="radio" name="form.checkStorage" value="">YES<input type="radio" name="" value="">NO  
						</td>
					</tr>
					<tr>
						<td>
							MRP类型：<input type="checkbox" name="form.MRPType" value="V1(手工订单)">V1(手工订单)
								   <input type="checkbox" name="form.MRPType" value="V2(自动订单)">V2(自动订单)
						</td>
						<td>
							再订购点 ：<input type="input" name="form.reorderPlace" >
						</td>
						<td>
							ND(无计划) ：<input type="checkbox" name="form.ND" >
						</td>
					</tr>
					<tr>
						<td colspan=3 >
							批量：<input type="checkbox" name="form.bluk" value="V1(手工订单)">EX(补充到再订购点)
								   <input type="checkbox" name="form.bluk" value="V2(自动订单)">HB(最高库存水平)
								   <input type="checkbox" name="form.bluk" value="V2(自动订单)">FX(修正批量)
						</td>
					</tr>
					<tr>
						<td colspan=3 >
							修正批量(如果选择FX)：<input type="input" name="form.ifFX" >
							最高库存水平(如果选择HB):<input type="input" name="form.ifHB" >					   
						</td>
					</tr>
					<tr>
						<td>
							标准包装(可选项)：<input type="input" name="form.standardPackage" >
						</td>
						<td>
							库存地点：<input type="input" name="form.storagePlace" >
						</td>
						<td>
							MRP控制者：韩建荣
						</td>
					</tr>
					<tr>
						<td colspan=3>最高库存期限(天数):<input type="input" name="form.maxPeriod" ></td>
					</tr>
					<tr>
						<td>管理名字:<input type="input" name="form.manger" ></td>
						<td>电话号码:<input type="input" name="form.managerPhone" ></td>
						<td>日期:<input type="input" name="form.managerDate" ></td>
					</tr>	
		
		</table>	
	

	</body>
	
</html>
