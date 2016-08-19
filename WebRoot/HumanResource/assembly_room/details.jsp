<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>人事会议室预定-详细信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
	
	<script type="text/javascript">
		function cancel(id) {
			if(window.confirm('确认要取消吗?')) {		
			
						var url = 'roomSystem.do?actionType=doCancel&flag=1&id='+id+'&b='+'${rs.begintime }'+'&e='+'${rs.endtime }'
						window.location.href = url;
						return;
			
			}
		}
		
		function cancels(id) {
			if(window.confirm('确认要取消吗?')) {	
				window.location.href='roomSystem.do?actionType=doCancel&id='+id
						return ;	
				
			}
		}
		
		function submitAgain(id,state) {
			if(window.confirm('确认要提交吗?')) {	
				window.location.href='roomSystem.do?actionType=doApprove&id='+id+'&state='+state
			}
		}
		
		function approve(id,flag) {
			var state,context;
			if(flag == 0) {
				state=-1; 
				context = '确认不批准?';
			}
			if(flag == 1) {
				state=2; 
				context = '确认批准?';
			}
			if(window.confirm(context)) {
				window.location.href='roomSystem.do?actionType=doApprove&id='+id+'&state='+state
			}
		}
	</script>
	
  </head>
  
  <body>
    <table class="tableborder" width = "600">
    	<tr align="center" class="altbg1">
    		<td>登记人：</td><td>${rs.emp.name }</td>
    	</tr>
    	<tr align="center" class="altbg1">
    		<td>部门：</td><td>${rs.emp.tdepartment.name }</td>
    	</tr>
    	
    	<tr align="center" class="altbg1">
    		<td>联系方式：</td><td>${rs.contact }</td>
    	</tr>
    	<tr align="center" class="altbg1">
    		<td>会议概述：</td><td>${rs.context }</td>
    	</tr>
    	<tr align="center" class="altbg1">
    		<td>开始时间：</td><td><fmt:formatDate value="${rs.begintime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    	</tr>
    	<tr align="center" class="altbg1">
    		<td>结束时间：</td><td><fmt:formatDate value="${rs.endtime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    	</tr>
    	<tr align="center" class="altbg1">
    		<td>会议室：</td><td>${rs.roomId.name }</td>
    	</tr>
    	<tr align="center" class="altbg1">
    		<td>状态：</td><td>${rs.cycleTypeName }</td>
    	</tr>
    	<tr align="center" class="altbg1">
    		<td>是否需要投影仪：</td><td>${rs.projectorName }</td>
    	</tr>
    	<tr align="center" class="altbg1">
    		<td>是否需要电话：</td><td>${rs.phoneidName }</td>
    	</tr>
    	<tr align="center" class="altbg1">
    		<td><a href ="${rs.fileName }"> 重要客户申请单</a> </td>
    	</tr>
    	<tr align="center" class="altbg1">
    		<td>会议类型：</td><td>${rs.cycleTypeName }</td>
    	</tr>
    	<tr align="center" class="altbg1">
    		<td>备注：</td><td>${rs.remark }</td>
    	</tr>
    	<c:if test="${flag != 1}">
	    	<tr align="center" class="altbg1">
	    		<td><input type="button" value ="单次取消" onclick=" cancel('${rs.id }')"></td><td><input type="button" value ="全部取消" onclick=" cancels('${rs.id }')"> </td>
	    	</tr>
    	</c:if>
    	<c:if test="${rs.state == -1}">
	    	<tr align="center" class="altbg1">
	    		<td></td><td><input type="button" value ="再次提交" onclick=" submitAgain('${rs.id }',1)"> </td>
	    	</tr>
    	</c:if>
    	<c:if test="${flag == 1}">
	    	<tr align="center" class="altbg1">
	    		<td><input type="button" value ="不批准" onclick=" approve('${rs.id }',0)"></td>
	    		<td><input type="button" value ="批准" onclick=" approve('${rs.id }',1)"> </td>
	    	</tr>
    	</c:if>
    </table>
  </body>
</html>
