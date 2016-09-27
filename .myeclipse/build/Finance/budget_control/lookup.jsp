<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
  </head>
  
  <body>
    <table>
    	<tr>
    	<td>版本：</td>
    	<td>
    		<select>
    			<option>version 1</option>
    			<option>version 2</option>
    			<option>version 3</option>
    			<option>version 4</option>
    		</select>
    	</td>
    	<td> <input type="button" value="query"> </td>
    	  <td></td> </tr>
    	<tr>    	
    		<td>Function</td>
    		<td>Budget Version</td>
    		<td>Account</td>
    		<td>Remark</td>
    		<td>ccid</td>
    		<td>Budget Assumption/Remark</td>
    		<td>Jan</td>
    		<td>Feb</td>
    		<td>Mar</td>
    		<td>Apr</td>
    		<td>May</td>
    		<td>Jun</td>
    		<td>Jul</td>
    		<td>Aug</td>
    		<td>Sep</td>
    		<td>Oct</td>
    		<td>Nov</td>
    		<td>Dec</td>
    	</tr>
    	<tr>
    		<td><input type="button" value="导入"> </td>
    	</tr>
    </table>
  </body>
</html>
