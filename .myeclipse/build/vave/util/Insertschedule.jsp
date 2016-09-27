<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
  
  function calc(){
  	var ts = document.getElementsByName('imvalue');
  	
  	var rs=0;
  	for(var i = 0 ;i<ts.length ;i++ ){	
  		if(ts[i].value != ''){	
  			rs = rs + parseFloat(ts[i].value);
  		}else {
  			ts[i].value = 0;
  		}
  	}
  
  	document.getElementById('estimatedSaving').value=rs;
  
  }
  </script>
  <body>
    <table>																						
		<tr>
		<td>
		预计节约成本(元)</td>
		<td><input type="text" id="estimatedSaving" name="expectSaving" class="required" ></td>
		<td>预计完成日期</td>
	    <td><input type="text" id="expectFinishTime" name="expectFinishDate" class="required" onfocus="calShow('expectFinishTime');"></td>
		</tr>
	</table>
	
  <table>
  	<tr>
  		<td vAlign="top"><table vAlign="top" id="tableNV">
  		  		 <thead>
 <tr id="intro_right" vAlign="top">
 <td>分类<br><br></td>
 <td>金额<br><br></td>
  <td><img src="images/add.gif" id="image"  onclick="TaddNV()">
  	<input type="button" onclick="calc()" value="计算">
  </td>
 </tr>
 </thead>
 <tr>
 	<td>物流运输费用节约</td><td><input type="text" name="imvalue"> </td> </tr>
  <tr>	<td>包装费用节约</td><td><input type="text" name="imvalue"></td> </tr>
  <tr>	<td>仓库租赁费用节约</td><td><input type="text" name="imvalue"></td> </tr>
 	<td>库存成本节约</td><td><input type="text" name="imvalue"></td> </tr>
  <tr>	<td>水电等能源费用节约</td><td><input type="text" name="imvalue"></td> </tr>
  <tr>	<td>辅料费用节约</td><td><input type="text" name="imvalue"></td> </tr>

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

 <td>
 <input type="radio" id="1"  name="a" onclick="checkXX()" checked="checked">现在方案成本
 <input type="radio" id="2" name="a" onclick="checkXY()">节约成本
 <input type="hidden" name="project" id="asd" value="1.现在方案成本"/><br><br></td>
 <td><input type="text" size="5" id="cur_cost" style="text-align: right" name="quantity" /><br><br></td>
 <td><input type="text" size="5" name="unit" /><br><br></td>
 </tr>

 <tr>

 <td>2.代用方案成本
 <input type="hidden" name="project" value="2.代用方案成本"/><br><br></td>
 <td><input type="text" size="5" id="ins_cost"  style="text-align: right" name="quantity" value="0"/><br><br></td>
 <td><input type="text" size="5" name="unit" /><br><br></td>
 </tr>
 

 <tr>

 <td>3.年用量合计<input type="hidden" name="project" value="3.年用量合计"/><br><br></td>
 <td><input type="text" size="5" id="Annual_quantity" style="text-align: right" name="quantity" value="0"/><br><br></td>
 <td><input type="text" size="5" name="unit" /><br><br></td>
 </tr>
 <tr>

 <td>4.总节约成本<input type="hidden" name="project" value="4.总节约成本"/><br><br></td>
 <td><input type="text" size="5" id="total_saving" style="text-align: right;background-color: gray;" name="quantity" readonly="readonly"/><br><br></td>
 <td>元<br><br></td>
 <input type="hidden" size="5" name="unit" value="元"/>
 </tr>
 <tr>

 <td>5.一次性费用<input type="hidden" name="project" value="5.一次性费用"/><br><br></td>
 <td><input type="text" size="5" id="total_onceTimeCosts" style="text-align: right;background-color: gray;" name="quantity" readonly="readonly"/><br><br></td>
 <td>元<br><br></td>
 <input type="hidden" size="5" name="unit" value="元"/>
 </tr>
 <tr>

 <td>6.净节约成本<input type="hidden" name="project" value="6.净节约成本"/><br><br></td>
 <td><input type="text" size="5" id="net_SavingCost" name="quantity" style="text-align: right;background-color: gray;" readonly="readonly"/><br><br></td>
 <td>元<br><br></td>
 <input type="hidden" size="5" name="unit" value="元"/>
 </tr>
 <tr>

 <td>7.C/D/S分配比例%<input type="hidden" name="project" value="7.C/D/S分配比例%"/>
 <br>如30/50/20
 </td>
 <td><input type="text" size="5" id="distribution_Program" style="text-align: right" name="quantity" /><br><br></td>
 <td>%</td>
 <input type="hidden" size="5" name="unit" value="%"/>
 </tr>
 <tr>


 <td>8.SDAAC净节约成本<input type="hidden" name="project" value="8.SDAAC净节约成本"/><br><br></td>
 <td><input type="text" size="5" id="sdaacnet_SavingCost" name="quantity" style="text-align: right;background-color: gray;" readonly="readonly"/></td>
 <td>元</td>
 <input type="hidden" size="5" name="unit" value="元"/>
 </tr>
<tr><td colspan="4"><input type="button" value="计算" onclick="cacl()"> </td></tr>
 </tbody>
 </table></td>
  	</tr>
  </table>

 

 </body>
</html>
