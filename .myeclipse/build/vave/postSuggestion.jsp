<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <table border="1">
   <thead></thead>
   <tbody>
   <tr><td rowspan="2">SDAAC VAVE提案表</td>
   <td>提案来源</td><td>提案编号</td><td>提案状态</td><td>最新修订时间</td><td>版本</td></tr>
   <tr>
   <td></td>
   <td></td>
   <td></td>
   <td></td>
   <td></td></tr>
   
   <tr><td>提案人</td> <td></td> <td>电话/部门/公司</td><td></td><td></td> </tr>
    <tr><td>收集人</td> <td></td> <td>电话/部门/公司</td><td></td><td></td> </tr>
    
    <tr><td>主  题</td></tr>
    
    <tr><td>零件名称</td><td>零件号</td><td>顾客</td><td>适用车型</td><td>系统年产量</td><td>零件年用量</td><td>单位</td></tr>
    <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
    <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
    <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
    
    <tr><td colspan="9"><textarea rows="3" cols="100%">现在方案（可附页）：</textarea> </td></tr>
    
     <tr><td>代用方案（可附页）：</td>
     <td><input type="checkbox" >A 方案描述  </td>
     <td><input type="checkbox" >B 功能说明 </td>
     <td><input type="checkbox" >C 成本说明 </td>
     <td><input type="checkbox" >D 进度说明 </td>
     <td><input type="checkbox" >E 实施建议 </td></tr>
    <tr><td colspan="9"><textarea rows="3" cols="100%"></textarea> </td></tr>
    
    <tr><td>建议类别:</td>
      <td><input type="checkbox" >设计更改  </td>
     <td><input type="checkbox" >材料更改 </td>
     <td><input type="checkbox" >工艺更改 </td>
     <td><input type="checkbox" >零件通用 </td>
     <td><input type="checkbox" >物流改进 </td>
      <td><input type="checkbox" >供应商改更 </td>
     <td><input type="checkbox" >其他 </td></tr>
     
    <tr><td>本部门意见</td><td><select>
    <option>推荐实施</option>
    <option>需完善</option>
    <option>暂缓实施</option>
    <option>不予推荐</option>
    <option>合理化建议</option>
    </select></td>
    <td>主要实施部门</td><td>
    <select>
    <option>产品部</option>
    <option>制造部</option>
    <option>采购部</option>
    <option>物流部</option>
    <option>HVAC</option>
    <option>PTC</option>
    <option>运营部</option>
    <option>市场部</option>
    <option>财务部</option>
    <option>人事部</option>
    </select>
    </td>
    <td><select>
    <option>产品部</option>
    <option>制造部</option>
    <option>采购部</option>
    <option>物流部</option>
    <option>HVAC</option>
    <option>PTC</option>
    <option>运营部</option>
    <option>市场部</option>
    <option>财务部</option>
    <option>人事部</option>
    </select></td>
    <td>批准</td><td></td><td>日期</td><td></td></tr>
    
    <tr><td>外部门意见</td><td><select>
    <option>推荐实施</option>
    <option>需完善</option>
    <option>暂缓实施</option>
    <option>不予推荐</option>
    <option>合理化建议</option>
    </select></td>
    <td >部门名称</td>
    <td colspan="2"></td>
    <td>批准</td><td></td><td>日期</td><td></td></tr>
    
     <tr><td>外部门意见</td><td><select>
    <option>推荐实施</option>
    <option>需完善</option>
    <option>暂缓实施</option>
    <option>不予推荐</option>
    <option>合理化建议</option>
    </select></td>
    <td >部门名称</td>
    <td colspan="2"></td>
    <td>批准</td><td></td><td>日期</td><td></td></tr>
    
     <tr><td>评审组意见</td><td><select>
    <option>推荐实施</option>
    <option>需完善</option>
    <option>暂缓实施</option>
    <option>不予推荐</option>
    <option>合理化建议</option>
    </select></td>
    <td >提案评级</td>
    <td colspan="2"></td>
    <td>批准</td><td></td><td>日期</td><td></td></tr>
       
    <tr><td>项目团队</td><td></td><td></td><td></td><td></td><td></td><td>计划完成时间</td><td></td></tr>
    <tr><td colspan="5">工作内容</td><td>负责人</td><td>计划日期</td><td>完成日期</td></tr>
    <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
    <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
    
    <tr><td>估计节约成本(元)</td><td></td><td>期望节约成本(元)</td><td></td><td></td><td></td><td>实际节约成本(元)</td><td></td></tr>
   <tr><td colspan="9"><textarea rows="3" cols="100%">项目总结）：</textarea> </td></tr>
 </tbody>
 </table>
																												
 <table border="1">
 <thead><tr><td> 计算附表</td><td>周期(D)</td><td>进度</td> <td>一次性费用（元）</td><td>项目</td><td>数量</td><td>单位	</td></tr> </thead>
 <tbody>
 <tr>
 <td> 设计开发</td><td></td>
 <td></td> <td></td>
 <td>现在方案成本</td><td></td><td></td>
 </tr>
  <tr>
 <td> 工装制造	</td><td></td>
 <td></td> <td></td>
 <td>代用方案成本</td><td></td><td></td>
 </tr>
  <tr>
 <td>  样件制造</td><td></td>
 <td></td> <td></td>
 <td>年用量合计</td><td></td><td></td>
 </tr>
  <tr>
 <td> 试验验证</td><td></td>
 <td></td> <td></td>
 <td>总节约成本</td><td></td><td></td>
 </tr>
  <tr>
 <td>顾客确认</td><td></td>
 <td></td> <td></td>
 <td>一次性费用</td><td></td><td></td>
 </tr>
   <tr>
 <td rowspan="3"> 产品切换</td><td></td>
 <td></td> <td></td>
 <td>净节约成本</td><td></td><td></td>
 </tr>
    <tr>
 <td>周期(D)</td>
 <td></td> <td></td>
 <td>C/D/S分配比例%</td><td></td><td></td>
 </tr>
    <tr>
 <td>周期(D)</td>
 <td></td> <td></td>
 <td>SDAAC净节约成本</td><td></td><td></td>
 </tr>
 </tbody>
</table>
 
   
   
  </body>
</html>
