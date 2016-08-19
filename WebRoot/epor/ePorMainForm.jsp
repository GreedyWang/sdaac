<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/ext-all.js"></script>
	<script type="text/javascript" src="epor/js/ePorMainFrom.js"></script>
	<script type="text/javascript" src="i18N/test.js"></script>

  </head>
  
  <body>
  <div id="hello-win"></div>
  <div id="hello-tabs"></div>
    <div id="hello-win2"></div>
  <div id="hello-tabs2"></div>
    <div id="hello-win3"></div>
  <div id="hello-tabs3"></div>
      <div id="hello-win4"></div>
  <div id="hello-tabs4"></div>
  <div id="west"></div>
   <div id="header" ></div>
    <div id="tree-div"></div>
     
     <div id="source"></div>
      <div id="centre"></div>
      <input id="uid" type="hidden" value="${logineduser.uid }">
 	  <input id="aaa" type="hidden" value="${logineduser.emp.roleids_b }">
 	  
 	  <!-- 以下为i18n标签 -->
 	<input id= q_uid type="hidden" value="<bean:message key='q_uid'/>"/>
	<input id= q_item type="hidden" value="<bean:message key='q_item'/>"/>
	<input id= q_costCenter  type="hidden" value="<bean:message key='q_costCenter'/>"/>
	<input id= q_buyer type="hidden" value="<bean:message key='q_buyer'/>"/>
	<input id= q_BOMdata type="hidden" value="<bean:message key='q_BOMdata'/>"/>
	<input id= file type="hidden" value="<bean:message key='file'/>"/>
	<input id= open type="hidden" value="<bean:message key='open'/>"/>
	<input id= save type="hidden" value="<bean:message key='save'/>"/>
	<input id= close type="hidden" value="<bean:message key='close'/>"/>
	<input id= edit type="hidden" value="<bean:message key='edit'/>"/>
	<input id= copy type="hidden" value="<bean:message key='copy'/>"/>
	<input id= find type="hidden" value="<bean:message key='find'/>"/>
	<input id= replace type="hidden" value="<bean:message key='replace'/>"/>
	<input id= tool type="hidden" value="<bean:message key='tool'/>"/>
	<input id= help type="hidden" value="<bean:message key='help'/>"/>
	<input id= importPayRecord type="hidden" value="<bean:message key='importPayRecord'/>"/>
	<input id= error type="hidden" value="<bean:message key='error'/>"/>
	<input id= menu type="hidden" value="<bean:message key='menu'/>"/>
	<input id= personal type="hidden" value="<bean:message key='personal'/>"/>
	<input id= approve type="hidden" value="<bean:message key='approve'/>"/>
	<input id= basicinfo type="hidden" value="<bean:message key='basicinfo'/>"/>
	<input id= myProposal type="hidden" value="<bean:message key='myProposal'/>"/>
	<input id= info_m type="hidden" value="<bean:message key='info_m'/>"/>
	<input id= w_PRform type="hidden" value="<bean:message key='w_PRform'/>"/>
	<input id= w_masterData type="hidden" value="<bean:message key='w_masterData'/>"/>
	<input id= myPR_form type="hidden" value="<bean:message key='myPR_form'/>"/>
	<input id= MRO_form type="hidden" value="<bean:message key='MRO_form'/>"/>
	<input id= pr_pendingApprove type="hidden" value="<bean:message key='pr_pendingApprove'/>"/>
	<input id= pr_inProcess type="hidden" value="<bean:message key='pr_inProcess'/>"/>
	<input id= pr_completed type="hidden" value="<bean:message key='pr_completed'/>"/>
	<input id= report type="hidden" value="<bean:message key='report'/>"/>
	<input id= YT_prForm  type="hidden" value="<bean:message key='YT_prForm'/>"/>
	<input id= setbackup type="hidden" value="<bean:message key='setbackup'/>"/>
	<input id= process_m type="hidden" value="<bean:message key='process_m'/>"/>
	<input id= permission_m  type="hidden" value="<bean:message key='permission_m'/>"/>
	<input id= DOA_m type="hidden" value="<bean:message key='DOA_m'/>"/>
	<input id= maintianBaseData type="hidden" value="<bean:message key='maintianBaseData'/>"/>
 	  
  </body>
</html>
