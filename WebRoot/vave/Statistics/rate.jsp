<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Participation Rate</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/table.css" rel="stylesheet" type="text/css" />
  </head>

  <body>
  <form action="statistics.do?actionType=rateAcount" method="post">
  <label>年份(格式：yyyy,如 2010 )(不输入年份时为当年数据)</label>	<input name="year" type="text">
  	<input type="submit" value="搜索">
  </form>
  	<label>参与率=(提案人数/总人数);人均批准率=(批准提案数/总人数);提案批准率={批准提案数/提案总数}</label>
 	<table>
 		<tr>
 			<td>部门名称</td>
 			<td>提案人数</td>
 			<td>总人数</td>
 			<td>参与率</td>
 			<td>批准提案数</td>
 			<td>提案总数</td>
 			<td>提案批准率</td>
 			<td>人均批准率</td>
 		</tr>
 		<c:forEach items="${participationRate}" var="item">
 			<tr>
	 			<td align="center">${item[3] }</td>
	 			<td align="right">${item[0] }</td> 			
	 			<td align="right">${item[1] }</td>
	 			<td align="right"><fmt:formatNumber pattern="0.0">${item[2]*100 }</fmt:formatNumber>%</td>
	 			<c:if test="${item[6]<1000}">
	 			<td align="right">${item[6]}</td>
	 			<td align="right">${item[5]}</td>
	 			<td align="right"><fmt:formatNumber pattern="0.0">${item[6]/item[5]*100}</fmt:formatNumber> %</td>
	 			<td align="right"><fmt:formatNumber pattern="0.0">${item[6]/item[1]*100}</fmt:formatNumber></td>
	 			</c:if>
 			</tr>
 		</c:forEach>
 	
 	</table>
 	
 	
 	
 	
 	
	
	
  </body>
</html>
