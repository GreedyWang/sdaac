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
 	<script type='text/javascript' src='/bpp/dwr/interface/bpManager.js'></script>
  	<script type='text/javascript' src='/bpp/dwr/engine.js'></script>
  	</head>
  <script type="text/javascript">
  function del(id,remark)
  {
  	var key=window.confirm("确定删除");
  	if(key)
  	{
	  	var param={
	  		id : id,
	  		remark : remark
	  	}
	  	bpManager.deleteBouncePost(param,function (data){
	  		alert("工序"+postid+"删除成功");
	  	});
  	}
  }
  function update(postid,postName,deviceNum)
  {
 		 var param={
	  		postId:postid,
	  		postName:postName,
	  		deviceNum:deviceNum,
	  		remark:''
	  	}
	  	alert("功能未完成");
	  //	bpManager.update(param,function (data){
	  //		alert("工序"+postid+"更新成功");
	 // 	})
  }
  </script>
  <body>

	<table>
   <tr>
   <form action="figureAndPostId.do?actionType=selectBouncePost"    method="post">
   <td>工序编号</td>
   <td><input type="text" name="bp.postId"></td>
   <td>工序名称</td>
   <td><input type="text" name="bp.postName"></td>
   <td>车间号</td>
   <td><input type="text" name="bp.remark"></td>
   <td><input type="submit"  value="查询"></td>
   </form></tr>

   </table>
<table cellspacing="1" cellpadding="4" width="90%"
			class="tableborder" id="table3">
   <thead>

  
  	<tr>
 	<td align="center" class="altbg1">工序号</td>
 	<td align="center" class="altbg1">工序名</td>
 	<td align="center" class="altbg1">设备数</td>
 	
  
     <td colspan="2" align="center" class="altbg1"><b>操作</b></td>
   </tr> </thead>
  <tbody>
<c:forEach items="${BouncePosts}" var="item">
 <tr>  
	<td align="center" class="altbg1">${item.postId }</td>
    <td align="center" class="altbg1">${item.postName }</td>
    <td align="center" class="altbg1">${item.deviceNum }</td>  
    <td align="center" class="altbg1"><img src="images/del.gif" onclick="del('${item.id }',${item.remark })"/></td> 
    <td align="center" class="altbg1"><img src="images/update.gif" onclick="update('${item.postId }','${item.postName }',${item.deviceNum })"/></td> 
   </tr> 
</c:forEach>

   </tbody>
   </table>
  </body>
</html>
