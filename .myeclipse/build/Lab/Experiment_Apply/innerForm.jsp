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
  			<td>委托人 ${rs.applicant.name }</td>
  			<td>委托部门 ${rs.applicant.tdepartment.name } </td>
  			<td>委托日期 ${rs.applicantDate }</td>
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
  			<td> ${rs.goodsName }</td>
  			<td>试验件型号/图号</td>
  			<td> ${rs.goodsFigure }</td>
  		</tr>
  			<tr>
  			<td>试验件编号</td>
  			<td>${rs.goodsSn }</td>
  			<td></td>
  			<td> </td>
  		</tr>
  		<tr>
  			<td>试验目的</td>
  			<td>${rs.testingAim }</td>
  			<td></td>
  			<td> </td>
  		</tr>
  		<tr>
  			<td>试验件描述</td>
  			<td>${rs.testingDesc }</td>
  			<td></td>
  			<td> </td>
  		</tr>
		<tr>
  			<td>制造单位</td>
  			<td>${rs.factory }</td>
  			<td></td>
  			<td> </td>
  		</tr>
  		<tr>
  			<td colspan="4" class="altbg1"  >检查要求/其他信息</td>
  		</tr>
  		<tr>
  			<td>试验项目名称</td>
  			<td>${rs.projectName }</td>
  			<td></td>
  			<td> </td>
  		</tr>
  		<tr>
  			<td>依据标准名称及编号</td>
  			<td>${rs.standard }</td>
  			<td>附加试验条件及判断依据描述(可附件)</td>
  			<td> ${rs.standardFile }</td>
  		</tr>
  		<tr>
  			<td>要求完成日期</td>
  			<td>${rs.requiredFinishDate }</td>
  			<td>预计试验持续时间</td>
  			<td> ${rs.costTime }</td>
  		</tr>
  		<tr>
  			<td>需出具《检查报告》</td>
  			<td>${rs.hasReport }</td>
  			<td>预计试验件送达日期</td>
  			<td> ${rs.sendedDate }</td>
  		</tr>
  		<tr>
  			<td>试验检查后处理说明</td>
  			<td>${rs.dealinfo }</td>
  			<td></td>
  			<td> </td>
  		</tr>
  		<tr>
  			<td>保密和保护所有权要求</td>
  			<td></td>
  			<td></td>
  			<td> </td>
  		</tr>
  		<tr>
  			<td>传送检查结果要求</td>
  			<td></td>
  			<td></td>
  			<td> </td>
  		</tr>
  		<tr>
  			<td>根据检查结果提供评价意见和建议要求</td>
  			<td></td>
  			<td></td>
  			<td> </td>
  		</tr>
  		<tr>
  			<td>在检查报告中提供测量不确定度要求</td>
  			<td></td>
  			<td></td>
  			<td> </td>
  		</tr>
  		<tr>
  			<td>其他特殊要求</td>
  			<td></td>
  			<td></td>
  			<td> </td>
  		</tr>
  		<tr>
  			<td colspan="4" class="altbg1" >以下项目由实验室填写,并已由委托联系人确认</td>
  		</tr>
  		<tr>
  			<td>主要检测设备名称</td>
  			<td></td>
  			<td>试验件是否符合检测要求?</td>
  			<td> </td>
  		</tr>
  		<tr>
  			<td>试验安排文件路径</td>
  			<td></td>
  			<td>费用</td>
  			<td> </td>
  		</tr>
  		<tr>
  			<td>接收人确认</td>
  			<td></td>
  			<td>接收日期</td>
  			<td> </td>
  		</tr>
  	</table>
  </body>
</html>
