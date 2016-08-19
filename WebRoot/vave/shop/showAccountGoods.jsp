<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
    <script type='text/javascript' src='/bpp/dwr/interface/goodsManager.js'></script>
  <script type='text/javascript' src='/bpp/dwr/engine.js'></script>
  
  <!-- 
   private Integer id;
     private Goods goods;
     private Tempolyee emp= new Tempolyee();
     private Date datetime;
     private Integer quantity;
     private String beginTime;
     private String endTime;
   -->
	<script type="text/javascript">
		function buy(){
			window.location.href='account.do?actionType=confirm&accountId='+'${account.id }';
		}
		
		function goodsDelete(id,index){
			var flag = window.confirm('确认删除');
			if(flag){
				goodsManager.deleteShoppingCar(id,function(data){
					document.getElementById('table1').deleteRow(index+1)
				})
			}
		}
	</script>
  <body>
   <table id="table1">
   	<tr>
   		<td>日期</td>
  		<td>${account.datetime }</td>
  		<td>状态<br></td>  
   		<td>${account.stateNames }</td>  	
   	</tr>
 
   	
   	<c:forEach items="${rs}" var="item" varStatus="index">
   		<tr>
	   		<td>${item.goods.name }</td>
	   		<td>${item.goods.quantity }</td>
	   		<td><input type="button" value="delete" onclick="goodsDelete('${item.id }','${index.index }')"> </td>
   		</tr>
   	</c:forEach>
   	<tr>
   		<td><input type="button" value="购买" onclick="buy()"> </td>
   	</tr>
   </table>
  </body>
</html>
