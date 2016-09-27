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
  
  <script type="text/javascript">
  	
  	function init(){
  		var type = '${param.aa }';
  		var cc = document.getElementById('cc');
  		if(type == 'sp') {
  			cc.src="SAP_REPORTS/Img/sp.JPG"
  		}else if(type == 'mp') {
  			cc.src="SAP_REPORTS/Img/mp.JPG"
  		}
  	}
  	
  </script>
  
  <body onLoad="init()">
  	<form action="sap.do?actionType=doUpload" method="post">
  		<table>
		  	<tr>
  				<td>
  					格式：
  				</td>
  			</tr>
  			<tr>
  				<td>
  					<image id="cc" src="SAP_REPORTS/Img/c.JPG"> </iamge>
  				</td>
  			</tr>
  			<tr>
  				<td> <input type="file" name="xls"> </td>
  			
  			</tr>
 			<tr>
  				<td> <input type="submit" value="提交/submit"> </td>
  			
  			</tr>
  			<input type="hidden" name="type" value="${param.aa }" > 
  		</table>
  	</form>
  </body>
</html>
