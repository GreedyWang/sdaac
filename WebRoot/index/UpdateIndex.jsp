<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'UpdateIndex.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
	function init()
	{
		var isChoice='${index.isChoice}'
		var type='${index.type}'
		var choices =document.getElementById('isChoice')
		var types=document.getElementById('type')
		for(var i=0;i<choices.length;i++)
		{
			if(choices[i].value==isChoice)
			{
				
				choices[i].selected=true;
			}
		}
		for(var i=0;i<types.length;i++)
		{
			if(types[i].value==type)
			{
				types[i].selected=true;
			}
		}
	}
	function checkType()
	{
		
		if(document.getElementById('type').value=='量化指标')
		{
			var a=document.getElementById('a').value
			var b=document.getElementById('b').value
			var c=document.getElementById('c').value
	 		var  re=/^(-|\+)?\d+(\.\d+)?$/;   
	
	       	if(!re.test(a)){ 
		        alert("量化指标不能为字符类型！") 
		        return false; 
	      	} 
	      	if(!re.test(b)){ 
		        alert("量化指标不能为字符类型！") 
		        return false; 
	      	} 
	      	if(!re.test(c)){ 
		        alert("量化指标不能为字符类型！") 
		        return false; 
	      	} 
	      	return true;
    	}
    		return true;
	}
	</script>

  </head>
  UpdateIndex.jsp
  <body onload="init()">
  <form action="index.do?actionType=doUpdate" method="post" onsubmit="return checkType()">
   <table cellspacing="1" cellpadding="4" width="100%"
			class="tableborder" id="table3">
			<tr><td><input type="radio" name="item.isAll" value="0" checked="checked">单条 </td>
			<td><input type="radio" name="item.isAll" value="1">全部</td></tr>
   <tr>
     <td align="center" class="altbg1"><b>名称</b></td>  
      <input name="item.id" type="hidden" size="20" value="${index.id }">
      <input name="item.version" type="hidden" size="20" value="${index.version }">
      <input name="item.departID" type="hidden" size="20" value="${index.departID }">
	  <input name="target" type="hidden" size="20" value="${cond[0] }">
      <input name="id" type="hidden" size="20" value="${cond[1] }">
      <input name="output" type="hidden" size="20" value="${cond[2] }">
     <td align="center" class="altbg1"><input name="item.name" type="text" size="20" value="${index.name }"></td>  
      </tr>
 <tr>
     <td align="center" class="altbg1"><b>计算公式</b></td>  
    <td align="center" class="altbg1"><input name="item.formula" type="text" size="20" value="${index.formula }"></td>
       </tr>
 <tr>
     <td align="center" class="altbg1"><b>类型</b></td>  
     <td align="center" class="altbg1">
	<select name="item.type" id="type">
	<option value="量化指标">量化指标</option>
	<option value="非量化指标">非量化指标</option>
	</select>
     </td> 
      </tr>
 <tr>
     <td align="center" class="altbg1"><b>是否必选</b></td>  
   <td align="center" class="altbg1">
     <select name="item.isChoice" id="isChoice" >
	<option value="必选">必选</option>
	<option value="可选">可选</option>
	<option value="必选且权重不可变">必选且权重不可变</option>
	</select></td>
    </tr>
 <tr>
 	 <td align="center" class="altbg1"><b>70%</b></td>
  <td align="center" class="altbg1">
 	 <input id="a" name="item.a" type="text" size="5" value="${index.a }"/>
 	 </td>
    </tr>
 <tr>
     <td align="center" class="altbg1"><b>100%</b></td>
 <td align="center" class="altbg1">
     <input id="b" name="item.b" type="text" size="5" value="${index.b }"/>
     </td>
     </tr>
 <tr>
     <td align="center" class="altbg1"><b>150%</b></td>
       <td align="center" class="altbg1">
     <input id="c" name="item.c" type="text" size="5" value="${index.c }"/>
     </td>
    </tr>
	<tr><td><input type="submit" value="提交"> </td></tr>
    </table>
    </from>
  </body>
</html>
