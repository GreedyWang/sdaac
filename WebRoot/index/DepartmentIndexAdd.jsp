<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>部门指标添加</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
	<link rel="stylesheet" type="text/css" href="css/floatDiv.css">
    <script type='text/javascript' src='/bpp/dwr/interface/IndexManager.js'></script>
    <script type='text/javascript' src='/bpp/dwr/engine.js'></script>
    <script type="text/javascript" src='js/util/wforms.js'></script>
     <script type="text/javascript" src="js/index/weight.js"></script>
     <script type="text/javascript" src="js/jquery.js"></script>
     <script type="text/javascript" src="js/util/dialog.js"></script>
  </head>              
  	<script type="text/javascript">
 
  	var long;
  	var flag=2;//index's departID,if is company then 1,else 2
  	function init()
  	{		
  		long=document.getElementById('table3').rows.length;
  		if('${departId }'==1)
  		{
  			flag=1;	
  		}
  		var curVersion='${version}';
  		IndexManager.doSelectVersions(function(data){
				var select1=document.getElementById('versions')
				select1.length=0
			 	for(var i=0;i<data.length;i++)
			 	{
			 		var opt=document.createElement('Option');
			 		opt.innerText = data[i]
					opt.value = data[i]
					if(opt.value==curVersion){
						opt.selected='selected'
					}
					select1.appendChild(opt);
		 		} 
			})
			
	//加载权重和
		var target='Weight'
  		var ele=table3.getElementsByTagName('input')
	 	 for(var i=0;i<ele.length;i++)
	 	 {	
		  	var eleId=ele[i].id
		  	var rr=eleId.substring(0,6)		
		  
		  	if(rr==target)
		  	{	  		
		  		
		  		if(ele[i].value!='')
		  		{
		  			wightSum=wightSum+parseInt(ele[i].value);
		  		}
		  	}
		  }
	  	  document.getElementById('totalWeight').innerText=wightSum;
		
		  
	
   }
	 function Tadd()
  {
  	
   // var index=document.getElementById('table3').rows.length-long;
  	var newTr = table3.insertRow();
	var newTd0 = newTr.insertCell();	
	var newTd1 = newTr.insertCell();
	var newTd2 = newTr.insertCell();
	var newTd3 = newTr.insertCell();
	var newTd4 = newTr.insertCell();
	var newTd5 = newTr.insertCell();
	var newTd6 = newTr.insertCell();
	var newTd7 = newTr.insertCell();
	var newTd8 = newTr.insertCell();
	var newTd9 = newTr.insertCell();
	var newTd10 = newTr.insertCell();
	
	newTd0.className='altbg1';
	newTd1.className='altbg1';
	newTd2.className='altbg1';
	newTd3.className='altbg1';
	newTd4.className='altbg1';
	newTd5.className='altbg1';
	newTd6.className='altbg1';
	newTd7.className='altbg1';
	newTd8.className='altbg1';
	newTd9.className='altbg1';
	newTd10.className='altbg1';
	var echkbox=document.createElement("input");   
	echkbox.setAttribute("type","hidden"); 
	echkbox.setAttribute("id",index);   
	document.body.appendChild(echkbox)
	newTd0.innerHTML= '<input class="required" name="items['+index+'].tindexTarget.name" id="items['+index+'].name" size="20"/> '; 
	newTd1.innerHTML= '<input  name="items['+index+'].tindexTarget.formula" id="items['+index+'].formula" size="20"/> '; 
	newTd2.innerHTML='<select class="required" name="items['+index+'].tindexTarget.type"><option value="量化指标">量化指标</option><option value="非量化指标">非量化指标</option></select>';
	newTd3.innerHTML='<select class="required" name="items['+index+'].tindexTarget.isChoice"><option value="必选">必选</option><option value="可选">可选</option><option value="必选且值不可变">必选且值不可变</option></select>';
	newTd4.innerHTML= '<input class="required" name="items['+index+'].tindexTarget.a" id="items['+index+'].a" size="5"/>';
	newTd5.innerHTML= '<input class="required" name="items['+index+'].tindexTarget.b" id="items['+index+'].b" size="5"/>';
	newTd6.innerHTML= '<input class="required" name="items['+index+'].tindexTarget.c" id="items['+index+'].c" size="5"/>';
	newTd7.innerHTML= '<input name="items['+index+'].percentage" id="items['+index+'].percentage" onfocus="minus(this)" onblur="checkWightInNew(this,'+index+')" size="5"/>';
	newTd8.innerHTML= '<input name="items['+index+'].act_output" id="items['+index+'].act_output" size="5"/>';
	newTd9.innerHTML= '<input name="items['+index+'].score" id="items['+index+'].score" readonly="readonly" size="5" />'+'<input name="items['+index+'].tindexTarget.departID" type="hidden" value="'+flag+'"/>';
	newTd10.innerHTML= '<img src="images/del.gif" id="image"  onclick="Tdelete(this,'+index+')" >';
	index=index+1;
  }
 
  function isQuantitative(elem,index)
  {

  	if(elem.value=='非量化指标')
  	{
  		var score	=document.getElementById('items['+index+'].score')
  		score.readOnly=false
  	}
  }
 
   function TdeleteTable4(elem)
  {
  	var t=document.getElementById('table4')   
  	t.deleteRow(elem.parentNode.parentNode.rowIndex)
  	//minus(elem)
  }
	function del_Company_Index(value,elem)
  	{
		if('${departId }'==1)
		{
		  	var bConfirmed = window.confirm("are you sure ");
			if(bConfirmed)
			{
				IndexManager.doDelete(value,function(data){
					alert('删除成功');
					//window.location.reload();
					TdeleteTable4(elem);
				});			
			}
		}
  	}
  	function del_Depart_Index(value,elem,index)
  	{	
		  	var bConfirmed = window.confirm("are you sure ");
			if(bConfirmed)
			{
				var target='Weight['+index+']';
	   			document.getElementById('totalWeight').innerText-=document.getElementById(target).value;
				IndexManager.doDelete(value,function(data){
					alert('删除成功');
					//window.location.reload();
					Tdelete(elem,-1);
				});			
			}
  	}
  	
  var sum_Value=0;
   function check_Weight2(elem)
  {
	
  	var sum=0;
  	var target='Weight2'
  	var ele=document.getElementsByTagName('input')
	  for(var i=0;i<ele.length;i++)
	  {
	  	
	  	if(ele[i].id==target)
	  	{
	  		
	  		sum+=parseInt(ele[i].value);
	  	
	  		if(sum>100)
	  		{
	  			alert('权重大于100！')
	  			elem.value=''
	  			break;
	  		}
	  	}
	  }
  	
  }
 
  
   function update_Company_Index(indexID,target,id,output)
  {
  		var url='${basePath}'+'index.do?actionType=selectByIndexID&indexID='+indexID+'&target='+target+'&id='+id+'&output='+output;
  		dialog("指标修改","iframe:"+url,"500px","500px","iframe");
  }
 //试运算
