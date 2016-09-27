<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
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


		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<script type='text/javascript'
			src='dwr/interface/prFormListManager.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="ext/ext-all.js"></script>
		<script type="text/javascript"
			src="epor/baseInfo/js/util/loadProjectData.js"></script>
		<script type="text/javascript" src="epor/approve/js/writePN.js"></script>

	</head>
	<script type="text/javascript">
      var PNTip
      function bb(index){
      	PNTip(index);
      }
      
      function cc(){      	
  		if(document.getElementById('opoion').value == 2 ) {
  			window.location.href = 'epor.do?actionType=goTOSAP&type=p&id='+'${prForm.id}'
  		} else {
  			if(window.confirm('确认已填写订单编号和预计入厂/入库日期?')) {
  				//document.forms[0].submit();
  				document.getElementById('aaxs').submit();
  			}
  		}    		     
      }
      
      function dd() {
      	   if(window.confirm('确认由系统外转系统内?')) {
  				window.location.href = 'epor.do?actionType=goTOSAP&type=e&id='+'${prForm.id}'
  			} 
      }
      </script>

	<body>
				
		<form action="epor.do?actionType=doApprovePR" method="post" id="aaxs">
			<input type="hidden" name="approvedPR.prPrForm.id"
				value="${prForm.id }">
			<input type="hidden" name="approvedPR.prPrForm.version"
				value="${prForm.version }">
			<input type="hidden" name="approvedPR.prPrForm.state"
				value="${prForm.state }">
			<input type="hidden" name="approvedPR.tempolyee.uid"
				value="${logineduser.emp.uid }">
			<input type="hidden" name="approvedPR.prPrForm.total"
				value="${prForm.total}">
			<input type="hidden" name="approvedPR.prPrForm.isPlan"
				value="${prForm.isPlan}">
			<input type="hidden" name="approvedPR.prPrForm.isCapital"
				value="${prForm.isCapital}">
						<input type="hidden" name="approvedPR.prPrForm.source"
				value="${prForm.source }">
			<table>
				<tr>
					<td>
						审批意见
					</td>
					<td>
						意见说明
					</td>

				</tr>
				<tr>
					<td>
						<select id="opoion" name="approvedPR.isApproved">
							<option value="1">
								批准
							</option>
							<option value="0">
								不批准
							</option>
					
					</td>
					<td>
						<textarea rows="" cols="100%" name="approvedPR.context"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="提交" onclick="cc()">
					</td>
					<td>
						<input type="button" value="由系统外转系统内" onclick="dd()">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
