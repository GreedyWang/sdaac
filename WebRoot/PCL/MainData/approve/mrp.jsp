<%@ page language="java"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
  </head>
  
  <body>
 		<table>
	 		<form action="mainData.do?actionType=doApprove" method = "post">
					<input type="hidden" name="form.uuid" value="${rs.uuid}">
						<input type="hidden" name="form.state" value="${rs.state}">  
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
					<tr>					
						<td> 
					<select name="apForm.isApproved">
						<option value="1">批准</option>
						<option value="3">需解释</option>
						<option value="2">不批准</option>
						<option value="4">回到上步</option>
					</select> 
					</td>
					<td><textarea name="apForm.context" rols="6" cols="120"></textarea> </td>
					<td> <input type="submit" value="提交/submit"></td>
					</tr>	
			</form>
		</table>	
  </body>
</html>
