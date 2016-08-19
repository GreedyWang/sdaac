<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>部门指标添加</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
  	<script type='text/javascript' src='/bpp/dwr/interface/IndexManager.js'></script>
  	<script type='text/javascript' src='/bpp/dwr/engine.js'></script>
  	</head>              
  	<script type="text/javascript">
	
  var long;
  	function init()
  	{
  		long=document.getElementById('table3').rows.length;
  	}
  	
  	 function Tadd()
  {
  	
    var index=document.getElementById('table3').rows.length-long;
  
  	var newTr = table3.insertRow();
	var newTd0 = newTr.insertCell();	
	var newTd1 = newTr.insertCell();
	var newTd2 = newTr.insertCell();
	var newTd3 = newTr.insertCell();
	var newTd4 = newTr.insertCell();
	var newTd5 = newTr.insertCell();
	var newTd6 = newTr.insertCell();
	var newTd7 = newTr.insertCell();
	var newTd8 = newTr.insertCell();
	var newTd9 = newTr.insertCell();
	var newTd10 = newTr.insertCell();
	
	newTd0.className='altbg1';
	newTd1.className='altbg1';
	newTd2.className='altbg1';
	newTd3.className='altbg1';
	newTd4.className='altbg1';
	newTd5.className='altbg1';
	newTd6.className='altbg1';
	newTd7.className='altbg1';
	newTd8.className='altbg1';
	newTd9.className='altbg1';
	newTd10.className='altbg1';
	newTd0.innerHTML= '<input class="required" name="items['+index+'].tindexTarget.name" id="items['+index+'].name" size="20"/> '; 
	newTd1.innerHTML= '<input class="required" name="items['+index+'].tindexTarget.formula" id="items['+index+'].formula" size="20"/> '; 
	newTd2.innerHTML='<select class="required" name="items['+index+'].tindexTarget.type"><option value="量化指标">量化指标</option><option value="非量化指标">非量化指标</option></select>';
	newTd3.innerHTML='<select class="required" name="items['+index+'].tindexTarget.isChoice"><option value="必选">必选</option><option value="可选">可选</option><option value="必选且值不可变">必选且值不可变</option></select>';
	newTd4.innerHTML= '<input class="required" name="items['+index+'].tindexTarget.a" id="items['+index+'].a" size="5"/>';
	newTd5.innerHTML= '<input class="required" name="items['+index+'].tindexTarget.b" id="items['+index+'].b" size="5"/>';
	newTd6.innerHTML= '<input class="required" name="items['+index+'].tindexTarget.c" id="items['+index+'].c" size="5"/>';
	newTd7.innerHTML= '<input name="items['+index+'].percentage" id="items['+index+'].percentage" size="5"/>';
	newTd8.innerHTML= '<input name="items['+index+'].act_output" id="items['+index+'].act_output" size="5"/>';
	newTd9.innerHTML= '<input name="items['+index+'].score" id="items['+index+'].score" size="5"/>';
	newTd10.innerHTML= '<img src="images/delete.gif" id="image"  onclick="Tdelete(this)"> ';
	index=index+1;
  }
  function Tdelete(elem)
  {
  	var t=document.getElementById('table3')   
  	t.deleteRow(elem.parentNode.parentNode.rowIndex)
  }
  
  function checkUid(elem,index)
  {
 
  var uid=elem.value;
  	// =document.getElementById('items.tempolyee.uid');?why can't get the value???!!
  
  	EmpManager.doSelectById(uid,function(data)
  	{
  
  		document.getElementById('uname['+index+']').value=data;
  	});
  }

    function checkPid(elem)
  {
  	
  	var uid=elem.value;	
  	
  	if(uid!=null&&uid!=""){
  	PostManager.doSelectById(uid,function(data)
  	{
  		if(data!=null){
  		document.getElementById('productName['+index+']').value=data.productName;
  		}
  	});}
  }
 
  function checkBlank(index)
  {
	var uid=document.getElementById('itemss['+index+'].tempolyee.uid').value;
	var uname=document.getElementById('uname['+index+']').value;
	var postid=document.getElementById('itemss['+index+'].tpost.id').value;
	var productName=document.getElementById('productName['+index+']').value;
	var worktime=document.getElementById('itemss['+index+'].worktime').value;
	var output=document.getElementById('itemss['+index+'].output').value;
	var masterid=document.getElementById('masterid').value;

	var masterName=document.getElementById('masterName').value;
  	if(uid=="")
  	{
  		alert('员工号不能为空');
  		return false;
	}
		if(uname=="[]")
  	{
  		alert('没有此员工');
  		return false;
	}	if(postid=="")
  	{
  		alert('岗位号不能为空');
  		return false;
	}	if(productName=="")
  	{
  		alert('没有此岗位');
  		return false;
	}	if(worktime=="")
  	{
  		alert('工作时间不能为空');
  		return false;
	}	if(output=="")
  	{
  		alert('产出不能为空');
  		return false;
	}
	if(masterid==""){
		alert('组长不能为空');
		return false;
	}
	if(masterName=="[]"){
		alert('没有此组长');
		return false;
	}
	return true;
  }
  
  function changeColor(color,title)
  {
  
  	var tb=document.getElementById('table3'); 	
  	tb.style.background=color;
  	var head=document.getElementById('head'); 	
  	head.style.background=color;
  	head.innerHTML=title;	
  }
  




  function createForm()
  {

	var activeForm= document.createElement('FORM');
	document.body.appendChild(activeForm);
	activeForm.method="POST";
	return activeForm;
  }
  
  
  function Tadd()
  {
  
 
  	var newTr = table3.insertRow();

	var newTd0 = newTr.insertCell();	
	var newTd1 = newTr.insertCell();
	var newTd2 = newTr.insertCell();
	var newTd3 = newTr.insertCell();
	var newTd4 = newTr.insertCell();
	var newTd5 = newTr.insertCell();
	var newTd6 = newTr.insertCell();
	var newTd7 = newTr.insertCell();
	index=index+1;
	
	newTd0.className='altbg1';
	newTd1.className='altbg1';
	newTd2.className='altbg1';
	newTd3.className='altbg1';
	newTd4.className='altbg1';
	newTd5.className='altbg1';
	newTd6.innerHTML='altbg1';
	newTd7.innerHTML='altbg1';
	
	newTd0.innerHTML= '<input name="items['+index+'].index.name" id="items['+index+'].index.name" onblur="checkIndex(this,index)"/> '; 
	newTd1.innerHTML='<select name="items['+index+'].index.type"><option value="量化指标">量化指标</option><option value="非量化指标">非量化指标</option></select>';
	
	newTd3.innerHTML= '<input name="items['+index+'].index.a" id="items['+index+'].index.a" size="10"/>';
	newTd4.innerHTML= '<input name="items['+index+'].index.b" id="items['+index+'].index.b" size="10"/>';
	newTd5.innerHTML= '<input name="items['+index+'].index.c" id="items['+index+'].index.c" size="10"/>';
	newTd6.innerHTML= '<input name="items['+index+'].percentage" id="items['+index+'].percentage" size="10"/>';
	newTd7.innerHTML= '<img src="images/delete.gif" id="image"  onclick="Tdelete(this)"> ';
  }
 function Tdelete(elem)
  {
  	table3.deleteRow(elem.parentNode.parentNode.rowIndex);
  	index=index-1;
  }
  
  
	function checkIndex(param,index)
  	{
  		var indexName=param.value+'-'+${logineduser.emp.tdepartment.id };
  		IndexManager.selectByIndexName(indexName,function(data)
  		{
  			var a=document.getElementById('items['+index+'].index.a');
  			var b=document.getElementById('items['+index+'].index.b');
  			var c=document.getElementById('items['+index+'].index.c');	
  			if(data!=null&&data!="")
  			{
  				a.value=data.a;
  				b.value=data.b;
  				c.value=data.c;
  			
  				a.readOnly=true;
  				b.readOnly=true;
  				c.readOnly=true;
  				alert('已定义过这个名字的指标');
  			}
  			
  		});
  	}
  
	function showIndexDetails()
	{
		var url='${basePath}'+'index.do?actionType=doSelectEveryDepartment&departID='+${logineduser.emp.tdepartment.id };
		alert(url);
		window.open(url, "xxx", "toolbars=0, location=0, statusbars=0, menubars=0,width=700,height=300,scrollbars=1");
	 	
	}
  </script>
  <body onload="init()">
  DepartmentIndexAddByDepartmentManager.jsp
 
  <form action="empIndex.do?actionType=doInsertEmpIndicators" method="post" >
  <table cellspacing="1" cellpadding="4" width="90%"
			class="tableborder" id="table3">
  <thead>
  <tr>
  <td colspan="8" class="header">指标定义 
  </td>
				
   </tr>
   <tr>
   <td  class="header"><input type="submit" value="提交"></td>
   <td colspan="7" class="header"><img src="images/add.gif" id="image"  onclick="Tadd()"></td>
   </tr>