function testCal(a,b,c,output,index,tableName)
{

	var target 
	var output=parseFloat(output.value) 
	if(tableName=='table4')
	{
		target='score['+index+']'
	}
	else if(tableName=='table3')
	{
		target='score2['+index+']'
	}
	var a=parseFloat(a) 
	var b=parseFloat(b) 
	var c=parseFloat(c) 

	if(a<b)//正向
	{
		
		if(a>output)
		{
			
			document.getElementById(target).innerText=0;
			return 
		}
		if(output>=a&&output<b)
		{
		
			var reslut=(30/(b-a))*output+(70-(30*a/(b-a)))
			document.getElementById(target).innerText=reslut;
			return 
		}
		if(output>=b&&output<c)
		{
			
			var reslut=(50/(c-b))*output+(100-(50*b/(c-b)))
			document.getElementById(target).innerText=reslut;
			
			return 
		}
		if(output>=c)
		{
		
			document.getElementById(target).innerText=150;
			return ;
		}
	}else{
	
		if(a<output)
		{
			
			document.getElementById(target).innerText=0;
			return 
		}
		if(output<=a&&output>b)
		{
		
			var reslut=(30/(b-a))*output+(70-(30*a/(b-a)))
			document.getElementById(target).innerText=reslut;
			return 
		}
		if(output<=b&&output>c)
		{
			
			var reslut=(50/(c-b))*output+(100-(50*b/(c-b)))
			document.getElementById(target).innerText=reslut;
			
			return 
		}
		if(output<=c)
		{
		
			document.getElementById(target).innerText=150;
			return ;
		}
	}
	
} 
 
  function create()
  {
 	document.getElementById('version').value=''
  	var flag=window.confirm("是否要导入上次的数据")
  	if(flag==true)
  	{
  		IndexManager.doCreateNewIndexLib(function(data){
  			alert("指标导入成功")
  		})		
  	}	
  }
	//返回
	function backWard()
	{
		window.location.href='departIndex.do?actionType=doSelectDepartmentDetails2';
	}
	
	function checkdpID(elem)
	{
		if('${departId }'==1)
		{
			elem.readOnly=false;
		}
	}
	//将公司指标变成半年的
	function half()
	{
		var version=document.getElementById('versions').value;
		IndexManager.doChangeHalf(version,function(data){
			alert('ok');
			window.location.reload();
		})
		
		
		
	}
	function copy(){
		var departmentId = document.getElementById('departmentId').value
		var verion = document.getElementById('versions').value
		window.location.href='departIndex.do?actionType=copyToEmployees&verion='+verion+'&departmentId='+departmentId;
		
	}
  </script>
  DepartmentIndexAdd.jsp
  <body onload="init()">
  <form action="departIndex.do?actionType=doManagerDepartmentIndex" method="post">
  <table>
    <tr>
    <td><select id="versions" name="version">
    </select></td>
     <td><input type="submit" value="查看历史版本"></td>
      <td><input type="button" value="新建今年指标" onclick="create()"> </td>
      <td><input type="button" value="公司半年指标" onclick="half()"> </td>
        <td><input type="button" value="公司全年指标" onclick="half()"> </td>
        <td><input type="button" value="拷贝部门得分" onclick="copy()"> </td>
    </tr>
    </table>
  <input type="hidden" id="departmentId" name="items.departID" value="${departId }">
  </form>
  <input type="button" value="返回" onclick="backWard()">
 <form action="departIndex.do?actionType=doAddDepartmentIndex" method="post" onsubmit="return checkSubmit()">
   <table cellspacing="1" cellpadding="4" width="100%"
			class="tableborder" id="table4">
   <thead>
   <tr>
	<td colspan="12" class="header">${departName }指标定义 
	<input type="hidden" name="departID" value="${departId }" >
	<input type="hidden" id="version" name="version" value="${version }">
	</td>		
  </tr>
   <tr><td  class="header"><input type="submit" value="提交" ></td>
   <td colspan="11" class="header"　><label>增加一行</label><img src="images/add.gif" id="image"  onclick="Tadd()"></td>
   </tr>
  
   <tr>
     <td align="center" class="altbg1"><b onclick="update_Company_Index(1)">名称</b></td>  
     <td align="center" class="altbg1"><b>计算公式</b></td>  
     <td align="center" class="altbg1"><b>类型</b></td>  
     <td align="center" class="altbg1"><b>是否必选</b></td>  
 	 <td align="center" class="altbg1"><b>70%</b></td>
     <td align="center" class="altbg1"><b>100%</b></td>
     <td align="center" class="altbg1"><b>150%</b></td>
     <td colspan="1" align="center" class="altbg1"><b>权重(%)</b></td>
 	 <td colspan="1" align="center" class="altbg1"><b>实际值</b></td>
 	 <td colspan="1" align="center" class="altbg1"><b>得分</b></td>
 	  <td align="center" class="altbg1" colspan="2"><b>操作</b></td> 
  
   </tr> </thead>
  <tbody>
  	
	<c:forEach items="${companyIndexs}" var="item_Index" varStatus="state"> 
    <tr>  
	<td align="center" class="altbg1">${item_Index.tindexTarget.name }</td>
	<td align="center" class="altbg1">${item_Index.tindexTarget.formula }</td>
    <td align="center" class="altbg1">${item_Index.tindexTarget.type }</td>
    <td align="center" class="altbg1">${item_Index.tindexTarget.isChoice }</td>
    <td align="center" class="altbg1">${item_Index.tindexTarget.a }</td>
    <td align="center" class="altbg1">${item_Index.tindexTarget.b}</td>  
    <td align="center" class="altbg1">${item_Index.tindexTarget.c } </td> 
	 <input type="hidden" name="fdpIndex" value="${item_Index.id}" >
	  <input type="hidden" name="indexID" value="${item_Index.tindexTarget.id }">
   <input type="hidden" name="ischoice" value="${item_Index.tindexTarget.isChoice }">
    <td align="center" class="altbg1">
    <input name="fpercentage" id="Weight2" type="text" value="${item_Index.percentage }" style="border: none" onfocus="minus(this)" onblur="checkWightInNew(this,'+index+')" size="5">
    </td>
    <td align="center" class="altbg1">
    <input name="fact_output" type="text" value="${item_Index.output }" style="border: none" onfocus="checkdpID(this)" readonly="readonly" onclick="testCal('${item_Index.tindexTarget.a }','${item_Index.tindexTarget.b }','${item_Index.tindexTarget.c }',this,'${state.index }','table4')" size="5"></td>  
    <td align="center" class="altbg1">
    <input id="score[${state.index }]" name="score" value="${item_Index.score }" onfocus="checkType2('${item_Index.tindexTarget.type }',this)" readonly="readonly" size="5">
   
    </td> 
      <td align="center" class="altbg1"><img src="images/del.gif" onclick="del_Company_Index('${item_Index.tindexTarget.id }',this)" /></td> 
    <td align="center" class="altbg1"><img src="images/update.gif" onclick="update_Company_Index('${item_Index.tindexTarget.id }','1','${item_Index.id }','${item_Index.act_output }')" /></td> 
   </tr> 
	</c:forEach>

