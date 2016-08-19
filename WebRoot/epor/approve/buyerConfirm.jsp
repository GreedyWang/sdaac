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
		<link rel="stylesheet" type="text/css" href="css/vave.css">
		<link rel="stylesheet" type="text/css"
			href="ext/resources/css/ext-all.css">

		<script type="text/javascript" src='epor/js/currentType.js'></script>
		<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="ext/ext-all.js"></script>
		<script type='text/javascript' src='dwr/interface/supplyService.js'></script>
		<script type='text/javascript' src='dwr/interface/formService.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		
	</head>
	<script type="text/javascript">
		function dl(id, fileName) {
			var url = '\\' + id + '\\' + fileName;
			document.getElementById('ffile').value = url
		
			document.getElementById('f1').submit();
 		}	
	
	function init(){
		if('${prForm.isInTheSap }'==1){
			document.getElementById('isInTheSap1').checked=true;
		}
		loadSupplyList();
		
	}
	
	function changeIsSub2(){
	var a = document.getElementById('buyerfilename').value

	if(a!='' && a.split('.')[a.split('.').length - 1] != 'zip'){
								alert('附件请压缩成.zip文件!');
								return;
							}else{
								document.forms[0].submit();
							}
	}
	
	function changeIsSub(){
	var a = document.getElementById('buyerfilename').value

	if(a!='' && a.split('.')[a.split('.').length - 1] != 'zip'){
								alert('请压缩成.zip文件!');
								return;
							}					
	if(document.getElementById('selIsApprove').value == 1){ // 批准
	
	    var all =  document.getElementsByTagName('input');
		for (var e = 0; e < all.length; e++) {
	    	  if (all[e].className == 'price') {
	       		if(all[e].value == '' || all[e].value < 0.01){
	       			alert('请填写单价');
	       			return 
	     		 }
	     	  }
	    }
    
		if(document.getElementById('isInTheSap1').checked ==false && document.getElementById('isInTheSap2').checked ==false){
			alert('请确认供应商是否在sap系统内')
			return 
		}
		if(document.getElementById('receivingPlacePerpoleTel').value==''){
			alert('请确认交货地点/收货人/电话')
			return 
		}
		if(document.getElementById('acutalSupplier').value==''){
			alert('请确认实际供应商')
			return 
		}
			
	}
		if( document.getElementsByName('price')){
			document.getElementById('isSubmit').value='true';
			if(window.confirm("确认提交？"+"采购附件为："+a))
			//document.forms[0].submit();
			document.getElementById('aaxs').submit();
	}
	}
	
	var win2 = false;
	function xxx() {
		//index = pindex;
		if (!win2) {
		var id = document.getElementById('prformid').value;
		var url = 'epor.do?actionType=doUpoladBuyerFile&id='+id
		var form = new Ext.form.FormPanel({
     baseCls : 'x-plain',
     labelWidth : 70,
     fileUpload : true,
     defaultType : 'textfield',
     items : [{
        xtype : 'textfield',
        fieldLabel : '上传文件名',
        name : 'formFile',
        id : 'userfile',
        inputType : 'file',
        blankText : 'File can\'t not empty.',
        anchor : '100%' // anchor width by percentage
       },{
       xtype:'button',
      text : '上传',
      handler : function() {
       if (form.form.isValid()) {
        if(Ext.getCmp('userfile').getValue() == ''){
         Ext.Msg.alert('错误','请选择你要上传的文件');
         return;
        }
        Ext.MessageBox.show({
           title : '请等待',
           msg : '文件正在上传...',
           progressText : '',
           width : 300,
           progress : true,
           closable : false,
           animEl : 'loding'
          });
        form.getForm().submit({
         url : url,
         method : 'POST',
         timeout : 10000,
         success : function(form, action) {
         var bfn = document.getElementById('userfile').value
        var bfn2 = bfn.split('\\')[bfn.split('\\').length-1];
         document.getElementById('buyerfilename').value='buyerFile-'+bfn2;
          Ext.Msg.alert('Message',
            '上传成功!');         
         },
         failure : function() {
          document.getElementById('buyerfilename').value=''
          Ext.Msg.alert('Error',
            '上传失败!');
         }
        })
       }
      }
     }]
       
    });
		
			win2 = new Ext.Window({
				applyTo : 'hello-win',
				layout : 'fit',
				width : 360,
				autoHeight : true,
				modal : true,
				closeAction : 'hide',
				plain : true,
			
					items : [ form]
	
				});

		

		}
		win2.center();
		win2.show();
	}	
		
