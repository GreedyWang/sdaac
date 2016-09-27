<%@ page language="java"  pageEncoding="utf-8"%>
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
  <script type="text/javascript">
  	 function cc(){      	
  			if(window.confirm('确认回退到财务?')) {
  				window.location.href = 'epor.do?actionType=goTOSAP&type=f&id='+'${prForm.id}'
  			}  		     
      }
  </script>
  <body>
    <form action="epor.do?actionType=doApprovePR" enctype="multipart/form-data" method="post">
   <input type="hidden" name="approvedPR.prPrForm.id" value="${prForm.id }">
   <input type="hidden" name="approvedPR.prPrForm.state" value="${prForm.state }">
   <input type="hidden" name="approvedPR.prPrForm.version" value="${prForm.version }">
   <input type="hidden" name="approvedPR.tempolyee.uid" value="${logineduser.emp.uid }">
   <input type="hidden" name="approvedPR.prPrForm.tempolyeeByBuyerId" value="${prForm.tempolyeeByBuyerId }">
   <input type="hidden" name="approvedPR.prPrForm.source" value="${prForm.source }">
   <input type="hidden" name="approvedPR.prPrForm.isCapital" value="${prForm.isCapital }">
   <table> 
  <tr>
   	<td>SAP_SN:<input type="text" name="approvedPR.prPrForm.prsn" > </td>
   
  </tr>
  <tr>
   	<td>
						<select name="approvedPR.isApproved">
							<option value="1">
								批准
							</option>
							<option value="0">
								不批准
							</option>
							<option value="2">
								需要解释
							</option>
						</select>
					</td>
					<td colspan="5">
						<textarea cols="100%" name="approvedPR.context"></textarea>
					</td>
					 </tr>
					 <tr><td><input type="submit" value="提交/submit"> </td>
   </tr>
   <tr>
      	 	<td><input type="button" value="回退到财务" onclick="cc()"> </td>

  

   </tr>
   </tr>
   </table>
   </form>
  </body>
</html>
