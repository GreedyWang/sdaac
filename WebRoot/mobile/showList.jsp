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
		<meta name="viewport" content="width=device-width,initial-scale=1" />
		<link rel="stylesheet" type="text/css"
			href="ext/resources/css/ext-all.css">
		<link rel="stylesheet" href="mobile/jquery.mobile-1.3.0.css">
				<script src="mobile/jquery.js"></script>
		<script src="mobile/jquery.mobile-1.3.0.js"></script>
		<script type='text/javascript'
			src='/bpp/dwr/interface/prFormListManager.js'></script>
		<script type='text/javascript' src='/bpp/dwr/interface/EmpManager.js'></script>
		<script type='text/javascript' src='/bpp/dwr/engine.js'></script>
		<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="ext/ext-all.js"></script>
		<script type="text/javascript" src="mobile/js/prShowList.js"></script>
		
	</head>
	<style type="text/css">
.icon-upload {
	background-image: url(images/icons/upload.png) !important;
}
</style>
<script type="text/javascript">
	function init(){
		$( "#mypanel" ).trigger( "updatelayout" );
	}
	function goMain(){
		window.location.href='mobile/Main.html'
	}
	
	
</script>
	<body>
		<!-- push data -->
		<div data-role="panel" id="mypanel" data-position="right" data-display="push"> 
		 	<iframe id="frame1" height="100%"></iframe>
		</div>
		<a href="mobile/Main.html">Open panel</a>

		<div id="tab"></div>
		<div id="centre"></div>
		<div id="top">

		</div>
		<input id="paramUid" type="hidden" value="${param.uid }">
		<input id="types" type="hidden" value="${param.types } ">
		<input id="isFin" type="hidden" value="${param.isFin } ">
		<input id="area" type="hidden" value="${param.area } ">
		
		<div data-role="footer" class="ui-bar" data-position="fixed">
			<a data-icon="back" onclick="goMain()">BACK</a>
			<a href="#">keyA</a>
			<a href="#">keyA</a>
		</div>
	</body>
</html>
