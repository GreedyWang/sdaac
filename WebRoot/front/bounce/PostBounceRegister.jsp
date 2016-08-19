<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>岗位管理</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link type="text/css" rel="stylesheet" href="calc/calendar-system.css" />
		<link rel="stylesheet" type="text/css" href="css/style_admin.css">
		<link type="text/css" href="css/themes/base/ui.all.css"
			rel="stylesheet" />
		<link type="text/css" href="css/themes/base/demos.css"
			rel="stylesheet" />

		<script type='text/javascript' src='/bpp/dwr/interface/bfManager.js'></script>
		<script type='text/javascript' src='/bpp/dwr/interface/bpManager.js'></script>
		<script type='text/javascript' src='/bpp/dwr/interface/EmpManager.js'></script>
		<script type='text/javascript' src='/bpp/dwr/interface/PostManager.js'></script>
		<script type='text/javascript' src='/bpp/dwr/engine.js'></script>
		<script type='text/javascript' src='/bpp/dwr/util.js'></script>

		<script type='text/javascript' src="front/bounce/postBounce.js"></script>

		<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="js/ui/ui.core.js"></script>
		<script type="text/javascript" src="js/ui/ui.draggable.js"></script>
		<script type="text/javascript" src="js/ui/ui.resizable.js"></script>
		<script type="text/javascript" src="js/ui/ui.dialog.js"></script>
		<script type="text/javascript" src="js/ui/ui.selectable.js"></script>
		<script type="text/javascript" src="js/ui/ui.datepicker.js"></script>
	
	</head>

	<script type="text/javascript">
	//显示组下面的成员
	function showTeam(){
		var teamName=$("#teamName").val();
	//	var uid=$("#masterid").val();
	//	alert(teamName)
		EmpManager.selectByTeamLeadId(teamName,function(data){
				$("#selectable")[0].innerHTML='';
			for(var i=0;i<data.length;i++){
			$("#selectable")[0].innerHTML+='<li class="ui-widget-content">'+data[i].name+','+data[i].uid+'</li>';			
			}
		//	$("#team")[0].innerHTML+='</ol>';
			$("#team").dialog();
		$("#team").dialog('open');
		});		
	}
	//选择组下面的成员
	$(function() {
		//日期控件
	//	$("#time").datepicker();
	$("#time").datepicker({dateFormat: 'yy-mm-dd'}); 
		
		$("#selectable").selectable({
			stop: function(){
				var result = $("#select-result").empty();
					$("#menebersUid")[0].value=''
				$(".ui-selected", this).each(function(){
					var index = $(this).text()
					var uid=index.split(',')[1];
				
					$("#menebersUid")[0].value+=uid+','
					result.append(" #" + (index ));
				});
			}
		});
	});

	
	</script>
	<style type="text/css">
	#feedback { font-size: 1.0em; }
	#selectable .ui-selecting { background: #FECA40; }
	#selectable .ui-selected { background: #F39814; color: white; }
	#selectable { list-style-type: none; margin: 0; padding: 0; width: 60%; }
	#selectable li { margin: 2px; padding: 0.4em; font-size: 1.0em; height: 8px; }
	</style>
	
	<body>

		<table>
			<div style="display: none" id="toolbar">
			<tr>
				<td>
					<br>
					图号
					<select id="firstName">
						<option>
							先选择条件
						</option>
					</select>
				</td>

				<td>
					部门
					<select id="midName" onchange="init()">
						<option value="104A" selected="selected">
							选择车间
						</option>
						<option value="104A">
							104A
						</option>
						<option value="100">
							100
						</option>
						<option value="chukou">
							chukou
						</option>
						<option value="103">
							103
						</option>
						<option value="101">
							101
						</option>
						<option value="102">
							102
						</option>
					</select>
				</td>

				<td>
					工序
					<select id="lastName">
						<option>
							先选择条件
						</option>
					</select>
				</td>
			</tr>

			</div>

			<tr>
				<form action="personal.do?actionType=doInsert" method="post"
					onsubmit="return checkBlank(index)">
				<td style="background-image: url('')" align="center" class="altbg1">
					组长编号：
				</td>
				<td align="center" class="altbg1">
					<input id="masterid" name="masterid" onblur="checkMasterUid()" />
				</td>
				<td align="center" class="altbg1">
					组长姓名：
				</td>
				<td align="center" class="altbg1">
					<input id="masterName" disabled="disabled" />
				</td>
				<td>
				<select id="teamName" multiple="multiple" size="1"></select>
				</td>
				<td>
					<label onclick="showTeam()">
						选择组员
					</label>
				</td>
				<td align="center" class="altbg1">
					日期
					<input  id="time" name="time" />
				</td>
			</tr>
			<div id="team">
			<ol id="selectable">
			</ol>
			</div>
		</table>
		<p id="feedback">
		你选择的成员: <span id="select-result">没有</span>.
		</p>
		<input type="hidden" name="menebersUid" id="menebersUid">
		<table cellspacing="1" cellpadding="4" width="90%" class="tableborder"
			id="table3">
			<thead>
				<tr>
					<td id="head" colspan="8" class="header">
						员工日产量记录
					</td>
				</tr>
				<tr>
					<td align="center" class="altbg1">
						<b>员工编号</b>
					</td>
					<td align="center" class="altbg1">
						<b>姓名</b>
					</td>
					<td align="center" class="altbg1">
						<b>岗位代码</b>
					</td>
					<td align="center" class="altbg1">
						<b>岗位名称</b>
					</td>
					<td align="center" class="altbg1">
						<b>时间(小时)</b>
					</td>
					<td align="center" class="altbg1">
						<b>产出</b>
					</td>
					<td align="center" class="altbg1">
						<b>add</b>
					</td>
				</tr>
			</thead>


			<tbody>
				<tr>
					<input id="type" type="hidden" name="type" value="2">
					<td align="center" class="altbg1">
						<input name="items[0].tempolyee.uid" id="items[0].tempolyee.uid"
							onblur="checkUid(this,0)" size="5" />
					</td>
					<td align="center" class="altbg1">
						<input id="uname[0]" disabled="disabled" size="5" />
					</td>
					<td align="center" class="altbg1">
						<input name="items[0].tpost.id" id="items[0].tpost.id"
							onblur="checkPid(this,0)" onfocus="showToolBar(this)" size="25" />
					</td>
					<td align="center" class="altbg1">
						<input id="productName[0]" disabled="disabled" size="25" />
					</td>
					<td align="center" class="altbg1">
						<input name="items[0].worktime" id="items[0].worktime" size="5" />
					</td>
					<td align="center" class="altbg1">
						<input name="items[0].output" id="items[0].output" size="5" />
					</td>
					<td align="center" class="altbg1">
						<img src="images/add.gif" id="image" onclick="Tadd()">
					</td>
				</tr>
				<tr>
					<input type="submit" value="登记" />
				</tr>
				</from>
			</tbody>
		</table>
	</body>
</html>
