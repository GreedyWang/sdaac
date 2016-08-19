<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib  prefix="c" uri="/WEB-INF/c.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'AccountTypeTime.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/ext-all.js"></script>

  </head>
  <script type="text/javascript">
  function saveAsExcel(HeadName, DivName) {
            var s = "<center>" + HeadName + "</center>" + "\r\n";
            s +=document.getElementById(DivName).innerHTML;
            var xlsWindow = window.open("", "_blank", "width=1,height=1,scrollbars=no,toolbar=no");
            xlsWindow.document.write(s);
            xlsWindow.document.close();
            xlsWindow.document.execCommand('Saveas', true, '%homeDrive%\\Data.xls')
            xlsWindow.close();
        }
  Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL='images/default/blank.gif'
	var ddd= new Ext.form.DateField({
		header:'要求交货日期',
		//dataIndex:'expectDeliveryDate',
		width:130,
		applyTo:'date',
		format:'Y-m'
	})
	
	var but=new Ext.Button({
		applyTo :'btn1',
		text :'查询',
		listeners :{
			'click':function( btn, e )
			{
				e.stopEvent();
				//alert('!')
				document.forms[0].submit();
			}
		}
	})	
		
	})
	function submitForm()
	{
		alert('!')
	}
  </script>
  
  <body> 
  <form action="personal.do?actionType=showTypeTime" method="post">
  <tr>
  <td><input id="date" name="time" type="text"></td>
  <td>
  <div id="btn1" onclick="submitForm()"></div>
  
  </tr>
  </form>
  <h3 align="center">双击表格内容下载成excel</h3>
   <div id="context">
   <table ondblclick="saveAsExcel('account','context')">
   <thead>
   <tr>
   <td>岗位号</td>
    <td>名称</td>
     <td>类型</td>
      <td>总时间(小时)</td>
   </tr>
   </thead>
   <c:forEach items="${accountType}" var="item">
   <tr>
   <td>${item[0] }</td>
      <td>${item[3] }</td>
    <td>${item[2] }</td>  
    <td>${item[1] }</td>
   </tr>
   </c:forEach>
   </table>
   
   </div>
  </body>
</html>
