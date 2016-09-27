<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'createProject.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css" rel="stylesheet" href="css/vave.css">
	<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
	<link rel="stylesheet" type="text/css" href="style/button.css">
	<link rel="stylesheet" type="text/css" href="style/icon.css">	
	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/ext-all.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/button.js"></script>
	<script type="text/javascript" src="vave/newTask/addTask.js"></script>
	<script type='text/javascript' src='/bpp/dwr/engine.js'></script>
	<script type='text/javascript' src='/bpp/dwr/interface/TaskManager.js'></script>
	<script type='text/javascript' src='/bpp/dwr/interface/projectManager.js'></script>
	<script type='text/javascript' src='/bpp/dwr/interface/EmpManager.js'></script>
	<script type='text/javascript' src='vave/newTask/createProject.js'></script>
	<style type="text/css">
		.jobstate0{
			background-color: #fa1223;
		}
		.jobstate1{
			background-color: #fafa12;
		}
		.jobstate4{
			background-color: #12fa21;
		}
	</style>
  </head>
  <script type="text/javascript">
  	function tmpSave()
	{
		document.forms[0].submit();
	}
	function doSubmit(proposalID,state)
	{
		window.location.href='proposal.do?actionType=changeState&proposalID='+proposalID+'&state='+state;
	}
  </script>

  <body>
    <table>
  <tr>
  <td>
  <input type="button" icon="icon-forum" value="添加成员" onclick="addEmps('${teamWorkList.workTeamId }')"> </td>
   <td><input type="button" icon="icon-add" value="产品/成本切换" onclick="doSubmit('${PropsalDetailByID.id}','WAITCHANGE')"></td>
    <td><input type="button" icon="icon-success" value="财务确认" onclick="projectFinish('${PropsalDetailByID.id}','${teamWorkList.workTeamId }')"></td>
   <td><input type="button" icon="icon-success" value="完成" onclick="finish('${PropsalDetailByID.id}','${teamWorkList.workTeamId }')"></td>
   <td><input type="button" icon="icon-error" value="终止项目" onclick="doSubmit('${PropsalDetailByID.id}','TERMINA_PROJECT')"></td>
   <td><input type="button" icon="project-leader" value="更变项目负责人" onclick="updateManager('${PropsalDetailByID.id}')"></td>
  
  </tr>  
  <tr>
  	<td><input type="button" icon="icon-edit" value="更变项目计划完成日期/节约金额" onclick="changeTeamPlan('${teamWorkList.workTeamId}')"></td>
  	 <td><input type="button" icon="icon-grid" value="查看原始提案" onclick="showProposal('${PropsalDetailByID.id}')"></td>
  	
  </tr>
  </table>
  <div id="emps" ></div>
  <div id="emps2" ></div>
  <div id="emps3" ></div>
    <table width="100%">
    <tr id="intro_right">
    <td>项目名称：</td>
    <td>团队负责人</td> 
    <td>计划完成时间</td>
    </tr>
    
    <tr>
    <td>${teamWorkList.teamName }</td>
    <td><label id="projectManagerName"> ${teamWorkList.projectManager.name }</label></td>
    <td>${teamWorkList.planfinishtime }</td>
    </tr>
   
   
    <tr id="intro_right"><td colspan="20" id="meneberNames">项目成员：
      <c:forEach items="${teamWorkList.meneberNames}" var="item">
    ${item },
    </c:forEach>
    </td></tr>
 
  
  
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
  
    <!--   任务列表 -->
    <table id="table1" width="100%">
  <tr><td>任务列表</td></tr>
  <form id="1" action="task.do?actionType=doInsertMyTeamTask"  method="post">
   <input type="hidden" name="workTeamID" value="${teamWorkList.workTeamId }" >
   <input type="hidden" name="proposalId" value="${PropsalDetailByID.id}">
   <tr id="intro_right">
  	<td>状态</td>
  	<td>类型</td>
    <td>工作内容</td>
    <td>负责人工号</td>
    <td>负责人姓名</td>
    <td>计划日期(yyyy-mm-dd)</td>
    <td>完成日期(yyyy-mm-dd)</td>
    <td>备注</td>
    <td colspan="2"><img src="images/add.gif" id="image"  onclick="Tadd(this.parentNode.parentNode.rowIndex)"></td>
    </tr>
    </thead>
    <tbody>
   
    <c:forEach items="${teamWorkProjectLsit}" var="item">
    	
   	<tr class="jobstate${item.state }">
    <td>${item.pstate }</td>
    <td>${item.type } </td>
    <td><label onclick="updateTask(${item.id })">${item.jobContent }</label></td>
	 <td>${item.responsiblePerson.uid }</td>
     <td>${item.responsiblePerson.name }</td>
    <td>${item.planTiem }</td>
    <input type="hidden" name="taskID" value="${item.id }">
    <td><input type="text" size="5" id="finishTiem" name="finishTiem" value="${item.finishTiem }"/></td>
    <td>
    <input type="text" size="10"  name="remarks" value="${item.remark }"/>
    </td>
	
    <td><img src="images/del.gif" onclick="delTask('${item.id }',this)"></td>
    </tr>
    <tr><td>任务日志</td><td colspan="7"><textarea cols="80">${item.jobLogs }</textarea> </td></tr>
    </c:forEach>
   <tr> <td><input type="submit" value="添加任务" ></td></tr>
	</form>
  </table>
 
  <table>
  <!-- 项目跟踪 -->
  <form id="2" action="task.do?actionType=ManangerProject" method="post">
  <input type="hidden" name="teamWork.workTeamId" id="workTeamId" value="${teamWorkList.workTeamId }" >
  <tr id="intro_right"><td>项目进度</td></tr>
  <tr><td><textarea name="teamWork.projectProcess" rows="3" cols="80">${teamWorkList.projectProcess }</textarea> </td></tr>
  <tr id="intro_right"><td>项目日志</td></tr>
   <tr><td><textarea name="teamWork.projectLogs" rows="3" cols="80">${teamWorkList.projectLogs }</textarea> </td></tr>
   <tr id="intro_right"><td>vave会议安排</td></tr>
   <tr><td><textarea name="teamWork.vaveDiscuss" rows="3" cols="80">${teamWorkList.vaveDiscuss }</textarea> </td></tr>
   <tr><td><input type="submit" value="提交"></td></tr>
  	</form>
    </tbody>
    </table>
   
  </body>
</html>