<input type="hidden" name="uid" value="${empUid }">
   <tr>
     <td align="center" class="altbg1"><b>名称</b></td>
    <td align="center" class="altbg1"><b>类型</b></td>
    <td align="center" class="altbg1"><b>是否必需</b></td>
 	<td align="center" class="altbg1"><b>75%</b></td>
    <td align="center" class="altbg1"><b>100%</b></td>
    <td align="center" class="altbg1"><b>200%</b></td>
    <td align="center" class="altbg1"><b>权重</b></td>
	<td align="center" class="altbg1"><b>option</b></td>
  
   </tr> </thead>
  <tbody>
  <c:forEach items="${DepartEmpIndexs}" var="item" varStatus="state">
	<tr class="altbg1">
	<td><input type="checkbox" value="${state.index }" name="checkFlag">
	${item.tindexTarget.name }</td>
	<td>${item.tindexTarget.type }</td>
	<td>${item.tindexTarget.isChoice }</td>
	<td>${item.tindexTarget.a }</td>
	<td>${item.tindexTarget.b }</td>
	<td>${item.tindexTarget.c }</td>
	<td><input type="text" name="percentage" value="${item.percentage }"></td>
	<input type="hidden" name="indexId" value="${item.tindexTarget.id }">
	<td><img src="images/delete.gif" id="image"  onclick="Tdelete(this)"> </td>
</c:forEach>
  
    <c:forEach items="${empIndexs}" var="item">
	<tr class="altbg1"><td>${item.index.name }</td>
	<td>${item.index.type }</td>
	<td>${item.index.isChoice }</td>
	<td>${item.index.a }</td>
	<td>${item.index.b }</td>
	<td>${item.index.c }</td>
	<td><input type="text" name="percentage" value="${item.percentage }"></td>
	<input type="hidden" name="indexId" value="${item.index.id }">
	<td><img src="images/delete.gif" id="image"  onclick="Tdelete(this)"> </td>
</c:forEach>
  
  
  
	
	
  

   </tbody>
   </table>
   	</form>
  </body>
</html>
