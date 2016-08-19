<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

		<title>小组管理</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">	
		<link type="text/css" href="css/themes/base/ui.all.css" rel="stylesheet" />
		<script type='text/javascript' src='/bpp/dwr/interface/EmpManager.js'></script>
		<script type='text/javascript' src='/bpp/dwr/engine.js'></script> 		 	
		<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="js/ui/ui.core.js"></script>
		<script type="text/javascript" src="js/ui/effects.core.js"></script>
		<script type="text/javascript" src="js/ui/effects.blind.js"></script>
		<script type="text/javascript" src="js/ui/ui.draggable.js"></script>
		<script type="text/javascript" src="js/ui/ui.resizable.js"></script>
		<script type="text/javascript" src="js/ui/ui.dialog.js"></script>
	
		<script type="text/javascript" src="js/ui/effects.highlight.js"></script>
		<script type="text/javascript" src="js/external/bgiframe/jquery.bgiframe.js"></script>
		<style type="text/css">
			body { font-size: 62.5%; }
			label, input { display:block; }
			input.text { margin-bottom:12px; width:95%; padding: .4em; }
			fieldset { padding:0; border:0; margin-top:25px; }
			h1 { font-size: 1.2em; margin: .6em 0; }
			div#users-contain {  width: 350px; margin: 20px 0; }
			div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
			div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
			.ui-button { outline: 0; margin:0; padding: .4em 1em .5em; text-decoration:none;  !important; cursor:pointer; position: relative; text-align: center; }
			.ui-dialog .ui-state-highlight, .ui-dialog .ui-state-error { padding: .3em;  }	
		</style>
	</head>
	<script type="text/javascript">
		//删除员工
		function remove2(elem)
		{
		  	EmpManager.deleteWorkerTeam(document.getElementById('teamId').value, elem.value,function(data){
		  		
		  		var ss=elem.options
			  	for(var i=0;i<ss.length;i++)
	  			{
			  		if(elem.value==ss[i].value)
			  		{
			  			ss.remove(i);
			  			alert(elem.value+'已经删除！')
			  		}
	  			}		
		  	})

		  
		    
		}
		function moreInfo(elem){
					showByUid(elem.value)				
					document.getElementById('teamId').value=elem.value
					document.getElementById('lable1').innerHTML=elem.innerText;

		}
		//得到组名
		function getTeamName(leaderId){
				EmpManager.selectTeamByLead(leaderId,function(data){
			    var sel=document.getElementById('teamList');
			    sel.innerHTML=''
			    for(var i=0;i<data.length;i++){
					sel.innerHTML+="<li value="+data[i].id+" onclick='moreInfo(this)'>"+data[i].teamName+'</li>';			   
			    }
		})
		}
		
		
		 //找组长下的所有成员
		 function showByUid(teamId){
		 		EmpManager.selectByTeamLeadId(teamId,function(data){
		 		document.getElementById("empName").options.length=0
		 				for(var i=0;i<data.length;i++)
  						{
  							var opt=document.createElement('Option');
			 				opt.innerText = data[i].name
							opt.value = data[i].uid   
		  					document.getElementById("empName").appendChild(opt)
  						}	
		 			
		 		})
		 
		 }
		 //添加员工
		 function addWorker(elem){		
			var ss=elem.options
			for(var i=0; i<ss.length;i++)
			{
				if(ss[i].value==elem.value)
				{
					document.getElementById("empName").appendChild(ss[i])
				}
			}		
		 }
		 //得到worker
		 function getWorker(param){
			 var p={uid:param};
			 document.getElementById('empUid').options.length=0
		 	EmpManager.selectFuzzy(p,function(data){
		 			for(var i=0;i<data.length;i++)
  						{
  							var opt=document.createElement('Option');
			 				opt.innerText = data[i].name
							opt.value = data[i].uid   
							//document.getElementById('empUid').options.add(opt)
		  					
		  					document.getElementById('empUid').appendChild(opt)
  						}	
		 	})
		 }
		 function createTeam(){
		 	
		 }
		 
	

	$(function() {
		
		var name = $("#name"),
			email = $("#email"),
			allFields = $([]).add(name).add(email),
			tips = $("#validateTips");

		function updateTips(t) {
			tips.text(t).effect("highlight",{},1500);
		}

		function checkLength(o,n,min,max) {

			if ( o.val().length > max || o.val().length < min ) {
				o.addClass('ui-state-error');
				updateTips("Length of " + n + " must be between "+min+" and "+max+".");
				return false;
			} else {
				return true;
			}

		}
		
		
		function checkLeaderId(leaderUid) {
		
			EmpManager.doSelectById(leaderUid,function(data){
				updateTips('')
				if(data=='[]'){
					updateTips('没有此员工号')
				}else{
					var p={
						teamName:email.val(),
						leader:{uid:leaderUid}
					}
					
					EmpManager.createTeamWork(p,function(data){	
							alert('创建成功！')				
					})
				}
			})
		
		}
	
		
		$("#dialog").dialog({
			bgiframe: true,
			autoOpen: false,
			height: 500,
			modal: true,
			buttons: {
				'创建': function() {
				//	var bValid = true;
					allFields.removeClass('ui-state-error');

					//bValid = bValid && ;
				//	bValid = bValid && checkLength(email,"email",6,80);
				checkLeaderId(name.val())
				
				},
				Cancel: function() {
					$(this).dialog('close');
				}
			},
			close: function() {
				allFields.val('').removeClass('ui-state-error');
			}
		});
		
		
		
		$('#create-user').click(function() {
			$('#dialog').dialog('open');
		})
		.hover(
			function(){ 
				$(this).addClass("ui-state-hover"); 
			},
			function(){ 
				$(this).removeClass("ui-state-hover"); 
			}
		).mousedown(function(){
			$(this).addClass("ui-state-active"); 
		})
		.mouseup(function(){
				$(this).removeClass("ui-state-active");
		});

	});
	// onsubmit 
	function check(){
		var empName=document.getElementById('empName').value
		if(document.getElementById('teamId').value==''){
			alert('请选择组！')
			return false;
		}else if(empName==''){
			alert('请选择需要添加的成员！')
			return false;
		}else{
			return true;
		}
	}
	</script>
	
	
	
	<body >


