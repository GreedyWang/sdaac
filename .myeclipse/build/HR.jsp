<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>控制台</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/style_admin.css">
<link rel="stylesheet" href="styling.css" type="text/css" media="screen" />
<!-- one style sheet to style them all, valid with no errors -->
<link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />
<!-- our custom little favicon -->

<meta http-equiv="imagetoolbar" content="no" />


</head>
<script type="text/javascript">
  function mfk()
{
  
   if(shang.style.display=='none')
    {
     shang.style.display='';
    }
   else
   {
     shang.style.display='none';
   }
}
  </script>
<body>

<!-- END search -->

<!-- BEGIN left column -->





<!-- BEGIN center column -->

<table width="100%" height="756" border="0" align="center"
	cellpadding="0" cellspacing="0">
	<tr>
		<td id="shang" valign="top" width="100px"><jsp:include
			page="left.jsp"></jsp:include></td>

		<td width="10px" align="center" class="unnamed1" onClick="mfk();"
			height="100%">
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td bgcolor="#3A5F94"><font color="#FFFFFF">点此切换</font></td>
			</tr>
		</table>
		</td>
		<td rowspan="2"><iframe frameBorder="0" id="main" name="main"
			scrolling="auto" height="100%" width="100%"></iframe></td>
	</tr>

</table>
</body>
</html>
