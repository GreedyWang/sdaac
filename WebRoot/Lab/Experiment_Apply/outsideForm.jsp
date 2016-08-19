<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
	<link rel="stylesheet" type="text/css" href="css/vave.css">
	
  </head>
  <body>
  	<table cellspacing="1" cellpadding="4" width="90%" BORDER="1"
			class="tableborder" >
  		<tr class="header">
  			<td width ="25%"> <img src=""> </td>
  			<td width ="50%" colspan="4" align="center" style="font-size:16">环模试验（布点）委托单及工作流程记录</td>
  			<td width ="25%" style="font-size:10"> 	表式编号：P4.4-RF04<br>
					生效日期：2007-11-01<br>
					Page 1 of 1					
  			</td>
  		</tr>
  		<tr>
  			<td></td>
  			<td ></td>
  			<td ></td>
  			<td ></td>
  			<td width ="20%">合同单编号:</td>
  			<td ></td>
  		</tr>
  		<tr>
  			<td  colspan="6" class="altbg1" >车辆情况（委托方提供的试验车辆应确保无故障，由委托人填写）：</td> 	
  		</tr>
  		<tr>
  			<td>型号/识别号</td>
  			<td></td>
  			<td>驱动形式</td>
  			<td></td>
  			<td>制造厂</td>
  			<td></td>
  		</tr>
  		<tr>
  			<td>尺寸(L/W/G)</td>
  			<td></td>
  			<td>门自锁功能</td>
  			<td>有□ 无□</td>
  			<td>行使里程</td>
  			<td></td>
  		</tr>
  		<tr>
  			<td>冷却液/油 液位</td>
  			<td>已正常□</td>
  			<td>油漆表面</td>
  			<td></td>
  			<td>车辆到达日期</td>
  			<td></td>
  		</tr>
  		<tr>
  			<td>钥匙（把）</td>
  			<td>1□  2□</td>
  			<td>项目编号</td>
  			<td></td>
  			<td>要求试验日期	</td>
  			<td></td>
  		</tr>
  		<tr>
  			<td>燃油</td>
  			<td>汽油  93#□ 97#□  其他：</td>
  			<td></td>
  			<td>柴油  0#□    其他：</td>
  		</tr>
 		<tr>
  			<td colspan="6" class="altbg1" >检测样品信息</td>
  		</tr>
  		<tr>
  			<td>试验（布点）内容及产品情况（可另加附页）</td>
  			<td></td>
  			<td></td>
  			<td> </td>
  		</tr>
  			<tr>
  			<td>道路曲线及布测点清单（可另加附页）</td>
  			<td></td>
  			<td></td>
  			<td> </td>
  		</tr>
  		<tr>
  			<td>新环模台□  老环模台□   都可以□</td>
  			<td></td>
  			<td></td>
  			<td> </td>
  		</tr>
  		<tr>
  			<td>   委托人:           日期：</td>
  			<td></td>
  			<td></td>
  			<td> </td>
  		</tr>
  		<tr>
  			<td colspan="6" class="altbg1"  >车辆试验（装拆车）安排情况（由试验室管理人员填写）：</td>
  		</tr>
  		<tr>
  			<td>布置测点日期</td>
  			<td>年     月     日～</td>
  			<td>年     月     日</td>
  			
  		</tr>
  		<tr>
  			<td>装拆车日期</td>
  			<td>年     月     日～</td>
  			<td>年     月     日</td>
  		</tr>
  		<tr>
  			<td>试验日期</td>
  			<td>年     月     日～</td>
  			<td>年     月     日</td>
  			<td>在 新环模台□  老环模台□</td>
  		</tr>
  		<tr>
  			<td>管理员:</td>
  			<td></td>
  			<td>日期：</td>
  			<td> </td>
  		</tr> 	
  		<tr>
  			<td colspan="6" class="altbg1" >车辆检查及布测点等情况（由操作员填写）：</td>
  		</tr>
  		<tr>
  			<td>发动机油位</td>
  			<td></td>
  			<td>冷却液液位</td>
  			<td> </td>
  			<td>行使里程</td>
  			<td></td>
  		</tr>
  		<tr>
  			<td>油表位置</td>
  			<td></td>
  			<td>轮胎汽压</td>
  			<td> </td>
  			<td>油漆表面</td>
  			<td></td>
  		</tr>
  		<tr>
  			<td>加油</td>
  			<td>	是□  否□</td>
  			<td>洗车</td>
  			<td>	是□  否□ </td>
  			<td>怠速</td>
  			<td>	是□  否□ </td>
  		</tr>
  		<tr>
  			<td>布置测点/车辆拆装</td>
  			<td>  完成□</td>
  			<td>操作员签字：</td>
  			<td> 日期： </td>
  		</tr>
  		<tr>
  			<td>车辆试验情况（由操作员填写）：</td>

  		</tr>
  		<tr>
  			<td>试验（装拆车）过程中是否有异常情况（如有请具体说明，可另加附页）</td>
  			<td>	是□  否□ </td>
  			<td>试验数据是否发送 </td>
  			<td>	是□  否□ </td>
  		</tr>
  	</table>
  </body>
</html>
