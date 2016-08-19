<%@ page language="java"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
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
  <style type="text/css">
  	.1{
  		color: red;
  	}
  </style>
  
  <script type="text/javascript">
  	 //下载保存excel
  	 function saveAsExcel(HeadName, DivName) {
            var s = "<center>" + HeadName + "</center>" + "\r\n";
            s +=document.getElementById(DivName).innerHTML;
            var xlsWindow = window.open("", "_blank", "width=1,height=1,scrollbars=no,toolbar=no");
            xlsWindow.document.write(s);
            xlsWindow.document.close();
            xlsWindow.document.execCommand('Saveas', true, '%homeDrive%\\Data.xls')
            xlsWindow.close();
        }
        
     //删除
     function dele(mergeTaxNo){
     	window.location.href='cw.do?actionType=deldeteLogs&mergeTaxNo='+mergeTaxNo
     }
     
     //批量删除
     function batchDel() {
     	if(window.confirm('确认批量删除?')) {
     	//	document.forms[1].action = 'cw.do?actionType=batchDel'	
     		document.forms[1].submit();
     	}
     }
     
     //对删除的发票回填新的金穗号
     function feedback(jinsuiNo){
     	window.location.href='cw/FeedBack.jsp?jinsuiNo='+jinsuiNo
     }
  </script>
  <body>
  <table>
  <form action="cw.do?actionType=searchLog" method="post">
  	<tr>
  	<td>员工工号</td>
  	<td><input type="text" name="cwLogs.operator"> </td>
  	<td>原发票号</td>
  	<td><input type="text" name="cwLogs.taxno"> </td>
  	<td>金穗发票号</td>
  	<td><input type="text" name="cwLogs.jinsuiNo"> </td>
  	<td><input type="submit" value="查询"></td>
  	<td><input type="button" value="下载" onclick="saveAsExcel('日志','context')"></td>
  	<td><input type="button" value="批量删除" onclick="batchDel()"></td>
  	</tr>
  <tr>
  	<td>客户号</td>
  	<td><input type="text" name="cwLogs.customerNo"> </td>
  	<td>分配号</td>
  	<td><input type="text" name="cwLogs.fenpeiNo"> </td>
  	<td>日期</td>
  	<td><input type="text" name="cwLogs.begin"> </td>
  	<td><input type="text" name="cwLogs.end"> </td>
  	<td>状态</td>
  	<td><select name="cwLogs.type">
  			
  			<option value="0" selected="selected">正确日志</option>
  			<option value="1">删除日志</option>
  			<option value="-1" >All</option>
  		</select> </td>
  	<td>合并发票号</td>
  	<td><input type="text" name="cwLogs.mergeTaxNo"> </td>
  	</tr>
  </form>
  </table>
  <div id="context">
  <form action="cw.do?actionType=batchDel" method="post" >
	  <table border="1">
	  <tr style="background-color: silver;">
	 		<td>选择</td>
	  		<td>日期</td>
	  		<td width="8%">合并发票号</td>
	  		<td width="8%">原发票号</td>
	  		<td>客户号</td>
	  		<td width="8%">分配号</td>
	  		<td>原发票金额</td>
	  		<td>原发票金额未加税</td>
	  		<td>税额</td>
	  		<td>金穗发票号</td>
			<td>金穗发票金额</td> 
			<td>金穗发票税额</td>  	 		
			<td>员工工号</td>		
			<td colspan="2">操作</td>		
	  </tr>
	  		<c:forEach items="${map}" var="map">
		  		<tr class="${map.value[0].type }">
		  			<td><input type="checkbox" name="cb" value="${map.key }"></td>
		  			<td ><fmt:formatDate value="${map.value[0].createDate }" pattern="yyyy-MM-dd hh:mm"/> </td>
		  			<td>${map.key }</td>
		  			<td></td>
		  			<td></td>
		  			<td></td>
		  			<td></td>
		  			<td></td>
		  			<td></td>
		  			<td><a target="blank" href="cw.do?actionType=showFeedbackLogs&feedbackNo=${map.value[0].feedbackNo }">${map.value[0].jinsuiNo }</a></td>
		  			<td>${map.value[0].jinsuiMoney }</td>
		  			<td>${map.value[0].jinsuiFaxMoney }</td>
	 				<td>${map.value[0].operator }</td>
	 				<c:if test="${map.value[0].type==0 }">
	 					<td><input type="button" value="删除" onclick="dele('${map.key }')" > </td>
	 				</c:if>
	 				<c:if test="${map.value[0].type==1 }">
	 					<td><input type="button" value="编辑" onclick="feedback('${map.value[0].jinsuiNo }')"> </td>
	 				</c:if>
	 				
		
		  		</tr>
		  		<c:forEach items="${map.value}" var="item">
		  			<tr>
		  				<td></td>
		  				<td>c</td>
		  				<td>${item.taxno }</td>
		  				<td>${item.customerNo }</td>
		  				<td>${item.fenpeiNo }</td>
		  				<td><fmt:formatNumber pattern="#.00">${item.money }</fmt:formatNumber></td>
		  				<td> <fmt:formatNumber pattern="#.00">${item.money/1.17 }</fmt:formatNumber>  </td>
		  				<td> <fmt:formatNumber pattern="#.00">${item.money/1.17*0.17 }</fmt:formatNumber></td>
		  				
	  				</tr>
		  		</c:forEach>		
	  			
	  		</c:forEach>
	  </table>
	  </form>
	  </div>
  </body>
</html>
