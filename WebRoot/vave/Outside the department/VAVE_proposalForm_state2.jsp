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
function tmpSave()
{
	document.getElementById('item.state').innerHTML='1';
	document.getf
}
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
	            //	alert('!')
	            	//alert(roleids[j])
	            	if(roleids[j]=='vavePM')//部门提案
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
</script>


  <body onload="init()">
  VAVE_proposalForm_state2.jsp
 	<jsp:include page="/vave/util/ShowProposalForm.jsp"></jsp:include>
    
	 

    <c:if test="${PropsalDetailByID.state==2}"> 
    <form action="otherSuggestion.do?actionType=doInsertSuggestion" method="post">
	<div id="tip" style="color: #4b04cc">计算附表<label style="color: blue;">（注:建议填写计算附表.）</label><img src="images/additional.gif">
	<jsp:include page="/vave/util/schedule.jsp"></jsp:include></div>
	<table id="commont_table" >
	<tr>
	<td>外部门意见</td>
	<td>
	<select name="item.suggestionType">
	<option value="推荐实施">推荐实施</option>
	<option value="需完善">需完善</option>
    <option value="不予推荐">不予推荐</option> 
    </select>
    </td>
    <td >部门名称: ${logineduser.emp.tdepartment.name }</td>
    <input type="hidden" name="item.tmainSuggestion.id" value="${mainSug.id }"> 
    <input type="hidden" name="item.departmentId" value="${logineduser.emp.tdepartment.id }" >
    <input type="hidden" name="item.tproposal.id" value="${PropsalDetailByID.id}">
    <td><input type="submit" value="提交" /></td></tr>   
    <td colspan="6">
    <textArea name="item.suggest_context" cols="90%"></textArea>
    </td>
 
    </tbody>
    </table>
    </c:if>
    
    
	
  
   </form>
  </body>
</html>
