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
	<!-- START 以下脚本用于动态添加条件 -->
	<script language="javascript">

function add(){
	 var i= eval(window.form1.count.value)+1;//已经添加的条件行数
	 var str = "<select name='selectName"+i+"'><option value=0></option><option value=1>申请人工号</option><option value=2>申请日期</option>      <option value=3>pr状态</option>    </select><select name='selectR"+i+"'>	 <option value=0></option>      <option value=1>大于</option>      <option value=2>小于</option>      <option value=3>等于</option>    </select><input id='selectValue"+i+"' value='' /><br >";
	var add = document.getElementById("add");
	add.innerHTML = add.innerHTML + str; 
	window.form1.count.value = i ;
	
}
function sub(){
var str = "and 1=1 ";
var i,j;
var selectName,selectR,selectValue,temp;

i=parseInt(window.form1.count.value);
j=1;

if(i==0) return str;
while(j<=i)
{
	selectName = parseInt(document.getElementById("selectName"+j).value);
	selectR = parseInt(document.getElementById("selectR"+j).value);
	selectValue=document.getElementById("selectValue"+j).value;
	if(selectName==0||selectR==0||selectValue=='') {j++;continue;}// 有一个条件为空，直接忽略本行已经输入的条件
	str =str + ' and';
	switch(selectName)
	{
		case 1: str = str+" pf.applicantid ";break;
		case 2: str = str +" applicant_date ";break;
		case 3: str = str +" state";break;
	}
	switch(selectR)
	{
		case 1: str +="> ";break;
		case 2: str +="< ";break;
		case 3: str +="= ";break;
	}
	str += document.getElementById("selectValue"+j).value;
	
	j++;

}
document.getElementById("sql").value=str;
document.getElementById("tip").innerHTML="条件保存成功，继续后续操作";
}
</script>
  </head>
  <!-- END -->
  <body>
  <form name='form1' action="epor.do?actionType=doDownReport" enctype="multipart/form-data" method="post">
   上传:<input type="file" name="formFile" >	
   <input name="type" type="text">
   <input type="submit" value="submit">
   <!--  START 以下用于生成sql语句  -->
   <p>条件筛选 <input type="button" value="添加" onclick="add()"/></p>

	<table>
		<tr id><td id="add"></td></tr>
	</table>
	<input type="hidden" value=0 name="count" />
	<input type="hidden" value=0 name="sql" /> <!-- 通过sub()生成语句保留在sql中 -->
	<p><input type="button" value="确定" onclick="sub()"/><div id='tip'></div></p>
	<!-- END -->
   </form>
  </body>
</html>
