<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<style type="text/css">  
#rbbox{ 
        position:absolute; 
        right:0; 
        bottom:0; 
        width:300px; 
        height:0px; 
        overflow:hidden; 
}  
.button{ 
        display:inline; 
        float:right; 
        font-size:12px; 
        cursor:pointer; 
}  
</style>  
<body> 
<div id="rbbox"> 
  <a class="button" onclick="closeBox()">¹Ø±Õ</a> 
  <iframe src="VAVEindex.jsp" frameborder="0" height="200" width="300" scrolling="no"></iframe> 
</div>  
<script language="javascript" type="text/javascript">  
window.onload=function(){ 
        showBox(); 
        setTimeout("closeBox()",5000) 
}  
function showBox(o){  
        if (o==undefined) o=document.getElementById("rbbox");  
        o.style.height=o.clientHeight+2+"px";  
        if (o.clientHeight<200) setTimeout(function(){showBox(o)},5);  
}  
function closeBox(){ 
         document.getElementById("rbbox").style.display="none"; 
}  
</script>  
</body>  
</html>  