function showSupplyList(value){
	if(value == 0){
		//hide actual a
		var list = document.getElementById('acutalSupplierlist');
		list.style.display = 'none';
		//document.getElementById('acutalSupplier').disabled = false;
	}else if(value == 1){
		//show actual list
		var list = document.getElementById('acutalSupplierlist');
		list.style.display="";
		//document.getElementById('acutalSupplier').disabled = true;
	}
	
	

	document.getElementById('');
}
//load supplyList
function loadSupplyList() {
	supplyService.showall(function(data) {
				var roles = document.getElementById('acutalSupplierlist')
				roles.options.length = 0;
				if (data != null) {
					for (var i = 0; i < data.length; i++) {
						var opt = document.createElement('Option');
						opt.text = data[i].supplyName
						opt.value = data[i].supplyName
						roles.options.add(opt);
					}
					//default
					var opt = document.createElement('Option');
					opt.text = '请重新选择'
					opt.value = -1		
					opt.selected = 'selected'
					roles.options.add(opt);
				}
			});
}	

function choiceSupply(val){
document.getElementById('acutalSupplier').value = val;
}	
</script>
	<body onload="init()">
		<form action="epor.do?actionType=doBuyerConfirm" method="post"
			id="aaxs" enctype="multipart/form-data">
			<table>
				<tr>
					<td colspan="4" align="center" id="intro_right">
						<h2>
							上海德尔福汽车空调系统有限公司
						</h2>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							申请人:
						</label>
						${prForm.tempolyeeByApplicantId.name }
					</td>
					<td>
						<label>
							部门:
						</label>
						${prForm.tempolyeeByApplicantId.tdepartment.name }
					</td>
					<td>
						<label>
							电话分机:
						</label>
					</td>
					<td>
						<label>
							申请日期:
						</label>
						${prForm.applicantDate}
					</td>
				</tr>
				<tr>
					<td>
						<label>
							采购员:
						</label>
						${prForm.tempolyeeByBuyerId }
					</td>
					<td>
						<label>
							成本中心:
						</label>
						${prForm.prCostCenter.costCenterName }
					</td>
					<td>
						<label>
							类型:
						</label>
						${prForm.typeName }
					</td>

					<c:if test="${prForm.isPlan==0 }">
						<td>
							<label>
								计划内
							</label>
						</td>
					</c:if>

					<c:if test="${prForm.isPlan==1 }">
						<td>
							<label>
								计划外
							</label>
						</td>
					</c:if>

				</tr>
				<tr>
					<td>
						<label>
							推荐供应商：
						</label>
						${prForm.recommendedSupplier }
					</td>
					<td>
						<label style="color: red">
							供应商是否在sap系统内
						</label>
						是
						<input id="isInTheSap1" type="radio" name="prForm.isInTheSap"
							value="1" onclick="showSupplyList(1)">
						否
						<input id="isInTheSap2" type="radio" name="prForm.isInTheSap"
							value="0" onclick="showSupplyList(0)">
					</td>
					<td>
						<label>
							实际供应商：
						</label>
						<input type="text" id="acutalSupplier" name="prForm.acutalSupplier" value="${prForm.acutalSupplier }">
						<select name="prForm.acutalSupplier" id="acutalSupplierlist" style="display: none;" onchange="choiceSupply(this.value)">
						</select>
					</td>

					<td colspan="2">
						<label>
							交货地点/收货人/电话：
						</label>
						<input type="text" id="receivingPlacePerpoleTel" class="required"
							name="prForm.receivingPlacePerpoleTel"
							value="${prForm.receivingPlacePerpoleTel }">
					</td>
				</tr>
				<tr>
					<td>
						单一供应商(申请人):<c:if test="${prForm.isUrgency==1 }">是</c:if><c:if test="${prForm.isUrgency!=1 }">不是</c:if>
					</td>

					<td>
						紧急采购(申请人):<c:if test="${prForm.isAssignation==1 }">是</c:if><c:if test="${prForm.isAssignation!=1 }">不是</c:if>
					</td>

				</tr>
				<tr>

					<td>
						单一供应商(采购员)
						<input id="isInTheSap1" type="checkbox" name="prForm.isUrgencyB" value="1">
						是
					</td>

					<td>
						紧急采购(采购员)
						<input id="isInTheSap1" type="checkbox" name="prForm.isAssignationB" value="1">
						是
					</td>
				</tr>
				<tr>
					<td>
						购买理由:
					</td>
					<td>
						${prForm.remark }
					</td>
				</tr>
			</table>
			<table>
				<tr id="intro_right">
					<td>
						名称
					</td>
					<td>
						备注
					</td>
					<td>
						品牌&型号
					</td>
					<td>
						数量
					</td>
					<td>
						单位
					</td>
					<td>
						要求交货日期
					</td>
					<td>
						单价(请购人估价)
					</td>
					<td>
						单价(不含税,2位小数,RMB)
					</td>
					<td>
						采购员
					</td>
					<td>
						总价
					</td>
					<td>
						备注
					</td>
					<td>
						总帐科目
					</td>
					<td>
						定单NO.
					</td>
				</tr>
				<c:forEach items="${prForm.prBuyContexts}" var="item"
					varStatus="index">
					<tr>
						<td>
							${item.description }
						</td>
						<td>
							${item.remark }
						</td>
						<td>
							${item.band }
						</td>						
						<td>
							${item.quantity}
						</td>
						<td>
							${item.unit }
						</td>
						<td>
							${item.expectDeliveryDate }
						</td>
						<td>
							${item.unitPriceE }
						</td>
						<td>
							<input type="text" class="price" name="price"
								onkeyup="checkNumber(this)" value="${item.unitPrice }">
						</td>
						<td>
							${item.buyerId }
						</td>
						<td>
							<br>
						</td>
						<td>
							<input type="text" name="currency" class="required"
								value="${item.currency }">
						</td>
						<input type="hidden" name="prBuyContextId" value="${item.id}">
						<input type="hidden" name="quantity" value="${item.quantity}">
					</tr>
				</c:forEach>

				<tr>
			</table>
			<table>
			<tr>
				<c:if test="${prForm.fileName != ''}">


					<td>
						<input type="button" value="附件下载:${prForm.fileName}"
							onClick="dl('${prForm.id}','${prForm.fileName}')">



					</td>

				</c:if>

				<c:if test="${prForm.buyerfileName != null}">

					<td>
						<input type="button" value="采购附件:${prForm.buyerfileName}"
							onClick="dl('${prForm.id}','${prForm.buyerfileName}')">




					</td>

				</c:if>

				<c:if test="${prForm.north_AmericaFileName != null}">
					<td>
						<input type="button" value="北美附件:${prForm.north_AmericaFileName}"
							onClick="dl('${prForm.id}','${prForm.north_AmericaFileName}')">


					</td>
				</c:if>





				<td>
					上传采购附件:
					<input type="text" name="prForm.buyerfileName" id="buyerfilename"
						readonly="readonly">
					<!-- 
						<input type="file" id="buyerfile2" name="formFile"
							value="${prForm.fileName }">
 -->
					<input type="button" value="上传采购附件" name="xxxc" onclick="xxx()">

				</td>
				</tr>
			</table>
			<table>
				<tr id="intro_right">
					<td>
						审批人职位
					</td>
					<td>
						审批人姓名
					</td>
					<td>
						建议
					</td>
					<td>
						审批意见
					</td>
					<td>
						审批日期
					</td>
				</tr>
				<c:forEach items="${prForm.copyPrApprovedForm}" var="item">
					<tr>
						<td>
							${item.type }
						</td>
						<td>
							${item.tempolyee.name }
						</td>
						<td>
							${item.isApprovedName }
						</td>
						<td>
							${item.context }
						</td>
						<td>
							${item.datetime }
						</td>
					</tr>
				</c:forEach>

				<input type="hidden" name="approvedPR.prPrForm.source"
					value="${prForm.source }">
				<input type="hidden" name="approvedPR.prPrForm.version"
					value="${prForm.version }">
				<input type="hidden" id="prformid" name="prForm.id"
					value="${prForm.id }">
				<input type="hidden" name="prForm.tempolyeeByBuyerId"
					value="${prForm.tempolyeeByBuyerId }">
				<input type="hidden" name="prForm.tempolyeeByApplicantId.area"
					value="${prForm.tempolyeeByApplicantId.area }">
				<input type="hidden" name="prForm.source" value="${prForm.source }">
				<input type="hidden" id="isSubmit" name="isSubmit" value="false">
				<input type="hidden" name="prPrForm.state" value="${prForm.state }">

				<table>
					<tr>
						<td>
							审批意见
						</td>
						<td>
							意见说明
						</td>

					</tr>
					<tr>
						<td>
							<select name="approvedPR.isApproved" id="selIsApprove">
								<option value="1">
									批准
								</option>
								<option value="0">
									不批准
								</option>
								<option value="2">
									需要解释
								</option>
							</select>
						</td>
						<td>
							<textarea rows="" cols="100%" name="approvedPR.context"></textarea>
						</td>
					</tr>
					<tr>
						<td>
							<input type="button" value="提交" onclick="changeIsSub()">
						</td>
						<td>
							<input type="button" value="保存" onclick="changeIsSub2()">
						</td>
					</tr>
				</table>
			</table>
		</form>

		<form id="f1" action="epor.do?actionType=doGetAttach" method="post">
			<input type="hidden" name="path" id="ffile">
		</form>

		<div id="hello-win">
			<div id="hello-tabs"></div>
		</div>
	</body>
</html>
