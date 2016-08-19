<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<link rel="stylesheet" target="text/css" href="css/index.css">
		<link rel="stylesheet" type="text/css" href="css/icons.css">
		<link rel="stylesheet" type="text/css" href="css/UploadDialog.css">
		<link rel="stylesheet" type="text/css" href="css/file-upload.css" />
		<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="ext/ext-all.js"></script>
		<script type="text/javascript" src="HumanResource/assembly_room/js/form.js"></script>
		<script type="text/javascript" src="epor/util/UploadDialog.js"></script>
		<script type="text/javascript" src="epor/util/FileUploadField.js"></script>
		<script type="text/javascript" src="epor/util/swfupload.js"></script>
		<script type='text/javascript' src='dwr/interface/prFormListManager.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type="text/javascript" src="epor/baseInfo/js/util/loadProjectData.js"></script>
	</head>
	<style type="text/css">
        .icon-add {
            background-image:url(images/icons/add.png) !important;
        }
        .icon-del {
            background-image:url(images/icons/delete.gif) !important;
        }
    </style>
	<body>

		<div  id="test">
			<div id="fi-button-msg" style="display: none;"></div>
			<div id="fi-button"></div>
		</div>
	</body>
</html>
