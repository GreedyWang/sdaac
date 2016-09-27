<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>vave提案单</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <style type="text/css" media="screen">@import url(css/vave.css);</style>
 <script type="text/javascript" src='js/util/wforms.js'></script>
    <script type="text/javascript" src="calc/calendar.js"></script>
	<script type="text/javascript" src="calc/calendar-en.js"></script>
	<script type='text/javascript' src="js/cal.js"></script>
    <script type='text/javascript' src='/bpp/dwr/interface/EmpManager.js'></script>
     <script type='text/javascript' src='/bpp/dwr/engine.js'></script>
	<link type="text/css" rel="stylesheet" href="calc/calendar-system.css"/>
  </head>
<script type="text/javascript">
var Alternative_programs =['A 方案描述 :','B 功能说明 :','C 成本说明 :','D 进度说明 :','E 实施建议 :'];

function init()
{
	var hrMonitor='hrMonitor'
     var hrManager='hrManager'
     var hrRecorder='hrRecorder'
     var ROLE_ADMIN='ROLE_ADMIN'
     var vaveCompany='vaveCompany'
     var vaveDepartEmp='vaveDepartEmp'
     var vavePM='vavePM'
     var vaveCompanyManager='vaveCompanyManager'
	var roleids=${logineduser.emp.roleids_b};
         // alert(roleids.length);
            if(roleids!=null)
            {
	            for(var j=0;j<roleids.length;j++)
	            {
	            //	alert('!')
	            	//alert(roleids[j])
	            	if(roleids[j]=='vaveCompanyManager')//部门提案
		            {
		       //     	alert(document.getElementById('commont_table').style.display)
		            	document.getElementById('commont_table').style.display='block'
		            }
		
		         
	            }
	            
            }
         ddd();
    
}
	function ddd()
  	{
  		var ins_programe= document.getElementById('context').innerHTML
    	ins_programe=ins_programe.replace("A","<br>");
 		ins_programe=ins_programe.replace("B","<br>");
 		ins_programe=ins_programe.replace("C","<br>");
 		ins_programe=ins_programe.replace("D","<br>");
 		ins_programe=ins_programe.replace("E","<br>");
		document.getElementById('context').innerHTML=ins_programe
  	}





var index=0;
function Tadd(dd)
{ 
  	var tableID=document.getElementById('table1');
  	var newTr = tableID.insertRow();
	var newTd0 = newTr.insertCell();	
	var newTd1 = newTr.insertCell();
	var newTd2 = newTr.insertCell();
	var newTd3 = newTr.insertCell();
	var newTd4 = newTr.insertCell();
	index=index+1;
	//newTr.='<form action="personal.do?actionType=doInsert" method="post" onsubmit="return checkBlank()">';
	newTd0.className='altbg1';
	newTd1.className='altbg1';
	newTd2.className='altbg1';
	newTd3.className='altbg1';
	
	newTd0.innerHTML= '<input name="teamWorkProject['+index+'].jobContent" size="40" /> '; 
	newTd1.innerHTML= '<input size="10" name="teamWorkProject['+index+'].responsiblePerson" />';
	newTd2.innerHTML= '<input size="10" name="teamWorkProject['+index+']myTime" id="planTiem" onfocus="calShow('+'planTiem'+')"/>';
	newTd3.innerHTML= '<input size="10" name="finishTiem" id="finishTiem" onfocus="calShow(finishTiem)"/>';
	newTd4.innerHTML= '<img src="images/delete.gif" id="image"  onclick="del(this)"> ';
}

  function   del(o){   
  var t=document.getElementById('table1')   
  t.deleteRow(o.parentNode.parentNode.rowIndex)   
  } 


function show(id)
{

	var elem=document.getElementById(id);
	var contex=document.getElementById('aa');
	//alert(contex.value)
	
	if(contex.value=='推荐实施')
	{
		elem.style.display='block';
		//add info
		
		document.getElementById('teamWorkName').value='${PropsalDetailByID.title}'
		document.getElementById('projectManagerId').value='${PropsalDetailByID.collectionPersion.uid }'
		document.getElementById('uname[0]').value='${PropsalDetailByID.collectionPersion.name }'
		//document.getElementById('estimatedCostSavings').value='${proposalMore.expectSaving}'
		document.getElementById('estimatedCostSavings').value=document.getElementById('estimatedSaving').value
		document.getElementById('Planfinishtime').value='${proposalMore.expectFinishDate}'
		return ;
	}
	else
	{
		
		elem.style.display='none';
		return ;
	}

}

