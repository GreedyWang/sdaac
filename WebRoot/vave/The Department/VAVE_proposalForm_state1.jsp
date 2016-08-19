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
        var vaveCompanyManager='vaveCompanyManager'
     var vavePM='vavePM'
	var roleids=${logineduser.emp.roleids_b};
         // alert(roleids.length);
            if(roleids!=null)
            {
	            for(var j=0;j<roleids.length;j++)
	            {
	            	if(roleids[j]=='vavePM')//部门提案
		            {

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
function show(id)
{
	var elem=document.getElementById(id);
	if(elem.style.display=='block')
	{
		elem.style.display='none';
		return ;
	}
	if(elem.style.display=='none')
	{
		elem.style.display='block';
		return ;
	}
}

function addContext(value)
{
	var elem=value;
	suggestions.innerHTML=suggestions.innerHTML+'&nbsp'+Alternative_programs[elem.id-1];
}

function checkFormBlank()
{
	var expectSaving=document.getElementById('estimatedSaving');
	var expectFinishTime=document.getElementById('expectFinishTime');
	var ins_program=document.getElementById('ins_program');
	var implementationPartment=document.getElementById('implementationPartment').value;
	var implementationPartment2= document.getElementById('implementationPartment2').value;
	var department_suggestion=document.getElementById('department_suggestion').value;
	
	var d1=document.getElementById('implementationPartment')//外部门1 
	var d2= document.getElementById('implementationPartment2');//外部门2
	var implement_departmentName=document.getElementById('implement_departmentName')//隐藏外部门名
	
	if(department_suggestion=='推荐实施')
	{	
		implement_departmentName.value=''
		
		if(implementationPartment=="")
		{
			alert('请选择外部门');
			return false;
		}
		
		//alert(department_suggestion);
		if(implementationPartment=='${logineduser.emp.tdepartment.id},')
		{
			
			alert('请选择其他部门');
			return false;
		}
		else
		{	
			var departName=d1.options[d1.selectedIndex].text
			implement_departmentName.value+=departName+','
		}
		if(implementationPartment2=='${logineduser.emp.tdepartment.id},')
		{
			alert('请选择其他部门');
			return false;
		}
		if(implementationPartment2!='${logineduser.emp.tdepartment.id},'&&implementationPartment2!='')
		{
			var departName=d2.options[d2.selectedIndex].text
			implement_departmentName.value+=departName+','
		}
	}
	
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
	//alert(implement_departmentName.value)
	return true;
}
</script>


  <body onload="init()">
  <label onclick="checkFormBlank()">VAVE_proposalForm_state1.jsp</label>
  <jsp:include page="/vave/util/ShowProposalForm.jsp"></jsp:include>
   
   
    	
  
   
	 <c:if test="${PropsalDetailByID.state==1}">
   <form action="mainSuggestion.do?actionType=departmentApproval" method="post" onsubmit="return checkFormBlank()" >

    <div id="tip" style="color: #4b04cc">计算附表<label style="color: blue;">（注:建议填写计算附表.）</label><img src="images/additional.gif">
	<jsp:include page="/vave/util/schedule.jsp"></jsp:include></div>
   <input type="hidden" name="mainsug.tproposal.id" value="${PropsalDetailByID.id}">
  
    <table id="commont_table" width="800">
    <tbody>
    <tr>
    <td>本部门意见</td>
    <td>
    <select id="department_suggestion" name="mainsug.suggestionType">
    <option value="推荐实施">推荐实施</option>
    <option value="需完善">需完善</option>
    <option value="不予推荐">不予推荐</option> 
    <option value="合理化建议">合理化建议</option>
    </select>
    </td>
    <td>主要实施部门</td>
    <td>
    <input name="mainsug.approved_Name" type="hidden" value="${logineduser.emp.name }">
    <input name="mainsug.implement_departmentName" type="hidden" id="implement_departmentName"> 
    <select id="implementationPartment" name="mainsug.implementationPartment">
    <option value="">选择外部门</option>
    <option value="10040,">产品部</option>
    <option value="10044,">制造部</option>
    <option value="10048,">采购部</option>
    <option value="10025,">物流部</option>
    <option value="10038,">HVAC</option>
    <option value="10039,">PTC</option>
    <option value="10026,">运营部</option>
    <option value="10049,">市场部</option>
    <option value="10047,">财务部</option>
    <option value="10030,">人事部</option>
    <option value="10046,">IT</option>
    </select>
    </td>
    <td><select id="implementationPartment2" name="mainsug.implementationPartment">
    <option value="">选择外部门</option>
    <option value="10040,">产品部</option>
    <option value="10044,">制造部</option>
    <option value="10048,">采购部</option>
    <option value="10025,">物流部</option>
    <option value="10038,">HVAC</option>
    <option value="10039,">PTC</option>
    <option value="10026,">运营部</option>
    <option value="10049,">市场部</option>
    <option value="10047,">财务部</option>
    <option value="10030,">人事部</option>
    <option value="10046,">IT</option>
    </select></td>
    <td><input type="submit" value="提交" /></td>
    </tr>
    <tr>
    <td colspan="6">
    <textArea name="mainsug.suggest_context" cols="90%"></textArea>
    </td>
    </tr>
    </tbody>
    </table>
    </c:if>    
	 </form>   
  </body>
</html>
