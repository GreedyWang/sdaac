<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
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
		<link rel="stylesheet" type="text/css" href="css/style_admin.css">
		<script type='text/javascript' src='dwr/interface/IndicateService.js'></script>
    	<script type='text/javascript' src='dwr/engine.js'></script>
  </head>
  
  <script type="text/javascript">
  	//启用指标
  	function enable(t,id){
  		
  		IndicateService.doUpdate(t,id,function(data){
  			
  		});
  	}
  	//更新权重
  	function changeWeight(){
  		var weights = document.getElementsByName('weight');
  		var size = weights.length;
  		var totalWeight = 0;
  		for(var i = 0 ; i<size ; i++){
  			totalWeight = parseInt(totalWeight) + parseInt(weights[i].value);
  		}
  		alert(totalWeight)
  		document.getElementById('total').innerText = 'total: '+totalWeight
  	}
  
  </script>
  <body>
    <table align ="center" valign="center" cellspacing="1" cellpadding="4" width="90%"
				class="tableborder">
    	<thead  class="header" >
   			<tr>    											    		
    			<td>类型	</td>
    			<td>名称</td>
    			<td>说明</td>  			
    			<td>权重</td>
    			
    			<td colspan = "2" >操作</td>
    		</tr>
    	</thead>
		<c:forEach items="${rs}" var="item">
			<tr>
					<td align="center" class="altbg1">
								${item.typeName}
					</td>
					<td align="center" class="altbg1">
								${item.name}
					</td>
					<td align="center" class="altbg1">
								${item.remark}
					</td>
	
					<td align="center" class="altbg1">
								<c:if test="${item.calc == 88}">
									<input size="5" disabled="disabled" type="text"  value="${item.weight}" >
								</c:if>
								<c:if test="${item.calc != 88}">
									<input size="5" type="text" name ="weight" value="${item.weight}" onchange="changeWeight()">
								</c:if>
								
					</td>
					<td align="center" class="altbg1">
		
								<c:if test="${item.state == 0}">
									启用<input type="checkbox" checked="true" onClick="enable(-1,'${item.id}')">
								</c:if>
								<c:if test="${item.state == -1}">
									启用<input type="checkbox" value="" onClick="enable(0,'${item.id}')">
								</c:if>
								
					</td>
			
			</tr>
		</c:forEach>
			
			<tr  class="header"> 
				<td></td>
				<td></td>
				<td></td>
				<td id="total"> total ： </td>
				<td>
					<lable><input type="button" value="提交"></lable>
				</td>
			</tr>
    </table>
  </body>
</html>
