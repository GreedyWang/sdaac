<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

		<title>紧急采购申请单</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

		<link rel="stylesheet" type="text/css"
			href="css/themes/gray/easyui.css">
		<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="css/demo.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

	</head>
	<script language="javascript" type="text/javascript">
function IsNum(e) {
var value = document.getElementById('prno').value;

			if(value.length >= 10) {
				alert('长度不能超过10位');
				return false;
			}
            var k = window.event ? e.keyCode : e.which;
        
            if (((k >= 48) && (k <= 57)) || k == 8 || k == 0) {
            } else {
            	alert("请输入数字!"); return false;
               
            }
        } 
        </script>
	<body>
		<div class="easyui-panel"
			title="紧急采购申请单/Urgent Purchase Requisition Form" style="width: 950px">
			<div style="padding: 10px 0 10px 60px">
				<form id="form1" target="_blank" action="epor.do?actionType=doSingle" method="post">
					<table>
						<tr>
							<td rowspan=2>
								<label style="size: 8">
									<h2>
										SDAAC
									</h2>
								</label>
							</td>
							<td align="right">
								<h3>
									SDAAC/F9.5-2I
								</h3>
							</td>
						</tr>
						<tr>

							<td align="right">

							</td>
						</tr>

						<tr>
							<td width="250">
								部门/Department:

							</td>
							<td>
								<input class="easyui-validatebox" type="text" name="">
							</td>
						</tr>
						<tr>
							<td>
								申请人/:
							</td>
							<td>
								<input class="easyui-validatebox" type="text" name="">
							</td>
						</tr>
						<tr>
							<td>
								采购物品名称/Purchase Article Name:

							</td>
							<td>

								<input type="text" name="singleForm.purchaseGoodsName">
							</td>
						</tr>

						<tr>
							<td colspan="2">
								<h3>
									基本信息/Basic Information
								</h3>
							</td>
						</tr>
						<tr>
							<td>
								AR号/成本中心/AR No./Cost Center:
							</td>
							<td>
								<input type="text" name="singleForm.arno" id="arno">
							</td>
						</tr>
						<tr>
							<td>
								PR号/PR No.:
							</td>
							<td>
								<input type="text" name="singleForm.prno" id="prno" onkeypress="return IsNum(event)" >
							</td>
						</tr>
						<tr>
							<td>
								供应商/Supplier:
							</td>
							<td>
								<input type="text" name="singleForm.supplier" id="supplier">
							</td>
						</tr>
						<tr>
							<td>
								总价/Total Amount:
							</td>
							<td>
								<input type="text" name="singleForm.totalAmount">
							</td>
						</tr>
						<tr>
							<td>
								联系人/Contact:
							</td>
							<td>
								<input type="text" name="singleForm.contact">
							</td>
						</tr>
						<tr>
							<td>
								采购员/Buyer:
							</td>
							<td>
								<input type="text" name="singleForm.buyer">
							</td>
						</tr>
						<tr>
							<td colspan="2" align=middle>
								<h3>
									紧急采购原因&项目背景介绍/Urgent Purchase Reason&Project Introduction
									<h3>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<textarea rows="4" cols="100" name="singleForm.reason"></textarea>
							</td>

						</tr>
						<tr>
							<td colspan="2" align=middle>
								<h3>
									特殊条款及注意事项/Special Term & Condition
									<h3>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<textarea rows="4" cols="100" name="singleForm.reason2"></textarea>
							</td>

						</tr>

					</table>



					<table>
						<tr>
							<td width=200>
								申请部门经理/Dept
							</td>
							<td width=200>
								<input type="text">
							</td>
							<td align="left" width=150>
								日期/Date
							</td>

							<td align="right">
								<input type="text">
							</td>
						</tr>
						<tr>
							<td>
								成本中心/Cost Center
							</td>
							<td>
								<input type="text">
							</td>
							<td>
								日期/Date
							</td>
							<td>
								<input type="text">
							</td>
						</tr>
						<tr>
							<td>
								财务经理/Fin
							</td>
							<td>
								<input type="text">
							</td>
							<td>
								日期/Date
							</td>
							<td>
								<input type="text">
							</td>
						</tr>
						<tr>
							<td>
								工程总监、工厂经理/Eng Dir、Plant Mgr
							</td>
							<td>
								<input type="text">
							</td>
							<td>
								日期/Date
							</td>
							<td>
								<input type="text">
							</td>
						</tr>
						<tr>
							<td>
								总经理/GM(大于3000CNY需要总经理审批)
							</td>
							<td>
								<input type="text">
							</td>
							<td>
								日期/Date
							</td>
							<td>
								<input type="text">
							</td>
						</tr>
					</table>
					<table>
						<tr>
							<td colspan="2">
								*Department manager is required to be the urgent request
								initiator.
							</td>
						</tr>
						<tr>
							<td colspan="2">
								*Cost center department manager is required to sign in case the
								cost center is not the requesting department.
							</td>
						</tr>
					</table>
				</form>
				<div style="text-align: center; padding: 5px">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						onclick="submitForm()">提交/Submit</a>
				</div>
			</div>
		</div>
	</body>

	<script type="text/javascript">
			 
			function submitForm(){
			var sname = document.getElementById('arno').value
				if(sname == ''){
					alert('AR No. 不能为空');
					return false;
				}
				var prno = document.getElementById('prno').value
				if(prno == ''){
					alert('PR No. 不能为空');
					return false;
				}
				var supplier = document.getElementById('supplier').value
				if(supplier == ''){
					alert('供应商不能为空');
					return false;
				}
				
				var value = document.getElementById('prno').value;

				if(value.length < 10) {
					alert('请填写10位PR号码');
					return false;
				}
				
				if(window.confirm("确认提交吗?")) {		
				// -	$('#form1').form('submit');	
				//-document.getElementById('form1').width = '75%'
				//-document.getElementById('form1').height = '75%'
					document.getElementById('form1').submit();						
				}
				
			}
								
		</script>
</html>
