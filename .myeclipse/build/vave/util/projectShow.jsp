<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-1_0-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  
  <body> 
 <table>
 <tbody>
  <c:if test="${proposalMore!=null}" >
  <tr id="intro_right">
  <td>预计节约金额(元)</td>
  <td>预计完成时间</td>
  </tr>
 <tr>
 
 <td>${proposalMore.expectSaving}</td>
  <td>${proposalMore.expectFinishDate} </td>
 </tr>
 </c:if>
 
 </tbody>
 </table>
  </body>
</html>
