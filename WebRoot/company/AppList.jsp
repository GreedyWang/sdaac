<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
		<link rel="stylesheet" type="text/css"
			href="ext/resources/css/ext-all.css">
		<link rel="stylesheet" type="text/css"
			href="company/css/xml-tree-loader.css">
		<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="ext/ext-all.js"></script>
		<script type="text/javascript" src="company/js/XmlTreeLoader.js"></script>
		<script type='text/javascript' src='/bpp/dwr/interface/newsManager.js'></script>
		<script type='text/javascript' src='/bpp/dwr/engine.js'></script>
		<script type="text/javascript" src="company/js/xml-tree-loader.js"></script>
		<style type="text/css">
			.login{
				background-image: url(images/icons/group.png) !important;			
			}
			.addressbook{
				background-image: url(images/icons/addressbook.png) !important;			
			}
			.wrench{
				background-image: url(images/icons/wrench.png) !important;			
			}
		</style>
	</head>

	<body >
	
		<input type="hidden" id="xml" name="xml">
		<div id="tree"></div>
	</body>
</html>
