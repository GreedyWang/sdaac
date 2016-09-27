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
	<script type="text/javascript" src="calc/calendar.js"></script>
	<script type="text/javascript" src="calc/calendar-en.js"></script>
	<script type='text/javascript' src="js/cal.js"></script>
	<link type="text/css" rel="stylesheet" href="calc/calendar-system.css"/>
  </head>
  <body>
  <form action="labAction.do?actionType=doApply" method="post">
  <input type = "hidden" name="type" value="inner">
  	<table cellspacing="1" cellpadding="4" width="90%" BORDER="1"
			class="tableborder" >
  		<tr class="header">
  			<td width ="25%"> <img src=""> </td>
  			<td width ="50%" colspan="2" align="center" style="font-size:16">检测委托合同单</td>
  			<td width ="25%" style="font-size:10"> 	表式编号：P4.4-RF01<br>
					生效日期：2007-11-01<br>
					Page 1 of 1
  			</td>
  		</tr>
  		<tr>
  			<td> 内部编号:</td>
  			<td width ="25%"></td>
  			<td width ="25%">合同单编号:</td>
  			<td > </td>
  		</tr>
  		<tr>
  			<td  colspan="4" class="altbg1" >管理信息</td>
  	
  		</tr>
  		<tr>
  			<td>委托人</td>
  			<td>委托部门</td>
  			<td>委托日期</td>
  			<td>电话</td>
  		</tr>
  		<tr>
  			<td>主管批准</td>
  			<td>日期</td>
  			<td>电话</td>
  			<td>意见</td>
  		</tr>
  		<tr>
  			<td>部门批准</td>
  			<td>日期</td>
  			<td>电话</td>
  			<td>意见</td>
  		</tr>
  		<tr>
  			<td>实验室经理批准</td>
  			<td>日期</td>
  			<td>电话</td>
  			<td>意见</td>
  		</tr>
  		<tr>
  			<td>主管意见</td>
  			<td>日期</td>
  			<td>电话</td>
  			<td>备注</td>
  		</tr>
 		<tr>
  			<td colspan="4" class="altbg1" >检测样品信息</td>
  		</tr>
  		<tr>
  			<td>试验件名称</td>
  			<td><input type="text" name="item.goodsName"> </td>
  			<td>试验件型号/图号</td>
  			<td><input type="text" name="item.goodsFigure"> </td>
  		</tr>
  			<tr>
  			<td>试验件编号</td>
  			<td><input type="text" name="item.goodsSn"></td>
  			<td></td>
  			<td> </td>
  		</tr>
  		<tr>
  			<td>试验目的</td>
  			<td colspan="3"> <input type="text" name="item.testingAim" size="150"> </td>
  		</tr>
  		<tr>
  			<td>试验件描述</td>
  			<td colspan="3"> <input type="text" name="item.testingDesc" size="150"> </td>
  		</tr>
		<tr>
  			<td>制造单位</td>
  			<td><input type="text" name="item.factory"></td>
  			<td></td>
  			<td> </td>
  		</tr>
  		<tr>
  			<td colspan="4" class="altbg1"  >检查要求/其他信息</td>
  		</tr>
  		<tr>
  			<td>试验项目名称</td>
  			<td><input type="text" name="item.projectName"></td>
  			<td></td>
  			<td> </td>
  		</tr>
  		<tr>
  			<td>依据标准名称及编号</td>
  			<td><input type="text" name="item.standard"></td>
  			<td>附加试验条件及判断依据描述(可附件)</td>
  			<td><input type="text" name="item.standardFile"></td>
  		</tr>
  		<tr>
  			<td>要求完成日期</td>
  			<td><input onfocus="calShow('requiredFinishDate');"　id="requiredFinishDate"　type="text" name="requiredFinishDate" ></td>
  			<td>预计试验持续时间</td>　
  			<td><input onfocus="calShow('costTime');"　id="costTime"　type="text" name="costTime" ></td>
  		</tr>
  		<tr>
  			<td>需出具《检查报告》</td>
  			<td><input type="checkbox" name="item.hasReport" value="1">是 </td>
  			<td>预计试验件送达日期</td>
  			<td><input onfocus="calShow('sendedDate');"　id="sendedDate"　type="text" name="sendedDate" ></td>
  		</tr>
  		<tr>
  			<td>试验检查后处理说明</td>
  			<td colspan="3"> <input type="text" name="item.dealinfo" size="150"> </td>
  		</tr>
  		<tr>
  			<td>保密和保护所有权要求</td>
  			<td colspan="3"> <input type="text" name="item.secrecyRequire" size="150"> </td>
  		</tr>
  		<tr>
  			<td>传送检查结果要求</td>
  			<td colspan="3"> <input type="text" name="item.a1" size="150"> </td>
  		</tr>
  		<tr>
  			<td>根据检查结果提供评价意见和建议要求</td>
  			<td colspan="3"> <input type="text" name="item.a2" size="150"> </td>
  		</tr>
  		<tr>
  			<td>在检查报告中提供测量不确定度要求</td>
  			<td colspan="3"> <input type="text" name="item.a3" size="150"> </td>
  		</tr>
  		<tr>
  			<td>其他特殊要求</td>
  			<td colspan="3"> <input type="text" name="item.a4" size="150"> </td>
  		</tr>
  		<tr>
  			<td colspan="4" class="altbg1" >以下项目由实验室填写,并已由委托联系人确认</td>
  		</tr>
  		<tr> 
  			<td>主要检测设备名称</td>
  			<td><input type="text" name="item.facilityId"></td>
  			<td>试验件是否符合检测要求?</td>
  			<td><input type="text" name="item.fis"> </td>
  		</tr>
  		<tr>
  			<td>试验安排文件路径</td>
  			<td><input type="text" name=""></td>
  			<td>费用</td>
  			<td><input type="text" name="item.totalmoney"></td>
  		</tr>
  		<tr>
  			<td>接收人确认</td>
  			<td><input type="text" name=""></td>
  			<td>接收日期</td>
  			<td><input type="text" name=""> </td>
  		</tr>
  		<tr>
  			<td><input type="submit" value="submit"></td>
  			<td></td>
  			<td></td>
  			<td></td>
  		</tr>
  	</table>
  	</form>
  </body>
</html>
