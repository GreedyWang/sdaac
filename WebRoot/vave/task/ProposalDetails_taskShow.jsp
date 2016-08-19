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

	function format()
  	{
  		var ins_programe= document.getElementById('context').innerHTML
    	ins_programe=ins_programe.replace("A","<br>");
 		ins_programe=ins_programe.replace("B","<br>");
 		ins_programe=ins_programe.replace("C","<br>");
 		ins_programe=ins_programe.replace("D","<br>");
 		ins_programe=ins_programe.replace("E","<br>");
		document.getElementById('context').innerHTML=ins_programe
  	}

	function changeJobState(stateValue,index)
	{
		var t1="jobstate["+index+"]"	
		document.getElementById(t1).value=stateValue;
		if(stateValue==1)
		{
			var reason=window.prompt("拒绝理由",'');
			if(reason!=null)
			{
				var t2="jobLogs["+index+"]"	
				document.getElementById(t2).value=reason;
				document.getElementById('jobForm['+index+']').submit();
			}
		}
		if(stateValue==3)
		{
			document.getElementById('jobForm['+index+']').submit();
		}
	}

</script>

ProposalDetails_taskShow.jsp
  <body onload="format()">
	 <table width="100%">
    <tr id="intro_right">
    <td>项目名称：</td>
    <td>团队负责人</td> 
    <td>计划完成时间</td>
    </tr>
    
    <tr>
    <td>${teamWorkList.teamName }</td>
    <td>${teamWorkList.projectManager.name }</td>
    <td>${teamWorkList.planfinishtime }</td>
    </tr>
   
   
    <tr id="intro_right"><td colspan="20">项目成员：</td></tr>
    <tr>
    <c:forEach items="${teamWorkList.meneberNames}" var="item">
    <td>${item }</td>
    </c:forEach>
    </tr>
 	</table>
 	<table>
     <tr id="intro_right">
    <td>零件名称</td>
    <td>零件号</td>
    <td>顾客</td>
    <td>适用车型</td>
    <td>系统年产量</td>
    <td>零件年用量</td>
    <td>单位</td>
    </tr>
   <c:forEach items="${PropsalDetailByID.tthemes}" var="item">
   <tr>
   <td>${item.partsName}</td>
   <td>${item.partsId }</td>
    <td>${item.customer }</td>
    <td>${item.applicableModels }</td>
  <td align="right"><fmt:formatNumber value="${item.systemAnnualOutputs }" pattern="#,#00.0#"/></td>
    <td align="right"><fmt:formatNumber value="${item.partsAnnualOutputs}" pattern="#,#00.0#"></fmt:formatNumber> </td>
    <td>${item.company }</td>
    </tr>
     </c:forEach>   
      </table>
      <table>
	  <tr id="intro_right"><td >现在方案：</tr>
	  <tr><td >${PropsalDetailByID.curProgram }</td></tr>
	  <tr id="intro_right"><td>代用方案：</td></tr> 
	  <tr><td id="context">  
	  ${PropsalDetailByID.ins_Program }
	  </td></tr>   
	  </table>

	<c:if test="${teamWorkProjectLsit!=null}">
	<table id="title" border="1" width="500">
	<tr>   
	<td>状态</td>
  	<td>类型</td>
    <td>工作内容</td>
    <td>负责人姓名</td>
    <td>计划日期</td>
    <td>完成日期</td>
    <td>备注</td>
	</tr>
	<c:forEach items="${teamWorkProjectLsit}" var="item" varStatus="state">
	<tr>
    <td>${item.pstate }</td>
    <td>${item.type } </td>
    <td>${item.jobContent }</td>
    <td>${item.responsiblePerson.name }</td>
    <td>${item.planTiem }</td>
    <td>${item.finishTiem }</td>
    <td>${item.remark }</td>
    <!-- 任务操作 -->
    <form id="jobForm[${state.index }]" action="task.do?actionType=ManangerJob" method="post">
	<input type="hidden" name="teamWorkProject.id" value="${item.id }">
	<input type="hidden" id="jobstate[${state.index }]" name="teamWorkProject.state" value="${item.state }">
    	<c:if test="${item.state==2}">
		    <tr><td><input type="button" value="接受任务" onclick="changeJobState(3,'${state.index }')"></td>
			<td><input type="button" value="拒绝任务" onclick="changeJobState(1,'${state.index }')"></td></tr>

		</c:if>
	
			<tr><td colspan="5">任务日志：
			<textarea id="jobLogs[${state.index }]" name="teamWorkProject.jobLogs" rows="3" cols="100">${item.jobLogs }</textarea></td></tr>
			<tr><td><input type="submit" value="提交"> </td></tr>
		
	</form>
  	</tr>
	</c:forEach>
	</table>
	</c:if>
  </body>
</html>
