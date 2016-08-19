<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/style_admin.css">
		<script type='text/javascript' src='dwr/interface/IndicateService.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
	</head>

	<script type="text/javascript">
  	//启用指标
  	function enable(ele,id,typeName){
  		var t;
  		if(ele.checked){
  			t = 0;
  		}else{
  			t = -1;
  		}
  		IndicateService.doUpdate(t,id,typeName,function(data){

  			if(data==1){
  				alert('更新成功!'+typeName+'类指标已禁用请重新分配权重')
  				//alert(document.getElementById(typeName).value)
  				//document.getElementById(typeName).disable = true;
  				document.getElementById(typeName).value = 0;
  				changeWeight()
  			}else{
  				alert('更新成功!')
  			}
  		});
  	}
  	//更新权重
  	function changeWeight(){
  		var weights = document.getElementsByName('weight');
  		var size = weights.length;
  		var totalWeight = 0;
  		for(var i = 0 ; i<size ; i++){
  			if(weights[i].getAttribute('id')!='加分')
  			totalWeight = parseInt(totalWeight) + parseInt(weights[i].value);
  		}
  		document.getElementById('total').innerText = 'total: '+totalWeight
  	}
  	
  	function aa(val){
  		alert(val.length)
  	}
  	  	
  	function init(){
  		var weights = document.getElementsByName('weight')
  		var totalWeight = 0
  		for(var i = 0; i<weights.length-1; i++ ){
  			totalWeight = parseInt(totalWeight) + parseInt(weights[i].value)
  		}
  		document.getElementById('total').innerText = 'Total :'+totalWeight
  	}
  	
  	function check(){
  		var text = document.getElementById('total').innerText;
  		if(text.split(':')[1]!=null){
  			if(text.split(':')[1]<=100){return true}
  			else{
  				alert('权重不能大于100');
  				return false
  			}
  		} 		
  	}
  </script>
	<body onload="init()">
	<form action="dMS.do?actionType=doUpdateIndicate" method="post" onsubmit="return check()">
		<table align="center" valign="center" cellspacing="1" cellpadding="4"
			width="90%" class="tableborder">
			<thead class="header">
				<tr>
					<td>
						类型
					</td>
					<td>
						权重
					</td>
					<td>
						名称
					</td>
					<td>
						說明
					</td>

					<td colspan="2">
						操作
					</td>
				</tr>
			</thead>
			<c:forEach items="${rs}" var="item" varStatus="state">
				<tr>
					<td align="center" class="altbg1"
						rowspan="${fn:length(item.value)+1}">
						${item.key}
					</td>

					<td align="center" class="altbg1"
						rowspan="${fn:length(item.value)+1}">
						
						<input size="5" type="text" name="weight" 
							value="${weights[state.index]}" id="${item.key}" onchange="changeWeight()">
							
						<input size="5" type="hidden" name="id" 
							value="${ids[state.index]}" >
						<input size="5" type="hidden" name="type" 
							value="${item.key}" >
					</td>

					<c:forEach items="${item.value}" var="v">

						<tr>
							<td align="center" class="altbg1">
								${v.name}
							</td>
							<td align="center" class="altbg1">
								${v.remark}
							</td>

							<td align="center" class="altbg1">

								
								<c:if test="${v.state == 0}">
									启用<input type="checkbox" checked="true"
										onClick="enable(this,'${v.id}','${v.typeName }')">
								</c:if>
								<c:if test="${v.state == -1}">
									启用<input type="checkbox"  onClick="enable(this,'${v.id}','${v.typeName }')">
								</c:if>

							</td>
						</tr>

					</c:forEach>
				</tr>
			</c:forEach>

			<tr class="header">
				<td></td>
				<td id="total" id="total">
					total ：
				</td>
				<td></td>
				<td></td>
				
				<td>
					<lable>
					<input type="submit" value="提交" >
					</lable>
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>
