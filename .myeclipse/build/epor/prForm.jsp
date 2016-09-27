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

		<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">		
		<link rel="stylesheet" type="text/css" href="css/file-upload.css" />
		
		<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="ext/ext-all.js"></script>
		

		<script type="text/javascript" src="epor/util/FileUploadField.js"></script>

		<script type='text/javascript' src='dwr/interface/prFormListManager.js'></script>
		<script type='text/javascript' src='dwr/interface/PuyerListBizManager'></script>
		
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type="text/javascript" src="epor/js/prForm.js"></script>
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
	<input type="hidden" id="area" value="${logineduser.emp.area }">
	<input type="hidden" id="departId" value="${logineduser.emp.tdepartment.id }">
		<div id="test">
			<div id="fi-button-msg" style="display: none;"></div>
			
			<div id="fi-button"></div>
		</div>
		
		<div id="div1"></div>
	</body>
</html>