</tbody>
</table>


   <table cellspacing="1" cellpadding="4" width="100%"
			class="tableborder" id="table3">
   <thead>

  
   <tr>
     <td align="center" class="altbg1"><b onclick="update_Company_Index(1)">名称</b></td>  
     <td align="center" class="altbg1"><b>计算公式</b></td>  
     <td align="center" class="altbg1"><b>类型</b></td>  
     <td align="center" class="altbg1"><b>是否必选</b></td>  
 	 <td align="center" class="altbg1"><b>70%</b></td>
     <td align="center" class="altbg1"><b>100%</b></td>
     <td align="center" class="altbg1"><b>150%</b></td>
     <td colspan="1" align="center" class="altbg1"><b>权重(<label id="totalWeight"></label>%)</b></td>
 	 <td colspan="1" align="center" class="altbg1"><b>实际值</b></td>
 	 <td colspan="1" align="center" class="altbg1"><b>得分</b></td>
  	 <td align="center" class="altbg1" colspan="2"><b>操作</b></td> 
   </tr> </thead>
  <tbody>
  	

	
	
	
	<c:forEach items="${departIndexs}" var="item_Index" varStatus="state"> 
    <tr>  
	<td align="center" class="altbg1">${item_Index.tindexTarget.name }</td>
	<td align="center" class="altbg1">${item_Index.tindexTarget.formula }</td>
    <td align="center" class="altbg1">${item_Index.tindexTarget.type }</td>
    <td align="center" class="altbg1">${item_Index.tindexTarget.isChoice }</td>
    <td align="center" class="altbg1">${item_Index.tindexTarget.a }</td>
    <td align="center" class="altbg1">${item_Index.tindexTarget.b}</td>  
    <td align="center" class="altbg1">${item_Index.tindexTarget.c } </td> 
    <input type="hidden" name="fdpIndex" value="${item_Index.id}" >
     <input type="hidden" name="indexID" value="${item_Index.tindexTarget.id }">
      <input type="hidden" name="ischoice" value="${item_Index.tindexTarget.isChoice }">
    <td align="center" class="altbg1">
    <input name="fpercentage" id="Weight[${state.index }]" type="text" value="${item_Index.percentage }" style="border: none" onfocus="minus(this)" onblur="checkWightInNew(this,'${state.index }')" size="5"></td>
    <td align="center" class="altbg1">
    <input name="fact_output" type="text" value="${item_Index.act_output }" style="border: none" onblur="testCal('${item_Index.tindexTarget.a }','${item_Index.tindexTarget.b }','${item_Index.tindexTarget.c }',this,'${state.index }','table3')" size="5"></td>  
    <td align="center" class="altbg1">
    <input id="score2[${state.index }]" name="score" value="${item_Index.score }" onmouseover="checkType2('${item_Index.tindexTarget.type }',this)" size="5">
    </td> 
    <td align="center" class="altbg1"><img src="images/del.gif" onclick="del_Depart_Index('${item_Index.tindexTarget.id }',this,'${state.index }')" /></td> 
    <td align="center" class="altbg1"><img src="images/update.gif" onclick="update_Company_Index('${item_Index.tindexTarget.id }','2','${item_Index.id }','${item_Index.act_output }')" /></td> 
    </tr> 
	</c:forEach>
   </tbody>
   </table>
   <table>
   <tr>
	 <tr><td>公司得分</td><td>个人得分</td><td>总得分</td></tr>
   	<tr><td colspan="3">${result }</td></tr>
	</tr>
	</table>
   	</form>
  </body>
</html>
