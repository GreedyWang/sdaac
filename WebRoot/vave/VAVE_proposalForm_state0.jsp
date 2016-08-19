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
    <script type="text/javascript" src='js/postFormRowADD.js'></script>
    <script type="text/javascript" src='js/util/wforms.js'></script>
    <script type='text/javascript' src='/bpp/dwr/engine.js'></script>
   <script type='text/javascript' src='/bpp/dwr/interface/vaveInfoManager.js'></script>
     <script type='text/javascript' src='/bpp/dwr/interface/ThemeManager.js'></script>
    <script type='text/javascript' src='vave/proposal.js'></script>
  </head>
<style type="text/css">
	#black{
		color:black;
	}
</style>

<script type="text/javascript">


function init()
{
	//钩选建议类型
	if('${VaveSchedule[0].project.project}'=='1.单位节约成本')
	{
		document.getElementById('2').checked=true;
	}
	
	var suggestionType='${PropsalDetailByID.suggestionType}'
	var suggestionTypes=suggestionType.split(',')

	for(var i=0;i<suggestionTypes.length;i++)
	{	
		if('  设计更改'==suggestionTypes[i]||'设计更改'==suggestionTypes[i])
		{
			document.getElementById('a').checked=true;
			i++;
		}
		if('  材料更改'==suggestionTypes[i]||'材料更改'==suggestionTypes[i])
		{
			document.getElementById('b').checked=true;	
			i++;
		}
		if('  零件通用'==suggestionTypes[i]||'零件通用'==suggestionTypes[i])
		{
			document.getElementById('c').checked=true;	
			i++;
		}
		if('  工艺更改'==suggestionTypes[i]||'工艺更改'==suggestionTypes[i])
		{
			document.getElementById('d').checked=true;	
			i++;
		}
		if('  包装物流'==suggestionTypes[i]||'包装物流'==suggestionTypes[i])
		{
			document.getElementById('e').checked=true;	
			i++;
		}
		if('  供应商变更'==suggestionTypes[i]||'供应商变更'==suggestionTypes[i])
		{
			document.getElementById('f').checked=true;	
			i++;
		}
		if('  其他'==suggestionTypes[i]||'其他'==suggestionTypes[i])
		{
			document.getElementById('g').checked=true;	
			i++;
		}
	}


	
	
	var element=document.getElementById('source')
	chang_source(element);
	if(element.innerHTML=='供应商')
	{
		var select_supplySimpleName=document.getElementById('supplySimpleName');
		for(var i=0;i<select_supplySimpleName.options.length;i++)
		{		
			if(select_supplySimpleName.options[i].value=='${PropsalDetailByID.supply.simpleName}')
			{
				select_supplySimpleName.options[i].selected='selected';
			}
		}
	}
}

var Alternative_programs =['A 方案描述 :','B 功能说明 :','C 成本说明 :','D 进度说明 :','E 实施建议 :'];

function show()
{
	var elem=document.getElementById('cal_Schedule');
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
	
	if(elem.checked==true)
	{
		var target=document.getElementById('item.ins_Program').innerHTML;
		document.getElementById('item.ins_Program').value+=Alternative_programs[elem.id-1]+'\r\n';
	}
}

function chang_source(element)
{	
	var source_details_div=document.getElementById('source_details');
	//alert(element.value);
	

	if(element.innerHTML=="供应商")
	{		
		source_details_div.style.display='block';
	//	alert('!');
	}
	else
	{
		source_details_div.style.display='none';
	}
}
function formSubmit()
{
document.forms[0].submit();
}

