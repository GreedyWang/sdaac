<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="/WEB-INF/c.tld"%>
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
		<meta http-equiv="description" content="This is my page">
		<link href="css/table.css" rel="stylesheet" type="text/css" />
		<script type='text/javascript' src='/bpp/dwr/interface/bfManager.js'></script>
		<script type='text/javascript' src='/bpp/dwr/engine.js'></script>
		<script type='text/javascript' src='front/hrpost/th/FigureAccount.js'></script>
		<script type="text/javascript" src="js/jquery.js"></script>
		<style type="text/css">
.red {
	background-color: #4871a5;
}

body {
	margin: 0px
}

#Loading {
	position: absolute;
	z-index: 10;
	left: 10px;
	top: 10px;
	border: 1px #666666 solid;
	background: #eeeeee;
	width: 10px;
	height: 10px
}
</style>
		<script language="JavaScript"> 

function $(){return document.getElementById?document.getElementById(arguments[0]):eval(arguments[0]);}
var OverH,OverW,ChangeDesc,ChangeH=50,ChangeW=50,Excelndex;

function OpenDiv(_Dw,_Dh,_Desc,index) {
$("Loading").innerHTML="";
OverH=_Dh;OverW=_Dw;ChangeDesc=_Desc;
Excelndex=index;
$("Loading").style.display='';
if(_Dw>_Dh){ChangeH=Math.ceil((_Dh-10)/((_Dw-10)/50))}else if(_Dw<_Dh){ChangeW=Math.ceil((_Dw-10)/((_Dh-10)/50))}
$("Loading").style.top=(document.documentElement.clientHeight-10)/2+"px";
$("Loading").style.left=(document.documentElement.clientWidth-10)/2+"px";
OpenNow()
}
var Nw=10,Nh=10;

