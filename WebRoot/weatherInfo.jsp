<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>城市天气信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		img {
			width: 24px;height: 24px
		}
		body{
			BACKGROUND-COLOR: transparent;
			font-size: 10px
		}
		font{
			
			font-size: 12px
		}
	</style>
  </head>
  
  <body >
    <% 
    String info = (String) request.getAttribute("info") ; 
    
    if(info.equals("#")) {
    %>
    <h1>该城市暂不被支持天气预报服务</h1>
    <%
    } else {
    String[] infos = info.split("#") ; 
    %>
    
        <table >
     <tr style="background-color: blue;">
     	<td>aaaaa</td>
     </tr>
     </table>
    
    
    <table width="">
     <tr style="background-color: blue;font-size: 18px;color: white;">
     <td colspan="3" style="font-size: ">

     </td>
     </tr>
     <tr align="center">
     <td>
     <table>
     <tr align="center">
     <!-- 日期 -->
     <td>
     <font >
     <%=infos[6] %>
     </font>
     </td>
     </tr>
     <tr align="center">
     <!-- 天气图标 -->
     <td>
     <img src="tqimgs/a_<%=infos[8]%>" />
     <img src="tqimgs/a_<%=infos[9]%>" />
     </td>
     </tr>
     <tr align="center">
     <!-- 天气细则 -->
     <td>
     <font >
     <%=infos[5] %><br>
     <%=infos[7] %><br>
     </font>
     </td>
     </tr>
     </table>
     </td>
     
     
     <td>
     <table align="center">
     <tr align="center">
     <td>
     <!-- 第二天的时间 -->
     <font >
     <%=infos[13] %>
     </font>
     </td>
     </tr>
     
     <tr align="center">
     <td>
     <!-- 第二天的天气图标 -->
     <img src="tqimgs/a_<%=infos[15]%>">
     <img src="tqimgs/a_<%=infos[16]%>">
	</td>
     </tr>
     
     <tr align="center">
     <td>
     <!-- 第二天的天气细则 -->
     <font >
     <%=infos[12] %><br>
     <%=infos[14] %><br>
     </font>
     </td>
     </tr>
     </table>
     </td>
     
     
     <td>
     <table align="center">
     <tr align="center">
     <td>
     <!-- 第三天的天气时间 -->
    	<font >
     <%=infos[18] %>
     </font>
		</td>
     </tr>
     
     <tr align="center">
     <td>
     <!-- 第三天的天气图标 -->
     <img src="tqimgs/a_<%=infos[20]%>" />
     <img src="tqimgs/a_<%=infos[21]%>" />
     </td>
     </tr>
     <tr align="center">
     <td>
     <!-- 第三天的天气细则 -->
     <font >
     <%=infos[17] %><br>
     <%=infos[19] %><br>
     </font>
     </td>
     </tr>
     </table>
     </td>
		 </tr>
		 <tr>
		 <td colspan="3">
 
    <%
    }
     
     %>
	 
		
  </body>
</html>
