<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
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
		<link rel="stylesheet" type="text/css"
			href="css/themes/gray/easyui.css">
		<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="css/demo.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

	</head>





	<script type="text/javascript">
			function check1() {
				var old = document.getElementById('old');
				old.disabled = false;
			}
			function check2() {
				var old = document.getElementById('old');
				old.disabled = ture;
			}
			
			function save() {

			}
			   
			function doSubmit(){
				//alert(document.getElementById('typeName').value);
				//if(document.getElementById('typeName').value ==''){
				//	alert('选择物料类型');
			//		return false; 
			//	}
				//else 
				if(document.getElementById('type2').value == 0 
				     && document.getElementById('type3').value == 0){
					alert('请选择设备维修项 (ERSA) 或 运营支持(HIBE)');
					return false; 
				}
				else if(document.getElementById('a').value ==''){
					alert('最低库存数量不能为空');
					return false; 
				}
				else if(document.getElementById('b').value ==''){
					alert('最高库存数量不能为空');
					return false; 
				}
				else if(document.getElementById('c').value ==''){
					alert('年度用量不能为空');
					return false; 
				}
				else if(document.getElementById('unit').value ==''){
					alert('基本单位不能为空');
					return false; 
				}
				else if(document.getElementById('chnDes').value.length>20){
					alert('中文描述不能超过20个字');
					return false; 
				}
				else if(document.getElementById('engDes').value.length>40){
					alert('英文描述不能超过40个字');
					return false; 
				}
				
				else {
					document.getElementById('form1').submit();
					window.alert("success");
				}
				
			}
			
			function fun1() {
				var t1 = document.getElementById('t1');
				var t2 = document.getElementById('t2');
				var typeName = document.getElementById('typeName');
				if(t1.checked == true ) {
					if(t2.checked == true ) {
						typeName.innerText = 'UNBW'
					}else {
						typeName.innerText = ''
					}
				}else {
					if(t2.checked == true ) {
						typeName.innerText = 'NLAG'
					}else {
						typeName.innerText = ''
					}
				}
			}
			
			function fun2(type) {
				var typeName = document.getElementById('typeName');
				typeName.innerText = type
			}
		</script>
	<body>
		<div class="easyui-panel" title="间接物料主数据申请单" style="width: 880px">
			<div style="padding: 10px 0 10px 60px">

				<form id="form1" action="mainData.do?actionType=doApply"
					enctype="multipart/form-data" method="post">
					<input type="hidden" name="isSave" id="isSave">
					<table>									
						<tr>
							<td>
								采购员
							</td>
							<td>
								<select class="easyui-combobox" name="form.buyerName" >
									<option value="张亚波483">张亚波483</option>
									<option value="郑驰晖487">郑驰晖487</option>
									<option value="徐桂根485">徐桂根485</option>
									<option value="任士龙481">任士龙481</option>
									<option value="刘  鸣">刘 鸣</option>								
									<option value="王其华">王其华</option>
									<option value="吴志平">吴志平</option>
								</select>
							</td>
							<td>
								工厂
							</td>
							<td>
								<select name="form.factory" class="easyui-combobox">
									<option value="上海">上海</option>
									<option value="沈阳">沈阳</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								电 话
							</td>
							<td>
								<input class="easyui-validatebox" type="text" name="form.phone" data-options="required:true">
							</td>
							<td>
								成本中心
							</td>
							<td>
								<input class="easyui-validatebox" type="text"
									name="form.costCenter">
							</td>
						</tr>

						<tr>
							<td>
								<input type="radio" name="form.feedbackType" group="aa"
									value="1">
							</td>
							<td>
								24小时内响应
							</td>
							<td>
								<input type="radio" name="form.feedbackType" group="aa"
									value="2">
							</td>
							<td>
								48小时内响应
							</td>
							<td>
								<input type="radio" name="form.feedbackType" group="aa"
									value="3">
							</td>
							<td>
								响应时间超出48小时
							</td>
						</tr>
						<tr>
							<td colspan="6" align="middle">
								注意：普通物料每次设置时间为从管理中心发出到物料主数据小组收到要1到2个工作日
							</td>
						</tr>
						<tr>
							<td>
								新建
							</td>
							<td>
								<input type="radio" name="form.isCreate" group="bb" value="1"
									onclick="check2()">
							</td>
							<td>
								修改
							</td>
							<td>
								<input type="radio" name="form.isCreate" group="bb" value="2"
									onclick="check1()">
							</td>
							<td>
								旧物料号
							</td>
							<td>
								<input id="old" type="text" name="form.oldMaterialSn"
									disabled=true>
							</td>

						</tr>
						<tr>
							<td>
								物料类型
							</td>
							<td>
								<select name="form.typeName" id="typeName"
									class="easyui-combobox" data-options="required:true">
									<option value="">选择类型</option>
									<c:forEach items="${types}" var="s">
										<option value="${s}">${s}</option>
									</c:forEach>
								</select>
							</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>
								物料描述(中文,小于20个字长)
							</td>
							<td colspan=5>
								<textArea rows=3 cols=80 class="easyui-validatebox" name="form.description" id="chnDes"
									data-options="required:true"></textArea>

							</td>
						</tr>
						<tr>
							<td>
								物料描述(英文,40个字母长)
							</td>
							<td colspan=5>
								<textArea rows=3 cols=80 class="easyui-validatebox" name="form.description2" id="engDes"
									data-options="required:true"></textArea>
							</td>
						</tr>
						<tr>
							<td>
								备注
							</td>
							<td colspan=5>
								<textArea rows=3 cols=80 class="easyui-validatebox" name="form.remark"
									data-options="required:true"></textArea>
							</td>
						</tr>

						<tr>
							<td>
								制造商物料号
							</td>
							<td>
								<input type="text" name="form.manufacturerSn">
							</td>
							<td>
								制造商名称
							</td>
							<td>
								<input type="text" name="form.manufacturerName">
							</td>
						</tr>


						<tr>
							<td>
								基本单位
							</td>
							<td>
								<input class="easyui-validatebox" type="text" name="form.unit" id="unit" data-options="required:true">
							</td>
							<td>
								资产标签号（仅用于ERSA（备件））
							</td>
							<td>
								<input type="text" name="form.ersa">
							</td>
						</tr>
						<tr>
							<td>
								危险物料
							</td>
							<td>
								<input type="checkbox" name="form.isDanger">
							</td>
							<td>
								If yes, FID #
							</td>
							<td>
								<input type="text" name="form.fid">
							</td>
						</tr>
						<tr>
							<td>
								最低库存数量
							</td>
							<td>
								<input class="easyui-validatebox" type="text" name="form.amin" id="a" data-options="required:true">
							</td>
							<td>
								最高库存数量
							</td>
							<td>
								<input class="easyui-validatebox" type="text" name="form.amax" id="b"
									data-options="required:true">
							</td>
							<td>
								年度用量
							</td>
							<td>
								<input class="easyui-validatebox" type="text" name="form.annual" id="c"
									data-options="required:true">
							</td>
						</tr>
						<tr>
							<td>
								长度
							</td>
							<td>
								<input type="text" name="form.longa">
							</td>
							<td>
								宽度
							</td>
							<td>
								<input type="text" name="form.width">
							</td>
							<td>
								高度
							</td>
							<td>
								<input type="text" name="form.height">
							</td>
						</tr>
						<tr>
							<td>
								自制
							</td>
							<td>
								<input type="checkbox" name="form.isSelfMade" value="1">
							</td>
							<td>
								单价
							</td>
							<td>
								<input type="text" name="form.unitPrice">
							</td>
						</tr>
						
						<tr>
							<td>
								设备维修项 (ERSA)
							</td>
							<td>
								<input type="radio" id="type2" name="form.type2" group="cc" value = "1" data-options="required:true">
							</td>
							<td>
								运营支持(HIBE)
							</td>
							<td>
								<input type="radio" id="type3" name="form.type2" group="cc" value = "2" data-options="required:true">
							</td>
						</tr>
						
						<tr>

							<td colspan="6">
								图片上传
								<input type="file" name="formFile">
							</td>
						</tr>
					</table>

				</form>
			</div>
		    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
	    </div>
	</div>

	<script>
		function submitForm(){
			if(window.confirm("确认提交吗?")) {	
				doSubmitV();	
			 	$('#form1').form('submit');							
			}
		}
		
		function doSubmitV(){
				//alert(document.getElementById('type2').checked);

				if(!document.getElementById('type2').checked
				     && !document.getElementById('type3').checked){
					alert('请选择设备维修项 (ERSA) 或 运营支持(HIBE)');
					return false; 
				}								
				
			}
		
		function clearForm(){
							if(window.confirm("确认保存吗?")) {
					document.getElementById('isSave').value=1
					$('#form1').form('submit');		
				}
		}
	</script>



	</body>
</html>
