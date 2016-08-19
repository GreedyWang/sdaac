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
	MyTaskTeam
	<jsp:include page="/vave/VAVE_proposalForm_demo_selfShow.jsp"></jsp:include>
   
   
  <form action="task.do?actionType=doInsertMyTeamTask"  method="post">
    <jsp:include page="/vave/util/VaveTeam.jsp"></jsp:include>
   <table>
   <tr>
   <td><input type="submit" value="添加任务" ></td>
   <td><input type="button" value="产品/成本切换" onclick="doSubmit('${PropsalDetailByID.id}','WAITCHANGE')"></td>
   <td><input type="button" value="终止项目" onclick="doSubmit('${PropsalDetailByID.id}','TERMINA_PROJECT')"></td>
   </tr>
   </table> 
    </form>
	<body><br></body>
</html>
