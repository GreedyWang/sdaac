<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

		<title>部门指标添加</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/style_admin.css">
		<link rel="stylesheet" type="text/css" href="css/floatDiv.css">
		<script type='text/javascript'
			src='/bpp/dwr/interface/EmpIndexManager.js'></script>
		<script type='text/javascript'
			src='/bpp/dwr/interface/IndexManager.js'></script>
		<script type='text/javascript' src='/bpp/dwr/engine.js'></script>
		<script type="text/javascript" src='js/util/wforms.js'></script>
		<script type="text/javascript" src="js/index/weight.js"></script>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/util/dialog.js"></script>
		<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
		<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="ext/ext-all.js"></script>
		<link rel="stylesheet" type="text/css" href="ext/multiselect.css"/>

	    <script type="text/javascript" src="ext/DDView.js"></script>
	    <script type="text/javascript" src="ext/MultiSelect.js"></script>
	    <script type="text/javascript" src="ext/ItemSelector.js"></script>
	    <script type="text/javascript" src="ext/multiselect-demo.js"></script>
		<script type='text/javascript' src='/bpp/dwr/interface/EmpManager.js'></script>
  		<script type='text/javascript' src='/bpp/dwr/interface/ogManager.js'></script>
  		<script type='text/javascript' src='/bpp/dwr/engine.js'></script>
		
		<script type="text/javascript" src="index/util/EmpIndexAdd.js"></script>
	</head>
	<script type="text/javascript">
        	
   var empData
  	function init()
  	{
  		//显示当前是版本号
		var curVersion='${version}';
		IndexManager.doSelectVersions(function(data){
		var select1=document.getElementById('versions')
		select1.length=0
		alert('当前是版本号:'+curVersion)
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
		//加载之前先累加权重和
		var target='Weight'
  		var ele=document.getElementsByTagName('input')
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
	  	  initEmployees();
  	}
	function delDpIndexManager(value,dpid,elem)
  	{
   	  if(dpid!=3)
   	  {
   	  	alert('不能删除公司定义的指标！')
   	  	return null;
   	  }
   	  var bConfirmed = window.confirm("are you sure ");
		if(bConfirmed)
		{
			IndexManager.doDelete(value,function(data){
				alert('删除成功');
				//window.location.reload();
				Tdelete(elem);
			});			
		}
		else
		{
			return null;
		}
  	}
  	
 	function delEmpIndex(value,elem,index)
  	{
	    var empIndex={
  			id:value
  		}
	  
	    var bConfirmed = window.confirm("are you sure ");
		if(bConfirmed)
		{
			var target='Weight['+index+']';
	   		document.getElementById('totalWeight').innerText-=document.getElementById(target).value;
			EmpIndexManager.doDelete(empIndex,function(data){
				alert('删除成功');
				Tdelete(elem,-1);
			});			
		}
		else
		{
			return null;
		}
  	}
  
   function update_Company_Index(indexID,target,id,output,dpid)
  {
  	 if(dpid!=3)
   	 {
   	  	alert('不能更新公司定义的指标！')
   	  	return null;
   	 }
  	var url='${basePath}'+'index.do?actionType=selectByIndexID&indexID='+indexID+'&target='+target+'&id='+id+'&output='+output;
  		dialog("指标修改","iframe:"+url,"500px","500px","iframe");
  }
 
function checkHasIndex(indexID,elem)
{
	//alert(indexID);
	var ids='${ids}';
	var newIds=ids.substring(1,ids.length-1).split(',')
	     for(var i=0;i<newIds.length;i++)
	     {
	     	//alert("*="+newIds[i])
	     	if(indexID==newIds[i]||' '+indexID==newIds[i])
	     	{
	     		alert('已经定义过该指标')
	     		elem.checked=false
	     	}
	     }     
}
function show()
{
	
	 window.location.href.target='blank'
	 window.location.href='index/util/test.jsp?uid='+'${empUid }'
}
//检查得分结果是否在范围内
function checkScore(elem){
	var  re=/^(-|\+)?\d+(\.\d+)?$/;   
     if(!re.test(elem.value)){ 
    	  alert("得分只能是浮点数！") 
    	  elem.value=0
      return ; 
     }
	if(elem.value>150||elem.value<0){
		alert('得分范围在0-150');
		 elem.value=0
		 return ; 
	}	      
}

function initEmployees(){
	var emp={uid:'${logineduser.emp.uid }'}
	ogManager.getAllSubordinate(emp,function(data){

		if(data!=null){			
		empData=new Array()
				for(var i=0;i<data.length;i++){
					empData[i]=[data[i].uid,data[i].name]
				}
		}
	})
	
}

  </script>
  EmpIndexAdd.jsp
	<body onload="init()">
		<form action="index.do?actionType=doSelectEmpIndex" method="post">
			<td>
				<select id="versions" name="version">
				</select>
			</td>
			<td>
				<input type="submit" value="查看历史版本">
			</td>
			<input type="hidden" name="empUid" value="${empUid }">
			<input type="hidden" name="version" value="${version }">
		</form>

		<table cellspacing="1" cellpadding="4" width="100%"
			class="tableborder" id="table1">
			<thead>
				<tr>
					<td colspan="12" class="header">
						公司指标
					</td>
				<tr>
					<td align="center" class="altbg1">
						<b>名称</b>
					</td>
					<td align="center" class="altbg1">
						<b>指标描述</b>
					</td>
					<td align="center" class="altbg1">
						<b>类型</b>
					</td>
					<td align="center" class="altbg1">
						<b>是否必选</b>
					</td>
					<td align="center" class="altbg1">
						<b>70%</b>
					</td>
					<td align="center" class="altbg1">
						<b>100%</b>
					</td>
					<td align="center" class="altbg1">
						<b>150%</b>
					</td>
					<td align="center" class="altbg1">
						<b>权重(100%)</b>
					</td>
					<td align="center" class="altbg1">
						<b>实际值</b>
					</td>
					<td align="center" class="altbg1">
						<b>得分</b>
					</td>
				</tr>
			</thead>
			<tbody>

				<!-- 显示公司的指标 -->
				<c:forEach items="${companyIndexs}" var="item" varStatus="state">
					<tr class="altbg1">
						<td>
							${item.tindexTarget.name }
						</td>
						<td>
							${item.tindexTarget.formula }
						</td>
						<td>
							${item.tindexTarget.type }
						</td>
						<td>
							${item.tindexTarget.isChoice }
						</td>
						<td>
							${item.tindexTarget.a }
						</td>
						<td>
							${item.tindexTarget.b }
						</td>
						<td>
							${item.tindexTarget.c }
						</td>
						<td>
							${item.percentage }
						</td>
						<td>
							${item.act_output }
						</td>
						<td>
							${item.score }
						</td>
						</td>
				</c:forEach>

			</tbody>
		</table>

		<form action="empIndex.do?actionType=doInsertEmpIndicators"
			method="post" onsubmit="return checkSubmit()">
			<table cellspacing="1" cellpadding="4" width="100%"
				class="tableborder" id="table3">
				<thead>
					<tr class="header">
						<td colspan="2">
							${empName }指标定义
							<input type="hidden" name="version" value="${version }">
							<input type="hidden" name="uid" value="${empUid }">
						</td>
						<td colspan="11" >
							<input type="button" value="部门指标库"  onclick="show()">
						</td>
					</tr>
					<tr>
						<td class="header">
							<input type="submit" value="提交">
						</td>
						<td colspan="6" class="header"　>
							<label>
								增加一行
							</label>
							<img src="images/add.gif" id="image" onclick="Tadd()">
						</td>
						<td colspan="6" class="header">
							<label id="totalWeight"></label>
						</td>
					</tr>

					<tr>
						<td align="center" class="altbg1">
							<b>名称</b>
						</td>
						<td align="center" class="altbg1">
							<b>指标描述</b>
						</td>
						<td align="center" class="altbg1">
							<b>类型</b>
						</td>
						<td align="center" class="altbg1">
							<b>是否必选</b>
						</td>
						<td align="center" class="altbg1">
							<b>70%</b>
						</td>
						<td align="center" class="altbg1">
							<b>100%</b>
						</td>
						<td align="center" class="altbg1">
							<b>150%</b>
						</td>
						<td colspan="1" align="center" class="altbg1">
							<b>权重(100%)</b>
						</td>
						<td colspan="1" align="center" class="altbg1">
							<b>实际值(只能是数值)</b>
						</td>
						<td colspan="1" align="center" class="altbg1">
							<b>得分</b>
						</td>
						<td align="center" class="altbg1" colspan="3">
							<b>操作</b>
						</td>
					</tr>
				</thead>
				<tbody>
					<!-- 显示已经定义的部门指标 	<div title="部门指标库" style="display: none" onclick="show(this)">dddddd</div>-->
					<tr>
						<td colspan="12">
							<table id="indexDatabase" title="部门指标库" style="display: none"
								>
								<c:forEach items="${DepartEmpIndexs}" var="item_Index"
									varStatus="state">
									<tr>
										<td align="center" class="altbg1">
											<input type="checkbox" value="${state.index }"
												name="checkFlag"
												onclick="checkHasIndex('${item_Index.tindexTarget.id}',this)">
											${item_Index.tindexTarget.name }
										</td>
										<td align="center" class="altbg1">
											${item_Index.tindexTarget.formula }
										</td>
										<td align="center" class="altbg1">
											${item_Index.tindexTarget.type }
										</td>
										<td align="center" class="altbg1">
											${item_Index.tindexTarget.isChoice }
										</td>
										<td align="center" class="altbg1">
											${item_Index.tindexTarget.a }
										</td>
										<td align="center" class="altbg1">
											${item_Index.tindexTarget.b}
										</td>
										<td align="center" class="altbg1">
											${item_Index.tindexTarget.c }
										</td>
										<input type="hidden" name="fdpIndex"
											value="${item_Index.tindexTarget.id}">
								
									
										
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
					<!-- 显示已经定义的指标 -->
					<c:forEach items="${empIndexs}" var="item" varStatus="state">
						<tr align="center" class="altbg1">
							<td>
								${item.index.name }
							</td>
							<td align="center" class="altbg1">
								${item.index.formula }
							</td>
							<td align="center" class="altbg1">
								${item.index.type }
							</td>
							<td align="center" class="altbg1">
								${item.index.isChoice }
							</td>
							<td align="center" class="altbg1">
								${item.index.a }
							</td>
							<td align="center" class="altbg1">
								${item.index.b }
							</td>
							<td align="center" class="altbg1">
								${item.index.c }
							</td>
							<input type="hidden" name="empIndexID" value="${item.id }">
							<td>
								<input type="text" name="fpercentage"
									id="Weight[${state.index }]" value="${item.percentage }"
									readonly="readonly" size="5"
									onclick="checkType('${item.index.isChoice }',this)"
									onblur="check_Weight('table3')">
							</td>
							<td>
								<input type="text" name="fact_output"
									value="${item.act_output }" size="5"
									onblur="testCal('${item.index.a }','${item.index.b }','${item.index.c }',this,'${state.index }')">
							</td>
							<td>
								<input id="score[${state.index }]" name="score"
									onmouseover="checkType2('${item.index.type }',this)"
									onchange="checkScore(this)" value="${item.score }"
									 style="background-color:  ${item.index.isReadOnly }" size="5">
							</td>
							<td>
								<img src="images/del.gif" id="image"
									onclick="delEmpIndex('${item.id }',this,'${state.index }')">
							</td>
							<td>
							
								<input type="button" value="更新"
									onclick="update_Company_Index('${item.index.id }','3','${item.id }','${item.act_output }','${item.index.departID }')" />
							</td>
							<td align="center" class="altbg1">
											<input type="button" value="拷贝" onclick="copytoOthers('${item.index.id }')">
							</td>
					</c:forEach>

				</tbody>
			</table>

			<table>
				<tr>
					<td>
						公司得分
					</td>
					<td>
						个人得分
					</td>
					<td>
						总得分
					</td>
				</tr>
				<tr>
					<td colspan="3">
						${result }
					</td>
				</tr>
			</table>
		</form>
		 <div id="itemselector" class="demo-ct"></div>
		
	</body>
</html>
