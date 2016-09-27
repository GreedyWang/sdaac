<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <script type="text/javascript" src="calc/calendar.js"></script>
	<script type="text/javascript" src="calc/calendar-en.js"></script>
	<script type='text/javascript' src="js/cal.js"></script>
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
	<link type="text/css" rel="stylesheet" href="calc/calendar-system.css"/>
 	</head>

  <script type="text/javascript">
  function search()
  {
  	var theForm=document.getElementById('search_Form');
  	theForm.submit();
  }
  </script>
  <body >
   	<form action="proposal.do?actionType=doSearchOutside" method="post" id="search_Form">
   <table >
  <tr style="background-color: #eeeeee">
   <td>目前状态</td>
   <td>提案人</td>
   <td>部门</td>
   <td>提案编号</td>
   </tr>
   	<tr>
   	<td><select name="item.state">
   	<option value="-3">请选择</option>
   	<option value="2">外部门审批</option>
   	<option value="3">公司审批</option>
   	<option value="4">实施</option>
   	</select>
   	</td> 
   	<td>
     <input name="item.proposalPerson.name" type="text" value="">
   	</td>
   	<td>
						<select name="item.proposalPerson.tdepartment.id">
							<option value="null">
								请选择
							</option>
							<c:forEach items="${departList}" var="item">
								<option value="${item.id }">
									${item.name }
								</option>
							</c:forEach>
						</select>
					</td>
   	   	<td><input name="item.id" type="text"></td>
   	   	</tr>
   	
   
   <tr style="background-color: #eeeeee">
   <td colspan="2">日期</td> 
   <td >主题</td>
   <td>搜索</td>
   </tr>
  
 
   <tr>
  
   
   	
					
	<td><input type="text"  name="lastModifyTime" id="time" onfocus="calShow('time');" ></td>
   	<td><input type="text"  name="item._selectBeginTimeString" id="time2" onfocus="calShow('time2');" ></td>

   	<td ><input name="item.title" type="text"></td>
   	<td><img src="images/search.gif" onclick="search()"></td>
 
 
   </tr>

   </table>
  	</form>
  
  
  </body>
</html>
