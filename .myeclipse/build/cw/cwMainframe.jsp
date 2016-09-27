<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cwMF.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
	function txtInfoFormSubmit()
	{
		document.getElementById('txtInfoForm').submit();
	}
	function downLoad()
	{
		window.location.href.target='blank'
		window.location.href='cw.do?actionType=Download';
	}
	</script>
  </head>
  
  <body>
  <form id="searchToolBar" action="cw.do?actionType=search" method="post">
   <table>
   <tr>
   <td>No:<input type="text" name="tax.no"> </td>
   <td>购货单位名称:<input type="text" name="tax.buyer.name"></td>
   <!--   <td>开票日期: <input type="text" name="">-<input type="text" name=""></td> -->
 
   <td><input type="submit" value="搜索"></td>
   <td><input type="button" value="合并" disabled="disabled" onclick="txtInfoFormSubmit()"></td>
   <td><input type="button" value="删除" onclick="txtInfoFormSubmit()"></td>
    <td><input type="button" value="导出" onclick="downLoad()"></td>
   </tr>
   <tr>
   <td colspan="3"><hr></td></tr>
   <tr>
   <td colspan="3">备注<input type="text" name="tax.seller.remarks"> </td>
   <td><input type="button" value="清空" onclick="javascript:window.location.href='cw.do?actionType=clearRemark'"> </td>
   </tr>
   </table>
   </form>
   
   <div style="border:1px solid black; padding:5px;height:100%;color:black;overflow: auto">
   <jsp:include page="cwTxtInfo.jsp"></jsp:include>
   </div>
  </body>
</html>
