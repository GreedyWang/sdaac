<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <base href="<%=basePath%>">
	<script type="text/javascript" src="calc/calendar.js"></script>
	<script type="text/javascript" src="calc/calendar-en.js"></script>
	<script type='text/javascript' src="js/cal.js"></script>
	<link type="text/css" rel="stylesheet" href="calc/calendar-system.css"/>
	<script type='text/javascript' src='/bpp/dwr/interface/TaskManager.js'></script>
  <script type='text/javascript' src='/bpp/dwr/engine.js'></script>
	<script type='text/javascript' src='/bpp/dwr/interface/EmpManager.js'></script>
     <script type='text/javascript' src='/bpp/dwr/engine.js'></script>
	    <script type="text/javascript" src='js/util/wforms.js'></script>
</head>
  <script type="text/javascript">
  var index=0;
  function addTask()
  {
  	var url='${basePath}'+'vave/task/AddTask.jsp?teamWorkID='+'${teamWorkList.workTeamId}'+'&proposalID='+'${teamWorkList.tproposal.id}';
  	alert(url);
	window.open(url, "xxx", "toolbars=0, location=0, statusbars=0, menubars=0,width=700,height=300,scrollbars=1");
  	
  }
  function updateTask(id)
  {
  	alert(id);
  }
  function delTask(id,elem)
  {
  	var key=window.confirm("确定删除");
  	if(key)
  	{  
	  	//var url='task.do?actionType=doDeleteMyTeamTask&teamWorkprojectID='+id;  
	  	//window.location.href=url;
	  	TaskManager.doDelete(id,function(data){});
	  	del(elem)
  	}
  }
  //add row----------------------------------
  //	var index=0
	function Tadd(rowNum)
	{ 
	 //	alert(rowNum)
	  	var tableID=document.getElementById('table1');
	  	var newTr = tableID.insertRow(rowNum+1);
		var newTd0 = newTr.insertCell();	
		var newTd1 = newTr.insertCell();
		var newTd2 = newTr.insertCell();
		var newTd3 = newTr.insertCell();
		var newTd4 = newTr.insertCell();
		var newTd5 = newTr.insertCell();
		var newTd6 = newTr.insertCell();
		var newTd7 = newTr.insertCell();
		var newTd8 = newTr.insertCell();
		var newTd9 = newTr.insertCell();
		//index=rowNum;
		newTd0.className='altbg1';
		newTd1.className='altbg1';
		newTd2.className='altbg1';
		newTd3.className='altbg1';
		newTd4.className='altbg1';
		newTd5.className='altbg1';
		newTd6.className='altbg1';
		newTd7.className='altbg1';
		newTd8.className='altbg1';
		newTd9.className='altbg1';
		
		newTd0.innerHTML= '';
		newTd1.innerHTML= '<select class="required" name="type" >'+
							'<option value="1.设计开发">1.设计开发</option>'+
							'<option value="2.供应商定点">2.供应商定点</option>'+
							'<option value="3.工装制造">3.工装制造</option>'+
							'<option value="4.样件制造">4.样件制造</option>'+
							'<option value="5.试验验证">5.试验验证</option>'+
							'<option value="6.顾客确认">6.顾客确认</option>'+
							'<option value="7.批量验证">7.批量验证</option>'+
							'<option value="8.产品切换">8.产品切换</option>'+
							'</select> ';						  				
		newTd2.innerHTML= '<input class="required" name="jobContent" size="40" /> '; 
		newTd3.innerHTML= '<input class="required" size="10" name="responsiblePerson" onblur="showName('+index+',this)"/>';
		newTd4.innerHTML= '<label size="10" id="name['+index+']" ></label>';
		newTd5.innerHTML= '<input class="required" size="10" name="myTime" id="planTiem" onfocus="calShow('+'planTiem'+')"/>';
		newTd6.innerHTML= '<input  size="10" name="finishTiem" id="finishTiem" onfocus="calShow(finishTiem)"/>';
		newTd7.innerHTML= '<input size="10" name="remarks" id="teamWorkProject['+index+'].remarks" />';
		newTd8.innerHTML= '<img src="images/add.gif" id="image"  onclick="Tadd(this.parentNode.parentNode.rowIndex)"> ';
		newTd9.innerHTML= '<img src="images/del.gif" id="image"  onclick="del(this)"> ';
			index+=1;
	}
	function showName(index,resouce)
	{
			
		var target='name['+index+']';
		var elem=document.getElementById(target);
		
		EmpManager.doSelectById(resouce.value,function(data)
	  	{
	  		if(data!='[]')
	  		{
	  			elem.innerText=data;
	  		
	  		}else
	  		{
	  			elem.innerText='无此员工'
	  			resouce.value=''
	  		}
	  		
	  	});
		 
	}
	//row del-----------------------
	function   del(o)
	{   
	  var t=document.getElementById('table1')   
	  t.deleteRow(o.parentNode.parentNode.rowIndex)   
	} 
  
  </script>
  <body>
   
    <table >
    <tr id="intro_right">
    <td>项目名称：</td>
    <input type="hidden" name="workTeamID" value="${teamWorkList.workTeamId }" >
    <td>${teamWorkList.teamName }</td>
    <td>团队负责人</td>
    <td>${teamWorkList.projectManager.name }</td>
    <td>计划完成时间</td>
    <td>${teamWorkList.planfinishtime }</td>
    </tr>
    </table>
    <table id="table1" >
   
    <thead>
    <tr id="intro_right">
  <!--   <td>类型</td> -->
  	<td>状态</td>
  	<td>类型</td>
    <td>工作内容</td>
    <td>负责人工号</td>
    <td>负责人姓名</td>
    <td>计划日期</td>
    <td>完成日期</td>
    <td>备注</td>
    <td colspan="2"><img src="images/add.gif" id="image"  onclick="Tadd(this.parentNode.parentNode.rowIndex)"></td>
    </tr>
    </thead>
    <tbody>
   
    <c:forEach items="${teamWorkProjectLsit}" var="item">
    <tr>
    <td>${item.grade }</td>
    <td>${item.type } </td>
    <td><label onclick="updateTask(${item.id })">${item.jobContent }</label></td>
    <td>${item.responsiblePerson.uid }</td>
    <td>${item.responsiblePerson.name }</td>
    <td>${item.planTiem }</td>
    <input type="hidden" name="taskID" value="${item.id }">
    <td><input type="text" size="10" id="finishTiem" name="finishTiem" value="${item.finishTiem }"/></td>
    <td>${item.remarks }</td>
  	<td><img src="images/add.gif" id="image"  onclick="Tadd(this.parentNode.parentNode.rowIndex)"></td>
    <td><img src="images/del.gif" onclick="delTask('${item.id }',this)"></td>
    </tr>
    </c:forEach>

    </tbody>
 	</table>
 	<table id="title" >
    <tr id="intro_right">
    <td>估计节约成本(元)</td>

    <td><input type="text" value="${teamWorkList.estimatedCostSavings}" readonly="readonly"></td>
    <td>期望节约成本(元)</td>
    <td><input type="text" name="teamWork.expectedCostSavings" value="${teamWorkList.expectedCostSavings}"></td>
    <td>实际节约成本(元)</td>
    <td><input type="text" name="teamWork.actualCostSavings" value="${teamWorkList.actualCostSavings}"></td>
    </tr>
  	<tr><td>项目总结：</td></tr>
   	<tr><td colspan="6">
   	<textarea name="teamWork.aggregate" rows="3" cols="100%"></textarea>
   	</td></tr>
 	</table>

  </body>
</html>
