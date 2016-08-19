<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

		<link rel="stylesheet" type="text/css" href="css/style_admin.css">
		<script type='text/javascript' src='/bpp/dwr/interface/EmpManager.js'></script>
		<script type='text/javascript' src='/bpp/dwr/engine.js'></script>

		<script type="text/javascript">

  
  function init()
  {
  	var select=document.getElementById('departList')
  	var dpName='${empList.tdepartment.name }'
  	//alert(dpName)
  	if(select!=null)
  	{
  		for(var i=0;i<select.options.length;i++)
  		{
  			if(select.options[i].text==dpName)
  			{
  				select.options[i].selected=true;
  			}
  		}
  	}
  	
  	var ck=document.getElementById('is')
  	var flag='${empList.isSeparation }'
  	if(flag==0)
  	{
  		ck.checked=true;
  	}
  	
  }
  
  function dismiss(){
 	if( window.confirm("确认?")){
	  	document.getElementById('type').value=1
	  	document.getElementById('f1').submit();
  	}
  }
  var flag = '';
  var length = 1;
  
    function bb(leaderuid){    
       var tagArr = document.getElementsByTagName('TR');
   	   for ( var i = 0; i < tagArr.length; i ++ ){
	      var att = tagArr[i].getAttribute( "name" );
	      if ( att == leaderuid )
	      if(tagArr[i].style.display == 'block'){
	      	tagArr[i].style.display = 'none'
	      }else{
	      	tagArr[i].style.display = 'block'
	      }
   		}
	}
  
  function aa(elem,index){
	var T = 0;
	for(var i = 0;i<flag.split(',').length;i++){
		if(flag.split(',')[i]==elem){
			T = -1;
		} 
	}
	
	if(T == 0){
		var newTr = document.getElementById('table3').insertRow(index);

		var newTd0 = newTr.insertCell();	
		var newTd1 = newTr.insertCell();
		var newTd2 = newTr.insertCell();
		var newTd3 = newTr.insertCell();
		var newTd4 = newTr.insertCell();
		var newTd5 = newTr.insertCell();
		var newTd6 = newTr.insertCell();
	  	
	  	newTd0.innerHTML='<td><input type="checkbox" value="${item.uid }" name="uids"></td>'
	  	newTd1.innerHTML='<td>0001</td>'
	  	newTd2.innerHTML='<td ><input type="button" onclick="aa(0001'+','+'3)" >A</td>'
	  	
	  	newTd3.innerHTML='<td>D2 Gamma</td>'
	  	newTd4.innerHTML='<td>yuangong</td>'
	  	newTd5.innerHTML='<td>1.1</td>'
	  	newTd6.innerHTML='<td>DT02</td>'
	  	flag =flag + elem+',';
	  	length = length+1
  	}else{
  	
  	}
  	 	
  }
  
  function goToSupper(){
  	window.open('dMS.do?actionType=doGetMyTeam')
  }
  </script>

	</head>

	<body>
		<form id="f1" action="dMS.do?actionType=workerManager" method="post">
			<input type="hidden" name="type">
			<table cellspacing="1" cellpadding="4" width="90%"
				class="tableborder" id='table3'>
				<tr class="header">
					<td width="30px" align="center" class="header">
						选择
					</td>
					<td align="center" class="header">
						工号
					</td>
					<td align="center" class="header">
						姓名
					</td>
					<td align="center" class="header">
						<b>直系领导工号</b>
					</td>

					<td align="center" class="header">
						<b>区域</b>
					</td>
					<td align="center" class="header">
						<b>组别</b>
					</td>
					<td align="center" class="header">
						<b>岗位系数</b>
					</td>

					<td align="center" class="header">
						<b>工厂</b>
					</td>
					<td align="center" class="header">
						<b>类型</b>
					</td>

					<td align="center" class="header">
						<input type="button" value="离职" onclick="dismiss()">
					</td>
				</tr>
				<c:forEach items="${rs}" var="item" varStatus="state">
				<c:if test="${item.groupName == '大组长'}" >
					<tr name="${item.leaderID}" class="header" style="background-color: #123456">
				</c:if>
				<c:if test="${item.groupName == '小组长'}">					
					<c:if test="${flag == '4'}">
						<tr name="${item.leaderID}" class="header" style="display: none;font-size: 16px">
					</c:if>
					<c:if test="${flag != '4'}">
						<tr name="${item.leaderID}">
					</c:if>
				</c:if>
				<c:if test="${item.groupName == '员工'}">
					<c:if test="${flag == '3' || flag == '4'}">
						<tr name="${item.leaderID}" style="display: none;">
					</c:if>
					<c:if test="${flag != '3' && flag != '4'}">
						<tr name="${item.leaderID}">
					</c:if>
				</c:if>
					
						<td align="center" class="altbg1">
							<input type="checkbox" value="${item.uid }" name="uids">
						</td>
						<td align="center" class="altbg1">
							${item.uid }
						</td>
						<td align="center" class="altbg1" onclick="bb('${item.uid}')">
							<label>
								${item.name }
							</label>
						</td>

						<td align="center" class="altbg1">
							${item.leaderID }
						</td>
						<td align="center" class="altbg1">
							${item.district }
						</td>
						<td align="center" class="altbg1">
							${item.groupName }
						</td>
						<td align="center" class="altbg1">
							${item.dmsratio }
						</td>
						<td align="center" class="altbg1">
							${item.area }
						</td>
						<td align="center" class="altbg1">
							${item.type }
						</td>
						<td align="center" class="altbg1"></td>
					</tr>
				</c:forEach>

			</table>
		</form>
		<tr>
		</tr>
		</tbody>
		</table>

	</body>
</html>
