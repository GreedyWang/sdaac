<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <script type="text/javascript">
  	function check(){
  		var sel = document.getElementById('type'); 		
  			if(document.getElementById('file2').value==''){
  				alert('请上传发票参照号');
  				return false;
  			}else{
  				var flag = window.confirm('你选择的客户是'+sel.value+"？")
  				if(flag){
  					return true;
  				}else{
  					return false;
  				}
  			}
  		
  	}
  </script>
  
  <body>
  	<form action="cw.do?actionType=show" method="post" enctype="multipart/form-data" onsubmit="return check()">
     	<table>
     		<tr><td>
     			<select name="type" id="type">
     				<option value="gm">GM</option> 
     				<option value="lm">长安铃木</option> 
     				<option value="xdy">沈阳兴远东</option> 
     				<option value="5500">PO:5500</option> 
 				    <option value="BS">北盛</option> 
     				<option value="DY">东岳</option> 
     				<option value="ord" selected="selected">普通客户</option> 
     			</select>
     		</td></tr>
     		<tr><td><input type="file" name="file">SAP数据</td></tr>
     			<tr><td><input type="file" name="file2" id="file2">发票参照号</td></tr>
     			<tr><td><input type="submit" value="提交"></td></tr>
     	</table>
    </form>
  </body>
</html>
