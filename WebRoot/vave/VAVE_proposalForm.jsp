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
    
    <title>vave提案单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <style type="text/css" media="screen">@import url(css/vave.css);</style>	
  	<script type='text/javascript' src='/bpp/dwr/interface/EmpManager.js'></script>
    <script type='text/javascript' src='/bpp/dwr/engine.js'></script>
    <script type="text/javascript" src='js/postFormRowADD.js'></script>
    <script type="text/javascript" src='js/util/wforms.js'></script>
   <script type='text/javascript' src='/bpp/dwr/interface/vaveInfoManager.js'></script>
   <script type='text/javascript' src='vave/proposal.js'></script>
  </head>
<script type="text/javascript">
function getDetails(elem)
{
	var empID=elem.value;
	EmpManager.doSelectGetEmpDetails(empID,function(data){
		if(data!=null)
		{
			document.getElementById('proposalPersonName').innerHTML=data.name+','+data.tdepartment.name ;	
		}
		else
		{
			document.getElementById('proposalPersonName').innerHTML='无此人';
		}
	});
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
	
	if(element.value=="供应商")
	{		
		source_details_div.style.display='block';
		
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
function toOthers()
{
	parent.parent.location.href="Login.jsp";
}
function checkTeam()
{
	var div_showTeam=document.getElementById('checkTeam')
	if(div_showTeam.style.display=='block')
	{
		div_showTeam.style.display='none'
		document.getElementById('proposalPersonUid').value='${logineduser.emp.uid }'
		document.getElementById('proposalPersonName').innerText='${logineduser.emp.name}'
	}
	else
	{
		div_showTeam.style.display='block'
	}
}
function getTeamName()
{
	var select_teamName=document.getElementById('teamName');
	for(var i=0 ;i<select_teamName.options.length;i++)
	{
		if(select_teamName.options[i].value==select_teamName.value)
		{
		
			document.getElementById('collectionPersion').value=select_teamName.value
			document.getElementById('proposalPersonName').innerText=select_teamName.options[i].innerText
		}
	}
	
}
function init(){
	
	for(var i=1;i<6;i++)
	{
		var elem=document.getElementById(i)
		elem.checked=true;
		addContext(elem)
	}
}

</script>


  <body onload="init()"> 
  

   <table id="title">
   <form action="proposal.do?actionType=doInsertProposalForm" method="post" onsubmit="return checkFormValidate()" >
   <tbody>

   <tr id="intro_right"><td rowspan="2" id="title" style="font-size: 20">SDAAC VAVE提案表</td>
   <td>提案来源</td></tr>
 
   <tr id="intro_right">
   <td><select name="item.source" onchange="chang_source(this)">
    <option value="公司" >公司</option>
    <option value="供应商">供应商</option>
    </select></td>              
   
   <input type="hidden" id="item.state" name="item.state" value="1">  
  
   
  
   </tbody>
   </table>
      <div class="normal" id="source_details"  style="display: none" >
   <td>供应商简称</td>
   <td><select name="item.supply.simpleName">
   <c:forEach items="${supplySimpleList}" var="item">
   	<option value="${item }">${item }</option>
   </c:forEach>
   </select> </td>
   </div>
   
  
  <input type="hidden" id="collectionPersion" name="item.collectionPersion.uid" value="${logineduser.emp.uid }" />
    
    <table id="title">
   <tbody>
     <tr>
  
   <td><label id="empDetails"></label></td>
   </tr>
   <tr>
   <td><input type="checkbox" id="checkbox" onclick="checkTeam()">选择团队提案 </td>
  	<td id="checkTeam" style="display: none">
  	<select id="teamName" onchange="getTeamName()">
   	<option value="">请选择团队</option>
   	<option value="1">VAVE团队</option>
    <option value="2">VAVE采购部</option>
    <option value="3">VAVE产品工程部</option>
    <option value="4">VAVE制造工程部</option>
    <option value="5">VAVE客满部</option>
    <option value="6">VAVE物流部</option>
	<option value="7">VAVE HVAC</option>
    <option value="8">VAVE PTC</option>
   </select> </td>
   </tr>
    <tr><td>提案人工号：</td><td><input type="text" id="proposalPersonUid" name="item.proposalPerson.uid" value="${logineduser.emp.uid }" onchange="getDetails(this)"/>  </td>
     <td>提案人姓名：<label id="proposalPersonName">${logineduser.emp.name }, ${logineduser.emp.tdepartment.name }</label></td>
   </tr>
   <tr><td>备注：</td><td><input type="text"  name="item.remarks" >  </td>
   </tr>
    <tr><td>主   题(主题选择以应/能一次完成PPAP批准为益):</td><td><input class="required" id="proposaltitle" type="text" size="50" name="item.title"  ></td></tr>
     <tr><td>类型:</td><td><input type="radio" name="NVtype" id="r1" value="直接物料">直接物料 <input type="radio" name="NVtype" id="r2" value="非直接物料">非直接物料</td></tr>
    </tbody>
    </table>
    
 	<table id="table1">
  	 <tbody>
    <tr id="intro_right">
    <td>零件名称</td><td>零件号</td><td>顾客
    </td><td>适用车型</td><td>系统年产量</td><td>零件年用量</td><td>单位</td><td><img src="images/add.gif" id="image"  onclick="Tadd()"></td>
    </tr>
    <tr><td><input class="required" type="text" size="10"  name="themes[0].partsName" /></td>
    <td><input class="required" type="text" size="10" id="themes[0].partsId" name="themes[0].partsId" /></td>
    <td>
    	<select class="required" id="themes[0].customer" name="themes[0].customer" onblur="showOthers(0)"  onfocus="getInfo(0)"></select>
    </td>
    <td>
    	<select class="required" id="select[0]"  name="themes[0].applicableModels" onblur="show2(0)">
    	</select>
    </td>
    <td><input class="required" type="text" style="text-align: right" size="10" id="themes[0].systemAnnualOutputs" name="themes[0].systemAnnualOutputs" /></td>
    <td> <input class="required" style="text-align: right" type="text" size="10" id="themes[0].partsAnnualOutputs" name="themes[0].partsAnnualOutputs" /></td>
    <td>
    	<select class="required"  name="themes[0].company">
    		<option selected="selected" value="件/台/套">件/台/套</option>
    		<option value="kg">kg</option>
    	</select>
    </td></tr>
    </tbody>
    </table>
    
   	<table >
  	 <tbody>
    <tr><td>现在方案</td></tr>
   <tr><td colspan="6">
   <textarea class="required"  name="item.curProgram" id="item.curProgram" rows="5" cols="85%" ></textarea></td></tr>
   </tbody>
   </table>
   	<table >
  	 <tbody>
     <tr><td>代用方案</td>
  
     <td><input id="1" type="checkbox" onclick="addContext(this)">A 方案描述 </td>
     <td><input id="2" type="checkbox" onclick="addContext(this)">B 功能说明 </td>
     <td><input id="3" type="checkbox" onclick="addContext(this)">C 成本说明 </td>
     <td><input id="4" type="checkbox" onclick="addContext(this)">D 进度说明 </td>
     <td><input id="5" type="checkbox" onclick="addContext(this)">E 实施建议 </td></tr>
 
   	 <tr><td colspan="6">
   	 <textarea class="required" id="item.ins_Program" name="item.ins_Program" rows="5" cols="85%"></textarea>
   	 </td></tr>      
      </tbody>
    </table>
    
	 <div class="required">
  	<table > 	
    <tr>
    <td>建议类别:</td>  
      <td><input type="checkbox" name="item.suggestionType" value="0">设计更改 </td>
     <td> <input type="checkbox" name="item.suggestionType" value="1">材料更改 </td>
     <td><input type="checkbox" name="item.suggestionType" value="2">工艺更改 </td>
     <td><input type="checkbox" name="item.suggestionType" value="3">零件通用 </td>
     <td><input type="checkbox" name="item.suggestionType" value="4">包装物流 </td>
     <td><input type="checkbox" name="item.suggestionType" value="5">供应商变更 </td>
     <td><input type="checkbox" name="item.suggestionType" value="6">其他 </td> 
     </tr>   
    </table>
       </div>
    <div id="tip" style="color: #4b04cc" >计算附表<label style="color: blue;">（注:建议填写计算附表.）</label><img src="images/additional.gif"></div>
																								
 			<jsp:include page="/vave/util/Insertschedule.jsp"></jsp:include>  
    <table>
    <tr><td><input type="submit" value="提交" /></td>
    <td> <input type="button" value="保存" onclick="tmpSave()"></td>
    </tr>
   </table>
  
</form>	
  </body>
</html>
