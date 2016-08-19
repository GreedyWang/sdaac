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
  
 <script language="javascript">
        function init() {
        	alert('${logineduser.userName}');
        	
        		document.getElementById('tb_UserName').value='${logineduser.userName}';
        	//document.getElementById('btnLogin').click();
        } 
        function load()
        {
        }
    </script>
<body id="login" scroll="no" onload="init();">
    <div id="container">
        <form name="form1" method="post" action="http://10.243.75.21/ultimusclient/logon.aspx" id="form1">
<div>
<input name="__LASTFOCUS" id="__LASTFOCUS" value="" type="hidden">
<input name="__EVENTTARGET" id="__EVENTTARGET" value="" type="hidden">
<input name="__EVENTARGUMENT" id="__EVENTARGUMENT" value="" type="hidden">
<input name="__VIEWSTATE" id="__VIEWSTATE" value="/wEPDwUKMTI4Njg2NTIzNw9kFgICAw9kFgYCAg8QZBAVAQlzZGFhYy5jb20VAQlzZGFhYy5jb20UKwMBZxQrAQBkAgMPDxYCHgRUZXh0BQbkuK3mlodkZAIFDw8WAh8ABQVMb2dpbmRkZGiEOsMCGHKYXF+WkM+PMsXaB3DT" type="hidden">
</div>



        <div id="header" style="">
                <h1>   
                    </h1>
            </div>
           <br>
           <br>
           <br>
           <br>
           <br>
      
         
     
            <div id="content">
                <table cellpadding="0" cellspacing="0">
                    <tbody><tr>
                        <th>
                            <strong><font style="font-size: 9pt;" color="#666666" face="Arial">User&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ID: </font></strong>
                        </th>
                        <td>
                            <input name="tb_UserName" id="tb_UserName" type="text"></td>
                    </tr>
                    <tr>
                        <th>
                            <strong><font style="font-size: 9pt;" color="#666666" face="Arial">Password: </font></strong>
                        </th>
                        <td>
                            <input name="tb_Password" id="tb_Password" type="password"></td>
                    </tr>
                    <tr>
                        <th>
                            <strong><font style="font-size: 9pt;" color="#666666" face="Arial">Domain:</font></strong>
                        </th>
                        <td>
                            <select name="DrpDomain" id="DrpDomain" style="font-size: 10px;">
	<option selected="selected" value="sdaac.com">sdaac.com</option>
</select>
                        </td>
                    </tr>
                    <tr align="right">
                         <th colspan="2"><img alt="" src="logon.aspx_files/arrow_white.htm" height="9" width="9">
                                    <a id="lbtnLang" href="javascript:__doPostBack('lbtnLang','')" style="display: inline-block; color: rgb(0, 192, 0); font-family: Arial; font-size: 9pt; text-decoration: none; width: 26px;">中文</a>
                             &nbsp;
                           
                                    <img alt="" src="logon.aspx_files/arrow_white.htm" height="9" width="9">&nbsp;<a href="http://10.243.75.21/UltimusClient/help/E-flow%20Training%20-%20Basic%20%20R20090701.pdf" target="_blank" style="text-decoration: none;"><span style="font-size: 9pt; color: rgb(0, 192, 0); font-family: Arial;">Help</span></a></th>
             
                    </tr>
                    </tbody><tfoot>
                        <tr>
                            <th>
                                <input name="msg" id="msg" style="width: 27px;" type="hidden"></th>
                            <td>
                                <input name="btnLogin" value="Login" id="btnLogin" tabindex="4" style="height: 24px; width: 50px;" type="submit">&nbsp;</td>
                        </tr>
                    </tfoot>
                </table>
            </div>
            <div id="footer">
                <p>
                    
                </p>
            </div>
        
<div>

	<input name="__EVENTVALIDATION" id="__EVENTVALIDATION" value="/wEWBwKRkOq6DwLxz5rXDwLF8dCIDALGg+jdBgLwkK36DgKK1brVCgKC3IeGDDj/Ev0/+okMn2BPKI8OaOlWv61p" type="hidden">
</div>


</form>
    </div>
<div style="position: absolute; display: none; z-index: 9999;" id="livemargins_control"><img src="logon.aspx_files/monitor-background-horizontal.png" style="position: absolute; left: -77px; top: -5px;" height="5" width="77">	<img src="logon.aspx_files/monitor-background-vertical.png" style="position: absolute; left: 0pt; top: -5px;">	<img id="monitor-play-button" src="logon.aspx_files/monitor-play-button.png" onmouseover="this.style.opacity=1" onmouseout="this.style.opacity=0.5" style="position: absolute; left: 1px; top: 0pt; opacity: 0.5; cursor: pointer;"></div></body></html>
