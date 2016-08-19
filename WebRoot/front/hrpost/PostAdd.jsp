<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>岗位管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/style_admin.css">
	<SCRIPT src="js/jquery.js" type=text/javascript></SCRIPT>
	<script type="text/javascript" src="js/util/jquery.multiselect.js"></script>
	
	<STYLE>.demo {
	BORDER-RIGHT: #333 1px solid; BORDER-TOP: #333 1px solid; FONT-SIZE: 14px; BACKGROUND: #eee; MARGIN: 10px; BORDER-LEFT: #333 1px 
	solid; WIDTH: 540px; BORDER-BOTTOM: #333 1px solid; HEIGHT: 150px
	}
	
	.sel {
		MARGIN: 10px
	}
	.select_div {
		BORDER-RIGHT: #333 1px solid; BORDER-TOP: #333 1px solid; OVERFLOW-Y: auto; FONT-SIZE: 12px; BORDER-LEFT: #333 1px solid; BORDER-
	
	BOTTOM: #333 1px solid; FONT-FAMILY: Verdana
	}
	.select_div TABLE {
		BORDER-TOP-WIDTH: 0px; TABLE-LAYOUT: fixed; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px
	}
	.select_div .select_div_title {
		PADDING-RIGHT: 5px; PADDING-LEFT: 5px; FONT-WEIGHT: bold; FONT-SIZE: 14px; BACKGROUND: #000; PADDING-BOTTOM: 5px; COLOR: #fff; 
	
	PADDING-TOP: 5px; WHITE-SPACE: nowrap; HEIGHT: 20px
	}
	.select_div TR {
		FONT-WEIGHT: bold; COLOR: #000; HEIGHT: 24px
	}
	.select_div .odd {
		BACKGROUND: #f77
	}
	.select_div .even {
		BACKGROUND: #fff
	}
	.select_div .selected {
		BACKGROUND: #d9e9fe
	}
	.select_div TD {
		PADDING-LEFT: 5px; WHITE-SPACE: nowrap
	}
	.select_div .right_td {
		PADDING-RIGHT: 5px; PADDING-LEFT: 5px; PADDING-BOTTOM: 5px; WIDTH: 20px; PADDING-TOP: 5px; TEXT-ALIGN: right
	}
	.select_div .select_div_bottom {
		PADDING-RIGHT: 8px; PADDING-LEFT: 8px; BACKGROUND: #ddd; PADDING-BOTTOM: 8px; PADDING-TOP: 8px; TEXT-ALIGN: right
	}
	</STYLE>
  </head>
   <SCRIPT>
	/**
	 * 参数说明：
	 * width：定义该多选div的宽度
	 * height:定义该div的最大高度
	 * iframe:定义是否启用iframe，如果有多个select，并需要盖住的话，将iframe设为true
	 */
	$(function(){
		$("#select_demo1").multiSelect({width:250,height:300,iframe:true});
		
	});
	
	function doSubmit(){
	alert(	document.forms.length)
	}

	</SCRIPT>
  <body>
  <!-- 奖金编码 -->
  
 
   <form id="2" action="post.do?actionType=doInsert" method="post">
   <table cellspacing="1" cellpadding="4" width="90%"
			class="tableborder" id="table2" style="position: relative;top: 100px">
   <thead>
   <tr>
	<td colspan="7" class="header">浮动岗位指标添加 
	</td>
 	 </tr>
   <tr>
   <td align="center" class="altbg1"><b>岗位编号</b></td>
   <td align="center" class="altbg1"><b>岗位名称</b></td>
   <td align="center" class="altbg1"><b>岗位类型</b></td>
   <td align="center" class="altbg1"><b>指标</b></td>
   <td align="center" class="altbg1"><b>目标数量</b></td>
  
   </tr> </thead>
  <tbody>

  
  
 <tr>  
	<td align="center" class="altbg1"><input name="item.id" type="text" ></td>
   <td align="center" class="altbg1"><input name="item.productName" type="text" /></td>

  
    <td align="center" class="altbg1"><select name="item.tpostType.type">
    	<option value="A">A</option>
    	<option value="B">B</option>
    	<option value="C">C</option>
    	<option value="D">D</option>
    </select> </td>  
    <!-- no use -->
       <td align="center" class="altbg1"><input readonly="readonly" name="item.target" type="text" /></td>  
     <td align="center" class="altbg1"><input readonly="readonly" name="" type="text" /></td>  
    <input type="hidden" name="item.type" value="1">
   </tr> 
   <tr><td class="header" colspan="7">
   <input  type="submit" value="提交"/></td> </tr>
  
   </tbody>
   </table>
    </form>
    
    <form>
    <table title="图号拷贝">
    	<tr>
    		<td></td>
    		<td></td>
    		<td></td>
    		<td></td>
    	</tr>
    </table>
    </form>
   </body>
   </html>
