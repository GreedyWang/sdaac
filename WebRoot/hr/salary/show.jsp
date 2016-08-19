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
		<link rel="stylesheet" type="text/css"
			href="css/themes/gray/easyui.css">
		<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="css/demo.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
  </head>
  <script type="text/javascript">
  function submitForm(){
       window.location.href='http://10.243.75.20:9090/bpp'
       }
    </script>
  <body>
   
   <h2>Notice</h2>
    <div class="demo-info">
        <div class="demo-tip icon-tip"></div>
        <div>Faster & Stable</div>
    </div>
    <div style="margin:10px 0;">
    <table>
    <tr>
    	<td> Cuz the server update,pls visit the new URL (http://10.243.75.20:9090/bpp)<br>  </td>
    	<td rowspan="2"> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Go</a></td>
    </tr>
    <tr>
    	<td>服务器升级，请访问新的地址(http://10.243.75.20:9090/bpp)</td>
    </tr>
      </table>
        
        
       
    </div>
   
  </body>
</html>
