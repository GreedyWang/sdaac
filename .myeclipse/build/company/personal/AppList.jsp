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
		<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="js/ui/ui.core.js"></script>
		<script type="text/javascript" src="js/ui/effects.core.js"></script>
		<script type="text/javascript" src="js/ui/effects.blind.js"></script>
		<script type="text/javascript" src="js/ui/ui.draggable.js"></script>
		<script type="text/javascript" src="js/ui/ui.resizable.js"></script>
		<script type="text/javascript" src="js/ui/ui.dialog.js"></script>
		<script type="text/javascript"
			src="company/personal/xml-tree-loader.js"></script>
		<style type="text/css">
			.icon-add {
				background-image: url(images/icons/add.png) !important;
			}
			
			.icon-delete {
				background-image: url(images/icons/delete.gif) !important;
			}
			
			.icon-modify {
				background-image: url(images/icons/user_edit.png) !important;
			}
		</style>
		<script type="text/javascript">
			var xmlName='${logineduser.emp.tdepartment.id}'
			var loader = new Ext.app.BookLoader({
								// dataUrl:'company/data/IT.xml'
								dataUrl : 'company/data/' + xmlName + '.xml'
							});
		</script>
	</head>

	<body onload="init()">
		<input type="hidden" id="xml" name="xml" value="IT.xml">
		<div id="tree">
			<div id="dialog" title="Create new user">
			</div>

		</div>
	</body>
</html>
