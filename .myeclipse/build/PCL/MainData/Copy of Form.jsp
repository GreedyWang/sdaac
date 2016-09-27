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
		<link rel="stylesheet" href="css/themes/base/jquery.ui.all.css">
		<link rel="stylesheet" href="css/demos.css">
		<script src="js/jquery-1.9.1.js"></script>
		<script src="js/jquery.ui.core.js"></script>
		<script src="js/jquery.ui.widget.js"></script>
		<script src="js/jquery.ui.button.js"></script>
		
	
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
				if(window.confirm("确认保存吗?")) {
					document.getElementById('isSave').value=1
					//window.forms[0].submit();
					document.getElementById('form1').submit();
				}
			}
			   
			function doSubmit(){
				if(document.getElementById('typeName').value ==''){
					alert('选择类型');
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
				}else {
					document.getElementById('form1').submit();
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
	<form id="form1" action="mainData.do?actionType=doApply" enctype="multipart/form-data" method = "post">
		<input type="hidden" name="isSave" id="isSave">
		<table  width="400">
			<tr class="header">
				<td colspan="4" align="middle">
					<label >间接物料主数据申请单</label>
				</td>
			</tr>
			<tr>
			<td >是否为包装<input type="checkbox" name="form.isPackage" value="1"> </td>
		
			<td><label style="color : red">批量申请</label><input type="checkbox" name="form.isPackage" value="批量申请"> <input type="file" name="file3"></td>
			</td>
			<td >采购员
						<select name="form.buyerName">
							<option value="丁兵兵482">
								丁兵兵482
							</option>
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
						</select>
			</td>
			<td>
				工厂 <select name="form.factory">
					<option value="上海">上海</option>
					<option value="沈阳">沈阳</option>
					</select>
			</td>
			</tr>

			<tr>
				<td>电话</td>
				<td><input type="text" name="form.phone"> </td>
				<td>成本中心</td>
				<td><input type="text" name="form.costCenter"></td>
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
				<td>新建<input type="radio" name="form.isCreate" group="bb" value="1" onclick="check2()"></td>
				<td>修改<input type="radio" name="form.isCreate" group="bb" value="2" onclick="check1()"></td>
				<td>旧物料号</td>
				<td><input id="old" type="text" name="form.oldMaterialSn" disabled=true></td>
			</tr>
			<tr>
				<td>物料类型</td>							
				<td>
				<select name="form.typeName" id="typeName">
				<option value="">选择类型</option>
	 		 			<c:forEach items="${types}" var="s">	
	   						<option value="${s}">${s}</option>
	  		 			</c:forEach>
					</select> 
				</td>
			</tr>
			<tr>
				<td>物料描述(中文,小于20个字长)</td>
				<td colspan=3 >
				<textArea rows=3 cols=130 name="form.description" id="chnDes"></textArea>
					
				</td>
			</tr>
			<tr>
				<td>物料描述(英文,40个字母长)</td>
				<td colspan=3 >
				<textArea rows=3 cols=130 name="form.description2" id="engDes"></textArea>		
				</td>
			</tr>
			<tr>
				<td>备注</td>
				<td colspan=3 >
				<textArea rows=3 cols=130 name="form.remark"></textArea>		
				</td>
			</tr>
			<tr>
				<td>制造商物料号</td>
				<td><input type="text" name="form.manufacturerSn"></td>
				<td>制造商名称</td>
				<td><input type="text" name="form.manufacturerName"></td>
			</tr>
			<tr>
				<td>基本单位</td>
				<td><input type="text" name="form.unit" id="unit"></td>
				<td>资产标签号（仅用于ERSA（备件））</td>
				<td><input type="text" name="form.ersa"></td>
			</tr>
			<tr>
				<td>危险物料<input type="checkbox" name="form.isDanger"></td>
				<td></td>
				<td>If yes, FID #:</td>
				<td><input type="text" name="form.fid"></td>
			</tr>
			<tr>
				<td>最低库存数量<input type="text" name="form.amin" id="a"></td>
				<td>最高库存数量<input type="text" name="form.amax" id="b"></td>
				<td>年度用量<input type="text" name="form.annual" id="c"></td>
			</tr>
			<tr>
				<td>长度<input type="text" name="form.longa"></td>
				<td>宽度<input type="text" name="form.width"></td>
				<td>高度<input type="text" name="form.height"></td>
			</tr>
			<tr>
				<td>自制<input type="checkbox" name="form.isSelfMade" value="1"></td>
				<td><br><br></td>
				<td>单价</td>
				<td><input type="text" name="form.unitPrice"></td>
			</tr>
			<tr>
			
				<td>图片上传<input type="file" name="formFile"></td>			
			</tr>	
			<tr>
				<td> <input type="button" value="提交" onClick="doSubmit()"></td>
				<td> <input type="button" value="保存" onClick="save()"> </td>
				
			</tr>			
		</table>
		
	</form>
	</body>
</html>
