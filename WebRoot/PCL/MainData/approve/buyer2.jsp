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
  function doSubmit(){
				if(document.getElementById('a').value ==''){
					alert('供应商代码');
					return false; 
				}
				else if(document.getElementById('b').value ==''){
					alert('供应商名称');
					return false; 
				}
				else if(document.getElementById('c').value ==''){
					alert('联系人');
					return false; 
				}
				else if(document.getElementById('d').value ==''){
					alert('联系方式');
					return false; 
				}
				else if(document.getElementById('e').value.length>20){
					alert('Sap合同号');
					return false; 
				}
				else if(document.getElementById('f').value.length>40){
					alert('采购周期');
					return false; 
				}else if(document.getElementById('g').value.length>20){
					alert('标准包装');
					return false; 
				}
				else if(document.getElementById('h').value.length>40){
					alert('最小起订量');
					return false; 
				}else {
					document.getElementById('form1').submit();
				}
				
			}
		</script>	
  <body>
 		<table>
	 		<form id="form1" action="mainData.do?actionType=doApprove" method = "post">				
					<input type="hidden" name="form.uuid" value="${rs.uuid}">
					<input type="hidden" name="form.state" value="${rs.state}"> 
					<tr>
						<td>
							供应商代码<input type="input" name="form.sapSupplyCode" id="a">
						</td>
						<td>
							供应商名称 <input type="input" name="form.supplysn" id="b"> 
						</td>
						<td>
							联系人<input type="input" name="form.supplier" id="c">
						</td>
						<td>
							联系方式 <input type="input" name="form.supplyContact" id="d"> 
						</td>
					</tr>
					<tr>
						<td>
							Sap合同号<input type="input" name="form.sapContract" id="e">
						</td>
						<td>
							采购周期 <input type="input" name="form.leadTime" id="f" > 
						</td>
						<td>
							标准包装<input type="input" name="form.standardPackage" id="g" >
						</td>
						<td>
							最小起订量 <input type="input" name="form.minOrder" id="h"> 
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
						<td colspan = "2">
							<textarea rols="6" cols="160" name="apForm.context"></textarea> </td>
						<td> <input type="button" value="提交/submit" onClick="doSubmit()"></td>
					</tr>	
			</form>
		</table>	
  </body>
</html>
