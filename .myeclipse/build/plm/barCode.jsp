<%@ page contentType="text/html;charset=UTF-8"%>

<%   
    String code = request.getParameter("pmartId");//条形码内容   
%>  
 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <style media="print" type="text/css">
.Noprint{display:none;}
.PageNext{page-break-after: always;}
</style>
<script type="text/javascript">
	 var HKEY_Root,HKEY_Path,HKEY_Key;    
     HKEY_Root="HKEY_CURRENT_USER";    
     HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";    
         //设置网页打印的页眉页脚为空    
     function PageSetup_Null()   
      {    
        try {    
                var Wsh=new ActiveXObject("WScript.Shell");    
        HKEY_Key="header";    
        Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");    
        HKEY_Key="footer";    
        Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");    
        }  catch(e){}    
      }    
      //恢复网页打印的页眉页脚   
      function PageSetup_default()   
      {    
        try {    
                var Wsh=new ActiveXObject("WScript.Shell");    
        HKEY_Key="header";    
        Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&w&b页码,&p/&P");    
        HKEY_Key="footer";    
        Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&u&b&d");    
        }  catch(e){}    
      }    
	function printpreview(){ 
// 打印页面预览 
PageSetup_Null(); 
document.all.WebBrowser.execwb(6,1) 

} 

function printit() 
{ 
if (confirm('确定打印吗？')) { 
PageSetup_Null(); 
document.all.WebBrowser.execwb(7,1) 
} 
} 

</script>
  <body>
  <br>
<div id="aa" >
    <%      
    StringBuffer barCode = new StringBuffer();   
    barCode.append("<img src='");   
    barCode.append(request.getContextPath());   
    barCode.append("/plm.do?actionType=showBarCode&code=");    
    barCode.append(code);   
    barCode.append("&barType=CODE39&checkCharacter=n&checkCharacterInText=n&barHeightCM=0.6'>");   
    out.println(barCode.toString());    
%>  
<p>
   <% out.println(barCode.toString());     %>
   </p></div>
   <OBJECT classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 id=WebBrowser width=0></OBJECT>    
 
<input class="Noprint" name=Button onClick="printpreview()" type=button value=打印>   
  
  </body>
</html>
