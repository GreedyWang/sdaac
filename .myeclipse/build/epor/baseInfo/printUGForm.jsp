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
	<style type="text/css">
body {
	font-size: 24px;
	line-height: 24px;
}

.exp1 {
	font-size: 12px;
}

.exp2 {
	font-size: xx-small;
}

.exp3 {
	font-size: small;
}

table.tr.td {
	font-size: 24px;
}

.exp5 {
	font-size: larger;
}

.exp6 {
	font-size: smaller;
}

.exp7 {
	font-size: 50%;
}

div{font-size:20px} 
</style>
	<body>
		<div class="easyui-panel"
			title="紧急采购申请单/Urgent Purchase Requisition Form" style="width: 950px">
			<div style="padding: 10px 0 10px 60px">
				<form id="form1" action="epor.do?actionType=doSingle" method="post">
					<table cellspacing="5" cellpadding="5" >
						<tr>
							<td rowspan=2>
								<label style="size: 8">
									<h2>
										No.:${singleForm.sno }
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

						<tr style="font-size:16px">
							<td width="250" style="font-size:16px">
								部门/Department:

							</td>
							<td>
								<input class="easyui-validatebox" type="text" style="font-size:16px"
									value="${singleForm.applicantId.tdepartment.name }">
							</td>
						</tr>
						<tr>
							<td style="font-size:16px">
								申请人/:
							</td>
							<td>
								<input class="easyui-validatebox" type="text" style="font-size:16px"
									value="${singleForm.applicantId.name }">
							</td>
						</tr>
						<tr>
							<td style="font-size:16px">
								采购物品名称/Purchase Article Name:

							</td>
							<td>

								<input type="text" name="singleForm.purchaseGoodsName" style="font-size:16px">
							</td>
							<td>

							</td>
						</tr>

						<tr>
							<td colspan="2">
								<h3>
									基本信息/Basic Information:
								</h3>
							</td>
							<td colspan="2">

							</td>
						</tr>
						<tr>
							<td style="font-size:16px">
								AR号/成本中心/AR No./Cost Center:
							</td>
							<td>
								<input type="text" name="singleForm.arno"
									value="${singleForm.arno }" style="font-size:16px">
							</td>
						</tr>
						<tr>
							<td style="font-size:16px">
								PR号/PR No.:
							</td>
							<td>
								<input type="text" name="singleForm.prno"
									value="${singleForm.prno }" style="font-size:16px">
							</td>
						</tr>
						<tr>
							<td style="font-size:16px">
								供应商/Supplier:
							</td>
							<td>
								<input type="text" name="singleForm.supplier"
									value="${singleForm.supplier }" style="font-size:16px">
							</td>
						</tr>
						<tr>
							<td style="font-size:16px">
								总价/Total Amount:
							</td>
							<td>
								<input type="text" name="singleForm.totalAmount"
									value="${singleForm.totalAmount }" style="font-size:16px">
							</td>
						</tr>
						<tr>
							<td style="font-size:16px">
								联系人/Contact:
							</td>
							<td>
								<input type="text" name="singleForm.contact"
									value="${singleForm.contact }" style="font-size:16px">
							</td>
						</tr>
						<tr>
							<td style="font-size:16px">
								采购员/Buyer:
							</td>
							<td>
								<input type="text" name="singleForm.buyer"
									value="${singleForm.buyer }" style="font-size:16px">
							</td>
							<td>
								<br>
								<br>

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
								<textarea style="font-size:16px" rows="5" cols="80" name="singleForm.reason">${singleForm.reason }</textarea>
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
								<textarea style="font-size:16px" rows="5" cols="80" name="singleForm.reason2">${singleForm.reason2 }</textarea>
							</td>

						</tr>

					</table>



					<table cellspacing="10" cellpadding="10">
						<tr>
							<td width=200 style="font-size:16px">
								申请部门经理/Dept
							</td>
							<td width=200>

							</td>
							<td align="left" width=150 style="font-size:16px">
								日期/Date
							</td>

							<td align="right">

							</td>
						</tr>
						<tr>
							<td style="font-size:16px">
								成本中心/Cost Center
							</td>
							<td>

							</td>
							<td style="font-size:16px">
								日期/Date
							</td>
							<td>

							</td>
						</tr>
						<tr>
							<td style="font-size:16px">
								财务经理/Fin
							</td>
							<td>

							</td>
							<td style="font-size:16px">
								日期/Date
							</td>
							<td>

							</td>
						</tr>
						<tr>
							<td style="font-size:16px">
								工程总监、工厂经理/Eng Dir、Plant Mgr
							</td>
							<td>

							</td>
							<td style="font-size:16px">
								日期/Date
							</td>
							<td>

							</td>
						</tr>
						<tr>
							<td style="font-size:16px">
								总经理/GM
							</td>
							<td>

							</td>
							<td style="font-size:16px">
								日期/Date
							</td>
							<td>

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
			</div>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="submitForm()">打印/Print</a>
			</div>
		</div>
	</body>

	<script type="text/javascript">
			 
			function submitForm(){
				window.print();
			}
								
		</script>
</html>
