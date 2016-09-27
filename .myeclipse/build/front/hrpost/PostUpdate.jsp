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
    <title>岗位管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
	<script type='text/javascript' src='/bpp/dwr/interface/PostManager.js'></script>
  	<script type='text/javascript' src='/bpp/dwr/engine.js'></script>
  	</head>
  <script type="text/javascript">
  function del(postid)
  {
  	var key=window.confirm("确定删除");
  
  	if(key)
  	{
	  	alert(postid);
	  	PostManager.doDelByid(postid,function (data){
	  	alert("岗位"+postid+"删除成功");
	  	});
  	}
  }
  function update(postid,postName)
  {
  	var url='${basePath}'+'front/updatePost.jsp?postid='+postid+'&postName='+postName;
		
		//alert(url);
	    window.open(url, "xxx", "toolbars=0, location=0, statusbars=0, menubars=0,width=700,height=300,scrollbars=1");
  	
  }
  </script>
  <body>
  PostUpdate.jsp
	<table>
   <tr>
   <form action="post.do?actionType=doSelectPost"  name="selectCustomerOrder"  method="post">
   <td>岗位编号</td>
   <td><input type="text" name="item.id"></td>
   <td><input type="submit"  value="查询"></td>
   </form></tr>

   </table>
<table cellspacing="1" cellpadding="4" width="90%"
			class="tableborder" id="table3">
   <thead>
   <tr>
				<td colspan="8" class="header">岗位管理 
				</td>
  </tr>
   <tr>
   <td align="center" class="altbg1"><b>岗位编号</b></td>
   <td align="center" class="altbg1"><b>岗位名称</b></td>
   <td align="center" class="altbg1"><b>岗位类型</b></td>
  
     <td colspan="2" align="center" class="altbg1"><b>操作</b></td>
   </tr> </thead>
  <tbody>
<c:if test="${postList!=null }">
  <form id="form${postList.id }" method="post" ">
  <input name="orderid" type="hidden" value="${postList.id }">
 <tr>  
	<td align="center" class="altbg1">${postList.id }</td>
   <td align="center" class="altbg1">${postList.productName }</td>
   <td align="center" class="altbg1">${postList.tpostType.type }</td>  

      <td align="center" class="altbg1"><img src="images/del.gif" onclick="del('${postList.id }')"/></td> 
       <td align="center" class="altbg1"><img src="images/update.gif" onclick="update('${postList.id }','${postList.productName }')"/></td> 
   </tr> 
   </from>
 </c:if>
 <c:if test="${empList==null}">
	无此岗位<br style="color: red" >
 </c:if>
<tr >
</tr>
   </tbody>
   </table>
  </body>
</html>
