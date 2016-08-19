<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>vave提案单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style type="text/css" media="screen">@import url(css/vave.css);</style>	
  </head>
<script type="text/javascript">
var Alternative_programs =['A 方案描述 :','B 功能说明 :','C 成本说明 :','D 进度说明 :','E 实施建议 :'];

function show()
{
	var elem=document.getElementById('cal_Schedule');
	if(elem.style.display=='block')
	{
		elem.style.display='none';
		return ;
	}
	if(elem.style.display=='none')
	{
		elem.style.display='block';
		return ;
	}  
}

function addContext(value)
{
	var elem=value;
	suggestions.innerHTML=suggestions.innerHTML+'&nbsp'+Alternative_programs[elem.id-1];
}
</script>


  <body >
   <table id="title">
   <tbody>
	<from>
	<tr><jsp:include flush="true" page="vave/Select_TopBar_Form.jsp"></jsp:include></tr>
	</form>
   <tr id="intro_right"><td rowspan="2" id="title" style="font-size: 20">SDAAC VAVE提案表</td>
   <td>提案来源</td><td>提案编号</td><td>提案状态</td><td>最新修订时间</td><td>版本</td></tr>
   <tr id="intro_right">
   <td><select>
    <option>公司</option>
    <option>子公司</option>
    <option>部门</option>
    <option>员工</option>
    <option>供应商</option>
    </select></td>              
   <td>VAVE-2009-2-20-sartest-uuid</td>
   <td align="middle">I</td>
   <td>2009-2-20</td>
   <td>v1.0</td></tr>
   
   <tr><td>提案人:</td><td><input type="text" name="" /></td> <td>电话/部门/公司</td><td><input type="text" name="" /></td><td><input type="text" name="" /></td><td><input type="text" name="" /></td></tr>
    <tr><td>收集人:</td> <td></td> <td>电话/部门/公司</td><td></td><td></td> </tr>
    
    <tr><td>主   题:</td><td><input type="text" name="" /></td></tr>
 
    <tr id="intro_right"><td>零件名称</td><td>零件号</td><td>顾客</td><td>适用车型</td><td>系统年产量</td><td>零件年用量</td><td>单位</td></tr>
    <tr><td><input type="text" name="" /></td><td><input type="text" name="" /></td><td><input type="text" name="" /></td><td><input type="text" name="" /></td><td><input type="text" name="" /></td><td><input type="text" name="" /></td><td><input type="text" name="" /></td></tr>
    <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
    <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
    
    <tr><td>现在方案（可附页）：</tr>
   <tr> <td colspan="9"><textarea rows="3" cols="100%"></textarea> </td></tr>
    
     <tr><td>代用方案（可附页）：</td>
     <td><input id="1" type="checkbox" onclick="addContext(this)">A 方案描述 </td>
     <td><input id="2" type="checkbox" onclick="addContext(this)">B 功能说明 </td>
     <td><input id="3" type="checkbox" onclick="addContext(this)">C 成本说明 </td>
     <td><input id="4" type="checkbox" onclick="addContext(this)">D 进度说明 </td>
     <td><input id="5" type="checkbox" onclick="addContext(this)">E 实施建议 </td></tr>
    <tr><td colspan="9"><textarea id="suggestions" rows="3" cols="100%"></textarea> </td></tr>
   
    
    

    <tr><td>建议类别:</td>
      <td><input type="checkbox" >设计更改  <input type="checkbox" >材料更改 </td>
     <td><input type="checkbox" >工艺更改 <input type="checkbox" >零件通用 </td>
     <td><input type="checkbox" >物流改进 </td>
     <td><input type="checkbox" >供应商改更 <input type="checkbox" >其他 </td></tr>
       </tbody>
     </table>
     
   
    <table id="title">
   <tbody>
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
    <td>批准</td><td><input type="text" name="" /></td><td>日期</td><td><input type="text" name="" /></td></tr>
     </tbody>
 </table>
      <!-- ggg
    <tr><td>外部门意见</td><td><select>
    <option>推荐实施</option>
    <option>需完善</option>
    <option>暂缓实施</option>
    <option>不予推荐</option>
    <option>合理化建议</option>
    </select></td>
    <td >部门名称</td>
    <td colspan="2"></td>
    <td>批准</td><td><input type="text" name="" /></td><td>日期</td><td><input type="text" name="" /></td></tr>
    
     <tr><td>外部门意见</td><td><select>
    <option>推荐实施</option>
    <option>需完善</option>
    <option>暂缓实施</option>
    <option>不予推荐</option>
    <option>合理化建议</option>
    </select></td>
    <td >部门名称</td>
    <td colspan="2"></td>
    <td>批准</td><td><input type="text" name="" /></td><td>日期</td><td><input type="text" name="" /></td></tr>
    
     <tr><td>评审组意见</td><td><select>
    <option>推荐实施</option>
    <option>需完善</option>
    <option>暂缓实施</option>
    <option>不予推荐</option>
    <option>合理化建议</option>
    </select></td>
    <td >提案评级</td>
    <td colspan="2"></td>
    <td>批准</td><td><input type="text" name="" /></td><td>日期</td><td><input type="text" name="" /></td></tr>
       
    <tr><td>项目团队</td><td></td><td></td><td></td><td></td><td></td><td>计划完成时间</td><td></td></tr>
    <tr id="intro_right"><td colspan="5">工作内容</td><td>负责人</td><td>计划日期</td><td>完成日期</td></tr>
    <tr><td colspan="5" ><input type="text" maxlength="60" name="" /></td><td><input type="text" name="" /></td><td><input type="text" name="" /></td><td><input type="text" name="" /></td></tr>
    <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
    
    <tr><td>估计节约成本(元)</td><td></td><td>期望节约成本(元)</td><td></td><td></td><td></td><td>实际节约成本(元)</td><td></td></tr>
  <tr><td>项目总结：</td></tr>
   <tr><td colspan="9"><textarea rows="3" cols="100%"></textarea> </td></tr>
 </tbody>
 </table>
			 -->
			
	<div id="tip" style="color: #4b04cc" onclick="show()">计算附表<img src="images/additional.gif"> </div>
		<div id="cal_Schedule" style="display: none">																									
 <table id="title" >
 <thead><tr id="intro_right">
 <td > 计算附表</td><td>周期(D)</td><td>进度</td>
  <td>一次性费用（元）</td><td>项目</td><td>数量</td><td>单位	</td>
  </tr></thead>
 <tbody>
 <tr>
 <td> 设计开发</td><td><input type="text" name="" /></td>
 <td><input type="text" name="" /></td>
  <td><input type="text" name="" /></td>
 <td>现在方案成本</td><td><input type="text" name="" /></td>
 <td><input type="text" name="" /></td>
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
 <td></td>
 <td></td> <td></td>
 <td>C/D/S分配比例%</td><td></td><td></td>
 </tr>
    <tr>
 <td></td>
 <td></td> <td></td>
 <td>SDAAC净节约成本</td><td></td><td></td>
 </tr>
 </tbody>
</table>  
</div>
  </body>
</html>