function Tdelete(themeID,elem)
{
	//alert(themeID);
	var key=window.confirm("确定删除");
	  	if(key)
	  	{
	  		ThemeManager.doDeleteByPK(themeID,function(data){
	  			//document.location.reload();
	  			del(elem)
	  		});
	  	}
}
</script>

  <body onload="init()">
  VAVE_proposalForm_state0.jsp
  <table id="title">
    <form action="proposal.do?actionType=doUpdateProposalForm" method="post" onsubmit="return checkFormValidate()">
   <tbody>

   <tr id="intro_right"><td rowspan="2" id="title" style="font-size: 20">SDAAC VAVE提案表</td>
   <td>提案来源</td></tr>
   <tr id="intro_right">
   <td><label id="source">${PropsalDetailByID.source }</label> </td>              
   <input type="hidden" id="item.state" name="item.state" value="1">  
   <input type="hidden" name="item.id" value="${PropsalDetailByID.id}">
    <input type="hidden" name="item.source" value="${PropsalDetailByID.source}">
   <input type="hidden" name="item.proposalPerson.uid" value="${PropsalDetailByID.proposalPerson.uid }">
   </tbody>
   </table>

  <div class="black" id="source_details"  style="display: none" >
   <td>供应商简称</td>
   <td><select id="supplySimpleName" name="item.supply.simpleName">
   <c:forEach items="${supplySimpleList}" var="item">
   	<option value="${item }">${item }</option>
   </c:forEach>
   </select> </td>
   </div>
   
    <table id="title">
   <tbody>
   <tr>
   <td>提案人:</td>
   <td><label id="proposalPersonName">${PropsalDetailByID.proposalPerson.name }</label></td> 
   <td>${PropsalDetailByID.proposalPerson.tdepartment.name }</td>
   </tr>
     <tr>
  
   <td><label id="empDetails"></label></td>
   </tr>
      <tr><td>备注：</td><td><input type="text"  name="item.remarks" value="${PropsalDetailByID.remarks }">  </td>
     <tr><td>主   题(主题选择以应/能一次完成PPAP批准为益):</td>
     <td><input class="required" type="text" size="30" name="item.title" id="proposaltitle" value="${PropsalDetailByID.title }" ></td></tr>
   	<tr><td>类型:</td><td><input type="radio" name="NVtype" id="r1" >直接物料 <input type="radio" name="NVtype" id="r2" >非直接物料</td></tr>
    </tbody>
    </table>
    
 	<table id="table1">
  	 <tbody>
    <tr id="intro_right">
    <td>零件名称</td><td>零件号</td><td>顾客</td><td>适用车型</td><td>系统年产量</td><td>零件年用量</td><td>单位</td><td><img src="images/add.gif" id="image"  onclick="Tadd()"></td>
    </tr>
    <c:forEach items="${PropsalDetailByID.tthemes}" var="item" varStatus="state">
    <input type="hidden" name="themes[${ state.index}].id" value="${item.id }">
    <tr><td><input type="text" size="10" name="themes[${ state.index}].partsName" value="${item.partsName}" /></td>
    <td><input class="required" type="text" size="10" name="themes[${ state.index}].partsId" value="${item.partsId }"/></td>
    <td><input class="required" type="text" size="10" name="themes[${ state.index}].customer" value="${item.customer }"/></td>
    <td><input class="required" type="text" size="10" name="themes[${ state.index}].applicableModels" value="${item.applicableModels }"/></td>
    <td><input class="required" type="text" size="10" name="themes[${ state.index}].systemAnnualOutputs" value="${item.systemAnnualOutputs }"/></td>
    <td><input class="required" type="text" size="10" name="themes[${ state.index}].partsAnnualOutputs" value="${item.partsAnnualOutputs}"/></td>
    <td><input class="required" type="text" size="10" name="themes[${ state.index}].company" value="${item.company }"/></td>
    <td><img src="images/delete.gif" id="image"  onclick="Tdelete('${item.id }',this)"></td>
    </tr>
    </c:forEach>
    
    
    </tbody>
    </table>
    
   	<table >
  	 <tbody>
    <tr><td>现在方案</a></tr>
   <tr><td colspan="6">
   <textarea class="required" name="item.curProgram" id="item.curProgram" rows="5" cols="85%">${PropsalDetailByID.curProgram }</textarea> </td></tr>
   </tbody>
   </table>
   	<table >
  	 <tbody>
     <tr><td>代用方案 ：</td>
     <td><input id="1" type="checkbox" onclick="addContext(this)">A 方案描述 </td>
     <td><input id="2" type="checkbox" onclick="addContext(this)">B 功能说明 </td>
     <td><input id="3" type="checkbox" onclick="addContext(this)">C 成本说明 </td>
     <td><input id="4" type="checkbox" onclick="addContext(this)">D 进度说明 </td>
     <td><input id="5" type="checkbox" onclick="addContext(this)">E 实施建议 </td></tr>
   	 <tr><td colspan="6"><textarea class="required" id="item.ins_Program" name="item.ins_Program" rows="5" cols="85%">${PropsalDetailByID.ins_Program }</textarea> </td></tr>      
    
      </tbody>
    </table>
    
    <div class="required">
   	<table >
  	 <tbody>
    <tr><td>建议类别:</td>
      <td><input id="a" type="checkbox"   name="item.suggestionType" value="0" >设计更改</td>
      <td><input id="b" type="checkbox" name="item.suggestionType" value="1">材料更改 </td>
     <td><input id="c" type="checkbox" name="item.suggestionType" value="2">工艺更改 </td>
     <td><input id="d" type="checkbox" name="item.suggestionType" value="3">零件通用 </td>
     <td><input id="e" type="checkbox" name="item.suggestionType" value="4">物流改进 </td>
     <td><input id="f" type="checkbox" name="item.suggestionType" value="5">供应商变更 </td>
     <td><input id="g" type="checkbox" name="item.suggestionType" value="6">其他 </td>
     </tr>
     </tbody>
     </table>
     </div>
     <table>
    
       </tbody>
     </table>
   	
      <c:if test="${mainSug.suggest_context!=null}"> 
    <table id="title">
   	<tbody>
    <tr>
    <td>本部门意见:</td>
    <td>${mainSug.suggestionType }</td>
    <td>批准人：${mainSug.approved_Name }</td>
    <td>主要实施部门:${mainSug.implement_departmentName }</td>
    <td>日期:${mainSug.suggestionDate }</td>
    </tr>
    <tr>
    <td colspan="3">${mainSug.suggest_context }</td>
    </tr>
    </tbody>
    </table>
    </c:if>
  
     <c:if test="${OtherSuggestion!=null}"> 
    <table id="title" border="1" width="500">
    <tr><hr></tr>
   	<tbody>
  		<c:forEach items="${OtherSuggestion}" var="otherSug">
   	<tr>
   	 <td>外部门审批:</td>
   	</tr>
   	<tr>
   	<td>外部门名称:${otherSug.departmentName }</td>
   	<td>批准人：${otherSug.approveName }</td>
   	<td colspan="2">外部门意见:${otherSug.suggestionType }</td>
   	<td colspan="2">日期:${otherSug.suggestionDate }</td>
   	</tr>
   	<tr>
   		<td colspan="6">评定:${otherSug.suggest_context }</td>
   	</tr>
   	</c:forEach>
	</tbody></table>
	</c:if>	
    
    
    <c:if test="${companySugs!=null}">	
	
     <tr>
    <tr><td id="intro_right" colspan="6">公司审批</td></tr>
 	
     <tr>
     <td colspan="4">评审组意见:${companySugs.suggestionType }</td>
    
    <td colspan="2">日期:${companySugs.suggestionDate }</td>
    </tr>
       
    <tr>
    <td colspan="6">评定:${companySugs.suggestionContext }</td>
    </tr>
       

    </c:if>	
	<div id="tip" style="color: #4b04cc">计算附表<label style="color: blue;">（注:建议填写计算附表.）</label><img src="images/additional.gif">
	<jsp:include page="/vave/util/schedule.jsp"></jsp:include></div>
	
	<tr><td><input type="submit" value="提交" /></td>
    <td><input type="button" value="保存" onclick="tmpSave()"><td>
    </tr>
	</form>	
  </body>
</html>
