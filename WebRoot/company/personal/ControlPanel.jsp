<%@ page language="java"  pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
  <title>控制面板</title>
  
  <base href="<%=basePath%>">
  
	<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css" />
 	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
    <link rel="stylesheet" type="text/css" href="menus.css"/>
    <script type="text/javascript" src="ext/ext-all.js"></script>
	<script type="text/javascript" src="company/personal/ControlPanel.js"></script>
	<style type="text/css">
	html, body {
        font:normal 12px verdana;
        margin:0;
        padding:0;
        border:0 none;
        overflow:hidden;
        height:100%;
    }
	p {
	    margin:5px;
	}
    .nav {
        background-image:url(../shared/icons/fam/folder_go.png);
    }
    </style>

</head>
<body>

  <div id="north-div"><div id='toolbar-div'></div></div>
  <div id="west-div"></div>
  <div id='center-div'></div>
 </body>
</html>
