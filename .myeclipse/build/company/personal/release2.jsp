<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/FCKeditor" prefix="FCK"%>
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

		<title>发布新闻</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src='js/util/wforms.js'></script>
		<link rel="stylesheet" type="text/css"
			href="ext/resources/css/ext-all.css">
		<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="ext/ext-all.js"></script>

	</head>
<script type="text/javascript">
//当选择可回复时，使匿/记名 可选
function changeType(){
	
	if(document.getElementById('rad').checked){
		document.getElementById('type2').disabled=false
	}else{
		document.getElementById('type2').disabled=true
	}
}
//选择匿/记名 改变‘rad’的值
function changeType2(elem){
	alert(elem.value)
	document.getElementById('rad').value=elem.value;
}
function showTip(){

	var tip='保存方式:';
	if(document.getElementById('save').checked){
		tip='暂存'
	}else{
		tip='提交'
	}
	tip+='、类型:'
	if(document.getElementById('rad').checked){
		if(document.getElementById('rad').value==1){
			tip+='可回复匿名'
		}else{
			tip+='可回复记名'
		}
	}else{
		tip+='不可回复'
	}
	return window.confirm("你发布的新闻类型为:"+tip+"?")
	

}
</script>

	<body>
	<table width="1000">
		<form name="form1" action='news.do?actionType=update' method="post" onsubmit="return showTip()">
			<input type="hidden" name="item.id" value="${item.id }">

			<input type="hidden" name="item.releaseEmployeeId.uid" value="${item.releaseEmployeeId.uid }">
			<tr>
				<td align="center">
					<label for="name">
						标题
					</label>
					<input class="required" type="text" name="item.title" size="50" value="${item.title }" />
				</td>
			</tr>
			<tr>
				<td>
					保存<input type="radio" id="save" name="item.state" value="0" checked="checked">
				
					提交<input type="radio" id="submit" name="item.state" value="1">
				</td>
			</tr>
			<tr>
				<td>
					不可回复
					<input type="radio" name="item.type" value="0" checked="checked" onclick="changeType(this)">
					可回复
					<input type="radio" id="rad" name="item.type" value="1" onclick="changeType(this)">
				</td>
				
				<td>
					<select id="type2" disabled="disabled" onchange="changeType2(this)">
						<option value="1">匿名</option>
						<option value="2">记名</option>
					</select>
				</td>
		</tr>
		<tr><td>
			<FCK:editor id="item.context" width="80%" height="320" 
				fontNames="宋体;黑体;隶书;楷体_GB2312;Arial;Comic Sans MS;Courier 
New;Tahoma;Times New Roman;Verdana"
				imageBrowserURL="/bpp/FCKeditor/editor/filemanager/browser/default/browser.html?
Type=Image&Connector=connectors/jsp/connector"
				linkBrowserURL="/bpp/FCKeditor/editor/filemanager/browser/default/browser.html?
Connector=connectors/jsp/connector"
				flashBrowserURL="/bpp/FCKeditor/editor/filemanager/browser/default/browser.html?
Type=Flash&Connector=connectors/jsp/connector"
				imageUploadURL="/bpp/FCKeditor/editor/filemanager/upload/simpleuploader?Type=Image"
				linkUploadURL="/bpp/FCKeditor/editor/filemanager/upload/simpleuploader?Type=File"
				flashUploadURL="/bpp/FCKeditor/editor/filemanager/upload/simpleuploader?Type=Flash">
					${item.context }
			</FCK:editor>
		</td></tr>
			<tr><td><input type="submit" name="SubM" value="发表" /></td></tr>
		</form>
	</table>
	</body>
</html>
