<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
  <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>vave提案单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style type="text/css" media="screen">@import url(css/vave.css);</style>	

  </head>
  <body >
   <table width="100%"> 
  <tbody>
   <tr id="intro_right">
   <td rowspan="2" id="title" style="font-size: 20">SDAAC VAVE提案表</td>
   <td>提案来源</td>
   <td>提案编号</td>
   <td>提案状态</td>
   <td>最新修订时间</td>
   </tr>
   <tr>
   <td>${PropsalDetailByID.source }<br></td>              
   <td>${PropsalDetailByID.id}<br></td>
   <td align="middle">${PropsalDetailByID.state_context}<br></td>
   <td>${PropsalDetailByID.lastModifyTime }</td>
   </tr>
   </tbody>
   </table>
   <table width="100%"> 
   <tbody>
   <tr>
  <c:if test="${PropsalDetailByID.source=='供应商'}">
   <td>供应商（电话/公司）:</td>
   <td>${PropsalDetailByID.supply.phone }/${PropsalDetailByID.supply.simpleName }</td>
   </c:if>
   <tr>
   <td>提案人:</td>
   <td>${PropsalDetailByID.collectionPersion.name }</td> 
   <td>${PropsalDetailByID.proposalPerson.tdepartment.name }</td>
   </tr>
 	  </tbody>
   </table>
   <table width="100%"> 
   <tbody>
   <tr>
   <td width="20%" >主   题:</td>
   <td>${PropsalDetailByID.title}</td>
   </tr>
    <tr>
   <td width="20%" >备注:</td>
   <td>${PropsalDetailByID.remarks}</td>
   </tr>
   </tbody>
   </table>
   <table width="100%"> 
   <tbody>
    <tr id="intro_right">
    <td>零件名称</td>
    <td>零件号</td>
    <td>顾客</td>
    <td>适用车型</td>
    <td>系统年产量</td>
    <td>零件年用量</td>
    <td>单位</td>
    </tr>
   <c:forEach items="${PropsalDetailByID.tthemes}" var="item">
   <tr>
   <td>${item.partsName}</td>
   <td>${item.partsId }</td>
    <td>${item.customer }</td>
    <td>${item.applicableModels }</td>
    <td align="right"><fmt:formatNumber value="${item.systemAnnualOutputs }" pattern="#,#00.0#"/></td>
    <td align="right"><fmt:formatNumber value="${item.partsAnnualOutputs}" pattern="#,#00.0#"></fmt:formatNumber> </td>
    <td>${item.company }</td>
    </tr>
     </c:forEach>   
    </tbody>
    </table>
    <table width="100%"> 
    <tbody>
    <tr id="intro_right"><td >现在方案：</tr>
  
    <tr><td colspan="9">${PropsalDetailByID.curProgram }</td></tr>
 
    <tr id="intro_right"><td>代用方案：</td></tr>
    
   <tr><td id="context">${PropsalDetailByID.ins_Program }
   </td></tr>     
    <tr>
   </tbody>
    </table>
    <table width="100%"> 
    <tbody>
    <td>建议类别:</td>
    <td> ${PropsalDetailByID.suggestionType }</td>
    </tr>
    
    </tbody>
    </table>   
     <table width="100%">
    <c:if test="${mainSug.suggest_context!=null}"> 
    <tr><td id="intro_right" colspan="6">本部门审批</td></tr>
    <tr>
    <td>本部门意见:</td>
    <td>${mainSug.suggestionType }</td>
    <td>批准人：${mainSug.approved_Name }</td>
    <td>主要实施部门:${mainSug.implement_departmentName }</td>
    <td>日期</td><td>${mainSug.suggestionDate }</td>
    </tr>
    <tr>
    <td colspan="6">评定:${mainSug.suggest_context }</td>
    </tr>
    </c:if>
     <c:if test="${OtherSuggestion!=null}">    
 
    <tr>
    <td id="intro_right" colspan="6">外部门审批</td></tr>
  
  	<c:forEach items="${OtherSuggestion}" var="otherSug">
   	<tr>
   	<td>外部门名称:${otherSug.departmentName }</td>
   	<td>批准人：${otherSug.approveName }</td>
   	<td colspan="2">外部门意见:${otherSug.suggestionType }</td>
   	<td colspan="2">日期:${otherSug.suggestionDate }</td>
   	</tr>
   	<tr>
   	<td colspan="6">评定:${otherSug.suggest_context }</td>
   	</tr>
   	</c:forEach>
	</c:if>
		
	<c:if test="${companySugs!=null}">	
	 
    	<tr><td id="intro_right" colspan="6">公司审批</td></tr>
 	
     <tr>
     <td colspan="4">评审组意见:${companySugs.suggestionType }</td>
    
    <td colspan="2">日期:${companySugs.suggestionDate }</td>
    </tr>
       
    <tr>
    <td colspan="6">评定:${companySugs.suggestionContext }</td>
    </tr>
    </c:if>	
    	
  
 	</table>	
</body>
</html>
