<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
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
		<script type='text/javascript' src='dwr/interface/TempleteService.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		   <script type='text/javascript' src='vave/proposal.js'></script>
		      <style type="text/css" media="screen">@import url(css/vave.css);</style>
	</head>




	<script type="text/javascript">
		function roleChange(elem){
			document.getElementById('role').innerText = elem.value
		}
		function roleChange2(elem){
			document.getElementById('role2').innerText = elem.value
			TempleteService.doShowByCond(document.getElementById('role').innerText ,document.getElementById('role2').innerText,
			function(data) {
				for(var i = 0;i< data.length; i++){
				alert(data[i].indicateId)
					document.getElementById('a1').checked = true;
				}
			})
		}
	
  </script>
	<body>
	<form action="dMS.do?actionType=doShow" method="post">
		<table>
			<tr>
				<td>
					版本 : ${version[0] }
				</td>
			</tr>
		</table>
	</form>
		<table >

			<form action="dMS.do?actionType=doScore" method="post">
			<tr >
				<td width="70px" rowspan="2">
					员工姓名
				</td>

				<c:forEach items="${templetes}" var="item">

					<td width="100px" >
					${item.indicate.name }</td>							
				</c:forEach>
			</tr>
			
			<tr >
			

				<c:forEach items="${templetes}" var="item">
				
					<td width="100px" >
					${item.indicate.calc }</td>							
				</c:forEach>
			</tr>
			
			<tr>

				<c:forEach items="${rs}" var="map">
					<tr>
						<td width="70px">
							${map.key }
						</td>

						<c:forEach items="${map.value}" var="item">
							<input type="hidden"   name="rs.uid" value="${item.uid}" >
							<input type="hidden"   name="rs.indicateId" value="${item.indicate.id}" >
						
						
							<td>
								<input type="type" style="width: 100px;"  name="rs.actual" value="${item.score}">
							</td>
						</c:forEach>
					</tr>

				</c:forEach>


			</tr>

			</form>
		</table>
	</body>
</html>
