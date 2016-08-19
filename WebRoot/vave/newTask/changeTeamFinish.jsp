<%@ page language="java"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/ext-all.js"></script>
	
	
  </head>
  
  <script type="text/javascript">
  	function update(){
  		
  		var	finishTime = document.getElementById("time").value;
  		var	esCostSavints = document.getElementById("money").value;
  		var	workTeamId = document.getElementById("teamId").value;
  		var proposalId = document.getElementById("proposalId").value;
  			Ext.Ajax.request({
								url : 'proposal.do?actionType=doFinish',
								success : function someFn() {
									alert("success");
								},
								failure : function otherFn() {
									alert("failure");
								},
								// headers: {
								// 'my-header': 'foo'
								// },
								params : {
									proposalId : proposalId,
									workTeamId : workTeamId,
									actSaving : esCostSavints,
									finishTime: finishTime
								}
							});
  	}
  </script>
  
  <body>
   <table>
 
   <input id="teamId" type="hidden" value="${param.projectId }">
   <input id="proposalId" type="hidden" value="${param.proposalId}">
  		<tr><td>节约金额</td><td><input type="text" id="money"> </td></tr>
   		<tr><td>完成日期</td><td><input type="text" id="time">  </td></tr>
   		<tr><td><input type="button" value="确定" onclick="update()"> </td></tr>

   </table>
  </body>
</html>
