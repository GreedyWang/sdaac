<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'EmpRightManager.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script type='text/javascript' src='/bpp/dwr/engine.js'></script>
    <script type='text/javascript' src='/bpp/dwr/interface/EmpManager.js'></script>
    <script type='text/javascript' src='/bpp/dwr/interface/userRoleManager.js'></script>
    
  </head>
  <script type="text/javascript">
  
  function getDetails(elem)
  {
	var empID=elem.value;
	EmpManager.doSelectGetEmpDetails(empID,function(data){
		var roles=document.getElementById('roles')
			roles.options.length=0;
		if(data!=null)
		{
			document.getElementById('empName').innerText=data.name+','+data.tdepartment.name ;	
			
			userRoleManager.doSelectByUid(empID,function(data2){
			var theEmpRoles=''
			for(var i=0;i<data2.length;i++)
			{
				addRole(data2[i],data2[i])
			}
			roles.size=data2.length;
				
			})
		}
		else
		{
			document.getElementById('proposalPersonName').innerHTML='无此人';
		}
	});
  }
  function addRole(text,value)
  {
	var roles=document.getElementById('roles')
	var opt=document.createElement('Option');
	opt.text = text
	opt.value =value
	roles.options.add(opt);

	roles.size+=1
  }
  function removeRole(elem)
  {
  	var roles=document.getElementById('roles').value
  	var uid=document.getElementById('empUid').value;
  	userRoleManager.deleteRightsExt(uid,roles,function(data){
  			for(var i=0;i<roles.length;i++)
  			{
		  		if(elem.value==roles[i].value)
		  		{
		  			roles.remove(i)
		  		}
  			}
  	})
  	
  
  }
  </script>
  
  <body>
   	<table>
   		<form action="rights.do?actionType=addRights" method="post">
   		<tr>
   		<td><label>员工编号</label></td>
   		<td><input name="empUid" id="empUid" type="text" onblur="getDetails(this)"></td>
   		<td><label id="empName"></label> </td>
   		</tr>
   		<tr>
   		<td><label>拥有的角色</label> </td>
   		<td><select name="roles" id="roles" ondblclick="removeRole(this)"></select> </td>   		
   		<td><input type="submit" value="提交"></td>
   		</tr>
   		</form>
   		<tr><td>所有角色</td><td>添加角色</td></tr>
   		<tr><td>
   		<select id="allRoles" size="30"  ondblclick="addRole(this.value,this.value)">
   		<c:forEach items="${allRoles}" var="item">
   		<option value="${item.roleid }">${item.roleName }</option> 
   		</c:forEach>
   		</select></td>
   		</tr>
   	</table>
  </body>
</html>