function OpenNow() {
if (Nw>OverW-ChangeW)ChangeW=2;
if (Nh>OverH-ChangeH)ChangeH=2;
Nw=Nw+ChangeW;Nh=Nh+ChangeH;

if(OverW>Nw||OverH>Nh) {
	if(OverW>Nw) {
	$("Loading").style.width=500+"px";
	$("Loading").style.left=300+"px";
	}
	if(OverH>Nh) {
	$("Loading").style.height=500+"px";
	$("Loading").style.top=100+"px";
	}
	window.setTimeout("OpenNow()",10)
	}else{
	Nw=10;Nh=10;ChangeH=50;ChangeW=50;
	$("Loading").innerHTML='<iframe id="context" width="100%" height="90%" src="front/hrpost/addExcel'+Excelndex+'.jsp"></iframe>双击关闭【X】';
	}
}

		function showDiv(index)
		{
			var elem=document.getElementById('inputExcel')
			var ifContext=document.getElementById('context');
			if(index==0)
			{
				ifContext.src='front/hrpost/addExcel0.jsp';
			}
			if(index==1)
			{
				ifContext.src='front/hrpost/addExcel.jsp';
			}
			if(index==2)
			{
				ifContext.src='front/hrpost/addExcel2.jsp';
			}
			if(index==3)
			{
				ifContext.src='front/hrpost/addExcel3.jsp';
			}
			if(elem.style.display=='block')
			{
				elem.style.display='none'
				return 
			}
			if(elem.style.display=='none')
			{
				elem.style.display='block'
				return 
			}
		}
		
		function showMore(figureId)
		{	
			  window.open('/bpp/figureAndPostId.do?actionType=selectInbounds&figureId='+figureId,null,
				"height=600,width=600,status=yes,toolbar=no,menubar=no,location=no");
		}
		
		function checkVaild(){
			var figureId = document.getElementById('figureId').value
			var remark = document.getElementById('remark').value
			var productArea = document.getElementById('productArea').value
			if(figureId!=''||remark!=''||productArea!=''){
				return true;
			}else{
				alert('请出入任意条件')
				return false;
			}
		}
	</script>

	</head>

	<body>


		<div id="Loading"
			style="top: 169px; left: 391px; width: 498px; height: 498px; z-index: 3; display: none;"
			ondblclick="this.style.display='none'">
		</div>



		<form action="figureAndPostId.do?actionType=selectFigure"
			method="post" onsubmit="return checkVaild()">
			<table>
				<tr>
					<td>
						图号
					</td>
					<td>
						<input id="figureId" type="text" name="bf.figureId">
					</td>
					<td>
						物料号
					</td>
					<td>
						<input id="remark" type="text" name="bf.remark">
					</td>
					<td>
						车型
					</td>
					<td>
						<input type="text" name="bf.carType">
					</td>
					<td>
						车间
					</td>
					<td>
						<input id="productArea" type="text" name="bf.productArea">
					</td>
					<td>
						<input type="submit" value="搜索">
					</td>
				</tr>
			</table>
		</form>


		<table id="table1">
			<tr>
				<td>
					图号
				</td>
				<td>
					物料号
					<img src="style/icons/excel.gif"
						onclick="OpenDiv(500,500,'双击关闭',0)">
				</td>
				<td>
						车间
					</td>
				<td>
					车型
				</td>
				<td>
					标准工时
					<img src="style/icons/excel.gif"
						onclick="OpenDiv(500,500,'双击关闭',1)">
				</td>
				<td>
					入库数
					<img src="style/icons/excel.gif"
						onclick="OpenDiv(500,500,'双击关闭',2)">
				</td>
				<td>
					类型
				</td>
				<td>
					加工
				</td>
				<td>
					翅片数
					<img src="style/icons/excel.gif"
						onclick="OpenDiv(500,500,'双击关闭',3)">
				</td>


				<td colspan="2">
					操作
				</td>

			</tr>
			<form action="figureAndPostId.do?actionType=updateFigure"
				method="post">
				<c:forEach items="${BounceFigures}" var="item" varStatus="state">
					<tr>
						<td>
							${item.figureId }
							<input id="bfs[${state.index }].figureId"
								name="bfs[${state.index }].figureId" type="hidden"
								value="${item.figureId }">

							<input id="bfs[${state.index }].id"
								name="bfs[${state.index }].id" type="hidden" value="${item.id }">
						</td>
						<td>
							<input id="bfs[${state.index }].remark"
								name="bfs[${state.index }].remark" type="text"
								value="${item.remark }" size="10">
						</td>
						<td>
							<input id="bfs[${state.index }].productArea"
								name="bfs[${state.index }].productArea" type="text"
								value="${item.productArea }" size="10">
						</td>
						<td>
							<input id="bfs[${state.index }].carType"
								name="bfs[${state.index }].carType" type="text"
								value="${item.carType }" size="10">
						</td>
						<td>
							<input id="bfs[${state.index }].standWorkTime"
								name="bfs[${state.index }].standWorkTime" type="text"
								value="${item.standWorkTime }" size="10">
						</td>

						<td>
							<label onclick="showMore('${item.figureId }')">
								more
							</label>
						</td>
						<td>
							<input id="bfs[${state.index }].type"
								name="bfs[${state.index }].type" type="text"
								value="${item.need }" size="10">
						</td>
						<td>
							<input id="bfs[${state.index }].need"
								name="bfs[${state.index }].need" type="text"
								value="${item.need }" size="10">
						</td>
						<td>
							<input id="bfs[${state.index }].picesNum"
								name="bfs[${state.index }].picesNum" type="text"
								value="${item.picesNum }" size="10">
						</td>


						<td>
							<img src="style/icons/reload.png"
								onclick="update('${item.figureId }','${state.index }')">
						</td>
						<td>
							<img src="style/icons/delete.gif"
								onclick="del('${item.id }',${item.productArea },this)">
						</td>

					</tr>
				</c:forEach>
			<tr>
				<td>
					<input type="submit" value="更新">
				</td>
			</tr>
			</form>
		</table>
	</body>
</html>
