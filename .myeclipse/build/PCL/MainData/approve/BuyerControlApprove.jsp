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
		<link rel="stylesheet" type="text/css"
			href="ext/resources/css/ext-all.css">

		<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="ext/ext-all.js"></script>
		
		<script type='text/javascript' src='dwr/interface/prFormListManager.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type="text/javascript" src="epor/baseInfo/js/util/loadProjectData.js"></script>
  </head>
  	<script type="text/javascript" >
		loadBuyerData2('DT02');				
		var buyerList = new Ext.form.ComboBox({
						renderTo:document.getElementById('aaaaaaaaa'),  
						fieldLabel : '采购员',
						name : 'buyerName',
						store : buyerList,
						valueField : 'buyId',
						displayField : 'buyId',
						typeAhead : true,
						mode : 'local',
						triggerAction : 'all',
						emptyText : '选择一名采购员',
						selectOnFocus : true,
						width : 150,
						blankText : 'empty',
						
						allowBlank : false

		})
  </script>
  <body>
 		<form action="mainData.do?actionType=doApprove" method = "post">
 			<div id="aaaaaaaaa"></div>
			<input type="hidden" name="apForm.item.uuid" value="form.uuid">
			<input type="hidden" name="apForm.item.state" value="form.state">    
					<tr>
					<td><label>选择采购员</label>
					
					 
					</td>
					<td> 
					<select name="apForm.item.buyerName">
						<option value="1">e1</option>
						<option value="3">e2</option>
						<option value="2">e3</option>
					</select> 
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
					<td><textarea name="apForm.context">
					</textarea> </td>
					<td> <input type="submit" value="提交/submit"></td>
				</tr>	
		</form>	
  </body>
</html>
