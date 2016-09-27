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
  	<script type='text/javascript' src='/bpp/dwr/interface/proposalManager.js'></script>
  	<script type='text/javascript' src='/bpp/dwr/engine.js'></script>
  	</head>
	<script type="text/javascript">
	function Tdelete(elem,state)
	{
		//alert(elem);
		//alert(state)
		var key=window.confirm("确定删除");
	  	if(key)
	  	{
			if(state<2)
			{
				proposalManager.doDelete(elem,function(data){
					
				})
				
			}
			else
			{
				alert('只能删除提案状态为个人提案和部门审批')
			}
		}
		document.location.reload();
	}
</script>
  <body >
  Select_Form.jsp

	<tr><jsp:include flush="true" page="Select_TopBar_Form.jsp"></jsp:include></tr>

  
   <table id="title" width="100%">
   
   <thead><tr style="background-color: #eeeeee">
   <td>是否</td>
   <td>状态</td>
   <td>日期</td>
   <td colspan="2">主题</td>
   <td>操作</td>
   </tr></thead>
   <tbody>
   <c:forEach items="${selectVaveSelf}" var="item" >
   <tr>
   <td><img src="img/mail/${item.isOpen }.gif"></td>
  <td>${item.state_context }</td>
   <td>${item.lastModifyTime }</td>
   <td colspan="2"><a href="proposal.do?actionType=doSelectByProposalID_SelfShow&proposalID=${item.id }">${item.title }</a></td>
   <td><img src="images/delete.gif" id="image"  onclick="Tdelete('${item.id }','${item.state }')"></td>
   </tr>
   </c:forEach>
   </tbody>
  
  </body>
</html>
