<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>岗位管理</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="calc/calendar.js"></script>
	<script type="text/javascript" src="calc/calendar-en.js"></script>
	<link type="text/css" rel="stylesheet" href="calc/calendar-system.css"/>
	<script type='text/javascript' src='/bpp/dwr/interface/EmpManager.js'></script>
    <script type='text/javascript' src='/bpp/dwr/interface/PostManager.js'></script>
    <script type='text/javascript' src='/bpp/dwr/interface/PostIDManager.js'></script>
    <script type='text/javascript' src='/bpp/dwr/interface/PPManager.js'></script>
    <script type='text/javascript' src='/bpp/dwr/engine.js'></script>
    <script type='text/javascript' src='/bpp/dwr/util.js'></script>
    <script type='text/javascript' src="js/cal.js"></script>
    <script type='text/javascript' src="front/js/personalEffectRegister.js"></script>
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
    </head>
	<script type="text/javascript">
		function test(){
			getElementsByClassName2('worktime');
		}
		function test2(){
			getElementsByClassName2('output');
		}
		function $(id){
   			 return document.getElementById(id);
		}
		function getElementsByClassName2(className){
		    //var elems = ($(parentElementID)||document.body).getElementsByTagName("*");
		   var elems=document.getElementsByTagName("input");
		   // var result=[];
		   var temp='blank';
		    for (i=0; j=elems[i]; i++){
		      if ((" "+j.className+" ").indexOf(" "+className+" ")!=-1){
		        if(temp=='blank'){
		        	temp = j.value;
		        }else{
		        	j.value=temp;
		        }
		        
		      }
		    }
		   // return result;
		} 
	</script>
  <body>
	<table>
	
	
	<div id="caclPostID" align="center" style="display: none"  >
	<tr>
	<td><input id="firstName_two"  onblur="selectFitstNameByTwo()" > </td>
	<td>
	<select id="firstName"  onblur="selectMidName()" >
	<option>先选择条件</option>
	</select></td>
	<td><select id="midName" onblur="selectLastName()" ><option>先选择条件</option></select></td>
	<td><select id="lastName" ><option>先选择条件</option></select></td>	
	</tr>
	</div>
	
	
<tr>
 <html:form  action="personal.do?actionType=doInsert" method="post" onsubmit="return checkBlank(index)">
 <input type="hidden" name="menebersUid">
<td style="background-image: url('')" align="center" class="altbg1">组长编号：</td><td align="center" class="altbg1"><input id="masterid" name="masterid" onblur="checkMasterUid()"/> </td>
<td align="center" class="altbg1">组长姓名：</td><td align="center" class="altbg1"><input id="masterName" disabled="disabled"/> </td>
<td align="center" class="altbg1">日期<input onfocus="calShow('time');" id="time" name="time" /> </td>
<td align="center" class="altbg1"><input type="button" value="导入上次信息" onclick="lastInfo()"> </td>
</tr>
   </table>
<table cellspacing="1" cellpadding="4" width="90%"
			class="tableborder" id="table3">
   <thead >
   <tr>
				<td id="head" colspan="8" class="header">员工日产量记录
				</td>
  </tr>
   <tr>
   <td align="center" class="altbg1" ><b>员工编号</b></td>
   <td align="center" class="altbg1"><b>姓名</b></td>
   <td align="center" class="altbg1"  ><b>岗位代码<img src="images/cacl.gif" onclick="cacl()"> </b></td>
   <td align="center" class="altbg1"><b>岗位名称</b></td>
   <td align="center" class="altbg1"><input type="button" value="时间(小时)" onclick="test()">  </td>
   <td align="center" class="altbg1"><input type="button" value="产出" onclick="test2()"> </td>
   <td align="center" class="altbg1"><b><img width="25" src="images/add.gif" id="image"  onclick="Tadd()"></b></td>
   </tr> </thead>
  
  
  <tbody>
 <tr>  
 
	<td align="center" class="altbg1"><input name="items[0].tempolyee.uid" id="items[0].tempolyee.uid" onblur="checkUid(this,0)"/> </td>
  <td align="center" class="altbg1"><input  id="uname[0]" disabled="disabled"/></td>
<td align="center" class="altbg1">
<input size="25" name="items[0].tpost.id" id="items[0].tpost.id" onblur="checkPid(this,0)" onclick="copyData(this)"/></td>
  <td align="center" class="altbg1"><input id="productName[0]" disabled="disabled"/></td>
  <td align="center" class="altbg1"><input name="items[0].worktime" id="items[0].worktime" class="worktime" /></td>
  <td align="center" class="altbg1"><input name="items[0].output" id="items[0].output" class="output" /></td>
  <td align="center" class="altbg1"><img src="images/delete.gif" id="image"  onclick="Tdelete(this)"> </td>
   </tr> 
   <tr><input type="submit" value="登记" /></tr>

</html:form> 

   </tbody>
   </table>
  </body>
</html>
