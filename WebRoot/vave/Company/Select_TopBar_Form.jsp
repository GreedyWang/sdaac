<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>vave提案单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <style type="text/css" media="screen">@import url(css/vave.css);</style>	
    <script type="text/javascript" src="calc/calendar.js"></script>
	<script type="text/javascript" src="calc/calendar-en.js"></script>
	<script type='text/javascript' src="js/cal.js"></script>
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
	<link type="text/css" rel="stylesheet" href="calc/calendar-system.css"/>
 	</head>

  <script type="text/javascript">
  function search()
  {
  	var theForm=document.getElementById('search_Form');
  	theForm.submit();
  }
  
  function showDiv()
		{
			var elem=document.getElementById('inputExcel')
		
			if(elem.style.display=='block')
			{
				elem.style.display='none'
				return 
			}
			if(elem.style.display=='none')
			{
				elem.style.display='block'
				return 
			}
		}
  
  /**下载日期内的提案**/
  function downloadProposal(){
 	var beginTime=document.getElementById('time').value
  	var endTime=document.getElementById('time2').value
 	if(beginTime==''||endTime==''){
 		alert('日期不能为空！')
 	}else{
	 	var url="task.do?actionType=proposalAccount&endTime="+endTime+'&beginTime='+beginTime
	  	window.location.href=url
  	}
  }
  
  function download()
  {
  	var state=document.getElementById('state').value;
  	var source=document.getElementById('source').value;
  	var form=document.getElementById('downLoadForm')
  	var beginTime=document.getElementById('time').value
  	var endTime=document.getElementById('time2').value
  	form.action+='&endTime='+endTime+'&beginTime='+beginTime+'&state='+state+'&source='+encodeURI(source);
  	//form.action+='&source='+source;
  	form.submit();
  }

  </script>
  <body >
 <div>
 </div>
 <div id="inputExcel" style="position:absolute;left:15%;top:40%;width: 800;height: 100;z-index: 2;background-color: #a3c3d5;float: left;display: none">
   	<!-- 		零件	零件代号	适应车型	计划完成日期	单位节约金额	"	预计节约金额"
   	 -->
   	 <form id="downLoadForm" action="task.do?actionType=projectAccount" method="post">
   	<table width="800">
   	<tr>
   	<td><input type="checkbox" name="context" value="0"> 负责人</td>
   	 <td><input type="checkbox" name="context" value="1">提案人</td>
   <td><input type="checkbox" name="context" value="2">项目名称</td>
   <td><input type="checkbox" name="context" value="3">零件</td>
   <td><input type="checkbox" name="context" value="4">零件代号</td>
   	<td><input type="checkbox" name="context" value="5">适应车型</td>
   	 <td><input type="checkbox" name="context" value="6">数量</td>
   <td ><input type="checkbox" name="context" value="7">计划完成日期</td> 
   <td ><input type="checkbox" name="context" value="8">单位节约金额</td>
  	 <td ><input type="checkbox" name="context" value="9">预计节约金额</td>
  	   	 <td ><input type="checkbox" name="context" value="10">分享比例</td>
  	   	  <td ><input type="hidden" name="context" value="11"></td>
  	   	   <td ><input type="hidden" name="context" value="12"></td>
   </tr>
   <tr><td><input type="button" value="下载" onclick="download()"> </td>
   <td><input type="button" value="关闭" onclick="showDiv()"></td></tr>
   	</table>
   	</form>
   </div>
   
   	<form action="proposal.do?actionType=doSearchAllCompany" method="post" id="search_Form">
   	
   	 <table >
  <tr style="background-color: #eeeeee">
   <td>目前状态</td>
   <td>提案人</td>
   <td>部门</td>
   <td>来源</td>
   <td>提案编号</td>
   </tr>
   	<tr>
   	<td><select id="state" name="item.state">
   	<option value="-3">请选择</option>
   	<option value="1">本部门审批</option>
   	<option value="2">外部门审批</option>
   	<option value="3">公司审批</option>
   	<option value="4">项目建立</option>
   	<option value="6">项目实施</option>
   	<option value="5">项目终止</option>
   	<option value="7">项目完成</option>
 	<option value="8">合理化建议</option>
   	<option value="9">等待产品/成本切换</option>
   	</select>
   	</td> 
   	<td>
     <input name="item.proposalPerson.name" type="text" >
   	</td>
   	<td>
						<select name="item.proposalPerson.tdepartment.id">
							<option value="null">
								请选择
							</option>
								<option value="10048">采购部</option>
								<option value="10044">制造工程部ME</option>
								<option value="10040">产品工程部PE</option>
						</select>
					</td>
	 <td><select id="source" name="item.source">
							<option value="-1">
								请选择
							</option>
								<option value="供应商">供应商</option>
								<option value="公司">公司</option>
							
						</select></td>
   	   	<td><input name="item.id" type="text"></td>
   	   	</tr>
   	
   
   <tr style="background-color: #eeeeee">
   <td colspan="2">日期</td> 
   <td colspan="2">主题</td>
   <td>搜索/导出数据</td>
   </tr>
  
 
   <tr>				
	<td><input type="text"  name="lastModifyTime" id="time" onfocus="calShow('time');" ></td>
   	<td><input type="text"  name="item._selectBeginTimeString" id="time2" onfocus="calShow('time2');" ></td>
   	<td colspan="2"><input name="item.title" type="text"></td>
   	<td><img src="images/search.gif" onclick="search()">
   	/	<input value="项目下载" type="button" onclick="showDiv()">/
   	<input value="提案下载" type="button" onclick="downloadProposal()">
   	</td>
   </tr>

   </table>
   	
   	
   	</form>


  
  
  </body>
</html>
