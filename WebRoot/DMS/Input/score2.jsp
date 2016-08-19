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
		<link rel="stylesheet" type="text/css" href="css/style_admin.css">

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
		//小数变成百分比形式
		function toP(val){		
			val.value = val.value*100 + "%"		
		}
		
		function init(){
			
		}
		
		//确认完成
		function doSubmitWithConfirm(){			
			document.getElementById('f1').action = 'dMS.do?actionType=doScore&isF=1';
			document.getElementById('f1').submit();
		} 
		
		function check(){
			var eles = document.getElementsByTagName('input')
			var temp;
			for(var i =0;i<eles.length;i++){
				if(eles[i].getAttribute('name')=='actual'||eles[i].getAttribute('name')=='target'){
					
				if(eles[i].value.charAt(eles[i].value.length-1)=='%'){
					temp = eles[i].value.substring(0,eles[i].value.length-1)
					//return false;
				}else{
					temp = eles[i].value;
				}
				
					if(!IsNum(temp)){
					 	alert(temp+'不能为输入值')
					 	return false;
					 }
				 }
			}
			return true;
			
		}
		
		function IsNum(num){  var reNum=/^[\+-]?[1-9]?\d*(\.\d{1,2})?$/;   return(reNum.test(num));}		
		
  </script>
	<body>
		<table>
			<tr align="center" class="header">
				<td>
					区域：${emp.district }
				</td>
				<td>
					组别：${emp.selectedGroup }

				</td>
				<td>
					版本：${version }
				</td>
			</tr>
		</table>
		<form action="dMS.do?actionType=doScore" method="post" id="f1"
			onsubmit="return check()">
			<table width="100%" cellspacing="1" cellpadding="4"
				class="tableborder">
				<!-- 类型 -->
				<tr>
					<td align="center" class="header" witdh="100px">
						类型
					</td>
					<c:forEach items="${indicateTitle}" var="aa" varStatus="aaState">

						<td align="center" class="header" colspan="${aa.value }">
							${aa.key }
						</td>
					</c:forEach>
				</tr>

				<tr>
					<td>
						员工姓名
					</td>

					<c:forEach items="${title.indicates}" var="item" varStatus="state">

						<c:if test="${item.state == 0}">
							<!-- state等于0的指标才显示 -->
							<c:if test="${state.index %2 ==0}">


								<c:if test="${item.type > 2}">
									<td width="50" style="background-color: #EDF6FF">
										系数
									</td>
								</c:if>
								<td width="50px" style="background-color: #EDF6FF">
									${item.name }
								</td>


							</c:if>
							<c:if test="${state.index %2 ==1}">

								<c:if test="${item.type > 2}">
									<td width="50px">
										系数
									</td>
								</c:if>

								<td align="center" class="altbg1" width="50px">
									${item.name }
								</td>
							</c:if>
						</c:if>
						<input type="hidden" value="${emp.district}" name="district">
						<input type="hidden" value="${emp.selectedGroup}" name="group">
						<input type="hidden" value="${item.id}" name="indicateid">
						<input type="hidden" value="${item.weight}" name="weight">
						<input type="hidden" value="${item.calc}" name="calcMethod">
						<input type="hidden" value="${version}" name="version">
					</c:forEach>
				</tr>


				<!-- 目标值 -->
				<tr>
					<td witdh="100px">
						目标值
					</td>
					<c:forEach items="${title.results}" var="item" varStatus="state">

						<c:if test="${title.indicates[state.index].state==0}">
							<!-- state等于0的指标的目标值 -->
							<c:if test="${item.xushu != -1}">
								<td></td>
							</c:if>
							<td width="50px" align="center">
								<c:if test="${item.target != -1}">
									<input size="5" style="width: 50px" type="text"
										value="${item.target}" name="target">
								</c:if>
								<c:if test="${item.target == -1}">
								0<input type="hidden" value="${item.target}" name="target">
								</c:if>
							</td>
						</c:if>
						
						<c:if test="${title.indicates[state.index].state!=0}">
							<!-- state不能于0的指标的目标值设为0 -->
							<input type="hidden" value="0" name="target">
						</c:if>
					</c:forEach>
				</tr>


				<c:forEach items="${templetes}" var="item">
					<tr>
						<td witdh="100px">
							${item.emp.name }
						</td>
						<input type="hidden" value="${item.emp.uid}" name="uid">
						<input type="hidden" value="${item.emp.group }" name="group">
						<input type="hidden" value="${item.emp.dmsratio}" name="dmsratio">

						<c:forEach items="${item.results}" var="item2" varStatus="state2">

							<!-- 指标系数 -->
							<c:if test="${item2.xushu != -1}">
								<c:if test="${item.indicates[state2.index].state==0}"><!-- 这个系数的指标状态等于0 -->
									<td width="30px">
										<input size="5" type="text" style="width: 50px"
											value="${item2.xushu}" name="xushu">
									</td>
								</c:if>
								<c:if test="${item.indicates[state2.index].state!=0}"><!-- 这个系数的指标状态不等于0 -->
									<input size="5" type="hidden" value="${item2.xushu}"
									name="xushu">
								</c:if>																
							</c:if>
							
							<c:if test="${item2.xushu == -1}">
								<input size="5" type="hidden" value="${item2.xushu}"
									name="xushu">
							</c:if>

							<!-- 实际结果 -->
							<c:if test="${state2.index %2 ==0}">
								<c:if test="${item.indicates[state2.index].state==0}">
									<td width="5px" align="center"
										style="background-color: #EDF6FF">
										<input size="5" style="width: 50px" type="text"
											value="${item2.actual}" name="actual">
									</td>
								</c:if>
								<c:if test="${item.indicates[state2.index].state!=0}">

									<input size="5" style="width: 50px" type="hidden" value="0"
										name="actual">

								</c:if>
							</c:if>

							<c:if test="${state2.index %2 ==1}">
								<c:if test="${item.indicates[state2.index].state==0}">
									<td width="5px" align="center">
										<input size="5" style="width: 50px" type="text"
											value="${item2.actual}" name="actual">
									</td>
								</c:if>
								
								<c:if test="${item.indicates[state2.index].state!=0}">
									<input size="5" style="width: 50px" type="hidden" value="0"
										name="actual">
								</c:if>

							</c:if>



						</c:forEach>

					</tr>
				</c:forEach>
			</table>
			<table>
				<tr>
					<td>
						<input type="submit" value="保存">
					</td>
					<td>
						<input type="button" value="确认完成" onclick="doSubmitWithConfirm()">
					</td>
					<td>
						<input type="button" value="解除完成">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
