<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
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
 <script type="text/javascript">
	function doSubmit(){
		
	}
 </script>
 
 
  <body>
    <table>
    	<tr>
    		<td>
   				<label>员工工号</label>
   			</td>
   			<td>
   				<label>区域</label>
   			</td>
   			<td>
   				<label>组</label>
   			</td>
   		</tr>
   		<tr>
   			<td>
   				<input type="text" name="uid" id="uid">
   			</td>
   			<td>
   				<select>
   					<option>暖风</option>
   					<option>冷凝</option>
   				</select>
   			</td>
   			<td>
 				<select>
   					<option>区域经理</option>
   					<option>大组长</option>
   				</select>
   			</td>
   			<td>
   				<input type="button" value="ADD">
   			</td>
   			<td>
   				<input type="button" value="Search" >
   			</td>
   		</tr>
    </table>
  </body>
</html>
