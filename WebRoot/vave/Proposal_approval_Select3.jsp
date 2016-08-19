<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
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

  <body >
	<tr><jsp:include flush="true" page="Company/Select_TopBar_Form.jsp"></jsp:include></tr>

  
   <table id="tblSort">
   
   <thead>
   <tr style="background-color: #eeeeee">

      <th onclick="sortTable('tblSort', 1)" 
                        style="cursor:pointer">是否</th>
   
 
   <th>状态</th>
  
    <th onclick="sortTable('tblSort',3,'date')" 
                        style="cursor:pointer">日期</th>

   <td>提案人</td>
 	<td>提案部门</td>
 
 	 <th onclick="sortTable('tblSort', 6,'int')" 
                        style="cursor:pointer">预计节约成本(元)</th>

 	<th onclick="sortTable('tblSort', 7,'date')" 
                        style="cursor:pointer">预计完成日期</th>
   <th colspan="2">主题</th>
   <th onclick="sortTable('tblSort', 9)" 
                        style="cursor:pointer">来源</th>

   </tr>
   </thead>
   <tbody>
 <c:forEach items="${Company_Wide_Suggests}" var="item" >
   <tr>
   <td><img src="img/mail/${item.isOpen }.gif"><br><br></td>
   <td style="display: none">ta${item.isOpen }.gif<br><br></td>
   <td>${item.state_context }</td>
   <td> <fmt:formatDate value="${item.lastModifyTime }" pattern="dd/MM/yyyy"/> </td>
   <td>${item.proposalPerson.name }</td>
   <td>${item.proposalPerson.tdepartment.name }</td>
   <td>${item.exceptSaving }</td>
   <td>${item.expectFinishDate }</td>
   <td colspan="2"><a href="proposal.do?actionType=doSelectByProposalIDC&proposalID=${item.id }">${item.title }</a></td>
   <td>${item.supplyName }</td>
   <td>

	
  </td>
   </tr>
   </c:forEach>
   </tbody>
  </table>
  
  </body>


        <script type="text/javascript">
            
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
</html>
