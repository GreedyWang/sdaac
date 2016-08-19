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
   <script type="text/javascript">
   			function ff(){
   				var supplyCode = document.getElementById('supplyCode')
   			}
   </script>
  <body>
 		<table width = "800">
 			 
	 		<form action="mainData.do?actionType=doApprove" method = "post">
					<input type="hidden" name="form.uuid" value="${rs.uuid}">
					<input type="hidden" name="form.state" value="${rs.state}">
					<input type="hidden" name="apForm.buyerid" value="${rs.buyerName}">   
					<tr>
						<td>
							<input type="radio" name="form.type3" value="单一采购"> 单一采购
						</td>
						<td>
							<input type="radio" name="form.type3" value="拉动">  拉动
						</td>
					</tr>
					
					<tr>
						<td>
							供应商代码<input id="supplyCode" type="input" name="form.sapSupplyCode" >
							<input type="button" value="查询/search" onclick="ff()">
						</td>
						<td>
							供应商名称 <input type="input" name="form.supplysn" > 
						</td>
						<td>
							联系人<input type="input" name="form.supplier" >
						</td>
						<td>
							联系方式 <input type="input" name="form.supplyContact" > 
						</td>
					</tr>
					<tr>
						<td>
							Sap合同号<input type="input" name="form.sapContract">
						</td>
						<td>
							采购周期 <input type="input" name="form.leadTime" > 
						</td>
						<td>
							标准包装<input type="input" name="form.standardPackage" >
						</td>
						<td>
							最小起订量 <input type="input" name="form.minOrder"> 
						</td>
					</tr>
					
					<tr>	
					<td> 
						<select name="apForm.isApproved">
							<option value="1">批准</option>
							<option value="3">需解释</option>
							<option value="2">不批准</option>
						</select> 
					</td>					
						
						<td> <input type="submit" value="提交/submit"></td>
					</tr>	
					<tr>
						<td colspan=4><textarea  rows=3 cols=180 name="apForm.context"></textarea> </td>
					</tr>
			</form>
		</table>	
  </body>
</html>
