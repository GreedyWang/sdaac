<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ShowEmpUid.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type='text/javascript' src='/bpp/dwr/interface/projectManager.js'></script>
	 <script type='text/javascript' src='/bpp/dwr/interface/DepartmemntManager.js'></script>
 	 <script type='text/javascript' src='/bpp/dwr/engine.js'></script>
 	<script type='text/javascript' src='/bpp/dwr/interface/EmpManager.js'></script>

	<script type="text/javascript">
		function aaa()
		{
			DepartmemntManager.getAll(function(data){
				var select1=document.getElementById('departName')
				select1.size=0
			 	for(var i=0;i<data.length;i++)
			 	{
			 		var opt=document.createElement('Option');
			 		opt.innerText = data[i].name
					opt.value = data[i].id    
					select1.appendChild(opt);
		 		} 
			})
			//加载已有成员
			var projectId=${param.projectId }
			var param={workTeamId:projectId}
			projectManager.doSelectByCond(param,function(data){	
				if(data[0].meneberUids!=null)
				{
					for(var i=0;i<data[0].meneberUids.length;i++)
				 	{
				 		var opt=document.createElement('Option');
						opt.text = data[0].meneberNames[i]
						opt.value =data[0].meneberUids[i]
						var sel=document.getElementById('empUid')
						opt.selected=true;
						sel.options.add(opt)
				 	}
			 	}
			})
		}
		
		function getEmp(departID)
		{
			EmpManager.doSelectByDepartID(departID,function(data){
				var select2=document.getElementById('empName')
				select2.size=0
			 	for(var i=0;i<data.length;i++)
			 	{
			 		var opt=document.createElement('Option');
			 		opt.innerText = data[i].name
					opt.value = data[i].uid    
					select2.appendChild(opt);
					
		 		} 
		 	//	select2.size=data.length
			
			})
		}
		function showEmpUid(elem)
		{
			var ss=elem.options
			for(var i=0; i<ss.length;i++)
			{
				if(ss[i].value==elem.value)
				{
					document.getElementById('empUid').appendChild(ss[i])
				}
			}		
		}
		function removeInfo(elem)
		 {
		  	var ss=elem.options
		  	for(var i=0;i<ss.length;i++)
  			{
		  		if(elem.value==ss[i].value)
		  		{
		  			ss.remove(i)
		  		}
  			}			  
		  }
	
	</script>
  </head>
  
  <body onload="aaa()" style="width: 600;height: 400">
   <table>
   	<tr>
   	<td>部门</td>
   	<td>员工列表</td>
   	<td>成员列表</td>
   	<td></td></tr>
   	<tr>
	<td><select id="departName" onchange="getEmp(this.value)"></select></td>
    <td><select id="empName" multiple="multiple" size="10" ondblclick="showEmpUid(this)"></select></td>
	
	<form action="task.do?actionType=ManangerProjectMembers" method="post">

	<td>
	<input type="hidden" name="projectId" value="${param.projectId }">
    <select multiple="multiple" size="10" style="width: 50" name="membersUid" id="empUid" ondblclick="removeInfo(this)"></select>
    </td>
	<td><input type="submit" value="添加成员"> </td></tr>
    </form>
  	</table>
  </body>
</html>
