<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>schedule</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/vave/cacl.js"></script>
    <script type="text/javascript" src="calc/calendar.js"></script>
	<script type="text/javascript" src="calc/calendar-en.js"></script>
	<script type='text/javascript' src="js/cal.js"></script>
	<link type="text/css" rel="stylesheet" href="calc/calendar-system.css"/>
  </head>
  <script type="text/javascript">
  function checkBlank()
{
	var expectSaving=document.getElementById('estimatedSaving');
	var expectFinishTime=document.getElementById('expectFinishTime');
	var ins_program=document.getElementById('ins_program');

	if(expectSaving.value==null||expectSaving.value=="")
	{
		if(window.confirm("确认不填写计算附表?"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
function checkXX()
{
	document.getElementById('asd').value='1.现在方案成本'
	document.getElementById('ins_cost').readOnly=false
}
function checkXY()
{
	document.getElementById('asd').value='1.单位节约成本'
	alert('不用再填写代用方案成本')
	document.getElementById('ins_cost').readOnly=true
}
function TaddNV() {

	var  table1=document.getElementById('tableNV');
 
  	var newTr = table1.insertRow();
	var newTd0 = newTr.insertCell();	
	var newTd1 = newTr.insertCell();
	var newTd2 = newTr.insertCell();
	
	var index=document.getElementById('tableNV').rows.length;
	index=index+1;	
	newTd0.innerHTML= '<input class="required" type="text" size="10" name="imvalue" />'; 
	newTd1.innerHTML= '<input class="required" type="text" size="10" name="imvalue" />';					
	newTd2.innerHTML= '<img src="images/delete.gif" id="image"  onclick="delNV(this)"> ';
}

  function   delNV(o){   
  var t=document.getElementById('tableNV')   
  t.deleteRow(o.parentNode.parentNode.rowIndex)   
  } 
  </script>
  <body>
   <table>																						
	<tr>
	<td>
	预计节约成本(元)</td>
	<td><input type="text" id="estimatedSaving" name="expectSaving" class="required" value="${proposalMore.expectSaving}"></td>
	<td>预计完成日期</td>
    <td><input type="text" id="expectFinishTime" name="expectFinishDate"  class="required" onfocus="calShow('expectFinishTime');" value="${proposalMore.expectFinishDate}"></td>
	</tr>
	<tr>
	
	</tr>
	</table>
	
<table>
  	<tr>
  		<td vAlign="top"><table vAlign="top" id="tableNV">
  		  		 <thead>
 <tr id="intro_right" vAlign="top">
 <td>分类<br><br></td>
 <td>金额<br><br></td>
  <td><img src="images/add.gif" id="image"  onclick="TaddNV()"></td>
 </tr>
 </thead>
 <tr>
 	<td>物流运输费用节约</td><td><input type="text" name="imvalue" value="${PropsalDetailByID.vv[0]}"> </td> </tr>
  <tr>	<td>包装费用节约</td><td><input type="text" name="imvalue" value="${PropsalDetailByID.vv[1]}"></td> </tr>
  <tr>	<td>仓库租赁费用节约</td><td><input type="text" name="imvalue" value="${PropsalDetailByID.vv[2]}"></td> </tr>
 	<td>库存成本节约</td><td><input type="text" name="imvalue" value="${PropsalDetailByID.vv[3]}"></td> </tr>
  <tr>	<td>水电等能源费用节约</td><td><input type="text" name="imvalue" value="${PropsalDetailByID.vv[4]}"></td> </tr>
  <tr>	<td>辅料费用节约</td><td><input type="text" name="imvalue" value="${PropsalDetailByID.vv[5]}"></td> </tr>

  <c:forEach items="${PropsalDetailByID.vvv}" var="item" varStatus="state">

	<c:if test="${(state.index) % 2 == 0}">
		<tr>
	</c:if>
	 <td><input type="text" name="imvalue" value="${item }"></td>
	<c:if test="${(state.index+1) % 2 == 0}">
		</tr>
	</c:if>
  </c:forEach>
  	</table></td>
  	
  		<td width="60%"><table>
   		 <thead>
 <tr id="intro_right">
 <td>项目<br><br></td>
 <td>数量<br><br></td>
 <td>单位<br><br></td>
 </tr>
 </thead>
  		<tbody>
 
 
 
 <tr>

  <td><input type="radio" id="1"  name="a" onclick="checkXX()" >1.现在方案成本
 <input type="radio" id="2" name="a" onclick="checkXY()">1.单位节约成本
 <input type="hidden" id="asd" name="project" value="${VaveSchedule[0].project.project}"/><br><br></td>
 <td><input type="text" size="5" id="cur_cost" style="text-align: right" name="quantity" value="${VaveSchedule[0].project.quantity}" /><br><br></td>
 <td><input type="text" size="5" name="unit" value="${VaveSchedule[0].project.unit}"/><br><br></td>
 </tr>
  <tr>
<td>${VaveSchedule[1].project.project}<input type="hidden" name="project" value="2.代用方案成本"/><br><br></td>
 <td><input type="text" size="5" id="ins_cost" style="text-align: right" name="quantity" value="${VaveSchedule[1].project.quantity}" /><br><br></td>
 <td><input type="text" size="5" name="unit" value="${VaveSchedule[1].project.unit}"/><br><br></td>
 </tr>
 <tr>
<td>${VaveSchedule[2].project.project}<input type="hidden" name="project" value="3.年用量合计"/><br><br></td>
 <td><input type="text" size="5" id="Annual_quantity" style="text-align: right" name="quantity" value="${VaveSchedule[2].project.quantity}" /><br><br></td>
 <td><input type="text" size="5" name="unit" value="${VaveSchedule[2].project.unit}"/><br><br></td>
 </tr>
 <tr>
<td>${VaveSchedule[3].project.project}<input type="hidden" name="project" value="4.总节约成本"/><br><br></td>
 <td><input type="text" size="5" id="total_saving"  style="text-align: right" name="quantity" value="${VaveSchedule[3].project.quantity}" /><br><br></td>
 <td>元<br><br></td>
  <input type="hidden" size="5" name="unit" value="元"/>
 </tr>

 <tr>
<td>${VaveSchedule[4].project.project}<input type="hidden" name="project" value="5.一次性费用"/><br><br></td>
 <td><input type="text" size="5" id="total_onceTimeCosts"  style="text-align: right" name="quantity" value="${VaveSchedule[4].project.quantity}" /><br><br></td>
 <td>元<br><br></td>
  <input type="hidden" size="5" name="unit" value="元"/>
 </tr>
<tr>
<td>${VaveSchedule[5].project.project}<input type="hidden" name="project" value="6.净节约成本"/><br><br></td>
 <td><input type="text" size="5" id="net_SavingCost"  style="text-align: right" name="quantity" value="${VaveSchedule[5].project.quantity}" /><br><br></td>
 <td>元<br><br></td>
  <input type="hidden" size="5" name="unit" value="元"/>
 </tr>
 
 <tr>
<td>${VaveSchedule[6].project.project}<input type="hidden" name="project" value="7.C/D/S分配比例%"/>
 <br>如30/50/20
 </td>
 <td><input type="text" size="6" id="distribution_Program"  style="text-align: right" name="quantity" value="${VaveSchedule[6].project.quantity}" /><br><br></td>
 <td>%<br><br></td>
  <input type="hidden" size="5" name="unit" value="%"/>
 </tr>
  <tr>
 <td>${VaveSchedule[7].project.project}<input type="hidden" name="project" value="8.SDAAC净节约成本"/><br><br></td>
 <td><input type="text" size="7" id="sdaacnet_SavingCost"  style="text-align: right" name="quantity" value="${VaveSchedule[7].project.quantity}" /><br><br></td>
 <td>元<br><br></td>
  <input type="hidden" size="5" name="unit" value="元"/>
 </tr>

 <tr><td colspan="4"><input type="button" value="计算" onclick="cacl()"></td></tr>

 </tbody>
 </table>  
</td></tr></table>
 </body>
</html>
