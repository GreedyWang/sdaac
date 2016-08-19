<%@ page language="java" import="java.util.*,common.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
   //Page pagebean =new Page(7);
  //pagebean.setTotalCount(150);
  //request.setAttribute("page",pagebean);
 // request.setAttribute("url","page.jsp");
    			
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>分面的工具页</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="css/common.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="js/common.js"></script>
	</head>

	<body>

		<c:if test="${page.totalCount == 0}">
			<table border="0" align="right" cellpadding="3" cellspacing="3">
				<tr>
					<td align="center">&nbsp; 
						<font color="#ff0000"><strong>没有可显示的项目</strong> </font>
					</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${page.totalCount != 0}">
			<c:set var="p_count" value="${page.pageCount}"></c:set>
			<c:set var="p_index" value="${page.pageIndex}"></c:set>
			<table border="0" align="right" cellpadding="3" cellspacing="3">
				<tr>
					<td class="pageNonClickable">
						共
						<b>${page.totalCount}</b>项，
						<b>${page.pageCount}</b>页：
					</td>
					<%--是否显示上一页 --%>
					<c:if test="${page.hasPrevious}">
						<c:set var="p_tmp" value="${p_index - 1}"></c:set>
						<td class="pageClickable" onMouseOver="hoverPage(this)"
							onMouseOut="normalPage(this)"
							onClick="location.assign(getPageLocation(${p_tmp}));">
							上一页
						</td>
					</c:if>
					<c:if test="${!page.hasPrevious}">
						<td class="pageUnclickable">
							上一页
						</td>
					</c:if>
				<%-- 	 如果前面页数过多,显示"...  --%>
					<c:if test="${p_index > 5}">
						<c:set var="p_prevPages" value="${p_index - 5}"></c:set>
						<c:set var="p_start" value="${p_index - 4}"></c:set>
						<td class="pageClickable" onMouseOver="hoverPage(this)"
							onMouseOut="normalPage(this)"
							onClick="location.assign(getPageLocation(${p_prevPages}))">
							…
						</td>
					</c:if>
					<c:if test="${p_index <= 5 }">
						<c:set var="p_start" value="1"></c:set>
					</c:if>
					<%--  显示当前页前后页附近的页${p_index},${p_start},${p_end} --%>
					
					<c:set var="p_end" value="${p_index + 4}"></c:set>
					<c:if test="${p_end > p_count}">
						<c:set var="p_end" value="${p_count}"></c:set>
					</c:if>
					<c:forEach begin="${p_start}" end="${p_end}" var="i">
						<c:if test="${i == p_index}">
							<td class="pageNonClickable">
								<b><font color="#FF0084">${i}</font>
								</b>
							</td>
						</c:if>
						<c:if test="${i != p_index}">
							<td class="pageClickable" onMouseOver="hoverPage(this)"
								onMouseOut="normalPage(this)"
								onClick="location.assign(getPageLocation(${i}))">
								${i}
							</td>
						</c:if>
					</c:forEach>
					<%--如果后面页数过多,显示"...": --%>
					<c:if test="${p_end < p_count}">
						<c:set var="p_nextPages" value="${p_end +1}"></c:set>
						<td class="pageClickable" onMouseOver="hoverPage(this)"
							onMouseOut="normalPage(this)"
							onClick="location.assign(getPageLocation(${p_nextPages}))">
							…
						</td>
					</c:if>
				<%--是否显示下一页 --%>
					<c:if test="${page.hasNext}">
						<c:set var="p_tmp" value="${p_index + 1}"></c:set>
						 <td class="pageClickable" onMouseOver="hoverPage(this)" onMouseOut="normalPage(this)" onClick="location.assign(getPageLocation(${p_tmp}))">下一页</td>
						 
					</c:if>
					<c:if test="${!page.hasNext}">
						<td class="pageUnclickable">下一页</td>
					</c:if>
				</tr>
			</table>
		</c:if>
 
	</body>
</html>
