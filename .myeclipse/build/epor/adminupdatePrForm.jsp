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

		<title>修改PR单</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
		<link rel="stylesheet" type="text/css" href="css/vave2.css">
		<link rel="stylesheet" type="text/css" href="style/button.css">
		<link rel="stylesheet" type="text/css" href="style/icon.css">
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/button.js"></script>

		<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="ext/ext-all.js"></script>
		<script type='text/javascript' src='dwr/interface/prFormListManager.js'></script>
		<script type='text/javascript' src='dwr/interface/formService.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type="text/javascript"
			src="epor/baseInfo/js/util/loadProjectData.js"></script>
	
		<script type="text/javascript" src='js/util/wforms.js'></script>
		<script type="text/javascript" src='epor/js/adminupdatePrForm.js'></script>
		<script type="text/javascript" src='epor/js/delForm.js'></script>
		<script type="text/javascript" src='epor/js/download.js'></script>
				<script type="text/javascript" src="epor/js/ardetail.js"></script>
	</head>
	<style type="text/css">
        .icon-add {
            background-image:url(images/icons/add.png) !important;
        }
        .icon-del {
            background-image:url(images/icons/delete.gif) !important;
        }
    </style>
	<script type="text/javascript">
  	/**
 	 * 初始化信息
     */
	function init(){

		
	var area = document.getElementById('area').value;
	
	var source = document.getElementById('source').value;
	if (area == null || area == ''||area == 'DT05') {		
		area = 'DT02';
	}
	if(source==1){
		document.getElementById('r1').checked = true;
		area = 'DT02';
	}else if(source==2){
		document.getElementById('r2').checked = true;
		area = 'DT03';
	}else if(source==3){
	    document.getElementById('r3').checked = true;
		area = 'DT04';
	}else if(source==4){
	    document.getElementById('r4').checked = true;
		area = 'DT02';
	}
		//------------------------initARdetail('update')
	loadCostcentreData(area,'${prForm.prCostCenter.id }');
	loadProjectData('${prForm.prProject.id }');
	newGrid(area,'${prForm.id }')

	if('${prForm.recommendedSupplier }'.indexOf('指定') > 0){
		document.getElementById('isAssignation').checked=true
	}
						
		var typeNameValues = '${prForm.typeName }'.split(',')
		//alert(typeNameValues)
		for(var i=0;i<typeNameValues.length;i++){
			if(typeNameValues[i] == '固定资产'){
				document.getElementById('a').checked="checked"
			}else if(typeNameValues[i] == '日常需求'){
				document.getElementById('b').checked="checked"
			}else if(typeNameValues[i] == '工具'){
				document.getElementById('c').checked="checked"
			}else if(typeNameValues[i] == '能    源'){
				document.getElementById('d').checked="checked"
			}else if(typeNameValues[i] == '租赁'){
				document.getElementById('e').checked="checked"
			}else if(typeNameValues[i] == '外包服务'){
				document.getElementById('f').checked="checked"
			}else if(typeNameValues[i] == '间接材料'){
				document.getElementById('g').checked="checked"
			}else if(typeNameValues[i] == '机器设备'){
				document.getElementById('h').checked="checked"
			}else if(typeNameValues[i] == '维修件'){
				document.getElementById('i').checked="checked"
			}else if(typeNameValues[i] == 'IT'){
				document.getElementById('j').checked="checked"
			}else if(typeNameValues[i] == '直接物料SPOT BUT'){
				document.getElementById('k').checked="checked"
			}else if(typeNameValues[i] == 'OST'){
				document.getElementById('l').checked="checked"
			}				
		}	

	}

	function changeBuyer(area){
		loadBuyerData2(area.value);
	}
  
  </script>
	<body onload="init()">
		<table width="100%">
			<tr>
				<td>
					<input type="button" icon="icon-delete" value="删除" onclick="doDel('${prForm.id }','${prForm.state }')">
				</td>
			</tr>
		</table>
		<!--  -->
		<form id="form" action="epor.do?actionType=doUpdateAdmin" enctype="multipart/form-data" method="post">
			<input type="hidden" name="prForm.id" value="${prForm.id }">
			<input type="hidden" name="prForm.version" value="${prForm.version }">
			<input type="hidden" id="source"  value="${prForm.source }">
	
			<input type="hidden" id="buyer" name="prForm.tempolyeeByBuyerId" >
			<input type="hidden" id="info" name="prForm.info" >
			<input type="hidden" id="uidd" name="approvedPR.tempolyee.uid" value="${logineduser.emp.uid }">
			<input type="hidden" id="total" name="prForm.total" value="${prForm.total }">
			<input type="hidden" id="area" value="${logineduser.emp.area }">
			
			<input type="hidden" name="oldccid" value="${prForm.prCostCenter.id }">
			<table width="100%">
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
							PR状态:
						</label>
						<input type="text" id="state" name="prForm.state" value="${prForm.state }">
					</td>
					<td>
						<label>
							PR状态2:1:等待解释,0:正在审批,-1:被退回,4:编辑状态,5:已解释,6:完成
						</label>
						<input type="text" id="info" name="prForm.info" value="${prForm.info }">
					</td>
					<td>
						<label>
						资本化/费用化://是否资本化 0 资本化，1费用化
						</label>
						<input type="text" id="isPlan" name="prForm.isPlan" value="${prForm.isPlan }">
					</td>
					<td>
						<label>
							计划内计划外://0 计划内 1 计划外
						</label>
						<input type="text" id="isCapital" name="prForm.isCapital" value="${prForm.isCapital }">
					</td>
					<td >
						<label >
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
					

				</tr>
				<tr>
					
					<td >
						<label>
							为哪个工厂/总部填写请购单:
						</label>
					</td>
					<td>
					
						<input id="r1" type="radio" name="source" value="dt02" onclick="changeBuyer(this)">SH
						<input id="r2" type="radio" name="source" value="dt03" onclick="changeBuyer(this)">SY
						<input id="r3" type="radio" name="source" value="dt04" onclick="changeBuyer(this)">YT
						<input id="r4" type="radio" name="source" value="dt05" onclick="changeBuyer(this)">SH_PLANT

					</td>
				</tr>				
				<tr>
					
					<td colspan="3">
						<label>
							成本中心:
						</label>
						<select id="costCenter" name="prForm.prCostCenter.id">
						</select>
					</td>
				</tr>
				
				<tr>
					<td colspan="3">
						<label>
							AR_NO：
						</label>
						<div id="bb"></div>
						<input class="required" id="recommendedSupplier" type="text"
							name="prForm.arno" value="${prForm.arno }">	
					</td>
				</tr>
				
				<tr>
					<td colspan="3">
						<label>
							项目标号：
						</label>
						<select id="projectId" name="prForm.prProject.id">
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<label>
							大额金额号：
						</label>
						<input class="required" id="recommendedSupplier" type="text"
							name="prForm.bigCountNo" value="${prForm.bigCountNo }">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<label>
							电话分机:
						</label>
						<input class="required" id="telephone" type="text"
							name="prForm.telephone" value="${prForm.telephone}">
					</td>
				<tr>
				<tr>
					<td >
						<label>
							推荐供应商：
						</label>
						<input class="required" id="recommendedSupplier" type="text"
							name="prForm.recommendedSupplier" value="${prForm.recommendedSupplier }">
					</td>
					<td>
						是否为指定<input id="isAssignation" type="checkbox" name="isAssignation" value="1" >
					</td>
				</tr>
				
				
				<tr>
					<td colspan="4">
						<label>
							类型:
						</label>
						
						<input id="a" type="checkbox" name="a" value="1">固定资产
						
						<input id="b" type="checkbox" name="a" value="2">日常需求
						
						<input id="c" type="checkbox" name="a" value="3">工具
						
						<input id="d" type="checkbox" name="a" value="4">能 源
					
						<input id="e" type="checkbox" name="a" value="5">	租赁
						
						<input id="f" type="checkbox" name="a" value="6">外包服务
					
						<input id="g" type="checkbox" name="a" value="7">	间接材料
						
						<input id="h" type="checkbox" name="a" value="8">机器设备
					
						<input id="i" type="checkbox" name="a" value="9">维修件
						
						<input id="j" type="checkbox" name="a" value="10">IT
						
						<input id="k" type="checkbox" name="a" value="11">直接物料SPOT BUT
						<input id="l" type="checkbox" name="a" value="12">OTS
					</td>
				</tr>
				<tr>
					<td >
						<label>
							购买理由：
							<!--  -->
						</label>
						<textarea rows=4 cols=50 class="required" 
							name="prForm.remark" />${prForm.remark }</textarea>
						
					</td>
				</tr>
				<tr>
				<c:if test="${prForm.fileName != ''}">
						<td>
							<a href="#" onClick="download(this,'${prForm.id}','${prForm.fileName}')">请购单附件:${prForm.fileName }</a>
						</td>
				</c:if>
				
					<td>
					上传附件:<input type="file" name="formFile" value="${prForm.fileName }" >			
					</td>

				
				
			</tr>
			
			</table>
			<div id="aa"></div>			
			<table width="100%">
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
							<input type="hidden"  name="mailer" value="${item.tempolyee.uid }">
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

				<tr>

					<td>
						<input type="button" value="提交" onclick="sub(2,'${prForm.id }')"> 
					</td>

			

				</tr>
			</table>
		</form>
	</body>
</html>