function addContext(value)
{
	var elem=value;
	suggestions.innerHTML=suggestions.innerHTML+'&nbsp'+Alternative_programs[elem.id-1];
}

function formSubmit()
{
	document.getElementById('estimatedCostSavings').value=document.getElementById('estimatedSaving').value
	var theForm=document.getElementById('cForm')
	theForm.submit();
}

function checkUid(elem,index)
{
 
  var uid=elem.value;
  	// =document.getElementById('item.tempolyee.uid');?why can't get the value???!!
  
  	EmpManager.doSelectById(uid,function(data)
  	{
  	
  		document.getElementById('uname['+index+']').value=data;
  	});
  }
</script>
VAVE_proposalForm_state3.jsp

  <body onload="init()">
 	<jsp:include page="/vave/util/ShowProposalForm.jsp"></jsp:include>	
	<c:if test="${PropsalDetailByID.state==3}"> 
  	
    <form id="cForm" action="companySuggestion.do?actionType=doInsert" method="post" >
   
   <div id="tip" style="color: #4b04cc">计算附表 <label style="color: blue;">（注：公司批准时,必须填写计算附表.）</label><img src="images/additional.gif">
	<jsp:include page="/vave/util/schedule.jsp"></jsp:include></div> 
    
    <table id="commont_table" >
    <tbody>
    <tr>
    <td>评审组意见</td>
   <input name="implements" value="${mainSug.implementationPartment }" type="hidden" >
    <input name="companySug.tproposal.id" type="hidden" value="${PropsalDetailByID.id}">
    <td>
    <select id="aa" name="companySug.suggestionType" onchange="show('projectTeam')">
    <option value="不予推荐">不予批准</option>
    <option value="需完善">需完善</option>
    <option value="推荐实施">批准实施</option>
    <option value="合理化建议">合理化建议</option>
    </select></td>
    <tr>
    <td colspan="6">
    <textArea name="companySug.suggestionContext" cols="90%"></textArea>
    </td>
    </tr>
  	<tr>
 	<td><input type="button" value="提交" onclick="formSubmit()"/></td>
 	</tr>
 	</tbody>
 	</table>
	
	
    <div id="projectTeam" style="display: none">	
    <table id="title" >
    <tr id="intro_right">
    <td>项目名称：</td>
    <td><input type="text" id="teamWorkName" name="teamWork.teamName"></td>
    <td>团队负责人(工号):</td>
    <td><input type="text" id="projectManagerId" name="teamWork.projectManager.uid"  onblur=" checkUid(this,0)"></td>
    <td><input id="uname[0]" disabled="disabled"/></td>
    <td>计划完成时间</td>
    <td><input type="text" id="Planfinishtime" name="teamWork.myPlanfinishtime" onfocus="calShow('Planfinishtime')"></td>
    </tr>
    </table>
   
 	<table width="100%" >
    <tr id="intro_right">
    <td>估计节约成本(元)</td>
     <input name="teamWork.tproposal.id" type="hidden" value="${PropsalDetailByID.id}">
    <td><input type="text" id="estimatedCostSavings" name="teamWork.estimatedCostSavings"></td>
    <td>期望节约成本(元)</td>
    <td><input type="text" name="teamWork.expectedCostSavings"></td>
    <td>实际节约成本(元)</td>
    <td><input type="text" name="teamWork.actualCostSavings"></td>
    </tr>
  	<tr><td colspan="2">项目备注：</td></tr>
   	<tr><td colspan="6">
   	<textarea name="teamWork.aggregate" rows="3" cols="100%"></textarea>
   	</td></tr>
 	</table>
 	</div>
		

	</c:if>	
	
	
	</form>	
  </body>
</html>