<div id="dialog" title="Create new user">
	

	
	<p id="validateTips"></p>
	
	<fieldset>
		<label for="name">组长编号</label>
		<input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" />
		<label for="email">组名</label>
		<input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all" />
		
	</fieldset>

</div>
	<form action="employee.do?actionType=createTeamWork" method="post" onsubmit="return check()">
		<table>
			<tr>
				<td>
					组长编号
				</td>
				<td>
					<input id="leaderId"  name="item.teamWork.leader.uid" type="text" onblur="getTeamName(this.value)">
					<img id="create-user" src="images/icons/add.png" >新建组
				</td>
			</tr>
			<tr>
			<td><ol id="teamList"></ol></td>
			</tr>
			<tr>
				<td>
					<label id="lable1" >组员</label>
				</td>
				<td>
					加入员工
				</td>
			</tr>
			<input type="hidden" name="teamId" id="teamId">
				<tr>
							<td></td>
							<td >
								<input type="text" size="4" id="workerUid" onblur="getWorker(this.value)">
							</td>
				</tr>
				<tr>
					
							<td>
								<select id="empName" name="uids" multiple="multiple" size="10" 
									ondblclick="remove2(this)"></select>
							</td>
						
						
							<td>
								<select multiple="multiple" size="10" style="width: 100"
									 id="empUid" ondblclick="addWorker(this)">
								</select>
							</td>
					
				</tr>
			</table>
		<table>
			<tr>		
			<td><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
	</body>
</html>
