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
</script>


  <body >
  VAVE_proposalForm_demo.jsp
  <table id="title">
   
   <tbody>

   <tr id="intro_right"><td rowspan="2" id="title" style="font-size: 20">SDAAC VAVE提案表</td>
   <td>提案来源</td><td>提案编号</td><td>提案状态</td><td>最新修订时间</td></tr>
   <tr id="intro_right">
   <td>${PropsalDetailByID.source }<br></td>              
   <td>
   ${PropsalDetailByID.id}<br>
   </td>
   <td align="middle">${PropsalDetailByID.state}<br></td>
   <td>${PropsalDetailByID.lastModifyTime }</td>
   </tr>
   
   <tr><td>提案人:</td><td>${PropsalDetailByID.proposalPerson.name }<td>电话/部门/公司</td><td>${PropsalDetailByID.proposalPerson.tdepartment.name }</td></tr>
    <tr><td>收集人:</td><td>${PropsalDetailByID.collectionPersion.uid }</td> <td>电话/部门/公司</td><td></td><td></td> </tr>
    
    <tr><td>主   题:</td><td>${PropsalDetailByID.title}</td></tr>
  <tr>
   <td width="20%" >备注:</td>
   <td>${PropsalDetailByID.remarks}</td>
   </tr>
    <tr id="intro_right"><td>零件名称</td><td>零件号</td><td>顾客</td><td>适用车型</td><td>系统年产量</td><td>零件年用量</td><td>单位</td></tr>
   <c:forEach items="${themeList}" var="item">
   <tr><td>${item.partsName}"</td><td>${item.partsId }</td>
    <td>${item.customer }</td><td>${item.applicableModels }</td><td>${item.systemAnnualOutputs }</td><td>${ item.partsAnnualOutputs}</td>
    <td>${item.company }</td></tr>
      </c:forEach>   
     <tr><td>现在方案（可附页）：</tr>
   <tr><td colspan="9">${PropsalDetailByID.curProgram } </td></tr>
    
     <tr><td>代用方案（可附页）：</td></tr>
   <tr><td>${PropsalDetailByID.ins_Program }
   </td></tr>     
 
    <tr><td>建议类别:</td><td> ${PropsalDetailByID.suggestionType }</td>
  
    </tr>
       </tbody>
     </table>
     
   <c:if test="${PropsalDetailByID.state==1}">
   <form action="mainSuggestion.do?actionType=departmentApproval" method="post">
   <input type="hidden" name="mainsug.tproposal.id" value="${PropsalDetailByID.id}">
    <table id="title">
    <tbody>
    <tr>
    <td>本部门意见</td>
    <td>
    <select name="mainsug.suggestionType">
    <option value="推荐实施">推荐实施</option>
    <option value="需完善">需完善</option>
    <option value="不予推荐">不予推荐</option> 
    </select>
    </td>
    <td>主要实施部门</td>
    <td>
    <select name="mainsug.implementationPartment">
    <option value="">产品部</option>
    <option value="">制造部</option>
    <option value="">采购部</option>
    <option value="">物流部</option>
    <option value="">HVAC</option>
    <option value="">PTC</option>
    <option value="">运营部</option>
    <option value="">市场部</option>
    <option value="">财务部</option>
    <option value="">人事部</option>
    <option value="10046,">IT</option>
    </select>
    </td>
    <td><select name="mainsug.implementationPartment">
    <option value="">产品部</option>
    <option value="">制造部</option>
    <option value="">采购部</option>
    <option value="">物流部</option>
    <option value="">HVAC</option>
    <option value="">PTC</option>
    <option value="">运营部</option>
    <option value="">市场部</option>
    <option value="">财务部</option>
    <option value="">人事部</option>
    </select></td>
    <td><input type="submit" value="submit" /></td>
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
      
      
      
    <c:if test="${PropsalDetailByID.state==2}"> 
    <table id="title">
    <tbody>     
    <tr>
    <td>本部门意见</td><td>${mainSug.suggestionType }</td>
    <td>主要实施部门</td>
    <td> ${mainSug.implementationPartment }</td>
    <td>日期</td>
    <td>${mainSug.suggestionDate }</td>
    </tr>
    <tr>
    <td colspan="3">${mainSug.suggest_context }</td>
    </tr>
      
      
      
      
      <!-- xxxxxx -->
    <form action="otherSuggestion.do?actionType=doInsertSuggestion" method="post">
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
    <td><input type="submit" value="submit" /></td></tr>   
    <td colspan="6">
    <textArea name="item.suggest_context" cols="90%"></textArea>
    </td>
    </form>
    </tbody>
    </table>
    </c:if>

	
    <c:if test="${PropsalDetailByID.state==3}"> 
    <table id="title">
    <tbody>
    <tr>
    <td>本部门意见</td><td>${mainSug.suggestionType }</td>
    <td>主要实施部门</td>
    <td>${mainSug.implementationPartment }</td>
    <td>日期</td><td>${mainSug.suggestionDate }</td>
    </tr>   
    <c:forEach items="${OtherSuggestion}" var="otherSug">
    <tr>
    <td>${otherSug.departmentId }</td>
    <td>${otherSug.suggestionType }</td>
    </tr>
    </c:forEach>
   

  	<tr><td colspan="10"><hr></td></tr>
    <tr>
    <td>评审组意见</td>
    <form action="companySuggestion.do?actionType=doInsert" method="post">
    <input name="companySug.tproposal.id" type="hidden" value="${PropsalDetailByID.id}">
    <td><select name="companySug.suggestionType" onchange="show('projectTeam')">
    <option value="不予推荐">不予推荐</option>
    <option value="推荐实施">推荐实施</option>
    </select></td>
    <tr>
    <td colspan="6">
    <textArea name="companySug.suggestionContext" cols="90%"></textArea>
    </td>
    </tr>
  	<tr>
 	<td><input type="submit" value="提交" /></td>
 	</tr>
 	</tbody>
 	</table>
	
	
    <div id="projectTeam" style="display: none">	
   
    <table id="title" >
 	<tr id="intro_right">
    <tr>
    <td>项目团队</td><td>计划完成时间</td><td><input type="text" name="teamWorkplanfinishtime"></td></tr>
   
    <tr id="intro_right">
    <td colspan="5">工作内容</td>
    <td>负责人</td>
    <td>计划日期</td>
    <td>完成日期</td></tr>
    <tr><td colspan="5" ><input type="text" maxlength="60" name="teamWorkProject.jobContent" /></td>
   
    <td><input type="text" name="teamWorkProject.responsiblePerson" /></td>
    <td><input type="text" name="planTiem" /></td>
    <td><input type="text" name="finishTiem" /></td>
    </tr>

    <tr>
    <td>估计节约成本(元)</td>
     <input name="teamWork.tproposal.id" type="hidden" value="${PropsalDetailByID.id}">
    <td><input type="text" name="teamWork.estimatedCostSavings"></td>
    <td>期望节约成本(元)</td>
    <td><input type="text" name="teamWork.expectedCostSavings"></td>
    <td>实际节约成本(元)</td>
    <td><input type="text" name="teamWork.actualCostSavings"></td>
    </tr>
  	<tr><td>项目总结：</td></tr>
   	<tr><td colspan="9">
   	<textarea name="teamWork.aggregate" rows="3" cols="100%"></textarea>
   	</td></tr>
 	</tbody>
 	</table>
 	</div>
		
	</form>	
	</c:if>	
	<!-- --------- -->
		<div id="tip" style="color: #4b04cc" onclick="show('cal_Schedule')">计算附表<img src="images/additional.gif">e</div>
		<div id="cal_Schedule" style="display: none">																									
 <table id="title" >
 <thead><tr id="intro_right">
 <td > 计算附表<br><br><br><br></td><td>周期(D)<br><br><br><br></td><td>进度<br><br><br><br></td>
  <td>一次性费用（元）<br><br><br><br></td><td>项目<br><br><br><br></td><td>数量<br><br><br><br></td><td>单位	<br><br><br><br></td>
  </tr></thead>
 <tbody>
 <tr>
 <td> 设计开发<br><br><br><br></td><td><input type="text" name="" /><br><br><br><br></td>
 <td><input type="text" name="" /><br><br><br><br></td>
  <td><input type="text" name="" /><br><br><br><br></td>
 <td>现在方案成本<br><br><br><br></td><td><input type="text" name="" /><br><br><br><br></td>
 <td><input type="text" name="" /><br><br><br><br></td>
 </tr>
  <tr>
 <td> 工装制造	<br><br><br><br></td><td><input type="text" name="" /><br><br><br><br></td>
 <td><input type="text" name="" /><br><br><br><br></td>
  <td><input type="text" name="" /><br><br><br><br></td>
 <td>代用方案成本<br><br><br><br></td><td><input type="text" name="" /><br><br><br><br></td>
 <td><input type="text" name="" /><br><br><br><br></td>
 </tr>
  <tr>
 <td>  样件制造<br><br><br><br></td><td><input type="text" name="" /><br><br><br><br></td>
 <td><input type="text" name="" /><br><br><br><br></td>
  <td><input type="text" name="" /><br><br><br><br></td>
 <td>年用量合计<br><br><br><br></td><td><input type="text" name="" /><br><br><br><br></td>
 <td><input type="text" name="" /><br><br><br><br></td>
 </tr>
  <tr>
 <td> 试验验证<br><br><br><br></td><td><input type="text" name="" /><br><br><br><br></td>
 <td><input type="text" name="" /><br><br><br><br></td>
  <td><input type="text" name="" /><br><br><br><br></td>
 <td>总节约成本<br><br><br><br></td><td><input type="text" name="" /><br><br><br><br></td>
 <td><input type="text" name="" /><br><br><br><br></td>
 </tr>
  <tr>
 <td>顾客确认<br><br><br><br></td><td><input type="text" name="" /><br><br><br><br></td>
 <td><input type="text" name="" /><br><br><br><br></td>
  <td><input type="text" name="" /><br><br><br><br></td>
 <td>一次性费用<br><br><br><br></td><td><input type="text" name="" /><br><br><br><br></td>
 <td><input type="text" name="" /><br><br><br><br></td>
 </tr>
   <tr>
 <td rowspan="3"> 产品切换<br><br><br><br></td><td><input type="text" name="" /><br><br><br><br></td>
 <td><input type="text" name="" /><br><br><br><br></td>
  <td><input type="text" name="" /><br><br><br><br></td>
  
 <td>净节约成本<br><br><br><br></td><td><input type="text" name="" /><br><br><br><br></td>
 <td><input type="text" name="" /><br><br><br><br></td>
 </tr>
    <tr>
    <td><input type="text" name="" /><br><br><br><br></td>
 <td><input type="text" name="" /><br><br><br><br></td>
  <td><input type="text" name="" /><br><br><br><br></td>

 <td>C/D/S分配比例%<br><br><br><br></td><td><input type="text" name="" /><br><br><br><br></td>
 <td><input type="text" name="" /><br><br><br><br></td>
 </tr>
    <tr>
    <td><input type="text" name="" /><br><br><br><br></td>
 <td><input type="text" name="" /><br><br><br><br></td>
  <td><input type="text" name="" /><br><br><br><br></td>

 <td>SDAAC净节约成本<br><br><br><br></td><td><input type="text" name="" /><br><br><br><br></td>
 <td>ttmt<input type="text" name="" /><br><br><br><br></td>
 </tr>
 </tbody>
</table>  
</div>
  </body>
</html>
