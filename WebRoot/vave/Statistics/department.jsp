<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'department.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/util/fff.js" charset="utf-8"></script>
	 <style type="text/css" media="screen">@import url(css/vave.css);</style>
  </head>
  <script type="text/javascript">
  	function showTbody()
  	{
  		var target=document.getElementById('tbody')
  	
  		if(target.style.display=='block')
  		{
  			target.style.display='none'
  			return 
  		}
  		if(target.style.display=='none')
  		{
  			target.style.display='block'
  			return 
  		}
  	}
  	

   
   //转换器，将列的字段类型转换为可以排序的类型：String,int,float
   function convert(sValue, sDataType) {
       switch(sDataType) {
           case "int":
               return parseInt(sValue);
           case "float":
               return parseFloat(sValue);
           case "date":
               return new Date(Date.parse(sValue));
           default:
               return sValue.toString();
       
       }
   }
   
   //排序函数产生器，iCol表示列索引，sDataType表示该列的数据类型
   function generateCompareTRs(iCol, sDataType) {

       return  function compareTRs(oTR1, oTR2) {
                   var vValue1 = convert(oTR1.cells[iCol].firstChild.nodeValue, sDataType);
                   var vValue2 = convert(oTR2.cells[iCol].firstChild.nodeValue, sDataType);

                   if (vValue1 < vValue2) {
                       return -1;
                   } else if (vValue1 > vValue2) {
                       return 1;
                   } else {
                       return 0;
                   }
               };
   }
   
   //排序方法
   function sortTable(sTableID, iCol, sDataType) {
       var oTable = document.getElementById(sTableID);
       var oTBody = oTable.tBodies[0];
       var colDataRows = oTBody.rows;
       var aTRs = new Array;
       
       //将所有列放入数组
       for (var i=0; i < colDataRows.length; i++) {
           aTRs[i] = colDataRows[i];
       }
        
       //判断最后一次排序的列是否与现在要进行排序的列相同，是的话，直接使用reverse()逆序
       if (oTable.sortCol == iCol) {
           aTRs.reverse();
       } else {
           //使用数组的sort方法，传进排序函数
           aTRs.sort(generateCompareTRs(iCol, sDataType));
       }

       var oFragment = document.createDocumentFragment();
       for (var i=0; i < aTRs.length; i++) {
           oFragment.appendChild(aTRs[i]);
       }

       oTBody.appendChild(oFragment);
       //记录最后一次排序的列索引
       oTable.sortCol = iCol;
   }

    
  </script>
  <body >
  <h3>联系人:董峰,电话:38663139,E-MAIL:dong.feng@sdaac.com</h3>
  
  <form action="statistics.do?actionType=doDepartProposalCount" method="post">
  	<label></label>
  	<select name="searchYear" id="year">
  	<option value="选择年份">选择年份</option>
  	<c:forEach begin="2007" end="2020" step="1" var="year">		
  	<option value="${year }">${year }</option>
  	</c:forEach>
  	</select>
  	<input type="submit" value="查询">
  	<h3>${year}统计结果</h3>
  </form>
  <div style=" padding:5px;height:115px;color:black;overflow: auto">
  <table>
  <thead>
  <tr id="intro_right">
  <td>部门名称</td>
  <td>提案总数</td>
  <td>批准数</td>
  <td>完成数</td>
  </tr>
  </thead>
    <c:forEach items="${departProposalCount}" var="item">
    	<tr>
    		<td>${item.departmentName }</td>
    		<td>${item.proposalNum }</td>
    		<td>${item.finishNum }</td>
    		<td>${item.doNum }</td>
    	</tr>
    </c:forEach>
    </table>
    </div>
   <div style=" padding:5px;height:115px;color:black;overflow: auto">
   <table>
  <thead>
  <tr id="intro_right">
  <td>供应商</td>
  <td>提案总数</td>
  <td>批准数</td>
  <td>完成数</td>
  </tr>
  </thead>
    <c:forEach items="${SupplyCount}" var="item" varStatus="state" >
    	
    	<tr>
    		<td>${item.departmentName }</td>
    		<td>${item.proposalNum }</td>
    		<td>${item.finishNum }</td>
    		<td>${item.doNum }</td>
    	</tr>
    
    </c:forEach>
    </table>
   </div>
   <div style=" padding:5px;height:250px;color:black;overflow: auto">
  <table id="tblSort">
  <thead>
  <tr id="intro_right">
  <td><label onclick="sortTable('tblSort',0,'int')" >排名</label></td>
  <td>员工名称</td>
  <td ><label onclick="sortTable('tblSort',2)" >提案人部门</label></td>
  <td>提案总数</td>
  <td>批准数</td>
  <td>完成数 <img src="images/default/select_right.gif" onclick="showTbody()"> </td>
  </tr>
  </thead>
  <tbody id="tbody" style="display: block">
    <c:forEach items="${personalProposal}" var="item" varStatus="state">
    	
    	<tr>
    		<td>${state.index+1 }</td>
    		<td>${item.uname }</td>
    		<td>${item.departmentName }</td>
    		<td>${item.proposalNum }</td>
    		<td>${item.finishNum }</td>
    		<td>${item.doNum }</td>
    	</tr>
    	
    </c:forEach>
    </tbody>
    </table>  
    </div>
    
    <div style=" padding:5px;height:250px;color:black;overflow: auto">
  <table id="tblSort">
  <thead>
  <tr id="intro_right">
  <td><label onclick="sortTable('tblSort',0,'int')" >排名</label></td>
  <td>员工名称</td>
  <td ><label onclick="sortTable('tblSort',2)" >提案人部门</label></td>
  <td>提案总数</td>
  <td>批准数</td>
  <td>完成数 <img src="images/default/select_right.gif" onclick="showTbody()"> </td>
  </tr>
  </thead>
  <tbody id="tbody" style="display: block">
    <c:forEach items="${personalProposal}" var="item" varStatus="state">
    	
    	<tr>
    		<td>${state.index+1 }</td>
    		<td>${item.uname }</td>
    		<td>${item.departmentName }</td>
    		<td>${item.proposalNum }</td>
    		<td>${item.finishNum }</td>
    		<td>${item.doNum }</td>
    	</tr>
    	
    </c:forEach>
    </tbody>
    </table>  
    </div>
  </body>
</html>
